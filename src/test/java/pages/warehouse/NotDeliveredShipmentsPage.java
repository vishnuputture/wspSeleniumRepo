package pages.warehouse;

import org.openqa.selenium.By;

public class NotDeliveredShipmentsPage {

    public static By subMenuNotDeliveredShip = By.xpath("//a[text()='Not Delivered Shipments ']");
    public static By notDeliveredShipmentsHeader = By.xpath("//h2[contains(text(),'Not Delivered Shipments')]");
    public static By addToManifestButton = By.xpath("//button[contains(text(),'Add to Manifest')]");
    public static By activeDay = By.xpath("//td[@class='day']");
    public static By notDeliveredDate = By.id("notDeliveredDate");
    public static By newManifestBtn = By.xpath("//button[contains(text(),'New Manifest')]");
    public static By selManNo = By.xpath("//td[@class='radio-division']/span");
    public static By manifestHeader = By.xpath("//h2[text()='MANIFESTS']");

}
