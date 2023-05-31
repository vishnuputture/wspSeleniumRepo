package pages.warehouse;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;
public class PickingInProcessPage extends ReusableLib{
    public PickingInProcessPage(Helper helper) {
            super(helper);
        }

    public static By verifyCredentialsButton = By.xpath("//input[@value='Verify Credentials']");
    public static By usernameTextbox = By.id("username");
    public static By passwordTextbox = By.id("password");
    public static By pickingInProcessHeader = By.xpath("//h2[text()='PICKING IN PROCESS']");
    public static By companyToggle = By.id("companySelectorToggle");
    public static By winIntoCompanyLabel = By.xpath("//label[contains(text(),'WIN Into Company')]");
    public static By companyNameTextbox = By.xpath("//div[@class='selected-company-div']//input[@placeholder='Enter company number']");
    public static By selectButton = By.xpath("//div[@class='select-btn-div']/button[contains(text(),'Select')]");
    public static By currentZoneDropDown = By.xpath("//select[@id='selectField']");
    public static By labelTotalLines = By.xpath("//label[text()='Open / Total Lines']");
    public static By labelPercentComplete = By.xpath("//div[contains(@class,'progress-status-block')]/label[text()='Percent Complete']");
    public static By filterButton = By.xpath("//button[@class='filters-btn']");
    public static By searchFilterHeader = By.xpath("//h1[text()='Search Filters']");
    public static By shipmentTableHeader = By.xpath("//th[@role='columnheader']//p[text()='Shipment']");
    public static By nameTableHeader = By.xpath("//th[@role='columnheader']//p[text()='Name']");
    public static By assignedRFPicker = By.xpath("//label[text()='Assigned RF Picker']/following-sibling::label");
    public static By assignedRFTableHeader = By.xpath("//th[@role='columnheader']//p[text()='Assigned RF Picker']");
    public static By autoLinesTableHeader = By.xpath("//th[@role='columnheader']//p[text()='Auto Lines']");
    public static By totalLinesTableHeader = By.xpath("//th[@role='columnheader']//p[text()='Total Lines']");
    public static By rfGunStagedTableHeader = By.xpath("//th[@role='columnheader']//p[text()='RF Gun Zones Staged']");

