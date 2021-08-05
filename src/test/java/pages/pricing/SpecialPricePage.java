package pages.pricing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SpecialPricePage extends ReusableLib {
	
	 public SpecialPricePage(Helper helper) {
	        super(helper);
	    }
	 public static By specialPricingAdd=By.xpath("//input[@type='button'][@value='F6=Add'][@class='win-button-genie']");
	 public static By btnF3=By.id("btnExit");
	 public static By btnProcessF9=By.xpath("//input[@value='F9=Process'][@id='btnCF09']");
	 public static By spclPriceTitle= By.xpath("//div[@class='app-header'][@id='D_2_15']");
	 public static By selectPricingRecordBox= By.xpath("//input[contains(@id,'I_1')]");
	 //public static By selectPricingRecordBox= By.xpath("//div[@class='A20'][contains(text(),'*')]/preceding-sibling::input[1]");
	 public static By filterTxtBox = By.id("I_4_59");
	 public static By posToCustNo = By.xpath("//a[contains(text(),'Cust')]/parent::div/following-sibling::input[1]");
	 public static By posToItemNo = By.xpath("//a[contains(text(),'Item')]/parent::div/following-sibling::input[1]");
	 public static By specialPriceRow1 = By.xpath("//div[starts-with(@id,'D_10')]");
	 public static By specialPriceRow2 = By.xpath("//div[starts-with(@id,'D_11')]");
	 public static By sortByItemNumBtn = By.xpath("//input[@title='F10=Sort By Item Number']");
	 public static By sortByCustNumBtn = By.xpath("//input[@title='F10=Sort by Customer Number']");
	 public static By noItemMsgLbl= By.id("D_12_4");
	 public static By btnRefreshF5= By.id("btnCF05");
	 public static By papMessage= By.id("D_24_2");
	
}
