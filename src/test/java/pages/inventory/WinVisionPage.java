package pages.inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;
public class WinVisionPage extends ReusableLib{
    public WinVisionPage(Helper helper) {
        super(helper);
    }

    public static By welcomeHeader = By.xpath("//div[@class='text-center']/h1");
    public static By welcomeSubHeader = By.xpath("//div[@class='text-center']/p");
    public static By menuIcon = By.xpath("//div[@title='Menu']");
    public static By onlineCatalog = By.id("catalog-panel-nav");
    public static By orderTracking = By.id("ot-panel-nav");
    public static By commodityPricing = By.id("cp-panel-nav");
    public static By servicePartners = By.id("sp-panel-nav");
    public static By lineCards = By.id("lc-panel-nav");
    public static By verifyCredentialsButton = By.xpath("//input[@value='Verify Credentials']");
    public static By usernameTextbox = By.id("username");
    public static By passwordTextbox = By.id("password");
    public static By pageHeader = By.xpath("//h2[@class='page-title']");
    public static By searchFilterHeader = By.xpath("//h1[@class='panel-title']");
    public static By helpIcon = By.xpath("//*[@id='page-information']/i");
    public static By searchIcon = By.id("flyout-search");
    public static By noResultText = By.xpath("//*[@class='no-results-text']");
    public static By buttonSearchClose = By.xpath("//button[@type='button']/span[contains(@class,'sidebar-close')]");
    public static By onlineCatalogPopUp = By.xpath("//div[@class='modal fade win-modal-small show']//h2[text()='Online Catalog']");
    public static By orderTrackingPopUp = By.xpath("//div[@class='modal fade win-modal-small show']//h2[text()='Order Tracking']");
    public static By ocPopUpBody1 = By.xpath("//div[@class='modal-content']//div[@class='modal-message']");
    public static By ocPopUpBody2 = By.xpath("//div[@class='modal-content']//div[@class='modal-message']/following-sibling::div");
    public static By otPopUpBody = By.xpath("//div[@class='modal-content']//div[@class='modal-message']");
    public static By closePopUpButton = By.xpath("//div[@class='modal fade win-modal-small show']//button[@aria-label='Close dialog']");
    public static By distributionCenterDropDown = By.xpath("//select[@ng-reflect-name='rdcNumber']");
    public static By stockFilterDropDown = By.xpath("//select[@id='select-stock']");
    public static By manufacturersDropDown = By.xpath("//select[@id='select-manufacturer']");
    public static By productCategoryDropDown = By.xpath("//select[@id='select-product-category']");
    public static By stockNumberTextBox = By.xpath("//input[@id='input-item-number']");
    public static By descriptionTextBox = By.xpath("//input[@id='input-description']");
    public static By applyFilterButton = By.xpath("//button[text()=' Apply Filters ']");
    public static By clearFilterButton = By.xpath("//button[text()=' Clear All Filters ']");

}
