package pages.Purchasing.SPO;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SpoPage extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public SpoPage(Helper helper) {
        super(helper);
    }

    public static By winLogin = By.id("winlogo");
    public static By userName = By.id("username");
    public static By password = By.id("password");
    public static By submit = By.xpath("//input[@type='submit']");
    public static By spoPageTitle = By.xpath("//h2[contains(@class,'page-title')]");
    public static By helpIcon = By.xpath("//i[@class='fa fa-help']");
    public static By verifyChecked = By.xpath("//div[@class='p-checkbox-box']");
    public static By pagination = By.xpath("//nav[@id='suggestedOrderLanderPagination1']//child::li[contains(@class,'pagination')]");
    public static By onePage = By.xpath("//nav[@id='suggestedOrderLanderPagination1']//child::span[text()='of 1']");
    public static By worksheetCount = By.xpath("//a[@class='header-name-link']");
    public static By disClearAllFilter = By.xpath("//button[contains(text(),'Clear All Filters') and @disabled]");
    public static By newWorksheetBtn = By.id("new-worksheet-button");
    public static By unitCostField = By.xpath("//input[contains(@class,'form-control text-right')]");
    public static By dayOfTheWeek = By.xpath("//label[text()='Day of the Week']/ancestor::div/descendant::div[contains(@class,'multiselect')]");
    public static By dayCheckBox = By.xpath("//span[@class='p-checkbox-icon pi pi-check']");
    public static By popUp = By.xpath("//div[contains(@class,'p-toast-summary')]");
    public static By worksheetNameFilter = By.xpath("//input[@placeholder='Worksheet Name']");
    public static By filteredWorksheet = By.xpath("//a[@class='header-name-link']");
    public static By worksheetSheetTemplateName = By.id("worksheetSheetTemplateName");
    public static By trailingMonths = By.id("trailingMonths");
    public static By saveWorksheet = By.id("save-worksheet");
    public static By costOption = By.id("costOption");
    public static By calcMethod = By.id("calculation-method-0");
    public static By isDisabledCalcMethod = By.xpath("//select[contains(@id,'calculation') and @ng-reflect-is-disabled='true']");
    public static By cancelBtnMinOrd = By.xpath("//button[contains(text(),'Save and Convert to PO')]/preceding-sibling::button[text()='Cancel']");
    public static By costOptions = By.xpath("//select[@id='costOption']//option");
    public static By editWsTempBtn = By.xpath("//span[text()='Edit Worksheet Template']");
    public static By hourDropDown = By.id("hour1");
    public static By minuteDropDown = By.id("minute1");
    public static By meridianDropDown = By.id("meridian1");
    public static By refreshIcon = By.xpath("//i[@class='fa fa-refresh']");
    public static By lastUpdate = By.xpath("//span[contains(text(),'Last Updated:')]/parent::div");
    public static By totalCost = By.xpath("//td[contains(text(),'$')]");
    public static By totalWeight = By.xpath("//td[contains(text(),'lbs')]");
    public static By disFindProduct = By.xpath("//button[contains(text(),'Find Products') and @disabled]");
    public static By worksheetNameLink = By.xpath("//span[contains(text(),'Last Updated:')]/preceding::a[@class='header-name-link']");
    public static By worksheetNameHeader = By.id("modalLabelSM");
    public static By searchTerm1 = By.id("searchTerm1");
    public static By orderQuantity = By.xpath("//input[contains(@class,'dollar')]");
    public static By assignedUser = By.id("assignedUser");
    public static By assignedUserIf = By.xpath("//label[text()='Assigned User']/following-sibling::input");
    public static By vendorNoHeader = By.xpath("//i[contains(@class,'sticky-note')]/ancestor::h3");
    public static By closeIcon = By.xpath("//span[text()='Order Minimum']/parent::div/following-sibling::button/descendant::i");
    public static By trashIcon = By.xpath("//i[contains(@class,'fa-trash')]");
    public static By notStdQty = By.xpath("//h2[text()='NOT A STANDARD PACKAGE QTY']");

    public static By savedTag = By.xpath("//span[contains(@class,'badge-success')]");
    public static By deleteSavedWorksheetPopup = By.xpath("//i[contains(@class,'fa-question')]");
    public static By exclamationWarn = By.xpath("//i[contains(@class,'fa-exclamation-triangle')]");
    public static By saveWorksheetBtn = By.xpath("//span[text()='Save Worksheet']");
    public static By leadTime = By.id("leadTime");
    public static By multiplier = By.xpath("//span[text()='Multiplier: ']");
    public static By discount = By.xpath("//span[text()='Discount: ']");
    public static By xIcon = By.xpath("//span[contains(@class,'p-toast-icon-close')]");
    public static By yellowModCol = By.xpath("//input[contains(@style,'solid yellow')]");
    public static By itemNoCol = By.xpath("//div[contains(@class,'item-number')]");
    public static By minFreightOrder = By.xpath("//span[contains(@class,'ng-star-inserted')]");
    public static By calHead = By.xpath("//span[contains(@class,'text')]");
    public static By backBtn = By.id("btnCF12");
    public static By isInvalidDisc = By.xpath("//label[text()='Discount']/following-sibling::input[contains(@class,'invalid')]");
    public static By invalidWSName = By.xpath("//div[contains(@class,'invalid-feedback')]");
    public static By plusIcon = By.xpath("//*[contains(@class,'fa-plus-square')]");
    public static By minusIcon = By.xpath("//i[contains(@class,'fa-minus-square')]");
    public static By discountError = By.xpath("//label[text()='Discount']/following-sibling::div");
    public static By supplyToOrder = By.id("supplyToOrder");
    public static By optionsConstants = By.id("optionsConstants");
    public static By cancelPopup = By.id("cancelConfirmation");
    public static By selectedMonth = By.xpath("//div[contains(@ng-reflect-ng-class,'sales-history-grid-selected')]");
    public static By excludeAsteriskItem = By.xpath("//span/preceding-sibling::input[@type='checkbox']");
    public static By itemNoCount = By.xpath("//a[contains(@id,'header-name')]");
    public static By addItemToWSLink = By.xpath("//i[@class='fa fa-plus']/parent::a");
    public static By addItemHeader = By.xpath("//div[@id='addItemstoWorksheetModalId']/descendant::h2");
    public static By autoSuggestion = By.xpath("//li[contains(@class,'autocomplete')]/span");
    public static By searchBtn = By.xpath("//span[text()='Search']");
    public static By buttonDisabled = By.xpath("//button[@disabled]/span");
    public static By asteriskItem = By.xpath("//label[contains(text(),'Asterisk Items')]/preceding-sibling::input[@id='excludeAsteriskItems']");
    public static By jobItem = By.xpath("//label[contains(text(),'Job Items')]/preceding-sibling::input[@id='jobItems']");
    public static By iovItem = By.xpath("//label[contains(text(),'IOV Items')]/preceding-sibling::input[@id='iovItems']");
    public static By selectItem = By.xpath("//input[@name='selectItem']");
    public static By activateList = By.xpath("//label[text()='Activate List']");
    public static By selectAllCheckBox = By.id("selectAllItems");
    public static By addSelectedItemToWSBtn = By.xpath("//span[text()='Add Selected Items to Worksheet ']");
    public static By itemNumSearch = By.xpath("//p-autocomplete[@id='itemNumber']/descendant::input");
    public static By itemDescription = By.xpath("//p-autocomplete[@id='itemDescription']/descendant::input");
    public static By toggleSlider = By.xpath("//div[@class='toggle-ball']");
    public static By convertPOBtn = By.xpath("//span[text()='Convert to PO']");
    public static By manufacturerCode = By.id("manufacturerCode");
    public static By productCode = By.id("productCode");
    public static By vendorName = By.id("vendorName");
    public static By lastOrdered = By.id("lastOrdered");
    public static By unitCost = By.id("unitCost");
    public static By unitWeight = By.id("unitWeight");
    public static By showStockedAtWss = By.id("showStockedAtWss");
    public static By discountOrMultiplier = By.id("discountOrMultiplier");
    public static By monthSupply = By.id("monthSupply");
    public static By runEachWeek = By.id("runEachWeek");
    public static By runEachOtherWeek = By.id("runEveryOtherWeek");
    public static By btnMinus = By.xpath("//a[@data-toggle='collapse'][@aria-expanded='true']");
    public static By btnPlus = By.xpath("//a[@data-toggle='collapse'][@aria-expanded='false']");
    public static By expandedSection = By.xpath("//div[@id='collapseList1'][@class='panel-collapse collapse show']");
    public static By contractedSection = By.xpath("//div[@id='collapseList1'][@class='panel-collapse collapse']");
    public static By btnRefreshWorksheet = By.xpath("//button[text()=' Refresh Worksheet ']");
    public static By btnConfirm = By.xpath("//button[text()='Yes']");
    public static By yesButton = By.id("Yes");
    public static By noButton = By.id("No");
    public static By refreshMessage = By.xpath("//span[text()='Worksheet updated successfully.']");
    public static By pageBlocker = By.xpath("//p-blockui/div/i");
    public static By btnExpand = By.xpath("//button[text()=' Expand All ']");
    public static By btnCollapse = By.xpath("//button[text()=' Collapse All ']");
    public static By itemExpanded = By.xpath("//div[@class='wrapper ng-star-inserted']");
    public static By disableFieldsChkbox = By.id("disableAllFieldsExceptOrderQty");
    public static By btnItemExpand = By.xpath("//i[@class='fa fa-plus-square fa-2x fa-minus-square-color']");
    public static By vendorPartNo = By.id("vendorPartNumber");
    public static By unitOfMeasure = By.id("unitOfMeasure");
    public static By conversionFactor = By.id("conversionFactor");
    public static By disableAllFields = By.id("disableAllFieldsExceptOrderQty");
    public static By vendorPartNumber = By.id("vendorPartNumber");
    public static By standardPackage = By.id("standardPackage");
    public static By discountLineItem = By.id("discount-line-item-0");
    public static By standardPackageWeight = By.id("standardPackageWeight");
    public static By noResultFound = By.xpath("//td[text()=' No Results Found ']");
    public static By getPD = By.xpath("//p-autocomplete[contains(@id,'pd-filter-')]/descendant::input");
    public static By getVN = By.xpath("//p-autocomplete[contains(@id,'vn-filter-0)']/descendant::input");
    public static By getMF = By.xpath("//p-autocomplete[contains(@id,'mf-filter-')]/descendant::input");
}
