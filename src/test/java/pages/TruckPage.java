package pages;

import org.openqa.selenium.By;

public class TruckPage {

    public static By unauth=By.xpath("//h1[contains(text(),'Unauthorized')]");
     public  static By companySelectorToggle= By.id("companySelectorToggle");
     public static By winIntoCompany=By.xpath("//label[contains(text(),'WIN Into Company')]");
     public static By companyNumInput=By.xpath("//label[contains(text(),'Company Selection')]/following-sibling::input");
     public static By selectButton=By.xpath("//button[contains(text(),'Select')]");

    public static  By addNewTruckBtn= By.xpath("//*[contains(text(),'New Truck')]");
    public static By menuIcontruch=By.xpath("//*[contains(@class,'menu-icon fa fa-truck')]");
    public static By subManuTruck=By.xpath("//a[contains(text(),'Trucks')]");
    public static By truckNameInput =By.id("truckName");
    public static By newTruckPlateExpInput =By.id("newTruckPlateExpiration");
    public static By licensePlateNumberInput =By.id("licensePlateNumber");
    public static By yearInput =By.id("year");
    public static By makeInput =By.id("make");
    public static By modelInput =By.id("model"); public static By vinInput =By.id("vin");
    public static By emptyTruckWeightInput =By.id("emptyTruckWeight");
    public static By weightLimitInput =By.id("weightLimit");
    public static By saveBtn=By.xpath("//button[contains(text(),'Save Truck')]");






}
