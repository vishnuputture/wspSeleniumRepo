package pages.common;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class MasterPage extends ReusableLib {
	 public MasterPage(Helper helper) {
	        super(helper);
	    }

	public static By orderProcessMenu=By.xpath("//div[text()='3']/following-sibling::div");
	public static By inventoryMenu = By.xpath("//div[text()='7']/following-sibling::div");
	public static By inventoryAdjustmentMenu = By.xpath("//div[text()='13']/following-sibling::div/a[contains(text(),'Entry')]");
	public static By inventoryReceiptMenu = By.xpath("//div[text()='5']/following-sibling::div/a[contains(text(),'Receipt')]");
	public static By avgCostAdjustmentMenu = By.xpath("//div[text()='7']/following-sibling::div/a[contains(text(),'Average')]");
	public static By specialPricingMenu=By.xpath("//div[text()='9']/following-sibling::div/a[contains(text(),'Revise')]");
	public static By ItemMasterMenu=By.xpath("//div[text()='12']/following-sibling::div/a[contains(text(),'Revision')]");
	public static  By btnSignOut=By.xpath("//a[text()='Signoff']");
	public static By sqlTxtBox=By.id("I_22_7");
	public static By sqlTitleLbl=By.id("D_1_11");
	public static By loadingAnime=By.id("_pui_loading_animation");
	
	
}
