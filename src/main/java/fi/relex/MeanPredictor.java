package fi.relex;

import java.time.LocalDate;
import java.util.Map;

/**
 */
public class MeanPredictor implements Predictor {

    double sum;
    int count;
    double mean;

    @Override
    public void startTraining(Map<String, Integer> variableMapping) {
        sum = 0;
        count = 0;
        mean = 0;
    }

    @Override
    public void endTraining() {
        mean = count > 0 ? sum / count : 0;
    }

    @Override
    public void startPredicting(Map<String, Integer> variableMapping) {}

    @Override
    public double predict(double[] input, LocalDate dateInput) {
        return mean;
    }

    @Override
    public void train(double[] input, LocalDate dateInput, double result) {
        count++;
        sum += result;
    }
}
