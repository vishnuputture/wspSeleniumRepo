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
    public static By actionTextBox = By.id("I_3_25");
    public static By productCode = By.id("I_4_15");
    public static By vendorCode = By.id("I_4_25");
    public static By descriptionLine1 = By.id("I_5_25");
    public static By vendorNumber = By.id("I_7_25");
    public static By btnF1 = By.id("btnCF01");
    public static By btnF2 = By.id("btnCF02");
    public static By submitButton = By.id("btnSubmit");
    public static By optionOneSelection = By.id("I_7_1");
    public static By optionVendorNumberSelection = By.id("I_8_2");
    public static By searchVendor = By.id("I_7_25");
    public static By minimumGMPercent = By.id("I_8_25");
}
