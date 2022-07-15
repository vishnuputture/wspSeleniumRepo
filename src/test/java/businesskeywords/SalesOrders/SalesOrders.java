package businesskeywords.SalesOrders;


import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.Util;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import pages.PurchaseOrders.PurchaseOrderDetailsPage;
import pages.SalesOrders.*;
import pages.SalesQuotes.SalesQuoteDetailLinesPage;
import pages.common.MasterPage;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import pages.pricing.spa.CustomerGroupMaintenancePage;
import software.amazon.awssdk.services.ec2.model.Purchase;
import supportLibraries.Utility_Functions;

public class SalesOrders extends ReusableLib{

	 CommonActions commonObj=new CommonActions(helper);
	private FrameworkDriver ownDriver;
	    /**
	     * Constructor to initialize the {@link Helper} object and in turn the
	     * objects wrapped by it
	     *
	     * @param helper The {@link Helper} object
	     */

	    public SalesOrders(Helper helper) {
	        super(helper);
			ownDriver=helper.getGSDriver();

	    }

	    public void validateSalesOrderTitle() throws NoSuchElementException {

	        commonObj.masterToOrderProcessing();
	        commonObj.orderProcessingToSalesOrders();
	        commonObj.validateText(SalesOrdersPage.pageTitle, "Sales Order Entry", "Validating sales orders page title");
	    }

