package ai.gradwohl.scaler;

public class DynamicMinMaxScaler {
    private float[] featureMin;
    private float[] featureMax;

    public DynamicMinMaxScaler() {
    }

    /**
     * Scales the entire dataset based on global min and max values for each feature.
     *
     * @param data The original data to be scaled. Each inner array represents one record with features.
     * @return The scaled data.
     */
    public float[][] scale(float[][] data) {
        int numRows = data.length;
        int numFeatures = data[0].length;
        featureMin = new float[numFeatures];
        featureMax = new float[numFeatures];
        float[][] scaledData = new float[numRows][numFeatures];

        // Initialize min and max values
        for (int j = 0; j < numFeatures; j++) {
            featureMin[j] = Float.MAX_VALUE;
            featureMax[j] = Float.MIN_VALUE;
        }

        // Find global min and max for each feature
        for (float[] row : data) {
            for (int j = 0; j < numFeatures; j++) {
                if (row[j] < featureMin[j]) featureMin[j] = row[j];
                if (row[j] > featureMax[j]) featureMax[j] = row[j];
            }
        }

        // Scale data to [0, 1] range using global min and max
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numFeatures; j++) {
                scaledData[i][j] = (data[i][j] - featureMin[j]) / (featureMax[j] - featureMin[j]);
            }
        }

        return scaledData;
    }

    /**
     * Inversely scales the provided scaled data back to its original scale using the same global min and max.
     *
     * @param scaledData The scaled data to be inversely scaled.
     * @return The original data before scaling.
     */
    public float[][] inverseScale(float[][] scaledData) {
        int numRows = scaledData.length;
        int numFeatures = scaledData[0].length;
        float[][] originalData = new float[numRows][numFeatures];

        // Inversely scale data back to original values using global min and max
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numFeatures; j++) {
                originalData[i][j] = scaledData[i][j] * (featureMax[j] - featureMin[j]) + featureMin[j];
            }
        }

        return originalData;
    }
}
