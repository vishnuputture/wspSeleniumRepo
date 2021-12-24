package pages.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class VendorInformationPage extends ReusableLib {

    public VendorInformationPage(Helper helper) {
        super(helper);
    }

    public static By vendorData = By.id("D_10_47");
    public static By vendorInfPageHeader = By.id("D_1_25");


}
