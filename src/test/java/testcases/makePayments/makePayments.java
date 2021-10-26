package testcases.makePayments;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class makePayments extends TestConfigurations {

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreateSalesOrderInvoice(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order invoice for make payments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_NavigateToMakePayments(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order invoice for make payments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_NavigateToSchedulePayments(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order invoice for make payments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_SortByInvoiceNumber(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order invoice for make payments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_SortByInvoiceTotal(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order invoice for make payments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_SortByInvoiceDiscount(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order invoice for make payments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_SortByInvoiceAmtDue(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order invoice for make payments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_SortByInvoiceDate(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order invoice for make payments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_SortByInvoicePaymentDueDate(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order invoice for make payments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_139_clickHere_Link_SchedulePage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Click Here Link On Schedule Page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_140_disabling_Terms_Condition_CheckBox_Enroll(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify error message when disabling Terms and Condition CheckBox");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_141_EnableTermsConditionCheckBoxAndEnroll(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify page title Enable Terms Condition CheckBox And Enroll");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_142_disabling_Terms_Condition_CheckBox_Exit(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify page title when disabling Terms and Condition CheckBox and Exit");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_143_enablingTermsConditionCheckBoxExit(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify page title when Enabling Terms Condition CheckBox Exit");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_145_FieldVerificationStartDateEndDate(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Field Verification of StartDate & EndDate");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_149_CalenderValidation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Validate Calender");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_152_ValidateOutStandingBalance(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Validate OutStandingBalance");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_153_ValidateOtherAmount(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Validate OtherAmount");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_205_ValidateOneTimeSchedule(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Validate OneTimeSchedule");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_208_ValidateOneTimeScheduleDateField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Validate OneTimeSchedule");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
