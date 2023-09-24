import tensorflow as tf
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler

# Read the CSV file
df = pd.read_csv("products.csv")

# Extract the input features and target variables
features = ['file']
target_features = ['product_name', 'frisch', 'teigig']

# convert all data to float, any data that can't be transformed is instead cast as NAN
df['teigig'] = pd.to_numeric(df['teigig'], errors='coerce')
df['product_name'] = pd.to_numeric(df['teigig'], errors='coerce')
df['frisch'] = pd.to_numeric(df['teigig'], errors='coerce')

# check if there is any NAN values (typically there are)
print("error check")
print(df.isna().sum())

df.dropna(inplace=True)  # drop any NAN values

X = df[features].values.astype(float)
y = df[target_features].values.astype(float)

# Split into training / testing
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Normalizing Data
scaler = StandardScaler()
X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

# Build the model
# Note that this is an extremely dense model, likely far more complex than necessery
model = tf.keras.models.Sequential([
  tf.keras.layers.Dense(units=32, activation='relu', input_shape=[X_train.shape[1]]),
  tf.keras.layers.Dense(units=32, activation='relu'),
  tf.keras.layers.Dense(units=32, activation='relu'),
  tf.keras.layers.Dense(units=32, activation='relu'),
  tf.keras.layers.Dense(units=16, activation='relu'),
  tf.keras.layers.Dense(units=16, activation='relu'),
  tf.keras.layers.Dense(units=16, activation='relu'),
  tf.keras.layers.Dense(units=3)  # Output layer with 3 units for the target features
])

# Compile the model adjust learning rate as needed,
# typically for these types of models with the limited dataset available
# the lower the better
model.compile(optimizer=tf.keras.optimizers.Adam(learning_rate=0.001), loss='mean_squared_error')

# Train the model
# Adjust Epoch as needed, 10000 is the absolute minimum to get any useable model, and even then it looks pretty bad
model.fit(X_train, y_train, epochs=1000, validation_data=(X_test, y_test))

# Evaluate the model
model.evaluate(X_test, y_test)

# Predict on new data
# More meant as an immediate test than anything
new_data = pd.DataFrame({'file': [20230213]})
scaled_data = scaler.transform(new_data.values)  # Convert new_data to a NumPy array
predictions = model.predict(scaled_data)

# Print the predicted feature values
predicted_features = pd.DataFrame(predictions, columns=target_features)
print(predicted_features)

model.save("Gradwohl_v2_17.5_error.h5")