import java.util.List;

public interface IPredictionService {
    List<PredictionResult> predict(float[][] unscaledData, List<String> productNames);
}