package testcases.Purchasing;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class VendorInvoiceReconciliation extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_433_vendorInvoiceRecUI(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case UI of Vendor Invoice Reconciliation Page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_435_verifyReceiverDocFields(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case verify Receiver Doc Field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_437_verifyReceiverDocBrowse(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case verify Receiver Doc Browse");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_440_verifySearchReceiverDocNo(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case verify Search Receiver Doc No");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_441_verifySearchRecDocVdrName(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case verify Search Rec Doc Vdr Name");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_443_verifySearchReferenceDocNo(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case verify Search Reference Document Number");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_444_verifySearchVdrInvoice(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case verify Search Vdr Invoice");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_445_verifySearchTotalCost(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case verify Search Total Cost");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_447_verifyRecDocPositionToPO(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test to verify that user should be able to pull the receiver document numbers using the Position to  PO field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_450_verifyRecDateFilter(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test to verify that user should be able to see the list of receiver document numbers, using the received date as filter");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_452_verifyPOMore(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test to verify that user should be able to filter the receiver document numbers using PO number");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreatePoDirect(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality Direct Po");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CompleteReceivingStockPOItemsInventoryReceiptsScreen(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to Complete Receiving Stock PO Items Inventory Receipts Screen");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_453_verifyDueDateField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user should be able to validate that \"Due Date\" is greater than or equal to received date");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_454_VerifyGMMgrAmount(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case to verify GM Mgr Amount");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_455_VerifyGrossMarginManagerPage(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case to verify Gross Margin Manager page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_459_VerifyFunctionalityOfF6KeyInReceiverDocumentBrowsePage(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case to verify functionality of F6 key in Receiver Document Browse page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_461_VerifyFunctionalityOfF12KeyInVendorInvoiceReconciliationPage(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case to verify functionality of F12 on Vendor Invoice Reconciliation page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_464_VerifyOptionsAvailableOnDetailPage(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case to verify options available on Detail page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_469_VerifyFreightAmountInVIRPage(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case to verify the freight amount in vendor invoice reconciliation page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_481_VerifyCreationOfDebitMemoForQuantityDifference(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case to verify creation of debit memo for the quantity difference");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_482_VerifyCreationOfDebitMemoForCostDifference(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case to verify creation of debit memo for the cost difference");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_486_VerifyCreationOfDebitMemoForItemsNotOnPO(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case to verify creation of debit memo for items not on PO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_487_VerifySpecialNotesOnDebitMemoProcessPage(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case to verify [Special Notes] on Debit Memo Process page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}