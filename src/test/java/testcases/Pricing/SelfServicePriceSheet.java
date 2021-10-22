package testcases.Pricing;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class SelfServicePriceSheet extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_379_CreateValidPriceSheetWithoutNETPriceValue(SeleniumTestParameters testParameters) {
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
    public void Tc_363_FillPriceSheetAndSave(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_360_PriceSheetOnHold(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_360_PriceSheetRemoveHold(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_367_PriceSheetMarkAsReady(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_367_ValidatePriceSheetProcessed(SeleniumTestParameters testParameters) {
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

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_399_VerifyListPriceInputField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify List Price field with multiple values");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_408_CreateValidPriceSheetWithNETPriceValue(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify List Price field with multiple values");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
