package businesskeywords.Inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import commonkeywords.DBCall;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.PurchaseOrders.InventoryReceiptsPage;
import pages.PurchaseOrders.PurchaseOrderDetailsPage;
import pages.common.MasterPage;
import pages.inventory.CostAdjustmentPage;
import pages.inventory.ItemMasterPage;
import pages.inventory.SalesPersonPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

import java.util.ArrayList;

public class itemMaster extends ReusableLib {

    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public itemMaster(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * This method navigates and validates the title of Item Master Page
     */
    public void navigateToItemMasterPage() {
        commonObj.masterToInventory();
        commonObj.inventoryToItemMaster();
        commonObj.validateText(ItemMasterPage.pageTitle, "ITEM MASTER (I-347)", "Validating [ITEM MASTER] page title");
    }

    /**
     * This method navigates and validates the title of Item Master Page
     */
    public void navigateToAddFreightChargeCodePage() {
        commonObj.masterToInventory();
        commonObj.inventoryToOptionsAndConstants();
        commonObj.inventoryOptionsAndConstantsToFreightClassCodePage();
        commonObj.validateText(MasterPage.pageTitleFreightClassCode, "Freight Classification Codes", "Validating [Freight Classification Codes] page title");
    }


    public void searchItemMasterData() {
        //  DBCall.createItemInItemMaster("*00000WS0004","Test-Item-Automation","EA");
        String item = DBCall.SearchItemInItemMaster(Utility_Functions.xGetJsonAsString("CreatedCost"));
        System.out.println("Item found " + item);
        Utility_Functions.xAssertEquals(report, Utility_Functions.xGetJsonAsString("CreatedCost"), item.substring(0, item.indexOf(" ")), "Item found Successfully");
        DBCall.closeDBConnection();

    }

    public void updateItemMasterData() {
        ArrayList<String> key = new ArrayList<>();
        ArrayList<String> val = new ArrayList<>();
        key.add(jsonData.getData("key"));
        val.add(jsonData.getData("val"));
        String res = DBCall.UpdateItemInItemMaster(Utility_Functions.xGetJsonAsString("CreatedCost"), key, val);
        System.out.println(res);
        String cmp = jsonData.getData("val");
        Utility_Functions.xAssertEquals(report, cmp, res, "Item updated successfully");
        DBCall.closeDBConnection();
    }

    public void updateOnHandQuantity() {
        String cmp = jsonData.getData("QTY");
        String qty = DBCall.updateAndSearchQTY(Utility_Functions.xGetJsonAsString("CreatedCost"), Integer.parseInt(cmp));
        System.out.println("Item found and Updated Quantity on hand is " + qty);
        System.out.println(cmp);
        Utility_Functions.xAssertEquals(report, cmp, qty, "On hand Value updated Successfully");
        DBCall.closeDBConnection();
    }

    public void updateItemAverageCost() {
        String cmp = jsonData.getData("Cost");
        String avg = DBCall.updateAndSearchAVGCost(Utility_Functions.xGetJsonAsString("CreatedCost"), Integer.parseInt(cmp));
        System.out.println("Item found and Updated average price is  " + avg);
        Utility_Functions.xAssertEquals(report, cmp, avg.substring(0, avg.indexOf(".")), "Average Cost is Updated successfully");
        DBCall.closeDBConnection();
    }

    /**
     * Keyword to select an Item in ITEM MASTER (I-347)
     */
    public void selectRandomItem() {
        click(ItemMasterPage.itemSearchIcon, "Click on [Item Search] icon in ITEM MASTER (I-347)");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        int count = Utility_Functions.xRandomFunction(1, 10);
        while (count > 0) {
            click(ItemMasterPage.btnDown);
            waitForElementDisappear(MasterPage.loadingAnime, globalWait);
            count--;
        }
        sendKeysAndEnter(CostAdjustmentPage.optBox, "1", "Select Item Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        String itemNumber = getAttribute(ItemMasterPage.txtBoxSearch, "value");
        Utility_Functions.xUpdateJson("ItemNoMaster", itemNumber);
    }

    /**
     * Keyword to enter List Price in ITEM MASTER (I-347)
     */
    public void editRandomListPrice() {
        String listPrice = Integer.toString(Utility_Functions.xRandomFunction(100, 200));
        Utility_Functions.xUpdateJson("ListPrice", listPrice + ".000000");
        enterListPrice(listPrice);
        enterConversionFactor("1");
        clickSave();
    }

    /**
     * Keyword to enter PO Cost in ITEM MASTER (I-347)
     */
    public void editRandomPOCost() {
        String poCost = Integer.toString(Utility_Functions.xRandomFunction(100, 200));
        Utility_Functions.xUpdateJson("POCost", poCost + ".000000");
        enterPOCost(poCost);
        enterConversionFactor("1");
        clickSave();
    }

    /**
     * Keyword to enter List Price in ITEM MASTER (I-347)
     */
    public void enterListPrice(String listPrice) {
        sendKeys(ItemMasterPage.txtBoxListPrice, listPrice, "Entered List Price as [" + listPrice + "]");
    }

    /**
     * Keyword to enter PO Codt in ITEM MASTER (I-347)
     */
    public void enterPOCost(String poCost) {
        sendKeys(ItemMasterPage.txtBoxPoCost, poCost, "Entered List Price as [" + poCost + "]");
    }

    /**
     * Keyword to enter Conversion Factor in ITEM MASTER (I-347)
     */
    public void enterConversionFactor(String conversionFactor) {
        sendKeys(ItemMasterPage.tbxConvFactor, conversionFactor, "Entered Conversion Factor as [" + conversionFactor + "]");
    }

    /**
     * Keyword to click [Save] button and verify success msg in ITEM MASTER (I-347)
     */
    public void clickSave() {
        click(ItemMasterPage.btnSave, "Clicked Save button");
        waitForVisible(ItemMasterPage.messageAddSuccessful);
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "Success message is displayed");
    }

    /**
     * Keyword to enter data in [Item Notes] in ITEM MASTER (I-347)
     */
    public void enterItemNotes() {
        String itemNotes = "Testing Item Notes !@#%^&*()" + Utility_Functions.xRandomFunction();
        sendKeysAndEnter(ItemMasterPage.itemNotes1, itemNotes, "Entered Item Notes as [" + itemNotes + "]");
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "Validating Success Message");
        jsonData.putData("ItemNotes", itemNotes);
    }

