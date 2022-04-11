package pages.warehouse.ReceivingInProcess;

import org.openqa.selenium.By;

public class ReceivingInProcessPage {

    public static By applyFilterBtn=By.id("applyFilterBtn");
    public static By clearAllBtn=By.id("clearAllBtn");
    public static By searchLabels= By.xpath("//div[@class='input-field']/descendant::label[contains(@class,'win-label')]");
    public static By clearFilterCrossIcon=By.xpath("//i[@class='fa fa-times win-text-error']");
    public static By refreshPoListLink=By.xpath("//span[@class='underline-refresh']");
    public static By containsCheckBox=By.id("itemNumberContains");
    public static By userRF=By.xpath("//ion-input[@id='textWithIcon']/input");
    public static By passwordRF=By.xpath("//ion-input[@type='password']/input");
    public static By loginBtn=By.xpath("//ion-button[contains(text(),'Login')]");
    public static By putAway=By.xpath("//ion-col[contains(text(),'Receive and Put Away')]");
    public static By searchPo=By.xpath("//input[@placeholder='Search by Item or PO']");
    public static By completeBtn=By.xpath("//button[text()='Confirm']");
    public static By poItems=By.xpath("//div[@class='description']");
    public static By qtyTxtBox=By.xpath("//ion-input[@id='po-input']/input");
    public static By scanBarCode=By.xpath("//input[@placeholder='Scan or Type Barcode']");
    public static By qtyError=By.xpath("//ion-input[contains(@class,'qty-error')]");
    public static By refreshPoList=By.xpath("//span[@class='underline-refresh']");
    public static By progressBar=By.xpath("//span[@class='progress-pos']");
    public static By serialItemPopup=By.xpath("//span[text()='SERIALIZED ITEMS']");
    public static By skipBtn=By.xpath("//ion-button[text()='Skip']");
    public static By processBtn=By.xpath("//button[contains(text(),'Process')]");
    public static By remainingCount=By.xpath("//ion-col[contains(text(),' Remaining: ')]");
    public static By tickIcon=By.xpath("//i[@onfocusclass='win-success']");
    public static By lastArrow=By.xpath("//a[text()='»']");

}
