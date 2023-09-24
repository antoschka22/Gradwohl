import tensorflow as tf
from tensorflow.keras.layers import Dense, Input, Concatenate
from tensorflow.keras.models import Model
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler

# Load the dataset
df = pd.read_csv("products.csv")
features = ['product_name', 'frisch', 'teigig']

df['teigig'] = pd.to_numeric(df['teigig'], errors='coerce')
df['product_name'] = pd.to_numeric(df['teigig'], errors='coerce')
df['frisch'] = pd.to_numeric(df['teigig'], errors='coerce')
df.dropna(inplace=True)

X = df[features].values
y = df['file'].values

# Split into training / testing
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Normalize Data
scaler = StandardScaler()
X_train_numeric = scaler.fit_transform(X_train[:, 1:])
X_test_numeric = scaler.transform(X_test[:, 1:])

# One-hot encode product_name
product_name_categories = df['product_name'].unique()
product_name_categories.sort()
num_product_name_categories = len(product_name_categories)

product_name_mapping = {name: i for i, name in enumerate(product_name_categories)}
X_train_product_name = tf.keras.utils.to_categorical(
    [product_name_mapping[name] for name in X_train[:, 0]],
    num_classes=num_product_name_categories
)
X_test_product_name = tf.keras.utils.to_categorical(
    [product_name_mapping[name] for name in X_test[:, 0]],
    num_classes=num_product_name_categories
)

# Define input layers
input_file = Input(shape=(2,), name='input_file')
input_product_name = Input(shape=(num_product_name_categories,), name='input_product_name')

# Concatenate the inputs
merged_inputs = Concatenate()([input_file, input_product_name])

# Hidden layers
hidden = Dense(units=32, activation='relu')(merged_inputs)
hidden = Dense(units=16, activation='relu')(hidden)

# Output layer
output = Dense(units=1)(hidden)

# Create the model
model = Model(inputs=[input_file, input_product_name], outputs=output)

# Compile the model
model.compile(optimizer=tf.keras.optimizers.Adam(learning_rate=0.001), loss='mean_squared_error')

# Train the model
model.fit(
    {'input_file': X_train_numeric, 'input_product_name': X_train_product_name},
    y_train,
    epochs=100,
    validation_data=({'input_file': X_test_numeric, 'input_product_name': X_test_product_name}, y_test)
)


prediction = model.predict({'input_file': 20230513, 'input_product_name': 224})
print(prediction)

# Save the model
model.save('testv3_unusable.h5')
