package businesskeywords.SalesQuotes.WorkWithSalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.PurchaseOrders.InventoryReceiptPage;
import pages.PurchaseOrders.PoEntryConversionFactorPage;
import pages.SalesQuotes.OldWorkWithSalesQuotesPage;
import pages.pricing.AddSpecialPricingPage;
import supportLibraries.Utility_Functions;

import java.util.Locale;


public class POReceiptCorrects extends ReusableLib {
    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public POReceiptCorrects(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver=helper.getGSDriver();
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
        sendKey(OldWorkWithSalesQuotesPage.purcOrdNo, Utility_Functions.xGetJsonData("POSmoke"));
        click(OldWorkWithSalesQuotesPage.process, "Click Process button");
        String imNo = Utility_Functions.getText(ownDriver, OldWorkWithSalesQuotesPage.itemNo).trim();
        String itmDesc = Utility_Functions.getText(ownDriver, OldWorkWithSalesQuotesPage.itmDesc).trim();
        String testDesc = jsonData.getData("TestDescription");
        String relSaleOrd = Utility_Functions.getText(ownDriver, OldWorkWithSalesQuotesPage.relSaleOrd);
        sendKey(OldWorkWithSalesQuotesPage.qtyRec, "1");
        Utility_Functions.xAssertEquals(report, imNo, Utility_Functions.xGetJsonData("SQItem"), "Item Number: ");
        Utility_Functions.xAssertEquals(report, testDesc, itmDesc, "Item Description: ");
        Utility_Functions.xAssertEquals(report, Utility_Functions.xGetJsonData("SOSmoke") + "-01", relSaleOrd, "Related Sale Order: ");
        click(OldWorkWithSalesQuotesPage.proc, "Click Process button");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        click(OldWorkWithSalesQuotesPage.exitBtn, "Click Exit Button");
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
        //click(WorkWithSalesQuotesPage.saleQExtBtn, "Click Exit Button");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
    }

    public void navigateToPoInqPage() {
        click(InventoryReceiptPage.poInqLink, "Click Purchase Order Inquiry Link");
        commonObj.validateText(InventoryReceiptPage.poInquiry, "Purchase Order Inquiry", "Purchase Order Inquiry Header is present");
    }

    /**
     * This method to exit from Purchase Order Inquiry
     */
    public void exitPoInqPage() {
        Utility_Functions.xScrollIntoView(ownDriver, OldWorkWithSalesQuotesPage.saleQExtBtn);
        click(AddSpecialPricingPage.btnF12, "Click Back button");
        commonObj.validateText(InventoryReceiptPage.inventoryHeader, "Inventory Receipts -", "'Inventory Receipts - (I-735)' header is present");
    }

    public void exitInventoryReceipt() {
        //Utility_Functions.xScrollIntoView(driver, WorkWithSalesQuotesPage.saleQExtBtn);
        //click(WorkWithSalesQuotesPage.saleQExtBtn, "Click Exit Button");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
    }

    public void exitInventoryReceiptPage() {
        click(OldWorkWithSalesQuotesPage.exitBtn, "Click Exit Button");
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
        Utility_Functions.xSelectDropdownByName(ownDriver, report, InventoryReceiptPage.ShipmentType, "DIRECT (Direct Shipment)", "Select direct Shipment from the Type drop down");
        int records = ownDriver.findElements(InventoryReceiptPage.orderTotal).size();
        String poNo = null;
        for (int i = 0; i < records; i++) {
            Double isLineItemPresent = Double.parseDouble(ownDriver.findElements(InventoryReceiptPage.orderTotal).get(i).getText());
            if (isLineItemPresent > 1) {
                poNo = ownDriver.findElements(By.xpath("//div[contains(@id,'P1RECNME')]/preceding::div[contains(@id,'P1ORDNUM')]")).get(i).getText().trim();
                Utility_Functions.xUpdateJson("PONoLineItem", poNo);
                sendKeys(ownDriver.findElements(By.xpath("//div[contains(@id,'P1RECNME')]/preceding::div[contains(@id,'P1OPT')]/input")).get(i), "1", "Navigate to Po details page");
                Utility_Functions.actionKey(Keys.ENTER, ownDriver);
                break;
            }
        }
        getPoDetails();
        Utility_Functions.xScrollIntoView(ownDriver, OldWorkWithSalesQuotesPage.saleQExtBtn);
        click(AddSpecialPricingPage.btnF12, "Click Back button");
        return poNo;
    }

    /**
     * This method to Enter PO Number
     */
    public String enterPoNo() {
        String poNo = Utility_Functions.xGetJsonData("PONumber");
        sendKeys(InventoryReceiptPage.purchaseOrdNo, poNo, "Enter Purchase Order Number");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        return poNo;
    }

    /**
     * This method verify the functionality of Purchase Order Number input field
     */
    public void poNoField() {
        String poNo = enterPoNo();
        commonObj.validateText(InventoryReceiptPage.headerIR, "inventory receipts", "inventory receipts Header is present");
        commonObj.validateText(InventoryReceiptPage.poInqIR, poNo, "Po number matches");
        commonObj.validateText(InventoryReceiptPage.ReceivedByIN, properties.getProperty("UserName").toUpperCase(Locale.ROOT), "Received By matches");
        exitIR();
    }

