package pages.Purchasing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class VendorInformationPage extends ReusableLib {

    public VendorInformationPage(Helper helper) {
        super(helper);
    }

    public static By vendorData = By.id("D_10_47");
    public static By vendorInfPageHeader = By.id("D_1_25");
    public static By actionCode = By.id("I_2_29");
    public static By vendorNo = By.id("I_3_29");
    public static By minOrderCode = By.id("I_16_32");
    public static By minOrderQty = By.id("I_17_32");
    public static By freightOrderCode = By.id("I_18_32");
    public static By freightOrderQty = By.id("I_19_32");
    public static By vendorName = By.id("D_3_48");

}
