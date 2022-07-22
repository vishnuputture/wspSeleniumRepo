package testcases.Inventory;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class ItemMaster extends TestConfigurations {

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_557_ItemMasterUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the UI of the Item Master page");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_597_VerifySearchItemField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user should be able to search for the item");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_595_VerifyPreviousNextItem(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the functionality of Previous Item and Next Item buttons");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_601_VerifySearchInvalidItem(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the field verification (Negative scenario) of the Search for Item input field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_603_VerifyItemDescription(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the user should not be able to change the Line1 Description and can change the line 2 Description");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_611_VerifyChangeMFPDVN(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the user is able to change the Manufacturer, Product and Vendor codes");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_615_VerifyHistoryMonthsToRetain(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the user can change the 'History Months to Retain'");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_606_VerifyDescDesc2(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the field verification of Description and Description 2 fields");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_612_VerifyNegChangeMFPDVN(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the field verification (Negative scenario) for the Manufacturer, Product and Vendor fields");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_614_VerifyItemType(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the user will not be able to change the 'Item Type'");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_578_VerifyUOMSearchSelling(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user should be able to enter 'Unit of measure' in the below methods");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_616_VerifyStdPkgQtyPoundStdPkgSelling(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the user can change the 'UOM', 'Std. Package Quantity' and 'Pounds per Std Pkg' fields");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_620_VerifyUOMStdPkgQtyPndStdPkgSelling(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the field verification for the 'UOM', 'Std. Package Quantity' and 'Pounds per Std Pkg' fields");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_579_VerifyUOMSearchPurchasing(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user should be able to enter 'Unit of measure' in the below methods");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_556_VerifyStdPkgQtyPoundStdPkgPurchasing(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the user can change the 'UOM', 'Std. Package Quantity' and 'Pounds per Std Pkg' fields");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_558_VerifyUOMStdPkgQtyPndStdPkgPurchasing(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the field verification for the 'UOM', 'Std. Package Quantity' and 'Pounds per Std Pkg' fields");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_564_VerifyChangeCalculationMethod(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the user can change the calculation method (calculation method: S,C,M,Q,X)");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_572_VerifyPurchasingMinMax(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the Purchasing Min and Purchasing Max field when the 'X' calc method is selected");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_591_VerifyConventionalFactor(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the Purchasing Min and Purchasing Max field when the 'X' calc method is selected");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_593_VerifyTaxableOption(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can change the Taxable options");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_594_VerifySerializedOption(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the user can change the Serialized options");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_565_VerifyFieldCalculationMethod(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the field verification for the Calc Method field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_592_VerifyFieldConventionalFactor(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the field verification for the Conversion factor field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_596_VerifyAddDeleteItemNote(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the user can add and delete the Item notes");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_555_VerifyABCHyperLink(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the functionality of the ABC hyperlink");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_566_VerifyAlternateItemLink(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can navigate to 'Alternate Number Stock Revisions' page and search for an item using its alternate item number");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_567_VerifyCreateNewItem(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that User can create new Item");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_569_VerifyDeleteNewItem(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that user can delete the Item Number");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_583_VerifyDuplicateItem(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the functionality of the Duplicate Item hyperlink");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_550_VerifyMatrixFieldValueChange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Matrix' field value change");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_551_VerifyMatrixFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Matrix' field range");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_554_VerifyPOCostFieldValueChange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'PO Cost' field value change");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_560_VerifyPOCostFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'PO Cost' field range");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_561_VerifyPriceMatrixRowFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Price Matrix Row' field range");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_562_VerifyPriceMatrixRowFieldValueChange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Price Matrix Row' field value change");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_563_VerifyQuantityBreakFieldValueChange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Quantity Break' field value change");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_568_VerifyQuantityBreakFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Quantity Break' field range");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_570_VerifyLostSalesFieldValueChange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Quantity Break' field value change");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_571_VerifyLostSalesFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Quantity Break' field range");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_573_VerifyExtraHistoryFieldValueChange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Extra History' field value change");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_574_VerifyExtraHistoryFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Extra History' field range");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_575_VerifyAdditionOfFreightClassCode(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify addition of 'Freight Class Code'");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_576_VerifyFreightClassCodeFieldValueChange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Freight Class Code' field value change");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_577_VerifyFreightClassCodeFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Freight Class Code' field range");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_586_VerifyMonthsOnWiseField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify data in 'Months on WISE' field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_599_VerifyUserCanChangeListPrice(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the user can change the List Price");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_600_VerifyListPriceFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the field verification for the List Price field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_602_VerifyGrossMarginCalculation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the calculation of the Gross Margin %");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB", "UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_613_VerifyUserCanChangeMinGrossMargin(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the user can change the Min. Gross Margin");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

}