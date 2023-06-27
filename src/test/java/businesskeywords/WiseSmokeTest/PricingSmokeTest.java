package businesskeywords.WiseSmokeTest;

import com.winSupply.core.BaseSmokeTest;
import com.winSupply.core.Helper;
import org.apache.tools.ant.taskdefs.Java;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.WiseSmokeTest.PricingSmokeTestPage;
import pages.WiseSmokeTest.WiseSmokeTestPage;
import pages.common.MasterPage;
import pages.inventory.CostAndPricePage;
import pages.inventory.ItemLedgerPage;
import pages.inventory.ItemMasterPage;
import pages.inventory.SalesPersonPage;
import pages.pricing.PriceSheet.LegacyCostPriceSheetPage;
import pages.pricing.matrixcost.MatrixCostUpdatePage;
import pages.pricing.pricingmatrix.PricingMatrixPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

import java.awt.*;

public class PricingSmokeTest extends BaseSmokeTest {
    public static Helper helper;
    public Utility_Functions utility_functions;
    SuccessFailureHeadersValidation successFailureHeadersValidation = null;

    public PricingSmokeTest(Helper helper) {
        super(helper);
        utility_functions = new Utility_Functions(helper);
    }

    public void searchItemOnItemMaster() {
        sendKeys(ItemMasterPage.txtBoxSearch, jsonData.getData("itemNumber"), "Enter valid Item Number and Hit Enter");
        click(ItemMasterPage.search_itemMaster_Lookup);
        sendKeysAndEnter(WiseSmokeTestPage.optBox, "1", "Chose the item");
        storeDetailsFromItemMaterDetails();
        utility_functions.exitToMaster(2);
    }

    public void storeDetailsFromItemMaterDetails() {
        Utility_Functions.xUpdateJson("Item_Master_List_Price", getItemMasterListPrice());
        Utility_Functions.xUpdateJson("Item_Master_Average_Price", getAveragePrice());
        Utility_Functions.xUpdateJson("Item_Master_Price_Matrix_Row", getPriceMatrixRow());
        Utility_Functions.xUpdateJson("Item_Master_Matrix_Price", getMatrixPrice());
        Utility_Functions.xUpdateJson("Item_Master_WSS_Net_Price", getWSSNetPrice());
    }

    public void chooseSuitableCustomer() {
        click(MasterPage.chooseAccountNumber);
        sendKeysAndEnter(MasterPage.opt1, "1", "Chose the customer");
        storeDetailsFromBillingInstructions();
        utility_functions.exitToMaster(2);
    }

    public void navigateToCostAndPriceRevisions() {
        utility_functions.navigations(7, 9);
        PageHeadersValidation pageHeadersValidation = null;
        String expectedHeaderText = pageHeadersValidation.verifyCostAndPricesHeader.getHeaderText();
        commonObj.validateText(PricingSmokeTestPage.costs_and_prices_header, expectedHeaderText, "Validating Cost and Prices page title");
    }

    public void searchItemNumberInCostAndPrices() {
        sendKeysAndEnter(CostAndPricePage.position_to_itemMaster, jsonData.getData("itemNumber"), "Entered the item number to edit");
        String actualListPrice = Utility_Functions.xGetJsonData("Item_Master_List_Price");
        clearText(CostAndPricePage.list_price_option);
        sendKeysAndEnter(CostAndPricePage.list_price_option, utility_functions.updatedDecimalToIntegerValue(actualListPrice), "Updated the List Price in Cost & Prices");
        Utility_Functions.xUpdateJson("updatedListPrice", utility_functions.updatedDecimalToIntegerValue(actualListPrice));
        String expectedHeaderText = successFailureHeadersValidation.verifyCostAndPricesUpdatedSuccessMessage.getOutcomeMessage();
        commonObj.validateText(CostAndPricePage.success_message_on_update, expectedHeaderText, "Validating the success message on updating list price");
        click(CostAndPricePage.exit_button);
        click(CostAndPricePage.yes_to_exit);
        utility_functions.exitToMaster(1);
    }

    public void storeDetailsFromBillingInstructions() {
        Utility_Functions.xUpdateJson("Billing_Instructions_Account_Number", getAccountNumber());
        Utility_Functions.xUpdateJson("Billing_Instructions_Pricing_Column", getPricingColumn());
    }

