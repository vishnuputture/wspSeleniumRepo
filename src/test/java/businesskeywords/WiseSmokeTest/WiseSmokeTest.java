package businesskeywords.WiseSmokeTest;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.*;
import pages.Purchasing.InventoryReceiptPage;
import pages.Purchasing.VendorInvoiceReconciliationPage;
import pages.WiseSmokeTest.WiseSmokeTestPage;
import pages.common.MasterPage;
import pages.pricing.SalesQuotes.SalesQuotePrintSendPage;
import pages.pricing.SalesQuotes.SalesQuotesPage;
import supportLibraries.Utility_Functions;

import java.io.IOException;
import java.util.Random;

public class WiseSmokeTest extends ReusableLib {

    CommonActions commonObj;
    private FrameworkDriver ownDriver;
    private final boolean isUsingProd = jsonData.getData("environment").equalsIgnoreCase("prod");
    private String company = isUsingProd ? jsonData.getData("prodCompany") : jsonData.getData("devCompany");
    private final String customer = isUsingProd ? jsonData.getData("prodCustomer") : jsonData.getData("devCustomer");
    private final String vendor = isUsingProd ? jsonData.getData("prodVendor") : jsonData.getData("devVendor");
    private final String multiBinStatus = jsonData.getData("multiBinStatus");

    public WiseSmokeTest(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
        company = String.format("%05d", Integer.parseInt(company));
    }

    public void url() throws IOException {
        commonObj.clearDownloadDirectory();
        String url = properties.getProperty("STGURL");
        if (isUsingProd) {
            Random random = new Random();
            int randomNumber = random.nextInt(900) + 100;
            String workstation = "B" + company + "D"+ randomNumber;
            url = properties.getProperty("PRODURL");
            url = url.replaceFirst("(workstnid=)(&)", "$1" + workstation + "$2");
        }
        ownDriver.get(url);
    }

    public void login() {
        sendKeys(WiseSmokeTestPage.userName, jsonData.getData("userName"), "Entering User Name");
        sendKeys(WiseSmokeTestPage.passWord, jsonData.getData("passWord"), "Entering Password");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(WiseSmokeTestPage.headerTitle, "WISE - Wholesalers Information Services Executive", "Validating header title for main menu");
    }

    public void win() {
        sendKeysAndEnter(MasterPage.sqlTxtBox, "win " + company, "WIN to company " + company);
    }

    public void where() {
        sendKeys(WiseSmokeTestPage.Where, "where", "Checking which company");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(WiseSmokeTestPage.whichLC, company, "Validating Company");
        click(WiseSmokeTestPage.f12Cancel, "Click on F12 Cancel to get on to main page");
        commonObj.validateText(WiseSmokeTestPage.headerTitle, "WISE - Wholesalers Information Services Executive", "Validating header title for main menu");
    }

    public void backorderOCClearforNewOrder() {
        click(WiseSmokeTestPage.orderProcessing, "Click on Order Processing from Main menu");
        click(WiseSmokeTestPage.ONC, "Click on Options and Constants");
        click(WiseSmokeTestPage.backOrderONC, "Click back order filling options and constants");
        clearText(WiseSmokeTestPage.alwaysNewOrderONC);
        click(WiseSmokeTestPage.Enter, "Save the changes made");
        click(WiseSmokeTestPage.F3exit, "Exit");
        click(WiseSmokeTestPage.F3exit, "Exit to the main menu");

    }

    public void salesQuote() {
        click(WiseSmokeTestPage.SalesQuote, "CLick on Sales Quotes from the main menu");
        click(WiseSmokeTestPage.WorkWithSQ, "Click on work with sales quotes");
        commonObj.validateText(WiseSmokeTestPage.WWSQPgmTitle, "WORKING WITH SALES QUOTES", "Validating Working with sales quotes page title");
        click(WiseSmokeTestPage.CreateSQ, "Click on Create Sales Quote");
        sendKeys(WiseSmokeTestPage.CustNum, customer, "Enter the customer number");
        sendKeys(WiseSmokeTestPage.QuoteJobName, jsonData.getData("QuoteJobName"), "Enter Quote/Job Name");
        click(WiseSmokeTestPage.NextCNSQ, "Click on Next button from Create New Sales Quote pop-up");
        commonObj.validateText(WiseSmokeTestPage.SQPgmTitle, "Sales Quotes", "Validating sales quotes page title");

        String sqNum = getText(WiseSmokeTestPage.SQNum);
        System.out.println("sqNum" + sqNum);
        Utility_Functions.xUpdateJson("SQNum"+company, sqNum);

        sendKeys(WiseSmokeTestPage.SQEmail, jsonData.getData("SQEmail"), "Entering email for sales quote");
        click(WiseSmokeTestPage.NextCNSQ, "Clicking on Next to navigate to Sales Quotes detail lines");
        click(WiseSmokeTestPage.NextCNSQ, "Clicking on Next to navigate to Sales Quotes detail lines");


    }

