# This model requires a GPU or a specifically compiled version of TF
# CUDA drivers are preferred, therefore preferably this should run on a Windows machine with a NVIDIA GPU

import tensorflow as tf
import pandas as pd

df = pd.read_csv("products.csv", dtype={'file': float})
features = ['product_name', 'frisch', 'teigig']

df['teigig'] = pd.to_numeric(df['teigig'], errors='coerce')

X = df[features].to_numpy()
y = df['file'].to_numpy()

print(df[features])

# Split into training / testing
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Normalizing Data
from sklearn.preprocessing import StandardScaler
scaler = StandardScaler()
X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

# build model
model = tf.keras.models.Sequential([
  tf.keras.layers.Dense(units=32, activation='relu', input_shape=[len(features)]),
  tf.keras.layers.Dense(units=16, activation='relu'),
  tf.keras.layers.Dense(units=1)
])

# compile the model
model.compile(optimizer=tf.keras.optimizers.Adam(learning_rate=0.001), loss='mean_squared_error')

# train model
model.fit(X_train, y_train, epochs=100, validation_data=(X_test, y_test))

# evaluate the model
model.evaluate(X_test, y_test)

# save the model
model.save('model_v01_17.5_error.h5')