    public String getPOCost() {
        String po_Cost = ownDriver.findElement(ItemMasterPage.txtBoxPoCost).getAttribute("value");
        return po_Cost;
    }

    public String getAccountNumber() {
        String account_Number = ownDriver.findElement(PricingSmokeTestPage.account_Number_value).getAttribute("value");
        return account_Number;
    }

    public String getPricingColumn() {
        String pricing_column = ownDriver.findElement(PricingSmokeTestPage.pricing_column_value).getAttribute("value");
        return pricing_column;
    }

    public String getItemMasterListPrice() {
        String list_price_item_master = ownDriver.findElement(ItemMasterPage.txtBoxListPrice).getAttribute("value");
        return list_price_item_master;
    }

    public String getAveragePrice() {
        String average_price_item_master = getText(ItemMasterPage.lblAverage);
        return average_price_item_master;
    }


    public String getMatrixPrice() {
        String matrix_price_item_master = ownDriver.findElement(ItemMasterPage.txtBoxMatrix).getAttribute("value");
        return matrix_price_item_master;
    }

    public String getPriceMatrixRow() {
        String price_matrix_row_item_master = ownDriver.findElement(ItemMasterPage.tbxPriceMatrixRow).getAttribute("value");
        return price_matrix_row_item_master;
    }

    public String getWSSNetPrice() {
        String WSS_Net_price_item_master = getText(ItemMasterPage.wssNetPrice);
        return WSS_Net_price_item_master;
    }

    public void verifyUpdatedListPriceAndUpdateMatrixCost() {
        utility_functions.navigations(7, 12);
        sendKeysAndEnter(ItemMasterPage.txtBoxSearch, jsonData.getData("itemNumber"), "Enter valid Item Number and Hit Enter");
        String actualListPrice = getItemMasterListPrice();
        int convertedListPrice = utility_functions.convertDecimalToInteger(actualListPrice);
        Assert.assertEquals(utility_functions.retrieveDecimalToIntegerValue(actualListPrice), Integer.toString(convertedListPrice));
        int po_Cost = utility_functions.convertDecimalToInteger(getPOCost());
        int updated_matrix_cost = po_Cost - 5;
        sendKeysAndEnter(ItemMasterPage.txtBoxMatrix, Integer.toString(updated_matrix_cost), "Updated Matrix cost to a price less than PO Cost");
        Utility_Functions.xUpdateJson("Updated_Matrix_Cost", Integer.toString(updated_matrix_cost));
//        String expectedHeaderText = successFailureHeadersValidation.verifyItemMasterUpdatedSuccessMessage.getOutcomeMessage();
//        commonObj.validateText(ItemMasterPage.messageAddSuccessful, expectedHeaderText, "Validating the success message on updating matrix price");
        click(ItemMasterPage.btnSave);
        utility_functions.exitToMaster(2);
    }

    public void verifyPricingMatrixValues() {
        utility_functions.navigations(3, 24, 20);
        sendKeys(PricingMatrixPage.strRow, Utility_Functions.xGetJsonData("Item_Master_Price_Matrix_Row"));
        sendKeysAndEnter(PricingMatrixPage.strCol, Utility_Functions.xGetJsonData("Billing_Instructions_Pricing_Column"), "Verifying the pricing matrix row and column");
        Assert.assertNotNull(PricingMatrixPage.rowValueForPricingMatrix);
        Assert.assertNotNull(PricingMatrixPage.colValueForPricingMatrix);
        utility_functions.exitToMaster(3);
    }

