package businesskeywords.Inventory.averagecost;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import commonkeywords.DBCall;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.PurchaseOrders.VendorInvoiceReconciliationPage;
import pages.common.MasterPage;
import pages.inventory.CostAdjustmentPage;
import pages.inventory.ItemMasterPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import pages.warehouse.BinMaintenance.BinMaintenancePage;
import supportLibraries.Utility_Functions;

import java.util.ArrayList;

public class itemMaster extends ReusableLib {

    CommonActions commonObj;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public itemMaster(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
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

    public void exitItemMaster(){
        click(SpecialPriceAllowancePage.btnExit,"Click [F3=Exit]");
    }

    public void itemMasterUI() {
        String[] ids = {"inItemNumber", "inItemDesc1", "InItemDesc2", "inManufacturerCode", "inProductCode", "inVendorCode", "inItemType", "inHstMntRtn", "inPurchasingUOM", "inBuyStdPackQty", "inPdsperStdKG", "inSellingUOM", "inStdPackQty", "inPdsPerStdKG", "inCalcMethod", "inCovFactor", "inItemNotes1"};
        for (String id : ids) {
            String[] idSplit=id.split("in");
            commonObj.validateElementExists(inputFields(id), "Input Field "+idSplit[1]+" is present");
        }
        String[] linkText = {"< Previous Item", "Next Item >", "ABC", "Alternate Item", "Add Item", "Delete Item", "Duplicate Item", "Item-Bin Maintenance", "Lead Time Maintenance", "Lead Time Maintenance", "Refresh", "Rich Data", "e-Comm"};
        for (String text : linkText) {
            commonObj.validateElementExists(linkField(text), "Input Field "+text+" is present");
        }
        String[] spanText = {"Item Details (I-349)", " Item-Bin Details"};
        for (String text : spanText) {
            commonObj.validateElementExists(spanField(text), "Field "+text+" is present");
        }
        String[] divIds = {"Search for Item","Description:","Description 2:","Manufacturer:","Product:","Vendor:","Item Type:","History Months to Retain:","UOM:","Std. Package Quantity:","Pounds per Std Pkg:","UOM:","Std. Package Quantity:","Pounds per Std Pkg:","Absolute ABC:","Calc Method:","Conversion Factor:","Taxable:","Lead Time in Days:","Lead Time Expires:","Serialized:","MID:","Available to Sell:","- With Receipts:","On Hand:","On PO:","Unscheduled:","On SO:","On BO:","List Price:","Gross Margin %:",
        "Min. Gross Margin:","Costs Last:","Average:","Matrix:","PO Cost:","WSS Net Price:","Price Matrix Row:","Quantity Break:","Lost Sales:","Extra History:","Months on Wise:","Freight Class Code:","Primary Bin:","Purchasing Min:","Purchasing Max:","Exclude M/C Rpt:","Auto Replenish:"};
        for (String id : divIds) {
            commonObj.validateElementExists(divField(), "Label "+getText(divField())+" is present");
        }
    }
}
