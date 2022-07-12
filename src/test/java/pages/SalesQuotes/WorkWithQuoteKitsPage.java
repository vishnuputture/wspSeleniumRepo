package pages.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class WorkWithQuoteKitsPage extends ReusableLib {

    public WorkWithQuoteKitsPage(Helper helper) {
        super(helper);
    }

    public static By createQuote = By.id("btnCF06"); //Change name to "createKit" instead?
    public static By nextBtn = By.id("btnSubmit");
    public static By accept = By.id("btnCF09");
    public static By saleQExtBtn = By.id("btnCF03");
    public static By createKit = By.id("btnCF06");
    public static By backBtn = By.id("btnCF12");
    public static By quoteKitsHeader = By.id("wbScrTitle");

}
