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
    public static By auditHistory = By.xpath("//span[text()='Audit History']");
    public static By sortOrder = By.xpath("//i[@class='fa fa-sort']");
    public static By ascOrder = By.xpath("//i[@class='fa fa-sort-asc']");
    public static By descOrder = By.xpath("//i[@class='fa fa-sort-desc']");
    public static By percentage = By.xpath("//label[text()='Counted Items / Total Items']/parent::div/p");
    public static By getPercentage = By.xpath("//span[@class='progress-pos']");
    public static By searchForUnassigned = By.xpath("//th[text()=' Assigned User ']/descendant::i");
    public static By editUnassigned = By.xpath("//td[text()='UNASSIGNED']/i");
    public static By searchUser = By.id("typeahead-company-users");
    public static By checkMark = By.xpath("//i[contains(@class,'fa fa-check')]");
    public static By closeMark = By.xpath("//i[contains(@class,'fa fa-close')]");
    public static By itemSearchField = By.id("undefined");
    public static By countedFilter = By.id("select-counted");
    public static By actionIcon = By.xpath("//i[@class='fa fa-ellipsis-v fa-fw']");
    public static By selectAssigned = By.id("select-assigned");
    public static By selectEvent = By.id("select-event");
    public static By addItemOpt = By.xpath("//select[@title='Select option to add item.']");
    public static By printSelectCheckBox = By.xpath("//div/input[@type='checkbox']");
    public static By selectBinStart = By.id("ngb-typeahead-7-0");
    public static By typeHead = By.xpath("//button[contains(@id,'ngb-typeahead')]");
    public static By selectBinEnd = By.id("ngb-typeahead-8-0");
    public static By removeOpoUp = By.xpath("//p[@class='title']");
    public static By currentUser = By.xpath("//td[text()='1']//parent::tr/td[2]");
    public static By printDropDown = By.id("selectPrinter1");
    public static By closeIcon = By.xpath("//div[@class='ui-growl-icon-close pi pi-times']");
    public static By inputItem = By.xpath("//cyc-item-filter[@placeholder='Item Number, Description, or Alternate']/input");
    public static By selectZone = By.xpath("//select[@title='zone filter select']");
    public static By assignedUser = By.xpath("//input[@id='input-assign-user']");
    public static By mfPdVnInput = By.xpath("//div[@class='form-group mt-2']/input");
    public static By removeIcon = By.xpath("//i[contains(@class,'fas fa-do_not_disturb')]");
    public static By zoneDrop = By.xpath("//h2[text()='Auto Assign Users']//ancestor::div[@class='modal-content']/descendant::select[contains(@class,'ng-pristine')]");
    public static By revertChanges = By.xpath("//i[@title='Revert Changes']");
    public static By selectAutoAssign = By.id("select-auto-assigned");
    public static By inputZone = By.xpath("//input[@id='input-zone']");
    public static By inputBinStart = By.xpath("//input[@id='input-bin-range-start']");
    public static By inputBinEnd = By.xpath("//input[@id='input-bin-range-end']");
    public static By inputMf = By.xpath("//input[@id='input-mf']");
    public static By inputPd = By.xpath("//input[@id='input-pd']");
    public static By qtyInput = By.xpath("//input[@title='quantity input']");
    public static By autoAssignInput = By.xpath("//h2[text()='Auto Assign Users']//ancestor::div[@class='modal-content']/descendant::cyc-all-company-users/div/input");
}