    public void exitIR() {
        Utility_Functions.xScrollIntoView(ownDriver, OldWorkWithSalesQuotesPage.proc);
        click(OldWorkWithSalesQuotesPage.exitIR, "Click Exit Button");
    }

    /**
     * This method switch tab
     */
    public void switchTab() {
        Utility_Functions.openNewTab(ownDriver);
        Utility_Functions.xSwitchToWindow(ownDriver, 1);
    }

    /**
     * This method switch tab
     */
    public void switchTabBack() {
        Utility_Functions.xSwitchToWindow(ownDriver, 0);
    }

    /**
     * This method verify the functionality of Order in Use popup
     */
    public void orderInUse() {
        String poNo = Utility_Functions.xGetJsonData("PONumber");
        commonObj.validateText(InventoryReceiptPage.orderInUsePopUp, "ORDER IN USE", "'ORDER IN USE' popup is present");
        commonObj.validateText(InventoryReceiptPage.outOrderNum, poNo, "'Order #" + poNo + " is in use' is present");
        commonObj.validateText(InventoryReceiptPage.outUserId, properties.getProperty(getProperties("ENV")+"UserName"), "User id is matches");
        click(InventoryReceiptPage.btnContinue, "Click Continue button");
        commonObj.validateText(InventoryReceiptPage.inventoryHeader, "Inventory Receipts -", "'Inventory Receipts - (I-735)' header is present");
        click(OldWorkWithSalesQuotesPage.exitBtn, "Click Exit Button");
    }

    public void verifyQtyField(String val, By by) {
        String qty = jsonData.getData(val);
        clearText(by);
        sendKeys(by, qty, "Enter " + qty + " into quantity received input field");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    }

    /**
     * This method verify the functionality of Quantity Received Input Field
     */
    public void qtyReceivedField() {
        verifyQtyField("negativeQtyReceived",InventoryReceiptPage.qtyReceived);
        Utility_Functions.xAssertEquals(report, getAttribute(InventoryReceiptPage.qtyReceived, "title").trim(), "Qty Received Cannot be less than 0.", "");
        verifyQtyField("blankSpaceQtyReceived",InventoryReceiptPage.qtyReceived);
        commonObj.validateText(PoEntryConversionFactorPage.toaster, "F9 Accepts Order", "'F9 Accepts Order' toaster message is present");
        verifyQtyField("zeroQtyReceived",InventoryReceiptPage.qtyReceived);
        commonObj.validateText(PoEntryConversionFactorPage.toaster, "F9 Accepts Order", "'F9 Accepts Order' toaster message is present");
        verifyQtyField("alphaNumericQtyReceived",InventoryReceiptPage.qtyReceived);
        commonObj.validateText(InventoryReceiptPage.displayProgramMes, "Display Program Messages", "'Display Program Messages' Header is present");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    }

    /**
     * This method to verify Unit Cost Field
     */
    public void unitCostField() {
        sendKeys(InventoryReceiptPage.unitCostField, jsonData.getData("unitCost") + Keys.ENTER, "Enter value into Unit COst field");
        commonObj.validateText(PoEntryConversionFactorPage.toaster, "F9 Accepts Order", "'F9 Accepts Order' toaster message is present");
    }

    /**
     * This method to verify Unit Cost Field
     */
    public void verifyUnitCostField() {
        verifyQtyField("negativeUnitCost",InventoryReceiptPage.unitCostField);
        Boolean isToasterPresent=isDisplayed(PoEntryConversionFactorPage.toaster);
        Utility_Functions.xAssertEquals(report,isToasterPresent,false,"Page is processed and 'F9 Accepts Order' toaster message is not present");
        verifyQtyField("zeroUnitCost",InventoryReceiptPage.unitCostField);
        commonObj.validateText(PoEntryConversionFactorPage.toaster, "F9 Accepts Order", "'F9 Accepts Order' toaster message is present");
        verifyQtyField("blankSpaceUnitCost",InventoryReceiptPage.unitCostField);
        commonObj.validateText(PoEntryConversionFactorPage.toaster, "F9 Accepts Order", "'F9 Accepts Order' toaster message is present");
        verifyQtyField("alphaNumericUnitCost",InventoryReceiptPage.unitCostField);
        commonObj.validateText(InventoryReceiptPage.displayProgramMes, "Display Program Messages", "'Display Program Messages' Header is present");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    }

    /**
     * This method to Enter PO Number
     */
    public void validatePoNo(String poNum) {
        String poNo = jsonData.getData(poNum);
        sendKeys(InventoryReceiptPage.purchaseOrdNo, poNo, "Enter Purchase Order Number");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(PoEntryConversionFactorPage.toaster,"No Detail Lines exist for this Purchase Order","'No Detail Lines exist for this Purchase Order' toaster is present");
    }

    /**
     * This method to verify Purchase Order Number input field
     */
    public void verifyPoNumber() {
        validatePoNo("validPO");
        validatePoNo("inValidPO");
        validatePoNo("BlankSpacePO");
        validatePoNo("alphaNumericInvalidPO");
    }

    /**
     * This method exit from Purchase Order Page
     */
    public void exitPurchaseToMasterPage() {
        commonObj.exitSalesQuotesToMasterPage();
    }

}