    public void verifyUpdatedValuesInSalesPersonInquiry() throws AWTException {
        utility_functions.navigations(7, 1);
        sendKeysAndEnter(SalesPersonPage.searchForItem, jsonData.getData("itemNumber"), "Entering the specified Item Number");
        sendKeysAndEnter(SalesPersonPage.customerTextBox, Utility_Functions.xGetJsonData("Billing_Instructions_Account_Number"), "Entered the customer acoount number");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Assert.assertEquals(utility_functions.retrieveDecimalToIntegerValue(getElement(SalesPersonPage.listPrice).getText()), Utility_Functions.xGetJsonData("updatedListPrice"));
        Assert.assertEquals(getElement(SalesPersonPage.fMROW).getText().replaceAll("[^0-9]", ""), Utility_Functions.xGetJsonData("Item_Master_Price_Matrix_Row"));
        Assert.assertEquals(utility_functions.retrieveDecimalToIntegerValue(getElement(SalesPersonPage.matrixCost).getText()), Utility_Functions.xGetJsonData("Updated_Matrix_Cost"));
        Utility_Functions.xUpdateJson("Cost_Calculation", utility_functions.retrieveDecimalToIntegerValue(getElement(SalesPersonPage.costCalculation).getAttribute("value")));
        Utility_Functions.xUpdateJson("Price_Calculation", getElement(SalesPersonPage.priceCalculation).getAttribute("value"));
        Utility_Functions.xUpdateJson("Gross_Margin_Percentage_Calculation", getElement(SalesPersonPage.grossMargCalculation).getAttribute("value").replaceAll("\\s+", ""));
        Utility_Functions.xUpdateJson("Quantity_Calculation", getElement(SalesPersonPage.qtyCalculation).getAttribute("value").replaceAll("\\s+", ""));
        Utility_Functions.xUpdateJson("Gross_Margin_Qucik_View", getElement(SalesPersonPage.grossMarginPrice).getText().replaceAll("\\s+", ""));
        click(SalesPersonPage.exit);
        utility_functions.exitToMaster(1);
    }

    public void verifyMatrixCostWarnings() {
//        utility_functions.navigations(7,21,1);
//        click(MatrixCostUpdatePage.updateMatrixCostCheckbox);
//        click(MatrixCostUpdatePage.excMCRepoRadioBtn);
//        click(MatrixCostUpdatePage.saveButton);
//        click(MatrixCostUpdatePage.updateButton);
//        utility_functions.exitToMaster(2);
    }

    public void verifyMatrixCostItemLedger() {
        utility_functions.navigations(7, 4);
        sendKeysAndEnter(ItemLedgerPage.txtItemNumber, jsonData.getData("itemNumber"), "Entered Item Number");
        Assert.assertEquals(utility_functions.retrieveDecimalToIntegerValue(getElement(ItemLedgerPage.lblLedgerPrice).getText()), Utility_Functions.xGetJsonData("Updated_Matrix_Cost"));
        Assert.assertEquals(getElement(ItemLedgerPage.lblLedgerSC).getText().trim(), jsonData.getData("SC"));
        Assert.assertEquals(getElement(ItemLedgerPage.updatedUserName).getText().toLowerCase(), jsonData.getData("userName"));
        click(ItemMasterPage.btnExit);
        utility_functions.exitToMaster(1);
    }

    public void pricingSPA() throws InterruptedException {
        createCustomerGroupMaintenance();
        addCustomer();
    }

    public void createCustomerGroupMaintenance() {
        utility_functions.navigations(5, 14);
        click(CustomerGroupMaintenancePage.addGroupsCust);
        Utility_Functions.xUpdateJson("Group_Number", getElement(CustomerGroupMaintenancePage.groupNumber).getText().trim());
        String customerGroupName = "PST" + utility_functions.appendLocalDate() + utility_functions.appendLocalTime();
        sendKeysAndEnter(CustomerGroupMaintenancePage.groupName, customerGroupName, "Entering The group name");
        Assert.assertEquals(successFailureHeadersValidation.verifyCustomerGroupAdditionSuccessMessage.getOutcomeMessage(), getElement(CustomerGroupMaintenancePage.GroupNameMessage).getText().trim());
        Utility_Functions.xUpdateJson("Group_Name", getElement(CustomerGroupMaintenancePage.groupName).getAttribute("value"));
        click(CustomerGroupMaintenancePage.cancelBtn);
    }

    public void addCustomer() throws InterruptedException {
        sendKeysAndEnter(CustomerGroupMaintenancePage.searchField, Utility_Functions.xGetJsonData("Group_Name"), "Entering the group name");
        sendKeysAndEnter(CustomerGroupMaintenancePage.groupOptField1, "2", "Editing the group name");
        click(CustomerGroupMaintenancePage.addGroupsCust);
        Utility_Functions.xUpdateJson("Customer_Number_For_SPA", getElement(CustomerGroupMaintenancePage.firCustNo).getText().trim());
        sendKeysAndEnter(CustomerGroupMaintenancePage.firstCust, "1", "Choosing the customer");
        utility_functions.multipleClicksOnTheSameElement(CustomerGroupMaintenancePage.submitBtn, 2);
        Assert.assertEquals(successFailureHeadersValidation.verifyCustomerAdditionToGroupSuccessMessage.getOutcomeMessage(), getElement(CustomerGroupMaintenancePage.GroupNameMessage).getText().trim());
        utility_functions.multipleClicksOnTheSameElement(CustomerGroupMaintenancePage.cancelBtn, 3);
    }

