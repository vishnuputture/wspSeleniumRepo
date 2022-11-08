package businesskeywords.warehousing.Objects;


import java.util.ArrayList;
import java.util.Iterator;

public class Manifest {
    private String number;
    private String id;
    private String status;
    private ArrayList<Stop> stops = new ArrayList<>();
    private Driver driver;
    private String truck;
    private String date;
    private String time;
    private String managerNotes;

    public void addShipment(Shipment shipment) {
        if (stops.size() == 0) {
            Stop newStop = new Stop("SO");
            newStop.getShipments().add(shipment);
            stops.add(newStop);
            System.out.println("Stop "+ stops.indexOf(newStop) + 1 + " created with shipment "+shipment.getShipmentNumber());
            return;
        }
        for (int i = stops.size(); i > 0; i--) {
            Stop stop = stops.get(i - 1);
            if (stop.getOrderType().equalsIgnoreCase("SO")) {
                Shipment firstShipment = stop.getShipments().get(0);
                if (firstShipment.getAcct().equalsIgnoreCase(shipment.getAcct())) {
                    stop.getShipments().add(shipment);
                    System.out.println("Added to existing stop");
                } else {
                    Stop newStop = new Stop("SO");
                    newStop.getShipments().add(shipment);
                    stops.add(newStop);
                    System.out.println("New stop created");
                }
                break;
            }

            if (i == 1) {
                Stop newStop = new Stop("SO");
                newStop.getShipments().add(shipment);
                stops.add(newStop);
                System.out.println("Cycled through all stops, new stop created");
            }
        }
    }

    public void addPurchaseOrder(PurchaseOrder purchaseOrder) {
        if (stops.size() == 0) {
            Stop newStop = new Stop("PO");
            newStop.getPurchaseOrders().add(purchaseOrder);
            stops.add(newStop);
            System.out.println("Stop "+ stops.indexOf(newStop) + 1 + " created with PO "+purchaseOrder.getNumber());
            return;
        }

        for (int i = stops.size(); i > 0; i--) {
            Stop stop = stops.get(i - 1);
             if (stop.getOrderType().equalsIgnoreCase("PO")) {
                PurchaseOrder po = stop.getPurchaseOrders().get(0);//DEBUG THIS
                if (po.getVendorNumber().equalsIgnoreCase(purchaseOrder.getVendorNumber())) {
                    stop.getPurchaseOrders().add(purchaseOrder);
                    System.out.println("Added PO to existing stop");
                } else {
                    Stop newStop = new Stop("PO");
                    newStop.getPurchaseOrders().add(purchaseOrder);
                    stops.add(newStop);
                    System.out.println("New PO stop created");
                }
                 break;
             }

             if (i == 1) {
                 Stop newStop = new Stop("PO");
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
