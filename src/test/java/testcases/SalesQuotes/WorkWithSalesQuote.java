package testcases.SalesQuotes;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class WorkWithSalesQuote extends TestConfigurations {

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void SalesQuotesSmoke(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Search Item in ItemMaster");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}