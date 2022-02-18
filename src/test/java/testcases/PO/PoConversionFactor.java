package testcases.PO;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class PoConversionFactor extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_242_verifyItemNumberOnPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Item Number On PO Page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_245_verifyEditedItemNumberOnPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify edited Item Number On PO Page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_246_verifyExtendListPriceCalculation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Extend List Price Calculation");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_247_verifyExtendPoCostCalculation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Extend PO Cost Calculation");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