    public void createSPAContract() throws InterruptedException {
        createContract();
        addItemsToContract();
        addMFPDCodes();
        loadSpecialPrice();
    }

    public void createContract() {
        utility_functions.navigations(13);
        click(SpecialPriceAllowancePage.btnAddContract);
        sendKeys(SpecialPriceAllowancePage.txtBoxContractname, utility_functions.appendLocalDate());
        ownDriver.findElement(SpecialPriceAllowancePage.txtBoxContractname).sendKeys(Keys.CONTROL, "a");
        ownDriver.findElement(SpecialPriceAllowancePage.txtBoxContractname).sendKeys(Keys.CONTROL, "c");
        ownDriver.findElement(SpecialPriceAllowancePage.txtBoxStartDate).sendKeys(Keys.CONTROL, "v");
        sendKeys(SpecialPriceAllowancePage.txtBoxContractname, utility_functions.appendFutureLocalDate(1));
        ownDriver.findElement(SpecialPriceAllowancePage.txtBoxContractname).sendKeys(Keys.CONTROL, "a");
        ownDriver.findElement(SpecialPriceAllowancePage.txtBoxContractname).sendKeys(Keys.CONTROL, "c");
        ownDriver.findElement(SpecialPriceAllowancePage.txtBoxEndDate).sendKeys(Keys.CONTROL, "v");
        String contractName = "PC " + utility_functions.appendLocalDate() + " " + utility_functions.appendLocalTime();
        Utility_Functions.xUpdateJson("Contract_Name", contractName);
        sendKeys(SpecialPriceAllowancePage.txtBoxContractname, contractName);
        for (int i = 0; i < 2; i++) {
            click(SpecialPriceAllowancePage.vendorNameLink, "Clicked on Add Vendor hyperlink");
        }
        sendKeysAndEnter(SpecialPriceAllowancePage.optionOneToChooseVendor, "1", "Choosing the appropriate Vendor");
        sendKeys(SpecialPriceAllowancePage.txtBoxGroupNo, Utility_Functions.xGetJsonData("Group_Number"));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Assert.assertEquals(successFailureHeadersValidation.verifyAdditionOfAContractSuccessMessage.getOutcomeMessage(), getElement(SpecialPriceAllowancePage.lblSuccess).getText().trim());
        click(SpecialPriceAllowancePage.btnReturn);
    }

