package businesskeywords.Pricing.SPA;

import businesskeywords.common.Master;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import supportLibraries.Utility_Functions;

import java.util.List;

public class CustomerGroupMaintenance extends ReusableLib {
    CommonActions commonObj;
    Master mstr;
    public static int count=0;

    public CustomerGroupMaintenance(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        mstr = new Master(helper);
    }

    /**
     * This method is invoked to navigate to Customer Group Maintenance Program
     */
    public void navigateToCustomerGroupMaintenance() {
        mstr.navigateToSalesAnalysis();
        click(CustomerGroupMaintenancePage.custGrpMntnc, "Click Customer Group Maintenance Program");
    }

    /**
     * This method click Add Groups Or Add Customer
     */
    public void clickAddGroupCustomer() {
        String text = driver.findElement(CustomerGroupMaintenancePage.addGroupsCust).getAttribute("value");
        click(CustomerGroupMaintenancePage.addGroupsCust, "Click " + text + "");
    }

    /**
     * This method to store values
     */
    public void storeGroupNumberName(String value, String el) {
        Utility_Functions.xUpdateJson("" + value + "", el);
    }

    /**
     * This method to verify group name is Filled
     */
    public void verifyGroupNameFilled() {
        String expErrorMessage = "Required field: Group Name is missing or contains invalid data.";
        String actErrorMessage = driver.findElement(CustomerGroupMaintenancePage.GroupNameMessage).getText();
        Utility_Functions.xAssertEquals(report, expErrorMessage, actErrorMessage, "Error message: ");
    }

    /**
     * This method to Enter GroupName and Verify success message
     */
    public void sendGroupName() {
        String name = Utility_Functions.xGenerateAlphaNumericString();
        storeGroupNumberName("GroupName", name);
        System.out.println("Group Name: " + name);
        sendKeys(CustomerGroupMaintenancePage.groupName, name);
        Utility_Functions.actionKey(Keys.ENTER, driver);
        String expSucMsg = "Group Name successfully changed.";
        String actSucMsg = driver.findElement(CustomerGroupMaintenancePage.GroupNameMessage).getText();
        Utility_Functions.xAssertEquals(report, expSucMsg, actSucMsg, "Message :");
    }

    /**
     * This method to Add Group name
     */
    public void addGroupName() {
        clickAddGroupCustomer();
        verifyGroupNameFilled();
        String result = driver.findElement(CustomerGroupMaintenancePage.groupNumber).getText().replaceAll("\\s", "");
        storeGroupNumberName("GroupNumber", result);
        sendGroupName();
        clickAddGroupCustomer();
    }

    /**
     * This method to select customer by entering "1" into option field
     */
    public void selectCustomers() {
        sendKeys(CustomerGroupMaintenancePage.firstCust, "1");
        storeGroupNumberName("SelectedCustomer1", driver.findElement(CustomerGroupMaintenancePage.firCustNo).getText());
        sendKeys(CustomerGroupMaintenancePage.secCust, "1");
        storeGroupNumberName("SelectedCustomer2", driver.findElement(CustomerGroupMaintenancePage.secCustNo).getText());
        sendKeys(CustomerGroupMaintenancePage.thrdCust, "1");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        String expColor = "rgba(204, 0, 51, 1)";
        String actColor1 = driver.findElement(CustomerGroupMaintenancePage.firCustNo).getCssValue("color");
        System.out.println("Selected color:" + actColor1);
        Utility_Functions.xAssertEquals(report, expColor, actColor1, "Selected customer/customers records should turn into red color: ");
    }

    /**
     * This method to deselect customer by entering "4" into option field
     */
    public void deSelectCustomers() {
        sendKeys(CustomerGroupMaintenancePage.thrdCust, "4");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        String expBlackClr = "rgba(0, 0, 0, 1)";
        String actColor = driver.findElement(CustomerGroupMaintenancePage.thrdCust).getCssValue("color");
        System.out.println("Black Color :" + actColor);
        Utility_Functions.xAssertEquals(report, expBlackClr, actColor, "Selected Customer Record Should be Deselected");
    }

    /**
     * This method to shows selected customers
     */
    public void sortSelectedCust() {
        click(CustomerGroupMaintenancePage.selectDeSel, "Click F5=Selected to show selected Customer");
    }

    /**
     * This method to shows All customers
     */
    public void showAllCustomer() {
        click(CustomerGroupMaintenancePage.selectDeSel, "Click F5=All to show all customer");
    }

    /**
     * This method to sort the customers w.r.t column name
     */
    public void sortByCustomerColumn() {
        String sortValue = driver.findElement(CustomerGroupMaintenancePage.sort).getAttribute("value");
        String value[] = sortValue.split("=");
        click(CustomerGroupMaintenancePage.sort, "Click " + sortValue + " and Record should be Sorted As per " + value[1] + " in Ascending  order");
    }

