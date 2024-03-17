import numpy as np
import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from tensorflow.keras.models import load_model


def prepare_data_and_scale(csv_file):
    """
    Load and preprocess data from CSV, fit a MinMaxScaler to the data,
    and prepare sequences for prediction.
    """
    # Load data
    data = pd.read_csv(csv_file)

    # Convert 'file' column to datetime and ensure sorting by 'product_name' and 'file' (date)
    data['file'] = pd.to_datetime(data['file'], format='%Y%m%d')
    data.sort_values(by=['product_name', 'file'], inplace=True)

    # Clean non-numeric values by converting to NaN, then dropping or imputing
    data['frisch'] = pd.to_numeric(data['frisch'], errors='coerce')
    data['teigig'] = pd.to_numeric(data['teigig'], errors='coerce')
    data.dropna(inplace=True)  # Option to drop; consider imputation if more appropriate

    # Initialize and fit the MinMaxScaler
    scaler = MinMaxScaler()
    data[['frisch', 'teigig']] = scaler.fit_transform(data[['frisch', 'teigig']])

    # Prepare sequences
    sequences, product_names = [], []
    unique_products = data['product_name'].unique()
    for product_name in unique_products:
        group = data[data['product_name'] == product_name]
        features = group[['frisch', 'teigig']].values

        # Ensure there's enough data to form a sequence
        if len(features) >= 30:
            sequences.append(features[-30:])  # Take the last 30 records
            product_names.append(product_name)

    return np.array(sequences), product_names, scaler


def predict_and_format(model, sequences, product_names, scaler):
    """
    Predict the next values using the model and inverse transform predictions.
    """
    # Make predictions
    predictions_scaled = model.predict(sequences)

    # Inverse transform predictions to the original scale
    predictions_original = scaler.inverse_transform(predictions_scaled)
    predictions_rounded = np.rint(predictions_original)

    # Format predictions with product names
    prediction_results = {product_name: prediction for product_name, prediction in
                          zip(product_names, predictions_rounded)}

    return prediction_results


# Paths
model_path = '/home/eta/PycharmProjects/Gradwohl/AI/app/LSTM_Model.h5'
csv_file = '/home/eta/PycharmProjects/Gradwohl/AI/app/products.csv'

# Load the trained model
model = load_model(model_path)

# Prepare the data and scale it
sequences, product_names, scaler = prepare_data_and_scale(csv_file)

# Predict and format the results
prediction_results = predict_and_format(model, sequences, product_names, scaler)

# Display the results
count = 0
total = 0
for product_name, prediction in prediction_results.items():
    count += 1
    print(f"Product: {product_name}, Predicted 'frisch': {int(prediction[0])}, Predicted 'teigig': {int(prediction[1])}")
    total += int(prediction[0]) + int(prediction[1])
print(f"Total Products Types: {count}, Total Products: {total}")