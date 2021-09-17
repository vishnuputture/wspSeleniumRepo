package businesskeywords.Pricing.SPA;

import businesskeywords.common.Master;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import supportLibraries.Utility_Functions;

import java.util.Arrays;
import java.util.List;

public class CustomerGroupMaintenance extends ReusableLib {
    CommonActions commonObj;
    Master mstr;
    public static int count = 0;

    public CustomerGroupMaintenance(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        mstr = new Master(helper);
    }

    /**
     * This method is invoked to navigate to Customer Group Maintenance Program
     *
     */
    public void navigateToCustomerGroupMaintenance() {
        mstr.navigateToSalesAnalysis();
        click(CustomerGroupMaintenancePage.custGrpMntnc, "Click Customer Group Maintenance Program");
    }

    /**
     * This method click Add Groups Or Add Customer
     *
     */
    public String clickAddGroupCustomer() {
        String customer = null;
        Boolean cust=Utility_Functions.xIsDisplayed(driver,CustomerGroupMaintenancePage.secCustNo);
        if(cust==true){
            customer=Utility_Functions.getText(driver,CustomerGroupMaintenancePage.secCustNo);
        }
        String text = driver.findElement(CustomerGroupMaintenancePage.addGroupsCust).getAttribute("value");
        click(CustomerGroupMaintenancePage.addGroupsCust, "Click " + text + "");
        return customer;
    }

    /**
     * This method to store values
     *
     */
    public void storeGroupNumberName(String value, String el) {
        Utility_Functions.xUpdateJson("" + value + "", el);
    }

    /**
     * This method to verify group name is Filled
     *
     */
    public void verifyGroupNameFilled() {
        String expErrorMessage = "Required field: Group Name is missing or contains invalid data.";
        String actErrorMessage = driver.findElement(CustomerGroupMaintenancePage.GroupNameMessage).getText();
        Utility_Functions.xAssertEquals(report, expErrorMessage, actErrorMessage, "Error message: ");
    }

    /**
     * This method to Enter GroupName and Verify success message
     *
     */
    public void sendGroupName() {
        String name = Utility_Functions.xGenerateAlphaNumericString();
        storeGroupNumberName("GroupName", name);
        sendKeys(CustomerGroupMaintenancePage.groupName, name);
        Utility_Functions.actionKey(Keys.ENTER, driver);
        String expSucMsg = "Group Name successfully changed.";
        String actSucMsg = driver.findElement(CustomerGroupMaintenancePage.GroupNameMessage).getText();
        Utility_Functions.xAssertEquals(report, expSucMsg, actSucMsg, "Message :");
    }

    /**
     * This method to Add Group name
     *
     */
    public void addGroup() {
        clickAddGroupCustomer();
        verifyGroupNameFilled();
        String result = driver.findElement(CustomerGroupMaintenancePage.groupNumber).getText().replaceAll("\\s", "");
        storeGroupNumberName("GroupNumber", result);
        sendGroupName();
    }

    /**
     * This method to Add Group name and click cancel button
     *
     */
    public String addGroupAndCancel() {
        addGroup();
        String groupName = Utility_Functions.xGetJsonAsString("GroupName");
        clickCancelBtn();
        return groupName;
    }

    /**
     * This method to Edit Group name
     *
     */
    public void editGroupName() {
        sendKeys(CustomerGroupMaintenancePage.groupOptField1,"2");
        String expGroupNm=getText(CustomerGroupMaintenancePage.firGroupName);
        String expGroupN=getText(CustomerGroupMaintenancePage.groupNumber1);
        String expGroupNo=expGroupN.trim();
        Utility_Functions.timeWait(2);
        Utility_Functions.actionKey(Keys.ENTER,driver);
        String actGroupN=getText(CustomerGroupMaintenancePage.groupNumber);
        String actGroupNm=getAttribute(CustomerGroupMaintenancePage.groupName,"value");
        String actGroupNo=actGroupN.trim();
        Utility_Functions.xAssertEquals(report,actGroupNo,expGroupNo,"Group Number: ");
        Utility_Functions.xAssertEquals(report,actGroupNm,expGroupNm,"Group Name: ");
    }

