package pages;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class InventoryManagementMainMenuPage extends ReusableLib {
    public InventoryManagementMainMenuPage(Helper helper) {
        super(helper);
    }

    public static By moreInventorySelections=By.xpath("//div[text()='21']/following-sibling::div/a[contains(text(),'More')]");

}