    public static By shipViaTableHeader = By.xpath("//th[@role='columnheader']//p[text()='Ship Via']");
    public static By PercentCompleteTableHeader = By.xpath("//th[@role='columnheader']//p[text()='Percent Complete']");
    public static By shipmentFilter = By.xpath("//*[@field='orderNumber']");
    public static By customerFilter = By.xpath("//*[@field='customer']");
    public static By assignedToFilter = By.xpath("//*[@field='displayName']");
    public static By truckNameFilter = By.xpath("//label[text()='Truck Name']/following-sibling::p-multiselect[@styleclass='multi-select-ship-via']");
    public static By shipViaFilter = By.xpath("//label[text()='Ship Via']/following-sibling::p-multiselect[@styleclass='multi-select-ship-via']");
    public static By pickStatusFilter = By.id("pickStatus");
    public static By shipDateFilter = By.id("filterShipDate");
    public static By applyFiltersButton = By.xpath("//button[text()='Apply Filters ']");
    public static By clearAllFiltersButton = By.xpath("//button[text()='Clear All Filters ']");
    public static By firstRowRecord = By.xpath("//table[@role='grid']//tbody/tr[1]/td[1]");
    public static By shipmentNoHeader = By.xpath("//h2[contains(text(),'SHIPMENT:')]");
    public static By customerNameField = By.xpath("//div[@class='row section-box']//span[text()='Customer Name:']/following-sibling::span");
    public static By dateTimeField = By.xpath("//div[@class='row section-box']//span[text()='Date / Time:']/following-sibling::span");
    public static By totalLinesField = By.xpath("//div[@class='row section-box']//span[text()='Open / Total Lines:']/following-sibling::span");
    public static By customerPOField = By.xpath("//div[@class='row section-box']//label[text()='Customer PO Number:']/following-sibling::label");
    public static By jobNameField = By.xpath("//div[@class='row section-box']//label[text()='Job Name:']/following-sibling::label");
    public static By shipViaField = By.xpath("//div[@class='row section-box']//label[text()='Ship Via:']/following-sibling::label");
    public static By percentageCompleteLabel = By.xpath("//span[text()='Percent Complete']");
    public static By expandAllLink = By.xpath("//*[text()=' Expand All ']");
    public static By rfGunOpenLines = By.xpath("//label[text()='RF Gun Open Lines']/following-sibling::div");
    public static By manualOpenLines = By.xpath("//label[text()='Manual Open Lines ']/following-sibling::span");
    public static By autoOpenLines = By.xpath("//label[text()='Auto Open Lines ']/following-sibling::span");
    public static By totalLines = By.xpath("//label[text()='Total Lines']/following-sibling::span");
    public static By percentComplete = By.xpath("//div[@class='order-detail-list']//div/label[text()='Percent Complete']");
    public static By itemNumberHeader = By.xpath("//table[@class='table']//th[1]");
    public static By itemDescriptionHeader = By.xpath("//table[@class='table']//th[2]");
    public static By qtyPlannedHeader = By.xpath("//table[@class='table']//th[3]");
    public static By qtyRemainingHeader = By.xpath("//table[@class='table']//th[4]");
    public static By binLocationHeader = By.xpath("//table[@class='table']//th[5]");
    public static By statusHeader = By.xpath("//table[@class='table']//th[6]");
    public static By pickViaHeader = By.xpath("//table[@class='table']//th[7]");
    public static By pickerHeader = By.xpath("//table[@class='table']//th[8]");
    public static By completeManualPickButton = By.xpath("//button[contains(text(),'Complete Manual Picking')]");
    public static By maintainPickersButton = By.xpath("//button[contains(text(),'Maintain Pickers')]");
    public static By printDocumentButton = By.xpath("//button[contains(text(),'Print Documents and Labels')]");
    public static By stagingButton = By.xpath("//button[contains(text(),'Staging')]");
    public static By pickStatusLink = By.xpath("//span[text()='Pick Status']");
    public static By closeFilter = By.xpath("//a/span[@class='pi pi-times']");
    public static By rfGunTableHeader = By.xpath("//p[text()='RF Gun']/ancestor::th[@role='columnheader']");
    public static By manualTableHeader = By.xpath("//p[text()='Manual']/ancestor::th[@role='columnheader']");
    public static By mainMenuTbx = By.id("I_22_7");
    public static By pickStatusValue = By.xpath("//win-select[@valuekey='pickStatusValue']");
    public static By logoutButton = By.xpath("//*[@id='side-nav-wrapper']//div[contains(@class,'logout-icon')]");
    public static By logoutToolTip = By.xpath("//a[contains(text(),'Logout')]");
    public static By selectCurrentZone = By.xpath("//option[contains(@value,'1:')]");
    public static By manualPickingHeader = By.xpath("//h3[text()='Complete Manual Picking']");
    public static By buttonConfirm = By.xpath("//button[text()='Confirm ']");
    public static By percentageCompleteBar = By.xpath("//div[@role='progressbar']");
    public static By totalLinesCount = By.xpath("//label[text()='Open / Total Lines']/following-sibling::p");
    public static By pickingInProcessBackLink = By.xpath("//span[@class='weight back-link ng-star-inserted']");
    public static By shipDateDropDown = By.xpath("//th[@ng-reflect-field='datetime']//div[@role='button']");
    public static By loadDateDropDownValue = By.xpath("//th[@ng-reflect-field='datetime']//ul//li/span[text()='Load Date/Time']");
    public static By shipDateDropDownValue = By.xpath("//th[@ng-reflect-field='datetime']//ul//li/span[text()='Ship Date']");
    public static By shipDateFirstRowVal = By.xpath("//table[@role='grid']//tbody/tr[1]/td[11]");
    public static By urgentIcon = By.xpath("//span[@tooltip='Urgent']");
    public static By returnToStockIcon = By.xpath("//span[@tooltip='Return To Stock']");
    public static By manifestIcon = By.xpath("//span[@tooltipclass='manifest-tooltip']");
    public static By totalPages = By.xpath("//label[@class='current-page-label']/span");
    //public static By nextPageArrow = By.xpath("//li[contains(@class,'next page-item')]/a[@class='page-link']");
    public static By returnToPickQPriorityLink = By.xpath("//*[text()='Return to Pick Queue Priority']");
    public static By nextPageArrow = By.xpath("//a[@class='page-link' and text()='›']");
    public static By lastPageArrow = By.xpath("//a[@class='page-link' and text()='»']");
    public static By previousPageArrow = By.xpath("//a[@class='page-link' and text()='‹']");
    public static By firstPageArrow = By.xpath("//a[@class='page-link' and text()='«']");
    public static By currentPageNumber = By.xpath("//*[@id='currentPage']");
    public static By disabledFirstPageArrow = By.xpath("//li[@class='pagination-first page-item ng-star-inserted disabled']");
    public static By disabledPreviousPageArrow = By.xpath("//li[@class='pagination-prev page-item ng-star-inserted disabled']");
    public static By disabledNextPageArrow = By.xpath("//li[@class='pagination-next page-item ng-star-inserted disabled']");
    public static By disabledLastPageArrow = By.xpath("//li[@class='pagination-last page-item ng-star-inserted disabled']");
    public static By pageCount15 = By.xpath("//div[@class='count-goto-page float-left page-size']//span[text()='15']");
    public static By pageCount30 = By.xpath("//div[@class='count-goto-page float-left page-size']//span[text()='30']");
    public static By loadPickQpopUpHeader = By.xpath("//div[@class='modal-dialog']//h2");
    public static By pickQPopUpBodyMsg1 = By.xpath("//h2[text()='LOAD PICK QUEUE']/following-sibling::p");
    public static By pickQPopUpBodyMsg2 = By.xpath("//h2[text()='LOAD PICK QUEUE']/following-sibling::p[2]");
    public static By processShipDateThroughLabel = By.xpath("//h2[text()='LOAD PICK QUEUE']/parent::div//label");
    public static By loadPickQCancelButton = By.xpath("//h2[text()='LOAD PICK QUEUE']/parent::div//button[text()='Cancel']");
    public static By loadPickQRunButton = By.xpath("//h2[text()='LOAD PICK QUEUE']/parent::div//button[text()='Run']");
    public static By loadPickQLink = By.xpath("//span[text()=' Load Pick Queue']");
    public static By nonPickedQtyRemainingValue = By.xpath("(//span[text()='Not-Picked']/ancestor::td/preceding-sibling::td)[4]");
    public static By nonPickedQtyPlannedValue = By.xpath("(//span[text()='Not-Picked']/ancestor::td/preceding-sibling::td)[3]");
    public static By percentageEquals50 = By.xpath("//table[@role='grid']//tbody/tr/td[9][contains(text(),'50')]/preceding-sibling::td");
    public static By pickedQtyPlannedValue = By.xpath("(//span[text()='Picked']/ancestor::td/preceding-sibling::td)[3]");
    public static By pickedQtyRemainingValue = By.xpath("(//span[text()='Picked']/ancestor::td/preceding-sibling::td)[4]");
    public static By collapseAll = By.xpath("//*[text()=' Collapse All ']");
    public static By printDocumentPopUpHeader = By.xpath("//div[@class='modal-body']//span[text()='Print Documents and Labels']");
    public static By popUpLabelShipment = By.xpath("//div[@class='modal-body']//label[text()='Shipment']");
    public static By popUpLabelCustomerName = By.xpath("//div[@class='modal-body']//label[text()='Customer Name ']");
    public static By pickListCheckbox = By.xpath("//span[text()='Pick List']/preceding-sibling::input");
    public static By packingListCheckbox = By.xpath("//span[text()='Packing List']/preceding-sibling::input");
    public static By shippingLabelCheckbox = By.xpath("//span[text()='Shipping Label']/preceding-sibling::input");
    public static By printDocumentPopUpCancel = By.xpath("//span[text()='Print Documents and Labels']/ancestor::div[@class='modal-body']//button[text()='Cancel']");
    public static By printDocumentPopUpPrint = By.xpath("//button[text()='Print ']");
    public static By stagedShippment = By.xpath("//div[contains(text(),'Staged')]/parent::td/preceding-sibling::td");
    public static By stagingPopUpHeader = By.xpath("//div[@class='modal-header' and contains(text(),'Staging on Shipment')]");
    public static By stagingLocationHeader = By.xpath("//div[contains(@class,'ui-table-wrapper')]//th[text()=' Staging Location ']");
    //public static By aassHeader = By.xpath("//div[contains(@class,'ui-table-wrapper')]//th[text()=' AASS ']");
    public static By removeStagingDataLink = By.xpath("//*[text()='Remove Staging Data ']");
    public static By removeStagingDataDisabledLink = By.xpath("//*[text()='Remove Staging Data ' and @disabled]");
    public static By settingsIcon = By.xpath("//i[contains(@class,'gear-icon icon-height-width')]");
    public static By warehouseSettingsLabel = By.xpath("//div[contains(@class,'link-hover')]//span[text()='Warehouse Settings']");
    public static By displaySettingsLabel = By.xpath("//div[@class='win-company']//*[text()='Display Settings']");
    public static By selectThemeLabel = By.xpath("//div[@class='win-company']//*[text()=' Select Theme: ']");
    public static By highContrastLabel = By.xpath("//div[@class='win-company']//*[text()='High Contrast:']");
    public static By warehouseSettingsPageHeader = By.xpath("//label[text()='WAREHOUSE SETTINGS']");
    public static By globalSettingsLink = By.id("globalSettingTab");
    public static By manualPickCompletionLink = By.xpath("//div[@id='globalSetting']/*[text()=' Manual Pick Completion ']");
    public static By shipViaLink = By.id("shippingMethodsTab");
    public static By pickQueueScheduleLink = By.id("pickQueueScheduleTab");
    public static By holidayEventScheduleLink = By.id("holidayeventcalanderTab");
    public static By packingUnitsTab = By.id("packagingUnitsTab");
    public static By documentsAndPrintingTab = By.id("documentsAndPrintingTab");
    public static By documentsAndPrinting = By.id("documentsAndPrinting");
    public static By subHeadingText = By.xpath("//p[contains(@class,'sub-heading')]");
    public static By manualPickCompletionLabel = By.xpath("//div[@class='global-setting']//div[contains(text(),'Manual Pick Completion')]");
    public static By standardCheckbox = By.id("standard");
    public static By batchCheckbox = By.id("batch");
    public static By individualCheckbox = By.id("individual");
    public static By shipViaPageHeader = By.xpath("//label[text()='Ship Via (Shipping Methods)']");
    public static By shipViaPageSubHeader = By.xpath("//p[text()='Sort these by the priority you would like their orders to have for picking. ']");
    public static By pickPriorityColumnHeader = By.xpath("//label[text()='Pick Priority']");
    public static By nameColumnHeader = By.xpath("//label[text()='Name']");
    public static By codeColumnHeader = By.xpath("//label[text()='Code']");
    public static By suggestedStagingAreaColumnHeader = By.xpath("//label[text()='Suggested Staging Area']");
    public static By stagingRequiredColumnHeader = By.xpath("//label[text()='Staging Required']");
    public static By printRoutingListColumnHeader = By.xpath("//label[text()='Print Routing List']");
    public static By packingListPrinterColumnHeader = By.xpath("//label[text()='Packing List Printer']");
    public static By rtiCriteriaColumnHeader = By.xpath("//label[text()='RTI Criteria']");
    public static By shipViaPageButton = By.xpath("//button[text()=' New Ship Via']");
    public static By pickPriority1Text = By.xpath("//input[@ng-reflect-model='1']/parent::div/following-sibling::div/label");
    public static By pickPriority2Text = By.xpath("//input[@ng-reflect-model='2']/parent::div/following-sibling::div/label");
    public static By pickPriority1 = By.xpath("//input[@ng-reflect-model='1']");
    public static By pickPriority4 = By.xpath("//input[@ng-reflect-model='4']");

