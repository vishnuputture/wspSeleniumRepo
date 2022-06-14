package businesskeywords.Inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import org.openqa.selenium.By;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import pages.PurchaseOrders.PurchaseOrderDetailsPage;
import pages.inventory.ItemMasterPage;
import pages.inventory.SalesPersonPage;
import pages.pricing.OrderByCustomerPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

import java.beans.PersistenceDelegate;

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
}