package businesskeywords.warehousing.Objects;

import java.io.Serializable;
import java.util.ArrayList;

public class SalesOrder implements Serializable {
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
    private String alphabeticName;
    private String addressLine1;
    private String addressLine2;
    private String deliveryType;
    private String city;
    private String State;
    private String zip;
    private String phoneNumber;
    private ArrayList<Shipment> shipments = new ArrayList<>();

    public SalesOrder() {}

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAlphabeticName() {
        return alphabeticName;
    }

    public void setAlphabeticName(String alphabeticName) {
        this.alphabeticName = alphabeticName;
    }

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
