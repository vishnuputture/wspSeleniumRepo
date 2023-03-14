package testcases.WiseSmokeTest;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;
import supportLibraries.Utility_Functions;

public class WiseSmokeTest extends TestConfigurations {
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_WiseSmokeTest_ACTIVE(SeleniumTestParameters testParameters) throws InterruptedException {
        testParameters.setCurrentTestDescription("Smoke Test for the release");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_WiseSmokeTest_PARTIAL(SeleniumTestParameters testParameters) throws InterruptedException {
        testParameters.setCurrentTestDescription("Smoke Test for the release");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_WiseSmokeTest_INACTIVE(SeleniumTestParameters testParameters) throws InterruptedException {
        testParameters.setCurrentTestDescription("Smoke Test for the release");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}