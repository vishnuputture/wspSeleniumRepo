package pages.pricing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import org.openqa.selenium.By;
import supportLibraries.Utility_Functions;
import org.openqa.selenium.Keys;

public class AddSpecialPricingPage extends ReusableLib {

	private FrameworkDriver ownDriver;
	 public AddSpecialPricingPage(Helper helper) {
	        super(helper);
		 ownDriver=helper.getGSDriver();
	    }
	 
	 public static By addSpecialPriceTitle=By.xpath("//div[@class='app-header'][@id='D_2_17']");
	 public static By btnF12=By.id("btnCF12");
	 public static By custNumTxtBox=By.xpath("//input[@id='I_4_22']");
	 public static By itemNumTxtBox=By.xpath("//input[@id='I_5_22']");
	 public static By specialPriceTxtBox= By.xpath(("//input[@id='I_8_22']"));
	 public static By expDateTxtBox= By.xpath(("//input[@id='I_9_64']"));
	 public static By validationLbl=By.xpath("//div[@id='D_24_2']");
	 public static By successLbl=By.xpath("//div[@id='D_24_2'][contains(text(),'Record')]");

	 public void enterCustNumber(String dataStr, String message){
		sendKeys(custNumTxtBox,dataStr,message);
		 Utility_Functions.actionKey(Keys.ENTER, ownDriver);
	 }
	 public void enterCustItemNumber(String custNoStr, String custMessage,String itemNoStr, String itemMessage){
		 sendKeys(custNumTxtBox,custNoStr,custMessage);
		 sendKeys(itemNumTxtBox,itemNoStr,itemMessage);
		 Utility_Functions.actionKey(Keys.ENTER, ownDriver);
	 }
	 
}
