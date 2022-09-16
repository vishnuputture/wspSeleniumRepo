package businesskeywords.Inventory.averagecost;


import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.inventory.CostAdjustmentPage;
import pages.inventory.ItemLedgerPage;
import pages.inventory.ItemMasterPage;
import pages.inventory.ReceiveCorrectionPage;
import supportLibraries.Utility_Functions;

import java.util.List;

public class createCost extends ReusableLib {
    CommonActions commonObj;
    private FrameworkDriver ownDriver;
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public createCost(Helper helper) {

        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver=helper.getGSDriver();
    }

    /**
     * This method validates the title of Item Master Page
     *
     * @throws NoSuchElementException
     */
    public void validateItemMasterTitle() throws NoSuchElementException {

        commonObj.masterToInventory();
        commonObj.inventoryToItemMaster();
        //commonObj.splPricingToAddPricing();
        commonObj.validateText(ItemMasterPage.pageTitle, "ITEM MASTER (I-347)", "Validating item Master page title");
    }
    
    /**
     * This method validates the title of Receive Correction Page
     *
     * @throws NoSuchElementException
     */
    public void validateRcvCorrectionTitle() throws NoSuchElementException {

        commonObj.masterToInventory();
        commonObj.inventoryToInvAdjustments();
        commonObj.inventoryAdjustToInvCorrections();
        //commonObj.splPricingToAddPricing();
        commonObj.validateText(ReceiveCorrectionPage.lblTitle, "Receiving Correction (I-355)", "Validating item Master page title");
    }
    
    /**
     * This method validates the title of cost adjustment Page
     *
     * @throws NoSuchElementException
     */
    public void validateAvgCostAdjustTitle() throws NoSuchElementException {

        commonObj.masterToInventory();
        commonObj.inventoryToInvAdjustments();
        commonObj.inventoryAdjustToavgCostAdjust();
        //commonObj.splPricingToAddPricing();
        commonObj.validateText(CostAdjustmentPage.lblTitle, "Average Cost Adjustment (I-355)", "Validating item Master page title");
    }

    public void createAverageCostItem() {
        Utility_Functions.timeWait(5);
        click(ItemMasterPage.addItemAction,"Click on add item");
        sendKeys(ItemMasterPage.txtBoxDescription,"testdesc1","Enter description");
        sendKeys(ItemMasterPage.txtBoxUOM,"EA","Enter UOM");
        click(ItemMasterPage.btnSave,"Click on save changes");

        if (Utility_Functions.xWaitForElementPresent(ownDriver, ItemMasterPage.messageAddSuccessful, 10)) {
            String successMessage = Utility_Functions.getText(ownDriver,  ItemMasterPage.messageAddSuccessful);
            System.out.println("Text: " + successMessage);
            Utility_Functions.xAssertEquals(report, "Record successfully added !", successMessage.trim(), "Validating success message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + ItemMasterPage.messageAddSuccessful);
        }

        System.out.println("Created Cost : "+getAttribute(ItemMasterPage.txtBoxSearch,"value"));
        Utility_Functions.xUpdateJson("CreatedCost", getAttribute(ItemMasterPage.txtBoxSearch,"value"));
    }

    public void deleteAvgCostItem() {
        sendKeys(ItemMasterPage.txtBoxSearch,Utility_Functions.xGetJsonAsString("CreatedCost"),"Entering search string");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,ItemMasterPage.deleteItemAction,"Click on delete action link");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,ItemMasterPage.btnAlertContinue,"Click on alert button");

        if (Utility_Functions.xWaitForElementPresent(ownDriver, ItemMasterPage.messageAddSuccessful, 10)) {
            String successMessage = Utility_Functions.getText(ownDriver,  ItemMasterPage.messageAddSuccessful);
            System.out.println("Text: " + successMessage);
            Utility_Functions.xAssertEquals(report, "Item "+Utility_Functions.xGetJsonAsString("CreatedCost")+" successfully deleted", successMessage.trim(), "Validating success message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + ItemMasterPage.messageAddSuccessful);
        }
    }
    
    public void validateListPrice() {
    	sendKeys(ItemMasterPage.txtBoxSearch,jsonData.getData("WiseItem"),"Entering search string");
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    	String temp=ownDriver.findElement(ItemMasterPage.txtBoxListPrice).getAttribute("value").trim();
    	if(temp.equalsIgnoreCase(Utility_Functions.xGetJsonData("ListPrice")))
        {
            report.updateTestLog("VerifyRecord", "Price Matched", Status.PASS);
        }else
        {
            report.updateTestLog("VerifyRecord", "Price Mis-Matched", Status.FAIL);
        }
    	
    }
    
