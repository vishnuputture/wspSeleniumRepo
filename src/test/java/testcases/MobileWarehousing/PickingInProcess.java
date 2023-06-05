package testcases.MobileWarehousing;
import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;
public class PickingInProcess extends TestConfigurations{

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_997_VerifyPickingInProcessHomeUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify UI of Picking in Process Home page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_999_VerifySearchFilterUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify UI of Picking in Process Home page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1000_VerifyOrderDetailsPageUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify UI of Order Details page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1003_VerifySortingFunctionality(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify shorting functionality");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1004_VerifyFilters(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify shorting functionality");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_998_NavigateToPickingInProcess(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify shorting functionality");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1154_VerifyDefaultPickStatusShipment(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that by default 'In process' pick status shipments are displayed on the screen");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1161_VerifyCurrentZoneFilter(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that by default 'In process' pick status shipments are displayed on the screen");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1001_VerifyLogout(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify logging out of the application");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1162_VerifyOpenTotalLines(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the Display and update of Open/Total lines when picking is done");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1199_VerifyShipDateToggle(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that Ship Date column can be toggled");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1200_VerifyIconsBesideShipment(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionalities of Icons present beside shipment");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1204_VerifyReturnToPickQPriority(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality of Return to pick Queue Priority");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1197_VerifyPagination(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Pagination of records");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1198_VerifyRecordsPerPage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify user can change and view the records per page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1203_VerifyLoadPickQueuePopUp(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the UI of Load Pick Queue Pop up ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1204_VerifyReturnToPickQueuePriority(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality of Return to pick Queue Priority");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1167_VerifyPercentCompleteCalculation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Percentage Completion Calculation");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1168_VerifyStatusOfZOne(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the Status of the Zone");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1169_VerifyPrintDocumentsAndLabelsUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality of the 'Print Documents and Labels' button");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1170_VerifyRemovingStagingDetails(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify user can Remove the Staging details");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1157_VerifyWarehouseSettingsPage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify navigation and UI if warehouse settings page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1160_VerifyShipViaScreenUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the UI of the Ship Via(Shipping Methods) page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1166_VerifyModifyingPriorityShipViaPage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that user is able to modify the ship via priority by drag and drop");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1195_CreateNewShipMethod(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the user can create a new ship method by clicking on the New Ship Via button.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1172_VerifyPickQueueScheduleScreenUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the UI of the \"Pick Queue Schedule\" page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1173_VerifyAddingDayInPickQueueScheduleScreen(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify user can add additional day to existing pick queue schedule");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1175_VerifyHolidayEventScheduleScreenUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the UI of the \"Holiday & Event Schedule\" page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1179_VerifyAddAnsEditEvent(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify adding and editing an event");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1177_VerifyEditEventFunctionality(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify adding and editing an event");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1182_VerifyNavigationToPackagingUnits(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify navigation to the 'Packaging Units'  page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1183_VerifyUIOfPackagingUnits(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify UI of the 'Packaging Units' page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1185_ModifyPackagingUnitsPriority(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify 'Packaging Units' priority");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1187_VerifyAddingPackagingUnits(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify add and edit packing units");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1188_VerifyListOrderAndUnitLabel(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality of List Order and Unit Label");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1189_VerifyLeaveWithoutSaving(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Leave without saving message");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1191_VerifyDocumentsAndPrintingScreenUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the UI of the[Documents & Printing] page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1192_VerifyPrinterDetailsPopupUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the UI of the [Printer Details] popup");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1194_VerifyAddingPrinter(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify user can add the new printer by clicking on the [Add Printer] hyperlink.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1206_VerifySearchRecordsWithShipmentNo(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search for the records using Shipment");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1207_VerifySearchRecordsWithCustomer(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search for the records using the Customer field.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1208_VerifySearchRecordsWithAssignedTo(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search the records using the 'Assigned to' field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1209_VerifySearchRecordsWithShipVia(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search the records using the 'Ship Via' field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1210_VerifySearchRecordsWithTruckName(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that user can search the records using the 'Truck Name'");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1211_VerifySearchRecordsWithPickStatus(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that user can search the records using the 'All' pick status");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1213_VerifySearchRecordsWithStagedStatusPrintY(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the search results using the staged status with Pack List Print 'Y'");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1214_VerifySearchRecordsWithStagedStatusPrintN(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the search results using the staged status with Pack List Print 'N'");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1218_VerifySearchRecordsWithShipDate(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search the records using the 'Ship date' field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1219_VerifySearchRecordsWithShipmentNoCustomer(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search for the records using 'shipment no and customer'.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1223_VerifySearchRecordsWithShipmentNoShipVia(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search the records using shipment no and Ship Via");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1224_VerifySearchRecordsWithShipmentNoTruckName(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search the records using shipment no and Truck name");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1225_VerifySearchRecordsWithShipmentNoPickStatus(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search using shipment no and Pick status");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1227_VerifySearchRecordsWithShipmentNoPickStatusStagedPrintY(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search using shipment no and Pick status Staged");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1228_VerifySearchRecordsWithShipmentNoStagedPrintN(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the search results using the staged 'Shipment no ,Staged status with Pack List Print 'N'");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1229_VerifySearchRecordsWithShipmentNoShipDate(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search using shipment no and Ship Date.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1230_VerifySearchRecordsWithCustomerAssignedTo(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search for the records using the Customer and assigned to field.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1232_VerifySearchRecordsWithCustomerShipVia(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search using Customer Name and Ship Via");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1233_VerifySearchRecordsWithCustomerTruckName(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search using Customer Name and Truck Name");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1238_VerifySearchRecordsWithCustomerPickStatus(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search using Customer Name and Pick Status");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1239_VerifySearchRecordsWithCustomerPickStatusStagedPrintY(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search using Customer name , Pick status staged and Pack List as Y");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1240_VerifySearchRecordsWithCustomerStagedPrintN(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search using Customer name and Pick status staged");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1243_VerifySearchRecordsWithCustomerShipDate(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search using Customer Name and Ship Date.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1244_VerifySearchRecordsWithShipViaTruckName(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search for the records using 'Ship Via and Truck Name''.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1245_VerifySearchRecordsWithShipViaPickStatus(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search for the records using 'Ship Via and Pick Status''.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1246_VerifySearchRecordsWithShipViaPickStatusStagedPrintY(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search for the records using 'Ship Via , Staged pick status and Print status as Y'.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1248_VerifySearchRecordsWithShipViaPickStatusStagedPrintN(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search for the records using 'Ship Via , Staged pick status and Print status as N'.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1247_VerifySearchRecordsWithShipViaShipDate(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search for the records using 'Ship Via and Ship Date''.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1249_VerifySearchRecordsWithShipDatePickStatusInProcess(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search for the records using 'Ship Date and Pick Status as In process''.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1250_VerifySearchRecordsWithShipDatePickStatusStagedPrintY(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search for the records using 'Ship Date Staged pick status and Print Status as Y.''.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1251_VerifySearchRecordsWithShipDatePickStatusStagedPrintN(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search for the records using 'Ship Date Staged pick status and Print Status as N''.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1252_VerifySearchRecordsWithTruckNameShipDatePickStatusInProcess(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search for the records using 'Truck Name ,Ship Date and Pick Status as In process'.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1259_VerifySearchRecordsWithShipmentNoCustomerTruckNamePickStatusInProcess(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search for the records using 'shipment no ,customer, truck Name and Pick Status In Process'.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1261_VerifySearchRecordsWithShipmentNoCustomerPickStatusStagedPrintY(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that User can search for the records using ''shipment no , customer and pick status as staged'");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1258_VerifyManualPickingInPickingInProcessPage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the manual picking on 'Picking In process' page ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1308_VerifySortingInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Validate the sorting of columns in Exception Queue page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1309_VerifyExportToExcelInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the 'Export to Excel' button");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1310_VerifyPaginationInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the pagination of Exception Queue page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1311_VerifyReslutionNotesInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Validate that the resolution notes can be saved");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1312_VerifyUIInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the UI of Exception Queue page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1313_VerifyClosingRecordsInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that individual records can be viewed and closed");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1314_VerifyClosingMultipleRecordsInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Validate that multiple entries can be closed");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1320_VerifySearchWithSourceInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that user can narrow down exception results using Source Process field.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1321_VerifySearchWithDateInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that user can narrow down exception results using Data Occurred field.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1322_VerifySearchWithErrorReasonInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that user can narrow down exception results using Error Reason field.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1323_VerifySearchWithStatusInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that user can narrow down exception results using Status field.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1324_VerifySearchWithDateSourceInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that user can narrow down exception results using 'Data Occurred & Source Process'field.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1325_VerifySearchWithDateErrorReasonInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that user can narrow down exception results using 'Data Occurred & Error Reason 'field.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1326_VerifySearchWithDateStatusInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that user can narrow down exception results using 'Data Occurred & Status 'field.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1332_VerifySearchWithSourceStatusInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that user can narrow down exception results using 'Source Process & Status 'field.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1333_VerifySearchWithErrorReasonStatusInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that user can narrow down exception results using 'Error Reason & Status 'field.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_1334_VerifySearchWithAllFiltersInExceptionQueuePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify that user can narrow down exception results using All Filters");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
