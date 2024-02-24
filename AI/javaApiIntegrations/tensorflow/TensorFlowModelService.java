import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.types.TFloat32;

public class TensorFlowModelService {

    private SavedModelBundle modelBundle;

    public TensorFlowModelService(String modelDirectory) {
        // Load the model
        modelBundle = SavedModelBundle.load(modelDirectory, "serve");
    }

    public TFloat32 predict(float[][] inputData) {
        // Create an input Tensor
        try (TFloat32 inputTensor = TFloat32.tensorOf(StdArrays.ndCopyOf(inputData))) {
            // Run model inference and return the result tensor directly
            Tensor<?> result = modelBundle.session().runner()
                    .feed("serving_default_input_layer_name", inputTensor)
                    .fetch("StatefulPartitionedCall")
                    .run().get(0);
            // Since the `result` tensor is auto-closable, we cast it to TFloat32 but don't close it here
            // to allow further operations outside this method. It's the caller's responsibility to close it.
            return (TFloat32) result;
        }
    }
}
