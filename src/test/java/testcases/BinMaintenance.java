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

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_812_verifySecondaryOptionsBinType(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Secondary Options BinType");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_813_verifyUpdateBinType(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Update BinType message");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_814_verifyBinItemDeleteIcon(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Bin Item Delete Icon");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_815_verifyBinItemEditIcon(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Edit Item details");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_819_verifyBinMinMax(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Bin min and bin max");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_824_verifyEditBinPopUp(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Edit bin popup");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_827_verifyCreateBinButton(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Create bin button");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_831_verifyCreateDuplicateBin(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Create Duplicate bin");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_836_verifyAddBinButton(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify ADD Bin Button");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_836_verifyDuplicateAddBin(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify ADD Duplicate Bin");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_858_verifyZoneUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Zone UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_864_verifyNewZone(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify New Zone Popup");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_869_verifyDeleteZOne(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify delete Zone");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
