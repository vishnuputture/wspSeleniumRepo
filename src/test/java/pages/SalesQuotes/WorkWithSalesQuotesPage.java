package pages.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class WorkWithSalesQuotesPage extends ReusableLib {
    public WorkWithSalesQuotesPage(Helper helper) {
        super(helper);
    }

    public static By workingSalesQuotes = By.xpath("//div[text()='1']/following-sibling::div/a[contains(text(),'Work')]");
    public static By createQuote = By.id("btnCF06");
    public static By searchJobName = By.id("S1JOBNME");
    public static By searchCust = By.id("VDFILTER");
    public static By creationDate = By.id("VDPOSTODT");
    public static By nextBtn = By.id("btnSubmit");
    public static By saleQExtBtn = By.id("btnCF03");
    public static By workWithQuotesHeader = By.id("pgmTitle");
    public static By quoteJobName = By.id("S1JOBNME");
    public static By custNumberCret = By.id("S1CUST");
    public static By backBtn = By.id("btnBack");

}
