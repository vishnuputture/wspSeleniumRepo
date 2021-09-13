package businesskeywords.SalesOrders;


import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
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
	    
	    public void saveExitSalesOrders() {
	    	click(SalesOrdersPage.btnSaveExit,"saving and exiting");
	    	click(SalesOrdersPage.btnExit," exiting sales order");
	    	
	    }
	    
	    
}
