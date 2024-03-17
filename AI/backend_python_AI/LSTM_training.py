import numpy as np
import pandas as pd
from sklearn.preprocessing import MinMaxScaler


def prepare_data(csv_file):
    # Load data
    data = pd.read_csv(csv_file)

    # Convert 'file' column to datetime and ensure sorting by 'product_name' and 'file' (date)
    data['file'] = pd.to_datetime(data['file'], format='%Y%m%d')
    data.sort_values(by=['product_name', 'file'], inplace=True)

    # Clean non-numeric values by converting to NaN, then dropping or imputing
    data['frisch'] = pd.to_numeric(data['frisch'], errors='coerce')
    data['teigig'] = pd.to_numeric(data['teigig'], errors='coerce')
    data.dropna(inplace=True)  # Option to drop; consider imputation if more appropriate

    # Normalize 'frisch' and 'teigig'
    scaler = MinMaxScaler()
    scaled_features = scaler.fit_transform(data[['frisch', 'teigig']])
    data[['frisch', 'teigig']] = scaled_features

    # Prepare sequences and keep track of additional information
    sequences, targets, dates, product_names = [], [], [], []
    for product_name, group in data.groupby('product_name'):
        features = group[['frisch', 'teigig']].values
        product_dates = group['file'].dt.strftime('%Y-%m-%d').values  # For easier handling

        for i in range(len(features) - 30):
            sequences.append(features[i:i + 30])
            targets.append(features[i + 30])
            dates.append(product_dates[i:i + 31])  # Include target date
            product_names.append(product_name)  # Keep track of the product name

    return np.array(sequences), np.array(targets), dates, product_names, scaler


csv_file = '/home/eta/PycharmProjects/Gradwohl/AI/app/products.csv'  # Adjust with the actual path to your CSV file
sequences, targets, dates, product_names, scaler = prepare_data(csv_file)

# Select a sample for demonstration
sample_index = 0  # Adjust as needed to view different samples
sample_sequence = sequences[sample_index]
sample_target = targets[sample_index]
sample_dates = dates[sample_index]
sample_product_name = product_names[sample_index]

# Inverse transform to original scale
sample_sequence_inverse = scaler.inverse_transform(sample_sequence)
sample_target_inverse = scaler.inverse_transform([sample_target])

print(f"Product Name: {sample_product_name}")
print("Sequence Dates and Features ('frisch' and 'teigig'):")
for date, features in zip(sample_dates[:-1], sample_sequence_inverse):  # Exclude last date as it's for the target
    print(f"Date: {date}, Features: {features}")

print(f"\nTarget Date and Values ('frisch' and 'teigig'): {sample_dates[-1]}, {sample_target_inverse[0]}")

# Build Model
# ---------------------------------------------------

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import LSTM, Dense

from tensorflow.keras.layers import Dropout
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint

# Adjusting the model architecture
def build_model(input_shape):
    model = Sequential([
        LSTM(20, activation='relu', input_shape=input_shape, return_sequences=True),  # Increased units
        Dropout(0.2),  # Increased dropout
        LSTM(20, activation='relu'),  # Tapering down the model
        Dense(50, activation='relu'),  # Additional dense layer
        Dropout(0.2),
        Dense(2)  # Output layer for predicting 'frisch' and 'teigig'
    ])
    model.compile(optimizer='adam', loss='mse')
    return model

# Callbacks
early_stopping = EarlyStopping(monitor='val_loss', patience=10, restore_best_weights=True)
model_checkpoint = ModelCheckpoint('LSTM_Model.h5', monitor='val_loss', save_best_only=True)

# Train Model
# ---------------------------------------------------

# Assuming sequences, targets have been prepared using the prepare_data function
input_shape = (sequences.shape[1], sequences.shape[2])  # (30, 2) based on the data preparation
model = build_model(input_shape)

# Splitting the data (simplistic approach, consider a more robust method for time series)
split_idx = int(len(sequences) * 0.8)  # 80% for training
X_train, X_test = sequences[:split_idx], sequences[split_idx:]
y_train, y_test = targets[:split_idx], targets[split_idx:]

# Train the model
history = model.fit(X_train, y_train, epochs=100, validation_split=0.2, callbacks=[early_stopping, model_checkpoint])

# Test Manually + Save
# ---------------------------------------------------

# Select a sample from the test set
sample_input = X_test[0:1]  # Select a single sample input
real_target = y_test[0]  # Real target for comparison

# Predict using the trained model
predicted_target = model.predict(sample_input)

# Inverse transform the predicted and real target to original scale
predicted_target_inverse = scaler.inverse_transform(predicted_target)
real_target_inverse = scaler.inverse_transform([real_target])


# Define a function to make predictions and inverse transform the results
def predict_and_inverse_transform(model, input_sequences, scaler, num_samples=5):
    # Ensure we don't exceed the dataset size
    num_samples = min(num_samples, len(input_sequences))

    for i in range(num_samples):
        # Prepare a single sample input
        sample_input = input_sequences[i:i + 1]  # Keeping the first dimension for batch
        real_target = y_test[i]  # Real target for comparison

        # Predict using the trained model
        predicted_target = model.predict(sample_input)

        # Inverse transform the predicted and real target to original scale
        predicted_target_inverse = scaler.inverse_transform(predicted_target)
        real_target_inverse = scaler.inverse_transform([real_target])

        print(f"Sample #{i + 1}")
        print(f"Sample Input (last of sequence): {sample_input[0][-1]}")
        print(f"Predicted Target: {predicted_target_inverse[0]}")
        print(f"Real Target: {real_target_inverse[0]}\n")


# Assuming the model, scaler, and test datasets (X_test, y_test) are already defined
predict_and_inverse_transform(model, X_test, scaler, num_samples=5)

# Save Model
# ------------------------------------------------------
import tkinter as tk
from tkinter import filedialog
import os
import tensorflow as tf


def save_model_with_confirmation(model):
    # Initialize Tkinter
    root = tk.Tk()
    root.withdraw()  # Hide the main window

    # Ask the user for confirmation and a directory to save the model
    save_confirmation = input("Do you want to save the model? (y/n): ")
    if save_confirmation.lower() == 'y':
        dir_path = filedialog.askdirectory(title="Select Folder to Save the Model")

        # Check if the user selected a directory
        if dir_path:
            model_path = os.path.join(dir_path, "my_model")
            model.save(model_path)
            print(f"Model saved to {model_path}")
        else:
            print("Model saving cancelled.")
    else:
        print("Model not saved.")

save_model_with_confirmation(model)
