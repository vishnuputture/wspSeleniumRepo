package pages.pricing.spa;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SpecialPriceAllowancePage extends ReusableLib {
    public SpecialPriceAllowancePage(Helper helper) {super(helper);}

    public static By header = By.id("D_2_25");
    public static By textSearchHeader = By.id("D_4_2");
    public static By inputSearchBox = By.xpath("//input[@id='I_4_32']");
    public static By optionsHeader = By.id("D_6_2");
    public static By optionsSet = By.id("D_7_3");
    public static By btnExit = By.id("btnExit");
    public static By lblExpired = By.xpath("//div[text()='Expired']");
    public static By lblActive = By.xpath("//div[text()='Active']");
    public static By btnHideShowExpired = By.id("btnCF07");
}
