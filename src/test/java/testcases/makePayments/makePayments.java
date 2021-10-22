package testcases.makePayments;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class makePayments extends TestConfigurations {

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreateSalesOrderInvoice(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order invoice for make payments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_NavigateToMakePayments(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order invoice for make payments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_NavigateToSchedulePayments(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order invoice for make payments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_SortByInvoiceNumber(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order invoice for make payments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_SortByInvoiceTotal(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order invoice for make payments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_139_clickHere_Link_SchedulePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Click Here Link On Schedule Page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_140_disabling_Terms_Condition_CheckBox_Enroll(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify error message when disabling Terms and Condition CheckBox");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_142_disabling_Terms_Condition_CheckBox_Exit(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify page title when disabling Terms and Condition CheckBox and Exit");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
