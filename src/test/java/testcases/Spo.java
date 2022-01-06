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
    public void Tc_62_PaginationSPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case Suggested Purchase Order Pagination");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_66_CreateWS_MFPDVNPODiscount(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case verify the functionality of 'Create Worksheet' button with MC+PC+VC+VN+List Price combination");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_67_CreateWS_MFPDVNListPriceDiscount(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case verify the functionality of 'Create Worksheet' button with MC+PC+VC+VN+PO Cost combination");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_68_CreateWS_MFPDVNLastCostDiscount(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case verify the functionality of 'Create Worksheet' button with MC+PC+VC+VN+PO Cost combination");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_VerifyDuplicateWS(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Duplicate Worksheet can not create");
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
        testParameters.setCurrentTestDescription("Test case to Verify pagination on 'ADD ITEMS TO THIS WORKSHEET' screen");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_102_VerifyEditWSTempUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Edit Worksheet Template UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_106_WSCalculationSection(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Worksheet Calculation Section");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_110_PaginationWSDetailsPage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Pagination WorkSheet Details Page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_116_VerifyAssignedUserCanBeChanged(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Assigned User Can Be Changed");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_117_VerifySalesOptionEditable(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Sales Option Editable");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_118_DayToRunSection(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Day To Run Section Editable");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_120_EditWSTrashIcon(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Delete Worksheet");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_121_SaveWsButton(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Save Button");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_129_VerifyOrderWeightTotal(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Order total and Total Weight");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_131_VerifyAddDuplicateItemToWS(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Duplicate item can not added to Worksheet");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_133_VerifySaveWorksheetBtn(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Save Worksheet Button");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_138_CalculationMethodOptions(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify the options available in \"Calculation Method\" dropdown box");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_139_VerifyConvertToPOOrderQuantitiesZero(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify cannot convert to PO when all items order quantities is zero.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_140_VerifyTotalOrderExceedForConvertPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Total Order Exceed For Convert PO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_141_VerifyOrderMinimum(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Order Minimum");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_144_VerifyUnitTotalWeight(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Unit weight and Total Weight");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_145_VerifyTotalCost(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify the Dependency of \"Total Cost\"");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_146_VerifyNotAStandardPackage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify \"NOT A STANDARD PACKAGE QTY\" Popup");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_VerifyTrashIconEditWSTemp(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Delete icon ");
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
    public void Tc_175_CreateWS_MFPDVNListPriceMultiplier(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case verify the functionality of 'Create Worksheet' button with MC+PC+VC+VN+List Price combination");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_176_CreateWS_MFPDVNPOMultiplier(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case verify the functionality of 'Create Worksheet' button with MC+PC+VC+VN+PO Cost combination");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_177_CreateWS_MFPDVNLastCostMultiplier(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case verify the functionality of 'Create Worksheet' button with MC+PC+VC+VN+PO Cost combination");
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