    public static By pickPriority4Text = By.xpath("//input[@ng-reflect-model='4']/parent::div/following-sibling::div/label");
    public static By shipViaDetailsPageHeader = By.xpath("//span[text()='Ship Via Details']");
    public static By nameTextField = By.xpath("//label[text()='Name *']/following-sibling::input");
    public static By codeTextField = By.xpath("//label[text()='Code *']/following-sibling::input");
    public static By buttonSave = By.xpath("//button[text()='Save ']");
    public static By pickQueueSchedulePageHeader = By.xpath("//label[text()='Pick Queue Schedule']");
    public static By pickQueueSchedulePageSubHeader = By.xpath("//p[text()=' For each day of the week, select what orders you would like to pick based on their shipping day. ']");
    public static By schedulePageSundayColumnHeader = By.xpath("//span[text()='Sunday']");
    public static By schedulePageMondayColumnHeader = By.xpath("//span[text()='Monday']");
    public static By schedulePageTuesdayColumnHeader = By.xpath("//span[text()='Tuesday']");
    public static By schedulePageWednesdayColumnHeader = By.xpath("//span[text()='Wednesday']");
    public static By schedulePageThursdayColumnHeader = By.xpath("//span[text()='Thursday']");
    public static By schedulePageFridayColumnHeader = By.xpath("//span[text()='Friday']");
    public static By schedulePageSaturdayColumnHeader = By.xpath("//span[text()='Saturday']");
    public static By pickQueueScheduleAddDay = By.xpath("//a[text()='Add Day']");
    public static By pickQUpdateSuccessMessage = By.xpath("//div[text()='Pick Queue Schedule successfully updated.']");
    public static By addDayWednesday = By.xpath("(//*[@class='text-center day-add'])[4]");
    public static By removeDayButton = By.xpath("//div[@class='container-fluid']/app-day[4]//a[@tooltip='Remove Day']");
    public static By pickQScheduleMessage = By.xpath("//*[text()='//Pick Queue Schedule successfully updated.']");
    //public static By collapseAll = By.xpath("");
    public static By holidayEventSchedulePageHeader = By.xpath("//label[text()='Holiday & Event Schedule']");
    public static By holidayEventSchedulePageSubHeader = By.xpath("//p[text()=' Orders shipping through the selected date will be added to the pick queue for the selected date range. ']");
    public static By holidayEventScheduleNameColumnHeader = By.xpath("//div[@class='holiday-years']//div[text()='Name']");
    public static By holidayEventScheduleDateColumnHeader = By.xpath("//div[@class='holiday-years']//div[text()='Date']");
    public static By holidayEvntSchOrdersShippingThroughColHeader = By.xpath("//div[@class='holiday-years']//div[text()='Orders Shipping Through']");
    public static By holidayEvntSchAddToQueueColHeader = By.xpath("//div[@class='holiday-years']//div[text()='Add to Queue']");
    public static By holidayEventScheduleActionsColumnHeader = By.xpath("//div[@class='holiday-years']//div[text()='Actions']");
    public static By currentYear = By.xpath("//div[contains(@class,'right year-row')]/a/following-sibling::div");
    public static By previousYearArrow = By.xpath("//div[contains(@class,'right year-row')]/a[contains(@class,'left')]");
    public static By  nextYearArrow = By.xpath("//div[contains(@class,'right year-row')]/a[contains(@class,'right')]");
    public static By eventNameColumnHeader = By.xpath("(//div[@class='holiday-years']//div[text()='Name'])[2]");
    public static By eventDateColumnHeader = By.xpath("(//div[@class='holiday-years']//div[text()='Date'])[2]");
    public static By eventOrdersShippingThroughColHeader = By.xpath("(//div[@class='holiday-years']//div[text()='Orders Shipping Through'])[2]");
    public static By eventAddToQueueColHeader = By.xpath("(//div[@class='holiday-years']//div[text()='Add to Queue'])[2]");
    public static By eventActionsColumnHeader = By.xpath("(//div[@class='holiday-years']//div[text()='Actions'])[2]");
    public static By addEventButton = By.xpath("//a[text()=' New Event']");
    public static By nameTextBox = By.xpath("//div[contains(@class,'event-data')]/input[@type='text']");
    public static By dateTextBox = By.xpath("//input[contains(@id,'eventdate')]");
    public static By shippingThroughTextBox = By.xpath("//input[contains(@id,'eventordership')]");
    public static By addToQueueStartTextBox = By.xpath("//input[contains(@id,'eventqueuestart')]");
    public static By addToQueueEndTextBox = By.xpath("//input[contains(@id,'eventqueueend')]");
    public static By cancelEventIcon = By.xpath("//a[@tooltip='Cancel']");
    public static By saveEventIcon = By.xpath("//a[@tooltip='Save']");
    public static By saveSuccessMessage = By.xpath("//div[text()='Settings successfully saved.']");
    public static By updateSuccessMessage = By.xpath("//div[text()='Settings successfully updated.']");
    public static By deleteSuccessMessage = By.xpath("//div[text()='Event successfully deleted.']");
    public static By eventDateFieldValue = By.xpath("//div[contains(@class,'row row-margin row-border-bottom ')]/div[2]/div");
    public static By startDateErrorMessage = By.xpath("//div[text()='Start date already exists.']");
    public static By allDatesErrorMessage = By.xpath("//div[text()='All date fields must be filled.']");
    public static By packagingUnitsPageHeader = By.xpath("//label[text()='Packaging Units']");
    public static By packagingUnitsPageSubHeader = By.xpath("//p[text()='These are the containers you use to package items. Arrange at least one, and up to six units in the order you would like them visible on the scan gun.']");
    public static By listOrdersInputField = By.xpath("//span[text()='List Order']/following-sibling::span/input");
    public static By unitLabelField = By.xpath("//span[text()='Unit Label']");
    public static By unitLabelInputField = By.xpath("//span[text()='Unit Label']/following-sibling::span/input");
    public static By editListOrderIcon = By.xpath("//i[contains(@class,'pencil') and @tooltip='Edit']");
    public static By editListOrder = By.xpath("//*[contains(text(),'TESTAUTO')]/ancestor::span/preceding-sibling::span[@containerclass='customTooltip']/i[@tooltip='Edit']");
    public static By trashListOrderIcon = By.xpath("//i[@tooltip='Delete']");
    public static By deleteListOrder = By.xpath("//*[contains(text(),'TESTAUTO')]/ancestor::span/preceding-sibling::span[@containerclass='customTooltip']/i[@tooltip='Delete']");
    public static By plusListOrderIcon = By.xpath("//i[contains(@class,'plus')]");
    public static By rightListOrderIcon = By.xpath("//i[@tooltip='Save']");
    public static By closeListOrderIcon = By.xpath("//i[@tooltip='Cancel']");
    public static By firstUnitLabel = By.xpath("(//span[text()='Unit Label']/following-sibling::span/p)[1]");
    public static By savePackingUnitsIcon = By.xpath("//i[contains(@class,'check')]");
    public static By cancelPackingUnitsIcon = By.xpath("//i[contains(@class,'close')]");
    public static By packingUnitsSuccessMsg = By.xpath("//*[text()='Packaging Units successfully updated.']");
    public static By deleteSuccessMsg = By.xpath("//*[contains(text(),'Packaging Units successfully deleted.']");
    public static By leaveWithouSavingPopUpHeader = By.xpath("//h2[text()='LEAVE WITHOUT SAVING?']");
    public static By leaveWithoutSaveCancelBtn = By.xpath("//div[@class='desktop-button']//button[text()='Cancel']");
    public static By leaveWithoutSaveContBtn = By.xpath("//div[@class='desktop-button']//button[text()='Continue']");
    public static By leaveWithouSavingPopUpMsg = By.xpath("//div[text()='Changes you made may not be saved.']");
    public static By documentsAndPrintingPageHeader = By.xpath("//label[text()='Documents & Printing']");
    public static By documentsAndPrintingPageSubHeader = By.xpath("//p[text()='Setup default values for your documents and printers. ']");
    public static By labelPrinterFormat = By.xpath("//div[@class='row']//div[text()=' Label Printer Format ']");
    public static By addPrinterLink = By.xpath("//div[@class='text-right day-add']/a[text()='Add Printer']");
    public static By printerDesignationColHeader = By.xpath("//div[text()='Printer Designation']");
    public static By printerNameColHeader = By.xpath("//div[text()='Printer Name']");
    public static By printerFormatColHeader = By.xpath("//div[text()='Printer Format']");
    public static By labelSizeColHeader = By.xpath("//div[text()='Label Size']");
    public static By printerDetailsPopupHeader = By.xpath("//div[@class='modal-content']//div[text()=' Printer Details ']");
    public static By printerDesignationField = By.xpath("//div[text()=' Printer Designation ']/../following-sibling::div//*[@ng-reflect-id='designation']");
    public static By printerFormatField = By.xpath("//div[text()=' Printer Format ']/../following-sibling::div//*[@ng-reflect-id='format']");
    public static By labelSizeField = By.xpath("//div[text()=' Label Size ']/../following-sibling::div//*[@ng-reflect-id='label']");
    public static By printerNameField = By.xpath("//div[text()=' Printer Name ']/following-sibling::input[@id='printerName']");