    public void salesQuoteDetailLines() {
        commonObj.validateText(WiseSmokeTestPage.SQTitle, "Sales Quote Detail Lines", "Validating  sales quotes detail lines page title");
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(WiseSmokeTestPage.Type), 0);
        waitForVisible(WiseSmokeTestPage.itemNumber);
        System.out.println("item number is visible");
        String itemNum = getText(WiseSmokeTestPage.itemNumber);
        System.out.println("itemNum" + itemNum);
        Utility_Functions.xUpdateJson("itemNum"+company, itemNum);

    }

    public void itemAddInformation() {
        Utility_Functions.timeWait(1);
        sendKeys(WiseSmokeTestPage.itemDesc1, jsonData.getData("itemDesc1"), "Enter line 1 description");
        sendKeys(WiseSmokeTestPage.itemDesc2, jsonData.getData("itemDesc2"), "Enter line 2 description");
        sendKeys(WiseSmokeTestPage.matrixCost, jsonData.getData("matrixCost"), "Enter matrix cost");
        sendKeys(WiseSmokeTestPage.poCost, jsonData.getData("poCost"), "Enter purchase order cost");
        sendKeys(WiseSmokeTestPage.listPrice, jsonData.getData("listPrice"), "Enter list price");
        click(WiseSmokeTestPage.Accept, "Clicking on F9 Accept");

    }

    public void salesQuotedtllines() throws IOException, InterruptedException {
        commonObj.validateText(WiseSmokeTestPage.SQTitle, "Sales Quote Detail Lines", "Validating  sales quotes detail lines page title");
        sendKeys(WiseSmokeTestPage.Qty, jsonData.getData("Qty"), "Entering Item Qty");
        click(WiseSmokeTestPage.NextCNSQ, "Clicking on Next to navigate to Sales Quotes ");
        click(WiseSmokeTestPage.NextCNSQ, "Clicking on Next to navigate to Sales Quotes");
        click(WiseSmokeTestPage.NextCNSQ, "Clicking on Next to navigate to Sales Quotes ");
        click(WiseSmokeTestPage.btnBack, "Clicking on back button");
        click(WiseSmokeTestPage.btnBack, "Clicking on back button");
        ownDriver.findElement(SalesQuotesPage.printSendBtn).click();
        Utility_Functions.waitForElementVisible(ownDriver, SalesQuotePrintSendPage.printSendHeader, 5);
        click(SalesQuotePrintSendPage.deselectAll, "Clicking Deselect All");
        click(SalesQuotePrintSendPage.localPrintCopyOptionsDropdown, "Clicking Dropdown");
        Utility_Functions.actionKey(Keys.NUMPAD5, ownDriver);
        click(WiseSmokeTestPage.Enter, "Clicking Next");
        String salesQuoteNum = Utility_Functions.xGetJsonData("SQNum"+company);
        String[] validations = {salesQuoteNum};
        commonObj.validatePDFPopUp(company,"sq", validations); //SQ0006699
        click(SalesQuotePrintSendPage.backBtn, "Clicking on back button on Sales Quote Print/Send page");
        click(WiseSmokeTestPage.btnBack, "Clicking on back button");
        click(WiseSmokeTestPage.SortQuote, "Sorting Quotes");
        if (!multiBinStatus.equalsIgnoreCase("INACTIVE"))
            click(WiseSmokeTestPage.SortQuote, "Sorting Quotes");
        Utility_Functions.xMouseClick(ownDriver, WiseSmokeTestPage.SQOptionsDD);
        click(WiseSmokeTestPage.Convert, "Clicking Convert");
        click(WiseSmokeTestPage.NextCNSQ);
        //click(WiseSmokeTestPage.Accept, "Clicking on override from Check Quote Status");
    }

    public void sqconversiontoSO() {

        waitForVisible(WiseSmokeTestPage.SONum);
        System.out.println("So number is visible");
        String soNum = getText(WiseSmokeTestPage.SONum);
        System.out.println("soNum" + soNum);
        Utility_Functions.xUpdateJson("soNum"+company, soNum);
        Utility_Functions.xMouseClick(ownDriver, WiseSmokeTestPage.soQtyShippedDD);
        sendKey(WiseSmokeTestPage.soQtyShippedDD, "1");
        click(WiseSmokeTestPage.NextCNSQ, "Click Next Button");
        click(WiseSmokeTestPage.Accept, "Click Process button");
        click(WiseSmokeTestPage.shipmentsTab, "Click on Shipments tab ");
    }

    public void shipmentsTab() {
        click(WiseSmokeTestPage.shipmentsTab, "Click on Shipments tab ");
        Utility_Functions.rightClick(ownDriver, WiseSmokeTestPage.rgtClk);
        click(WiseSmokeTestPage.createPcsOrd, "Click on create purchase order");
        click(WiseSmokeTestPage.convertEntShp, "Click on Convert Entire Shipment");
        click(WiseSmokeTestPage.btnNext, "Click on Next button");
        click(WiseSmokeTestPage.searchBtn, "Click on search");
        sendKey(WiseSmokeTestPage.MMOptionbox, "1");
        click(WiseSmokeTestPage.Enter, "Click on Enter");
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(WiseSmokeTestPage.ddnFreightCode), 1);
        click(WiseSmokeTestPage.btnCreatePO, "Click on create PO Button");
        String purchaseOrder = Utility_Functions.getText(ownDriver, WiseSmokeTestPage.txtPONumber);
        Utility_Functions.xUpdateJson("POSmoke"+company, purchaseOrder);
        System.out.println("purchaseOrder: " + purchaseOrder);
        click(WiseSmokeTestPage.btnBack, "Click Back Button");
        String RelPO = Utility_Functions.getText(ownDriver, WiseSmokeTestPage.relatedPO);
        Utility_Functions.xAssertEquals(report, "Related P.O.", RelPO, "Related P.O.: ");
        click(WiseSmokeTestPage.btnSaveExit, "Click on Save and Exit");
        //click(WiseSmokeTestPage.btnExitcustTab,"Click on F12 By Pass PO entry");
        click(WiseSmokeTestPage.btnExitcustTab, "Click on Exit button on customer Tab");
        click(WiseSmokeTestPage.saleQExtBtn, "Click Exit Button");
        click(WiseSmokeTestPage.F3exit, "Click Exit Button");
    }

    public void validatePOPDF() throws IOException, InterruptedException {
        String purchaseOrder = Utility_Functions.xGetJsonData("POSmoke"+company);
        String itemNumber = Utility_Functions.xGetJsonData("itemNum"+company);
        sendKeys(WiseSmokeTestPage.PostoOrderPOI, purchaseOrder);
        //search for purchase order
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(WiseSmokeTestPage.poStatusDropDown), 2);
        Utility_Functions.timeWait(1);
        Utility_Functions.xMouseClick(ownDriver, WiseSmokeTestPage.poOptionDropDown);
        Utility_Functions.xMouseClick(ownDriver, By.xpath("(//div[normalize-space()='5-Display PDF/Vendor'])[1]"));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        String[] validations = {purchaseOrder};
        commonObj.validatePDFPopUp(company, "po", validations); //last 6 digits of username
        Utility_Functions.actionKey(Keys.F3, ownDriver);
        Utility_Functions.actionKey(Keys.F3, ownDriver);
    }

    public void navigateToInventoryReceipt() {
        commonObj.purchaseOrderToInventoryReceipt();
        commonObj.validateText(InventoryReceiptPage.inventoryHeader, "Inventory Receipts -", "'Inventory Receipts - (I-735)' header is present");
    }

    public void inventoryreceipts() {
        sendKey(WiseSmokeTestPage.purchaseOrdNo, Utility_Functions.xGetJsonData("POSmoke"+company));

        click(WiseSmokeTestPage.processButton, "Click on button to process the PO");
        if (!multiBinStatus.equalsIgnoreCase("INACTIVE")) {
            Utility_Functions.actionKey(Keys.F9, ownDriver);
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        }

        sendKeys(WiseSmokeTestPage.qtyReceived, jsonData.getData("qtyReceived"), "Enter the qty to be received");

        if (!multiBinStatus.equalsIgnoreCase("INACTIVE")) {
            click(WiseSmokeTestPage.binLocationSearch, "Clicking Bin Location Search");
            click(WiseSmokeTestPage.firstBinSelect, "Selecting First Bin");
            click(WiseSmokeTestPage.btnSave, "Clicking Select Button");
        }
        String RecDoc = Utility_Functions.getText(ownDriver, WiseSmokeTestPage.ReceiverDocNum);
        System.out.println("RecDoc" + RecDoc);
        Utility_Functions.xUpdateJson("RecDoc"+company, RecDoc);


        click(WiseSmokeTestPage.mainInvProcessbtn, "Click on Process button");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        click(WiseSmokeTestPage.btnEnter, "Clicking Continue");
        click(WiseSmokeTestPage.exitBtn, "Click Exit Button");

        //Freight Allocation
        //String FFA = Utility_Functions.getText(ownDriver, WiseSmokeTestPage.FFATitle);
        // System.out.println("FFA" + FFA);

        Utility_Functions.actionKey(Keys.ENTER, ownDriver);

        //click(WiseSmokeTestPage.btnExitInvRec,"Click on Exit from INV Receipts screen");
        click(WiseSmokeTestPage.F3exit, "Click on F3exit to navigate back to the main menu");


    }

    public void navigateToVendorInvoiceRec() {
        commonObj.purchaseOrderToVendorInvoiceReconciliation();
        commonObj.validateText(VendorInvoiceReconciliationPage.vendorInvoiceRecHeader, "Vendor Invoice Reconciliation", "Validating [Vendor Invoice Reconciliation] page title");
    }

    public void vIR() {
        commonObj.validateElementExists(WiseSmokeTestPage.Operator, "Operator is present: " + getAttribute(WiseSmokeTestPage.Operator, "value"));
        sendKey(WiseSmokeTestPage.ReceiverDocVIR, Utility_Functions.xGetJsonData("RecDoc"+company));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        sendKeys(WiseSmokeTestPage.VIRDueDate, jsonData.getData("virDueDate"), "Entering VIR Due Date");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        Utility_Functions.actionKey(Keys.F3, ownDriver);
        Utility_Functions.actionKey(Keys.F3, ownDriver);
    }

    public void navigatetoItemLedger() {
        click(WiseSmokeTestPage.InvMngmnt, "Click on menu 7 Inventory Management");
        click(WiseSmokeTestPage.ItemLedger, "Click on Item ledger menu option from Inventory Management");

    }

    public void verifyItemLedgerWISE() {
        sendKey(WiseSmokeTestPage.itmnumledger, Utility_Functions.xGetJsonData("itemNum"+company));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);

        String SCLine1 = Utility_Functions.getText(ownDriver, WiseSmokeTestPage.SCline1);
        System.out.println("SCLine1" + SCLine1);
        Utility_Functions.xUpdateJson("SCLine1"+company, SCLine1);

        String SCLine2 = Utility_Functions.getText(ownDriver, WiseSmokeTestPage.SCline2);
        System.out.println("SCLine2" + SCLine2);
        Utility_Functions.xUpdateJson("SCLine2"+company, SCLine2);
        commonObj.verifyElementContainsText(WiseSmokeTestPage.SCline1, "PA", "Verify text to be PA");
        commonObj.verifyElementContainsText(WiseSmokeTestPage.SCline2, "PR", "verify text to be PR");

        String QtyAfterLine1 = Utility_Functions.getText(ownDriver, WiseSmokeTestPage.QtyAfterLine1);
        System.out.println("QtyAfterLine1" + QtyAfterLine1);
        Utility_Functions.xUpdateJson("QtyAfterLine1"+company, QtyAfterLine1);

        String QtyAfterLine2 = Utility_Functions.getText(ownDriver, WiseSmokeTestPage.QtyAfterLine2);
        System.out.println("QtyAfterLine2" + QtyAfterLine2);
        Utility_Functions.xUpdateJson("QtyAfterLine2"+company, QtyAfterLine2);

        commonObj.verifyElementContainsText(WiseSmokeTestPage.QtyAfterLine1, "1", "Verify qty to be 1");
        commonObj.verifyElementContainsText(WiseSmokeTestPage.QtyAfterLine2, "1", "verify qty to be 1");
        click(WiseSmokeTestPage.F3exit, "Exit out of Item Ledger screen");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
    }

    public void naviagtetoSOInquiry() {
        click(WiseSmokeTestPage.OrderProcessingMenu, "Click on Order Processing Menu");
        click(WiseSmokeTestPage.SOInquiry, "Click on Sales Order Inquiry");
    }

    public void salesOrderSelectionfromInq() throws IOException, InterruptedException {
        sendKey(WiseSmokeTestPage.SONumberSOI, Utility_Functions.xGetJsonData("soNum"+company));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.xMouseClick(ownDriver, WiseSmokeTestPage.SOline2);
        Utility_Functions.xMouseClick(ownDriver, WiseSmokeTestPage.SOENTRY9);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        click(WiseSmokeTestPage.Itemstab, "Click on Items tab");
        click(WiseSmokeTestPage.BasicTab, "Click on Basic tab from Items tab");
        commonObj.verifyElementContainsText(WiseSmokeTestPage.QtyToShip, "1", "Verify qty to ship to be 1");
        if (multiBinStatus.equalsIgnoreCase("ACTIVE")) {
            click(WiseSmokeTestPage.shipmentsTab, "Click Shipment Tab");
            click(WiseSmokeTestPage.counterPicks, "Click Counter Picks");
            sendKey(WiseSmokeTestPage.counterPickQuantity, "1");
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
            click(WiseSmokeTestPage.btnSave, "Save Counter Pick");
        }
        click(WiseSmokeTestPage.PaymentsTab, "Click on Payments Tab to navigate to payments screen");
        click(WiseSmokeTestPage.PaymentMethod, "Click on Payment Method dropdown");
        // Utility_Functions.xMouseClick(ownDriver, WiseSmokeTestPage.PaymentMethod);
        //Utility_Functions.xMouseClick(ownDriver, WiseSmokeTestPage.CashMethod);
        click(WiseSmokeTestPage.CashMethod, "Click on option cash as payment method");
        click(WiseSmokeTestPage.ApplyButton, "Click on Apply after selecting payment as cash");
        click(WiseSmokeTestPage.PrintNExit, "Click on F3 Print and Exit");
        //click(WiseSmokeTestPage.f12Cancel, "click on F12 ByPass PO Entry");
        click(WiseSmokeTestPage.OrdAck, "click on Order Acknowledgement from Document settings screen");
        if (multiBinStatus.equalsIgnoreCase("ACTIVE")) {
            click(WiseSmokeTestPage.PackingList, "click on Packing List from Document settings screen");
        }
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        clearText(WiseSmokeTestPage.CreateOrdAcknow);
        sendKeys(WiseSmokeTestPage.CreateOrdAcknow, jsonData.getData("OrderAcknow"), "Enter Y for Order Acknowledgement");
        clearText(WiseSmokeTestPage.PrintYesNo);
        sendKeys(WiseSmokeTestPage.PrintYesNo, jsonData.getData("Print"), "Enter Y for Printing");
        clearText(WiseSmokeTestPage.PrinterName);
        sendKeys(WiseSmokeTestPage.PrinterName, jsonData.getData("PrinterName"), "Enter P1 for Printer Name");
        //sendKeys(WiseSmokeTestPage.SendTo, jsonData.getData("SendTo"),"Enter an email to send the order acknowledgement");
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        Utility_Functions.actionKey(Keys.F3, ownDriver);
        if (!multiBinStatus.equalsIgnoreCase("INACTIVE"))
            click(WiseSmokeTestPage.saleQExtBtn, "Click exit on customer tab");
        click(WiseSmokeTestPage.SOline2, "Click on Options box for line 2");
        Utility_Functions.xMouseClick(ownDriver, WiseSmokeTestPage.SOline2);
        Utility_Functions.xMouseClick(ownDriver, WiseSmokeTestPage.SOENTRY5);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        sendKeysAndEnter(WiseSmokeTestPage.optBox, "1", "Select Packing List");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        String[] validations = {Utility_Functions.xGetJsonData("soNum"+company)};
        commonObj.validatePDFPopUp(company, "packlist", validations); //custNumber(6 digits)_orderNumber(6)_02
        Utility_Functions.actionKey(Keys.F12, ownDriver);
        Utility_Functions.actionKey(Keys.F3, ownDriver);
        Utility_Functions.actionKey(Keys.F3, ownDriver);
    }

    public void navigateToShippingConf() {
        click(WiseSmokeTestPage.OrderProcessingMenu, "Click on Order Processing Menu");
        click(WiseSmokeTestPage.ShippingConfirmation, "Click on Shipping Confirmation");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);

    }

    public void shippingConf() {

        sendKey(WiseSmokeTestPage.SCOrdNum, Utility_Functions.xGetJsonData("soNum"+company));
        sendKeys(WiseSmokeTestPage.SCSuffix, jsonData.getData("SCSuffix"), "Enter Sales Order Suffix as 01");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        String SCQTYShip = getValue(WiseSmokeTestPage.SCQTYShip);
        Utility_Functions.xUpdateJson("SCQTYShip"+company, SCQTYShip);
        //commonObj.validateText(WiseSmokeTestPage.SCQTYShip,"1","1");
        click(WiseSmokeTestPage.SCConfirmOrder, "Confirm the order from shipping confirmation screen");
        click(WiseSmokeTestPage.F3exit, "Click on F3 Exit to navigate back to shipping confirmation menu");
        if (Utility_Functions.xIsDisplayed(ownDriver, WiseSmokeTestPage.confirmManualPicks)) {
            click(WiseSmokeTestPage.confirmManualPicks, "Confirming Manual Picks");
        }
    }

    public void invoiceSO() {
        click(WiseSmokeTestPage.Reports22, "Click on Reports 3/22 for invoicing");
        click(WiseSmokeTestPage.PrintInvoices, "Click on Print Invoices menu option 9");
        sendKeys(WiseSmokeTestPage.IRselection, "I", "Select I for Individual invoices");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        click(WiseSmokeTestPage.ReportInvOrd1, "Click on Key Numbers for Order 1");
        sendKeys(WiseSmokeTestPage.ReportInvOrd1, Utility_Functions.xGetJsonData("soNum"+company));
        sendKeys(WiseSmokeTestPage.ReportSuffOrd1, jsonData.getData("SCSuffix"), "Enter Sales Order Suffix as 01 in Print Invoices screen");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.actionKey(Keys.F1, ownDriver);
        Utility_Functions.actionKey(Keys.F3, ownDriver);
        Utility_Functions.actionKey(Keys.F3, ownDriver);
    }

    public void invoiceVerificationSOI() {

        sendKey(WiseSmokeTestPage.SONumberSOI, Utility_Functions.xGetJsonData("soNum"+company));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(WiseSmokeTestPage.SOILine2status, "Invoiced", "Invoiced");
        commonObj.validateText(WiseSmokeTestPage.SOILine3status, "Closed", "Closed");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
        //click(WiseSmokeTestPage.saleQExtBtn, "Click F3 Exit from SOI");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
    }

    public void navigatetoinventoryandSPInq() {
        click(WiseSmokeTestPage.InvMngmnt, "Click on Inventory Management from main menu");
        click(WiseSmokeTestPage.SPInq, "Click on Sales Person Inquiry");
    }

    public void invNSPInq() {
        commonObj.validateText(WiseSmokeTestPage.SPINQHeaderText, "Salesperson Inquiry", "Salesperson Inquiry");
        sendKey(WiseSmokeTestPage.SPINQQItemNumber, Utility_Functions.xGetJsonData("itemNum"+company));
        click(WiseSmokeTestPage.saleQExtBtn, "Click F3 Exit from SPInquiry");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
    }

    public void navigateToBuyersInq() {
        click(WiseSmokeTestPage.InvMngmnt, "Click on Inventory Management from main menu");
        click(WiseSmokeTestPage.BuyersInq, "Click on Buyer's Inquiry");
        //commonObj.validateText(WiseSmokeTestPage.BuyersInqHeaderText," Buyer's Inquiry"," Buyer's Inquiry");
    }

    public void invNBuyInq() {
        sendKey(WiseSmokeTestPage.BuyersStockNumber, Utility_Functions.xGetJsonData("itemNum"+company));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(WiseSmokeTestPage.BuyersItemDesc1, "QA Automation Desc1", "QA Automation Desc1");
        commonObj.validateText(WiseSmokeTestPage.BuyersItemDesc2, "QA Automation Desc2", "QA Automation Desc2");
        click(WiseSmokeTestPage.F3exit, "Click F3 Exit from Buyer's Inquiry");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
    }

    public void navigateToLiveShop() {
        click(WiseSmokeTestPage.InvMngmnt, "Click on Inventory Management from main menu");
        click(WiseSmokeTestPage.LiveInvShopping, "Click on Live Inventory Shopping");
        //commonObj.validateText(WiseSmokeTestPage.LiveInvShopHeaderText, " Live Inventory Shopping", "Live Inventory Shopping");
    }

    public void invNLiveShop() {
        click(WiseSmokeTestPage.LivePosToItem, "Click on Position to item to enter an item number");
        sendKeys(WiseSmokeTestPage.Industry, jsonData.getData("Industry"), "Enter as R for HVAC");
        sendKeys(WiseSmokeTestPage.LivePosToItem, jsonData.getData("LiveItem"), "Enter an item Number in Position to item number field");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(WiseSmokeTestPage.FirstItemLiveShop, "AABABM100", "AABABM100");
        click(WiseSmokeTestPage.F3exit, "Navigate back to Inventory Menu");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
    }

    public void navigateToPOEntry() {
        click(WiseSmokeTestPage.PurchaseOrder, "Click on Purchase Orders from main menu");
        click(WiseSmokeTestPage.POEntry, "Click on PO entry from Purchasing menu");
        commonObj.verifyElementContainsText(WiseSmokeTestPage.POHeaderText, " Purchase Order Headings", " Purchase Order Headings");
    }

    public void poNPOEntry() {
        clearText(WiseSmokeTestPage.ActionCodePOEntry);
        sendKey(WiseSmokeTestPage.ActionCodePOEntry, "I");
        sendKeys(WiseSmokeTestPage.PONumEntry, Utility_Functions.xGetJsonData("POSmoke"+company));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.verifyElementContainsText(WiseSmokeTestPage.POHeaderText, " Purchase Order Headings", " Purchase Order Headings");
        Utility_Functions.xAssertEquals(report, vendor, getAttribute(WiseSmokeTestPage.VendorPOEntry, "value"),  "Validating Vendor");
        click(WiseSmokeTestPage.F3exit, "Navigate back to PO Header screen");
        click(WiseSmokeTestPage.F3exit, "Navigate back to PO Header screen");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
    }

    public void navigateToPOInquiry() {
        click(WiseSmokeTestPage.PurchaseOrder, "Click on Purchase Orders from main menu");
        click(WiseSmokeTestPage.POInquiry, "Click on Purchase order-Inquiry from Purchasing menu");
    }

    public void poNPOInq() {
        click(WiseSmokeTestPage.PostoOrderPOI, "Click on Position to Order from PO Inquiry screen");
        sendKeys(WiseSmokeTestPage.PostoOrderPOI, Utility_Functions.xGetJsonData("POSmoke"+company));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.verifyElementContainsText(WiseSmokeTestPage.POIHeaderText, "PURCHASE ORDER INQUIRY", "PURCHASE ORDER INQUIRY");

        String POrderLine1 = Utility_Functions.getText(ownDriver, WiseSmokeTestPage.POrderLine1);
        System.out.println("POrderLine1" + POrderLine1);
        Utility_Functions.xUpdateJson("POrderLine1"+company, POrderLine1);

        String POrderLine2 = Utility_Functions.getText(ownDriver, WiseSmokeTestPage.POrderLine2);
        System.out.println("POrderLine2" + POrderLine2);
        Utility_Functions.xUpdateJson("POrderLine2"+company, POrderLine2);

        commonObj.verifyElementContainsText(WiseSmokeTestPage.POStatusLine1, "OPEN  ", "OPEN  ");
        commonObj.verifyElementContainsText(WiseSmokeTestPage.POStatusLine2, "CLOSED", "CLOSED");
        click(WiseSmokeTestPage.saleQExtBtn, "Click on F3 Exit to navigate back to PO main menu Screen");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
    }

    public void signoff() {
        click(WiseSmokeTestPage.signoff, "Click on option 90signoff to exit from WISE");
    }


}


