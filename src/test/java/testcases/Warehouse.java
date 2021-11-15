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
    public void TC_258_ManifestSearchFilterUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Manifest Search Filter UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
