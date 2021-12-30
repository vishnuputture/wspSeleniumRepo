package testcases;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class Spo extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_50_SuggestedPOUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case UI of Suggested Purchase Order Page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_51_CreateNewWorksheetTemplateUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case UI of Create New Worksheet Template Page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_52_VerifyCancelButtonCreateWSTemp(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("verify Cancel button on create worksheet template");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_54_CreateWorksheet_MCPCVCVNPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case verify the functionality of 'Create Worksheet' button with MC+PC+VC+VN+PO Cost combination");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_62_PaginationSPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case Suggested Purchase Order Pagination");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_70_wsItemScreenUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify the UI of 'Worksheet Items' screen");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_71_PaginationWsItemScreen(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to functionality of pagination,cancel and back buttons on worksheet items screen");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_73_RefreshWorkSheet(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Refresh Worksheet");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_74_DeleteWorkSheet(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to delete Worksheet");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_86_verifySavedWorkSheetAvailablePopup(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Saved WorkSheet Available Popup");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_87_VerifyAddItemsToWS(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify adding additional items to the worksheet");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_88_AddItemToWSUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify UI of 'Add Items To This Worksheet' screen");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_89_PaginationAddItemToWSScreen(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify pagination on 'ADD ITEMS TO THIS WORKSHEET' screen\"");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_148_searchFilterUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case UI of search filter");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_156_CreateWSMultiplier(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Create WS with Multiplier");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_164_verifyDiscountMultiplier(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case verify Discount or multiplier option");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_VerifyWSItemsSearchFilter(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case Verify WS Items Search Filter");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
