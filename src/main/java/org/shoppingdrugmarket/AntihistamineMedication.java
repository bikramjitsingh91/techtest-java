package org.shoppingdrugmarket;

public class AntihistamineMedication extends Medication {
    private int medicationOptimalPoint;

    /**
     * A medication has a name, and a type(out of the three defined above)
     *
     * @param medicationName the name of the medication, a simple string
     * @param medicationType the type of the medication, from a defined enum
     */
    public AntihistamineMedication(String medicationName) {
        super(medicationName, Medication.ANTIHISTAMINE);
        this.medicationOptimalPoint = 100;
    }

    public double getMedicationCost(int unit) {
        if (unit > 100) {
            return unit * 0.8;
        } else if (unit > 50) {
            return unit * 0.9;
        } else {
            return unit;
        }
    }

    public int getMedicationOptimalPoint() { return this.medicationOptimalPoint;  }
}
