package testcases.AccountReceivable;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class AR2WISE extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_416_FixBusinessDaysUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify UI of 'Fix business day' page without records.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_417_FixBusinessDaysRecordUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify UI of 'Fix business day' page with records.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_420_verifyCompanyNoAndName(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the field verification for the 'Company Number and Name' field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_421_verifySearchRecordUsingBusinessDays(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the user is able to search the records using a particular Business Day");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_424_verifySearchAllField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the field verification for the 'Business Day' dropdown");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_415_verifySearchResult(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can search the results using Customer Number, Customer Name, Invoice Number, Business Day, Ship Date, Amount and Gross Margin Amount");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
        public void Tc_423_verifyItemPerPage(SeleniumTestParameters testParameters) {
            testParameters.setCurrentTestDescription("Test to verify that user can select the the number of records he/she can view on the page by selecting the value from 'Item per page' dropdown");
            CoreScript coreScript = new CoreScript(testParameters);
            coreScript.driveTestExecution();
            tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_426_verifyPagination(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify Pagination");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_427_verifySortableColumn(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the user can sort the columns in ascending or descending order");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
