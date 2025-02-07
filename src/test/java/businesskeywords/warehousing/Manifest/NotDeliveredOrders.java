package businesskeywords.warehousing.Manifest;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.common.MasterPage;
import pages.warehouse.*;
import supportLibraries.Utility_Functions;

import java.util.List;

public class NotDeliveredOrders extends ReusableLib {
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
    public NotDeliveredOrders(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        manFest = new Manifests(helper);
        drv=new Drivers(helper);
        truck=new Trucks(helper);
        ownDriver=helper.getGSDriver();
    }

    /**
     * Keyword to Navigate to Not Delivered Orders Screen
     *
     */
    public void navigateToNotDeliveredOrdersScreen() {
        click(TruckPage.menuIconTruck);
        //truck.callSelectCompany();
        try {
            click(NotDeliveredShipmentsPage.subMenuNotDeliveredShip, "Navigate to trucks page");
        }catch (Exception e){
            Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.menuIconTruck,"Click Menu");
            Utility_Functions.timeWait(3);
            click(NotDeliveredShipmentsPage.subMenuNotDeliveredShip, "Navigate to trucks page");
        }
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.timeWait(3);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,NotDeliveredShipmentsPage.notDeliveredShipmentsHeader, "Not Delivered Shipments Screen Header is present");
    }

    /**
     * Keyword to verify UI of Not Delivered Orders
     */
    public void notDeliveredOrdersUI() {
        String[] actText={"Shipment","Date Not Delivered","Customer PO Number","Deliver To","Driver","Manifest Number","Actions"};
        List<WebElement> els=ownDriver.findElements(By.xpath("//th"));
        int i=0;
        for(WebElement el:els){
            if(i==7){break;}else {
                Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
                i++;
            }
        }
        commonObj.validateElementExists(TruckPage.helpIcon,"Help Icon '?' is present");
        commonObj.validateElementExists(TruckPage.filterSearch,"Search filter icon is present");
        String page=Utility_Functions.getText(ownDriver,TruckPage.currentPage);
        commonObj.validateElementExists(TruckPage.currentPage,"Current Page "+page+" "+Utility_Functions.getText(ownDriver,TruckPage.outOf)+"");
        List<WebElement> elm=ownDriver.findElements(TruckPage.show);
        String[] acText={"10","15","30"};
        int j=0;
        for(WebElement el:elm){
            if(j==3){
                break;
            }else {
                Utility_Functions.xAssertEquals(report, el.getText().trim(), acText[j], "");
                j++;
            }
        }
    }

    /**
     * Keyword to verify UI of Search Filters
     */
    public void searchFiltersNotDlvrdOrdrsdUI() {
        click(TruckPage.filterSearch, "Click Search Filter icon");
        Utility_Functions.timeWait(2);
        commonObj.validateText(TruckPage.searchFilterPanelTitle, "Search Filters", "Search Filters panel title is present");
        String[] actText = {"Order Number", "Date Not Delivered", "Customer PO Number", "Deliver To","Driver", "Manifest Number"};
        List<WebElement> els = ownDriver.findElements(TruckPage.searchFiltersLabel);
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        commonObj.validateElementExists(TruckPage.filtersCrossIcon, "Cross icon is present");
        commonObj.validateText(TruckPage.applyFiltersDis, "Apply Filters", "Apply Filters button exist and button is disabled");
    }

    /**
     * Keyword to Verify functionality of Page Count
     *
     */
    public void funPageCountNotDelivered() {
        Utility_Functions.xScrollWindow(ownDriver);
        if(Utility_Functions.xIsDisplayed(ownDriver, DriversPage.onePage)){
            int driverCount = ownDriver.findElements(DriversPage.driverNameCount).size()-1;
            commonObj.validateText(DriversPage.onePage,"of 1","One page is available having count "+driverCount+"");
        }else {
            int driverCount = ownDriver.findElements(DriversPage.driverNameCount).size()-1;
            System.out.println("........." + driverCount + ".......");
            Utility_Functions.xAssertEquals(report, "" + driverCount + "", "10", "The Count is 10 by default");
            drv.valPageCount(10);
            drv.valPageCount(15);
            drv.valPageCount(30);
        }
    }

    public By filterField(String label){
        return By.xpath("//label[text()='"+label+"']/parent::div/descendant::input");
    }

    /**
     * Keyword to verify Delivered Orders
     */
    public void verifyNotDeliveredOrders() {
        Utility_Functions.timeWait(8);
        click(TruckPage.filterSearch, "Click search filter icon");
        Utility_Functions.timeWait(1);
        sendKeys(filterField("Shipment"), Utility_Functions.xGetJsonData("SalesOrder")+"-01", "Enter Order Number");
        click(NotDeliveredShipmentsPage.notDeliveredDate,"Click Not Delivered Date");
        int size=ownDriver.findElements(NotDeliveredShipmentsPage.activeDay).size();
        click(ownDriver.findElements(NotDeliveredShipmentsPage.activeDay).get(size-1),"Select today date");
        String date=ownDriver.findElement(NotDeliveredShipmentsPage.notDeliveredDate).getAttribute("ng-reflect-model");
        Utility_Functions.xMouseClick(ownDriver, NotDeliveredShipmentsPage.notDeliveredDate);
        Utility_Functions.timeWait(2);
        String driverName = Utility_Functions.xGetJsonData("Driver");
        System.out.println("driverName...." + driverName);
        click(ownDriver.findElement(By.xpath("//label[text()='Driver']/parent::div/descendant::option[@class='ng-star-inserted' and contains(text(),'" + driverName + "')]")), "Select Driver from the drop down");
        Utility_Functions.timeWait(2);
        sendKeys(filterField("Manifest Number"), Utility_Functions.xGetJsonData("ManifestNumber"), "Enter Manifest Number");
        click(TruckPage.applyFilter, "Click Apply Filters");
        Utility_Functions.timeWait(2);
        commonObj.validateText(drv.getTruck("Order Number"), Utility_Functions.xGetJsonData("SalesOrder")+"-01", "After filter Order Number: ");
        commonObj.validateText(drv.getTruck("Driver "), driverName, "After filter Driver Name: ");
        commonObj.validateText(drv.getTruck("Date Not Delivered"), date, "After filter Date Not Delivered: ");
        commonObj.validateText(drv.getTruck("Manifest Number"),Utility_Functions.xGetJsonData("ManifestNumber") , "After filter status: ");
    }

    /**
     * Keyword to verify Create new manifest for Not Delivered order
     *
     */
    public void createManNotDeliveredOrder() {
        verifyNotDeliveredOrders();
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,NotDeliveredShipmentsPage.addToManifestButton,"CLick Add To Manifest button");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,NotDeliveredShipmentsPage.manifestHeader,"Manifest screen header: ");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,NotDeliveredShipmentsPage.newManifestBtn,"Click New Manifest Button");
        manFest.fillDetails();
        try {
            click(ManifestsPage.createManifestBtn, "Click Create Manifest Button");
        }catch (Exception e){
            click(ManifestsPage.manifestBtn, "Click Create Manifest Button");
        }
        Utility_Functions.timeWait(5);
        manFest.navigateToManifestsScreen();
        manFest.verifyManifestNum();
    }

    /**
     * Keyword to verify Adding Not Delivered order to Exist Manifest
     *
     */
    public void addToManifestNotDelOrd() {
        verifyNotDeliveredOrders();
        click(NotDeliveredShipmentsPage.addToManifestButton,"CLick Add To Manifest button");
        Utility_Functions.timeWait(5);
        commonObj.validateText(NotDeliveredShipmentsPage.manifestHeader,"MANIFESTS","Manifest screen header: ");
        Utility_Functions.timeWait(7);
        String manifestNo=Utility_Functions.getText(ownDriver,NotDeliveredShipmentsPage.selManNo);
        click(truck.getTruck("Status"),"Select "+manifestNo+" manifest number");
        Utility_Functions.timeWait(2);
        int size=ownDriver.findElements(NotDeliveredShipmentsPage.addToManifestButton).size()-1;
        click(ownDriver.findElements(NotDeliveredShipmentsPage.addToManifestButton).get(size),"Click Add to Manifest Number");
        Utility_Functions.timeWait(3);
        commonObj.validateText(TruckPage.deletePopUp,"Order number "+Utility_Functions.xGetJsonData("SalesOrder")+"-01"+" successfully added to manifest "+manifestNo+".","");
        manFest.navigateToManifestsScreen();
        click(ManifestsPage.orderColLink, "Click on order number link from orders column");
        Utility_Functions.timeWait(4);
        commonObj.validateText(By.xpath("//label[contains(text(),'Order Number: "+Utility_Functions.xGetJsonData("SalesOrder")+"-01"+"')]"),"Order Number: "+Utility_Functions.xGetJsonData("SalesOrder")+"-01"+"","");
    }

}
