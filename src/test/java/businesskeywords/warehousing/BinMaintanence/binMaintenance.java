package businesskeywords.warehousing.BinMaintanence;

import businesskeywords.common.Login;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.SalesOrders.SalesOrdersPage;
import pages.common.MasterPage;
import pages.inventory.ItemMasterPage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import pages.warehouse.BinMaintenance.BinMaintenancePage;
import pages.warehouse.DriversPage;
import pages.warehouse.TruckPage;
import supportLibraries.Utility_Functions;

import java.util.ArrayList;
import java.util.List;

public class binMaintenance extends ReusableLib {
    CommonActions commonObj;
    businesskeywords.SPO.Spo sp;
    Login login;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public binMaintenance(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        sp = new businesskeywords.SPO.Spo(helper);
        login = new Login(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * Keyword to Navigate to Bin Maintenance
     */
    public void navigateToBinMain() {
        String url;
        if (System.getProperty("url") == null) {
            url = properties.getProperty("binMaintenanceURL");
        } else {
            String prodUrl = System.getProperty("url");
            System.out.println("Taken URL from Config File......" + properties.getProperty(prodUrl));
            url = properties.getProperty(prodUrl);

        }
        ownDriver.get(url);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to verify UI of Item-Bin Maintenance page when no search filters were applied
     */
    public void binMaintenanceUI() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//h2"),"");
        commonObj.validateText(By.xpath("//h2"), "Item-Bin Maintenance", "Item-Bin Maintenance Header is present");
        commonObj.validateText(tabs("Item Maintenance"), "Item Maintenance", "Item Maintenance tab is present");
        commonObj.validateText(tabs("Bin Maintenance"), "Bin Maintenance", "Bin Maintenance tab is present");
        verifyIcons();
        verifySearchFilterFields();
    }

    /**
     * Keyword to click [Bin Maintenance] tab
     */
    public void clickBinMaintenanceTab() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,tabs("Bin Maintenance"), "Click on [Bin Maintenance] tab");
        waitForElementDisappear(MasterPage.loadingSpinner, globalWait);
    }

    /**
     * Keyword to verify UI of Item-Bin Maintenance > Bin Maintenance tab when no search filters applied
     */
    public void binMaintenanceUIDefault() {
        clickBinMaintenanceTab();
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//h2"),"Item-Bin Maintenance Header is present");
        commonObj.validateText(tabs("Item Maintenance"), "Item Maintenance", "Item Maintenance tab is present");
        commonObj.validateText(tabs("Bin Maintenance"), "Bin Maintenance", "Bin Maintenance tab is present");
        commonObj.validateText(tabs("Apply filters to see results."), "Apply filters to see results.", "label [Apply filters to see results.] is present");
        verifyIcons();
        verifySearchFilterFields();
    }

    /**
     * Keyword to verify Availability of icons present at left corner of the page
     */
    public void verifyIcons() {
        commonObj.validateElementExists(BinMaintenancePage.menuIcon, "Main menu icon is present at left top corner of the page");
        commonObj.validateElementExists(BinMaintenancePage.inboxIcon, "Inbox icon is present at left top corner of the page");
        commonObj.validateElementExists(BinMaintenancePage.shippingManifestIcon, "Shipping Manifest icon is present at left top corner of the page");
        commonObj.validateElementExists(BinMaintenancePage.printIcon, "Print icon is present at left top corner of the page");
        commonObj.validateElementExists(BinMaintenancePage.gearIcon, "Gear icon is present at right top of the page");
        commonObj.validateElementExists(BinMaintenancePage.logOutIcon, "Logout icon is present at left top corner of the page");
    }

    /**
     * Keyword to verify Availability of fields on the search filter
     */
    public void verifySearchFilterFields() {
        SearchCheckBox();
        String[] field = {"Item Number", "Item Description", "Zone", "Bin Condition", "IOV", "Bin Type", "Manufacturer", "Product Group", "Vendor", "Total On Hand Qty"};
        for (String label : field) {
            commonObj.validateElementExists(By.xpath("//label[contains(text(),'" + label + "')]/parent::div/descendant::input"), label + " is present");
        }
        commonObj.validateElementExists(BinMaintenancePage.applyFilter, "Apply filter button is present");
        commonObj.validateElementExists(BinMaintenancePage.clearFilter, "Clear All filter button is present");
    }

    /**
     * Keyword to verify Availability of fields on the search filter after clicking [Bin Maintenance] tab
     */
    public void verifyBinMaintenanceTabSearchFilterFields() {
        SearchCheckBox();
        String[] field = {"Item Number", "Item Description", "Zone", "Bin Condition", "IOV", "Bin Type", "Bin Start", "Bin Stop", "MF", "PD", "VN"};
        for (String label : field) {
            commonObj.validateElementExists(By.xpath("//label[contains(text(),'" + label + "')]/parent::div/descendant::input"), label + " is present");
        }
        commonObj.validateElementExists(BinMaintenancePage.applyFilter, "Apply filter button is present");
        commonObj.validateElementExists(BinMaintenancePage.clearFilter, "Clear All filter button is present");
    }

    public void SearchCheckBox() {
        commonObj.validateText(By.xpath("//h1"), "Search Filters", "Search Filters header is present");
        commonObj.validateText(validateCheckBox("Exclude * Items"), "Exclude * Items", "Exclude * Items check box is present");
        commonObj.validateText(validateCheckBox("Exclude Job Items"), "Exclude Job Items", "Exclude Job Items check box is present");
        commonObj.validateText(validateCheckBox("Exclude ZZ98 Items"), "Exclude ZZ98 Items", "Exclude ZZ98 Items check box is present");
        commonObj.validateText(validateCheckBox("Contains"), "Contains", "Contains check box is present");
    }

    public By tabs(String tab) {
        return By.xpath("//span[text()='" + tab + "']");
    }

    public By validateCheckBox(String label) {
        return By.xpath("//input[@type='checkbox']/following-sibling::label[text()='" + label + "']");
    }

    public By button(String buttonName) {
        return By.xpath("//button[text()='" + buttonName + "']");
    }

    public By label(String labelName) {
        return By.xpath("//div[text()='" + labelName + "']");
    }

    public By getColumnData(String colName) {
        return By.xpath("//table/tbody/tr[1]/td[count(//table/thead/tr/th[contains(text(),'" + colName + "')]/preceding-sibling::th)+1]/span");
    }

    public By getStatus(String binLocation) {
        return By.xpath("//td[contains(text(),'" + binLocation + "')]/following-sibling::td/span");
    }

    public By getZone(String binLocation) {
        return By.xpath("//td[contains(text(),'" + binLocation + "')]/following-sibling::td");
    }

    public By getBinLocation(String binType) {
        return By.xpath("//select[contains(@ng-reflect-model,'" + binType + "')]/ancestor::td//preceding-sibling::td");
    }

    public By deleteBinLocationByType(String binType) {
        return By.xpath("//select[@ng-reflect-model='" + binType + "']/ancestor::tr/descendant::i[@title='Delete']");
    }

    public By deleteBinLocation(String binLocation) {
        return By.xpath("//td[contains(text(),'" + binLocation + "')]/ancestor::tr/descendant::i[@title='Delete']");
    }

    public By zoneDisabledFields(String label, String text) {
        return By.xpath("//label[contains(text(),'" + label + "')]/following-sibling::input[@disabled and contains(@ng-reflect-model,'" + text + "')]");
    }

    public By saveButtonEditedZone(String zoneName) {
        return By.xpath("//input[@ng-reflect-model='" + zoneName + "']/ancestor::div/following-sibling::div[@ng-reflect-ng-class='show']/descendant::button");
    }

    public By pickSequence(String zoneName) {
        return By.xpath("//input[@ng-reflect-model='" + zoneName + "']/ancestor::div/following-sibling::div[@ng-reflect-ng-class='show']/descendant::input");
    }

    public By editBinsButton(int binCount) {
        return By.xpath("//button[contains(text(),'Edit " + binCount + " Bin(s)')]");
    }