    public static By saveButton = By.xpath("//button[text()='Save']");

    public static By cancelButton = By.xpath("//button[text()='Cancel']");
    public static By closeButton = By.xpath("//i[contains(@class,'card-close')]");
    public static By searchFilterIcon = By.xpath("//i[@class='fa fa-search']");
    public static By shipmentNumber1 = By.xpath("//table[@role=\"grid\"]/tbody/tr[1]/td[1]");
    public static By shipmentFilterTextField = By.xpath("//*[@field='orderNumber']//input[@role='searchbox']");
    public static By removeShipmentFilter = By.xpath("//span[text()='Shipment']");
    public static By noResultFoundMsg = By.xpath("//td[text()='No Results Found']");
    public static By customer1 = By.xpath("//table[@role=\"grid\"]/tbody/tr[1]/td[2]");
    public static By customerFilterTextField = By.xpath("//*[@field='customer']//input[@role='searchbox']");
    public static By removeCustomerFilter = By.xpath("//span[text()='Customer']");
    public static By assignedRF1 = By.xpath("//table[@role=\"grid\"]/tbody/tr[1]/td[3]//div");
    public static By assignedRFFilterTextField = By.xpath("//*[@field='displayName']//input[@role='searchbox']");
    public static By removeassignedRFFilter = By.xpath("//span[text()='Assigned To']");
    public static By shipVia1 = By.xpath("//table[@role=\"grid\"]/tbody/tr[1]/td[4]");
    public static By removeShipViaFilter = By.xpath("//span[text()='Ship Via']");
    public static By truckNameSrs = By.xpath("//li[@aria-label='srs                 ']//div[contains(@class,'chkbox')]/div");
    public static By truckNameTruck1 = By.xpath("//li[@aria-label='truck1              ']//div[contains(@class,'chkbox')]/div");
    public static By removeTruckNameFilter = By.xpath("//span[text()='Truck Name']");
    public static By pickStatusStaged = By.xpath("//span[text()='Staged']");
    public static By pickStatusAll = By.xpath("//span[text()='All']");
    public static By pickStatusInProcess = By.xpath("//span[text()='In Process']");
    public static By packListPrintStatus = By.xpath("//input[@id='printStatus']");
    public static By packListPrintStatusValueY = By.xpath("//input[@id='printStatus']/../following-sibling::ul/li/span[text()='Yes']");
    public static By stagedValue = By.xpath("//div[contains(text(),' Staged ')]");
    public static By packListPrintStatusLink = By.xpath("//span[text()='Pack List Print Status']");
    public static By clearFilterLink = By.xpath("//span[text()='Clear Filters ']");
    public static By packListPrintStatusValueN = By.xpath("//input[@id='printStatus']/../following-sibling::ul/li/span[text()='No']");
    public static By shipDate = By.xpath("//table[@role='grid']/tbody/tr[1]/td[11]");
    public static By shipDateDropDownFilter = By.xpath("//input[@id='filterShipDate']");
    public static By removeShipDateFilter = By.xpath("//span[text()='Ship Date']");

