package pages.warehouse;

import org.openqa.selenium.By;

public class ManifestsPage {

    //public static By subMenuManifest = By.xpath("//a[contains(text(),'Manifest')]");
    public static By subMenuManifest = By.xpath("//*[@id=\"side-nav-wrapper\"]/div[4]/div/div/div[2]/div[3]/a");
    public static By subMenuRTS = By.xpath("/html/body/my-app/win-nav/div[1]/side-nav/expanded-side-nav/div/div[2]/div[5]/div[2]/a[3]");
    public static By manifestListHeader = By.xpath("//h2[contains(text(),' Manifest List ')]");
    public static By menuIcon = By.id("open");
    public static By closeMenu = By.xpath("//i[contains(@class, 'fa fa-times')]");
    public static By createManBtn = By.xpath("//button[contains(text(),'Create Manifest')]");
    public static By createNewManHeader = By.xpath("//h2[contains(text(),'Create New Manifest')]");
    public static By manifestDetail = By.xpath("//a[text()='Manifest Detail']");
    public static By createManLabel = By.xpath("//div[contains(@class,'col')]//label[@class='win-label' and @for]");
    public static By addShipmentLabel = By.xpath("//label[text()='Add Shipment']");
    public static By collapseIcon = By.xpath("//a[@data-toggle='collapse']");
    public static By stopNOrderLab = By.xpath("//span[contains(text(),'STOPS & ORDERS')]");
    public static By addButton = By.xpath("//span[text()='Add']");
    public static By addStopBtn = By.xpath("//span[text()='Add Stop']");
    public static By addPOBtn = By.xpath("//span[text()='Add PO']");
    public static By createManifestBtn = By.xpath("//span[text()='Create Manifest']");
    public static By newManifestDeliveryDate = By.id("newManifestDeliveryDate");
    public static By newManifestStartTime = By.id("newManifestStartTime");
    public static By hour = By.id("hours");
    public static By notes = By.id("notes");
    public static By truckEle = By.xpath("//select[@name='truck']");
    public static By driverEle = By.xpath("//select[@name='driver']");
    public static By createStatus = By.xpath("//span[contains(text(),'Created')]");
    public static By firstStatus = By.xpath("//span[@class='manifest-status-text']");
    public static By mobileIcon = By.xpath("//i[@class='fa fa-generate']");
    public static By addOrderNo = By.xpath("//input[@placeholder='Enter Shipment']");
    public static By orderAddedMessage = By.xpath("//span[contains(@class,'ui-messages-summary')]");
    public static By orderColLink = By.xpath("//a[@class='ng-star-inserted']");
    public static By soPoOrders = By.xpath("//span[@class='float-right']");
    public static By manifestOrderHeader = By.xpath("//label[contains(@class,'align-top header')]");
    public static By colExpIconManifestOrder = By.id("stopOrders");
    public static By manageNote = By.id("managerNotes");
    public static By successMsg = By.xpath("//span[@class='ui-growl-title']");
    public static By editNote = By.xpath("//a[text()='Edit Notes']");
    public static By updateStatusDrop = By.xpath("//p[contains(text(),'Update Status')]");
    public static By updateStatusSO = By.xpath("//div[contains(text(),'Delivered')]");
    public static By updateStatusPOPick = By.xpath("//div[text()=' Picked Up ']");
    public static By updateStatusPONotPick = By.xpath("//div[text()=' Not Picked Up ']");
    public static By soStatus = By.xpath("//p[@class='order-detail']");
    public static By ifErrorExist = By.xpath("//span[@class='ui-growl-image pi pi-times']");
    public static By warningIcon = By.xpath("//i[contains(@class,'win-status-warning')]");
    public static By deliveredGreenIcon = By.xpath("//win-status-icon[@ng-reflect-status='DELIVERED']");
    public static By notDeliveredRedIcon = By.xpath("//win-status-icon[@ng-reflect-status='NOT DELIVERED']");
    public static By generatedIcon = By.xpath("//i[@class='fa fa-generate']");
    public static By orderNoHeader = By.xpath("//h2[contains(text(),'ORDER NUMBERs')]");
    public static By orderNoHeaderPO = By.xpath("//h2[contains(text(),'PURCHASE ORDER NUMBER')]");
    public static By poDetails = By.xpath("//h3[text()='PO DETAIL']");
    public static By salesDetails = By.xpath("//span[@class='win-blue']");
    public static By expandAll = By.xpath("//a[text()='EXPAND ALL']");
    public static By closePopUp = By.xpath("//div[contains(@class,'ui-growl-icon-close')]");
    public static By addPoRowCount = By.xpath("//tr[contains(@class,'ui-selectable-row')]");
    public static By pickUpCount = By.xpath("//input[@ng-reflect-name='tempQuantityPickedup0']");
    public static By pickedUpStatus = By.xpath("//p[text()='Picked Up']");
    public static By notPickedUpStatus = By.xpath("//p[text()='Not Picked Up']");
    public static By inProgressStatus = By.xpath("//win-status-icon/following-sibling::span[contains(text(),'In Process')]");
    public static By manStatus = By.xpath("//span[@class='manifest-status-text']");
    public static By closedStatus = By.xpath("//span[contains(text(),'Closed')]");
    public static By closeIcon = By.xpath("//i[@class='fa fa-check-circle-o']");
    public static By closeManifestMsg = By.xpath("//h2[text()='CLOSE MANIFEST']");
    public static By deleteManifest = By.xpath("//span[text()='Delete Manifest']");
    public static By delButton = By.xpath("//h2[text()='DELETE MANIFEST']/ancestor::div[@class='modal-content']/descendant::button");
    public static By deliveredInput = By.id("quantityAdjusted0");

}