    /**
     * This method to Add Customer to Group Name
     *
     */
    public void addCustomerToGroupName() {
        String existCustomer=getText(CustomerGroupMaintenancePage.secCustNo);
        deleteParCustomer(existCustomer);
        clickAddGroupCustomer();
        String custNm=selectOneCustomers();
        System.out.println("custName: "+custNm);
        clickSubmit();
        clickSubmit();
        addCustCnfrmMessage();
        clickCancelBtn();
        Utility_Functions.timeWait(2);
        String[] custName=custNm.split("\\s",0);
        Boolean cust=Utility_Functions.xIsDisplayed(driver,By.xpath("//div[contains(text(),'" + custName[1] + "')]"));
        Utility_Functions.xAssertEquals(report,""+cust+"","true","Customer: "+custNm+" is added to group");

    }

    /**
     * This method to view connected groups to customer
     *
     */
    public void displayGroups() {
        sendKeys(CustomerGroupMaintenancePage.secCust,"5");
        String custName=Utility_Functions.getText(driver.findElement(CustomerGroupMaintenancePage.secCustNo));
        Utility_Functions.actionKey(Keys.ENTER,driver);
        String actCustName=Utility_Functions.getText(driver.findElement(CustomerGroupMaintenancePage.customerName));
        Utility_Functions.xAssertEquals(report,actCustName,custName,"Group Name: ");
        clickCancelBtn();
    }

    /**
     * This method to delete particular Customer from the group
     *
     */
    public void deleteParCustomer(String customer) {
        String res[]=customer.split("\\s");
        int size=driver.findElements(By.xpath("//div[contains(text(),'"+res[0]+"')]/preceding::input")).size();
        System.out.println("size: "+size);
        sendKeys(By.xpath("(//div[contains(text(),'"+res[0]+"')]/preceding::input)["+size+"]"),"4");
        Utility_Functions.actionKey(Keys.ENTER,driver);
        click(CustomerGroupMaintenancePage.submitBtn,"Click F9=Delete Record");
        /*sendKeys(CustomerGroupMaintenancePage.searchField,customer);
        Utility_Functions.actionKey(Keys.ENTER,driver);*/
        Boolean find1=Utility_Functions.xIsDisplayed(driver,By.xpath("//div[text()='" + res[0] + "']"));
        Utility_Functions.xAssertEquals(report,""+find1+"","false","GroupName: "+customer+" is deleted");
    }

    /**
     * This method to delete Customer from the group
     *
     */
    public void deleteCustomer() {
        sendKeys(CustomerGroupMaintenancePage.secCust,"4");
        String custName=Utility_Functions.getText(driver.findElement(CustomerGroupMaintenancePage.secCustNo));
        Utility_Functions.actionKey(Keys.ENTER,driver);
        click(CustomerGroupMaintenancePage.submitBtn,"Click F9=Delete Record");
        sendKeys(CustomerGroupMaintenancePage.searchField,custName);
        Utility_Functions.actionKey(Keys.ENTER,driver);
        Boolean find1=Utility_Functions.xIsDisplayed(driver,By.xpath("//div[text()='" + custName + "']"));
        Utility_Functions.xAssertEquals(report,""+find1+"","false","GroupName: "+custName+" is deleted");
        clickCancelBtn();
        clickCancelBtn();
    }

    /**
     * This method to Display Group Name
     *
     */
    public void displayGroupName() {
        sendKeys(CustomerGroupMaintenancePage.groupOptField1,"5");
        String expGroupNm=getText(CustomerGroupMaintenancePage.firGroupName);
        String expGroupN=getText(CustomerGroupMaintenancePage.groupNumber1);
        String expGroupNo=expGroupN.trim();
        Utility_Functions.actionKey(Keys.ENTER,driver);
        String actGroupN=getText(CustomerGroupMaintenancePage.groupNumber);
        String actGroupNo=actGroupN.trim();
        String actGroupNm=getAttribute(CustomerGroupMaintenancePage.groupName,"value");
        Utility_Functions.xAssertEquals(report,actGroupNo,expGroupNo,"Group Number: ");
        Utility_Functions.xAssertEquals(report,actGroupNm,expGroupNm,"Group Name: ");
        clickCancelBtn();
    }

