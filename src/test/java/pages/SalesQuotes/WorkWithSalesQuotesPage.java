package pages.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class WorkWithSalesQuotesPage extends ReusableLib {
    public WorkWithSalesQuotesPage(Helper helper) {
        super(helper);
    }
    public static By createQuote = By.id("btnCF06");
    public static By searchJobName = By.id("S1JOBNME");
    public static By searchCust = By.id("VDFILTER");
    public static By creationDate = By.id("VDPOSTODT");
    public static By nextBtn = By.id("btnSubmit");
    public static By saleQExtBtn=By.id("btnCF03");
    public static By workWithQuotesHeader=By.id("pgmTitle");

}
