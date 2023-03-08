package pages.pricing.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SalesQuotePrintSendPage extends ReusableLib {

    public SalesQuotePrintSendPage(Helper helper) {
        super(helper);
    }

    public static By nextBtn = By.id("btnSubmit");
    public static By process = By.id("btnCF09");
    public static By quoteNum = By.id("S0QUOTE");
    public static By quoteName = By.id("S0JOBNME");
    public static By backBtn = By.id("btnCA12");
    public static By printSendHeader = By.id("wbScrTitle");
    public static By deselectAll = By.id("Deselect All");
    public static By localPrintCopyOptionsDropdown = By.xpath("//div[contains(text(),'Local') and contains(text(),'Print') and contains(text(),'Copy')]/preceding::div[4]/select[@id='S1OPT.2']");

}
