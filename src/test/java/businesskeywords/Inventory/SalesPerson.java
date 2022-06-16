package businesskeywords.Inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import org.openqa.selenium.By;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.PurchaseOrders.PurchaseOrderDetailsPage;
import pages.common.MasterPage;
import pages.inventory.ItemMasterPage;
import pages.inventory.SalesPersonPage;
import pages.pricing.OrderByCustomerPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

import java.beans.PersistenceDelegate;
import java.util.List;

public class SalesPerson extends ReusableLib {

    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public SalesPerson(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * This method validates the title of Sales Person Page
     */
    public void navigateToSalesPersonScreen() {
        commonObj.masterToInventory();
        commonObj.inventoryToSalesPerson();
    }

    public void exitFromSAlesPersonScreen() {
        click(OrderByCustomerPage.exitutton, "Click [F3 - Exit]");
        commonObj.validateText(SpecialPriceAllowancePage.header, "Inventory Management - Main Menu", "Validating Inventory Management - Main Menu page title");
    }

    public By linkElement(String text) {
        return By.xpath("(//a[text()='" + text + "'])[1]");
    }

    public By hElement(String text) {
        return By.xpath("//h3[text()='" + text + "']");
    }

    public void verifyHeaders() {
        String[] headers = {"Actions", "CALCULATIONS", "Quick View", "Item Notes"};
        for (String header : headers)
            commonObj.validateText(By.xpath("//h3[text()='" + header + "']"), header, header + " header is present");
    }

    public void verifyCalculationLabels() {
        String[] labels = {"Cost:", "Price:", "Quantity:"};
        for (String label : labels)
            commonObj.validateText(By.xpath("//div[text()='" + label + "']"), label, label + " header is present");
    }

    public void verifyInventoryLabels() {
        String[] labels = {"Inventory", "Quantity", "Direct", "Purchasing"};
        for (String label : labels)
            commonObj.validateElementExists(By.xpath("//div[contains(text(),'" + label + "')]"), label + " header is present");
    }

    public By divElement(String text) {
        return By.xpath("//div[text()='" + text + "']");
    }

    public By linkIdEle(String id) {
        return By.xpath("//div[@id='" + id + "']/a");
    }

    public void verifyLinks() {
        int i = 0;
        String links[] = {"Hyperlink1", "Hyperlink2", "alternateItem", "CustomerNotes_copy", "directjobqty_copy", "inventoryOverValue_copy", "lnkItemBinMaint", "itemLedger", "jobUsage", "kitMaintenance", "ordersByItem", "preferedCompanyUsage", "quotesByItem", "richData"};
        String linksText[] = {"< Previous Item", "Next Item >", "Alternate Item", "Customer Notes", "Direct/Job Qty", "IOV", "Item-Bin Maintenance", "Item Ledger", "Job Usage", "Kit Maintenance", "Orders by Item", "Preferred Co. Usage", "Quotes by Item", "Rich Data", ""};
        for (String link : links)
            while (!linksText[i].equals("")) {
                commonObj.validateText(linkIdEle(link), linksText[i], linksText[i] + " is present");
                i++;
                break;
            }
    }

    public void verifyTextBoxFields() {
        commonObj.validateElementExists(SalesPersonPage.itemNumberSearchIcon, "[Search for Item] Text box is present");
        commonObj.validateElementExists(SalesPersonPage.customerTextBox, "[Customer] Text box is present");
        commonObj.validateElementExists(SalesPersonPage.pricingColumn, "[Pricing Column] Text box is present");
        commonObj.validateElementExists(SalesPersonPage.rowStatic, "[Row] Text is present");
        commonObj.validateElementExists(SalesPersonPage.itemNumberSearchIcon, "Search Icon for Search for Item is present");
        commonObj.validateElementExists(SalesPersonPage.customerSearchIcon, "Search Icon for Customer is present");
        commonObj.validateElementExists(SalesPersonPage.pricingColSearchIcon, "Search Icon for Pricing Column is present");
        commonObj.validateElementExists(SalesPersonPage.attachIcon, "Attach icon is present");
        commonObj.validateElementExists(SalesPersonPage.viewIcon, "View Icon is present");
    }

    /**
     * This method UI of Sales Person Page
     */
    public void verifySalesPersonScreenUI() {
        verifyTextBoxFields();
        verifyLinks();
        verifyHeaders();
        verifyCalculationLabels();
        verifyInventoryLabels();
    }

    public String navigateToNextItem() {
        sendKeysAndEnter(SalesPersonPage.searchForItem, jsonData.getData("ItemNo"), "Enter Item Number");
        click(linkIdEle("Hyperlink2"), "Click [Next Item >]");
        String nextItem = getAttribute(SalesPersonPage.searchForItem, "value").trim();
        if (nextItem.equals(jsonData.getData("ItemNo"))) {
            try {
                throw new Exception("Doesn't move to Next Item");
            } catch (Exception e) {
            }
        }
        return nextItem;
    }

    public void navigateNextBackLink() {
        navigateToNextItem();
        click(linkIdEle("Hyperlink1"), "Click [Previous Item]");
        String previousItem = getAttribute(SalesPersonPage.searchForItem, "value").trim();
        Utility_Functions.xAssertEquals(report, jsonData.getData("ItemNo"), previousItem, "");
    }

    public void navigateToItemMasterBrowser() {
        click(SalesPersonPage.searchForItem, "Click [Search For Item] input field");
        Utility_Functions.actionKey(Keys.F4, ownDriver);
        commonObj.validateText(PurchaseOrderDetailsPage.poPrintSendHeader, "Item Master Browse - Local", "[Item Master Browse - Local] title is present");
    }

    public void navigateToMatrixColumnBrowser() {
        click(SalesPersonPage.pricingColumn, "Click [F4 - Search for Pricing Columns] input field");
        Utility_Functions.actionKey(Keys.F4, ownDriver);
        commonObj.validateText(SpecialPriceAllowancePage.header, "Matrix Column Browse", "[Matrix Column Browse] title is present");
    }

    public void verifyItemDetails() {
        String itemNo = getText(ItemMasterPage.itemNumber);
        String itemDesc = getText(SalesPersonPage.itemDescription).trim();
        sendKeysAndEnter(CustomerGroupMaintenancePage.groupOptField2, "1", "Select an Item");
        String itemNum = getAttribute(SalesPersonPage.searchForItem, "value").trim();
        Utility_Functions.xAssertEquals(report, itemNum, itemNo, "");
        commonObj.validateText(SalesPersonPage.itemDesc, itemDesc, itemDesc + " is present");
    }

    public void verifySearchItem() {
        navigateToItemMasterBrowser();
        verifyItemDetails();
        click(SalesPersonPage.searchIconForItem, "Click Search Icon");
        verifyItemDetails();
    }

    public void invalidItemValidation(String data){
        sendKeysAndEnter(SalesPersonPage.searchForItem, jsonData.getData(data), "Enter [" + jsonData.getData(data) + "] into Search FOr Item field");
        commonObj.validateText(SalesPersonPage.errorItem, "E501", "[E501] is present");
    }

    public void verifySearchItemField() {
        invalidItemValidation("InvalidNumber");
        invalidItemValidation("AlphaNumeric");
        invalidItemValidation("NegativeNumber");
        invalidItemValidation("SpecialCharacterNumber");
    }

    public void verifyPricingColumn() {
        navigateToItemMasterBrowser();
        verifyItemDetails();
        navigateToMatrixColumnBrowser();
        String columnName = getText(SpecialPriceAllowancePage.gName).trim();
        sendKeysAndEnter(CustomerGroupMaintenancePage.groupOptField1, "1", "Select an Item");
        String columnNm = getAttribute(SalesPersonPage.pricingColumn, "value").trim();
        Utility_Functions.xAssertEquals(report,columnName,columnNm,"");
        click(SalesPersonPage.pricingColumn, "Click [F4 - Search for Pricing Columns] input field");
        click(SalesPersonPage.pricingColSearchIcon,"Click Search Icon");
        String clnName = getText(SpecialPriceAllowancePage.gName).trim();
        sendKeysAndEnter(CustomerGroupMaintenancePage.groupOptField1, "1", "Select an Item");
        String clnNm = getAttribute(SalesPersonPage.pricingColumn, "value").trim();
        Utility_Functions.xAssertEquals(report,clnName,clnNm,"");
    }

    public void verifyPricingNoRow(){
        navigateToItemMasterBrowser();
        verifyItemDetails();
        navigateToMatrixColumnBrowser();
        String columnName = getText(SpecialPriceAllowancePage.gName).trim();
        sendKeysAndEnter(CustomerGroupMaintenancePage.groupOptField1, "1", "Select an Item");
        String columnNm = getAttribute(SalesPersonPage.pricingColumn, "value").trim();
        Utility_Functions.xAssertEquals(report,columnName,columnNm,"");
        commonObj.validateText(SalesPersonPage.noRow, "No Row", "[No Row] is present");
    }

    public void verifyOnlyRowPricing(){
        sendKeysAndEnter(SalesPersonPage.searchForItem, jsonData.getData("ItemNo"),"Search For Item" );
        commonObj.validateElementExists(SalesPersonPage.fMROW, getText(SalesPersonPage.fMROW) + " Row is present");
    }

    public void verifyOnlyMultiplierPricing(){
        sendKeysAndEnter(SalesPersonPage.searchForItem, jsonData.getData("ItemNo"),"Search For Item" );
        commonObj.validateElementExists(SalesPersonPage.fMROW, getText(SalesPersonPage.fMROW) + " Row is present");
        navigateToMatrixColumnBrowser();
        sendKeysAndEnter(SalesPersonPage.positionPricingColumn, jsonData.getData("ColumnName"),"Search For Pricing Column" );
        sendKeysAndEnter(CustomerGroupMaintenancePage.groupOptField1, "1", "Select an Item");
        commonObj.validateElementExists(SalesPersonPage.noRow, getText(SalesPersonPage.noRow) + "[No Mult] is present");
    }

    public void verifyMultiplierPricing(){
        sendKeysAndEnter(SalesPersonPage.searchForItem, jsonData.getData("ItemNo"),"Search For Item" );
        commonObj.validateElementExists(SalesPersonPage.fMROW, getText(SalesPersonPage.fMROW) + " Row is present");
        navigateToMatrixColumnBrowser();
        sendKeysAndEnter(CustomerGroupMaintenancePage.groupOptField1, "1", "Select an Item");
        commonObj.validateElementExists(SalesPersonPage.mutlType, "Multiplier Type: "+getText(SalesPersonPage.mutlType) + " is present");
        commonObj.validateElementExists(SalesPersonPage.mutlValue, "Multiplier Value: "+getText(SalesPersonPage.mutlValue) + " is present");
    }

    public void invalidColumnValidation(String data){
        sendKeysAndEnter(SalesPersonPage.pricingColumn, jsonData.getData(data), "Enter [" + jsonData.getData(data) + "] into Pricing Column field");
        commonObj.validateText(SalesPersonPage.invalidColumn, "Invalid Column", "[Invalid Column] is present");
    }

    public void verifyPricingColumnField() {
        navigateToItemMasterBrowser();
        verifyItemDetails();
        invalidColumnValidation("InvalidNumber");
        invalidColumnValidation("AlphaNumeric");
        invalidColumnValidation("NegativeNumber");
        invalidColumnValidation("SpecialCharacterNumber");
    }

    /**
     * Keyword to enter Item No in [Search for Item] textbox in SalesPerson Inquiry Page
     */
    public void searchForItem(){
        sendKeysAndEnter(SalesPersonPage.searchForItem, jsonData.getData("ItemNo"),"Search For Item" );
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to enter Item No from Integration4.json in [Search for Item] textbox in SalesPerson Inquiry Page
     */
    public void searchForItem2(){
        sendKeysAndEnter(SalesPersonPage.searchForItem, Utility_Functions.xGetJsonData("ItemNoMaster"), "Search For Item" );
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to validate details in [Quick View] section after entering Item No
     */
    public void vrfyQuickView(){
        String costTxt = getAttribute(SalesPersonPage.costCalculation, "value");
        String priceTxt = getAttribute(SalesPersonPage.priceCalculation, "value");
        if (priceTxt.contains(",")){
            priceTxt = priceTxt.replaceAll(",", "");
        }
        double price = Double.parseDouble(priceTxt);

        String grossMarginPercentTxt = getAttribute(SalesPersonPage.grossMargCalculation, "value");
        if (grossMarginPercentTxt.contains("-")){
            grossMarginPercentTxt = grossMarginPercentTxt.replaceAll("-", "");
        }
        double grossMarginPercent = Double.parseDouble(grossMarginPercentTxt);
        double grossMarginValue = (grossMarginPercent/100)*price;
        String expectedGrossMarginValue = Double.toString(grossMarginValue);

        String grossMarginQVText = getText(SalesPersonPage.grossMargin);
        if (grossMarginQVText.contains(",")){
            grossMarginQVText = grossMarginQVText.replaceAll(",", "");
        }
        if (grossMarginQVText.contains("-")){
            grossMarginQVText = grossMarginQVText.replaceAll("-", "");
        }
        double grossMarginQV = Double.parseDouble(grossMarginQVText);
        String actualGrossMarginValue = Double.toString(grossMarginQV);

        Utility_Functions.xAssertEquals(report, expectedGrossMarginValue, actualGrossMarginValue, "Quick View [Gross Margin $] is displayed properly");
        commonObj.validateText(SalesPersonPage.listPrice, priceTxt.trim(), "Quick View [List Price] is displayed properly");
        commonObj.validateText(SalesPersonPage.matrixCost, costTxt.trim(), "Quick View [Matrix Cost] is displayed properly");
        commonObj.validateText(SalesPersonPage.marginPercent, getAttribute(SalesPersonPage.grossMargCalculation, "value").trim(), "Quick View [Margin Percent] is displayed properly");
    }

    /**
     * Keyword to validate Item Notes in SalesPerson Inquiry Page
     */
    public void vrfyItemNotes(){
        commonObj.validateElementExists(SalesPersonPage.hdrItemNotes, "The [Item Notes] section is present");
        String itemNotesExpected = jsonData.getData("ItemNotes");
        commonObj.validateText(SalesPersonPage.itemNotes1, itemNotesExpected, "Validating Item Notes");
    }

    /**
     * Keyword to validate Inventory section sub-headers in SalesPerson Inquiry Page
     */
    public void vrfyInventoryHeaders(){
        commonObj.validateElementExists(SalesPersonPage.hdrInventory, "Header [Inventory] is present");
        commonObj.validateElementExists(SalesPersonPage.hdrQuantity, "Header [Quantity] is present");
        commonObj.validateElementExists(SalesPersonPage.hdrDirectShips, "Header [Direct Ships] is present");
        commonObj.validateElementExists(SalesPersonPage.hdrPurchasing, "Header [Purchasing] is present");
        commonObj.validateElementExists(SalesPersonPage.hdrQuantityBreak, "Header [Quantity Break] is present");
    }

    /**
     * Keyword to validate Inventory section field labels in SalesPerson Inquiry Page
     */
    public void vrfyInventoryTabFieldLabels(){
        commonObj.validateElementExists(SalesPersonPage.lblInventoryAvaiToSell, "Field label [Available to Sell:] is present under Inventory");
        commonObj.validateElementExists(SalesPersonPage.lblInventoryWithReceipts, "Field label [with Receipts:] is present under Inventory");
        commonObj.validateElementExists(SalesPersonPage.lblInventorySelling, "Field label [Selling UOM:] is present under Inventory");
        commonObj.validateElementExists(SalesPersonPage.lblInventoryPackage, "Field label [Package Qty:] is present under Inventory");

        commonObj.validateElementExists(SalesPersonPage.lblQtyOnHand, "Field label [On Hand:] is present under Quantity");
        commonObj.validateElementExists(SalesPersonPage.lblQtyInHold, "Field label [In Hold:] is present under Quantity");
        commonObj.validateElementExists(SalesPersonPage.lblQtyOnPO, "Field label [On PO:] is present under Quantity");
        commonObj.validateElementExists(SalesPersonPage.lblQtyUnscheduled, "Field label [Unscheduled:] is present under Quantity");
        commonObj.validateElementExists(SalesPersonPage.lblQtyOnSO, "Field label [On SO:] is present under Quantity");
        commonObj.validateElementExists(SalesPersonPage.lblQtyOnBO, "Field label [On BO:] is present under Quantity");
        commonObj.validateElementExists(SalesPersonPage.lblQtyOnSQ, "Field label [On SQ:] is present under Quantity");

        commonObj.validateElementExists(SalesPersonPage.lblDirShipOnHand, "Field label [On Hand:] is present under Direct Ships");
        commonObj.validateElementExists(SalesPersonPage.lblDirShipInHold, "Field label [In Hold:] is present under Direct Ships");
        commonObj.validateElementExists(SalesPersonPage.lblDirShipOnPO, "Field label [On PO:] is present under Direct Ships");
        commonObj.validateElementExists(SalesPersonPage.lblDirShipOnSO, "Field label [On SO:] is present under Direct Ships");
        commonObj.validateElementExists(SalesPersonPage.lblDirShipOnBO, "Field label [On BO:] is present under Direct Ships");

        commonObj.validateElementExists(SalesPersonPage.lblPrchsngPurchase, "Field label [Purchase UOM:] is present under Purchasing");
        commonObj.validateElementExists(SalesPersonPage.lblPrchsngWeight, "Field label [Weight(lbs):] is present under Purchasing");
        commonObj.validateElementExists(SalesPersonPage.lblPrchsngPckg, "Field label [Package Qty:] is present under Purchasing");
        commonObj.validateElementExists(SalesPersonPage.lblPrchsngConvFact, "Field label [Conversion Factor:] is present under Purchasing");
        commonObj.validateElementExists(SalesPersonPage.lblPrchsngLT, "Field label [L/T:] is present under Purchasing");
    }

    /**
     * Keyword to validate Item-Bin Details section in SalesPerson Inquiry Page
     */
    public void vrfyItemBinDetailsTab(){
        click(SalesPersonPage.tabItemBinDetails);

        List<WebElement> lstTableHeaders = getListElement(SalesPersonPage.lstBinDetailsTableHeader);
        List<String> lstHeaderText = Utility_Functions.xGetTextVisibleListString(ownDriver, lstTableHeaders);

        String[] headers = {"Bin Location", "Bin Type", "Bin Condition", "Bin On Hand", "Available To Pick", "Bin Min", "Bin Max", "Zone"};
        for (int i=0; i<headers.length; i++){
            if (headers[i].equalsIgnoreCase(lstHeaderText.get(i).trim()))
                report.updateTestLog("Verify Item Bin Details Headers", "Header ["+lstHeaderText.get(i)+"] is matching expected value ["+headers[i]+"]", Status.PASS);
            else
                report.updateTestLog("Verify Item Bin Details Headers", "Header ["+lstHeaderText.get(i)+"] is NOT matching expected value ["+headers[i]+"]", Status.FAIL);
        }
        commonObj.validateElementExists(SalesPersonPage.lnkExportToExcel, "Link to download excel is present under Purchasing");
        commonObj.validateElementExists(SalesPersonPage.lblTotal, "Field label [Total On Hand + In Hold] is present under Purchasing");
    }
}