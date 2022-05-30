package businesskeywords.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import pages.PurchaseOrders.PurchaseOrderEntryPage;
import pages.PurchaseOrders.PurchaseOrderInquiryPage;
import pages.PurchaseOrders.VendorInvoiceReconciliationPage;
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
        ownDriver=helper.getGSDriver();
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

    public By textElement(String text){
        return By.xpath("//div[contains(text(),'"+text+"')))]]");
    }

    public void validateReceivedDocFields(){
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.operatorTextBox, "[Operator] is present: "+getAttribute(VendorInvoiceReconciliationPage.operatorTextBox,"value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.receiverDoc, "[Received Doc.] is present: "+getAttribute(VendorInvoiceReconciliationPage.receiverDoc,"value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.dueDateTextBox, "[Due Date] is present: "+getAttribute(VendorInvoiceReconciliationPage.dueDateTextBox,"value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.receivedDate, "[Received Date] is present: "+getText(VendorInvoiceReconciliationPage.receivedDate));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.vendorInvTextBox, "[Vendor Inv #]  is present: "+getAttribute(VendorInvoiceReconciliationPage.operatorTextBox,"value"));
    }

    public void validateCalculationFields(){
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.invoiceTotalTextBox, "[Invoice Total] is present: "+getAttribute(VendorInvoiceReconciliationPage.invoiceTotalTextBox,"value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.discountPercentTextBox, "[Disc.] Percent is present: "+getAttribute(VendorInvoiceReconciliationPage.discountPercentTextBox,"value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.amountTextBox, "[-or- Amount] is present: "+getAttribute(VendorInvoiceReconciliationPage.amountTextBox,"value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.totalNetDue, "[Total Net Due] is present: "+getText(VendorInvoiceReconciliationPage.totalNetDue));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.freightAmountTextBox, "[Freight Amt] is present: "+getAttribute(VendorInvoiceReconciliationPage.freightAmountTextBox,"value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.allocMethodTextBox, "[Alloc. Method[ is present: "+getAttribute(VendorInvoiceReconciliationPage.allocMethodTextBox,"value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.custFrtAmountTextBox, "[Cust Frt Amt] is present: "+getAttribute(VendorInvoiceReconciliationPage.custFrtAmountTextBox,"value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.debitMemoTextBox, "[Debit Memo] is present: "+getAttribute(VendorInvoiceReconciliationPage.debitMemoTextBox,"value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.transDateTextBox, "[Trans. Date] is present: "+getText(VendorInvoiceReconciliationPage.transDateTextBox));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.detailedValue, "[Detail Total] is present: "+getText(VendorInvoiceReconciliationPage.detailedValue));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.paymentTerms, "[Payment Terms] is present: "+getText(VendorInvoiceReconciliationPage.paymentTerms) + getText(VendorInvoiceReconciliationPage.netVal));
    }

    public void validateVendorFields(){
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.orderNo, "[Order Number] is present: "+getText(VendorInvoiceReconciliationPage.orderNo));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.originalVendor, "[Original Vndr] is present: "+getAttribute(VendorInvoiceReconciliationPage.originalVendor,"value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.vendor, "[Vendor] is present: "+getText(VendorInvoiceReconciliationPage.vendor));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.recDocNote, "[Rec Doc Notes] is present");
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.invoicingVdrTextBox, "[Invoicing Vdr] is present: "+getAttribute(VendorInvoiceReconciliationPage.invoicingVdrTextBox,"value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.gmMarginAmt, "[GM Mgr Amount] is present");
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.receivedBy, "[Received By] is present: "+getText(VendorInvoiceReconciliationPage.receivedBy));
    }

    public void validateAllocationMethFields(){
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.freightCharges, "[Freight Charges] is present");
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.allocationMethod, "[Allocation Methods:] is present: "+getText(VendorInvoiceReconciliationPage.allocationMethod));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.qtyManual, "[Q = by quantity        M = manual] is present: "+getText(VendorInvoiceReconciliationPage.qtyManual));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.dollarCustPayFreight, "[D = by dollars         C = cust pays freight] is present: "+getText(VendorInvoiceReconciliationPage.dollarCustPayFreight));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.byWeightNotAllowed, "[W = by weight          Blank = not allocated] is present: "+getText(VendorInvoiceReconciliationPage.byWeightNotAllowed));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.warMessage, "[Due Date, Disc Percent and Disc Amount changes must also be made on AP Portal.] is present: "+getText(VendorInvoiceReconciliationPage.warMessage));
    }

    public void validateLinks(){
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.f4Prompt, "[F4=Prompt] is present: "+getAttribute(VendorInvoiceReconciliationPage.f4Prompt,"value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.newDoc, "[F5=New Doc.] is present: "+getAttribute(VendorInvoiceReconciliationPage.newDoc,"value"));
        commonObj.validateElementExists(VendorInvoiceReconciliationPage.moreKeys, "[f24=MoreKeys] is present: "+getAttribute(VendorInvoiceReconciliationPage.moreKeys,"value"));
        commonObj.validateElementExists(PurchaseOrderInquiryPage.btnSubmit, "[ENTER] is present: "+getAttribute(PurchaseOrderInquiryPage.btnSubmit,"value"));
        commonObj.validateElementExists(PurchaseOrderInquiryPage.btnExitItemLedger, "[f3=Exit] is present: "+getAttribute(PurchaseOrderInquiryPage.btnExitItemLedger,"value"));
    }

    /**
     * This method Availability fields on Vendor invoices Reconciliation page
     */
    public void vendorInvoiceRecUI() {
        click(VendorInvoiceReconciliationPage.receiverDoc,"Click [Receiver Doc] text field");
        click(PurchaseOrderEntryPage.custBrowse,"Click [F1=Browse Rcv Doc]");
        sendKeysAndEnter(VendorInvoiceReconciliationPage.selectVendor,"1","Select Vendor");
        validateReceivedDocFields();
        validateCalculationFields();
        validateVendorFields();
        validateAllocationMethFields();
        validateLinks();
    }
}
