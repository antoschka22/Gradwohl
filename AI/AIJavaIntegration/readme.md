# AI Java Integration Sub-Project Documentation

## Overview
The AI Java Integration sub-project within a larger application ecosystem is dedicated to providing predictive analytics capabilities. It leverages a TensorFlow-based machine learning model to generate forecasts based on input data, aiding decision-making processes.

## Project Structure
The sub-project is organized into several packages, each serving a distinct purpose:

- **ai.gradwohl.model**: Contains domain models representing the data structures used for predictions.
- **ai.gradwohl.prediction**: Holds the services responsible for orchestrating the prediction workflow.
- **ai.gradwohl.scaler**: Includes utilities for scaling data before feeding it into the machine learning model.
- **ai.gradwohl.service**: Defines interfaces and implementations for services that interact with data sources and external services.
- **ai.gradwohl.tensorflow**: Contains classes that interface directly with TensorFlow, managing the loading, running, and interpreting of the machine learning model.

## Usage
1. **Model Package**: Define your data entities related to predictions, such as `PredictionResult`, which may contain fields for product identifiers and predicted quantities.

2. **Prediction Package**: Use `PredictionService` to fetch data, perform predictions, and process results. This is the entry point for running predictions.

3. **Scaler Package**: Utilize `DynamicMinMaxScaler` to normalize data before predictions and revert the scaling post-prediction to interpret results in their original scale.

4. **Service Package**: Implement services that provide necessary business logic and data retrieval, such as `WarenbestellungsService`, which fetches historical sales data.

5. **TensorFlow Package**: Rely on the `TensorFlowModelService` to manage interactions with the TensorFlow model, including feeding input data and fetching predictions.

## Integration
To integrate this sub-project into your Spring Boot application, include it as a module or ensure that its packages are scanned during the Spring Boot startup process. Autowire the `PredictionService` in your application components to start making predictions.

## Running Predictions
To run a prediction, autowire `PredictionService` in your Spring Boot component:

```java
@Autowired
private PredictionService predictionService;
```

Then, call the prediction method with a `Filiale` object, which is a representation of your store or business unit:

```java
Filiale myFiliale = new Filiale(1, "Central Store"); // Example instantiation
List<PredictionResult> results = predictionService.predictForFiliale(myFiliale);
```

Handle the list of `PredictionResult` objects to inform your business strategy, inventory management, or other relevant operations.

## Dependencies
The sub-project depends on TensorFlow for machine learning capabilities, so ensure that the TensorFlow Java SDK is included in your project dependencies.

## Testing
Include unit and integration tests, especially focusing on the `predictForFiliale` method within the `PredictionService` to ensure the reliability of predictions and to catch any regression in the prediction process.

## Configuration
Make sure to configure the path to your TensorFlow model and any other necessary parameters like input/output layer names, as these may vary depending on your specific model setup.

## Notes
Regularly update your machine learning model and retrain with new data to improve prediction accuracy. Monitor performance metrics to identify any deviations in prediction quality over time.