import org.tensorflow.types.TFloat32;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PredictionServiceImpl implements IPredictionService {
    private final DynamicMinMaxScaler scaler;
    private final TensorFlowModelService modelService;

    public PredictionServiceImpl(DynamicMinMaxScaler scaler, TensorFlowModelService modelService) {
        this.scaler = scaler;
        this.modelService = modelService;
    }

    @Override
    public List<PredictionResult> predict(float[][] unscaledData, List<String> productNames) {
        List<PredictionResult> results = new ArrayList<>();
        float[][] scaledData = scaler.scale(unscaledData);
        TFloat32 inputTensor = TensorFlowDataUtils.convertArrayToTensor(scaledData);
        TFloat32 predictionTensor = modelService.predict(inputTensor);
        float[][] scaledPredictions = TensorFlowDataUtils.convertTensorToArrayAndClose(predictionTensor);
        float[][] originalPredictions = scaler.inverseScale(scaledPredictions);

        for (int i = 0; i < originalPredictions.length; i++) {
            String productName = productNames.get(i);
            float frischPrediction = originalPredictions[i][0];
            float teigigPrediction = originalPredictions[i][1];
            results.add(new PredictionResult(productName, frischPrediction, teigigPrediction));
        }

        return results;
    }
}