package pages.pricing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MatrixCostUpdatePage extends ReusableLib {
    public MatrixCostUpdatePage(Helper helper) {
        super(helper);
    }

    public static By avgCostRadioBtn=By.xpath("//label[text()='Average Cost']/preceding::input[@id='RbtAvgCost']");
    public static By lastCostRadioBtn=By.xpath("//label[text()='Last Cost']/preceding::input[@id='RbtLastCost']");
    public static By enabledRadioBtn = By.xpath("//input[@class='label']");
    public static By f3ExitOpt = By.xpath("//option[text()='F3 - Exit']");
    public static By f1HelpOpt = By.xpath("//option[text()='F1 - Help']");
    public static By warningPopUp = By.xpath("//span[text()='Warning Message']");
    public static By exitButton=By.id("btnCancel");
    public static By noActionRadioBtn=By.id("rbtNoAction.1");
    public static By excMCRepoRadioBtn=By.id("rbtExlMC.1");
    public static By selectFunDrop=By.id("ddbSelFunction");
    public static By discardButton=By.id("CssButton1");
    public static By cancelButton=By.id("CssButton2");
    public static By checkBox=By.id("RbtMtxUpdAll");
    public static By avgColor=By.id("outAvgCost.1");
    public static By lastColor=By.id("outLastCost.1");
    public static By continueButton=By.id("CssButton1");
    public static By radioButton=By.xpath("//input[contains(@id,'rbtUpdMtxC')]");
    public static By poField=By.xpath("//input[contains(@id,'outPOCost')]");
    public static By ProposedMtxCost=By.xpath("//input[contains(@id,'outPropMtx')]");
    public static By saveButton=By.id("btnSubmit");
    public static By updateButton=By.id("CssButton1");
    public static By invalidErrorPopUp = By.xpath("//input[contains(@class,'error-invalid')]");
    public static By warningMessagePopUp = By.xpath("//input[@value='Yes']");
    public static By noChangesPopUp = By.xpath("//div[contains(text(),'No changes to update')]");
    public static By inventoryManagementMenu2Header = By.xpath("//div[@class='app-header' and contains(text(),'Inventory')]");
    public static By itemNumberLink = By.xpath("//div[@class='link']");
    public static By itemLedgerHeader=By.xpath("//div[contains(text(),'Ledger')]");
    public static By firstItemNumber=By.xpath("//input[@id='inpItemNbr']");
    public static By itemNumber=By.xpath("//input[contains(@id,'rbtUpdMtxC')]/preceding::div[contains(@id,'outItemNum')]/a");
    public static By updatedPoValue=By.xpath("//input[@id='rbtUpdMtxC']/preceding::input[@id='outPOCost']");
    public static By totalPages=By.xpath("//div[@id='txtTotalPage']");
    public static By itemList=By.xpath("//a[@href='javascript:void(0)']");
    public static By nextPage=By.xpath("//span[text()='Next']");
    public static By txtBoxSearch = By.xpath("//input[@id='inItemNumber']");
    public static By poFieldItem = By.xpath("//input[@id='inPOCost']");
    public static By btnF3=By.id("btnExit");
    public static By validateUpdatedPoValue(String itemNo){
        return By.xpath("//a[text()='"+itemNo+"']/following::input");
    }
    public static By validateItemNumber(String itemNo){
        return By.xpath("//a[text()='"+itemNo+"']");
    }

}