    public void addItemsToContract() throws InterruptedException {
        sendKeysAndEnter(SpecialPriceAllowancePage.inputSearchBox, Utility_Functions.xGetJsonData("Contract_Name"), "Entered the contract name in search box");
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxOption, "2", "Editing the contract");
        click(SpecialPriceAllowancePage.individualItem);
        click(SpecialPriceAllowancePage.btnAddContract);
        sendKeysAndEnter(SpecialPriceAllowancePage.mfCOde, jsonData.getData("itemNumber"), "Entering the Item Number");
        sendKeysAndEnter(SpecialPriceAllowancePage.rebateCost, jsonData.getData("Rebate Cost"), "Entering the rebate cost");
        sendKeysAndEnter(SpecialPriceAllowancePage.sellingPrice, jsonData.getData("Selling Price"), "Entering the Selling Price");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Assert.assertEquals(successFailureHeadersValidation.verifyAdditionOfIndividualItemsToContractSuccessMessage.getOutcomeMessage(), getElement(SpecialPriceAllowancePage.successMessageForIndividualItems).getText().trim());
        utility_functions.multipleClicksOnTheSameElement(SpecialPriceAllowancePage.btnReturn, 2);
    }

    public void addMFPDCodes() throws InterruptedException {
        click(SpecialPriceAllowancePage.addMFPDCodes);
        for (int i = 0; i < 2; i++)
            click(SpecialPriceAllowancePage.mfpdCodes);
        sendKeysAndEnter(SpecialPriceAllowancePage.optionOneForMFPDCode, "1", "Selected an MFPD code");
        click(SpecialPriceAllowancePage.prompt);
        sendKeysAndEnter(SpecialPriceAllowancePage.productSelectionCode, "1", "Selected a Product Selection code");
        sendKeys(SpecialPriceAllowancePage.rebateDiscountType, jsonData.getData("Rebate Discount Type"));
        sendKeys(SpecialPriceAllowancePage.rebateDiscount, jsonData.getData("Rebate Discount"));
        sendKeys(SpecialPriceAllowancePage.sellMultTypeValue, jsonData.getData("Selling Multiplier Type"));
        sendKeys(SpecialPriceAllowancePage.sellMult, jsonData.getData("Selling Multiplier"));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Assert.assertEquals(successFailureHeadersValidation.verifyAdditionOfAContractSuccessMessage.getOutcomeMessage(), getElement(SpecialPriceAllowancePage.lblSuccess).getText().trim());
        utility_functions.multipleClicksOnTheSameElement(SpecialPriceAllowancePage.btnReturn, 2);
    }

    public void loadSpecialPrice() {
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxOption, "7", "Loading the Special Price");
        //Assert.assertEquals(successFailureHeadersValidation.verifyLoadSpecialPricingConfirmationMessage.getOutcomeMessage(), getElement(SpecialPriceAllowancePage.confirmLoadSpecialPricing).getText().trim());
        click(SpecialPriceAllowancePage.loadSpecialPrice);
        Assert.assertEquals(successFailureHeadersValidation.verifyLoadSpecialPricingSuccessMessage.getOutcomeMessage(), getElement(SpecialPriceAllowancePage.successMessageForIndividualItems).getText().trim());
        click(SpecialPriceAllowancePage.btnReturn);
        Assert.assertEquals(getElement(SpecialPriceAllowancePage.SP).getText().trim(), jsonData.getData("Special Pricing Value"));
        click(SpecialPriceAllowancePage.btnReturn);
        utility_functions.exitToMaster(2);
    }

    public void verifySpecialPriceInItemMaster() {
        utility_functions.navigations(7, 1);
        sendKeysAndEnter(SalesPersonPage.searchForItem, jsonData.getData("itemNumber"), "Entered the item number to edit");
        sendKeysAndEnter(SalesPersonPage.customerTextBox, Utility_Functions.xGetJsonData("Customer_Number_For_SPA"), "Entered the Customer to verify the Special Pricing");
        Assert.assertEquals(utility_functions.retrieveDecimalToIntegerValue(getElement(SalesPersonPage.specialPrice).getText().trim()), jsonData.getData("Selling Price"));
        click(SalesPersonPage.exit);
        utility_functions.exitToMaster(1);
    }

    public void legacyCostPriceSheets() {
        utility_functions.winIntoCompany();
        utility_functions.navigations(7, 25, 1);
        updateAnExistingPriceSheet();
        navigatetoAMaintainingSheet();
    }

    public void updateAnExistingPriceSheet() {
        sendKeysAndEnter(LegacyCostPriceSheetPage.optionOneForSheet, "1", "Choosing the first sheet for verification");
        Assert.assertEquals(getElement(LegacyCostPriceSheetPage.status).getText().trim(), LegacyCostPriceSheetPage.currentSheetStatus.trim());
        sendKeys(LegacyCostPriceSheetPage.sheetMultiplier, jsonData.getData("legacySheetCostPriceSheetMultiplier"));
        sendKeys(LegacyCostPriceSheetPage.listPrice, "Y");
        sendKeys(LegacyCostPriceSheetPage.poCost, "Y");
        sendKeysAndEnter(LegacyCostPriceSheetPage.matrixCost, "Y", "Updated the sheet");
        String currentSheetCode = getElement(LegacyCostPriceSheetPage.priceSheetCode).getAttribute("value");
        Assert.assertEquals(currentSheetCode + " " + successFailureHeadersValidation.verifyLegacyPriceSheetUpdateSuccessMessage.getOutcomeMessage(), getElement(LegacyCostPriceSheetPage.successMessageValidation).getText());
        Assert.assertEquals(getElement(LegacyCostPriceSheetPage.status).getText().trim(), LegacyCostPriceSheetPage.changedSheetStatus);
        click(LegacyCostPriceSheetPage.returnButton);
    }

    public void navigatetoAMaintainingSheet() {
        sendKeysAndEnter(LegacyCostPriceSheetPage.optionOneForSheet, "1", "Choosing the first sheet for verification");
        click(LegacyCostPriceSheetPage.runComparisonReport);
        String currentSheetCode = getElement(LegacyCostPriceSheetPage.priceSheetCode).getAttribute("value");
        Utility_Functions.xUpdateJson("Legacy_Cost_Price_Sheet_Code", currentSheetCode);
        Assert.assertEquals(Utility_Functions.xGetJsonData("Legacy_Cost_Price_Sheet_Code") + " " + successFailureHeadersValidation.verifyLegacyPriceSheetRunComparisonReportsSuccessMessage.getOutcomeMessage(), getElement(LegacyCostPriceSheetPage.successMessageValidation).getText());
        sendKeys(LegacyCostPriceSheetPage.processNow, "Y");
        sendKeysAndEnter(LegacyCostPriceSheetPage.processDate, "00000", "Enter a dummy value to avoid a process date and process now conflict");
        String actualText = utility_functions.lengthyTextConvertedIntoAString(LegacyCostPriceSheetPage.processSheetMessage, ownDriver);
        Assert.assertEquals(actualText, successFailureHeadersValidation.verifyLegacyPriceSheetImmediateProcessValidationMessage.getOutcomeMessage());
        click(LegacyCostPriceSheetPage.processImmediately);
        Assert.assertEquals(getElement(LegacyCostPriceSheetPage.status).getText().trim(), LegacyCostPriceSheetPage.processedSheetStatus);
        utility_functions.exitToMaster(4);
    }

    public void addItem() {
        click(LegacyCostPriceSheetPage.AddItem);
        click(LegacyCostPriceSheetPage.AddItemNumberLink);
        sendKeysAndEnter(LegacyCostPriceSheetPage.chooseFirstItemNumber, "1", "Choosing the value");
        Utility_Functions.xUpdateJson("Legacy_Cost_Price_Sheet_Item_Number", getElement(LegacyCostPriceSheetPage.ItemNumberValue).getAttribute("value"));
        Utility_Functions.xUpdateJson("Legacy_Cost_Price_Sheet_List_Price", getElement(LegacyCostPriceSheetPage.ListPriceValue).getAttribute("value").trim());
        click(LegacyCostPriceSheetPage.enter);
        click(LegacyCostPriceSheetPage.returnButton);
    }

    public void verifyLegacyCostPriceItemDetailsInItemMaster() {
        utility_functions.navigations(7, 12);
        sendKeysAndEnter(ItemMasterPage.txtBoxSearch, Utility_Functions.xGetJsonData("Legacy_Cost_Price_Sheet_Item_Number"), "Searching for the Legacy Cost Price Item in Item Master");
        Assert.assertEquals(getItemMasterListPrice().trim(), Utility_Functions.xGetJsonData("Legacy_Cost_Price_Sheet_List_Price"));
        double expectedPOCost = Double.parseDouble(jsonData.getData("legacySheetCostPriceSheetMultiplier")) * Double.parseDouble(Utility_Functions.xGetJsonData("Legacy_Cost_Price_Sheet_List_Price"));
        Assert.assertEquals(utility_functions.removeTheTrailingZeors(getPOCost()), Double.toString(expectedPOCost));
        Utility_Functions.xUpdateJson("Update_Matrix_Cost_Legacy_Cost_Price",getMatrixPrice());
        click(ItemMasterPage.btnExit);
        utility_functions.exitToMaster(1);
    }

    public void verifyItemLedgerForLegacyCostPriceSheet()
    {
        utility_functions.navigations(7, 4);
        sendKeysAndEnter(ItemLedgerPage.txtItemNumber, Utility_Functions.xGetJsonData("Legacy_Cost_Price_Sheet_Item_Number"), "Entered Item Number used in Legacy Cost Price Sheet");
        Assert.assertEquals(getElement(ItemLedgerPage.lblLedgerPrice).getText(), Utility_Functions.xGetJsonData("Update_Matrix_Cost_Legacy_Cost_Price"));
        Assert.assertEquals(getElement(ItemLedgerPage.lblLedgerSC).getText().trim(), jsonData.getData("SC"));
        Assert.assertEquals(getElement(ItemLedgerPage.updatedUserName).getText().toLowerCase(), jsonData.getData("userName"));
        click(ItemMasterPage.btnExit);
        utility_functions.exitToMaster(1);
    }
}
