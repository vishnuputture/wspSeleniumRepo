package businesskeywords.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.junit.validator.ValidateWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.PurchaseOrders.MailingMasterSearchPage;
import pages.PurchaseOrders.PurchaseOrderEntryPage;
import pages.PurchaseOrders.PurchaseOrderInquiryPage;
import pages.PurchaseOrders.VendorInvoiceReconciliationPage;
import pages.inventory.OptionsConstantsPage;
import pages.pricing.pricingmatrix.PricingMatrixPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import software.amazon.awssdk.services.glacier.model.PurchaseProvisionedCapacityRequest;
import supportLibraries.Utility_Functions;

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
        if (!isDisplayed(VendorInvoiceReconciliationPage.noRecords)) {
            Utility_Functions.xAssertEquals(report, vendorNumber, getText(VendorInvoiceReconciliationPage.selMult), "The records get sorted based on vendor number");
            clearText(VendorInvoiceReconciliationPage.searchVendor);
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        }
        getDetailsPoRecDate();
        getDetailsVdrInvTotal();
    }

    public void selectVendor(){
        sendKeys(VendorInvoiceReconciliationPage.selectVendor, "1" + Keys.ENTER, "Select Customer No");
    }

    public void getDetailsPoRecDate() {
        if (getText(VendorInvoiceReconciliationPage.poCol).equals("P.O.")) {
            click(PricingMatrixPage.copyRow, "Click [F2=Vend Inv/Total Cost]");
        }
        Utility_Functions.xUpdateJson("Vendor", getText(VendorInvoiceReconciliationPage.selMult).trim());
        Utility_Functions.xUpdateJson("VendorName", getText(VendorInvoiceReconciliationPage.getVendorName).trim());
        Utility_Functions.xUpdateJson("RecDoc", getText(VendorInvoiceReconciliationPage.recDoc).trim());
        Utility_Functions.xUpdateJson("VendorInvoice", getText(VendorInvoiceReconciliationPage.recInvVal).trim());
        Utility_Functions.xUpdateJson("TotalCost", getText(VendorInvoiceReconciliationPage.totalCostVal).trim());
    }

    public void getDetailsVdrInvTotal() {
        if (!getText(VendorInvoiceReconciliationPage.poCol).equals("P.O.")) {
            click(PricingMatrixPage.copyRow, "Click [F2=PO/Rec Date/Due Date]");
        }
        Utility_Functions.xUpdateJson("P.O.", getText(VendorInvoiceReconciliationPage.recInvVal).trim());
        Utility_Functions.xUpdateJson("Rcv Date", getText(VendorInvoiceReconciliationPage.venInvVal).trim());
        Utility_Functions.xUpdateJson("DueDate", getText(VendorInvoiceReconciliationPage.totalCostVal).trim());
        if(!isDisplayed(PricingMatrixPage.selectRowTxtBox)){
            click(PricingMatrixPage.copyRow);
        }
    }

    public String getDetails(String data){
        return Utility_Functions.xGetJsonData(data);
    }

    public void verifyVendorInvDetails() {
        Utility_Functions.xAssertEquals(report,getDetails("Vendor"),getAttribute(VendorInvoiceReconciliationPage.originalVendor,"value"),"Original Vendor matches");
        commonObj.validateText(VendorInvoiceReconciliationPage.vendor, getDetails("VendorName"), "Vendor Name Matches: "+getDetails("VendorName"));
        Utility_Functions.xAssertEquals(report,getDetails("RecDoc"),getAttribute(VendorInvoiceReconciliationPage.receiverDoc,"value"),"Receiver Doc matches");
        commonObj.validateText(VendorInvoiceReconciliationPage.totalNetDue, getDetails("TotalCost"), "Total Net Due Matches: "+getDetails("TotalCost"));
        commonObj.validateText(VendorInvoiceReconciliationPage.detailedValue, getDetails("TotalCost"), "Detail Total Matches: "+getDetails("TotalCost"));
        commonObj.validateText(VendorInvoiceReconciliationPage.orderNo, getDetails("P.O."), "Order No Matches: "+getDetails("P.O."));
        commonObj.validateText(VendorInvoiceReconciliationPage.receivedDate, getDetails("Rcv Date"), "Received Date Matches: "+getDetails("Rcv Date"));
        Utility_Functions.xAssertEquals(report,getDetails("DueDate"),getAttribute(VendorInvoiceReconciliationPage.dueDateTextBox,"value").trim(),"Due date matches");
        Utility_Functions.xAssertEquals(report,getDetails("TotalCost"),getAttribute(VendorInvoiceReconciliationPage.invoiceTotalTextBox,"value").trim(),"Invoice Total matches");
        Utility_Functions.xAssertEquals(report,getDetails("VendorInvoice"),getAttribute(VendorInvoiceReconciliationPage.vendorInvTextBox,"value").trim(),"Vendor Inv# matches");
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

    public void clickReturn() {
        click(PurchaseOrderInquiryPage.btnF12Return, "Click [F12=Return]");
    }

    public String changeToPositionToVendorName() {
        click(CustomerGroupMaintenancePage.sort, "Click [F10=Vendor Name]");
        commonObj.validateText(SpecialPriceAllowancePage.textSearchHeader, "Position to Vendor Name", "Changed to [Position to Vendor Name]");
        return getText(VendorInvoiceReconciliationPage.getVendorName);
    }

    public String positionToRecDoc() {
        changeToPositionToVendorName();
        click(CustomerGroupMaintenancePage.sort, "Click [F10=Sort by Rec Doc]");
        commonObj.validateText(SpecialPriceAllowancePage.textSearchHeader, "Position to Rec Doc . .", "Changed to [Position to Rec Doc . .]");
        return getText(VendorInvoiceReconciliationPage.recDoc);
    }

    public void positionToVdrInvoice() {
        changeToPositionToVendorName();
        click(CustomerGroupMaintenancePage.sort, "Click [F10=Sort by Rec Doc]");
        commonObj.validateText(SpecialPriceAllowancePage.textSearchHeader, "Position to Rec Doc . .", "Changed to [Position to Rec Doc . .]");
        click(CustomerGroupMaintenancePage.sort, "Click [F10=Vend Inv.]");
        commonObj.validateText(SpecialPriceAllowancePage.textSearchHeader, "Position to Vend Inv  .", "Changed to [Position to Vend Inv  .]");
    }

    public void positionToTotalCost() {
        positionToVdrInvoice();
        click(CustomerGroupMaintenancePage.sort, "Click [F10=Total Cost]");
        commonObj.validateText(SpecialPriceAllowancePage.textSearchHeader, "Position to Total Cost.", "Changed to [Position to Total Cost]");
    }

    public By divTag(String text) {
        return By.xpath("//div[text()='" + text + "']");
    }

    public void changedPoRecDateDueDate() {
        navigateToReceiverDocBrowser();
        click(PricingMatrixPage.copyRow, "Click [F2=PO/Rec Date/Due Date]");
        commonObj.validateText(VendorInvoiceReconciliationPage.poCol, "P.O.", "Column Changed to [P.O.]");
        commonObj.validateText(VendorInvoiceReconciliationPage.rcvDateCol, "Rcv Date", "Column Changed to [Rcv Date]");
        commonObj.validateText(VendorInvoiceReconciliationPage.dueDateCol, "Due Date", "Column Changed to [Due Date]");
    }

    public void positionToPO() {
        positionToRecDoc();
        click(CustomerGroupMaintenancePage.sort, "Click [F10=P.O.]");
        commonObj.validateText(SpecialPriceAllowancePage.textSearchHeader, "Position to P.O.  . . .", "Changed to [Position to P.O.  . . .]");
    }

    public void positionToRcvDate() {
        positionToPO();
        click(CustomerGroupMaintenancePage.sort, "Click [F10=RCV Date]");
        commonObj.validateText(VendorInvoiceReconciliationPage.rvcDatePrompt, "Position to Rcv Date  »", "Changed to [Position to Rcv Date  »]");
    }

    public void changePositionToRcvDate() {
        positionToPO();
    }

    /**
     * This method verify Search Receiver Doc Vendor Name
     */
    public void verifySearchRecDocVdrName() {
        navigateToReceiverDocBrowser();
        String vendorName = changeToPositionToVendorName();
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox, vendorName, "Enter Vendor Name");
        commonObj.validateText(VendorInvoiceReconciliationPage.getVendorName, vendorName, "Vendor Name Sorted " + vendorName);
        click(VendorInvoiceReconciliationPage.searchVendor, "Click [Search Vendor ] Text Box");
        validateVendor();
    }

    /**
     * This method verify Search Position To Rec Doc
     */
    public void verifySearchRecDoc() {
        navigateToReceiverDocBrowser();
        String recDoc = positionToRecDoc();
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox, recDoc, "Enter Vendor Name");
        commonObj.validateText(VendorInvoiceReconciliationPage.recDoc, recDoc, "Vendor Name Sorted " + recDoc);
        click(VendorInvoiceReconciliationPage.searchVendor, "Click [Search Vendor ] Text Box");
        validateVendor();
    }

    /**
     * This method verify Search Position To Vendor Invoice
     */
    public void verifySearchVendorInvoice() {
        navigateToReceiverDocBrowser();
        positionToVdrInvoice();
        click(VendorInvoiceReconciliationPage.searchVendor, "Click [Search Vendor ] Text Box");
        validateVendor();
    }

    /**
     * This method verify Search Position To Total Cost
     */
    public void verifySearchTotalCost() {
        navigateToReceiverDocBrowser();
        positionToTotalCost();
        click(VendorInvoiceReconciliationPage.searchVendor, "Click [Search Vendor ] Text Box");
        validateVendor();
    }

    public void validateSortedField(){
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox, jsonData.getData("TenNumeric"), "Enter Vendor Name as " + jsonData.getData("TenNumeric"));
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox, jsonData.getData("AlphaNumeric"), "Enter Vendor Name as " + jsonData.getData("AlphaNumeric"));
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox, jsonData.getData("SpecialCharacter"), "Enter Vendor Name as " + jsonData.getData("SpecialCharacter"));
        sendKeysAndEnter(PricingMatrixPage.selectRowTxtBox, jsonData.getData("zero"), "Enter Vendor Name as " + jsonData.getData("zero"));
    }

    /**
     * This method verify Position To PO
     */
    public void verifyPositionToPO() {
        changedPoRecDateDueDate();
        positionToPO();
        click(VendorInvoiceReconciliationPage.searchVendor, "Click [Search Vendor ] Text Box");
        validateVendor();
    }

    /**
     * This method verify Position To RCV Date
     */
    public void verifyPositionToRcvDate() {
        changedPoRecDateDueDate();
        positionToRcvDate();
        click(VendorInvoiceReconciliationPage.searchVendor, "Click [Search Vendor ] Text Box");
        validateVendor();
    }
}
