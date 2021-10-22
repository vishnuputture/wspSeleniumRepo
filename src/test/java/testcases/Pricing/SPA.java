package testcases.Pricing;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class SPA extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_161_VerifySPAScreenHeader(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Title");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_166_VerifySPAScreenInformation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Special Price Allowance Screen Information");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_323_VerifySPAHideShowExpiredRecords(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify user is able to hide and show expired records");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }


    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_311_VerifyAddCustomersToGroupName(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify added customer to GroupName");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_322_VerifySPAAddRecords(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify user is able to add records");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
}
    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_337_VerifySPASearchRecords(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify user is able to search records");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
}
    
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_326_VerifySPARecordDeletion(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify user is able to delete records");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
}

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_315_VerifyEditCustomerGroup(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Edit customer");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_316_VerifyDisplayCustomer(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify added customer to GroupName");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_317_VerifyDeleteGroupName(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify delete group name");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_318_VerifyCustomerGroupSorting(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Customer group sorting");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_319_VerifyDeleteMultipleGroupName(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify delete Multiple group name");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_324_VerifySortingContract(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Sort Contract record");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_325_VerifyEditContract(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Edit Contract/Job Name");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_328_VerifyCopyContract(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Copy Contract/Job Name");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_336_VerifyLoadPriceSheet(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Load Special Price");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_334_VerifyUpdateCost(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Load Special Price");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_332_VerifyDisplayContractDetails(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Display Contract Details");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_327_VerifyAssignMFPDCode(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Assign MF/PD Code");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_331_VerifyAssignIndividualItem(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Assign Individual Item");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

}
