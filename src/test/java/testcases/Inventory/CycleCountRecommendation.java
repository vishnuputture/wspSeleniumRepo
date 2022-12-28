package testcases.Inventory;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class CycleCountRecommendation extends TestConfigurations {

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_692_verifyADDITEMSUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the \"ADD ITEMS\" popup and the \"select an Options to Add Items\" dropdown");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_694_verifyReAssignUserUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the \"RE-ASSIGN USERS\" popup");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_690_verifyCycleCountRecAuditorUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the \"CYCLE COUNT RECOMMENDATION\" (Auditor) page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_693_verifyCycleCountRecLocalUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the \"CYCLE COUNT RECOMMENDATION\" (Auditor) page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_691_verifySearchFilterUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the Search Filters");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_695_verifyPrintCycleCountSheetUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the \"PRINT CYCLE COUNT SHEET\" popup");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_696_verifyAutoAssignUserUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the \"AUTO ASSIGN USERS\" popup");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_697_verifyCycleCountAuditHistoryUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the \"CYCLE COUNT AUDIT HISTORY\" page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_731_verifySortingColLocalCompany(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the user can sort the columns in ascending or descending order");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_742_verifyPageCountLocalCompany(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can view the number of records on a page by selecting appropriate option from 'Show' field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_743_verifyPaginationLocalCompany(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user should be able to navigate through the pages using the navigation icons.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_745_verifyGraphPercentage(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the functionality of the Percent Complete graph");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_747_verifyChangesLocalCompWithAuditor(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that any changes made in 'local company' view should not effect the view in 'auditor'. ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_730_verifyEditAssignedUser(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user is able to edit \"Assigned User\" for the recommendation displayed");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_733_verifyCycleCountEntry(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the \"Cycle Count Entry\" hyperlink present on the menu option");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_746_verifyUnassignedUser(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that User can Un-Assign an assigned user for the recommendation");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_741_verifyRemoveCounted(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify tRemove records");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_800_verifyCountAudit(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that when the user selects ' Count Audit' from the menu option for an item, then the user should be navigated to 'Count Audit History Page' ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_699_verifyItemLedger(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that when the user selects ' Item Ledger' from the menu option for an item, then the user should be navigated to 'Item Ledger' page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_700_verifyItemBinLedger(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that when the user selects ' Item Bin Ledger' from the menu option for an item, then the user should be navigated to 'Item Ledger' page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_708_verifyOpenOrder(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that when the user selects 'Open Order' from the menu option for an item, then the user should be navigated to 'Item Ledger' page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_708_verifyPrintLabel(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the functionality of Print Labels option from the menu");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_744_verifyExportButton(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the 'Last Count User' and 'Last Count' displays the data when the item present in multiple bins, and any one bin has been counted.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_714_verifyAddItemBinLocationRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can add items to recommendation by using Bin Location Range");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_715_verifyAddItemUsingQuantity(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can add items to recommendation by using Quantity");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_716_verifyAddItemUsingMFPDVN(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can add items to recommendation by using MF PD VN Codes");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_717_verifyAddItemUsingZone(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can add items to recommendation by using Zone");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_704_verifyUserReassign(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can Reassign the user name to the record");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_705_verifyRemoveAssignedUser(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that assigned user can be removed from the \"RE-ASSIGN USERS\" popup and also changes can be reverted back");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_707_verifyCountSheet(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the functionality of \"Count Sheets\" button");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_735_verifyAutoAssignAcrossBinZone(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can auto assign the user by using \"Across all bin locations and zones\" option");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_737_verifyAutoAssignByZone(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can auto assign user by using 'By Zone' option");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_738_verifyAutoBinLocation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can auto assign user by using 'By Bin Location Range' option");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_738_verifyAutoAssignReasonForRec(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can auto assign user by using 'By Reason for Recommendation' option");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_740_verifyAutoAssignMFPDVN(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can auto assign user by using 'By MF/PD/VN' option");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_712_verifyZoneFilter(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify user is able to filter the records by zone.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_718_verifyBinLocationFilter(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify user is able to filter the records by bin location start and end range.");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_719_verifyMFPDVNFilter(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify user able to filter the records entering valid MF PD VN code");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
