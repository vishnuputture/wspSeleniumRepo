package pages.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SalesQuotePrintSendPage extends ReusableLib {

    public SalesQuotePrintSendPage(Helper helper) {
        super(helper);
    }

    public static By nextBtn = By.id("btnSubmit");
    public static By process = By.id("btnCF09");
    public static By quoteNum = By.id("S0QUOTE");
    public static By quoteName = By.id("S0JOBNME");
    public static By backBtn = By.id("btnCA12");
    public static By printSendHeader = By.id("wbScrTitle");

}
