package pages.Purchasing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class DiscountGroups extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public DiscountGroups(Helper helper) {
        super(helper);
    }
    public static By addDiscountGroup = By.id("btnCF06");
    public static By manufactureCode = By.id("I_4_9_W1");
    public static By productCode = By.id("I_5_9_W1");
    public static By vendorCode = By.id("I_6_9_W1");
    public static By multiplier = By.id("I_8_27_W1");
    public static By costBase = By.id("I_10_27_W1");
    public static By F9toConfirm = By.id("btnCF09");
    public static By cancel = By.id("btnCancel");
}
