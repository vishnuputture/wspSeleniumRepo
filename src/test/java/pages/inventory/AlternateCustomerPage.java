package pages.inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;
import software.amazon.awssdk.services.kendra.model.Search;

public class AlternateCustomerPage extends ReusableLib{

    public AlternateCustomerPage(Helper helper) {
        super(helper);
    }

    public static By pageTitleAlternateCustomerRevision = By.id("D_2_22");
    public static By revisionAlternateCustomerMenu = By.xpath("//div[text()='2']/following-sibling::div");
    public static By tbxCustomerNumber = By.id("I_4_27");
    public static By tbxAltCustomerNo1 = By.id("I_10_2");
    public static By notificationMsg = By.id("D_23_2");
    public static By lblCustomerName = By.id("D_4_35");

    /************************************ Mailing Master Search Page elements ************************************/

    public static By pageTitleMailingMasterSearch = By.id("D_2_26");
    public static By btnDown = By.id("down_button");
    public static By acct1 = By.id("D_8_75");
    public static By addLine1 = By.id("D_8_5");
    public static By opt1 = By.id("I_8_2");

}
