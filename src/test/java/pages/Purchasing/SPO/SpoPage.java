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
    public static By itemNo=By.id("D_15_6");
    public static By inputBox=By.id("I_15_3");
    public static By currentPage=By.xpath("//nav[@id='addItemsToWorksheetPagination2']//child::span[text()='of 1']");
    public static By pageCount=By.xpath("//nav[@id='addItemsToWorksheetPagination2']//child::input");
    public static By pageArrow=By.xpath("//nav[@id='addItemsToWorksheetPagination2']//child::a[@class='page-link']");
    public static By lastPage=By.xpath("//nav[@id='addItemsToWorksheetPagination2']//li[contains(@class,'disabled')]");
    public static By pagination = By.xpath("//nav[@id='suggestedOrderLanderPagination1']//child::li[contains(@class,'pagination')]");
    public static By onePage = By.xpath("//nav[@id='suggestedOrderLanderPagination1']//child::span[text()='of 1']");
    public static By worksheetCount = By.xpath("//a[@class='header-name-link']");
    public static By disClearAllFilter = By.xpath("//button[contains(text(),'Clear All Filters') and @disabled]");
    public static By newWorksheetBtn = By.id("new-worksheet-button");
    public static By unitCostField = By.xpath("(//span[@class='p-inputnumber p-component']/input)[2]");
    public static By dayOfTheWeek = By.xpath("//label[text()='Day of the Week']/ancestor::div/descendant::div[contains(@class,'multiselect')]");
    public static By dayCheckBox = By.xpath("//span[@class='p-checkbox-icon pi pi-check']");
    public static By popUp = By.xpath("//div[contains(@class,'p-toast-summary')]");
    public static By worksheetNameFilter = By.xpath("//input[@placeholder='Worksheet Name']");
    public static By filteredWorksheet = By.xpath("//a[@class='header-name-link']");
    public static By worksheetSheetTemplateName = By.id("worksheetSheetTemplateName");
    public static By companyName=By.xpath("//div[contains(text(),'AUTOMATION TEST CO')]");
    public static By closeIcn=By.xpath("//span[contains(@class,'p-toast-icon-close-icon')]");
    public static By trailingMonths = By.id("trailingMonths");
    public static By saveWorksheet = By.id("save-worksheet");
    public static By costOption = By.id("costOption");
    public static By calcMethod = By.id("calculation-method-0");
    public static By isDisabledCalcMethod = By.xpath("//select[contains(@id,'calculation') and @disabled]");
    public static By cancelBtnMinOrd = By.xpath("//button[contains(text(),'Save and Convert to PO')]/preceding-sibling::button[text()='Cancel']");
    public static By costOptions = By.xpath("//select[@id='costOption']//option");
    public static By editWsTempBtn = By.xpath("//span[text()='Edit Worksheet Template']");
    public static By hourDropDown = By.id("hour1");
    public static By minuteDropDown = By.id("minute1");
    public static By meridianDropDown = By.id("meridian1");
    public static By refreshIcon = By.xpath("//i[@class='fa fa-refresh']");
    public static By lastUpdate = By.xpath("//span[contains(text(),'Last Updated:')]/parent::div");
    public static By totalCost = By.xpath("//div[contains(text(),'$')]");
    public static By totalWeight = By.xpath("//td[contains(text(),'lbs')]");
    public static By disFindProduct = By.xpath("//button[contains(text(),'Find Products') and @disabled]");
    public static By findProducts = By.id("find-products");
    public static By worksheetNameLink = By.xpath("//span[contains(text(),'Last Updated:')]/preceding::a[@class='header-name-link']");
    public static By worksheetNameHeader = By.id("modalLabelSM");
    public static By modalLabel=By.id("modalLabel");
    public static By saveButton=By.id("save-button");
    public static By searchTerm1 = By.id("searchTerm1");
    public static By orderQuantity = By.xpath("//input[contains(@id,'order-quantity')]");
    public static By assignedUser = By.id("assignedUser");
    public static By assignedUserIf = By.xpath("//label[text()='Assigned User']/following-sibling::input");
    public static By vendorNoHeader = By.xpath("//i[contains(@class,'sticky-note')]/ancestor::h3");
    public static By closeIcon = By.xpath("//button[contains(text(),'Back')]");
    public static By trashIcon = By.xpath("//i[contains(@class,'fa-trash')]");
    public static By notStdQty = By.xpath("//h2[text()='NOT A STANDARD PACKAGE QTY']");
    public static By pagntn = By.xpath("//nav[@id='addItemsToWorksheetPagination2']/descendant::a[@class='page-link']");
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
    public static By manufacturerCode = By.xpath("//p-autocomplete[@id='manufacturerCode1']/span/input");
    public static By productCode = By.xpath("//p-autocomplete[@id='productCode1']/span/input");
    public static By vendorName = By.xpath("//p-autocomplete[@id='vendorName1']/span/input");

    public static By vendorNumber = By.xpath("//p-autocomplete[@id='searchTerm1']/span/input");
    public static By lastOrdered = By.id("lastOrdered");
    public static By unitCost = By.id("unitCost");
    public static By unitWeight = By.id("unitWeight");
    public static By showStockedAtWss = By.id("showStockedAtWss");
    public static By discountOrMultiplier = By.id("discount");
    public static By multiplierField = By.id("multiplier");
    public static By monthSupply = By.id("monthSupply");
    public static By runEachWeek = By.id("runEachWeek");
    public static By runEachOtherWeek = By.id("runEveryOtherWeek");
    public static By btnMinus = By.xpath("//button[@aria-expanded='true']");
    public static By btnPlus = By.xpath("//button[@aria-expanded='false']");
    public static By expandedSection = By.xpath("//div[@id='collapseList1'][@class='panel-collapse collapse show']");
    public static By contractedSection = By.xpath("//div[@id='collapseList1'][@class='panel-collapse collapse']");
    public static By btnRefreshWorksheet = By.xpath("//button[text()=' Refresh Worksheet ']");
    public static By btnConfirm = By.xpath("//button[text()='Yes']");
    public static By yesButton = By.id("Yes");
    public static By noButton = By.id("No");
    public static By refreshMessage = By.xpath("//span[text()='Worksheet refreshed successfully.']");
    public static By pageBlocker = By.xpath("//p-blockui/div/i");
    public static By btnExpand = By.xpath("//button[text()=' Expand All ']");
    public static By btnCollapse = By.xpath("//button[text()=' Collapse All ']");
    public static By itemExpanded = By.xpath("//div[@class='wrapper ng-star-inserted']");
    public static By disableFieldsChkbox = By.id("disableAllFieldsExceptOrderQty");
    public static By btnItemExpand = By.xpath("//i[@class='fa fa-plus-square fa-2x fa-minus-square-color']");
    public static By vendorPartNo = By.id("vendor-part-number-0");
    public static By unitOfMeasure = By.id("unit-of-measure-0");
    public static By conversionFactor = By.id("conversion-factor-0");
    public static By disableAllFields = By.id("disableAllFieldsExceptOrderQty");
    public static By freightChargeCodes = By.id("freightChargeCodes");
    public static By standardPackage = By.id("standard-package-qty-0");
    public static By discountLineItem = By.xpath("(//span[@class='p-inputnumber p-component']/input)[3]");
    public static By standardPackageWeight = By.id("standardPackageWeight");
    public static By noResultFound = By.xpath("//td[text()=' No Results Found ']");
    public static By getPD = By.xpath("//p-autocomplete[contains(@id,'pd-filter-')]/descendant::input");
    public static By errorMsg = By.xpath("//tr[contains(@class,'ng-dirty p-highlight ng-invalid')]");
    public static By getMF = By.xpath("//p-autocomplete[contains(@id,'mf-filter-')]/descendant::input");
    public static By createTemplate = By.id("create-worksheet");

    public static By search = By.id("open-flyout-filter");
    public static By purchasingTemplateName = By.xpath("//input[@placeholder='Worksheet Name']");

    public static By applyFilter = By.id("apply-filters");

    public static By refreshWorksheet = By.id("refresh-worksheet-0");
    public static By extractESTDateAndTime = By.xpath("//span[contains(text(),'Last Updated:')]/..");
    public static By workSheet = By.id("header-name-0");
    public static By backButton = By.id("back-button");
    public static By deleteButton = By.xpath("//i[contains(@id,'delete-worksheet')]");
    public static By deleteMessage = By.xpath("//*[contains(text(),'Are you sure you want to permanently delete the saved worksheet')]");

    public static By deleteYes = By.id("delete-worksheet");

    public static By deleteNo = By.xpath("//button[text()='No']");

    public static By clearFilter = By.xpath("//i[@class='fa fa-times win-text-error']");

    public static By defaultPagination = By.xpath("(//a[@class='count win-blue mx-1 selected-count ng-star-inserted']/span[@class='font-weight-bold'])[1]");
    public static By templateTableRows = By.xpath("//table/tbody/tr");

    public static By chooseRowsFifteen = By.xpath("(//a[@class='count win-blue mx-1 ng-star-inserted']/span[contains(text(),'15')])[1]");

    public static By currentSheetNumber = By.xpath("(//input[@id='currentPage'])[1]");

    public static By noOfSheets = By.xpath("(//input[@id='currentPage']/following::span[1])[1]");

    public static By nextPageNavigation = By.xpath("(//li[@class='pagination-next page-item ng-star-inserted'])[1]");

    public static By lastPageNavigation = By.xpath("(//li[@class='pagination-last page-item ng-star-inserted'])[1]");

    public static By invalidManufactureCode =By.xpath("(//div[text()='Invalid Manufacturer Code.'])[1]");
    public static By invalidProductCode =By.xpath("(//div[text()='Invalid Product Code.'])[1]");
    public static By invalidVendorCode =By.xpath("(//div[text()='Invalid Vendor Code.'])[1]");
    public static By loadingSpinner = By.xpath("//i[contains(@class,'fa fa-spinner')]");
    public static By defaultCostOption = By.id("costOption");
    public static By cancel = By.xpath("//button[@id='cancel-button' and contains(@class,'btn win-button win-button-secondary')]");
    public static By cancelTemplateWarningMessage = By.xpath("//*[contains(text(),' Are you sure you want to cancel the creation of this template? ')]");
    public static By cancelTemplateYes = By.xpath("//button[@class='btn win-button win-button-primary mt-2']");

    public static By monthsSupplyToOrder = By.id("supplyToOrder");
    public static By monthSupplyToOrderErrorMessage = By.xpath("(//div[@class='invalid-feedback ng-star-inserted'])[1]");

    public static By purchasingMultiplierDefaultValue = By.xpath("//label[text()='Multiplier']/following-sibling::p-inputnumber/span/input");
    public static By leadTimeErrorMessage = By.xpath("(//div[contains(text(),'Please enter a valid lead time from 1-999.')])[1]");

    public static By noOfSelectedBoxes = By.xpath("(//div[@class='col-12']/div[@class='row no-gutters sales-history-grid-row'])[2]/div[@ng-reflect-ng-class='sales-history-grid-selected']");

    public static By noOfDeSelectedBoxes = By.xpath("(//div[@class='col-12']/div[@class='row no-gutters sales-history-grid-row'])[2]/div[@ng-reflect-ng-class='sales-history-grid-deselected']");
    public static By noOfMonthsInsertedBoxes = By.xpath("(//div[@class='col-12 ng-star-inserted']/div[@class='row no-gutters'])[1]/div[@class='col-1 sales-history-grid-col sales-history-grid-deselected ng-star-inserted']");

    public static By hourDropdown = By.xpath("//select[@id='hour']");
    public static By minutesDropdown = By.xpath("//select[@id='minute']");
    public static By meridianDropdown = By.xpath("//select[@id='meridian']");

    public static By dayOfTheWeekButton = By.xpath("(//div[text()='5 items selected'])[1]");

    public static By daysOfTheWeekDropdown = By.xpath("//span[@class='p-checkbox-icon pi pi-check']");
    public static By selectADay = By.xpath("//li[@aria-label='Monday']");
    public static By namesOfDays = By.xpath("//span[@class='p-checkbox-icon pi pi-check']/../../following-sibling::span[1]");


}
