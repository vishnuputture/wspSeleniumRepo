package businesskeywords.warehousing.ElectronicCommerce;

import businesskeywords.common.Login;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import pages.warehouse.ElectronicCommerce.ElectronicCommercePage;
import supportLibraries.Utility_Functions;

public class ElectronicCommerce extends ReusableLib {
    CommonActions commonObj;
    businesskeywords.Purchasing.SPO.Spo sp;
    Login login;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public ElectronicCommerce(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        sp = new businesskeywords.Purchasing.SPO.Spo(helper);
        login = new Login(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * Keyword to Sign Out
     */
    public void userSignOut() {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, ElectronicCommercePage.signOutLink, "Click Sign out");
    }

    public By aTag(String text) {
        return By.xpath("//a[text()='" + text + "']");
    }

    public By pTag(String text) {
        return By.xpath("//p[text()='" + text + "']");
    }

    public By spanTag(String text) {
        return By.xpath("//span[text()='" + text + "']");
    }

    public By h2Tag(String text) {
        return By.xpath("//h2[text()='" + text + "']");
    }

    public By labelTag(String text) {
        return By.xpath("//label[text()='" + text + "']");
    }

    public By buttonTag(String text) {
        return By.xpath("//button[text()='" + text + "']");
    }

    public By checkBoxTick(String text){
        return By.xpath("//span[text()='"+text+"']/parent::div/descendant::label");
    }

    public void navigateToListTab() {
        click(aTag("Lists "), "Click [Lists] Tab");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, spanTag("Lists"), "[Lists] sub header is present");
    }

    public void navigateToCreateNewGroup() {
        click(aTag(" Create New Group "), "Click [Create New Group]");
        commonObj.validateText(h2Tag("Create New Group"), "Create New Group", "[Create New Group] header is present");
        commonObj.validateText(labelTag("Group Name:"), "Group Name:", "[Group Name:] label is present");
        commonObj.validateText(ElectronicCommercePage.createGroup, "Create Group", "[Create Group] button is present");
        commonObj.validateText(ElectronicCommercePage.cancelButton, "Cancel", "[Cancel] button is present");
        commonObj.validateElementExists(spanTag(" close "), "Close icon [x] is present");
    }

    public void exitCreateNewGroup() {
        click(ElectronicCommercePage.closeIcon, "click [x]");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, spanTag("Lists"), "[Lists] sub header is present");
    }

    public void createNewGroup() {
        String groupName = Utility_Functions.getRandomName();
        Utility_Functions.xUpdateJson("groupName", groupName);
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, ElectronicCommercePage.groupNameInput, "Wait for page to be loaded");
        sendKeys(ElectronicCommercePage.groupNameInput, groupName, "Enter Group Name");
        click(ElectronicCommercePage.createGroup, "Click [CREATE GROUP]");
        commonObj.validateText(ElectronicCommercePage.toasterMessage, "Group Created successfully.", "[ Group Created successfully. ] toaster is present");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, spanTag("Lists"), "[Lists] sub header is present");
        commonObj.validateText(spanTag(groupName), groupName, "Group name present");
    }

    public void clickException(By by, String msg) {
        int size = ownDriver.findElements(by).size();
        try {
            click(by, msg);
        } catch (Exception e) {
            click(ownDriver.findElements(by).get(size - 1), msg);
        }
    }

    public void navigateToCreateNewLink() {
        click(aTag(" Create New List "), "CLick [Create New List]");
        commonObj.validateText(h2Tag("Create New List"), "Create New List", "[Create New List] header is present");
        commonObj.validateText(labelTag("List Name:"), "List Name:", "[List Name:] label is present");
        commonObj.validateText(labelTag("Add to Group:"), "Add to Group:", "[Add to Group:] label is present");
        commonObj.validateText(ElectronicCommercePage.newGroupFromLink, "Create New Group.", "[Create New Group.] link is present");
        commonObj.validateText(pTag("All fields are required"), "All fields are required", "[All fields are required] label is present");
        commonObj.validateText(ElectronicCommercePage.createList, "Create List", "[Create Group] button is present");
        commonObj.validateText(ElectronicCommercePage.cancelList, "Cancel", "[Cancel] button is present");
        commonObj.validateElementExists(spanTag(" close "), "Close icon [x] is present");
    }

    public void createNewLink() {
        String groupName = Utility_Functions.xGetJsonData("groupName");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, ElectronicCommercePage.groupNameInput, "Wait for page to be loaded");
        sendKeys(ElectronicCommercePage.listName, groupName, "Enter List Name");
        Utility_Functions.xSelectDropdownByName(ownDriver,ElectronicCommercePage.addTiGroup,groupName);
        click(ElectronicCommercePage.createList, "Click [CREATE LIST]");
        commonObj.validateText(ElectronicCommercePage.toasterMessage, "List Created successfully.", "[ Group Created successfully. ] toaster is present");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, spanTag("Lists"), "[Lists] sub header is present");
        commonObj.validateText(spanTag(groupName), groupName, "Group name present");
    }

    public void validateGroupList(){
        click(listExpand(Utility_Functions.xGetJsonData("groupName")));
        commonObj.validateText(aTag(Utility_Functions.xGetJsonData("groupName")),Utility_Functions.xGetJsonData("groupName"),"List is present");
    }

    public By listExpand(String groupName){
        return By.xpath("//span[text()='"+groupName+"']/parent::div/button");
    }

    public void createGroupFromList(){
        String groupName=Utility_Functions.getRandomName();
        String listName=Utility_Functions.getRandomName();
        sendKeys(ElectronicCommercePage.listName,listName,"Enter List Name");
        click(ElectronicCommercePage.newGroupFromLink,"Click Create New Group");
        commonObj.validateText(ElectronicCommercePage.newGroupFromLink,"Nevermind, Add to existing group","");
        sendKeys(ElectronicCommercePage.addGroup,groupName,"Enter group name");
        click(ElectronicCommercePage.createList, "Click [CREATE LIST]");
        commonObj.validateText(ElectronicCommercePage.toasterMessage, "List Created successfully.", "[ Group Created successfully. ] toaster is present");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, spanTag("Lists"), "[Lists] sub header is present");
        commonObj.validateText(spanTag(groupName), groupName, "Group name present");
    }

    public boolean isEnabled(By by){
        return ownDriver.findElement(by).isEnabled();
    }

    public void verifyCheckBox(){
        Utility_Functions.waitTillClickHardSleep(report,ownDriver, ElectronicCommercePage.searchBox,"validating make payment dropdown");
        Boolean bl=isEnabled(ElectronicCommercePage.addSelectedToCart);
        Utility_Functions.xAssertEquals(report,bl,false,"[ADD SELECTED TO CARD] button is disabled");
        checkButtonIsEnabled(true);
        click(checkBoxTick(Utility_Functions.xGetJsonData("groupName")),"Click check box");
    }

    public void checkButtonIsEnabled(boolean bl){
        Utility_Functions.waitTillClickHardSleep(report,ownDriver, ElectronicCommercePage.searchBox,"validating make payment dropdown");
        boolean bln=isEnabled(ElectronicCommercePage.trashIcon);
        Utility_Functions.xAssertEquals(report,bln,bl,"[Trash] Icon is disabled");
        bln=isEnabled(ElectronicCommercePage.copyIcon);
        Utility_Functions.xAssertEquals(report,bln,bl,"[Copy] Icon is disabled");
        bln=isEnabled(ElectronicCommercePage.sortIcon);
        Utility_Functions.xAssertEquals(report,bln,bl,"[Sort] Icon is disabled");
    }
}
