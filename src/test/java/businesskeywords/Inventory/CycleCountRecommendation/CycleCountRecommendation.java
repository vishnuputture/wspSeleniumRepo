package businesskeywords.Inventory.CycleCountRecommendation;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.Purchasing.SPO.SpoPage;
import pages.common.MasterPage;
import pages.inventory.CostAdjustmentPage;
import pages.inventory.CycleCountRecommendationPage;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
import pages.warehouse.BinMaintenance.BinMaintenancePage;
import pages.warehouse.DriversPage;
import pages.warehouse.ManifestsPage;
import pages.warehouse.TruckPage;
import supportLibraries.Utility_Functions;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CycleCountRecommendation extends ReusableLib {

    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public CycleCountRecommendation(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * Keyword to Navigate to Suggested PO
     */
    public void navigateToCycleCountRecommendation() {
        String url = properties.getProperty("cycleCountRec");
        ownDriver.get(url);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    public By button(String btn) {
        return By.xpath("//button[text()='" + btn + "']");
    }

    public By header(String text) {
        return By.xpath("//h2[text()='" + text + "']");
    }

    public By label(String labelName) {
        return By.xpath("//label[text()='" + labelName + "']");
    }

    public void navigateToAddItemScreen() {
        click(button(" Add Items "), "Click [Add Item] Button");
        commonObj.validateText(DriversPage.newDriverHeader, "Add Items", "[Add Items] header is present");
    }

    public void navigateToReAssignUserScreen() {
        click(button(" Reassign Users "), "Click [Reassign Users] Button");
        commonObj.validateText(header("Re-Assign Users"), "Re-Assign Users", "[Re-Assign Users] header is present");
    }

    /**
     * Keyword to login to SPO
     */
    public void winLogin() {
        if (Utility_Functions.xWaitForElementPresent(ownDriver, SpoPage.winLogin, 10)) {
            sendKeys(SpoPage.userName, properties.getProperty("CycleCostUserName"));
            sendKeys(SpoPage.password, Utility_Functions.xGetJsonData("spoPass"));
            Utility_Functions.waitTillClickHardSleep(report, ownDriver, SpoPage.submit, "");
        }
    }

    /**
     * Keyword to select Company
     */
    public void selectCompanyCycleCost() {
        winLogin();
        if (isDisplayed(BinMaintenancePage.toasterCloseIcon)) {
            click(BinMaintenancePage.toasterCloseIcon);
        }
        if (isDisplayed(ManifestsPage.closePopUp)) {
            Utility_Functions.timeWait(6);
            click(ManifestsPage.closePopUp);
        }
        if (!Utility_Functions.xIsDisplayed(ownDriver, SpoPage.companyName)) {
            if (Utility_Functions.xIsDisplayed(ownDriver, SelfServicePriceSheetPage.companySelector)) {
                Utility_Functions.waitTillClickHardSleep(report, ownDriver, SelfServicePriceSheetPage.companySelector, "");
                click(SelfServicePriceSheetPage.companyLabel);
                if (System.getProperty("company") == null) {
                    sendKey(SelfServicePriceSheetPage.winCompanyNumber, "99599");
                } else {
                    sendKey(SelfServicePriceSheetPage.winCompanyNumber, System.getProperty("company"));
                }
                click(SelfServicePriceSheetPage.selectButton);
            }
        }
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, By.xpath("//h2"), "");
    }

    public void clickInventory() {
        click(CycleCountRecommendationPage.inventorySub);
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, CycleCountRecommendationPage.cycleCountRecHeader, "[Cycle Count Recommendation] Header is present");
    }

    public void navigateToCycleCountRec() {
        ownDriver.get(properties.getProperty("cycleCountRecDev"));
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * This method validates the UI of Add Items Popup
     */
    public void verifyADDITEMSUI() {
        navigateToAddItemScreen();
        String[] options = {"By Individual Item", "By Bin Location Range", "By Quantity", "By MF/PD/VN Code", "By Zone"};
        for (int i = 1; i < options.length; i++)
            Utility_Functions.xAssertEquals(report, options[i - 1], ownDriver.findElements(CycleCountRecommendationPage.addItemOptions).get(i), "[" + options[i] + "] drop down option is present");
        commonObj.validateText(button(" Cancel "), "Cancel", "[Cancel] button is present");
        commonObj.validateText(button(" Process "), "Process", "[Process] button is present");
        click(button(" Cancel "), "Click [Cancel] button");
        commonObj.validateText(CycleCountRecommendationPage.cycleCountRecHeader, "Cycle Count Recommendation", "[Cycle Count Recommendation] Header is present");
    }

    public void verifyReAssignUserUI() {
        navigateToReAssignUserScreen();
        String[] options = {"User Count", "Current User", "Name", "New User", "Name"};
        for (String option : options)
            Utility_Functions.xAssertEquals(report, option, ownDriver.findElement(By.xpath("//th[text()='" + option + "']")), "[" + option + "] column is present");
        commonObj.validateText(CycleCountRecommendationPage.reAssignUserCancelBtn, "Cancel", "[Cancel] button is present");
        commonObj.validateText(CycleCountRecommendationPage.reAssignUserProcessBtn, "Process", "[Process] button is present");
        click(CycleCountRecommendationPage.reAssignUserCancelBtn, "Click [Cancel] button");
        commonObj.validateText(CycleCountRecommendationPage.cycleCountRecHeader, "Cycle Count Recommendation", "[Cycle Count Recommendation] Header is present");
    }

    public void verifyCycleCountRecAuditorUI() {
        Utility_Functions.xSelectDropdownByName(ownDriver, CycleCountRecommendationPage.viewingAs, "Auditor");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, CycleCountRecommendationPage.cycleCountRecHeader, "[Cycle Count Recommendation] Header is present");
        commonObj.validateText(CycleCountRecommendationPage.auditorBanner, "Currently Viewing As Auditor", "[Currently Viewing As Auditor] Banner is present");
        commonObj.validateElementExists(SpoPage.refreshIcon, "[Refresh] icon present");
        String[] options = {"Local Company", "Auditor"};
        for (String option : options)
            Utility_Functions.xAssertEquals(report, option, ownDriver.findElement(By.xpath("//option[text()='" + option + "']")), "[" + option + "] option is present");
        String[] columns = {" Item Number ", " Item Description ", " Reason for Recommendation ", " Bin Location ", " Bin Type ", " Zone ", " Assigned User ", " Last Count User ", " Last Count "};
        for (String column : columns)
            commonObj.validateText(By.xpath("//th[text()='" + column + "']"), column.trim(), "[" + column + "] column is present");
        String[] labels = {"Viewing As:", "Counted Items / Total Items", "Percent Complete"};
        for (String label : labels)
            commonObj.validateText(label(label), label, "[" + label + "] label is present");
        commonObj.validateElementExists(CostAdjustmentPage.searchIcon, "Search icon present");
        String[] buttons = {" Export ", " Add Items ", " Reassign Users ", " Count Sheets ", " Auto Assign Users "};
        for (String button : buttons)
            commonObj.validateText(button(button), button.trim(), "[" + button + "] button is present");
    }

    public void verifyCycleCountRecLocalUI() {
        Utility_Functions.xSelectDropdownByName(ownDriver, CycleCountRecommendationPage.viewingAs, "Local Company");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, CycleCountRecommendationPage.cycleCountRecHeader, "[Cycle Count Recommendation] Header is present");
        Utility_Functions.xAssertEquals(report, isDisplayed(CycleCountRecommendationPage.auditorBanner), false, "");
        commonObj.validateElementExists(SpoPage.refreshIcon, "[Refresh] icon present");
        String[] options = {"Local Company", "Auditor"};
        for (String option : options)
            Utility_Functions.xAssertEquals(report, option, ownDriver.findElement(By.xpath("//option[text()='" + option + "']")), "[" + option + "] option is present");
        String[] columns = {" Item Number ", " Item Description ", " Reason for Recommendation ", " Bin Location ", " Bin Type ", " Zone ", " Assigned User ", " Last Count User ", " Last Count "};
        for (String column : columns)
            commonObj.validateText(By.xpath("//th[text()='" + column + "']"), column.trim(), "[" + column + "] column is present");
        String[] labels = {"Viewing As:", "Counted Items / Total Items", "Percent Complete"};
        for (String label : labels)
            commonObj.validateText(label(label), label, "[" + label + "] label is present");
        commonObj.validateElementExists(CostAdjustmentPage.searchIcon, "Search icon present");
        String[] buttons = {" Export ", " Add Items ", " Reassign Users ", " Count Sheets ", " Auto Assign Users "};
        for (String button : buttons)
            commonObj.validateText(button(button), button.trim(), "[" + button + "] button is present");
    }

    public void minimizeWindow(int count) {
        Utility_Functions.timeWait(2);
        for (int i = 0; i < count; i++) {
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_MINUS);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_MINUS);
                Utility_Functions.timeWait(2);
            } catch (Exception e1) {
            }
        }
        Utility_Functions.timeWait(2);
    }

    public void verifySearchFilterUI() {
        minimizeWindow(4);
        click(CostAdjustmentPage.searchIcon, "CLick Search Icon");
        Utility_Functions.timeWait(3);
        commonObj.validateText(By.xpath("//h1"), "Search Filters", "Search Filters panel title is present");
        String[] filterOptions = {"Item Number", "Zone", "Bin Location Range Start", "Bin Location Range End", "MF", "PD", "VN", "Assigned", "Counted", "Reason for Recommendation"};
        for (String filterOption : filterOptions)
            commonObj.validateText(label(filterOption), filterOption, "[" + filterOption + "] label is present");
        commonObj.validateText(button(" Apply Filters "), "Apply Filters", "[Apply Filters] button is present");
        commonObj.validateText(button(" Clear All Filters "), "Clear All Filters", "[Clear All Filters] button is present");
    }

    public void printCycleCountSheetUI() {
        click(button(" Count Sheets "), "Click [Count Sheets] button");
        commonObj.validateText(header("Print Cycle Count Sheet"), "Print Cycle Count Sheet", "[Print Cycle Count Sheet] header is present");
        commonObj.validateText(CycleCountRecommendationPage.printerPreference, "Printer Preferences", "[Printer Preferences] sub-header is present");
        String[] labels = {"Where would you like your cycle count sheets to print?", "How many copies would you like to print?", "Assigned User", "Select Optional Columns and Order:", "Option 1", "Option 2", "Option 3"};
        for (String label : labels)
            commonObj.validateText(label(label), label, "[" + label + "] label is present");
        commonObj.validateText(By.xpath("//h3[text()='Document Preferences']"), "Document Preferences", "[Document Preferences] header is present");
        commonObj.validateElementExists(button("Cancel "), "[Cancel] button is present");
        commonObj.validateElementExists(button(" Print "), "[Print] button is present");
    }

    public void multipleElementClick(By by) {
        int size = ownDriver.findElements(by).size();
        for (int i = 0; i < size; i++) {
            try {
                click(ownDriver.findElements(by).get(i));
                break;
            } catch (Exception e) {
            }
        }
    }

    public void verifyPrintCycleCountSheetUI() {
        printCycleCountSheetUI();
        minimizeWindow(2);
        multipleElementClick(button(" Cancel "));
        Utility_Functions.xSelectDropdownByName(ownDriver, CycleCountRecommendationPage.viewingAs, "Auditor");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, CycleCountRecommendationPage.cycleCountRecHeader, "[Cycle Count Recommendation] Header is present");
        commonObj.validateText(CycleCountRecommendationPage.auditorBanner, "Currently Viewing As Auditor", "[Currently Viewing As Auditor] Banner is present");
        printCycleCountSheetUI();
    }

    public void autoAssignUserUi() {
        click(button(" Auto Assign Users "), "Click [Auto Assign Users] button");
        commonObj.validateText(header("Auto Assign Users"), "Auto Assign Users", "[Auto Assign Users] header is present");
        commonObj.validateText(label("Select an Option to Auto Assign"), "Select an Option to Auto Assign", "[Select an Option to Auto Assign] label is present");
        commonObj.validateElementExists(button("Cancel "), "[Cancel] button is present");
        commonObj.validateElementExists(button("Process "), "[Process] button is present");
    }

    public void verifyAutoAssignUserUI() {
        autoAssignUserUi();
        multipleElementClick(button("Cancel "));
        Utility_Functions.xSelectDropdownByName(ownDriver, CycleCountRecommendationPage.viewingAs, "Auditor");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, CycleCountRecommendationPage.cycleCountRecHeader, "[Cycle Count Recommendation] Header is present");
        commonObj.validateText(CycleCountRecommendationPage.auditorBanner, "Currently Viewing As Auditor", "[Currently Viewing As Auditor] Banner is present");
        autoAssignUserUi();
    }

    public void verifyCycleCountAuditHistoryUI() {
        click(CycleCountRecommendationPage.menuBar, "Click menu bar");
        click(CycleCountRecommendationPage.auditHistory, "Click [Audit History]");
        click(CycleCountRecommendationPage.auditHistory, "Click [Audit History]");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, header("Cycle Count Audit History"), "[Cycle Count Audit History] header is present");
        String[] columns = {" Item Number ", " Item Description ", " Date/Time ", " Event ", " Bin Location ", " Bin Type ", " Zone ", " Counter ", " Keyer ", " Bin Count ", " Item on Hand "};
        for (String column : columns)
            commonObj.validateText(By.xpath("//th[text()='" + column + "']"), column.trim(), "[" + column + "] column is present");
        commonObj.validateElementExists(CostAdjustmentPage.searchIcon, "Search icon present");
        commonObj.validateText(button(" Export "), "Export", "[Export] button is present");
        commonObj.validateElementExists(SpoPage.refreshIcon, "[Refresh] icon present");
    }

    public void verifySortingCol() {
        String[] columns = {" Item Number ", " Item Description ", " Reason for Recommendation ", " Bin Location ", " Bin Type ", " Zone ", " Assigned User ", " Last Count User ", " Last Count "};
        for (String column : columns) {
            Utility_Functions.waitTillClickHardSleep(report, ownDriver, CycleCountRecommendationPage.sortOrder, "Click " + column + " column");
            Utility_Functions.waitTillClickHardSleep(report, ownDriver, CycleCountRecommendationPage.ascOrder, "Click " + column + " column and verify ascending order");
            Utility_Functions.waitTillClickHardSleep(report, ownDriver, CycleCountRecommendationPage.descOrder, "Click " + column + " column and verify descending order");
        }
    }

    public By spanTag(String text) {
        return By.xpath("//span[text()='" + text + "']");
    }

    public void pageClick(String page) {
        Utility_Functions.timeWait(6);
        int size = ownDriver.findElements(By.xpath("//tr/td")).size();
        if (size != 0) {
            try {
                Utility_Functions.xScrollIntoView(ownDriver, spanTag("10"));
                click(spanTag(page), "Click " + page);
            } catch (Exception e) {
                Utility_Functions.xScrollIntoView(ownDriver, spanTag("10"));
            }
            commonObj.validateElementExists(By.xpath("//tr/td"), "Row " + size);
        }
    }

    public void verifyPageCount() {
        Utility_Functions.timeWait(6);
        if (Utility_Functions.xIsDisplayed(ownDriver, SpoPage.onePage)) {
            int truckCount = ownDriver.findElements(SpoPage.worksheetCount).size();
            commonObj.validateText(DriversPage.onePage, "of 1", "One page is available having Worksheet count " + truckCount + "");
        } else {
            pageClick("10");
            pageClick("15");
            pageClick("30");
        }
    }

    public void verifyGraphPercentage() {
        Utility_Functions.timeWait(6);
        String items[] = getText(CycleCountRecommendationPage.percentage).split("/");
        double countedItem = Double.parseDouble(items[0].trim());
        double totalItem = Double.parseDouble(items[1].trim());
        double total = countedItem / totalItem;
        double percentage = total * 100;
        commonObj.validateText(CycleCountRecommendationPage.getPercentage, "" + Math.round(percentage) + "%", "Percentage matches");
    }

    public void searchForItem(String itemNo, int count) {
        minimizeWindow(count);
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, CostAdjustmentPage.searchIcon, "Click search Icon");
        sendKeys(CycleCountRecommendationPage.itemSearchField, itemNo, "Enter Item Number");
        Utility_Functions.xSelectDropdownByName(ownDriver, CycleCountRecommendationPage.selectAssigned, "Select");
        click(button(" Apply Filters "), "Click [Apply Filter] Button");
    }

    public By tdTag(String text) {
        return By.xpath("//td[text()='" + text + "']");
    }

    public By columnData(String label) {
        return By.xpath("//table/tbody/tr/td[count(//table/thead/tr/th[contains(text(),'" + label + "')]/preceding-sibling::th)+1]");
    }

    public String getUnassignedUser() {
        String itemNo = null;
        int size = ownDriver.findElements(By.xpath("//tr/td/i")).size();
        for (int i = 0; i < size; i++) {
            if (ownDriver.findElements(columnData("Assigned User")).get(i).getText().equals("UNASSIGNED")) {
                itemNo = ownDriver.findElements(columnData("Item Number")).get(i).getText();
                searchForItem(itemNo, 2);
                break;
            }
        }
        return itemNo;
    }

    public void filterResult() {
        minimizeWindow(2);
        click(CostAdjustmentPage.searchIcon, "Click Search icon");
        Utility_Functions.xSelectDropdownByName(ownDriver, CycleCountRecommendationPage.selectAssigned, "Unassigned");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,button(" Apply Filters "), "Click [Apply Filter] Button");
    }

    public void verifyEditAssignedUser() {
        Utility_Functions.timeWait(6);
        filterResult();
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, By.xpath("//h2"), "");
        int i = 0;
        while (!isDisplayed(tdTag("UNASSIGNED"))) {
            Utility_Functions.waitTillClickHardSleep(report, ownDriver, CycleCountRecommendationPage.searchForUnassigned, "Click Assigned User column");
            i++;
            if (i == 2) {
                break;
            }
        }
        if (i != 2) {
            getUnassignedUser();
            Utility_Functions.timeWait(2);
            Utility_Functions.waitTillClickHardSleep(report, ownDriver, By.xpath("//h2"), "");
            changeAssignedUser();
        }
    }

    public void changeAssignedUser() {
        Utility_Functions.xHoverElementClk(ownDriver.findElement(CycleCountRecommendationPage.editUnassigned), ownDriver);
        Utility_Functions.timeWait(2);
        sendKeysAndEnter(CycleCountRecommendationPage.searchUser, jsonData.getData("user"), "Enter User name and Hot Enter");
        click(CycleCountRecommendationPage.closeMark, "Click Close [x] Icon");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(tdTag("UNASSIGNED"), "Validating Unassigned status");
        Utility_Functions.xHoverElementClk(ownDriver.findElement(CycleCountRecommendationPage.editUnassigned), ownDriver);
        Utility_Functions.timeWait(2);
        sendKeysAndEnter(CycleCountRecommendationPage.searchUser, jsonData.getData("user"), "Enter User name and Hot Enter");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.timeWait(2);
        click(CycleCountRecommendationPage.checkMark, "Click Right [👍] Icon");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, spanTag("Assigned users were successfully updated"), "[Assigned users were successfully updated] message present");
        commonObj.validateElementExists(tdTag(jsonData.getData("user").toUpperCase()), "Validating Assigned User");
    }

    public void addItem(String item) {
        click(button(" Add Items "), "Click [Add Item] Button");
        commonObj.validateText(DriversPage.newDriverHeader, "Add Items", "[Add Items] header is present");
        Utility_Functions.xSelectDropdownByName(ownDriver, CycleCountRecommendationPage.addItemOpt, jsonData.getData("option"));
        sendKeys(CycleCountRecommendationPage.inputItem, item);
        click(button(" Process "), "Click [Process] button");
        validationToaster(ManifestsPage.successMsg, jsonData.getData("option"));
        click(button(" Cancel "), "Click [Cancel] button");
    }

    public void validationToaster(By el, String option) {
        switch (option) {
            case "By Individual Item":
                commonObj.validateText(el, "The Chosen item was added successfully.", "[The Chosen item was added successfully.] is present");
                break;
            case "By Quantity":
                commonObj.validateText(el, "Add items by quantity request has been received. request has been received.", "[Add items by quantity request has been received. request has been received.] is present");
                break;
        }
        Utility_Functions.timeWait(7);
    }

    public void verifyChangesLocalCompWithAuditor() {
        Utility_Functions.timeWait(6);
        filterResult();
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, By.xpath("//h2"), "Wait for page to be loaded");
        String itemNo = ownDriver.findElements(columnData("Item Number")).get(0).getText();
        navigateToViewer("Auditor");
        addItem(itemNo);
        searchForItem(itemNo, 1);
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, By.xpath("//h2"), "Wait for page to be loaded");
        navigateToViewer("Local Company");
        searchForItem(itemNo, 0);
        Utility_Functions.timeWait(6);
        changeAssignedUser();
        navigateToViewer("Auditor");
        searchForItem(itemNo, 0);
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, By.xpath("//h2"), "Wait for page to be loaded");
        commonObj.validateElementExists(tdTag("UNASSIGNED"), "Validating Unassigned status");
    }

    public void navigateToViewer(String viewer) {
        Utility_Functions.xSelectDropdownByName(ownDriver, CycleCountRecommendationPage.viewingAs, viewer);
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, CycleCountRecommendationPage.cycleCountRecHeader, "[Cycle Count Recommendation] Header is present");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, CycleCountRecommendationPage.auditorBanner, "[Currently Viewing As " + viewer + "] Banner is present");
    }

    public By actionDropdown(String option) {
        return By.xpath("//li/a[text()='" + option + "']");
    }

    public void assignedCountedFilter(String assign,String counted) {
        Utility_Functions.timeWait(2);
        minimizeWindow(2);
        click(CostAdjustmentPage.searchIcon, "Click Search icon");
        Utility_Functions.xSelectDropdownByName(ownDriver, CycleCountRecommendationPage.selectAssigned, jsonData.getData(assign));
        Utility_Functions.xSelectDropdownByName(ownDriver, CycleCountRecommendationPage.countedFilter, jsonData.getData(counted));
        click(button(" Apply Filters "), "Click [Apply Filter] Button");
    }

    public void navigateToCycleCountEntry() {
        Utility_Functions.xHoverElementClk(ownDriver.findElement(CycleCountRecommendationPage.actionIcon), ownDriver);
        try {
            click(CycleCountRecommendationPage.actionIcon);
        }catch (Exception e){}
        Utility_Functions.timeWait(2);
        click(actionDropdown("Cycle Count Entry"), "Click [Cycle Count Entry]");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, By.xpath("//h2"), "[Cycle Count Entry] header present");
    }

    public void cycleCountEntryUI() {
        commonObj.validateElementExists(SpoPage.refreshIcon, "[Refresh] icon present");
        String[] options = {"Local Company", "Auditor"};
        for (String option : options)
            Utility_Functions.xAssertEquals(report, option, ownDriver.findElement(By.xpath("//option[text()='" + option + "']")), "[" + option + "] option is present");
        String[] columns = {" Item Number ", " Item Description ", "MF PD VN", "Last Count"," Bin Location ", "Bin Type", " Zone ", " Assigned User ", "Bin Count", " Item on Hand "," On Hand + In Hold "};
        for (String column : columns)
            commonObj.validateText(By.xpath("//th[text()='" + column + "']"), column.trim(), "[" + column + "] column is present");
        commonObj.validateElementExists(CostAdjustmentPage.searchIcon, "Search icon present");
        String[] buttons = {" Count Sheets ", " Labels ", " Save ", " Back "};
        for (String button : buttons)
            commonObj.validateText(button(button), button.trim(), "[" + button + "] button is present");
    }

    public By aTag(String text){
        return By.xpath("//a[text()='"+text+"']");
    }

    public void paginationUI(){
        commonObj.validateText(spanTag("Show: "),"Show:","Pagination [Show:] present");
        commonObj.validateText(spanTag("10"),"10","Pagination [10] present");
        commonObj.validateText(spanTag("15"),"15","Pagination [15] present");
        commonObj.validateText(spanTag("30"),"30","Pagination [30] present");
        commonObj.validateText(aTag("«"),"«","Arrow [«] present");
        commonObj.validateText(aTag("‹"),"‹","Arrow [<] present");
        commonObj.validateText(aTag("›"),"›","Arrow [>] present");
        commonObj.validateText(aTag("»"),"»","Arrow [>>] present");
    }

    public void verifyCycleCountEntry() {
        assignedCountedFilter("Assigned","Counted");
        navigateToCycleCountEntry();
        cycleCountEntryUI();
        paginationUI();
    }

    public void removeCycleRec() {
        Utility_Functions.timeWait(2);
        Utility_Functions.xHoverElementClk(ownDriver.findElement(CycleCountRecommendationPage.actionIcon), ownDriver);
        try {
           /* Utility_Functions.timeWait(2);
            click(CycleCountRecommendationPage.actionIcon);*/
        }catch (Exception e){}
        Utility_Functions.timeWait(2);
        click(actionDropdown("Remove"), "Click [Remove]");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, CycleCountRecommendationPage.removeOpoUp, "[Remove] Popup present");
    }

    public void verifyRemoveCounted() {
        assignedCountedFilter("Assigned","Counted");
        if(!isDisplayed(CycleCountRecommendationPage.actionIcon)){
            commonObj.validateElementExists(By.xpath("//h2"),"[Remove] action not present");
        }else{
            new Exception("Remove Action Present Present");
        }
        assignedCountedFilter("Assigned","NotCounted");
        removeCycleRec();
        click(button("Cancel "),"Click [Cancel] button");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, By.xpath("//h2"), "[Cycle Count Rec] header present");
        removeCycleRec();
        click(button("Remove "),"Click [Remove] button");
        commonObj.validateText(TruckPage.deletePopUp,"Successfully removed recommendation.","[Successfully removed recommendation.] toaster is present");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, By.xpath("//h2"), "[Cycle Count Rec] header present");
    }
}
