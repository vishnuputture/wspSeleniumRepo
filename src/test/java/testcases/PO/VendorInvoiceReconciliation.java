package testcases.PO;

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
}
