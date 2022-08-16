package pages.pricing.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SalesQuotesPage extends ReusableLib {

    public SalesQuotesPage(Helper helper) {
        super(helper);
    }

    public static By searchJobName = By.id("S1JOBNME");
    public static By backBtn = By.id("btnBack");
    public static By overRide = By.id("btnCF09");
    public static By saleQExtBtn = By.id("btnCF03");
    public static By convert = By.id("Convert");
    public static By quotesHeader = By.id("wbScrTitle");
    public static By quoteNum = By.id("S1NBR");
    public static By costMethodDropDown = By.id("S1COSTU");
    public static By printSendBtn = By.id("btnCF08");
    public static By statusHeader = By.id("OutputField1");
    public static By nextBtn = By.id("btnSubmit");
}
