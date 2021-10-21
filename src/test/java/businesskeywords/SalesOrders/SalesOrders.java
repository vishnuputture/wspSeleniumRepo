package businesskeywords.SalesOrders;


import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import commonkeywords.CommonActions;
import org.apache.hc.client5.http.auth.StandardAuthScheme;
import pages.SalesOrders.*;
import pages.pricing.pricingmatrix.PricingMatrixPage;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import supportLibraries.Utility_Functions;

public class SalesOrders extends ReusableLib{

	 CommonActions commonObj=new CommonActions(helper);
	    /**
	     * Constructor to initialize the {@link Helper} object and in turn the
	     * objects wrapped by it
	     *
	     * @param helper The {@link Helper} object
	     */

	    public SalesOrders(Helper helper) {
	        super(helper);
	    }

	    public void validateSalesOrderTitle() throws NoSuchElementException {

	        commonObj.masterToOrderProcessing();
	        commonObj.orderProcessingToSalesOrders();
	        commonObj.validateText(SalesOrdersPage.pageTitle, "Sales Order Entry", "Validating sales orders page title");
	    }
	    
	    public void validateSalesOrderFields() {
	    	sendKeys(SalesOrdersPage.billToAcct,jsonData.getData("accountNo"),"Entering bill to account number");
	    	Utility_Functions.actionKey(Keys.ENTER, driver);
	    	
	    	Utility_Functions.xAssertEquals(report, Utility_Functions.xgetSelectedDropdownValue(driver, SalesOrdersPage.deliveryTypeDropDown), "Delivery", "Validating selected delivery type");
	    	Utility_Functions.xAssertEquals(report, Utility_Functions.xgetSelectedDropdownValue(driver, SalesOrdersPage.directShipDropDown), "No", "Validating selected direct ship value");
	    	Utility_Functions.xAssertEquals(report, Utility_Functions.xgetSelectedDropdownValue(driver, SalesOrdersPage.shipCompleteDropDown), "No", "Validating selected ship complete value");
	    	Utility_Functions.xSelectDropdownByVisibleText(driver, SalesOrdersPage.shipViaDropDown, "DIRECT SHIP");
	    }
	    
	    public void navigateToItemsTab() {
	    	click(SalesOrdersPage.itemsTab,"Navigating to items tab");
	    	
	    }
	    
	    public void addItemsToSalesorder() {
	    	sendKeys(SalesOrdersPage.qtyOrdered,"1","Entering ordered quantity");
	    	sendKeys(SalesOrdersPage.itemNumber,jsonData.getData("itemNo1"),"Entering item Number");
	    	sendKeys(SalesOrdersPage.qtyToShip,"1","Entering quantity to ship");
	    	Utility_Functions.actionKey(Keys.ENTER, driver);
	    	
	    	sendKeys(SalesOrdersPage.qtyOrdered,"1","Entering ordered quantity");
	    	sendKeys(SalesOrdersPage.itemNumber,jsonData.getData("itemNo2"),"Entering item Number");
	    	sendKeys(SalesOrdersPage.qtyToShip,"1","Entering quantity to ship");
	    	Utility_Functions.actionKey(Keys.ENTER, driver);
	    	
	    	Utility_Functions.xUpdateJsonWithArray("SalesOrderNo",driver.findElement(SalesOrdersPage.salesOrderField).getAttribute("value"));
	    	
	    	
	    	
	    }

	    public void navigateToShipmentsTab()
		{
			click(SalesOrdersPage.shipmentTab,"Navigating to Shipment tab");
		}

		public void changeShipmentStatus()
		{
            Utility_Functions.timeWait(20);
			Utility_Functions.xSelectDropdownByIndex(driver,driver.findElement(SalesOrdersPage.shipmentStatus),1);
			Utility_Functions.xScrollIntoView(driver,driver.findElement(SalesOrdersPage.printAndExitbtn));
			click(SalesOrdersPage.printAndExitbtn,"Click on Print&Exit button");
			Utility_Functions.xScrollIntoView(driver,driver.findElement(SalesOrdersPage.invoiceImage));
			click(SalesOrdersPage.invoiceImage,"clicking invoice image");
			click(SalesOrdersPage.continuebtn,"Click on continue button");
			click(SalesOrdersPage.btnExitSalesOrderSummary,"Exiting Sales Order Summary Page");

		}

		public void loadLastOrder()
		{
        click(SalesOrdersPage.loadSalesOrder,"click on load icon");
        String status=Utility_Functions.xgetSelectedDropdownValue(driver,SalesOrdersPage.orderStatus);
        if(status.equalsIgnoreCase("closed"))
		{
			report.updateTestLog("verifyRecord","Status is closed", Status.PASS);
		}
        else
		{
			report.updateTestLog("verifyRecord","Status is not  closed", Status.FAIL);
		}
		}

	    
	    public void saveExitSalesOrders() {
	    	click(SalesOrdersPage.btnSaveExit,"saving and exiting");
	    	click(SalesOrdersPage.btnExit," exiting sales order");
	    	
	    }
	    
	    
}
