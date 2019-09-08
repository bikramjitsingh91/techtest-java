package org.shoppingdrugmarket;

public class DecongestantMedication extends Medication {

    private int medicationOptimalPoint;

    /**
     * A medication has a name, and a type(out of the three defined above)
     *
     * @param medicationName the name of the medication, a simple string
     * @param medicationType the type of the medication, from a defined enum
     */
    public DecongestantMedication(String medicationName) {
        super(medicationName, Medication.DECONGESTANT);
        this.medicationOptimalPoint = 200;
    }

    public double getMedicationCost(int unit) {
        return unit * 2.0;
    }

    public int getMedicationOptimalPoint() { return this.medicationOptimalPoint;  }
}
