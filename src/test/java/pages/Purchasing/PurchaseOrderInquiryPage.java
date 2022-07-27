package pages.Purchasing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class PurchaseOrderInquiryPage extends ReusableLib {

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
    public static By lnkBuyersWorksheet = By.xpath("//a[contains(text(),'Buyers')]");
    public static By hdrBuyersWorksheet = By.id("D_2_17");
    public static By lnkPOEntry = By.xpath("//div[@id='D_6_7']/a[contains(text(),'Orders')]");
    public static By hdrPOEntry = By.id("D_1_16");
    public static By lnkPOPreferences = By.xpath("//a[contains(text(),'Preferences')]");
    public static By hdrPOPreferences = By.id("D_2_26");
    public static By btnF12Return = By.id("btnCF12");
    public static By lineColumn = By.id("outP4LNID.1");
    public static By unitPriceColumn = By.id("outP4STSDTL.1");
    public static By extPriceColumn = By.id("outP4RECNUMDT.1");
    public static By vendorNoPoDetails = By.id("outP4VENDNUM");

    /************************ PURCHASE ORDER INQUIRY - DETAILS page elements ************************/

    public static By inquiryHeader = By.id("constant4");
    public static By hdrPOInquiryDetails = By.id("outconstant4");
    public static By qty = By.id("outP4QTY.1");
    public static By btnItemLedger = By.id("Button3.1");
    public static By itemNumber = By.id("outP4ITMNO.1");
    public static By tbxItemNumber = By.id("inpItemNbr");
    public static By btnExitItemLedger = By.id("btnExit");
    public static By hdrItemLedger = By.id("hdrItemLedger");
    public static By btnExitPOInquiryDtls = By.id("btnCF03");
    public static By poInquiry = By.xpath("//div[text()='13']/following-sibling::div/a[contains(text(),'Purchase')]");
    public static By vendorTextField = By.id("P1PTVENDNB");
    public static By orderBySearchIcon = By.id("imgOrderedBy");
    public static By optField = By.xpath("//input[@class='A24 input']");
    public static By noResult1 = By.id("constant60");
    public static By noResult2 = By.id("constant61");
    public static By noResult3 = By.id("constant62");
    public static By vendorVerify = By.id("P1VENDNB.1");
    public static By msgNoDetailLinesToDisplay = By.id("constant45");
    public static By detailsOptionsColumn = By.id("ddbP4OPTION.1");
    public static By positionToLine = By.id("txtP4LINE");
    public static By exitCustInfo = By.xpath("//span[text()='F12-Back']");
    public static By itemNumberTextBox = By.id("txtP4ITEMNO");
    public static By showAllLink = By.xpath("//div[@id='lnkShowStatus']/a");
    public static By statusPoDetails = By.id("outP4Status");
    public static By stockType = By.id("outP4TYPDSC");
    public static By openAmount = By.id("outP4ORDTOT");
    public static By dateField = By.id("outP4ORDDTE");
    public static By orderNumberPoDetails = By.id("outP4ORDNUM");
    public static By errorTitle = By.xpath("//input[@title='Invalid line number entered.']");
    public static By orderNumber = By.id("D_4_18");
    public static By stockOnlyLinkDis = By.xpath("//div[@id='lnkStockOnly' and @disabled='true']");
    public static By customerTextBox = By.id("inpCustNo");
    public static By inpSrcCode = By.id("inpSrcCode");
    public static By calendarIcon = By.xpath("//div[contains(@class,'pui-calendar-icon')]");
    public static By yearPicker = By.xpath("//span[@arrow='1']");
    public static By firstDay = By.xpath("//td[text()='1']");
    public static By gridExist = By.id("OutLdDate.1");
}
