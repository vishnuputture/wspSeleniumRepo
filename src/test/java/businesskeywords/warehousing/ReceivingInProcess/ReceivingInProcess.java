package businesskeywords.warehousing.ReceivingInProcess;

import businesskeywords.warehousing.BinMaintanence.binMaintenance;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.SPO.SpoPage;
import pages.common.MasterPage;
import pages.warehouse.BinMaintenance.BinMaintenancePage;
import pages.warehouse.ReceivingInProcess.ReceivingInProcessPage;
import pages.warehouse.TruckPage;
import supportLibraries.Utility_Functions;

import java.util.List;

public class ReceivingInProcess extends ReusableLib {
    CommonActions commonObj;
    binMaintenance binMain;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public ReceivingInProcess(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        binMain = new binMaintenance(helper);
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

    public By pageLabel(String field) {
        return By.xpath("//div[text()='" + field + "']");
    }

    public By spanElement(String text) {
        return By.xpath("//span[contains(text(),'" + text + "')]");
    }

    public By button(String button) {
        return By.xpath("//button[text()='" + button + "']");
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
        String userId = getUserId();
        clickSearchIcon();
        clearText(searchField("Purchase Order"));
        searchAndApplyFilter("User ID", userId);
        commonObj.validateText(By.xpath("//tr/td/span"), userId, "User id is present" + userId);
        String vendor = driver.findElements(By.xpath("//tr/td")).get(1).getText();
        clickSearchIcon();
        clearText(searchField("User ID"));
        searchAndApplyFilter("Vendor", vendor);
        Utility_Functions.xAssertEquals(report, driver.findElements(By.xpath("//tr/td")).get(1).getText(), vendor, "");
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
        if (isDisplayed(BinMaintenancePage.itemNumberContains)) {
            click(BinMaintenancePage.itemNumberContains, "Enable item Number Contains check box");
        } else {
            sendKeys(searchField("Purchase Order"), "03", "Search for po");
        }
        Utility_Functions.timeWait(2);
        click(ReceivingInProcessPage.clearAllBtn, "Click Clear All Button");
        commonObj.validateText(TruckPage.applyFiltersDis, "apply filters", "Apply Filter button is disabled");
        commonObj.validateText(SpoPage.disClearAllFilter, "Clear All Filters", "Clear All Filters button is disabled");
        click(TruckPage.filtersCrossIcon, "Click close icon");
    }

    /**
     * Keyword to verify PurchaseOrder Input Field
     */
    public void verifyPurchaseOrderInputField() {
        clickSearchIcon();
        searchAndApplyFilter("Purchase Order", jsonData.getData("InValidPo"));
        commonObj.validateText(spanElement("There are no orders currently being received."), "There are no orders currently being received.", "'There are no orders currently being received.' message is present");
        clickSearchIcon();
        searchAndApplyFilter("Purchase Order", jsonData.getData("AlphaNumericPo"));
        commonObj.validateText(spanElement("There are no orders currently being received."), "There are no orders currently being received.", "'There are no orders currently being received.' message is present");
    }

    /**
     * Keyword to verify User ID Input Field
     */
    public void verifyUserIdInputField() {
        clickSearchIcon();
        searchAndApplyFilter("User ID", jsonData.getData("InValidUserId"));
        commonObj.validateText(spanElement("There are no orders currently being received."), "There are no orders currently being received.", "'There are no orders currently being received.' message is present");
        clickSearchIcon();
        searchAndApplyFilter("User ID", jsonData.getData("AlphaNumericUserId"));
        commonObj.validateText(spanElement("There are no orders currently being received."), "There are no orders currently being received.", "'There are no orders currently being received.' message is present");
        clickSearchIcon();
        searchAndApplyFilter("User ID", jsonData.getData("PartialUserId"));
        commonObj.validateText(spanElement("There are no orders currently being received."), "There are no orders currently being received.", "'There are no orders currently being received.' message is present");
    }

    /**
     * Keyword to verify Vendor Input Field
     */
    public void verifyVendorInputField() {
        clickSearchIcon();
        searchAndApplyFilter("Vendor", jsonData.getData("InValidVendor"));
        commonObj.validateText(spanElement("There are no orders currently being received."), "There are no orders currently being received.", "'There are no orders currently being received.' message is present");
        click(ReceivingInProcessPage.clearFilterCrossIcon, "Click Vendor x");
        commonObj.validateElementExists(By.xpath("//tr/td/a"), "Page is processed and all the records are present");
        Utility_Functions.timeWait(3);
        searchAndApplyFilter("Vendor", jsonData.getData("AlphaNumericVendor"));
        commonObj.validateText(spanElement("There are no orders currently being received."), "There are no orders currently being received.", "'There are no orders currently being received.' message is present");
    }

    /**
     * Keyword to verify receiving InProcess UI
     */
    public void receivingInProcessUI() {
        commonObj.validateText(By.xpath("//h2"), "Receiving In Process", "'Receiving In Process' header is present");
        commonObj.validateText(By.xpath("//h3"), "OPEN PURCHASE ORDERS", "' OPEN PURCHASE ORDERS' sub header is present");
        commonObj.validateText(pageLabel("Received / Open Lines"), "Received / Open Lines", "'Received / Open Lines' section is present");
        commonObj.validateText(pageLabel("Percent Complete"), "Percent Complete", "'Percent Complete' section is present");
        commonObj.validateText(ReceivingInProcessPage.refreshPoListLink, "Refresh PO List", "'Refresh PO List' is present");
        String[] columnVal = {"Purchase Order", "Vendor", "Receiver Doc", "User ID", "Received / Open Lines", "Percent Complete", "First Received", "Last Received", "Actions"};
        for (String name : columnVal) {
            commonObj.validateText(By.xpath("//th[contains(text(),'" + name + "')]"), name, name + "Column is present");
        }
    }

    /**
     * Keyword to verify Sorting Functionality
     */
    public void sortingFunctionality() {
        String[] columnVal = {"Purchase Order", "Vendor", "Receiver Doc", "User ID", "Received / Open Lines", "Percent Complete", "First Received", "Last Received"};
        for (String name : columnVal) {
            commonObj.validateText(By.xpath("//th[contains(text(),'" + name + "')]"), name, name + "Column is present");
            click(By.xpath("//th[contains(text(),'" + name + "')]/descendant::i"), "click" + name + " column");
            Utility_Functions.timeWait(3);
        }
    }

    public void navigateToRemainingPage() {
        String po = getText(By.xpath("//tr/td/a")).trim();
        click(By.xpath("//tr/td/a"), "Click Purchase Order link");
        commonObj.validateText(By.xpath("//h2[text()='ORDER NUMBER: " + po + "']"), "ORDER NUMBER: " + po, "ORDER NUMBER: " + po + " is present");
    }

    /**
     * Keyword to verify search Filter Remaining UI
     */
    public void searchFilterRemainingUI() {
        clickSearchIcon();
        String[] actText = {"Item Number", "Item Description"};
        List<WebElement> els = driver.findElements(ReceivingInProcessPage.searchLabels);
        int i = 0;
        for (WebElement el : els) {
            if (el.getText().trim().contains(actText[i])) {
                commonObj.validateElementExists(ReceivingInProcessPage.searchLabels, actText[i] + " is present");
            }
        }
        commonObj.validateElementExists(ReceivingInProcessPage.containsCheckBox, "item Number Contains check box is present");
        commonObj.validateElementExists(TruckPage.filtersCrossIcon, "Cross icon is present");
        commonObj.validateText(TruckPage.applyFiltersDis, "Apply Filters", "Apply Filters button exist and button is disabled");
        commonObj.validateText(SpoPage.disClearAllFilter, "Clear All Filters", "Clear All Filters button is exist with red in color and button is disabled");
    }

    public void searchFilterRemainFunctionality() {
        clickSearchIcon();
        binMain.verifyContainsCheckBox();
    }

    /**
     * Keyword to verify Item Number Input Field
     */
    public void verifyItemNoInputField() {
        clickSearchIcon();
        searchAndApplyFilter("Item Number", jsonData.getData("InValidItemNo"));
        commonObj.validateText(spanElement("There are no items remaining."), "There are no items remaining.", "'There are no items remaining.' message is present");
        clickSearchIcon();
        searchAndApplyFilter("Item Number", jsonData.getData("AlphaNumericItemNo"));
        commonObj.validateText(spanElement("There are no items remaining."), "There are no items remaining.", "'There are no items remaining.' message is present");
    }

    /**
     * Keyword to verify Item Description Input Field
     */
    public void verifyItemDescriptionInputField() {
        clickSearchIcon();
        searchAndApplyFilter("Item Number", jsonData.getData("InValidItemDesc"));
        commonObj.validateText(spanElement("There are no items remaining."), "There are no items remaining.", "'There are no items remaining.' message is present");
        clickSearchIcon();
        searchAndApplyFilter("Item Number", jsonData.getData("AlphaNumericItemDesc"));
        commonObj.validateText(spanElement("There are no items remaining."), "There are no items remaining.", "'There are no items remaining.' message is present");
    }

    /**
     * Keyword to verify Order Number Remaining UI
     */
    public void verifyOrderNumberRemainingUI() {
        commonObj.validateText(By.xpath("//h3"), "REMAINING", "REMAINING' sub header is present");
        commonObj.validateText(pageLabel("Vendor"), "Vendor", "'Received / Open Lines' section is present");
        commonObj.validateText(pageLabel("First Received - Last Received"), "First Received - Last Received", "'First Received - Last Received' section is present");
        commonObj.validateText(pageLabel("Receiver Doc"), "Receiver Doc", "'Receiver Doc' section is present");
        commonObj.validateText(pageLabel("Percent Complete"), "Percent Complete", "'Percent Complete' section is present");
        String[] columnVal = {"Line", "Item Number", "Item Description", "Open Qty", "Qty Received", "Bin Location", "Zone", "User ID", "Actions"};
        for (String name : columnVal) {
            commonObj.validateText(By.xpath("//th[contains(text(),'" + name + "')]"), name, name + "Column is present");
        }
        commonObj.validateText(button("Back"), "Back", "'Back' button is present");
    }

    /**
     * Keyword to navigate to RF Gun
     */
    public void navigateToRFGun() {
        Utility_Functions.openNewTab(driver);
        driver.get(properties.getProperty("RF_Gun"));
        sendKeys(ReceivingInProcessPage.userRF, properties.getProperty("VPNUsn"), "Enter RF user name");
        sendKeys(ReceivingInProcessPage.passwordRF, properties.getProperty("VPNPass"), "Enter RF Password");
        click(ReceivingInProcessPage.loginBtn, "Click Login Button");
        click(ReceivingInProcessPage.putAway, "Click Receive Put Away");
    }

    /**
     * Keyword to search Item Or PO
     */
    public void searchPo() {
        Utility_Functions.timeWait(4);
        sendKeys(ReceivingInProcessPage.searchPo, "", "search Item Or PO");
        Utility_Functions.actionKey(Keys.ENTER, driver);
    }

    /**
     * Keyword to process the PO
     */
    public void processPO() {
        Utility_Functions.timeWait(3);
        click(ReceivingInProcessPage.processButton, "Click on Process");
        Utility_Functions.timeWait(3);
        commonObj.validateText(ReceivingInProcessPage.poConfirmationPopup, "PO COMPLETE CONFIRMATION", "'PO COMPLETE CONFIRMATION' is present");
        click(ReceivingInProcessPage.completeBtn, "Click Complete PO button");
    }

    public void switchBackToReceivingInProcess() {
        Utility_Functions.xSwitchToWindow(driver, 0);
    }
}