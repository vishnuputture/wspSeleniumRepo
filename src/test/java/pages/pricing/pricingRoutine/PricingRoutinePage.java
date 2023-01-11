package pages.pricing.pricingRoutine;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class PricingRoutinePage extends ReusableLib {
    public PricingRoutinePage(Helper helper) {
        super(helper);
    }

    public static By startDate = By.id("I_10_64");
    public static By endDate = By.id("I_11_64");
    public static By custNo = By.id("I_4_24");
    public static By itemNo = By.id("I_5_24");
    public static By specialPrice = By.id("D_11_32");
}
