package businesskeywords.Inventory.WinVision;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.testng.Assert;
import pages.AccountReceivable.MiscChargesAndAdjustmentsPage.MiscChargesAndAdjustmentsPage;
import pages.inventory.WinVisionPage;
import supportLibraries.Utility_Functions;
public class OnlineCatalog extends ReusableLib{
    CommonActions commonObj;
    private FrameworkDriver ownDriver;
    public OnlineCatalog(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }
    public void verifyOnlineCatalogHomePageUI() {

        commonObj.validateText(WinVisionPage.welcomeHeader, "Welcome!", "Validating [Welcome!] Page header");
        Utility_Functions.xHoverElementclicks(ownDriver.findElement(By.xpath("//div[@title='Menu']")),ownDriver);
        click(WinVisionPage.menuIcon);
        waitForVisible(WinVisionPage.onlineCatalog);
        click(WinVisionPage.onlineCatalog);
        Utility_Functions.timeWait(5);
        waitForVisible(WinVisionPage.pageHeader);
        commonObj.validateText(WinVisionPage.pageHeader, "Online Catalog", "Validating Header [Online Catalog]");
        waitForVisible(WinVisionPage.searchFilterHeader);


    }

    public void verifyOnlineCatalogPageWithoutResults() {
        waitForVisible(WinVisionPage.helpIcon);
        click(WinVisionPage.buttonSearchClose);
        waitForVisible(WinVisionPage.searchIcon);
        commonObj.validateText(WinVisionPage.noResultText, "No catalog results were found at this time", "Validating message [No catalog results were found at this time]");
    }

    public void verifyOCHelpIcon() {
        click(WinVisionPage.helpIcon);
        waitForVisible(WinVisionPage.onlineCatalogPopUp);
        commonObj.validateText(WinVisionPage.ocPopUpBody1, "Provides pricing information & On-hand quantities for all items carried in a given RDC via search by MF/Vendor Name.", "Validating message [Provides pricing information & On-hand quantities for all items carried in a given RDC via search by MF/Vendor Name.]");
        commonObj.validateText(WinVisionPage.ocPopUpBody2, "Select a RDC, Stock item number, description, and/or manufacturer.", "Validating message [Select a RDC, Stock item number, description, and/or manufacturer.]");
        click(WinVisionPage.closePopUpButton);
        }

    public void verifySearchPopUpUI() {
        commonObj.validateText(WinVisionPage.searchFilterHeader, "Search Filters", "Validating Header [Search Filters]");
        commonObj.validateElementExists(WinVisionPage.buttonSearchClose, "Validated Close button");
        commonObj.validateElementExists(WinVisionPage.distributionCenterDropDown, "Validated presence of Distribution Center Dropdown");
        commonObj.validateElementExists(WinVisionPage.stockFilterDropDown, "Validated presence of Stock Filter dropdown");
        commonObj.validateElementExists(WinVisionPage.manufacturersDropDown, "Validated presence of Manufacturer Drop down");
        commonObj.validateElementExists(WinVisionPage.productCategoryDropDown, "Validated presence of Product Category dropdown");
        commonObj.validateElementExists(WinVisionPage.stockNumberTextBox, "Validated presence of Stock Number Text box");
        commonObj.validateElementExists(WinVisionPage.descriptionTextBox, "Validated presence of Description Text box");
        commonObj.validateElementExists(WinVisionPage.applyFilterButton, "Validated presence of Apply Filter button");
        commonObj.validateElementExists(WinVisionPage.clearFilterButton, "Validated presence of Clear Filter button");
    }
}
