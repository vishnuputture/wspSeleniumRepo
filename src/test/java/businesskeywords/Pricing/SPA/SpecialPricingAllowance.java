package businesskeywords.Pricing.SPA;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;

import com.winSupply.framework.Util;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;

import java.security.Key;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import pages.pricing.AddSpecialPricingPage;
import pages.pricing.SpecialPricePage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

import java.text.SimpleDateFormat;

public class SpecialPricingAllowance extends ReusableLib {
    int count = 10;
    int lastNo = 6;
    private FrameworkDriver ownDriver;

    public SpecialPricingAllowance(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver=helper.getGSDriver();
    }

    CommonActions commonObj;

    public void validateHideExpiredContract() {
        click(SpecialPriceAllowancePage.btnHideShowExpired, "Clicking on hide expired button");
        List<WebElement> expiredCont = ownDriver.findElements(SpecialPriceAllowancePage.lblExpired);
        if (expiredCont.size() > 0) {
            report.updateTestLog("Validate expired contracts", "No expired contracts should be displayed", Status.FAIL);
        } else {
            report.updateTestLog("Validate expired contracts", "No expired contracts should be displayed", Status.PASS);
        }
    }

    public void validateShowExpiredContract() {
        click(SpecialPriceAllowancePage.btnHideShowExpired, "Clicking on show expired button");
        List<WebElement> expiredCont = ownDriver.findElements(SpecialPriceAllowancePage.lblExpired);
        if (expiredCont.size() > 0) {
            report.updateTestLog("Validate expired contracts", "Expired contracts should be displayed", Status.PASS);
        } else {
            report.updateTestLog("Validate expired contracts", "Expired contracts should be displayed", Status.FAIL);
        }
    }

    public void navAddContractToSPA() {
        click(SpecialPriceAllowancePage.btnReturn);
    }

