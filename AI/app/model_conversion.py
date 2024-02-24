import tensorflow as tf

# Load the existing H5 model
model = tf.keras.models.load_model('/home/eta/PycharmProjects/Gradwohl/AI/app/LSTM_Model.h5')

# Save the model in the SavedModel format
tf.saved_model.save(model, '/home/eta/PycharmProjects/Gradwohl/AI/app/LSTM_Model/')