    public static By assignedToFilterValue = By.xpath("//*[contains(@class,'ui-autocomplete-items')]/li");
    public static By manualLinesSort = By.xpath("//th[@ng-reflect-field='manualLines']//i[contains(@ng-reflect-ng-class,'[object Object]')]");
    public static By manualColumnValue1 = By.xpath("//table[@role=\"grid\"]/tbody/tr[1]/td[6]");
    public static By completeManualPickPopUp = By.xpath("//h3[text()=\"COMPLETE MANUAL PICKING\"]");
    public static By completeManualPickPopUpText = By.xpath("//i[contains(text(),'You are about to complete Manual Picking for')]");
    public static By completeManualPickPopUpCancel = By.xpath("//button[text()='Cancel ']");
    public static By completeManualPickPopUpConfirm = By.xpath("//button[text()='Confirm ']");
    public static By printPackingListPopUp = By.xpath("//span[contains(text(),'PRINT PACKING LIST')]");
    public static By printPackingListPopUpCancel = By.xpath("//span[contains(text(),'PRINT PACKING LIST')]/../following-sibling::div/div/following-sibling::div/div/div/button[contains(text(),'Cancel')]");
    public static By greenCheckManual = By.xpath("//i[contains(@class,'circle')]");
    public static By exceptionQueueSideMenu = By.xpath("//div[@id='side-nav-wrapper']/div[7]");
    public static By exceptionQueuePageHeader = By.xpath("//h2[contains(text(),'Exceptions Queue ')]");
    public static By dateTimeColumnSort = By.xpath("//thead[@class='ui-table-thead']/tr/th[2]/div/div[2]");
    public static By dateTimeColumnHeader = By.xpath("//thead[@class='ui-table-thead']/tr/th[2]");
    public static By sourceColumnHeader = By.xpath("//thead[@class='ui-table-thead']/tr/th[3]");
    public static By sourceColumnSort = By.xpath("//thead[@class='ui-table-thead']/tr/th[3]/div/div[2]");
    public static By errorReasonColumnHeader = By.xpath("//thead[@class='ui-table-thead']/tr/th[4]");
    public static By errorReasonColumnSort = By.xpath("//thead[@class='ui-table-thead']/tr/th[4]/div/div[2]");
    public static By statusColumnHeader = By.xpath("//thead[@class='ui-table-thead']/tr/th[5]");
    public static By statusColumnSort = By.xpath("//thead[@class='ui-table-thead']/tr/th[5]/div/div[2]");
    public static By exportToExcelButton = By.xpath("//button[text()=' Export To Excel ']");
    public static By itemNumber1 = By.xpath("//tbody[@class='ui-table-tbody']/tr/td[6]");
    public static By source1 = By.xpath("//tbody[@class='ui-table-tbody']/tr/td[3]");
    public static By date1 = By.xpath("//tbody[@class='ui-table-tbody']/tr/td[2]/div");
    public static By errorreason1 = By.xpath("//tbody[@class='ui-table-tbody']/tr/td[4]");
    public static By status1 = By.xpath("//tbody[@class='ui-table-tbody']/tr/td[5]");
    public static By itemNumberTextField = By.xpath("//*[@id='itemNumber']/span/input");
    public static By paginationNextPage = By.xpath("//li[contains(@class,'pagination-next page')]");
    public static By paginationPrevPage = By.xpath("//li[contains(@class,'pagination-prev page')]");
    public static By paginationLastPage = By.xpath("//li[contains(@class,'pagination-last page')]");
    public static By paginationFirstPage = By.xpath("//li[contains(@class,'pagination-first page')]");
    public static By pageNumber = By.xpath("//*[@id='currentPage']");
    public static By viewDetailsBox = By.xpath("//tr[@name='dataRow']/td[10]");
    public static By eyeIcon = By.xpath("//tr[@name='dataRow']/td[10]/div/span/a");
    public static By resolutionNotesBox = By.xpath("//*[@id='textArea']");
    public static By resolutionBoxSave = By.xpath("//button[text()=' Save Note ']");
    public static By resolutionNotesSaveMessage = By.xpath("//span[text()='Resolution note successfully updated.']");
    public static By savedCommentIcon = By.xpath("//i[contains(@class,'comment')]");
    public static By itemNumberColumnHeader = By.xpath("//div[text()='Item Number']");
    public static By itemNumberColumnSort = By.xpath("//div[text()='Item Number']/../div[2]/i");
    public static By filtersButtonLink = By.xpath("//button[contains(@class,'filter')]");
    public static By exceptionCheckbox = By.xpath("//tbody/tr[1]//div[contains(@class,'ui-chkbox-box')]");
    public static By hideDetailsIcon = By.xpath("//tr[@name='dataRow']/td[10]/div/span/a");
    public static By closeEntryIcon = By.xpath("//tr[@name='dataRow']/td[10]/div/span/a[@tooltip='Close Entry']");
    public static By exceptionClosedMessage = By.xpath("//span[text()='Exception(s) successfully closed.']");
    public static By statusDropdown = By.xpath("//*[@valuekey='exceptionStatusCode']/div/div/span");
    public static By statusClosedValue = By.xpath("//*[@valuekey='exceptionStatusCode']/div/ul/li[3]");
    public static By statusOpenValue = By.xpath("//*[@valuekey='exceptionStatusCode']/div/ul/li[2]");
    public static By statusAllValue = By.xpath("//*[@valuekey='exceptionStatusCode']/div/ul/li[1]");
    public static By itemNumber2 = By.xpath("//tbody[@class='ui-table-tbody']/tr[2]/td[6]");
    public static By closedSelectedEntriesButton = By.xpath("//button[text()=' Close Selected Entries ']");
    public static By statusFilter = By.xpath("//span[text()='Status']");
    public static By exceptionCheckbox2 = By.xpath("//tbody/tr[2]//div[contains(@class,'ui-chkbox-box')]");
    public static By sourceProcessDropdown = By.xpath("//*[@valuekey='sourceProcessCode']/div/div/span");
    public static By SourcePickingValue = By.xpath("//*[@valuekey='sourceProcessCode']/div/ul/li[2]");
    public static By SourceAllValue = By.xpath("//*[@valuekey='sourceProcessCode']/div/ul/li[1]");
    public static By fromDate = By.xpath("//input[@id='fromDate']");
    public static By toDate = By.xpath("//input[@id='toDate']");
    public static By errorReasonDropdown = By.xpath("//input[@id='errorReason']");
    public static By errorReasonValue = By.xpath("//*[@valuekey='exceptionReasonsCode']/div/ul/li[31]");
    public static By errorReasonAllValue = By.xpath("//*[@valuekey='exceptionReasonsCode']/div/ul/li[1]");
    public static By errorReasonValue1 = By.xpath("//*[@valuekey='exceptionReasonsCode']/div/ul/li[20]");
    public static By sourceProcessFilter = By.xpath("//span[text()='Clear Filters ']");
    public static By errorReasonFilter = By.xpath("//span[text()='Error Reason']");
    public static By rangeFilter = By.xpath("//span[text()='Range']");










    //public static By cancelEventIcon = By.xpath("");
    //public static By cancelEventIcon = By.xpath("");
    //public static By cancelEventIcon = By.xpath("");
    //public static By cancelEventIcon = By.xpath("");
    //public static By cancelEventIcon = By.xpath("");
    //public static By cancelEventIcon = By.xpath("");


}
