package testcases.Pricing;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;


public class ShipmentInquiry extends TestConfigurations {

    @Test(dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void ShipmentInquiry(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

}
