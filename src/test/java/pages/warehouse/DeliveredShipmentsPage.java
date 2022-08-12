package pages.warehouse;

import org.openqa.selenium.By;

public class DeliveredShipmentsPage {

    public static By subMenuDeliveredShip = By.xpath("//a[text()='Delivered Shipments ']");
    public static By deliveredShipmentsHeader = By.xpath("//h2[contains(text(),' Delivered Shipments')]");
    public static By unAuth = By.xpath("//h1[text()='Unauthorized']");
    public static By exportButton = By.xpath("//button[contains(text(),'Export')]");
    public static By activeDay = By.xpath("//td[@class='day']");
    public static By deliveredFromDate = By.id("deliveredFromDate");
    public static By deliveredToDate = By.xpath("deliveredToDate");
    public static By driverDrop = By.id("driver");
    public static By truckDrop = By.id("truck");
}
