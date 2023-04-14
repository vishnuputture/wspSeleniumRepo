package pages.WiseSmokeTest;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class PricingSmokeTestPage extends ReusableLib {

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public PricingSmokeTestPage(Helper helper) {
        super(helper);
    }

    public static By account_Number_value = By.xpath("//div[@id='D_5_1']/following-sibling::input[1]");
    public static By pricing_column_value = By.xpath("//div[@id='D_15_2']/following-sibling::input[1]");
    public static By costs_and_prices_header = By.id("hdrCostPrices");
}
