package businesskeywords.Purchasing.SPO;

import businesskeywords.Pricing.SPA.SpecialPricingAllowance;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Settings;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.AccountReceivable.makePayments.SchedulePaymentPage;
import pages.Purchasing.MailingMasterSearchPage;
import pages.Purchasing.SPO.SpoPage;
import pages.Purchasing.VendorInformationPage;
import pages.common.MasterPage;
import pages.inventory.CostAdjustmentPage;
import pages.inventory.ItemLedgerPage;
import pages.inventory.ItemMasterPage;
import pages.inventory.OptionsConstantsPage;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
import pages.pricing.pricingmatrix.PricingMatrixPage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import pages.warehouse.BinMaintenance.BinMaintenancePage;
import pages.warehouse.DriversPage;
import pages.warehouse.ManifestsPage;
import pages.warehouse.TruckPage;
import supportLibraries.Utility_Functions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.List;
import java.util.*;

public class Spo extends ReusableLib {
    CommonActions commonObj;
    SpecialPricingAllowance specPrAll;
    public static Properties properties = Settings.getInstance();
    private FrameworkDriver ownDriver;
    int i = 0;

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
        ownDriver = helper.getGSDriver();
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
        Utility_Functions.xUpdateJson("ItemNoMaster", Utility_Functions.getText(ownDriver, SpoPage.itemNo));
        sendKeys(SpoPage.inputBox, "1", "Select Item Number");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        String manufacturerCode = getAttribute(ItemMasterPage.manufacturerCode, "value");
        Utility_Functions.xUpdateJson("ManufacturerCode", manufacturerCode);
        String productCode = getAttribute(ItemMasterPage.productCode, "value");
        Utility_Functions.xUpdateJson("ProductCode", productCode);
        String vendorCode = getAttribute(ItemMasterPage.vendorCode, "value");
        Utility_Functions.xUpdateJson("VendorCode", vendorCode);
        String poCost = getAttribute(ItemMasterPage.txtBoxPoCost, "value").trim();
        Utility_Functions.xUpdateJson("POCost", poCost);
    }

    /**
     * This method to Inquire Vendor Information
     */
    public void inquireVendor() {
        sendKeys(OptionsConstantsPage.actionTextBox, "I", "Enter I into Action Text box field");
        sendKeys(PricingMatrixPage.txtBoxAction, Utility_Functions.xGetJsonData("VendorCode"), "Enter Copied(From Item Master) vendor code");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        String val = getAttribute(OptionsConstantsPage.descriptionLine, "value");
        Utility_Functions.xAssertEquals(report, val, "SMITH", "");
        String vendorNo = getAttribute(OptionsConstantsPage.vendorNo, "value");
        Utility_Functions.xAssertEquals(report, vendorNo, "V" + Utility_Functions.xGetJsonData("VendorNumber"), "");
    }

    public void backToMaster() {
        Utility_Functions.waitForElementVisible(ownDriver, SpoPage.backBtn, 10);
        click(SpoPage.backBtn);
        click(SpoPage.backBtn, "Navigate back to master");
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
        sendKeys(MailingMasterSearchPage.firstCustomerSelect, "1", "Select Vendor number");
        String VendorNumber = Utility_Functions.getText(ownDriver, OptionsConstantsPage.vendorNumber);
        Utility_Functions.xUpdateJson("VendorNumber", VendorNumber);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.timeWait(3);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
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
     * This method to navigate to Worksheet option Page
     */
    public void navigateWSOption() throws AWTException {
        verifyTrashIconPresence();
        click(worksheetName("Saved"), "Click Worksheet hyper link");
        Utility_Functions.timeWait(6);
        verifyWsOptionHeader();
    }

    /**
     * This method to navigate to Edit Worksheet Template page
     */
    public void navigateEditWSTemp() {
        click(SpoPage.editWsTempBtn, "Click Edit Worksheet template Button");
        Utility_Functions.timeWait(3);
        commonObj.validateText(By.xpath("//h2"), "EDIT WORKSHEET TEMPLATE", "Validating EDIT WORKSHEET TEMPLATE page title");
    }

    /**
     * This method validates the title of Buying Discount Group Page
     */
    public void navigateToBuyingDiscountGroup() {
        commonObj.masterToPurchaseOrder();
        commonObj.navigateToOptionAndConstantsMenu();
        commonObj.navigateToBuyingDiscountGroup();
    }

    public void navigateWSItem() {
        clickButton("Next");
    }

    /**
     * This method validates the title of Discount or Multiplier Page
     */
    public void navigateToDiscountMultiplier() {
        commonObj.masterToPurchaseOrder();
        commonObj.navigateToOptionAndConstantsMenu();
        commonObj.navigateToDiscountMultiplier();
    }

    public void getPassword() {
        String username = "vprasad";
        String password = "6L@ss&9zg0NG";
        String URL = "https://" + username + ":" + password + "@daily.winwholesale.com/";
        ownDriver.get(URL);
        click(ownDriver.findElement(By.xpath("//a[text()='QA ']")));
        Utility_Functions.timeWait(4);
        System.out.println(getText(By.xpath("//h2")));
        Utility_Functions.xUpdateJson("spoPass", getText(By.xpath("//h2")));
    }

    /**
     * Keyword to Navigate to Suggested PO
     */
    public void navigateToSPO() {
        String url = properties.getProperty("SpoURL");
        ownDriver.get(url);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to login to SPO
     */
    public void winLoginSPO() {
        if (Utility_Functions.xWaitForElementPresent(ownDriver, SpoPage.winLogin, 10)) {
            sendKeys(SpoPage.userName, properties.getProperty("SpoUserName"));
            sendKeys(SpoPage.password, Utility_Functions.xGetJsonData("spoPass"));
            Utility_Functions.waitTillClickHardSleep(report, ownDriver, SpoPage.submit, "");
        }
    }

    /**
     * Keyword to select Company
     */
    public void selectCompany() {
        winLoginSPO();
        if (isDisplayed(BinMaintenancePage.toasterCloseIcon)) {
            click(BinMaintenancePage.toasterCloseIcon);
        }
        if (isDisplayed(ManifestsPage.closePopUp)) {
            Utility_Functions.timeWait(6);
            click(ManifestsPage.closePopUp);
        }
        if (!Utility_Functions.xIsDisplayed(ownDriver, SpoPage.companyName)) {
            if (Utility_Functions.xIsDisplayed(ownDriver, SelfServicePriceSheetPage.companySelector)) {
                Utility_Functions.waitTillClickHardSleep(report, ownDriver, SelfServicePriceSheetPage.companySelector, "");
                click(SelfServicePriceSheetPage.companyLabel);
                if (System.getProperty("company") == null) {
                    sendKey(SelfServicePriceSheetPage.winCompanyNumber, "99599");
                } else {
                    sendKey(SelfServicePriceSheetPage.winCompanyNumber, System.getProperty("company"));
                }
                click(SelfServicePriceSheetPage.selectButton);
            }
        }
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, By.xpath("//h2"), "");
    }

    /**
     * Keyword to validate SPO UI
     */
    public void verifySpoUI() {
        commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
        String[] actText = {"Worksheet Name\nLast Updated", "Items Below Order Point", "Cost Option", "Total Suggested PO Cost", "Freight Minimum", "Percentage of Target Met", "Order Minimum", "Assigned User"};
        List<WebElement> els = ownDriver.findElements(By.xpath("//th"));
        for (int i = 0; i < 8; i++) {
            Utility_Functions.xAssertEquals(report, els.get(i).getText().trim(), actText[i], "");
            i++;
        }
        commonObj.validateElementExists(SpoPage.helpIcon, "Help Icon '?' is present");
        commonObj.validateElementExists(TruckPage.filterSearch, "Search filter icon is present");
        Utility_Functions.timeWait(4);
        if (!isDisplayed(SpoPage.noResultFound)) {
            int size = ownDriver.findElements(SpoPage.pagination).size();
            Utility_Functions.xAssertEquals(report, size, 8, "Next page and previous page arrow icon is present");
            String page = ownDriver.findElement(TruckPage.currentPage).getAttribute("ng-reflect-model");
            commonObj.validateElementExists(TruckPage.currentPage, "Current Page " + page + " " + Utility_Functions.getText(ownDriver, TruckPage.outOf) + "");
            List<WebElement> elm = ownDriver.findElements(TruckPage.show);
            String[] acText = {"10", "15", "30"};
            for (int i = 0; i < 3; i++) {
                Utility_Functions.xAssertEquals(report, elm.get(i).getText().trim(), acText[i], "");
                i++;
            }
        }
        commonObj.validateText(SpoPage.newWorksheetBtn, "New template", "New template button is present");
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
        click(ownDriver.findElements(DriversPage.pageArrow).get(actPageNo), "Click on " + arrowIcon + " Present at top the Right side of the page");
        String pageNo = ownDriver.findElement(TruckPage.currentPage).getAttribute("ng-reflect-model");
        Utility_Functions.xAssertEquals(report, pageNo, expPage, "Moved to " + pageNo + " Page");
        Utility_Functions.timeWait(2);
    }

    public int pageCount() {
        int size = 2;
        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(ownDriver, SpoPage.onePage)) {
            commonObj.validateText(SpoPage.onePage, "of 1", "One page is available");
        } else {
            click(ownDriver.findElements(DriversPage.pageArrow).get(2));
            while (!Utility_Functions.xIsDisplayed(ownDriver, DriversPage.lastPage)) {
                size++;
                click(ownDriver.findElements(DriversPage.pageArrow).get(2));
            }
            click(ownDriver.findElements(DriversPage.pageArrow).get(0));
        }
        return size;
    }

    /**
     * Keyword to Verify functionality of Pagination
     */
    public void paginationSPO() {
        int size = pageCount();
        selectPage(2, "2", "Right Arrow (>)");
        selectPage(1, "1", "Left Arrow (<)");
        selectPage(3, "" + size + "", "Right double Arrow (>>)");
        selectPage(0, "1", "Left double Arrow (<<)");
    }

    public int pageCount2() {
        int size = 2;
        Utility_Functions.timeWait(2);
        if (isDisplayed(SpoPage.currentPage)) {
            commonObj.validateText(SpoPage.currentPage, "of 1", "One page is available");
        } else {
            click(ownDriver.findElements(SpoPage.pageArrow).get(2));
            while (!Utility_Functions.xIsDisplayed(ownDriver, SpoPage.lastPage)) {
                size++;
                click(ownDriver.findElements(SpoPage.pageArrow).get(2));
            }
            click(ownDriver.findElements(SpoPage.pageArrow).get(0));
        }
        return size;
    }

    public void paginationSpo2() {
        int size = pageCount2();
        selectPage2(2, "2", "Right Arrow (>)");
        selectPage2(1, "1", "Left Arrow (<)");
        selectPage2(3, "" + size + "", "Right double Arrow (>>)");
        selectPage2(0, "1", "Left double Arrow (<<)");
    }

    public void selectPage2(int actPageNo, String expPage, String arrowIcon) {
        click(ownDriver.findElements(SpoPage.pagntn).get(actPageNo), "Click on " + arrowIcon + " Present at top the Right side of the page");
        Utility_Functions.timeWait(2);
        String pageNo = ownDriver.findElement(SpoPage.pageCount).getAttribute("ng-reflect-model");
        Utility_Functions.xAssertEquals(report, pageNo, expPage, "Moved to " + pageNo + " Page");
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to verify Page Count
     */
    public void valPageCount(int pageNum) {
        Utility_Functions.xClickHiddenElement(ownDriver, By.xpath("//span[text()='" + pageNum + "']"));
        int truckCount = ownDriver.findElements(SpoPage.itemNoCount).size();
        if (truckCount == 0) {
            truckCount = ownDriver.findElements(By.xpath("//td[@ng-reflect-ng-class='item-color']")).size();
        }
        if (truckCount == pageNum) {
            Utility_Functions.xAssertEquals(report, "" + truckCount + "", "" + pageNum + "", "'" + pageNum + "' is in disable state and showing " + pageNum + " Worksheet Count");
        } else {
            commonObj.validateElementExists(By.xpath("//*[text()='']"), "Total Worksheet count is " + truckCount + "");
        }
    }

    /**
     * Keyword to Verify functionality of Page Count
     */
    public void funPageCountSPO() {
        if (Utility_Functions.xIsDisplayed(ownDriver, SpoPage.onePage)) {
            int truckCount = ownDriver.findElements(SpoPage.worksheetCount).size();
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
        for (int i = 0; i < 2; i++) {
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_MINUS);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_MINUS);
                Utility_Functions.timeWait(2);
            } catch (Exception e1) {
            }
        }
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, TruckPage.filterSearch, "Click Search Filter icon");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//h1"), "Search Filters", "Search Filters panel title is present");
        String[] actText = {"Worksheet Name", "Cost Option", "Percentage of Target Met", "Target Metric", "Assigned User"};
        List<WebElement> els = ownDriver.findElements(TruckPage.searchFiltersLabel);
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
        commonObj.validateText(SpoPage.popUp, "worksheet refreshed successfully.", "[worksheet refreshed successfully.]. pop up message is present");
        String lastDate = Utility_Functions.getText(ownDriver, SpoPage.lastUpdate);
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
        String combination = worksheetName + " - " + manufacturer + " " + productCode + " " + vendorCode + "";
        commonObj.validateText(By.xpath("//h2[contains(text(),'" + combination + "')]"), combination, combination + " is present");
        Utility_Functions.timeWait(4);
        try {
            commonObj.validateText(SpoPage.vendorNoHeader, "Vendor: 000388 - automation test vendor", "VENDOR: 000388 - automation test vendor is present");
        } catch (Exception e) {
            refreshPage();
            Utility_Functions.timeWait(4);
            commonObj.validateText(SpoPage.vendorNoHeader, "Vendor: 000388 - automation test vendor", "VENDOR: 000388 - automation test vendor is present");
        }
    }

    public void isWSActive() {
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.toggleSlider);
        if (Utility_Functions.xIsDisplayed(ownDriver, SpoPage.activateList)) {
            click(SpoPage.toggleSlider, "Activate Worksheet");
            updatedMessage();
            clickButton("Cancel");
            Utility_Functions.timeWait(6);
            commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
        }
    }

    /**
     * Keyword to verify WS expand and contract functionality
     */
    public void verifyExpandContract() {
        click(SpoPage.btnMinus, "Click on minus button to contract the section");
        commonObj.validateElementExists(SpoPage.btnPlus, "Search filters and worksheet calculation section is compressed");
        click(SpoPage.btnPlus, "Click on plus button to expand the section");
        commonObj.validateElementExists(SpoPage.btnMinus, "Search filters and worksheet calculation section is expanded");

    }

    /**
     * Keyword to validate refresh functionality
     */
    public void verifyRefreshFunction() {
        for (int i = 0; i < 2; i++) {
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_MINUS);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_MINUS);
                Utility_Functions.timeWait(2);
            } catch (Exception e1) {
            }
        }
        String cost = jsonData.getData("CostOption");
        int size = ownDriver.findElements(SpoPage.costOption).size() - 1;
        click(ownDriver.findElements(SpoPage.costOption).get(size), "Click Cost Option Drop Down");
        Utility_Functions.timeWait(2);
        click(By.xpath("//option[contains(text(),'List Price')]"), "Select List Price option");
        Utility_Functions.timeWait(2);
        if (isDisplayed(SpoPage.multiplierField))
            sendKeys(SpoPage.multiplierField, "10", "Enter Multiplier");
        else
            sendKeys(SpoPage.discountOrMultiplier, "10", "Enter discount");
        click(SpoPage.btnRefreshWorksheet, "Click on refresh worksheet button");
        Utility_Functions.timeWait(2);
        if (waitForElementDisappear(SpoPage.pageBlocker, 10)) {
            click(SpoPage.btnConfirm, "Click on yes button");
        }
        commonObj.validateElementExists(SpoPage.popUp, "Refresh successful message is displayed");
    }

    /**
     * Keyword to validate expand collapse function
     */
    public void validateExpandCollapse() {
        for (int i = 0; i < 3; i++) {
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_MINUS);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_MINUS);
                Utility_Functions.timeWait(2);
            } catch (Exception e1) {
            }
        }
        click(SpoPage.btnExpand, "Click on expand all button");
        commonObj.validateElementExists(SpoPage.itemExpanded, "Item section is expanded");
        click(SpoPage.btnCollapse, "Click on collapse all button");
        if (!xWaitForElementPresent(ownDriver, SpoPage.itemExpanded, 2)) {
            report.updateTestLog("Validate collapse", "The items section is collapsed", Status.PASS);
        } else {
            report.updateTestLog("Validate collapse", "The items section is expanded", Status.FAIL);
        }
    }

    /**
     * Keyword to validate disable all fields
     */
    public void validateDisableFields() {
        for (int i = 0; i < 3; i++) {
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_MINUS);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_MINUS);
                Utility_Functions.timeWait(2);
            } catch (Exception e1) {
            }
        }
        click(SpoPage.disableFieldsChkbox, "Uncheck disable fields checkbox");
        click(SpoPage.btnExpand, "Click item expand button");
        Utility_Functions.timeWait(2);
        if (waitForElementDisappear(SpoPage.pageBlocker, 10)) {
            if (ownDriver.findElement(SpoPage.vendorPartNo).isEnabled() && ownDriver.findElement(SpoPage.unitOfMeasure).isEnabled() && ownDriver.findElement(SpoPage.conversionFactor).isEnabled()) {
                report.updateTestLog("Validate fields are enabled", "The item fields are enabled", Status.PASS);
            } else {
                report.updateTestLog("Validate fields are enabled", "The item fields are disabled", Status.FAIL);
            }
        }
    }

    /**
     * Keyword to click Work sheet name hyperlink
     */
    public void clickWorkSheetName() throws AWTException {
        click(SpoPage.worksheetNameLink, "Click Worksheet name hyperlink");
        String worksheetName = Utility_Functions.xGetJsonData("WorksheetTempName");
        Utility_Functions.timeWait(4);
        if (Utility_Functions.xIsDisplayed(ownDriver, SpoPage.popUp)) {
            if (Utility_Functions.getText(ownDriver, SpoPage.popUp).equals("")) {
                click(SpoPage.worksheetNameLink, "Click Worksheet name hyperlink");
                Utility_Functions.timeWait(4);
            }
            commonObj.validateText(SpoPage.popUp, "worksheet refreshed successfully.", "worksheet refreshed successfully. pop up message is present");
            Utility_Functions.timeWait(4);
            verifyWsOptionHeader();
        } else {
            Utility_Functions.timeWait(4);
            isWSActive();
            filterCreatedWorksheet();
            click(SpoPage.worksheetNameLink, "Click Worksheet name hyperlink");
            Utility_Functions.timeWait(4);
        }
    }

    /**
     * Keyword to Verify functionality of Trash icon
     */
    public void verifyTrashIcon() throws AWTException {
        if (!Utility_Functions.xIsDisplayed(ownDriver, SpoPage.trashIcon)) {
            clickWorkSheetName();
            click(SpoPage.closeIcon, "Click Close Icon");
            Utility_Functions.timeWait(2);
            commonObj.validateText(SpoPage.savedTag, "Saved", "Worksheet status turned to Saved tag");
        }
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
        Utility_Functions.xAssertEquals(report, Utility_Functions.xIsDisplayed(ownDriver, SpoPage.savedTag), false, "Saved Worksheet is deleted and not present on SPO page");
    }

    public By worksheetName(String tag) {
        return By.xpath("//span[contains(text(),'" + tag + "')]/ancestor::td/descendant::a");
    }

    /**
     * Keyword to click a button
     * if Multiple index found and target element must be last one
     */
    public void clickButtonWithSize(String button) {
        int size = ownDriver.findElements(By.xpath("//button[text()='" + button + "']")).size() - 1;
        click(ownDriver.findElements(By.xpath("//button[text()='" + button + "']")).get(size), "Click " + button + " button");
        Utility_Functions.timeWait(2);
    }

    public void discountError() {
        if (Utility_Functions.xIsDisplayed(ownDriver, SpoPage.isInvalidDisc)) {
            Utility_Functions.xScrollIntoView(ownDriver, SpoPage.discountError);
            commonObj.validateText(SpoPage.discountError, "Discount cannot be 0 when using List Price.", "Discount cannot be 0 when using List Price. error message is present");
            Utility_Functions.timeWait(2);
            Utility_Functions.xScrollIntoView(ownDriver, SpoPage.closeIcon);
            sendKeys(SpoPage.isInvalidDisc, "-1", "Enter negative value into Discount Text box");
            if (Utility_Functions.xIsDisplayed(ownDriver, button("Refresh Worksheet"))) {
                clickButton("Refresh Worksheet");
            }
            Utility_Functions.timeWait(2);
        }
    }

    public void yesButton() {
        commonObj.validateElementExists(SpoPage.exclamationWarn, "ARE YOU SURE? popup is present");
        Utility_Functions.timeWait(4);
        clickButton("Yes");
        Utility_Functions.timeWait(4);
        commonObj.validateText(SpoPage.popUp, "worksheet refreshed successfully.", "worksheet refreshed successfully. popup is present");
        Utility_Functions.timeWait(6);
    }

    public void noButton() {
        commonObj.validateElementExists(SpoPage.exclamationWarn, "ARE YOU SURE? popup is present");
        Utility_Functions.timeWait(4);
        clickButton("No");
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to Functionality of refresh button
     */
    public void functionalRefreshButton() {
        try {
            clickButton("Refresh Worksheet");
        } catch (Exception e) {
            for (int i = 0; i < 2; i++) {
                try {
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_MINUS);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.keyRelease(KeyEvent.VK_MINUS);
                } catch (Exception e1) {
                }
            }
            clickButton("Refresh Worksheet");
        }
        discountError();
        Utility_Functions.timeWait(4);
        if (!Utility_Functions.xIsDisplayed(ownDriver, SpoPage.exclamationWarn)) {
            sendKeys(SpoPage.leadTime, "20", "Lead time 10 is present already once again Modify lead time field");
            clickButton("Refresh Worksheet");
            Utility_Functions.timeWait(4);
            discountError();
        }
        noButton();
        clickButton("Refresh Worksheet");
        Utility_Functions.timeWait(2);
        discountError();
        yesButton();
    }

    /**
     * Keyword to verify Start New Worksheet Button
     * On Saved worksheet Available popup
     */
    public void verifyStartNewWorkSheet() {
        clickLastUpdateWS();
        commonObj.validateElementExists(SpoPage.exclamationWarn, "Open Saved Worksheet popup is present");
        clickButton("Start New Worksheet");
        Utility_Functions.timeWait(8);
        verifyWsOptionHeader();
    }

    public void clickLastUpdateWS() {
        click(worksheetName("Last Updated:"), "Click Worksheet hyper link");
        Utility_Functions.timeWait(4);
    }

    /**
     * Keyword to verify functionality of Cancel button
     * On Saved worksheet Available popup
     */
    public void verifyCancelSavedWSPopup() {
        clickLastUpdateWS();
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
        clickLastUpdateWS();
        clickButton("Open Saved Worksheet");
        Utility_Functions.timeWait(10);
        verifyWsOptionHeader();
    }

    public void modifyLeadTimeField() {
        Utility_Functions.xScrollWindowTopByValue(ownDriver, 1);
        sendKeys(SpoPage.leadTime, "10", "Modify lead time field");
        functionalRefreshButton();
        /*click(SpoPage.saveWorksheetBtn, "Click Save Worksheet button");
        Utility_Functions.timeWait(2);
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.closeIcon);
        Utility_Functions.timeWait(2);
        Utility_Functions.xMouseClick(ownDriver, SpoPage.closeIcon);*/
        clickButton("Back");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, SpoPage.spoPageTitle, "Wait page to be loaded");
        commonObj.validateText(SpoPage.savedTag, "Saved", "Worksheet status turned to Saved tag");
    }

    public void verifyTrashIconPresence() throws AWTException {
        if (!Utility_Functions.xIsDisplayed(ownDriver, SpoPage.trashIcon)) {
            clickWorkSheetName();
            Utility_Functions.timeWait(3);
            click(button("Back"), "Click Back Button");
            Utility_Functions.timeWait(2);
            commonObj.validateText(SpoPage.savedTag, "Saved", "Worksheet status turned to Saved tag");
        }
    }

    /**
     * Keyword to verify saved work sheet
     */
    public void verifySavedWorkSheet() throws AWTException {
        verifyTrashIconPresence();
        verifyCancelSavedWSPopup();
        verifyOpenNewWSBtn();
        modifyLeadTimeField();
        verifyOpenNewWSBtn();
        Utility_Functions.xScrollWindowTopByValue(ownDriver, 1);
        String leadTimeAct = getAttribute(SpoPage.leadTime, "value");
        if (leadTimeAct.equals("10")) {
            Utility_Functions.xAssertEquals(report, leadTimeAct, "10", "Modified lead time is present");
        } else {
            Utility_Functions.xAssertEquals(report, leadTimeAct, "15", "Modified lead time is present");
        }
        click(button("Back"), "Click Back Button");
        Utility_Functions.timeWait(4);
        verifyStartNewWorkSheet();
        modifyLeadTimeField();
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
        int size = ownDriver.findElements(By.xpath("//div[@class='col-3']//select[@id='" + field + "1']/option")).size();
        Utility_Functions.xAssertEquals(report, size, optionCount, "Total " + size + " count on " + field + "");
        if (field.equals("minute")) {
            String[] min = {"00", "15", "30", "45"};
            List<WebElement> el = ownDriver.findElements(By.xpath("//div[@class='col-3']//select[@id='" + field + "1']/option"));
            int i = 0;
            for (WebElement elm : el) {
                Utility_Functions.xAssertEquals(report, elm.getText(), min[i], "");
                i++;
            }
        } else if (field.equals("Meridian")) {
            String[] mer = {"AM", "PM"};
            List<WebElement> el = ownDriver.findElements(By.xpath("//div[@class='col-3']//select[@id='" + field + "1']/option"));
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

    public By wsFields(String field) {
        return By.xpath("//label[text()='" + field + "']/parent::div/descendant::input");
    }

    /**
     * Keyword to Verify UI of Search Filter UI
     */
    public void createNewWorksheetTemplateUI() {
        String[] field = {"Name Your Worksheet", "Assigned User", "Manufacturer Code", "Product Code", "Vendor Code", "Vendor Number", "Months Supply to Order", "Multiplier", "Lead Time (Days)", ""};
        int i = 0;
        while (Utility_Functions.xIsDisplayed(ownDriver, By.xpath("//label[text()='" + field[i] + "']/parent::div/descendant::input"))) {
            String text = field[i];
            commonObj.validateElementExists(wsFields(text), "" + text + " text box field present");
            i++;
        }
        commonObj.validateElementExists(SpoPage.costOption, "Cost Option drop down present");
        Utility_Functions.xSelectDropdownByName(ownDriver, SpoPage.costOption, "List Price");
        String[] option = {"List Price", "PO Cost", "Last Cost"};
        for (int j = 0; j < option.length; j++) {
            Utility_Functions.xAssertEquals(report, ownDriver.findElements(SpoPage.costOptions).get(j).getText(), option[j], "" + option[j] + " is present");
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
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.dayOfTheWeek);
        click(SpoPage.dayOfTheWeek, "Click Day Of The Week Drop Down");
        String[] days = {"Monday", "Tuesday", "Wednesday"};
        for (String day : days) {
            Utility_Functions.timeWait(2);
            click(ownDriver.findElement(By.xpath("//span[text()='" + day + "']")), "Select Day");
        }
        int size = ownDriver.findElements(SpoPage.verifyChecked).size();
        click(SpoPage.trailingMonths);
        Utility_Functions.timeWait(2);
        if (size == 3) {
            commonObj.validateText(By.xpath("//div[text()='Thursday, Friday']"), "Thursday, Friday", "Multiple days selected");
            Utility_Functions.xUpdateJson("selectedDays", "Thursday, Friday");
        } else {
            commonObj.validateText(By.xpath("//div[text()='Monday, Tuesday, Wednesday']"), "Monday, Tuesday, Wednesday", "Multiple days selected");
            Utility_Functions.xUpdateJson("selectedDays", "Monday, Tuesday, Wednesday");
        }
    }

    public By button(String buttonName) {
        return By.xpath("//button[contains(text(),'" + buttonName + "')]");
    }

    /**
     * Keyword to click button
     */
    public void clickButton(String buttonName) {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, button(buttonName), "Click " + buttonName + " button");
    }

    /**
     * Keyword to verify functionality create Worksheet Button
     */
    public void createWorksheet() {
        Utility_Functions.timeWait(4);
        String itemNo = null;
        int i;
        int totalItem = ownDriver.findElements(By.xpath("//td[@ng-reflect-ng-class='item-color']")).size();
        for (i = 0; i < totalItem; i++) {
            itemNo = ownDriver.findElements(By.xpath("//td[@ng-reflect-ng-class='item-color']")).get(i).getText().trim().toUpperCase();
            Character letter = itemNo.charAt(0);
            Character letter1 = 'J';
            Boolean bl = letter.equals(letter1);
            if (!bl) {
                break;
            }
        }
        Utility_Functions.xUpdateJson("ItemNoWS", itemNo);
        String itemDesc;
        try {
            itemDesc = ownDriver.findElement(By.xpath("//tr[" + i + "]/td/div")).getText().trim();
        } catch (Exception e) {
            itemDesc = ownDriver.findElement(By.xpath("//tr[" + (i + 1) + "]/td/div")).getText().trim();
        }
        Utility_Functions.xUpdateJson("itemDescWS", itemDesc);
        String unitCost;
        try {
            unitCost = ownDriver.findElements(By.xpath("//tr[" + i + "]/td")).get(4).getText().trim();
        } catch (Exception e) {
            unitCost = ownDriver.findElements(By.xpath("//tr[" + (i + 1) + "]/td")).get(4).getText().trim();
        }
        Utility_Functions.xUpdateJson("unitCost", unitCost);
        clickButton("Create Worksheet");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, SpoPage.popUp, "Wait for Pop up");
        commonObj.validateText(SpoPage.popUp, Utility_Functions.xGetJsonData("WorksheetTempName") + " template successfully created.", Utility_Functions.xGetJsonData("WorksheetTempName") + " template successfully created. pop message is present");
        if (isDisplayed(SpoPage.closeIcn))
            click(SpoPage.closeIcn, "Click CLose icon");
    }

    public void handleDiscountFieldIfPresent() {
        if (Utility_Functions.xIsDisplayed(ownDriver, SpoPage.isInvalidDisc)) {
            commonObj.validateText(SpoPage.discountError, "Discount cannot be 0 when using List Price.", "Discount cannot be 0 when using List Price. error message is present");
            sendKeys(SpoPage.isInvalidDisc, "1", "Enter Discount");
            clickButton("Find Products");
            Utility_Functions.timeWait(5);
        }
    }

    /**
     * Keyword to Create WorkSheet
     */
    public void findProductMultiplier() {
        String name = Utility_Functions.xGenerateAlphaNumericString();
        Utility_Functions.xUpdateJson("WorksheetTempName", name);
        sendKeys(SpoPage.worksheetSheetTemplateName, name, "Name Your Worksheet");
        assignedUser();
        getMFVNPC();
        commonObj.validateElementExists(worksheetInputFields("Multiplier"), "'Multiplier' field is present");
        selectPreviousMonth("6");
        selectPreviousMonth("24");
        selectDays();
        Utility_Functions.timeWait(2);
        clickButton("Find Products");
        Utility_Functions.timeWait(5);
    }

    /**
     * Keyword to Previous select month
     */
    public void selectPreviousMonth(String month) {
        sendKeys(SpoPage.trailingMonths, month, "Enter '" + month + "' into Trailing month");
        int size = ownDriver.findElements(SpoPage.selectedMonth).size();
        Utility_Functions.xAssertEquals(report, "" + size / 2 + "", month, "");
    }

    public void costOption() {
        /*Utility_Functions.xScrollWindowTopByValue(ownDriver, 1);
        Utility_Functions.timeWait(2);
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.costOption);*/
        String cost = jsonData.getData("CostOption");
        if (!(cost == null)) {
            int size = ownDriver.findElements(SpoPage.costOption).size() - 1;
            try {
                click(ownDriver.findElements(SpoPage.costOption).get(size), "Click Cost Option Drop Down");
            } catch (Exception e) {
                try {
                    click(ownDriver.findElement(SpoPage.costOption), "Click Cost Option Drop Down");
                } catch (Exception e3) {
                    Utility_Functions.xScrollIntoView(ownDriver, SpoPage.costOption);
                    click(ownDriver.findElements(SpoPage.costOption).get(size), "Click Cost Option Drop Down");
                }
            }
            Utility_Functions.timeWait(2);
            click(By.xpath("//option[contains(text(),'" + cost + "')]"), "Select " + cost + " option");
        }
    }

    public void errorRepeatSteps() {
        if (i < 2) {
            try {
                Utility_Functions.timeWait(3);
                if (ownDriver.findElement(SpoPage.popUp).getText().trim().equals("There are no items found for this MF, PD, VN combination.")) {
                    Utility_Functions.timeWait(4);
                    click(SpoPage.xIcon);
                    navigateToCreateWorksheet();
                    findProduct();
                }
            } catch (Exception e) {
            }
        }
        i++;
    }

    /**
     * Keyword to Create WorkSheet and Worksheet name not stored
     */
    public void findProductWS() {
        String name = Utility_Functions.xGenerateAlphaNumericString();
        sendKeys(SpoPage.worksheetSheetTemplateName, name, "Name Your Worksheet");
        assignedUser();
        fillMandatoryField();
        clickFindProduct();
    }

    public void fillMandatoryField() {
        getMFVNPC();
        costOption();
        selectDays();
        Utility_Functions.timeWait(4);
    }

    public void clickFindProduct() {
        clickButton("Find Products");
        Utility_Functions.timeWait(5);
        handleDiscountFieldIfPresent();

        errorRepeatSteps();
    }

    /**
     * Keyword to Create WorkSheet
     */
    public void findProduct() {
        String name = Utility_Functions.xGenerateAlphaNumericString();
        Utility_Functions.xUpdateJson("WorksheetTempName", name);
        sendKeys(SpoPage.worksheetSheetTemplateName, name, "Name Your Worksheet");
        assignedUser();
        fillMandatoryField();
        clickFindProduct();
    }

    public void getMFVNPC() {
        sendKeys(worksheetInputFields("Vendor Code"), Utility_Functions.xGetJsonData("VendorCode"), "Enter Vendor Code");
        Utility_Functions.timeWait(3);
        sendKeysAndTab(worksheetInputFields("Vendor Number"), Utility_Functions.xGetJsonData("VendorNumber"), "Enter Vendor Number");
        Utility_Functions.timeWait(3);
        sendKeys(worksheetInputFields("Manufacturer Code"), Utility_Functions.xGetJsonData("ManufacturerCode"), "Enter Manufacturer code");
        Utility_Functions.timeWait(4);
        String vend = ownDriver.findElement(worksheetInputFields("Vendor Number")).getAttribute("value");
        Utility_Functions.xUpdateJson("VendorNoForHeader", vend);
        sendKeys(worksheetInputFields("Product Code"), Utility_Functions.xGetJsonData("ProductCode"), "Enter Product Code");
        String ven = getAttribute(SpoPage.searchTerm1, "ng-reflect-model").trim();
        Utility_Functions.xUpdateJson("VendorNumberItem", ven);
    }

    /**
     * Keyword to verify Page Count
     */
    public void valPageCountCreateItem(int pageNum) {
        Utility_Functions.xScrollWindow(ownDriver);
        Utility_Functions.xScrollWindow(ownDriver);
        Utility_Functions.timeWait(4);
        click(By.xpath("//span[text()='" + pageNum + "']"), "Click on '" + pageNum + "' Present below the Left corner of the page");
        int driverCount = ownDriver.findElements(SpoPage.itemNoCount).size();
        if (driverCount == pageNum) {
            Utility_Functions.xAssertEquals(report, "" + driverCount + "", "" + pageNum + "", "'" + pageNum + "' is in disable state and showing " + pageNum + " driver Count");
        } else {
            commonObj.validateElementExists(SpoPage.itemNoCount, "Total count is " + driverCount + "");
        }
    }

    /**
     * Keyword to Verify functionality of Page Count
     */
    public void funPageCountCreateItem() {
        Utility_Functions.xScrollWindow(ownDriver);
        if (Utility_Functions.xIsDisplayed(ownDriver, DriversPage.onePage)) {
            int driverCount = ownDriver.findElements(SpoPage.itemNoCount).size();
            if (driverCount == 0) {
                driverCount = ownDriver.findElements(By.xpath("//td[@ng-reflect-ng-class='item-color']")).size();
            }
            commonObj.validateText(DriversPage.onePage, "of 1", "One page is available having count " + driverCount + "");
        } else {
            int driverCount = ownDriver.findElements(SpoPage.itemNoCount).size();
            if (driverCount == 0) {
                driverCount = ownDriver.findElements(By.xpath("//td[@ng-reflect-ng-class='item-color']")).size();
            }
            valPageCount(10);
            valPageCount(15);
            valPageCount(30);
        }
    }

    /**
     * This method to select day to run radio button
     */
    public By runWsRadioBtn(String text) {
        return By.xpath("//label[contains(text(),'" + text + "')]/preceding-sibling::input[@name='selectedBiWeeklyFlag']");
    }

    /**
     * This method to select day to run radio button
     */
    public void runWSRadioBtn() {
        if (ownDriver.findElement(runWsRadioBtn("Run each week")).isSelected()) {
            click(runWsRadioBtn("Run each week"));
        } else {
            click(runWsRadioBtn("Run every other week"));
        }
    }

    /**
     * This method to verify cancel and back button on Worksheet item screen
     */
    public void wsItemsScreenBtn() {
        clickButton("Back");
        Utility_Functions.timeWait(4);
        commonObj.validateText(SpoPage.spoPageTitle, "CREATE NEW WORKSHEET TEMPLATE", "Worksheet items Header is present");
        clickButton("Find Products");
        Utility_Functions.timeWait(5);
        commonObj.validateText(By.xpath("//h3"), "worksheet items exclude all asterisk items from this worksheet", "Page subtitle 'WORKSHEET ITEMS' is present");
        verifyCancelBtn();
    }

    public void wsItemsScreenColumn() {
        Utility_Functions.timeWait(3);
        commonObj.validateText(SpoPage.spoPageTitle, "CREATE NEW WORKSHEET TEMPLATE", "Worksheet items Header is present");
        String[] actText = {"Item Number", "Item Description", "Date of Last Sales Order", "Unit Cost", "Unit Weight", "Stocked at Home WSS"};
        List<WebElement> els = ownDriver.findElements(By.xpath("//th"));
        for (int i = 1; i <= 6; i++) {
            Utility_Functions.xAssertEquals(report, els.get(i).getText().trim(), actText[i - 1], "");
            i++;
        }
    }

    /**
     * This method to UI Of worksheet item screen
     */
    public void wsItemsScreenUI() {
        wsItemsScreenColumn();
        commonObj.validateElementExists(TruckPage.filterSearch, "Search filter icon is present");
        commonObj.validateElementExists(SpoPage.excludeAsteriskItem, "'Exclude all asterisk items from this worksheet' check box present");
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.addItemToWSLink);
        commonObj.validateText(SpoPage.addItemToWSLink, "Add Items to this Worksheet", "'+ Add Items to this Worksheet link is present'");
        commonObj.validateElementExists(button("Cancel"), "Cancel button is present");
        commonObj.validateElementExists(button("Back"), "Back button is present");
        commonObj.validateElementExists(button("Create Worksheet"), "Create Worksheet button is present");
    }

    public void clickSearch() {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, TruckPage.filterSearch, "Click Search Filter icon");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//h1"), "Search Filters", "Search Filters panel title is present");
        sendKeys(SpoPage.worksheetNameFilter, Utility_Functions.xGetJsonData("WorksheetTempName"), "Enter created worksheet");
        Utility_Functions.timeWait(2);
        Utility_Functions.xClickHiddenElement(ownDriver, TruckPage.applyFilter);
        Utility_Functions.timeWait(6);
    }

    /**
     * Keyword to Filter Created worksheet
     */
    public void filterCreatedWorksheet() throws AWTException {
        //Utility_Functions.timeWait(7);
        if (Utility_Functions.xIsDisplayed(ownDriver, TruckPage.filterSearch)) {
            clickSearch();
            commonObj.validateText(SpoPage.filteredWorksheet, Utility_Functions.xGetJsonData("WorksheetTempName"), "Created Work sheet present");
        }
    }

    /**
     * This method to choose Multiplier
     */
    public void chooseMultiplierOpt() {
        sendKeys(pages.Purchasing.OptionsConstantsPage.discMultOpt, "1", "Enter 1 into field");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    }

    /**
     * This method to choose Discount
     */
    public void chooseDiscountOpt() {
        clearText(pages.Purchasing.OptionsConstantsPage.discMultOpt);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
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

    /**
     * This method to verify default value on create worksheet template
     */
    public void verifyDefaultValues() {
        String supplyToOrder = ownDriver.findElement(SpoPage.supplyToOrder).getAttribute("placeholder");
        String trailingMonths = ownDriver.findElement(SpoPage.trailingMonths).getAttribute("ng-reflect-model");
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.optionsConstants);
        String discount = ownDriver.findElement(SpoPage.optionsConstants).getAttribute("placeholder");
        String leadTime = ownDriver.findElement(SpoPage.leadTime).getAttribute("placeholder");
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.hourDropDown);
        String hour = ownDriver.findElement(SpoPage.hourDropDown).getAttribute("ng-reflect-model");
        Utility_Functions.xAssertEquals(report, supplyToOrder, "1.00", "Months Supply to Order '1.00' value is set by default");
        Utility_Functions.xAssertEquals(report, trailingMonths, "3", "Previous 3 Months of History From Current by default");
        Utility_Functions.xAssertEquals(report, discount, "0", "Discount ");
        Utility_Functions.xAssertEquals(report, leadTime, "15", "Lead Time (Days) ");
        Utility_Functions.xAssertEquals(report, hour, "05", "Time of the day/night '5:00 AM' value is set by default ");
    }

    /**
     * This method to verify Cancel button on create worksheet template
     */
    public void verifyCancelBtn() {
        clickButton("Cancel");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(SpoPage.cancelPopup, "Cancel confirmation popup is present");
        clickButton("No");
        Utility_Functions.timeWait(1);
        commonObj.validateText(By.xpath("//h2"), "CREATE NEW WORKSHEET TEMPLATE", "Validating  CREATE NEW WORKSHEET TEMPLATE page title");
        clickButton("Cancel");
        Utility_Functions.timeWait(2);
        clickButton("Yes");
        Utility_Functions.timeWait(5);
        commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
    }

    /**
     * This method to Navigate to Add item to WS screen
     */
    public void navigateToAddItemToWS() {
        Utility_Functions.timeWait(4);
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.addItemToWSLink);
        Utility_Functions.timeWait(1);
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, SpoPage.addItemToWSLink, "Click '+ Add Items to this worksheet link'");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SpoPage.addItemHeader, "ADD ITEMS TO THIS WORKSHEET", "Header 'ADD ITEMS TO THIS WORKSHEET' is present");
    }

    /**
     * This method to Verify Functionality of Back To Worksheet button
     */
    public void backToWSBtn() {
        clickButton("Return to Worksheet");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//h2"), "CREATE NEW WORKSHEET TEMPLATE", "Validating  CREATE NEW WORKSHEET TEMPLATE page title");
    }

    /**
     * This method to Verify Functionality of Back To Worksheet button
     */
    public void backToWSBtnEditWs() {
        clickButton("Return to Worksheet");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//h2"), "EDIT WORKSHEET TEMPLATE", "Validating EDIT WORKSHEET TEMPLATE page title");
    }

    /**
     * This method to select Item from the list
     */
    public void selectItem() {
        int sz = ownDriver.findElements(SpoPage.selectItem).size();
        if (sz == 0) {
            addItemsToWS();
        }
        Utility_Functions.xScrollIntoView(ownDriver, ownDriver.findElements(SpoPage.selectItem).get(sz - 1));
        click(ownDriver.findElements(SpoPage.selectItem).get(sz - 1), "Select item from the list");
        String item1 = ownDriver.findElement(By.xpath("(//input[@name='selectItem'])[" + sz + "]/parent::td/following-sibling::td")).getText();
        Utility_Functions.xUpdateJson("SelectItem1", item1);
        getMfVnPdCode(1);
    }

    public void getMfVnPdCode(int row) {
        int size = ownDriver.findElements(SpoPage.getMF).size();
        Utility_Functions.xUpdateJson("MFCode" + row, ownDriver.findElements(SpoPage.getMF).get(size - row).getAttribute("value"));
        Utility_Functions.xUpdateJson("PDCode" + row, ownDriver.findElements(SpoPage.getPD).get(size - row).getAttribute("value"));
    }

    public void searchVN() {
        int size = ownDriver.findElements(worksheetInputFields("VN")).size() - 1;
        sendKeys(ownDriver.findElements(worksheetInputFields("VN")).get(size), "X", "Search VN");
        Utility_Functions.timeWait(2);
        for (int i = 0; i < ownDriver.findElements(SpoPage.autoSuggestion).size(); i++) {
            Utility_Functions.timeWait(4);
            click(ownDriver.findElements(SpoPage.autoSuggestion).get(i), "Select VN from the suggestion");
            Utility_Functions.timeWait(2);
            click(SpoPage.searchBtn, "Click Search Button");
            Utility_Functions.timeWait(2);
            if (!isDisplayed(SpoPage.noResultFound)) {
                String vn = ownDriver.findElements(worksheetInputFields("VN")).get(size).getAttribute("value");
                Utility_Functions.xUpdateJson("VNCode", vn);
                break;
            } else {
                ownDriver.findElements(worksheetInputFields("VN")).get(size).clear();
                sendKeys(ownDriver.findElements(worksheetInputFields("VN")).get(size), "X", "Search VN");
                Utility_Functions.timeWait(2);
            }
        }
        //Utility_Functions.timeWait(4);
    }

    /**
     * This method to verify of 'Create Worksheet' button
     * with adding additional items to the worksheet with selecting checkbox
     */
    public void addItemsToWS() {
        searchVN();
        String bl = ownDriver.findElement(SpoPage.selectAllCheckBox).getAttribute("ng-reflect-model");
        Utility_Functions.xAssertEquals(report, bl, "true", "All check boxes are enabled by default");
        click(SpoPage.selectAllCheckBox, "Click checkbox");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(SpoPage.buttonDisabled, "'Add Selected Items to Worksheet' button is disabled");
        String bl1 = ownDriver.findElement(SpoPage.selectAllCheckBox).getAttribute("ng-reflect-model");
        Utility_Functions.xAssertEquals(report, bl1, "false", "All check boxes are disabled");
        selectItem();
    }

    public void selectSecItem() {
        int sz = ownDriver.findElements(SpoPage.selectItem).size();
        if (sz > 1) {
            Utility_Functions.xScrollIntoView(ownDriver, ownDriver.findElements(SpoPage.selectItem).get(sz - 2));
            click(ownDriver.findElements(SpoPage.selectItem).get(sz - 2), "Select item from the list");
            Utility_Functions.timeWait(3);
            String item2 = ownDriver.findElement(By.xpath("(//input[@name='selectItem'])[" + (sz - 1) + "]/parent::td/following-sibling::td")).getText();
            Utility_Functions.xUpdateJson("SelectItem2", item2);
            getMfVnPdCode(2);
            click(SpoPage.addSelectedItemToWSBtn, "Click 'Add Selected Items to Worksheet' Button");
            Utility_Functions.timeWait(4);
            String pop = getText(SpoPage.popUp);
            if (pop.equals("1 item added successfully. 1 item already existed.")) {
                Utility_Functions.xAssertEquals(report, pop, "1 item added successfully. 1 item already existed.", "'1 item added successfully. 1 item already existed.' popup message is Present");
                Utility_Functions.timeWait(7);
            }
            click(SpoPage.addSelectedItemToWSBtn, "Again select same item and Click 'Add Selected Items to Worksheet' Button");
            Utility_Functions.timeWait(1);
            commonObj.validateText(SpoPage.popUp, "zero items added. 2 items already existed.", "'zero items added. 2 items already existed.' message is Present");
        }
    }

    public By getColumnData(String colName) {
        return By.xpath("//table/tbody/tr[1]/td[count(//table/thead/tr/th[contains(text(),'" + colName + "')]/preceding-sibling::th)+1]/span");
    }

    /**
     * This method to verify of 'Add Selected Items to Worksheet ' button
     */
    public void addSelectedItemsWorksheetBtn() {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, SpoPage.addSelectedItemToWSBtn, "Click 'Add Selected Items to Worksheet' Button");
        Utility_Functions.timeWait(4);
        String pop = getText(SpoPage.popUp);
        if (pop.equals("1 item added successfully.")) {
            Utility_Functions.xAssertEquals(report, pop, "1 item added successfully.", "'1 item added successfully.' popup message is Present");
            Utility_Functions.timeWait(7);
        }
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, SpoPage.addSelectedItemToWSBtn, "Again select same item and Click 'Add Selected Items to Worksheet' Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SpoPage.popUp, "Zero items added. 1 item already existed.", "'Zero items added. 1 item already existed.' message is Present");
        selectSecItem();
    }

    public By getItemNo(String label) {
        return By.xpath("//table/tbody/tr[1]/td[count(//table/thead/tr/th[contains(text(),'" + label + "')]/preceding-sibling::th)]");
    }

    public void searchItem(String item, int row) {
        Utility_Functions.timeWait(4);
        click(TruckPage.filterSearch, "Click Search Filter icon");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//h1"), "Search Filters", "Search Filters panel title is present");
        sendKeys(SpoPage.itemNumSearch, Utility_Functions.xGetJsonData(item), "Enter Selected Item");
        Utility_Functions.timeWait(2);
        clearText(SpoPage.manufacturerCode);
        sendKeys(SpoPage.manufacturerCode, Utility_Functions.xGetJsonData("MFCode" + row), "Enter Selected MF");
        Utility_Functions.timeWait(2);
        sendKeys(SpoPage.productCode, Utility_Functions.xGetJsonData("PDCode" + row), "Enter Selected PD");
        Utility_Functions.timeWait(2);
        sendKeys(SpoPage.vendorName, Utility_Functions.xGetJsonData("VNCode"), "Enter Selected VN");
        Utility_Functions.timeWait(2);
        click(SpoPage.manufacturerCode);
        Utility_Functions.timeWait(2);
        Utility_Functions.xClickHiddenElement(ownDriver, TruckPage.applyFilter);
        Utility_Functions.timeWait(2);
        commonObj.validateText(getItemNo("Item Number"), Utility_Functions.xGetJsonData(item), "Selected Item is added to the worksheet");
    }

    /**
     * This method to verify Added item to Worksheet
     */
    public void verifyAddedItem() {
        searchItem("SelectItem2", 2);
        searchItem("SelectItem1", 1);
    }

    public void validateColumn() {
        String[] actText = {"Item Number", "Item Description", "Qty on Hand", "Qty Available", "Qty on PO", "Qty on SO", "MF", "PD", "VN"};
        List<WebElement> els = ownDriver.findElements(By.xpath("//th"));
        for (int i = 9; i <= 17; i++) {
            Utility_Functions.xAssertEquals(report, els.get(i).getText().trim(), actText[i - 9], "");
            i++;
        }
    }

    /**
     * This method to UI of Add items to this worksheet screen
     */
    public void addItemsToWSUI() {
        commonObj.validateElementExists(worksheetInputFields("Enter Search Terms"), "'Enter Search Terms' input text field present");
        commonObj.validateElementExists(worksheetInputFields("MF"), "'MF' input text field present");
        commonObj.validateElementExists(worksheetInputFields("PD"), "'PD' input text field present");
        commonObj.validateElementExists(worksheetInputFields("VN"), "'VN' input text field present");
        commonObj.validateElementExists(SpoPage.searchBtn, "'Search' button is present");
        commonObj.validateElementExists(button("Return to Worksheet"), "'Return to Worksheet' button is present");
        commonObj.validateElementExists(SpoPage.addSelectedItemToWSBtn, "'Add Selected Items to Worksheet' button is present");
        commonObj.validateElementExists(SpoPage.asteriskItem, "'Asterisk Items' Check Box is present");
        commonObj.validateElementExists(SpoPage.jobItem, "'Job Items' Check Box is present");
        commonObj.validateElementExists(SpoPage.iovItem, "'IOV Items' Check Box is present");
        validateColumn();
    }

    /**
     * This method to verify Worksheet item search filter
     */
    public void wsItemSearchFilter() throws AWTException {
        ArrayList<String> str = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            String val = ownDriver.findElements(By.xpath("//td")).get(i).getText().trim();
            str.add(val);
        }
        click(TruckPage.filterSearch, "Click Search Filter icon");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//h1"), "Search Filters", "Search Filters panel title is present");
        sendKeys(SpoPage.itemNumSearch, str.get(0), "Enter Selected Item");
        Utility_Functions.timeWait(2);
        sendKeys(SpoPage.itemDescription, str.get(1), "Enter item Description");
        Utility_Functions.timeWait(2);
        sendKeys(SpoPage.lastOrdered, str.get(2), "Enter last Ordered");
        Utility_Functions.timeWait(2);
        sendKeys(SpoPage.unitCost, str.get(3), "Enter unitCost");
        Utility_Functions.timeWait(2);
        sendKeys(SpoPage.unitWeight, str.get(4), "Enter unitWeight");
        Utility_Functions.timeWait(2);
        if (str.get(5).equals("Yes")) {
            click(SpoPage.showStockedAtWss, "Click Show Stock At Wss check box");
            Utility_Functions.timeWait(2);
        }
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_MINUS);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_MINUS);
        Utility_Functions.timeWait(2);
        click(TruckPage.applyFilter, "Click Apply Filters");
        Utility_Functions.timeWait(2);
        commonObj.validateText(getItemNo("Item Number"), str.get(0), "Selected Item is added to the worksheet");
    }

    /**
     * This method to assert the cost
     */
    public void assertCost(String CostOption) {
        String costValue = Utility_Functions.xGetJsonData("unitCost");
        if (costValue.contains("$0.")) {
            costValue = costValue.replace("$0", "");
        }
        switch (CostOption) {
            case "List Price":
                Utility_Functions.xScrollIntoView(ownDriver, ItemMasterPage.txtBoxListPrice);
                String listPrice = getAttribute(ItemMasterPage.txtBoxListPrice, "value").trim();
                Utility_Functions.xAssertEquals(report, listPrice, costValue, "List Price: ");
                break;
            case "PO Cost":
                Utility_Functions.xScrollIntoView(ownDriver, ItemMasterPage.txtBoxPoCost);
                String PoCost = getAttribute(ItemMasterPage.txtBoxPoCost, "value").trim();
                Utility_Functions.xAssertEquals(report, PoCost + "00", costValue, "Po Cost: ");
                break;
            default:
                Utility_Functions.xScrollIntoView(ownDriver, ItemMasterPage.inCostsLast);
                Utility_Functions.timeWait(2);
                String LastCost = getText(ItemMasterPage.inCostsLast).trim();
                Utility_Functions.xAssertEquals(report, LastCost + "00", costValue, "Last Cost: ");
                break;
        }
    }

    /**
     * This method to verify Unit Cost from Wise
     */
    public void verifyUnitCost() {
        sendKeys(ItemMasterPage.txtBoxSearch, Utility_Functions.xGetJsonData("ItemNoWS"));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.timeWait(2);
        String itemDesc1 = getAttribute(ItemMasterPage.itemDesc1, "value").trim();
        Utility_Functions.xAssertEquals(report, itemDesc1, Utility_Functions.xGetJsonData("itemDescWS"), "Item description: ");
        String man = getAttribute(ItemMasterPage.manufacturerCode, "value");
        Utility_Functions.xAssertEquals(report, man, Utility_Functions.xGetJsonData("ManufacturerCode"), "ManufacturerCode: ");
        String vendor = getAttribute(ItemMasterPage.vendorCode, "value");
        Utility_Functions.xAssertEquals(report, vendor, Utility_Functions.xGetJsonData("VendorCode"), "VendorCode: ");
        String CostOption = jsonData.getData("CostOption");
        assertCost(CostOption);
        click(SpecialPriceAllowancePage.btnExit);
    }

    /**
     * This method to verify Worksheet calculation section
     */
    public void verifyWSCalculation() {
        costOption();
        sendKeys(SpoPage.discountOrMultiplier, "0", "Enter '0' into Discount input text field");
        sendKeys(SpoPage.monthSupply, "5", "Enter '5' into 'Mos Supply' input text field");
        sendKeys(SpoPage.leadTime, "10", "Enter '10' into 'Lead Time' input text field");
        functionalRefreshButton();
        Utility_Functions.xScrollWindowTop(ownDriver);
        click(SpoPage.closeIcon, "Click Close Icon");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
    }

    public By isDisabled(String label) {
        return By.xpath("//input[@disabled]/preceding-sibling::label[text()='" + label + "']");
    }

    public By actDeActToggle(Boolean bl) {
        return By.xpath("//input[@id='isListActivated' and @ng-reflect-model='" + bl + "']/following-sibling::div");
    }

    /**
     * This method to verify Edit Worksheet template page field
     */
    public void verifyEditWSTempFields() {
        String[] labels = {"Name Your Worksheet", "Manufacturer Code", "Product Code", "Vendor Code", "Vendor Number"};
        for (String label : labels) {
            commonObj.validateElementExists(isDisabled(label), "'" + label + "' text box is disabled");
        }
        commonObj.validateElementExists(SpoPage.trashIcon, "Delete icon present");
        commonObj.validateElementExists(SpoPage.costOption, "Cost Option drop down present");
        String[] option = {"List Price", "PO Cost", "Last Cost"};
        for (int j = 0; j < option.length; j++) {
            Utility_Functions.xAssertEquals(report, ownDriver.findElements(SpoPage.costOptions).get(j).getText(), option[j], "" + option[j] + " is present");
        }
        commonObj.validateElementExists(SpoPage.trailingMonths, "Trailing Month section present");
        validateTimeOfDayNight();
        commonObj.validateElementExists(actDeActToggle(true), "By default list is activated");
        commonObj.validateElementExists(button("Cancel"), "Cancel Button is present");
        commonObj.validateElementExists(button("Next "), "Next button is present");
        String day = Utility_Functions.xGetJsonData("selectedDays");
        commonObj.validateText(By.xpath("//div[text()='" + day + "']"), day, "'Day of the week' is present: " + day);
    }

    /**
     * This method to verify Assigned User can be changed
     */
    public void verifyAssignedUserChange() throws AWTException {
        sendKeysAndTab(SpoPage.assignedUser, "AutomationTesting", "Edit Assigned User");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SpoPage.popUp, "template updated successfully.", "template updated successfully. popup is present");
        Utility_Functions.timeWait(2);
        click(button("Cancel"), "Click Cancel Button");
        Utility_Functions.timeWait(6);
        //commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
        verifyWsOptionHeader();
        clickButton("Back");
        Utility_Functions.timeWait(6);
        commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
        filterCreatedWorksheet();
        navigateWSOption();
        navigateEditWSTemp();
        String user = ownDriver.findElement(SpoPage.assignedUser).getAttribute("value");
        Utility_Functions.xAssertEquals(report, user, "AutomationTesting", "Assigned User is updated ");
    }

    /**
     * This method to verify edited Sales Option Section
     */
    public void verifyEditedSalesOption() throws AWTException {
        filterCreatedWorksheet();
        navigateWSOption();
        navigateEditWSTemp();
        String monSupply = ownDriver.findElement(SpoPage.supplyToOrder).getAttribute("value");
        String discount = ownDriver.findElement(SpoPage.discountOrMultiplier).getAttribute("value");
        String leadTime = ownDriver.findElement(SpoPage.leadTime).getAttribute("value");
        String trailMon = ownDriver.findElement(SpoPage.trailingMonths).getAttribute("value");
        Utility_Functions.xAssertEquals(report, monSupply, "3", "Months Supply to Order is updated");
        Utility_Functions.xAssertEquals(report, discount, "1", "Discount to Order is updated");
        Utility_Functions.xAssertEquals(report, leadTime, "20", "leadTime to Order is updated");
        Utility_Functions.xAssertEquals(report, trailMon, "24", "trail Month to Order is updated");
    }

    public void updatedMessage() {
        if (Utility_Functions.xIsDisplayed(ownDriver, SpoPage.popUp)) {
            commonObj.validateText(SpoPage.popUp, "template updated successfully.", "template updated successfully. popup is present");
            Utility_Functions.timeWait(2);
        }
    }

    /**
     * This method to verify Sales Option Section can be changed
     */
    public void verifySalesOptionEditable() throws AWTException {
        costOption();
        discountError();
        sendKeysAndTab(SpoPage.supplyToOrder, "3", "Edit 'Months Supply to Order' field");
        Utility_Functions.timeWait(2);
        updatedMessage();
        sendKeysAndTab(SpoPage.leadTime, "20", "Edit 'Lead Time (Days)' field");
        Utility_Functions.timeWait(2);
        updatedMessage();
        sendKeys(SpoPage.trailingMonths, "24", "Enter '24' into Trailing month");
        int size = ownDriver.findElements(SpoPage.selectedMonth).size();
        Utility_Functions.xAssertEquals(report, "" + size + "", "24", "Total " + size + " columns highlighted ");
        click(button("Cancel"), "Click Cancel Button");
        Utility_Functions.timeWait(6);
        verifyWsOptionHeader();
        clickButton("Back");
        Utility_Functions.timeWait(6);
        commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
    }

    public void selectDayToRun(String dayTime) {
        click(By.xpath("//option[contains(text(),'" + dayTime + "')]"), "Select " + dayTime + " option");
        Utility_Functions.timeWait(2);
    }

    public By isRadioSelected(Boolean bl) {
        return By.xpath("//input[@id='exampleRadios1' and @ng-reflect-value='" + bl + "']");
    }

    public By toggleActivate(Boolean bl) {
        return By.xpath("//input[@id='isListActivated' and @ng-reflect-model='" + bl + "']");
    }

    public void radioAndToggleBtn() {
        if (Utility_Functions.xIsDisplayed(ownDriver, isRadioSelected(false))) {
            click(SpoPage.runEachOtherWeek, "Click Run Each Other Week Radio Button");
        } else {
            click(SpoPage.runEachWeek, "Click Run Each Week Radio Button");
        }
        updatedMessage();
        click(SpoPage.toggleSlider);
        Utility_Functions.timeWait(6);
        if (Utility_Functions.xIsDisplayed(ownDriver, toggleActivate(false))) {
            click(SpoPage.toggleSlider, "Click Activate List toggle Button");
        }
        updatedMessage();
    }

    /**
     * This method to verify Create Worksheet Fields
     */
    public void verifyWSFields() {
        String[] field = {"Manufacturer Code", "Product Code", "Vendor Code", "Vendor Number"};
        int i = 0;
        while (Utility_Functions.xIsDisplayed(ownDriver, By.xpath("//label[text()='" + field[i] + "']/parent::div/descendant::input"))) {
            String text = field[i];
            sendKeysAndTab(wsFields(text), "WzxctfdfghQQwe", "Click " + text + " Input field");
            Utility_Functions.timeWait(5);
            String val = ownDriver.findElements(SpoPage.invalidWSName).get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, val, "Invalid " + text + ".", "Invalid " + text + ". is present");
            i++;
            if (text.equals("Vendor Number")) {
                break;
            }
        }
    }

    /**
     * This method to verify Day to run Section can be changed
     */
    public void verifyDayToRunEditable() {
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.hourDropDown);
        click(SpoPage.hourDropDown);
        Utility_Functions.timeWait(2);
        selectDayToRun("10");
        click(SpoPage.hourDropDown);
        Utility_Functions.timeWait(2);
        selectDayToRun("15");
        click(SpoPage.meridianDropDown);
        Utility_Functions.timeWait(2);
        selectDayToRun("AM");
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.dayOfTheWeek);
        click(SpoPage.dayOfTheWeek, "Click Day Of The Week Drop Down");
        Utility_Functions.timeWait(2);
        int size = ownDriver.findElements(SpoPage.dayCheckBox).size();
        if (size > 2) {
            for (int i = 0; i < size - 2; i++) {
                click(ownDriver.findElements(SpoPage.dayCheckBox).get(i), "Select Day");
                updatedMessage();
            }
            click(SpoPage.trailingMonths);
            Utility_Functions.timeWait(2);
            radioAndToggleBtn();
        }
    }

    public void clickTrashIcon() {
        try {
            click(SpoPage.trashIcon, "Click Trash Icon");
        } catch (Exception e) {
            Utility_Functions.xClickHiddenElement(ownDriver, SpoPage.trashIcon);
        }
        Utility_Functions.timeWait(3);
    }

    /**
     * This method to verify Delete saved Worksheet popup
     */
    public void verifyDeleteSavedWS() throws AWTException {
        clickTrashIcon();
        commonObj.validateElementExists(SpoPage.deleteSavedWorksheetPopup, "Delete Saved Worksheet Popup message present");
        clickButtonWithSize("No");
        commonObj.validateText(By.xpath("//h2"), "EDIT WORKSHEET TEMPLATE", "Validating EDIT WORKSHEET TEMPLATE page title");
        clickTrashIcon();
        commonObj.validateElementExists(SpoPage.deleteSavedWorksheetPopup, "Delete Saved Worksheet Popup message present");
        clickButtonWithSize("Yes");
        Utility_Functions.timeWait(3);
        String worksheetName = Utility_Functions.xGetJsonData("WorksheetTempName");
        commonObj.validateText(SpoPage.popUp, "" + worksheetName + " template successfully deleted.", "" + worksheetName + " template successfully deleted. pop up message is present");
        Utility_Functions.timeWait(4);
        commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
        clickSearch();
        commonObj.validateText(By.xpath("//td"), "No Results Found", "'No Results Found message' is present");
    }

    /**
     * This method to verify Save Worksheet
     */
    public void verifySavedWS() {
        click(SpoPage.saveWorksheet, "Click Save WorkSheet button");
        Utility_Functions.timeWait(4);
        String worksheetName = Utility_Functions.xGetJsonData("WorksheetTempName");
        commonObj.validateText(SpoPage.popUp, worksheetName + " template updated successfully.", worksheetName + " template updated successfully. popup is present");
        Utility_Functions.timeWait(4);
        commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
    }

    /**
     * This method to verify Save Worksheet button
     */
    public void verifySavedWSBtn() {
        Utility_Functions.xScrollIntoView(ownDriver, By.xpath("//th[text()=' Order Quantity ']"));
        try {
            sendKeys(ownDriver.findElements(SpoPage.orderQuantity).get(1), "10", "Modify Order Quantity");
        } catch (Exception e) {
            for (int i = 0; i < 3; i++) {
                try {
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_MINUS);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.keyRelease(KeyEvent.VK_MINUS);
                    Utility_Functions.timeWait(2);
                } catch (Exception e1) {
                }
            }
            sendKeys(ownDriver.findElements(SpoPage.orderQuantity).get(1), "10", "Modify Order Quantity");
        }
        click(SpoPage.saveWorksheetBtn, "Click Save Worksheet button");
        if (isDisplayed(SpoPage.modalLabel)) {
            click(SpoPage.saveButton);
        }
        Utility_Functions.timeWait(4);
        commonObj.validateText(SpoPage.popUp, "Worksheet saved successfully.", "Worksheet saved successfully. popup is present");
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.closeIcon);
        Utility_Functions.timeWait(2);
        Utility_Functions.xMouseClick(ownDriver, SpoPage.closeIcon);
        Utility_Functions.timeWait(4);
        commonObj.validateText(SpoPage.savedTag, "Saved", "Worksheet status turned to Saved tag");
        commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
    }

    public void assignedUser() {
        int val = ownDriver.findElements(SpoPage.assignedUserIf).size();
        if (ownDriver.findElements(SpoPage.assignedUserIf).get(val - 1).getAttribute("ng-reflect-model") == null) {
            sendKeys(SpoPage.assignedUserIf, properties.getProperty("SpoUserName"));
        }
    }

    /**
     * Keyword to Verify Duplicate WS
     */
    public void verifyDuplicateWS() {
        sendKeysAndTab(SpoPage.worksheetSheetTemplateName, Utility_Functions.xGetJsonData("WorksheetTempName"), "Name Your Worksheet");
        errorRepeatSteps();
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.invalidWSName);
        commonObj.validateText(SpoPage.invalidWSName, "Worksheet with this name already exists.", "Duplicate Worksheet can not create");
        handleDiscountFieldIfPresent();
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.invalidWSName);
        commonObj.validateText(SpoPage.invalidWSName, "Worksheet with this name already exists.", "Duplicate Worksheet can not create");
    }

    /**
     * Keyword to Modify Order Quantity
     */
    public void modifyOrderQty() {
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.disableAllFields);
        Utility_Functions.timeWait(2);
        sendKeys(ownDriver.findElements(SpoPage.orderQuantity).get(1), "99999999", "Modify First item Order Quantity to 999999");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(SpoPage.yellowModCol, "Border color changed to Yellow after Order quantity modification");
        sendKeys(ownDriver.findElements(SpoPage.orderQuantity).get(2), "999999999", "Modify Second item Order Quantity to 999999");
        Utility_Functions.xIsDisplayed(ownDriver, SpoPage.errorMsg);
    }

    /**
     * Keyword to Click Convert to PO Button
     */
    public void clickConcertPOBtn() {
        Utility_Functions.timeWait(2);
        click(SpoPage.convertPOBtn, "Click Convert Po Button");
    }

    /**
     * Keyword to Click Convert to PO Button
     */
    public void verifyOrderExceedError() {
        modifyOrderQty();
        //clickConcertPOBtn();
        Utility_Functions.timeWait(2);
        //commonObj.validateText(SpoPage.popUp, "Worksheet Order Total exceeds Maximum of 9,999,999.99 for PO Conversion. Please modify and try again.", "'Worksheet Order Total exceeds Maximum of $9,999,999.99 for PO Conversion. Please modify and try again.' error message is present");
        if (Utility_Functions.xIsDisplayed(ownDriver, SpoPage.xIcon)) {
            click(SpoPage.xIcon);
        }
        click(button("Back"));
        Utility_Functions.timeWait(5);
        commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
    }

    /**
     * Keyword to Assign Order Minimum and Freight Minimum to Vendor
     */
    public void assignOrderMinFreightMin() {
        String order = jsonData.getData("OrderMinimum");
        String freight = jsonData.getData("FreightMinimum");
        sendKeys(VendorInformationPage.actionCode, "I", "Enter 'I' into Action Code input field");
        sendKeys(VendorInformationPage.vendorNo, Utility_Functions.xGetJsonData("VendorNumber"), "Enter Vendor Number");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        sendKeys(VendorInformationPage.actionCode, "C", "Enter 'C' into Action Code input field");
        sendKeys(VendorInformationPage.vendorNo, Utility_Functions.xGetJsonData("VendorNumber"), "Enter Vendor Number");
        sendKeys(VendorInformationPage.minOrderCode, "$", "Enter Order Minimum Code as '$'");
        sendKeys(VendorInformationPage.minOrderQty, order + ".00", "Enter Order Minimum Quantity as " + order);
        sendKeys(VendorInformationPage.freightOrderCode, "$", "Enter Freight Minimum Code as '$'");
        sendKeys(VendorInformationPage.freightOrderQty, freight + ".00", "Enter Freight Minimum Quantity as " + freight);
        Utility_Functions.timeWait(2);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        inquireAssignOrderMinFreightMin();
    }

    public void validateAssignedField() {
        String order = jsonData.getData("OrderMinimum");
        String freight = jsonData.getData("FreightMinimum");
        String orderCode = ownDriver.findElement(VendorInformationPage.minOrderCode).getText().trim();
        String orderQty = ownDriver.findElement(VendorInformationPage.minOrderQty).getAttribute("value").trim();
        String freightCode = ownDriver.findElement(VendorInformationPage.freightOrderCode).getText().trim();
        String freightQty = ownDriver.findElement(VendorInformationPage.freightOrderQty).getAttribute("value").trim();
        Utility_Functions.xAssertEquals(report, orderCode, "$", "Minimum Order Code is present");
        Utility_Functions.xAssertEquals(report, orderQty, order + ".00", "Minimum Order Quantity is present");
        Utility_Functions.xAssertEquals(report, freightCode, "$", "Minimum Freight Code is present");
        Utility_Functions.xAssertEquals(report, freightQty, freight + ".00", "Minimum Freight Quantity is present");
    }

    /**
     * Keyword to Inquire Assign Order Minimum and Freight Minimum to Vendor
     */
    public void inquireAssignOrderMinFreightMin() {
        sendKeys(VendorInformationPage.actionCode, "I", "Enter 'I' into Action Code input field");
        sendKeys(VendorInformationPage.vendorNo, Utility_Functions.xGetJsonData("VendorNumber"), "Enter Vendor Number");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        String[] split = Utility_Functions.xGetJsonData("VendorNoForHeader").split(" ");
        String vendorName = split[2] + " " + split[3] + " " + split[4];
        commonObj.validateText(VendorInformationPage.vendorName, vendorName, "Vendor Name: " + vendorName + " is present");
        Utility_Functions.xScrollIntoView(ownDriver, VendorInformationPage.minOrderCode);
        validateAssignedField();
        click(ItemLedgerPage.btnExit, "Exit from page");
    }

    public void verifyOrderFreightMin() {
        String ord = printCurrency(Locale.US, jsonData.getData("OrderMinimum"));
        String order = ord.replace("$", "");
        String freght = printCurrency(Locale.US, jsonData.getData("FreightMinimum"));
        String freight = freght.replace("$", "");
        int size = ownDriver.findElements(SpoPage.minFreightOrder).size();
        String orderMin = ownDriver.findElements(SpoPage.minFreightOrder).get(size - 1).getText().trim();
        Utility_Functions.xAssertEquals(report, order, orderMin, "Order minimum is matches");
        String freightMin = ownDriver.findElements(SpoPage.minFreightOrder).get(size - 2).getText().trim();
        Utility_Functions.xAssertEquals(report, freight, freightMin, "Freight minimum is matches");
    }

    public void cancelButtonPO() {
        click(SpoPage.convertPOBtn, "Click Convert to PO button");
        Utility_Functions.timeWait(3);
        if (Utility_Functions.xIsDisplayed(ownDriver, SpoPage.exclamationWarn)) {
            commonObj.validateElementExists(SpoPage.exclamationWarn, "'MINIMUM ORDER NOT MET' Popup is present");
        }
        click(SpoPage.cancelBtnMinOrd, "Click Cancel Button");
    }

    public void saveAndConvertPOBtn() {
        click(SpoPage.convertPOBtn, "click Convert to PO button");
        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(ownDriver, SpoPage.exclamationWarn)) {
            commonObj.validateElementExists(SpoPage.exclamationWarn, "'MINIMUM ORDER NOT MET' Popup is present");
        }
        clickButton("Save and Convert to PO");
        Utility_Functions.timeWait(4);
        commonObj.validateText(SpoPage.popUp, "Worksheet saved successfully.", "Worksheet saved successfully. popup is present");
        commonObj.validateText(DriversPage.newDriverHeader, "FREIGHT CHARGES", "FREIGHT CHARGES pop-up is present");
    }

    /**
     * Keyword to verify cannot convert to PO when all items order quantities is zero
     */
    public void verifyOrderQuantityZero() {
        click(SpoPage.convertPOBtn, "Click Convert to PO button");
        Utility_Functions.timeWait(2);
        if (isDisplayed(By.xpath("//h2[text()='FREIGHT CHARGES']"))) {
            Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SpoPage.freightChargeCodes), 1);
            click(button(" Convert "));
            Utility_Functions.timeWait(2);
            click(button(" Return to Landing Page "));
        } else {
            commonObj.validateText(SpoPage.popUp, "Cannot convert to PO since all items order quantities is zero.", "'Cannot convert to PO since all items order quantities is zero.' message is present");
            click(SpoPage.xIcon);
            Utility_Functions.timeWait(2);
        }
    }

    /**
     * Keyword to navigate back to SPO page
     */
    public void navigateBackToSPOPage() {
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.closeIcon);
        Utility_Functions.timeWait(2);
        Utility_Functions.xClickHiddenElement(ownDriver, SpoPage.closeIcon);
        Utility_Functions.timeWait(2);
        commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
    }

    /**
     * Keyword to verify Order Minimum
     */
    public void verifyOrderMinimum() {
        verifyOrderFreightMin();
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.orderQuantity);
        sendKeys(ownDriver.findElements(SpoPage.orderQuantity).get(1), "1", "Modify Order Quantity");
        cancelButtonPO();
        saveAndConvertPOBtn();
        clickButton("Cancel");
        Utility_Functions.timeWait(5);
        commonObj.validateText(SpoPage.spoPageTitle, "SUGGESTED PURCHASE ORDERS", "SPO Screen Header is present");
    }

    public String printCurrency(Locale locale, String value) {
        double dbl = Double.parseDouble(value);
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        String currency = formatter.format(dbl);
        System.out.println(currency + " for the locale " + locale);
        return currency;
    }

    public void disableAndExpand() {
        Utility_Functions.xScrollIntoView(ownDriver, button(" Refresh Worksheet "));
        for (int i = 0; i < 2; i++) {
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_MINUS);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_MINUS);
            } catch (Exception e1) {
            }
        }
        clickButton(" Expand All ");
        Utility_Functions.timeWait(4);
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.isDisabledCalcMethod);
        commonObj.validateElementExists(SpoPage.isDisabledCalcMethod, "Calculation Method drop box is disabled");
        Utility_Functions.xScrollIntoView(ownDriver, button(" Refresh Worksheet "));
        click(SpoPage.disableAllFields, "Uncheck Disable All Fields");
        Utility_Functions.timeWait(3);
    }

    /**
     * Keyword to Verify the options available in "Calculation Method" dropdown box
     */
    public void verifyCalculationMethodOptions() {
        disableAndExpand();
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.calcMethod);
        String[] options = {"M - Manual", "X - Max", "S - Suggested Order Qty", "Q - Economic Order Qty"};
        int i = 0;
        for (String option : options) {
            commonObj.validateText(By.xpath("//option[text()='" + option + "']"), options[i], options[i] + " Option is present");
            i++;
        }
    }

    public double convertDouble(String str) {
        String unitCost = str.substring(1);
        System.out.println("unitCost: " + unitCost);
        double dbl = Double.parseDouble(unitCost);
        return dbl;
    }

    public double discountTotalCostCalc() {
        Utility_Functions.timeWait(3);
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.discountOrMultiplier);
        Utility_Functions.timeWait(3);
        sendKeys(SpoPage.discountOrMultiplier, "10", "Enter Discount");
        Utility_Functions.timeWait(2);
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.minusIcon);
        Utility_Functions.timeWait(2);
        String totalCost = ownDriver.findElement(SpoPage.totalCost).getText();
        double discount = convertDouble(totalCost);
        return discount;
    }

    /**
     * Keyword to Verify Total Cost Calculation
     */
    public void verifyTotalCostCalculation() {
        for (int i = 0; i < 2; i++) {
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_MINUS);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_MINUS);
                Utility_Functions.timeWait(2);
            } catch (Exception e1) {
            }
        }
        try {
            click(SpoPage.disableAllFields, "Uncheck Disable All Fields");
        } catch (Exception e) {
            Utility_Functions.xScrollIntoView(ownDriver, button(" Refresh Worksheet "));
            click(SpoPage.disableAllFields, "Uncheck Disable All Fields");
        }
        Utility_Functions.timeWait(3);
        try {
            click(SpoPage.plusIcon, "Click Expand Icon");
        } catch (Exception e) {
            Utility_Functions.xScrollIntoView(ownDriver, SpoPage.plusIcon);
            click(SpoPage.plusIcon, "Click Expand Icon");
        }
        Utility_Functions.timeWait(3);
        sendKeys(ownDriver.findElements(SpoPage.orderQuantity).get(0), "15", "Modify Order Quantity");
        Utility_Functions.timeWait(2);
        Utility_Functions.actionKey(Keys.TAB, ownDriver);
        Utility_Functions.timeWait(2);
        String unitCostVal = ownDriver.findElement(SpoPage.unitCostField).getAttribute("value");
        double unitCost = convertDouble(unitCostVal) * 15;
        double disc = Double.parseDouble(getAttribute(SpoPage.discountLineItem, "value"));
        double totalDisc = (unitCost * disc) / 100;
        double afterDisc = unitCost - totalDisc;
        String totalCost = ownDriver.findElement(SpoPage.totalCost).getText();
        Utility_Functions.xAssertEquals(report, afterDisc + "0", totalCost, "Total cost Calculation Matches ");
    }

    /**
     * Keyword to Verify std Package Quantity
     */
    public void stdPackageQty() {
        for (int i = 0; i < 2; i++) {
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_MINUS);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_MINUS);
                Utility_Functions.timeWait(2);
            } catch (Exception e1) {
            }
        }
        try {
            click(SpoPage.disableAllFields, "Uncheck Disable All Fields");
        } catch (Exception e) {
            Utility_Functions.xScrollIntoView(ownDriver, button(" Refresh Worksheet "));
            click(SpoPage.disableAllFields, "Uncheck Disable All Fields");
        }
        Utility_Functions.timeWait(3);
        try {
            click(SpoPage.plusIcon, "Click Expand Icon");
        } catch (Exception e) {
            Utility_Functions.xScrollIntoView(ownDriver, SpoPage.plusIcon);
            click(SpoPage.plusIcon, "Click Expand Icon");
        }
        Utility_Functions.timeWait(3);
        sendKeys(ownDriver.findElements(SpoPage.orderQuantity).get(0), "125", "Modify Order Quantity");
        Utility_Functions.timeWait(3);
        sendKeysAndTab(SpoPage.standardPackage, "100", "Update standard quantity");
        Utility_Functions.timeWait(4);
        if (Utility_Functions.xIsDisplayed(ownDriver, By.xpath("//*[contains(text(),'Worksheet saved successfully.')]"))) {
            commonObj.validateText(SpoPage.popUp, "Worksheet saved successfully.", "Worksheet saved successfully. popup is present");
        }
        Utility_Functions.timeWait(2);
        if (isDisplayed(SpoPage.notStdQty)) {
            Utility_Functions.timeWait(3);
            commonObj.validateText(SpoPage.notStdQty, "NOT A STANDARD PACKAGE QTY", "Popup is present");
        } else {
            click(SpoPage.saveWorksheetBtn, "Click Save Worksheet button");
        }
        try {
            click(SchedulePaymentPage.cancelButton, "Click Cancel button");
        } catch (Exception e) {
            Utility_Functions.xClickHiddenElement(ownDriver, ownDriver.findElements(By.xpath("//button[contains(text(),'Cancel')]")).get(1));
        }
        Utility_Functions.timeWait(2);
        click(SpoPage.saveWorksheetBtn, "Click Save Worksheet button");
        Utility_Functions.timeWait(2);
        clickButton(" Save ");
        Utility_Functions.timeWait(4);
        commonObj.validateText(SpoPage.popUp, "Worksheet saved successfully.", "Worksheet saved successfully. popup is present");
    }

    public double getData(String str) {
        By el = By.xpath("//td[contains(text(),'" + str + "')]");
        int size = ownDriver.findElements(el).size();
        double val2 = 0;
        double val;
        String data;
        for (int i = 0; i < size; i++) {
            if (str.equals("$")) {
                data = ownDriver.findElements(el).get(i).getText().replace(str, "").replace(",", "");
            } else {
                data = ownDriver.findElements(el).get(i).getText().replace(" " + str, "");
            }
            System.out.println(data);
            val = Double.parseDouble(data);
            System.out.println(val);
            double sum = val + val2;
            val2 = sum;
        }
        return val2;
    }

    /**
     * Keyword to Verify Order Total and Total Weight
     */
    public void orderWeightTotal() {
        Utility_Functions.timeWait(3);
        Utility_Functions.xScrollIntoView(ownDriver, SpoPage.plusIcon);
        double orderTotal = getData("$");
        double totalWeight = getData("lbs");
        int size = ownDriver.findElements(SpoPage.minFreightOrder).size();
        String order = ownDriver.findElements(SpoPage.minFreightOrder).get(size - 3).getText().trim();
        order = order.replace(",", "");
        Utility_Functions.xAssertEquals(report, orderTotal + "0", order, "order Total matches");
        int sz = ownDriver.findElements(SpoPage.calHead).size();
        String weight = ownDriver.findElements(SpoPage.calHead).get(sz - 2).getText();
        Utility_Functions.xAssertEquals(report, weight, totalWeight + "00", "Total Weight matches");
    }

    /**
     * Keyword to Verify Unit Weight
     */
    public void verifyUnitWeight() {
        Utility_Functions.timeWait(3);
        for (int i = 0; i < 2; i++) {
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_MINUS);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_MINUS);
                Utility_Functions.timeWait(2);
            } catch (Exception e1) {
            }
        }
        try {
            click(SpoPage.disableAllFields, "Uncheck Disable All Fields");
        } catch (Exception e) {
            Utility_Functions.xScrollIntoView(ownDriver, button(" Refresh Worksheet "));
            click(SpoPage.disableAllFields, "Uncheck Disable All Fields");
        }
        sendKeys(ownDriver.findElements(SpoPage.orderQuantity).get(1), "200", "Modify Order Quantity");
        Utility_Functions.timeWait(3);
        click(SpoPage.plusIcon, "Click on '+'");
        Utility_Functions.timeWait(4);
        int stdPack = 100;
        int lbsPack = 200;
        sendKeys(SpoPage.standardPackage, "" + stdPack + "", "Enter Std Package Qty");
        Utility_Functions.timeWait(4);
        sendKeys(SpoPage.standardPackageWeight, "" + lbsPack + "", "Enter Lbs/Std Package Qty");
        Utility_Functions.timeWait(4);
        int unitWeight = lbsPack / stdPack;
        String unitWeightEx = getText(By.xpath("//div[text()='" + unitWeight + ".000 lbs ']")).trim().replace(" lbs", "");
        Utility_Functions.xAssertEquals(report, unitWeightEx, "" + unitWeight + "" + ".000", "Unit Weight Matches");
        int totWeight = 200 * unitWeight;
        String totalWeight = getText(SpoPage.totalWeight).trim().replace(" lbs", "");
        Utility_Functions.xAssertEquals(report, totalWeight, "" + totWeight + "" + ".000", "Total Weight Matches");
        int sz = ownDriver.findElements(SpoPage.calHead).size();
        String weight = ownDriver.findElements(SpoPage.calHead).get(sz - 2).getText();
        Utility_Functions.xAssertEquals(report, weight, "" + totWeight + "" + ".000", "Current LBS matches");
    }

    public void deleteAllWS() throws AWTException {
        navigateToSPO();
        selectCompany();
        Utility_Functions.timeWait(10);
        while (ownDriver.findElement(By.xpath("//a[@class='header-name-link']")).isDisplayed()) {
            int size = ownDriver.findElements(By.xpath("//a[@class='header-name-link']")).size();
            for (int i = 0; i < size; i++) {
                Utility_Functions.xScrollWindowTop(ownDriver);
                Utility_Functions.timeWait(3);
                click(ownDriver.findElements(By.xpath("//a[@class='header-name-link']")).get(i));
                Utility_Functions.timeWait(7);
                navigateEditWSTemp();
                verifyDeleteSavedWS();
            }
        }
    }
}