    /**
     * This method to find groupName by clicking down button and Enter option
     *
     */
    public WebElement findGroupName(String text,int option) {
        int cnt=0;
        Boolean find1=Utility_Functions.xIsDisplayed(driver,By.xpath("//div[text()='" + text + "']"));
        while (find1==false){
            cnt++;
            click(CustomerGroupMaintenancePage.downBtn);
            Utility_Functions.timeWait(2);
            find1=Utility_Functions.xIsDisplayed(driver,By.xpath("//div[text()='" + text + "']"));
        }
        int size1 = driver.findElements(By.xpath("//div[text()='" + text + "']/preceding::input")).size();
        WebElement el1 = driver.findElements(By.xpath("//div[text()='" + text + "']/preceding::input")).get(size1 - 1);
        sendKeys(el1, ""+option+"", "Enter "+option+" in option field to delete group Name: " + text + "");
        for(int i=1;i<=cnt;i++){
            click(CustomerGroupMaintenancePage.upBtn);
        }
        return el1;
    }

    /**
     * This method to Add multiple Group name and delete Multiple Groups
     *
     */
    public void deleteMultipleGroups() {
        String groupName1 = addGroupAndCancel();
        String groupName2 = addGroupAndCancel();
        findGroupName(groupName1,4);
        findGroupName(groupName2,4);
        Utility_Functions.actionKey(Keys.ENTER, driver);
        clickSubmit();
        clickCancelBtn();
        Boolean actGN1=Utility_Functions.xIsDisplayed(driver,By.xpath("//div[text()='" + groupName1 + "']"));
        Boolean actGN2=Utility_Functions.xIsDisplayed(driver,By.xpath("//div[text()='" + groupName2 + "']"));
        Utility_Functions.xAssertEquals(report,""+actGN1+"","false","GroupName: "+groupName1+" is deleted");
        Utility_Functions.xAssertEquals(report,""+actGN2+"","false","GroupName: "+groupName2+" is deleted");
    }

    /**
     * This method to Add Group name and click Add group customer
     *
     */
    public void addGroupName() {
        addGroup();
    }

    /**
     * This method to select customer by entering "1" into option field
     *
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
        Utility_Functions.xAssertEquals(report, expColor, actColor1, "Selected customer/customers records should turn into red color: ");
    }

    /**
     * This method to select one customer by entering "1" into option field
     *
     */
    public String selectOneCustomers() {
        sendKeys(CustomerGroupMaintenancePage.firstCust, "1");
        String customer=driver.findElement(CustomerGroupMaintenancePage.firstCustName).getText();
        Utility_Functions.actionKey(Keys.ENTER, driver);
        String expColor = "rgba(204, 0, 51, 1)";
        String actColor1 = driver.findElement(CustomerGroupMaintenancePage.firCustNo).getCssValue("color");
        Utility_Functions.xAssertEquals(report, expColor, actColor1, "Selected customer/customers records should turn into red color: ");
        return customer;
    }

    /**
     * This method to deselect customer by entering "4" into option field
     *
     */
    public void deSelectCustomers(By el) {
        sendKeys(el, "4");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        String expBlackClr = "rgba(0, 0, 0, 1)";
        String actColor = driver.findElement(CustomerGroupMaintenancePage.thrdCust).getCssValue("color");
        Utility_Functions.xAssertEquals(report, expBlackClr, actColor, "Selected Customer Record Should be Deselected");
    }

    /**
     * This method to shows selected customers
     *
     */
    public void sortSelectedCust() {
        click(CustomerGroupMaintenancePage.selectDeSel, "Click F5=Selected to show selected Customer");
    }

    /**
     * This method to shows All customers
     *
     */
    public void showAllCustomer() {
        click(CustomerGroupMaintenancePage.selectDeSel, "Click F5=All to show all customer");
    }

    /**
     * This method to sort the customers w.r.t column name
     *
     */
    public void sortByCustomerColumn() {
        String sortValue = driver.findElement(CustomerGroupMaintenancePage.sort).getAttribute("value");
        String value[] = sortValue.split("=");
        click(CustomerGroupMaintenancePage.sort, "Click " + sortValue + " and Record should be Sorted As per " + value[1] + " in Ascending  order");
    }

