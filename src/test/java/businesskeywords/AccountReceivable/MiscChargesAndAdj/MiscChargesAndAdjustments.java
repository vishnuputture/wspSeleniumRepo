package businesskeywords.AccountReceivable.MiscChargesAndAdj;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.AccountReceivable.MiscChargesAndAdjustmentsPage.MiscChargesAndAdjustmentsPage;
import pages.Purchasing.OptionsConstantsPage;
import pages.Purchasing.VendorInvoiceReconciliationPage;
import pages.common.LoginPage;
import pages.common.MasterPage;
import pages.pricing.pricingmatrix.PricingMatrixPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

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
}
