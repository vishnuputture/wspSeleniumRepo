package pages;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class CostAdjustmentPage extends ReusableLib{
	
	public CostAdjustmentPage(Helper helper) {
        super(helper);
    }

	 public static By lblTitle = By.xpath("//div[@id='outC1HDR']");
	 
	 public static By txtItemNumber = By.xpath("//div/input[contains(@id,'SITEM.')]");
	 public static By txtQuantity = By.xpath("//div/input[contains(@id,'txtSQTYD.')]");
	 public static By txtInCorrectCost = By.xpath("//div/input[contains(@id,'txtSICOST.')]");
	 public static By txtNewCost = By.xpath("//div/input[contains(@id,'txtSNCOST.')]");
	 public static By lblCurrAvgCost = By.xpath("//div[contains(@id,'SOCOST.')]");
	 public static By lblCurrNewCost = By.xpath("//div[contains(@id,'outSACOST.')]");
	 public static By btnProcess = By.xpath("//input[@id='btnSubmit']");
	 public static By btnLedger = By.xpath("//input[contains(@id,'Button1.')]");
	
}
