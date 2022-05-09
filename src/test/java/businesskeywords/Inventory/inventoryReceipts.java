package businesskeywords.Inventory;

import businesskeywords.PurchaseOrders.PoEntryConversionFactor;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.PurchaseOrders.InventoryReceiptsPage;
import pages.common.MasterPage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import pages.warehouse.ReceivingInProcess.ReceivingInProcessPage;
import supportLibraries.Utility_Functions;

import java.util.List;


public class inventoryReceipts extends ReusableLib {

    CommonActions commonObj;
    PoEntryConversionFactor objPOEntryConvFactor;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public inventoryReceipts(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        objPOEntryConvFactor = new PoEntryConversionFactor(helper);
        ownDriver=helper.getGSDriver();
    }

    /**
     * This method validates the title of Inventory Receipts Page
     */
    public void navigateToInventoryReceipts() {
        commonObj.masterToPurchaseOrder();
        commonObj.purchaseOrderToInventoryReceipt();
        commonObj.validateText(InventoryReceiptsPage.pageTitle, "inventory receipts -", "Validating Inventory Receipts page title");
    }

    /**
     * This method exits from Inventory Receipts Page to master page
     */
    public void navigateInventoryReceiptsToMaster() {
        Utility_Functions.xScrollWindow(ownDriver);
        click(InventoryReceiptsPage.btnExit, "Click Exit Button");
        click(SpecialPriceAllowancePage.btnExit, "Click Exit Button");
    }

    /**
     * Keyword to select existing PO in Inventory Receipts Page
     */
    public void selectExistingPO() {
        click(InventoryReceiptsPage.btnPOInquiry, "Click [Purchase Order Inquiry] Button");
        String selectedPO = objPOEntryConvFactor.selectRandomPO();
        jsonData.putData("PONumber", selectedPO);

        xWaitForElementPresent(ownDriver, InventoryReceiptsPage.tbxPONumber, globalWait);
        String actualPO = getAttribute(InventoryReceiptsPage.tbxPONumber, "value");
        Utility_Functions.xAssertEquals(report, selectedPO, actualPO, "Selected Purchase Order Number is displayed");
    }

