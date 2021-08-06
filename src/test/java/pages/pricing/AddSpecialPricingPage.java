package pages.pricing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class AddSpecialPricingPage extends ReusableLib {

	 public AddSpecialPricingPage(Helper helper) {
	        super(helper);
	    }
	 
	 public static By addSpecialPriceTitle=By.xpath("//div[@class='app-header'][@id='D_2_17']");
	 public static By btnF12=By.id("btnCF12");
	 public static By custNumTxtBox=By.xpath("//input[@id='I_4_22']");
	 public static By itemNumTxtBox=By.xpath("//input[@id='I_5_22']");
	 public static By specialPriceTxtBox= By.xpath(("//input[@id='I_8_22']"));
	 public static By expDateTxtBox= By.xpath(("//input[@id='I_9_64']"));
	 public static By validationLbl=By.xpath("//div[@id='D_24_2']");
	 public static By successLbl=By.xpath("//div[@id='D_24_2'][contains(text(),'Record')]");
	 
	 
}
