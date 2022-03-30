package pages.warehouse.ReceivingInProcess;

import org.openqa.selenium.By;

public class ReceivingInProcessPage {

    public static By applyFilterBtn=By.id("applyFilterBtn");
    public static By clearAllBtn=By.id("clearAllBtn");
    public static By searchLabels= By.xpath("//div[@class='input-field']/descendant::label[contains(@class,'win-label')]");
    public static By clearFilterCrossIcon=By.xpath("//i[@class='fa fa-times win-text-error']");
    public static By refreshPoListLink=By.xpath("//span[@class='underline-refresh']");
    public static By containsCheckBox=By.id("itemNumberContains");

}
