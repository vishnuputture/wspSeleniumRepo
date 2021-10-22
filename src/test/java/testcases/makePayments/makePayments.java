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
}
