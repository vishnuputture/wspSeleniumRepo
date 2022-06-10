package testcases.Inventory;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class SalesPerson extends TestConfigurations {

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_521_SalesPersonInquiryUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of SALESPERSON INQUIRY page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}