    public void validateMatrixPrice() {	
    	sendKeys(ItemMasterPage.txtBoxSearch,jsonData.getData("WiseItem"),"Entering search string");
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    	String temp=ownDriver.findElement(ItemMasterPage.txtBoxMatrix).getAttribute("value").trim();
    	if(temp.equalsIgnoreCase(Utility_Functions.xGetJsonData("MatrixCostMultiplier")))
        {
            report.updateTestLog("VerifyRecord", "Price Matched", Status.PASS);
        }else
        {
            report.updateTestLog("VerifyRecord", "Price Mis-Matched", Status.FAIL);
        }
    	
    }
    
    public void validatePoCost() {	
    	sendKeys(ItemMasterPage.txtBoxSearch,jsonData.getData("WiseItem"),"Entering search string");
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    	String temp=ownDriver.findElement(ItemMasterPage.txtBoxPoCost).getAttribute("value").trim();
    	if(temp.equalsIgnoreCase(Utility_Functions.xGetJsonData("PoCostMultiplier")))
        {
            report.updateTestLog("VerifyRecord", "Cost Matched", Status.PASS);
        }else
        {
            report.updateTestLog("VerifyRecord", "Cost Mis-Matched", Status.FAIL);
        }
    	
    }
    
    public void updateAvgCostItem() {
    	sendKeys(ItemMasterPage.txtBoxSearch,Utility_Functions.xGetJsonAsString("CreatedCost"),"Entering search string");
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    	sendKeys(ItemMasterPage.txtInputPkgQuantity,"100","Updating package quantity");
    	 click(ItemMasterPage.btnSave,"Click on save changes");
    	 
    	 if (Utility_Functions.xWaitForElementPresent(ownDriver, ItemMasterPage.messageAddSuccessful, 10)) {
             String successMessage = Utility_Functions.getText(ownDriver,  ItemMasterPage.messageAddSuccessful);
             System.out.println("Text: " + successMessage);
             Utility_Functions.xAssertEquals(report, "Fields have recently been changed=>VERIFY CHANGES!", successMessage.trim(), "Validating success message");
         } else {
             System.out.println("Text: Not found");
             throw new NoSuchElementException("Could not find :" + ItemMasterPage.messageAddSuccessful);
         }
    	
    }
    
    public void createMultipleItems() {
    	for(int i=0;i<=1;i++) {
    		click(ItemMasterPage.addItemAction,"Click on add item");
            sendKeys(ItemMasterPage.txtBoxDescription,"testdesc"+i,"Enter description for item number"+i+1);
            sendKeys(ItemMasterPage.txtBoxUOM,"EA","Enter UOM");
            click(ItemMasterPage.btnSave,"Click on save changes");

            if (Utility_Functions.xWaitForElementPresent(ownDriver, ItemMasterPage.messageAddSuccessful, 10)) {
                String successMessage = Utility_Functions.getText(ownDriver,  ItemMasterPage.messageAddSuccessful);
                System.out.println("Text: " + successMessage);
                Utility_Functions.xAssertEquals(report, "Record successfully added !", successMessage.trim(), "Validating success message");
            } else {
                System.out.println("Text: Not found");
                throw new NoSuchElementException("Could not find :" + ItemMasterPage.messageAddSuccessful);
            }

            System.out.println("Created item : "+getAttribute(ItemMasterPage.txtBoxSearch,"value"));
            Utility_Functions.xUpdateJson("ItemNo"+i, getAttribute(ItemMasterPage.txtBoxSearch,"value"));
    	}

    	click(ItemMasterPage.btnExit,"Exit from page");
    }

    public void attachVendor() {
    	sendKeys(ReceiveCorrectionPage.txtVendorSearch,"000388","Enter vendor code");
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);

    	List<WebElement> eleQuantityList = ownDriver.findElements(ReceiveCorrectionPage.txtQuantity);
    	List<WebElement> eleItemNumberList = ownDriver.findElements(ReceiveCorrectionPage.txtItemNumber);
    	List<WebElement> eleExplanationList = ownDriver.findElements(ReceiveCorrectionPage.txtExplanation);
    	//sendKeys(ReceiveCorrectionPage.txtQuantityfirst,"100","Enter quantity");
    	//sendKeys(ReceiveCorrectionPage.txtItemNumberfirst,Utility_Functions.xGetJsonAsString("CreatedCost"),"Enter item number");
    	//sendKeys(ReceiveCorrectionPage.txtExplanationfirst,"Test data","Enter explanation");

