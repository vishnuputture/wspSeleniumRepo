package businesskeywords.warehousing.Objects;

import java.util.ArrayList;

public class SalesOrder {
    private String orderNumber;
    private String billTo;
    private String billToName;
    private String poNumber;
    private String jobName;
    private String placedBy;
    private String primarySalesperson;
    private String secondarySalesperson;
    private String matrixColumn;
    private String writtenBy;
    private String filledBy;
    private ArrayList<Shipment> shipments = new ArrayList<>();

    public String getBillToName() {
        return billToName;
    }

    public void setBillToName(String billToName) {
        this.billToName = billToName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getPlacedBy() {
        return placedBy;
    }

    public void setPlacedBy(String placedBy) {
        this.placedBy = placedBy;
    }

    public String getPrimarySalesperson() {
        return primarySalesperson;
    }

    public void setPrimarySalesperson(String primarySalesperson) {
        this.primarySalesperson = primarySalesperson;
    }

    public String getSecondarySalesperson() {
        return secondarySalesperson;
    }

    public void setSecondarySalesperson(String secondarySalesperson) {
        this.secondarySalesperson = secondarySalesperson;
    }

    public String getMatrixColumn() {
        return matrixColumn;
    }

    public void setMatrixColumn(String matrixColumn) {
        this.matrixColumn = matrixColumn;
    }

    public String getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public String getFilledBy() {
        return filledBy;
    }

    public void setFilledBy(String filledBy) {
        this.filledBy = filledBy;
    }

    public SalesOrder(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBillTo() {
        return billTo;
    }

    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public ArrayList<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(ArrayList<Shipment> shipments) {
        this.shipments = shipments;
    }
}
