package pages.SalesOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;


public class SalesOrdersPage extends ReusableLib {

	 public SalesOrdersPage(Helper helper) {
	        super(helper);
	    }
	public static By pageTitle = By.id("wob-app-header");
	public static By billToAcct = By.id("inBillToAcct");
	public static By deliveryTypeDropDown = By.id("ddbDeliveryType");
	public static By directShipDropDown = By.id("ddbDirectShip");
	public static By shipViaDropDown = By.id("ddbShipViaLabel");
	public static By shipCompleteDropDown = By.id("ddbShipComplete");
	public static By itemsTab = By.xpath("//span[text()='Items'][@istab='true']");
	public static By qtyOrdered = By.id("inQtyOrder");
	public static By itemNumber = By.id("inItemNumber");
	public static By qtyToShip = By.id("inQtyShip");
	public static By salesOrderField = By.id("inOrderNum");
	public static By btnSaveExit = By.id("btnSaveExit");
	public static By btnExit = By.id("btnExitBill");
}
