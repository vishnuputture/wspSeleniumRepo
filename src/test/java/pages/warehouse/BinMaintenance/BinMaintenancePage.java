package pages.warehouse.BinMaintenance;

import org.openqa.selenium.By;

public class BinMaintenancePage {

    public static  By itemMaintenanceTab= By.id("ui-tabpanel-0-label");
    public static  By binMaintenanceTab= By.id("ui-tabpanel-1-label");
    public static  By itemNumberContains=By.id("itemNumberContains");
    public static  By excludeAsteriskItems=By.id("excludeAsteriskItems");
    public static  By includeJobItems=By.id("includeJobItems");
    public static  By excludeZZ98Items=By.id("excludeZZ98Items");
    public static  By itemNumber=By.id("itemNumber");
    public static  By binType=By.id("binType");
    public static  By printQty0=By.id("printQty0");
    public static  By outQtyOnHand=By.id("outQtyOnHand");
    public static  By itemDescription=By.id("itemDescription");
    public static  By binConditionId=By.id("binConditionId");
    public static  By createBinLabel=By.id("createBinLabel");
    public static  By createBinCondition=By.id("createBinCondition");
    public static  By menuIcon=By.xpath("//i[contains(@class,'bars menu-icon')]");
    public static  By inboxIcon=By.xpath("//i[@class='menu-icon fa fa-inbox']");
    public static  By shippingManifestIcon=By.xpath("//i[contains(@class,'fa fa-truck')]");
    public static  By printIcon=By.xpath("//i[@class='menu-icon fa fa-print']");
    public static  By logOutIcon=By.xpath("//i[contains(@class,'menu-icon log-out-icon')]");
    public static  By gearIcon=By.xpath("//i[contains(@class,'gear-icon')]");
    public static  By applyFilter=By.xpath("//button[text()='Apply Filters ']");
    public static  By clearFilter=By.xpath("//button[text()=' Clear All Filters ']");
    public static  By searchIcon=By.xpath("//button[@class='filters-btn']");
    public static  By buttonDis=By.xpath("//button[@disabled]");
    public static  By matchesHeader=By.xpath("//h2[text()='SUGGESTED MATCHES']");
    public static  By itemBinManItemDet=By.xpath("//h2[text()='Item-Bin Maintenance - Item Details']");
    public static  By itemCount=By.xpath("//span[contains(text(),' Items ') or contains(text(),' Bins ')]");
    public static  By selectAllCheckbox=By.xpath(" //input[@type='checkbox']/following-sibling::button[contains(text(),'elect All Items') or contains(text(),'elect All Bins')]");
    public static  By selectAllPop=By.xpath("//label[contains(text(),'elect All Items')]/parent::div/input");
    public static  By itemCountSP=By.xpath("//tbody/tr/td[contains(@class,'pl-3 pr-3')]");
    public static  By binsCountSP=By.xpath("//tbody/tr/td[@class='pl-3']");
    public static  By highlightRow=By.xpath("//tr[contains(@class,'ui-state-highlight')]");
    public static  By toaster=By.xpath("//div[@class='ui-toast-summary']");
    public static  By primaryOpt=By.xpath("//select[@ng-reflect-model='Primary']");
    public static  By secondaryOpt=By.xpath("//select[@ng-reflect-model='Secondary']");
    public static  By temporaryOpt=By.xpath("//select[@ng-reflect-model='Temporary']");
    public static  By deleteIcon=By.xpath("//i[@title='Delete']");
    public static  By editIcon=By.xpath("//i[@title='Edit']");
    public static  By deletePopup=By.xpath("//h2[text()='Delete Bin Item']");
    public static  By editTextBox=By.xpath("//input[@type='text' and @placeholder='None']");
    public static  By cancelIcon=By.xpath("//i[@title='Cancel']");
    public static  By saveIcon=By.xpath("//i[@title='Save']");
    public static  By getItemVal=By.xpath("//table[@role='grid']/descendant::th[contains(text(),'Bin Location')]/ancestor::thead/following-sibling::tbody/descendant::td");
    public static  By zoneIdDropDown=By.xpath("//select[@name='zoneId']");
    public static  By goToItemBinLedger=By.xpath("//a[@title='Go To Item Bin Ledger']");
    public static  By createBinPopup=By.xpath("//h2[text()='CREATE BIN AND ASSIGN']");

    public static  By loadingSpinner=By.xpath("//i[contains(@class,'fa fa-spinner')]");
    public static  By lblBinCount=By.xpath("//h2//following-sibling::div/span[contains(text(),' Bins')]");
    public static  By lblShowRowsCount=By.xpath("//span[text()='Show: ']");
    public static  By btnShowRowsCount=By.xpath("//nav[@id='binMaintenancePagination']//following::a[contains(@class,'count')]");
    public static  By pagination=By.className("pagination");
    public static  By toastMsg=By.className("ui-toast-summary");
    public static  By tdZoneFirstRow=By.xpath("//tbody//tr[1]/td[2]");
    public static  By tdStatusFirstRow=By.xpath("//tbody//tr[1]//span[contains(@class,'win-badge-success')]");

    /******************************************** Create New Bin popup elements ********************************************/

    public static By hdrCreateNewBinPopup = By.xpath("//h2[text()='CREATE NEW BIN']");
    public static By chbxAvailableToSell = By.xpath("//label[text()='Available to Sell']//preceding-sibling::input[@type='checkbox']");
    public static By chbxSpecialHandling = By.xpath("//label[text()='Special Handling']//preceding-sibling::input[@type='checkbox']");
    public static By tbxBinLocation = By.xpath("//label[contains(text(),'Bin Location')]//following-sibling::input");
    public static By btnCancelCreateNewBinpopup = By.xpath("//create-bin-component//button[contains(text(),'Cancel')]");
    public static By btnSaveCreateNewBinpopup = By.xpath("//create-bin-component//button[contains(text(),'Save')]");
    public static By lstDropdownsCreateNewBinPopup = By.id("createBinCondition");

    /******************************************** Edit Bin(s) popup elements ********************************************/

    public static By hdrEditBinPopup = By.xpath("//div[@class='modal-header']/h2[contains(text(),'EDIT ')]");
    public static By btnCancelEditBinPopup = By.xpath("//edit-multiple-bin//button[contains(text(),'Cancel')]");
    public static By btnSaveEdiBinPopup = By.xpath("//edit-multiple-bin//button[contains(text(),'Save')]");
    public static By ddnZoneEditBinPopup = By.xpath("//form//label[contains(text(),'Zone')]//following-sibling::select");
    public static By ddnConditionEditBinPopup = By.xpath("//form//label[contains(text(),'Condition')]//following-sibling::select");
    public static By ddnReceivingEditBinPopup = By.xpath("//form//label[contains(text(),'Receiving')]//following-sibling::select");
    public static By ddnPickingEditBinPopup = By.xpath("//form//label[contains(text(),'Picking')]//following-sibling::select");
    public static By chkbxSpclHndlngEditBinPopup = By.xpath("//form//label[contains(text(),'Special Handling')]/preceding-sibling::input");
    public static By chkbxAvToSellEditBinPopup = By.xpath("//form//label[contains(text(),'Available to Sell')]/preceding-sibling::input");
    public static By chkbxStgAreaEditBinPopup = By.xpath("//form//label[contains(text(),'Staging Area')]/preceding-sibling::input");




}
