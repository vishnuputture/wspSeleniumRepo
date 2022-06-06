package pages.warehouse.ReceivingInProcess;

import org.openqa.selenium.By;

public class ReceivingInProcessPage {

    public static By applyFilterBtn=By.id("applyFilterBtn");
    public static By clearAllBtn=By.id("clearAllBtn");
    public static By searchLabels= By.xpath("//div[@class='input-field']/descendant::label[contains(@class,'win-label')]");
    public static By clearFilterCrossIcon=By.xpath("//i[@class='fa fa-times win-text-error']");
    public static By refreshPoListLink=By.xpath("//span[@class='underline-refresh']");
    public static By containsCheckBox=By.id("itemNumberContains");
    public static By userRF=By.xpath("//ion-input[@id='textWithIcon']/input");
    public static By passwordRF=By.xpath("//ion-input[@type='password']/input");
    public static By loginBtn=By.xpath("//ion-button[contains(text(),'Login')]");
    public static By putAway=By.xpath("//ion-col[contains(text(),'Receive and Put Away')]");
    public static By searchPo=By.xpath("//input[@placeholder='Search by Item or PO']");
    public static By completeBtn=By.xpath("//button[text()='Confirm']");
    public static By poItems=By.xpath("//div[@class='description']");
    public static By qtyTxtBox=By.xpath("//ion-input[@id='po-input']/input");
    public static By scanBarCode=By.xpath("//input[@placeholder='Scan or Type Barcode']");
    public static By qtyError=By.xpath("//ion-input[contains(@class,'qty-error')]");
    public static By refreshPoList=By.xpath("//span[@class='underline-refresh']");
    public static By progressBar=By.xpath("//span[@class='progress-pos']");
    public static By serialItemPopup=By.xpath("//span[text()='SERIALIZED ITEMS']");
    public static By skipBtn=By.xpath("//ion-button[text()='Skip']");
    public static By processBtn=By.xpath("//button[contains(text(),'Process')]");
    public static By remainingCount=By.xpath("//ion-col[contains(text(),' Remaining: ')]");
    public static By tickIcon=By.xpath("//i[@onfocusclass='win-success']");
    public static By lastArrow=By.xpath("//a[text()='»']");
    public static By recDocNo=By.id("RC1CTL_WKRDOC");

    public static By pageLoadSpinner = By.xpath("//i[contains(@class,'fa-spinner')]");
    public static By lblNoResultsFound = By.xpath("//i[text()='No results found']");
    public static By btnClearSearch = By.xpath("//button[contains(@class,'searchbar-clear-button')]");
    public static By lstVendorNameSearchResults = By.xpath("//ion-col[contains(@class,'format-vendor-name')]//ion-col[1]");
    public static By lstChkbxSelectPOSearchResults = By.xpath("//span[contains(@class,'checkbox-icons')]");
    public static By btnCancelPOSelection = By.xpath("//ion-button[text()='Cancel']");
    public static By btnReceivePO = By.xpath("//button[contains(text(),'Receive')]");
    public static By btnConfirm=By.xpath("//button[text()='Confirm']");

    /******************************************* Search By Vendor *******************************************/
    public static By btnFilterByVendor = By.xpath("//span[text()='Filter by Vendor']");
    public static By tbxSearchByVendor = By.xpath("//input[@placeholder='Search by Vendor']");
    public static By lstVendorNameOptions = By.xpath("//ul[@role='listbox']/li[@role='option']/span");
    public static By btnClearVendorSearch = By.xpath("//i[contains(@class,'clear-vendor-search')]");

    /******************************************* Put Away *******************************************/
    public static By tbxQty = By.xpath("//win-input-qty//input");
    public static By tbxScanLocation = By.xpath("//input[@placeholder='Scan or Type Barcode']");
    public static By btnSkip = By.xpath("//ion-button[text()='Skip']");
    public static By msg = By.xpath("//span[(text()='PO Line is received') or (text()='Receiving is still in process')]");

    /******************************************* Purchase Order Items *******************************************/
    public static By hdrPurchaseOrderItems = By.xpath("//ion-label[text()='PURCHASE ORDER ITEMS']");

    /******************************************* Skip PO modal popup *******************************************/
    public static By btnSkipPO = By.xpath("//span[text()='Skip PO']");
    public static By btnSkipPOModal = By.xpath("//skip-po-modal//span[text()='Skip PO']");
    public static By btnReturnToPutAwaySkipPOModal = By.xpath("//skip-po-modal//span[text()='Return to Put Away']");
    public static By msgSkipPO = By.xpath("//ion-col[contains(text(),'skip this PO?')]");

    /******************************************* Add Alternate Items *******************************************/
    public static By iconAddAlternateItem = By.xpath("//i[contains(@class,'alternate-icon')]");
    public static By tbxEnterAlternateItemNumber = By.xpath("//input[@placeholder='Scan or enter new value']");
    public static By btnBack = By.xpath("//button[contains(text(),'Back')]");
    public static By btnAddAlternateItem = By.xpath("//button[contains(text(),'Add Alternate Item Number')]");
    public static By msgAlternatePartNoAddedSuccess = By.xpath("//span[text()='Alternate Part Number successfully added.']");

}
