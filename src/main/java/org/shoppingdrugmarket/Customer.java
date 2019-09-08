package org.shoppingdrugmarket;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A customer of Shopping Drug Market
 */
public class Customer {
    private String name;
    private List<Prescription> prescriptions = new ArrayList();

    /**
     * A customer must have a name
     *
     * @param name a string defining the customer's name
     */
    public Customer(String name) {
        this.name = name;
    }

    /**
     * When a customer needs to add a prescription to their list of prescriptions
     *
     * @param newPrescription the new prescription being added for the customer
     */
    public void addNewPrescription(Prescription newPrescription) {
        prescriptions.add(newPrescription);
    }

    public String getName() {
        return name;
    }

    /**
     * Generate a plain text receipt for the customer's purchases
     *
     * @return a string containing the items purchased, their cost and the number of points the customer received
     */
    public String generatePrescriptionReceiptText() {
        double totalCost = 0.0;
        int totalOptimalPoints = 0;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String result = "Prescription receipt for " + getName() + ":\n";

        return calculateCostOptimalPoints(totalCost, totalOptimalPoints, result, false);
    }

    /**
     * Generate an HTML receipt for e-mailing to the customer
     *
     * @return an html string containing the items purchased, their cost and the number of points the customer received
     */
    public String generatePrescriptionReceiptHtml() {
        double totalCost = 0.0;
        int totalOptimalPoints = 0;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String result = "<html><body><p><h3>Prescription receipt for " + getName() + ":</h3></p>";
        result += "<table><tr><th>Medication</th><th>Price</th><th>Optimal Points</th></tr>";

        return calculateCostOptimalPoints(totalCost, totalOptimalPoints, result, true);
    }

    private String calculateCostOptimalPoints(double totalCost, int totalOptimalPoints, String result, boolean ishtml){
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        for (Prescription prescription : prescriptions) {
            int thisCost = 0;
            int thisOptimalPoints = 0;
            int unit = prescription.getSize();
            thisCost += prescription.getMedication().getMedicationCost(unit);

            // add optimal points for future in-store redemption
            thisOptimalPoints += prescription.getMedication().getMedicationOptimalPoint();

            // now we can add to the string, showing the cost and number of points gained per item
            if(ishtml){
                result += "<tr><td>" + prescription.getMedication().getMedicationName() +
                        "</td><td>" + decimalFormat.format(thisCost/100.0) +
                        "</td><td>" + String.valueOf(thisOptimalPoints) + "</td></tr>";
            }
            else{
                result += "\t " + prescription.getMedication().getMedicationName() + ":\t" + decimalFormat.format(thisCost/100.0) + "\t" + String.valueOf(thisOptimalPoints) + "\n";
            }
            totalCost += thisCost/100;
            totalOptimalPoints += thisOptimalPoints;
        }
        if (ishtml){
            result += "</table>";
            result += "<p>Total cost: " + decimalFormat.format(totalCost) + "</p>";
            result += "<p>Total optimal points earned: " + totalOptimalPoints + "</p></body></html>";
        }
        else {
            result += "Total cost:\t" + decimalFormat.format(totalCost) + "\n";
            result += "Total optimal points earned:\t" + String.valueOf(totalOptimalPoints) + "\n";
        }
        return result;
    }

}
