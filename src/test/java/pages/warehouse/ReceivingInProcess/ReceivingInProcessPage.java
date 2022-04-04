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
    public static By processButton=By.xpath("//button[contains(text(),'Process')]");
    public static By poConfirmationPopup=By.xpath("//div[text()='PO COMPLETE CONFIRMATION']");
    public static By completeBtn=By.xpath("//button[contains(text(),'Complete PO')]");
}
