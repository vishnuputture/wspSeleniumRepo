package businesskeywords.Inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import commonkeywords.DBCall;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import pages.PurchaseOrders.InventoryReceiptsPage;
import pages.PurchaseOrders.PoEntryConversionFactorPage;
import pages.PurchaseOrders.PurchaseOrderDetailsPage;
import pages.PurchaseOrders.VendorInvoiceReconciliationPage;
import pages.SalesOrders.SalesOrdersPage;
import pages.common.MasterPage;
import pages.inventory.CostAdjustmentPage;
import pages.inventory.ItemMasterPage;
import pages.inventory.SalesPersonPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import pages.warehouse.BinMaintenance.BinMaintenancePage;
import supportLibraries.Utility_Functions;

import java.util.ArrayList;
import java.util.Arrays;

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

    public By selectCodes(String text) {
        return By.xpath("//div[@class='cell odd']/div[text()='" + text + "']");
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

    public void selectMFCode() {
        click(ItemMasterPage.mfSearchIcon, "Click Manufactured search icon");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, selectCodes(jsonData.getData("MF")), "Select Manufacturer Code");
    }

    public void selectPDCode() {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, ItemMasterPage.pdSearchIcon, "Click Product search icon");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, selectCodes(jsonData.getData("PD")), "Select Product Code");
    }

    public void selectVNCode() {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, ItemMasterPage.vnSearchIcon, "Click Vendor search icon");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, selectCodes(jsonData.getData("VN")), "Select Vendor Code");
    }

    public void verifyChangeMFPDVN() {
        selectMFCode();
        selectPDCode();
        selectVNCode();
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, ItemMasterPage.manufacturerCode, "Wait for page to be loaded");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
        String[] mfCode = getAttribute(ItemMasterPage.manufacturerCode, "value").split(" ");
        String[] pdCode = getAttribute(ItemMasterPage.productCode, "value").split(" ");
        String[] vnCode = getAttribute(ItemMasterPage.vendorCode, "value").split(" ");
        Utility_Functions.xAssertEquals(report, mfCode[0], jsonData.getData("MF"), "");
        Utility_Functions.xAssertEquals(report, pdCode[0], jsonData.getData("PD"), "");
        Utility_Functions.xAssertEquals(report, vnCode[0], jsonData.getData("VN"), "");
    }

    public void verifyHistoryMonthsToRetain() {
        sendKeysAndEnter(inputFields("inHstMntRtn"), jsonData.getData("HistoryMonths"), "Enter History Months to Retain");
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
        Utility_Functions.xAssertEquals(report, getAttribute(inputFields("inHstMntRtn"), "value"), jsonData.getData("HistoryMonths"), "");
    }

    public void verifyDescDesc2() {
        click(ItemMasterPage.addItemAction, "Click on add item");
        sendKeys(ItemMasterPage.txtBoxDescription, jsonData.getData("Description"), "Enter description for item number");
        sendKeys(ItemMasterPage.txtBoxUOM, "EA", "Enter UOM");
        click(ItemMasterPage.btnSave, "Click on save changes");
        if (Utility_Functions.xWaitForElementPresent(ownDriver, ItemMasterPage.messageAddSuccessful, 10)) {
            String successMessage = Utility_Functions.getText(ownDriver, ItemMasterPage.messageAddSuccessful);
            System.out.println("Text: " + successMessage);
            Utility_Functions.xAssertEquals(report, "Record successfully added !", successMessage.trim(), "Validating success message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + ItemMasterPage.messageAddSuccessful);
        }
        commonObj.validateText(ItemMasterPage.itemDetail, jsonData.getData("Description").substring(0, 30), "[Description] is present");
    }

    public void selectInvalidMFCode(String mfCode) {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, ItemMasterPage.manufacturerCode, "Wait for page to be loaded");
        sendKeysAndEnter(ItemMasterPage.manufacturerCode, jsonData.getData(mfCode), "Enter Invalid Manufacture");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.manufacturerCode, "title"), "Invalid Manufacturer or Vendor Code.", "Validating Error message");
    }

    public void selectInvalidVNCode(String vnCode) {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, ItemMasterPage.vendorCode, "Wait for page to be loaded");
        sendKeysAndEnter(ItemMasterPage.vendorCode, jsonData.getData(vnCode), "Invalid Manufacturer or Vendor Code.");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.vendorCode, "title"), "Invalid Manufacturer or Vendor Code.", "Validating Error message");
    }

    public void selectInvalidPDCode(String pdCode) {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, ItemMasterPage.productCode, "Wait for page to be loaded");
        sendKeysAndEnter(ItemMasterPage.productCode, jsonData.getData(pdCode), "Enter Invalid Product Code");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.productCode, "title"), "Invalid Product Code", "Validating Error message");
    }

    public void verifyNegDescDesc2() {
        selectInvalidMFCode("AlphaSpecialMF");
        selectInvalidVNCode("AlphaSpecialVN");
        selectInvalidPDCode("AlphaSpecialPD");
        selectInvalidMFCode("AlphaNumericMF");
        selectInvalidVNCode("AlphaNumericVN");
        selectInvalidPDCode("AlphaNumericPD");
        selectInvalidMFCode("InvalidVN");
        selectInvalidVNCode("InvalidMF");
        selectInvalidPDCode("InvalidPD");
    }

    public void itemType(String value) {
        sendKeysAndEnter(ItemMasterPage.itemType, value, "Enter [" + value + "] into Item Type");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.itemType, "title"), "Can not change item type", "");
    }

    public void verifyItemType() {
        clearText(ItemMasterPage.itemType);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.itemType, "title"), "Can not change item type", "");
        itemType("A");
        itemType("AA");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.historyMonth, "title"), "Invalid Number", "");
        itemType("&");
        itemType("2");
    }

    public void verifyUOMSearch() {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, ItemMasterPage.uomSearch, "Click UOM search Icon");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, selectCodes("BD"), "Select UOM Code");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.txtBoxUOM, "value"), "BD", "");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, ItemMasterPage.txtBoxUOM, "Click [UOM] input field");
        Utility_Functions.actionKey(Keys.F4, ownDriver);
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, SalesOrdersPage.btnBack, "Click [Cancel] Button");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, ItemMasterPage.txtBoxUOM, "Wait for Page to be loaded");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.txtBoxUOM, "value"), "BD", "");
        sendKeysAndEnter(ItemMasterPage.txtBoxUOM, jsonData.getData("UOM"), "Enter [" + jsonData.getData("UOM") + "] into UOM");
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.txtBoxUOM, "value"), jsonData.getData("UOM"), "UOM matches");
    }

    public void verifyStdPkgQtyPoundStdPkg() {
        sendKeysAndEnter(ItemMasterPage.stdPkgQty, jsonData.getData("StdPackageQty"), "Enter [" + jsonData.getData("StdPackageQty") + "] into Std. Package Quantity");
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.stdPkgQty, "value"), jsonData.getData("StdPackageQty"), "Std. Package Quantity matches");
        sendKeysAndEnter(ItemMasterPage.poundStdPkg, jsonData.getData("PoundsPerStdPkg"), "Enter [" + jsonData.getData("PoundsPerStdPkg") + "] into Std. Package Quantity");
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.poundStdPkg, "value"), jsonData.getData("PoundsPerStdPkg") + ".000", "Std. Package Quantity matches");
    }

    public void verifyMOU(String uom) {
        clearText(ItemMasterPage.txtBoxUOM);
        sendKeysAndEnter(ItemMasterPage.txtBoxUOM, jsonData.getData(uom), "Enter [" + jsonData.getData(uom) + "] into UOM");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.txtBoxUOM, "title"), "Invalid U/M", "");
        sendKeys(ItemMasterPage.txtBoxUOM, "EA", "Enter [EA] into UOM");
    }

    public void verifyPoundStdPkg(String poundStdPkg) {
        clearText(ItemMasterPage.poundStdPkg);
        sendKeysAndEnter(ItemMasterPage.poundStdPkg, jsonData.getData(poundStdPkg), "Enter [" + jsonData.getData(poundStdPkg) + "] into Pounds per Std Pkg:");
        String numberOnly = jsonData.getData(poundStdPkg).replaceAll("[^1-9]", "");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.poundStdPkg, "value"), numberOnly + ".000", "");
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
    }

    public void verifyStdPkgQty(String stdPkgQty) {
        clearText(ItemMasterPage.stdPkgQty);
        sendKeysAndEnter(ItemMasterPage.stdPkgQty, jsonData.getData(stdPkgQty), "Enter [" + jsonData.getData(stdPkgQty) + "] into Std. Package Quantity:");
        String numberOnly = jsonData.getData(stdPkgQty).replaceAll("[^1-9]", "");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.stdPkgQty, "value"), numberOnly, "");
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
    }

    public void verifyUOMStdPkgQtyPndStdPkg() {
        verifyMOU("UOMSpecialNum");
        verifyMOU("UOMSpecialAlpha");
        verifyMOU("UOMDecimal");
        verifyStdPkgQty("StdPackageQtySpecialNum");
        verifyStdPkgQty("StdPackageQtyDec");
        verifyStdPkgQty("StdPackageQtySpecialAlphaNum");
        verifyPoundStdPkg("PoundsPerStdPkgSpecialNum");
        verifyPoundStdPkg("PoundsPerStdPkgDec");
        verifyPoundStdPkg("PoundsPerStdPkgSpecialAlphaNum");
    }

    public void verifyUOMSearchPurchasing() {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, ItemMasterPage.uomSearchPur, "Click UOM search Icon");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, selectCodes("BD"), "Select UOM Code");
        Utility_Functions.xAssertEquals(report, getAttribute(PoEntryConversionFactorPage.inPurchasingUOM, "value"), "BD", "");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, PoEntryConversionFactorPage.inPurchasingUOM, "Click [UOM] input field");
        Utility_Functions.actionKey(Keys.F4, ownDriver);
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, SalesOrdersPage.btnBack, "Click [Cancel] Button");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, PoEntryConversionFactorPage.inPurchasingUOM, "Wait for Page to be loaded");
        Utility_Functions.xAssertEquals(report, getAttribute(PoEntryConversionFactorPage.inPurchasingUOM, "value"), "BD", "");
        sendKeysAndEnter(PoEntryConversionFactorPage.inPurchasingUOM, jsonData.getData("UOM"), "Enter [" + jsonData.getData("UOM") + "] into UOM");
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
        Utility_Functions.xAssertEquals(report, getAttribute(PoEntryConversionFactorPage.inPurchasingUOM, "value"), jsonData.getData("UOM"), "UOM matches");
    }

    public void verifyStdPkgQtyPoundStdPkgPurchasing() {
        sendKeysAndEnter(ItemMasterPage.txtInputPkgQuantity, jsonData.getData("StdPackageQty"), "Enter [" + jsonData.getData("StdPackageQty") + "] into Std. Package Quantity");
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.txtInputPkgQuantity, "value"), jsonData.getData("StdPackageQty"), "Std. Package Quantity matches");
        sendKeysAndEnter(ItemMasterPage.poundStdPkgPur, jsonData.getData("PoundsPerStdPkg"), "Enter [" + jsonData.getData("PoundsPerStdPkg") + "] into Std. Package Quantity");
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.poundStdPkgPur, "value"), jsonData.getData("PoundsPerStdPkg") + ".000", "Std. Package Quantity matches");
    }

    public void verifyMOUPurchasing(String uom) {
        clearText(PoEntryConversionFactorPage.inPurchasingUOM);
        sendKeysAndEnter(PoEntryConversionFactorPage.inPurchasingUOM, jsonData.getData(uom), "Enter [" + jsonData.getData(uom) + "] into UOM");
        Utility_Functions.xAssertEquals(report, getAttribute(PoEntryConversionFactorPage.inPurchasingUOM, "title"), "Invalid U/M", "");
        sendKeys(PoEntryConversionFactorPage.inPurchasingUOM, "EA", "Enter [EA] into UOM");
    }

    public void verifyPoundStdPkgPurchasing(String poundStdPkg) {
        clearText(ItemMasterPage.poundStdPkgPur);
        sendKeysAndEnter(ItemMasterPage.poundStdPkgPur, jsonData.getData(poundStdPkg), "Enter [" + jsonData.getData(poundStdPkg) + "] into Pounds per Std Pkg:");
        String numberOnly = jsonData.getData(poundStdPkg).replaceAll("[^1-9]", "");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.poundStdPkgPur, "value"), numberOnly + ".000", "");
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
    }

    public void verifyStdPkgQtyPurchasing(String stdPkgQty) {
        clearText(ItemMasterPage.txtInputPkgQuantity);
        sendKeysAndEnter(ItemMasterPage.txtInputPkgQuantity, jsonData.getData(stdPkgQty), "Enter [" + jsonData.getData(stdPkgQty) + "] into Std. Package Quantity:");
        String numberOnly = jsonData.getData(stdPkgQty).replaceAll("[^1-9]", "");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.txtInputPkgQuantity, "value"), numberOnly, "");
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
    }

    public void verifyUOMStdPkgQtyPndStdPkgPurchasing() {
        verifyMOUPurchasing("UOMSpecialNum");
        verifyMOUPurchasing("UOMSpecialAlpha");
        verifyMOUPurchasing("UOMDecimal");
        verifyStdPkgQtyPurchasing("StdPackageQtySpecialNum");
        verifyStdPkgQtyPurchasing("StdPackageQtyDec");
        verifyStdPkgQtyPurchasing("StdPackageQtySpecialAlphaNum");
        verifyPoundStdPkgPurchasing("PoundsPerStdPkgSpecialNum");
        verifyPoundStdPkgPurchasing("PoundsPerStdPkgDec");
        verifyPoundStdPkgPurchasing("PoundsPerStdPkgSpecialAlphaNum");
    }

    public void verifyCalcMethod(String calcMethod) {
        clearText(ItemMasterPage.calcMethod);
        sendKeysAndEnter(ItemMasterPage.calcMethod, calcMethod, "Enter " + calcMethod + " into Calc Method:");
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
    }

    public void enterCalcMethodAndVerify(String calMethod) {
        verifyCalcMethod(calMethod);
        exitItemMaster();
        inventoryToItemMasterScreen();
        searchItem();
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.calcMethod, "value"), calMethod, "");
    }

    public void verifyChangeCalculationMethod() {
        enterCalcMethodAndVerify("S");
        enterCalcMethodAndVerify("C");
        enterCalcMethodAndVerify("M");
        enterCalcMethodAndVerify("Q");
        enterCalcMethodAndVerify("X");
    }

    public void verifyPurchasingMinMax() {
        verifyCalcMethod("X");
        sendKeys(ItemMasterPage.purchasingMin, jsonData.getData("PurchasingMin"), "Enter [" + jsonData.getData("PurchasingMin") + "] into Purchasing Min");
        sendKeysAndEnter(ItemMasterPage.purchasingMax, jsonData.getData("PurchasingMax"), "Enter [" + jsonData.getData("PurchasingMax") + "] into Purchasing Max And Hit Enter");
        Utility_Functions.xAssertEquals(report, "Error - Maximum Quantity less than Minimun Quantity", getAttribute(ItemMasterPage.purchasingMin, "title"), "Error in Purchasing Min field");
        Utility_Functions.xAssertEquals(report, "Error - Maximum Quantity less than Minimun Quantity", getAttribute(ItemMasterPage.purchasingMax, "title"), "Error in Purchasing Max field");
        sendKeysAndEnter(ItemMasterPage.purchasingMin, "" + (Integer.parseInt(jsonData.getData("PurchasingMin")) + 100) + "", "Enter [" + (Integer.parseInt(jsonData.getData("PurchasingMin")) + 100) + "] into Purchasing Max And Hit Enter");
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
    }

    public void verifyConventionalFactor() {
        successToasterMsg(ItemMasterPage.inCovFactor,"ConventionalFactor");
        exitItemMaster();
        inventoryToItemMasterScreen();
        searchItem();
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.inCovFactor, "value"), jsonData.getData("ConventionalFactor") + ".000", "");
    }

    public void verifyTaxable(String option) {
        Utility_Functions.xSelectDropdownByName(ownDriver, report, ItemMasterPage.inTaxable, option, "Select [" + option + "] Taxable");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
        exitItemMaster();
        inventoryToItemMasterScreen();
        searchItem();
        Utility_Functions.xAssertEquals(report, option.substring(0, 1), getAttribute(ItemMasterPage.inTaxable, "value"), "");
    }

    public void verifyTaxableOption() {
        verifyTaxable("Yes");
        verifyTaxable("No");
    }

    public void verifySerialized() {
        String marginPX = ownDriver.findElement(ItemMasterPage.verifySerializedBtn).getCssValue("margin-right");
        click(ItemMasterPage.btnSerialized, "Click Serialized");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
        if (!marginPX.equals(ownDriver.findElement(ItemMasterPage.verifySerializedBtn).getCssValue("margin-right"))) {
        } else {
            try {
                throw new Exception("Serialized toggle not working");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void verifySerializedOption() {
        verifySerialized();
        verifySerialized();
    }

    public void verifyFieldCalc(String calcMethod){
        clearText(ItemMasterPage.calcMethod);
        sendKeysAndEnter(ItemMasterPage.calcMethod, jsonData.getData(calcMethod),"Enter calc method: "+jsonData.getData(calcMethod));
        String errorTitle=getAttribute(ItemMasterPage.calcMethod,"title");
        Utility_Functions.xAssertEquals(report, errorTitle, "Invalid EOQ", "");
    }

    public void verifyFieldCalculationMethod() {
        successToasterMsg(ItemMasterPage.calcMethod,"TwoCharacterCalcMethod");
        verifyFieldCalc("InvalidCalcMethod");
        verifyFieldCalc("NumericCalcMethod");
        verifyFieldCalc("SpecialCharCalcMethod");
    }

    public void verifyFieldConFactor(String conversionFactor,String errorMsg){
        clearText(ItemMasterPage.tbxConvFactor);
        sendKeysAndEnter(ItemMasterPage.tbxConvFactor, jsonData.getData(conversionFactor),"Enter Conversion Factor: "+jsonData.getData(conversionFactor));
        commonObj.validateText(ItemMasterPage.tipIcon, errorMsg, "Message ["+errorMsg+"] is present");

    }

    public void successToasterMsg(By by,String data){
        sendKeysAndEnter(by, jsonData.getData(data),"Enter "+ data.split("") +": "+jsonData.getData(data));
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
    }

    public void verifyFieldConventionalFactor() {
        successToasterMsg(ItemMasterPage.tbxConvFactor,"ZeroConvFact");
        successToasterMsg(ItemMasterPage.tbxConvFactor,"NineDigitConvFact");
        successToasterMsg(ItemMasterPage.tbxConvFactor,"SpecialDigitConvFact");
        successToasterMsg(ItemMasterPage.tbxConvFactor,"NegativeConvFact");
        verifyFieldConFactor("DecimalConvFact", jsonData.getData("DecimalConvFact")+" has too many decimal places. (max: 3)");
        successToasterMsg(ItemMasterPage.tbxConvFactor,"ZeroConvFact");
    }

    public void verifyUIAbsoluteABC() {
        sendKeys(ItemMasterPage.itemNotes1, jsonData.getData("ItemNote1"),"Enter Item Note in 1st line");
        sendKeys(ItemMasterPage.itemNote2,jsonData.getData("ItemNote2"),"Enter Item Note in 2nd line");
        sendKeysAndEnter(ItemMasterPage.itemNote3,jsonData.getData("ItemNote3"),"Enter Item Note in 3rd line");
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
    }

    public void verifyItemsNotes(){
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.itemNotes1,"value").replace("$", "").trim(), jsonData.getData("ItemNote1").toUpperCase().trim(), "");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.itemNote2,"value").replace("$", "").trim(), jsonData.getData("ItemNote2").toUpperCase().trim(), "");
        Utility_Functions.xAssertEquals(report, getAttribute(ItemMasterPage.itemNote3,"value").replace("$", "").trim(), jsonData.getData("ItemNote3").toUpperCase().trim(), "");
        clearText(ItemMasterPage.itemNotes1);
        clearText(ItemMasterPage.itemNote2);
        clearText(ItemMasterPage.itemNote3);
        Utility_Functions.actionKey(Keys.ENTER,ownDriver);
        commonObj.validateText(InventoryReceiptsPage.growlText, "Fields have recently been changed=>VERIFY CHANGES!", "[Fields have recently been changed=>VERIFY CHANGES!] toaster is present");
    }
}
