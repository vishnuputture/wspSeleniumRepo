package pages.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;
public class SalesQuoteCustomerPrintPreferencesPage extends ReusableLib {

    public SalesQuoteCustomerPrintPreferencesPage(Helper helper) {
        super(helper);
    }

    public static By custNum=By.id("S1CUST");
    public static By nextBtn=By.id("btnSubmit");
    public static By custName=By.id("S1ALPH");
    public static By backBtn=By.id("btnCF12");
    public static By printHeader=By.id("PrintPreferenceHeading");
}
