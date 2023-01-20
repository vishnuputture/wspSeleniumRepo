package businesskeywords.warehousing.Objects;

import software.amazon.awssdk.services.ec2.model.Purchase;

import java.io.Serializable;
import java.util.ArrayList;

public class Manifest implements Serializable {
    private String number;
    private String id;
    private String status;
    private ArrayList<Stop> stops = new ArrayList<>();
    private Driver driver;
    private String truck;
    private String date;
    private String time;
    private String managerNotes;

    public void addOrder(Object object) {
        boolean isShipment = object instanceof Shipment;
        String type = isShipment ? "SO" : "PO";
        String orderNumber = "";
        Shipment shipment = null;
        PurchaseOrder purchaseOrder = null;

        if (isShipment){
            shipment = (Shipment) object;
            orderNumber = shipment.getShipmentNumber();
        } else {
            purchaseOrder = (PurchaseOrder) object;
            orderNumber = purchaseOrder.getNumber();
        }

        if (stops.size() == 0) {
            Stop newStop = new Stop(type);
            if (isShipment)
                newStop.getShipments().add(shipment);
            else
                newStop.getPurchaseOrders().add(purchaseOrder);

            stops.add(newStop);
            System.out.println("Stop "+ stops.indexOf(newStop) + 1 + " created with "+ type +" "+ orderNumber);
            return;
        }

        for (int i = stops.size(); i > 0; i--) {
            Stop stop = stops.get(i - 1);
            boolean stopOrderType = stop.getOrderType().equalsIgnoreCase(type);
            if (isShipment && stopOrderType) {
                Shipment firstShipment = stop.getShipments().get(0);
                if (firstShipment.getAcct().equalsIgnoreCase(shipment.getAcct())) {
                    stop.getShipments().add(shipment);
                    System.out.println("Added "+ type +" to existing stop");
                    break;
                }
            } else if (!isShipment && stopOrderType){
                PurchaseOrder po = stop.getPurchaseOrders().get(0);
                if (po.getVendorNumber().equals(purchaseOrder.getVendorNumber())) {
                    stop.getPurchaseOrders().add(purchaseOrder);
                    System.out.println("Added "+ type +" to existing stop");
                    break;
                }
            }

            if (i == 1) {
                Stop newStop = new Stop(type);
                if (isShipment)
                    newStop.getShipments().add(shipment);
                else
                    newStop.getPurchaseOrders().add(purchaseOrder);
                stops.add(newStop);
                System.out.println("Cycled through all stops, new stop created");
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStops(ArrayList<Stop> stops) {
        this.stops = stops;
    }

    public String getManagerNotes() {
        return managerNotes;
    }

    public void setManagerNotes(String managerNotes) {
        this.managerNotes = managerNotes;
    }

    public Manifest(String number) {
        this.id = number;
        this.number = String.valueOf(Integer.parseInt(number));
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Stop> getStops() {
        return stops;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
