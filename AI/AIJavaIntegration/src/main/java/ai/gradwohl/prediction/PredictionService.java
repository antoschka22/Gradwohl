package ai.gradwohl.prediction;

import ai.gradwohl.model.PredictionResult;
import ai.gradwohl.scaler.DynamicMinMaxScaler;
import ai.gradwohl.tensorflow.TensorFlowDataUtils;
import ai.gradwohl.tensorflow.TensorFlowModelService;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.service.WarenbestellungsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.types.TFloat32;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class PredictionService {
    private final DynamicMinMaxScaler scaler;
    private final TensorFlowModelService modelService;
    private final WarenbestellungsService warenbestellungsService;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    public PredictionService(DynamicMinMaxScaler scaler, TensorFlowModelService modelService,
                             WarenbestellungsService warenbestellungsService) {
        this.scaler = scaler;
        this.modelService = modelService;
        this.warenbestellungsService = warenbestellungsService;
    }

    public List<PredictionResult> predictForFiliale(Filiale filiale) throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<List<PredictionResult>> futurePrediction = CompletableFuture.supplyAsync(() -> {
            List<Map<String, Object>> rawData = warenbestellungsService.getLast30DaysOrdersByFiliale(filiale);
            float[][] scaledData = prepareAndScaleData(rawData);
            TFloat32 predictionsTensor = modelService.predict(TFloat32.tensorOf(StdArrays.ndCopyOf(scaledData)));
            float[][] predictions = TensorFlowDataUtils.convertTensorToArrayAndClose(predictionsTensor);
            return applyCleaningLogicAndConvertToResult(rawData, predictions);
        }, executorService);

        return futurePrediction.get(30, TimeUnit.SECONDS);
    }

    private float[][] prepareAndScaleData(List<Map<String, Object>> rawData) {
        float[][] data = rawData.stream()
                .map(d -> new float[]{(float) d.get("frisch"), (float) d.get("teigig")})
                .toArray(float[][]::new);
        return scaler.scale(data);
    }

    private List<PredictionResult> applyCleaningLogicAndConvertToResult(List<Map<String, Object>> rawData, float[][] predictions) {
        List<PredictionResult> results = new ArrayList<>();
        for (int i = 0; i < rawData.size(); i++) {
            Map<String, Object> record = rawData.get(i);
            String productId = (String) record.get("product_name");
            float frisch = predictions[i][0];
            float teigig = predictions[i][1];

            int id = Integer.parseInt(productId);
            boolean isTeigig = id >= 2000 && id < 3000;

            if (isTeigig) {
                // Adjust teigig products
                results.add(new PredictionResult(String.valueOf(id - 2000), 0, teigig)); // Teigig to frisch
            } else {
                // Adjust frisch products, if they have teigig value, add a new product
                results.add(new PredictionResult(productId, frisch, 0)); // Original as frisch
                if (teigig > 0) {
                    results.add(new PredictionResult(String.valueOf(id + 2000), 0, teigig)); // New teigig
                }
            }
        }
        return results;
    }

    @PreDestroy
    public void destroy() {
        executorService.shutdownNow();
    }
}
