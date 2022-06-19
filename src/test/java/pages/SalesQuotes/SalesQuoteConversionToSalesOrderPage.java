package pages.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;
public class SalesQuoteConversionToSalesOrderPage extends ReusableLib{

    public SalesQuoteConversionToSalesOrderPage(Helper helper) {
        super(helper);
    }
    public static By nextBtn=By.id("btnSubmit");
    public static By jobName=By.id("SJOBNME");
    public static By quoteNum=By.id("SQNBR");
    public static By saleOrderNum=By.id("SONBR");
    public static By salesOrderQuantityOrdered=By.id("SCQTYORD");
    public static By salesOrderQtyShipped=By.id("SCQTYSHP");
    public static By backBtn=By.id("btnCF12");
    public static By quoteConversionHeader=By.id("wbScrTitle");

}