	    public void validateSalesOrderFields() {
	    	sendKeys(SalesOrdersPage.billToAcct,jsonData.getData("accountNo"),"Entering bill to account number");
	    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);
			if(isDisplayed(CustomerNotesPage.customerNotesRevisionsHeader)) {
				click(CustomerNotesPage.btnExit, "Exiting customer notes");
			}
			if(isDisplayed(SalesOrdersPage.deliveryTypeError)) {
				Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.deliveryTypeDropDown), 1);
			}
			Utility_Functions.timeWait(1);
	    	//Utility_Functions.xAssertEquals(report, Utility_Functions.xgetSelectedDropdownValue(driver, SalesOrdersPage.deliveryTypeDropDown), "Pick Up", "Validating selected delivery type");
	    	Utility_Functions.xAssertEquals(report, Utility_Functions.xgetSelectedDropdownValue(ownDriver, SalesOrdersPage.directShipDropDown), "No", "Validating selected direct ship value");
			Utility_Functions.xAssertEquals(report, Utility_Functions.xgetSelectedDropdownValue(ownDriver, SalesOrdersPage.shipCompleteDropDown), "No", "Validating selected ship complete value");
			Utility_Functions.xSelectDropdownByVisibleText(ownDriver, SalesOrdersPage.shipViaDropDown, "DIRECT SHIP");
	    }

		public void fillInOtherFields(){
			Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.deliveryTypeDropDown), 1);
			sendKeys(SalesOrdersPage.txtJobName, jsonData.getData("JobName"), "Entering job name");
			sendKeys(SalesOrdersPage.poNumberEntry, jsonData.getData("PONumber"), "Entering PO number");
			sendKeys(SalesOrdersPage.placedByEntry, jsonData.getData("PlacedBy"), "Entering placed by");
			Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.primarySalespersonDropDown), 0);
			Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.writtenByDropDown), 0);
			sendKeys(SalesOrdersPage.filledByEntry, jsonData.getData("FilledBy"), "Entering filled by");
		}
		public void createSalesOrderCOD(){
			sendKeys(SalesOrdersPage.billToAcct,jsonData.getData("accountNo"),"Entering bill to COD account number");
			Utility_Functions.actionKey(Keys.ENTER, ownDriver);
		}



	    public void navigateToItemsTab() {
			Utility_Functions.timeWait(5);
	    	click(SalesOrdersPage.itemsTab,"Navigating to items tab");
			click(SalesOrdersPage.itemsTab);
			if(isDisplayed(SalesOrdersPage.matrixError)){
				click(SalesOrdersPage.closeError);
				click(SalesOrdersPage.matrixSearch);
				click(SalesOrdersPage.firstMatrixColumn);
				click(SalesOrdersPage.itemsTab,"Navigating to items tab");
			}
	    }

	    public void addItemsToSalesorder() {
	    	sendKeys(SalesOrdersPage.qtyOrdered,"1","Entering ordered quantity");
	    	sendKeys(SalesOrdersPage.itemNumber,jsonData.getData("itemNo1"),"Entering item Number");
	    	sendKeys(SalesOrdersPage.qtyToShip,"1","Entering quantity to ship");
	    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);

			if(isDisplayed(SalesOrdersPage.itemAlreadyOnOrderWindow)){
				Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElement(SalesOrdersPage.lineNumberToCombine));
			}
			if(isDisplayed(SalesOrdersPage.itemEntryIssueWindow)){
				click(SalesOrdersPage.combineSelect);
				click(SalesOrdersPage.continuebtn);
			}
			Utility_Functions.timeWait(5);
			if(ownDriver.isElementVisible(SalesOrdersPage.creditLimitHeader)){
				click(SalesOrdersPage.creditLimitContinue);
			}

	    	sendKeys(SalesOrdersPage.qtyOrdered,"1","Entering ordered quantity");
	    	sendKeys(SalesOrdersPage.itemNumber,jsonData.getData("itemNo2"),"Entering item Number");
	    	sendKeys(SalesOrdersPage.qtyToShip,"1","Entering quantity to ship");
	    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);

			if(isDisplayed(SalesOrdersPage.itemAlreadyOnOrderWindow)){
				Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElement(SalesOrdersPage.lineNumberToCombine));
			}
			if(isDisplayed(SalesOrdersPage.itemEntryIssueWindow)){
				click(SalesOrdersPage.combineSelect);
				click(SalesOrdersPage.continuebtn);
			}
			Utility_Functions.timeWait(5);
			if(ownDriver.isElementVisible((SalesOrdersPage.creditLimitHeader))){
				click(SalesOrdersPage.creditLimitContinue);
			}

	    	Utility_Functions.xUpdateJsonWithArray("SalesOrderNo",ownDriver.findElement(SalesOrdersPage.salesOrderField).getAttribute("value"));
			Utility_Functions.xUpdateJson("SalesOrder",ownDriver.findElement(SalesOrdersPage.salesOrderField).getAttribute("value"));
	    }

	    public void navigateToShipmentsTab()
		{
			click(SalesOrdersPage.shipmentTab,"Navigating to Shipment tab");
		}
		public void changeShipmentDeliveryType(){
			Utility_Functions.timeWait(5);
			Utility_Functions.xSelectDropdownByVisibleText(ownDriver,SalesOrdersPage.shipmentDeliveryTypeDropDown,"Delivery");
			//Utility_Functions.timeWait(5);
			//Utility_Functions.xSelectDropdownByVisibleText(driver,SalesOrdersPage.directShipDropdown,"Yes");

		}

		public void deleteItemFromShipment(){
			Utility_Functions.timeWait(5);
			Utility_Functions.contextClickOnElement(ownDriver,SalesOrdersPage.elementForContextClick);
			click(SalesOrdersPage.contextOptionLineDetails,"Removing item from shipment");
			Utility_Functions.contextClickOnElement(ownDriver,SalesOrdersPage.shipmentItemName);
			Utility_Functions.timeWait(5);
			click(SalesOrdersPage.contextOptionDeleteItem,"Removing item from shipment");
			if(isDisplayed(SalesOrdersPage.updateShipmentBtn)) {
				click(SalesOrdersPage.updateShipmentBtn, "Updating shipment after removing item");
			}
			Utility_Functions.timeWait(5);
			click(SalesOrdersPage.maintainShipmentBtn, "Clicking on maintain shipment button");


		}

		public void createShipment(){

			Utility_Functions.xSelectDropdownByVisibleText(ownDriver,SalesOrdersPage.createShipmentDeliveryTypeDropDown,"Delivery");
			Utility_Functions.timeWait(5);
			//Utility_Functions.xSelectDropdownByVisibleText(driver,SalesOrdersPage.directShipDropdown,"Yes");
			//Utility_Functions.timeWait(5);
			click(SalesOrdersPage.selectItemCheckBox);
			sendKeys(SalesOrdersPage.qtyToShiptxtBox,"1");
			clearText(SalesOrdersPage.backOrder);
			click(SalesOrdersPage.btnCreateShipment,"Creating new shipment");
			Utility_Functions.timeWait(5);
			//List<WebElement> ele = driver.findElements(SalesOrdersPage.directShipDropdown);

			/*for (WebElement e:ele) {
				Utility_Functions.xSelectDropdownByVisibleText(driver,e,"Yes");
				Utility_Functions.timeWait(5);
			}*/
			/*for(int i=0; i<ele.size();i++){
				Utility_Functions.xSelectDropdownByVisibleText(driver,driver.findElement(By.id("slbDirectShip."+(i+1))),"Yes");
				Utility_Functions.timeWait(5);
			}*/
		}

		public void changeShipmentStatus()
		{

            Utility_Functions.timeWait(5);
			Utility_Functions.xSelectDropdownByIndex(ownDriver,ownDriver.findElement(SalesOrdersPage.shipmentStatus),1);
			Utility_Functions.timeWait(5);
			Utility_Functions.xScrollIntoView(ownDriver,ownDriver.findElement(SalesOrdersPage.printAndExitbtn));
			Utility_Functions.waitForElementVisible(ownDriver,SalesOrdersPage.printAndExitbtn,5);
			click(SalesOrdersPage.printAndExitbtn,"Click on Print&Exit button");
			Utility_Functions.timeWait(5);
			Utility_Functions.xScrollIntoView(ownDriver,ownDriver.findElement(SalesOrdersPage.printAndExitbtn));
			//click(SalesOrdersPage.printAndExitbtn,"Click on Print&Exit button");
			Utility_Functions.xClickHiddenElement(ownDriver, SalesOrdersPage.printAndExitbtn);
			click(SalesOrdersPage.btnContinue,"Click on continue button");
			Utility_Functions.timeWait(5);
			Utility_Functions.xScrollIntoView(ownDriver,ownDriver.findElement(SalesOrdersPage.invoiceImage));
			click(SalesOrdersPage.invoiceImage,"clicking invoice image");
			click(SalesOrdersPage.continuebtn,"Click on continue button");
			click(SalesOrdersPage.btnExitSalesOrderSummary,"Exiting Sales Order Summary Page");

		}

		public void loadLastOrder()
		{
        click(SalesOrdersPage.loadSalesOrder,"click on load icon");
		if(isDisplayed(CustomerNotesPage.customerNotesRevisionsHeader))
        {
				click(CustomerNotesPage.btnExit, "Exiting customer notes");
		}
        Utility_Functions.timeWait(3);
        String status=Utility_Functions.xgetSelectedDropdownValue(ownDriver,SalesOrdersPage.orderStatus);
        Utility_Functions.xUpdateJson("CreatedSalesOrder", ownDriver.findElement(SalesOrdersPage.salesOrderField).getAttribute("value"));


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
			if(ownDriver.isElementVisible(SerializedTrackingSelectionPage.serializedHeader)){
				click(SerializedTrackingSelectionPage.btnEnterSerialNum);
				sendKeys(SerializedTrackingSelectionPage.serialNumField, "123456789", "Entering serial number");
				click(SerializedTrackingSelectionPage.btnProcessSerialNum);
				click(SerializedTrackingSelectionPage.btnBack);
			}
	    	click(SalesOrdersPage.btnExit," exiting sales order");
	    }

	public void saveExitSalesOrdersCreateShipment() {
		click(SalesOrdersPage.btnSaveExit,"saving and exiting");
		Utility_Functions.xSelectDropdownByVisibleText(ownDriver,SalesOrdersPage.paymentMethodDropdown,"Cash");
		click(SalesOrdersPage.btnApplyPayment,"Applying  payment method");
		Utility_Functions.timeWait(5);
		//Utility_Functions.xClickHiddenElement(driver,SalesOrdersPage.btnSaveExit);
		click(SalesOrdersPage.btnSaveExitPayment,"saving and exiting");
		//driver.findElement(SalesOrdersPage.btnSaveExitPayment).sendKeys(Keys.ENTER);
		click(SalesOrdersPage.btnExit," exiting sales order");

	}

	/**
	 * Keyword to select Delivery Information Sales Order page
	 */
	public void selectDeliveryInfo(){
		Utility_Functions.xSelectDropdownByVisibleText(ownDriver,SalesOrdersPage.directShipDropDown,jsonData.getData("DirectShip"));
		waitForElementDisappear(MasterPage.loadingAnime, globalWait);
		Utility_Functions.xSelectDropdownByVisibleText(ownDriver,SalesOrdersPage.shipViaDropDown,jsonData.getData("ShipVia"));
		waitForElementDisappear(MasterPage.loadingAnime, globalWait);
	}

	public void selectDeliveryInformation(){
		waitForElementDisappear(MasterPage.loadingAnime, globalWait);
		Utility_Functions.xSelectDropdownByVisibleText(ownDriver,SalesOrdersPage.shipViaDropDown,jsonData.getData("ShipVia"));
		waitForElementDisappear(MasterPage.loadingAnime, globalWait);
	}

	/**
	 * Keyword to select Items in Sales Order page
	 */
	public void selectItems(){
		sendKeys(SalesOrdersPage.qtyOrdered,jsonData.getData("QtyOrder"),"Entering ordered quantity");
		sendKeys(SalesOrdersPage.itemNumber,jsonData.getData("itemNo"),"Entering item Number");
		sendKeys(SalesOrdersPage.qtyToShip,jsonData.getData("QtyShip"),"Entering quantity to ship");

		/************ Sometimes the Unit Price value is empty ************/
		String text = getText(SalesOrdersPage.tbxUnitPrice);
		if (text.isEmpty())
			sendKeys(SalesOrdersPage.tbxUnitPrice,"100","Entering value in Unit Price");

		Utility_Functions.actionKey(Keys.ENTER, ownDriver);
		jsonData.putData("SalesOrderNo", ownDriver.findElement(SalesOrdersPage.salesOrderField).getAttribute("value"));
		Utility_Functions.xUpdateJson("SalesOrderNumber", ownDriver.findElement(SalesOrdersPage.salesOrderField).getAttribute("value"));
	}

	/**
	 * Keyword to select Items in Sales Order page
	 */
	public void selectItemsAndQty(){
		sendKeys(SalesOrdersPage.qtyOrdered,jsonData.getData("QtyOrder"),"Entering ordered quantity");
		sendKeys(SalesOrdersPage.itemNumber,jsonData.getData("itemNo"),"Entering item Number");

		/************ Sometimes the Unit Price value is empty ************/
		String text = getText(SalesOrdersPage.tbxUnitPrice);
		if (text.isEmpty())
			sendKeys(SalesOrdersPage.tbxUnitPrice,"100","Entering value in Unit Price");
		Utility_Functions.actionKey(Keys.ENTER, ownDriver);
		jsonData.putData("SalesOrderNo", ownDriver.findElement(SalesOrdersPage.salesOrderField).getAttribute("value"));
		Utility_Functions.xUpdateJson("SalesOrderNumber", ownDriver.findElement(SalesOrdersPage.salesOrderField).getAttribute("value"));
	}

	/**
	 * Keyword to create Purchase Order from shipment tab in Sales Order page
	 */
	public void createPOFromShipments() {
		Utility_Functions.contextClickOnElement(ownDriver, SalesOrdersPage.elementForContextClick);
		Utility_Functions.xmouseOver(ownDriver, SalesOrdersPage.contextOptionCreatePO);
		click(SalesOrdersPage.contextOptionCreatePOSelectedItem, "Click [Convert Selected Items] option");
		commonObj.validateText(SalesOrdersPage.hdrCreatePO,"Create Purchase Order","Validating Create Purchase Order popup header");
		click(SalesOrdersPage.btnNext, "Click [Next] button");
		commonObj.validateText(SalesOrdersPage.hdrBuildPO,"Build Purchase Order for All Items on a Shipment","Validating Build Purchase Order popup header");
		Utility_Functions.timeWait(3);
		Utility_Functions.xSelectDropdownByVisibleText(ownDriver,SalesOrdersPage.ddnFreightCode, jsonData.getData("FreightCode"));
		sendKeysAndEnter(SalesOrdersPage.tbxVendorNumber, jsonData.getData("VendorNumber"),"Entering Vendor Number");
		click(SalesOrdersPage.btnCreatePO, "Click [Create PO] button");
		commonObj.validateText(SalesOrdersPage.hdrRelatedPO,"Related Purchase Orders","Validating Related Purchase Orders popup header");
		String poNumber = getText(SalesOrdersPage.txtPONumber);
		jsonData.putData("PONumber", poNumber);
		Utility_Functions.xUpdateJson("PONumber",poNumber);
		Utility_Functions.actionKey(Keys.F12, ownDriver);
		//click(SalesOrdersPage.btnBack, "Click [Return to Sales Order] button");
		commonObj.validateText(SalesOrdersPage.lblRelatedPO,"Related P.O.","Validating Related Purchase Orders label");
	}

}
