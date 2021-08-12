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
    public void Tc_142_verify_ProposedMatrixCost_values_and_its_update_on_Item_Master_when_Average_Cost_is_selected(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_143_verify_ProposedMatrixCost_values_and_its_update_on_Item_Master_when_Last_Cost_is_selected(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_168_verify_the_functionality_of_PO_Cost_and_Proposed_Matrix_Cost_box_and_Save_Button(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_154_verify_the_popup_message_when_user_switch_from_Average_Cost_to_Last_Cost(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_145_verify_the_functionality_of_Update_Matrix_Cost_checkbox_and_radio_button(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_140_verify_the_functionality_of_Select_Function_Drop_Down(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_148_verify_the_functionality_of_Exclude_MC_Report_column_Radio_buttons(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_149_verify_the_functionality_of_No_Action_column_Radio_buttons(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_177_verify_the_functionality_of_adding_a_Row_to_Pricing_Matrix(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_179_verify_the_functionality_of_copying_Row_to_Pricing_Matrix(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_172_verify_POCost_values_and_its_update_on_Item_Master_when_Last_Cost_is_selected(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_171_verify_POCost_values_and_its_update_on_Item_Master_when_Average_Cost_is_selected(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_136_verify_the_navigation_UI_of_Matrix_Cost_Update_page(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case validate filter ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
