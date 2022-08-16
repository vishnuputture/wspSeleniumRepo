package pages.AccountReceivable.MiscChargesAndAdjustmentsPage;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class MiscChargesAndAdjustmentsPage extends ReusableLib {
    public MiscChargesAndAdjustmentsPage(Helper helper) {
        super(helper);
    }

    public static By entryMiscChargesAdj = By.xpath("//div[@id='D_6_7']/a");
    public static By cashReceiptAdjEntry=By.xpath("//div[@id='D_5_7']/a");
    public static By optionsARMiscCharges=By.xpath("//div[@id='D_14_7']/a");
    public static By cashAdjHeader=By.id("D_2_29");
    public static By headerMis = By.id("D_2_20");
    public static By invoiceComLine = By.id("I_15_49");
    public static By userId=By.id("D_1_2");
    public static By actionCodeTxtBx=By.id("I_3_22");
    public static By invoiceDateCashReceipt=By.id("I_3_22");
    public static By transactionType = By.id("I_4_22");
    public static By memoType=By.id("I_5_22");
    public static By accountNo = By.xpath("//div[@id='D_6_1']/a");
    public static By accountNoTxtBx = By.id("I_6_22");
    public static By invoiceNo=By.id("I_7_22");
    public static By daysBusiness=By.id("I_8_22");
    public static By invoiceAmount = By.id("I_9_22");
    public static By memoTypeDesc=By.id("D_5_35");
    public static By displayInEBP = By.id("I_22_71");
    public static By custMsg=By.id("D_23_1");
    public static By typeSelection = By.id("I_7_1");
    public static By invoiceDate=By.id("I_10_22");

    public static By taxableCode = By.id("D_13_1");
    public static By stateTaxAmount=By.id("D_15_1");
    public static By localTaxAmount=By.id("D_16_1");
    public static By disputeCode = By.id("D_17_1");
    public static By disputeExplanation=By.id("D_18_1");
    public static By units = By.id("D_19_1");
    public static By unitPrice=By.id("D_20_1");
    public static By dateShipped = By.id("D_21_1");
    public static By customerPO=By.id("D_22_1");
    public static By prevInv=By.id("D_3_24");
    public static By prevName=By.id("D_4_25");
    public static By transactionTypes = By.id("D_8_25");
    public static By debitMemo = By.id("D_10_31");
    public static By invoiceCommentLines=By.id("D_14_1");


    public static By accountNoSuffixTxtBx = By.id("I_6_41");
    public static By invoiceNoSuffixTbx=By.id("I_7_31");
    public static By tbxDateShipped=By.id("I_21_22");
    public static By tbxAccountName=By.id("D_6_44");
    public static By tbxPrevInv=By.id("D_3_60");
    public static By tbxPrevInvSuffix=By.id("D_3_69");
    public static By tbxPrevName=By.id("D_4_60");

    /************************************* Mailing Master Search page elements *************************************/
    public static By acctNumber=By.id("D_8_75");
    public static By pNumber=By.id("D_8_73");
    public static By optBox=By.id("I_8_2");


}