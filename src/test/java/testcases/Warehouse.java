package testcases;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class Warehouse extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_TruckUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case UI of Trucks screen");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_TrucksHelpIcon(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify functionality of Help (?)icon");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_TrucksPagination(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify functionality of Pagination");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_TrucksPageCount(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify functionality of Page Count");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_AddNewTruckScreenUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify functionality of Add new truck Screen UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_AddTruck(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case add Truck");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_searchFiltersTruckUi(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Search Filters Truck Ui");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_DriversUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case UI of Drivers screen");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_197_DriversHelpIcon(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify functionality of Help (?)icon");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_198_DriversPagination(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify functionality of Help (?)icon");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_200_DriversPageCount(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify functionality of Page Count");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_209_AddNewDriverScreenUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case UI of Add New Driver Screen");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_AddNewDriver(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case Add New Driver");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_ApplyFilterDriver(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Apply Filter Driver");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_ApplyFilterTruck(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Apply Filter");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_ManifestListUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Manifest List Screen UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_235_ManifestListHelpIcon(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify ManifestList Help Icon");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_236_ManifestListPagination(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify ManifestList Pagination");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_238_ManifestListPageCount(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify ManifestList Page Count");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreateSalesOrderWISE(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Create Sales Order");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_240_CreateManifestUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Create new Manifest Ui");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_241_collapseExpandSession(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify collapse and expand icon");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_243_CreateManifest(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Create new Manifest");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_OrderNumberSO_UI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Order Number UI: Sales Order");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_SaveAdjustment(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Save Adjustment");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_SOUpdateStatusDelivered(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Functionality Update status drop down");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_DeliveredOrdersSearchFilterUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Delivered Orders Search Filter UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_DeliveredOrdersUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Delivered Orders UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_VerifyDeliveredOrders(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify filter Delivered Orders");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_SOUpdateStatusNotDelivered(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Functionality Update status drop down");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_NotDeliveredOrdersSearchFilterUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Not Delivered Orders Search Filter UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_NotDeliveredOrdersUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Not Delivered Orders UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_VerifyNotDeliveredOrders(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Not Delivered Orders");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CloseManifest(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Close manifest");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_DeleteManifest(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Delete manifest");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreateManifestNotDeliveredOrd(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Create Manifest NotDelivered Order");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_AddToManifestNotDelOrd(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Add to manifest for NotDelivered Order");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreateManifestPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Functionality ADD PO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_OrderNumberPO_UI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Order Number PO UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_SaveAdjustmentPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Save adjustment button PO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_POUpdateStatus(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("TC_POUpdateStatus");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_244_CreateManifestMultipleShipments(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Create new Manifest");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_InProgressStatus(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Verify In-Progress Status");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_258_ManifestSearchFilterUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Manifest Search Filter UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_295_ManifestOrderUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Manifest Orders UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_297_SaveNoteExpandCollapse(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Functionality of save note button");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_DeleteTruck(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Delete Truck button");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_DeleteDriver(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Delete Driver button");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_UpdateTruckUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Update Truck Screen UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_UpdateTruck(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Update Truck");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_UpdateDriverUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Update Driver screen UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_UpdateDriver(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Update Truck");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_DeleteManifestList(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Delete Manifest List");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
