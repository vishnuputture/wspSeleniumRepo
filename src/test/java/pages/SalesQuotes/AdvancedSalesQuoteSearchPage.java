package pages.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;
public class AdvancedSalesQuoteSearchPage extends ReusableLib {

    public AdvancedSalesQuoteSearchPage(Helper helper) {
        super(helper);
    }
    public static By nextBtn=By.id("btnSubmit");
    public static By backBtn=By.id("btnBack");
    public static By saleQExtBtn=By.id("btnCF03"); //More UI filler?
    public static By itemNumber=By.id("C1ITEM");
    public static By itemDescription=By.id("C1DESC1");
    public static By jobName=By.id("C1JOB");
    public static By custNum=By.id("C1CUST");
    public static By salespersonNum=By.id("C1SALES");
    public static By createDateFrom=By.id("C1FDATE");
    public static By createDateTo=By.id("C1TDATE");
    public static By quotedBy=By.id("C1QTDBY");
    public static By followupDateFrom=By.id("C1FFDATE");
    public static By followupDateTo=By.id("C1TFDATE");
    public static By quoteType=By.id("C1TYPE");
    public static By expDateFrom=By.id("C1FEDATE");
    public static By expDateTo=By.id("C1TEDATE");
    public static By quotesHeader=By.id("wbScrTitle");


}
