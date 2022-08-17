package pages.pricing.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class WorkWithSalesQuoteDetailLinesPage extends ReusableLib {

    public WorkWithSalesQuoteDetailLinesPage(Helper helper) {
        super(helper);
    }

    public static By addBtn = By.id("btnCF06");
    public static By nextBtn = By.id("btnSubmit");
    public static By backBtn = By.id("btnBack");
    public static By saleQExtBtn = By.id("btnCF03");
    public static By workWithLinesHeader = By.id("wbScrTitle");
    public static By quoteNum = By.id("S0QUOTE");

}
