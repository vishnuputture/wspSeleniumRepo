package pages.inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class CycleCountRecommendationPage extends ReusableLib {
    public CycleCountRecommendationPage(Helper helper) {
        super(helper);
    }

    public static By cycleCountRecHeader = By.xpath("//h2[text()='Cycle Count Recommendation']");
    public static By addItemOptions = By.xpath("//label[text()='Select an Option to Add Items']/parent::div/select/option");
    public static By inventorySub = By.xpath("//a[text()='Inventory']");
    public static By reAssignUserCancelBtn = By.xpath("//div[@id='reassign-users-modal']//button[text()='Cancel ']");
    public static By reAssignUserProcessBtn = By.xpath("//div[@id='reassign-users-modal']//button[text()='Process ']");
    public static By viewingAs = By.xpath("//select[@name='roleSelector']");
    public static By auditorBanner = By.xpath("//div[@class='auditor-banner']");
    public static By printerPreference = By.xpath("//h3[text()='Printer Preferences']");
    public static By menuBar = By.xpath("//i[@class='fa fa-bars menu-icon']");
    public static By auditHistory=By.xpath("//span[text()='Audit History']");
}
