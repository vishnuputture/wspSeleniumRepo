package pages.warehouse;

import org.openqa.selenium.By;

public class DriversPage {

    public static By subMenuDriver=By.xpath("//a[contains(text(),'Drivers')]");
    public static By driversHeader=By.xpath("//h2[contains(text(),'Drivers')]");
    public static By pageArrow=By.xpath("//a[@class='page-link']");
    public static By onePage=By.xpath("//span[text()='of 1']");
    public static By lastPage=By.xpath("//li[contains(@class,'disabled')]");
    public static By driverNameCount=By.xpath("//tbody[@class='ui-table-tbody']/tr");
    public static By addNewDriver=By.xpath("//button[contains(text(),'New Driver')]");
    public static By newDriverHeader=By.xpath("//h2[starts-with(@class,'modal-title text')]");
    public static By popupHeader=By.xpath("//span[@class='driver-modal-title']");
    public static By firstName=By.id("firstname");
    public static By lastname=By.id("lastname");
    public static By alias=By.id("alias");
    public static By username=By.id("username");
    public static By saveDriver=By.xpath("//button[contains(text(),'Save Driver')]");
    public static By saveDriverDis=By.xpath("//button[@disabled and contains(text(),'Save Driver')]");
    public static By cdlSort=By.xpath("//th[contains(text(),'CDL Expiration')]");
    public static By addNewDriverLabel=By.xpath("//div[contains(@class,'input-field col')]//label[@for and @class='win-label']");
    public static By crossIcon=By.xpath("//i[@class='fa fa-times' and @aria-hidden='true']");
    public static By driverNameSelect=By.id("driverNameSelect");
    public static By rankSelect=By.id("rankSelect");
    public static By statusSelect=By.id("statusSelect");
    public static By updateDriverHeader=By.xpath("//h2[text()='Update Driver']");
    public static By deleteButtonDisable=By.xpath("//button[contains(text(),'Delete') and @disabled]");
    public static By statusDriver=By.id("status");
    public static By inActive=By.xpath("//select[@id='status' and @name]/option[contains(text(),'Inactive')]");
    public static By driverFirstName=By.xpath("//th[contains(text(),'Driver Name')]/ancestor::table/descendant::td/a");

}
