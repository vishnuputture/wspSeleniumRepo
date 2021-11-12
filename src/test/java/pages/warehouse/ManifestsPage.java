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

}
