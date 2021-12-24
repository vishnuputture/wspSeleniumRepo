package pages.inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class OptionsConstantsPage extends ReusableLib {
    public OptionsConstantsPage(Helper helper) {
        super(helper);
    }

    public static By mfVendorCode=By.xpath("//div[text()='14']/following-sibling::div");
    public static By mfVendorCodePageTitle=By.id("D_1_28");
    public static By actionTextBox=By.id("I_3_25");
    public static By descriptionLine=By.id("I_5_25");
    public static By vendorNumber=By.id("D_8_75");
    public static By vendorNo=By.id("I_7_25");
}
