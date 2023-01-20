package pages.warehouse;

import org.openqa.selenium.By;

public class DriverAppPage {
    public static By username = By.xpath("//input[@name='ion-input-0']");
    public static By password = By.xpath("//input[@name='ion-input-1']");
    public static By loginButton = By.xpath("//ion-button[@expand='block']");
    public static By startDeliveryButton = By.xpath("//ion-col[3]//ion-button[1]");
}
