package pages.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SalesQuoteUserPreferencesPage extends ReusableLib {
    public SalesQuoteUserPreferencesPage(Helper helper) {
        super(helper);
    }
    public static By preferencesHeader=By.id("wbScrTitle");
    public static By backBtn=By.id("btnCF12");
    public static By nextBtn=By.id("btnSubmit");

}
