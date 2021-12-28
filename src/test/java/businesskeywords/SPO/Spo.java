package businesskeywords.SPO;

import businesskeywords.Pricing.SPA.SpecialPricingAllowance;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Settings;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.PurchaseOrders.VendorInformationPage;
import pages.SPO.SpoPage;
import pages.SalesQuotes.WorkWithSalesQuotesPage;
import pages.common.MasterPage;
import pages.inventory.CostAdjustmentPage;
import pages.inventory.ItemMasterPage;
import pages.inventory.OptionsConstantsPage;
import pages.makePayments.SchedulePaymentPage;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
import pages.pricing.pricingmatrix.PricingMatrixPage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import pages.warehouse.DriversPage;
import pages.warehouse.TruckPage;
import supportLibraries.Utility_Functions;

import java.util.Calendar;
import java.util.List;
import java.util.Properties;

public class Spo extends ReusableLib {
    CommonActions commonObj;
    SpecialPricingAllowance specPrAll;
    public static Properties properties = Settings.getInstance();

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public Spo(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        specPrAll = new SpecialPricingAllowance(helper);
    }

    /**
     * This method validates the title of Item Master Page
     *
     * @throws NoSuchElementException
     */
    public void validateItemMasterTitle() throws NoSuchElementException {
        commonObj.masterToInventory();
        commonObj.inventoryToItemMaster();
        commonObj.validateText(ItemMasterPage.pageTitle, "ITEM MASTER (I-347)", "Validating item Master page title");
    }

    /**
     * This method get MF/PD/VN Code from item master
     */
    public void getMfPdVn() {
        click(CostAdjustmentPage.searchIcon, "Click Search Icon");
        Utility_Functions.timeWait(2);
        sendKeys(CostAdjustmentPage.optBox, "1", "Select Item Number");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        String manufacturerCode = getAttribute(ItemMasterPage.manufacturerCode, "value");
        Utility_Functions.xUpdateJson("ManufacturerCode", manufacturerCode);
        String productCode = getAttribute(ItemMasterPage.productCode, "value");
        Utility_Functions.xUpdateJson("ProductCode", productCode);
        String vendorCode = getAttribute(ItemMasterPage.vendorCode, "value");
        Utility_Functions.xUpdateJson("VendorCode", vendorCode);
    }

    /**
     * This method to Inquire Vendor Information
     */
    public void inquireVendor() {
        sendKeys(OptionsConstantsPage.actionTextBox, "I", "Enter I into Action Text box field");
        sendKeys(PricingMatrixPage.txtBoxAction, Utility_Functions.xGetJsonData("VendorCode"), "Enter Copied(From Item Master) vendor code");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        String val = getAttribute(OptionsConstantsPage.descriptionLine, "value");
        Utility_Functions.xAssertEquals(report, val, "smith", "");
        String vendorNo = getAttribute(OptionsConstantsPage.vendorNo, "value");
        Utility_Functions.xAssertEquals(report, vendorNo, "V" + Utility_Functions.xGetJsonData("VendorNumber"), "");
    }

    /**
     * This method to assign vendor Number for MF/VN code
     */
    public void assignVendorNumber() {
        validateItemMasterTitle();
        getMfPdVn();
        click(SpecialPriceAllowancePage.btnExit);
        commonObj.navigateToOptionsAndConstantsMenu();
        commonObj.navigateToMfVendorCode();
        sendKeys(OptionsConstantsPage.actionTextBox, "C", "Enter C into Action Text box field");
        sendKeys(PricingMatrixPage.txtBoxAction, Utility_Functions.xGetJsonData("VendorCode"), "Enter Copied(From Item Master) vendor code");
        sendKeys(OptionsConstantsPage.descriptionLine, "smith", "Enter Description Line 1");
        click(SpecialPriceAllowancePage.assignedGroups, "Click F2-Vendor Alpha Search");
        sendKeys(WorkWithSalesQuotesPage.sltOpt, "1", "Select Vendor number");
        String VendorNumber = Utility_Functions.getText(driver, OptionsConstantsPage.vendorNumber);
        Utility_Functions.xUpdateJson("VendorNumber", VendorNumber);
        Utility_Functions.actionKey(Keys.ENTER, driver);
        Utility_Functions.timeWait(3);
        Utility_Functions.actionKey(Keys.ENTER, driver);
        inquireVendor();
        click(SpecialPriceAllowancePage.btnExit);
    }

