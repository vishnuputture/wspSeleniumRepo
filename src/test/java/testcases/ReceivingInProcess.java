package testcases;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class ReceivingInProcess extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_957_SearchFilterUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify UI of Search Filter");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_958_SearchFilterFunctionality(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Search Filter Functionality");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_956_clearAllApplyFilterFunctionality(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify clear All Apply Filter Functionality");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_946_verifyPurchaseOrderInputField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Purchase Order Input Field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_947_verifyUserIdInputField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify User Id Input Field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_948_verifyVendorInputField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Vendor Input Field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_945_receivingInProcessUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Receiving In Process UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_959_sortingFunctionality(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Sorting Functionality");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_960_SearchFilterRemainingUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Search Filter Remaining Page UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_961_SearchFilterRemainFunctionality(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Search Filter Remaining Page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_962_clearAllApplyFilterFunctionality(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify clear All Apply Filter Functionality");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_949_verifyItemNoInputField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Item No Input Field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_950_verifyItemDescriptionInputField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Item Description Input Field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_951_verifyRemainingUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify Remaining Page UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_965_ReceiveItemsStockPORFGun(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("verify Receive Items Stock PO RF Gun");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
