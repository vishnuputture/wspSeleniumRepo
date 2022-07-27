package testcases.Inventory;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class SalesPerson extends TestConfigurations {

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_521_SalesPersonInquiryUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of SALESPERSON INQUIRY page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_518_NavigateNextPreviousScreen(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user should be able to navigate to previous and next items using \"<Previous\" and \"Next>\" hyperlinks or Pgup and Pgdwn keys");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_519_verifyPricingColumn(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the functionality of the Pricing Column");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_533_verifySearchItem(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the functionality of Search for Item input field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_528_verifySearchItemField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the field verification for the Search for Item input field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_526_verifyPricingNoRow(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to Verify the \"No Row\" text below the pricing columns selected Item does not have a Pricing Matrix row defined");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_ValidateAddRowPricingMatrix(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to test that user is able to add a row in pricing matrix page ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_527_verifyOnlyRowPricing(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify selected item is in a Pricing Matrix row, but no column/customer is specified, so only the Row is displayed");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_529_verifyNoMultiplierPricing(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify selected item is in a Pricing Matrix row, but no column/customer is specified, no multiplier is defined in the Pricing Matrix");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_544_verifyMultiplierPricing(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify selected item is in a Pricing Matrix row, but no column/customer is specified, multiplier is defined in the Pricing Matrix");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_verifyActionLinks(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify selected item is in a Pricing Matrix row, but no column/customer is specified, multiplier is defined in the Pricing Matrix");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_534_verifyPricingColumnField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the range of values accepted by 'Pricing Column' field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_530_verifyManuallyEnteredMultiplierValue(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the manually entered multiplier value is reset when the item numbers are changed");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_535_verifyExtTotalCalculation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that extended total or 'Ext Total' is the amount calculated by multiplying the number of quantities of an item with its price");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_542_verifyResetMultiplierValue(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Multiplier' value is reset when pricing column is changed or calculation is reset by clearing out sales price and multiplier fields");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_543_verifyToggleCostList(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user should be able to toggle between 'Cost Up' and 'List Down' in 'Calculations' section using shift + F9");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_546_verifyGrossMarginCalculation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the GM% Calculation");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_547_verifyPriceCalculation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the Price Calculation");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_525_verifyMultiplierField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user should be able to verify the range of values accepted by 'Multiplier' field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_548_verifyGrossMarginField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user should be able to verify the range of values accepted by 'Gross Margin' field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_505_verifyActionTab(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the navigation to below pages from ACTIONS tab");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_541_VerifyOptionsAvailableUnderQUICKVIEW(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the options available under QUICK VIEW");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_511_VerifyItemNotesInSalespersonInquiryPage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify Item Notes in Salesperson Inquiry Page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_514_FieldVerificationForItemNotes(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify Field verification for Item Notes");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_520_VerifyFieldsAvailableInInventoryTab(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the fields available in 'Inventory' tab");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_522_VerifyFieldsAvailableInItemBinDetailsTab(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the fields available in 'Inventory' tab");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_508_VerifyAlternateCustomerNumberInSalesPersonInquiry(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify alternative customer number in Salesperson Inquiry page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_509_VerifyCustomerSearchUsingInvalidAlternateCustomerNumber(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify alternative customer number in Salesperson Inquiry page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_510_VerifyAddEditCustomerNotes(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify alternative customer number in Salesperson Inquiry page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_517_ViewItemDetailsUsingItemNoAndCustNo(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify user should be able to view the item details using the combination of item number and customer number in salesperson inquiry page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_532_VerifyFunctionalityOfCustomerInputField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify Functionality of Customer input field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}