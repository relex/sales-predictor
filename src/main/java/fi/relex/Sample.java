package fi.relex;

import java.time.LocalDate;

/**
 */
public class Sample {

    public final double quantity;
    public final double[] inputs;
    public final LocalDate date;

    public Sample(double quantity, double[] inputs, LocalDate date) {
        this.quantity = quantity;
        this.inputs = inputs;
        this.date = date;
    }
}