    /**
     * This method to click submit/Process
     *
     */
    public void clickSubmit() {
        String res = driver.findElement(CustomerGroupMaintenancePage.submitBtn).getAttribute("value");
        click(CustomerGroupMaintenancePage.submitBtn, "Click " + res + "");
    }

    /**
     * This method to add customer confirmation message
     *
     */
    public void addCustCnfrmMessage() {
        String expCustSucMessage = "Successfully added Customer. Add another or F12 Return.";
        String actCustSucMessage = driver.findElement(CustomerGroupMaintenancePage.GroupNameMessage).getText();
        Utility_Functions.xAssertEquals(report, expCustSucMessage, actCustSucMessage, "add customer confirmation message: ");
    }

    /**
     * This method to add Customer to the group
     *
     */
    public void addCustomerToGroup() {
        clickAddGroupCustomer();
        selectCustomers();
        deSelectCustomers(CustomerGroupMaintenancePage.thrdCust);
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
     *
     */
    public void clickCancelBtn() {
        click(CustomerGroupMaintenancePage.cancelBtn, "Click F12=Cancel");
    }

    /**
     * This method to Navigate to Customer Group Details and verify Selected customer under Group Name
     *
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
     * This method to search Group Name
     *
     */
    public void searchGroupName() {
        String groupName = Utility_Functions.xGetJsonAsString("GroupName");
        sendKeys(CustomerGroupMaintenancePage.searchField, groupName);
        Utility_Functions.actionKey(Keys.ENTER, driver);
        String actGroupName = driver.findElement(CustomerGroupMaintenancePage.searchGroup).getText();
        Utility_Functions.xAssertEquals(report, groupName, actGroupName, "Verify GroupName is added: ");
    }

    /**
     * This method to Navigate to Customer Group Browse and verify Group Name
     *
     */
    public void verifyGroupName() {
        clickCancelBtn();
        searchGroupName();
    }

    /**
     * This method to sort Customer group
     *
     */
    public void sortCustomerGroup() {
        sortByCustomerColumn();
        sortByCustomerColumn();
        sortByCustomerColumn();
    }

    /**
     * This method to delete group Name by entering "4" into option field
     *
     */
    public void deleteGroupName() {
        tillDisplayed();
        String deletedRec = Utility_Functions.xGetJsonAsString("deletedGroup");
        sendKeys(CustomerGroupMaintenancePage.searchField, deletedRec);
        Utility_Functions.actionKey(Keys.ENTER, driver);
        Boolean actual=Utility_Functions.xIsDisplayed(driver,By.xpath("//div[text()='" + deletedRec + "']"));
        Utility_Functions.xAssertEquals(report,""+actual+"","false","GroupName: "+actual+" is deleted");
    }

    /**
     * This method to run till record is deleted as Group Number being used in SPA cannot delete
     *
     */
    public void tillDisplayed() {
        addGroupAndCancel();
        List<WebElement> list = driver.findElements(CustomerGroupMaintenancePage.groupOptField);
        int size = list.size();
        WebElement el = null;
        for (int i = 1; i <= size; i++) {
            WebElement elem = driver.findElements(CustomerGroupMaintenancePage.groupOptField).get(i);
            sendKeys(elem, "4", "Enter 4 in option field to delete group Name");
            el = driver.findElements(CustomerGroupMaintenancePage.getGroupName).get(count);
            count += 3;
            storeGroupNumberName("deletedGroup", el.getText());
            Utility_Functions.timeWait(2);
            Utility_Functions.actionKey(Keys.ENTER, driver);
            Utility_Functions.timeWait(3);
            clickSubmit();
            String res = getText(CustomerGroupMaintenancePage.GroupNameMessage);
            if (res.equals("Group Number being used in SPA cannot delete.")) {
                Utility_Functions.xAssertEquals(report, res, "Group Number being used in SPA cannot delete.", "");
                clickCancelBtn();
            } else {
                clickCancelBtn();
                break;
            }
        }
    }

    /**
     * This method Exit From Customer Group Maintenance
     *
     */
    public void exitFromCustomerGroupMaintenance() {
        commonObj.exitFromCustomerGroupMaintenance();
    }
}
