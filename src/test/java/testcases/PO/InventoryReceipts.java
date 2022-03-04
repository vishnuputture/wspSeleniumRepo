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
        testParameters.setCurrentTestDescription("Test case to functionality of Disable/Enable Cost checkbox");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}