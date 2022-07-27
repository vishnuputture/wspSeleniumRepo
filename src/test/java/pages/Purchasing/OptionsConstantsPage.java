package pages.Purchasing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class OptionsConstantsPage extends ReusableLib {

    public OptionsConstantsPage(Helper helper) {
        super(helper);
    }

    public static By optionAndConstantsMenu = By.xpath("//div[text()='24']/following-sibling::div/a[contains(text(),'Options')]");
    public static By optionConstantHeader = By.id("D_2_21");
    public static By buyingDiscountMenu = By.xpath("//div[text()='13']/following-sibling::div/a[contains(text(),'Discount')]");
    public static By discountMultiplier = By.xpath("//div[text()='10']/following-sibling::div/a[contains(text(),'Discount')]");
    public static By buyingDiscPageHeader = By.id("D_2_24");
    public static By processingOptionHeader = By.id("D_2_32");
    public static By discMultOpt = By.id("I_14_65");

}
