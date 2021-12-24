package testcases;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class Spo extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_50_SuggestedPOUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case UI of Suggested Purchase Order Page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_51_CreateNewWorksheetTemplateUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case UI of Create New Worksheet Template Page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_54_CreateWorksheet_MCPCVCVNPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case verify the functionality of 'Create Worksheet' button with MC+PC+VC+VN+PO Cost combination");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_62_Pagination(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case Suggested Purchase Order Pagination");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_73_RefreshWorkSheet(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Refresh Worksheet");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_74_DeleteWorkSheet(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to delete Worksheet");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_86_verifySavedWorkSheetAvailablePopup(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Saved WorkSheet Available Popup");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_148_searchFilterUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case UI of search filter");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }


    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_164_verifyDiscountMultiplier(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case verify Discount or multiplier option");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

}
