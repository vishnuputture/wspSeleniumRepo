package testcases.PO;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class PoConversionFactor extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_242_verifyItemNumberOnPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Item Number On PO Page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_245_verifyEditedItemNumberOnPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify edited Item Number On PO Page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_246_verifyExtendListPriceCalculation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Extend List Price Calculation");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_247_verifyExtendPoCostCalculation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Extend PO Cost Calculation");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_248_verifyExtendLastCostCalculation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Extend Last Cost Calculation");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_249_EditItemListPriceAndVerifyInPODetailsPage(SeleniumTestParameters testParameters) {
        System.out.println("Test case : " + testParameters.getCurrentTestcase() + " Thread " + Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case to Edit item number List Price from ITEM MASTER and verify in PO details page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_250_EditItemPOPriceAndVerifyInPODetailsPage(SeleniumTestParameters testParameters) {
        System.out.println("Test case : " + testParameters.getCurrentTestcase() + " Thread " + Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case to Edit item number PO Price from ITEM MASTER and verify in PO details page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_251_VerifyErrorWhileAddingItemToDirectPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Error message while adding line item to Direct Purchase Orders");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_252_VerifyItemDetailsFromBuyersInquiryToPOEntry(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Item details from Buyer's Inquiry to PO Entry");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_253_VerifyItemDetailsAfterProcessingPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Item details after Processing PO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_256_VerifyLineItemAfterProcessingPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Line Item after Processing PO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_257_VerifyWarningMsgWhenAddingLineItemToDirectPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify WARNING message while adding line item to Direct POs");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_258_VerifyAddingDirectPOToRelatedSO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to the functionality of adding Direct POs to  Related SO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
