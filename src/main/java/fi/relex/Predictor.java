package fi.relex;

import java.time.LocalDate;
import java.util.Map;

/**
 */
public interface Predictor {

    /**
     * Signals that we will start training next.
     * * @param variableMapping mapping of the variables to indices
     */
    void startTraining(Map<String, Integer> variableMapping);

    /**
     * Signals the end of training data.
     */
    void endTraining();

    /**
     * Signals that we will start predicting next.
     * @param variableMapping mapping of the variables to indices
     */
    void startPredicting(Map<String, Integer> variableMapping);

    default void endPredicting() {}

    /**
     *
     * @param input this array <em>must not</em> be modified
     * @param dateInput
     * @return
     */
    double predict(double[] input, LocalDate dateInput);

    /**
     * Offer one sample for the model for training.
     * @param input this array <em>must not</em> be modified
     * @param dateInput
     * @param result
     * @return
     */
    void train(double[] input, LocalDate dateInput, double result);

}
