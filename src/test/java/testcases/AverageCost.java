package testcases;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class AverageCost extends TestConfigurations {

    @Test(groups = {}, dataProvider = "API", dataProviderClass = TestConfigurations.class)
    public void tc_AddItemData(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to add Item in Item Master from DB");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}