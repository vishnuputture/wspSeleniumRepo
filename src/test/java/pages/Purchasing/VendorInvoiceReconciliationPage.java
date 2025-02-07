package pages.Purchasing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class VendorInvoiceReconciliationPage extends ReusableLib {

    public VendorInvoiceReconciliationPage(Helper helper) {
        super(helper);
    }

    public static By vendorInvoiceRecHeader = By.id("D_1_20");
    public static By PoMainMenuHeader = By.id("D_2_27");
    public static By receiverDoc = By.id("I_4_17");
    public static By selectVendor = By.id("I_12_2");
    public static By operatorTextBox = By.id("I_2_17");
    public static By dueDateTextBox = By.id("I_5_17");
    public static By receivedDate = By.id("D_6_17");
    public static By vendorInvTextBox = By.id("I_7_17");
    public static By invoiceTotalTextBox = By.id("I_9_17");
    public static By discountPercentTextBox = By.id("I_10_17");
    public static By amountTextBox = By.id("I_11_17");
    public static By allocMethodTextBox = By.id("I_14_17");
    public static By custFrtAmountTextBox = By.id("I_15_17");
    public static By debitMemoTextBox = By.id("I_16_17");
    public static By transDateTextBox = By.id("I_17_17");
    public static By detailedValue = By.id("D_18_17");
    public static By paymentTerms = By.id("I_19_17");
    public static By netVal = By.id("D_19_21");
    public static By warMessage = By.id("D_21_2");
    public static By orderNo = By.id("D_3_51");
    public static By originalVendor = By.id("I_4_51");
    public static By vendor = By.id("D_5_51");
    public static By recDocNote = By.id("D_6_51");
    public static By freightAmountTextBox = By.id("I_13_17");
    public static By totalNetDue = By.id("D_12_17");
    public static By invoicingVdrTextBox = By.id("I_7_51");
    public static By receivedBy = By.id("D_9_51");
    public static By gmMarginAmt = By.id("D_11_51");
    public static By freightCharges = By.id("I_13_55");
    public static By allocationMethod = By.id("D_14_19");
    public static By qtyManual = By.id("D_15_32");
    public static By dollarCustPayFreight = By.id("D_16_32");
    public static By selMult = By.id("D_12_5");
    public static By byWeightNotAllowed = By.id("D_17_26");
    public static By boxHeader = By.xpath("//div[@class='box-header']");
    public static By f4Prompt = By.id("btnCF04");
    public static By newDoc = By.id("btnCF05");
    public static By moreKeys = By.id("btnCF24");
    public static By warningMessage = By.id("D_23_2");
    public static By browseHeader = By.id("D_2_28");
    public static By searchVendor = By.id("I_5_26");
    public static By getVendorName = By.id("D_12_12");
    public static By recDoc = By.id("D_12_39");
    public static By noRecords = By.id("D_13_20");
    public static By poCol = By.id("D_11_49");
    public static By rcvDateCol = By.id("D_11_56");
    public static By dueDateCol = By.id("D_11_65");
    public static By recInvVal = By.id("D_12_49");
    public static By venInvVal = By.id("D_12_56");
    public static By totalCostVal = By.id("D_12_65");
    public static By rvcDatePrompt = By.xpath("//div[@id='D_4_2']/a");
    public static By moreLink = By.id("btnPageDown");
    public static By recDocFifth = By.id("D_16_39");
    public static By selectVendorFifth = By.id("I_16_2");
    public static By btnF1 = By.id("btnCF01");
    public static By btnF2 = By.id("btnCF01");
    public static By btnF9 = By.id("btnCF01");
    public static By btnF11 = By.id("btnCF01");
    public static By btnF18 = By.id("btnCF01");

    /************************************************** Receiver Document Browse page elements **************************************************/

    public static By btnDown = By.id("down_button");
    public static By positionToRecDoc = By.id("I_4_26");

    /************************************* Vendor Invoice Reconciliation - Gross Margin Manager page elements *************************************/

    public static By hdrGMMPage = By.id("D_1_17");
    public static By gmManagerAmount = By.id("D_4_51");
    public static By recordThisGMManager = By.id("I_9_51");
    public static By warningMessageGMMgr = By.id("D_23_1");
    public static By explanation = By.id("I_11_51");

    /************************************************ Order Details and Line Items page elements ************************************************/

    public static By lblOrderNumber = By.id("D_3_1");
    public static By tbxOrderNumber = By.id("D_3_17");
    public static By tbxInvoiceTotal = By.id("D_4_17");
    public static By divHeader = By.id("D_9_1");
    public static By tbxQtyRcvd = By.id("I_10_42");
    public static By hdrNotesManagementPage = By.id("D_1_18_W1");
    public static By hdrLastUserColumn = By.id("D_5_52_W1");
    public static By hdrChgDateColumn = By.id("D_5_63_W1");
    public static By hdrNoteColumn = By.id("D_5_1_W1");

    /************************************************** Debit Memo Process page elements **************************************************/

    public static By hdrDebitMemoProcessPage = By.id("D_1_23");
    public static By hdrDebitMemoSummaryPage = By.id("D_1_24");
    public static By tbxReceiverDocDebitMemoSummary = By.id("D_5_17");
    public static By lnkNote = By.xpath("//div[@id='D_6_33']/a");
    public static By hdrVIRComments = By.id("D_2_18_W1");
    public static By btnMore = By.id("btnPageDown");
    public static By txtWarning = By.id("D_2_35");

    /************************************************** Debit Memo Process-Other Items page elements **************************************************/

    public static By hdrDebitMemoProcessOtherItemsPage = By.id("D_1_14");
    public static By tbxItemDescription = By.id("I_8_9");
    public static By tbxAdditionalNotesLine = By.id("I_7_3");
}