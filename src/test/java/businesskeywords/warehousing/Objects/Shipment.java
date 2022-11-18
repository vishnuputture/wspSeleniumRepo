package businesskeywords.warehousing.Objects;



import java.io.Serializable;
import java.util.ArrayList;

public class Shipment implements Serializable {
    private String shipmentNumber;
    private String shipDate;
    private String requiredDate;
    private String direct;
    private String deliveryType;
    private String shipVia;
    private String shipmentDescription;
    private String deliveryInstructions;
    private String acct;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private ArrayList<Item> items = new ArrayList<>();;

    public Shipment() {
    }

    public Shipment(Shipment shipment) {
        this.shipmentNumber = shipment.getShipmentNumber();
        this.shipDate = shipment.getShipDate();
        this.requiredDate = shipment.getRequiredDate();
        this.direct = shipment.getDirect();
        this.deliveryType = shipment.getDeliveryType();
        this.shipVia = shipment.getShipVia();
        this.shipmentDescription = shipment.getShipmentDescription();
        this.deliveryInstructions = shipment.getDeliveryInstructions();
        this.acct = shipment.getAcct();
        this.name = shipment.getName();
        this.addressLine1 = shipment.getAddressLine1();
        this.addressLine2 = shipment.getAddressLine2();
        this.city = shipment.getCity();
        this.state = shipment.getState();
        this.zip = shipment.getZip();
        this.phone = shipment.getPhone();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getShipVia() {
        return shipVia;
    }

    public void setShipVia(String shipVia) {
        this.shipVia = shipVia;
    }

    public String getShipmentDescription() {
        return shipmentDescription;
    }

    public void setShipmentDescription(String shipmentDescription) {
        this.shipmentDescription = shipmentDescription;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    public String getAcct() {
        return acct;
    }

    public void setAcct(String acct) {
        this.acct = acct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
