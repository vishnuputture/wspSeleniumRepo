package pages.Purchasing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class VendorNotesPage extends ReusableLib {

    public VendorNotesPage(Helper helper) {
        super(helper);
    }

    public static By actionVendorNotes = By.id("I_3_12");
    public static By vendorInputVendorNotes = By.id("I_4_12");
    public static By Line1VendorNotes = By.id("I_6_10");

    public static By erroMsgVendorNotes = By.id("D_23_3");
    public static By hdrVendorNotes = By.id("D_1_11");

}
