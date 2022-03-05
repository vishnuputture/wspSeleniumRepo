package businesskeywords.Inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.PurchaseOrders.InventoryReceiptsPage;
import pages.common.MasterPage;
import pages.inventory.BuyersInquiryPage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

import java.util.List;


public class inventoryReceipts extends ReusableLib {

    CommonActions commonObj;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public inventoryReceipts(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
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
    public void navigateInventoryReceiptsToMaster()  {
        Utility_Functions.xScrollWindow(driver);
        click(InventoryReceiptsPage.btnExit,"Click Exit Button");
        click(SpecialPriceAllowancePage.btnExit,"Click Exit Button");
    }

    /**
     * Keyword to enter existing PO Number in Inventory Receipts Page
     */
    public void enterExistingPONumber(){
        String poNumber = jsonData.getData("PONumber");
        sendKeysAndEnter(InventoryReceiptsPage.tbxPONumber, poNumber,"Enter Purchase Order Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to select desired Stock No. in Buyer's Inquiry Page
     */
    public void enterQtyRcvdAndProcess(){
        String qty = jsonData.getData("QtyOrdered");
        sendKeysAndEnter(InventoryReceiptsPage.tbxQtyRcvd, qty,"Enter Quantity Received");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.actionKey(Keys.ENTER, driver);
        click(InventoryReceiptsPage.btnProcess,"Click Process Button");
    }

    /**
     * Keyword to validate Enable/Disable Cost checkbox in Inventory Receipts Page
     */
    public void validateEnableDisableCostChbx(){
        click(InventoryReceiptsPage.chbxEnableDisableCost,"Click [Disable Cost] checkbox");
        commonObj.validateText(InventoryReceiptsPage.growlText, "F9 Accepts Order", "Validating growl text");

        List<WebElement> lstUnitCostChbx = getListElement(InventoryReceiptsPage.lstUnitCostTbx);
        int count = 0;
        for (WebElement element : lstUnitCostChbx){
            boolean flag = element.isEnabled();
            if (!flag)
                count++;
        }
        if (count == lstUnitCostChbx.size())
            report.updateTestLog("Verify Unit Cost Enabled/isabled", "Verify that [Unit Cost] textboxes are Disabled", Status.PASS);
        else
            report.updateTestLog("Verify Unit Cost Enabled/isabled", "Verify that [Unit Cost] textboxes are Enabled", Status.FAIL);

        click(InventoryReceiptsPage.chbxEnableDisableCost,"Click [Enable Cost] checkbox");
        commonObj.validateText(InventoryReceiptsPage.growlText, "F9 Accepts Order", "Validating growl text");

        lstUnitCostChbx = getListElement(InventoryReceiptsPage.lstUnitCostTbx);
        count = 0;
        for (WebElement element : lstUnitCostChbx){
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
    public void validateSOHyperlink(){
        commonObj.verifyElementContainsText(InventoryReceiptsPage.lstRelatedSalesOrder, jsonData.getData("SalesOrderNo"), "Validate SO Number in [Inventory Receipts] page");
        click(InventoryReceiptsPage.lstRelatedSalesOrder,"Click [Related Sales Order] link");
        commonObj.validateText(InventoryReceiptsPage.hdrSalesOrderInquiry, "Sales Order Inquiry", "Validating [Sales Order Inquiry] title");

        String expectedSO = jsonData.getData("SalesOrderNo");
        String actualSO = getAttribute(InventoryReceiptsPage.tbxOrderNo, "value");
        if (actualSO.contains(expectedSO))
            report.updateTestLog("Verify SO number", "Validate SO Number in [SALES ORDER INQUIRY] page",Status.PASS);
        else
            report.updateTestLog("Verify SO number", "Validate SO Number in [SALES ORDER INQUIRY] page",Status.FAIL);
    }

    /**
     * Keyword to navigate back to Inventory Receipts Page from Sales Order Inquiry
     */
    public void exitSalesOrderInquiry(){
        click(InventoryReceiptsPage.btnExit,"Click [Exit] button in Sales Order Inquiry page");
    }




}
