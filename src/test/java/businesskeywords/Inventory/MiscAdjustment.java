package businesskeywords.Inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import pages.common.MasterPage;
import pages.inventory.MiscAdjustmentPage;
import supportLibraries.Utility_Functions;

public class MiscAdjustment extends ReusableLib {
    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public MiscAdjustment(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * Keyword to Navigate to AR2WISE
     */
    public void navigateToMiscAdj() {
        commonObj.masterToInventory();
        commonObj.inventoryToInvAdjustments();
        navMiscAdjPage();
    }

    /**
     * This method navigate to Misc Adjustment Option
     */
    public void navMiscAdjPage() {
        click(MiscAdjustmentPage.miscAdjMenuBtn, "Click [Miscellaneous Adjustments] menu button");
        commonObj.validateText(MiscAdjustmentPage.pageHeader, "Misc Adjustment (I-355)", "Validating [Misc Adjustment (I-355)] page header");
    }

    /**
     * Keyword to select [Adjustment Code] and [Vendor/Customer] in [MISC ADJUSTMENT (I-355)] page
     */
    public void selectAdjCodeAndCustomer() {
        String adjCode = jsonData.getData("AdjustmentCode");
        String vendor = jsonData.getData("Vendor");

        Utility_Functions.xSelectDropdownByName(ownDriver, MiscAdjustmentPage.ddnAjdCode, adjCode);
        sendKeysAndEnter(MiscAdjustmentPage.tbxVendorCustomer, vendor, "Enter Customer and selecting Adjustment Code");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to enter Item details in [MISC ADJUSTMENT (I-355)] page
     */
    public void enterItemDetails() {
        String itemNumber = jsonData.getData("ItemNumber");
        String qty = jsonData.getData("Quantity");
        String explanation = "Test Exp"+Utility_Functions.xRandomFunction(99, 9999);
        jsonData.putData("Explanation", explanation);

        sendKeysAndEnter(MiscAdjustmentPage.tbxItem1, itemNumber, "Enter Item Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        sendKeysAndEnter(MiscAdjustmentPage.tbxQty1, qty, "Enter Quantity for Item Number");
        sendKeysAndEnter(MiscAdjustmentPage.tbxExplanation1, explanation, "Enter Explanation for Item Number");
        String amount = getText(MiscAdjustmentPage.txtAmount);
        jsonData.putData("Amount", amount);
    }

    /**
     * Keyword to process Item Details in [MISC ADJUSTMENT (I-355)] page
     */
    public void processMiscAdjItems() {
        click(MiscAdjustmentPage.btnProcess, "Click on [Process] button");
        click(MiscAdjustmentPage.btnContinue, "Click on [Continue] button");
    }
}
