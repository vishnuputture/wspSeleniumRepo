package pages.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class PurchaseOrderInquiryPage extends ReusableLib{

    public PurchaseOrderInquiryPage(Helper helper) {
        super(helper);
    }

    public static By pageTitle = By.id("OP18_heading");
    public static By hdrPOInquiry = By.xpath("//div[(@class='win-h2') and contains(text(),'Purchase')]");
    public static By tbxPONumber = By.id("P1POSTOORD");
    public static By lstOrderNumber = By.xpath("//div[starts-with(@id,'P1ORDNUM')]");
    public static By lstActions = By.xpath("//div[starts-with(@id,'P1OPT')]/input");
    public static By btnSubmit = By.id("btnSubmit");
    public static By ddnStatus = By.id("ddbP1PTODRSTS");
    public static By ddnType = By.id("P1PTSHPTYP");
    public static By btnSwitchViewTable = By.xpath("//img[@id='imgShiftImage']");

    public static By tblHdrOrderTotal = By.xpath("//div[text()='Order Total']");
    public static By tblHdrJobName = By.xpath("//div[text()='Job Name']");
    public static By tblHdrMiscNotes = By.xpath("//div[text()='Misc Notes']");
    public static By tblHdrCustomerPO = By.xpath("//div[text()='Customer PO']");
    public static By tblHdrQuoteNumber = By.xpath("//div[text()='Quote Number']");
    public static By tblHdrReceiverDoc = By.xpath("//div[text()='Receiver Doc']");

    public static By lstShipmentColText = By.xpath("//div[starts-with(@id,'P1ORDTYP')]");
    public static By lstStatusColText = By.xpath("//div[starts-with(@id,'P1ORDSTS')]");

    public static By lnkBuyersInquiry = By.xpath("//a[text()='Buyers Inquiry']");
    public static By hdrBuyersInquiry = By.id("D_1_30");
    public static By lnkBuyersWorksheet = By.xpath("//a[text()='Buyers Worksheet']");
    public static By hdrBuyersWorksheet = By.id("D_2_17");
    public static By lnkPOEntry = By.xpath("//a[text()='PO Entry']");
    public static By hdrPOEntry = By.id("D_1_16");
    public static By lnkPOPreferences = By.xpath("//a[text()='PO Preferences']");
    public static By hdrPOPreferences = By.id("D_2_26");

    public static By btnF12Return = By.id("btnCF12");

    /************************ PURCHASE ORDER INQUIRY - DETAILS page elements ************************/

    public static By qty = By.id("outP4QTY.1");
    public static By btnItemLedger = By.id("Button3.1");
    public static By itemNumber = By.id("outP4ITMNO.1");
    public static By tbxItemNumber = By.id("inpItemNbr");
    public static By btnExitItemLedger = By.id("btnExit");
    public static By btnExitPOInquiryDtls = By.id("btnCF03");
    public static By msgNoDetailLinesToDisplay = By.id("constant45");


}
