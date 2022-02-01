package pages.warehouse.BinMaintenance;

import org.openqa.selenium.By;

public class BinMaintenancePage {

    public static  By itemMaintenanceTab= By.id("ui-tabpanel-0-label");
    public static  By binMaintenanceTab= By.id("ui-tabpanel-1-label");
    public static  By itemNumberContains=By.id("itemNumberContains");
    public static  By excludeAsteriskItems=By.id("excludeAsteriskItems");
    public static  By includeJobItems=By.id("includeJobItems");
    public static  By excludeZZ98Items=By.id("excludeZZ98Items");
    public static  By itemNumber=By.id("itemNumber");
    public static  By printQty0=By.id("printQty0");
    public static  By outQtyOnHand=By.id("outQtyOnHand");
    public static  By itemDescription=By.id("itemDescription");
    public static  By menuIcon=By.xpath("//i[contains(@class,'bars menu-icon')]");
    public static  By inboxIcon=By.xpath("//i[@class='menu-icon fa fa-inbox']");
    public static  By shippingManifestIcon=By.xpath("//i[contains(@class,'fa fa-truck')]");
    public static  By printIcon=By.xpath("//i[@class='menu-icon fa fa-print']");
    public static  By logOutIcon=By.xpath("//i[contains(@class,'menu-icon log-out-icon')]");
    public static  By gearIcon=By.xpath("//i[contains(@class,'gear-icon')]");
    public static  By applyFilter=By.xpath("//button[text()='Apply Filters ']");
    public static  By clearFilter=By.xpath("//button[text()=' Clear All Filters ']");
    public static  By searchIcon=By.xpath("//button[@class='filters-btn']");
    public static  By buttonDis=By.xpath("//button[@disabled]");
    public static  By matchesHeader=By.xpath("//h2[text()='SUGGESTED MATCHES']");
    public static  By itemBinManItemDet=By.xpath("//h2[text()='Item-Bin Maintenance - Item Details']");
    public static  By itemCount=By.xpath("//span[contains(text(),' Items ')]");
    public static  By selectAllCheckbox=By.xpath("//input[@type='checkbox']/following-sibling::button[contains(text(),'elect All Items')]");
    public static  By selectAllPop=By.xpath("//label[contains(text(),'elect All Items')]/parent::div/input");
    public static  By itemCountSP=By.xpath("//tbody/tr/td[contains(@class,'pl-3 pr-3')]");
    public static  By highlightRow=By.xpath("//tr[contains(@class,'ui-state-highlight')]");
    public static  By toaster=By.xpath("//div[@class='ui-toast-summary']");

}
