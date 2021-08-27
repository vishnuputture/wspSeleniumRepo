package pages.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class WorkWithSalesQuotes extends ReusableLib {

    public WorkWithSalesQuotes(Helper helper) {
        super(helper);
    }
    public static By createQuote=By.id("btnCF06");
    public static By searchJobName=By.id("S1JOBNME");
    public static By searchCust=By.id("VDFILTER");
    public static By creationDate=By.id("VDPOSTODT");
    public static By custNumberCret=By.id("S1CUST");
    public static By quoteJobName=By.id("S1JOBNME");
    public static By nextBtn=By.id("btnSubmit");
    public static By workingSalesQuotes = By.xpath("//div[text()='1']/following-sibling::div/a[contains(text(),'Work')]");
}
