package pages.pricing.PriceSheet;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class PriceSheetDetails extends ReusableLib
{
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public PriceSheetDetails(Helper helper) {
        super(helper);
    }

    public static By detailsPageHeader= By.xpath("//h2[text()='Price Sheet Details']");

    public static By codeValue=By.xpath("//div[contains(@class,'sheet-detail d-flex justify-content-between')]/div[1]/p");
    public static By manufacturerValue=By.xpath("//div[contains(@class,'sheet-detail d-flex justify-content-between')]/div[2]/p");
    public static By statusValue=By.xpath("//div[contains(@class,'sheet-detail d-flex justify-content-between')]/div[3]//span");

    public static By nameValue=By.xpath("//input[@id='priceSheetName']");


}
