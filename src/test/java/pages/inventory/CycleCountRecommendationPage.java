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
    public static By reAssignUserCancelBtn = By.xpath("//div[@id='reassign-users-modal']//button[text()=' Cancel ']");
    public static By reAssignUserProcessBtn = By.xpath("//div[@id='reassign-users-modal']//button[text()=' Process ']");
    public static By viewingAs = By.xpath("//select[@name='roleSelector']");
    public static By auditorBanner = By.xpath("//div[@class='auditor-banner']");
    public static By printerPreference = By.xpath("//h3[text()='Printer Preferences']");
    public static By menuBar = By.xpath("//i[@class='fa fa-bars menu-icon']");
    public static By auditHistory=By.xpath("//span[text()='Audit History']");
    public static By sortOrder=By.xpath("//i[@class='fa fa-sort']");
    public static By ascOrder=By.xpath("//i[@class='fa fa-sort-asc']");
    public static By descOrder=By.xpath("//i[@class='fa fa-sort-desc']");
    public static By percentage=By.xpath("//label[text()='Counted Items / Total Items']/parent::div/p");
    public static By getPercentage=By.xpath("//span[@class='progress-pos']");
    public static By searchForUnassigned=By.xpath("//th[text()=' Assigned User ']/descendant::i");
    public static By editUnassigned=By.xpath("//td[text()='UNASSIGNED']/i");
    public static By searchUser=By.id("typeahead-company-users");
    public static By checkMark=By.xpath("//i[contains(@class,'fa fa-check')]");
    public static By closeMark=By.xpath("//i[contains(@class,'fa fa-close')]");
    public static By itemSearchField=By.id("undefined");
    public static By countedFilter=By.id("select-counted");
    public static By actionIcon=By.xpath("//i[@class='fa fa-ellipsis-v fa-fw']");
    public static By selectAssigned=By.id("select-assigned");
    public static By selectEvent=By.id("select-event");
    public static By addItemOpt=By.xpath("//select[@title='Select option to add item.']");
    public static By printSelectCheckBox=By.xpath("//div/input[@type='checkbox']");
    public static By printBtnDisabled=By.xpath("//button[@disabled and text()='Print']");
    public static By removeOpoUp=By.xpath("//p[@class='title']");
    public static By printDropDown=By.id("selectPrinter1");
    public static By inputItem=By.xpath("//cyc-item-filter[@placeholder='Item Number, Description, or Alternate']/input");
}
