import tensorflow as tf

# Load the existing H5 model
model = tf.keras.models.load_model('path/to/your_model.h5')

# Save the model in the SavedModel format
tf.saved_model.save(model, 'path/to/save_savedmodel')