    /**
     * Keyword to enter existing PO Number in Inventory Receipts Page
     */
    public void enterExistingPONumber() {
        String poNumber = jsonData.getData("PONumber");
        sendKeysAndEnter(InventoryReceiptsPage.tbxPONumber, poNumber, "Enter Purchase Order Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to enter existing PO Number in Inventory Receipts Page
     */
    public void enterExistingPONumberRFGun() {
        String poNumber = Utility_Functions.xGetJsonData("PONumber");
        sendKeysAndEnter(InventoryReceiptsPage.tbxPONumber, poNumber, "Enter Purchase Order Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to enter invalid PO Number in Inventory Receipts Page
     */
    public void enterInvalidPONumber() {
        String poNumber = jsonData.getData("InvalidPONumber");
        sendKeysAndEnter(InventoryReceiptsPage.tbxPONumber, poNumber, "Enter invalid Purchase Order Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(InventoryReceiptsPage.growlText, "No Detail Lines exist for this Purchase Order", "Validating growl text");
    }

    /**
     * Keyword to enter existing PO Number without hitting enter key in Inventory Receipts Page
     */
    public void enterExistingPONumber2() {
        String poNumber = jsonData.getData("PONumber");
        sendKeys(InventoryReceiptsPage.tbxPONumber, poNumber, "Enter Purchase Order Number");
    }

    /**
     * Keyword to select desired Stock No. in Buyer's Inquiry Page
     */
    public void enterQtyRcvdAndProcess() {
        String qty = jsonData.getData("QtyOrdered");
        sendKeysAndEnter(InventoryReceiptsPage.tbxQtyRcvd, qty, "Enter Quantity Received");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to enter Quantity Received and process Order in Inventory Receipt page
     */
    public void enterQtyReceivedAndProcess() {
        Utility_Functions.timeWait(2);
        String qty = jsonData.getData("QtyReceived");
        sendKeysAndEnter(InventoryReceiptsPage.tbxQtyRcvd, qty, "Enter Quantity Received");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.timeWait(2);
        if (isDisplayed(ReceivingInProcessPage.serialItemPopup)) {
            click(ReceivingInProcessPage.skipBtn, "Click Skip Button");
            Utility_Functions.timeWait(2);
        }
    }

    /**
     * Keyword to validate Enable/Disable Cost checkbox in Inventory Receipts Page
     */
    public void validateEnableDisableCostChbx() {
        click(InventoryReceiptsPage.chbxEnableDisableCost, "Click [Disable Cost] checkbox");
        commonObj.validateText(InventoryReceiptsPage.growlText, "F9 Accepts Order", "Validating growl text");

        List<WebElement> lstUnitCostChbx = getListElement(InventoryReceiptsPage.lstUnitCostTbx);
        int count = 0;
        for (WebElement element : lstUnitCostChbx) {
            boolean flag = element.isEnabled();
            if (!flag)
                count++;
        }
        if (count == lstUnitCostChbx.size())
            report.updateTestLog("Verify Unit Cost Enabled/isabled", "Verify that [Unit Cost] textboxes are Disabled", Status.PASS);
        else
            report.updateTestLog("Verify Unit Cost Enabled/isabled", "Verify that [Unit Cost] textboxes are Enabled", Status.FAIL);

        click(InventoryReceiptsPage.chbxEnableDisableCost, "Click [Enable Cost] checkbox");
        commonObj.validateText(InventoryReceiptsPage.growlText, "F9 Accepts Order", "Validating growl text");

        lstUnitCostChbx = getListElement(InventoryReceiptsPage.lstUnitCostTbx);
        count = 0;
        for (WebElement element : lstUnitCostChbx) {
            boolean flag = element.isEnabled();
            if (flag)
                count++;
        }
        if (count == lstUnitCostChbx.size())
            report.updateTestLog("Verify Unit Cost Enabled/isabled", "Verify that [Unit Cost] textboxes are Enabled", Status.PASS);
        else
            report.updateTestLog("Verify Unit Cost Enabled/isabled", "Verify that [Unit Cost] textboxes are Disabled", Status.FAIL);
    }

    /**
     * Keyword to validate Sales Order Number link in Inventory Receipts Page
     */
    public void validateSOHyperlink() {
        commonObj.verifyElementContainsText(InventoryReceiptsPage.lstRelatedSalesOrder, jsonData.getData("SalesOrderNo"), "Validate SO Number in [Inventory Receipts] page");
        click(InventoryReceiptsPage.lstRelatedSalesOrder, "Click [Related Sales Order] link");
        commonObj.validateText(InventoryReceiptsPage.hdrSalesOrderInquiry, "Sales Order Inquiry", "Validating [Sales Order Inquiry] title");

        String expectedSO = jsonData.getData("SalesOrderNo");
        String actualSO = getAttribute(InventoryReceiptsPage.tbxOrderNo, "value");
        if (actualSO.contains(expectedSO))
            report.updateTestLog("Verify SO number", "Validate SO Number in [SALES ORDER INQUIRY] page", Status.PASS);
        else
            report.updateTestLog("Verify SO number", "Validate SO Number in [SALES ORDER INQUIRY] page", Status.FAIL);
    }

    /**
     * Keyword to navigate back to Inventory Receipts Page from Sales Order Inquiry
     */
    public void exitSalesOrderInquiry() {
        click(InventoryReceiptsPage.btnExit, "Click [Exit] button in Sales Order Inquiry page");
    }

    /**
     * Keyword to paginator in Inventory Receipts Page
     */
    public void vrfyPaginator() {
        commonObj.validateText(InventoryReceiptsPage.pageNo, "Page 1", "Validating page number");

        boolean flag = getAttribute(InventoryReceiptsPage.btnPrevious, "class").contains("disabled");
        if (flag)
            report.updateTestLog("Verify Previous Button", "Verify Previous Button is disabled", Status.PASS);
        else
            report.updateTestLog("Verify Previous Button", "Verify Previous Button is disabled", Status.FAIL);

        flag = getAttribute(InventoryReceiptsPage.btnNext, "class").contains("disabled");
        if (!flag) {
            click(InventoryReceiptsPage.btnNext, "Click [Next] button in Inventory Receipts page");
            if (!getAttribute(InventoryReceiptsPage.btnPrevious, "class").contains("disabled"))
                report.updateTestLog("Verify Previous Button", "Verify Previous Button is enabled", Status.PASS);
            else
                report.updateTestLog("Verify Previous Button", "Verify Previous Button is enabled", Status.FAIL);
        } else
            report.updateTestLog("Verify Next Button", "Verify Next Button is disabled by default", Status.PASS);
    }

    /**
     * Keyword to verify Cancel Purchase Order popup in Inventory Receipts Page
     */
    public void vrfyCancelPONumberPopup() {
        click(InventoryReceiptsPage.btnCancelPO, "Click [Cancel PO] button in Sales Order Inquiry page");
        //commonObj.validateText(InventoryReceiptsPage.hdrCancelPO, "CANCEL PURCHASE ORDER", "Validating Cancel PO popup header");
        commonObj.verifyElementContainsText(InventoryReceiptsPage.msgWarning, "Are you sure you wish to cancel this purchase order?", "Validating warning message");
        commonObj.validateElementExists(InventoryReceiptsPage.btnNo, "[No] button is present in CANCEL PURCHASE ORDER popup");
        commonObj.validateElementExists(InventoryReceiptsPage.btnYes, "[Yes] button is present in CANCEL PURCHASE ORDER popup");
    }

    /**
     * Keyword to verify Cancel Purchase Order functionality in Inventory Receipts Page
     */
    public void vrfyCancelPONumberFunctionality() {
        vrfyCancelPONumberPopup();
        click(InventoryReceiptsPage.btnNo, "Click [No] button in Cancel Purchase Order popup");
        navigateInventoryReceiptsToMaster();
        navigateToInventoryReceipts();
        enterExistingPONumber2();
        vrfyCancelPONumberPopup();
        click(InventoryReceiptsPage.btnYes, "Click [Yes] button in Cancel Purchase Order popup");
        String actualPONumber = getAttribute(InventoryReceiptsPage.tbxPONumber, "value");
        Utility_Functions.xAssertEquals(report, "", actualPONumber, "Verify PO number textbox is blank");
    }

    /**
     * Keyword to click Purchase Order Inquiry in Inventory Receipts Page
     */
    public void inquiryPO() {
        click(InventoryReceiptsPage.purchaseOrderInquiry, "Click [Purchase Order Inquiry] button");
    }

    /**
     * Keyword to enter Over Quantity Received and verify popup in Inventory Receipt page
     */
    public void verifyOverReceivingPO() {
        String qty = jsonData.getData("QtyReceived");
        sendKeysAndEnter(InventoryReceiptsPage.tbxQtyRcvd, qty, "Enter Quantity Received");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        verifyOverReceivingPOPopup();
        clickUpdateSalesOrderBtn();

        sendKeysAndEnter(InventoryReceiptsPage.tbxQtyRcvd, "500", "Enter Quantity Received");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        clickUpdateOnHandQtyBtn();

        Utility_Functions.actionKey(Keys.F9, ownDriver);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to verify [QUANTITY RECEIVED IS GREATER] popup in Inventory Receipt page
     */
    public void verifyOverReceivingPOPopup() {
        commonObj.verifyElementContainsText(InventoryReceiptsPage.hdrOverReceivedPOPopup, "QUANTITY RECEIVED IS GREATER THAN DIRECT SHIP BO", "Validating Over Received PO popup header");
        commonObj.validateElementExists(InventoryReceiptsPage.btnUpdateOnHandQty, "[Update On Hand Quantity] button is present in Over Received PO popup");
        commonObj.validateElementExists(InventoryReceiptsPage.btnUpdateSO, "[Uppdate Sales Order] button is present in Over Received PO popup");
    }

    /**
     * Keyword to click [Update Sales Order] button in [QUANTITY RECEIVED IS GREATER] popup
     */
    public void clickUpdateSalesOrderBtn() {
        click(InventoryReceiptsPage.btnUpdateSO, "Click [Update Sales Order] button");
        Utility_Functions.timeWait(2);
        boolean flag = getElement(InventoryReceiptsPage.tbxQtyRcvd).isEnabled();
        if (flag)
            report.updateTestLog("Verify Quantity Received tbx", "Verify Quantity Received tbx is enabled", Status.PASS);
        else
            report.updateTestLog("Verify Quantity Received tbx", "Verify Quantity Received tbx is enabled", Status.FAIL);
    }

    /**
     * Keyword to click [Update On Hand Quantity] button in [QUANTITY RECEIVED IS GREATER] popup
     */
    public void clickUpdateOnHandQtyBtn() {
        click(InventoryReceiptsPage.btnUpdateOnHandQty, "Click [Update Sales Order] button");
        Utility_Functions.timeWait(2);
    }


}