    public By inputFields(String id) {
        return By.xpath("//input[@id='" + id + "']");
    }

    public By linkField(String text) {
        return By.xpath("//a[text()='" + text + "']");
    }

    public By spanField(String text) {
        return By.xpath("//span[text()='" + text + "']");
    }

    public By divField() {
        return By.xpath("//div[@class='win-label']");
    }

    public void exitItemMaster() {
        click(SpecialPriceAllowancePage.btnExit, "Click [F3=Exit]");
    }

    public void itemMasterAv() {
        String[] ids = {"inItemNumber", "inItemDesc1", "inManufacturerCode", "inProductCode", "inVendorCode", "inItemType", "inHstMntRtn", "inPurchasingUOM", "inBuyStdPackQty", "inPdsperStdKG", "inSellingUOM", "inStdPackQty", "inPdsPerStdKG", "inCalcMethod", "inCovFactor", "inItemNotes1"};
        for (String id : ids) {
            String[] idSplit = id.split("in");
            commonObj.validateElementExists(inputFields(id), "Input Field " + idSplit[1] + " is present");
        }
        String[] linkText = {"< Previous Item", "Next Item >", "ABC", "Alternate Item", "Add Item", "Delete Item", "Duplicate Item", "Item-Bin Maintenance", "Lead Time Maintenance", "Lead Time Maintenance", "Refresh", "Rich Data", "e-Comm"};
        for (String text : linkText) {
            commonObj.validateElementExists(linkField(text), "Input Field " + text + " is present");
        }
        String[] spanText = {"Item Details (I-349)", " Item-Bin Details"};
        for (String text : spanText) {
            commonObj.validateElementExists(spanField(text), "Field " + text + " is present");
        }
        String[] divIds = {"Search for Item", "Description:", "Description 2:", "Manufacturer:", "Product:", "Vendor:", "Item Type:", "History Months to Retain:", "UOM:", "Std. Package Quantity:", "Pounds per Std Pkg:", "UOM:", "Std. Package Quantity:", "Pounds per Std Pkg:", "Absolute ABC:", "Calc Method:", "Conversion Factor:", "Taxable:", "Lead Time in Days:", "Lead Time Expires:", "Serialized:", "MID:", "Available to Sell:", "- With Receipts:", "On Hand:", "On PO:", "Unscheduled:", "On SO:", "On BO:", "List Price:", "Gross Margin %:",
                "Min. Gross Margin:", "Costs Last:", "Average:", "Matrix:", "PO Cost:", "WSS Net Price:", "Price Matrix Row:", "Quantity Break:", "Lost Sales:", "Extra History:", "Months on Wise:", "Freight Class Code:", "Primary Bin:", "Purchasing Min:", "Purchasing Max:", "Exclude M/C Rpt:", "Auto Replenish:"};
        for (int i = 0; i < divIds.length; i++) {
            commonObj.validateElementExists(divField(), "Label " + ownDriver.findElements(divField()).get(i).getText() + " is present");
        }
    }

    public void navigateToItemMasterBrowser() {
        click(ItemMasterPage.txtBoxSearch, "Click [Search For Item] input field");
        Utility_Functions.actionKey(Keys.F4, ownDriver);
        commonObj.validateText(PurchaseOrderDetailsPage.poPrintSendHeader, "Item Master Browse - Local", "[Item Master Browse - Local] title is present");
    }

