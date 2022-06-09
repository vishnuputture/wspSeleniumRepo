package pages.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SalesQuoteUserPreferencesPage extends ReusableLib {
    //This page is pretty bare for the moment.
    //I'm not sure what, if anything, needs to be added.
    public SalesQuoteUserPreferencesPage(Helper helper) {
        super(helper);
    }
    public static By preferencesHeader=By.id("wbScrTitle");
    public static By backBtn=By.id("btnCF12");
    public static By nextBtn=By.id("btnSubmit");

}
