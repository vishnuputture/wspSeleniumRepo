package testcases.AccountReceivable;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class AR2WISE extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_430_VerifyUserCanViewNonTradePaymentRecords(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the user can view the Non-Trade Payment records");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_433_VerifyUIofTrnsctnExcptnLstWithoutRecord(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify UI of Transaction Exception list without records");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_434_VerifyUserCanChangeStatus(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify user is able to change to Status");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_436_VerifyUIofTrnsctnExcptnLstWithRecord(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify UI of Transaction Exception list with records");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_437_VerifyCollapseExpandFunctionForTrnsctnExcptnLst(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the maximize and minimize functionality in Transaction Exception list page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_438_VerifyCompanyNameAndNumberField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Field verification for the 'Company Number and Name' field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_450_VerifyViewHyperLink(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the functionality of the View hyperlink");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_451_VerifyResolveRecordFromNTRWindow(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can resolve the record from the popup screen");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_432_VerifyUserCanViewGrossMarginManagerRecords(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can view the Gross Margin Manager records");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_453_VerifyUserCanChangeStatusOfGMMR(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can change status of Gross Margin Manager records");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_456_VerifyViewHyperLinkForGMMR(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the functionality of the View hyperlink for Gross Margin Manager records");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_457_VerifyResolveRecordFromGMMRWindow(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can resolve the record from Gross Margin Manager pop-up");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_439_VerifyUserCanViewNonTradeInvoices(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that that user can view the Non-Trade Invoices");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_459_VerifyUserCanChangeStatusOfNonTradeInvoices(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user is able to change of Non-Trade Invoices");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_461_VerifyViewHyperLinkForNonTradeInvoices(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the functionality of the View hyperlink for Non-Trade Invoices");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_462_VerifyResolveRecordFromNTIWindow(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can resolve the record from Non-Trade Invoices pop-up");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_440_VerifyUserCanViewInventoryAdjustmentRecords(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that that user can view the Inventory Adjustments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_463_VerifyUserCanChangeStatusOfInventoryAdjustments(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user is able to change of Inventory Adjustments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_465_VerifyViewHyperLinkForInventoryAdjustments(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the functionality of the View hyperlink for Inventory Adjustments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_466_VerifyResolveRecordFromInvAdjWindow(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can resolve the record from Inventory Adjustments pop-up");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_441_VerifyUserCanViewMiscInventoryRecords(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that that user can view the Misc. Inventory Adjustments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_467_VerifyUserCanChangeStatusOfMiscInventory(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user is able to change of Misc. Inventory Adjustments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_469_VerifyViewHyperLinkForMiscInventory(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the functionality of the View hyperlink for Misc. Inventory Adjustments");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_470_VerifyResolveRecordFromMiscInventoryWindow(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can resolve the record from Misc. Inventory Adjustments pop-up");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }




}