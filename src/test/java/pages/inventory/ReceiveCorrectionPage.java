package pages.inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class ReceiveCorrectionPage extends ReusableLib{

	 public ReceiveCorrectionPage(Helper helper) {
	        super(helper);
	    }
	 
	 public static By txtVendorSearch = By.xpath("//input[@id='txtIAVEND']");
	 public static By txtItemNumber = By.xpath("//div/input[contains(@id,'txtITEM.')]");
	 public static By txtQuantity = By.xpath("//div/input[contains(@id,'outIIQTYD.')]");
	 public static By txtExplanation = By.xpath("//div/input[contains(@id,'txtIEXPL.')]");
	 //public static By txtItemNumbersecond = By.xpath("//div/input[@id='txtITEM.1']");
	 //public static By txtQuantitysecond = By.xpath("//div/input[@id='outIIQTYD.1']");
	 //public static By txtExplanationsecond = By.xpath("//div/input[@id='txtIEXPL.1']");
	 public static By btnProcess = By.xpath("//input[@id='btnSubmit']");
	 public static By lblOnHand = By.xpath("//div/div[contains(@id,'outIonhandqty.')]/a");
	 public static By btnContinuePop = By.xpath("//input[@id='btnButton3']");
	 public static By lblTitle = By.xpath("//div[@id='outC1HDR']");
	 public static By btnItemLedger = By.xpath("//div[contains(@id,'faItmBinLed.')]");
}
