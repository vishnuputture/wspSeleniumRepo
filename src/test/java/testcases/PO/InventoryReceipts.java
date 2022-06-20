package testcases.PO;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class InventoryReceipts extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_902_InventoryReceiptUI(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case UI of Inventory Receipt Page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_903_PurchaseOrderInquiryHyperlink(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Purchase Order Inquiry Hyperlink");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreatePoDirect(SeleniumTestParameters testParameters) {
        System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case to Create Purchase Order");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_904_PurchaseOrderNumberField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Purchase Order Number Field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_897_OrderInUse(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify order In Use popup");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_905_QtyReceivedField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Quantity Received Input Field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_906_changeUnitCost(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify change unit cost");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_907_VerifyUnitCostField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify unit cost field for negative values");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_908_verifyPONumber(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Po Number");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_909_ValidateEnableDisableCostCheckbox(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify functionality of Disable/Enable Cost checkbox");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_910_ValidateRelatedSOHyperlink(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify functionality of Related Sales Order Hyperlink");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_913_ValidatePreviousAndNextButtons(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify functionality of Previous and Next button");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_915_ValidateCancelPOButton(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify functionality of Cancel Purchase Order button");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_924_VerifyFunctionalityOfPartialReceivingPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify functionality of Partial Receiving PO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_925_VerifyPartialReceivingPODoesNotDisplayedInClosedStatus(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Direct PO's partial receiving should not reflect in closed status until full receiving ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_928_VerifyFunctionalityOfOverReceivingPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify the functionality of over receiving a PO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_929_verifyPositionToOrder(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify verify Position To Order");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_932_verifyVendorOrderByField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Vendor Order By Field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_934_VerifyFunctionalityOfRefreshButton(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify the functionality of Refresh button in PURCHASE ORDER INQUIRY");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_935_VerifyFunctionalityOfTypeDropdown(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify the functionality of Type Dropdown in PURCHASE ORDER INQUIRY");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_936_VerifyFunctionalityOfStatusDropdown(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify the functionality of Status Dropdown in PURCHASE ORDER INQUIRY");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_937_VerifyFunctionalityOfHyperlinks(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify the functionality of hyperlinks in PURCHASE ORDER INQUIRY");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreatePo(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality Creation of SO from stock PO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_358_VerifyPOInquiryDetailsUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality PO Inquiry Details UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_375_VerifyOptionAvailablePoInquiryDetails(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality PO Inquiry Details Options column");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_380_VerifyPositionToLine(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality Position To Line");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_385_VerifyItemNumberField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Item Number Field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_390_VerifyNextBackExitItemLedger(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Next Back Exit Item Ledger");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_979_PartialReceivingStockPOItemsInventoryReceiptsScreen(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Partial Receiving of Stock PO Items from inventory receipts screen");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_406_VerifyOpenPODetails(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Open PO Details");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_408_VerifyPoEntryDetailsOpenPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Po Entry Details Open PO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_410_VerifyPoEntryDetailsClosedPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Po Entry Details Closed PO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_407_VerifyClosedPODetails(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Closed PO Details");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_391_VerifyClosedPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Closed PO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_395_VerifyCloseItemManyTimes(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Close Item Many Times");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_400_VerifyMoreInfoLink(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify More Info Link");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_378_VerifyItemLedgerUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Item Ledger UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_405_VerifyItemNumberFieldItemLedger(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Item Number Field Item Ledger");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_411_VerifyCustomerNumberFieldItemLedger(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Customer Number Field Item Ledger");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_412_VerifySourceFieldItemLedger(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Source Field Item Ledger");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_413_VerifyCalendarFieldItemLedger(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Calendar Field Item Ledger");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void TC_427_VerifyPOHasNoItemInPoDetails(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify PO Has No Item In Po Details");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}