package pages.inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class BuyersInquiryPage extends ReusableLib {

    public BuyersInquiryPage(Helper helper) {
        super(helper);
    }

    public static By pageTitle = By.id("D_1_30");
    public static By btnStockNo = By.xpath("//div[@id='D_2_2']/a");
    public static By tbxStockNo = By.id("I_2_15");
    public static By withReceipts = By.id("D_7_22");
    public static By onPO = By.id("D_10_22");
    public static By AvailToSell = By.id("D_6_22");

}
