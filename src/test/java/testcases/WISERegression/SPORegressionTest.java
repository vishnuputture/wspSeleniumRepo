package testcases.WISERegression;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class SPORegressionTest extends TestConfigurations{
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_SPO_Regression_Prerequisites(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Smoke Test for the release");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_SPO_LandingPage_Pagination_Regression_Test(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Smoke Test for the release");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_SPO_Sales_Option_Regression_Test(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Smoke Test for the release");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_SPO_Days_To_Refresh_Regression_Test(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Smoke Test for the release");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
