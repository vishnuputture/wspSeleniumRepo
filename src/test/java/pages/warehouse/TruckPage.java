package pages.warehouse;

import org.openqa.selenium.By;

public class TruckPage {

    public static  By trucksHeader= By.xpath("//h2[contains(text(),'Trucks')]");
    public static  By addNewTruckBtn= By.xpath("//*[contains(text(),'New Truck')]");
    public static  By addNewTruckHeader= By.xpath("//h2[text()='Add New Truck']");
    public static  By newTruckLabel= By.xpath("//div[contains(@class,'col')]//label[@class='win-label']");
    public static By menuIconTruck=By.xpath("//*[contains(@class,'menu-icon fa fa-truck')]");
    public static By subMenuTruck=By.xpath("//a[contains(text(),'Trucks')]");
    public static By truckNameInput =By.id("truckName");
    public static By newTruckPlateExpInput =By.id("newTruckPlateExpiration");
    public static By licensePlateNumberInput =By.id("licensePlateNumber");
    public static By yearInput =By.id("year");
    public static By makeInput =By.id("make");
    public static By modelInput =By.id("model");
    public static By vinInput =By.id("vin");
    public static By emptyTruckWeightInput =By.id("emptyTruckWeight");
    public static By weightLimitInput =By.id("weightLimit");
    public static By saveBtn=By.xpath("//button[contains(text(),'Save Truck')]");
    public static By saveBtnDis=By.xpath("//button[@disabled and contains(text(),'Save Truck')]");
    public static By pagination=By.xpath("//li[contains(@class,'pagination')]");
    public static By currentPage=By.id("currentPage");
    public static By outOf=By.xpath("//span[@class='out-of']");
    public static By show=By.xpath("//span[@class='font-weight-bold']");
    public static By helpIcon=By.xpath("//a[contains(@href,'Help')]");
    public static By filterSearch=By.xpath("//i[@class='fa fa-search']");
    public static By licensePlateExpSelect=By.xpath("//td[@class='day']");
    public static By searchFiltersLabel=By.xpath("//div[@class='input-field']//label[@class='win-label']");
    public static By applyFiltersDis=By.xpath("//button[@disabled and contains(text(),'Apply Filters')]");
    public static By clearFilters=By.xpath("//button[contains(text(),'Clear All Filters') and contains(@class,'red')]");
    public static By searchFilterPanelTitle=By.xpath("//h1[@class='panel-title']");
    public static By filtersCrossIcon=By.xpath("//span[@class='pi pi-times']");
    public static By truckFilter=By.id("trucks");
    public static By licensePlateNoFilter=By.id("licensePlate");
    public static By statusFilter=By.id("status");
    public static By cdlRequiredFilter=By.id("cdlRequired");
    public static By applyFilter=By.xpath("//button[contains(text(),'Apply Filters')]");
    public static By truckFirstName=By.xpath("//th[contains(text(),'Truck Name')]/ancestor::table/descendant::td/a");

}
