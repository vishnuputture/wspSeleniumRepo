package testcases.WiseSmokeTest;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class WiseSmokeTest extends TestConfigurations {
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_WiseSmokeTest(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Smoke Test for the release");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }


}