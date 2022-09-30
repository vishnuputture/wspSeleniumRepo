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

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_428_verifyMinMaximize(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that search results can be minimized or expanded using '-' and '+' icons");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_374_VerifyCreationOfCreditMemo(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test Case to verify creation of credit memo");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_429_verifyCreditMemo(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user created credit memo, debit memo or invoice ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_375_VerifyCreationOfDebitMemo(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test Case to verify creation of debit memo");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_429_verifyDebitMemo(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user created credit memo, debit memo or invoice");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_480_verifyARGLDetailInquiryUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify UI of A/R G/L detail inquiry page without records");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_481_verifyARGLDetailInquiryRecordsUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify UI of A/R G/L detail inquiry page with records");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_495_verifyMinMaximizeARGL(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the header section fields can be expanded or collapsed using maximize and minimize button");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_489_verifyCompanyNoAndNameARGL(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the field verification for the 'Company Number and Name' field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_490_verifyEditDateField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the user is able to edit the date field in the format MM/YYYY");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_497_verifyDateField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the range of values accepted by 'Date' field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_496_verifyFindARJRecords(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify A/R G/L DETAIL BREAKDOWN display when user clicks on individual Customer records of ARJ Journal Type");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_496_verifyFindNTRRecords(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify A/R G/L DETAIL BREAKDOWN display when user clicks on individual Customer records of NTR Journal Type");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_496_verifyFindCRJRecords(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify A/R G/L DETAIL BREAKDOWN display when user clicks on individual Customer records of CRJ Journal Type");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void CreateSalesOrderCA(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create a sales order");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_492_verifyARRecordsFromWise(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that AR records created in WISE application is reflected in AR2WISE application");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_473_verifySearchFilterItems(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify user can search/filter the results using Customer, Date, Reference, Amount or User");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_477_verifySearchAllField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the field verification for the 'Business Day' dropdown");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_479_verifySortingColumn(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the columns can be sorted in ascending or descending order");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_482_verifyPaginationARGL(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the columns can be sorted in ascending or descending order");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_483_verifyArrowFunctionality(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user is able to move back and froth between the pages by clicking on '>' and '<' pagination arrows");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_505_verifyCashReceiptUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of 'Cash Receipt' pop up");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_verifyPaymentAdjustmentScreen(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of 'Cash Receipt' pop up");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_verifyEntryBankDepositScreen(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of 'Cash Receipt' pop up");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_504_verifyCashReceiptFromWise(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'cash receipts' can be added by adjustment (9/1) screen in WISE");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_503_verifyNTRRecordFromWise(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify NTR record from WISE");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
