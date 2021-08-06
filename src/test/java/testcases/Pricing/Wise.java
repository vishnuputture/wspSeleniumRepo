package testcases.Pricing;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.Browser;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class Wise extends TestConfigurations {
    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_17_NavigateToAddSpecialScreen(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to test that user is able to navigate to add special price page ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_19_ValidateCustomerNumber(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to test user is able to create special price record using valid customer number ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_20_ValidateItemNumber(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to test user is able to create special price record using valid item number");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_22_ValidateDBafterAdd(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to validate user is able to retrieve and display special price by querying db from application ");
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
    public void Tc_API_updatePricing(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Update Pricing Reponse  ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_5_ValidatePositiontoCustomerNumber_Beginning(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_10_ValidatePositiontoCustomerNumber_End(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_9_ValidatePositiontoCustomerNumber_Middle(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_11_ValidatePositiontoItemNumber_Beginning(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_13_ValidatePositiontoItemNumber_Middle(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_23_ValidateDBAfterDelete(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test Case To Validate DB after Delete ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_2_ValidateNoSpecialPriceMessage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_3_ValidateDefaultFiltersSplPricing(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate default filter value ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_12_ValidatePositiontoItemNumber_End(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_24_InsertPAPRecordToDelete(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_21_E2E(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_137_verify_Item_number_by_navigating_to_Item_Ledger(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_151_verify_updating_POCost_providing_Input_values(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_152_verify_updating_ProposedMatrixCost_providing_Input_values(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_155_verify_updated_Field_in_Item_Master_Page(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_154_verify_Warning_message_Switching_LastCost_to_AverageCost(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
