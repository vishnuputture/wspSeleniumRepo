package businesskeywords.SalesOrders;


import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import commonkeywords.CommonActions;
import org.apache.hc.client5.http.auth.StandardAuthScheme;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.SalesOrders.*;
import pages.pricing.pricingmatrix.PricingMatrixPage;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import supportLibraries.Utility_Functions;

import java.util.List;

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
	    	
	    	Utility_Functions.xAssertEquals(report, Utility_Functions.xgetSelectedDropdownValue(driver, SalesOrdersPage.deliveryTypeDropDown), "Pick Up", "Validating selected delivery type");
	    	Utility_Functions.xAssertEquals(report, Utility_Functions.xgetSelectedDropdownValue(driver, SalesOrdersPage.directShipDropDown), "No", "Validating selected direct ship value");
	    	Utility_Functions.xAssertEquals(report, Utility_Functions.xgetSelectedDropdownValue(driver, SalesOrdersPage.shipCompleteDropDown), "No", "Validating selected ship complete value");
	    	Utility_Functions.xSelectDropdownByVisibleText(driver, SalesOrdersPage.shipViaDropDown, "DIRECT SHIP");
	    }

		public void createSalesOrderCOD(){
			sendKeys(SalesOrdersPage.billToAcct,jsonData.getData("accountNo"),"Entering bill to COD account number");
			Utility_Functions.actionKey(Keys.ENTER, driver);
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
			Utility_Functions.xUpdateJson("SalesOrder",driver.findElement(SalesOrdersPage.salesOrderField).getAttribute("value"));
	    	
	    	
	    	
	    }

	    public void navigateToShipmentsTab()
		{
			click(SalesOrdersPage.shipmentTab,"Navigating to Shipment tab");
		}
		public void changeShipmentDeliveryType(){
			Utility_Functions.timeWait(5);
			Utility_Functions.xSelectDropdownByVisibleText(driver,SalesOrdersPage.shipmentDeliveryTypeDropDown,"Delivery");
			//Utility_Functions.timeWait(5);
			//Utility_Functions.xSelectDropdownByVisibleText(driver,SalesOrdersPage.directShipDropdown,"Yes");

		}

		public void deleteItemFromShipment(){
			Utility_Functions.timeWait(5);
			Utility_Functions.contextClickOnElement(driver,SalesOrdersPage.elementForContextClick);
			click(SalesOrdersPage.contextOptionLineDetails,"Removing item from shipment");
			Utility_Functions.contextClickOnElement(driver,SalesOrdersPage.shipmentItemName);
			Utility_Functions.timeWait(5);
			click(SalesOrdersPage.contextOptionDeleteItem,"Removing item from shipment");
			click(SalesOrdersPage.updateShipmentBtn,"Updating shipment after removing item");
			Utility_Functions.timeWait(5);
			click(SalesOrdersPage.maintainShipmentBtn, "Clicking on maintain shipment button");


		}

		public void createShipment(){

			Utility_Functions.xSelectDropdownByVisibleText(driver,SalesOrdersPage.createShipmentDeliveryTypeDropDown,"Delivery");
			Utility_Functions.timeWait(5);
			//Utility_Functions.xSelectDropdownByVisibleText(driver,SalesOrdersPage.directShipDropdown,"Yes");
			//Utility_Functions.timeWait(5);
			click(SalesOrdersPage.selectItemCheckBox);
			sendKeys(SalesOrdersPage.qtyToShiptxtBox,"1");
			click(SalesOrdersPage.btnCreateShipment,"Creating new shipment");
			Utility_Functions.timeWait(5);
			List<WebElement> ele = driver.findElements(SalesOrdersPage.directShipDropdown);

			/*for (WebElement e:ele) {
				Utility_Functions.xSelectDropdownByVisibleText(driver,e,"Yes");
				Utility_Functions.timeWait(5);
			}*/
			for(int i=0; i<ele.size();i++){
				Utility_Functions.xSelectDropdownByVisibleText(driver,driver.findElement(By.id("slbDirectShip."+(i+1))),"Yes");
				Utility_Functions.timeWait(5);
			}
		}

		public void changeShipmentStatus()
		{

            Utility_Functions.timeWait(5);
			Utility_Functions.xSelectDropdownByIndex(driver,driver.findElement(SalesOrdersPage.shipmentStatus),1);
			Utility_Functions.timeWait(5);
			Utility_Functions.xScrollIntoView(driver,driver.findElement(SalesOrdersPage.printAndExitbtn));
			Utility_Functions.waitForElementVisible(driver,SalesOrdersPage.printAndExitbtn,5);
			click(SalesOrdersPage.printAndExitbtn,"Click on Print&Exit button");
			Utility_Functions.timeWait(5);
			Utility_Functions.xScrollIntoView(driver,driver.findElement(SalesOrdersPage.printAndExitbtn));
			//click(SalesOrdersPage.printAndExitbtn,"Click on Print&Exit button");
			Utility_Functions.xClickHiddenElement(driver, SalesOrdersPage.printAndExitbtn);
			click(SalesOrdersPage.btnContinue,"Click on continue button");
			Utility_Functions.timeWait(5);
			Utility_Functions.xScrollIntoView(driver,driver.findElement(SalesOrdersPage.invoiceImage));
			click(SalesOrdersPage.invoiceImage,"clicking invoice image");
			click(SalesOrdersPage.continuebtn,"Click on continue button");
			click(SalesOrdersPage.btnExitSalesOrderSummary,"Exiting Sales Order Summary Page");

		}

		public void loadLastOrder()
		{
        click(SalesOrdersPage.loadSalesOrder,"click on load icon");

        Utility_Functions.timeWait(3);
        String status=Utility_Functions.xgetSelectedDropdownValue(driver,SalesOrdersPage.orderStatus);
        Utility_Functions.xUpdateJson("CreatedSalesOrder", driver.findElement(SalesOrdersPage.salesOrderField).getAttribute("value"));


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

	public void saveExitSalesOrdersCreateShipment() {
		click(SalesOrdersPage.btnSaveExit,"saving and exiting");
		Utility_Functions.xSelectDropdownByVisibleText(driver,SalesOrdersPage.paymentMethodDropdown,"Cash");
		click(SalesOrdersPage.btnApplyPayment,"Applying  payment method");
		Utility_Functions.timeWait(5);
		//Utility_Functions.xClickHiddenElement(driver,SalesOrdersPage.btnSaveExit);
		click(SalesOrdersPage.btnSaveExitPayment,"saving and exiting");
		//driver.findElement(SalesOrdersPage.btnSaveExitPayment).sendKeys(Keys.ENTER);
		click(SalesOrdersPage.btnExit," exiting sales order");

	}
	    
	    
}
