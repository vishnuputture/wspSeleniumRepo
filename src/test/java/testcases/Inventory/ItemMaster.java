package testcases.Inventory;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class ItemMaster extends TestConfigurations {

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_550_VerifyMatrixFieldValueChange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Matrix' field value change");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_551_VerifyMatrixFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Matrix' field range");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_554_VerifyPOCostFieldValueChange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'PO Cost' field value change");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_560_VerifyPOCostFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'PO Cost' field range");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_561_VerifyPriceMatrixRowFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Price Matrix Row' field range");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_562_VerifyPriceMatrixRowFieldValueChange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Price Matrix Row' field value change");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_563_VerifyQuantityBreakFieldValueChange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Quantity Break' field value change");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_568_VerifyQuantityBreakFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Quantity Break' field range");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_570_VerifyLostSalesFieldValueChange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Quantity Break' field value change");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_571_VerifyLostSalesFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Quantity Break' field range");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_573_VerifyExtraHistoryFieldValueChange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Extra History' field value change");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_574_VerifyExtraHistoryFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Extra History' field range");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_575_VerifyAdditionOfFreightClassCode(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify addition of 'Freight Class Code'");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_576_VerifyFreightClassCodeFieldValueChange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Freight Class Code' field value change");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_577_VerifyFreightClassCodeFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify 'Freight Class Code' field range");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_586_VerifyMonthsOnWiseField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify data in 'Months on WISE' field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_599_VerifyUserCanChangeListPrice(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify that the user can change the List Price");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_600_VerifyListPriceFieldRange(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the field verification for the List Price field");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_602_VerifyGrossMarginCalculation(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the calculation of the Gross Margin %");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"DB","UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_613_VerifyUserCanChangeMinGrossMargin(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test to verify the user can change the Min. Gross Margin");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}