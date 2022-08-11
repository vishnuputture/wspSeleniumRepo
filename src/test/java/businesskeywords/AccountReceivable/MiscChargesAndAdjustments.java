package businesskeywords.AccountReceivable;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.AccountReceivable.MiscChargesAndAdjustmentsPage;
import pages.PurchaseOrders.OptionsConstantsPage;
import pages.PurchaseOrders.VendorInvoiceReconciliationPage;
import pages.common.LoginPage;
import pages.common.MasterPage;
import pages.inventory.ItemMasterPage;
import pages.pricing.pricingmatrix.PricingMatrixPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MiscChargesAndAdjustments extends ReusableLib {

    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public MiscChargesAndAdjustments(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    public void navigateToAccountReceivable(){
        click(MasterPage.accountReceivable, "Click [Account Receivable]");
    }

    public void navigateToMiscChargesAndAdjustment() {
        navigateToAccountReceivable();
        click(MiscChargesAndAdjustmentsPage.entryMiscChargesAdj, "Click [Entry - Misc. Charges & Adjustments]");
        miscChargeAdjHeader();
    }

    public void navigateToCashReceiptAdjEntry() {
        click(MasterPage.accountReceivable, "Click [Account Receivable]");
        clickCashReceiptAdjEntry();
    }

    public void clickCashReceiptAdjEntry(){
        click(MiscChargesAndAdjustmentsPage.cashReceiptAdjEntry, "Click [Entry - Payments & Adjustments]");
        commonObj.validateText(MiscChargesAndAdjustmentsPage.cashAdjHeader, "Cash Receipts/Adj Entry", "Validating [Cash Receipts/Adj Entry] page title");
    }

    public void navigateToProcessingOptions() {
        click(MasterPage.menuOptionsAndConstants, "Click [--> Options and Constants Menu]");
        click(MiscChargesAndAdjustmentsPage.optionsARMiscCharges, "Click [Options - A/R Misc. Charges]");
        commonObj.validateText(OptionsConstantsPage.processingOptionHeader, "Processing Options", "Validating [Processing Options] page title");
    }

    public void miscChargeAdjHeader() {
        commonObj.validateText(MiscChargesAndAdjustmentsPage.headerMis, "Misc. Charges and Adjustments", "Validating [Misc. Charges and Adjustments] page title");
    }

    public void miscChargesAdjUI() {
        miscChargeAdjHeader();
        String[] fields = {"Action Code . . . .", "Transaction Type. .", "Memo Type . . . . .", "Invoice Number. . .", "Days Business . . .",
                "Invoice Amount. . .", "Invoice Date. . . .", "Original Invoice #.", "Payment Terms . . .", "Taxable Code. . . .", "State Tax Amount. .", "Local Tax Amount. .", "Dispute Code. . . .",
                "Dispute Explanation", "Units . . . . . . .", "Unit Price. . . . .", "Date Shipped. . . .", "Customer P.O. # . .", "Prev Inv. :", "Prev Name :", "Transaction Types:", "CM - Credit Memo",
                "DM - Debit Memo", "Invoice Comment Lines", "Display in EBP. . . ."};
        String[] ids = {"D_3_1", "D_4_1", "D_5_1", "D_7_1", "D_8_1",
                "D_9_1", "D_10_1", "D_11_1", "D_12_1", "D_13_1", "D_15_1", "D_16_1", "D_17_1",
                "D_18_1", "D_19_1", "D_20_1", "D_21_1", "D_22_1", "D_3_24", "D_4_25", "D_8_25", "D_9_36",
                "D_10_31", "D_14_1", "D_22_43"};
        for (int i = 0; i < fields.length; i++) {
            commonObj.validateText(By.id("" + ids[i] + ""), fields[i], "Validating field [" + fields[i] + "]");
        }
        commonObj.validateText(MiscChargesAndAdjustmentsPage.userId, getProperties("STGUserName"), "Validating user [" + getProperties("STGUserName") + "]");
        commonObj.validateText(VendorInvoiceReconciliationPage.warningMessageGMMgr, "Key in Transaction Type, Memo Type & Acct. Number and press <ENTER>.", "Validating message [Key in Transaction Type, Memo Type & Acct. Number and press <ENTER>.]");
    }

    public void exitMiscChargesAdj() {
        click(SpecialPriceAllowancePage.btnExit, "Click [F3=Exit]");
    }

    public void selectTypeAccount(){
        sendKeys(MiscChargesAndAdjustmentsPage.transactionType, jsonData.getData("TransactionType"),"Enter Transaction Type as "+jsonData.getData("TransactionType"));
        click(PricingMatrixPage.addRow);
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.typeSelection,"1","Select Memo Type");
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.accountNoTxtBx, jsonData.getData("AccountNo"), "Select Account Number");
        Utility_Functions.xUpdateJson("AccountNumber",getAttribute(MiscChargesAndAdjustmentsPage.accountNoTxtBx,"value"));
        Utility_Functions.xUpdateJson("InvoiceNumber",getAttribute(MiscChargesAndAdjustmentsPage.invoiceNo,"value"));
        Utility_Functions.xUpdateJson("InvoiceDate",getAttribute(MiscChargesAndAdjustmentsPage.invoiceDate,"value"));
    }

    public void displayInEBP(){
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.displayInEBP,"Y","Enter EBP");
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - Invalid Invoice amount.", "Validating message [ERROR - Invalid Invoice amount.]");
    }

    public void enterInvoiceNo(){
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.invoiceAmount, jsonData.getData("InvoiceAmount"), "Enter CM Invoice Amount");
        Utility_Functions.xUpdateJson("InvoiceAmount",jsonData.getData("InvoiceAmount"));
    }

    public void validationMessage(){
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ALL ENTRIES VALID.  PRESS F9 TO ACCEPT.", "Validating message [ALL ENTRIES VALID.  PRESS F9 TO ACCEPT.]");
        Utility_Functions.actionKey(Keys.F9,ownDriver);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "Successful application of entries to customer account.", "Validating message [Successful application of entries to customer account.]");
    }

    public void verifyDisplayEBP() {
        Utility_Functions.xAssertEquals(report,getAttribute(MiscChargesAndAdjustmentsPage.actionCodeTxtBx,"value"),"A","");
        selectTypeAccount();
        displayInEBP();
        enterInvoiceNo();
        validationMessage();
    }

    public By getTypeAndInvoice(String transaction,String invoiceNo){
        return By.xpath("//div[text()='"+transaction+"']/following-sibling::div[text()='"+invoiceNo+"']");
    }

    public void verifyInvoiceReflectedOnCashRecAdjEntryMemo() {
        sendKeys(VendorInvoiceReconciliationPage.dueDateTextBox,Utility_Functions.xGetJsonData("AccountNumber"),"Enter customer Number");
        sendKeysAndEnter(LoginPage.userNametxtBox,"CA","Enter Payment Type");
        click(VendorInvoiceReconciliationPage.moreKeys,"Click [F24=More Keys]");
        click(CustomerGroupMaintenancePage.sort,"Click [F10=Open Amt]");
        String transactionType= jsonData.getData("TransactionType");
        String invoiceNo=Utility_Functions.xGetJsonAsString("InvoiceNumber");
        By getOpenAmt=By.xpath("(//div[text()='003379']/following-sibling::div[contains(text(),'100')])[4]");
        commonObj.validateElementExists(getTypeAndInvoice(transactionType,invoiceNo), "Validating Transaction Type and Invoice Number");
        commonObj.validateElementExists(getOpenAmt, "Validating Open AMount");
        String invoiceDate=getAttribute(MiscChargesAndAdjustmentsPage.invoiceDateCashReceipt,"value");
        Utility_Functions.xAssertEquals(report,invoiceDate,Utility_Functions.xGetJsonData("InvoiceDate"),"");
    }

    public void changeSystemAssignNo(){
        if(jsonData.getData("AssignNumber").equals("1")){
            sendKeysAndEnter(OptionsConstantsPage.discMultOpt,"1","Enter [1] into assign Number field");
        }else {
            clearText(OptionsConstantsPage.discMultOpt);
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        }
    }

    public void verifyInvoiceNo(){
        Utility_Functions.xAssertEquals(report,"000000",getAttribute(MiscChargesAndAdjustmentsPage.invoiceNo,"value"),"");
    }

    public void validateErrorActionCode(String actionCode){
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.actionCodeTxtBx, jsonData.getData(actionCode),"Enter Special Character Into Action Code field" );
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - 'A', 'I' and 'C' are the only valid Action Codes.", "Validating message [ERROR - 'A', 'I' and 'C' are the only valid Action Codes.]");
    }

    public void verifyActionCode(){
        validateErrorActionCode("ActionCodeSpecialCharacter");
        validateErrorActionCode("ActionCodeNumber");
        validateErrorActionCode("ActionInvalidCode");
        clearText(MiscChargesAndAdjustmentsPage.actionCodeTxtBx);
        Utility_Functions.actionKey(Keys.ENTER,ownDriver);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "Key in Transaction Type, Memo Type & Acct. Number and press <ENTER>.", "Validating message [Key in Transaction Type, Memo Type & Acct. Number and press <ENTER>.]");
    }

    public void validateErrorTransactionType(String transactionType){
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.transactionType, jsonData.getData(transactionType),"Enter Special Character Into Action Code field" );
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - Invalid Transaction Type.", "Validating message [ERROR - Invalid Transaction Type.]");
    }

    public void verifyTransactionTypeField(){
        validateErrorTransactionType("TransactionTypeSpecialCharacter");
        validateErrorTransactionType("TransactionTypeNumber");
        validateErrorTransactionType("TransactionInvalidType");
        clearText(MiscChargesAndAdjustmentsPage.transactionType);
        Utility_Functions.actionKey(Keys.ENTER,ownDriver);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - Invalid Transaction Type.", "Validating message [ERROR - Invalid Transaction Type.");
    }

    public void validateErrorMemoType(String memoType){
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.memoType, jsonData.getData(memoType),"Enter Special Character Into Action Code field" );
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - Invalid Memo Type -- press F1 to browse valid Memo Types.", "Validating message [ERROR - Invalid Memo Type -- press F1 to browse valid Memo Types.]");
    }

    public void verifyMemoTypeField(){
        sendKeys(MiscChargesAndAdjustmentsPage.transactionType,"CM");
        validateErrorMemoType("MemoTypeSpecialCharacter");
        validateErrorMemoType("MemoTypeNumber");
        validateErrorMemoType("MemoInvalidType");
        clearText(MiscChargesAndAdjustmentsPage.memoType);
        Utility_Functions.actionKey(Keys.ENTER,ownDriver);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - Invalid Memo Type -- press F1 to browse valid Memo Types.", "Validating message [ERROR - Invalid Memo Type -- press F1 to browse valid Memo Types.]");
    }

    public void validateErrorAccNo(String accNo){
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.accountNoTxtBx, jsonData.getData(accNo),"Enter Special Character Into Action Code field" );
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - Account Number is either blank or invalid.", "Validating message [ERROR - Account Number is either blank or invalid.]");
    }

    public void verifyAccountField(){
        sendKeys(MiscChargesAndAdjustmentsPage.transactionType,"CM");
        sendKeys(MiscChargesAndAdjustmentsPage.memoType,"DSC");
        validateErrorAccNo("AccNoSpecialCharacter");
        validateErrorAccNo("AccNoNumber");
        validateErrorAccNo("InvalidAccNo");
        clearText(MiscChargesAndAdjustmentsPage.accountNoTxtBx);
        Utility_Functions.actionKey(Keys.ENTER,ownDriver);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - Account Number is either blank or invalid.", "Validating message [ERROR - Account Number is either blank or invalid.]");
    }

    public void verifyInvoiceCommentLines(){
        selectTypeAccount();
        displayInEBP();
        sendKeys(MiscChargesAndAdjustmentsPage.invoiceComLine, jsonData.getData("InvoiceCommentLine"));
        enterInvoiceNo();
        validationMessage();
    }

    public void validateComLine(){
        selectTypeAccount();
        //Utility_Functions.xAssertEquals(report,getAttribute(MiscChargesAndAdjustmentsPage.invoiceComLine,"value"),jsonData.getData("InvoiceCommentLine"),"Invoice Comment line matches");
    }

    /**
     * Keyword to validate the default selections in [Misc. Charges and Adjustments] page
     */
    public void vrfyDefaultValue(){
        Utility_Functions.xAssertEquals(report,"A",getAttribute(MiscChargesAndAdjustmentsPage.actionCodeTxtBx,"value"),"Validating default [Action Code] value");
        sendKeys(MiscChargesAndAdjustmentsPage.transactionType, jsonData.getData("TransactionType"), "Entering Transaction Type");
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.memoType, jsonData.getData("MemoType"), "Entering Memo Type");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.memoTypeDesc, "Discount Adjustment", "Validating memo type description");
        Utility_Functions.xAssertEquals(report,"000000",getAttribute(MiscChargesAndAdjustmentsPage.accountNoTxtBx,"value"),"Validating default [Account Number] value");
        Utility_Functions.xAssertEquals(report,"S",getAttribute(MiscChargesAndAdjustmentsPage.accountNoSuffixTxtBx,"value"),"Validating default [Account Number suffix] value");
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - Account Number is either blank or invalid.", "Validating message [ERROR - Account Number is either blank or invalid.]");
    }

    /**
     * Keyword to validate the default selections in [Misc. Charges and Adjustments] page
     */
    public void vrfyDefaults(){
        Utility_Functions.xAssertEquals(report,"A",getAttribute(MiscChargesAndAdjustmentsPage.actionCodeTxtBx,"value"),"Validating default [Action Code] value");
        String daysBusiness = getAttribute(MiscChargesAndAdjustmentsPage.daysBusiness,"value");
        if (daysBusiness.isEmpty())
            report.updateTestLog("Verify Days Business is populated", "Verify Days Business", Status.FAIL);
        else
            report.updateTestLog("Verify Days Business is populated", "Verify Days Business",Status.PASS);

        String dateShipped = new SimpleDateFormat("MM/dd/yy").format(Calendar.getInstance().getTime());
        if (dateShipped.charAt(0) == '0'){
            dateShipped = dateShipped.substring(1);
        }
        String dateShippedActual = getAttribute(MiscChargesAndAdjustmentsPage.tbxDateShipped,"value").trim();
        Utility_Functions.xAssertEquals(report, dateShipped, dateShippedActual,"Validating default [Action Code] value");
    }

    /**
     * Keyword to enter Memo Type in [Misc. Charges and Adjustments] page
     */
    public void enterMemoType() {
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.memoType, jsonData.getData("MemoType"), "Entering Memo Type");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to enter Transaction Type in [Misc. Charges and Adjustments] page
     */
    public void enterTransactionType() {
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.transactionType, jsonData.getData("TransactionType"), "Entering Transaction Type");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to enter Invoice Amount in [Misc. Charges and Adjustments] page
     */
    public void enterInvoiceAmount() {
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.invoiceAmount, jsonData.getData("InvoiceAmount"), "Entering Invoice Amount");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to select Account Number in [Misc. Charges and Adjustments] page
     */
    public void selectAccountNumber(){
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.accountNoTxtBx, jsonData.getData("AccountNo"), "Entering Account Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.xAssertEquals(report, jsonData.getData("AccountNo"), getAttribute(MiscChargesAndAdjustmentsPage.accountNoTxtBx,"value"),"Validating selected [Account Number] value");

        String invoiceNumber = getAttribute(MiscChargesAndAdjustmentsPage.invoiceNo,"value");
        String invoiceNumberSuffix = getAttribute(MiscChargesAndAdjustmentsPage.invoiceNoSuffixTbx,"value");
        if (invoiceNumber.isEmpty() && invoiceNumberSuffix.isEmpty())
            report.updateTestLog("Verify system generated Invoice Number", "Verify Invoice Number", Status.FAIL);
        else
            report.updateTestLog("Verify system generated Invoice Number", "Verify Invoice Number",Status.PASS);
    }

    /**
     * Keyword to select random Account Number in [Misc. Charges and Adjustments] page
     */
    public void selectRandomAccountNumber() {
        click(MiscChargesAndAdjustmentsPage.accountNo, "Clicked on [Account Number] byperlink");
        int count = Utility_Functions.xRandomFunction(1, 10);
        while (count > 0) {
            click(ItemMasterPage.btnDown);
            count--;
        }
        String acctNumber = getText(MiscChargesAndAdjustmentsPage.acctNumber);
        String acctNumberSuffix = getText(MiscChargesAndAdjustmentsPage.pNumber);
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.optBox, "1", "Select Account Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.xAssertEquals(report, acctNumber, getAttribute(MiscChargesAndAdjustmentsPage.accountNoTxtBx,"value"),"Validating selected [Account Number] value");
        Utility_Functions.xAssertEquals(report, acctNumberSuffix, getAttribute(MiscChargesAndAdjustmentsPage.accountNoSuffixTxtBx,"value"),"Validating selected [Account Number] suffix value");

        jsonData.putData("AccountNo", acctNumber);
        Utility_Functions.xUpdateJson("AccountNo", acctNumber);
    }

    /**
     * Keyword to select random Account Number in [Misc. Charges and Adjustments] page
     */
    public void selectRandomAccountNumber2() {
        click(MiscChargesAndAdjustmentsPage.accountNo, "Clicked on [Account Number] byperlink");
        int count = Utility_Functions.xRandomFunction(1, 10);
        while (count > 0) {
            click(ItemMasterPage.btnDown);
            count--;
        }
        String acctNumber = getText(MiscChargesAndAdjustmentsPage.acctNumber);
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.optBox, "1", "Select Account Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.xAssertEquals(report, acctNumber, getAttribute(MiscChargesAndAdjustmentsPage.accountNoTxtBx,"value"),"Validating selected [Account Number] value");

        jsonData.putData("AccountNo", acctNumber);
        Utility_Functions.xUpdateJson("AccountNo", acctNumber);
    }

    /**
     * Keyword to enter random generated Invoice Number in [Misc. Charges and Adjustments] page
     */
    public void enterRandomInvoiceNumber() {
        String randomInvoiceNumber = "1" + Utility_Functions.xRandomFunction(99999);
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.invoiceNo, randomInvoiceNumber, "Entering random Invoice Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to validate InvoiceNumber field in [Misc. Charges and Adjustments] page
     */
    public void vrfyInvoiceNumberField() {
        String randomInvoiceNumber = "1"+Utility_Functions.xRandomFunction(99999);
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.invoiceNo, randomInvoiceNumber, "Entering random Invoice Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - Invalid Invoice amount.", "Validating message [ERROR - Invalid Invoice amount.]");

        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.invoiceAmount, jsonData.getData("InvoiceAmount"), "Entering Invoice Amount");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ALL ENTRIES VALID.  PRESS F9 TO ACCEPT.", "Validating message [ALL ENTRIES VALID.  PRESS F9 TO ACCEPT.]");
    }

    /**
     * Keyword to validate [ERROR - Account Number is either blank or invalid.] message in [Misc. Charges and Adjustments] page
     */
    public void vrfyAccNoInvalidMsg() {
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - Account Number is either blank or invalid.", "Validating message [ERROR - Account Number is either blank or invalid.]");
    }

    /**
     * Keyword to validate Transaction Type field is required in [Misc. Charges and Adjustments] page
     */
    public void vrfyTransactionTypeRequired() {
        String randomInvoiceNumber = "1"+Utility_Functions.xRandomFunction(99999);
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.invoiceNo, randomInvoiceNumber, "Entering random Invoice Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - Invalid Transaction Type.", "Validating message [ERROR - Invalid Transaction Type.]");

        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.invoiceAmount, jsonData.getData("InvoiceAmount"), "Entering random Invoice Amount");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - Invalid Transaction Type.", "Validating message [ERROR - Invalid Transaction Type.]");

        String invoiceDate = new SimpleDateFormat("MM/dd/yy").format(Calendar.getInstance().getTime());
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.invoiceDate, invoiceDate, "Entering Invoice Date");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - Invalid Transaction Type.", "Validating message [ERROR - Invalid Transaction Type.]");

        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.transactionType, jsonData.getData("TransactionType"), "Entering Transaction Type");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - Invalid Invoice amount.", "Validating message [ERROR - Invalid Invoice amount.]");

        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.invoiceAmount, jsonData.getData("InvoiceAmount"), "Entering random Invoice Amount");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ALL ENTRIES VALID.  PRESS F9 TO ACCEPT.", "Validating message [ALL ENTRIES VALID.  PRESS F9 TO ACCEPT.]");
    }

    /**
     * Keyword to validate Invoice Date field in [Misc. Charges and Adjustments] page
     */
    public void vrfyInvoiceDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        String invoiceDate = new SimpleDateFormat("MM/dd/yy").format(calendar.getTime());
        if (invoiceDate.charAt(0) == '0'){
            invoiceDate = invoiceDate.substring(1);
        }
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.invoiceDate, invoiceDate, "Entering Invoice Date as Today's date + 1");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ALL ENTRIES VALID.  PRESS F9 TO ACCEPT.", "Validating message [ALL ENTRIES VALID.  PRESS F9 TO ACCEPT.]");

        calendar.add(Calendar.DATE, 31);
        invoiceDate = new SimpleDateFormat("MM/dd/yy").format(calendar.getTime());
        if (invoiceDate.charAt(0) == '0'){
            invoiceDate = invoiceDate.substring(1);
        }
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.invoiceDate, invoiceDate, "Entering Invoice Date as as Today's date + 31");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "WARNING - Date is greater than 30 days from today.  F7 to accept.", "Validating message [WARNING - Date is greater than 30 days from today.  F7 to accept.]");

        Utility_Functions.actionKey(Keys.F7, ownDriver);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ALL ENTRIES VALID.  PRESS F9 TO ACCEPT.", "Validating message [ALL ENTRIES VALID.  PRESS F9 TO ACCEPT.]");

        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.tbxDateShipped, invoiceDate, "Entering Date Shipped as as Today's date + 31");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - SHIP Date Must Be Current Date", "Validating message [ERROR - SHIP Date Must Be Current Date]");
    }

    /**
     * Keyword to validate [Display in EBP] field error in [Misc. Charges and Adjustments] page
     */
    public void vrfyDisplayInEBPError() {
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "The indicated field is in error and must be corrected to proceed.", "Validating message [The indicated field is in error and must be corrected to proceed.]");
    }

    /**
     * Keyword to validate Invalid Invoice Amount error in [Misc. Charges and Adjustments] page
     */
    public void vrfyInvalidInvoiceAmtError() {
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - Invalid Invoice amount.", "Validating message [ERROR - Invalid Invoice amount.]");
    }

    /**
     * Keyword to validate Alll Entries Valid message in [Misc. Charges and Adjustments] page
     */
    public void vrfyAllEntriesValidMsg() {
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ALL ENTRIES VALID.  PRESS F9 TO ACCEPT.", "Validating message [ALL ENTRIES VALID.  PRESS F9 TO ACCEPT.]");
    }

    /**
     * Keyword to validate [Display in EBP] field in [Misc. Charges and Adjustments] page
     */
    public void vrfyDisplayInEBPField() {
        vrfyDisplayInEBPError();
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.displayInEBP, "Y", "Entering [Display in EBP] value");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        vrfyInvalidInvoiceAmtError();
        enterInvoiceAmount();
        vrfyAllEntriesValidMsg();
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.displayInEBP, "N", "Entering [Display in EBP] value");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        vrfyAllEntriesValidMsg();
    }

    /**
     * Keyword to get Account Number value from [Misc. Charges and Adjustments] page
     */
    public String getAccountNumber() {
        String accNumber = getAttribute(MiscChargesAndAdjustmentsPage.accountNoTxtBx, "value").trim();
        jsonData.putData("AccountNo", accNumber);
        Utility_Functions.xUpdateJson("AccountNo", accNumber);
        return accNumber;
    }

    /**
     * Keyword to get Account Name value from [Misc. Charges and Adjustments] page
     */
    public String getAccountName() {
        String accName = getText(MiscChargesAndAdjustmentsPage.tbxAccountName).trim();
        jsonData.putData("AccountName", accName);
        Utility_Functions.xUpdateJson("AccountName", accName);
        return accName;
    }

    /**
     * Keyword to get Invoice Number value from [Misc. Charges and Adjustments] page
     */
    public String getInvoiceNumber() {
        String invoiceNumb = getAttribute(MiscChargesAndAdjustmentsPage.invoiceNo, "value").trim();
        String invoiceNumbSuffix = getAttribute(MiscChargesAndAdjustmentsPage.invoiceNoSuffixTbx, "value").trim();
        String invoiceNumber =  invoiceNumb+"-"+invoiceNumbSuffix;
        jsonData.putData("InvoiceNumber", invoiceNumber);
        Utility_Functions.xUpdateJson("InvoiceNumber", invoiceNumber);
        return invoiceNumber;
    }

    /**
     * Keyword to validate [Prev Inv] default value in [Misc. Charges and Adjustments] page
     */
    public void vrfyDefaultPrevInvPrevNameValue() {
        commonObj.validateText(MiscChargesAndAdjustmentsPage.tbxPrevInv, "000000", "Validating [Prev Inv.] default value");
    }

    /**
     * Keyword to validate [Prev Inv] and [Prev Name] fields are auto populated in [Misc. Charges and Adjustments] page
     */
    public void vrfyPrevInv() {
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "Successful application of entries to customer account.", "Validating message [Successful application of entries to customer account.]");

        String prevInv = getText(MiscChargesAndAdjustmentsPage.tbxPrevInv).trim();
        String prevInvSuffix = getText(MiscChargesAndAdjustmentsPage.tbxPrevInvSuffix).trim();
        String prevInvActual = prevInv+"-"+prevInvSuffix;
        String prevInvExpected = jsonData.getData("InvoiceNumber");
        Utility_Functions.xAssertEquals(report, prevInvExpected, prevInvActual,"Validating [Prev Inv.] auto-populated value");

        String prevNameActual = getText(MiscChargesAndAdjustmentsPage.tbxPrevName).trim();
        String prevNameExpected = jsonData.getData("AccountName");
        Utility_Functions.xAssertEquals(report, prevNameExpected, prevNameActual,"Validating [Prev Name] auto-populated value");
    }

    /**
     * Keyword to validate user cannot change or delete CM/DM transaction in [Misc. Charges and Adjustments] page
     */
    public void vrfyCMDMTransactionCannotBeModified() {
        Utility_Functions.xAssertEquals(report,"A",getAttribute(MiscChargesAndAdjustmentsPage.actionCodeTxtBx,"value"),"Validating default [Action Code] value");
        Utility_Functions.xAssertEquals(report,jsonData.getData("TransactionType"), getAttribute(MiscChargesAndAdjustmentsPage.transactionType,"value"),"Validating last used [Transaction Type] value");
        Utility_Functions.xAssertEquals(report,jsonData.getData("MemoType"), getAttribute(MiscChargesAndAdjustmentsPage.memoType,"value"),"Validating last used [Memo Type] value");

        String accNo = jsonData.getData("AccountNo");
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.accountNoTxtBx, accNo, "Entering previously used [Account Number] value");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        String invNo = jsonData.getData("InvoiceNumber").substring(0, 6);
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.accountNoTxtBx, invNo, "Entering previously used [Invoice Number] value");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.actionCodeTxtBx, "I", "Entering [Action Code] value");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        sendKeysAndEnter(MiscChargesAndAdjustmentsPage.actionCodeTxtBx, "D", "Entering [Action Code] value");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(MiscChargesAndAdjustmentsPage.custMsg, "ERROR - 'A', 'I' and 'C' are the only valid Action Codes.", "Validating message [ERROR - 'A', 'I' and 'C' are the only valid Action Codes.]");
    }

}