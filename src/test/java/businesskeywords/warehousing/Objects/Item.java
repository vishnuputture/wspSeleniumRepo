package businesskeywords.warehousing.Objects;

import java.io.Serializable;

public class Item implements Serializable {
    private String number;
    private String qtyOrdered;
    private String qtyToShip;
    private String lineNumber;
    private String unitPrice;
    private String averageCost;
    private String extendedPrice;
    private String extendedCost;
    private String grossMarginPercent;
    private String description;
    private String qtyAvailable;
    private String quantityOnBackOrder;

    public Item(String number, String qtyOrdered, String qtyToShip, String lineNumber) {
        this.number = number;
        this.qtyOrdered = qtyOrdered;
        this.qtyToShip = qtyToShip;
        this.lineNumber = lineNumber;

    }
    public Item() {

    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(String averageCost) {
        this.averageCost = averageCost;
    }

    public String getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(String extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    public String getExtendedCost() {
        double ordered = Double.parseDouble(qtyOrdered);
        double avgCost = Double.parseDouble(averageCost);
        return String.valueOf(ordered * avgCost);
    }

    public String getQuantityOnBackOrder() {
        return String.valueOf(Integer.parseInt(qtyOrdered) - Integer.parseInt(qtyToShip));
    }

    public void setExtendedCost(String extendedCost) {
        this.extendedCost = extendedCost;
    }

    public String getGrossMarginPercent() {
        return grossMarginPercent;
    }

    public void setGrossMarginPercent(String grossMarginPercent) {
        this.grossMarginPercent = grossMarginPercent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQtyAvailable() {
        return qtyAvailable;
    }

    public void setQtyAvailable(String qtyAvailable) {
        this.qtyAvailable = qtyAvailable;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getQtyOrdered() {
        return qtyOrdered;
    }

    public void setQtyOrdered(String qtyOrdered) {
        this.qtyOrdered = qtyOrdered;
    }

    public String getQtyToShip() {
        return qtyToShip;
    }

    public void setQtyToShip(String qtyToShip) {
        this.qtyToShip = qtyToShip;
    }
}
