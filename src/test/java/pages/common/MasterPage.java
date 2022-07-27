package pages.common;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class MasterPage extends ReusableLib {
    public MasterPage(Helper helper) {
        super(helper);
    }

    public static By loadingSpinner = By.xpath("//i[contains(@class,'fa fa-spinner')]");
    public static By accountReceivable = By.xpath("//div[@id='D_13_7']/a");
    public static By orderProcessMenu = By.xpath("//div[text()='3']/following-sibling::div");
    public static By salesAnalysisMenu = By.xpath("//div[text()='5']/following-sibling::div");
    public static By inventoryMenu = By.xpath("//div[text()='7']/following-sibling::div");
    public static By inventoryAdjustmentMenu = By.xpath("//div[text()='13']/following-sibling::div/a[contains(text(),'Entry')]");
    public static By purchaseOrderMenu = By.xpath("//div[text()='11']/following-sibling::div/a[contains(text(),'Purchase')]");
    public static By inventoryReceiptMenu = By.xpath("//div[text()='5']/following-sibling::div/a[contains(text(),'Receipt')]");
    public static By inventoryReciptMenu = By.xpath("//div[text()='4']/following-sibling::div/a[contains(text(),'Inventory')]");
    public static By avgCostAdjustmentMenu = By.xpath("//div[text()='7']/following-sibling::div/a[contains(text(),'Average')]");
    public static By itemMasterMenu = By.xpath("//div[text()='12']/following-sibling::div/a[contains(text(),'Revision')]");
    public static By btnSignOut = By.xpath("//a[text()='Signoff']");
    public static By sqlTxtBox = By.id("I_22_7");
    public static By companyLbl = By.id("D_5_13_W1");
    public static By sqlTitleLbl = By.id("D_1_11");
    public static By loadingAnime = By.id("_pui_loading_animation");
    public static By optionAndConstantsMenu = By.xpath("//div[text()='24']/following-sibling::div/a[contains(text(),'Options')]");
    public static By pricingMatrix = By.xpath("//div[text()='20']/following-sibling::div/a[contains(text(),'Pricing')]");
    public static By salesQuotes = By.xpath("//div[text()='13']/following-sibling::div/a[contains(text(),'Sales')]");
    public static By salesOrdersMenu = By.id("D_9_7");
	public static By optionConstPageTitle = By.id("D_2_21");
	public static By inquiryBuyerMenu =By.xpath("//div[text()='2']/following-sibling::div/a[contains(text(),'Buyer')]");
	public static By inquiryPurchaseOrdersMenu =By.xpath("//div[text()='13']/following-sibling::div[@id='D_5_47']/a");
	public static By vendorInvoiceReconciliation =By.xpath("//div[text()='8']/following-sibling::div[@id='D_12_7']/a");
	public static By salesPersonMenu =By.xpath("//div[text()='1']/following-sibling::div[@id='D_5_7']/a");
	public static By mailingMasterMenu =By.xpath("//div[text()='1']/following-sibling::div/a[contains(text(),'Mailing')]");
	public static By pageTitleInventoryManagement =By.id("D_2_25");
	public static By menuOptionsAndConstants =By.xpath("//div[@id='D_16_47']/a[contains(text(),'Options')]");
	public static By menuFreightClassCode =By.xpath("//div[@id='D_7_47']/a[contains(text(),'Freight')]");
	public static By pageTitleFreightClassCode =By.id("D_2_28");
}
