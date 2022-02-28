package businesskeywords.SalesQuotes.WorkWithSalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.PurchaseOrders.InventoryReceiptPage;
import pages.SalesQuotes.WorkWithSalesQuotesPage;
import pages.pricing.AddSpecialPricingPage;
import supportLibraries.Utility_Functions;

import java.awt.event.KeyEvent;


public class POReceiptCorrects extends ReusableLib {
    CommonActions commonObj;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public POReceiptCorrects(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    /************************Inventory Receipt***********************/
    /**
     * This method navigate To Inventory Receipt
     */
    public void navigateToInventoryReceipt() {
        commonObj.masterToPurchaseOrder();
        commonObj.purchaseOrderToInventoryReceipt();
        commonObj.validateText(InventoryReceiptPage.inventoryHeader, "Inventory Receipts -", "'Inventory Receipts - (I-735)' header is present");
    }

    /**
     * This method navigate To Inventory Receipt
     */
    public void inventReceipt() {
        sendKey(WorkWithSalesQuotesPage.purcOrdNo, Utility_Functions.xGetJsonData("POSmoke"));
        click(WorkWithSalesQuotesPage.process, "Click Process button");
        String imNo = Utility_Functions.getText(driver, WorkWithSalesQuotesPage.itemNo).trim();
        String itmDesc = Utility_Functions.getText(driver, WorkWithSalesQuotesPage.itmDesc).trim();
        String testDesc = jsonData.getData("TestDescription");
        String relSaleOrd = Utility_Functions.getText(driver, WorkWithSalesQuotesPage.relSaleOrd);
        sendKey(WorkWithSalesQuotesPage.qtyRec, "1");
        Utility_Functions.xAssertEquals(report, imNo, Utility_Functions.xGetJsonData("SQItem"), "Item Number: ");
        Utility_Functions.xAssertEquals(report, testDesc, itmDesc, "Item Description: ");
        Utility_Functions.xAssertEquals(report, Utility_Functions.xGetJsonData("SOSmoke") + "-01", relSaleOrd, "Related Sale Order: ");
        click(WorkWithSalesQuotesPage.proc, "Click Process button");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        click(WorkWithSalesQuotesPage.exitBtn, "Click Exit Button");
    }

    /**
     * This method verify UI of INVENTORY RECEIPTS - (I-735)
     */
    public void inventoryReceiptUI() {
        commonObj.validateElementExists(InventoryReceiptPage.purchaseOrdNo, "Purchase Order Number input field is present");
        commonObj.validateElementExists(InventoryReceiptPage.vendorInvoiceNo, "Vendor Invoice Number input field is present");
        commonObj.validateElementExists(InventoryReceiptPage.receivedBy, "Received By input field is present");
        commonObj.validateElementExists(InventoryReceiptPage.receiverDoc, "Receiver Document input field is present");
        commonObj.validateElementExists(InventoryReceiptPage.vendor, "Vendor field is present");
        commonObj.validateElementExists(InventoryReceiptPage.receivedDate, "Received Date input field is present");
        commonObj.validateElementExists(InventoryReceiptPage.receiverDocNotes, "Receiver Document Notes input field is present");
        commonObj.validateElementExists(InventoryReceiptPage.lastOrdNo, "Last Order Number field is present");
        commonObj.validateElementExists(InventoryReceiptPage.freightCharges, "Freight Charges drop down is present");
        commonObj.validateElementExists(InventoryReceiptPage.exitBtn, "Exit button is present");
        commonObj.validateElementExists(InventoryReceiptPage.cancelPO, "Cancel Po Button is present");
        commonObj.validateElementExists(InventoryReceiptPage.processBtn, "Process Button is present");
        click(WorkWithSalesQuotesPage.saleQExtBtn, "Click Exit Button");
    }

    public void navigateToPoInqPage() {
        click(InventoryReceiptPage.poInqLink, "Click Purchase Order Inquiry Link");
        commonObj.validateText(InventoryReceiptPage.poInquiry, "Purchase Order Inquiry", "Purchase Order Inquiry Header is present");
    }

    /**
     * This method to exit from Purchase Order Inquiry
     */
    public void exitPoInqPage() {
        Utility_Functions.xScrollIntoView(driver, WorkWithSalesQuotesPage.saleQExtBtn);
        click(AddSpecialPricingPage.btnF12, "Click Back button");
        commonObj.validateText(InventoryReceiptPage.inventoryHeader, "Inventory Receipts -", "'Inventory Receipts - (I-735)' header is present");
    }

    public void exitInventoryReceipt() {
        Utility_Functions.xScrollIntoView(driver, WorkWithSalesQuotesPage.saleQExtBtn);
        click(WorkWithSalesQuotesPage.saleQExtBtn, "Click Exit Button");
    }

    /**
     * This method verify PO Inquiry Link
     */
    public void poInquiryLink() {
        navigateToPoInqPage();
        exitPoInqPage();
    }

    public void getPoDetails() {
        Utility_Functions.xUpdateJson("VendorNamePoDetails", getText(InventoryReceiptPage.VendNameDet).trim());
    }

    /**
     * This method to find PO number which having Line Item
     */
    public String findLineItemPO() {
        Utility_Functions.xSelectDropdownByName(driver, report, InventoryReceiptPage.ShipmentType, "DIRECT (Direct Shipment)", "Select direct Shipment from the Type drop down");
        int records = driver.findElements(InventoryReceiptPage.orderTotal).size();
        String poNo = null;
        for (int i = 0; i < records; i++) {
            Double isLineItemPresent = Double.parseDouble(driver.findElements(InventoryReceiptPage.orderTotal).get(i).getText());
            if (isLineItemPresent > 1) {
                poNo = driver.findElements(By.xpath("//div[contains(@id,'P1RECNME')]/preceding::div[contains(@id,'P1ORDNUM')]")).get(i).getText().trim();
                Utility_Functions.xUpdateJson("PONoLineItem", poNo);
                sendKeys(driver.findElements(By.xpath("//div[contains(@id,'P1RECNME')]/preceding::div[contains(@id,'P1OPT')]/input")).get(i), "1", "Navigate to Po details page");
                Utility_Functions.actionKey(Keys.ENTER, driver);
                break;
            }
        }
        getPoDetails();
        Utility_Functions.xScrollIntoView(driver, WorkWithSalesQuotesPage.saleQExtBtn);
        click(AddSpecialPricingPage.btnF12, "Click Back button");
        return poNo;
    }

    /**
     * This method to Enter PO Number
     */
    public void enterPoNo() {
        sendKeys(InventoryReceiptPage.purchaseOrdNo, Utility_Functions.xGetJsonData("PONoLineItem"), "Enter Purchase Order Number");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        commonObj.validateText(InventoryReceiptPage.headerIR, "inventory receipts", "inventory receipts Header is present");
    }

    /**
     * This method verify the functionality of Purchase Order Number input field
     */
    public void poNoField() {
        navigateToPoInqPage();
        /*String poNumber = findLineItemPO();
        exitPoInqPage();*/
        enterPoNo();
        commonObj.validateText(InventoryReceiptPage.poInqIR, "030128", "Po number matches");
        commonObj.validateText(InventoryReceiptPage.ReceivedByIN, System.getProperty("UserName"), "Received By matches");
        Utility_Functions.xScrollIntoView(driver, WorkWithSalesQuotesPage.proc);
        click(WorkWithSalesQuotesPage.saleQExtBtn, "Click Exit Button");
    }

    /**
     * This method verify the functionality of Order in Use popup
     */
    public void orderInUse() {
        String poNo = jsonData.getData("PurchaseOrderNo");
        sendKeys(InventoryReceiptPage.purchaseOrdNo, poNo, "Enter Purchase Order Number");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        commonObj.validateText(InventoryReceiptPage.orderInUsePopUp, "ORDER IN USE", "'ORDER IN USE' popup is present");
        commonObj.validateText(InventoryReceiptPage.outOrderNum, poNo, "'Order #" + poNo + " is in use' is present");
        commonObj.validateText(InventoryReceiptPage.outUserId, properties.getProperty("UserName"), "User id is matches");
        commonObj.validateText(InventoryReceiptPage.outWorkStation, "DEVTPM001", "Workstation is matches");
        click(InventoryReceiptPage.btnContinue, "Click Continue button");
        commonObj.validateText(InventoryReceiptPage.inventoryHeader, "Inventory Receipts -", "'Inventory Receipts - (I-735)' header is present");
        click(WorkWithSalesQuotesPage.exitBtn, "Click Exit Button");
    }

    /**
     * This method exit from Purchase Order Page
     */
    public void exitPurchaseToMasterPage() {
        commonObj.exitSalesQuotesToMasterPage();
    }

}
