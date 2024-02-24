import org.springframework.stereotype.Service;
import org.tensorflow.types.TFloat32;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class PredictionService {
    private final DynamicMinMaxScaler scaler;
    private final TensorFlowModelService modelService;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public PredictionService(DynamicMinMaxScaler scaler, TensorFlowModelService modelService) {
        this.scaler = scaler;
        this.modelService = modelService;
    }

    public List<PredictionResult> predict(float[][] unscaledData, List<String> productNames) {
        CompletableFuture<List<PredictionResult>> future = CompletableFuture.supplyAsync(() -> {
            float[][] scaledData = scaler.scale(unscaledData);
            TFloat32 inputTensor = TensorFlowDataUtils.convertArrayToTensor(scaledData);
            TFloat32 predictionTensor = modelService.predict(inputTensor);
            float[][] scaledPredictions = TensorFlowDataUtils.convertTensorToArrayAndClose(predictionTensor);
            return scaler.inverseScale(scaledPredictions);
        }, executorService);

        List<PredictionResult> results = new ArrayList<>();
        try {
            // Attempt to get the prediction results within a 15-second timeout
            float[][] originalPredictions = future.get(15, TimeUnit.SECONDS);
            for (int i = 0; i < originalPredictions.length; i++) {
                results.add(new PredictionResult(productNames.get(i), originalPredictions[i][0], originalPredictions[i][1]));
            }
        } catch (TimeoutException e) {
            // Timeout occurred, fall back to calculating the mean
            results = calculateMeanForLast30Days(unscaledData, productNames);
        } catch (InterruptedException | ExecutionException e) {
            // Handle other exceptions, possibly rethrow as a RuntimeException
            throw new RuntimeException("Error during prediction", e);
        }

        return results;
    }

    /**
     * Fallback in case TF is slow or throws an error, default activation after 15 sec
     * @param data
     * @param sequenceLength
     * @return List<PredictionResult>
     */
    private List<PredictionResult> calculateMeanForLast30Days(List<Map<String, Object>> data, int sequenceLength) {
        // Group the data by product
        Map<String, List<Map<String, Object>>> groupedData = data.stream()
                .collect(Collectors.groupingBy(record -> (String) record.get("product_name")));

        // Calculate the mean for each product
        List<PredictionResult> meanResults = new ArrayList<>();
        for (String productName : groupedData.keySet()) {
            List<Map<String, Object>> productData = groupedData.get(productName);
            double sumFrisch = 0;
            double sumTeigig = 0;
            int count = 0;

            for (Map<String, Object> record : productData) {
                sumFrisch += (Double) record.get("frisch");
                sumTeigig += (Double) record.get("teigig");
                count++;
            }

            double meanFrisch = count > 0 ? sumFrisch / count : 0;
            double meanTeigig = count > 0 ? sumTeigig / count : 0;
            meanResults.add(new PredictionResult(productName, meanFrisch, meanTeigig));
        }

        return meanResults;
    }

    @PreDestroy
    public void destroy() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
