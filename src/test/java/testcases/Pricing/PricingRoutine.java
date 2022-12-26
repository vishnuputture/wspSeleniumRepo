package testcases.Pricing;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class PricingRoutine extends TestConfigurations {

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_912_verifySPItemCustomerWithSPA(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Special Pricing loaded with an SPA assigned to specific items take precedence over all except Manual Price");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreateSalesOrderWISECustomerWithSPA(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Create SO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_913_SPWithoutSPAOther(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Special Pricing in IM08T with CAFILL not (SPA or MAP) for a specific item.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_verifyItemWithNoCAFILL(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Special Pricing in IM08T with CAFILL not (SPA or MAP) for a specific item.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_915_SPWithoutSPAOther(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("#5 MFPD SPA Special Pricing");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreateSalesOrderWISESPWithoutSPAOther(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Create SO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_911_CustomerWithoutSPAC2(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("#6 MFPD in 3/9");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreateSalesOrderWISECustomerWithoutSPAC2(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Create SO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_917_MFCustomerC3(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("#8 Special Price by MF from 3/9");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreateSalesOrderWISEMFCustomerC3(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Create SO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_919_PDCustomerC4(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("#8 Special Price by MF from 3/9");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreateSalesOrderWISEPDCustomerC4(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Create SO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_923_AllCustomerX1(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("#12 Special Price for an item with *ALL customers");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreateSalesOrderWISEAllCustomerX1(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Create SO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_923_MFPDAllCustomerX2(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("#14 Special Pricing by MFPD for *ALL Customers in 3/9");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreateSalesOrderWISEMFPDAllCustomerX2(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Create SO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_923_MFAllCustomerX3(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("#16 Special Price by MF code for *ALL customers in 3/9");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreateSalesOrderWISEMFAllCustomerX3(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Create SO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_929_PDAllCustomerX4(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("#18 Special Price by PD code for *ALL Customers in 3/9");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreateSalesOrderWISEPDAllCustomerX4(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Create SO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
