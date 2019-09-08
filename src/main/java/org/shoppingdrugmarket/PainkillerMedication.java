package org.shoppingdrugmarket;

public class PainkillerMedication extends Medication{

    private int medicationOptimalPoint;

    /**
     * A medication has a name, and a type(out of the three defined above)
     *
     * @param medicationName the name of the medication, a simple string
     * @param medicationType the type of the medication, from a defined enum
     */
    public PainkillerMedication(String medicationName) {
        super(medicationName, Medication.PAINKILLER);
        this.medicationOptimalPoint = 100;
    }

    public double getMedicationCost(int unit){
        if (unit > 200) {
            return unit * 1.5;
        } else if (unit > 100) {
            return unit * 2;
        } else {
            return unit * 3;
        }
    }

    public int getMedicationOptimalPoint() { return this.medicationOptimalPoint;  }
}
