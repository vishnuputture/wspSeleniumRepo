package pages.common;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class MasterPage extends ReusableLib {
	 public MasterPage(Helper helper) {
	        super(helper);
	    }

	public static By orderProcessMenu=By.xpath("//div[text()='3']/following-sibling::div");
    public static By salesAnalysisMenu=By.xpath("//div[text()='5']/following-sibling::div");
	public static By inventoryMenu = By.xpath("//div[text()='7']/following-sibling::div");
	public static By inventoryAdjustmentMenu = By.xpath("//div[text()='13']/following-sibling::div/a[contains(text(),'Entry')]");
	public static By purchaseOrderMenu = By.xpath("//div[text()='11']/following-sibling::div/a[contains(text(),'Purchase')]");
	public static By inventoryReceiptMenu = By.xpath("//div[text()='5']/following-sibling::div/a[contains(text(),'Receipt')]");
	public static By inventoryReciptMenu = By.xpath("//div[text()='4']/following-sibling::div/a[contains(text(),'Inventory')]");
	public static By avgCostAdjustmentMenu = By.xpath("//div[text()='7']/following-sibling::div/a[contains(text(),'Average')]");
    public static By itemMasterMenu =By.xpath("//div[text()='12']/following-sibling::div/a[contains(text(),'Revision')]");
	public static By btnSignOut=By.xpath("//a[text()='Signoff']");
	public static By sqlTxtBox=By.id("I_22_7");
	public static By sqlTitleLbl=By.id("D_1_11");
	public static By loadingAnime=By.id("_pui_loading_animation");
	public static By optionAndConstantsMenu = By.xpath("//div[text()='24']/following-sibling::div/a[contains(text(),'Options')]");
	public static By pricingMatrix = By.xpath("//div[text()='20']/following-sibling::div/a[contains(text(),'Pricing')]");
	public static By salesQuotes = By.xpath("//div[text()='13']/following-sibling::div/a[contains(text(),'Sales')]");
    public static By salesOrdersMenu = By.id("D_9_7");

}
