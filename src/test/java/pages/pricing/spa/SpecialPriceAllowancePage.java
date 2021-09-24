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
    public static By btnAddContract = By.id("btnCF06");
    public static By txtBoxContractname = By.id("I_4_22");
    public static By txtBoxStartDate = By.id("I_5_22");
    public static By txtBoxEndDate = By.id("I_5_72");
    public static By txtBoxVendorNo = By.id("I_6_22");
    public static By txtBoxGroupNo = By.id("I_7_22");
    public static By lblSuccess = By.xpath("//div[@id='D_24_2'][contains(text(),'Contract')]");
    public static By lblNoRecords = By.xpath("//div[@id='D_11_20'][contains(text(),'*')]");
    public static By txtBoxSearchCon = By.id("I_4_32");
    public static By lblContractName = By.id("D_10_13");
    public static By txtBoxOption = By.id("I_10_3");
    public static By btnReturn = By.id("btnCF12");
    public static By lblInvalidDeletionMessage = By.xpath("//div[@id='D_24_2'][contains(text(),'Delete')]");
    public static By btnDelete = By.id("btnCF09");
    
}