    /**
     * This method validates the title of Vendor Information Page
     */
    public void navigateToVendorInformation() {
        commonObj.masterToPurchaseOrder();
        commonObj.navigateToVendorInf();
        commonObj.validateText(VendorInformationPage.vendorInfPageHeader, "Vendor Information", "Validating Vendor Data page title");
    }

    /**
     * This method validates the title of Buying Discount Group Page
     */
    public void navigateToBuyingDiscountGroup() {
        commonObj.masterToPurchaseOrder();
        commonObj.navigateToOptionAndConstantsMenu();
        commonObj.navigateToBuyingDiscountGroup();
    }

    /**
     * This method validates the title of Discount or Multiplier Page
     */
    public void navigateToDiscountMultiplier() {
        commonObj.masterToPurchaseOrder();
        commonObj.navigateToOptionAndConstantsMenu();
        commonObj.navigateToDiscountMultiplier();
    }

    /**
     * Keyword to Navigate to Suggested PO
     */
    public void navigateToSPO() {
        String url = properties.getProperty("SpoURL");
        driver.get(url);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to login to SPO
     */
    public void winLogin() {
        if (Utility_Functions.xWaitForElementPresent(driver, SpoPage.winLogin, 10)) {
            sendKeys(SpoPage.userName, properties.getProperty("SpoUserName"));
            sendKeys(SpoPage.password, properties.getProperty("SpoPassword"));
            Utility_Functions.timeWait(2);
            click(SpoPage.submit);
            Utility_Functions.timeWait(5);
        }
    }

    /**
     * Keyword to select Company
     */
    public void selectCompany() {
        winLogin();
        if (!Utility_Functions.xIsDisplayed(driver, SpoPage.spoPageTitle)) {
            click(SelfServicePriceSheetPage.companySelector);
            click(SelfServicePriceSheetPage.companyLabel);
            sendKey(SelfServicePriceSheetPage.winCompanyNumber, "99599");
            click(SelfServicePriceSheetPage.selectButton);
            Utility_Functions.timeWait(5);
        }
    }

    /**
     * Keyword to validate SPO UI
     */
    public void verifySpoUI() {
        commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
        String[] actText = {"Worksheet Name\nLast Updated", "Items Below Order Point", "Cost Option", "Total Suggested PO Cost", "Freight Minimum", "Percentage of Target Met", "Order Minimum", "Assigned User"};
        List<WebElement> els = driver.findElements(By.xpath("//th"));

        for (int i = 0; i < 8; i++) {
            Utility_Functions.xAssertEquals(report, els.get(i).getText().trim(), actText[i], "");
            i++;
        }
        commonObj.validateElementExists(SpoPage.helpIcon, "Help Icon '?' is present");
        commonObj.validateElementExists(TruckPage.filterSearch, "Search filter icon is present");
        int size = driver.findElements(SpoPage.pagination).size();
        Utility_Functions.xAssertEquals(report, size, 8, "Next page and previous page arrow icon is present");
        String page = driver.findElement(TruckPage.currentPage).getAttribute("ng-reflect-model");
        commonObj.validateElementExists(TruckPage.currentPage, "Current Page " + page + " " + Utility_Functions.getText(driver, TruckPage.outOf) + "");
        List<WebElement> elm = driver.findElements(TruckPage.show);
        String[] acText = {"10", "15", "30"};
        for (int i = 0; i < 3; i++) {
            Utility_Functions.xAssertEquals(report, elm.get(i).getText().trim(), acText[i], "");
            i++;
        }
        commonObj.validateText(SpoPage.newWorksheetBtn, "New Worksheet", "New Worksheet button is present");
    }

    /**
     * Keyword to Verify functionality of Help (?)icon
     */
    public void helpIconSPO() {
        click(SpoPage.helpIcon, "Click Help Icon");
        Utility_Functions.timeWait(3);
    }

    /**
     * Keyword to Verify Pagination against current page
     */
    public void selectPage(int actPageNo, String expPage, String arrowIcon) {
        click(driver.findElements(DriversPage.pageArrow).get(actPageNo), "Click on " + arrowIcon + " Present at top the Right side of the page");
        String pageNo = driver.findElement(TruckPage.currentPage).getAttribute("ng-reflect-model");
        Utility_Functions.xAssertEquals(report, pageNo, expPage, "Moved to " + pageNo + " Page");
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to Verify functionality of Pagination
     */
    public void paginationSPO() {
        int size = 2;
        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(driver, SpoPage.onePage)) {
            commonObj.validateText(SpoPage.onePage, "of 1", "One page is available");
        } else {
            click(driver.findElements(DriversPage.pageArrow).get(2));
            while (!Utility_Functions.xIsDisplayed(driver, DriversPage.lastPage)) {
                size++;
                click(driver.findElements(DriversPage.pageArrow).get(2));
            }
            click(driver.findElements(DriversPage.pageArrow).get(0));
            selectPage(2, "2", "Right Arrow (>)");
            selectPage(1, "1", "Left Arrow (<)");
            selectPage(3, "" + size + "", "Right double Arrow (>>)");
            selectPage(0, "1", "Left double Arrow (<<)");
        }
    }

    /**
     * Keyword to verify Page Count
     */
    public void valPageCount(int pageNum) {
        click(By.xpath("//span[text()='" + pageNum + "']"), "Click on '" + pageNum + "' Present below the Left corner of the page");
        int truckCount = driver.findElements(SpoPage.worksheetCount).size();
        if (truckCount == pageNum) {
            Utility_Functions.xAssertEquals(report, "" + truckCount + "", "" + pageNum + "", "'" + pageNum + "' is in disable state and showing " + pageNum + " Worksheet Count");
        } else {
            commonObj.validateElementExists(SpoPage.worksheetCount, "Total Worksheet count is " + truckCount + "");
        }
    }

    /**
     * Keyword to Verify functionality of Page Count
     */
    public void funPageCountSPO() {
        if (Utility_Functions.xIsDisplayed(driver, SpoPage.onePage)) {
            int truckCount = driver.findElements(SpoPage.worksheetCount).size();
            commonObj.validateText(DriversPage.onePage, "of 1", "One page is available having Worksheet count " + truckCount + "");
        } else {
            /*int truckCount = driver.findElements(SpoPage.worksheetCount).size();
            Utility_Functions.xAssertEquals(report, "" + truckCount + "", "10", "The Worksheet Count is 10 by default");*/
            valPageCount(10);
            valPageCount(15);
            valPageCount(30);
            valPageCount(30);
            valPageCount(10);
        }
    }

    /**
     * Keyword to Verify UI of Search Filter UI
     */
    public void searchFilterUI() {
        click(TruckPage.filterSearch, "Click Search Filter icon");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//h1"), "Search Filters", "Search Filters panel title is present");
        String[] actText = {"Worksheet Name", "Cost Option", "Percentage of Target Met", "Target Metric", "Assigned User"};
        List<WebElement> els = driver.findElements(TruckPage.searchFiltersLabel);
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
     * Keyword to Verify functionality of refresh icon
     */
    public void verifyRefreshIcon() {
        click(SpoPage.refreshIcon, "Click Refresh Icon");
        String worksheetName = Utility_Functions.xGetJsonData("WorksheetTempName");
        Utility_Functions.timeWait(3);
        commonObj.validateText(SpoPage.popUp, "" + worksheetName + " updated successfully.", "" + worksheetName + " updated successfully. pop up message is present");
        String lastDate = Utility_Functions.getText(driver, SpoPage.lastUpdate);
        String date = specPrAll.getDate(Calendar.DATE, 0);
        if (lastDate.contains(date)) {
            commonObj.validateElementExists(SpoPage.lastUpdate, "Worksheet is updated showing correct date and time: " + lastDate + "");
        }
    }

    /**
     * Keyword to verify worksheet option header
     */
    public void verifyWsOptionHeader() {
        String worksheetName = Utility_Functions.xGetJsonData("WorksheetTempName");
        String manufacturer = Utility_Functions.xGetJsonData("ManufacturerCode").substring(0, 2);
        String productCode = Utility_Functions.xGetJsonData("ProductCode").substring(0, 2);
        String vendorCode = Utility_Functions.xGetJsonData("VendorCode").substring(0, 2);
        commonObj.validateText(SpoPage.worksheetNameHeader, worksheetName + " - " + manufacturer + "" + productCode + "" + vendorCode + "", "" + worksheetName + " - " + manufacturer + "" + productCode + "" + vendorCode + " is present");
        commonObj.validateText(SpoPage.vendorNoHeader, "Vendor: " + Utility_Functions.xGetJsonData("VendorNumber") + " - " + Utility_Functions.xGetJsonData("VendorCode").replace("- ", "") + "", "VENDOR: " + Utility_Functions.xGetJsonData("VendorNumber") + " - " + Utility_Functions.xGetJsonData("VendorCode").replace("-", "") + " is present");
    }

    /**
     * Keyword to click Work sheet name hyperlink
     */
    public void clickWorkSheetName() {
        click(SpoPage.worksheetNameLink, "Click Worksheet name hyperlink");
        String worksheetName = Utility_Functions.xGetJsonData("WorksheetTempName");
        Utility_Functions.timeWait(4);
        if (Utility_Functions.getText(driver, SpoPage.popUp).equals("")) {
            click(SpoPage.worksheetNameLink, "Click Worksheet name hyperlink");
            Utility_Functions.timeWait(4);
        }
        commonObj.validateText(SpoPage.popUp, "" + worksheetName + " updated successfully.", "" + worksheetName + " updated successfully. pop up message is present");
        Utility_Functions.timeWait(4);
        verifyWsOptionHeader();
    }

    /**
     * Keyword to Verify functionality of Trash icon
     */
    public void verifyTrashIcon() {
        clickWorkSheetName();
        click(SpoPage.closeIcon, "Click Close Icon");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SpoPage.savedTag, "Saved", "Worksheet status turned to Saved tag");
        click(SpoPage.trashIcon, "Click Trash Icon");
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(SpoPage.deleteSavedWorksheetPopup, "Delete Saved Worksheet Popup message present");
        clickButtonWithSize("No");
        commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
        click(SpoPage.trashIcon, "Click Trash Icon");
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(SpoPage.deleteSavedWorksheetPopup, "Delete Saved Worksheet Popup message present");
        clickButtonWithSize("Yes");
        Utility_Functions.timeWait(3);
        String worksheetName = Utility_Functions.xGetJsonData("WorksheetTempName");
        commonObj.validateText(SpoPage.popUp, "" + worksheetName + " worksheet successfully deleted.", "" + worksheetName + " worksheet successfully deleted. pop up message is present");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
        Utility_Functions.xAssertEquals(report, Utility_Functions.xIsDisplayed(driver, SpoPage.savedTag), false, "Saved Worksheet is deleted and not present on SPO page");
    }

    public By worksheetName(String tag) {
        return By.xpath("//span[contains(text(),'" + tag + "')]/ancestor::td/descendant::a");
    }

    /**
     * Keyword to click a button
     * if Multiple index found and target element must be last one
     */
    public void clickButtonWithSize(String button) {
        int size = driver.findElements(By.xpath("//button[text()='" + button + "']")).size() - 1;
        click(driver.findElements(By.xpath("//button[text()='" + button + "']")).get(size), "Click " + button + " button");
        Utility_Functions.timeWait(2);
    }

    public void discountError(){
        if (Utility_Functions.xIsDisplayed(driver, SpoPage.isInvalidDisc)) {
            commonObj.validateText(SpoPage.discountError, "Discount cannot be 0 when using List Price.", "Discount cannot be 0 when using List Price. error message is present");
            sendKeys(SpoPage.isInvalidDisc, "1", "Enter Discount");
            clickButton("Refresh Worksheet");
            Utility_Functions.timeWait(2);
        }
    }

    /**
     * Keyword to Functionality of refresh button
     */
    public void functionalRefreshButton() {
        clickButton("Refresh Worksheet");
        discountError();
        Utility_Functions.timeWait(4);
        if (!Utility_Functions.xIsDisplayed(driver, SpoPage.exclamationWarn)) {
            sendKeys(SpoPage.leadTime, "20", "Lead time 10 is present already once again Modify lead time field");
            clickButton("Refresh Worksheet");
            Utility_Functions.timeWait(4);
            discountError();
        }
        commonObj.validateElementExists(SpoPage.exclamationWarn, "ARE YOU SURE? popup is present");
        Utility_Functions.timeWait(4);
        clickButton("No");
        clickButton("Refresh Worksheet");
        Utility_Functions.timeWait(2);
        discountError();
        commonObj.validateElementExists(SpoPage.exclamationWarn, "ARE YOU SURE? popup is present");
        Utility_Functions.timeWait(4);
        clickButton("Yes");
        Utility_Functions.timeWait(4);
        commonObj.validateText(SpoPage.popUp, "Worksheet updated successfully.", "Worksheet updated successfully. popup is present");
        Utility_Functions.timeWait(4);
    }

    /**
     * Keyword to verify Start New Worksheet Button
     * On Saved worksheet Available popup
     */
    public void verifyStartNewWorkSheet() {
        click(worksheetName("Last Updated:"), "Click Worksheet hyper link");
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(SpoPage.exclamationWarn, "Open Saved Worksheet popup is present");
        clickButton("Start New Worksheet");
        Utility_Functions.timeWait(8);
        verifyWsOptionHeader();
    }

    /**
     * Keyword to verify functionality of Cancel button
     * On Saved worksheet Available popup
     */
    public void verifyCancelSavedWSPopup() {
        click(worksheetName("Last Updated:"), "Click Worksheet hyper link");
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(SpoPage.exclamationWarn, "Open Saved Worksheet popup is present");
        clickButtonWithSize("Cancel");
        commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
    }

    /**
     * Keyword to verify functionality of Open Saved Worksheet button
     * On Saved worksheet Available popup
     */
    public void verifyOpenNewWSBtn() {
        Utility_Functions.timeWait(3);
        click(worksheetName("Last Updated:"), "Click Worksheet hyper link");
        Utility_Functions.timeWait(4);
        clickButton("Open Saved Worksheet");
        Utility_Functions.timeWait(10);
        verifyWsOptionHeader();
    }

    public void modifyLeadTimeField() {
        sendKeys(SpoPage.leadTime, "10", "Modify lead time field");
        functionalRefreshButton();
        click(SpoPage.saveWorksheetBtn, "Click Save Worksheet button");
        Utility_Functions.timeWait(2);
        Utility_Functions.xScrollIntoView(driver, SpoPage.closeIcon);
        Utility_Functions.timeWait(2);
        Utility_Functions.xMouseClick(driver, SpoPage.closeIcon);
        //click(SpoPage.closeIcon, "Click Close Icon");
        Utility_Functions.timeWait(4);
        commonObj.validateText(SpoPage.savedTag, "Saved", "Worksheet status turned to Saved tag");
    }

    /**
     * Keyword to verify saved work sheet
     */
    public void verifySavedWorkSheet() {
        if (!Utility_Functions.xIsDisplayed(driver, SpoPage.trashIcon)) {
            clickWorkSheetName();
            click(SpoPage.closeIcon, "Click Close Icon");
            Utility_Functions.timeWait(2);
            commonObj.validateText(SpoPage.savedTag, "Saved", "Worksheet status turned to Saved tag");
        }
        verifyCancelSavedWSPopup();
        verifyOpenNewWSBtn();
        modifyLeadTimeField();
        verifyOpenNewWSBtn();
        String leadTimeAct = getAttribute(SpoPage.leadTime, "ng-reflect-model");
        if (leadTimeAct.equals("10")) {
            Utility_Functions.xAssertEquals(report, leadTimeAct, "10", "Modified lead time is present");
        } else {
            Utility_Functions.xAssertEquals(report, leadTimeAct, "15", "Modified lead time is present");
        }
        click(SpoPage.closeIcon, "Click Close Icon");
        Utility_Functions.timeWait(4);
        verifyStartNewWorkSheet();
        modifyLeadTimeField();
        /*verifyStartNewWorkSheet();
        String leadTimeAct1=getAttribute(SpoPage.leadTime,"ng-reflect-model");
        Utility_Functions.xAssertEquals(report,leadTimeAct1,"10","Modified lead time is present");
        click(SpoPage.closeIcon, "Click Close Icon");
        Utility_Functions.timeWait(4);*/
    }

    /**
     * Keyword to navigate to Create WorkSheet
     */
    public void navigateToCreateWorksheet() {
        click(SpoPage.newWorksheetBtn, "Click New Worksheet Button");
        Utility_Functions.timeWait(3);
        commonObj.validateText(By.xpath("//h2"), "CREATE NEW WORKSHEET TEMPLATE", "Validating  CREATE NEW WORKSHEET TEMPLATE page title");
    }

    public By labels(String label) {
        return By.xpath("//label[contains(text(),'" + label + "')]");
    }


    /**
     * Keyword to validate Time Of the Day/night Fields
     */
    public void validateTimeOfDayNightDropDown(String field, int optionCount) {
        int size = driver.findElements(By.xpath("//div[@class='col-3']//select[@id='" + field + "1']/option")).size();
        Utility_Functions.xAssertEquals(report, size, optionCount, "Total " + size + " count on " + field + "");
        if (field.equals("minute")) {
            String[] min = {"00", "15", "30", "45"};
            List<WebElement> el = driver.findElements(By.xpath("//div[@class='col-3']//select[@id='" + field + "1']/option"));
            int i = 0;
            for (WebElement elm : el) {
                Utility_Functions.xAssertEquals(report, elm.getText(), min[i], "");
                i++;
            }
        } else if (field.equals("Meridian")) {
            String[] mer = {"AM", "PM"};
            List<WebElement> el = driver.findElements(By.xpath("//div[@class='col-3']//select[@id='" + field + "1']/option"));
            int i = 0;
            for (WebElement elm : el) {
                Utility_Functions.xAssertEquals(report, elm.getText(), mer[i], "");
                i++;
            }
        }
    }

    /**
     * Keyword to validate Time Of the Day/night Fields
     */
    public void validateTimeOfDayNight() {
        commonObj.validateElementExists(labels("Time of the day/night"), "Time of the day/night field exist");
        commonObj.validateElementExists(SpoPage.hourDropDown, "Hour drop down box exist");
        validateTimeOfDayNightDropDown("hour", 12);
        commonObj.validateElementExists(SpoPage.minuteDropDown, "Minute drop down box exist");
        validateTimeOfDayNightDropDown("minute", 4);
        commonObj.validateElementExists(SpoPage.meridianDropDown, "Meridian drop down box exist");
        validateTimeOfDayNightDropDown("meridian", 2);
        commonObj.validateElementExists(labels("Run each week"), "Run each week radio button exist");
        commonObj.validateElementExists(labels("Run every other week"), "Run every other week radio button exist");
    }

    /**
     * Keyword to Verify UI of Search Filter UI
     */
    public void createNewWorksheetTemplateUI() {
        String[] field = {"Name Your Worksheet", "Assigned User", "Manufacturer Code", "Product Code", "Vendor Code", "Vendor Number", "Months Supply to Order", "Multiplier", "Lead Time (Days)", ""};
        int i = 0;
        while (Utility_Functions.xIsDisplayed(driver, By.xpath("//label[text()='" + field[i] + "']/parent::div/descendant::input"))) {
            commonObj.validateElementExists(By.xpath("//label[text()='" + field[i] + "']/parent::div/descendant::input"), "" + field[i] + " text box field present");
            i++;
        }
        commonObj.validateElementExists(SpoPage.costOption, "Cost Option drop down present");
        String[] option = {"List Price", "PO Cost", "Last Cost", "WSS Cost"};
        for (int j = 0; j < 4; j++) {
            Utility_Functions.xAssertEquals(report, driver.findElements(SpoPage.costOptions).get(j).getText(), option[j], "" + option[j] + " is present");
        }
        commonObj.validateElementExists(SpoPage.trailingMonths, "Trailing Month section present");
        validateTimeOfDayNight();
        commonObj.validateElementExists(SchedulePaymentPage.cancelButton, "Cancel button present");
        commonObj.validateElementExists(SpoPage.disFindProduct, "Find Product button present and button is disabled");
    }

    public By worksheetInputFields(String label) {
        return By.xpath("//label[text()='" + label + "']/parent::div/descendant::input");
    }

    /**
     * Keyword to select days
     */
    public void selectDays() {
        Utility_Functions.xScrollIntoView(driver, SpoPage.dayOfTheWeek);
        click(SpoPage.dayOfTheWeek, "Click Day Of The Week Drop Down");
        for (int i = 1; i < 4; i++) {
            click(driver.findElements(SpoPage.dayCheckBox).get(i), "Select Day");
        }
        click(SpoPage.trailingMonths);
        Utility_Functions.timeWait(2);
        commonObj.validateText(SpoPage.selectedDays, "Monday, Tuesday, Wednesday", "Multiple days selected");
    }

    /**
     * Keyword to click button
     */
    public void clickButton(String buttonName) {
        click(By.xpath("//button[contains(text(),'" + buttonName + "')]"), "Click " + buttonName + " button");
    }

    /**
     * Keyword to verify functionality create Worksheet Button
     */
    public void createWorksheetButtonFunctionality() {
        Utility_Functions.timeWait(4);
        clickButton("Create Worksheet");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SpoPage.popUp, Utility_Functions.xGetJsonData("WorksheetTempName") + " worksheet successfully created.", Utility_Functions.xGetJsonData("WorksheetTempName") + " worksheet successfully created. pop message is present");
    }