    /**
     * Keyword to verify Availability of Menu Icon
     */
    public void verifyAvailMenuIcon() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.menuIcon, "Click on main menu");
        String[] field = {"BIN LOCATIONS", "Receiving", "SHIPPING MANIFESTS", "Exceptions Queue", "Print Documents and Labels"};
        for (String label : field) {
            commonObj.validateText(tabs(label), label, label + " option is present");
        }
    }

    public void verifyApplyFilter() {
        commonObj.validateElementExists(TruckPage.searchFilterPanelTitle, "Search filter is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.applyFilter, "Click Apply filter button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(tabs("Apply filters to see results."), "Apply filters to see results.", "'Apply filters to see results.' message is present");
    }

    public void verifyClearAllFilter() {
        click(TruckPage.filterSearch, "Click Search icon");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Clear All Filter button is disabled");
        click(BinMaintenancePage.itemNumberContains, "Enable item Number Contains check box");
        Utility_Functions.timeWait(2);
        Boolean bl = Utility_Functions.xIsDisplayed(ownDriver, BinMaintenancePage.buttonDis);
        Utility_Functions.xAssertEquals(report, bl, false, "Clear All Filter button is enabled");
        Utility_Functions.xClickHiddenElement(ownDriver, BinMaintenancePage.clearFilter);
        Utility_Functions.timeWait(2);
        Boolean bl1 = ownDriver.findElement(BinMaintenancePage.itemNumberContains).isSelected();
        Utility_Functions.xAssertEquals(report, bl1, false, "After Click Clear All Filter, item Number Contains check box is disabled");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Clear All Filter button is disabled");
    }

    /**
     * Keyword to verify Apply Filter and Clear All Filter
     */
    public void verifyApplyClearAllFilter() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.filtersCrossIcon, "Click search close icon");
        commonObj.validateElementExists(BinMaintenancePage.searchIcon, "Search icon is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.filterSearch, "Click Search icon");
        verifyApplyFilter();
        verifyClearAllFilter();
    }

    public void enableAsteriskItem(By by, String decision) {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,by, decision + " exclude Asterisk Items check box");
        click(BinMaintenancePage.itemNumberContains, decision + " contains check box");
        click(BinMaintenancePage.applyFilter, "Click Apply filter");
    }

    /**
     * Keyword to verify exclude * Items
     */
    public void verifyExcludeItems(By by, String checkBoxName) {
        enableAsteriskItem(by, "Enable");
        Utility_Functions.timeWait(2);
        commonObj.validateText(tabs("Item Number"), "Item Number", "'Item Number' filtered value is present at right top corner of the page");
        commonObj.validateText(tabs(checkBoxName), checkBoxName, checkBoxName + " filtered value is present at right top corner of the page");
        commonObj.validateText(tabs("Clear Filters "), "Clear Filters", "'Clear Filters ' filtered value is present at right top corner of the page");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.filterSearch, "Click Search icon");
        Utility_Functions.timeWait(2);
        enableAsteriskItem(by, "Disable");
        Utility_Functions.xAssertEquals(report, Utility_Functions.xIsDisplayed(ownDriver, tabs("Item Number")), false, "Item number result Filter is removed");
        Utility_Functions.xAssertEquals(report, Utility_Functions.xIsDisplayed(ownDriver, tabs(checkBoxName)), false, checkBoxName + " result Filter is removed");
        Utility_Functions.xAssertEquals(report, Utility_Functions.xIsDisplayed(ownDriver, tabs("Clear Filters ")), false, "Clear Filters result Filter is removed");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.filterSearch, "Click Search icon");
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to verify check box Filter
     */
    public void verifyExcludeAstJobZZ98Item() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.excludeAsteriskItems, "Exclude * Items");
        verifyExcludeItems(BinMaintenancePage.excludeAsteriskItems, "Exclude * Items");
        verifyExcludeItems(BinMaintenancePage.includeJobItems, "Exclude Job Items");
        verifyExcludeItems(BinMaintenancePage.excludeZZ98Items, "Exclude ZZ98 Items");
    }

    /**
     * Keyword to verify Contains check box Filter
     */
    public void verifyContainsCheckBox() {
        String itemNumber = jsonData.getData("itemNo");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemNumber,"");
        sendKeys(BinMaintenancePage.itemNumber, itemNumber, "Enter " + itemNumber + " into Item number text field");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.filterSearch, "Click Search icon");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemNumberContains, "Enable item Number Contains check box");
        Utility_Functions.timeWait(2);
        Boolean bl1 = ownDriver.findElement(BinMaintenancePage.itemNumberContains).isSelected();
        Utility_Functions.xAssertEquals(report, bl1, true, "After Click Clear All Filter, item Number Contains check box is enabled");
        Utility_Functions.timeWait(2);
        Utility_Functions.xAssertEquals(report, ownDriver.findElement(BinMaintenancePage.itemNumber).getAttribute("ng-reflect-model"), "~" + itemNumber, "");
    }

    /**
     * Keyword to Verify Suggested Matches pop-up message
     */
    public void verifySuggestedMatchesPopUp() {
        String itemNumber = jsonData.getData("itemNo");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemNumber,"");
        sendKeys(BinMaintenancePage.itemNumber, itemNumber, "Enter " + itemNumber + " into Item number text field");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.matchesHeader,"");
        commonObj.validateText(BinMaintenancePage.matchesHeader, "SUGGESTED MATCHES", "'SUGGESTED MATCHES' popup message is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,getColumnData("Item Number"), "Click Item Number");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemBinManItemDet,"");
        commonObj.validateText(BinMaintenancePage.itemBinManItemDet, "Item-Bin Maintenance - Item Details", "'Item-Bin Maintenance - Item Details' header is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Back"), "Click on back button");
        Utility_Functions.timeWait(2);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//h2"), "Navigate back to 'Item-Bin Maintenance' Page");
    }

    /**
     * Keyword to Verify the negative Scenarios for Item Number input field
     */
    public void verifyItemNoField() {
        String negativeValue = jsonData.getData("negativeValue");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemNumber,"");
        sendKeys(BinMaintenancePage.itemNumber, negativeValue, "Enter " + negativeValue + " into Item number text field");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.timeWait(3);
        commonObj.validateText(tabs("There are currently no results."), "There are currently no results.", "'There are currently no results.' message is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.filterSearch, "Click Search icon");
        Utility_Functions.timeWait(2);
        String alphaNum = jsonData.getData("alphaNumeric");
        sendKeys(BinMaintenancePage.itemNumber, alphaNum, "Enter " + alphaNum + " into Item number text field");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.timeWait(3);
        commonObj.validateText(tabs("There are currently no results."), "There are currently no results.", "'There are currently no results.' message is present");
    }

    public void validateAndBack() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemBinManItemDet,"");
        commonObj.validateText(BinMaintenancePage.itemBinManItemDet, "Item-Bin Maintenance - Item Details", "'Item-Bin Maintenance - Item Details' header is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Back"), "Click on back button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//h2"), "Item-Bin Maintenance", "Navigate back to 'Item-Bin Maintenance' Page");
    }

    /**
     * Keyword to Verify the Item description filter
     */
    public void verifyItemDescriptionFilter() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemNumberContains, "Enable Contains check box");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.filterSearch, "Click Search icon");
        Utility_Functions.timeWait(2);
        String desc = ownDriver.findElement(By.xpath("//td/div")).getText().trim();
        Utility_Functions.xUpdateJson("ItemNmDescription", desc);
        sendKeys(BinMaintenancePage.itemDescription, desc, "Enter description");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        if (ownDriver.findElements(BinMaintenancePage.itemBinManItemDet).size() > 1) {
            Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//td/a"),"");
        }
        validateAndBack();
    }

    /**
     * Keyword to Verify the negative Scenarios for Item Description input field
     */
    public void verifyItemDescriptionField() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.filterSearch, "Click Search icon");
        commonObj.validateText(tabs("Apply filters to see results."), "Apply filters to see results.", "'Apply filters to see results.' message is present");
        Utility_Functions.timeWait(2);
        sendKeys(BinMaintenancePage.itemDescription, "A", "Enter a single character 'A'");
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Apply Filter button is disabled");
        sendKeys(BinMaintenancePage.itemDescription, "AA", "Enter a single character 'AA'");
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Apply Filter button is disabled");
        String itemDesc = Utility_Functions.xGetJsonData("ItemNmDescription");
        sendKeys(BinMaintenancePage.itemDescription, itemDesc, "Enter a single character 'A'");
        click(BinMaintenancePage.applyFilter, "Click Apply Filter button");
        validateAndBack();
    }

    public void enableContainsFilter() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemNumberContains, "Enable Contains check box");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemCount,"Click Item COunt");
        String itemCount = Utility_Functions.getText(ownDriver, BinMaintenancePage.itemCount);
        commonObj.validateElementExists(BinMaintenancePage.itemCount, "Total item present on Item Maintenance Tab: " + itemCount);
    }

    public void verifyClearAllButton() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemNumberContains, "Enable Contains check box");
        click(BinMaintenancePage.excludeAsteriskItems, "Enable Exclude * Items");
        click(BinMaintenancePage.includeJobItems, "Enable Exclude Job Items");
        click(BinMaintenancePage.excludeZZ98Items, "Enable Exclude ZZ98 Items");
        Utility_Functions.timeWait(2);
        Utility_Functions.xClickHiddenElement(ownDriver, BinMaintenancePage.clearFilter);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(BinMaintenancePage.isCheckBoxEnabled, "All filters are cleared");
    }

    public void verifyOnHand() {
        String symbl = getAttribute(BinMaintenancePage.onHand, "ng-reflect-model");
        Utility_Functions.xAssertEquals(report, symbl, "=", "By default On Hand Symbol '='");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.onHand,"");
        Utility_Functions.timeWait(5);
        String[] syms = {"=", "<", ">"};
        for (String sym : syms) {
            if (Utility_Functions.xIsDisplayed(ownDriver, TruckPage.filterSearch)) {
                Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.filterSearch, "Click Search icon");
                Utility_Functions.timeWait(2);
            }
            if (!(sym.equals("="))) {
                Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.onHand,"");
            }
            commonObj.validateText(tabs(sym), sym, sym + " is present");
            Utility_Functions.waitTillClickHardSleep(report,ownDriver,tabs(sym), sym + "is selected");
            sendKeys(BinMaintenancePage.onHandFilter, "-1", "Enter on hand quantity");
            click(BinMaintenancePage.applyFilter, "Click Apply filter");
            Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//h2"),"");
            if (Utility_Functions.xIsDisplayed(ownDriver, By.xpath("//td[text()='-1']"))) {
                commonObj.validateText(By.xpath("//td[text()='-1']"), "-1", "On Hand is filtered");
            }
        }
    }

    /**
     * Keyword to Verify the UI of the page when Item Numbers are available
     */
    public void verifyUIItemNumbersAreAvailable() {
        enableContainsFilter();
        commonObj.validateText(BinMaintenancePage.selectAllCheckbox, "Select All Items", "'Select All Items' check box is present");
        String[] actText = {"Item Number", "Description", "Manufacturer", "Product Group", "Vendor", "Bins", "On Hand"};
        List<WebElement> els = ownDriver.findElements(By.xpath("//th"));
        for (int i = 0; i < 4; i++) {
            Utility_Functions.xAssertEquals(report, els.get(i).getText().trim(), actText[i], "");
            i++;
        }
        commonObj.validateText(button(" Export to Excel"), "Export to Excel", "'Export to Excel' button is present");
        commonObj.validateText(button(" Labels "), "Labels", "'Labels' button is present");
    }

    /**
     * Keyword to verify Page Count
     */
    public void valPageCount(int pageNum) {
        Utility_Functions.xScrollWindow(ownDriver);
        Utility_Functions.timeWait(2);
        try {
            click(By.xpath("//nav[@id='itemDetailsPagination1']/descendant::span[text()='" + pageNum + "']"), "Click on '" + pageNum + "' Present below the Left corner of the page");
        } catch (Exception e) {
            click(By.xpath("//span[text()='" + pageNum + "']"), "Click on '" + pageNum + "' Present below the Left corner of the page");
        }
        int ItemCount = ownDriver.findElements(BinMaintenancePage.itemCountSP).size();
        if (ItemCount == pageNum) {
            Utility_Functions.xAssertEquals(report, "" + ItemCount + "", "" + pageNum + "", "'" + pageNum + "' is in disable state and showing " + pageNum + " Item Count");
        } else {
            commonObj.validateElementExists(BinMaintenancePage.itemCountSP, "Total Item count is " + ItemCount + "");
        }
    }

    /**
     * Keyword to Verify functionality of Page Count
     */
    public void funPageCountItemMaintenance() {
        Utility_Functions.xScrollWindow(ownDriver);
        Utility_Functions.xScrollWindow(ownDriver);
        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(ownDriver, DriversPage.onePage)) {
            int itemCount = ownDriver.findElements(BinMaintenancePage.itemCountSP).size();
            commonObj.validateText(DriversPage.onePage, "of 1", "One page is available having Item count " + itemCount + "");
        } else {
            valPageCount(10);
            valPageCount(15);
            valPageCount(30);
            valPageCount(30);
            valPageCount(10);
        }
    }

    /**
     * Keyword to verify Page Count for [Bin Maintenance]
     */
    public void valPageCountBins(int pageNum) {
        Utility_Functions.xScrollWindow(ownDriver);
        Utility_Functions.timeWait(2);
        click(By.xpath("//span[text()='" + pageNum + "']"), "Click on '" + pageNum + "' Present below the Left corner of the page");
        int ItemCount = ownDriver.findElements(BinMaintenancePage.binsCountSP).size();
        if (ItemCount == pageNum) {
            Utility_Functions.xAssertEquals(report, "" + ItemCount + "", "" + pageNum + "", "'" + pageNum + "' is in disable state and showing " + pageNum + " Bin Count");
        } else {
            commonObj.validateElementExists(BinMaintenancePage.binsCountSP, "Total Bin count is " + ItemCount + "");
        }
    }

    /**
     * Keyword to Verify functionality of Page Count for [Bin Maintenance]
     */
    public void funPageCountBinMaintenance() {
        Utility_Functions.xScrollWindow(ownDriver);
        Utility_Functions.xScrollWindow(ownDriver);
        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(ownDriver, DriversPage.onePage)) {
            int itemCount = ownDriver.findElements(BinMaintenancePage.binsCountSP).size();
            commonObj.validateText(DriversPage.onePage, "of 1", "One page is available having Item count " + itemCount + "");
        } else {
            valPageCountBins(10);
            valPageCountBins(15);
            valPageCountBins(30);
            valPageCountBins(30);
            valPageCountBins(10);
        }
    }

    /**
     * Keyword to Verify Pagination against current page
     */
    public void selectPage(int actPageNo, String expPage, String arrowIcon) {
        try {
            click(ownDriver.findElements(BinMaintenancePage.pageArrow).get(actPageNo), "Click on " + arrowIcon + " Present below the Right Corner of the page");
            Utility_Functions.xScrollWindow(ownDriver);
            String pageNo = ownDriver.findElement(BinMaintenancePage.currentPage).getAttribute("ng-reflect-model");
            Utility_Functions.xAssertEquals(report, pageNo, expPage, "Moved to " + pageNo + " Page");
        } catch (Exception e) {
            click(ownDriver.findElements(DriversPage.pageArrow).get(actPageNo), "Click on " + arrowIcon + " Present below the Right Corner of the page");
            Utility_Functions.xScrollWindow(ownDriver);
            String pageNo = ownDriver.findElement(TruckPage.currentPage).getAttribute("ng-reflect-model");
            Utility_Functions.xAssertEquals(report, pageNo, expPage, "Moved to " + pageNo + " Page");
        }
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to Verify functionality of Pagination
     */
    public void paginationItemMaintenance() {
        int size = 2;
        Utility_Functions.xScrollWindow(ownDriver);
        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(ownDriver, DriversPage.onePage)) {
            commonObj.validateText(DriversPage.onePage, "of 1", "One page is available");
        } else {
            try {
                click(ownDriver.findElements(BinMaintenancePage.pageArrow).get(2));
                Utility_Functions.xScrollWindow(ownDriver);
                click(ownDriver.findElements(BinMaintenancePage.pageArrow).get(0));
            } catch (Exception e) {
                click(ownDriver.findElements(DriversPage.pageArrow).get(2));
                Utility_Functions.xScrollWindow(ownDriver);
                click(ownDriver.findElements(DriversPage.pageArrow).get(0));
            }
            Utility_Functions.xScrollWindow(ownDriver);
            selectPage(2, "2", "Right Arrow (>)");
            selectPage(1, "1", "Left Arrow (<)");
            try {
                click(ownDriver.findElements(BinMaintenancePage.pageArrow).get(3), "Click on " + 3 + " Present below the Right Corner of the page");
                Utility_Functions.xScrollWindow(ownDriver);
                String pageNo = ownDriver.findElement(BinMaintenancePage.currentPage).getAttribute("ng-reflect-model");
                Utility_Functions.xAssertEquals(report, pageNo, pageNo, "Moved to " + pageNo + " Page");
            } catch (Exception e) {
                click(ownDriver.findElements(DriversPage.pageArrow).get(3), "Click on " + 3 + " Present below the Right Corner of the page");
                Utility_Functions.xScrollWindow(ownDriver);
                String pageNo = ownDriver.findElement(TruckPage.currentPage).getAttribute("ng-reflect-model");
                Utility_Functions.xAssertEquals(report, pageNo, pageNo, "Moved to " + pageNo + " Page");
            }
            Utility_Functions.timeWait(2);
            selectPage(0, "1", "Left double Arrow (<<)");
        }
    }

    /**
     * Keyword to Verify functionality of Zone drop down
     */
    public void verifyZoneDropDown() {
        int size = ownDriver.findElements(BinMaintenancePage.zoneDropFilter).size();
        for (int i = 1; i < size; i++) {
            String option = ownDriver.findElements(BinMaintenancePage.zoneIdDropDown).get(i).getText().trim();
            Utility_Functions.xSelectDropdownByNameIfAvlbl(ownDriver, report, ownDriver.findElement(BinMaintenancePage.zoneIdDropDown), option, "Select '" + option + "' option from the drop down ");
            click(BinMaintenancePage.applyFilter, "Click apply filter");
            Utility_Functions.timeWait(3);
            String itemCount = Utility_Functions.getText(ownDriver, BinMaintenancePage.itemCount);
            commonObj.validateElementExists(BinMaintenancePage.itemCount, "Total item present on Item Maintenance Tab: " + itemCount);
            validateItemHeader();
            //commonObj.validateElementExists(By.xpath("//td[contains(text(),'')]"));

        }
    }

    /**
     * Keyword to Verify the functionality of Select All Items Checkbox
     */
    public void verifySelectAllCheckBox() {
        click(BinMaintenancePage.selectAllCheckbox, "Enable 'Select All Items' check box");
        Utility_Functions.timeWait(2);
        int size = ownDriver.findElements(BinMaintenancePage.highlightRow).size();
        Utility_Functions.xAssertEquals(report, size, 10, "All items are Selected");
        Utility_Functions.timeWait(2);
        click(By.xpath("//tr[2]"), "Deselect one item from the list");
        Utility_Functions.timeWait(2);
        Utility_Functions.xAssertEquals(report, ownDriver.findElements(BinMaintenancePage.highlightRow).size(), 9, "One record is Deselected");
        click(BinMaintenancePage.selectAllCheckbox, "Click 'Select All Items' check box");
        Utility_Functions.timeWait(2);
        click(BinMaintenancePage.selectAllCheckbox, "Click 'Deselect All Items' check box");
        Utility_Functions.timeWait(2);
        Boolean count = Utility_Functions.xIsDisplayed(ownDriver, BinMaintenancePage.highlightRow);
        Utility_Functions.xAssertEquals(report, count, false, "All items are Deselected");
        Utility_Functions.timeWait(2);
        click(By.xpath("//tr[2]"), "Select one item from the list");
        Utility_Functions.timeWait(2);
        Utility_Functions.xAssertEquals(report, ownDriver.findElements(BinMaintenancePage.highlightRow).size(), 1, "One record is Selected");
    }

    public void validateNegQty() {
        click(button(" Labels "), "Click Label button");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.selectAllPop, "Click select All Items");
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Print button is disabled");
        sendKeys(BinMaintenancePage.printQty0, "Auto$%", "Enter string 'Auto$' into Qty text field");
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Print button is disabled");
        sendKeys(BinMaintenancePage.printQty0, "-12", "Enter string '-12' into Qty text field");
        Utility_Functions.timeWait(2);
        String val = getAttribute(BinMaintenancePage.printQty0, "ng-reflect-model");
        Utility_Functions.xAssertEquals(report, val, "10", "Quantity adjusted to 10");
        click(button("Print"), "Click Print Button");
        Utility_Functions.timeWait(3);
        commonObj.validateText(BinMaintenancePage.toaster, "Labels sent to printer.", "Toaster 'Labels sent to printer.' is present");
    }

    /**
     * Keyword to Verify the functionality of Labels button
     */
    public void verifyLabelButton() {
        for (int i = 1; i < 4; i++) {
            Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//tr[" + i + "]"), "Select item from the list");
            Utility_Functions.timeWait(2);
        }
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button(" Labels "), "Click Label button");
        commonObj.validateText(DriversPage.newDriverHeader, "Print Labels", "'Print Labels' popup is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Cancel "), "Click Cancel Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//h2"), "Item-Bin Maintenance", "Item-Bin Maintenance Header is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button(" Labels "), "Click Label button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(DriversPage.newDriverHeader, "Print Labels", "'Print Labels' popup is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Print"), "Click Print Button");
        Utility_Functions.timeWait(3);
        commonObj.validateText(BinMaintenancePage.toaster, "Labels sent to printer.", "Toaster 'Labels sent to printer.' is present");
        validateNegQty();
    }

    public ArrayList<String> itemDetails() {
        ArrayList<String> setVal = new ArrayList();
        for (int i = 1; i <= 6; i++) {
            String val = getText(By.xpath("(//td/*)[" + i + "]")).trim();
            setVal.add(val);
        }
        String bin = getText(By.xpath("(//td)[6]")).trim();
        String onHand = getText(By.xpath("(//td)[7]")).trim();
        setVal.add(bin);
        setVal.add(onHand);
        return setVal;
    }

    /**
     * Keyword to Verify the Item Details
     */
    public void verifyItemDetailsMaster() {
        ArrayList val = itemDetails();
        login.launchApp();
        login.siteLogin();
        sp.validateItemMasterTitle();
        sendKeys(SalesOrdersPage.itemNumber, val.get(0).toString(), "Enter item number");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.timeWait(4);
        String[] manufacturerCode = getAttribute(ItemMasterPage.manufacturerCode, "value").trim().split("-");
        String[] productCode = getAttribute(ItemMasterPage.productCode, "value").trim().split("-");
        String[] vendorCode = getAttribute(ItemMasterPage.vendorCode, "value").trim().split("-");
        String desc = getAttribute(ItemMasterPage.itemDesc1, "value").trim();
        Utility_Functions.xAssertEquals(report, val.get(1).toString().trim(), desc, "Description ");
        Utility_Functions.xAssertEquals(report, val.get(3).toString().trim(), manufacturerCode[0].trim(), "Manufacture code ");
        Utility_Functions.xAssertEquals(report, val.get(4).toString().trim(), productCode[0].trim(), "Product code ");
        Utility_Functions.xAssertEquals(report, val.get(5).toString().trim(), vendorCode[0].trim(), "vendor code ");
        commonObj.validateText(BinMaintenancePage.outQtyOnHand, val.get(7).toString(), "On hand quantity matches");
        click(SpecialPriceAllowancePage.btnExit);
    }

    public void validateItemHeader() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//td/a"),"");
        Utility_Functions.timeWait(3);
        commonObj.validateText(BinMaintenancePage.itemBinManItemDet, "Item-Bin Maintenance - Item Details", "Item-Bin Maintenance - Item Details Header is present");
    }

    /**
     * Keyword to Verify the functionality of Pagination
     */
    public void verifyItemDetailsUI() {
        validateItemHeader();
        commonObj.validateText(label("Item Number"), "Item Number", "Item Number label is present");
        commonObj.validateText(label("Item Description"), "Item Description", "Item Description label is present");
        commonObj.validateText(label("Bins"), "Bins", "Bins label is present");
        verifyIcons();
        String[] actText = {"Bin Location", "Zone", "Status", "Bin Type", "Bin Min", "Bin Max", "Action"};
        for (int i = 0; i <= 6; i++) {
            Utility_Functions.xAssertEquals(report, ownDriver.findElement(By.xpath("//th[contains(text(),'" + actText[i] + "')]")).getText().trim(), actText[i], "");
            i++;
        }
        String[] actButton = {"Back", " Edit Bin ", "Create Bin", "Add Bin"};
        for (int i = 0; i < 4; i++) {
            Utility_Functions.xAssertEquals(report, ownDriver.findElement(button(actButton[i])).getText(), actButton[i], "");
            i++;
        }
    }

    /**
     * Keyword to Verify the functionality of select/Deselect items
     */
    public void verifySelectDeselectItem() {
        {
            Utility_Functions.waitTillClickHardSleep(report,ownDriver,getBinLocation(""), "Select item from the list");
            Utility_Functions.timeWait(2);
            Utility_Functions.xAssertEquals(report, ownDriver.findElements(BinMaintenancePage.highlightRow).size(), 1, "One record is Selected");
            Utility_Functions.waitTillClickHardSleep(report,ownDriver,getBinLocation(""), "Select item from the list");
            Utility_Functions.timeWait(2);
            Boolean count = Utility_Functions.xIsDisplayed(ownDriver, BinMaintenancePage.highlightRow);
            Utility_Functions.xAssertEquals(report, count, false, "item is Deselected");
        }
    }

    /**
     * Keyword to Verify secondary options from Bin Type Dropdown
     */
    public void verifySecondaryOptionsBinType() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemNumberContains, "Enable Contains check box");
        click(BinMaintenancePage.binType, "Click Bin Type drop down");
        click(tabs("Primary"), "Click Primary option");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//h2"),"");
        if (Utility_Functions.xIsDisplayed(ownDriver, By.xpath("//td/a"))) {
            validateItemHeader();
        }
        Utility_Functions.xSelectDropdownByNameIfAvlbl(ownDriver, report, ownDriver.findElement(BinMaintenancePage.primaryOpt), "Secondary", "Select secondary option from the drop down ");
        Utility_Functions.timeWait(3);
        commonObj.validateText(BinMaintenancePage.toaster, "Cannot set primary bin to secondary", "Error toaster 'Cannot set primary bin to secondary' is present");
        int size = ownDriver.findElements(BinMaintenancePage.primaryOpt).size();
        if (size == 2) {
            size = size - 1;
        }
        Utility_Functions.xAssertEquals(report, size, 1, "Only one primary Bin Type is present as expected");
    }

    /**
     * Keyword to Verify Update Bin Type
     */
    public void verifyUpdateBinType() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemNumberContains, "Enable Contains check box");
        click(BinMaintenancePage.binType, "Click Bin Type drop down");
        click(tabs("Secondary"), "Click Secondary option");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//h2"),"");
        if (Utility_Functions.xIsDisplayed(ownDriver, By.xpath("//td/a"))) {
            validateItemHeader();
        }
        Utility_Functions.xSelectDropdownByNameIfAvlbl(ownDriver, report, ownDriver.findElement(BinMaintenancePage.secondaryOpt), "Temporary", "Select Temporary option from the drop down ");
        Utility_Functions.timeWait(3);
        commonObj.validateText(BinMaintenancePage.toaster, "Bin-item is successfully Updated.", "'Bin-item is successfully Updated.' is present");
        Utility_Functions.xSelectDropdownByNameIfAvlbl(ownDriver, report, ownDriver.findElement(BinMaintenancePage.temporaryOpt), "Secondary", "And then Select Secondary option from the drop down ");
        Utility_Functions.timeWait(3);
        commonObj.validateText(BinMaintenancePage.toaster, "Bin-item is successfully Updated.", "'Bin-item is successfully Updated.' is present");
    }

    public void isPrimaryItemDelete() {
        if (getText(BinMaintenancePage.toaster).equals("Cannot delete primary if secondary/temporary bin exists")) {
            commonObj.validateText(BinMaintenancePage.toaster, "Cannot delete primary if secondary/temporary bin exists", "'Cannot delete primary if secondary/temporary bin exists' is present");
            click(ownDriver.findElements(BinMaintenancePage.deleteIcon).get(1), "Click delete icon");
            Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.deletePopup,"");
            commonObj.validateText(BinMaintenancePage.deletePopup, "Delete Bin Item", "'Delete Bin Item' popup is present");
            Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Yes"), "Click 'Yes' Button");
            Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemBinManItemDet,"");
        }
    }

    /**
     * Keyword to Verify Bin Item Delete Icon
     */
    public void verifyBinItemDeleteIcon() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemNumberContains, "Enable Contains check box");
        click(BinMaintenancePage.binType, "Click Bin Type drop down");
        click(tabs("Primary"), "Click Primary option");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//h2"),"");
        if (Utility_Functions.xIsDisplayed(ownDriver, By.xpath("//td/a"))) {
            validateItemHeader();
        }
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.deleteIcon, "Click delete icon");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.deletePopup,"");
        commonObj.validateText(BinMaintenancePage.deletePopup, "Delete Bin Item", "'Delete Bin Item' popup is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("No"), "Click 'No' Button");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemBinManItemDet,"");
        commonObj.validateText(BinMaintenancePage.itemBinManItemDet, "Item-Bin Maintenance - Item Details", "Item-Bin Maintenance - Item Details Header is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.deleteIcon, "Click delete icon");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.deletePopup,"");
        commonObj.validateText(BinMaintenancePage.deletePopup, "Delete Bin Item", "'Delete Bin Item' popup is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Yes"), "Click 'Yes' Button");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemBinManItemDet,"");
        isPrimaryItemDelete();
        commonObj.validateText(BinMaintenancePage.toaster, "Bin-item is successfully Deleted.", "'Bin-item is successfully Deleted.' is present");
    }

    public void clickEditButton() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemNumberContains, "Enable Contains check box");
        click(BinMaintenancePage.binType, "Click Bin Type drop down");
        click(tabs("Primary"), "Click Primary option");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//h2"),"");
        if (Utility_Functions.xIsDisplayed(ownDriver, By.xpath("//td/a"))) {
            validateItemHeader();
        }
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.editIcon, "Click Edit Icon");
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to Verify Bin Item Edit Icon
     */
    public void verifyBinItemEditIcon() {
        clickEditButton();
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.cancelIcon, "Click Cancel Icon");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.editIcon, "Click Edit Icon");
        Utility_Functions.timeWait(2);
        sendKeys(BinMaintenancePage.editTextBox, "1", "Enter Bin min as 1");
        sendKeys(ownDriver.findElements(BinMaintenancePage.editTextBox).get(1), "10", "Enter Bin max as 10");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.saveIcon, "Click Save icon");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemBinManItemDet,"");
        commonObj.validateText(BinMaintenancePage.toaster, "Bin Item updated successfully.", "'Bin Item updated successfully.' is present");
        commonObj.validateText(By.xpath("//div[text()=' 1 ']"), "1", "Bin min updated to 1");
        commonObj.validateText(By.xpath("//div[text()=' 10 ']"), "10", "Bin min updated to 10");
    }

    /**
     * Keyword to Verify Bin Item Edit Icon
     */
    public void verifyBinMinMax() {
        clickEditButton();
        clearText(BinMaintenancePage.editTextBox);
        Utility_Functions.xClearAndSendKeys(ownDriver, ownDriver.findElements(BinMaintenancePage.editTextBox).get(1), "");
        sendKeys(BinMaintenancePage.editTextBox, "ASDD#$", "Enter special character and alphabets into Bin min as ASDD#$");
        sendKeys(ownDriver.findElements(BinMaintenancePage.editTextBox).get(1), "Auto#$", "Enter special character and alphabets into Bin Max as 'Auto#$'");
        sendKeys(BinMaintenancePage.editTextBox, "10011111111111", "Enter more than 10 number into Bin min");
        sendKeys(ownDriver.findElements(BinMaintenancePage.editTextBox).get(1), "12345678901", "Enter 10 into Bin Max");
        click(BinMaintenancePage.saveIcon, "Click Save icon");
        Utility_Functions.timeWait(3);
        commonObj.validateText(BinMaintenancePage.toaster, "Bin Item updated successfully.", "'Bin Item updated successfully.' is present");
        commonObj.validateText(By.xpath("//div[text()=' 1234567890 ']"), "1234567890", "Bin min holds max 10 number");
        commonObj.validateText(By.xpath("//div[text()=' 1001111111 ']"), "1001111111", "Bin min holds max 10 number");
        click(BinMaintenancePage.editIcon, "Click Edit Icon");
        Utility_Functions.timeWait(2);
        sendKeys(BinMaintenancePage.editTextBox, "10", "Enter number 10 into Bin min");
        sendKeys(ownDriver.findElements(BinMaintenancePage.editTextBox).get(1), "5", "Enter number 5 into Bin Max");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.saveIcon, "Click Save icon");
        Utility_Functions.timeWait(3);
        commonObj.validateText(BinMaintenancePage.toaster, "Bin Max qty must be greater than Bin Min qty", "'Bin Max qty must be greater than Bin Min qty' is present");
    }

    public void clickEditBin(String binLoc) {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button(" Edit Bin "), "Click Edit Button");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//h2[text()='EDIT BIN - " + binLoc + "']"),"");
        commonObj.validateText(By.xpath("//h2[text()='EDIT BIN - " + binLoc + "']"), "EDIT BIN - " + binLoc, "'EDIT BIN - " + binLoc + "' Popup is present");
    }

    public String navigateToEditBinPopUp() {
        String binLocation = getText(BinMaintenancePage.getItemVal).trim();
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.primaryOpt,"");
        clickEditBin(binLocation);
        return binLocation;
    }

    public void clickSaveBtn(){
        try {
            click(ownDriver.findElements(button("Save ")).get(1), "Click Save button");
        } catch (Exception e) {
            click(BinMaintenancePage.stagingArea);
            click(ownDriver.findElements(button("Save ")).get(0), "Click Save button");
        }
    }

    public void validateDefectiveStatus(String status) {
        if(getAttribute(BinMaintenancePage.nSellable,"ng-reflect-model").equals("true")) {
            if (status.equals("Defective")) {
                commonObj.validateText(BinMaintenancePage.toaster, "Sellable must be N when bin condition is defective", "'Sellable must be N when bin condition is defective' is present");
                Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.nSellable,"");
                clickSaveBtn();
            }
        }
    }

    public void changeStatus(String binLocation, String status) {
        Utility_Functions.xSelectDropdownByNameIfAvlbl(ownDriver, report, ownDriver.findElement(BinMaintenancePage.zoneIdDropDown), "Test", "Select Test option from the drop down ");
        Utility_Functions.xSelectDropdownByNameIfAvlbl(ownDriver, report, ownDriver.findElement(BinMaintenancePage.binConditionId), status, "Select '" + status + "' option from the drop down ");
        Utility_Functions.timeWait(2);
        clickSaveBtn();
        validateDefectiveStatus(status);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemBinManItemDet,"");
        commonObj.validateText(BinMaintenancePage.toaster, "Selected Bins updated successfully.", "'Selected Bins updated successfully.' is present");
        if (status.equals("No Change")) {
            commonObj.validateText(getStatus(binLocation), "Damaged", "For bin location: " + binLocation + "No change in status");
        } else {
            commonObj.validateText(getStatus(binLocation), status, "For bin location: " + binLocation + " status changed to " + status);
        }
    }

    /**
     * Keyword to Verify Edit bin Popup
     */
    public void verifyEditBinPopUp() {
        clickEditButton();
        String binLocation = navigateToEditBinPopUp();
        Utility_Functions.xSelectDropdownByNameIfAvlbl(ownDriver, report, ownDriver.findElement(BinMaintenancePage.binConditionId), "No Change", "Select No Change option from the drop down ");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Save button is disabled");
        String[] statuses = {"Good", "Defective", "Damaged", "No Change"};
        for (String status : statuses) {
            changeStatus(binLocation, status);
            clickEditBin(binLocation);
        }
    }

    public void enterRequiredData() {
        Utility_Functions.xSelectDropdownByNameIfAvlbl(ownDriver, report, ownDriver.findElement(BinMaintenancePage.createBinCondition), jsonData.getData("BinCondition"), "Select 'Good' option from the drop down ");
        Utility_Functions.timeWait(2);
        Utility_Functions.xSelectDropdownByNameIfAvlbl(ownDriver, report, ownDriver.findElements(BinMaintenancePage.createBinCondition).get(1), jsonData.getData("BinZone"), "Select 'Test' option from the drop down ");
        Utility_Functions.timeWait(2);
        Utility_Functions.xSelectDropdownByNameIfAvlbl(ownDriver, report, ownDriver.findElements(BinMaintenancePage.createBinCondition).get(2), jsonData.getData("Picking"), "Select 'RF Gun' option from the drop down ");
        Utility_Functions.timeWait(2);
        Utility_Functions.xSelectDropdownByNameIfAvlbl(ownDriver, report, ownDriver.findElements(BinMaintenancePage.createBinCondition).get(3), jsonData.getData("Receiving"), "Select 'RF Gun' option from the drop down ");
        Utility_Functions.timeWait(2);
    }

    public void createBin() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Create Bin"), "Click 'Create Bin' button");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.createBinPopup,"");
        int binLoc = Utility_Functions.xRandomFunction();
        Utility_Functions.xUpdateJson("itemLocation", "" + binLoc + "");
        sendKeys(BinMaintenancePage.createBinLabel, "" + binLoc + "", "Enter Bin Location");
        enterRequiredData();
        click(ownDriver.findElement(button("Create ")), "Click Save button");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.toaster,"");
        commonObj.validateText(BinMaintenancePage.toaster, "Bin and Bin-item are created successfully", "'Bin and Bin-item are created successfully' is present");
        int i = 0;
        while (i == 0) {
            try {
                Utility_Functions.xScrollIntoView(ownDriver, By.xpath("//td[contains(text(),'" + binLoc + "')]"));
                commonObj.validateElementExists(By.xpath("//td[contains(text(),'" + binLoc + "')]"), binLoc + " Bin location is created");
                break;
            } catch (Exception e) {
                Utility_Functions.xClickHiddenElement(ownDriver, ownDriver.findElements(By.xpath("//a[text()='›']")).get(1));
                Utility_Functions.timeWait(4);
            }
        }
        commonObj.validateText(getStatus("" + binLoc + ""), "Good", "For bin location: " + binLoc + " status is Good");
        commonObj.validateText(getZone("" + binLoc + ""), "QS", "For bin location: " + binLoc + " Zone is TS");
    }

    /**
     * Keyword to Verify Create Bin Button
     */
    public void verifyCreateBinButton() {
        String itemLedger = getText(BinMaintenancePage.goToItemBinLedger);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Create Bin"), "Click 'Create Bin' button");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.createBinPopup,"");
        commonObj.validateText(BinMaintenancePage.createBinPopup, "CREATE BIN AND ASSIGN", "CREATE BIN AND ASSIGN popup is present");
        commonObj.validateText(By.xpath("//div[text()='" + itemLedger + "']"), itemLedger, "Item Number " + itemLedger + " is present");
        String[] labels = {"Special Handling", "Available to Sell", "Staging Area"};
        for (String label : labels) {
            commonObj.validateText(By.xpath("//input[@type='checkbox']/following-sibling::label[text()='" + label + "']"), label, label + " checkbox is present");
        }
        String[] textLabels = {" Bin Location", "Condition", "Zone", "Picking", "Receiving"};
        for (String textLabel : textLabels) {
            commonObj.validateText(By.xpath("//label[text()='" + textLabel + "' and contains(@for,'createBin')]"), textLabel.trim(), textLabel + " text box is present");
        }
        click(ownDriver.findElements((button("Cancel "))).get(1), "Click Cancel button");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemBinManItemDet,"");
        commonObj.validateText(BinMaintenancePage.itemBinManItemDet, "Item-Bin Maintenance - Item Details", "Item-Bin Maintenance - Item Details Header is present");
        createBin();
    }

    /**
     * Keyword to Verify Create duplicate Bin
     */
    public void verifyCreateDuplicateBin() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Create Bin"), "Click 'Create Bin' button");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.createBinPopup,"");
        commonObj.validateText(BinMaintenancePage.createBinPopup, "Create Bin and Assign", "[Create Bin and Assign] popup is present");
        sendKeys(BinMaintenancePage.createBinLabel, Utility_Functions.xGetJsonData("itemLocation"), "Enter Bin Location");
        Utility_Functions.timeWait(2);
        enterRequiredData();
        click(ownDriver.findElement(button("Create ")), "Click Save button");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.createBinPopup,"");
        commonObj.validateText(BinMaintenancePage.toaster, "Bin Location already exists", "'Bin Location already exists' is present");
    }

    public void addBinCancelBtn() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Add Bin"), "Click 'Add Bin' button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(BinMaintenancePage.addBinPopup, "ADD BIN", "ADD BIN popup is present");
        Utility_Functions.timeWait(2);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.addCancel, "Click Cancel Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(BinMaintenancePage.itemBinManItemDet, "Item-Bin Maintenance - Item Details", "Item-Bin Maintenance - Item Details Header is present");
    }

    public void addBinSaveBtn(String binLct) {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Add Bin"), "Click 'Add Bin' button");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.addBinLoc,"");
        sendKeys(BinMaintenancePage.addBinLoc, binLct, "Enter Bin Location");
        Utility_Functions.timeWait(2);
        // "Test" is not an option in the dropdown
        Utility_Functions.xSelectDropdownByNameIfAvlbl(ownDriver, report, ownDriver.findElement(BinMaintenancePage.addBinZoneDrop), "Test", "Select 'Test' option from the drop down ");
        Utility_Functions.timeWait(2);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.addSave, "Click Save button");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.toaster,"");
    }

    public void filterSecAndNavBinMainDetails() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemNumberContains, "Enable Contains check box");
        click(BinMaintenancePage.binType, "Click Bin Type drop down");
        click(tabs("Primary"), "Click Primary option");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//h2"),"");
        if (Utility_Functions.xIsDisplayed(ownDriver, By.xpath("//td/a"))) {
            validateItemHeader();
        }
    }

    /**
     * Keyword to Verify ADD Bin Button
     */
    public void verifyAddBinButton() {
        String binLct = getText(getBinLocation("Secondary"));
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,deleteBinLocation(binLct), "Delete BinLocation: " + binLct);
        Utility_Functions.timeWait(2);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Yes"),"Click YES");
        Utility_Functions.timeWait(3);
        addBinCancelBtn();
        addBinSaveBtn(binLct);
        commonObj.validateText(BinMaintenancePage.toaster, "Bin-item is created successfully.", "'Bin-item is created successfully.' is present");
        commonObj.validateText(By.xpath("//td[contains(text(),'" + binLct + "')]"), binLct, binLct + " Bin location is added");
        commonObj.validateText(getZone("" + binLct + ""), "QS", "For bin location: " + binLct + " Zone is TS");
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to Verify Duplicate ADD Bin
     */
    public void verifyDuplicateAddBin() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemBinManItemDet,"");
        String binLct = getText(getBinLocation("Secondary"));
        addBinSaveBtn(binLct);
        commonObj.validateText(BinMaintenancePage.toaster, "Item Bin association alredy exists", "'Item Bin association alredy exists' is present");
    }

    /**
     * Keyword to Navigate to Zone Page
     */
    public void navigateZonePage() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.inboxIcon, "Click Inbox Icon");
        Utility_Functions.timeWait(2);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.zone, "Navigate to zone page");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.zoneHeader, "");
        commonObj.validateText(BinMaintenancePage.zoneHeader, "Zones", "Zones header is present");
    }

    /**
     * Keyword to Verify Zone UI
     */
    public void verifyZoneUI() {
        String[] labels = {"Zone Name", "Abbreviation", "Bins"};
        for (String label : labels) {
            commonObj.validateText(By.xpath("//*[contains(text(),'" + label + "')]"), label, label + " is present");
        }
        commonObj.validateText(button(" New Zone "), "New Zone", "'+ New Zone' button is present");
        commonObj.validateElementExists(BinMaintenancePage.collapsedIcon, "Collapsed icon is present");
    }

    public void validateAlreadyExistZoneAbrv() {
        Utility_Functions.timeWait(2);
        if (!Utility_Functions.xIsDisplayed(ownDriver, BinMaintenancePage.toaster)) {
            Utility_Functions.waitTillClickHardSleep(report,ownDriver,button(" Cancel "),"");
            Utility_Functions.timeWait(2);
            deleteZoneIcon();
            createZoneAgain();
            Utility_Functions.timeWait(3);
        }
    }

    public String createZoneAgain() {
        Utility_Functions.timeWait(2);
        commonObj.validateText(BinMaintenancePage.zoneHeader, "Zones", "Zones header is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button(" New Zone "), "Click 'New Zone' button");
        Utility_Functions.timeWait(2);
        String zoneName = Utility_Functions.xGetJsonData("zoneName");
        String zoneAbv = Utility_Functions.xGetJsonData("zoneAbv");
        sendKeys(BinMaintenancePage.zoneName, zoneName, "Enter Zone Name");
        sendKeys(BinMaintenancePage.zoneAbv, zoneAbv, "Enter Zone Abbreviation as " + zoneAbv + "Q");
        Utility_Functions.timeWait(3);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Save "), "Click 'Save' Button");
        return zoneAbv;
    }

    public String createZone() {
        Utility_Functions.timeWait(2);
        commonObj.validateText(BinMaintenancePage.zoneHeader, "Zones", "Zones header is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button(" New Zone "), "Click 'New Zone' button");
        Utility_Functions.timeWait(2);
        int zoneName = Utility_Functions.xRandomFunction();
        String zoneAbv = Integer.toString(zoneName).substring(0, 1);
        Utility_Functions.xUpdateJson("zoneName", "" + zoneName + "autoqa");
        Utility_Functions.xUpdateJson("zoneAbv", zoneAbv + "Q");
        sendKeys(BinMaintenancePage.zoneName, zoneName + "AutoQA", "Enter Zone Name");
        sendKeys(BinMaintenancePage.zoneAbv, zoneAbv + "Q", "Enter Zone Abbreviation as " + zoneAbv + "Q");
        Utility_Functions.timeWait(3);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Save "), "Click 'Save' Button");
        Utility_Functions.timeWait(2);
        return zoneAbv;
    }

    /**
     * Keyword to Verify New Zone
     */
    public void verifyNewZone() {
        Utility_Functions.timeWait(3);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button(" New Zone "), "Click 'New Zone' button");
        Utility_Functions.timeWait(2);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button(" Cancel "), "Click Cancel button");
        String zoneAbv = createZone();
        validateAlreadyExistZoneAbrv();
        String zName = Utility_Functions.xGetJsonData("zoneName").toLowerCase();
        commonObj.validateText(BinMaintenancePage.toaster, "Zone " + zName + " created successfully.", "'Zone " + zName + " created successfully.' message is present");
        Utility_Functions.timeWait(6);
        Utility_Functions.xScrollIntoView(ownDriver, ownDriver.findElements(By.xpath("//input[@ng-reflect-model='" + zName + "']")).get(0));
        Utility_Functions.xAssertEquals(report, ownDriver.findElements(By.xpath("//input[@ng-reflect-model='" + zName + "']")).get(0).getAttribute("ng-reflect-model"), zName, zName + " Zone Name is present");
        Utility_Functions.xAssertEquals(report, ownDriver.findElements(By.xpath("//input[@ng-reflect-model='" + zoneAbv + "Q" + "']")).get(0).getAttribute("ng-reflect-model"), zoneAbv + "Q", zoneAbv + "q Zone Abbreviation is present");
    }

    public String expandZone() {
        Utility_Functions.timeWait(3);
        String zoneAbv = Utility_Functions.xGetJsonData("zoneAbv");
        WebElement expand = ownDriver.findElement(By.xpath("//input[@ng-reflect-model='" + zoneAbv + "']/ancestor::div/following-sibling::div/a"));
        Utility_Functions.xScrollIntoView(ownDriver, expand);
        Utility_Functions.timeWait(2);
        click(expand, "Expand Zone detail");
        Utility_Functions.timeWait(2);
        return zoneAbv;
    }

    public void clickEditIcon(String zoneAbv) {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//input[@ng-reflect-model='" + zoneAbv + "']/ancestor::div/following-sibling::div/descendant::i"), "Click Edit icon");
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to Verify Edit Zone icon
     */
    public String clickEditZoneIcon() {
        String zoneAbv = expandZone();
        clickEditIcon(zoneAbv);
        return zoneAbv;
    }

    /**
     * Keyword to Verify delete Zone icon
     */
    public void clickDeleteZoneIcon() {
        String zoneAbv = clickEditZoneIcon();
        String zoneName = Utility_Functions.xGetJsonData("zoneName");
        WebElement deleteIcon = ownDriver.findElement(By.xpath("//input[@ng-reflect-model='" + zoneAbv + "']/ancestor::div/following-sibling::div/descendant::i[contains(@class,'trash zone-delete')]"));
        Utility_Functions.timeWait(2);
        click(deleteIcon, "Click Delete Icon");
        Utility_Functions.timeWait(2);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.yesButtonPopUp, "Click Yes Button");
        Utility_Functions.timeWait(3);
        commonObj.validateText(BinMaintenancePage.toaster, "Zone " + zoneName + " deleted successfully.", "'Zone " + zoneName + " deleted successfully.' is present");
    }


    public void deleteZoneIcon() {
        String zoneAbv = clickEditZoneIcon();
        String zoneName = Utility_Functions.xGetJsonData("zoneName");
        WebElement deleteIcon = ownDriver.findElement(By.xpath("//input[@ng-reflect-model='" + zoneAbv + "']/ancestor::div/following-sibling::div/descendant::i[contains(@class,'trash zone-delete')]"));
        Utility_Functions.timeWait(2);
        click(deleteIcon, "Click Delete Icon");
        Utility_Functions.timeWait(2);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.yesButtonPopUp, "Click Yes Button");
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(BinMaintenancePage.toaster, getText(BinMaintenancePage.toaster) + " is present");
    }

    public void verifyDuplicateZoneName(String zoneName) {
        clearText(BinMaintenancePage.zoneAbv);
        Utility_Functions.timeWait(2);
        sendKeys(BinMaintenancePage.zoneName, zoneName, "Enter Zone Name");
        Utility_Functions.timeWait(2);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Save "), "Click 'Save' Button");
        Utility_Functions.timeWait(3);
        commonObj.validateText(By.xpath("//div[text()='Zone " + zoneName + " already exists']"), "Zone " + zoneName + " already exists", "'Zone " + zoneName + " already exists' error message is present");
    }

    public void verifyDuplicateZoneAbv(String zoneAbv) {
        clearText(BinMaintenancePage.zoneName);
        Utility_Functions.timeWait(2);
        sendKeys(BinMaintenancePage.zoneName, "pk123456789", "Enter Zone Name");
        Utility_Functions.timeWait(2);
        sendKeys(BinMaintenancePage.zoneAbv, zoneAbv, "Clear Zone name field and Enter Zone Abbreviation as " + zoneAbv + "Q");
        Utility_Functions.timeWait(2);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Save "), "Click 'Save' Button");
        Utility_Functions.timeWait(3);
        commonObj.validateText(By.xpath("//div[text()='Zone " + zoneAbv + " already exists']"), "Zone " + zoneAbv + " already exists", "'Zone " + zoneAbv + " already exists' error message is present");
    }

    public String verifySaveButtonDisZoneName() {
        String zoneName = Utility_Functions.xGetJsonData("zoneName");
        sendKeys(BinMaintenancePage.zoneName, zoneName + "AutoQA", "Enter Zone Name");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Save button is disabled");
        return zoneName;
    }

    public String verifySaveButtonDisZoneAvr() {
        String zoneAbv = Utility_Functions.xGetJsonData("zoneAbv");
        sendKeys(BinMaintenancePage.zoneAbv, zoneAbv, "Clear Zone name field and Enter Zone Abbreviation as " + zoneAbv + "Q");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Save button is disabled");
        return zoneAbv;
    }

    public void verifyBlankSpaceCreateZone() {
        clearText(BinMaintenancePage.zoneName);
        Utility_Functions.timeWait(2);
        clearText(BinMaintenancePage.zoneAbv);
        sendKeys(BinMaintenancePage.zoneName, "  ", "Enter 2 Blank space into Zone Name text field");
        sendKeys(BinMaintenancePage.zoneAbv, "  ", "Enter 2 Blank space into Zone Abbreviation text field");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Save "), "Click 'Save' Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(BinMaintenancePage.toaster, "Error while creating Zone.", "'Error while creating Zone.' error message is present");
    }

    /**
     * Keyword to verify UI of Item-Bin Maintenance > Bin Maintenance tab when Bins are available
     */
    public void binMaintenanceUIwithFilters() {
        commonObj.validateElementExists(BinMaintenancePage.lblBinCount, "Bins count label is present below left top page header");
        commonObj.validateElementExists(button(" Select All Bins "), "[Select All Bins] is present on top right side of table");
        String[] field = {"Bin", "Zone", "Status", "Available to Sell", "Items"};
        for (String label : field) {
            commonObj.validateElementExists(By.xpath("//thead/tr/th[contains(text(),'" + label + "')]"), "Table header [" + label + "] is present");
        }
        commonObj.validateElementExists(button("Delete Bin"), "[Delete Bin] button is present on bottom of page");
        commonObj.validateElementExists(button(" Edit  Bin(s) "), "[Edit Bin(s)] button is present on bottom of page");
        commonObj.validateElementExists(button("Create New Bin"), "[Create New Bin] button is present on bottom of page");
        commonObj.validateElementExists(button(" Export to Excel"), "[Export to Excel] button is present on bottom of page");
        commonObj.validateElementExists(button(" Labels "), "[Labels] button is present on bottom of page");
        verifyPresenceOfShowRowsCountsLabel();
        commonObj.validateElementExists(BinMaintenancePage.pagination, "[Pagination] buttons are present below the table");
    }

    /**
     * Keyword to Verify presence of Show Rows count label below Bins table
     */
    public void verifyPresenceOfShowRowsCountsLabel() {
        commonObj.validateElementExists(BinMaintenancePage.lblShowRowsCount, "Show Rows Count label is present below the table");
        String[] rowsCount = {"10", "15", "30"};
        for (String count : rowsCount) {
            commonObj.validateElementExists(By.xpath("//nav[@id='binMaintenancePagination']//following::span[text()='" + count + "']"), "Rows Count " + count + " is present");
        }
        List<WebElement> lstElement = Utility_Functions.findElementsByXpath(ownDriver, BinMaintenancePage.btnShowRowsCount);
        if (lstElement.get(0).getAttribute("class").contains("selected-count")) {
            report.updateTestLog("Verify show rows count", "Verify show rows count [10] is set by default", Status.PASS);
        }
    }

    /**
     * Keyword to click [Create New Bin] button
     */
    public void clickCreateNewBinBtn() {
        try {
            click(button("Create New Bin"), "Click on [Create New Bin] button");
        } catch (Exception e) {
            Utility_Functions.xClickHiddenElement(ownDriver, button("Create New Bin"));
        }
        waitForVisible(BinMaintenancePage.hdrCreateNewBinPopup);
    }

    /**
     * Keyword to verify presence of fields on [CREATE NEW BIN] popup]
     */
    public void verifyCreateNewBinPopFields() {
        commonObj.validateText(BinMaintenancePage.hdrCreateNewBinPopup, "create new bin", "[CREATE NEW BIN] header is present");
        vrfyPresenceOfChkbxAddBinPopup();
        vrfyDefaultSelectionOfChbxInCreateNewBinPopup();
        commonObj.validateElementExists(BinMaintenancePage.tbxBinLocation, "[Bin Location] textbox is present");

        String[] field = {"Condition", "Zone", "Picking", "Receiving"};
        for (String label : field) {
            commonObj.validateElementExists(By.xpath("//label[contains(text(),'" + label + "')]//following-sibling::select"), "Dropdown [" + label + "] is present");
        }
        commonObj.validateElementExists(BinMaintenancePage.btnCancelCreateNewBinpopup, "[Cancel] button is present");
        commonObj.validateElementExists(button("Create "), "[Create] button is present");
    }

    /**
     * Keyword to verify presence of checkboxes in Add Bin popup
     */
    public void vrfyPresenceOfChkbxAddBinPopup() {
        commonObj.validateText(validateCheckBox("Special Handling"), "Special Handling", "[Special Handling] check box is present");
        commonObj.validateText(validateCheckBox("Available to Sell"), "Available to Sell", "[Available to Sell] check box is present");
        commonObj.validateText(validateCheckBox("Staging Area"), "Staging Area", "[Staging Area] check box is present");
    }


    public void vrfyDefaultSelectionOfChbxInCreateNewBinPopup() {
        String attrAvaiToSell = getAttribute(BinMaintenancePage.chbxAvailableToSell, "ng-reflect-model");
        String attrSpclHndlng = getAttribute(BinMaintenancePage.chbxSpecialHandling, "ng-reflect-model");

        if (attrAvaiToSell.equalsIgnoreCase("true"))
            report.updateTestLog("Verify Available To Sell chbx", "[Available to Sell] checkbox is selected by default", Status.PASS);
        else
            report.updateTestLog("Verify Available To Sell chbx", "[Available to Sell] checkbox is selected by default", Status.FAIL);
        if (attrSpclHndlng.isEmpty() || attrSpclHndlng.equalsIgnoreCase("false"))
            report.updateTestLog("Verify Special Handling chbx", "[Special Handling] checkbox is not selected by default", Status.PASS);
        else
            report.updateTestLog("Verify Special Handling chbx", "[Special Handling] checkbox is not selected by default", Status.FAIL);
    }

    /**
     * Keyword to verify Create Duplicate Bin error
     */
    public void vrfyCreateDuplicateBinErrorAndFields() {
        boolean isSaveEnabled = getElement(BinMaintenancePage.btnSaveCreateNewBinpopup).isEnabled();
        if (!isSaveEnabled)
            report.updateTestLog("Verify Save Btn state", "[Save] button is disabled", Status.PASS);
        else
            report.updateTestLog("Verify Save Btn state", "[Save] button is disabled", Status.FAIL);

        vrfyBinLocationTextbox();
        selectDropdownsCreateNewBinPopup("Good", "Default", "Automatic", "Manual");
        click(BinMaintenancePage.btnSaveCreateNewBinpopup, "Click [Save] button");
        waitForVisible(BinMaintenancePage.toastMsg);
        commonObj.validateText(BinMaintenancePage.toastMsg, "Bin Location already exists", "Duplicate Bin error message");
        click(BinMaintenancePage.btnCancelCreateNewBinpopup, "Click [Cancel] button");
        waitForElementDisappear(BinMaintenancePage.hdrCreateNewBinPopup, globalWait);
    }

    /**
     * Keyword to verify [Bin Location] textbox
     */
    public void vrfyBinLocationTextbox() {
        sendKeys(BinMaintenancePage.tbxBinLocation, "!@#$%^&*()_+=~", "entering Special Characters in Bin Location textbox");
        Utility_Functions.timeWait(2);
        String textActual = getValue(BinMaintenancePage.tbxBinLocation);
        Utility_Functions.xAssertEquals(report, "", textActual, "Special Characters are NOT accepted in Bin Location textbox");

        sendKeys(BinMaintenancePage.tbxBinLocation, "TEST123", "entering Alphanumeric Characters in Bin Location textbox");
        Utility_Functions.timeWait(2);
        textActual = getValue(BinMaintenancePage.tbxBinLocation);
        Utility_Functions.xAssertEquals(report, "TEST123", textActual, "Alphanumeric Characters are accepted in Bin Location textbox");

        sendKeys(BinMaintenancePage.tbxBinLocation, "AUTOMATION", "enter existing Bin [AUTOMATION] in Bin Location textbox");
        Utility_Functions.timeWait(2);
        textActual = getValue(BinMaintenancePage.tbxBinLocation);
        Utility_Functions.xAssertEquals(report, "AUTOMATION", textActual, "existing Bin [AUTOMATION] is accepted in Bin Location textbox");
    }

    /**
     * Keyword to select dropdown values in Create New Bin popup
     */
    public void selectDropdownsCreateNewBinPopup(String condition, String zones, String picking, String receiving) {
        String[] texts = {condition, zones, picking, receiving};
        List<WebElement> lstElement = Utility_Functions.findElementsByXpath(ownDriver, BinMaintenancePage.lstDropdownsCreateNewBinPopup);
        for (int i = 0; i < lstElement.size(); i++) {
            Utility_Functions.xSelectDropdownByName(ownDriver, report, lstElement.get(i), texts[i], "selected value [" + texts[i] + "] in drodpown");
            Utility_Functions.timeWait(1);
        }
    }

    /**
     * Keyword to create a New Bin and verify data in table
     */
    public void createNewBinAndVerifyData() {
        clickCreateNewBinBtn();
        String binLocation = "TEST" + Utility_Functions.xRandomFunction(99999);
        jsonData.putData("BinLocation", binLocation);
        Utility_Functions.xUpdateJson("BinLocation", binLocation);
        String condition = jsonData.getData("BinCondition");
        String zones = jsonData.getData("BinZones");
        String picking = jsonData.getData("BinPicking");
        String receiving = jsonData.getData("BinReceiving");
        String availableToSell = jsonData.getData("BinAvailableToSell");
        String specialHandling = jsonData.getData("BinSpecialHandling");
        String stagingArea = jsonData.getData("BinStagingArea");
        String items = jsonData.getData("BinItems");

        selectDropdownOptionCreateNewBinPopup(binLocation, condition, zones, picking, receiving);
        selectUnselectChbxCreateNewBinPopup(BinMaintenancePage.chbxSpecialHandling, specialHandling);
        selectUnselectChbxCreateNewBinPopup(BinMaintenancePage.chbxAvailableToSell, availableToSell);
        click(BinMaintenancePage.chbxStagingArea);
        Utility_Functions.timeWait(1);
        selectUnselectChbxCreateNewBinPopup(BinMaintenancePage.chbxStagingArea, stagingArea);

        click(BinMaintenancePage.btnSaveCreateNewBinpopup, "Click [Create] button");
        waitForElementDisappear(MasterPage.loadingSpinner, globalWait);
        waitForVisible(BinMaintenancePage.toastMsg);
        commonObj.validateText(BinMaintenancePage.toastMsg, "Bin " + binLocation + " created successfully.", "Bin Creates Successfully message is displayed");
        Utility_Functions.timeWait(2);
        vrfyFirstRowValueInBinTable(binLocation, condition, zones, availableToSell, items);
    }

    /**
     * Keyword to enter data in [Create New Bin] popup
     */
    public void selectDropdownOptionCreateNewBinPopup(String binLocation, String condition, String zones, String
            picking, String receiving) {
        sendKeys(BinMaintenancePage.tbxBinLocation, binLocation, "Entering [" + binLocation + "] in Bin Location textbox");
        Utility_Functions.xSelectDropdownByName(ownDriver, report, BinMaintenancePage.ddnConditionCreateBinPopup, condition, "Selected option [" + condition + "] in Condition dropdown");
        Utility_Functions.xSelectDropdownByName(ownDriver, report, BinMaintenancePage.ddnZoneCreateBinPopup, zones, "Selected option [" + zones + "] in Zoned dropdown");
        Utility_Functions.xSelectDropdownByName(ownDriver, report, BinMaintenancePage.ddnPickingCreateBinPopup, picking, "Selected option [" + picking + "] in Picking dropdown");
        Utility_Functions.xSelectDropdownByName(ownDriver, report, BinMaintenancePage.ddnReceivingCreateBinPopup, receiving, "Selected option [" + receiving + "] in Receiving dropdown");
    }

    /**
     * Keyword to select/unselect checkbox in [Create New Bin] popup
     */
    public void selectUnselectChbxCreateNewBinPopup(By checkbox, String desiredState) {
        String state = "";
        if (!isDisplayed(checkbox))
            report.updateTestLog("Select/Unselect checkbox", "Checkbox is NOT present", Status.FAIL);
        else {
            switch (desiredState) {
                case "Yes":
                    state = getAttribute(checkbox, "ng-reflect-model");
                    if (state.equalsIgnoreCase("false"))
                        click(checkbox, "enabled checkbox");
                    else
                        report.updateTestLog("Select/Unselect checkbox", "Checkbox is already enabled", Status.PASS);
                    break;
                case "No":
                    state = getAttribute(checkbox, "ng-reflect-model");
                    if (state.equalsIgnoreCase("true"))
                        click(checkbox, "enabled checkbox");
                    else
                        report.updateTestLog("Select/Unselect checkbox", "Checkbox is already not enabled", Status.PASS);
                    break;
            }
        }
    }

    /**
     * Keyword to verify data in first row from records table - Bin Maintenance
     */
    public void vrfyFirstRowValueInBinTable(String binLocation, String condition, String zones, String
            avlToSell, String items) {
        String actual = getText(BinMaintenancePage.tdBinFirstRow);
        Utility_Functions.xAssertEquals(report, binLocation, actual, "Verify [Bin] value in records table");
        actual = getText(BinMaintenancePage.tdZoneFirstRow);
        Utility_Functions.xAssertEquals(report, zones, actual, "Verify [Zone] value in records table");
        actual = getText(BinMaintenancePage.tdStatusFirstRow);
        Utility_Functions.xAssertEquals(report, condition, actual, "Verify [Status] value in records table");
        actual = getText(BinMaintenancePage.tdAvaibaleToSellFirstRow);
        Utility_Functions.xAssertEquals(report, avlToSell, actual, "Verify [Available to Sell] value in records table");
        actual = getText(BinMaintenancePage.tdItemsFirstRow);
        Utility_Functions.xAssertEquals(report, items, actual, "Verify [Items] value in records table");
    }

    /**
     * Keyword to select one records from [Bin Maintenance] table and click on [Edit Bin(s)] button
     */
    public void selectFirstBinRecordAndClickEditBinBtn() {
        String binLocation = selectFirstRecordBinMaintenance();
        click(editBinsButton(1), "Click [Edit " + 1 + " Bin(s)] button");
        waitForVisible(BinMaintenancePage.hdrEditBinPopup);
        commonObj.validateText(BinMaintenancePage.hdrEditBinPopup, "EDIT BIN - " + binLocation, "Verify Edit 1 Bin Popup");
    }

    /**
     * Keyword to click on first row in [Bin Maintenance] table for selection
     */
    public String selectFirstRecordBinMaintenance() {
        waitForElementDisappear(MasterPage.loadingSpinner, globalWait);
        String selectedBin = "";
        List<WebElement> lstElement = Utility_Functions.findElementsByXpath(ownDriver, BinMaintenancePage.binsCountSP);

        if (!lstElement.isEmpty()) {
            selectedBin = Utility_Functions.xClickgetTextofFirstElementfromList(lstElement);
            report.updateTestLog("select one row", "Selected row with bin [" + selectedBin + "]", Status.PASS);
        } else
            report.updateTestLog("Click on Table Records", "No records available to click!", Status.FAIL);
        return selectedBin;
    }

    /**
     * Keyword to verify fields present in Edit Single Bin popup
     */
    public void vrfyUIOfEditBinPopup() {
        commonObj.validateElementExists(BinMaintenancePage.chkbxSpclHndlngEditBinPopup, "[Special Handling] checkbox is present");
        commonObj.validateElementExists(BinMaintenancePage.chkbxAvToSellEditBinPopup, "[Available to Sell] checkbox is present");
        commonObj.validateElementExists(BinMaintenancePage.chkbxStgAreaEditBinPopup, "[Staging Area] checkbox is present");

        String[] field = {"Zone", "Condition", "Receiving", "Picking"};
        for (String label : field) {
            commonObj.validateElementExists(By.xpath("//form//label[contains(text(),'" + label + "')]//following-sibling::select"), "Dropdown [" + label + "] is present");
        }
        commonObj.validateElementExists(BinMaintenancePage.btnCancelEditBinPopup, "[Cancel] button is present");
        commonObj.validateElementExists(BinMaintenancePage.btnSaveEdiBinPopup, "[Save] button is present");
    }

    /**
     * Keyword to verify fields present in Edit Multiple Bins popup
     */
    public void vrfyUIOfEditMultipleBinsPopup() {
        String[] field = {"Zone", "Condition", "Receiving", "Picking", "Special Handling", "Available to Sell", "Staging Area"};
        for (String label : field) {
            commonObj.validateElementExists(By.xpath("//form//label[contains(text(),'" + label + "')]//following-sibling::select"), "Dropdown [" + label + "] is present");
        }
        commonObj.validateElementExists(BinMaintenancePage.btnCancelEditBinPopup, "[Cancel] button is present");
        commonObj.validateElementExists(BinMaintenancePage.btnSavEdiBinPopup, "[Save] button is present");
    }

    /**
     * Keyword to select value in Edit Bin popup and verify in table for first row
     */
    public void selectRandomDropdownValueAndVerifyAfterSave() {
        String zone = Utility_Functions.xSelectDropdownByNameRandomValue(ownDriver, BinMaintenancePage.ddnZoneEditBinPopup);
        String condition = Utility_Functions.xSelectDropdownByNameRandomValue(ownDriver, BinMaintenancePage.ddnConditionEditBinPopup);
        String receiving = Utility_Functions.xSelectDropdownByNameRandomValue(ownDriver, BinMaintenancePage.ddnReceivingEditBinPopup);
        String picking = Utility_Functions.xSelectDropdownByNameRandomValue(ownDriver, BinMaintenancePage.ddnPickingEditBinPopup);
        click(BinMaintenancePage.btnSavEdiBinPopup, "Click [Save] button");
        waitForElementDisappear(MasterPage.loadingSpinner, globalWait);

        String actual = getText(BinMaintenancePage.tdZoneFirstRow);
        Utility_Functions.xAssertEquals(report, zone, actual, "Verify [Zone] value in records table");
        actual = getText(BinMaintenancePage.tdStatusFirstRow);
        Utility_Functions.xAssertEquals(report, condition, actual, "Verify [Status] value in records table");
    }

    /**
     * Keyword to click on records from [Bin Maintenance] table for selection
     */
    public void selectMultipleRecordAndClickEditBinBtn() {
        int count = Integer.parseInt(jsonData.getData("CountOfRecordsToSelect"));
        selectMultiRecordsBinMaintenance(count);
        click(editBinsButton(count), "Click [Edit " + count + " Bin(s)] button");
        waitForVisible(BinMaintenancePage.hdrEditBinPopup);
        commonObj.validateText(BinMaintenancePage.hdrEditBinPopup, "EDIT MULTIPLE BINS", "Verify Edit Multiple Bins Popup");
    }

    /**
     * Keyword to click on records from [Bin Maintenance] table for selection
     */
    public void selectMultiRecordsBinMaintenance(int count) {
        int flag = 0;
        List<WebElement> lstElement = Utility_Functions.findElementsByXpath(ownDriver, BinMaintenancePage.binsCountSP);
        if (!lstElement.isEmpty()) {
            if (count < lstElement.size()) {
                for (int i = 0; i < count; i++) {
                    click(lstElement.get(i));
                    Utility_Functions.timeWait(1);
                    flag++;
                }
            } else {
                for (int i = 0; i < lstElement.size() / 2; i++) {
                    click(lstElement.get(i));
                    Utility_Functions.timeWait(1);
                    flag++;
                }
            }
        } else
            report.updateTestLog("Click on Table Records", "No records available to clik!", Status.FAIL);

        if (count == flag) {
            report.updateTestLog("Select records from table", "Clicked on [" + flag + "] rows from Bin Maintenance table", Status.PASS);
        }
    }

    /**
     * Keyword to enter data in [Edit Multiple Bins] popup and verify after saving the details
     */
    public void enterDataInEditMultipleBinPopupAndVerifyAfterSave() {
        int rowCount = Integer.parseInt(jsonData.getData("CountOfRecordsToSelect"));
        String condition = jsonData.getData("BinCondition");
        String zones = jsonData.getData("BinZones");
        String picking = jsonData.getData("BinPicking");
        String receiving = jsonData.getData("BinReceiving");
        String availableToSell = jsonData.getData("BinAvailableToSell");
        String specialHandling = jsonData.getData("BinSpecialHandling");
        String stagingArea = jsonData.getData("BinStagingArea");

        selectDropdownOptionEditMultipleBinsPopup(condition, zones, picking, receiving, specialHandling, availableToSell, stagingArea);
        click(BinMaintenancePage.btnSavEdiBinPopup, "Click [Save] button");
        waitForElementDisappear(MasterPage.loadingSpinner, globalWait);
        commonObj.validateText(BinMaintenancePage.toastMsg, "Selected Bins updated successfully.", "Bins Updated Successfully message is displayed");
        Utility_Functions.timeWait(2);

        vrfyRowValueInBinTable(BinMaintenancePage.lstZonesColumnData, zones, rowCount);
        vrfyRowValueInBinTable(BinMaintenancePage.lstStatusColumnData, condition, rowCount);
        vrfyRowValueInBinTable(BinMaintenancePage.lstAvailableToSellColumnData, availableToSell, rowCount);
    }

    /**
     * Keyword to enter data in [Edit Multiple Bins] popup
     */
    public void selectDropdownOptionEditMultipleBinsPopup(String condition, String zones, String picking, String
            receiving, String specialHandling, String availableToSell, String stagingArea) {
        Utility_Functions.xSelectDropdownByName(ownDriver, report, BinMaintenancePage.ddnConditionEditBinPopup, condition, "Selected option [" + condition + "] in Condition dropdown");
        Utility_Functions.xSelectDropdownByName(ownDriver, report, BinMaintenancePage.ddnZoneEditBinPopup, zones, "Selected option [" + zones + "] in Zoned dropdown");
        Utility_Functions.xSelectDropdownByName(ownDriver, report, BinMaintenancePage.ddnPickingEditBinPopup, picking, "Selected option [" + picking + "] in Picking dropdown");
        Utility_Functions.xSelectDropdownByName(ownDriver, report, BinMaintenancePage.ddnReceivingEditBinPopup, receiving, "Selected option [" + receiving + "] in Receiving dropdown");
        Utility_Functions.xSelectDropdownByName(ownDriver, report, BinMaintenancePage.ddnSpclHndlngEditBinPopup, specialHandling, "Selected option [" + specialHandling + "] in Special Handling dropdown");
        Utility_Functions.xSelectDropdownByName(ownDriver, report, BinMaintenancePage.ddnAvailToSellEditBinPopup, availableToSell, "Selected option [" + availableToSell + "] in Available To Sell dropdown");
        Utility_Functions.xSelectDropdownByName(ownDriver, report, BinMaintenancePage.ddnStgAreaEditBinPopup, stagingArea, "Selected option [" + stagingArea + "] in Staging Area dropdown");
    }

    /**
     * Keyword to verify data under column of records table - Bin Maintenance
     */
    public void vrfyRowValueInBinTable(By element, String expectedText, int rowCount) {
        int flag = 0;
        List<WebElement> lstElements = getListElement(element);
        List<String> lstText = Utility_Functions.xGetTextVisibleListString(ownDriver, lstElements);

        for (String text : lstText) {
            if (text.equalsIgnoreCase(expectedText))
                flag++;
            if (flag == rowCount) {
                report.updateTestLog("Verify rows data", "Validated table rows data", Status.PASS);
                break;
            }
        }
    }

    /**
     * Keyword to select a Bin with Items and click [Delete Bin] button and verify notification msg
     */
    public void tryDeleteBinWithItems() {
        selectBinWithItems();
        click(button("Delete Bin"), "Click on [Delete Bin] button");
        waitForVisible(BinMaintenancePage.toastMsg);
        commonObj.validateText(BinMaintenancePage.toastMsg, "Remove quantities from bin before deleting.", "Remove Items before deleting Bins message is displayed");
    }

    /**
     * Keyword to select a bin having items from records table - Bin Maintenance
     */
    public String selectBinWithItems() {
        String bin = "";
        waitForElementDisappear(MasterPage.loadingSpinner, globalWait);
        List<WebElement> lstElements = getListElement(BinMaintenancePage.lstItemsColumnData);
        List<WebElement> lstBin = getListElement(BinMaintenancePage.lstBinColumnData);
        //List<String> lstBinText = Utility_Functions.xGetTextVisibleListString(driver, lstBin);
        for (WebElement element : lstElements) {
            String itemCount = element.getText().trim();
            if (!itemCount.equalsIgnoreCase("0")) {
                element.click();
                bin = lstBin.get(lstElements.indexOf(element)).getText().trim();
                break;
            }
        }
        return bin;
    }

    /**
     * Keyword to select first record from [Bin Maintenance] table and click on [Delete Bin] button
     */
    public void selectFirstBinRecordAndClickDeleteBinBtn() {
        String binLocation = selectFirstRecordBinMaintenance();
        click(button("Delete Bin"), "Click on [Delete Bin] button");
        waitForVisible(BinMaintenancePage.hdrDeleteBinLocationPopup);
        Utility_Functions.timeWait(1);
        click(BinMaintenancePage.btnNoDeleteBinBinPopup, "Click on [No] button in DELETE BIN LOCATION popup");
        waitForElementDisappear(BinMaintenancePage.hdrDeleteBinLocationPopup, globalWait);
        Utility_Functions.timeWait(1);
        click(button("Delete Bin"), "Click on [Delete Bin] button");
        waitForVisible(BinMaintenancePage.hdrDeleteBinLocationPopup);
        Utility_Functions.timeWait(1);
        click(BinMaintenancePage.btnYesDeleteBinBinPopup, "Click on [Yes] button in DELETE BIN LOCATION popup");
        waitForElementDisappear(MasterPage.loadingSpinner, globalWait);
        waitForVisible(BinMaintenancePage.toastMsg);
        commonObj.validateText(BinMaintenancePage.toastMsg, "Selected Bins deleted successfully.", "Bins deleted message is displayed");
    }

    /**
     * Keyword to select first record from [Bin Maintenance] table and click on [Labels] button
     */
    public void selectFirstBinRecordAndClickLabelsBtn() {
        String binLocation = selectFirstRecordBinMaintenance();
        click(button(" Labels "), "Click on [Labels] button");
        waitForVisible(BinMaintenancePage.hdrPrintLabelsPopup);
        Utility_Functions.timeWait(1);
        commonObj.validateText(BinMaintenancePage.lblBinLocationPrintLabelsPopup, binLocation, "Selected Bin Location is displayed in Print Labels popup");
    }

    /**
     * Keyword to verify fields present in [Print Labels] popup
     */
    public void vrfyUIOfPrintLabelsPopup() {
        commonObj.validateElementExists(BinMaintenancePage.hdrPrintLabelsPopup, "[PRINT LABELS] header is present");
        commonObj.validateElementExists(BinMaintenancePage.itemLabelsTabPopup, "[Item Labels] tab is present");
        commonObj.validateElementExists(BinMaintenancePage.binLabelsTabPopup, "[Bin Labels] tab is present");
        commonObj.validateElementExists(BinMaintenancePage.ddnPrinterPrintLabelPopup, "[Printer] dropdown is present");
        commonObj.validateElementExists(BinMaintenancePage.chkbxSelectAllBinsPrintLabelPopup, "[Select All Bins] checkbox is present");
        commonObj.validateElementExists(BinMaintenancePage.btnCancelPrintLabelsPopup, "[Cancel] button is present");
        commonObj.validateElementExists(BinMaintenancePage.btnPrintLabelsPopup, "[Print] button is present");
    }

    /**
     * Keyword to click [Print] button in [Print Labels] popup
     */
    public void clickPrintAndVerifyToastMsg() {
        click(BinMaintenancePage.btnPrintLabelsPopup, "Click on [Print] button");
        waitForElementDisappear(MasterPage.loadingSpinner, globalWait);
        waitForVisible(BinMaintenancePage.toastMsg);
        commonObj.validateText(BinMaintenancePage.toastMsg, "Labels sent to printer.", "[Labels sent to printer.] message is displayed");
    }

    /**
     * Keyword to select a Bin with Items and click [Labels] button
     */
    public void selectBinWithItemsAndClickLabels() {
        String binLocation = selectBinWithItems();
        click(button(" Labels "), "Click on [Labels] button");
        waitForVisible(BinMaintenancePage.hdrPrintLabelsPopup);
        Utility_Functions.timeWait(1);
        commonObj.validateText(BinMaintenancePage.lblBinLocationPrintLabelsPopup, binLocation, "Selected Bin Location is displayed in Print Labels popup");
    }

    /**
     * Keyword to verify UI of Print Labels - Items Labels popup
     */
    public void verifyUIofItemsLabelsPopup() {
        click(BinMaintenancePage.itemLabelsTabPopup, "Click on [Item Labels] tab button");
        waitForElementDisappear(MasterPage.loadingSpinner, globalWait);
        Utility_Functions.timeWait(1);
        clickAllItemsAndVrfyItsQtyInPrintItemLabelsPopup("1");
        Utility_Functions.timeWait(1);
        clickAllItemsAndVrfyItsQtyInPrintItemLabelsPopup("");
        Utility_Functions.timeWait(1);
        enableDisableSelectAllItemsAndVrfyItemsQty("No");
        enableDisableSelectAllItemsAndVrfyItemsQty("Yes");
    }

    /**
     * Keyword to select all items and verify default qty in Print Items Labels popup
     */
    public void clickAllItemsAndVrfyItsQtyInPrintItemLabelsPopup(String qty) {
        List<WebElement> lstItems = getListElement(BinMaintenancePage.lstBinItemLabelsPopup);
        for (WebElement element : lstItems) {
            click(element, "Click on item no [" + lstItems.indexOf(element) + 1 + "] from list of items to print labels");
            Utility_Functions.timeWait(2);
        }
        List<WebElement> lstQtyTbx = getListElement(BinMaintenancePage.lstQtyItemLabelsPopup);
        validateListValue(lstQtyTbx, qty);
    }

    /**
     * Keyword to enable/disable Select All Items checkbox and verify default qty in Print Items Labels popup
     *
     * @Parameter stateExpected e.g., Yes or No
     */
    public void enableDisableSelectAllItemsAndVrfyItemsQty(String stateExpected) {
        selectUnselectChbxCreateNewBinPopup(BinMaintenancePage.chkbxSelectAllItemsPrintLabelPopup, stateExpected);
        Utility_Functions.timeWait(1);
        List<WebElement> lstQtyTbx = getListElement(BinMaintenancePage.lstQtyItemLabelsPopup);
        if (stateExpected.equalsIgnoreCase("Yes"))
            validateListValue(lstQtyTbx, "1");
        else
            validateListValue(lstQtyTbx, "");
    }

    /**
     * Keyword to verify Items Qty value in Print Labels popup
     */
    public void validateListValue(List<WebElement> lstElement, String valueExpected) {
        int flag = 0;
        List<String> lstElementValue = Utility_Functions.xGetTextVisibleListString(ownDriver, lstElement);
        for (String value : lstElementValue) {
            if (value.equalsIgnoreCase(valueExpected))
                flag++;
        }
        if (flag == lstElementValue.size())
            report.updateTestLog("Verify List Value", "Verify value equals " + valueExpected, Status.PASS);
        else
            report.updateTestLog("Verify List Value", "Verify value equals " + valueExpected, Status.FAIL);
    }

    /**
     * Keyword to Verify duplicate Zone
     */
    public void verifyDuplicateZone() {
        click(button(" New Zone "), "Click 'New Zone' button");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Save button is disabled");
        String zoneName = verifySaveButtonDisZoneName();
        clearText(BinMaintenancePage.zoneName);
        String zoneAbv = verifySaveButtonDisZoneAvr();
        sendKeys(BinMaintenancePage.zoneName, zoneName, "Enter Zone Name");
        click(button("Save "), "Click 'Save' Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//div[text()='Zone " + zoneName + " already exists']"), "Zone " + zoneName + " already exists", "'Zone " + zoneName + " already exists' error message is present");
        commonObj.validateText(By.xpath("//div[text()='Zone " + zoneAbv + " already exists']"), "Zone " + zoneAbv + " already exists", "'Zone " + zoneAbv + " already exists' error message is present");
        verifyDuplicateZoneName(zoneName);
        verifyDuplicateZoneAbv(zoneAbv);
        verifyBlankSpaceCreateZone();
    }

    public void isZoneFieldDisabled(String label, String text) {
        commonObj.validateElementExists(zoneDisabledFields(label, text), label + " field is disabled");
    }

    public void isZoneFieldsEditable(String label, String text) {
        boolean isDisabled = false;
        try {
            isDisabled = ownDriver.findElement(zoneDisabledFields(label, text)).isDisplayed();
            throw new Exception("Zone Field should Enable");
        } catch (Exception e) {
            Utility_Functions.xAssertEquals(report, isDisabled, false, label + " field is Enabled");
        }
    }

    public void editZoneField(String zoneName) {
        Utility_Functions.timeWait(2);
        String zName = zoneName + "a";
        Utility_Functions.xUpdateJson("zoneName", zName);
        sendKeys(By.xpath("//input[@ng-reflect-model='" + zoneName + "']"), zName, "Edit Zone Name");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,saveButtonEditedZone(zName), "Click 'Save' Button");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.toaster,"Click Toaster message");
        commonObj.validateText(BinMaintenancePage.toaster, "Zone " + zName + " updated successfully.", "'Zone " + zName + " updated successfully.' message is present");
        commonObj.validateElementExists(zoneDisabledFields("Zone Name", zName), "Zone Name field is modified and disabled");
    }

    /**
     * Keyword to Verify Edit Zone
     */
    public void verifyEditZone() {
        String zoneAvr = expandZone();
        String zoneName = Utility_Functions.xGetJsonData("zoneName");
        isZoneFieldDisabled("Zone Name", zoneName);
        isZoneFieldDisabled("Abbreviation", zoneAvr);
        Boolean bl1 = ownDriver.findElement(BinMaintenancePage.pickSequence).isEnabled();
        Utility_Functions.xAssertEquals(report, bl1, false, "Pick Sequence field is disabled");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Save button is disabled");
        clickEditIcon(zoneAvr);
        isZoneFieldsEditable("Zone Name", zoneName);
        isZoneFieldsEditable("Abbreviation", zoneAvr);
        editZoneField(zoneName);
    }

    public void validatePickSeqField(String zoneName, String validVal, String inValidVal) {
        Utility_Functions.timeWait(2);
        sendKeys(pickSequence(zoneName), validVal, "Enter '" + validVal + "' into Pick Sequence text field");
        boolean bl = Utility_Functions.xIsDisplayed(ownDriver, BinMaintenancePage.pickSeqError);
        Utility_Functions.xAssertEquals(report, bl, false, "Error message is disappeared");
        sendKeys(pickSequence(zoneName), "100000");
        sendKeys(pickSequence(zoneName), inValidVal, "Enter '" + inValidVal + "' into Pick Sequence text field");
        Utility_Functions.timeWait(2);
        commonObj.validateText(BinMaintenancePage.pickSeqError, "Please enter a pick sequence between 1-999.", "'Please enter a pick sequence between 1-999.' is present");
    }

    /**
     * Keyword to Verify Pick sequence
     */
    public void verifyPickSequence() {
        String zoneAvr = expandZone();
        String zoneName = Utility_Functions.xGetJsonData("zoneName");
        clickEditIcon(zoneAvr);
        validatePickSeqField(zoneName, "1", "1000");
        clearText(pickSequence(zoneName));
        commonObj.validateText(BinMaintenancePage.pickSeqError, "Please enter a pick sequence between 1-999.", "'Please enter a pick sequence between 1-999.' is present");
        validatePickSeqField(zoneName, "-1", "0");
        validatePickSeqField(zoneName, "2.5", "#$%%FGH");
    }

    /**
     * Keyword to Navigate to Item Bin Ledger
     */
    public void navigateItemBinLedgerPage() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.inboxIcon, "Click Inbox Icon");
        Utility_Functions.timeWait(2);
        click(BinMaintenancePage.itemBinLedger, "Navigate to item Bin Ledger page");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemBinLedgerHeader,"");
        commonObj.validateText(BinMaintenancePage.itemBinLedgerHeader, "ITEM-BIN LEDGER", "ITEM-BIN LEDGER header is present");
    }

    /**
     * Keyword to verify Availability of fields on the search filter Item Bin Ledger
     */
    public void verifySearchFilterLedger() {
        Utility_Functions.timeWait(3);
        String[] field = {"Item Number", "Bin Location", "Source", "Reference", "Start Order Date", "End Order Date", "User"};
        for (String label : field) {
            commonObj.validateElementExists(By.xpath("//label[contains(text(),'" + label + "')]/parent::div/descendant::input"), label + " is present");
        }
        commonObj.validateElementExists(BinMaintenancePage.applyFilter, "Apply filter button is present");
        commonObj.validateElementExists(BinMaintenancePage.clearFilter, "Clear All filter button is present");
    }

    /**
     * Keyword to UI of Item Bin Ledger Page
     */
    public void itemBinLedgerPageUI() {
        commonObj.validateText(tabs("Item Number is missing."), "Item Number is missing.", "'Item Number is missing.' message is present");
        verifyIcons();
        verifySearchFilterLedger();
        commonObj.validateElementExists(BinMaintenancePage.excelBtn, "Export to Excel button is present");
    }

    /**
     * Keyword to verify Apply Clear Filter
     */
    public void verifyApplyClearFilter() {
        Utility_Functions.xClickHiddenElement(ownDriver, BinMaintenancePage.clearFilter);
        Utility_Functions.timeWait(2);
        commonObj.validateText(tabs("Item Number is missing."), "Item Number is missing.", "'Item Number is missing.' message is present");
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Apply Filter button is disabled");
        click(TruckPage.filtersCrossIcon, "Click 'x' icon");
        Utility_Functions.timeWait(2);
        try {
            Utility_Functions.xIsDisplayed(ownDriver, TruckPage.searchFilterPanelTitle);
        } catch (Exception e) {
            Utility_Functions.xAssertEquals(report, false, false, "Search Filter is disappeared");
        }
    }

    public String navigateToItemBinMain() {
        String itemNumber = jsonData.getData("itemNumber");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemNumber,"");
        sendKeys(BinMaintenancePage.itemNumber, itemNumber, "Enter " + itemNumber + " into Item number text field");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//a[contains(text(),'" + itemNumber + "')]"), "Click Item Number");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemBinManItemDet,"'Item-Bin Maintenance - Item Details' header is present");
        return itemNumber;
    }

    public void backAndClearFilter() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button("Back"), "Click on back button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//h2"), "ITEM-BIN LEDGER", "Navigate back to 'ITEM-BIN LEDGER' Page");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.filterSearch, "Click Search icon");
        Utility_Functions.timeWait(2);
        Utility_Functions.xClickHiddenElement(ownDriver, BinMaintenancePage.clearFilter);
        commonObj.validateText(tabs("Item Number is missing."), "Item Number is missing.", "'Item Number is missing.' message is present");
    }

    /**
     * Keyword to verify Apply Clear Filter
     */
    public void verifyItemNumberField() {
        navigateToItemBinMain();
        backAndClearFilter();
    }

    public void verifyItemNumber(String value) {
        Utility_Functions.timeWait(2);
        sendKeys(BinMaintenancePage.itemNumber, value, "Enter '" + value + "' into Item number text field");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.timeWait(3);
        commonObj.validateText(BinMaintenancePage.toaster, "Not a valid item number.", "'Not a valid item number.' error message is present");
        Utility_Functions.timeWait(2);
        click(BinMaintenancePage.toasterCloseIcon, "Close error toaster");
    }

    /**
     * Keyword to verify Neg ItemNumber Field
     */
    public void verifyNegItemNumberField() {
        Utility_Functions.timeWait(3);
        verifyItemNumber("-10");
        verifyItemNumber("^%$DF123");
        verifyItemNumber("0");
        verifyItemNumber("1.123");
    }

    /**
     * Keyword to verify Bin Location
     */
    public void verifyBinLocation() {
        Utility_Functions.timeWait(2);
        createBin();
        backAndClearFilter();
        String binLocation = Utility_Functions.xGetJsonData("itemLocation");
        Utility_Functions.timeWait(2);
        sendKeys(BinMaintenancePage.binLocationFilter, binLocation, "Enter Bin location");
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Save button is disabled");
        navigateToItemBinMain();
        int i = 0;
        while (i == 0) {
            try {
                Utility_Functions.timeWait(5);
                Utility_Functions.waitTillClickHardSleep(report,ownDriver,BinMaintenancePage.itemBinManItemDet,"'Item-Bin Maintenance - Item Details' header is present");
                Utility_Functions.xScrollIntoView(ownDriver, By.xpath("//td[contains(text(),'" + binLocation + "')]"));
                commonObj.validateElementExists(By.xpath("//td[contains(text(),'" + binLocation + "')]"), binLocation + " Bin location is created");
                break;
            } catch (Exception e) {
                Utility_Functions.xClickHiddenElement(ownDriver, ownDriver.findElements(By.xpath("//a[text()='›']")).get(1));
                Utility_Functions.timeWait(4);
            }
        }
    }
}