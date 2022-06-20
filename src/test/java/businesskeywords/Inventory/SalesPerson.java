package businesskeywords.Inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import pages.PurchaseOrders.PurchaseOrderDetailsPage;
import pages.PurchaseOrders.PurchaseOrderInquiryPage;
import pages.PurchaseOrders.VendorInvoiceReconciliationPage;
import pages.PurchaseOrders.VendorNotesPage;
import pages.inventory.ItemMasterPage;
import pages.inventory.SalesPersonPage;
import pages.pricing.OrderByCustomerPage;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
import pages.pricing.pricingmatrix.PricingMatrixPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

import java.awt.*;
import java.awt.event.KeyEvent;

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

    public void invalidItemValidation(String data) {
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
        Utility_Functions.xAssertEquals(report, columnName, columnNm, "");
        click(SalesPersonPage.pricingColumn, "Click [F4 - Search for Pricing Columns] input field");
        click(SalesPersonPage.pricingColSearchIcon, "Click Search Icon");
        String clnName = getText(SpecialPriceAllowancePage.gName).trim();
        sendKeysAndEnter(CustomerGroupMaintenancePage.groupOptField1, "1", "Select an Item");
        String clnNm = getAttribute(SalesPersonPage.pricingColumn, "value").trim();
        Utility_Functions.xAssertEquals(report, clnName, clnNm, "");
    }

    public void verifyPricingNoRow() {
        navigateToItemMasterBrowser();
        verifyItemDetails();
        navigateToMatrixColumnBrowser();
        String columnName = getText(SpecialPriceAllowancePage.gName).trim();
        sendKeysAndEnter(CustomerGroupMaintenancePage.groupOptField1, "1", "Select an Item");
        String columnNm = getAttribute(SalesPersonPage.pricingColumn, "value").trim();
        Utility_Functions.xAssertEquals(report, columnName, columnNm, "");
        commonObj.validateText(SalesPersonPage.noRow, "No Row", "[No Row] is present");
    }

    public void verifyOnlyRowPricing() {
        sendKeysAndEnter(SalesPersonPage.searchForItem, jsonData.getData("ItemNo"), "Search For Item");
        commonObj.validateElementExists(SalesPersonPage.fMROW, getText(SalesPersonPage.fMROW) + " Row is present");
    }

    public void verifyOnlyMultiplierPricing() {
        sendKeysAndEnter(SalesPersonPage.searchForItem, jsonData.getData("ItemNo"), "Search For Item");
        commonObj.validateElementExists(SalesPersonPage.fMROW, getText(SalesPersonPage.fMROW) + " Row is present");
        navigateToMatrixColumnBrowser();
        sendKeysAndEnter(SalesPersonPage.positionPricingColumn, jsonData.getData("ColumnName"), "Search For Pricing Column");
        sendKeysAndEnter(CustomerGroupMaintenancePage.groupOptField1, "1", "Select an Item");
        commonObj.validateElementExists(SalesPersonPage.noRow, getText(SalesPersonPage.noRow) + "[No Mult] is present");
    }

    public void choosePricingColumn(){
        navigateToMatrixColumnBrowser();
        sendKeysAndEnter(CustomerGroupMaintenancePage.groupOptField1, "1", "Select an Item");
        commonObj.validateElementExists(SalesPersonPage.mutlType, "Multiplier Type: " + getText(SalesPersonPage.mutlType) + " is present");
        commonObj.validateElementExists(SalesPersonPage.mutlValue, "Multiplier Value: " + getText(SalesPersonPage.mutlValue) + " is present");
    }

    public void verifyMultiplierPricing() {
        sendKeysAndEnter(SalesPersonPage.searchForItem, jsonData.getData("ItemNo"), "Search For Item");
        commonObj.validateElementExists(SalesPersonPage.fMROW, getText(SalesPersonPage.fMROW) + " Row is present");
        choosePricingColumn();
    }

    public void invalidColumnValidation(String data) {
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

    public void pressShiftF9(){
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_F9);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void verifyManuallyEnteredMultiplierValue() {
        verifyMultiplierPricing();
        pressShiftF9();
        sendKeysAndEnter(SalesPersonPage.multiplierTextField, jsonData.getData("Multiplier"), "Enter Multiplier value");
        commonObj.validateText(SalesPersonPage.mutlValue, jsonData.getData("Multiplier") + "000", jsonData.getData("Multiplier") + "000 is updated");
        click(linkIdEle("Hyperlink1"), "Click [Page Up - Next Item]");
        Utility_Functions.xAssertEquals(report, isDisplayed(By.xpath("//div[text()='" + jsonData.getData("Multiplier") + "']")), false, "Multiplier is different for Previous Item");
    }

    public void verifyExtTotal() {
        String price = getAttribute(SalesPersonPage.priceCalculation, "value").trim();
        String qty = getAttribute(SalesPersonPage.qtyCalculation, "value").trim();
        Double extTotal = Double.parseDouble(price) * Integer.parseInt(qty);
        commonObj.validateText(SalesPersonPage.extTolCalculation, "" + extTotal + "0", "EXT Total is matches");
    }

    public void verifyExtTotalCalculation() {
        verifyMultiplierPricing();
        verifyExtTotal();
        sendKeysAndEnter(SalesPersonPage.qtyCalculation, jsonData.getData("Quantity"), "Enter Quantity value");
        verifyExtTotal();
    }

    public void verifyResetMultiplierValue() {
        verifyMultiplierPricing();
        pressShiftF9();
        String pricingCol=getText(SalesPersonPage.mutlValue);
        sendKeysAndEnter(SalesPersonPage.multiplierTextField, jsonData.getData("Multiplier"), "Enter Multiplier value");
        commonObj.validateText(SalesPersonPage.mutlValue, jsonData.getData("Multiplier") + "000", jsonData.getData("Multiplier") + "000 is updated");
        sendKeysAndEnter(SalesPersonPage.pricingColumn,"B","Enter different value into Pricing Column");
        choosePricingColumn();
        commonObj.validateText(SalesPersonPage.mutlValue, pricingCol, "The Pricing Column matches");
    }

    public By isHiddenVisibleElement(String id,String visibleORHidden){
        return By.xpath("//div[@id='"+id+"' and contains(@style,'"+visibleORHidden+"')]");
    }

    public void costVisible(){
        String[] ids={"constant9","constant14","constant11"};
        for(String id:ids)
        commonObj.validateElementExists(isHiddenVisibleElement(id,"visible"), getText(isHiddenVisibleElement(id,"visible")) + " Label is Visible");
    }

    public void costIsHidden(){
        String[] idEls={"constant10","constant15","constant19","constant11"};
        for(String id:idEls)
            commonObj.validateElementExists(isHiddenVisibleElement(id,"visible"), getText(isHiddenVisibleElement(id,"visible")) + " Label is visible");
    }

    public void verifyToggleCostList() {
        verifyMultiplierPricing();
        costVisible();
        pressShiftF9();
        costIsHidden();
        pressShiftF9();
        costVisible();
    }

    public void verifyGrossMarginCalculation() {
        verifyMultiplierPricing();
        Double price=Double.parseDouble(getAttribute(SalesPersonPage.priceCalculation,"value").trim());
        Double cost=Double.parseDouble(getAttribute(SalesPersonPage.costCalculation,"value").trim());
        Double costDiff=price-cost;
        Double grossMargin=(costDiff/price)*100;
        String grossMarginAct=getAttribute(SalesPersonPage.grossMargCalculation,"value").trim();
        String grossMarginExp=grossMargin.toString().substring(0,5);
        Utility_Functions.xAssertEquals(report,grossMarginAct,grossMarginExp,"");
    }

    public void verifyPriceCalculation() {
        verifyMultiplierPricing();
        pressShiftF9();
        Double list=Double.parseDouble(getAttribute(SalesPersonPage.listCalculation,"value").trim());
        Double multiplier=Double.parseDouble(getAttribute(SalesPersonPage.multiplierTextField,"value").trim());
        Double price=list*multiplier;
        String priceAct=getAttribute(SalesPersonPage.priceCalculation,"value").trim().substring(0,5);
        Utility_Functions.xAssertEquals(report,priceAct,price.toString(),"");
    }

    public void verifyMultiplierField() {
        verifyMultiplierPricing();
        pressShiftF9();
        String multiplier=getAttribute(SalesPersonPage.multiplierTextField,"value").trim();
        sendKeysAndEnter(SalesPersonPage.multiplierTextField, jsonData.getData("InvalidDigitMultiplier"),"Edit the multiplier value in Multiplier field to greater than 9.999 " );
        commonObj.validateText(SalesPersonPage.customMsg, "Mult Must Be <= 9.999 but was = "+jsonData.getData("InvalidDigitMultiplier"), "[Mult Must Be <= 9.999 but was = "+jsonData.getData("InvalidDigitMultiplier")+"] is present");
        sendKeysAndEnter(SalesPersonPage.multiplierTextField, jsonData.getData("5DecimalMultiplier"),"Edit the multiplier value in Multiplier field to a number <=9.9999 and more than 4 digits after decimal point");
        sendKeysAndEnter(SalesPersonPage.multiplierTextField, jsonData.getData("SpecialAlphaNumeric"),"Edit the multiplier value in Multiplier field to the combination of special characters and alphanumeric numbers");
        sendKeysAndEnter(SalesPersonPage.multiplierTextField, jsonData.getData("ZeroMultiplier"),"Edit the multiplier value in Multiplier field to 0" );
        Utility_Functions.xAssertEquals(report,multiplier,getAttribute(SalesPersonPage.multiplierTextField,"value").trim(),"Multiplier value reset to Original Value");
        sendKeysAndEnter(SalesPersonPage.multiplierTextField, jsonData.getData("NegativeMultiplier"),"Edit the multiplier value in Multiplier field to Negative Value" );
        commonObj.validateText(SalesPersonPage.customMsg, "(  Blank Price and Mult to Reset  )","[(Blank Price and Mult to Reset)] is present");
    }

    public void grossMarginReset(String grossMarginAct,String value){
        sendKeysAndEnter(SalesPersonPage.grossMargin, jsonData.getData(value),"Edit the multiplier value in Multiplier field to greater than 9.999 " );
        Utility_Functions.xAssertEquals(report,grossMarginAct,getAttribute(SalesPersonPage.grossMargin,"value").trim(),"Gross Margin value reset to Original Value");
    }

    public void verifyGrossMarginField() {
        verifyMultiplierPricing();
        pressShiftF9();
        String grossMarginAct=getAttribute(SalesPersonPage.grossMargin,"value").trim();
        grossMarginReset(grossMarginAct,"InvalidGrossMargin");
        grossMarginReset(grossMarginAct,"NegativeGrossMargin");
        grossMarginReset(grossMarginAct,"5DecimalMultiplier");
        grossMarginReset(grossMarginAct,"SpecialAlphaNumeric");
    }

    public void navigateToAlternativeItem(){
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,linkIdEle("alternateItem"),"Click [Alternate Item] Link");
        commonObj.validateText(SpecialPriceAllowancePage.header, "Alternate Stock Number Revisions","[Alternate Stock Number Revisions] header is present");
    }

    public void validateAlternateItemScreen(){
        click(PricingMatrixPage.addRow,"Click [F1=Stk# Browse]");
        commonObj.validateText(SalesPersonPage.f1Alt, "Stock Number:","Changes to [Stock Number:]");
        click(SpecialPriceAllowancePage.btnExit,"Click [F3=Exit]");
        commonObj.validateText(SalesPersonPage.salesPersonInquiryTitle, "Salesperson Inquiry", "Validating Sales Person page title");
    }

    public void enterCustomer(){
        sendKeysAndEnter(SalesPersonPage.customerTextBox, jsonData.getData("Customer"), "Enter Customer");
    }

    public void navigateToCustomerNotes(){
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,linkIdEle("CustomerNotes_copy"),"Click [Customer Notes] Link");
        commonObj.validateText(PurchaseOrderDetailsPage.poPrintSendHeader, "Customer Notes Revisions","[Customer Notes Revisions] header is present");
    }

    public void validateCustomerNotesScreen(){
        sendKeys(VendorNotesPage.vendorInputVendorNotes,"A","Enter action [A]");
        sendKeysAndEnter(SalesPersonPage.noteStatement,"Customer Notes - Automation Testing","Enter Note");
        commonObj.validateText(VendorNotesPage.erroMsgVendorNotes, "c"+jsonData.getData("Customer")+" - Customer Notes Changed.","[c"+jsonData.getData("Customer")+" - Customer Notes Changed.] message is present");
        click(SpecialPriceAllowancePage.btnExit,"Click [F3=Exit]");
        commonObj.validateText(SalesPersonPage.salesPersonInquiryTitle, "Salesperson Inquiry", "Validating Sales Person page title");
    }

    public void navigateToIOVScreen(){
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,linkIdEle("inventoryOverValue_copy"),"Click [IOV] Link");
        commonObj.validateText(VendorInvoiceReconciliationPage.PoMainMenuHeader, "IOV Calculation","[IOV Calculation] header is present");
    }

    public void navigateToItemBinScreen(){
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,linkIdEle("lnkItemBinMaint"),"Click [Item-Bin Maintenance] Link");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver, SelfServicePriceSheetPage.companySelector,"[Item-Bin Maintenance - Item Details] header is present");
        ownDriver.close();
    }

    public void navigateToItemLedgerScreen(){
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,linkIdEle("itemLedger"),"Click [Item Ledger] Link");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,PurchaseOrderInquiryPage.hdrItemLedger, "[Item Ledger (I-385)] header is present");
    }

    public void navigateToJobUsageScreen(){
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,linkIdEle("jobUsage"),"Click [Job Usage] Link");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,PurchaseOrderDetailsPage.ediStat,"[Job Usage by Item Inquiry] header is present");
    }

    public void navigateToKitMaintenanceScreen(){
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,linkIdEle("kitMaintenance"),"Click [Kit Maintenance] Link");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//span[text()='Kit Maintenance']"),"[Kit Maintenance] header is present");
    }

    public void navigateToOrderByItemScreen(){
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,linkIdEle("ordersByItem"),"Click [Orders by Item] Link");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,SpecialPriceAllowancePage.header,"[Open Orders By Item] header is present");
    }

    public void navigateToQuotesByItemScreen(){
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,linkIdEle("quotesByItem"),"Click [Quotes by Item] Link");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,SalesPersonPage.wbScrTitle,"[SALES QUOTE DETAIL LINES] header is present");
    }

    public void navigateToPreferredCoUsageScreen(){
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,linkIdEle("preferedCompanyUsage"),"Click [Preferred Co. Usage] Link");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//span[text()='Preferred Local Company Usage']"),"[Preferred Local Company Usage] header is present");
    }

    public void verifyActionTab() {
        verifyMultiplierPricing();
        navigateToAlternativeItem();
        validateAlternateItemScreen();
        enterCustomer();
        navigateToCustomerNotes();
        validateCustomerNotesScreen();
        navigateToIOVScreen();
        click(SpecialPriceAllowancePage.btnExit,"Click [F3=Exit]");
        navigateToItemBinScreen();
    }

    public void validateActionLinks(){
        verifyMultiplierPricing();
        enterCustomer();
        navigateToItemLedgerScreen();
        click(SpecialPriceAllowancePage.btnExit,"Click [F3=Exit]");
        navigateToJobUsageScreen();
        click(SpecialPriceAllowancePage.btnExit,"Click [F3=Exit]");
        navigateToKitMaintenanceScreen();
        click(By.xpath("//span[text()='F3-Exit']"));
        navigateToOrderByItemScreen();
        click(SpecialPriceAllowancePage.btnExit,"Click [F3=Exit]");
        navigateToPreferredCoUsageScreen();
        click(By.xpath("//span[text()='F12-Back']"));
        navigateToQuotesByItemScreen();
        click(CustomerGroupMaintenancePage.cancelBtn,"Click [Back]");
        commonObj.validateText(SalesPersonPage.salesPersonInquiryTitle, "Salesperson Inquiry", "Validating Sales Person page title");
    }
}