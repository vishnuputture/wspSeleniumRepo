package testcases.SalesQuotes;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class WorkWithSalesQuote extends TestConfigurations {

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void SalesQuotesToPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Search Item in ItemMaster");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void CheckInventoryReceipts(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Search Item in ItemMaster");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }


    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void InquiryOrdersByCustomer(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Search Item in ItemMaster");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void OrderShippingConfirmation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Search Item in ItemMaster");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void PrintReportInvoices(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Search Item in ItemMaster");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void FinalInquiryOrderStatus(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Search Item in ItemMaster");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}