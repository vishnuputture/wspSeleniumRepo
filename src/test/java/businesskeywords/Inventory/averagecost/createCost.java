package businesskeywords.Inventory.averagecost;


import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.WebElement;
import pages.AddSpecialPricingPage;
import pages.ItemMasterPage;
import pages.LoginPage;
import pages.MasterPage;
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
}