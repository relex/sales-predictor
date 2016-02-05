package fi.relex;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 */
public class Runner {

    public static void main(String[] ignoredArgs) {
        try {
            SampleSet trainingSet = DataLoader.loadData(Runner.class.getResourceAsStream("/resources/training.csv"));
            SampleSet testSet = DataLoader.loadData(Runner.class.getResourceAsStream("/resources/testing.csv"));
            testAndTrain(trainingSet, testSet);
        } catch (IOException e) {
            System.err.println("Unable to read inputs");
            e.printStackTrace(System.err);
        }
    }

    private static void testAndTrain(SampleSet trainingSet, SampleSet testSet) {
        final List<Predictor> predictors = Arrays.asList(new MeanPredictor() /*, your predictor here? */);
        for (Predictor predictor : predictors) {
            predictor.startTraining(trainingSet.sampleMapping);
            for (Sample sample : trainingSet.samples) {
                predictor.train(sample.inputs, sample.date, sample.quantity);
            }
            predictor.endTraining();
            testAndPrint(predictor, testSet);
        }
    }


    private static void testAndPrint(Predictor predictor, SampleSet testSet) {
        DescriptiveStatistics squaredDiff = new DescriptiveStatistics();
        DescriptiveStatistics absDiff = new DescriptiveStatistics();
        DescriptiveStatistics difference = new DescriptiveStatistics();
        predictor.startPredicting(testSet.sampleMapping);
        for (Sample sample : testSet.samples) {
            double prediction = predictor.predict(sample.inputs, sample.date);
            double actual = sample.quantity;
            double diff = prediction - actual;
            difference.addValue(diff);
            squaredDiff.addValue(diff * diff);
            absDiff.addValue(Math.abs(diff));
        }
        predictor.endPredicting();
        System.out.printf("Predictor \"%s\"%n", predictor.getClass().getSimpleName());
        System.out.println("====diff====");
        printStatistics(difference);
        System.out.println("===|diff|===");
        printStatistics(absDiff);
        System.out.println("====diff^2==");
        printStatistics(squaredDiff);
    }

    private static void printStatistics(DescriptiveStatistics stats) {
        System.out.printf("mean:    %016.04f%n", stats.getMean());
        System.out.printf("min:     %016.04f%n", stats.getMin());
        System.out.printf("max:     %016.04f%n", stats.getMax());
        System.out.printf("std dev: %016.04f%n", stats.getStandardDeviation());
        System.out.printf("sum:     %016.04f%n", stats.getSum());
    }

}
