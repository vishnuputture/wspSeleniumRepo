package businesskeywords.warehousing.BinMaintanence;

import businesskeywords.common.Login;
import businesskeywords.warehousing.Drivers;
import businesskeywords.warehousing.Manifests;
import businesskeywords.warehousing.Trucks;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.SPO.SpoPage;
import pages.SalesOrders.SalesOrdersPage;
import pages.common.MasterPage;
import pages.inventory.CostAdjustmentPage;
import pages.inventory.ItemMasterPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import pages.warehouse.BinMaintenance.BinMaintenancePage;
import pages.warehouse.DriversPage;
import pages.warehouse.TruckPage;
import software.amazon.awssdk.services.fsx.model.BackupNotFoundException;
import supportLibraries.Utility_Functions;
import testcases.PO.Spo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class binMaintenance extends ReusableLib {
    CommonActions commonObj;
    businesskeywords.SPO.Spo sp;
    Login login;

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
    }

    /**
     * Keyword to Navigate to Suggested PO
     */
    public void navigateToBinMain() {
        String url = properties.getProperty("binMaintenanceURL");
        driver.get(url);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to verify UI of Item-Bin Maintenance page when no search filters were applied
     */
    public void binMaintenanceUI() {
        commonObj.validateText(By.xpath("//h2"), "Item-Bin Maintenance", "Item-Bin Maintenance Header is present");
        commonObj.validateText(tabs("Item Maintenance"), "Item Maintenance", "Item Maintenance tab is present");
        commonObj.validateText(tabs("Bin Maintenance"), "Bin Maintenance", "Bin Maintenance tab is present");
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
        String[] field = {"Item Number", "Item Description", "Zone", "Bin Condition", "IOV", "Bin Type", "MF", "PD", "VN", "Total On Hand Qty"};
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

    /**
     * Keyword to verify Availability of Menu Icon
     */
    public void verifyAvailMenuIcon() {
        click(BinMaintenancePage.menuIcon, "Click on main menu");
        String[] field = {"BIN LOCATIONS", "Item-Bin Maintenance", "Item-Bin Ledger", "SHIPPING MANIFESTS", "Print Documents and Labels"};
        for (String label : field) {
            commonObj.validateText(tabs(label), label, label + " option is present");
        }
    }

    public void verifyApplyFilter() {
        commonObj.validateElementExists(TruckPage.searchFilterPanelTitle, "Search filter is present");
        click(BinMaintenancePage.applyFilter, "Click Apply filter button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(tabs("Apply filters to see results."), "Apply filters to see results.", "'Apply filters to see results.' message is present");
    }

    public void verifyClearAllFilter() {
        click(TruckPage.filterSearch, "Click Search icon");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Clear All Filter button is disabled");
        click(BinMaintenancePage.itemNumberContains, "Enable item Number Contains check box");
        Utility_Functions.timeWait(2);
        Boolean bl = Utility_Functions.xIsDisplayed(driver, BinMaintenancePage.buttonDis);
        Utility_Functions.xAssertEquals(report, bl, false, "Clear All Filter button is enabled");
        Utility_Functions.xClickHiddenElement(driver, BinMaintenancePage.clearFilter);
        Utility_Functions.timeWait(2);
        Boolean bl1 = driver.findElement(BinMaintenancePage.itemNumberContains).isSelected();
        Utility_Functions.xAssertEquals(report, bl1, false, "After Click Clear All Filter, item Number Contains check box is disabled");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Clear All Filter button is disabled");
    }

    /**
     * Keyword to verify Apply Filter and Clear All Filter
     */
    public void verifyApplyClearAllFilter() {
        click(TruckPage.filtersCrossIcon, "Click search close icon");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(BinMaintenancePage.searchIcon, "Search icon is present");
        click(TruckPage.filterSearch, "Click Search icon");
        Utility_Functions.timeWait(2);
        verifyApplyFilter();
        verifyClearAllFilter();
    }

    public void enableAsteriskItem(By by, String decision) {
        click(by, decision + " exclude Asterisk Items check box");
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
        click(TruckPage.filterSearch, "Click Search icon");
        Utility_Functions.timeWait(2);
        enableAsteriskItem(by, "Disable");
        Utility_Functions.xAssertEquals(report, Utility_Functions.xIsDisplayed(driver, tabs("Item Number")), false, "Item number result Filter is removed");
        Utility_Functions.xAssertEquals(report, Utility_Functions.xIsDisplayed(driver, tabs(checkBoxName)), false, checkBoxName + " result Filter is removed");
        Utility_Functions.xAssertEquals(report, Utility_Functions.xIsDisplayed(driver, tabs("Clear Filters ")), false, "Clear Filters result Filter is removed");
        click(TruckPage.filterSearch, "Click Search icon");
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to verify check box Filter
     */
    public void verifyExcludeAstJobZZ98Item() {
        verifyExcludeItems(BinMaintenancePage.excludeAsteriskItems, "Exclude * Items");
        verifyExcludeItems(BinMaintenancePage.includeJobItems, "Exclude Job Items");
        verifyExcludeItems(BinMaintenancePage.excludeZZ98Items, "Exclude ZZ98 Items");
    }

    /**
     * Keyword to verify Contains check box Filter
     */
    public void verifyContainsCheckBox() {
        String itemNumber = jsonData.getData("itemNo");
        sendKeys(BinMaintenancePage.itemNumber, itemNumber, "Enter " + itemNumber + " into Item number text field");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        click(TruckPage.filterSearch, "Click Search icon");
        Utility_Functions.timeWait(2);
        click(BinMaintenancePage.itemNumberContains, "Enable item Number Contains check box");
        Utility_Functions.timeWait(2);
        Boolean bl1 = driver.findElement(BinMaintenancePage.itemNumberContains).isSelected();
        Utility_Functions.xAssertEquals(report, bl1, true, "After Click Clear All Filter, item Number Contains check box is enabled");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(BinMaintenancePage.buttonDis, "Apply filter button is disabled");
        Utility_Functions.xAssertEquals(report, driver.findElement(BinMaintenancePage.itemNumber).getAttribute("ng-reflect-model"), "~" + itemNumber, "");
    }

    /**
     * Keyword to Verify Suggested Matches pop-up message
     */
    public void verifySuggestedMatchesPopUp() {
        String itemNumber = jsonData.getData("itemNo");
        sendKeys(BinMaintenancePage.itemNumber, itemNumber, "Enter " + itemNumber + " into Item number text field");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.timeWait(3);
        commonObj.validateText(BinMaintenancePage.matchesHeader, "SUGGESTED MATCHES", "'SUGGESTED MATCHES' popup message is present");
        click(getColumnData("Item Number"), "Click Item Number");
        Utility_Functions.timeWait(4);
        commonObj.validateText(BinMaintenancePage.itemBinManItemDet, "Item-Bin Maintenance - Item Details", "'Item-Bin Maintenance - Item Details' header is present");
        click(button("Back"), "Click on back button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//h2"), "Item-Bin Maintenance", "Navigate back to 'Item-Bin Maintenance' Page");
    }

    /**
     * Keyword to Verify the negative Scenarios for Item Number input field
     */
    public void verifyItemNoField() {
        String negativeValue = jsonData.getData("negativeValue");
        sendKeys(BinMaintenancePage.itemNumber, negativeValue, "Enter " + negativeValue + " into Item number text field");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.timeWait(3);
        commonObj.validateText(tabs("There are currently no results."), "There are currently no results.", "'There are currently no results.' message is present");
        click(TruckPage.filterSearch, "Click Search icon");
        Utility_Functions.timeWait(2);
        String alphaNum = jsonData.getData("alphaNumeric");
        sendKeys(BinMaintenancePage.itemNumber, alphaNum, "Enter " + alphaNum + " into Item number text field");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.timeWait(3);
        commonObj.validateText(tabs("There are currently no results."), "There are currently no results.", "'There are currently no results.' message is present");
    }

    public void validateAndBack() {
        commonObj.validateText(BinMaintenancePage.itemBinManItemDet, "Item-Bin Maintenance - Item Details", "'Item-Bin Maintenance - Item Details' header is present");
        click(button("Back"), "Click on back button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//h2"), "Item-Bin Maintenance", "Navigate back to 'Item-Bin Maintenance' Page");
    }

    /**
     * Keyword to Verify the Item description filter
     */
    public void verifyItemDescriptionFilter() {
        click(BinMaintenancePage.itemNumberContains, "Enable Contains check box");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.timeWait(3);
        String desc = driver.findElement(By.xpath("//td/div")).getText().trim();
        Utility_Functions.xUpdateJson("ItemNmDescription", desc);
        click(TruckPage.filterSearch, "Click Search icon");
        Utility_Functions.timeWait(2);
        sendKeys(BinMaintenancePage.itemDescription, desc, "Enter description");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.timeWait(3);
        if (driver.findElements(BinMaintenancePage.itemBinManItemDet).size() > 1) {
            click(By.xpath("//td/a"));
            Utility_Functions.timeWait(3);
        }
        validateAndBack();
    }

    /**
     * Keyword to Verify the negative Scenarios for Item Description input field
     */
    public void verifyItemDescriptionField() {
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.timeWait(3);
        commonObj.validateText(tabs("Apply filters to see results."), "Apply filters to see results.", "'Apply filters to see results.' message is present");
        click(TruckPage.filterSearch, "Click Search icon");
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
        click(BinMaintenancePage.itemNumberContains, "Enable Contains check box");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.timeWait(3);
        String itemCount = Utility_Functions.getText(driver, BinMaintenancePage.itemCount);
        commonObj.validateElementExists(BinMaintenancePage.itemCount, "Total item present on Item Maintenance Tab: " + itemCount);
    }

    /**
     * Keyword to Verify the UI of the page when Item Numbers are available
     */
    public void verifyUIItemNumbersAreAvailable() {
        enableContainsFilter();
        commonObj.validateText(BinMaintenancePage.selectAllCheckbox, "Select All Items", "'Select All Items' check box is present");
        String[] actText = {"Item Number", "Description", "MF PD VN", "Bins", "On Hand"};
        List<WebElement> els = driver.findElements(By.xpath("//th"));
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
        Utility_Functions.xScrollWindow(driver);
        Utility_Functions.timeWait(2);
        click(By.xpath("//span[text()='" + pageNum + "']"), "Click on '" + pageNum + "' Present below the Left corner of the page");
        int ItemCount = driver.findElements(BinMaintenancePage.itemCountSP).size();
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
        Utility_Functions.xScrollWindow(driver);
        Utility_Functions.xScrollWindow(driver);
        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(driver, DriversPage.onePage)) {
            int itemCount = driver.findElements(BinMaintenancePage.itemCountSP).size();
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
     * Keyword to Verify Pagination against current page
     */
    public void selectPage(int actPageNo, String expPage, String arrowIcon) {
        click(driver.findElements(DriversPage.pageArrow).get(actPageNo), "Click on " + arrowIcon + " Present below the Right Corner of the page");
        Utility_Functions.xScrollWindow(driver);
        String pageNo = driver.findElement(TruckPage.currentPage).getAttribute("ng-reflect-model");
        Utility_Functions.xAssertEquals(report, pageNo, expPage, "Moved to " + pageNo + " Page");
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to Verify functionality of Pagination
     */
    public void paginationItemMaintenance() {
        int size = 2;
        Utility_Functions.xScrollWindow(driver);
        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(driver, DriversPage.onePage)) {
            commonObj.validateText(DriversPage.onePage, "of 1", "One page is available");
        } else {
            click(driver.findElements(DriversPage.pageArrow).get(2));
            Utility_Functions.xScrollWindow(driver);
            click(driver.findElements(DriversPage.pageArrow).get(0));
            Utility_Functions.xScrollWindow(driver);
            selectPage(2, "2", "Right Arrow (>)");
            selectPage(1, "1", "Left Arrow (<)");
            click(driver.findElements(DriversPage.pageArrow).get(3), "Click on " + 3 + " Present below the Right Corner of the page");
            Utility_Functions.xScrollWindow(driver);
            String pageNo = driver.findElement(TruckPage.currentPage).getAttribute("ng-reflect-model");
            Utility_Functions.xAssertEquals(report, pageNo, pageNo, "Moved to " + pageNo + " Page");
            Utility_Functions.timeWait(2);
            selectPage(0, "1", "Left double Arrow (<<)");
        }
    }

    /**
     * Keyword to Verify the functionality of Select All Items Checkbox
     */
    public void verifySelectAllCheckBox() {
        click(BinMaintenancePage.selectAllCheckbox, "Enable 'Select All Items' check box");
        Utility_Functions.timeWait(2);
        int size = driver.findElements(BinMaintenancePage.highlightRow).size();
        Utility_Functions.xAssertEquals(report, size, 10, "All items are Selected");
        Utility_Functions.timeWait(2);
        click(By.xpath("//tr[2]"), "Deselect one item from the list");
        Utility_Functions.timeWait(2);
        Utility_Functions.xAssertEquals(report, driver.findElements(BinMaintenancePage.highlightRow).size(), 9, "One record is Deselected");
        click(BinMaintenancePage.selectAllCheckbox, "Click 'Select All Items' check box");
        Utility_Functions.timeWait(2);
        click(BinMaintenancePage.selectAllCheckbox, "Click 'Deselect All Items' check box");
        Utility_Functions.timeWait(2);
        Boolean count = Utility_Functions.xIsDisplayed(driver, BinMaintenancePage.highlightRow);
        Utility_Functions.xAssertEquals(report, count, false, "All items are Deselected");
        Utility_Functions.timeWait(2);
        click(By.xpath("//tr[2]"), "Select one item from the list");
        Utility_Functions.timeWait(2);
        Utility_Functions.xAssertEquals(report, driver.findElements(BinMaintenancePage.highlightRow).size(), 1, "One record is Selected");
    }

    public void validateNegQty() {
        click(button(" Labels "), "Click Label button");
        Utility_Functions.timeWait(2);
        click(BinMaintenancePage.selectAllPop, "Click select All Items");
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
            click(By.xpath("//tr[" + i + "]"), "Select item from the list");
            Utility_Functions.timeWait(2);
        }
        click(button(" Labels "), "Click Label button");
        commonObj.validateText(DriversPage.newDriverHeader, "Print Labels", "'Print Labels' popup is present");
        click(button("Cancel "), "Click Cancel Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//h2"), "Item-Bin Maintenance", "Item-Bin Maintenance Header is present");
        click(button(" Labels "), "Click Label button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(DriversPage.newDriverHeader, "Print Labels", "'Print Labels' popup is present");
        click(button("Print"), "Click Print Button");
        Utility_Functions.timeWait(3);
        commonObj.validateText(BinMaintenancePage.toaster, "Labels sent to printer.", "Toaster 'Labels sent to printer.' is present");
        validateNegQty();
    }

    public ArrayList<String> itemDetails() {
        ArrayList<String> setVal = new ArrayList();
        for (int i = 1; i <= 4; i++) {
            String val = getText(By.xpath("(//td/*)[" + i + "]")).trim();
            setVal.add(val);
        }
        String bin = getText(By.xpath("(//td)[4]")).trim();
        String onHand = getText(By.xpath("(//td)[5]")).trim();
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
        Utility_Functions.actionKey(Keys.ENTER, driver);
        Utility_Functions.timeWait(4);
        String[] manufacturerCode = getAttribute(ItemMasterPage.manufacturerCode, "value").trim().split("-");
        String[] productCode = getAttribute(ItemMasterPage.productCode, "value").trim().split("-");
        String[] vendorCode = getAttribute(ItemMasterPage.vendorCode, "value").trim().split("-");
        String desc = getAttribute(ItemMasterPage.itemDesc1, "value").trim();
        Utility_Functions.xAssertEquals(report, val.get(1).toString(), desc, "Description ");
        String[] splitCode = val.get(3).toString().trim().split(" ");
        Utility_Functions.xAssertEquals(report, splitCode[0], manufacturerCode[0].trim(), "Manufacture code ");
        Utility_Functions.xAssertEquals(report, splitCode[1], productCode[0].trim(), "Product code ");
        Utility_Functions.xAssertEquals(report, splitCode[2], vendorCode[0].trim(), "vendor code ");
        commonObj.validateText(BinMaintenancePage.outQtyOnHand, val.get(5).toString(), "On hand quantity matches");
        click(SpecialPriceAllowancePage.btnExit);
    }

    public void validateItemHeader() {
        click(By.xpath("//td/a"));
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
            Utility_Functions.xAssertEquals(report, driver.findElement(By.xpath("//th[contains(text(),'" + actText[i] + "')]")).getText().trim(), actText[i], "");
            i++;
        }
        String[] actButton = {"Back", " Edit Bin ", "Create Bin", "Add Bin"};
        for (int i = 0; i < 4; i++) {
            Utility_Functions.xAssertEquals(report, driver.findElement(button(actButton[i])).getText(), actButton[i], "");
            i++;
        }
    }

    /**
     * Keyword to Verify the functionality of select/Deselect items
     */
    public void verifySelectDeselectItem() {
        {
            click(By.xpath("//tr[1]"), "Select item from the list");
            Utility_Functions.timeWait(2);
            Utility_Functions.xAssertEquals(report, driver.findElements(BinMaintenancePage.highlightRow).size(), 1, "One record is Selected");
            click(By.xpath("//tr[1]"), "Select item from the list");
            Utility_Functions.timeWait(2);
            Boolean count = Utility_Functions.xIsDisplayed(driver, BinMaintenancePage.highlightRow);
            Utility_Functions.xAssertEquals(report, count, false, "item is Deselected");
        }
    }
}
