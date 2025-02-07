package testcases.Marketplace;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class MarketplaceAPI extends TestConfigurations{
    @Test(groups = {}, dataProvider = "API", dataProviderClass = TestConfigurations.class)
    public void Tc_API_validateWiseAccount(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to validate API Reponse  ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {}, dataProvider = "API", dataProviderClass = TestConfigurations.class)
    public void Tc_API_validateLocalCompany(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to validate API Reponse  ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {}, dataProvider = "API", dataProviderClass = TestConfigurations.class)
    public void Tc_API_validateInventory(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to validate API Reponse  ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {}, dataProvider = "API", dataProviderClass = TestConfigurations.class)
    public void Tc_API_validatePricing(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to validate API Reponse  ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {}, dataProvider = "API", dataProviderClass = TestConfigurations.class)
    public void Tc_API_validateTax(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to validate API Reponse  ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {}, dataProvider = "API", dataProviderClass = TestConfigurations.class)
    public void Tc_API_validateOrders(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to validate API Reponse  ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
