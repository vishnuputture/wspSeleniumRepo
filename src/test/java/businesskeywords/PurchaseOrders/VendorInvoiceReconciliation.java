package businesskeywords.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.junit.validator.ValidateWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import pages.PurchaseOrders.*;
import pages.common.MasterPage;
import pages.inventory.OptionsConstantsPage;
import pages.pricing.pricingmatrix.PricingMatrixPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import software.amazon.awssdk.services.glacier.model.PurchaseProvisionedCapacityRequest;
import software.amazon.awssdk.services.securityhub.model.Note;
import supportLibraries.Utility_Functions;

import java.util.Locale;

public class VendorInvoiceReconciliation extends ReusableLib {

    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public VendorInvoiceReconciliation(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * This method validates the title of Vendor Invoice Reconciliation Page
     */
    public void navigateToVendorInvoiceRec() {
        commonObj.masterToPurchaseOrder();
        commonObj.purchaseOrderToVendorInvoiceReconciliation();
        commonObj.validateText(VendorInvoiceReconciliationPage.vendorInvoiceRecHeader, "Vendor Invoice Reconciliation", "Validating [Vendor Invoice Reconciliation] page title");
    }

    /**
     * This method exit from Vendor invoices Reconciliation page
     */
    public void exitVendorInvoiceRec() {
        click(PurchaseOrderInquiryPage.btnExitItemLedger, "Click [Exit] button");
        commonObj.validateText(VendorInvoiceReconciliationPage.PoMainMenuHeader, "Purchase Orders - Main Menu", "Validating [Purchase Orders - Main Menu] page title");
    }

    public By textElement(String text) {
        return By.xpath("//div[contains(text(),'" + text + "')))]]");
    }

    public void validateReceivedDocFields() {
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.operatorTextBox, "[Operator] is present: " + getAttribute(VendorInvoiceReconciliationPage.operatorTextBox, "value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.receiverDoc, "[Received Doc.] is present: " + getAttribute(VendorInvoiceReconciliationPage.receiverDoc, "value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.dueDateTextBox, "[Due Date] is present: " + getAttribute(VendorInvoiceReconciliationPage.dueDateTextBox, "value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.receivedDate, "[Received Date] is present: " + getText(VendorInvoiceReconciliationPage.receivedDate));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.vendorInvTextBox, "[Vendor Inv #]  is present: " + getAttribute(VendorInvoiceReconciliationPage.operatorTextBox, "value"));
    }

    public void validateCalculationFields() {
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.invoiceTotalTextBox, "[Invoice Total] is present: " + getAttribute(VendorInvoiceReconciliationPage.invoiceTotalTextBox, "value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.discountPercentTextBox, "[Disc.] Percent is present: " + getAttribute(VendorInvoiceReconciliationPage.discountPercentTextBox, "value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.amountTextBox, "[-or- Amount] is present: " + getAttribute(VendorInvoiceReconciliationPage.amountTextBox, "value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.totalNetDue, "[Total Net Due] is present: " + getText(VendorInvoiceReconciliationPage.totalNetDue));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.freightAmountTextBox, "[Freight Amt] is present: " + getAttribute(VendorInvoiceReconciliationPage.freightAmountTextBox, "value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.allocMethodTextBox, "[Alloc. Method[ is present: " + getAttribute(VendorInvoiceReconciliationPage.allocMethodTextBox, "value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.custFrtAmountTextBox, "[Cust Frt Amt] is present: " + getAttribute(VendorInvoiceReconciliationPage.custFrtAmountTextBox, "value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.debitMemoTextBox, "[Debit Memo] is present: " + getAttribute(VendorInvoiceReconciliationPage.debitMemoTextBox, "value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.transDateTextBox, "[Trans. Date] is present: " + getText(VendorInvoiceReconciliationPage.transDateTextBox));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.detailedValue, "[Detail Total] is present: " + getText(VendorInvoiceReconciliationPage.detailedValue));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.paymentTerms, "[Payment Terms] is present: " + getText(VendorInvoiceReconciliationPage.paymentTerms) + getText(VendorInvoiceReconciliationPage.netVal));
    }

    public void validateVendorFields() {
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.orderNo, "[Order Number] is present: " + getText(VendorInvoiceReconciliationPage.orderNo));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.originalVendor, "[Original Vndr] is present: " + getAttribute(VendorInvoiceReconciliationPage.originalVendor, "value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.vendor, "[Vendor] is present: " + getText(VendorInvoiceReconciliationPage.vendor));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.recDocNote, "[Rec Doc Notes] is present");
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.invoicingVdrTextBox, "[Invoicing Vdr] is present: " + getAttribute(VendorInvoiceReconciliationPage.invoicingVdrTextBox, "value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.gmMarginAmt, "[GM Mgr Amount] is present");
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.receivedBy, "[Received By] is present: " + getText(VendorInvoiceReconciliationPage.receivedBy));
    }

    public void validateAllocationMethFields() {
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.freightCharges, "[Freight Charges] is present");
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.allocationMethod, "[Allocation Methods:] is present: " + getText(VendorInvoiceReconciliationPage.allocationMethod));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.qtyManual, "[Q = by quantity        M = manual] is present: " + getText(VendorInvoiceReconciliationPage.qtyManual));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.dollarCustPayFreight, "[D = by dollars         C = cust pays freight] is present: " + getText(VendorInvoiceReconciliationPage.dollarCustPayFreight));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.byWeightNotAllowed, "[W = by weight          Blank = not allocated] is present: " + getText(VendorInvoiceReconciliationPage.byWeightNotAllowed));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.warMessage, "[Due Date, Disc Percent and Disc Amount changes must also be made on AP Portal.] is present: " + getText(VendorInvoiceReconciliationPage.warMessage));
    }

    public void validateLinks() {
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.f4Prompt, "[F4=Prompt] is present: " + getAttribute(VendorInvoiceReconciliationPage.f4Prompt, "value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.newDoc, "[F5=New Doc.] is present: " + getAttribute(VendorInvoiceReconciliationPage.newDoc, "value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.moreKeys, "[f24=MoreKeys] is present: " + getAttribute(VendorInvoiceReconciliationPage.moreKeys, "value"));
        commonObj.validateElementExists(PurchaseOrderInquiryPage.btnSubmit, "[ENTER] is present: " + getAttribute(PurchaseOrderInquiryPage.btnSubmit, "value"));
        commonObj.validateElementExists(PurchaseOrderInquiryPage.btnExitItemLedger, "[f3=Exit] is present: " + getAttribute(PurchaseOrderInquiryPage.btnExitItemLedger, "value"));
    }

    /**
     * This method Availability fields on Vendor invoices Reconciliation page
     */
    public void vendorInvoiceRecUI() {
        getReceiverDoc();
        validateReceivedDocFields();
        validateCalculationFields();
        validateVendorFields();
        validateAllocationMethFields();
        validateLinks();
    }

    public void navigateToReceiverDocBrowser() {
        click(VendorInvoiceReconciliationPage.receiverDoc, "Click [Receiver Doc] text field");
        click(PurchaseOrderEntryPage.custBrowse, "Click [F1=Browse Rcv Doc]");
        commonObj.validateText(VendorInvoiceReconciliationPage.browseHeader, "Receiver Document Browse", "[Receiver Document Browse] Header is present");
    }

    public void getReceiverDoc() {
        navigateToReceiverDocBrowser();
        sendKeysAndEnter(VendorInvoiceReconciliationPage.selectVendor, "1", "Select Vendor");
        commonObj.validateText(VendorInvoiceReconciliationPage.vendorInvoiceRecHeader, "Vendor Invoice Reconciliation", "Validating [Vendor Invoice Reconciliation] page title");
    }

    public void validateErrorMessage(String inputValue) {
        sendKeysAndEnter(VendorInvoiceReconciliationPage.receiverDoc, jsonData.getData(inputValue), "Enter " + jsonData.getData(inputValue));
        commonObj.validateText(VendorInvoiceReconciliationPage.warningMessage, "Receiver Document Not Found", "[Receiver Document Not Found] warning is present");
    }

    /**
     * This method verify Receiver Doc Field
     */
    public void verifyReceiverDocField() {
        getReceiverDoc();
        click(VendorInvoiceReconciliationPage.newDoc, "Click [F5=New Doc]");
        validateErrorMessage("SpecialCharacter");
        validateErrorMessage("zero");
        validateErrorMessage("TenNumeric");
        validateErrorMessage("AlphaNumeric");
    }

    /**
     * This method verify Receiver Doc Field
     */
    public void verifyReceiverDocBrowse() {
        getReceiverDoc();
        Utility_Functions.actionKey(Keys.F1, ownDriver);
        sendKeysAndEnter(VendorInvoiceReconciliationPage.selectVendor, "1", "Select Vendor");
        commonObj.validateText(VendorInvoiceReconciliationPage.vendorInvoiceRecHeader, "Vendor Invoice Reconciliation", "Validating [Vendor Invoice Reconciliation] page title");
    }

    public void validateVendor() {
        click(PricingMatrixPage.addCol, "Click [F4=Prompt]");
        commonObj.validateText(PurchaseOrderInquiryPage.hdrPOPreferences, "Mailing Master Search (O-946)", "Validating [Mailing Master Search (O-946)] page title");
        String vendorNumber = getText(OptionsConstantsPage.vendorNumber);
        sendKeys(MailingMasterSearchPage.firstCustomerSelect, "1" + Keys.ENTER, "Select Customer No");
        Utility_Functions.timeWait(4);
        if(!isDisplayed(VendorInvoiceReconciliationPage.noRecords)){
            Utility_Functions.xAssertEquals(report, vendorNumber, getText(VendorInvoiceReconciliationPage.selMult), "The records get sorted based on vendor number");
        }
    }

    /**
     * This method verify Search Receiver Doc Vendor Number
     */
    public void verifySearchReceiverDocNo() {
        navigateToReceiverDocBrowser();
        click(PricingMatrixPage.selectRowTxtBox, "Click [Position to Vendor  . »] text box");
        validateVendor();
        clearText(PricingMatrixPage.selectRowTxtBox);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        click(VendorInvoiceReconciliationPage.searchVendor, "Click [Search Vendor ] Text Box");
        validateVendor();
    }

    public void clickReturn(){
        click(PurchaseOrderInquiryPage.btnF12Return, "Click [F12=Return]");
    }

    public String changeToPositionToVendorName() {
        click(CustomerGroupMaintenancePage.sort, "Click [F10=Vendor Name]");
        commonObj.validateText(SpecialPriceAllowancePage.textSearchHeader, "Position to Vendor Name", "Changed to [Position to Vendor Name]");
        return getText(VendorInvoiceReconciliationPage.getVendorName);
    }

    public String positionToRecDoc(){
        changeToPositionToVendorName();
        click(CustomerGroupMaintenancePage.sort, "Click [F10=Sort by Rec Doc]");
        commonObj.validateText(SpecialPriceAllowancePage.textSearchHeader, "Position to Rec Doc . .", "Changed to [Position to Rec Doc . .]");
        return getText(VendorInvoiceReconciliationPage.recDoc);
    }

    public void positionToVdrInvoice(){
        changeToPositionToVendorName();
        click(CustomerGroupMaintenancePage.sort, "Click [F10=Sort by Rec Doc]");
        commonObj.validateText(SpecialPriceAllowancePage.textSearchHeader, "Position to Rec Doc . .", "Changed to [Position to Rec Doc . .]");
        click(CustomerGroupMaintenancePage.sort, "Click [F10=Vend Inv.]");
        commonObj.validateText(SpecialPriceAllowancePage.textSearchHeader, "Position to Vend Inv  .", "Changed to [Position to Vend Inv  .]");
    }

    /**
     * This method verify Search Receiver Doc Vendor Name
     */
    public void verifySearchRecDocVdrName() {
        navigateToReceiverDocBrowser();
        String vendorName=changeToPositionToVendorName();
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox,vendorName,"Enter Vendor Name");
        commonObj.validateText(VendorInvoiceReconciliationPage.getVendorName, vendorName, "Vendor Name Sorted "+vendorName);
        click(VendorInvoiceReconciliationPage.searchVendor, "Click [Search Vendor ] Text Box");
        validateVendor();
    }

    /**
     * This method verify Search Position To Rec Doc
     */
    public void verifySearchRecDoc() {
        navigateToReceiverDocBrowser();
        String recDoc=positionToRecDoc();
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox,jsonData.getData("SpecialCharacter"),"Enter Vendor Name as "+jsonData.getData("SpecialCharacter"));
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox,jsonData.getData("zero"),"Enter Vendor Name as "+jsonData.getData("zero"));
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox,jsonData.getData("TenNumeric"),"Enter Vendor Name as "+jsonData.getData("TenNumeric"));
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox,jsonData.getData("AlphaNumeric"),"Enter Vendor Name as "+jsonData.getData("AlphaNumeric"));
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox,recDoc,"Enter Vendor Name");
        commonObj.validateText(VendorInvoiceReconciliationPage.recDoc, recDoc, "Vendor Name Sorted "+recDoc);
        click(VendorInvoiceReconciliationPage.searchVendor, "Click [Search Vendor ] Text Box");
        validateVendor();
    }

    /**
     * This method verify Search Position To Vendor Invoice
     */
    public void verifySearchVendorInvoice() {
        navigateToReceiverDocBrowser();
        positionToVdrInvoice();
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox,jsonData.getData("SpecialCharacter"),"Enter Vendor Name as "+jsonData.getData("SpecialCharacter"));
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox,jsonData.getData("zero"),"Enter Vendor Name as "+jsonData.getData("zero"));
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox,jsonData.getData("TenNumeric"),"Enter Vendor Name as "+jsonData.getData("TenNumeric"));
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox,jsonData.getData("AlphaNumeric"),"Enter Vendor Name as "+jsonData.getData("AlphaNumeric"));
        click(VendorInvoiceReconciliationPage.searchVendor, "Click [Search Vendor ] Text Box");
        validateVendor();
    }

    /**
     * Keyword to enter Receiver Doc.
     */
    public void enterReceiverDoc() {
        String receiverDoc = jsonData.getData("ReceiverDoc");
        sendKeysAndEnter(VendorInvoiceReconciliationPage.receiverDoc, receiverDoc, "Enter Receiver Doc as ["+receiverDoc+"]");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        String text = getAttribute(VendorInvoiceReconciliationPage.receiverDoc, "value");
        Utility_Functions.xAssertEquals(report, receiverDoc, text, "Receiver Doc ["+receiverDoc+"] selected successfully");
    }

    /**
     * Keyword to select random Receiver Doc.
     */
    public void selectRandomReceiverDoc() {
        navigateToReceiverDocBrowser();
        int count = Utility_Functions.xRandomFunction(0, 5);
        while(count>0){
            click(VendorInvoiceReconciliationPage.btnDown);
            waitForElementDisappear(MasterPage.loadingAnime, globalWait);
            count--;
        }
        String selectedRecDoc = getText(VendorInvoiceReconciliationPage.recDoc);
        jsonData.putData("ReceiverDoc", selectedRecDoc);
        sendKeysAndEnter(VendorInvoiceReconciliationPage.selectVendor, "1", "Selecting Vendor having Rec Doc as ["+selectedRecDoc+"]");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        if(Utility_Functions.xWaitForElementPresent(ownDriver, VendorInvoiceReconciliationPage.receiverDoc, 5)) {
            String text = getAttribute(VendorInvoiceReconciliationPage.receiverDoc, "value");
            Utility_Functions.xAssertEquals(report, selectedRecDoc, text, "Receiver Doc ["+selectedRecDoc+"] selected successfully");
        }else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :"+VendorInvoiceReconciliationPage.receiverDoc);
        }
    }

    /**
     * Keyword to search and select Receiver Doc.
     */
    public void vrfySelectReceiverDoc() {
        searchReceiverDocPositionToRecDoc();
        String selectedRecDoc = getText(VendorInvoiceReconciliationPage.recDocFifth);
        jsonData.putData("ReceiverDoc", selectedRecDoc);
        sendKeysAndEnter(VendorInvoiceReconciliationPage.selectVendorFifth, "1", "Selecting Vendor having Rec Doc as ["+selectedRecDoc+"]");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        String text = getAttribute(VendorInvoiceReconciliationPage.receiverDoc, "value");
        Utility_Functions.xAssertEquals(report, selectedRecDoc, text, "Receiver Doc ["+selectedRecDoc+"] selected successfully");
    }

    /**
     * Keyword to navigate to [Receiver Document Browse] page and set [Position to Rec Doc]
     */
    public void searchReceiverDocPositionToRecDoc() {
        navigateToReceiverDocBrowser();
        Utility_Functions.actionKey(Keys.F10, ownDriver);
        Utility_Functions.actionKey(Keys.F10, ownDriver);
    }

    /**
     * Keyword to verify Invoice Total and Detail Total are same
     */
    public void verifyInvoiceAndDetailTotal() {
        String txtInvoiceTotal = getAttribute(VendorInvoiceReconciliationPage.invoiceTotalTextBox, "value").trim();
        double it = Double.parseDouble(txtInvoiceTotal);

        String txtFreightAmount = getAttribute(VendorInvoiceReconciliationPage.freightAmountTextBox, "value").trim();
        double fa = Double.parseDouble(txtFreightAmount);

        double dtExpected = it-fa;
        String expectedDetailTotal = Double.toString(dtExpected);

        String txtDetailTotal = Utility_Functions.getText(ownDriver, VendorInvoiceReconciliationPage.detailedValue).trim();
        double dt = Double.parseDouble(txtDetailTotal);
        String actualDetailTotal = Double.toString(dt);
        Utility_Functions.xAssertEquals(report, expectedDetailTotal, actualDetailTotal, "Detail Total is displaying value ["+actualDetailTotal+"]");
    }

    /**
     * Keyword to verify Invoice Total and Detail Total are same
     */
    public void verifyInvoiceAndDetailTotalNoEdit() {
        String txtInvoiceTotal = getAttribute(VendorInvoiceReconciliationPage.invoiceTotalTextBox, "value").trim();
        commonObj.validateText(VendorInvoiceReconciliationPage.detailedValue, txtInvoiceTotal, "Validating [Detail Total] field");
    }

    /**
     * Keyword to verify GM Mgr Amount after editing Invoice Total
     */
    public void verifyGMMgrAmount() {
        String invoiceTotal = getAttribute(VendorInvoiceReconciliationPage.invoiceTotalTextBox, "value");
        if (invoiceTotal.contains(",")){
            invoiceTotal = invoiceTotal.replaceAll(",", "");
        }
        float num = Float.parseFloat(invoiceTotal)+1;
        String invoiceTotalEdit = Float.toString(num);
        sendKeysAndEnter(VendorInvoiceReconciliationPage.invoiceTotalTextBox, invoiceTotalEdit, "Entering ["+invoiceTotalEdit+"] in Invoice Total textbox");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        String gmMgrAmount = Utility_Functions.getText(ownDriver, VendorInvoiceReconciliationPage.gmMarginAmt).trim();
        Utility_Functions.xAssertEquals(report, "1.00-", gmMgrAmount, "GM Mgr Amount field is auto-calculated");

        sendKeysAndEnter(VendorInvoiceReconciliationPage.invoiceTotalTextBox, invoiceTotal, "Resetting Invoice Total tbx to ["+invoiceTotal+"]");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to enter Due Date same as Received Date
     */
    public void enterDueDateSameAsReceivedDate() {
        String receivedDate = getAttribute(VendorInvoiceReconciliationPage.receivedDate, "value");
        receivedDate = getText(VendorInvoiceReconciliationPage.receivedDate);
        sendKeysAndEnter(VendorInvoiceReconciliationPage.dueDateTextBox, receivedDate, "Entering Due Date same as Received Date ["+receivedDate+"]");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to accept Invoice by pressing F9
     */
    public void acceptInvoice() {
        ensureFreightCharges();
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.warningMessage, "Discount percentage and amount are ZERO.  Press F9 to accept invoice.", "validating warning message");
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.hdrGMMPage, "Vendor Invoice Reconciliation - Gross Margin Manager", "Validating [Vendor Invoice Reconciliation - Gross Margin Manager] page title");
        vrfyGMMgrPage();
    }

    /**
     * Keyword to accept Invoice by pressing F9 without editing Total Invoice
     */
    public void acceptInvoiceNoEdit() {
        ensureDueDate();
        ensureFreightCharges();
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.warningMessage, "Discount percentage and amount are ZERO.  Press F9 to accept invoice.", "validating warning message");
        Utility_Functions.actionKey(Keys.F9, ownDriver);
    }

    /**
     * Keyword to accept Invoice by pressing F9 after editing Total Invoice
     */
    public void acceptInvoicePostEdit() {
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.hdrGMMPage, "Vendor Invoice Reconciliation - Gross Margin Manager", "Validating [Vendor Invoice Reconciliation - Gross Margin Manager] page title");
    }

    /**
     * Keyword to add Freight Charges if not present
     */
    public void ensureFreightCharges() {
        String freightCharges = getText(VendorInvoiceReconciliationPage.freightCharges).trim();
        if (freightCharges.isEmpty()){
            sendKeysAndEnter(VendorInvoiceReconciliationPage.freightCharges, "FFA", "Entering Freight Charges as [FFA]");
        }
    }

    /**
     * Keyword to enter Due Date is NOT already present
     */
    public void ensureDueDate() {
        String dueDate = getText(VendorInvoiceReconciliationPage.dueDateTextBox).trim();
        if (dueDate.isEmpty()){
            enterDueDateSameAsReceivedDate();
        }
    }

    /**
     * Keyword to verify Gross Margin Manager Page
     */
    public void vrfyGMMgrPage() {
        String gmMgrAmount = Utility_Functions.getText(ownDriver, VendorInvoiceReconciliationPage.gmManagerAmount).trim();
        Utility_Functions.xAssertEquals(report, "1.00-", gmMgrAmount, "Validating auto-calculated field [The Gross Margin Manager Amount]");

        String recordGMMgr = getAttribute(VendorInvoiceReconciliationPage.recordThisGMManager, "value").trim();
        Utility_Functions.xAssertEquals(report, "Y", recordGMMgr, "Valudating default value for question [Do you wish to record this GM Manager?]");
    }

    /**
     * Keyword to verify Gross Margin Manager Page warning after pressing F9 without entering description
     */
    public void vrfyGMMgrWarning() {
        sendKeys(VendorInvoiceReconciliationPage.recordThisGMManager, "N", "Entering [N] in [Do you wish to record this GM Manager?] field");
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.warningMessageGMMgr, "Enter a description when NOT recording GM/Mgr.", "validating warning message");

        sendKeys(VendorInvoiceReconciliationPage.recordThisGMManager, "Y", "Entering [Y] in [Do you wish to record this GM Manager?] field");
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.warningMessageGMMgr, "Enter a description when recording GM/Mgr.", "validating warning message");
    }

    /**
     * Keyword to accept Invoice by pressing F9
     */
    public void acceptInvoiceGMMgrPage() {
        sendKeysAndEnter(VendorInvoiceReconciliationPage.explanation, "Testing Purpose", "Entering data in [Please key an explanation.] field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(VendorInvoiceReconciliationPage.warningMessageGMMgr, "Please press F9 to accept the GM/Mgr.", "validating warning message");

        Utility_Functions.actionKey(Keys.F9, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.vendorInvoiceRecHeader, "Vendor Invoice Reconciliation", "Validating [Vendor Invoice Reconciliation] page title");
    }

    /**
     * Keyword to accept Invoice having Debit Memo by pressing F9
     */
    public void acceptInvoiceWithDebitMemoGMMgrPage() {
        sendKeys(VendorInvoiceReconciliationPage.recordThisGMManager, "Y", "Entering [Y] in [Do you wish to record this GM Manager?] field");
        sendKeysAndEnter(VendorInvoiceReconciliationPage.explanation, "Testing Purpose", "Entering data in [Please key an explanation.] field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(VendorInvoiceReconciliationPage.warningMessageGMMgr, "Please press F9 to accept the GM/Mgr.", "validating warning message");
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.hdrDebitMemoProcessPage, "Debit Memo Process", "Validating [Debit Memo Process] page title");
    }

    /**
     * Keyword to verify details in [Vendor Invoice Reconciliation] after Accept Invoice operation
     */
    public void vrfyVIRPageDetailsAfterAccept() {
        String disabled = getAttribute(VendorInvoiceReconciliationPage.invoiceTotalTextBox, "class");
        commonObj.validateContainsText("readOnly", disabled, "Validating [Invoice Total] field");

        String gmMgrAmount = Utility_Functions.getText(ownDriver, VendorInvoiceReconciliationPage.gmMarginAmt).trim();
        Utility_Functions.xAssertEquals(report, "1.00-", gmMgrAmount, "GM Mgr Amount field is auto-calculated");

        commonObj.validateText(VendorInvoiceReconciliationPage.warningMessage, "Receiver Doc has been submitted for VIR.", "validating warning message");
    }

    /**
     * Keyword to verify warning for selecting already invoiced Receiver Doc.
     */
    public void vrfyInvoicedWarning() {
        commonObj.validateText(VendorInvoiceReconciliationPage.warningMessage, "Receiver Doc has been submitted for VIR.", "validating warning message");
    }

    /**
     * Keyword to validate [Position to Receiver Doc.]
     */
    public void vrfyPositionToRecDoc() {
        searchReceiverDocPositionToRecDoc();
        String recDocInvoiced = jsonData.getData("ReceiverDoc");
        sendKeysAndEnter(VendorInvoiceReconciliationPage.positionToRecDoc, recDocInvoiced, "Entering data in [Position to Rec Doc] field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        String recDoc = getText(VendorInvoiceReconciliationPage.recDoc).trim();
        if (!recDocInvoiced.equalsIgnoreCase(recDoc.trim()))
            report.updateTestLog("Verify Rec Doc number", "Verify Rec Doc number", Status.PASS);
        else
            report.updateTestLog("Verify Rec Doc number", "Verify Rec Doc number", Status.FAIL);

        Utility_Functions.actionKey(Keys.F6, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.recDoc, recDocInvoiced, "Validating Rec Doc Number after pressing [F6]");
    }

    /**
     * Keyword to navigate to [Vendor Note Revisions] page
     */
    public void navigateToVendorNoteRevisions() {
        Utility_Functions.actionKey(Keys.F12, ownDriver);
        commonObj.validateText(VendorNotesPage.hdrVendorNotes, "Vendor Note Revisions", "Validating [Vendor Note Revisions] page title");

        String action = getAttribute(VendorNotesPage.actionVendorNotes, "value").trim();
        Utility_Functions.xAssertEquals(report, "I", action, "Validating Action field value in [Vendor Note Revisions] page");
    }

    /**
     * Keyword to validate warnings in [Vendor Note Revisions] page
     */
    public void vrfyVendorNotesWarningWhenNoSelection() {
        sendKeys(VendorNotesPage.actionVendorNotes, "A", "Entering [A] in Action field");
        sendKeysAndEnter(VendorNotesPage.Line1VendorNotes, "Test Notes", "Entering data in vendor notes line 1");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(VendorNotesPage.erroMsgVendorNotes, "V000000 - Invalid Vendor Number.  Vendor must exist in file.", "Validating warning msg in [Vendor Note Revisions] page");
    }

    /**
     * Keyword to navigate back to [Vendor Invoice Reconciliation] page
     */
    public void backToVIRPage() {
        Utility_Functions.actionKey(Keys.F3, ownDriver);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.purchaseOrderToVendorInvoiceReconciliation();
    }

    /**
     * Keyword to add notes in [Vendor Note Revisions] page
     */
    public void addVendorNotes() {
        String vendorNote = "Test Automation Notes "+Utility_Functions.xRandomFunction();
        sendKeys(VendorNotesPage.actionVendorNotes, "A", "Entering [A] in Action field");
        sendKeysAndEnter(VendorNotesPage.Line1VendorNotes, vendorNote, "Entering data in vendor notes line 1");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.verifyElementContainsText(VendorNotesPage.erroMsgVendorNotes, "- Vendor Notes Changed.", "Validating success msg in [Vendor Note Revisions] page");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
    }

    /**
     * Keyword to navigate to [Order Details] page
     */
    public void navigateToOrderDetailsFromVIR() {
        String orderNo = getText(VendorInvoiceReconciliationPage.orderNo).trim();
        String invoiceTotal = getAttribute(VendorInvoiceReconciliationPage.invoiceTotalTextBox, "value").trim();

        Utility_Functions.actionKey(Keys.F11, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.tbxOrderNumber, orderNo, "Validating order number");
        commonObj.validateText(VendorInvoiceReconciliationPage.tbxInvoiceTotal, invoiceTotal, "Validating Invoice Total");
    }

    /**
     * Keyword to verify Actions btn in [Order Details] page
     */
    public void vrfyActionsBtnInOrderDetailsPage() {
        Utility_Functions.actionKey(Keys.F1, ownDriver);
        commonObj.verifyElementContainsText(VendorInvoiceReconciliationPage.divHeader, "Vendor Part Number", "Clicking on [F1=Vendor Part #] > Validating [Vendor Part Number] header");
        Utility_Functions.actionKey(Keys.F1, ownDriver);
        commonObj.verifyElementContainsText(VendorInvoiceReconciliationPage.divHeader, "Stock Number", "Clicking on [F1=Stock #] > Validating [Stock Number] header");
        Utility_Functions.actionKey(Keys.F1, ownDriver);
        commonObj.verifyElementContainsText(VendorInvoiceReconciliationPage.divHeader, "Description", "Clicking on [F1=Description] > Validating [Description] header");
        Utility_Functions.actionKey(Keys.F2, ownDriver);
        commonObj.verifyElementContainsText(VendorInvoiceReconciliationPage.divHeader, "Extd. Cost", "Clicking on [F2=Extend Cost] > Validating [Extd. Cost] header");
        Utility_Functions.actionKey(Keys.F2, ownDriver);
        commonObj.verifyElementContainsText(VendorInvoiceReconciliationPage.divHeader, "Unit Cost", "Clicking on [F2=Unit Cost] > Validating [Unit Cost] header");

        /************************************************** Remove below steps after confirming **************************************************/
        Utility_Functions.actionKey(Keys.F11, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.vendorInvoiceRecHeader, "Vendor Invoice Reconciliation", "Validating [Vendor Invoice Reconciliation] page title");
    }

    /**
     * Keyword to verify Actions btn in [Notes Management - Inquiry] page
     */
    public void vrfyActionsBtnInNotesManagementPage() {
        //click(VendorInvoiceReconciliationPage.tbxQtyRcvd);
        Utility_Functions.actionKey(Keys.F1, ownDriver);
        Utility_Functions.xMoveToElment(getElement(VendorInvoiceReconciliationPage.tbxQtyRcvd), ownDriver);
        Utility_Functions.timeWait(10);
        Utility_Functions.xClickHiddenElement(ownDriver, VendorInvoiceReconciliationPage.btnF18);
        //click(VendorInvoiceReconciliationPage.btnF18, "Clicked on [F18=Notes] button");
        commonObj.validateText(VendorInvoiceReconciliationPage.hdrNotesManagementPage, "Notes Management - Inquiry", "Validating [Notes Management - Inquiry] page header");
        Utility_Functions.actionKey(Keys.F11, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.hdrLastUserColumn, "Last User", "Clicking on [F11=Show User/Date] > Validating [Last User] column header");
        commonObj.validateText(VendorInvoiceReconciliationPage.hdrChgDateColumn, "Chg Date", "Clicking on [F11=Show User/Date] > Validating [Chg Date] column header");
        Utility_Functions.actionKey(Keys.F11, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.hdrNoteColumn, "Note", "Clicking on [F11=Show Note Data] > Validating [Note] column header");
        Utility_Functions.actionKey(Keys.F12, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.lblOrderNumber, "Order Number :", "Clicking on [F12=Cancel] > Validating [Order Details page]");
        Utility_Functions.actionKey(Keys.F11, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.vendorInvoiceRecHeader, "Vendor Invoice Reconciliation", "Validating [Vendor Invoice Reconciliation] page title");
    }

    /**
     * Keyword to validate VIR details like - ReceivedDate, FreightCharges, etc
     */
    public void vrfyVIRDetails() {
        String rcvdDate = jsonData.getData("ReceivedDate");
        if (rcvdDate.charAt(0)=='0'){
            rcvdDate  = rcvdDate.substring(1);
        }
        String fCode = jsonData.getData("FCode");

        commonObj.validateText(VendorInvoiceReconciliationPage.receivedDate, rcvdDate, "Validating Received Date");
        String actualFreightCode = getAttribute(VendorInvoiceReconciliationPage.freightCharges, "value").trim();
        Utility_Functions.xAssertEquals(report, fCode, actualFreightCode, "Validating Freight Charges code");
        String actualFreightAmount = getAttribute(VendorInvoiceReconciliationPage.freightAmountTextBox, "value").trim();
        Utility_Functions.xAssertEquals(report, "10.00", actualFreightAmount, "Validating Freight Amount");
        String allocMethod = getAttribute(VendorInvoiceReconciliationPage.allocMethodTextBox, "value").trim();
        Utility_Functions.xAssertEquals(report, "M", allocMethod, "Validating  Alloc. Method");
    }

    /**
     * Keyword to enter Debit Memo and Discount Amount data in VIR page
     */
    public void vrfyDebitMemoAndAmount() {
        vrfyDebitMemo();
        String amount = jsonData.getData("DiscAmount");
        double am = Double.parseDouble(amount);
        sendKeysAndEnter(VendorInvoiceReconciliationPage.amountTextBox, amount, "Entering data in [-or- Amount] field");

        String invoiceTotal = getAttribute(VendorInvoiceReconciliationPage.invoiceTotalTextBox, "value");
        if (invoiceTotal.contains(",")){
            invoiceTotal = invoiceTotal.replaceAll(",", "");
        }
        double it = Double.parseDouble(invoiceTotal);
        String totalAmountExpected = Double.toString(it-am);

        String totalNetDue = getText(VendorInvoiceReconciliationPage.totalNetDue);
        if (totalNetDue.contains(",")){
            totalNetDue = totalNetDue.replaceAll(",", "");
        }
        double tnd = Double.parseDouble(totalNetDue);
        String totalNetDueActual = Double.toString(tnd);
        Utility_Functions.xAssertEquals(report, totalAmountExpected, totalNetDueActual, "Validating [Total Net Due] field");
    }

    /**
     * Keyword to enter Debit Memo and Discount Percentage data in VIR page
     */
    public void vrfyDebitMemoAndDiscount() {
        vrfyDebitMemo();
        sendKeysAndEnter(VendorInvoiceReconciliationPage.debitMemoTextBox, "0", "Resetting [Debit Memo] field");
        sendKeysAndEnter(VendorInvoiceReconciliationPage.amountTextBox, "0", "Resetting data in [Debit Memo] field");
        sendKeysAndEnter(VendorInvoiceReconciliationPage.freightAmountTextBox, "0", "Resetting [Freight Amount] field");
        sendKeysAndEnter(VendorInvoiceReconciliationPage.discountPercentTextBox, "0", "Resetting [Disc. Percent] field");

        String percentage = jsonData.getData("DiscPercentage");
        double dp = Double.parseDouble(percentage);
        sendKeysAndEnter(VendorInvoiceReconciliationPage.discountPercentTextBox, percentage, "Entering data in [Disc. Percent] field");

        String invoiceTotal = getAttribute(VendorInvoiceReconciliationPage.invoiceTotalTextBox, "value");
        if (invoiceTotal.contains(",")){
            invoiceTotal = invoiceTotal.replaceAll(",", "");
        }
        double it = Double.parseDouble(invoiceTotal);
        double discountValue = (it/100)*dp;
        String totalNetDueExpected = Double.toString(it-discountValue);

        String totalNetDue = getText(VendorInvoiceReconciliationPage.totalNetDue);
        if (totalNetDue.contains(",")){
            totalNetDue = totalNetDue.replaceAll(",", "");
        }
        double tnd = Double.parseDouble(totalNetDue);
        String totalNetDueActual = Double.toString(tnd);
        Utility_Functions.xAssertEquals(report, totalNetDueExpected, totalNetDueActual, "Validating [Total Net Due] field");

        String debitMemo = jsonData.getData("DebitMemo");
        sendKeysAndEnter(VendorInvoiceReconciliationPage.debitMemoTextBox, debitMemo, "Entering data in [Debit Memo] field");
        sendKeysAndEnter(VendorInvoiceReconciliationPage.freightAmountTextBox, "10", "Resetting [Freight Amount] field");
        sendKeysAndEnter(VendorInvoiceReconciliationPage.discountPercentTextBox, percentage, "Entering data in [Disc. Percent] field");
    }

    /**
     * Keyword to validate Debit Memo in VIR page
     */
    public void vrfyDebitMemo() {
        String debitMemo = jsonData.getData("DebitMemo");
        sendKeysAndEnter(VendorInvoiceReconciliationPage.debitMemoTextBox, "0", "Resetting [Debit Memo] field");
        sendKeysAndEnter(VendorInvoiceReconciliationPage.amountTextBox, "0", "Resetting data in [Debit Memo] field");

        sendKeysAndEnter(VendorInvoiceReconciliationPage.debitMemoTextBox, debitMemo, "Entering data in [Debit Memo] field");
        commonObj.validateText(VendorInvoiceReconciliationPage.gmMarginAmt, debitMemo, "Validating [GM Mgr Amount] is set as Debit Memo");
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.warningMessage, "Discount percentage and amount are ZERO.  Press F9 to accept invoice.", "validating warning message");
    }

        /**
         * Keyword to verify details in Debit Memo Process page
         */
    public void vrfyDebitMemoProcess() {
        String order = jsonData.getData("OrderNo");
        String receiverDoc = jsonData.getData("ReceiverDoc");
        commonObj.verifyElementContainsText(VendorInvoiceReconciliationPage.tbxOrderNumber, order, "Validating [Order Number] field");
        commonObj.validateText(VendorInvoiceReconciliationPage.tbxInvoiceTotal, receiverDoc, "Validating [Receiver Doc] field");

        Utility_Functions.actionKey(Keys.F9, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.hdrDebitMemoSummaryPage, "Debit Memo Summary", "Validating [Debit Memo Summary] page title");
        commonObj.verifyElementContainsText(VendorInvoiceReconciliationPage.tbxInvoiceTotal, order, "Validating [Order Number] field");
        commonObj.validateText(VendorInvoiceReconciliationPage.tbxReceiverDocDebitMemoSummary, receiverDoc, "Validating [Receiver Doc] field");
    }

    /**
     * Keyword to verify details in Debit Memo Process page
     */
    public void vrfyDebitMemoProcessDetails() {
        String order = jsonData.getData("OrderNo");
        String receiverDoc = jsonData.getData("ReceiverDoc");
        commonObj.verifyElementContainsText(VendorInvoiceReconciliationPage.tbxOrderNumber, order, "Validating [Order Number] field");
        commonObj.validateText(VendorInvoiceReconciliationPage.tbxInvoiceTotal, receiverDoc, "Validating [Receiver Doc] field");
    }

    /**
     * Keyword to verify the functionality of [Note] in Debit Memo Summary page
     */
    public void vrfyNotesDebitMemoSummaryPage(){
        click(VendorInvoiceReconciliationPage.lnkNote, "Click [Note] link");
        commonObj.validateText(VendorInvoiceReconciliationPage.hdrVIRComments, "VIR Comments for A/P Portal In", "Validating [VIR Comments for A/P Portal In] popup header");
        click(VendorInvoiceReconciliationPage.btnMore, "Click [More] button");
        Utility_Functions.actionKey(Keys.F12, ownDriver);
        Utility_Functions.xWaitForElementDisappear(ownDriver, VendorInvoiceReconciliationPage.hdrVIRComments, globalWait);
        Utility_Functions.actionKey(Keys.F9, ownDriver);

        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(ownDriver, VendorInvoiceReconciliationPage.txtWarning)){
            Utility_Functions.actionKey(Keys.F9, ownDriver);
        }
        commonObj.validateText(VendorInvoiceReconciliationPage.vendorInvoiceRecHeader, "Vendor Invoice Reconciliation", "Validating [Vendor Invoice Reconciliation] page title");
    }

    /**
     * Keyword to get Order Number from VIR page
     */
    public void getOrderNo(){
        String order = getText(VendorInvoiceReconciliationPage.orderNo);
        jsonData.putData("OrderNo", order);
    }

    /**
     * Keyword to verify navigation from Debit Memo Process page to Debit Memo Process-Other Items page
     */
    public void vrfyNavDebitMemoProcessToOtherItems() {
        String order = jsonData.getData("OrderNo");
        String receiverDoc = jsonData.getData("ReceiverDoc");
        Utility_Functions.actionKey(Keys.F5, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.hdrDebitMemoProcessOtherItemsPage, "Debit Memo Process-Other Items", "Validating [Debit Memo Process-Other Items] page title");
        commonObj.verifyElementContainsText(VendorInvoiceReconciliationPage.tbxOrderNumber, order, "Validating [Order Number] field");
        commonObj.validateText(VendorInvoiceReconciliationPage.tbxInvoiceTotal, receiverDoc, "Validating [Receiver Doc] field");
    }

    /**
     * Keyword to verify navigation from Debit Memo Process-Other Items page to Special Notes page
     */
    public void vrfySpecialNotes() {
        vrfyNavDebitMemoProcessToSpclNotes();

        Utility_Functions.actionKey(Keys.F3, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.hdrDebitMemoProcessOtherItemsPage, "Debit Memo Process-Other Items", "Validating [Debit Memo Process-Other Items] page title");

        sendKeysAndEnter(VendorInvoiceReconciliationPage.tbxItemDescription, "Test Comments", "Entering data in [Item Description] field");
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.hdrDebitMemoProcessPage, "Debit Memo Process", "Validating [Debit Memo Process] page title");
    }

    /**
     * Keyword to verify navigation from Debit Memo Process page to Special Notes page
     */
    public void vrfyNavDebitMemoProcessToSpclNotes() {
        String order = jsonData.getData("OrderNo");
        String receiverDoc = jsonData.getData("ReceiverDoc");
        Utility_Functions.actionKey(Keys.F12, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.hdrDebitMemoSummaryPage, "Additional Notes", "Validating [Additional Notes] page title");
        commonObj.verifyElementContainsText(VendorInvoiceReconciliationPage.tbxInvoiceTotal, order, "Validating [Order Number] field");
        commonObj.validateText(VendorInvoiceReconciliationPage.tbxReceiverDocDebitMemoSummary, receiverDoc, "Validating [Receiver Doc] field");
    }

    /**
     * Keyword to add notes in Special Notes page and then verify
     */
    public void addSpclNotesAndVerify() {
        String spclNotes = "test special notes";
        sendKeysAndEnter(VendorInvoiceReconciliationPage.tbxAdditionalNotesLine, spclNotes, "Entering data in [Special Notes] Line1 field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.actionKey(Keys.F12, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.hdrDebitMemoSummaryPage, "Additional Notes", "Validating [Additional Notes] page title");

        String spclNotesActual = getAttribute(VendorInvoiceReconciliationPage.tbxAdditionalNotesLine, "value").trim().toLowerCase(Locale.ROOT);
        Utility_Functions.xAssertEquals(report, spclNotes, spclNotesActual, "Validating [Special Notes] field value as added previously");

        Utility_Functions.actionKey(Keys.F3, ownDriver);
        commonObj.validateText(VendorInvoiceReconciliationPage.vendorInvoiceRecHeader, "Vendor Invoice Reconciliation", "Validating [Vendor Invoice Reconciliation] page title");
    }



}
