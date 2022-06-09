package businesskeywords.warehousing.ReceivingInProcess;

import businesskeywords.PurchaseOrders.CreatePurchaseOrder;
import businesskeywords.warehousing.BinMaintanence.binMaintenance;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.PurchaseOrders.InventoryReceiptsPage;
import pages.PurchaseOrders.PurchaseOrderDetailsPage;
import pages.SPO.SpoPage;
import pages.common.MasterPage;
import pages.pricing.matrixcost.MatrixCostUpdatePage;
import pages.warehouse.BinMaintenance.BinMaintenancePage;
import pages.warehouse.ReceivingInProcess.ReceivingInProcessPage;
import pages.warehouse.TruckPage;
import supportLibraries.Utility_Functions;

import java.util.List;

public class ReceivingInProcess extends ReusableLib {
    CommonActions commonObj;
    binMaintenance binMain;
    CreatePurchaseOrder createPurchaseOrder;
    private FrameworkDriver ownDriver;

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
        createPurchaseOrder = new CreatePurchaseOrder(helper);
        ownDriver=helper.getGSDriver();
    }

    /**
     * Keyword to Navigate to ReceivingInProcess
     */
    public void navigateToReceivingInProcess() {
        ownDriver.get(properties.getProperty("ReceivingInProcess"));
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
        List<WebElement> els = ownDriver.findElements(ReceivingInProcessPage.searchLabels);
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
        if (isDisplayed(ReceivingInProcessPage.clearFilterCrossIcon)) {
            click(ReceivingInProcessPage.clearFilterCrossIcon, "Click Vendor x");
            Utility_Functions.timeWait(2);
        } else {
            click(TruckPage.filterSearch, "Click Search Filter icon");
        }
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
        if (userId!=null) {
            clearText(searchField("Purchase Order"));
            searchAndApplyFilter("User ID", userId);
            commonObj.validateText(By.xpath("//tr/td/span"), userId, "User id is present" + userId);
            clickSearchIcon();
        }
        String vendor = ownDriver.findElements(By.xpath("//tr/td")).get(1).getText();
        clearText(searchField("User ID"));
        searchAndApplyFilter("Vendor", vendor);
        Utility_Functions.xAssertEquals(report, ownDriver.findElements(By.xpath("//tr/td")).get(1).getText(), vendor, "");
    }

    /**
     * Keyword to get User Id
     */
    public String getUserId() {
        String userID = null;
        List<WebElement> list = ownDriver.findElements(By.xpath("//tr/td/span"));
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
        Utility_Functions.timeWait(4);
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
        List<WebElement> els = ownDriver.findElements(ReceivingInProcessPage.searchLabels);
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

    public void openNewTab() {
        Utility_Functions.openNewTab(ownDriver);
    }

    /**
     * Keyword to navigate to RF Gun
     */
    public void navigateToRFGun() {
        openNewTab();
        ownDriver.get(properties.getProperty("RF_Gun"));
        Utility_Functions.timeWait(4);
        if (Utility_Functions.isAlert(ownDriver)) {
        }
        sendKeys(ReceivingInProcessPage.userRF, properties.getProperty("VPNUsn"), "Enter RF user name");
        Utility_Functions.timeWait(3);
        sendKeys(ReceivingInProcessPage.passwordRF, properties.getProperty("VPNPass"), "Enter RF Password");
        Utility_Functions.timeWait(4);
        click(ReceivingInProcessPage.loginBtn, "Click Login Button");
        click(ReceivingInProcessPage.putAway, "Click Receive Put Away");
    }

    /**
     * Keyword to search Item Or PO
     */
    public void searchPo() {
        Utility_Functions.timeWait(4);
        sendKeys(ReceivingInProcessPage.searchPo, Utility_Functions.xGetJsonData("PONumber"), "search Item Or PO");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    }

    public void processPurchase() {
        Utility_Functions.timeWait(5);
        click(ReceivingInProcessPage.poItems, "Click on 1st PO Item");
        Utility_Functions.timeWait(5);
        if (isDisplayed(ReceivingInProcessPage.completeBtn)) {
            click(ReceivingInProcessPage.completeBtn, "Click Complete PO button");
        }
        Utility_Functions.timeWait(5);
        createBin();
        enterQty(jsonData.getData("InvalidQty"));
        Utility_Functions.timeWait(2);
        String[] remainingCount = getText(ReceivingInProcessPage.remainingCount).split(":");
        enterQty(remainingCount[1].trim());
    }

    public void processPartialPurchase() {
        Utility_Functions.timeWait(5);
        click(ReceivingInProcessPage.poItems, "Click on 1st PO Item");
        Utility_Functions.timeWait(5);
        if (isDisplayed(ReceivingInProcessPage.completeBtn)) {
            click(ReceivingInProcessPage.completeBtn, "Click Complete PO button");
        }
        Utility_Functions.timeWait(5);
        createBin();
        enterQty(jsonData.getData("InvalidQty"));
        Utility_Functions.timeWait(2);
        String[] remainingCount = getText(ReceivingInProcessPage.remainingCount).split(":");
        int count = Integer.parseInt(remainingCount[1].trim());
        enterQty("" + (count - 2) + "");
    }

    /**
     * Keyword to process the PO
     */
    public void processPO() {
        processPurchase();
        enterScanBarCode();
    }

    /**
     * Keyword to process the Partial PO
     */
    public void processPartialPO() {
        processPartialPurchase();
        enterScanBarCode();
    }

    /**
     * Keyword to Enter Scan Bar Code
     */
    public void enterScanBarCode() {
        int size = 0;
        sendKeys(ReceivingInProcessPage.scanBarCode, "A");
        Utility_Functions.timeWait(4);
        size = ownDriver.findElements(By.xpath("//ul/li")).size();
        if (size == 0) {
            Utility_Functions.timeWait(5);
            size = ownDriver.findElements(By.xpath("//ul/li")).size();
        }
        click(ownDriver.findElements(By.xpath("//ul/li")).get(size - 1), "Select Location");
        Utility_Functions.timeWait(2);
        Utility_Functions.xUpdateJson("BinLocation", getAttribute(ReceivingInProcessPage.scanBarCode, "value"));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.timeWait(5);
    }

    public void navigateBack() {
        ownDriver.navigate().back();
        Utility_Functions.timeWait(4);
    }

    public void enterScanBarCodeNotReceiveRfGun() {
        sendKeys(ReceivingInProcessPage.scanBarCode, Utility_Functions.xGetJsonData("BinLocation"));
        Utility_Functions.timeWait(4);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.timeWait(1);
        commonObj.validateText(spanElement("not available for RF Gun Receiving"), Utility_Functions.xGetJsonData("BinLocation") + " is not available for RF Gun Receiving.", Utility_Functions.xGetJsonData("BinLocation") + " is not available for RF Gun Receiving. is present");
    }

    /**
     * Keyword to Enter Quantity
     */
    public void enterQty(String qty) {
        Utility_Functions.timeWait(3);
        sendKeysAndTab(ReceivingInProcessPage.qtyTxtBox, qty, "Enter " + qty + " into Quantity field");
        if (isDisplayed(ReceivingInProcessPage.serialItemPopup)) {
            click(ReceivingInProcessPage.skipBtn, "Click Skip Button");
            Utility_Functions.timeWait(2);
        }
        if (qty.contains("-"))
            commonObj.validateElementExists(ReceivingInProcessPage.qtyError, "Error in Quantity field");
    }

    /**
     * Keyword to switch Back To Receiving In Process
     */
    public void switchBackToReceivingInProcess() {
        Utility_Functions.timeWait(2);
        Utility_Functions.xSwitchToWindow(ownDriver, 0);
    }

    /**
     * Keyword to verify Receive Search Po
     */
    public void verifyReceiveSearchPo() {
        searchItem();
        commonObj.validateText(spanElement("There are no orders currently being received."), "There are no orders currently being received.", "'There are no orders currently being received.' message is present");
    }

    public void clickReceiveInProcessLink() {
        Utility_Functions.timeWait(4);
        click(By.xpath("//a[text()='Receiving in Process']"));
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    public void switchToNextWindow() {
        Utility_Functions.xSwitchToWindow(ownDriver, report, 1);
    }

    public void verifyPoNotFound() {
        String po = Utility_Functions.xGetJsonData("PONumber");
        Utility_Functions.timeWait(4);
        commonObj.validateText(BinMaintenancePage.toaster, "PO " + po + " is not available.", "'PO " + po + " is not available.' is present");
    }

    public void searchItem() {
        clickSearchIcon();
        searchAndApplyFilter("Purchase Order", Utility_Functions.xGetJsonData("PONumber"));
    }

    public void confirmFreight() {
        Utility_Functions.timeWait(6);
        if (isDisplayed(InventoryReceiptsPage.btnProcess)) {
            click(InventoryReceiptsPage.btnProcess, "Click on process");
            Utility_Functions.timeWait(2);
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        }
    }

    /**
     * Keyword to verify Received / Open Lines
     */
    public void verifyReceivedOpenLine() {
        Utility_Functions.timeWait(4);
        if (isDisplayed(ReceivingInProcessPage.refreshPoListLink)) {
            Utility_Functions.xClickHiddenElement(ownDriver, ReceivingInProcessPage.refreshPoListLink);
        }
        Utility_Functions.timeWait(4);
        commonObj.validateText(By.xpath("//tr/td/a"), Utility_Functions.xGetJsonData("PONumber"), "Result PO number found");
        commonObj.validateText(By.xpath("//div[text()='" + jsonData.getData("ReceivedQty") + " / 2']"), jsonData.getData("ReceivedQty") + " / 2", "Verify Received / Open Lines Count");
        commonObj.validateText(By.xpath("//td[text()='" + jsonData.getData("ReceivedPercentage") + "']"), jsonData.getData("ReceivedPercentage"), "Verify Percentage Completed");
        commonObj.validateText(ReceivingInProcessPage.progressBar, jsonData.getData("ReceivedPercentage"), "Verify Progress Bar");
    }

    public By getRecField(String val) {
        return By.xpath("//*[text()='" + val + "']");
    }

    public By getItem(String val) {
        return By.xpath("(//*[text()='" + val + "']/preceding-sibling::td)[1]");
    }

    public By getButton(String val) {
        return By.xpath("//button[contains(text(),'" + val + "')]");
    }

    /**
     * Keyword to verify Received PO
     */
    public void verifyReceivedPO() {
        commonObj.validateText(getRecField("UNASSIGNED"), Utility_Functions.xGetJsonData("BinLocation"), "'UNASSIGNED' Bin location for item " + getItem("UNASSIGNED"));
        commonObj.validateText(getRecField("Closed"), "Closed", "Closed Open Qty is present for item " + getItem("Closed"));
        commonObj.validateText(getRecField("1"), "1", "1 Qty Received is present for item " + getItem("1"));
        commonObj.validateText(getRecField("0"), "0", "0 Qty Received is present for item " + getItem("0"));
    }

    /**
     * Keyword to verify Received PO
     */
    public void verifyReceivedPOPartial() {
        commonObj.validateText(getRecField("UNASSIGNED"), Utility_Functions.xGetJsonData("BinLocation"), "'UNASSIGNED' Bin location for item " + getItem("UNASSIGNED"));
        commonObj.validateText(getRecField("8"), "8", "8 Qty Received is present for item " + getItem("8"));
    }

    /**
     * Keyword to verify Received PO for All Received Item
     */
    public void verifyAllReceivedPO() throws Exception {
        commonObj.validateText(getRecField("UNASSIGNED"), Utility_Functions.xGetJsonData("BinLocation"), "'UNASSIGNED' Bin location for item " + getItem("UNASSIGNED"));
        commonObj.validateText(getRecField("Closed"), "Closed", "Closed Open Qty is present for item " + getItem("Closed"));
        commonObj.validateText(getRecField("1"), "1", "1 Qty Received is present for item " + getItem("1"));
        int closeCount = ownDriver.findElements(getRecField("Closed")).size();
        int unassigned = ownDriver.findElements(getRecField("UNASSIGNED")).size();
        Utility_Functions.xAssertEquals(report, closeCount, 2, "Closed status is present for the item");
        Utility_Functions.xAssertEquals(report, unassigned, 2, "unassigned Bin Location is present for the item");
        if (isDisplayed(getRecField("0"))) {
            throw new Exception("Error in receiving 1 item not Received");
        }
    }

    /**
     * Keyword to verify Received PO for All Received Item
     */
    public void verifyAllReceivedSingleItemPO() throws Exception {
        commonObj.validateText(getRecField("UNASSIGNED"), Utility_Functions.xGetJsonData("BinLocation"), "'UNASSIGNED' Bin location for item " + getItem("UNASSIGNED"));
        commonObj.validateText(getRecField("Closed"), "Closed", "Closed Open Qty is present for item " + getItem("Closed"));
        commonObj.validateText(getRecField("1"), "1", "1 Qty Received is present for item " + getItem("1"));
        int closeCount = ownDriver.findElements(getRecField("Closed")).size();
        int unassigned = ownDriver.findElements(getRecField("UNASSIGNED")).size();
        Utility_Functions.xAssertEquals(report, closeCount, 1, "Closed status is present for the item");
        Utility_Functions.xAssertEquals(report, unassigned, 1, "unassigned Bin Location is present for the item");
        if (isDisplayed(getRecField("0"))) {
            throw new Exception("Error in receiving 1 item not Received");
        }
    }

    /**
     * Keyword to verify Direct Ship status
     */
    public void directShip() {
        Utility_Functions.timeWait(4);
        commonObj.validateText(button(" Direct Ship "), "Direct Ship", "'Direct Ship' is present");
    }

    public void orderInUseRFGun() {
        Utility_Functions.timeWait(4);
        commonObj.validateText(button(" Order In Use "), "Order In Use", "'Order In Use' is present");
    }

    public By tabs(String tab) {
        return By.xpath("//span[text()='" + tab + "']");
    }

    /**
     * Keyword to select No Bin option
     */
    public void selectNoBin() {
        click(BinMaintenancePage.itemNumberContains, "Enable Contains check box");
        click(BinMaintenancePage.binType, "Click Bin Type drop down");
        click(tabs("No Bin"), "Click No Bin option");
        click(BinMaintenancePage.applyFilter, "Click apply filter");
        Utility_Functions.timeWait(3);
        Utility_Functions.xUpdateJson("NoBimItem", getText(By.xpath("//td[text()='0']/preceding-sibling::td/a")));
    }

    /**
     * Keyword to enter [Qty] and [No Bin Item Number] in Purchase Order Details page
     */
    public void enterItemNumberAndQtyNoBin() {
        String itemNo = Utility_Functions.xGetJsonAsString("NoBimItem");
        String qty = jsonData.getData("QtyOrdered");
        sendKeysAndEnter(PurchaseOrderDetailsPage.quantityOrdered, qty, "Enter Quantity Ordered");
        sendKeysAndEnter(PurchaseOrderDetailsPage.itemNumberPOD, itemNo, "Enter Item Number as[" + itemNo + "]");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        vrfyCostError();
        getOrderNumberVal();
    }

    public void addItem(String no) {
        String itemNo = jsonData.getData("ItemNo" + no);
        String qty = jsonData.getData("QtyOrdered");
        sendKeysAndEnter(PurchaseOrderDetailsPage.quantityOrdered, qty, "Enter Quantity Ordered");
        sendKeysAndEnter(PurchaseOrderDetailsPage.itemNumberPOD, itemNo, "Enter Item Number as[" + itemNo + "]");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        vrfyCostError();
    }

    /**
     * Keyword to enter [Qty] and [two Item Number] in Purchase Order Details page
     */
    public void enterItemNumberQty() {
        addItem("1");
        addItem("2");
        getOrderNumberVal();
    }

    /**
     * Keyword to enter [Qty] and [two Item Number] in Purchase Order Details page
     */
    public void enterSingleItemNumberQty() {
        addItem("1");
        getOrderNumberVal();
    }

    public void redirectToMasterPage() {
        click(MatrixCostUpdatePage.btnF3, "Navigate Back to Master Page");
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to Press Enter and verify Sales Order error in Purchase Order Details page
     */
    public void vrfyCostError() {
        click(PurchaseOrderDetailsPage.btnSubmit, "Clicked Submit button");
        String error = getText(PurchaseOrderDetailsPage.errorMsgPOD);
        if (error.contains("F8 to accept")) {
            Utility_Functions.actionKey(Keys.F8, ownDriver);
        }
        error = getText(PurchaseOrderDetailsPage.errorMsgPOD);
        if (error.contains("WARNING- Price field is zero or blank")) {
            sendKeysAndEnter(PurchaseOrderDetailsPage.pricePOD, "100", "Enter value in Price tbx");
            waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        }
        error = getText(PurchaseOrderDetailsPage.errorMsgPOD);
        if (error.contains("ERROR - Multiplier can NOT be 0")) {
            sendKeysAndEnter(PurchaseOrderDetailsPage.tbxMultiplier, "1", "Enter value in Multiplier tbx");
            waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        }
        error = getText(PurchaseOrderDetailsPage.errorMsgPOD);
        if (error.contains("F4 Overrides")) {
            Utility_Functions.actionKey(Keys.F4, ownDriver);
        }
        if (getText(PurchaseOrderDetailsPage.errorMsgPOD).trim().contains("WARNING- Cost variance -- F5 to accept.  Average cost is")) {
            Utility_Functions.xScrollIntoView(ownDriver, PurchaseOrderDetailsPage.errorMsgPOD);
            commonObj.verifyElementContainsText(PurchaseOrderDetailsPage.errorMsgPOD, "WARNING- Cost variance -- F5 to accept.  Average cost is", "Verify Cost Variance Warning message");
            Utility_Functions.actionKey(Keys.F5, ownDriver);
        }
    }

    /**
     * Keyword to get Conv Factor value from Purchase Order Details page
     */
    public void getOrderNumberVal() {
        String orderNo = getText(PurchaseOrderDetailsPage.orderNo2).trim();
        Utility_Functions.xUpdateJson("PONumber", orderNo);
    }

    public void createBin() {
        if (isDisplayed(spanElement("Create Bin"))) {
            commonObj.validateText(pageLabel("No Bin Locations Associated"), "No Bin Locations Associated", "'No Bin Locations Associated' is present");
            click(spanElement("Create Bin"), "Click [Create Bin]");
            Utility_Functions.timeWait(3);
            int binLoc = Utility_Functions.xRandomFunction();
            sendKeys(searchField("Enter Bin Name"), "" + binLoc + "", "Enter Bin Location");
            click(spanElement("Select Zone"));
            Utility_Functions.timeWait(2);
            click(By.xpath("(//li/span)[1]"), "Select Zone");
            click(button("Create Association "), "Click 'Create Association' button");
            Utility_Functions.timeWait(3);
            commonObj.validateText(By.xpath("//ion-col[contains(text(),'" + binLoc + "')]"), "" + binLoc + "", binLoc + " is present");
        }
    }

    public void backToReceivingScreen() {
        click(button("Back"));
        Utility_Functions.timeWait(4);
    }

    /**
     * Keyword to enter invalid PO Number in Inventory Receipts Page
     */
    public void enterPONumberInvtryRecpt() {
        String poNumber = Utility_Functions.xGetJsonData("PONumber");
        sendKeysAndEnter(InventoryReceiptsPage.tbxPONumber, poNumber, "Enter invalid Purchase Order Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(InventoryReceiptsPage.growlText, "No Detail Lines exist for this Purchase Order", "Validating growl text");
    }

    public void completeProcess() {
        try {
            Utility_Functions.timeWait(2);
            Utility_Functions.xAssertEquals(report, getAttribute(ReceivingInProcessPage.tickIcon, "ng-reflect-tooltip"), "Process", "");
            click(ReceivingInProcessPage.tickIcon, "Click Process Tick Icon");
        } catch (Exception e) {
            Utility_Functions.timeWait(4);
            click(ReceivingInProcessPage.tickIcon, "Click Process Tick Icon");
        }
        click(button("Complete PO"), "Click 'Complete PO' Button");
        Utility_Functions.timeWait(4);
        Utility_Functions.xAssertEquals(report, getAttribute(ReceivingInProcessPage.tickIcon, "ng-reflect-tooltip"), "Completed", "");
    }

    /**
     * Keyword to complete PO
     */
    public void completePo() {
        Utility_Functions.timeWait(4);
        click(getButton("Process"), "Click Process");
        click(getButton("Complete PO"), "Click Complete PO");
        Utility_Functions.timeWait(5);
    }

    /**
     * Keyword to search using ItemNo
     */
    public void searchUsingItemNo() {
        String itemNo = jsonData.getData("ItemNo");
        enterDataInSearchTbx(itemNo);
        vrfyResultsDisplayed();
    }

    /**
     * Keyword to verify search results are displayed
     */
    public void vrfyResultsDisplayed(){
        List<WebElement> lstSearchResults = getListElement(ReceivingInProcessPage.lstVendorNameSearchResults);
        int count = 0;
        if(lstSearchResults.size()>0){
            for (WebElement element : lstSearchResults){
                Utility_Functions.xScrollIntoView(ownDriver, element);
                if (element.isDisplayed())
                    count++;
            }
            if(count==lstSearchResults.size())
                report.updateTestLog("Verify Search Results", "Search Results are displayed",Status.PASS);
            else
                report.updateTestLog("Verify Search Results", "Search Results are NOT displayed",Status.FAIL);
        }else
            report.updateTestLog("Verify Search Results", "No Search Results",Status.PASS);
    }

    /**
     * Keyword to enter ItemNo/PO in search textbox
     */
    public void enterDataInSearchTbx(String itemNo) {
        waitForElementDisappear(ReceivingInProcessPage.pageLoadSpinner,globalWait);
        Utility_Functions.timeWait(2);
        waitForElementPresent(ReceivingInProcessPage.searchPo);
        sendKeysAndEnter(ReceivingInProcessPage.searchPo, itemNo, "Enter Item Number "+itemNo+" in [Search Item Or PO] box");
        waitForElementDisappear(ReceivingInProcessPage.pageLoadSpinner,globalWait);
    }

    /**
     * Keyword to verify search using invalid ItemNo
     */
    public void verifySearchByItemNo() {
        String invalidItemNo = jsonData.getData("InvalidItemNumber");
        enterDataInSearchTbx(invalidItemNo);
        commonObj.validateText(ReceivingInProcessPage.lblNoResultsFound, "No results found", "Validating Search Results");
        click(ReceivingInProcessPage.btnClearSearch);
        waitForElementDisappear(ReceivingInProcessPage.pageLoadSpinner,globalWait);

        searchUsingItemNo();
    }

    /**
     * Keyword to click on [Filter By Vendor] button
     */
    public void clickFilterByVendorBtn() {
        waitForElementDisappear(ReceivingInProcessPage.pageLoadSpinner,globalWait);
        waitForElementPresent(ReceivingInProcessPage.btnFilterByVendor);
        Utility_Functions.xScrollIntoView(ownDriver, ReceivingInProcessPage.btnFilterByVendor);

        click(ReceivingInProcessPage.btnFilterByVendor, "Click [Filter by Vendor] button");
        waitForElementDisappear(ReceivingInProcessPage.pageLoadSpinner,globalWait);
        waitForElementPresent(ReceivingInProcessPage.tbxSearchByVendor);
    }

    /**
     * Keyword to search using Vendor Name
     */
    public void searchByVendor() {
        clickFilterByVendorBtn();
        String vendorName = jsonData.getData("VendorName");
        enterDataInSearchByVendorTbx(vendorName);
        vrfyResultsDisplayed();
    }

    /**
     * Keyword to enter Vendor Name in search textbox
     */
    public void enterDataInSearchByVendorTbx(String vendorName) {
        waitForElementDisappear(ReceivingInProcessPage.pageLoadSpinner,globalWait);
        waitForElementPresent(ReceivingInProcessPage.tbxSearchByVendor);
        sendKeys(ReceivingInProcessPage.tbxSearchByVendor, vendorName, "Enter Vendor Name "+vendorName+" in [Search by Vendor] box");
        Utility_Functions.timeWait(5);

        List<WebElement> lstVendorNameOptions = getListElement(ReceivingInProcessPage.lstVendorNameOptions);
        if (lstVendorNameOptions.size()==1){
            String vendor = lstVendorNameOptions.get(0).getText();
            click(lstVendorNameOptions.get(0), "Selected Vendor ["+vendor+"] from type-ahead list");
        }else{
            int random = Utility_Functions.xRandomFunction(0, lstVendorNameOptions.size()-1);
            String vendor = lstVendorNameOptions.get(random).getText();
            click(lstVendorNameOptions.get(random), "Selected Vendor ["+vendor+"] from type-ahead list");
        }
        waitForElementDisappear(ReceivingInProcessPage.pageLoadSpinner,globalWait);
    }

    /**
     * Keyword to verify search by Vendor Name functionality
     */
    public void verifySearchByVendorName() {
        String invalidVendorName = jsonData.getData("InvalidVendorName");
        sendKeysAndEnter(ReceivingInProcessPage.tbxSearchByVendor, invalidVendorName, "Enter invalid Vendor Name "+invalidVendorName+" in [Search by Vendor] box");
        waitForElementDisappear(ReceivingInProcessPage.pageLoadSpinner,globalWait);
        commonObj.validateText(ReceivingInProcessPage.lblNoResultsFound, "No results found", "Validating Search Results");
        click(ReceivingInProcessPage.btnClearVendorSearch, "Click clear vendor search button");
        waitForElementDisappear(ReceivingInProcessPage.pageLoadSpinner,globalWait);

        searchByVendor();
    }

    /**
     * Keyword to randomly click on select checkbox from Search Results
     */
    public void selectRandomPOChkbxRFGun() {
        List<WebElement> lstChkBxSelectPO = getListElement(ReceivingInProcessPage.lstChkbxSelectPOSearchResults);
        if (lstChkBxSelectPO.size()==1){
            click(lstChkBxSelectPO.get(0), "Selected first PO from Search Results list");
            Utility_Functions.timeWait(2);

            lstChkBxSelectPO = getListElement(ReceivingInProcessPage.lstChkbxSelectPOSearchResults);
            boolean chkbxIsSelected = lstChkBxSelectPO.get(0).getAttribute("class").contains("selected-checkbox");
            if (chkbxIsSelected)
                report.updateTestLog("Verify PO Selected", "Verify PO Selected",Status.PASS);
            else
                report.updateTestLog("Verify PO Selected", "failed to select PO",Status.FAIL);
        }else{
            int random = Utility_Functions.xRandomFunction(0, lstChkBxSelectPO.size()-1);
            Utility_Functions.xScrollIntoView(ownDriver, lstChkBxSelectPO.get(random));
            click(lstChkBxSelectPO.get(random), "Selected random PO from Search Results list");
            Utility_Functions.timeWait(2);

            lstChkBxSelectPO = getListElement(ReceivingInProcessPage.lstChkbxSelectPOSearchResults);
            boolean chkbxIsSelected = lstChkBxSelectPO.get(random).getAttribute("class").contains("selected-checkbox");
            if (chkbxIsSelected)
                report.updateTestLog("Verify PO Selected", "Verify PO Selected",Status.PASS);
            else
                report.updateTestLog("Verify PO Selected", "failed to select PO",Status.FAIL);
        }
    }

    /**
     * Keyword to click on first [N] checkbox from Search Results
     */
    public void selectPOChkbxRFGun() {
        int count = Integer.parseInt(jsonData.getData("Count").trim());
        selectFirstNPOChkbxRFGun(count);
    }

    /**
     * Keyword to click on first [N] checkbox from Search Results
     */
    public void selectFirstNPOChkbxRFGun(int count) {
        List<WebElement> lstChkBxSelectPO = getListElement(ReceivingInProcessPage.lstChkbxSelectPOSearchResults);
        if (lstChkBxSelectPO.size()==1){
            click(lstChkBxSelectPO.get(0), "Selected first PO from Search Results list");
            Utility_Functions.timeWait(2);

            lstChkBxSelectPO = getListElement(ReceivingInProcessPage.lstChkbxSelectPOSearchResults);
            boolean chkbxIsSelected = lstChkBxSelectPO.get(0).getAttribute("class").contains("selected-checkbox");
            if (chkbxIsSelected)
                report.updateTestLog("Verify PO Selected", "Verify PO Selected",Status.PASS);
            else
                report.updateTestLog("Verify PO Selected", "failed to select PO",Status.FAIL);
        }else{
            for (int i=0; i<count; i++){
                Utility_Functions.xScrollIntoView(ownDriver, lstChkBxSelectPO.get(i));
                click(lstChkBxSelectPO.get(i), "Selected ["+i+1+"th] PO from Search Results list");
                Utility_Functions.timeWait(2);

                lstChkBxSelectPO = getListElement(ReceivingInProcessPage.lstChkbxSelectPOSearchResults);
                boolean chkbxIsSelected = lstChkBxSelectPO.get(i).getAttribute("class").contains("selected-checkbox");
                if (chkbxIsSelected)
                    report.updateTestLog("Verify PO Selected", "Verify PO Selected",Status.PASS);
                else
                    report.updateTestLog("Verify PO Selected", "failed to select PO",Status.FAIL);
            }
        }
    }

    /**
     * Keyword to click on [Cancel] button and validate PO checkbox gets deselected in Search Results
     */
    public void clickCancelBtnRFGun(){
        click(ReceivingInProcessPage.btnCancelPOSelection, "Click [Cancel]  button to deselect PO selection");
        Utility_Functions.timeWait(2);

        List<WebElement> lstChkBxSelectPO = getListElement(ReceivingInProcessPage.lstChkbxSelectPOSearchResults);
        int count = 0;
        for (WebElement element : lstChkBxSelectPO){
            Utility_Functions.xScrollIntoView(ownDriver, element);
            boolean chkbxIsSelected = element.getAttribute("class").contains("selected-checkbox");
            if (!chkbxIsSelected)
                count++;
        }
        if (count==lstChkBxSelectPO.size())
            report.updateTestLog("Verify PO selection is cleared", "Verify PO selection is cleared", Status.PASS);
        else
            report.updateTestLog("Verify PO selection is cleared", "failed to clear select PO", Status.FAIL);
    }

    /**
     * Keyword to click on [Receive n PO(s)] button
     */
    public void clickReceivePOBtnRFGun() {
        click(ReceivingInProcessPage.btnReceivePO, "Click [Receive n PO(s)] button");
        Utility_Functions.timeWait(2);
        waitForElementDisappear(ReceivingInProcessPage.pageLoadSpinner,globalWait);
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to click on [Confirm] button
     */
    public void clickConfirmBtn() {
        waitForElementPresent(ReceivingInProcessPage.btnConfirm);
        click(ReceivingInProcessPage.btnConfirm, "Click [Confirm] button");
        waitForElementDisappear(ReceivingInProcessPage.pageLoadSpinner, globalWait);
        clickSkipBtnIfPresent();
        Utility_Functions.actionKey(Keys.ESCAPE, ownDriver);
    }

    /**
     * Keyword to click on [Skip] button if present
     */
    public void clickSkipBtnIfPresent() {
        Utility_Functions.timeWait(2);
        if (isDisplayed(ReceivingInProcessPage.btnSkip)){
            click(ReceivingInProcessPage.btnSkip, "Click [Skip] button");
            Utility_Functions.timeWait(1);
        }
    }

    /**
     * Keyword to select [Qty] and [Bin] in put away screen
     */
    public void enterQtyAndBin() {
        String qty = jsonData.getData("Qty");
        String bin = jsonData.getData("Bin");
        sendKeysAndTab(ReceivingInProcessPage.tbxQty, qty, "Enter Quantity as ["+qty+"] in [Qty] box");
        Utility_Functions.timeWait(2);
        click(ReceivingInProcessPage.btnSkip, "Click [Skip] button");
        Utility_Functions.timeWait(2);
        sendKeysAndEnter(ReceivingInProcessPage.tbxScanLocation, bin, "Enter Bin as ["+bin+"] in [Scan Location] box");
        waitForElementDisappear(ReceivingInProcessPage.pageLoadSpinner,globalWait);

        boolean isMsgDisplayed = Utility_Functions.xWaitForElementPresent(ownDriver, ReceivingInProcessPage.msg, globalWait);
        if (isMsgDisplayed)
            report.updateTestLog("Verify notification is displayed", "Notification is displayed", Status.PASS);
        else
            report.updateTestLog("Verify notification is displayed", "Notification is NOT displayed", Status.FAIL);

        Utility_Functions.timeWait(5);
        commonObj.validateText(ReceivingInProcessPage.hdrPurchaseOrderItems, "PURCHASE ORDER ITEMS", "Validating [PURCHASE ORDER ITEMS] header");
    }

    /**
     * Keyword to select [Qty] and [Bin] in put away screen when Multiple PO's are selected
     */
    public void enterQtyAndBinWhenMultiplePOSelected() {
        String qty = jsonData.getData("Qty");
        String bin = jsonData.getData("Bin");
        clickSkipBtnIfPresent();
        sendKeysAndTab(ReceivingInProcessPage.tbxQty, qty, "Enter Quantity as ["+qty+"] in [Qty] box");
        Utility_Functions.timeWait(1);
        click(ReceivingInProcessPage.btnSkip, "Click [Skip] button");
        Utility_Functions.timeWait(1);
        sendKeysAndEnter(ReceivingInProcessPage.tbxScanLocation, bin, "Enter Bin as ["+bin+"] in [Scan Location] box");
        waitForElementDisappear(ReceivingInProcessPage.pageLoadSpinner,globalWait);

        boolean isMsgDisplayed = Utility_Functions.xWaitForElementPresent(ownDriver, ReceivingInProcessPage.msg, globalWait);
        if (isMsgDisplayed)
            report.updateTestLog("Verify notification is displayed", "Notification is displayed", Status.PASS);
        else
            report.updateTestLog("Verify notification is displayed", "Notification is NOT displayed", Status.FAIL);

        Utility_Functions.timeWait(3);
    }

    /**
     * Keyword to click on [Skip PO] button
     */
    public void clickSkipPOBtn() {
        waitForElementPresent(ReceivingInProcessPage.btnSkipPO);
        click(ReceivingInProcessPage.btnSkipPO, "Click [Skip PO] button");
        commonObj.validateText(ReceivingInProcessPage.msgSkipPO, "Are you sure you want to skip this PO? This action cannot be undone.", "Validate Skip PO message");
        commonObj.validateElementExists(ReceivingInProcessPage.btnSkipPOModal, "Validate presence of [Skip PO] button");
        commonObj.validateElementExists(ReceivingInProcessPage.btnReturnToPutAwaySkipPOModal, "Validate presence of [Return to Put Away] button");
        click(ReceivingInProcessPage.btnSkipPOModal, "Click [Skip PO] button in Skip PO modal");
        waitForElementDisappear(ReceivingInProcessPage.pageLoadSpinner, globalWait);
    }

    /**
     * Keyword to click on [Add Alternate Item Number] button
     */
    public void clickAddAlternateItemNumberBtn() {
        waitForElementPresent(ReceivingInProcessPage.iconAddAlternateItem);
        click(ReceivingInProcessPage.iconAddAlternateItem, "Click [Add Alternate Item Number] icon");
        commonObj.validateElementExists(ReceivingInProcessPage.tbxEnterAlternateItemNumber, "Validate presence of [Scan or enter new value] searchbar");
        commonObj.validateElementExists(ReceivingInProcessPage.btnBack, "Validate presence of [Back] button");
        commonObj.validateElementExists(ReceivingInProcessPage.btnAddAlternateItem, "Validate presence of [Add Alternate Item Number] button");
    }

    /**
     * Keyword to enter random Alternate Item Number
     */
    public void enterRandomAlternateItemNumber() {
        String randomAlternateItemNumber = "TEST"+Utility_Functions.xRandomFunction();
        jsonData.putData("AlternateItemNo", randomAlternateItemNumber);
        sendKeys(ReceivingInProcessPage.tbxEnterAlternateItemNumber, randomAlternateItemNumber, "Enter random Alternate ItemNumber ["+randomAlternateItemNumber+"] in [Scan or enter new value] searchbar");
        click(ReceivingInProcessPage.btnAddAlternateItem, "Click [Add Alternate Item Number] button");
        waitForElementPresent(ReceivingInProcessPage.msgAlternatePartNoAddedSuccess);
    }
}