package testcases.MobileWarehousing;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class BinMaintenance extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_748_ItemBinMaintenanceUI(SeleniumTestParameters testParameters) {
        System.out.println("Test case : " + testParameters.getCurrentTestcase() + " Thread " + Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case to verify UI of Item Bin Maintenance");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_749_MainMenuOptions(SeleniumTestParameters testParameters) {
        System.out.println("Test case : " + testParameters.getCurrentTestcase() + " Thread " + Thread.currentThread().getId());
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
    public void Tc_787_verifyOnHand(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify On Hand Filter");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_790_verifyClearAllButton(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify the Clear all filter button");
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
    public void Tc_751_verifyUIOfBinMaintenanceWhenNoDataPresent(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify UI of Bin Maintenance Tab when no data present");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_754_verifyUIOfBinMaintenanceWhenBinsAreAvailable(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify UI of Bin Maintenance Tab when Bins are available");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_755_verifyFunctionalityOfRowCountOfBinMaintenance(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Functionality of Row Count of Bin Maintenance Tab when Bins are available");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_756_verifyFunctionalityOfPaginationForBinMaintenanceTable(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Pagination for Bin Maintenance table when Bins are available");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_757_verifyFunctionalityOfSelectAllBinsForBinMaintenanceTable(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Select All Bins for Bin Maintenance table when Bins are available");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_766_verifyUIOfCreateNewBinPopup(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify UI of Create New Bin popup");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_767_verifyCreateNewBinWithConditionGood(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify creation of New Bin with Condition as Good");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_768_verifyCreateNewBinWithConditionDamaged(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify creation of New Bin with Condition as Damaged");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_769_verifyCreateNewBinWithConditionDefective(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify creation of New Bin with Condition as Defective");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_770_verifyCreateDuplicateBinErrorAndFieldVerification(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Duplicate Bin error and field verification");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_780_verifyEditBinsButtonFunctionality(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify functionality of Edit Bin(s) button");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_782_verifyEditBinsButtonFunctionalityForMultipleBins(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify functionality of Edit Bin(s) button when Multiple records are selected");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_784_verifyBinWithItemsCannotBeDeleted(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify that Bins containing Item No cannot be deleted");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_785_verifyBinWithoutItemsCanBeDeleted(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify that Bins without Items can be deleted");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_786_verifyPrintBinLabelsFunctionality(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify the functionality of Print Labels for Bin Labels");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_789_verifyPrintBinItemsLabelFunctionality(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify the functionality of Print Labels for items in Bin");
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
    public void Tc_837_verifyDuplicateAddBin(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify ADD Duplicate Bin");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_857_ItemBinLedgerPageUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Item Bin Ledger Page UI");
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
    public void Tc_860_verifyApplyClearFilter(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Apply Clear Filter");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_861_verifyItemNumberField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Item Number Field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_862_verifyNegItemNumberField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify NegItem Number Field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_863_verifyBinLocation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Bin Location");
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
    public void Tc_865_verifyDuplicateZone(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify duplicate Zone");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_866_verifyEditZone(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Edit Zone");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_868_verifyPickSequence(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to verify Pick Sequence");
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
