package businesskeywords.Inventory.averagecost;


import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import pages.ItemMasterPage;
import pages.ReceiveCorrectionPage;
import pages.CostAdjustmentPage;
import supportLibraries.Utility_Functions;
import commonkeywords.*;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

public class createCost extends ReusableLib {
    CommonActions commonObj;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public createCost(Helper helper) {

        super(helper);
        commonObj = new CommonActions(helper);
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
        click(ItemMasterPage.addItemAction,"Click on add item");
        sendKeys(ItemMasterPage.txtBoxDescription,"testdesc1","Enter description");
        sendKeys(ItemMasterPage.txtBoxUOM,"EA","Enter UOM");
        click(ItemMasterPage.btnSave,"Click on save changes");

        if (Utility_Functions.xWaitForElementPresent(driver, ItemMasterPage.messageAddSuccessful, 10)) {
            String successMessage = Utility_Functions.getText(driver,  ItemMasterPage.messageAddSuccessful);
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
        Utility_Functions.actionKey(Keys.ENTER, driver);
        click(ItemMasterPage.deleteItemAction,"Click on delete action link");
        click(ItemMasterPage.btnAlertContinue,"Click on alert button");

        if (Utility_Functions.xWaitForElementPresent(driver, ItemMasterPage.messageAddSuccessful, 10)) {
            String successMessage = Utility_Functions.getText(driver,  ItemMasterPage.messageAddSuccessful);
            System.out.println("Text: " + successMessage);
            Utility_Functions.xAssertEquals(report, "Item "+Utility_Functions.xGetJsonAsString("CreatedCost")+" successfully deleted", successMessage.trim(), "Validating success message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + ItemMasterPage.messageAddSuccessful);
        }
    }
    
    public void updateAvgCostItem() {
    	sendKeys(ItemMasterPage.txtBoxSearch,Utility_Functions.xGetJsonAsString("CreatedCost"),"Entering search string");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	sendKeys(ItemMasterPage.txtInputPkgQuantity,"100","Updating package quantity");
    	 click(ItemMasterPage.btnSave,"Click on save changes");
    	 
    	 if (Utility_Functions.xWaitForElementPresent(driver, ItemMasterPage.messageAddSuccessful, 10)) {
             String successMessage = Utility_Functions.getText(driver,  ItemMasterPage.messageAddSuccessful);
             System.out.println("Text: " + successMessage);
             Utility_Functions.xAssertEquals(report, "Fields have recently been changed=>VERIFY CHANGES!", successMessage.trim(), "Validating success message");
         } else {
             System.out.println("Text: Not found");
             throw new NoSuchElementException("Could not find :" + ItemMasterPage.messageAddSuccessful);
         }
    	
    }
    
    public void attachVendor() {
    	sendKeys(ReceiveCorrectionPage.txtVendorSearch,"000388","Enter vendor code");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	sendKeys(ReceiveCorrectionPage.txtQuantity,"100","Enter quantity");
    	sendKeys(ReceiveCorrectionPage.txtItemNumber,Utility_Functions.xGetJsonAsString("CreatedCost"),"Enter item number");
    	sendKeys(ReceiveCorrectionPage.txtExplanation,"Test data","Enter explanation");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	
    	
    	 click(ReceiveCorrectionPage.btnProcess,"Click on process");
    	 click(ReceiveCorrectionPage.btnContinuePop,"Click on continue");
    	 
    	 sendKeys(ReceiveCorrectionPage.txtItemNumber,Utility_Functions.xGetJsonAsString("CreatedCost"),"Enter item number");
    	 Utility_Functions.actionKey(Keys.ENTER, driver);
     	String onHandVal = Utility_Functions.getText(driver,ReceiveCorrectionPage.lblOnHand);
     	
     	Utility_Functions.xAssertEquals(report, "100", onHandVal.trim(), "Validating on hand value");
    }
    
    public void fillCostAdjustmentDetails() {
    	
    	sendKeys(CostAdjustmentPage.txtItemNumber,Utility_Functions.xGetJsonAsString("CreatedCost"),"Enter item number");
    	sendKeys(CostAdjustmentPage.txtQuantity,"50","Enter quantity to adjust");
    	sendKeys(CostAdjustmentPage.txtInCorrectCost,"1","Enter incorrect cost");
    	sendKeys(CostAdjustmentPage.txtNewCost,"2","Enter correct cost");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	 String currAvgCost = Utility_Functions.getText(driver, CostAdjustmentPage.lblCurrAvgCost);
    	 String newAvgCost = Utility_Functions.getText(driver, CostAdjustmentPage.lblCurrNewCost);
    	 Utility_Functions.xUpdateJson("NewAvgCost", newAvgCost);
    	 Utility_Functions.xAssertNotEquals(report, currAvgCost, newAvgCost, "Validating that current avg cost is not equal to new avg cost");
    	 
    	
    }
    
    public void processCostAdjustment() {
    	click(CostAdjustmentPage.btnProcess,"Click on the process button");
    }
    
    public void validateLedger() {
    	sendKeys(CostAdjustmentPage.txtItemNumber,Utility_Functions.xGetJsonAsString("CreatedCost"),"Enter item number");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	click(CostAdjustmentPage.btnLedger,"Click on the item ledger button");
    	//Utility_Functions.xAssertEquals(report,  Utility_Functions.getText(driver, CostAdjustmentPage.lblLedgerPrice).substring(0, 4), Utility_Functions.xGetJsonAsString("NewAvgCost").substring(0, 4), "Validating on ledger price value");
    }
}
