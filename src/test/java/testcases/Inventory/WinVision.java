package testcases.Inventory;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class WinVision extends TestConfigurations{

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_802_VerifyWinVisionHomeScreen(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the navigation to the WinVision module");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_803_VerifyNavigationToOnlineCatalog(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the navigation to the Online Catalog page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_813_VerifyUIofOnlineCatalogWithoutSearchResults(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of Online Catalog page with out search results.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_814_VerifyHelpIcon(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the Help Pop up is displayed on clicking the help icon '?' .");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_815_VerifyUIofSearchPop(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the availability of search icon and UI of the Pop up");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_806_VerifyNavigationToOrderTracking (SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the availability of search icon and UI of the Pop up");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_807_VerifyHelpIconUI (SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the availability of search icon and UI of the Pop up");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_811_VerifyUIWithoutRecord (SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the availability of search icon and UI of the Pop up");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
