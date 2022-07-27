package testcases.Purchasing;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.testng.annotations.Test;

public class POEntry extends TestConfigurations {

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_203_PurchaseOrderHeadingUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case UI for  Purchase Order Heading");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        // tearDownTestRunner(testParameters, coreScript);
    }

    /*
        @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
        public void Tc_204_ORDER_IN_USE_popup_when_user_IAD_particular_Order_No(SeleniumTestParameters testParameters) {
            testParameters.setCurrentTestDescription("ORDER IN USE popup when user I/A/D particular Order No");
            CoreScript coreScript = new CoreScript(testParameters);
            coreScript.driveTestExecution();
            tearDownTestRunner(testParameters, coreScript);
        }

    */
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_205_Purchase_Order_DetailsUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case UI for  Purchase Order Details");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_206_Field_Verification_for_Vendor_and_Order_Number(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Field Verification for Vendor and Order Number");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_207_Functionality_of_F12_Vendor_Notes(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Functionality of F12=Vendor Notes");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_208_Create_line_item_by_changing_Customer_number(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Create line item by changing Customer number");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_209_Verify_the_functionality_of_Type_Shipment_S(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality of Type Shipment : S");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_210_Verify_the_error_message(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the error message");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_211_Calculation_of_Extended_amount_for_List_Price_with_Discount(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Calculation of Extended amount for List Price with Discount");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_212_Calculation_of_Extended_amount_for_Last_Cost_with_and_without_Discount(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Calculation of Extended amount for Last Cost with and without Discount");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_213_Verification_for_addition_of_duplicate_line_item_number(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verification for addition of duplicate line item number");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    /*
        @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
        public void Tc_214_Verify_error_message_inquiring_line_item_that_does_not_exist_and_Freight_Charges_code_is_not_selected(SeleniumTestParameters testParameters) {
            testParameters.setCurrentTestDescription("Verify error message inquiring line item that does not exist and NMRQ: Verify error\n" +
                    "message Freight Charges code is not selected");
            CoreScript coreScript = new CoreScript(testParameters);
            coreScript.driveTestExecution();
            tearDownTestRunner(testParameters, coreScript);
        }

        @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
        public void Tc_215_Verify_the_functionality_of_Ship_Date_and_Line_item_have_different_Ship_Date(SeleniumTestParameters testParameters) {
            testParameters.setCurrentTestDescription("Verify the functionality of Ship Date and NMRQ: Verify the functionality of Line item\n" +
                    "have different Ship Date");
            CoreScript coreScript = new CoreScript(testParameters);
            coreScript.driveTestExecution();
            tearDownTestRunner(testParameters, coreScript);
        }
    */
    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_216_Verify_the_functionality_of_Freight_Charges(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality of Freight Charges");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_217_Verify_the_functionality_of_Cost_Option(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality of Cost Option");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_308_CreateSOUI(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality Create SO UI");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreatePo(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality Creation of SO from stock PO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_310_SoFromStockPO(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality Creation of SO from stock PO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_311_VerifySalesOrderCreated(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality Sales Order Created");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_309_verifyF21Preferences(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality Sales Order Created");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_314_verifyErrorMessageEmailFaxOptions(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality Sales Order Created");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_315_verifySelectChangeDeselect(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality Sales Order Created");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_321_verifyClosedMail(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality Sales Order Created");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_CreatePoDirect(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality Direct Po");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_312_VerifyCannotCreateSoForDirectPo(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality Create SO For Direct PO");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_316_verifyFieldExpansionOrderNo(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality of Field Expansion Order Number");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_317_verifyFieldExpansionSONoField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality of Field Expansion Sales Order Number Fields");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_318_verifyFieldExpansionRelatedSaleField(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality of Field Expansion Related Sales Fields");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_319_InquiryOrderNumber(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality of Order Number and Line No");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_335_deleteOrderNumber(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality of Delete Order Number");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_339_deleteDirectOrderNumber(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Verify the functionality of Delete Order Number");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
