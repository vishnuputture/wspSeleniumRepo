package businesskeywords.warehousing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.common.MasterPage;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
import pages.warehouse.DriversPage;
import pages.warehouse.ManifestsPage;
import pages.warehouse.TruckPage;
import software.amazon.awssdk.services.route53domains.model.UnsupportedTldException;
import supportLibraries.Utility_Functions;

import java.util.List;

public class Manifests extends ReusableLib {

    CommonActions commonObj;
    Trucks truckObj;

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
    }

    /**
     * Keyword to Select Company
     */
    public void selectCompany() {
        click(SelfServicePriceSheetPage.companySelector);
        click(SelfServicePriceSheetPage.companyLabel);
        sendKey(SelfServicePriceSheetPage.winCompanyNumber, "99599");
        click(SelfServicePriceSheetPage.selectButton);
        Utility_Functions.timeWait(5);
    }

    /**
     * Keyword to Navigate to Manifests Screen
     */
    public void navigateToManifestsScreen() {
        selectCompany();
        click(TruckPage.menuIconTruck);
        click(ManifestsPage.subMenuManifest, "Navigate to Manifest List page");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.timeWait(3);
        commonObj.validateText(ManifestsPage.manifestListHeader, "Manifest List", "Manifest List Screen Header is present");
    }

    /**
     * Keyword to verify the availability of field again Manifest List Screen
     */
    public void verifyManifestListUI() {
        String[] actText = {"Manifest Number", "Delivery Date", "Orders", "Delivered Orders", "Stops", "Status", "Driver", "Truck", "Actions"};
        List<WebElement> els = driver.findElements(By.xpath("//th"));
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        commonObj.validateElementExists(TruckPage.helpIcon, "Help Icon '?' is present");
        commonObj.validateElementExists(TruckPage.filterSearch, "Search filter icon is present");
        int size = driver.findElements(TruckPage.pagination).size();
        Utility_Functions.xAssertEquals(report, size, 4, "Next page and previous page arrow icon is present");
        String page = Utility_Functions.getText(driver, TruckPage.currentPage);
        commonObj.validateElementExists(TruckPage.currentPage, "Current Page " + page + " " + Utility_Functions.getText(driver, TruckPage.outOf) + "");
        List<WebElement> elm = driver.findElements(TruckPage.show);
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
        click(driver.findElements(DriversPage.pageArrow).get(actPageNo), "Click on " + arrowIcon + " Present below the Right Corner of the page");
        Utility_Functions.xScrollWindow(driver);
        String pageNo = driver.findElement(TruckPage.currentPage).getAttribute("ng-reflect-model");
        Utility_Functions.xAssertEquals(report, pageNo, expPage, "Moved to " + pageNo + " Page");
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to Verify functionality of Pagination
     */
    public void paginationManifest() {
        int size = 2;
        Utility_Functions.xScrollWindow(driver);
        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(driver, DriversPage.onePage)) {
            commonObj.validateText(DriversPage.onePage, "of 1", "One page is available");
        } else {
            click(driver.findElements(DriversPage.pageArrow).get(2));
            Utility_Functions.xScrollWindow(driver);
            while (!Utility_Functions.xIsDisplayed(driver, DriversPage.lastPage)) {
                size++;
                click(driver.findElements(DriversPage.pageArrow).get(2));
                Utility_Functions.xScrollWindow(driver);
            }
            click(driver.findElements(DriversPage.pageArrow).get(0));
            Utility_Functions.xScrollWindow(driver);
            selectPage(2, "2", "Right Arrow (>)");
            selectPage(1, "1", "Left Arrow (<)");
            selectPage(3, "" + size + "", "Right double Arrow (>>)");
            selectPage(0, "1", "Left double Arrow (<<)");
        }
    }

    public void navigateToCreateManifest() {
        click(ManifestsPage.createManBtn, "Click on Create New Manifest button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(ManifestsPage.createNewManHeader, "Create New Manifest", "Verify Create New Manifest Header");
    }

    /**
     * Keyword to verify the UI of Create Manifest Screen
     */
    public void uiCreateManifest() {
        String[] actText = {"Delivery Date", "Start Time", "Truck (Optional)", "Notes to Driver (optional)"};
        List<WebElement> els = driver.findElements(ManifestsPage.createManLabel);
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        Utility_Functions.timeWait(2);
        commonObj.validateText(ManifestsPage.manifestDetail, "Manifest Detail", "Manifest Detail text is present");
        commonObj.validateElementExists(DriversPage.crossIcon, "Cross icon is present");
        commonObj.validateElementExists(ManifestsPage.collapseIcon, "Collapse icon is present");
        commonObj.validateText(ManifestsPage.stopNOrderLab, "STOPS & ORDERS", "Verify STOPS & ORDERS text is present");
        commonObj.validateText(ManifestsPage.addOrdNoLabel, "Add Order Number", "Add Order Number text is present");
        commonObj.validateText(ManifestsPage.addButton, "Add", "Add button is present");
        commonObj.validateText(ManifestsPage.addStopBtn, "Add Stop", "Add Stop button is present");
        commonObj.validateText(ManifestsPage.addPOBtn, "Add PO", "Add PO button is present");
        commonObj.validateText(ManifestsPage.createManifestBtn, "Create Manifest", "Create Manifest button is present");
        click(DriversPage.crossIcon, "Click Cross close Icon");
        Utility_Functions.timeWait(3);
        commonObj.validateText(ManifestsPage.manifestListHeader, "Manifest List", "Manifest List Screen Header is present");
    }

    public By isCollapsed(String bol){
        return By.xpath("//a[@aria-expanded='"+bol+"']");
    }

    /**
     * Keyword to create manifest
     *
     */
    public void verifyAreaExpanded(){
        click(ManifestsPage.collapseIcon,"Click Collapse Icon");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(isCollapsed("false"),"Session is collapsed");
        click(ManifestsPage.collapseIcon,"Click Expand Icon");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(isCollapsed("true"),"Session is expanded");
    }

    public WebElement truckDriverDrop(String label) {
        return driver.findElement(By.xpath("//label[text()='" + label + "']/parent::div/descendant::option[@class='ng-star-inserted']"));
    }

    /**
     * Keyword to create manifest
     *
     */
    public void createManifest() {
        click(ManifestsPage.newManifestDeliveryDate);
        click(TruckPage.licensePlateExpSelect, "Select Delivery Date");
        click(ManifestsPage.newManifestStartTime, "Select Start Date");
        click(ManifestsPage.truckEle);
        Utility_Functions.timeWait(3);
        click(truckDriverDrop("Truck (Optional)"),"Select truck from the drop down");
        click(ManifestsPage.notes);
        Utility_Functions.timeWait(2);
        Utility_Functions.xMouseClick(driver,ManifestsPage.driverEle);
        Utility_Functions.timeWait(2);
        click(truckDriverDrop("Driver (Optional)"), "Select driver from the drop down");
        Utility_Functions.timeWait(2);
        sendKeys(ManifestsPage.notes, "All good", "Enter values in Notes to Driver (optional)");
        Utility_Functions.timeWait(2);
        Utility_Functions.xScrollIntoView(driver,ManifestsPage.addOrderNo);
        Utility_Functions.timeWait(2);
        sendKeys(ManifestsPage.addOrderNo, "000552-01", "Enter Order number");
        click(ManifestsPage.addButton, "Click Add button");
        commonObj.validateText(ManifestsPage.orderAddedMessage, "Order 000552-01 successfully added to manifest", "Order is added");
        click(ManifestsPage.createManifestBtn, "Click Create Manifest Button");
        Utility_Functions.timeWait(5);
        String maniNo = Utility_Functions.getText(driver, truckObj.getTruck("Manifest Number"));
        commonObj.validateText(ManifestsPage.createStatus,"Created","Manifest is created and Manifest number is :"+maniNo+"");
    }

    /**
     * Keyword to verify UI of Search Filters
     *
     */
    public void searchFiltersManifestUI() {
        click(TruckPage.filterSearch,"Click Search Filter icon");
        Utility_Functions.timeWait(2);
        Utility_Functions.xScrollWindow(driver);
        Utility_Functions.timeWait(2);
        commonObj.validateText(TruckPage.searchFilterPanelTitle,"Search Filters","Search Filters panel title is present");
        String[] actText = {"Manifest Number", "Delivery Date","Start Time", "Status","Driver","Truck","Customer","Customer PO Number","Order Number","Job Name"};
        List<WebElement> els = driver.findElements(TruckPage.searchFiltersLabel);
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        commonObj.validateElementExists(TruckPage.filtersCrossIcon,"Cross icon is present");
        commonObj.validateText(TruckPage.applyFiltersDis,"Apply Filters","Apply Filters button exist and button is disabled");
        //commonObj.validateText(TruckPage.clearFilters,"Clear All Filters","Clear All Filters button is exist and text is in red color");
    }

    public By getRowVal(String label){
        return By.xpath("//table/tbody/tr[1]/td[count(//table/thead/tr/th[contains(text(),'"+label+"')]/preceding-sibling::th)+1]");
    }

    /**
     * Keyword to verify UI of Search Filters
     *
     */
    public void navigateToManifestOrder() {
        String manNo=Utility_Functions.getText(driver,getRowVal("Manifest Number")).trim();
        Utility_Functions.xmouseOver(driver,ManifestsPage.orderColLink);
        Utility_Functions.timeWait(2);
        String salesOrderCount= Utility_Functions.getText(driver,ManifestsPage.soPoOrders);
        String PurchaseOrder=driver.findElements(ManifestsPage.soPoOrders).get(1).getText();
        click(ManifestsPage.orderColLink,"Click on order number link from orders column");
        Utility_Functions.timeWait(4);
        String header=Utility_Functions.getText(driver,ManifestsPage.manifestOrderHeader).trim();
        Utility_Functions.xAssertEquals(report,header,"MANIFEST #: "+manNo+" - ORDERS","Manifest Orders Header: ");
    }

    public By btn(String button){
        return By.xpath("//button[text()='"+button+"']");
    }

    public By lbl(String label){
        int size=driver.findElements(By.xpath("//*[text()='"+label+"']")).size();
        return By.xpath("(//*[text()='"+label+"'])["+size+"]");
    }

    /**
     * Keyword to verify UI of Search Filters
     *
     */
    public void manifestOrderUI() {
        commonObj.validateText(lbl("Warehouse Manager Notes"),"Warehouse Manager Notes","Label matches");
        commonObj.validateText(lbl("Notes to Driver"),"Notes to Driver","Label matches");
        commonObj.validateText(btn("Save Notes"),"Save Notes","Button matches");
        commonObj.validateText(lbl("STOPS & ORDERS "),"STOPS & ORDERS","Label matches");
        commonObj.validateElementExists(DriversPage.crossIcon,"Cross icon is present");
        commonObj.validateElementExists(ManifestsPage.colExpIconManifestOrder,"Collapse icon is present");
    }
}
