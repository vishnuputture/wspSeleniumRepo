package testcases.Pricing;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class SelfServicePriceSheet extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_161_LoginToSelfServicePriceSheet(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
