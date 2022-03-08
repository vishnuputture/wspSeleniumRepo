package pages.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class PurchaseOrderInquiryPage extends ReusableLib{

    public PurchaseOrderInquiryPage(Helper helper) {
        super(helper);
    }

    public static By pageTitle = By.id("OP18_heading");
    public static By tbxPONumber = By.id("P1POSTOORD");
    public static By lstOrderNumber = By.xpath("//div[starts-with(@id,'P1ORDNUM')]");
    public static By lstActions = By.xpath("//div[starts-with(@id,'P1OPT')]/input");
    public static By btnSubmit = By.id("btnSubmit");
    public static By ddnStatus = By.id("ddbP1PTODRSTS");

    /************************ PURCHASE ORDER INQUIRY - DETAILS page elements ************************/

    public static By inquiryHeader=By.id("constant4");
    public static By qty = By.id("outP4QTY.1");
    public static By btnItemLedger = By.id("Button3.1");
    public static By itemNumber = By.id("outP4ITMNO.1");
    public static By tbxItemNumber = By.id("inpItemNbr");
    public static By btnExitItemLedger = By.id("btnExit");
    public static By btnExitPOInquiryDtls = By.id("btnCF03");
    public static By poInquiry=By.xpath("//div[text()='13']/following-sibling::div/a[contains(text(),'Purchase')]");
    public static By vendorTextField=By.id("P1PTVENDNB");
    public static By orderBySearchIcon=By.id("imgOrderedBy");
    public static By optField=By.xpath("//input[@class='A24 input']");
    public static By noResult1=By.id("constant60");
    public static By noResult2=By.id("constant61");
    public static By noResult3=By.id("constant62");
    public static By vendorVerify=By.id("P1VENDNB.1");
}
