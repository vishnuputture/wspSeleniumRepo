package pages.AccountReceivable.AR2WISE;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class AR2WISEPage extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public AR2WISEPage(Helper helper) {
        super(helper);
    }

    public static By headerTitle = By.className("panel-header");
    public static By openMenu = By.xpath("//a[@title='Click to open menu']/i");
    public static By noResultFound = By.xpath("//span[text()='No results found']");
    public static By winLabel = By.id("typeahead-company");
    public static By debitCreditTotal=By.xpath("//tfoot/tr/td");
    public static By breakDownCal=By.xpath("(//tbody[@role='rowgroup'])[2]/tr/td");
    public static By dropDownSearchResult = By.xpath("//ngb-highlight[@ng-reflect-result='99599 - Automation Test Co.']");
    public static By expandMinimize=By.xpath("//i[@class='fa fa-minus-square-o']");
    public static By invalidCompany=By.xpath("//input[contains(@class,'invalid-company')]");
    public static By businessDays=By.xpath("//span/span[@class='ng-star-inserted']");
    public static By businessDay=By.id("businessDay");
    public static By searchAll=By.xpath("//label[contains(text(),'Search All')]/parent::div/div/input");
    public static By resultStatus=By.xpath("//span[@class='row text-lg-center']");
    public static By arrowIcon=By.xpath("//div[contains(@class,'mat-select-arrow-wrapper')]/div");
    public static By totalRecords=By.xpath("//div[@class='mat-paginator-range-label']");
    public static By nextPage=By.xpath("//button[@ng-reflect-disabled='false' and @aria-label='Next page']/span");
    public static By previousPage=By.xpath("//button[@ng-reflect-disabled='false' and @aria-label='Previous page']/span");
    public static By columnName=By.xpath("//tr/th/preceding::button");
    public static By castReceipt=By.xpath("//a[text()=' Cash Receipts ']");
    public static By journalType=By.id("journalType");
    public static By glAccountNumber=By.id("glAccountNumber");
    public static By glAccount=By.id("glAccount");
    public static By description=By.id("description");
    public static By processBtn=By.id("processBtn");
    public static By okToApply=By.id("I_8_21");
    public static By printReceipt=By.id("I_9_23");
    public static By commentBox=By.id("I_15_35");
    public static By amountReceived=By.id("amountReceived");
    public static By datePick=By.id("date");
    public static By amount=By.id("I_7_61");
    public static By warningMsg=By.id("D_2_17_W1");
    public static By invoiceTicket=By.id("I_11_50");
    public static By printJournal=By.id("I_16_50");
    public static By referenceNo=By.id("I_11_2");
    public static By optionChekBx=By.xpath("//input[@class='A26 input']");
    public static By PaymentType =By.xpath("//span[text()='Payments']");
    public static By searchAllARGL=By.id("glSearchFilter");
    public static By filteredTotal=By.xpath("//span/strong");
    public static By bankDeposit=By.xpath("//div[@id='D_8_7']/a");
    public static By cashRecComName=By.xpath("(//input[@role])[2]");
    public static By cashRecDate=By.xpath("(//input[@id='date'])[2]");
    public static By totalBusinessDays=By.xpath("//select[@id='businessDay']/option");
    public static By totalAmount=By.xpath("//span[text()=' | Results: ']/strong/preceding-sibling::strong");

    public static By hdrARGLDInquiry = By.xpath("//span[text()='A/R G/L DETAIL INQUIRY']");
    public static By hdrTrnsctnExcptnList = By.xpath("//span[text()='transaction exception list']");
    public static By btnOpenMenu = By.id("open");
    public static By menuTrnsctnExcptn = By.xpath("//a[text()='Transaction Exception']");
    public static By loaderIcon = By.xpath("//div[@class='loader ng-star-inserted']");
    public static By txtNoResultsFound = By.xpath("//span[text()='No results found']");
    public static By btnCollapse = By.xpath("//i[@class='fa fa-minus-square-o']");
    public static By btnExpand = By.xpath("//i[@class='fa fa-plus-square-o']");
    public static By tbxCompany = By.id("typeahead-company");
    public static By lstCompanyDdnOptions = By.xpath("//win-company-typeahead//button/ngb-highlight");
    public static By tbxDate = By.xpath("//label[text()='Date']/following-sibling::div/input");
    public static By ddnDocumentType = By.xpath("//select[@name='document']");
    public static By tbxSearchAll = By.xpath("//label[text()=' Search All ']/following-sibling::div/input");
    public static By thDocumentType = By.xpath("//th[text()='Document Type']");
    public static By lstDocumentTypeTable = By.xpath("//tr/td[5]/span");
    public static By lstResolveBtnTable = By.xpath("//i[contains(@class,'fa-check-circle-o pull-right')]");
    public static By lstStatusTable = By.xpath("//tr/td[6]//span[1]");
    public static By lstUnresolveBtnTable = By.xpath("//i[contains(@class,'fa-times pull-right')]");
    public static By lstViewBtnTable = By.xpath("//a[text()=' View']");
    public static By lblItemsPerPage = By.xpath("//div[text()='Items per page:']");
    public static By tbxPagination = By.xpath("//span[contains(@class,'mat-select-value-text')]/span");
    public static By ddnItemsPerPage = By.xpath("//mat-select//div[contains(@class,'mat-select-arrow-wrapper')]");
    public static By btnPaginationNext = By.xpath("//button[contains(@class,'mat-paginator-navigation-next')]");
    public static By btnPaginationPrevious = By.xpath("//button[contains(@class,'mat-paginator-navigation-previous')]");
    public static By txtPaginatorRange = By.xpath("//div[@class='mat-paginator-range-label']");
    public static By txtResultsHidden = By.xpath("//span[@class='row text-lg-center']");


    /************************************************ Table elements ************************************************/

    public static By thDate = By.xpath("//button[text()=' Date ']/../..");
    public static By thDescription = By.xpath("//button[text()=' Description ']/../..");
    public static By thStatus = By.xpath("//button[text()=' Status ']/../..");
    public static By lstDateColValue = By.xpath("//td[contains(@class,'mat-column-date')]");
    public static By lstAmountColValue = By.xpath("//td[contains(@class,'mat-column-amount')]");
    public static By lstDescriptionColValue = By.xpath("//td[contains(@class,'mat-column-explanation')]/span");
    public static By lstStatusColValue = By.xpath("//td[contains(@class,'mat-column-status')]/div/div/span[1]");

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

