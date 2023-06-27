package pages.inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class CostAdjustmentPage extends ReusableLib {

    public CostAdjustmentPage(Helper helper) {
        super(helper);
    }

    public static By lblTitle = By.xpath("//div[@id='outC1HDR']");

    public static By txtItemNumber = By.xpath("//div/input[contains(@id,'SITEM.')]");
    public static By txtQuantity = By.xpath("//div/input[contains(@id,'txtSQTYD.')]");
    public static By txtInCorrectCost = By.xpath("//div/input[contains(@id,'txtSICOST.')]");
    public static By txtNewCost = By.xpath("//div/input[contains(@id,'txtSNCOST.')]");
    public static By lblCurrAvgCost = By.xpath("//div[contains(@id,'SOCOST.')]");
    public static By lblCurrNewCost = By.xpath("//div[contains(@id,'outSACOST.')]");
    public static By btnProcess = By.xpath("//input[@id='btnSubmit']");
    public static By btnLedger = By.xpath("//input[contains(@id,'Button1.')]");
    public static By cycleCountAdj = By.xpath("//div[text()='1']/following-sibling::div/a[contains(text(),'Cycle')]");
    public static By countFiled = By.id("txtICCNTD.1");
    public static By countFiled2 = By.id("txtICCNTD.2");
    public static By searchIcon = By.xpath("//i[@class='fa fa-search']");
    public static By searchIcon2 = By.xpath("(//i[@class='fa fa-search'])[3]");
    public static By explanation = By.xpath("//input[@class='combo-main-box win-combo-option']");
    public static By process = By.xpath("//input[@type='button' and @id='btnSubmit']");
    public static By optBox = By.id("I_11_3");
    public static By optBox1 = By.id("I_12_3");

    public static By onHold = By.id("D_11_101");
    public static By quantity = By.id("AdjQty");
    public static By includeYes = By.id("I_6_64");
    public static By firRowAdj = By.id("outIAQTY.1");
    public static By secRowAdj = By.id("outIAQTY.2");
    public static By firRowAmt = By.id("outIAMT.1");
    public static By secRowAmt = By.id("outIAMT.2");
    public static By countNum = By.id("Ccount");
    public static By amountT = By.id("Amt1");
    public static By countMod = By.xpath("//div[@class='win-p win-leftAlign']");
    public static By adjQtyMod = By.xpath("(//div[@class='win-p win-leftAlign'])[2]");
    public static By amountyMod = By.xpath("(//div[@class='win-p win-leftAlign'])[3]");
    public static By procConfm = By.id("OutTitle");
    public static By cancelBtn = By.id("btnButton2");
    public static By postBtn = By.id("btnButton1");
    public static By continueBtn = By.id("btnButton3");
    public static By exitBtn = By.xpath("//input[@type='button' and @id='btnCF03']");
    public static By itemLedger = By.xpath("//i[@class='fa fa-item-ledger']");
    public static By itemLedSearch = By.xpath("//i[@class='fa fa-search']");
    public static By extBtnLedger = By.id("btnExit");
    public static By units = By.id("outUnits.1");
    public static By qtyAfter = By.id("outQtyAfter.1");
    public static By explanationLedger = By.id("outExplanation.1");


}