    /**
     * Keyword to Create WorkSheet
     */
    public void createWorksheet() {
        navigateToCreateWorksheet();
        String name = Utility_Functions.xGenerateAlphaNumericString();
        Utility_Functions.xUpdateJson("WorksheetTempName", name);
        sendKeys(SpoPage.worksheetSheetTemplateName, name, "Name Your Worksheet");
        sendKeys(worksheetInputFields("Vendor Code"), Utility_Functions.xGetJsonData("VendorCode"), "Enter Vendor Code");
        sendKeysAndTab(worksheetInputFields("Vendor Number"), Utility_Functions.xGetJsonData("VendorNumber"), "Enter Vendor Number");
        sendKeys(worksheetInputFields("Manufacturer Code"), Utility_Functions.xGetJsonData("ManufacturerCode"), "Enter Manufacturer code");
        String vend = driver.findElement(worksheetInputFields("Vendor Number")).getAttribute("value");
        Utility_Functions.xUpdateJson("VendorNoForHeader", vend);
        sendKeys(worksheetInputFields("Product Code"), Utility_Functions.xGetJsonData("ProductCode"), "Enter Product Code");
        selectDays();
        Utility_Functions.timeWait(2);
        clickButton("Find Products");
        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(driver, SpoPage.isInvalidDisc)) {
            commonObj.validateText(SpoPage.discountError, "Discount cannot be 0 when using List Price.", "Discount cannot be 0 when using List Price. error message is present");
            sendKeys(SpoPage.isInvalidDisc, "1", "Enter Discount");
            clickButton("Find Products");
        }
        createWorksheetButtonFunctionality();
    }

    /**
     * Keyword to Filter Created worksheet
     */
    public void filterCreatedWorksheet() {
        Utility_Functions.timeWait(4);
        click(TruckPage.filterSearch, "Click Search Filter icon");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//h1"), "Search Filters", "Search Filters panel title is present");
        sendKeys(SpoPage.worksheetNameFilter, Utility_Functions.xGetJsonData("WorksheetTempName"), "Enter created worksheet");
        click(TruckPage.applyFilter, "Click Apply Filters");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SpoPage.filteredWorksheet, Utility_Functions.xGetJsonData("WorksheetTempName"), "Created Work sheet present");
    }

    /**
     * This method to choose Multiplier
     */
    public void chooseMultiplierOpt() {
        sendKeys(pages.PurchaseOrders.OptionsConstantsPage.discMultOpt, "1", "Enter 1 into field");
        Utility_Functions.actionKey(Keys.ENTER, driver);
    }

    /**
     * This method to choose Discount
     */
    public void chooseDiscountOpt() {
        clearText(pages.PurchaseOrders.OptionsConstantsPage.discMultOpt);
        Utility_Functions.actionKey(Keys.ENTER, driver);
    }

    /**
     * This method validates Multiplier availability
     */
    public void verifyMultiAvailability() {
        commonObj.validateText(SpoPage.multiplier, "Multiplier:", "Multiplier is present below the Cost Option Column");
    }

    /**
     * This method validates Discount availability
     */
    public void verifyDiscountAvailability() {
        commonObj.validateText(SpoPage.discount, "Discount:", "Discount is present below the Cost Option Column");
    }
}