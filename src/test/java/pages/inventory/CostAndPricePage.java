package pages.inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class CostAndPricePage extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public CostAndPricePage(Helper helper) {
        super(helper);
    }

    public static By position_to_itemMaster = By.id("inItemNbr");
    public static By list_price_option = By.xpath("//div[contains(text(),'041501880')]/../following-sibling::div[2]//input[@id='LPW0.1']");
    public static By success_message_on_update = By.xpath("//div[@id='dynoInfoBox']/span[1]");
    public static By exit_button = By.id("btnExit");
    public static By yes_to_exit = By.xpath("//input[@class='win-button win-button-primary' and @value='Yes']");
}
