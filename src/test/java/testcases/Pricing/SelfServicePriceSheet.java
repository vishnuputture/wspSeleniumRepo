package testcases.Pricing;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class SelfServicePriceSheet extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_188_NavigateSelfServicePriceSheet(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_189_NavigatePriceSheetDetails(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_190_FillPriceSheetAndSave(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_191_PriceSheetOnHold(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_192_PriceSheetRemoveHold(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_193_PriceSheetMarkAsReady(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_194_ValidatePriceSheetProcessed(SeleniumTestParameters testParameters) {
	 testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
     CoreScript coreScript = new CoreScript(testParameters);
     coreScript.driveTestExecution();
     tearDownTestRunner(testParameters, coreScript);
 }
    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_195_ValidatePriceSheetItemListCost(SeleniumTestParameters testParameters) {
	 testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
     CoreScript coreScript = new CoreScript(testParameters);
     coreScript.driveTestExecution();
     tearDownTestRunner(testParameters, coreScript);
 }
    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_196_ValidatePriceSheetItemMatrixCost(SeleniumTestParameters testParameters) {
	 testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
     CoreScript coreScript = new CoreScript(testParameters);
     coreScript.driveTestExecution();
     tearDownTestRunner(testParameters, coreScript);
 }
    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_197_ValidatePriceSheetItemPoCost(SeleniumTestParameters testParameters) {
	 testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
     CoreScript coreScript = new CoreScript(testParameters);
     coreScript.driveTestExecution();
     tearDownTestRunner(testParameters, coreScript);
 }
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_344_VerifySearchByName(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify SearchByName");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_344_VerifyFilterByManufacturer(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify FilterByManufacturer");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_344_VerifyFilterByStatus(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify FilterByStatus");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    
   
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_344_VerifyFilterEffectiveDate(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify PriceSheetRecords");

        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
