package pages.Purchasing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class PurchaseOrderDetailsPage extends ReusableLib {

    public PurchaseOrderDetailsPage(Helper helper) {
        super(helper);
    }

    public static By PODHeader = By.id("D_1_16");
    public static By action1 = By.id("D_2_3");
    public static By lineNo = By.id("D_3_3");
    public static By shipVia = By.id("D_4_3");
    public static By orderno = By.id("D_2_37");
    public static By weight = By.id("D_2_57");
    public static By vendor = By.id("D_3_37");
    public static By shipViaPOD = By.id("D_4_3");
    public static By customerPOD = By.id("D_4_37");
    public static By quantityOrdered = By.id("I_7_6");
    public static By quantityOpen = By.id("D_8_15");
    public static By itemNumberPOD = By.id("I_7_23");
    public static By pricePOD = By.id("I_7_43");
    public static By umPOD = By.id("I_7_58");
    public static By disc = By.id("I_7_61");
    public static By extendedAmount = By.id("I_7_68");
    public static By descPOD = By.id("I_8_8");
    public static By line2 = By.id("I_8_48");
    public static By vendorPart = By.id("I_9_18");
    public static By relatedSo = By.id("I_9_52");
    public static By datePOD = By.id("I_10_9");
    public static By weightPOD = By.id("I_10_26");
    public static By pkgQty = By.id("I_10_45");
    public static By convFactor = By.id("D_11_52");
    public static By ediStat = By.id("D_2_15");
    public static By sendViaFax = By.id("D_3_33");
    public static By tyPOD = By.id("I_7_3");
    public static By errorMsg = By.id("D_3_64");
    public static By subjectEditMail = By.id("I_7_3_W2");

    public static By firstItemNumber = By.id("I_10_3");
    public static By secondItemNumber = By.id("I_11_3");
    public static By errorMsgPOD = By.id("D_24_2");
    public static By userIdentifier = By.id("D_4_29");
    public static By nameAppear = By.id("I_5_29");
    public static By poPrintSendHeader = By.id("D_2_29");
    public static By emailAddress = By.id("I_6_29");
    public static By teleNo = By.id("I_7_31");
    public static By continueTextBox = By.id("I_14_14_W1");
    public static By EditMailTextHeader = By.id("D_2_28_W1");
    public static By emailContact = By.id("D_5_20_W1");
    public static By emailAddressPopup = By.id("D_6_20_W1");
    public static By teleNo1 = By.id("I_7_37");
    public static By confirmPrintSendPopup = By.id("D_2_18_W1");
    public static By closedSentViaMail = By.id("D_4_1_W1");
    public static By teleNo2 = By.id("I_7_41");
    public static By getExtendedAmountPOD = By.id("I_8_68");
    public static By createSo = By.id("btnCF13");
    public static By sendViaMail = By.id("I_18_70");
    public static By sendViaEDI = By.id("I_19_70");

    public static By getLineItemsList = By.xpath("//div[string-length(normalize-space(text()))>75 ]");

    public static By amountHeader = By.id("D_1_65");
    public static By actionInput = By.id("I_2_12");
    public static By lineno = By.id("I_3_12");

    public static By convFactorValue = By.id("D_11_65");
    public static By orderNo2 = By.id("D_2_49");
    public static By btnSubmit = By.id("btnSubmit");
    public static By tbxMultiplier = By.id("I_7_61");

    /******************************* Actions panel locators *******************************/

    public static By f4 = By.id("btnCF04");

}

