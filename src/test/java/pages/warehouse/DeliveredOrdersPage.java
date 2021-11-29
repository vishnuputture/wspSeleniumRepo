package pages.warehouse;

import org.openqa.selenium.By;

public class DeliveredOrdersPage {

    public static By subMenuDeliveredOrd=By.xpath("//a[text()='Delivered Orders ']");
    public static By deliveredOrdersHeader=By.xpath("//h2[contains(text(),' Delivered Orders')]");
    public static By unAuth=By.xpath("//h1[text()='Unauthorized']");
    public static By exportButton=By.xpath("//button[contains(text(),'Export')]");
    public static By activeDay=By.xpath("//td[@class='day']");
    public static By deliveredFromDate=By.id("deliveredFromDate");
    public static By deliveredToDate=By.xpath("deliveredToDate");
    public static By driverDrop=By.id("driver");
    public static By truckDrop=By.id("truck");
}
