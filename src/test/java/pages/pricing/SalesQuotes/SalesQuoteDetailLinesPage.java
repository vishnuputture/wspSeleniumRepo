package pages.pricing.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SalesQuoteDetailLinesPage extends ReusableLib {

    public SalesQuoteDetailLinesPage(Helper helper) {
        super(helper);
    }

    public static By detailLinesHeader = By.id("wbScrTitle");
    public static By quoteNum = By.id("S0QUOTE");
    public static By jobName = By.id("S0JOBNME");
    public static By cutName = By.id("S0NAME");
    public static By typeItem = By.id("S5TYPE");
    public static By qty = By.id("S5QTY");
    public static By itemNumberItem = By.id("S1ITEM.1");
    public static By nextBtn = By.id("btnSubmit");
    public static By extBtn = By.id("BtnF3_Exit");
    public static By backBtn = By.id("btnBack");
    public static By printSendBtn = By.id("btnCF08_main");

    /************** Item Add Information Form **************/
    public static By itemNumber = By.id("D_3_23");
    public static By testDesc = By.id("I_4_23");
    public static By mtxCost = By.id("I_9_23");
    public static By poCost = By.id("I_10_23");
    public static By listPrc = By.id("I_11_23");
    public static By accept = By.id("btnCF09");
}