    public void searchContract() {
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxSearchCon, jsonData.getData("InvalidSearchtext"), "Searching with invalid contract name");
        if (Utility_Functions.xWaitForElementPresent(ownDriver, SpecialPriceAllowancePage.lblNoRecords, 5)) {
            String searchMessage = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.lblNoRecords);
            System.out.println("Text: " + searchMessage);
            Utility_Functions.xAssertEquals(report, "* NO RECORDS TO DISPLAY *", searchMessage.trim(), "Validating message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + SpecialPriceAllowancePage.lblNoRecords);
        }

        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxSearchCon, jsonData.getData("Contract_Name"), "Searching with valid contract name");

        String contractName = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.lblContractName);
        Utility_Functions.xAssertEquals(report, jsonData.getData("Contract_Name").toUpperCase(), contractName.trim(), "Validating search result");
    }

    public void addActiveContract() {
        click(SpecialPriceAllowancePage.btnAddContract, "Clicking on add contract button");
        sendKeys(SpecialPriceAllowancePage.txtBoxContractname, jsonData.getData("Contract_Name"), "Entering contract name");
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        String startDate = formatter.format(dt);

        sendKeys(SpecialPriceAllowancePage.txtBoxStartDate, startDate, "Entering contract start date");
        c.setTime(dt);
        c.add(Calendar.DATE, 5);
        dt = c.getTime();
        //SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        String endDate = formatter.format(dt);
        sendKeys(SpecialPriceAllowancePage.txtBoxEndDate, endDate, "Entering contract start date");
        sendKeys(SpecialPriceAllowancePage.txtBoxVendorNo, jsonData.getData("VendorNo"), "Entering vendor number");
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxGroupNo, jsonData.getData("GroupNo"), "Entering group number");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        if (Utility_Functions.xWaitForElementPresent(ownDriver, SpecialPriceAllowancePage.lblSuccess, 5)) {
            String successMessage = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.lblSuccess);
            System.out.println("Text: " + successMessage);
            Utility_Functions.xAssertEquals(report, "Successfully added Contract. Add another or F12 Return.", successMessage.trim(), "Validating success message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + SpecialPriceAllowancePage.lblSuccess);
        }
    }

    public void addExpiredContract() {
        //click(SpecialPriceAllowancePage.btnAddContract,"Clicking on add contract button");
        sendKeys(SpecialPriceAllowancePage.txtBoxContractname, jsonData.getData("Expired_Contract_Name"), "Entering contract name");
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -5);
        c.add(Calendar.YEAR, -3);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        String startDate = formatter.format(dt);

        sendKeys(SpecialPriceAllowancePage.txtBoxStartDate, startDate, "Entering contract start date");
        c.setTime(dt);
        c.add(Calendar.DATE, -1);
        c.add(Calendar.YEAR, 1);
        dt = c.getTime();
        //SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        String endDate = formatter.format(dt);
        sendKeys(SpecialPriceAllowancePage.txtBoxEndDate, endDate, "Entering contract start date");
        sendKeys(SpecialPriceAllowancePage.txtBoxVendorNo, jsonData.getData("VendorNo"), "Entering vendor number");
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxGroupNo, jsonData.getData("GroupNo"), "Entering group number");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        if (Utility_Functions.xWaitForElementPresent(ownDriver, SpecialPriceAllowancePage.lblSuccess, 5)) {
            String successMessage = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.lblSuccess);
            System.out.println("Text: " + successMessage);
            Utility_Functions.xAssertEquals(report, "Successfully added Contract. Add another or F12 Return.", successMessage.trim(), "Validating success message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + SpecialPriceAllowancePage.lblSuccess);
        }
    }

    public void contractDeletionValidation() {
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxSearchCon, jsonData.getData("Contract_Name"), "Searching with active contract name");
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxOption, "4", "Deleting active contract");
        if (Utility_Functions.xWaitForElementPresent(ownDriver, SpecialPriceAllowancePage.lblInvalidDeletionMessage, 5)) {
            String deleteMessage = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.lblInvalidDeletionMessage);
            System.out.println("Text: " + deleteMessage);
            Utility_Functions.xAssertEquals(report, "Contract \"End Date\" is less than 1 year old - Delete not allowed.", deleteMessage.trim(), "Validating deletion message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + SpecialPriceAllowancePage.lblInvalidDeletionMessage);

        }
        clearText(SpecialPriceAllowancePage.txtBoxOption);
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxSearchCon, jsonData.getData("Expired_Contract_Name"), "Searching with expired contract name");
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxOption, "4", "Deleting expired contract");
        click(SpecialPriceAllowancePage.btnDelete, "Confirming delete");

        if (Utility_Functions.xWaitForElementPresent(ownDriver, SpecialPriceAllowancePage.lblSuccess, 5)) {
            String deleteMessage = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.lblSuccess);
            System.out.println("Text: " + deleteMessage);
            Utility_Functions.xAssertEquals(report, "Contracts have been deleted.", deleteMessage.trim(), "Validating success message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + SpecialPriceAllowancePage.lblSuccess);
        }
    }

    public ArrayList<String> getColumnValue(String columnNo) {
        int cnt = 0;
        if (count > 10) {
            count = 10;
        }
        ArrayList<String> al = new ArrayList<String>();
        int size = ownDriver.findElements(By.xpath("//div[starts-with(@id,'D_') and (@class='A20')]")).size() - 1;
        Boolean bl = Utility_Functions.xIsDisplayed(ownDriver, SpecialPriceAllowancePage.bottom);
        while (bl != true) {
            bl = Utility_Functions.xIsDisplayed(ownDriver, SpecialPriceAllowancePage.bottom);
            for (int i = 1; i <= size; i++) {
                try {
                    al.add(ownDriver.findElement(By.xpath("(//div[starts-with(@id,'D_" + count + "') and (@class='A20')])[" + columnNo + "]")).getText());
                    count++;
                } catch (Exception e) {
                    break;
                }
            }
            click(SpecialPriceAllowancePage.downButton);
            System.out.println(bl);
            cnt++;
            System.out.println("cnt: " + cnt);
            count = 10;
        }
        return al;
    }

    /**
     * This method to sort the contract record by column number
     */
    public void sortByColumn(String columnNo) {
        int size = ownDriver.findElements(By.xpath("//div[starts-with(@id,'D_') and (@class='A20')]")).size() - 1;
        String sortValue = ownDriver.findElement(CustomerGroupMaintenancePage.sort).getAttribute("value");
        String value[] = sortValue.split("=");
        ArrayList<String> al = getColumnValue(columnNo);
        System.out.println("Before sort: " + al);
        Collections.sort(al);
        //al.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        click(CustomerGroupMaintenancePage.sort, "Click " + sortValue + "");
        ArrayList<String> al1 = getColumnValue(columnNo);
        System.out.println("After Collection sort: " + al);
        System.out.println("Wise sort: " + al1);
        boolean boolval = al.equals(al1);
        //Utility_Functions.xAssertEquals(report, boolval, "true", "Record Sorted As per " + value[1] + " in Ascending  order");
    }

    /**
     * This method to sort the contract Name/Start Date/End Date/Vendor
     */
    public void sortContractRecord() {
        sortByColumn("2");
        sortByColumn("3");
        sortByColumn("4");
        sortByColumn("1");
    }

    /**
     * This method to show the contract/Show Date
     */
    public void showContractOrShowDate() {
        String sortValue = ownDriver.findElement(SpecialPriceAllowancePage.showContractDate).getAttribute("value");
        String value[] = sortValue.split("=");
        click(SpecialPriceAllowancePage.showContractDate, "Click " + sortValue + "");
        String colmVal = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.contractStartDate);
        Utility_Functions.xAssertEquals(report, colmVal, "Contract #", "Start date and End date Column should be Replaced with " + value[1] + " Column");
        String sortVal = ownDriver.findElement(SpecialPriceAllowancePage.showContractDate).getAttribute("value");
        click(SpecialPriceAllowancePage.showContractDate, "Click " + sortVal + ", " + value[1] + " Column should be Replaced again with Start date and End date Columns ");
        String startDate = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.contractStartDate);
        String endDate = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.endDate);
        Utility_Functions.xAssertEquals(report, startDate, "Start Date", "Column:");
        Utility_Functions.xAssertEquals(report, endDate, "End Date", "Column:");
    }

    /**
     * This method to add random Active Contract Name
     * Returns Added Contract Name
     */
    public String addRanActiveContract() {
        click(SpecialPriceAllowancePage.btnAddContract, "Clicking on add contract button");
        verifySpaChangeContractTitle("SPA - Add Contract");
        String startDate = getDate(Calendar.DATE, -1);
        String endDate = getDate(Calendar.DATE, 2);
        String name = Utility_Functions.xGenerateAlphaNumericString();
        sendKeys(SpecialPriceAllowancePage.txtBoxContractname, name, "contract name");
        sendKeys(SpecialPriceAllowancePage.txtBoxStartDate, startDate, "Start Date");
        sendKeys(SpecialPriceAllowancePage.txtBoxEndDate, endDate, "End Date");
        sendKeys(SpecialPriceAllowancePage.txtBoxVendorNo, jsonData.getData("VendorNo"), "vendor number");
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxGroupNo, jsonData.getData("GroupNo"), "group number");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        if (Utility_Functions.xWaitForElementPresent(ownDriver, SpecialPriceAllowancePage.lblSuccess, 5)) {
            String successMessage = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.lblSuccess);
            System.out.println("Text: " + successMessage);
            Utility_Functions.xAssertEquals(report, "Successfully added Contract. Add another or F12 Return.", successMessage.trim(), "Validating success message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + SpecialPriceAllowancePage.lblSuccess);
        }
        return name;
    }

    /**
     * This method to get Date MM/DD/YYY Format
     */
    public String getDate(int cal, int amount) {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(cal, amount);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        return formatter.format(dt);
    }

    /**
     * This method to enter value into Option text box
     */
    public List<String> optTextBox(String option, String message) {
        List<String> firstRowValues = Utility_Functions.xGetTextVisibleListString(ownDriver, ownDriver.findElements(SpecialPriceAllowancePage.firstRow));
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxOption, option, message);
        if (Utility_Functions.xIsDisplayed(ownDriver, SpecialPriceAllowancePage.succMesg)) {
            try {
                sendKeysAndEnter(SpecialPriceAllowancePage.optInd, option, message);
            } catch (Exception e) {
                System.out.println("Element Not exist");
            }
        }
        return firstRowValues;
    }

    /**
     * This method to verify SPA - Change Contract Page Title
     */
    public void verifySpaChangeContractTitle(String titleName) {
        String title = getText(SpecialPriceAllowancePage.spaChangeContactPageTitle);
        String tr = title.trim();
        Utility_Functions.xAssertEquals(report, tr, titleName, "Page Title: ");
    }

    /**
     * This method to click F6=Add More Groups
     */
    public void clickF6AddMoreGroups() {
        click(SpecialPriceAllowancePage.btnAddContract, "Click F6=Add More Groups");
        verifySpaChangeContractTitle("Customer Group Browse ()");
    }

    /**
     * This method to click F2=Assigned Groups
     */
    public void clickAssignedGroups() {
        click(SpecialPriceAllowancePage.assignedGroups, "click F2=Assigned Groups");
        verifySpaChangeContractTitle("Customer Group Browse ()");
    }

    /**
     * This method to sort the Group Name/Group No/Date Added
     */
    public void sortGroupRecColumn() {
        sortByColumn("2");
        sortByColumn("3");
        sortByColumn("1");
    }

    /**
     * This method to Edit Contract/Job Name
     */
    public void editContractRec() {
        optTextBox("2", "Enter 2 to Edit Contract");
        verifySpaChangeContractTitle("SPA - Change Contract");
        String startDate = getDate(Calendar.DATE, -1);
        String endDate = getDate(Calendar.DATE, 2);
        String name = Utility_Functions.xGenerateAlphaNumericString();
        sendKeys(SpecialPriceAllowancePage.txtBoxContractname, name, "Edit contract name");
        sendKeys(SpecialPriceAllowancePage.txtBoxStartDate, startDate, "Edit Start Date");
        sendKeys(SpecialPriceAllowancePage.txtBoxEndDate, endDate, "Edit End Date");
        sendKeys(SpecialPriceAllowancePage.txtBoxVendorNo, jsonData.getData("VendorNo"), "Edit vendor number");
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxGroupNo, jsonData.getData("GroupNo"), "Edit group number");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        if (Utility_Functions.xWaitForElementPresent(ownDriver, SpecialPriceAllowancePage.lblSuccess, 5)) {
            String successMessage = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.lblSuccess);
            System.out.println("Text: " + successMessage);
            Utility_Functions.xAssertEquals(report, "Contract successfully updated.", successMessage.trim(), "Validating success message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + SpecialPriceAllowancePage.lblSuccess);
        }
        click(SpecialPriceAllowancePage.btnReturn, "Click F12=Return");
        sendKeysAndEnter(SpecialPriceAllowancePage.inputSearchBox, name, "Search for edited Contract Name");
        optTextBox("5", "Enter 5 to Display Edited Contract/Job Name Record");
        Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.contractJobName).trim(), name, "Contract Name after edit:");
        if (startDate.charAt(0) == '0') {
            StringBuilder sb = new StringBuilder(startDate);
            String startDate1 = sb.deleteCharAt(0).toString();
            startDate = startDate1;
        }
        if (endDate.charAt(0) == '0') {
            StringBuilder sb = new StringBuilder(endDate);
            String endDate1 = sb.deleteCharAt(0).toString();
            endDate = endDate1;
        }
        Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.startDate).trim(), startDate, "Start Date after edit:");
        Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.endDat).trim(), endDate, "End Date after edit:");
        Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.vendNo).trim(), jsonData.getData("VendorNo"), "Vendor No after edit:");
        Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.groupNo).trim(), jsonData.getData("GroupNo"), "Group No after edit:");
        click(SpecialPriceAllowancePage.btnReturn, "Click F12=Return");
    }

    /**
     * This method to Edit and Add more groups to Contract/Job Name
     */
    public void editAndAddMoreGroups() {
        editContractRec();
        optTextBox("2", "Enter 2 to Edit Contract");
        verifySpaChangeContractTitle("SPA - Change Contract");
        clickF6AddMoreGroups();
        List<String> val = optTextBox("1", "Select Group Name");
        String erMessage = "Group Number selected already assigned to this Contract.";
        String exName = getText(SpecialPriceAllowancePage.exGroupName);
        if (Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.lblSuccess).equals(erMessage)) {
            String message = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.lblSuccess);
            Utility_Functions.xAssertEquals(report, message, erMessage, "Message: ");
        } else {
            String exNo = ownDriver.findElement(SpecialPriceAllowancePage.exGroupNo).getAttribute("value");
            Utility_Functions.xAssertEquals(report, exName, val.get(0), "Group Name: ");
            Utility_Functions.xAssertEquals(report, exNo, val.get(1), "Group No: ");
        }
    }

    /**
     * This method to Display Customers
     */
    public void displayCustomer() {
        List<String> val = optTextBox("5", "Enter 5 to Display Customer");
        String grpName = ownDriver.findElement(SpecialPriceAllowancePage.grpName).getAttribute("value");
        String groupName = grpName.trim();
        String grpNo = getText(SpecialPriceAllowancePage.grpNo);
        String groupNo = grpNo.trim();
        Utility_Functions.xAssertEquals(report, groupName, val.get(0), "Group Name: ");
        Utility_Functions.xAssertEquals(report, groupNo, val.get(1).trim(), "Group No: ");
    }

    /**
     * This method to delete Customers
     */
    public void deleteCustomer() {
        List<String> val = optTextBox("4", "Enter 4 to delete Customer");
        String grpName = getText(SpecialPriceAllowancePage.delGrpName);
        String groupName = grpName.trim();
        String grpNo = getText(SpecialPriceAllowancePage.delGrpNo);
        String groupNo = grpNo.trim();
        Utility_Functions.xAssertEquals(report, groupName, val.get(0), "Group Name: ");
        Utility_Functions.xAssertEquals(report, groupNo, val.get(1).trim(), "Group No: ");
        click(SpecialPriceAllowancePage.btnDelete, "CLick F9=Delete Record");
        //sendKeysAndEnter(SpecialPriceAllowancePage.delSpaRec, "Y", "Delete Special Price Record");
        verifySpaChangeContractTitle("Customer Group Browse ()");
        Boolean bl = Utility_Functions.xIsDisplayed(ownDriver, By.xpath("//div[text()='" + grpName + "']"));
        Utility_Functions.xAssertEquals(report, "" + bl + "", "" + false + "", "Customer deleted from the group:");
    }

    /**
     * This method to See Assigned Groups. Delete and Display Customers
     */
    public void displayDeleteAssignedGroups() {
        clickAssignedGroups();
        sortGroupRecColumn();
        displayCustomer();
        click(SpecialPriceAllowancePage.btnReturn, "Click F12=Return");
        deleteCustomer();
        click(SpecialPriceAllowancePage.btnReturn, "Click F12=Return");
        click(SpecialPriceAllowancePage.btnReturn, "Click F12=Return");
    }

    /**
     * This method to Copy Contract/Job Details
     */
    public void copyContractDetail() {
        optTextBox("3", "Enter 3 to Copy Contract");
        verifySpaChangeContractTitle("SPA - Copy Contract");
        String name = Utility_Functions.xGenerateAlphaNumericString();
        sendKeys(SpecialPriceAllowancePage.txtBoxContractname, name, "Edit contract name");
        String exStartDate = ownDriver.findElement(SpecialPriceAllowancePage.txtBoxStartDate).getAttribute("value").trim();
        String exEndDate = ownDriver.findElement(SpecialPriceAllowancePage.txtBoxEndDate).getAttribute("value").trim();
        String exVendorNo = ownDriver.findElement(SpecialPriceAllowancePage.txtBoxVendorNo).getAttribute("value").trim();
        String exGroupNo = ownDriver.findElement(SpecialPriceAllowancePage.txtBoxGroupNo).getAttribute("value").trim();
        String exGroupName = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.exGroupName).trim();
        String exVendorName = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.vendorName).trim();
        String exSpecPricing = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.specialPricing).trim();
        String status = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.status).trim();
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        String successMessage = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.lblSuccess);
        System.out.println("Text: " + successMessage);
        Utility_Functions.xAssertEquals(report, "Successfully added Contract. Add another or F12 Return.", successMessage.trim(), "Validating success message");
        click(SpecialPriceAllowancePage.btnReturn, "Click F12=Return");
        sendKeysAndEnter(SpecialPriceAllowancePage.inputSearchBox, name, "Search for Copied Contract Name");
        optTextBox("5", "Enter 5 to Display Edited Contract/Job Name Record");
        String actContractName = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.contractJobName).trim();
        String actStartDate = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.startDate).trim();
        String actEndDate = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.endDat).trim();
        String actStatus = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.status).trim();
        String actVendorNo = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.vendNo).trim();
        String actGroupName = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.exGroupName).trim();
        String actGroupNo = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.groupNo).trim();
        String actVendorName = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.vendorName).trim();
        String actSpecPricing = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.specialPricing).trim();
        Utility_Functions.xAssertEquals(report, name, actContractName, "Contract Name:");
        Utility_Functions.xAssertEquals(report, actStatus, status, "Status:");
        Utility_Functions.xAssertEquals(report, actStartDate, exStartDate, "Start Date:");
        Utility_Functions.xAssertEquals(report, actEndDate, exEndDate, "End Date:");
        Utility_Functions.xAssertEquals(report, actVendorNo, exVendorNo, "Vendor No:");
        Utility_Functions.xAssertEquals(report, actVendorName, exVendorName, "Vendor Name:");
        Utility_Functions.xAssertEquals(report, actGroupName, exGroupName, "Group Name:");
        Utility_Functions.xAssertEquals(report, actGroupNo, exGroupNo, "Group No:");
        Utility_Functions.xAssertEquals(report, actSpecPricing, exSpecPricing, "Special Pricing:");
        click(SpecialPriceAllowancePage.btnReturn, "CLick F12=Return");
    }

    /**
     * This method to click F9=Load S/P and Verify S/P column
     */
    public void verifySP(String spStatus, String message) {
        String successMessage;
        click(SpecialPriceAllowancePage.btnDelete, "F9=Load Special Price");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,SpecialPriceAllowancePage.indexes,"Wait for page to be loaded");
        if (Utility_Functions.xIsDisplayed(ownDriver, SpecialPriceAllowancePage.lblSuccess)) {
            successMessage = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.lblSuccess);
        } else {
            successMessage = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.loadedSuccess);
        }
        System.out.println("Text: " + successMessage);
        //Utility_Functions.xAssertEquals(report, message, successMessage.trim(), "Validating success message");
        click(SpecialPriceAllowancePage.btnReturn, "CLick F12=Return");
        //Utility_Functions.xAssertEquals(report, spStatus, Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.SP).trim(), "Validating S/P");
    }

    /**
     * This method to Load Special Pricing
     */
    public void loadSpecialPricing() {
        String name = addRanActiveContract();
        click(SpecialPriceAllowancePage.btnReturn);
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxSearchCon, name, "Search Contract Name");
        optTextBox("7", "Enter 7 to Load Special Pricing");
        verifySpaChangeContractTitle("Load Special Pricing");
        String expVendorNo = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.vendNo).trim();
        String expContractName = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.startDate).trim();
        Utility_Functions.xAssertEquals(report, expContractName, name, "Contract Name:");
        Utility_Functions.xAssertEquals(report, jsonData.getData("VendorNo"), expVendorNo, "Vendor No:");
        verifySP("N", "Contract not loaded successfully. F12 to Return.");
        assignMfPdCode();
        optTextBox("7", "Enter 7 to Load Special Pricing");
        verifySpaChangeContractTitle("Load Special Pricing");
        verifySP("Y", "Load Special Pricing completed. F12 to Return.");
    }

    /**
     * This method to Update Cost
     */
    public void updateCost() {
        optTextBox("9", "Enter 9 to Update Cost");
        verifySpaChangeContractTitle("SPA - Contract Browse ()");
        String title = getText(SpecialPriceAllowancePage.codeFileLookUp);
        String tr = title.trim();
        Utility_Functions.xAssertEquals(report, tr, "Codes File Lookup / Selection", "Pop-Up Title: ");
        sendKeysAndEnter(SpecialPriceAllowancePage.optCostUpdate, "1", "Select Last Cost");
        String succ = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.codeUpdateSuccess);
        Utility_Functions.xAssertEquals(report, succ, "Cost has been successfully Updated.", "Update cost Successful message");
    }

    /**
     * This method to Validate F5=Left/Rebate and F5=Right/Selling
     * In "Work With SPA Items - Rebate Details"
     */
    public void verifyColRebateSell() {
        Utility_Functions.xIsElementDisplayed(report, ownDriver.findElement(SpecialPriceAllowancePage.rebateCostCol), "Rebate Cost column is displayed");
        Utility_Functions.xIsElementDisplayed(report, ownDriver.findElement(SpecialPriceAllowancePage.rebateAmtCol), "Rebate Amount column is displayed");
        Utility_Functions.xIsElementDisplayed(report, ownDriver.findElement(SpecialPriceAllowancePage.rebateMulTp), "rebate Mul. Type column is displayed");

        click(SpecialPriceAllowancePage.debateAm, "Click F5=Right/Selling");
        Utility_Functions.xIsElementDisplayed(report, ownDriver.findElement(SpecialPriceAllowancePage.rebateCostCol), "Sell Price:");
        Utility_Functions.xIsElementDisplayed(report, ownDriver.findElement(SpecialPriceAllowancePage.selMult), "Selling Multiplier Type :");
    }

    /**
     * This method to display Contract Details
     */
    public void displayContractDetail() {
        optTextBox("5", "Enter 5 to Display Contract/Job Name Record");
        verifySpaChangeContractTitle("SPA - Contract Browse");
        String contName = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.contractJobName).trim();
        String vendNo = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.vendNo).trim();
        String vendName = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.vendorName).trim();
        String groupName = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.exGroupName).trim();
        String groupNo = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.groupNo).trim();

        click(SpecialPriceAllowancePage.btnHideShowExpired, "Click F7=MF/PD COde");
        verifySpaChangeContractTitle("Work with SPA MF/PD - Details");
        Utility_Functions.xAssertEquals(report, contName, Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.startDate).trim(), "Start Date");
        Utility_Functions.xAssertEquals(report, vendNo, Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.vendNo).trim(), "Vendor No:");
        Utility_Functions.xAssertEquals(report, vendName, Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.venNameMfPd).trim(), "Vendor Name:");
        if (Utility_Functions.xIsDisplayed(ownDriver, SpecialPriceAllowancePage.noRecordsMsg)) {
            Utility_Functions.xAssertEquals(report, "* NO RECORDS TO DISPLAY *", Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.noRecordsMsg).trim(), "Vendor Name:");
        } else {

        }

        click(SpecialPriceAllowancePage.individualItem, "CLick F8=Individual Item");
        String title = getText(SpecialPriceAllowancePage.appHeader).trim();
        Utility_Functions.xAssertEquals(report, title, "Work With SPA Items - Rebate Details", "Page Title: ");
        verifyColRebateSell();
        click(SpecialPriceAllowancePage.individualItem, "Click F8=Show Desc");
        Utility_Functions.xIsElementDisplayed(report, ownDriver.findElement(SpecialPriceAllowancePage.showDescn), "Item Description is displayed");
        click(SpecialPriceAllowancePage.individualItem, "Click F8=Hide Desc");
        Boolean bl = Utility_Functions.xIsDisplayed(ownDriver, SpecialPriceAllowancePage.showDescn);
        Utility_Functions.xAssertEquals(report, "" + bl + "", "false", "Hide Item Description:");

        Utility_Functions.xAssertEquals(report, contName, Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.contractJobName).trim(), "Contract Name:");
        Utility_Functions.xAssertEquals(report, vendNo, Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.startDate).trim(), "Vendor No:");
        Utility_Functions.xAssertEquals(report, vendName, Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.vendNo).trim(), "Vendor Name:");

        click(SpecialPriceAllowancePage.assignedGroups, "CLick F2=Assigned Groups");
        verifySpaChangeContractTitle("Customer Group Browse ()");
        Utility_Functions.xAssertEquals(report, groupName, Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.gName).trim(), "Group Name:");
        Utility_Functions.xAssertEquals(report, groupNo, Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.grpNum).trim(), "Group No:");

        click(SpecialPriceAllowancePage.btnReturn, "F12=Return");
        Utility_Functions.xAssertEquals(report, title, "Work With SPA Items - Rebate Details", "Page Title: ");
        click(SpecialPriceAllowancePage.btnReturn, "F12=Return");
        verifySpaChangeContractTitle("Work with SPA MF/PD - Details");
        click(SpecialPriceAllowancePage.btnReturn, "F12=Return");
        verifySpaChangeContractTitle("SPA - Contract Browse");
        click(SpecialPriceAllowancePage.btnReturn, "F12=Return");
    }

    /**
     * This method to To delete first Record from the table
     */
    public void deleteRec(String deleteRec) {
        Utility_Functions.timeWait(2);
        Utility_Functions.waitForElementVisible(ownDriver, SpecialPriceAllowancePage.btnDelete, 10);
        click(SpecialPriceAllowancePage.btnDelete, "CLick F9=Delete Record");
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        //sendKeysAndEnter(SpecialPriceAllowancePage.delSpaRec, "Y", "Delete Special Price Record");
        Boolean bl = Utility_Functions.xIsDisplayed(ownDriver, By.xpath("//div[text()='" + deleteRec + "']"));
        Utility_Functions.xAssertEquals(report, "" + bl + "", "" + false + "", "Item deleted:");
    }

    /**
     * This method to To Assigning MF/PD Code
     */
    public void assignMfPd() {
        String mFCode = null;
        String pDCOde = null;
        click(SpecialPriceAllowancePage.btnHideShowExpired, "Click F7=MF/PD Codes");
        if (Utility_Functions.xIsDisplayed(ownDriver, SpecialPriceAllowancePage.xCode)) {
            System.out.println("TRY");
            verifySpaChangeContractTitle("Work with SPA MF/PD - Details");
            mFCode = getText(SpecialPriceAllowancePage.xCode);
            pDCOde = getText(SpecialPriceAllowancePage.olCode);
            sendKeysAndEnter(SpecialPriceAllowancePage.optMfPd, "4", "Enter 4 to delete MF/PD");
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
            deleteRec(mFCode);
            click(SpecialPriceAllowancePage.btnAddContract, "Click F6=Add");
        } else {
            System.out.println("Catch");
            verifySpaChangeContractTitle("SPA - Add MF/PD");
        }
        if (mFCode == null) {
            mFCode = "XX";
            pDCOde = "01";
        }
        sendKeys(SpecialPriceAllowancePage.multiplierType, "c", "Multiplier type C");
        sendKeys(SpecialPriceAllowancePage.rebateDisc, ".2", "Enter Rebate Discount");
        sendKeys(SpecialPriceAllowancePage.mfCOde, "" + mFCode + "");
        sendKeysAndEnter(SpecialPriceAllowancePage.pdCOde, "" + pDCOde + "", "Enter MF/PD code");
        sendKeys(SpecialPriceAllowancePage.sellMultType, "C", "Enter C into Selling Multiplier Type");
        sendKeys(SpecialPriceAllowancePage.sellMult, ".3", "Enter Selling Multiplier");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        String mes = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.lblSuccess).trim();
        Utility_Functions.xAssertEquals(report, mes, "Successfully added Contract. Add another or F12 Return.", "Successful Message:");
        click(SpecialPriceAllowancePage.btnReturn, "Click F12=Return");
        if (Utility_Functions.xIsDisplayed(ownDriver, SpecialPriceAllowancePage.xCode)) {
            verifySpaChangeContractTitle("Work with SPA MF/PD - Details");
            click(SpecialPriceAllowancePage.btnReturn, "Click F12=Return");
        }
        verifySpaChangeContractTitle("SPA - Change Contract");
        click(SpecialPriceAllowancePage.btnHideShowExpired, "Click F7=MF/PD Codes");
        Boolean bl = Utility_Functions.xIsDisplayed(ownDriver, SpecialPriceAllowancePage.xCode);
        Utility_Functions.xAssertEquals(report, "" + bl + "", "true", "MF/PD Code assigned to Contract:");
    }

    /**
     * This method to To Assigning MF/PD Code to the Contracts
     */
    public void assignMfPdCode() {
        optTextBox("2", "Enter 2 to Edit Contract");
        verifySpaChangeContractTitle("SPA - Change Contract");
        assignMfPd();
        click(SpecialPriceAllowancePage.btnReturn, "Click F12=Return");
        click(SpecialPriceAllowancePage.btnReturn, "Click F12=Return");
    }

    /**
     * This method to display Item number Details
     */
    public void disItemNumberDet(String item, List<String> values) {
        int size = ownDriver.findElements(By.xpath("//div[text()='" + item + "']/preceding::input")).size();
        sendKeysAndEnter(By.xpath("(//div[text()='" + item + "']/preceding::input)[" + size + "]"), "5", "Enter 5 to display");
        List<String> str = Utility_Functions.xGetTextVisibleListString(ownDriver, ownDriver.findElements(By.xpath("//*[(@class='A26 input') or (@class='A22')]")));
        Utility_Functions.xAssertEquals(report, values.get(0), str.get(0), "Contract Name:");
        Utility_Functions.xAssertEquals(report, values.get(1), str.get(1), "Vendor No:");
        Utility_Functions.xAssertEquals(report, values.get(2), str.get(2), "Vendor Name:");
        Utility_Functions.xAssertEquals(report, values.get(5), str.get(4), "Item Name:");
        Utility_Functions.xAssertEquals(report, values.get(7), str.get(5), "PO COst:");
        Utility_Functions.xAssertEquals(report, values.get(8), str.get(6), "Rebate COst:");
        Utility_Functions.xAssertEquals(report, values.get(9), str.get(7), "Rebate Amount:");
        Utility_Functions.xAssertEquals(report, values.get(10), str.get(8), "Rebate Multiplier Type:");
        Utility_Functions.xAssertEquals(report, values.get(11), str.get(9), "Rebate Multiplier:");
        Utility_Functions.xAssertEquals(report, values.get(13), str.get(11), "Selling Multiplier Type:");
        Utility_Functions.xAssertEquals(report, values.get(14), str.get(12), "Selling Multiplier:");
        click(SpecialPriceAllowancePage.btnReturn, "Click F12=Return");
    }

    /**
     * This method to To Assigning Individual Item to the Contracts
     */
    public void assignIndividualItem() {
        String firstItemNo = null;
        optTextBox("2", "Enter 2 to Edit Contract");
        verifySpaChangeContractTitle("SPA - Change Contract");
        click(SpecialPriceAllowancePage.individualItem, "CLick F8=Individual Item");
        String title = getText(SpecialPriceAllowancePage.appHeader).trim();
        Utility_Functions.xAssertEquals(report, title, "Work With SPA Items - Rebate Details", "Page Title: ");
        if (Utility_Functions.xIsDisplayed(ownDriver, SpecialPriceAllowancePage.firstItemNo)) {
            firstItemNo = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.firstItemNo);
            sendKeys(SpecialPriceAllowancePage.lOpt, "4", "Delete Item Number");
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
            deleteRec(firstItemNo);
        }
        click(SpecialPriceAllowancePage.btnAddContract, "Click F6=Add");
        if (firstItemNo == null) {
            click(ownDriver.findElement(By.xpath("//a[contains(text(),'Number')]")));
            sendKeysAndEnter(SpecialPriceAllowancePage.optInd, "1", "Select Item number");
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
            firstItemNo = ownDriver.findElement(SpecialPriceAllowancePage.mfCOde).getAttribute("value");
        }
        sendKeys(SpecialPriceAllowancePage.rebateMultType, "c", "Enter Rebate Multiplier Type");
        sendKeys(SpecialPriceAllowancePage.rebateMult, ".2", "Enter Rebate Multiplier");
        sendKeys(SpecialPriceAllowancePage.mfCOde, firstItemNo, "Enter " + firstItemNo + " as Item number");
        sendKeys(SpecialPriceAllowancePage.indSellMultType, "C", "Enter Selling multiplier Type");
        sendKeys(SpecialPriceAllowancePage.indSellMult, ".3", "Enter Selling Multiplier");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        List<String> str1 = Utility_Functions.xGetTextVisibleListString(ownDriver, ownDriver.findElements(By.xpath("//*[(@class='A26 input') or (@class='A22')]")));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        String mes = Utility_Functions.getText(ownDriver, SpecialPriceAllowancePage.succMesg).trim();
        Utility_Functions.xAssertEquals(report, mes, "Successfully added item. Add another or F12 Return.", "Successful Message:");
        click(SpecialPriceAllowancePage.btnReturn, "Click F12=Return");
        Boolean bl = Utility_Functions.xIsDisplayed(ownDriver, By.xpath("//div[text()='" + firstItemNo + "']"));
        System.out.println("bl: " + bl + " Item: " + firstItemNo);
        Utility_Functions.xAssertEquals(report, "" + bl + "", "true", "Item Number is Added to Contract");
        disItemNumberDet(firstItemNo, str1);
        click(SpecialPriceAllowancePage.btnReturn, "Click F12=Return");
        click(SpecialPriceAllowancePage.btnReturn, "Click F12=Return");
    }
}
