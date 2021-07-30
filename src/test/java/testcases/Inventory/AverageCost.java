package testcases.Inventory;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class AverageCost extends TestConfigurations {

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_21_SearchItemData(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Search Item in ItemMaster");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"DB"}, dataProvider = "DB", dataProviderClass = TestConfigurations.class)
    public void Tc_22_UpdateItemData(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to update item In ItemMaster");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"DB"}, dataProvider = "DB", dataProviderClass = TestConfigurations.class)
    public void Tc_23_UpdateItemQuantity(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to update on hand quantity");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"DB"}, dataProvider = "DB", dataProviderClass = TestConfigurations.class)
    public void Tc_24_UpdateItemAVGCost(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to update average cost");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI","DB"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_25_ValidateItemCreation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to create average cost ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_26_ValidateItemDeletion(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to delete average cost ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_27_ValidateItemUpdate(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to delete average cost ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_28_ValidateReceiveCorrection(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to delete average cost ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_31_ValidateMultipleReceiveCorrection(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to delete average cost ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_29_ValidateCostAdjustmentProcess(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to delete average cost ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_30_ValidateCostAdjustmentLedger(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to delete average cost ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_32_ValidateMultipleCostAdjustment(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to delete average cost ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}