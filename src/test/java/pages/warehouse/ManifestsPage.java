package pages.warehouse;

import org.openqa.selenium.By;

public class ManifestsPage {

    public static By subMenuManifest=By.xpath("//a[contains(text(),'Manifest')]");
    public static By manifestListHeader=By.xpath("//h2[contains(text(),' Manifest List ')]");
    public static By createManBtn=By.xpath("//button[contains(text(),'Create Manifest')]");
    public static By createNewManHeader=By.xpath("//h2[contains(text(),'Create New Manifest')]");
    public static By manifestDetail=By.xpath("//a[text()='Manifest Detail']");
    public static By createManLabel=By.xpath("//div[contains(@class,'col')]//label[@class='win-label' and @for]");
    public static By addOrdNoLabel=By.xpath("//label[text()='Add Order Number']");
    public static By collapseIcon=By.xpath("//a[@data-toggle='collapse']");
    public static By stopNOrderLab=By.xpath("//span[contains(text(),'STOPS & ORDERS')]");
    public static By addButton=By.xpath("//span[text()='Add']");
    public static By addStopBtn=By.xpath("//span[text()='Add Stop']");
    public static By addPOBtn=By.xpath("//span[text()='Add PO']");
    public static By createManifestBtn=By.xpath("//span[text()='Create Manifest']");
    public static By newManifestDeliveryDate=By.id("newManifestDeliveryDate");
    public static By newManifestStartTime=By.id("newManifestStartTime");
    public static By notes=By.id("notes");
    public static By truckEle=By.xpath("//select[@name='truck']");
    public static By driverEle=By.xpath("//select[@name='driver']");
    public static By createStatus=By.xpath("//span[contains(text(),'Created')]");
    public static By addOrderNo=By.xpath("//input[@placeholder='Enter Order Number']");
    public static By orderAddedMessage=By.xpath("//span[contains(@class,'ui-messages-summary')]");
    public static By orderColLink=By.xpath("//a[@class='ng-star-inserted']");
    public static By soPoOrders=By.xpath("//span[@class='float-right']");
    public static By manifestOrderHeader=By.xpath("//label[contains(@class,'align-top header')]");
    public static By colExpIconManifestOrder=By.id("stopOrders");


}
