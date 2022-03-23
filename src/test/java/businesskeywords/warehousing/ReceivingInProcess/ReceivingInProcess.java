package businesskeywords.warehousing.ReceivingInProcess;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.SPO.SpoPage;
import pages.common.MasterPage;
import pages.warehouse.ReceivingInProcess.ReceivingInProcessPage;
import pages.warehouse.TruckPage;
import supportLibraries.Utility_Functions;

import java.util.ArrayList;
import java.util.List;

public class ReceivingInProcess extends ReusableLib {
    CommonActions commonObj;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public ReceivingInProcess(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    /**
     * Keyword to Navigate to ReceivingInProcess
     */
    public void navigateToReceivingInProcess() {
        driver.get(properties.getProperty("ReceivingInProcess"));
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    public By searchField(String field) {
        return By.xpath("//input[@placeholder='" + field + "']");
    }

    /**
     * Keyword to Verify UI of Search Filter UI
     */
    public void searchFilterUI() {
        clickSearchIcon();
        String[] actText = {"Purchase Order", "User ID", "Vendor"};
        List<WebElement> els = driver.findElements(ReceivingInProcessPage.searchLabels);
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        commonObj.validateElementExists(TruckPage.filtersCrossIcon, "Cross icon is present");
        commonObj.validateText(TruckPage.applyFiltersDis, "Apply Filters", "Apply Filters button exist and button is disabled");
        commonObj.validateText(SpoPage.disClearAllFilter, "Clear All Filters", "Clear All Filters button is exist with red in color and button is disabled");
    }

    /**
     * Keyword to click search filter icon
     */
    public void clickSearchIcon() {
        Utility_Functions.timeWait(3);
        click(TruckPage.filterSearch, "Click Search Filter icon");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//h1"), "Search Filters", "Search Filters panel title is present");
    }

    /**
     * Keyword to Verify search Filter Functionality
     */
    public void searchFilterFunctionality() {
        String po = getText(By.xpath("//tr/td/a")).trim();
        clickSearchIcon();
        searchAndApplyFilter("Purchase Order", po);
        commonObj.validateText(By.xpath("//tr/td/a"), po, "Result PO number found");
        clickSearchIcon();
        searchAndApplyFilter("Purchase Order", jsonData.getData("PO"));
        commonObj.verifyElementContainsText(By.xpath("//tr/td/a"), jsonData.getData("PO"), "Filter results found");
        String userId=getUserId();
        clickSearchIcon();
        clearText(searchField("Purchase Order"));
        searchAndApplyFilter("User ID", userId);
        commonObj.validateText(By.xpath("//tr/td/span"),userId,"User id is present"+ userId);
        String vendor=driver.findElements(By.xpath("//tr/td")).get(1).getText();
        clickSearchIcon();
        clearText(searchField("User ID"));
        searchAndApplyFilter("Vendor",vendor);
        Utility_Functions.xAssertEquals(report,driver.findElements(By.xpath("//tr/td")).get(1).getText(),vendor,"");
    }

    /**
     * Keyword to get User Id
     */
    public String getUserId() {
        String userID = null;
        List<WebElement> list = driver.findElements(By.xpath("//tr/td/span"));
        for (WebElement element : list) {
            if (element.getText().trim().equals("")) {
            } else {
                userID = element.getText().trim();
                break;
            }
        }
        return userID;
    }

    /**
     * Keyword to search and Apply Filter
     */
    public void searchAndApplyFilter(String label, String val) {
        sendKeys(searchField(label), val, "Search " + val);
        Utility_Functions.timeWait(2);
        click(ReceivingInProcessPage.applyFilterBtn, "Apply Filter");
    }

    /**
     * Keyword to verify functionality of Clear All and Apply Filter button
     */
    public void applyClearAllFilterBtn() {
        clickSearchIcon();
        sendKeys(searchField("Purchase Order"), "03", "Search for po");
        Utility_Functions.timeWait(2);
        click(ReceivingInProcessPage.clearAllBtn,"Click Clear All Button");
        commonObj.validateText(TruckPage.applyFiltersDis,"apply filters","Apply Filter button is disabled");
        commonObj.validateText(SpoPage.disClearAllFilter,"Clear All Filters","Clear All Filters button is disabled");
        click(TruckPage.filtersCrossIcon,"Click close icon");
    }

    /**
     * Keyword to verify PurchaseOrder Input Field
     */
    public void verifyPurchaseOrderInputField() {
        clickSearchIcon();
        sendKeys(searchField("Purchase Order"), "03", "Search for po");
        Utility_Functions.timeWait(2);
        click(ReceivingInProcessPage.clearAllBtn,"Click Clear All Button");
        commonObj.validateText(TruckPage.applyFiltersDis,"apply filters","Apply Filter button is disabled");
        commonObj.validateText(SpoPage.disClearAllFilter,"Clear All Filters","Clear All Filters button is disabled");
        click(TruckPage.filtersCrossIcon,"Click close icon");
    }
}
