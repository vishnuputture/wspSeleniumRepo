package businesskeywords.warehousing.Manifest;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.Purchasing.SPO.SpoPage;
import pages.common.MasterPage;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
import pages.warehouse.BinMaintenance.BinMaintenancePage;
import pages.warehouse.DeliveredShipmentsPage;
import pages.warehouse.DriversPage;
import pages.warehouse.ManifestsPage;
import pages.warehouse.TruckPage;
import supportLibraries.Utility_Functions;

import java.util.List;

public class Manifests extends ReusableLib {

    CommonActions commonObj;
    Trucks truckObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public Manifests(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        truckObj = new Trucks(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * Keyword to Navigate to Manifests Screen
     */
    public void navigateToManifestsScreen() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.menuIconTruck,"Click Menu");
        //callSelectCompany();
        Utility_Functions.timeWait(3);
        try {
            click(ManifestsPage.subMenuManifest, "Navigate to trucks page");
        }catch (Exception e){
            Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.menuIconTruck,"Click Menu");
            Utility_Functions.timeWait(3);
            click(ManifestsPage.subMenuManifest, "Navigate to trucks page");
        }
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.timeWait(3);
        commonObj.validateText(ManifestsPage.manifestListHeader, "Manifest List", "Manifest List Screen Header is present");
    }

    public void winLogin() {
        if (Utility_Functions.xWaitForElementPresent(ownDriver, SpoPage.winLogin, 10)) {
            sendKeys(SpoPage.userName, properties.getProperty("CycleCostUserName"));
            sendKeys(SpoPage.password, Utility_Functions.xGetJsonData("spoPass"));
            Utility_Functions.waitTillClickHardSleep(report, ownDriver, SpoPage.submit, "");
        }
    }

    /**
     * Keyword to select Company
     */
    public void selectCompanyMan() {
        winLogin();
        if (isDisplayed(BinMaintenancePage.toasterCloseIcon)) {
            click(BinMaintenancePage.toasterCloseIcon);
        }
        Utility_Functions.timeWait(6);
        if(isDisplayed(ManifestsPage.closePopUp)){
            click(ManifestsPage.closePopUp);
        }
        if (Utility_Functions.xIsDisplayed(ownDriver, DeliveredShipmentsPage.unAuth)) {
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
     * Keyword to verify the availability of field again Manifest List Screen
     */
    public void verifyManifestListUI() {
        String[] actText = {"Manifest Number", "Delivery Date", "Orders", "Delivered Orders", "Stops", "Status", "Driver", "Truck", "Actions"};
        List<WebElement> els = ownDriver.findElements(By.xpath("//th"));
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        commonObj.validateElementExists(TruckPage.helpIcon, "Help Icon '?' is present");
        commonObj.validateElementExists(TruckPage.filterSearch, "Search filter icon is present");
        int size = ownDriver.findElements(TruckPage.pagination).size();
        Utility_Functions.xAssertEquals(report, size, 4, "Next page and previous page arrow icon is present");
        String page = Utility_Functions.getText(ownDriver, TruckPage.currentPage);
        commonObj.validateElementExists(TruckPage.currentPage, "Current Page " + page + " " + Utility_Functions.getText(ownDriver, TruckPage.outOf) + "");
        List<WebElement> elm = ownDriver.findElements(TruckPage.show);
        String[] acText = {"10", "15", "30"};
        int j = 0;
        for (WebElement el : elm) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), acText[j], "");
            j++;
        }
    }

    /**
     * Keyword to Verify functionality of Help (?)icon
     */
    public void helpIconManifest() {
        click(TruckPage.helpIcon);
        Utility_Functions.timeWait(3);
    }

    /**
     * Keyword to Verify Pagination against current page
     */
    public void selectPage(int actPageNo, String expPage, String arrowIcon) {
        click(ownDriver.findElements(DriversPage.pageArrow).get(actPageNo), "Click on " + arrowIcon + " Present below the Right Corner of the page");
        Utility_Functions.xScrollWindow(ownDriver);
        String pageNo = ownDriver.findElement(TruckPage.currentPage).getAttribute("ng-reflect-model");
        Utility_Functions.xAssertEquals(report, pageNo, expPage, "Moved to " + pageNo + " Page");
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to Verify functionality of Pagination
     */
    public void paginationManifest() {
        int size = 2;
        Utility_Functions.xScrollWindow(ownDriver);
        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(ownDriver, DriversPage.onePage)) {
            commonObj.validateText(DriversPage.onePage, "of 1", "One page is available");
        } else {
            click(ownDriver.findElements(DriversPage.pageArrow).get(2));
            Utility_Functions.xScrollWindow(ownDriver);
            while (!Utility_Functions.xIsDisplayed(ownDriver, DriversPage.lastPage)) {
                size++;
                click(ownDriver.findElements(DriversPage.pageArrow).get(2));
                Utility_Functions.xScrollWindow(ownDriver);
            }
            click(ownDriver.findElements(DriversPage.pageArrow).get(0));
            Utility_Functions.xScrollWindow(ownDriver);
            selectPage(2, "2", "Right Arrow (>)");
            selectPage(1, "1", "Left Arrow (<)");
            selectPage(3, "" + size + "", "Right double Arrow (>>)");
            selectPage(0, "1", "Left double Arrow (<<)");
        }
    }

    public void navigateToCreateManifest() {
        click(ManifestsPage.createManBtn, "Click on Create New Manifest button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(ManifestsPage.createNewManHeader, "Create Manifest", "Verify [Create Manifest] Header");
    }

    public void navigateToEditManifest() {
        click(By.xpath("//td/a"));
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,ManifestsPage.manifestDetail, "Manifest Detail text is present");
    }

    public By labels(String text){
       return By.xpath("//div[contains(@class,'col')]//label[@class='win-label' and @for and text()='"+text+"']");
    }

    /**
     * Keyword to verify the UI of Create Manifest Screen
     */
    public void uiCreateManifest() {
        String[] actText = {"Delivery Date", "Start Time", "Truck (Optional)", "Driver Notes (Optional)"};
        int i = 0;
        for (String text : actText) {
            int size=ownDriver.findElements(labels(text)).size();
            Utility_Functions.xAssertEquals(report, ownDriver.findElements(labels(text)).get(size-1).getText(), actText[i], "");
            i++;
        }
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(DriversPage.crossIcon, "Cross icon is present");
        commonObj.validateText(ManifestsPage.createManifestBtn, "Create Manifest", "Create Manifest button is present");
        click(DriversPage.crossIcon, "Click Cross close Icon");
        Utility_Functions.timeWait(3);
        commonObj.validateText(ManifestsPage.manifestListHeader, "Manifest List", "Manifest List Screen Header is present");
    }

    public By isCollapsed(String bol) {
        return By.xpath("//a[@aria-expanded='" + bol + "']");
    }

    /**
     * Keyword to create manifest
     */
    public void verifyAreaExpanded() {
        commonObj.validateElementExists(ManifestsPage.collapseIcon, "Collapse icon is present");
        commonObj.validateText(ManifestsPage.stopNOrderLab, "STOPS & ORDERS", "Verify STOPS & ORDERS text is present");
        commonObj.validateText(ManifestsPage.addShipmentLabel, "Add Shipment", "Add Shipment text is present");
        commonObj.validateText(ManifestsPage.addButton, "Add", "Add button is present");
        commonObj.validateText(ManifestsPage.addStopBtn, "Add Stop", "Add Stop button is present");
        commonObj.validateText(ManifestsPage.addPOBtn, "Add PO", "Add PO button is present");
        click(ManifestsPage.collapseIcon, "Click Collapse Icon");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(isCollapsed("true"), "Session is collapsed");
        click(ManifestsPage.collapseIcon, "Click Expand Icon");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(isCollapsed("false"), "Session is expanded");
    }

    public WebElement truckDriverDrop(String label) {
        return ownDriver.findElement(By.xpath("//label[text()='" + label + "']/parent::div/descendant::option[@class='ng-star-inserted']"));
    }

    public void fillDetails() {
        try {
            click(ManifestsPage.newManifestDeliveryDate);
        }catch (Exception e){
            click(ManifestsPage.newManifestDlvryDate);
        }
        click(TruckPage.licensePlateExpSelect, "Select Delivery Date");
        try {
            click(ownDriver.findElements(ManifestsPage.newManifestStartTime).get(1), "Select Start Date");
        }catch (Exception e){
            click(ManifestsPage.newManifestStartTime, "Select Start Date");
        }
        int hour = Utility_Functions.genRandNum(12);
        sendKeys(ManifestsPage.hour, "" + hour + "");
        try {
            click(ownDriver.findElements(ManifestsPage.truckEle).get(1));
        }catch (Exception e){
            click(ManifestsPage.truckEle);
        }
        Utility_Functions.timeWait(3);
        String truckName = Utility_Functions.xGetJsonData("TruckName");
        click(ownDriver.findElement(By.xpath("//label[text()='Truck (Optional)']/parent::div/descendant::option[@class='ng-star-inserted' and contains(text(),'" + truckName + "')]")), "Select truck from the drop down");
        try{
            click(ownDriver.findElements(ManifestsPage.notes).get(1));
        }catch (Exception e){
            click(ManifestsPage.notes);
        }
        Utility_Functions.timeWait(2);
        Utility_Functions.xMouseClick(ownDriver, ManifestsPage.driverEle);
        Utility_Functions.timeWait(2);
        String driverName = Utility_Functions.xGetJsonData("Driver");
        click(ownDriver.findElement(By.xpath("//label[text()='Driver (Optional)']/parent::div/descendant::option[@class='ng-star-inserted' and contains(text(),'" + driverName + "')]")), "Select Driver from the drop down");
        Utility_Functions.timeWait(2);
        try{
            sendKeys(ownDriver.findElements(ManifestsPage.notes).get(1), "All good", "Enter values in Notes to Driver (optional)");
        }catch (Exception e){
            sendKeys(ManifestsPage.notes, "All good", "Enter values in Notes to Driver (optional)");
        }
        Utility_Functions.timeWait(2);
    }

    public void verifyManifestNum() {
        Utility_Functions.timeWait(5);
        String maniNo = Utility_Functions.getText(ownDriver, truckObj.getTruck("Manifest Number"));
        Utility_Functions.xUpdateJson("ManFest", maniNo);
        commonObj.validateText(ManifestsPage.createStatus, "Created", "Manifest is created and Manifest number is :" + maniNo + "");
    }

    /**
     * Keyword to create manifest
     */
    public void createManifest() {
        fillDetails();
        click(ManifestsPage.createManifestBtn, "Click Create Manifest Button");
        verifyManifestNum();
        navigateToEditManifest();
        Utility_Functions.xScrollIntoView(ownDriver, ManifestsPage.addOrderNo);
        Utility_Functions.timeWait(2);
        sendKeys(ManifestsPage.addOrderNo, Utility_Functions.xGetJsonData("SalesOrder") + "-01", "Enter shipment number");
        click(ManifestsPage.addButton, "Click Add button");
        commonObj.validateText(ManifestsPage.orderAddedMessage, "Order " + Utility_Functions.xGetJsonData("SalesOrder") + "-01 successfully added to manifest", "Order is added");
        click(ManifestsPage.saveManifest, "Click Save Manifest Button");
        verifyManifestNum();
        commonObj.validateElementExists(ManifestsPage.mobileIcon, "Generate icon is present");
    }

    /**
     * Keyword to create manifest with multiple shipments
     */
    public void createManifestMultiShip() {
        fillDetails();
        click(ManifestsPage.createManifestBtn, "Click Create Manifest Button");
        verifyManifestNum();
        navigateToEditManifest();
        Utility_Functions.xScrollIntoView(ownDriver, ManifestsPage.addOrderNo);
        Utility_Functions.timeWait(2);
        sendKeys(ManifestsPage.addOrderNo, Utility_Functions.xGetJsonData("SalesOrder") + "-01", "Enter first shipment number");
        click(ManifestsPage.addButton, "Click Add button");
        commonObj.validateText(ManifestsPage.orderAddedMessage, "Order " + Utility_Functions.xGetJsonData("SalesOrder") + "-01 successfully added to manifest", "Order is added");
        Utility_Functions.timeWait(2);
        sendKeys(ManifestsPage.addOrderNo, Utility_Functions.xGetJsonData("SalesOrder") + "-02", "Enter first shipment number");
        click(ManifestsPage.addButton, "Click Add button");
        Utility_Functions.timeWait(5);
        commonObj.validateText(ManifestsPage.orderAddedMessage, "Order " + Utility_Functions.xGetJsonData("SalesOrder") + "-02 successfully added to manifest", "Order is added");
        click(ManifestsPage.saveManifest, "Click Save Manifest Button");
        verifyManifestNum();
        commonObj.validateElementExists(ManifestsPage.mobileIcon, "Generate icon is present");
    }

    /**
     * Keyword to create manifest Add PO
     */
    public void createManifestPO() {
        fillDetails();
        click(ManifestsPage.createManifestBtn, "Click Create Manifest Button");
        verifyManifestNum();
        navigateToEditManifest();
        Utility_Functions.xScrollIntoView(ownDriver, ManifestsPage.addOrderNo);
        Utility_Functions.timeWait(2);
        click(ManifestsPage.addPOBtn, "CLick Add PO button");
        Utility_Functions.timeWait(5);
        click(ManifestsPage.addPoRowCount, "Select PO");
        Utility_Functions.timeWait(3);
        click(btn(" Add to Manifest "), "Click Add to Manifest Button");
        Utility_Functions.timeWait(3);
        Utility_Functions.xScrollIntoView(ownDriver, By.xpath("//div[contains(@class,'order-header')]/span"));
        click(ManifestsPage.saveManifest, "Click Save Manifest Button");
        verifyManifestNum();
        commonObj.validateElementExists(ManifestsPage.mobileIcon, "Generate icon is present");
    }

    public void ifExist() {
        if (isDisplayed(ManifestsPage.ifErrorExist)) {
            click(ownDriver.findElement(By.xpath("//label[text()='Driver (Optional)']/parent::div/descendant::option[@class='ng-star-inserted' and contains(text(),'')]")), "Select Driver from the drop down");
            Utility_Functions.timeWait(2);
            click(ownDriver.findElement(By.xpath("//label[text()='Driver (Optional)']/parent::div/descendant::option[@class='ng-star-inserted' and contains(text(),'')]")), "Select Driver from the drop down");
            Utility_Functions.timeWait(2);
            click(ManifestsPage.createManifestBtn, "Click Create Manifest Button");
            Utility_Functions.timeWait(3);
        }
    }

    /**
     * Keyword to verify UI of Search Filters
     */
    public void searchFiltersManifestUI() {
        click(TruckPage.filterSearch, "Click Search Filter icon");
        Utility_Functions.timeWait(2);
        Utility_Functions.xScrollWindow(ownDriver);
        Utility_Functions.timeWait(2);
        commonObj.validateText(TruckPage.searchFilterPanelTitle, "Search Filters", "Search Filters panel title is present");
        String[] actText = {"Manifest Number", "Delivery Date", "Start Time", "Status", "Driver", "Truck", "Customer", "Customer PO Number", "Shipment", "Job Name"};
        List<WebElement> els = ownDriver.findElements(TruckPage.searchFiltersLabel);
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        commonObj.validateElementExists(TruckPage.filtersCrossIcon, "Cross icon is present");
        commonObj.validateText(TruckPage.applyFiltersDis, "Apply Filters", "Apply Filters button exist and button is disabled");
        //commonObj.validateText(TruckPage.clearFilters,"Clear All Filters","Clear All Filters button is exist and text is in red color");
    }

    public By getRowVal(String label) {
        return By.xpath("//table/tbody/tr[1]/td[count(//table/thead/tr/th[contains(text(),'" + label + "')]/preceding-sibling::th)+1]");
    }

    /**
     * Keyword to verify UI of Search Filters
     */
    public void navigateToManifestOrder() {
        Utility_Functions.timeWait(4);
        String manNo = Utility_Functions.getText(ownDriver, getRowVal("Manifest Number")).trim();
        Utility_Functions.xUpdateJson("ManifestNumber", manNo);
        if (Utility_Functions.xIsDisplayed(ownDriver, ManifestsPage.generatedIcon)) {
            click(ManifestsPage.generatedIcon);
        }
        Utility_Functions.xmouseOver(ownDriver, ManifestsPage.orderColLink);
        Utility_Functions.timeWait(2);
        /*String salesOrderCount= Utility_Functions.getText(driver,ManifestsPage.soPoOrders);
        String PurchaseOrder=driver.findElements(ManifestsPage.soPoOrders).get(1).getText();*/
        click(ManifestsPage.orderColLink, "Click on order number link from orders column");
        Utility_Functions.timeWait(4);
        String header = Utility_Functions.getText(ownDriver, ManifestsPage.manifestOrderHeader).trim();
        Utility_Functions.xAssertEquals(report, header, "MANIFEST #: " + manNo + " - ORDERS", "Manifest Orders Header: ");
    }

    public By btn(String button) {
        return By.xpath("//button[text()='" + button + "']");
    }

    public By lbl(String label) {
        int size = ownDriver.findElements(By.xpath("//*[text()='" + label + "']")).size();
        return By.xpath("(//*[text()='" + label + "'])[" + size + "]");
    }

    public By head(String header) {
        return By.xpath("(//h3[text()='" + header + "'])");
    }

    /**
     * Keyword to verify UI of Search Filters
     */
    public void manifestOrderUI() {
        commonObj.validateText(lbl("Warehouse Manager Notes"), "Warehouse Manager Notes", "Label matches");
        commonObj.validateText(lbl("Notes to Driver"), "Notes to Driver", "Label matches");
        //commonObj.validateText(btn("Save Notes"), "Save Notes", "Button matches");
        commonObj.validateText(lbl("STOPS & ORDERS "), "STOPS & ORDERS", "Label matches");
        commonObj.validateElementExists(DriversPage.crossIcon, "Cross icon is present");
        commonObj.validateElementExists(ManifestsPage.colExpIconManifestOrder, "Collapse icon is present");
    }

    /**
     * Keyword to verify functionality of save note button and expand-Collapse Session
     */
    public void saveNote() {
        if (Utility_Functions.xIsDisplayed(ownDriver, btn("Save Notes"))) {
            sendKeys(ManifestsPage.manageNote, "2:1 order and wait for order");
            click(btn("Save Notes"), "Click Save Button");
            Utility_Functions.timeWait(1);
            commonObj.validateText(ManifestsPage.successMsg, "Manager notes saved successfully", "Successful message:");
        }
        click(ManifestsPage.editNote, "Click on edit note link");
        sendKeys(ManifestsPage.manageNote, "Cancel the order please!!!");
        click(btn("Save Notes"), "Click Save Button");
        Utility_Functions.timeWait(1);
        commonObj.validateText(ManifestsPage.successMsg, "Manager notes saved successfully", "Successful message:");
        click(ownDriver.findElements(ManifestsPage.collapseIcon).get(1), "Click Collapse Icon");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(isCollapsed("false"), "Session is collapsed");
        click(ownDriver.findElements(ManifestsPage.collapseIcon).get(1), "Click Expand Icon");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(isCollapsed("true"), "Session is expanded");
    }

    /**
     * Keyword to verify functionality of update status(Delivered) button for Sales order
     */
    public void updateStatusDeliveredSO() {
        click(ManifestsPage.updateStatusDrop, "Click Update Status Drop down");
        click(ManifestsPage.updateStatusSO, "Click Delivered");
        Utility_Functions.timeWait(5);
        commonObj.validateText(ManifestsPage.soStatus, "Delivered", "Verify the status: ");
        commonObj.validateElementExists(ManifestsPage.deliveredGreenIcon, "Stop icon changed to green color tick mark");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,ownDriver.findElements(ManifestsPage.closeIcn).get(3), "Click Cross icon(Close icon)");
        Utility_Functions.timeWait(5);
        commonObj.validateText(ManifestsPage.manStatus, "Delivered", "Verify status: ");
    }

    public void deleteMan() {
        Utility_Functions.timeWait(4);
        int count = 1;
        int size = ownDriver.findElements(TruckPage.manifestCount).size();
        for (int i = 1; i <= size; i++) {
            Utility_Functions.timeWait(4);
            String man = Utility_Functions.getText(ownDriver, By.xpath("(//td/a[contains(text(),'0')])[" + count + "]"));
            Utility_Functions.xClickHiddenElement(ownDriver, By.xpath("//td/a[contains(text(),'" + man + "')]"));
            Utility_Functions.timeWait(5);
            if (isDisplayed(ManifestsPage.deleteManifest)) {
                click(ManifestsPage.deleteManifest, "Click Delete Manifest");
                if (isDisplayed(TruckPage.deleteConfPopUp)) {
                    commonObj.validateElementExists(TruckPage.deleteConfPopUp, "Delete Confirmation Pop Up is present");
                    click(ownDriver.findElements(ManifestsPage.delButton).get(0), "Click No Button");
                    commonObj.validateText(By.xpath("//h2[text()=' Manifest #: " + man + " ']"), "Manifest #: " + man + "", "Manifest Number Screen Header: ");
                    click(ManifestsPage.deleteManifest, "Click Delete Manifest");
                    commonObj.validateElementExists(TruckPage.deleteConfPopUp, "Delete Confirmation Pop Up is present");
                    click(ownDriver.findElements(ManifestsPage.delButton).get(1), "Click Yes Button");
                }
                Utility_Functions.timeWait(3);
                String exp = "Manifest #" + man + " successfully deleted.";
                commonObj.validateElementExists(TruckPage.deletePopUp, exp);
            } else {
                count++;
            }
            ownDriver.navigate().back();
            Utility_Functions.timeWait(2);
        }
    }

    /**
     * Keyword to Delete Manifest
     */
    public void deleteManifestList() {
        int size1 = ownDriver.findElements(TruckPage.deleteInProgress).size();
        for (int j = 0; j < size1; j++) {
            Utility_Functions.xClickHiddenElement(ownDriver, TruckPage.deleteInProgress);
            Utility_Functions.timeWait(3);
            int size = ownDriver.findElements(ManifestsPage.updateStatusDrop).size();
            for (int i = 0; i < size; i++) {
                Utility_Functions.xScrollIntoView(ownDriver, ManifestsPage.updateStatusDrop);
                Utility_Functions.timeWait(3);
                try {
                    Utility_Functions.xClickHiddenElement(ownDriver, ownDriver.findElements(ManifestsPage.updateStatusDrop).get(i));
                } catch (Exception e) {
                    Utility_Functions.xClickHiddenElement(ownDriver, ownDriver.findElements(ManifestsPage.updateStatusDrop).get(i + 1));
                }
                try {
                    Utility_Functions.xClickHiddenElement(ownDriver, ManifestsPage.updateStatusSO);
                } catch (Exception e) {
                    Utility_Functions.xClickHiddenElement(ownDriver, ManifestsPage.updateStatusPOPick);
                }
                Utility_Functions.timeWait(5);
            }
            ownDriver.navigate().back();
            Utility_Functions.timeWait(3);
        }
        deleteMan();
    }

    /**
     * Keyword to verify functionality of update status(Not Delivered) button for Sales order
     */
    public void updateStatusNotDeliveredSO() {
        click(ManifestsPage.updateStatusDrop, "Click Update Status Drop down");
        click(ownDriver.findElements(ManifestsPage.updateStatusSO).get(1), "Click Not Delivered");
        Utility_Functions.timeWait(5);
        commonObj.validateText(ManifestsPage.soStatus, "Not Delivered", "Verify the status: ");
        commonObj.validateElementExists(ManifestsPage.notDeliveredRedIcon, "Stop icon changed to Red color Cross mark");
        //commonObj.validateElementExists(ManifestsPage.warningIcon, "In progress Icon is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,ownDriver.findElements(ManifestsPage.closeIcn).get(3), "Click Cross icon(Close icon)");
        Utility_Functions.timeWait(5);
        commonObj.validateText(ManifestsPage.manStatus, "Not Delivered", "Verify status: ");
    }

    /**
     * Keyword to verify functionality of update status button for Purchase order
     */
    public void inProgressStatus() {
        click(ManifestsPage.updateStatusDrop, "Click Update Status Drop down");
        click(ManifestsPage.updateStatusSO, "Click Delivered");
        Utility_Functions.timeWait(5);
        commonObj.validateText(ManifestsPage.soStatus, "Delivered", "Verify the status: ");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(ManifestsPage.warningIcon, "In progress Icon is present");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,ownDriver.findElements(ManifestsPage.closeIcn).get(3), "Click Cross icon(Close icon)");
        Utility_Functions.timeWait(5);
        commonObj.validateText(ManifestsPage.inProgressStatus, "In Process", "Verify status: ");
    }

    /**
     * Keyword to verify functionality of update status button for Purchase order
     */
    public void updateStatusPO() {
        Utility_Functions.timeWait(3);
        click(ManifestsPage.updateStatusDrop, "Click Update Status Drop down");
        Utility_Functions.timeWait(3);
        Utility_Functions.xHoverElementClk(ownDriver.findElement(ManifestsPage.updateStatusPOPick), ownDriver);
        // click(ManifestsPage.updateStatusPOPick, "Click Picked Up");
        Utility_Functions.timeWait(5);
        commonObj.validateText(ManifestsPage.pickedUpStatus, "Picked Up", "Verify the status: ");
        commonObj.validateElementExists(ManifestsPage.deliveredGreenIcon, "Stop icon changed to green color tick mark");
        click(ManifestsPage.updateStatusDrop, "Click Update Status Drop down");
        Utility_Functions.timeWait(2);
        Utility_Functions.xHoverElementClk(ownDriver.findElement(ManifestsPage.updateStatusPONotPick), ownDriver);
        //click(ManifestsPage.updateStatusPONotPick, "Click Not Picked Up");
        Utility_Functions.timeWait(5);
        commonObj.validateText(ManifestsPage.notPickedUpStatus, "Not Picked Up", "Verify the status: ");
        commonObj.validateElementExists(ManifestsPage.notDeliveredRedIcon, "Stop icon changed to Red color Cross mark");
    }

    /**
     * Keyword to Navigate To Order Number Page Sales Order
     */
    public void navigateToOrderNumberSO() {
        click(btn(" Packing List "), "Click Packing List Button");
        commonObj.validateText(ManifestsPage.orderNoHeader, "ORDER NUMBERs " + Utility_Functions.xGetJsonData("SalesOrder") + "-01", "Order numbers header: ");
    }

    /**
     * Keyword to Navigate To Order Number Page Purchase Order
     */
    public void navigateToOrderNumberPO() {
        click(btn(" PO Item Details "), "Click PO Item Details Button");
        commonObj.validateElementExists(ManifestsPage.orderNoHeaderPO, "Order numbers header: ");
    }

    /**
     * Keyword to verify UI of Order Number sales order page
     */
    public void orderNumberUISO() {
        commonObj.validateText(head("PACKING LIST"), "PACKING LIST", "Sub Header: is present");
        commonObj.validateText(lbl("Order Date:"), "Order Date:", "");
        commonObj.validateText(lbl("Requested:"), "Requested:", "");
        commonObj.validateText(lbl("Customer PO:"), "Customer PO:", "");
        commonObj.validateText(lbl("Total Number of Units:"), "Total Number of Units:", "");
        commonObj.validateText(head("SOLD TO:"), "SOLD TO:", "SOLD TO: is present");
        commonObj.validateText(head("SHIP TO:"), "SHIP TO:", "SHIP TO: is present");
        commonObj.validateElementExists(btn("Return to Orders"), "Return to Orders button present");
        commonObj.validateElementExists(btn("Save Adjustments"), "Save Adjustments is present");
        commonObj.validateElementExists(ManifestsPage.expandAll, "Expand All link is present");
    }

    /**
     * Keyword to verify UI of Order Number Purchase order page
     */
    public void orderNumberUIPO() {
        commonObj.validateText(head("ITEM DETAILS"), "ITEM DETAILS", "Sub Header: is present");
        commonObj.validateText(lbl("Order Date:"), "Order Date:", "");
        commonObj.validateText(lbl("Requested:"), "Requested:", "");
        commonObj.validateText(lbl("Total Number of Units:"), "Total Number of Units:", "");
        commonObj.validateText(head("PICKUP FROM:"), "PICKUP FROM:", "PICKUP FROM: is present");
        commonObj.validateText(head("SHIP TO:"), "SHIP TO:", "SHIP TO: is present");
        commonObj.validateElementExists(btn("Return to Orders"), "Return to Orders button present");
        commonObj.validateElementExists(ManifestsPage.poDetails, "PO DETAIL is present");
        commonObj.validateElementExists(ManifestsPage.expandAll, "Expand All link is present");
    }

    /**
     * Keyword to verify Save Adjustment and Return to Orders Button
     */
    public void saveAdjReturnToOrderBtn() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,ownDriver.findElements(ManifestsPage.closeIcn).get(3), "Click Cross icon(Close icon)");
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(ManifestsPage.manifestOrderHeader, "Navigate back to manifest Order page");
        click(btn(" Packing List "), "Click Packing List Button");
        commonObj.validateText(ManifestsPage.orderNoHeader, "ORDER NUMBERs " + Utility_Functions.xGetJsonData("SalesOrder") + "-01", "Order numbers header: ");
        click(ManifestsPage.expandAll, "Click Expand All Link");
        sendKeys(ManifestsPage.deliveredInput, "1000", "Update delivered count");
        commonObj.validateText(ManifestsPage.successMsg, "Delivered quantity cannot be greater than shipped quantity.", "Error message popUp: ");
        Utility_Functions.timeWait(2);
        click(ManifestsPage.closePopUp);
        sendKeys(ManifestsPage.deliveredInput, "1", "Update delivered count");
        click(btn("Save Adjustments"), "Click Save Adjustments");
        Utility_Functions.timeWait(5);
        Utility_Functions.xScrollWindowTop(ownDriver);
        Utility_Functions.xScrollWindowTop(ownDriver);
        click(ownDriver.findElements(btn("Return to Orders")).get(1), "Click Return To Order");
        Utility_Functions.timeWait(3);

        click(btn(" Packing List "), "Click Packing List Button");
        commonObj.validateText(ManifestsPage.orderNoHeader, "ORDER NUMBERs " + Utility_Functions.xGetJsonData("SalesOrder") + "-01", "Order numbers header: ");
        click(ManifestsPage.expandAll, "Click Expand All Link");
        Utility_Functions.timeWait(3);
        Utility_Functions.xScrollIntoView(ownDriver, ManifestsPage.deliveredInput);
        Utility_Functions.timeWait(2);
        String actVal = ownDriver.findElement(ManifestsPage.deliveredInput).getAttribute("ng-reflect-model");
        Utility_Functions.xAssertEquals(report, "1", actVal, "");
        click(ownDriver.findElements(btn("Return to Orders")).get(1), "Click Return To Order");
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(ManifestsPage.manifestOrderHeader, "Navigate back to manifest Order page");
    }

    /**
     * Keyword to verify Save Adjustment and Return to Orders Button
     */
    public void saveAdjReturnToOrderBtnPO() {
        Utility_Functions.timeWait(2);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,ownDriver.findElements(ManifestsPage.closeIcn).get(3), "Click Cross icon(Close icon)");
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(ManifestsPage.manifestOrderHeader, "Navigate back to manifest Order page");
        navigateToOrderNumberPO();
        Utility_Functions.timeWait(4);
        click(ManifestsPage.expandAll, "Click Expand All Link");
        sendKeys(ManifestsPage.pickUpCount, "0", "Update Pick Up count");
        click(btn("Save Adjustments"), "Click Save Adjustments");
        Utility_Functions.timeWait(5);
        Utility_Functions.xScrollWindowTop(ownDriver);
        Utility_Functions.xScrollWindowTop(ownDriver);
        click(ownDriver.findElements(btn("Return to Orders")).get(1), "Click Return To Order");
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(ManifestsPage.manifestOrderHeader, "Navigate back to manifest Order page");
    }

    /**
     * Keyword to verify Close Manifest
     */
    public void closeManifest() {
        Utility_Functions.timeWait(2);
        click(ManifestsPage.closeIcon, "Click CLose manifest icon");
        commonObj.validateText(ManifestsPage.closeManifestMsg, "CLOSE MANIFEST", "CLOSE MANIFEST popup window is present");
        click(TruckPage.noButtonPopUp, "Click No button");
        commonObj.validateText(ManifestsPage.manifestListHeader, "Manifest List", "Manifest List Screen Header is present");
        click(ManifestsPage.closeIcon, "Click CLose manifest icon");
        commonObj.validateText(ManifestsPage.closeManifestMsg, "CLOSE MANIFEST", "CLOSE MANIFEST popup window is present");
        click(TruckPage.yesButtonPopUp, "Click Yes button");
        commonObj.validateText(ManifestsPage.manifestListHeader, "Manifest List", "Manifest List Screen Header is present");
        commonObj.validateText(ManifestsPage.manStatus, "Closed", "Verify status: ");
    }

    /**
     * Keyword to verify delete Manifest
     */
    public void deleteManifest() {
        String man = Utility_Functions.getText(ownDriver, truckObj.getTruck("Manifest Number"));
        Utility_Functions.xClickHiddenElement(ownDriver, By.xpath("//td/a"));
        Utility_Functions.timeWait(5);
        commonObj.validateText(By.xpath("//h2[text()=' Manifest #: " + man + " ']"), "Manifest #: " + man + "", "Manifest Number Screen Header: ");
        click(ManifestsPage.deleteManifest, "Click Delete Manifest");
        commonObj.validateElementExists(TruckPage.deleteConfPopUp, "Delete Confirmation Pop Up is present");
        click(ownDriver.findElements(ManifestsPage.delButton).get(0), "Click No Button");
        commonObj.validateText(By.xpath("//h2[text()=' Manifest #: " + man + " ']"), "Manifest #: " + man + "", "Manifest Number Screen Header: ");
        click(ManifestsPage.deleteManifest, "Click Delete Manifest");
        commonObj.validateElementExists(TruckPage.deleteConfPopUp, "Delete Confirmation Pop Up is present");
        click(ownDriver.findElements(ManifestsPage.delButton).get(1), "Click Yes Button");
        Utility_Functions.timeWait(3);
        String exp = "Manifest #" + man + " successfully deleted.";
        commonObj.validateText(TruckPage.deletePopUp, exp, "Deleted Manifest Successful message is present");
    }

    /**
     * Keyword to verify Close All Manifest
     */
    public void closeAllManifest() {
        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(ownDriver, ManifestsPage.closeIcon)) {
            int size = ownDriver.findElements(ManifestsPage.closeIcon).size();
            for (int i = 0; i < size; i++) {
                Utility_Functions.timeWait(3);
                click(ManifestsPage.closeIcon, "Click CLose manifest icon");
                commonObj.validateText(ManifestsPage.closeManifestMsg, "CLOSE MANIFEST", "CLOSE MANIFEST popup window is present");
                Utility_Functions.timeWait(2);
                click(TruckPage.noButtonPopUp, "Click No button");
                Utility_Functions.timeWait(2);
                commonObj.validateText(ManifestsPage.manifestListHeader, "Manifest List", "Manifest List Screen Header is present");
                click(ManifestsPage.closeIcon, "Click CLose manifest icon");
                Utility_Functions.timeWait(2);
                click(TruckPage.yesButtonPopUp, "Click Yes button");
                commonObj.validateText(ManifestsPage.manifestListHeader, "Manifest List", "Manifest List Screen Header is present");
                //commonObj.validateText(ManifestsPage.closedStatus, "Closed", "Verify status: ");
            }
        }
    }
}