package pages.pricing.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class WorkWithKitComponentsPage extends ReusableLib {

    public WorkWithKitComponentsPage(Helper helper) {
        super(helper);
    }

    public static By nextBtn = By.id("btnSubmit");
    public static By backBtn = By.id("btnCF12");
    public static By kitCompHeader = By.id("wbScrTitle");
    public static By addItem = By.id("btnAddItem");

}
