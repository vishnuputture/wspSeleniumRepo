package testcases.Inventory;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class CycleCountRecommendation extends TestConfigurations {

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_692_verifyADDITEMSUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the \"ADD ITEMS\" popup and the \"select an Options to Add Items\" dropdown");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_694_verifyReAssignUserUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the \"RE-ASSIGN USERS\" popup");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_690_verifyCycleCountRecAuditorUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the \"CYCLE COUNT RECOMMENDATION\" (Auditor) page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_693_verifyCycleCountRecLocalUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the \"CYCLE COUNT RECOMMENDATION\" (Auditor) page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_691_verifySearchFilterUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the Search Filters");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_695_verifyPrintCycleCountSheetUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the \"PRINT CYCLE COUNT SHEET\" popup");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_696_verifyAutoAssignUserUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the \"AUTO ASSIGN USERS\" popup");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_697_verifyCycleCountAuditHistoryUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the \"CYCLE COUNT AUDIT HISTORY\" page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
