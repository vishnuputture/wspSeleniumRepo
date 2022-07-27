package businesskeywords.Inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import pages.PurchaseOrders.PurchaseOrderDetailsPage;
import pages.PurchaseOrders.PurchaseOrderInquiryPage;
import pages.PurchaseOrders.VendorInvoiceReconciliationPage;
import pages.PurchaseOrders.VendorNotesPage;
import pages.common.MasterPage;
import pages.inventory.AlternateCustomerPage;
import pages.inventory.ItemMasterPage;
import pages.inventory.SalesPersonPage;
import pages.pricing.OrderByCustomerPage;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
import pages.pricing.pricingmatrix.PricingMatrixPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

import java.awt.*;
import java.util.List;
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

    /**
     * Keyword to enter Alternate Customer Number in [Customer] textbox in SalesPerson Inquiry Page
     */
    public void enterAltCustomerNumber(){
        sendKeysAndEnter(SalesPersonPage.tbxCustomer, jsonData.getData("AltCustomerNumber"),"Enter Alternate Customer Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        String custNoExpeccted = jsonData.getData("CustomerNumber");
        String custNoActual = getAttribute(SalesPersonPage.tbxCustomer, "value");
        Utility_Functions.xAssertEquals(report, custNoExpeccted, custNoActual, "Validating selected Customer Number");
    }

    /**
     * Keyword to validate Customer Name in SalesPerson Inquiry Page
     */
    public void vrfyCustomerName(){
        String custName = jsonData.getData("CustomerName");
        commonObj.validateText(SalesPersonPage.customerName, custName, "Validating selected Customer Name");
    }

    /**
     * Keyword to enter Invalid Customer Number in [Customer] textbox in SalesPerson Inquiry Page
     */
    public void enterInvalidCustomerNumber(){
        sendKeysAndEnter(SalesPersonPage.tbxCustomer, jsonData.getData("InvalidCustomerNumber"),"Enter Invalid Customer Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(SalesPersonPage.customerName, "Invalid", "Validating invalid Customer Name");
    }

    /**
     * Keyword to enter Customer Number in [Customer] textbox in SalesPerson Inquiry Page
     */
    public void enterCustomerNumber(){
        sendKeysAndEnter(SalesPersonPage.tbxCustomer, jsonData.getData("CustomerNumber"),"Enter valid Customer Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        String custNoExpeccted = jsonData.getData("CustomerNumber");
        String custNoActual = getAttribute(SalesPersonPage.tbxCustomer, "value");
        Utility_Functions.xAssertEquals(report, custNoExpeccted, custNoActual, "Validating selected Customer Number");
    }

    /**
     * Keyword to enter Customer Number in [Customer] textbox in SalesPerson Inquiry Page
     */
    public void vrfyCustNotesNavigation(){
        String custNumber = getAttribute(SalesPersonPage.tbxCustomer, "value");
        click(SalesPersonPage.actionCustomerNotes,"Click Customer Notes action button");
        commonObj.validateText(SalesPersonPage.hdrCustomerNotesRevision, "Customer Notes Revisions", "Validating page title");

        String custNoActual = getAttribute(SalesPersonPage.tbxCustNoCustNotesPage, "value");
        Utility_Functions.xAssertEquals(report, custNumber, custNoActual, "Validating selected Customer Number");

        String actionsValue = getAttribute(SalesPersonPage.tbxActions, "value");
        Utility_Functions.xAssertEquals(report, "I", actionsValue, "Validating Action textbox value");
    }

    /**
     * Keyword to validate Add/Edit/Delete Customer Notes functionality
     */
    public void validateAddEditCustomerNotes(){
        String customerNotes = "TESTING NOTES"+Utility_Functions.xRandomFunction();
        sendKeys(SalesPersonPage.tbxActions, "A","Enter [A] in Action textbox");
        sendKeysAndEnter(SalesPersonPage.customerNotesLine1, customerNotes,"Enter Customer Notes");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.verifyElementContainsText(SalesPersonPage.notificationMsg, "Customer Notes Changed", "Validating notification message");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
        report.updateTestLog("Press [F3] exit button", "Press [F3] exit button",Status.PASS);
        vrfyCustNotesNavigation();
        String custNotesActual = getAttribute(SalesPersonPage.customerNotesLine1, "value");
        Utility_Functions.xAssertEquals(report, customerNotes, custNotesActual, "Validating Customer Notes");

        customerNotes = "TESTING NOTES"+Utility_Functions.xRandomFunction();
        sendKeys(SalesPersonPage.tbxActions, "C","Enter [C] in Action textbox");
        sendKeysAndEnter(SalesPersonPage.customerNotesLine1, customerNotes,"Enter Customer Notes");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.verifyElementContainsText(SalesPersonPage.notificationMsg, "Customer Notes Changed", "Validating notification message");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
        report.updateTestLog("Press [F3] exit button", "Press [F3] exit button",Status.PASS);
        vrfyCustNotesNavigation();
        custNotesActual = getAttribute(SalesPersonPage.customerNotesLine1, "value");
        Utility_Functions.xAssertEquals(report, customerNotes, custNotesActual, "Validating Customer Notes");

        sendKeysAndEnter(SalesPersonPage.tbxActions, "D","Enter [D] in Action textbox");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.verifyElementContainsText(SalesPersonPage.notificationMsg, "All Customer Notes Will be Deleted.  Press Enter to Delete.", "Validating notification message");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        report.updateTestLog("Press [Enter] key", "Press [Enter] key to delete all notes",Status.PASS);
        custNotesActual = getAttribute(SalesPersonPage.customerNotesLine1, "value");
        Utility_Functions.xAssertEquals(report, "", custNotesActual, "Validating Customer Notes deleted");
    }

    /**
     * Keyword to clear [Search For Item] textbox in SalesPerson Inquiry Page
     */
    public void clearSearchForItem() {
        sendKeysAndEnter(SalesPersonPage.searchForItem, "","Clear [Search For Item] textbox");
    }

    /**
     * Keyword to enter invalid Item No in [Search for Item] textbox in SalesPerson Inquiry Page
     */
    public void enterInvalidItemNumber(){
        sendKeysAndEnter(SalesPersonPage.searchForItem, jsonData.getData("InvalidItemNo"),"Enter Invalid Item Number" );
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(SalesPersonPage.msgItemNo, "E501", "Validating error below [Search for Item] textbox");
    }

    /**
     * Keyword to hover over Customer tbx and verify tooltip in SalesPerson Inquiry Page
     */
    public void vrfyCustTbxTooltip(){
        Utility_Functions.xMoveToElment(getElement(SalesPersonPage.tbxCustomer), ownDriver);
        Utility_Functions.timeWait(1);
        String tooltip = getAttribute(SalesPersonPage.tbxCustomer, "title");
        Utility_Functions.xAssertEquals(report, "F4 - Search for Customers", tooltip, "Validating Customer textbox tooltip");

        Utility_Functions.xMoveToElment(getElement(SalesPersonPage.custSearchIcon), ownDriver);
        Utility_Functions.timeWait(1);
        tooltip = getAttribute(SalesPersonPage.custSearchIcon, "title");
        Utility_Functions.xAssertEquals(report, "Search for Customers", tooltip, "Validating Customer Search icon tooltip");
    }

    /**
     * Keyword to navigate to Customer Search by pressing [F4] key in SALESPERSON INQUIRY page
     */
    public void navToCustSearchInSalesPersonInq(){
        click(SalesPersonPage.custSearchIcon, "Click Customer Search icon for navigation to Customer Search");
        commonObj.validateText(AlternateCustomerPage.pageTitleMailingMasterSearch, "Mailing Master Search (O-946)", "Validating [Mailing Master Search] page title");
    }

    /**
     * Keyword to navigate to Customer Search by clicking Search icon in SALESPERSON INQUIRY page
     */
    public void clickCustSearchIconSalesPersonInq(){
        click(SalesPersonPage.tbxCustomer);
        Utility_Functions.actionKey(Keys.F4, ownDriver);
        report.updateTestLog("Press [F4] button on Customer Input field for navigation to Customer Search", "Press [F4] button",Status.PASS);
        commonObj.validateText(AlternateCustomerPage.pageTitleMailingMasterSearch, "Mailing Master Search (O-946)", "Validating [Mailing Master Search] page title");
    }

    /**
     * Keyword to select random Customer in SALESPERSON INQUIRY page
     */
    public String selectRandomCustomerInSalesPersonInq(){
        int count = Utility_Functions.xRandomFunction(1, 10);
        while(count>0){
            click(AlternateCustomerPage.btnDown);
            count--;
        }
        String customerNumber = getText(AlternateCustomerPage.acct1).trim();
        String customerName = getText(AlternateCustomerPage.addLine1).trim();
        sendKeysAndEnter(AlternateCustomerPage.opt1, "1", "Select Customer");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(SalesPersonPage.salesPersonInquiryTitle, "Salesperson Inquiry", "Validating [Salesperson Inquiry] page title");
        String custNoActual = getAttribute(SalesPersonPage.tbxCustomer, "value");
        Utility_Functions.xAssertEquals(report, customerNumber, custNoActual, "Validating selected Customer Number");
        commonObj.validateText(SalesPersonPage.customerName, customerName, "Validating selected Customer Name");
        return customerNumber;
    }
}