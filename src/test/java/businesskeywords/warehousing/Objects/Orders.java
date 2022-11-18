package businesskeywords.warehousing.Objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Orders implements Serializable {
    private ArrayList<SalesOrder> salesOrders;
    private ArrayList<PurchaseOrder> purchaseOrders;

    public Orders() {
        salesOrders = new ArrayList<>();
        purchaseOrders = new ArrayList<>();
    }

    public ArrayList<SalesOrder> getSalesOrders() {
        return salesOrders;
    }

    public void setSalesOrders(ArrayList<SalesOrder> salesOrders) {
        this.salesOrders = salesOrders;
    }

    public ArrayList<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(ArrayList<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }
}
