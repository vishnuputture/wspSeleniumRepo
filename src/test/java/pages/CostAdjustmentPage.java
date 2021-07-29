package pages;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class CostAdjustmentPage extends ReusableLib{
	
	public CostAdjustmentPage(Helper helper) {
        super(helper);
    }

	 public static By lblTitle = By.xpath("//div[@id='outC1HDR']");
	 
	 public static By txtItemNumber = By.xpath("//div/input[@id='SITEM.1']");
	 public static By txtQuantity = By.xpath("//div/input[@id='txtSQTYD.1']");
	 public static By txtInCorrectCost = By.xpath("//div/input[@id='txtSICOST.1']");
	 public static By txtNewCost = By.xpath("//div/input[@id='txtSNCOST.1']");
	 public static By lblCurrAvgCost = By.xpath("//div[@id='SOCOST.1']");
	 public static By lblCurrNewCost = By.xpath("//div[@id='outSACOST.1']");
	 public static By btnProcess = By.xpath("//input[@id='btnSubmit']");
	 public static By btnLedger = By.xpath("//input[@id='Button1.1']");
	
}
