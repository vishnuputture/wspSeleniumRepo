package pages.warehouse;

import org.openqa.selenium.By;

public class RTSPlannerPage {

    public static By RTSHeader = By.xpath("//h2[contains(text(),' READY TO SHIP PLANNER ')]");
    public static By createManifestHeader = By.xpath("//span[contains(text(),'Create Manifest')]");
    public static By createManifest = By.xpath("//button[contains(text(), 'Create Manifest')]");
    public static By manifestsFilters = By.xpath("//body/my-app/div[@id='containerFluid']/ready-to-ship-planner/div/div/div/div/div/button[1]");
    public static By shipmentFilters = By.xpath("//div[@id='containerFluid']//ready-to-ship-planner//div//div//div//div//div//div//div//div//button[@type='button'][normalize-space()='Filters']");
    public static By dateModal = By.xpath("/html/body/my-app/div/ready-to-ship-planner/div[3]/new-manifest-modal/div/div/div[2]/form/div[1]/div[1]/div/win-datepicker/div/input");
    public static By startTime = By.xpath("/html[1]/body[1]/my-app[1]/div[1]/ready-to-ship-planner[1]/div[3]/new-manifest-modal[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[1]/win-timepicker[1]/div[1]/input[1]");
    public static By truckDropDown = By.xpath("(//input[@id='editTruck'])[1]");
    public static By driverDropDown = By.xpath("//input[@id='driver'][1]");
    public static By completeCreate = By.xpath("//button[@type='button']//span[contains(text(),'Create Manifest')]");
    public static By driverNotes = By.xpath("(//textarea[@id='notes'])[2]");
    public static By createManifestClose = By.className("fa fa-close2");
    public static By createManifestModal = By.className("modal-dialog");
    public static By successMessage = By.cssSelector(".ui-growl-title");
    public static By manifestFilterFromDate = By.xpath("(//input[@id='fromDate'])[1]");
    public static By manifestFilterToDate = By.xpath("(//input[@id='toDate'])[1]");
    public static By somethingID = By.id("BinMaint.ng");
    public static By manifestFilterStatus = By.xpath("(//input[@id='status'])[1]");
    public static By manifestFilterDriver = By.xpath("(//input[@id='driver'])[2]");
    public static By manifestFilterTruck = By.xpath("(//input[@id='truck'])[1]");
    public static By manifestFilterApply = By.xpath("(//button[contains(@type,'button')][normalize-space()='Apply'])[1]");
    public static By shipmentSearch = By.id("ShipmentSearch");
    public static By manifestExists = By.xpath("(//span[contains(text(),'A manifest with this truck or driver already exist')])[1]");
    public static By minuteUpArrow = By.xpath("//*[@id=\"addNewManifestModal\"]/new-manifest-modal/div/div/div[2]/form/div[1]/div[2]/div/win-timepicker/div/div/div/div[3]/a[1]/span");
    public static By hourUpArrow = By.xpath("//*[@id=\"addNewManifestModal\"]/new-manifest-modal/div/div/div[2]/form/div[1]/div[2]/div/win-timepicker/div/div/div/div[1]/a[1]/span");
    public static By closeGrowl = By.xpath("//body//my-app//div[@aria-live='polite']//div//div[1]");
    public static By shipmentCustomerName = By.xpath("(//input[@placeholder='Enter Customer Name'])[1]");
    public static By shipmentStartDate = By.xpath("(//input[@id='startDate'])[1]");
    public static By shipmentEndDate = By.xpath("(//input[@id='endDate'])[1]");
    public static By shipmentShipVia = By.xpath("(//input[@id='shipVia'])[1]");
    public static By shipmentStatus = By.xpath("(//select[@id='status'])[2]");
    public static By shipmentApply = By.xpath("(//button[@type='button'][normalize-space()='Apply'])[2]");
    public static By detailsDate = By.xpath("(//input[@id='newManifestDeliveryDate'])[1]");
    public static By detailsTime = By.xpath("(//input[@id='newManifestStartTime'])[1]");
    public static By detailsTruck = By.xpath("(//select[@id='truck'])[1]");
    public static By detailsDriver = By.xpath("(//select[@id='driver'])[1]");
    public static By detailsClose = By.xpath("//*[@id=\"newManifestModalRCP\"]/new-manifest/div/div/div[1]/button/span/i");
    public static By loading = By.xpath("//i[@class='fa fa-spinner fa-spin fa-3x fa-fw win-spinner']");
    public static By clearShipmentFilters = By.xpath("//*[@id=\"shipmentFiltersModal\"]/div/div/div[1]/div/button");
    public static By orderDropDown = By.xpath("//input[@id='table-content-filter']");
    public static By poSelect = By.xpath("//li[@class='ng-star-inserted']//span[contains(text(),'Purchase Orders')]");
    public static By poSearch = By.xpath("(//input[@id='purchaseOrderSearch'])[1]");
    public static By vendorSearch = By.xpath("(//input[@id='vendor'])[1]");

    ////*[@id="containerFluid"]/ready-to-ship-planner/div[1]/div[2]/div/div[2]/div[1]
    ////*[@id="containerFluid"]/ready-to-ship-planner/div[1]/div[2]/div/div[2]/div[2]
    ////*[@id="containerFluid"]/ready-to-ship-planner/div[1]/div[2]/div/div[2]/div[1]/win-summary-card/div/div[2]/span
    ////*[@id="containerFluid"]/ready-to-ship-planner/div[1]/div[2]/div/div[2]/div[2]/win-summary-card/div/div[2]/span
    ////*[@id="containerFluid"]/ready-to-ship-planner/div[1]/div[2]/div/div[2]/div[1]/win-summary-card/div/div[1]/div/span[1]
    ////*[@id="containerFluid"]/ready-to-ship-planner/div[1]/div[2]/div/div[2]/div[2]/win-summary-card/div/div[1]/div/span[1]
}