    	for(int i=0;i<=1;i++) {
    		sendKeys(eleQuantityList.get(i),"100","Enter quantity");
    		sendKeys(eleItemNumberList.get(i),Utility_Functions.xGetJsonAsString("CreatedCost"),"Enter item number");
    		sendKeys(eleExplanationList.get(i),"Test data","Enter explanation");
    	}
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);


    	 click(ReceiveCorrectionPage.btnProcess,"Click on process");
         Utility_Functions.timeWait(5);
    	 click(ReceiveCorrectionPage.btnContinuePop,"Click on continue");

    	 sendKeys(ownDriver.findElements(ReceiveCorrectionPage.txtItemNumber).get(0),Utility_Functions.xGetJsonAsString("CreatedCost"),"Enter item number");
    	 Utility_Functions.actionKey(Keys.ENTER, ownDriver);
     	String onHandVal = Utility_Functions.getText(ownDriver.findElements(ReceiveCorrectionPage.lblOnHand).get(0));

     	Utility_Functions.xAssertEquals(report, "200", onHandVal.trim(), "Validating on hand value");
    }

    public void validateQuantityReceivedCorrections() {
    	sendKeys(ownDriver.findElements(ReceiveCorrectionPage.txtQuantity).get(0),"600","Enter excess quantity");
    	sendKeys(ownDriver.findElements(ReceiveCorrectionPage.txtItemNumber).get(0),Utility_Functions.xGetJsonAsString("CreatedCost"),"Enter item number");

    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);

    	Utility_Functions.xAssertEquals(report,"Warning: Quantity exceeds limit of 500. Ensure value is correct.",Utility_Functions.getText(ownDriver.findElements(ReceiveCorrectionPage.txtQuantity).get(0), "title"),"Validating warning message for excess quantity");

    	sendKeys(ownDriver.findElements(ReceiveCorrectionPage.txtQuantity).get(0),"-500","Enter negative quantity");
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);

    	Utility_Functions.xAssertEquals(report,"Warning: Qty for "+Utility_Functions.xGetJsonAsString("CreatedCost")+" will take On-Hand to a negative value.",Utility_Functions.getText(ownDriver.findElements(ReceiveCorrectionPage.txtQuantity).get(0), "title"),"Validating warning message for negative quantity");
    }

    public void attachVendorMultipleItem() {
    	 commonObj.inventoryToInvAdjustments();
         commonObj.inventoryAdjustToInvCorrections();
         //commonObj.splPricingToAddPricing();
         commonObj.validateText(ReceiveCorrectionPage.lblTitle, "Receiving Correction (I-355)", "Validating item Master page title");
        sendKeys(ReceiveCorrectionPage.txtVendorSearch,"000388","Enter vendor code");
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);

    	List<WebElement> eleQuantityList = ownDriver.findElements(ReceiveCorrectionPage.txtQuantity);
    	List<WebElement> eleItemNumberList = ownDriver.findElements(ReceiveCorrectionPage.txtItemNumber);
    	List<WebElement> eleExplanationList = ownDriver.findElements(ReceiveCorrectionPage.txtExplanation);

    	for(int i=0;i<=1;i++) {
    		sendKeys(eleQuantityList.get(i),"100","Enter quantity");
    		sendKeys(eleItemNumberList.get(i),Utility_Functions.xGetJsonAsString("ItemNo"+i),"Enter item number");
    		sendKeys(eleExplanationList.get(i),"Test data","Enter explanation");
    	}

    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);


   	 click(ReceiveCorrectionPage.btnProcess,"Click on process");
   	 click(ReceiveCorrectionPage.btnContinuePop,"Click on continue");

   	 for(int i=0;i<=1;i++) {
   		 sendKeys(ownDriver.findElements(ReceiveCorrectionPage.txtItemNumber).get(i),Utility_Functions.xGetJsonAsString("ItemNo"+i),"Enter item number");
   		Utility_Functions.actionKey(Keys.ENTER, ownDriver);
     	String onHandVal = Utility_Functions.getText(ownDriver.findElements(ReceiveCorrectionPage.lblOnHand).get(i));
     	
     	Utility_Functions.xAssertEquals(report, "100", onHandVal.trim(), "Validating on hand value");
   	 }
    }

    public void navigateItemLedgerFromCorrection() {
    	click(ownDriver.findElements(ReceiveCorrectionPage.btnItemLedger).get(0),"Click on item ledger");

    }

    public void navigateItemLedgerFromAdjustment() {
    	sendKeys(ownDriver.findElements(CostAdjustmentPage.txtItemNumber).get(0),Utility_Functions.xGetJsonAsString("CreatedCost"),"Enter item number");
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    	click(ownDriver.findElements(CostAdjustmentPage.btnLedger).get(0),"Click on the item ledger button");
    }

    public void fillCostAdjustmentDetails() {
    	
    	sendKeys(ownDriver.findElements(CostAdjustmentPage.txtItemNumber).get(0),Utility_Functions.xGetJsonAsString("CreatedCost"),"Enter item number");
    	sendKeys(ownDriver.findElements(CostAdjustmentPage.txtQuantity).get(0),"50","Enter quantity to adjust");
    	sendKeys(ownDriver.findElements(CostAdjustmentPage.txtInCorrectCost).get(0),"1","Enter incorrect cost");
    	sendKeys(ownDriver.findElements(CostAdjustmentPage.txtNewCost).get(0),"2","Enter correct cost");
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    	 String currAvgCost = Utility_Functions.getText(ownDriver.findElements(CostAdjustmentPage.lblCurrAvgCost).get(0));
    	 String newAvgCost = Utility_Functions.getText(ownDriver.findElements(CostAdjustmentPage.lblCurrNewCost).get(0) );
    	 //Utility_Functions.xUpdateJson("NewAvgCost", newAvgCost);
    	 Utility_Functions.xAssertNotEquals(report, currAvgCost, newAvgCost, "Validating that current avg cost is not equal to new avg cost");
    	 
    	
    }
    
    public void processMultipleCostAdjustments() {
    	for(int i=0;i<=1;i++) {
    		sendKeys(ownDriver.findElements(CostAdjustmentPage.txtItemNumber).get(i),Utility_Functions.xGetJsonAsString("ItemNo"+i),"Enter item number");
        	sendKeys(ownDriver.findElements(CostAdjustmentPage.txtQuantity).get(i),"50","Enter quantity to adjust");
        	sendKeys(ownDriver.findElements(CostAdjustmentPage.txtInCorrectCost).get(i),"1","Enter incorrect cost");
        	sendKeys(ownDriver.findElements(CostAdjustmentPage.txtNewCost).get(i),"2","Enter correct cost");

    	}
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    	click(CostAdjustmentPage.btnProcess,"Click on the process button");
    }

    public void processCostAdjustment() {
    	click(CostAdjustmentPage.btnProcess,"Click on the process button");
    }
    
    public void validateLedger() {


    	Utility_Functions.xAssertEquals(report,  getAttribute(ItemLedgerPage.txtItemNumber,"value"), Utility_Functions.xGetJsonAsString("CreatedCost"), "Validating item number");
    	Utility_Functions.xAssertEquals(report,  Utility_Functions.getText(ownDriver,ItemLedgerPage.lblQtyAfter), "200", "Validating item quantity");

    }

    public void validateMultipleLedgerFromCorrection() {
    	for(int i=0;i<=1;i++) {
    		click(ownDriver.findElements(ReceiveCorrectionPage.txtItemNumber).get(i),"");
    		click(ownDriver.findElements(ReceiveCorrectionPage.btnItemLedger).get(i),"Click on item ledger");
    		Utility_Functions.xAssertEquals(report,  getAttribute(ItemLedgerPage.txtItemNumber,"value"), Utility_Functions.xGetJsonAsString("ItemNo"+i), "Validating item number");
        	Utility_Functions.xAssertEquals(report,  Utility_Functions.getText(ownDriver,ItemLedgerPage.lblQtyAfter), "100", "Validating item quantity");
        	click(ItemLedgerPage.btnExit,"Exit from page");
    	}
    }

    public void validateMultipleLedgerFromAdjustment() {
    	for(int i=0; i<=1;i++) {
    		sendKeys(ownDriver.findElements(CostAdjustmentPage.txtItemNumber).get(i),Utility_Functions.xGetJsonAsString("ItemNo"+i),"Enter item number");
    		Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    	}

    	for(int i=0;i<=1;i++) {
    		click(ownDriver.findElements(CostAdjustmentPage.btnLedger).get(i),"Click on the item ledger button");
    		Utility_Functions.xAssertEquals(report,  getAttribute(ItemLedgerPage.txtItemNumber,"value"), Utility_Functions.xGetJsonAsString("ItemNo"+i), "Validating item number");
        	Utility_Functions.xAssertEquals(report,  Utility_Functions.getText(ownDriver,ItemLedgerPage.lblQtyAfter), "100", "Validating item quantity");
        	click(ItemLedgerPage.btnExit,"Exit from page");
    	}
    }
}
