package ai.gradwohl.tensorflow;

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.types.TFloat32;

public class TensorFlowModelService {

    private SavedModelBundle modelBundle;

    public TensorFlowModelService(String modelDirectory) {
        // Load the model
        // NOTE: The model is the entire folder in this API implementation, so /path/to/model/LSTM_Model
        // Programmer is expected to create this with the correct parameters
        modelBundle = SavedModelBundle.load(modelDirectory, "serve");
    }

    public TFloat32 predict(TFloat32 inputData) {
        // Create an input Tensor
        try (TFloat32 inputTensor = TFloat32.tensorOf(StdArrays.ndCopyOf(new TFloat32[]{inputData}).shape())) {
            // Run model inference and return the result tensor directly
            Tensor result = modelBundle.session().runner()
                    .feed("lstm_input", inputTensor)
                    .fetch("StatefulPartitionedCall")
                    .run().getFirst();
            // Since the `result` tensor is auto-closable, we cast it to TFloat32 but don't close it here
            // to allow further operations outside this method. It's the caller's responsibility to close it.
            return (TFloat32) result;
        }
    }
}