    public void verifyItemDetails() {
        String itemNo = getText(ItemMasterPage.itemNumber);
        String itemDesc = getText(SalesPersonPage.itemDescription).trim();
        sendKeysAndEnter(CustomerGroupMaintenancePage.groupOptField2, "1", "Select an Item");
        String itemNum = getAttribute(ItemMasterPage.txtBoxSearch, "value").trim();
        Utility_Functions.xAssertEquals(report, itemNum, itemNo, "");
        commonObj.validateText(ItemMasterPage.itemDetail, itemDesc, itemDesc + " is present");
    }

    public void clearSearchField() {
        clearText(ItemMasterPage.txtBoxSearch);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(ItemMasterPage.itemDetail, "", "Validate fields are cleared");
    }

    public void inventoryToItemMasterScreen() {
        click(MasterPage.itemMasterMenu, "Click item master");
    }

    public void searchItem() {
        sendKeysAndEnter(ItemMasterPage.txtBoxSearch, jsonData.getData("validItemNo"), "Enter valid Item Number and Hit Enter");
        commonObj.validateElementExists(ItemMasterPage.itemDetail, "Item Description " + getText(ItemMasterPage.itemDetail) + " is present");
    }

    public void searchItemMaster() {
        sendKeysAndEnter(ItemMasterPage.txtBoxSearch, Utility_Functions.xGetJsonData("ItemNoMaster"), "Enter valid Item Number and Hit Enter");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    public void verifySearchItemsField() {
        searchItem();
        clearSearchField();
        navigateToItemMasterBrowser();
        verifyItemDetails();
        click(CostAdjustmentPage.searchIcon, "Click Search Icon");
        verifyItemDetails();
    }

    public String navigateToNextItem() {
        sendKeysAndEnter(ItemMasterPage.txtBoxSearch, jsonData.getData("ItemNo"), "Enter Item Number");
        click(linkIdEle("lnkNextItem"), "Click [Next Item >]");
        String nextItem = getAttribute(ItemMasterPage.txtBoxSearch, "value").trim();
        if (nextItem.equals(jsonData.getData("ItemNo"))) {
            try {
                throw new Exception("Doesn't move to Next Item");
            } catch (Exception e) {
            }
        }
        return nextItem;
    }

    public By linkIdEle(String id) {
        return By.xpath("//div[@id='" + id + "']/a");
    }

    public void navigateNextBackLinkItemMaster() {
        navigateToNextItem();
        click(linkIdEle("lnkPrevItem"), "Click [Previous Item]");
        String previousItem = getAttribute(ItemMasterPage.txtBoxSearch, "value").trim();
        Utility_Functions.xAssertEquals(report, jsonData.getData("ItemNo"), previousItem, "");
    }

    public void verifySearchFieldNeg(String itemNo) {
        sendKeysAndEnter(ItemMasterPage.txtBoxSearch, itemNo, "Enter Invalid Item Number " + itemNo + " and Hit Enter");
        String errorTitle = getAttribute(ItemMasterPage.txtBoxSearch, "title");
        Utility_Functions.xAssertEquals(report, errorTitle, "Item Error", "");
        clearSearchField();
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.txtBoxSearch, "class"), "win-input", "");
    }

    public void verifySearchInvalidItems() {
        verifySearchFieldNeg(jsonData.getData("InvalidItemNo"));
        verifySearchFieldNeg(jsonData.getData("AlphaNumericSpecialCharacterItem"));
        verifySearchFieldNeg(jsonData.getData("NegativeItem"));
        verifySearchFieldNeg(jsonData.getData("ZerosItem"));
    }

    public void verifyItemDescription() {
        Utility_Functions.xIsDisplayed(ownDriver, ItemMasterPage.itemDesc1);
        sendKeysAndEnter(ItemMasterPage.itemDesc2, jsonData.getData("Description2"), "Edit Item Description2");
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
    }

    public void verifyDescription2() {
        String desc2 = getAttribute(ItemMasterPage.itemDesc2, "value");
        Utility_Functions.xAssertEquals(report, desc2, jsonData.getData("Description2"), "Description Matches");
    }

    public void verifyChangeMFPDVN() {

    }

    /**
     * Keyword to enter data in [Matrix] field in ITEM MASTER (I-347)
     */
    public void editMatrixField() {
        Utility_Functions.xIsDisplayed(ownDriver, ItemMasterPage.txtBoxMatrix);
        String random = String.valueOf(Utility_Functions.xRandomFunction(1, 9999999));
        sendKeysAndEnter(ItemMasterPage.txtBoxMatrix, random, "Edit Matrix field value");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
    }

    /**
     * Keyword to validate [Matrix] field in ITEM MASTER (I-347)
     */
    public void validateMatrixField() {
        String alphaNumeric = "abc!@#*";
        sendKeys(ItemMasterPage.txtBoxMatrix, alphaNumeric, "Entering aphla-numeric value in Matrix field");
        String matrixValue = getAttribute(ItemMasterPage.txtBoxMatrix, "value");
        Utility_Functions.xAssertEquals(report, "", matrixValue, "Matrix field is NOT accepting alphabets");

        String negative = "-10";
        sendKeys(ItemMasterPage.txtBoxMatrix, negative, "Entering negative value in Matrix field");
        matrixValue = getAttribute(ItemMasterPage.txtBoxMatrix, "value");
        Utility_Functions.xAssertEquals(report, "10", matrixValue, "Matrix field is NOT accepting negative values");

        String maxValue = "99999999";
        sendKeysAndEnter(ItemMasterPage.txtBoxMatrix, maxValue, "Entering value greater than max accepted value in Matrix field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.tooltip, maxValue+" has an incorrect data length or decimal position.", "Error tooltip is present");

        clearText(ItemMasterPage.txtBoxMatrix);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
        matrixValue = getAttribute(ItemMasterPage.txtBoxMatrix, "value");
        Utility_Functions.xAssertEquals(report, ".0000", matrixValue, "Matrix field is defaulted to [.0000]");

        String random = String.valueOf(Utility_Functions.xRandomFunction(1, 9999999));
        sendKeysAndEnter(ItemMasterPage.txtBoxMatrix, random, "Entering acceptable value in Matrix field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
    }

    /**
     * Keyword to enter data in [PO Cost] field in ITEM MASTER (I-347)
     */
    public void editPOCostField() {
        Utility_Functions.xIsDisplayed(ownDriver, ItemMasterPage.txtBoxPoCost);
        String random = String.valueOf(Utility_Functions.xRandomFunction(1, 9999999));
        sendKeysAndEnter(ItemMasterPage.txtBoxPoCost, random, "Edit PO Cost field value");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
    }

    /**
     * Keyword to validate [PO Cost] field in ITEM MASTER (I-347)
     */
    public void validatePOCostField() {
        String alphaNumeric = "abc!@#*";
        sendKeys(ItemMasterPage.txtBoxPoCost, alphaNumeric, "Entering aphla-numeric value in PO Cost field");
        String poCostValue = getAttribute(ItemMasterPage.txtBoxPoCost, "value");
        Utility_Functions.xAssertEquals(report, "", poCostValue, "PO Cost field is NOT accepting alphabets");

        String negative = "-10";
        sendKeys(ItemMasterPage.txtBoxPoCost, negative, "Entering negative value in PO Cost field");
        poCostValue = getAttribute(ItemMasterPage.txtBoxPoCost, "value");
        Utility_Functions.xAssertEquals(report, "10", poCostValue, "PO Cost field is NOT accepting negative values");

        String maxValue = "99999999";
        sendKeysAndEnter(ItemMasterPage.txtBoxPoCost, maxValue, "Entering value greater than max accepted value in PO Cost field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.tooltip, maxValue+" has an incorrect data length or decimal position.", "Error tooltip is present");

        clearText(ItemMasterPage.txtBoxPoCost);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
        poCostValue = getAttribute(ItemMasterPage.txtBoxPoCost, "value");
        Utility_Functions.xAssertEquals(report, ".0000", poCostValue, "PO Cost field is defaulted to [.0000]");

        String random = String.valueOf(Utility_Functions.xRandomFunction(1, 9999999));
        sendKeysAndEnter(ItemMasterPage.txtBoxPoCost, random, "Entering acceptable value in PO Cost field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
    }

    /**
     * Keyword to enter data in [Price Matrix Row] field in ITEM MASTER (I-347)
     */
    public void editPriceMatrixRowField() {
        Utility_Functions.xIsDisplayed(ownDriver, ItemMasterPage.tbxPriceMatrixRow);
        sendKeysAndEnter(ItemMasterPage.tbxPriceMatrixRow, "99", "entering invalid value in Price Matrix Row field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        String tooltip = getAttribute(ItemMasterPage.tbxPriceMatrixRow, "title");
        Utility_Functions.xAssertEquals(report, "Invalid Price Matrix Code", tooltip, "Validating error tooltip");
        String color = getElement(ItemMasterPage.tbxPriceMatrixRow).getCssValue("color");
        Utility_Functions.xAssertEquals(report, "rgba(85, 85, 85, 1)", color, "Validating Price Matrix Row textbox color for invalid value");

        sendKeys(ItemMasterPage.tbxPriceMatrixRow, "9999", "entering invalid value in Price Matrix Row field");
        String value = getAttribute(ItemMasterPage.tbxPriceMatrixRow, "value");
        Utility_Functions.xAssertEquals(report, "999", value, "Price Matrix Row accepts only 3 characters");
    }

    /**
     * Keyword to validate [Price Matrix Row] field in ITEM MASTER (I-347)
     */
    public void validatePriceMatrixRowField() {
        Utility_Functions.xIsDisplayed(ownDriver, ItemMasterPage.tbxPriceMatrixRow);
        Utility_Functions.xMouseClick(ownDriver, ItemMasterPage.tbxPriceMatrixRow);
        Utility_Functions.actionKey(Keys.F4, ownDriver);
        commonObj.validateText(ItemMasterPage.hdrMatrixRowCodes, "Matrix Row Codes", "Validating [Matrix Row Codes] page title > After pressing F4 on Price Matrix Row field");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
        commonObj.validateText(ItemMasterPage.pageTitle, "ITEM MASTER (I-347)", "Validating [ITEM MASTER (I-347)] page title > After pressing F3");

        selectPriceMatrixRow();
        sendKeysAndEnter(ItemMasterPage.tbxPriceMatrixRow, jsonData.getData("PriceMatrixRow"), "Enter value in Price Row Matrix field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
        String selectedMatrixRowCode = getAttribute(ItemMasterPage.tbxPriceMatrixRow, "value");
        Utility_Functions.xAssertEquals(report, jsonData.getData("PriceMatrixRow"), selectedMatrixRowCode, "Price Row Matrix field value saved successfully");

        Utility_Functions.actionKey(Keys.F3, ownDriver);
        commonObj.validateText(MasterPage.pageTitleInventoryManagement, "Inventory Management - Main Menu", "Validating [Inventory Management - Main Menu] page title");
        commonObj.inventoryToItemMaster();
        commonObj.validateText(ItemMasterPage.pageTitle, "ITEM MASTER (I-347)", "Validating [ITEM MASTER] page title");
        searchItemMaster();
        selectedMatrixRowCode = getAttribute(ItemMasterPage.tbxPriceMatrixRow, "value");
        Utility_Functions.xAssertEquals(report, jsonData.getData("PriceMatrixRow"), selectedMatrixRowCode, "Validating Price Row Matrix field value after re-navigation");
    }

    /**
     * Keyword to select random [Price Matrix Row] in ITEM MASTER (I-347)
     */
    public String selectPriceMatrixRow() {
        click(ItemMasterPage.btnSearchPriceMatrixRow, "Clicked on Search icon besides Price Matrix Row field");
        commonObj.validateText(ItemMasterPage.hdrMatrixRowCodes, "Matrix Row Codes", "Validating [Matrix Row Codes] page title");

        int count = Utility_Functions.xRandomFunction(1, 10);
        while (count > 0) {
            click(ItemMasterPage.btnDown);
            count--;
        }
        String row1 = getText(ItemMasterPage.itemNumber);
        String selectMatrixRowCode = row1.substring(0, 3);
        sendKeysAndEnter(CostAdjustmentPage.optBox, "1", "Select Item Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        String selectedMatrixRowCode = getAttribute(ItemMasterPage.tbxPriceMatrixRow, "value");
        Utility_Functions.xAssertEquals(report, selectMatrixRowCode, selectedMatrixRowCode, "Selected Price Matrix Row");
        jsonData.putData("SelectedPriceMatrixRow", selectedMatrixRowCode);
        return selectedMatrixRowCode;
    }

    /**
     * Keyword to enter data in [Quantity Break] field in ITEM MASTER (I-347)
     */
    public void editQuantityBreakField() {
        Utility_Functions.xIsDisplayed(ownDriver, ItemMasterPage.tbxQuantityBreak);
        sendKeysAndEnter(ItemMasterPage.tbxQuantityBreak, "99", "entering invalid value in Quantity Break field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        String tooltip = getAttribute(ItemMasterPage.tbxQuantityBreak, "title");
        Utility_Functions.xAssertEquals(report, "Invalid Quantity Break", tooltip, "Validating error tooltip");
        String color = getElement(ItemMasterPage.tbxQuantityBreak).getCssValue("color");
        Utility_Functions.xAssertEquals(report, "rgba(85, 85, 85, 1)", color, "Validating Quantity Break textbox color for invalid value");

        sendKeys(ItemMasterPage.tbxQuantityBreak, "999", "entering invalid value in Quantity Break field");
        String value = getAttribute(ItemMasterPage.tbxQuantityBreak, "value");
        Utility_Functions.xAssertEquals(report, "99", value, "Quantity Break accepts only 2 characters");
    }

    /**
     * Keyword to validate [Quantity Break] field in ITEM MASTER (I-347)
     */
    public void validateQuantityBreakField() {
        Utility_Functions.xIsDisplayed(ownDriver, ItemMasterPage.tbxQuantityBreak);
        Utility_Functions.xMouseClick(ownDriver, ItemMasterPage.tbxQuantityBreak);
        Utility_Functions.actionKey(Keys.F4, ownDriver);
        commonObj.validateText(ItemMasterPage.hdrQtyBreakDiscountCodes, "Quantity Break Discount Codes", "Validating [Quantity Break Discount Codes] page title > After pressing F4 on Quantity Break field");
        Utility_Functions.actionKey(Keys.F12, ownDriver);
        commonObj.validateText(ItemMasterPage.pageTitle, "ITEM MASTER (I-347)", "Validating [ITEM MASTER (I-347)] page title > After pressing F12");

        click(ItemMasterPage.btnSearchQuantityBreak, "Clicked on Search icon besides Quantity Break field");
        commonObj.validateText(ItemMasterPage.hdrQtyBreakDiscountCodes, "Quantity Break Discount Codes", "Validating [Matrix Row Codes] page title");
        commonObj.validateText(ItemMasterPage.msgNoQtyBreakDiscountCodeRecords, "* NO RECORDS TO DISPLAY *", "Validating [No Records to Display]  message");
        Utility_Functions.actionKey(Keys.F12, ownDriver);
        commonObj.validateText(ItemMasterPage.pageTitle, "ITEM MASTER (I-347)", "Validating [ITEM MASTER (I-347)] page title > After pressing F12");
    }

    /**
     * Keyword to enter data in [Lost Sales] field in ITEM MASTER (I-347)
     */
    public void editLostSalesField() {
        Utility_Functions.xIsDisplayed(ownDriver, ItemMasterPage.tbxLostSales);
        String random = String.valueOf(Utility_Functions.xRandomFunction(0, 3));
        sendKeysAndEnter(ItemMasterPage.tbxLostSales, random, "Edit Lost Sales field value");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");

        Utility_Functions.actionKey(Keys.F3, ownDriver);
        commonObj.validateText(MasterPage.pageTitleInventoryManagement, "Inventory Management - Main Menu", "Validating [Inventory Management - Main Menu] page title");
        commonObj.inventoryToItemMaster();
        commonObj.validateText(ItemMasterPage.pageTitle, "ITEM MASTER (I-347)", "Validating [ITEM MASTER] page title");
        searchItemMaster();
        String selectedvalue = getAttribute(ItemMasterPage.tbxLostSales, "value");
        Utility_Functions.xAssertEquals(report, random, selectedvalue, "Validating Lost Sales field value after re-navigation");

    }

    /**
     * Keyword to validate [PO Cost] field in ITEM MASTER (I-347)
     */
    public void validateLostSalesField() {
        String random = String.valueOf(Utility_Functions.xRandomFunction(5, 9));
        sendKeysAndEnter(ItemMasterPage.tbxLostSales, random, "Entering value from 5-9 in Lost Sales field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        String tooltip = getAttribute(ItemMasterPage.tbxLostSales, "title");
        Utility_Functions.xAssertEquals(report, "Error - Lost Sales Limits:  Minimum 0 Maximum 3", tooltip, "Validating error tooltip");
        String color = getElement(ItemMasterPage.tbxLostSales).getCssValue("color");
        Utility_Functions.xAssertEquals(report, "rgba(85, 85, 85, 1)", color, "Validating Lost Sales textbox color for invalid value");

        sendKeysAndEnter(ItemMasterPage.tbxLostSales, "-1", "Entering negative value in Lost Sales field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        tooltip = getAttribute(ItemMasterPage.tbxLostSales, "title");
        Utility_Functions.xAssertEquals(report, "Error - Lost Sales Limits:  Minimum 0 Maximum 3", tooltip, "Validating error tooltip");
        color = getElement(ItemMasterPage.tbxLostSales).getCssValue("color");
        Utility_Functions.xAssertEquals(report, "rgba(85, 85, 85, 1)", color, "Validating Lost Sales textbox color for invalid value");

        sendKeysAndEnter(ItemMasterPage.tbxLostSales, "99", "Entering 2 digit value in Lost Sales field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.tooltip, "99 has an incorrect data length or decimal position.", "Error tooltip is present");
        color = getElement(ItemMasterPage.tbxLostSales).getCssValue("color");
        Utility_Functions.xAssertEquals(report, "rgba(85, 85, 85, 1)", color, "Validating Lost Sales textbox color for invalid value");

        sendKeysAndEnter(ItemMasterPage.tbxLostSales, "9", "Entering 2 digit value in Lost Sales field");
    }

    /**
     * Keyword to enter data in [Extra History] field in ITEM MASTER (I-347)
     */
    public void editExtraHistoryField() {
        Utility_Functions.xIsDisplayed(ownDriver, ItemMasterPage.tbxExtraHistory);
        String random = String.valueOf(Utility_Functions.xRandomFunction(1, 3));
        sendKeysAndEnter(ItemMasterPage.tbxExtraHistory, random, "Edit Extra History field value");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");

        Utility_Functions.actionKey(Keys.F3, ownDriver);
        commonObj.validateText(MasterPage.pageTitleInventoryManagement, "Inventory Management - Main Menu", "Validating [Inventory Management - Main Menu] page title");
        commonObj.inventoryToItemMaster();
        commonObj.validateText(ItemMasterPage.pageTitle, "ITEM MASTER (I-347)", "Validating [ITEM MASTER] page title");
        searchItemMaster();
        String selectedvalue = getAttribute(ItemMasterPage.tbxExtraHistory, "value");
        Utility_Functions.xAssertEquals(report, random, selectedvalue, "Validating Extra History field value after re-navigation");
    }

    /**
     * Keyword to validate [Extra History] field in ITEM MASTER (I-347)
     */
    public void validateExtraHistoryField() {
        String random = String.valueOf(Utility_Functions.xRandomFunction(5, 9));
        sendKeysAndEnter(ItemMasterPage.tbxExtraHistory, random, "Entering value from 5-9 in Extra History field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        String tooltip = getAttribute(ItemMasterPage.tbxExtraHistory, "title");
        Utility_Functions.xAssertEquals(report, "Error - Extra History Limits:  Minimum 1 Maximum 3", tooltip, "Validating error tooltip");
        String color = getElement(ItemMasterPage.tbxExtraHistory).getCssValue("color");
        Utility_Functions.xAssertEquals(report, "rgba(85, 85, 85, 1)", color, "Validating Extra History textbox color for invalid value");

        String negative = "-1";
        sendKeys(ItemMasterPage.tbxExtraHistory, negative, "Entering negative value in Extra History field");
        String value = getAttribute(ItemMasterPage.tbxExtraHistory, "value");
        Utility_Functions.xAssertEquals(report, "1", value, "Extra History field is NOT accepting negative values");

        String twodigits = "99";
        sendKeys(ItemMasterPage.tbxExtraHistory, twodigits, "Entering 2 digit value in Extra History field");
        value = getAttribute(ItemMasterPage.tbxExtraHistory, "value");
        Utility_Functions.xAssertEquals(report, "9", value, "Extra History field is NOT accepting two digit values");
    }

    /**
     * Keyword to validate [Freight Class Code] addition functionality
     */
    public void vrfyAddFreightClassCode() {
        String random = String.valueOf(Utility_Functions.xRandomFunction(10, 99));
        sendKeys(ItemMasterPage.tbxActionFCC, "A", "Entering value in Actions textbox");
        sendKeys(ItemMasterPage.tbxFCC, random, "Entering value in Freight Class Code textbox");
        sendKeysAndEnter(ItemMasterPage.tbxDescriptionFCC, "A", "Entering value in Description Line 1 textbox");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.xUpdateJson("FreightClassCode", random);

        Utility_Functions.actionKey(Keys.F1, ownDriver);
        sendKeysAndEnter(ItemMasterPage.tbxActionFCC, Utility_Functions.xGetJsonData("FreightClassCode"), "Entering value in Freight Class Code textbox for search");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        String value = getText(ItemMasterPage.lblCode);
        Utility_Functions.xAssertEquals(report, Utility_Functions.xGetJsonData("FreightClassCode"), value, "Validating added Freight Class Code");
    }

    /**
     * Keyword to validate [Freight Class Code] field in ITEM MASTER (I-347)
     */
    public void vrfyFreightClassCodeField() {
        sendKeysAndEnter(ItemMasterPage.tbxFreightClassCode, Utility_Functions.xGetJsonData("FreightClassCode"), "Entering value in Freight Class Code textbox for search");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");

        Utility_Functions.actionKey(Keys.F3, ownDriver);
        commonObj.validateText(MasterPage.pageTitleInventoryManagement, "Inventory Management - Main Menu", "Validating [Inventory Management - Main Menu] page title");
        commonObj.inventoryToItemMaster();
        commonObj.validateText(ItemMasterPage.pageTitle, "ITEM MASTER (I-347)", "Validating [ITEM MASTER] page title");
        searchItemMaster();
        String value = getAttribute(ItemMasterPage.tbxFreightClassCode, "value");
        Utility_Functions.xAssertEquals(report, Utility_Functions.xGetJsonData("FreightClassCode"), value, "Validating added Freight Class Code");
    }

    /**
     * Keyword to validate [Freight Class Code] field range in ITEM MASTER (I-347)
     */
    public void vrfyFreightClassCodeFieldRange() {
        sendKeysAndEnter(ItemMasterPage.tbxFreightClassCode, "BX", "Entering invalid value  in Freight Class Code field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        String tooltip = getAttribute(ItemMasterPage.tbxFreightClassCode, "title");
        Utility_Functions.xAssertEquals(report, "Invalid Freight Class", tooltip, "Validating error tooltip");
        String color = getElement(ItemMasterPage.tbxFreightClassCode).getCssValue("color");
        Utility_Functions.xAssertEquals(report, "rgba(85, 85, 85, 1)", color, "Validating Freight Class Code textbox color for invalid value");

        String threeCharacters = "999";
        sendKeys(ItemMasterPage.tbxFreightClassCode, threeCharacters, "Entering 3 character value in Freight Class Code field");
        String value = getAttribute(ItemMasterPage.tbxFreightClassCode, "value");
        Utility_Functions.xAssertEquals(report, "99", value, "Freight Class Code field is NOT accepting 3 character values");
    }

    /**
     * Keyword to verify [MonthsOnWise] field in ITEM MASTER (I-347)
     */
    public void vrfyMonthsOnWiseField() {
        Utility_Functions.xIsDisplayed(ownDriver, ItemMasterPage.lblMonthsOnWise);
    }

    /**
     * Keyword to enter data in [List Price] field in ITEM MASTER (I-347)
     */
    public void editListPriceField() {
        Utility_Functions.xIsDisplayed(ownDriver, ItemMasterPage.txtBoxListPrice);
        String random = String.valueOf(Utility_Functions.xRandomFunction(1, 999999));
        sendKeysAndEnter(ItemMasterPage.txtBoxListPrice, random, "Edit List Price field value");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");

        Utility_Functions.actionKey(Keys.F3, ownDriver);
        commonObj.validateText(MasterPage.pageTitleInventoryManagement, "Inventory Management - Main Menu", "Validating [Inventory Management - Main Menu] page title");
        commonObj.inventoryToItemMaster();
        commonObj.validateText(ItemMasterPage.pageTitle, "ITEM MASTER (I-347)", "Validating [ITEM MASTER] page title");
        searchItemMaster();
        String selectedvalue = getAttribute(ItemMasterPage.txtBoxListPrice, "value").trim();
        selectedvalue = selectedvalue.replaceAll(",", "");
        Utility_Functions.xAssertEquals(report, random+".000000", selectedvalue, "Validating List Price field value after re-navigation");
    }

    /**
     * Keyword to validate range [List Price] field in ITEM MASTER (I-347)
     */
    public void vrfyListPriceFieldRange() {
        String maxValue = "99999999";
        sendKeysAndEnter(ItemMasterPage.txtBoxListPrice, maxValue, "Entering value greater than max accepted value in List Price field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.tooltip, maxValue + " has an incorrect data length or decimal position.", "Error tooltip is present");

        String negativeValue = "-10";
        sendKeysAndEnter(ItemMasterPage.txtBoxListPrice, negativeValue, "Entering negative value in List Price field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
        String value = getAttribute(ItemMasterPage.txtBoxListPrice, "value").trim();
        Utility_Functions.xAssertEquals(report, "10.000000-", value, "Validating List Price field value after entering -10");

        String maxDecimalValue = "123.6666666";
        sendKeysAndEnter(ItemMasterPage.txtBoxListPrice, maxDecimalValue, "Entering value greater than max accepted decimal value in List Price field");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        //commonObj.validateText(ItemMasterPage.tooltip, maxValue + " has too many decimal places. (max: 6)", "Error tooltip is present");

        sendKeysAndEnter(ItemMasterPage.txtBoxListPrice, "0", "Entering 0 in List Price field value");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
        value = getAttribute(ItemMasterPage.txtBoxListPrice, "value").trim();
        Utility_Functions.xAssertEquals(report, ".000000", value, "Validating List Price field value after entering 0");
    }

    /**
     * Keyword to validate calculation of [Gross Margin %] field in ITEM MASTER (I-347)
     */
    public void vrfyGrossMarginCal() {
        String listPriceText1 = getAttribute(ItemMasterPage.txtBoxListPrice, "value").trim();
        String listPriceText = listPriceText1.replaceAll(",", "");
        float listPrice = Float.parseFloat(listPriceText);

        String averagePriceText1 = getText(ItemMasterPage.lblAverage).trim();
        String averagePriceText = averagePriceText1.replaceAll(",", "");
        float averagePrice = Float.parseFloat(averagePriceText);

        float gm = ((listPrice-averagePrice)/listPrice)*100;
        String grossMarginExpected = String.format("%.02f", gm);

        String grossMarginActualText = getAttribute(ItemMasterPage.tbxGrossMargin, "value").trim();
        Utility_Functions.xAssertEquals(report, grossMarginExpected, grossMarginActualText, "Validating Gross Margin % value");
    }

    /**
     * Keyword to enter data in [Min Gross Margin] field in ITEM MASTER (I-347)
     */
    public void editMinGrossMarginField() {
        Utility_Functions.xIsDisplayed(ownDriver, ItemMasterPage.tbxMinGrossMargin);
        String random = String.valueOf(Utility_Functions.xRandomFunction(1, 99));
        sendKeysAndEnter(ItemMasterPage.tbxMinGrossMargin, random, "Edit Min. Gross Margin field value");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(ItemMasterPage.messageAddSuccessful, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");

        Utility_Functions.actionKey(Keys.F3, ownDriver);
        commonObj.validateText(MasterPage.pageTitleInventoryManagement, "Inventory Management - Main Menu", "Validating [Inventory Management - Main Menu] page title");
        commonObj.inventoryToItemMaster();
        commonObj.validateText(ItemMasterPage.pageTitle, "ITEM MASTER (I-347)", "Validating [ITEM MASTER] page title");
        searchItemMaster();
        String selectedvalue = getAttribute(ItemMasterPage.tbxMinGrossMargin, "value").trim();
        selectedvalue = selectedvalue.replaceAll(",", "");
        Utility_Functions.xAssertEquals(report, random, selectedvalue, "Validating Min. Gross Margin field value after re-navigation");
    }

}
