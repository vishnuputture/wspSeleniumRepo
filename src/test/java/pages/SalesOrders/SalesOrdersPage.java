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

	//shipment tab

	public static By shipmentTab = By.xpath("//span[text()='Shipments'][@istab='true']");

	public static By shipmentStatus=By.id("inShipmentStatus.1");
	public static By printAndExitbtn= By.id("btnSavePrint");

	public static By btnContinue = By.id("Continue");


	//image invoice
	public static  By invoiceImage=By.id("ImgInvoice");
	public static By continuebtn=By.id("btnContinue");

	public static By btnExitSalesOrderSummary=By.id("btnExit");

    // Order INformation Page (Landing Page)
	public static By loadSalesOrder= By.xpath("//div[@id='lblSalesOrderNumber']//following-sibling::div[2]/i");
	public  static By orderStatus=By.xpath("//select[@id='SelectOrderStatusDescription']");
}
