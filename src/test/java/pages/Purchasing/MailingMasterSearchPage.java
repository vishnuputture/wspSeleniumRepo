package pages.Purchasing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class MailingMasterSearchPage extends ReusableLib {

    public MailingMasterSearchPage(Helper helper) {
        super(helper);
    }

    public static By firstaddressLine1 = By.xpath("//div[@id='D_8_5']/../div[@subfileline=8][1]");
    public static By firstaddressLine2 = By.xpath("//div[@id='D_8_5']/../div[@subfileline=8][2]");
    public static By firstCustomerSelect = By.id("I_8_2");
    public static By vendorNumber = By.id("D_8_75");

    public static By action = By.id("I_3_19");
    public static By accountPrefix = By.id("I_3_55");
    public static By accountNumber = By.id("D_4_1");

    public static By cancel = By.id("btnCancel");

}
