package pages.inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class MiscAdjustmentPage extends ReusableLib {

    public MiscAdjustmentPage(Helper helper) {
        super(helper);
    }

    public static By miscAdjMenuBtn = By.xpath("//div[text()='9']/following-sibling::div/a[contains(text(),'Miscellaneous')]");
    public static By pageHeader = By.id("outC1HDR");
    public static By ddnAjdCode = By.id("ddbIATYPE");
    public static By tbxVendorCustomer = By.id("txtIAVEND");
    public static By tbxItem1 = By.id("txtITEM.1");
    public static By tbxQty1 = By.id("outIIQTYD.1");
    public static By tbxExplanation1 = By.id("txtIEXPL.1");
    public static By txtAmount = By.id("Amt1");
    public static By btnProcess = By.xpath("//input[@type='button' and @value='Process']");
    public static By btnContinue = By.xpath("//input[@type='button' and @value='Continue']");

}
