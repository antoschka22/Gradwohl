package ai.gradwohl.tensorflow;

import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.types.TFloat32;

public class TensorFlowDataUtils {

    /**
     * Converts a 2D float array into a TFloat32 tensor.
     *
     * @param data The 2D float array to convert.
     * @return A TFloat32 tensor representing the input data.
     */
    public static TFloat32 convertArrayToTensor(float[][] data) {
        return TFloat32.tensorOf(StdArrays.ndCopyOf(data));
    }

    /**
     * Converts a TFloat32 tensor into a 2D float array and automatically closes the tensor.
     *
     * @param tensor The TFloat32 tensor to convert.
     * @return A 2D float array representing the tensor data, with the tensor being automatically closed after conversion.
     */
    public static float[][] convertTensorToArrayAndClose(TFloat32 tensor) {
        try (tensor) {
            long[] shape = tensor.shape().asArray();
            float[][] array = new float[(int) shape[0]][(int) shape[1]];

            // Read the tensor data into the Java array.
            tensor.scalars().forEachIndexed((idx, f) -> array[Math.toIntExact(idx[0])][Math.toIntExact(idx[1])] = f.getFloat());

            return array;
        }
    }
}
