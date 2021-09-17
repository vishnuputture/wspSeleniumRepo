package testcases.Pricing;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class SPA extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_161_VerifySPAScreenHeader(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_166_VerifySPAScreenInformation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Screen Information");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_311_VerifyAddCustomersToGroupName(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify added customer to GroupName");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_315_VerifyEditCustomerGroup(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Edit customer");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_316_VerifyDisplayCustomer(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify added customer to GroupName");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_317_VerifyDeleteGroupName(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify delete group name");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_318_VerifyCustomerGroupSorting(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Customer group sorting");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_319_VerifyDeleteMultipleGroupName(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify delete Multiple group name");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
