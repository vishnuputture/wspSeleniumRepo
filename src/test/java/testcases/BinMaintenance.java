package testcases;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class BinMaintenance extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_748_ItemBinMaintenanceUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify UI of Item Bin Maintenance");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_749_MainMenuOptions(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify UI of Main Menu options");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_753_verifyApplyClearAllFilter(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Apply Filter Clear All Filter");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_758_verifyFilterCheckBox(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Apply Filter CheckBox");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_759_verifyContainsCheckBox(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Filter Contains CheckBox");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_761_verifySuggestedMatchesPopUp(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Suggested Matches PopUp Message");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_763_verifyItemNoNegValue(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify the negative Scenarios for Item Number input field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_764_verifyItemDescriptionFilter(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify the functionality of Item Description field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_765_verifyItemDescriptionField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify the functionality of Item Description field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_750_VerifyUIItemNumbersAreAvailable(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify the UI of the page when Item Numbers are available");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_791_verifyPageCountItemMaintenance(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify the functionality of Pagination");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_793_verifyPaginationItemMaintenance(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify the functionality of Page count");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_795_verifySelectAllCheckBox(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify the functionality of Select All Items Checkbox");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_801_verifyLabelButton(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify the functionality of Labels button");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_805_verifyItemDetailsMaster(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify the particular Item Number's Details");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_808_verifyItemDetailsUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify the particular Item Number's Details");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_809_verifyPaginationItemDetails(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify the functionality of Pagination");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_810_verifyPageCountItemDetails(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify the functionality of Page count");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_811_verifySelectDeselectItem(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Select/Deselect Items");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
