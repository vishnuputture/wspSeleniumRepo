package pages.pricing.matrixcost;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class InventoryManagementMenu2Page extends ReusableLib {
    public InventoryManagementMenu2Page(Helper helper) {
        super(helper);
    }

    public static By matrixCostUpdate=By.xpath("//div[text()='1']/following-sibling::div/a[contains(text(),'Matrix')]");

}
