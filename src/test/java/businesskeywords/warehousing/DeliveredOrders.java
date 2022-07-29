package businesskeywords.warehousing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.common.MasterPage;
import pages.warehouse.DeliveredOrdersPage;
import pages.warehouse.TruckPage;
import supportLibraries.Utility_Functions;

import java.util.List;

public class DeliveredOrders extends ReusableLib {
    CommonActions commonObj;
    Manifests manFest;
    Drivers drv;
    Trucks truck;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public DeliveredOrders(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        manFest = new Manifests(helper);
        drv = new Drivers(helper);
        truck = new Trucks(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * Keyword to Navigate to Delivered Orders Screen
     */
    public void navigateToDeliveredOrdersScreen() {
        click(TruckPage.menuIconTruck);
        truck.callSelectCompany();
        Utility_Functions.timeWait(3);
        click(DeliveredOrdersPage.subMenuDeliveredShip, "Navigate to Delivered Shipments page");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.timeWait(3);
        commonObj.validateText(DeliveredOrdersPage.deliveredShipmentsHeader, "Delivered Shipments", "Delivered Shipments Screen Header is present");
    }

    /**
     * Keyword to verify UI of Delivered Orders
     */
    public void deliveredOrdersUI() {
        String[] actText = {"Shipment", "Date/Time", "PO No.", "Manifest No.", "Delivered To", "Shipment Status", "Manifest Status", "Driver", "Truck", "Ship Via"};
        List<WebElement> els = ownDriver.findElements(By.xpath("//th"));
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        commonObj.validateElementExists(TruckPage.helpIcon, "Help Icon '?' is present");
        commonObj.validateElementExists(TruckPage.filterSearch, "Search filter icon is present");
        commonObj.validateElementExists(DeliveredOrdersPage.exportButton, "Export button is present");
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
     * Keyword to verify UI of Search Filters
     */
    public void searchFiltersDlvrdOrdrsdUI() {
        click(TruckPage.filterSearch, "Click Search Filter icon");
        Utility_Functions.timeWait(2);
        commonObj.validateText(TruckPage.searchFilterPanelTitle, "Search Filters", "Search Filters panel title is present");
        String[] actText = {"Order Number", "Date Delivered Range", "Customer PO Number", "Delivered To", "Ship Via", "Shipment Status", "Manifest Order Status", "Driver", "Truck", "Manifest Number"};
        List<WebElement> els = ownDriver.findElements(TruckPage.searchFiltersLabel);
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        commonObj.validateElementExists(TruckPage.filtersCrossIcon, "Cross icon is present");
        commonObj.validateText(TruckPage.applyFiltersDis, "Apply Filters", "Apply Filters button exist and button is disabled");
    }

    public By filterField(String label) {
        return By.xpath("//label[text()='" + label + "']/parent::div/descendant::input");
    }

    /**
     * Keyword to verify Delivered Orders
     */
    public void verifyDeliveredOrders() {
        Utility_Functions.timeWait(8);
        click(TruckPage.filterSearch, "Click search filter icon");
        Utility_Functions.timeWait(1);
        sendKeys(filterField("Order Number"), Utility_Functions.xGetJsonData("SalesOrder"), "Enter Order Number");
        Utility_Functions.xMouseClick(ownDriver, DeliveredOrdersPage.truckDrop);
        Utility_Functions.timeWait(2);
        String truckName = Utility_Functions.xGetJsonData("TruckName");
        System.out.println("truckName...." + truckName);
        click(ownDriver.findElement(By.xpath("//label[text()='Truck']/parent::div/descendant::option[@class='ng-star-inserted' and contains(text(),'" + truckName + "')]")), "Select truck from the drop down");
        Utility_Functions.timeWait(2);
        Utility_Functions.xMouseClick(ownDriver, DeliveredOrdersPage.driverDrop);
        Utility_Functions.timeWait(2);
        String driverName = Utility_Functions.xGetJsonData("Driver");
        System.out.println("driverName...." + driverName);
        click(ownDriver.findElement(By.xpath("//label[text()='Driver']/parent::div/descendant::option[@class='ng-star-inserted' and contains(text(),'" + driverName + "')]")), "Select Driver from the drop down");
        Utility_Functions.timeWait(2);
        sendKeys(filterField("Manifest Number"), Utility_Functions.xGetJsonData("ManifestNumber"), "Enter Manifest Number");
        click(TruckPage.applyFilter, "Click Apply Filters");
        Utility_Functions.timeWait(2);
        commonObj.validateText(drv.getTruck("Order Number"), Utility_Functions.xGetJsonData("SalesOrder") + "-01", "After filter Order Number: ");
        commonObj.validateText(drv.getTruck("Manifest Status"), "Delivered", "After filter Manifest Order Status: ");
        commonObj.validateText(drv.getTruck("Shipment Status"), "Open", "After filter Shipment Status: ");
        commonObj.validateText(drv.getTruck("Truck"), truckName, "After filter Truck Name: ");
        commonObj.validateText(drv.getTruck("Driver"), driverName, "After filter Driver Name: ");
        commonObj.validateText(drv.getTruck("Manifest No."), Utility_Functions.xGetJsonData("ManifestNumber"), "After filter status: ");
    }
}