    /**
     * This method to click submit/Process
     */
    public void clickSubmit() {
        String res = driver.findElement(CustomerGroupMaintenancePage.submitBtn).getAttribute("value");
        click(CustomerGroupMaintenancePage.submitBtn, "Click " + res + "");
    }

    /**
     * This method to add customer confirmation message
     */
    public void addCustCnfrmMessage() {
        String expCustSucMessage = "Successfully added Customer. Add another or F12 Return.";
        String actCustSucMessage = driver.findElement(CustomerGroupMaintenancePage.GroupNameMessage).getText();
        Utility_Functions.xAssertEquals(report, expCustSucMessage, actCustSucMessage, "add customer confirmation message: ");
    }

    /**
     * This method to add Customer to the group
     */
    public void addCustomerToGroup() {
        selectCustomers();
        deSelectCustomers();
        sortSelectedCust();
        showAllCustomer();
        sortByCustomerColumn();
        sortByCustomerColumn();
        clickSubmit();
        clickSubmit();
        addCustCnfrmMessage();
    }

    /**
     * This method to click cancel button
     */
    public void clickCancelBtn() {
        click(CustomerGroupMaintenancePage.cancelBtn, "Click F12=Cancel");
    }

    /**
     * This method to Navigate to Customer Group Details and verify Selected customer under Group Name
     */
    public void verifySelectedCustomer() {
        clickCancelBtn();
        String expFirstCust = Utility_Functions.xGetJsonAsString("SelectedCustomer1");
        String expSecCust = Utility_Functions.xGetJsonAsString("SelectedCustomer2");
        String actCust1 = driver.findElement(CustomerGroupMaintenancePage.checkCust1).getText();
        String actCust2 = driver.findElement(CustomerGroupMaintenancePage.checkCust2).getText();
        Utility_Functions.xAssertEquals(report, expFirstCust, actCust1, "Customer is added to group name");
        Utility_Functions.xAssertEquals(report, expSecCust, actCust2, "Customer is added to group name");
    }

    /**
     * This method to Navigate to Customer Group Browse and verify Group Name
     */
    public void verifyGroupName() {
        clickCancelBtn();
        String groupName = Utility_Functions.xGetJsonAsString("GroupName");
        sendKeys(CustomerGroupMaintenancePage.searchField, groupName);
        Utility_Functions.actionKey(Keys.ENTER, driver);
        String actGroupName = driver.findElement(CustomerGroupMaintenancePage.searchGroup).getText();
        Utility_Functions.xAssertEquals(report, groupName, actGroupName, "Verify GroupName is added: ");
    }

    /**
     * This method to sort Customer group
     */
    public void sortCustomerGroup() {
        sortByCustomerColumn();
        sortByCustomerColumn();
        sortByCustomerColumn();
    }

    /**
     * This method to delete group Name by entering "4" into option field
     */
    public void deleteGroupName() {
        tillDisplayed();
        String deletedRec=Utility_Functions.xGetJsonAsString("deletedGroup");
        sendKeys(CustomerGroupMaintenancePage.searchField, deletedRec);
        Utility_Functions.actionKey(Keys.ENTER, driver);
        Utility_Functions.xIsElementDisplayed(report, driver.findElement(CustomerGroupMaintenancePage.searchField), "GroupName: " + deletedRec + " not present");
    }

    /**
     * This method to run till record is deleted as Group Number being used in SPA cannot delete
     *
     */
    public void tillDisplayed(){
        List<WebElement> list=driver.findElements(CustomerGroupMaintenancePage.groupOptField);
        int size=list.size();
        System.out.println("Length group name: "+size);
        WebElement el = null;
        for(int i=1;i<=size;i++){
            System.out.println("i Value: "+i);
            WebElement elem=driver.findElements(CustomerGroupMaintenancePage.groupOptField).get(i);
            sendKeys(elem, "4","Enter 4 in option field to delete group Name");
            el = driver.findElements(CustomerGroupMaintenancePage.getGroupName).get(count);
            System.out.println("to be deleted record: "+el.getText());
            count+= 3;
            storeGroupNumberName("deletedGroup", el.getText());
            Utility_Functions.actionKey(Keys.ENTER, driver);
            clickSubmit();
            String res=getText(CustomerGroupMaintenancePage.GroupNameMessage);
            System.out.println("res message: "+res);
            if(res.equals("Group Number being used in SPA cannot delete.")) {
                Utility_Functions.xAssertEquals(report,res,"Group Number being used in SPA cannot delete.","");
                clickCancelBtn();
            }else{clickCancelBtn();break;}
        }
    }

    /**
     * This method Exit From Customer Group Maintenance
     */
    public void exitFromCustomerGroupMaintenance() {
        commonObj.exitFromCustomerGroupMaintenance();
    }
}
