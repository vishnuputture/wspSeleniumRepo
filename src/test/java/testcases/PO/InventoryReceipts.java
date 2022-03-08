package testcases.PO;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class InventoryReceipts extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_902_InventoryReceiptUI(SeleniumTestParameters testParameters) {
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
    public void Tc_929_verifyPositionToOrder(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify verify Position To Order");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}