package businesskeywords.Inventory.averagecost;


import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.WebElement;
import pages.AddSpecialPricingPage;
import pages.ItemMasterPage;
import pages.LoginPage;
import pages.MasterPage;
import pages.ReceiveCorrectionPage;
import pages.CostAdjustmentPage;
import pages.ItemLedgerPage;
import businesskeywords.*;
import supportLibraries.Utility_Functions;
import commonkeywords.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

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
    
    public void createMultipleItems() {
    	for(int i=0;i<=1;i++) {
    		click(ItemMasterPage.addItemAction,"Click on add item");
            sendKeys(ItemMasterPage.txtBoxDescription,"testdesc"+i,"Enter description for item number"+i+1);
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

            System.out.println("Created item : "+getAttribute(ItemMasterPage.txtBoxSearch,"value"));
            Utility_Functions.xUpdateJson("ItemNo"+i, getAttribute(ItemMasterPage.txtBoxSearch,"value"));
    	}
    	
    	click(ItemMasterPage.btnExit,"Exit from page");
    }
    
    public void attachVendor() {
    	sendKeys(ReceiveCorrectionPage.txtVendorSearch,"000388","Enter vendor code");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	
    	List<WebElement> eleQuantityList = driver.findElements(ReceiveCorrectionPage.txtQuantity);
    	List<WebElement> eleItemNumberList = driver.findElements(ReceiveCorrectionPage.txtItemNumber);
    	List<WebElement> eleExplanationList = driver.findElements(ReceiveCorrectionPage.txtExplanation);
    	//sendKeys(ReceiveCorrectionPage.txtQuantityfirst,"100","Enter quantity");
    	//sendKeys(ReceiveCorrectionPage.txtItemNumberfirst,Utility_Functions.xGetJsonAsString("CreatedCost"),"Enter item number");
    	//sendKeys(ReceiveCorrectionPage.txtExplanationfirst,"Test data","Enter explanation");
    	
    	for(int i=0;i<=1;i++) {
    		sendKeys(eleQuantityList.get(i),"100","Enter quantity");
    		sendKeys(eleItemNumberList.get(i),Utility_Functions.xGetJsonAsString("CreatedCost"),"Enter item number");
    		sendKeys(eleExplanationList.get(i),"Test data","Enter explanation");
    	}
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	
    	
    	 click(ReceiveCorrectionPage.btnProcess,"Click on process");
    	 click(ReceiveCorrectionPage.btnContinuePop,"Click on continue");
    	 
    	 sendKeys(driver.findElements(ReceiveCorrectionPage.txtItemNumber).get(0),Utility_Functions.xGetJsonAsString("CreatedCost"),"Enter item number");
    	 Utility_Functions.actionKey(Keys.ENTER, driver);
     	String onHandVal = Utility_Functions.getText(driver.findElements(ReceiveCorrectionPage.lblOnHand).get(0));
     	
     	Utility_Functions.xAssertEquals(report, "200", onHandVal.trim(), "Validating on hand value");
    }
    
    public void attachVendorMultipleItem() {
    	 commonObj.inventoryToInvAdjustments();
         commonObj.inventoryAdjustToInvCorrections();
         //commonObj.splPricingToAddPricing();
         commonObj.validateText(ReceiveCorrectionPage.lblTitle, "Receiving Correction (I-355)", "Validating item Master page title");
        sendKeys(ReceiveCorrectionPage.txtVendorSearch,"000388","Enter vendor code");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	
    	List<WebElement> eleQuantityList = driver.findElements(ReceiveCorrectionPage.txtQuantity);
    	List<WebElement> eleItemNumberList = driver.findElements(ReceiveCorrectionPage.txtItemNumber);
    	List<WebElement> eleExplanationList = driver.findElements(ReceiveCorrectionPage.txtExplanation);
    	
    	for(int i=0;i<=1;i++) {
    		sendKeys(eleQuantityList.get(i),"100","Enter quantity");
    		sendKeys(eleItemNumberList.get(i),Utility_Functions.xGetJsonAsString("ItemNo"+i),"Enter item number");
    		sendKeys(eleExplanationList.get(i),"Test data","Enter explanation");
    	}
    	
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	
    	
   	 click(ReceiveCorrectionPage.btnProcess,"Click on process");
   	 click(ReceiveCorrectionPage.btnContinuePop,"Click on continue");
   	 
   	 for(int i=0;i<=1;i++) {
   		 sendKeys(driver.findElements(ReceiveCorrectionPage.txtItemNumber).get(i),Utility_Functions.xGetJsonAsString("ItemNo"+i),"Enter item number");
   		Utility_Functions.actionKey(Keys.ENTER, driver);
     	String onHandVal = Utility_Functions.getText(driver.findElements(ReceiveCorrectionPage.lblOnHand).get(i));
     	
     	Utility_Functions.xAssertEquals(report, "100", onHandVal.trim(), "Validating on hand value");
   	 }
    }
    
    public void navigateItemLedgerFromCorrection() {
    	click(driver.findElements(ReceiveCorrectionPage.btnItemLedger).get(0),"Click on item ledger");
    	
    }
    
    public void navigateItemLedgerFromAdjustment() {
    	sendKeys(CostAdjustmentPage.txtItemNumber,Utility_Functions.xGetJsonAsString("CreatedCost"),"Enter item number");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	click(CostAdjustmentPage.btnLedger,"Click on the item ledger button");
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
    	
    	
    	Utility_Functions.xAssertEquals(report,  getAttribute(ItemLedgerPage.txtItemNumber,"value"), Utility_Functions.xGetJsonAsString("CreatedCost"), "Validating item number");
    	Utility_Functions.xAssertEquals(report,  Utility_Functions.getText(driver,ItemLedgerPage.lblQtyAfter), "200", "Validating item quantity");
    	
    }
    
    public void validateMultipleLedgerFromCorrection() {
    	for(int i=0;i<=1;i++) {
    		click(driver.findElements(ReceiveCorrectionPage.txtItemNumber).get(i),"");
    		click(driver.findElements(ReceiveCorrectionPage.btnItemLedger).get(i),"Click on item ledger");
    		Utility_Functions.xAssertEquals(report,  getAttribute(ItemLedgerPage.txtItemNumber,"value"), Utility_Functions.xGetJsonAsString("ItemNo"+i), "Validating item number");
        	Utility_Functions.xAssertEquals(report,  Utility_Functions.getText(driver,ItemLedgerPage.lblQtyAfter), "100", "Validating item quantity");
        	click(ItemLedgerPage.btnExit,"Exit from page");
    	}
    }
}