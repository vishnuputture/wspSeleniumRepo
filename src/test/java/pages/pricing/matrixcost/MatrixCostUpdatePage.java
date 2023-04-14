package pages.pricing.matrixcost;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class MatrixCostUpdatePage extends ReusableLib {
    public MatrixCostUpdatePage(Helper helper) {
        super(helper);
    }

    public static By avgCostRadioBtn = By.xpath("//label[text()='Average Cost']/preceding::input[@id='RbtAvgCost']");
    public static By lastCostRadioBtn = By.xpath("//label[text()='Last Cost']/preceding::input[@id='RbtLastCost']");
    public static By pageHeader = By.id("lblPageHeader");
    public static By exitButton = By.id("btnCancel");
    public static By saveWarBtn = By.id("CssButton3");
    public static By noActionRadioBtn = By.id("rbtNoAction.1");
    public static By excMCRepoRadioBtn = By.id("rbtExlMC.1");
    public static By selectFunDrop = By.id("ddbSelFunction");
    public static By discardButton,updateButton = By.id("CssButton1");
    public static By cancelButton = By.id("CssButton2");
    public static By checkBox = By.id("RbtMtxUpdAll");
    public static By avgColor = By.id("outAvgCost.1");
    public static By lastColor = By.id("outLastCost.1");
    public static By continueButton = By.id("CssButton1");
    public static By saveButton = By.id("btnSubmit");
    public static By itmMsrPropoFld = By.id("inMatrix");

    public static By updateMatrixCostCheckbox = By.xpath("//div[contains(text(),'Update Matrix Cost')]/a[1]/input[1]");
    public static By firstItemNumber = By.id("inpItemNbr");
    public static By totalPages = By.id("txtTotalPage");
    public static By txtBoxSearch = By.id("inItemNumber");
    public static By poFieldItem = By.id("inPOCost");
    public static By btnF3 = By.id("btnExit");
    public static By f3ExitOpt = By.xpath("//option[text()='F3 - Exit']");
    public static By f1HelpOpt = By.xpath("//option[text()='F1 - Help']");
    public static By warningPopUp = By.xpath("//span[text()='Warning Message']");
    public static By compareTo = By.xpath("//label[contains(@for,'Rbt')]");
    public static By radioButton = By.xpath("//input[contains(@id,'rbtUpdMtxC')]");
    public static By tableHeaders = By.xpath("//div[@class='cell header-cell']/div");
    public static By poField = By.xpath("//input[contains(@id,'outPOCost')]");
    public static By exportExcel = By.xpath("//span[contains(text(),'Export to Excel')]");
    public static By ProposedMtxCost = By.xpath("//input[contains(@id,'outPropMtx')]");
    public static By greenActFilOpt = By.xpath("//div[contains(text(),'*Green')]");
    public static By cbtn = By.xpath("//input[@value='Continue']");
    public static By infMessage = By.xpath("//span[text()='Informational Message']");
    public static By invalidErrorPopUp = By.xpath("//input[contains(@class,'error-invalid')]");
    public static By warningMessagePopUp = By.xpath("//input[@value='Yes']");
    public static By inventoryManagementMenu2Header = By.xpath("//div[@class='app-header' and contains(text(),'Inventory')]");
    public static By itemNumberLink = By.xpath("//div[@class='link']");
    public static By itemLedgerHeader = By.xpath("//div[contains(text(),'Ledger')]");
    public static By itemNumber = By.xpath("//input[contains(@id,'rbtUpdMtxC')]/preceding::div[contains(@id,'outItemNum')]/a");
    public static By updatedPoValue = By.xpath("//input[@id='rbtUpdMtxC']/preceding::input[@id='outPOCost']");
    public static By nextPage = By.xpath("//span[text()='Next']");
}
