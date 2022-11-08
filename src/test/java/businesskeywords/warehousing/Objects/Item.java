package businesskeywords.warehousing.Objects;

public class Item {
    private String number;
    private String qtyOrdered;
    private String qtyToShip;
    private String lineNumber;

    public Item(String number, String qtyOrdered, String qtyToShip, String lineNumber) {
        this.number = number;
        this.qtyOrdered = qtyOrdered;
        this.qtyToShip = qtyToShip;
        this.lineNumber = lineNumber;
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
