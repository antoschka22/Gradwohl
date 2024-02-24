public class PredictionResult {
    private String productName;
    private float frischPrediction;
    private float teigigPrediction;

    // Constructor
    public PredictionResult(String productName, float frischPrediction, float teigigPrediction) {
        this.productName = productName;
        this.frischPrediction = frischPrediction;
        this.teigigPrediction = teigigPrediction;
    }

    // Getters
    public String getProductName() {
        return productName;
    }

    public float getFrischPrediction() {
        return frischPrediction;
    }

    public float getTeigigPrediction() {
        return teigigPrediction;
    }
}