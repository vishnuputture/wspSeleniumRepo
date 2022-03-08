package businesskeywords.PurchaseOrders;

import businesskeywords.Inventory.inventoryReceipts;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Report;
import com.winSupply.framework.Status;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.PurchaseOrders.InventoryReceiptPage;
import pages.PurchaseOrders.InventoryReceiptsPage;
import pages.PurchaseOrders.PurchaseOrderInquiryPage;
import pages.common.MasterPage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;
import testcases.PO.InventoryReceipts;

import java.util.List;


public class PurchaseOrderInquiry extends ReusableLib {

    CommonActions commonObj;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public PurchaseOrderInquiry(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    /**
     * This method navigate To Po Inquiry
     */
    public void navigateToPoInquiry() {
        commonObj.masterToPurchaseOrder();
        click(PurchaseOrderInquiryPage.poInquiry,"Click Inquiry - Purchase Orders");
        commonObj.validateText(PurchaseOrderInquiryPage.inquiryHeader, "Purchase Order Inquiry", "'Purchase Order Inquiry' header is present");
    }

    /**
     * Keyword to enter PO Number and select action in PURCHASE ORDER INQUIRY Page
     */
    public void clickExitBtnPOInquiry() {
        click(PurchaseOrderInquiryPage.btnExitPOInquiryDtls, "Click [Exit] button");
    }

    /**
     * Keyword to enter PO Number and select action in PURCHASE ORDER INQUIRY Page
     */
    public void enterPONumberAndSelectAction() {
        String poNumber = jsonData.getData("PONumber");
        sendKeysAndEnter(PurchaseOrderInquiryPage.tbxPONumber, poNumber, "Enter Purchase Order Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        String action = jsonData.getData("Action");

        List<WebElement> lstOrderNo = getListElement(PurchaseOrderInquiryPage.lstOrderNumber);
        List<WebElement> lstAction = getListElement(PurchaseOrderInquiryPage.lstActions);

        int index = 0;
        for (WebElement element : lstOrderNo) {
            String text = element.getText().trim();
            if (text.equalsIgnoreCase(poNumber)) {
                index = lstOrderNo.indexOf(element);
                break;
            }
        }
        sendKeys(lstAction.get(index), action, "Selected PO Number");
        click(PurchaseOrderInquiryPage.btnSubmit, "Click [Submit] Button");
    }

    /**
     * Keyword to enter PO Number in PURCHASE ORDER INQUIRY Page
     */
    public void enterPONumber() {
        String poNumber = jsonData.getData("PONumber");
        sendKeysAndEnter(PurchaseOrderInquiryPage.tbxPONumber, poNumber, "Enter Purchase Order Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to verify that PO Number is NOT present in the table in PURCHASE ORDER INQUIRY Page
     */
    public void vrfyPONotInList() {
        String poNumber = jsonData.getData("PONumber");

        boolean flag = false;
        List<WebElement> lstOrderNo = getListElement(PurchaseOrderInquiryPage.lstOrderNumber);
        for (WebElement element : lstOrderNo) {
            String text = element.getText().trim();
            flag = text.equalsIgnoreCase(poNumber);
        }
        if (!flag)
            report.updateTestLog("Verify Order Number Not available", "Verify Order Number Not available in table", Status.PASS);
        else
            report.updateTestLog("Verify Order Number Not available", "Verify Order Number Not available in table", Status.FAIL);
    }

    /**
     * Keyword to verify Quatity and Item Number in PURCHASE ORDER INQUIRY and Item Ledger Page
     */
    public void vrfyQtyAndItemLedgerDetails() {
        String qty = jsonData.getData("Qty");
        commonObj.validateText(PurchaseOrderInquiryPage.qty, qty, "validate Quantity");

        String itemNumber = getElement(PurchaseOrderInquiryPage.itemNumber).getText().trim();
        click(PurchaseOrderInquiryPage.btnItemLedger, "Click [Item Ledger] Button");
        String itemNumberActual = getAttribute(PurchaseOrderInquiryPage.tbxItemNumber, "value");
        Utility_Functions.xAssertEquals(report, itemNumber, itemNumberActual, "Validate Item Number textbox value");
    }

    /**
     * Keyword to come back to Inventory Receipts from Item Ledger Page
     */
    public void backToInventoryReceipts() {
        click(PurchaseOrderInquiryPage.btnExitItemLedger, "Click [Exit] Button");
        click(PurchaseOrderInquiryPage.btnExitPOInquiryDtls, "Click [Exit] Button");
    }

    /**
     * Keyword to select Status
     */
    public void selectStatus() {
        Utility_Functions.xSelectDropdownByName(driver, PurchaseOrderInquiryPage.ddnStatus, jsonData.getData("Status"));
    }

    /**
     * Keyword to Enter Vendor and check for result
     */
    public void enterVendor() {
        String vendorNo=jsonData.getData("VendorNo");
        sendKeys(PurchaseOrderInquiryPage.vendorTextField,vendorNo+Keys.ENTER,"Enter Vendor Number");

    }
}
