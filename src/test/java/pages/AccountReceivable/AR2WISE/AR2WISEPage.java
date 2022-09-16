package pages.AccountReceivable.AR2WISE;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class AR2WISEPage extends ReusableLib {
    public AR2WISEPage(Helper helper) {
        super(helper);
    }

    public static By hdrARGLDInquiry = By.xpath("//span[text()='A/R G/L DETAIL INQUIRY']");
    public static By hdrTrnsctnExcptnList = By.xpath("//span[text()='transaction exception list']");
    public static By btnOpenMenu = By.id("open");
    public static By menuTrnsctnExcptn = By.xpath("//a[text()='Transaction Exception']");
    public static By loaderIcon = By.xpath("//div[@class='loader ng-star-inserted']");
    public static By tbxCompany = By.id("typeahead-company");
    public static By ddnCompany = By.xpath("//ngb-typeahead-window/button");
    public static By tbxDate = By.xpath("//label[text()='Date']/following-sibling::div/input");
    public static By ddnDocumentType = By.xpath("//select[@name='document']");
    public static By thDocumentType = By.xpath("//th[text()='Document Type']");
    public static By lstDocumentTypeTable = By.xpath("//tr/td[5]/span");
    public static By lstResolveBtnTable = By.xpath("//i[contains(@class,'fa-check-circle-o pull-right')]");
    public static By lstStatusTable = By.xpath("//tr/td[6]//span[1]");
    public static By lstUnresolveBtnTable = By.xpath("//i[contains(@class,'fa-times pull-right')]");
    public static By lstViewBtnTable = By.xpath("//a[text()=' View']");



    /************************************************ Non-Trade Payment popup elements ************************************************/

    public static By hdrNTRWindow = By.xpath("//h2[text()=' Non-Trade Payment ']");
    public static By lstFieldsFirstSet = By.xpath("//h2[text()=' Non-Trade Payment ']/following-sibling::div[1]//strong");
    public static By lstFieldsScndSet = By.xpath("//h2[text()=' Non-Trade Payment ']/following-sibling::div[2]//strong");
    public static By btnCancelNTRWindow = By.xpath("//button[text()='Cancel']");
    public static By btnResolveNTRWindow = By.xpath("//button[text()='Resolve ']");
    public static By tbxComments = By.xpath("//textarea");

    /************************************************ Gross Margin Manager popup elements ************************************************/

    public static By hdrGMMWindow = By.xpath("//h2[text()=' Gross Margin Manager ']");
    public static By lstGMMFieldsFirstSet = By.xpath("//h2[text()=' Gross Margin Manager ']/following-sibling::div[1]//strong");
    public static By lstGMMFieldsScndSet = By.xpath("//h2[text()=' Gross Margin Manager ']/following-sibling::div[2]//strong");

    /************************************************ NON-TRADE INVOICES popup elements ************************************************/

    public static By hdrNTIWindow = By.xpath("//h2[text()=' Non-Trade Invoices ']");
    public static By lstNTIFieldsFirstSet = By.xpath("//h2[text()=' Non-Trade Invoices ']/following-sibling::div[1]//strong");
    public static By lstNTIFieldsScndSet = By.xpath("//h2[text()=' Non-Trade Invoices ']/following-sibling::div[2]//strong");

    /************************************************ INVENTORY ADJUSTMENTS popup elements ************************************************/

    public static By hdrInvAdjWindow = By.xpath("//h2[text()=' Inventory Adjustments ']");
    public static By lstInvAdjFieldsFirstSet = By.xpath("//h2[text()=' Inventory Adjustments ']/following-sibling::div[1]//strong");
    public static By lstInvAdjFieldsScndSet = By.xpath("//h2[text()=' Inventory Adjustments ']/following-sibling::div[2]//strong");



}