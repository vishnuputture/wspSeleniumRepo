package businesskeywords.warehousing.PickingInProcess;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import java.math.*;

import org.openqa.selenium.interactions.Actions;
import pages.warehouse.PickingInProcessPage;
import supportLibraries.Utility_Functions;
import businesskeywords.common.Login;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class PickingInProcessHome extends ReusableLib {
    CommonActions commonObj;
    private FrameworkDriver ownDriver;
    Login login;

    public PickingInProcessHome(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    public void navigateToPiPHomePage() {
        String url = properties.getProperty("PickingQA");
        ownDriver.get(url);
        /*commonObj.validateElementExists(PickingInProcessPage.verifyCredentialsButton, "Navigated to Login Page");

        String userName = properties.getProperty("CycleCostUserName");
        String Password = properties.getProperty("PIPPass");
        waitForVisible(PickingInProcessPage.verifyCredentialsButton);
        sendKeys(PickingInProcessPage.usernameTextbox, userName, "Entering username " + userName);
        sendKeys(PickingInProcessPage.passwordTextbox, Password, "Entering password ******");
        click(PickingInProcessPage.verifyCredentialsButton, "Clicked on Verify Credentials Button");
        Utility_Functions.timeWait(7);

        //select Company
        selectCompany();*/
    }

    public void selectCompany() {
        click(PickingInProcessPage.companyToggle);
        waitForVisible(PickingInProcessPage.winIntoCompanyLabel);
        click(PickingInProcessPage.winIntoCompanyLabel);
        waitForVisible(PickingInProcessPage.companyNameTextbox);
        String[] companyArr = getProperties("CompanyNumber").split(String.valueOf(','));
        sendKeys(PickingInProcessPage.companyNameTextbox, properties.getProperty("PIPCompanyName"), "Entering Company Name " + properties.getProperty("PIPCompanyName"));
        click(PickingInProcessPage.selectButton);
        Utility_Functions.timeWait(3);
        waitForVisible(PickingInProcessPage.pickingInProcessHeader);
    }

    public void verifyPIHomePageUI() {
        commonObj.verifyElementContainsText(PickingInProcessPage.pickingInProcessHeader, "PICKING IN PROCESS", "Verifying Header [PICKING IN PROCESS]");
        waitForVisible(PickingInProcessPage.currentZoneDropDown);
        waitForVisible(PickingInProcessPage.labelTotalLines);
        waitForVisible(PickingInProcessPage.labelPercentComplete);
        waitForVisible(PickingInProcessPage.filterButton);
        click(PickingInProcessPage.filterButton);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        waitForVisible(PickingInProcessPage.shipmentTableHeader);
        waitForVisible(PickingInProcessPage.nameTableHeader);
        waitForVisible(PickingInProcessPage.assignedRFTableHeader);
        waitForVisible(PickingInProcessPage.shipViaTableHeader);
        commonObj.validateElementExists(PickingInProcessPage.PercentCompleteTableHeader, "[Percent Complete] field present");
        commonObj.validateElementExists(PickingInProcessPage.rfGunTableHeader, "[RF GUN] field present");
        commonObj.validateElementExists(PickingInProcessPage.manualTableHeader, "[Manual] field present");
        commonObj.validateElementExists(PickingInProcessPage.autoLinesTableHeader, "[Auto Lines] field present");
        commonObj.validateElementExists(PickingInProcessPage.totalLinesTableHeader, "[Total Lines] field present");
        commonObj.validateElementExists(PickingInProcessPage.rfGunStagedTableHeader, "[RF Gun Zones Staged] field present");
        commonObj.validateElementExists(PickingInProcessPage.shipDateDropDown, "[Ship Date/ Load Date/Time] field present");
    }

    public void verifyPIPSearchFilterUI() {
        waitForVisible(PickingInProcessPage.filterButton);
        click(PickingInProcessPage.filterButton);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        commonObj.validateText(PickingInProcessPage.searchFilterHeader, "Search Filters", "Validating Header [Search Filters]");
        waitForVisible(PickingInProcessPage.shipmentFilter);
        waitForVisible(PickingInProcessPage.customerFilter);
        waitForVisible(PickingInProcessPage.assignedToFilter);
        waitForVisible(PickingInProcessPage.shipViaFilter);
        waitForVisible(PickingInProcessPage.truckNameFilter);
        waitForVisible(PickingInProcessPage.pickStatusFilter);
        waitForVisible(PickingInProcessPage.shipDateFilter);
        commonObj.validateElementExists(PickingInProcessPage.applyFiltersButton, "[Apply Filters] button present");
        commonObj.validateElementExists(PickingInProcessPage.clearAllFiltersButton, "[Clear All Filters] button present");
    }

    public void selectFirstOrder() {
        click(PickingInProcessPage.firstRowRecord);
        waitForVisible(PickingInProcessPage.shipmentNoHeader);
        String actual = commonObj.getText(PickingInProcessPage.shipmentNoHeader);
        commonObj.validateContainsText("SHIPMENT:", actual, "Validating Navigation to Order Details Page");
    }

    public void verifyShipmentDetailsUI() {
        commonObj.validateElementExists(PickingInProcessPage.customerNameField, "[Customer Name:] field present");
        commonObj.validateElementExists(PickingInProcessPage.dateTimeField, "[Date/Time:] field present");
        commonObj.validateElementExists(PickingInProcessPage.totalLines, "[Open / Total Lines:] field present");
        commonObj.validateElementExists(PickingInProcessPage.customerPOField, "[Customer PO Number:] field present");
        commonObj.validateElementExists(PickingInProcessPage.jobNameField, "[Customer PO Number:] field present");
        commonObj.validateElementExists(PickingInProcessPage.shipViaField, "[Ship Via:] field present");
        commonObj.validateElementExists(PickingInProcessPage.percentageCompleteLabel, "[Customer Name:] field present");
        commonObj.validateElementExists(PickingInProcessPage.assignedRFPicker, "[Assigned RF Picker] field present");
        commonObj.validateElementExists(PickingInProcessPage.rfGunOpenLines, "[RF Gun Open Lines] field present");
        commonObj.validateElementExists(PickingInProcessPage.manualOpenLines, "[Manual Open Lines] field present");
        commonObj.validateElementExists(PickingInProcessPage.autoOpenLines, "[Manual Open Lines] field present");
        commonObj.validateElementExists(PickingInProcessPage.totalLines, "[Total Lines] field present");
        commonObj.validateElementExists(PickingInProcessPage.percentComplete, "[Percent Complete] field present");
        click(PickingInProcessPage.expandAllLink);
        commonObj.validateElementExists(PickingInProcessPage.itemNumberHeader, "[Item Number] field present");
        commonObj.validateElementExists(PickingInProcessPage.itemDescriptionHeader, "[Item Description] field present");
        commonObj.validateElementExists(PickingInProcessPage.qtyPlannedHeader, "[Qty Planned] field present");
        commonObj.validateElementExists(PickingInProcessPage.qtyRemainingHeader, "[Qty Remaining] field present");
        commonObj.validateElementExists(PickingInProcessPage.binLocationHeader, "[Bin Location] field present");
        commonObj.validateElementExists(PickingInProcessPage.statusHeader, "[Status] field present");
        commonObj.validateElementExists(PickingInProcessPage.pickViaHeader, "[Pick Via] field present");
        commonObj.validateElementExists(PickingInProcessPage.pickerHeader, "[Picker] field present");
        commonObj.validateElementExists(PickingInProcessPage.completeManualPickButton, "[Complete Manual Picking] button present");
        commonObj.validateElementExists(PickingInProcessPage.maintainPickersButton, "[Maintain Pickers] button present");
        commonObj.validateElementExists(PickingInProcessPage.printDocumentButton, "[Print Documents and Labels] button present");
        commonObj.validateElementExists(PickingInProcessPage.stagingButton, "[Staging] button present");
    }

    public void verifySorting() {
        //verify shipment sorting
        String oldAttribute = null;
        String newAttribute = null;
        String xpath = null;
        oldAttribute = ownDriver.findElement(By.xpath("//p[text()='Shipment']/ancestor::th[@role='columnheader']")).getAttribute("aria-sort");
        if (oldAttribute.contains("none")) {
            click(PickingInProcessPage.shipmentTableHeader);
            Utility_Functions.timeWait(3);
        }
        newAttribute = ownDriver.findElement(By.xpath("//p[text()='Shipment']/ancestor::th[@role='columnheader']")).getAttribute("aria-sort");
        Utility_Functions.xAssertEquals(report, "ascending", newAttribute);
        xpath = "//table[@role='grid']//tbody/tr/td[1]/a";
        verifyAscendingOrder(xpath, "Shipment");
        click(PickingInProcessPage.shipmentTableHeader);
        Utility_Functions.timeWait(3);
        verifyDescendingOrder(xpath, "Shipment");

        //verify name sorting
        click(PickingInProcessPage.nameTableHeader);
        Utility_Functions.timeWait(3);
        newAttribute = ownDriver.findElement(By.xpath("//p[text()='Name']/ancestor::th[@role='columnheader']")).getAttribute("aria-sort");
        Utility_Functions.xAssertEquals(report, "ascending", newAttribute);
        xpath = "//table[@role='grid']//tbody/tr/td[2]";
        verifyAscendingOrder(xpath, "Name");
        click(PickingInProcessPage.nameTableHeader);
        Utility_Functions.timeWait(3);
        verifyDescendingOrder(xpath, "Name");

        //verify AssignedRFTableHeader sorting
        click(PickingInProcessPage.assignedRFTableHeader);
        Utility_Functions.timeWait(3);
        newAttribute = ownDriver.findElement(By.xpath("//p[text()='Assigned RF Picker']/ancestor::th[@role='columnheader']")).getAttribute("aria-sort");
        Utility_Functions.xAssertEquals(report, "ascending", newAttribute);
        xpath = "//table[@role='grid']//tbody/tr/td[3]";
        verifyAscendingOrder(xpath, "Assigned RF Picker");
        click(PickingInProcessPage.assignedRFTableHeader);
        Utility_Functions.timeWait(3);
        //verifyDescendingOrder(xpath, "Assigned RF Picker");

        //verify Ship VIA sorting
        click(PickingInProcessPage.shipViaTableHeader);
        Utility_Functions.timeWait(3);
        newAttribute = ownDriver.findElement(By.xpath("//p[text()='Ship Via']/ancestor::th[@role='columnheader']")).getAttribute("aria-sort");
        Utility_Functions.xAssertEquals(report, "ascending", newAttribute);
        xpath = "//table[@role='grid']//tbody/tr/td[4]";
        verifyAscendingOrder(xpath, "Ship Via");
        click(PickingInProcessPage.shipViaTableHeader);
        Utility_Functions.timeWait(3);
        verifyDescendingOrder(xpath, "Ship Via");

        //verify RF Gun sorting
        click(PickingInProcessPage.rfGunTableHeader);
        Utility_Functions.timeWait(3);
        newAttribute = ownDriver.findElement(By.xpath("//p[text()='RF Gun']/ancestor::th[@role='columnheader']")).getAttribute("aria-sort");
        Utility_Functions.xAssertEquals(report, "ascending", newAttribute);
        xpath = "//table[@role='grid']//tbody/tr/td[5]";
        verifyAscendingOrder(xpath, "RF Gun");
    }

    public void verifyAscendingOrder(String xpath, String columnName) {
        ArrayList<String> list = new ArrayList<String>();
        List<WebElement> elements = ownDriver.findElements(By.xpath(xpath));
        if (columnName.equalsIgnoreCase("Shipment")) {
            for (int j = 0; j < elements.size(); j++) {
                list.add(elements.get(j).getText().substring(0, 6));
            }
            ArrayList<String> list2 = new ArrayList<>(list);
            Collections.sort(list2);
            Assert.assertTrue(list.equals(list2));
        } else {
            for (int j = 0; j < elements.size(); j++) {
                list.add(elements.get(j).getText());
            }
            ArrayList<String> list2 = new ArrayList<>(list);
            Collections.sort(list2);
            Assert.assertTrue(list.equals(list2));
        }
    }

    public void verifyDescendingOrder(String xpath, String columnName) {
        ArrayList<String> list = new ArrayList<String>();
        List<WebElement> elements = ownDriver.findElements(By.xpath(xpath));
        if (columnName.equalsIgnoreCase("Shipment")) {
            for (int j = 0; j < elements.size(); j++) {
                list.add(elements.get(j).getText().substring(0, 6));
            }
            ArrayList<String> list2 = new ArrayList<>(list);
            Collections.sort(list2, Collections.reverseOrder());
            Assert.assertTrue(list.equals(list2));
        } else {
            for (int j = 0; j < elements.size(); j++) {
                list.add(elements.get(j).getText());
            }
            ArrayList<String> list2 = new ArrayList<>(list);
            Collections.sort(list2, Collections.reverseOrder());
            Assert.assertTrue(list.equals(list2));
        }
    }

    public void verifyFilters() {
        click(PickingInProcessPage.pickStatusLink);
        click(PickingInProcessPage.filterButton);
        commonObj.validateElementExists(isDisabled("Clear All Filters"), "Clear All Filters button is disabled");
        commonObj.validateElementExists(isDisabled("Apply Filters"), "Apply Filters button is disabled");
        click(PickingInProcessPage.closeFilter);
    }

    public By isDisabled(String label) {
        return By.xpath("//button[@disabled][contains(text(),'" + label + "')]");
    }

    public void launchWISEapp() {
        login = new Login(helper);
        login.launchApp();
        login.siteLogin();
        System.out.println("LoggedIn successfully");
    }
    public void navigateToPIP() {
        sendKeys(PickingInProcessPage.mainMenuTbx, "19", "Enter item number");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        waitForVisible(PickingInProcessPage.mainMenuTbx);
        sendKeys(PickingInProcessPage.mainMenuTbx, "9", "Enter item number");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.timeWait(12);
        ArrayList<String> tabs = new ArrayList<String>(ownDriver.getWindowHandles());
        ownDriver.switchTo().window(tabs.get(1));
        selectCompany();
        click(PickingInProcessPage.logoutButton);
        click(PickingInProcessPage.logoutToolTip);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.verifyCredentialsButton, "Navigated to Login Page");
    }

    public void defaultPickStatusShipments() {
        commonObj.validateElementExists(PickingInProcessPage.pickStatusLink, "Pick Status link is present");
        click(PickingInProcessPage.filterButton);
        commonObj.validateElementExists(PickingInProcessPage.searchFilterHeader, "Search Filters is present");
        String attribute = ownDriver.findElement(By.id("pickStatus")).getAttribute("ng-reflect-default-option");
        Utility_Functions.xAssertEquals(report, "Picking,Not-Picked", attribute);
        click(PickingInProcessPage.closeFilter);
    }

    public void filterCurrentZone() {
        commonObj.validateElementExists(PickingInProcessPage.currentZoneDropDown, "Current Zone dropdown is present");
        List<WebElement> zone1 = ownDriver.findElements(By.xpath("//table[@role='grid']/tbody/tr/td/a"));
        click(PickingInProcessPage.currentZoneDropDown);
        click(PickingInProcessPage.selectCurrentZone);
        Utility_Functions.timeWait(3);
        List<WebElement> zone2 = ownDriver.findElements(By.xpath("//table[@role='grid']/tbody/tr/td/a"));
        Assert.assertTrue(zone2.size() != zone1.size());
    }

    public void logoutFromApp() {
        click(PickingInProcessPage.logoutButton);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.verifyCredentialsButton, "Navigated to Login Page");
    }

    public void verifyOpenTotalLines() {
        commonObj.validateElementExists(PickingInProcessPage.labelTotalLines, "Open/Total Lines section is displayed");
        String totalLines = getText(PickingInProcessPage.totalLinesCount);
        String attribute[] = ownDriver.findElement(By.xpath("//div[@role='progressbar']")).getAttribute("style").split(":");
        String value = attribute[1].replace("%", "").trim();
        int percent = Integer.parseInt(value.replace(";", ""));
        //int percent = Integer.parseInt(getText(PickingInProcessPage.percentageCompleteBar).replace("%",""));
        String[] part1 = totalLines.split("/");
        int beforeCount = Integer.parseInt(part1[0].trim());

        click(PickingInProcessPage.PercentCompleteTableHeader);
        Utility_Functions.timeWait(2);
        selectFirstOrder();
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.expandAllLink);
        completeManualPicking();
        click(PickingInProcessPage.pickingInProcessBackLink);

        String totalLines2 = getText(PickingInProcessPage.totalLinesCount);
        String attri[] = ownDriver.findElement(By.xpath("//div[@role='progressbar']")).getAttribute("style").split(":");
        value = attri[1].replace("%", "").trim();
        int newPercent = Integer.parseInt(value.replace(";", ""));
        //int newPercent = Integer.parseInt(getText(PickingInProcessPage.percentageCompleteBar).replace("%",""));
        String[] part2 = totalLines2.split("/");
        int afterCount = Integer.parseInt(part2[0].trim());
        Assert.assertTrue("Open / Total Lines count does not change", afterCount < beforeCount);
        Assert.assertTrue("Percentage does not change", newPercent > percent);
    }

    public void completeManualPicking() {
        click(PickingInProcessPage.completeManualPickButton);
        Utility_Functions.timeWait(2);
        commonObj.verifyElementContainsText(PickingInProcessPage.manualPickingHeader, "COMPLETE MANUAL PICKING", "[Complete Manual Picking] header is present");
        click(PickingInProcessPage.buttonConfirm);
        Utility_Functions.timeWait(7);
    }

    public void shipDateDropDown() {
        click(PickingInProcessPage.shipDateDropDown);
        commonObj.validateElementExists(PickingInProcessPage.loadDateDropDownValue, "[Load Date/Time] drop down value is present");
        commonObj.validateElementExists(PickingInProcessPage.shipDateDropDownValue, "[Ship Date] drop down value is present");
        click(PickingInProcessPage.loadDateDropDownValue);
        Utility_Functions.timeWait(2);
        Assert.assertTrue(getText(PickingInProcessPage.shipDateFirstRowVal).contains(":"));

        click(PickingInProcessPage.shipDateDropDown);
        click(PickingInProcessPage.shipDateDropDownValue);
        Utility_Functions.timeWait(2);
        String getdate = getText(PickingInProcessPage.shipDateFirstRowVal);
        Assert.assertTrue(!getdate.contains(":"));
    }

    public void verifyIconsBesideShipment() {
        getIconsBesideShipment(PickingInProcessPage.urgentIcon);
        String actual = commonObj.getText(PickingInProcessPage.shipmentNoHeader);
        commonObj.validateContainsText("SHIPMENT:", actual, "Validating Navigation to Order Details Page");
        click(PickingInProcessPage.pickingInProcessBackLink);
        Utility_Functions.timeWait(3);
        getIconsBesideShipment(PickingInProcessPage.returnToStockIcon);
        commonObj.validateContainsText("SHIPMENT:", actual, "Validating Navigation to Order Details Page");
        click(PickingInProcessPage.pickingInProcessBackLink);
        Utility_Functions.timeWait(3);
        getIconsBesideShipment(PickingInProcessPage.manifestIcon);
        commonObj.validateContainsText("SHIPMENT:", actual, "Validating Navigation to Order Details Page");
        click(PickingInProcessPage.pickingInProcessBackLink);
        Utility_Functions.timeWait(3);

    }

    public List<WebElement> getAllElements(By ele) {
        List<WebElement> elements = ownDriver.findElements(ele);
        return elements;
    }

    public void getIconsBesideShipment(By ele) {
        String page = Utility_Functions.getText(ownDriver, PickingInProcessPage.totalPages);
        int totalPages = Integer.parseInt(page.replaceFirst(".*?(\\d+).*", "$1"));
        for (int i = 0; i < totalPages; i++) {
            List<WebElement> elements = ownDriver.findElements(ele);
            if (elements.size() > 0) {
                click(ele);
                Utility_Functions.timeWait(2);
                break;
            } else {
                //WebElement arrrow = ownDriver.findElement(By.xpath("//li[contains(@class,'next page-item')]/a[@class='page-link']"));
                Utility_Functions.xScrollIntoView(ownDriver, PickingInProcessPage.nextPageArrow);
                click(PickingInProcessPage.nextPageArrow);
                Utility_Functions.timeWait(2);
            }
        }
    }

    public void verifyReturnToPickQPriority() {
        List<WebElement> elements = getAllElements(PickingInProcessPage.returnToPickQPriorityLink);
        if (elements.size() == 0) {
            click(PickingInProcessPage.shipmentTableHeader);
        }
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.returnToPickQPriorityLink, "[Return To Pick Queue Priority] link present");
        click(PickingInProcessPage.shipmentTableHeader);
        Utility_Functions.timeWait(2);
        String firstOrder1 = Utility_Functions.getText(ownDriver, PickingInProcessPage.firstRowRecord);
        click(PickingInProcessPage.returnToPickQPriorityLink);
        Utility_Functions.timeWait(2);
        String firstOrder2 = Utility_Functions.getText(ownDriver, PickingInProcessPage.firstRowRecord);
        Assert.assertTrue(firstOrder1 != firstOrder2);
    }

    public void moveToNextPage() {
        String page = Utility_Functions.getText(ownDriver, PickingInProcessPage.totalPages);
        int totalPages = Integer.parseInt(page.replaceFirst(".*?(\\d+).*", "$1"));
        if (totalPages > 1) {
            Utility_Functions.xScrollIntoView(ownDriver, PickingInProcessPage.nextPageArrow);
            click(PickingInProcessPage.nextPageArrow);
        } else {
            System.out.println("Next Page not Present");
        }
    }

    public void moveToPreviousPage() {
        String page = Utility_Functions.getText(ownDriver, PickingInProcessPage.totalPages);
        int totalPages = Integer.parseInt(page.replaceFirst(".*?(\\d+).*", "$1"));
        if (totalPages > 1) {
            Utility_Functions.xScrollIntoView(ownDriver, PickingInProcessPage.previousPageArrow);
            click(PickingInProcessPage.previousPageArrow);
        } else {
            System.out.println("This is the first page.");
        }
    }

    public void moveToLastPage() {
        String page = Utility_Functions.getText(ownDriver, PickingInProcessPage.totalPages);
        int totalPages = Integer.parseInt(page.replaceFirst(".*?(\\d+).*", "$1"));
        if (totalPages > 1) {
            Utility_Functions.xScrollIntoView(ownDriver, PickingInProcessPage.lastPageArrow);
            click(PickingInProcessPage.lastPageArrow);

        } else {
            System.out.println("Next Page not Present");
        }
    }

    public void moveToFirstPage() {
        String page = Utility_Functions.getText(ownDriver, PickingInProcessPage.totalPages);
        int totalPages = Integer.parseInt(page.replaceFirst(".*?(\\d+).*", "$1"));
        if (totalPages > 1) {
            Utility_Functions.xScrollIntoView(ownDriver, PickingInProcessPage.firstPageArrow);
            click(PickingInProcessPage.firstPageArrow);

        } else {
            System.out.println("This is the first page ");
        }
    }

    public void verifyPagination() {
        // move to the next page
        commonObj.validateElementExists(PickingInProcessPage.disabledFirstPageArrow, "First Page arrow is disabled");
        commonObj.validateElementExists(PickingInProcessPage.disabledPreviousPageArrow, "Previous Page arrow is disabled");
        int page = Integer.parseInt(Utility_Functions.getText(ownDriver, PickingInProcessPage.currentPageNumber).trim());
        moveToNextPage();
        Utility_Functions.timeWait(2);
        int newPage = Integer.parseInt(Utility_Functions.getText(ownDriver, PickingInProcessPage.currentPageNumber).trim());
        Assert.assertTrue(newPage > page);
        //move back to the previous page
        moveToPreviousPage();
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.disabledFirstPageArrow, "First Page arrow is disabled");
        commonObj.validateElementExists(PickingInProcessPage.disabledPreviousPageArrow, "Previous Page arrow is disabled");
        //move to last page
        moveToLastPage();
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.disabledLastPageArrow, "Last Page arrow is disabled");
        commonObj.validateElementExists(PickingInProcessPage.disabledNextPageArrow, "Next Page arrow is disabled");
        //move to previous Page
        int lastPage = Integer.parseInt(Utility_Functions.getText(ownDriver, PickingInProcessPage.currentPageNumber).trim());
        moveToPreviousPage();
        Utility_Functions.timeWait(2);
        int prevPage = Integer.parseInt(Utility_Functions.getText(ownDriver, PickingInProcessPage.currentPageNumber).trim());
        Assert.assertTrue(lastPage > prevPage);
    }

    public void changeViewOfRecords() {
        String xpath = "//table[@role='grid']//tbody/tr/td[1]/a";
        List<WebElement> elements = ownDriver.findElements(By.xpath(xpath));
        Assert.assertTrue(elements.size() == 10);
        click(PickingInProcessPage.pageCount15);
        Utility_Functions.timeWait(2);
        elements = ownDriver.findElements(By.xpath(xpath));
        Assert.assertTrue(elements.size() == 15);
        click(PickingInProcessPage.pageCount30);
        Utility_Functions.timeWait(2);
        elements = ownDriver.findElements(By.xpath(xpath));
        Assert.assertTrue(elements.size() == 30);
    }

    public void verifyLoadPickQpopUp() {
        click(PickingInProcessPage.loadPickQLink);
        Utility_Functions.timeWait(2);
        String actual = commonObj.getText(PickingInProcessPage.loadPickQpopUpHeader);
        commonObj.validateContainsText("LOAD PICK QUEUE", actual, "Validating LOAD PICK QUEUE pop-up header");
        actual = commonObj.getText(PickingInProcessPage.pickQPopUpBodyMsg1);
        commonObj.validateContainsText("Select a date below to load shipments into the Pick Queue.", actual, "Validating pop-up message body");
        actual = commonObj.getText(PickingInProcessPage.pickQPopUpBodyMsg2);
        commonObj.validateContainsText("You may schedule up to 7 days from today.", actual, "Validating pop-up message body");

        actual = commonObj.getText(PickingInProcessPage.processShipDateThroughLabel);
        commonObj.validateContainsText(" Process Ship Dates Through", actual, "Validating pop-up message body");
        commonObj.validateElementExists(PickingInProcessPage.loadPickQCancelButton, "Validating Cancel Button");
        commonObj.validateElementExists(PickingInProcessPage.loadPickQRunButton, "Validating Run Button");
        click(PickingInProcessPage.loadPickQCancelButton);
    }

    public void verifyReturnToPickQ() {
        click(PickingInProcessPage.shipViaTableHeader);
        Utility_Functions.timeWait(4);
        commonObj.validateElementExists(PickingInProcessPage.returnToPickQPriorityLink, "Validating Return to Pick Queue Priority Link");
        click(PickingInProcessPage.shipViaTableHeader);
        Utility_Functions.timeWait(4);
        String col1Val = getText(PickingInProcessPage.shipDateFirstRowVal);
        click(PickingInProcessPage.returnToPickQPriorityLink);
        Utility_Functions.timeWait(4);
        String col2Val = getText(PickingInProcessPage.shipDateFirstRowVal);
        Assert.assertTrue(!col1Val.equals(col2Val));

    }

    public void verifyPercentCompleteCalculation() {
        String totalLines = ownDriver.findElement(By.xpath("//label[text()='Open / Total Lines']/following-sibling::p")).getText();
        String[] ch = totalLines.split("/");
        int open = Integer.parseInt(ch[0].trim());
        int total = Integer.parseInt(ch[1].trim());
        double val = (total - open) * 100;
        double per = val / total;
        int percent = (int) Math.round(per);
        String attribute[] = ownDriver.findElement(By.xpath("//div[@role='progressbar']")).getAttribute("style").split(":");
        String value = attribute[1].replace("%", "").trim();
        int getPercent = Integer.parseInt(value.replace(";", ""));
        Assert.assertTrue(percent == getPercent);
    }

    public void clickOnShipIDWithHalfCompleted() {
        Utility_Functions.xScrollIntoView(ownDriver, PickingInProcessPage.nextPageArrow);
        click(PickingInProcessPage.pageCount30);
        Utility_Functions.timeWait(2);
        String attribute = ownDriver.findElement(By.xpath("//p[text()='Percent Complete']/ancestor::th[@role='columnheader']")).getAttribute("aria-sort");
        if (attribute.equalsIgnoreCase("none")) {
            click(PickingInProcessPage.PercentCompleteTableHeader);
            Utility_Functions.timeWait(5);
            click(PickingInProcessPage.PercentCompleteTableHeader);
            Utility_Functions.timeWait(5);
        }
        click(PickingInProcessPage.percentageEquals50);
        Utility_Functions.timeWait(4);
    }

    public void verifyStatusForPicked() {
        click(PickingInProcessPage.expandAllLink);
        Utility_Functions.timeWait(2);
        String plannedPicked = getText(PickingInProcessPage.pickedQtyPlannedValue).trim();
        String remainingPicked = getText(PickingInProcessPage.pickedQtyRemainingValue).trim();
        int planned = Integer.parseInt(plannedPicked);
        int remaining = Integer.parseInt(remainingPicked);
        Assert.assertTrue(remaining == 0);
        Assert.assertTrue(planned > remaining);
    }

    public void verifyStatusForNotPicked() {
        String plannedNonPicked = getText(PickingInProcessPage.nonPickedQtyPlannedValue).trim();
        String remainingNonPicked = getText(PickingInProcessPage.nonPickedQtyRemainingValue).trim();
        int planned = Integer.parseInt(plannedNonPicked);
        int remaining = Integer.parseInt(remainingNonPicked);
        Assert.assertTrue(planned == remaining);
        click(PickingInProcessPage.collapseAll);
    }

    public void verifyPrintDocumentLabelsUI() {
        click(PickingInProcessPage.printDocumentButton);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.printDocumentPopUpHeader, "[Print Documents and Labels] header is present");
        commonObj.validateElementExists(PickingInProcessPage.popUpLabelShipment, "[Shipment] label is present");
        commonObj.validateElementExists(PickingInProcessPage.popUpLabelCustomerName, "[Customer Name] label is present");
        commonObj.validateElementExists(PickingInProcessPage.packingListCheckbox, "[Picking List] checkbox is present");
        commonObj.validateElementExists(PickingInProcessPage.pickListCheckbox, "[Pick List] checkbox is present");
        commonObj.validateElementExists(PickingInProcessPage.shippingLabelCheckbox, "[shipping Label] checkbox is present");
        commonObj.validateElementExists(PickingInProcessPage.printDocumentPopUpCancel, "[Cancel] button is present ");
        commonObj.validateElementExists(PickingInProcessPage.printDocumentPopUpPrint, "[Print] button is present");
        click(PickingInProcessPage.printDocumentPopUpCancel);
    }

    public void clickOnPickStatus() {
        click(PickingInProcessPage.pickStatusLink);
        Utility_Functions.timeWait(4);
    }

    public void removeStagingDetails() {
        click(PickingInProcessPage.stagedShippment);
        Utility_Functions.timeWait(3);
        click(PickingInProcessPage.stagingButton);
        Utility_Functions.timeWait(3);
        Utility_Functions.waitForElementVisible(ownDriver, PickingInProcessPage.stagingPopUpHeader, 3);
        commonObj.validateElementExists(PickingInProcessPage.stagingLocationHeader, "[Staging Location] table header present");
    }

    public void navigateToWarehouseSettings() {
        click(PickingInProcessPage.settingsIcon);
        waitForVisible(PickingInProcessPage.warehouseSettingsLabel);
        commonObj.validateElementExists(PickingInProcessPage.warehouseSettingsLabel, "[Warehouse Settings] header is present");
        commonObj.validateElementExists(PickingInProcessPage.displaySettingsLabel, "[Display Settings] label is present");
        commonObj.validateElementExists(PickingInProcessPage.selectThemeLabel, "[ Select Theme: ] label is present");
        commonObj.validateElementExists(PickingInProcessPage.highContrastLabel, "[High Contrast:] label is present");
        click(PickingInProcessPage.warehouseSettingsLabel);
        Utility_Functions.waitForElementVisible(ownDriver, PickingInProcessPage.warehouseSettingsPageHeader, 3);
    }

    public void verifyWarehouseSettingsPageUI() {
        commonObj.validateElementExists(PickingInProcessPage.globalSettingsLink, "[ Global Settings ] label is present");
        commonObj.validateElementExists(PickingInProcessPage.manualPickCompletionLink, "[ Manual Pick Completion ] label is present");
        commonObj.validateElementExists(PickingInProcessPage.shipViaLink, "[Ship Via] label is present");
        commonObj.validateElementExists(PickingInProcessPage.pickQueueScheduleLink, "[Pick Queue Schedule] label is present");
        commonObj.validateElementExists(PickingInProcessPage.holidayEventScheduleLink, "[Holiday & Event Schedule] label is present");
        commonObj.validateElementExists(PickingInProcessPage.packingUnitsTab, "[Packaging Units] label is present");
        commonObj.validateElementExists(PickingInProcessPage.documentsAndPrintingTab, "[ Documents & Printing ] label is present");
        commonObj.validateElementExists(PickingInProcessPage.documentsAndPrinting, "[ Label Printer Format ] label is present");
        commonObj.validateElementExists(PickingInProcessPage.subHeadingText, "[These settings control how the warehousing applications function. Changes made here affect all applications in the warehousing suite.] label is present");
        commonObj.validateElementExists(PickingInProcessPage.manualPickCompletionLabel, "[ Manual Pick Completion ] label is present");
        commonObj.validateElementExists(PickingInProcessPage.standardCheckbox, "[ Standard ] checkbox is present");
        commonObj.validateElementExists(PickingInProcessPage.batchCheckbox, "[ Batch ] checkbox is present");
        commonObj.validateElementExists(PickingInProcessPage.individualCheckbox, "[ Individual ] checkbox is present");
    }

    public void navigateToShipViaScreen() {
        click(PickingInProcessPage.shipViaLink);
        Utility_Functions.timeWait(2);
        waitForVisible(PickingInProcessPage.shipViaPageHeader);
    }

    public void verifyShipViaScreenUI() {
        commonObj.validateElementExists(PickingInProcessPage.shipViaPageHeader, "[ Ship Via (Shipping Methods) ] label is present");
        commonObj.validateElementExists(PickingInProcessPage.shipViaPageSubHeader, "[Sort these by the priority you would like their orders to have for picking.] text is present");
        commonObj.validateElementExists(PickingInProcessPage.pickPriorityColumnHeader, "Pick Priority Column is present");
        commonObj.validateElementExists(PickingInProcessPage.nameColumnHeader, "Name Column is present");
        commonObj.validateElementExists(PickingInProcessPage.codeColumnHeader, "Code Column is present");
        commonObj.validateElementExists(PickingInProcessPage.suggestedStagingAreaColumnHeader, "Suggested Staging Area Column is present");
        commonObj.validateElementExists(PickingInProcessPage.stagingRequiredColumnHeader, "Staging Required Column is present");
        commonObj.validateElementExists(PickingInProcessPage.printRoutingListColumnHeader, "Print Routing List Column is present");
        commonObj.validateElementExists(PickingInProcessPage.packingListPrinterColumnHeader, "Packing List Printer Column is present");
        commonObj.validateElementExists(PickingInProcessPage.rtiCriteriaColumnHeader, "RTI Criteria Column is present");
        commonObj.validateElementExists(PickingInProcessPage.shipViaPageButton, "New Ship Via button is present");
    }

    public void modifyPriorityInShipViaScreen() {
        String pickPriority1Text = getText(PickingInProcessPage.pickPriority1Text).trim();
        String pickPriority2Text = getText(PickingInProcessPage.pickPriority2Text).trim();
        click(PickingInProcessPage.pickPriority1);
        Utility_Functions.xClearAndSendKeys(ownDriver, ownDriver.findElement(By.xpath("//input[@ng-reflect-model='1']")), "4");
        click(PickingInProcessPage.pickPriorityColumnHeader);
        Utility_Functions.timeWait(2);
        String newPickPriority1Text = getText(PickingInProcessPage.pickPriority1Text).trim();
        Assert.assertTrue(pickPriority2Text.equals(newPickPriority1Text));
        Assert.assertTrue(pickPriority1Text.equals(getText(PickingInProcessPage.pickPriority4Text).trim()));

        pickPriority1Text = getText(PickingInProcessPage.pickPriority1Text).trim();
        pickPriority2Text = getText(PickingInProcessPage.pickPriority2Text).trim();
        Actions action = new Actions(ownDriver.getWebDriver());
        WebElement drag = ownDriver.findElement(By.xpath("//input[@ng-reflect-model='1']/parent::div/preceding-sibling::div//i"));
        WebElement drop = ownDriver.findElement(By.xpath("//input[@ng-reflect-model='5']/parent::div/preceding-sibling::div//i"));
        action.dragAndDrop(drag, drop).build().perform();
        Utility_Functions.timeWait(2);
        Assert.assertTrue(pickPriority1Text.equals(getText(PickingInProcessPage.pickPriority4Text).trim()));
        Assert.assertTrue(pickPriority2Text.equals(getText(PickingInProcessPage.pickPriority1Text).trim()));
    }

    public void createNewShipMethod() {
        Utility_Functions.timeWait(4);
        click(PickingInProcessPage.shipViaPageButton);
        waitForVisible(PickingInProcessPage.shipViaDetailsPageHeader);
        String name = Utility_Functions.getRandomName().toUpperCase();
        sendKeys(PickingInProcessPage.nameTextField, name);
        sendKeys(PickingInProcessPage.codeTextField, "3");
        click(PickingInProcessPage.buttonSave);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipViaPageHeader, "[ Ship Via (Shipping Methods) ] label is present");
        isDisplayed(ownDriver.findElement(By.xpath("//label[text()='" + name + "']")));
    }

    public void navigateToPickQueueScheduleScreen() {
        click(PickingInProcessPage.pickQueueScheduleLink);
        Utility_Functions.timeWait(2);
        waitForVisible(PickingInProcessPage.pickQueueSchedulePageHeader);
    }

    public void verifyPickQueueScheduleScreenUI() {
        commonObj.validateElementExists(PickingInProcessPage.pickQueueSchedulePageHeader, "[ PickQueueSchedulePageHeader label is present");
        commonObj.validateElementExists(PickingInProcessPage.pickQueueSchedulePageSubHeader, "For each day of the week, select what orders you would like to pick based on their shipping day text is present");
        commonObj.validateElementExists(PickingInProcessPage.schedulePageSundayColumnHeader, "Sunday Column is present");
        commonObj.validateElementExists(PickingInProcessPage.schedulePageMondayColumnHeader, "Monday Column is present");
        commonObj.validateElementExists(PickingInProcessPage.schedulePageTuesdayColumnHeader, "Tuesday Column is present");
        commonObj.validateElementExists(PickingInProcessPage.schedulePageWednesdayColumnHeader, "Wednesday Column is present");
        commonObj.validateElementExists(PickingInProcessPage.schedulePageThursdayColumnHeader, "Thursday Column is present");
        commonObj.validateElementExists(PickingInProcessPage.schedulePageFridayColumnHeader, "Friday Column is present");
        commonObj.validateElementExists(PickingInProcessPage.schedulePageSaturdayColumnHeader, "Saturday Column is present");
        commonObj.validateElementExists(PickingInProcessPage.pickQueueScheduleAddDay, "Add Day link is present");
    }

    public void addingDayInPickQueueScheduleScreen() {
        click(PickingInProcessPage.pickQueueScheduleAddDay);
        Utility_Functions.timeWait(2);
        WebElement days = ownDriver.findElement(By.xpath("(//*[@class='text-center day-add'])[4]/a"));
        String status = days.getAttribute("class");
        if (!status.contains("disabled")) {
            click(PickingInProcessPage.addDayWednesday);
        } else {
            List<WebElement> elements = ownDriver.findElements(By.xpath("//div[@class='container-fluid']/app-day[4]//p[contains(@class,'shipping-day-selected')]"));
            for (int i = 1; i < elements.size(); i++) {
                waitForVisible(PickingInProcessPage.removeDayButton);
                click(PickingInProcessPage.removeDayButton);
                Utility_Functions.timeWait(3);
            }
            click(PickingInProcessPage.addDayWednesday);
        }

        for (int i = 1; i <= 5; i++) {
            waitForVisible(PickingInProcessPage.removeDayButton);
            click(PickingInProcessPage.addDayWednesday);
            Utility_Functions.timeWait(3);
        }
        status = days.getAttribute("class");
        Assert.assertTrue(status.contains("disabled"));

        commonObj.verifyElementContainsText(PickingInProcessPage.pickQScheduleMessage, "Pick Queue Schedule successfully updated.", "text mismatch");
    }

    public void navigateToHolidayEventScheduleScreen() {
        click(PickingInProcessPage.holidayEventScheduleLink);
        Utility_Functions.timeWait(2);
        waitForVisible(PickingInProcessPage.holidayEventSchedulePageHeader);
    }

    public void verifyHolidayEventScheduleScreenUI() {
        commonObj.validateElementExists(PickingInProcessPage.holidayEventSchedulePageHeader, "[ Holiday & Event Schedule Page Header label is present");
        commonObj.validateElementExists(PickingInProcessPage.holidayEventSchedulePageSubHeader, " 'Orders shipping through the selected date will be added to the pick queue for the selected date range. ' text is present");
        commonObj.validateElementExists(PickingInProcessPage.holidayEventScheduleNameColumnHeader, "Name Column is present under Holidays section");
        commonObj.validateElementExists(PickingInProcessPage.holidayEventScheduleDateColumnHeader, "Date Column is present under Holidays section");
        commonObj.validateElementExists(PickingInProcessPage.holidayEvntSchOrdersShippingThroughColHeader, "Orders Shipping Through Column is present under Holidays section");
        commonObj.validateElementExists(PickingInProcessPage.holidayEvntSchAddToQueueColHeader, "Add to Queue Column is present under Holidays section");
        commonObj.validateElementExists(PickingInProcessPage.holidayEventScheduleActionsColumnHeader, "Actions Column is present under Holidays section");
        String getYear = getText(PickingInProcessPage.currentYear);
        String currentYr = Integer.toString(getCurrentYear());
        Assert.assertTrue(getYear.equals(currentYr));

        commonObj.validateElementExists(PickingInProcessPage.previousYearArrow, "[ Move to previous Year arrow is present");
        String status = getAttribute(PickingInProcessPage.previousYearArrow, "class");
        Assert.assertTrue(!status.contains("disable"));
        status = getAttribute(PickingInProcessPage.nextYearArrow, "class");
        Assert.assertTrue(status.contains("disable"));

        commonObj.validateElementExists(PickingInProcessPage.eventNameColumnHeader, "Name Column is present under Event section");
        commonObj.validateElementExists(PickingInProcessPage.eventDateColumnHeader, "Date Column is present under Event section");
        commonObj.validateElementExists(PickingInProcessPage.eventOrdersShippingThroughColHeader, "Orders Shipping Through Column is present under Event section");
        commonObj.validateElementExists(PickingInProcessPage.eventAddToQueueColHeader, "Add to Queue Column is present under Event section");
        commonObj.validateElementExists(PickingInProcessPage.eventActionsColumnHeader, "Actions Column is present under Event section");
        commonObj.validateElementExists(PickingInProcessPage.addEventButton, "Add new event button is present under Event section");

    }

    public int getCurrentYear() {
        Date dt = new Date();
        int year = 1900 + dt.getYear();
        return year;
    }

    public void addNewEvent() {
        click(PickingInProcessPage.addEventButton);
        Utility_Functions.xScrollIntoView(ownDriver, PickingInProcessPage.nameTextBox);
        waitForVisible(PickingInProcessPage.nameTextBox);
        sendKeys(PickingInProcessPage.nameTextBox, Utility_Functions.getRandomName());
        sendKeys(PickingInProcessPage.dateTextBox, Utility_Functions.xGetCurrentDate("MM/dd/yyyy"));
        sendKeys(PickingInProcessPage.shippingThroughTextBox, Utility_Functions.xGetCurrentDate("MM/dd/yyyy"));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String dt = dtf.format(now);
        sendKeys(PickingInProcessPage.addToQueueStartTextBox, "01/" + dt);
        sendKeys(PickingInProcessPage.addToQueueEndTextBox, Utility_Functions.xGetCurrentDate("MM/dd/yyyy"));
        click(PickingInProcessPage.saveEventIcon);
        waitForVisible(PickingInProcessPage.saveSuccessMessage);
        commonObj.validateElementExists(PickingInProcessPage.saveSuccessMessage, "[Settings successfully saved.] message is displayed]");

    }

    public void deleteEvent() {
        List<WebElement> elements = ownDriver.findElements(By.xpath("//div[contains(@class,'group event-data')]//a[@tooltip='Delete']"));
        int last = elements.size();
        click(ownDriver.findElement(By.xpath("(//div[contains(@class,'group event-data')]//a[@tooltip='Delete'])[" + last + "]")));
        waitForVisible(PickingInProcessPage.deleteSuccessMessage);
        commonObj.validateElementExists(PickingInProcessPage.deleteSuccessMessage, "[Event successfully deleted.] message is displayed]");
    }

    public void editEventSuccessfully() {
        List<WebElement> elements = ownDriver.findElements(By.xpath("//div[contains(@class,'group event-data')]//a[@tooltip='Edit']"));
        int last = elements.size();
        click(ownDriver.findElement(By.xpath("(//div[contains(@class,'group event-data')]//a[@tooltip='Edit'])[" + last + "]")));
        String start = getAttribute(PickingInProcessPage.addToQueueStartTextBox, "ng-reflect-model");
        String[] strtDt = start.split("/");
        int strt = Integer.parseInt(strtDt[0].replace("0", ""));
        int newDay = strt + 1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String dt = dtf.format(now);
        String startDate = "0" + newDay + "/" + dt;
        click(PickingInProcessPage.addToQueueStartTextBox);
        clearText(PickingInProcessPage.addToQueueStartTextBox);
        sendKeys(PickingInProcessPage.addToQueueStartTextBox, startDate);
        String tomorrow = (now.minusDays(1)).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        click(PickingInProcessPage.addToQueueEndTextBox);
        clearText(PickingInProcessPage.addToQueueEndTextBox);
        sendKeys(PickingInProcessPage.addToQueueEndTextBox, tomorrow);
        click(PickingInProcessPage.saveEventIcon);
        commonObj.validateElementExists(PickingInProcessPage.updateSuccessMessage, "[Settings successfully updated.] message is displayed]");
    }

    public void verifyPreviousYearHolidays() {
        click(PickingInProcessPage.previousYearArrow);
        Utility_Functions.timeWait(2);
        String getDate = getText(PickingInProcessPage.eventDateFieldValue);
        String cYear = getText(PickingInProcessPage.currentYear);
        Assert.assertTrue(getDate.contains(cYear));
        click(PickingInProcessPage.nextYearArrow);
        Utility_Functions.timeWait(2);
        String attribute = getAttribute(PickingInProcessPage.nextYearArrow, "class");
        Assert.assertTrue(attribute.contains("disable"));
    }

    public void deleteExistingRecords() {
        List<WebElement> elements = ownDriver.findElements(By.xpath("//div[contains(@class,'group event-data')]//a[@tooltip='Delete']"));
        for (WebElement ele : elements) {
            click(ownDriver.findElement(By.xpath("//div[contains(@class,'group event-data')]//a[@tooltip='Delete']")));
            Utility_Functions.timeWait(2);
        }
    }

    public void verifyMandatoryFieldMessages() {
        List<WebElement> elements = ownDriver.findElements(By.xpath("//div[contains(@class,'group event-data')]//a[@tooltip='Edit']"));
        int last = elements.size();
        click(ownDriver.findElement(By.xpath("(//div[contains(@class,'group event-data')]//a[@tooltip='Edit'])[" + last + "]")));
        Utility_Functions.timeWait(2);
        sendKeys(PickingInProcessPage.nameTextBox, Utility_Functions.getRandomName());
        click(PickingInProcessPage.saveEventIcon);
        commonObj.validateElementExists(PickingInProcessPage.startDateErrorMessage, "[Start date already exists.] message is displayed");
        sendKeys(PickingInProcessPage.addToQueueStartTextBox, "02/02/2023");
        click(PickingInProcessPage.saveEventIcon);
        commonObj.validateElementExists(PickingInProcessPage.allDatesErrorMessage, "[All date fields must be filled.] message is displayed");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.cancelEventIcon);
        waitForVisible(ownDriver.findElement(By.xpath("(//div[contains(@class,'group event-data')]//a[@tooltip='Edit'])[" + last + "]")));
    }

    public void navigateToPackagingUnitsScreen() {
        click(PickingInProcessPage.packingUnitsTab);
        Utility_Functions.timeWait(3);
        waitForVisible(PickingInProcessPage.packagingUnitsPageHeader);
    }

    public void verifyPackagingUnitsScreenUI() {
        commonObj.validateElementExists(PickingInProcessPage.packagingUnitsPageHeader, "Packaging Units Page Header is present ");
        commonObj.validateElementExists(PickingInProcessPage.packagingUnitsPageSubHeader, "Packaging Units Page Sub Header is present");
        commonObj.validateElementExists(PickingInProcessPage.listOrdersInputField, "List Order input field is present");
        commonObj.validateElementExists(PickingInProcessPage.unitLabelField, "Unit Label input field is present");
        commonObj.validateElementExists(PickingInProcessPage.editListOrderIcon, "Pencil icon is present for Active packaging units");
        commonObj.validateElementExists(PickingInProcessPage.trashListOrderIcon, "Trash icon is present for Active packaging units");
        commonObj.validateElementExists(PickingInProcessPage.plusListOrderIcon, "Plus(+) icon is present for Active packaging units");
        click(PickingInProcessPage.editListOrderIcon);
        sendKeys(PickingInProcessPage.unitLabelInputField, "3");
        commonObj.validateElementExists(PickingInProcessPage.rightListOrderIcon, "Right(Tick) icon is present for Active packaging units");
        commonObj.validateElementExists(PickingInProcessPage.closeListOrderIcon, "Close icon is present for Active packaging units");
    }

    public void verifyPackagingUnitPriority() {
        String labelBefore = getText(PickingInProcessPage.firstUnitLabel);
        Actions action = new Actions(ownDriver.getWebDriver());
        WebElement drag = ownDriver.findElement(By.xpath("(//span[@class='box-1 border-right'])[1]/i"));
        WebElement drop = ownDriver.findElement(By.xpath("(//span[@class='box-1 border-right'])[3]/i"));
        action.dragAndDrop(drag, drop).build().perform();
        Utility_Functions.timeWait(2);
        String labelAfter = getText(PickingInProcessPage.firstUnitLabel);
        Assert.assertTrue(!labelBefore.contains(labelAfter));
        sendKeys(PickingInProcessPage.listOrdersInputField, "3");
        Utility_Functions.timeWait(3);
        String postChange = getText(PickingInProcessPage.firstUnitLabel);
        Assert.assertTrue(!postChange.contains(labelAfter));
    }

    public void addAndEditPackingUnits() {
        click(PickingInProcessPage.plusListOrderIcon);
        waitForVisible(PickingInProcessPage.unitLabelInputField);
        waitForVisible(PickingInProcessPage.savePackingUnitsIcon);
        waitForVisible(PickingInProcessPage.cancelPackingUnitsIcon);
        sendKeys(PickingInProcessPage.unitLabelInputField, Utility_Functions.getRandomName());
        click(PickingInProcessPage.savePackingUnitsIcon);
        commonObj.validateElementExists(PickingInProcessPage.packingUnitsSuccessMsg, "[Packaging Units successfully updated.] message is displayed");
        Utility_Functions.timeWait(3);
        click(PickingInProcessPage.editListOrder);
        waitForVisible(PickingInProcessPage.unitLabelInputField);
        waitForVisible(PickingInProcessPage.savePackingUnitsIcon);
        waitForVisible(PickingInProcessPage.cancelPackingUnitsIcon);
        sendKeys(PickingInProcessPage.unitLabelInputField, Utility_Functions.getRandomName());
        click(PickingInProcessPage.savePackingUnitsIcon);
        commonObj.validateElementExists(PickingInProcessPage.packingUnitsSuccessMsg, "[Packaging Units successfully updated.] message is displayed");
        Utility_Functions.timeWait(3);
        click(PickingInProcessPage.deleteListOrder);
        commonObj.validateElementExists(PickingInProcessPage.packingUnitsSuccessMsg, "[Packaging Units successfully deleted.] message is displayed");
    }

    public void verifyListOrderAndUnitLabel() {
        String getVal = getAttribute(PickingInProcessPage.listOrdersInputField, "ng-reflect-model");
        clearText(PickingInProcessPage.listOrdersInputField);
        sendKeys(PickingInProcessPage.listOrdersInputField, "@");
        click(PickingInProcessPage.unitLabelField);
        Utility_Functions.timeWait(1);
        String getAfterVal = getAttribute(PickingInProcessPage.listOrdersInputField, "ng-reflect-model");
        Assert.assertTrue(getVal.equals(getAfterVal));
        clearText(PickingInProcessPage.listOrdersInputField);
        sendKeysAndEnter(PickingInProcessPage.listOrdersInputField, "-3", "Entered negative value");
        Utility_Functions.timeWait(1);
        getVal = getAttribute(PickingInProcessPage.listOrdersInputField, "ng-reflect-model");
        Assert.assertTrue(getVal.equals(getAfterVal));
        clearText(PickingInProcessPage.listOrdersInputField);
        click(PickingInProcessPage.unitLabelField);
        Utility_Functions.timeWait(1);
        getAfterVal = getAttribute(PickingInProcessPage.listOrdersInputField, "ng-reflect-model");
        Assert.assertTrue(getVal.equals(getAfterVal));
    }

    public void verifyLeaveWithoutSavingPopUp() {
        click(PickingInProcessPage.editListOrderIcon);
        waitForVisible(PickingInProcessPage.unitLabelInputField);
        waitForVisible(PickingInProcessPage.savePackingUnitsIcon);
        waitForVisible(PickingInProcessPage.cancelPackingUnitsIcon);
        sendKeys(PickingInProcessPage.unitLabelInputField, Utility_Functions.getRandomName());
        click(PickingInProcessPage.pickQueueScheduleLink);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.leaveWithouSavingPopUpHeader, "[Leave Without Saving] pop-up is displayed");
        commonObj.validateElementExists(PickingInProcessPage.leaveWithoutSaveCancelBtn, "[Cancel] button is displayed");
        commonObj.validateElementExists(PickingInProcessPage.leaveWithoutSaveContBtn, "[Continue] button is displayed");
        commonObj.validateElementExists(PickingInProcessPage.leaveWithouSavingPopUpMsg, "[Changes you made may not be saved] message is displayed");
        click(PickingInProcessPage.leaveWithoutSaveCancelBtn);
        waitForVisible(PickingInProcessPage.unitLabelInputField);
        click(PickingInProcessPage.pickQueueScheduleLink);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.leaveWithoutSaveContBtn);
        commonObj.validateElementExists(PickingInProcessPage.pickQueueSchedulePageHeader, "[Pick Queue Schedule] header is displayed");

    }

    public void navigateToDocumentsAndPrintingScreen() {
        click(PickingInProcessPage.documentsAndPrintingTab);
        Utility_Functions.timeWait(3);
        waitForVisible(PickingInProcessPage.documentsAndPrintingPageHeader);
    }

    public void verifyDocumentsAndPrintingScreenUI() {
        commonObj.validateElementExists(PickingInProcessPage.documentsAndPrintingPageHeader, "Documents & Printing Page Header is present ");
        commonObj.validateElementExists(PickingInProcessPage.documentsAndPrintingPageSubHeader, "Documents & Printing Page Sub Header is present");
        commonObj.validateElementExists(PickingInProcessPage.labelPrinterFormat, "Label Printer Format section is present");
        commonObj.validateElementExists(PickingInProcessPage.addPrinterLink, "Add Printer hyperlink is present");
        commonObj.validateElementExists(PickingInProcessPage.printerDesignationColHeader, "Printer Designation Column Header is present");
        commonObj.validateElementExists(PickingInProcessPage.printerNameColHeader, "Printer Name Column Header is present");
        commonObj.validateElementExists(PickingInProcessPage.printerFormatColHeader, "Printer Format Column Header is present");
        commonObj.validateElementExists(PickingInProcessPage.labelSizeColHeader, "Label Size Column Header is present");

    }

    public void verifyPrinterDetailsPopupUI() {
        click(PickingInProcessPage.addPrinterLink);
        Utility_Functions.timeWait(3);
        waitForVisible(PickingInProcessPage.printerDetailsPopupHeader);
        commonObj.validateElementExists(PickingInProcessPage.printerDesignationField, "Printer Designation Dropdown field is present in the Printer Details pop up ");
        commonObj.validateElementExists(PickingInProcessPage.printerFormatField, "Printer Format Dropdown field is present in the Printer Details pop up ");
        commonObj.validateElementExists(PickingInProcessPage.labelSizeField, "Label Size Dropdown field is present in the Printer Details pop up ");
        commonObj.validateElementExists(PickingInProcessPage.printerNameField, "Printer Name Text field is present in the Printer Details pop up ");
        commonObj.validateElementExists(PickingInProcessPage.saveButton, "Save Button is present in the Printer Details pop up ");
        commonObj.validateElementExists(PickingInProcessPage.cancelButton, "Cancel Button is present in the Printer Details pop up ");
        commonObj.validateElementExists(PickingInProcessPage.closeButton, "close Button is present in the Printer Details pop up ");
    }

    public void verifyAddingPrinter() {
        click(PickingInProcessPage.addPrinterLink);
        Utility_Functions.timeWait(3);
        waitForVisible(PickingInProcessPage.printerDetailsPopupHeader);
        click(PickingInProcessPage.printerDesignationField);
    }

    public void verifySearchRecordsWithShipmentNo() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String shipment = getText(PickingInProcessPage.shipmentNumber1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, shipment);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipment1 = getText(PickingInProcessPage.shipmentNumber1);
        Assert.assertTrue("Verifying shipment filter", shipment.contentEquals(shipment1));
        click(PickingInProcessPage.removeShipmentFilter);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, "abc");
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
    }

    public void verifySearchRecordsWithCustomer() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String customer = getText(PickingInProcessPage.customer1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, customer);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String customer1 = getText(PickingInProcessPage.customer1);
        Assert.assertTrue("Verifying customer filter", customer.contentEquals(customer1));
        click(PickingInProcessPage.removeCustomerFilter);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, "abc987");
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
    }

    public void verifySearchRecordsWithAssignedTo() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.assignedRFTableHeader);
        String assignedRF = getText(PickingInProcessPage.assignedRF1);
        char[] firstChar = assignedRF.toCharArray();

        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.assignedRFFilterTextField);
        sendKeys(PickingInProcessPage.assignedRFFilterTextField, String.valueOf(firstChar[0]));
        Utility_Functions.timeWait(3);
        click(PickingInProcessPage.assignedToFilterValue);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String assignedRF1 = getText(PickingInProcessPage.assignedRF1);
        Assert.assertTrue("Verifying Assigned To filter", assignedRF.contentEquals(assignedRF1));
        click(PickingInProcessPage.removeassignedRFFilter);
    }

    public void verifySearchRecordsWithShipVia() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String shipvia = getText(PickingInProcessPage.shipVia1).trim();
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='" + shipvia + "']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipVia2 = getText(PickingInProcessPage.shipVia1);
        Assert.assertTrue("Verifying customer filter", shipvia.contentEquals(shipVia2));
        click(PickingInProcessPage.removeShipViaFilter);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele1 = ownDriver.findElement(By.xpath("//li[@aria-label='C']//div[contains(@class,'chkbox')]/div"));
        click(ele1);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
    }

    public void verifySearchRecordsWithTruckName() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameTruck1);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page with Truck Name");
        click(PickingInProcessPage.removeTruckNameFilter);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameSrs);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
    }

    public void verifySearchRecordsWithPickStatus() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page with Pick Status [Staged]");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusAll);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page with Pick Status[All]");
    }

    public void verifySearchRecordsWithStagedStatusPrintY() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueY);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.stagedValue, "Shipment Details is present in the Picking In Process page with Pick Status [Staged]");
        commonObj.validateElementExists(PickingInProcessPage.pickStatusLink, "Pick Status Link present at the top Right corner");
        commonObj.validateElementExists(PickingInProcessPage.packListPrintStatusLink, "Pack List Print Status Link present at the top Right corner");
        commonObj.validateElementExists(PickingInProcessPage.clearFilterLink, "Clear Filter Link present at the top Right corner");
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithStagedStatusPrintN() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueN);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.stagedValue, "Shipment Details is present in the Picking In Process page with Pick Status [Staged]");
        commonObj.validateElementExists(PickingInProcessPage.pickStatusLink, "Pick Status Link present at the top Right corner");
        commonObj.validateElementExists(PickingInProcessPage.packListPrintStatusLink, "Pack List Print Status Link present at the top Right corner");
        commonObj.validateElementExists(PickingInProcessPage.clearFilterLink, "Clear Filter Link present at the top Right corner");
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithShipDate() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String shipdate = getText(PickingInProcessPage.shipDate);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, shipdate);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipdate1 = getText(PickingInProcessPage.shipDate);
        Assert.assertTrue("Verifying Ship Date filter", shipdate.contentEquals(shipdate1));
        click(PickingInProcessPage.removeShipDateFilter);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, "02/23/2001");
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
    }

    public void verifySearchRecordsWithValidShipmentNoValidCustomer() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String shipment = getText(PickingInProcessPage.shipmentNumber1);
        String customer = getText(PickingInProcessPage.customer1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, shipment);
        sendKeys(PickingInProcessPage.customerFilterTextField, customer);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipment1 = getText(PickingInProcessPage.shipmentNumber1);
        String customer1 = getText(PickingInProcessPage.customer1);
        Assert.assertTrue("Verifying shipment filter", shipment.contentEquals(shipment1));
        Assert.assertTrue("Verifying customer filter", customer.contentEquals(customer1));
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidShipmentNoInvalidCustomer() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String shipment = getText(PickingInProcessPage.shipmentNumber1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, shipment);
        sendKeys(PickingInProcessPage.customerFilterTextField, "abc987");
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);

    }

    public void verifySearchRecordsWithInvalidShipmentNoValidCustomer() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String customer = getText(PickingInProcessPage.customer1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, customer);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, "abc");
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithInvalidShipmentNoInvalidCustomer() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, "abc987");
        sendKeys(PickingInProcessPage.shipmentFilterTextField, "abc");
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(3);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidShipmentNoValidShipVia() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String shipment = getText(PickingInProcessPage.shipmentNumber1);
        String shipvia = getText(PickingInProcessPage.shipVia1).trim();
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, shipment);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='" + shipvia + "']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipment1 = getText(PickingInProcessPage.shipmentNumber1);
        Assert.assertTrue("Verifying shipment filter", shipment.contentEquals(shipment1));
        String shipVia2 = getText(PickingInProcessPage.shipVia1);
        Assert.assertTrue("Verifying Ship Via filter", shipvia.contentEquals(shipVia2));
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }


    public void verifySearchRecordsWithInvalidShipmentNoValidShipVia() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String shipvia = getText(PickingInProcessPage.shipVia1).trim();
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, "abc");
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='" + shipvia + "']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidShipmentNoInvalidShipVia() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String shipment = getText(PickingInProcessPage.shipmentNumber1);
        String shipvia = getText(PickingInProcessPage.shipVia1).trim();
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, shipment);
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameSrs);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='C']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);

    }

    public void verifySearchRecordsWithValidShipmentNoValidTruckName() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameTruck1);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(2);
        String shipment = getText(PickingInProcessPage.shipmentNumber1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, shipment);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page with Truck Name");
        String shipment1 = getText(PickingInProcessPage.shipmentNumber1);
        Assert.assertTrue("Verifying shipment filter", shipment.contentEquals(shipment1));
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithInvalidShipmentNoValidTruckName() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, "abc");
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameTruck1);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidShipmentNoInvalidTruckName() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameTruck1);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        String shipment = getText(PickingInProcessPage.shipmentNumber1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, shipment);
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameSrs);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);

    }

    public void verifySearchRecordsWithValidShipmentNoValidPickStatus() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusInProcess);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipment = getText(PickingInProcessPage.shipmentNumber1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, shipment);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String shipment1 = getText(PickingInProcessPage.shipmentNumber1);
        Assert.assertTrue("Verifying shipment filter", shipment.contentEquals(shipment1));
        click(PickingInProcessPage.removeShipmentFilter);
        click(PickingInProcessPage.pickStatusLink);
    }

    public void verifySearchRecordsWithInvalidShipmentNoValidPickStatus() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, "abc");
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusAll);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        click(PickingInProcessPage.removeShipmentFilter);
        //click(PickingInProcessPage.pickStatusLink);
    }

    public void verifySearchRecordsWithValidShipmentNoValidPickStatusStaged() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueY);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipment = getText(PickingInProcessPage.shipmentNumber1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, shipment);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String shipment1 = getText(PickingInProcessPage.shipmentNumber1);
        Assert.assertTrue("Verifying shipment filter", shipment.contentEquals(shipment1));
        click(PickingInProcessPage.removeShipmentFilter);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithInvalidShipmentNoValidPickStatusStaged() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, "abc");
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidShipmentNoValidStagedPrintN() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueN);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipment = getText(PickingInProcessPage.shipmentNumber1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, shipment);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String shipment1 = getText(PickingInProcessPage.shipmentNumber1);
        Assert.assertTrue("Verifying shipment filter", shipment.contentEquals(shipment1));
        click(PickingInProcessPage.removeShipmentFilter);
        click(PickingInProcessPage.pickStatusLink);
    }

    public void verifySearchRecordsWithInvalidShipmentNoValidPickStatusStagedPrintN() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, "abc");
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidShipmentNoValidShipDate() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String shipment = getText(PickingInProcessPage.shipmentNumber1);
        String shipdate = getText(PickingInProcessPage.shipDate);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, shipment);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, shipdate);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String shipment1 = getText(PickingInProcessPage.shipmentNumber1);
        Assert.assertTrue("Verifying shipment filter", shipment.contentEquals(shipment1));
        String shipdate1 = getText(PickingInProcessPage.shipDate);
        Assert.assertTrue("Verifying Ship Date filter", shipdate.contentEquals(shipdate1));
        click(PickingInProcessPage.removeShipmentFilter);
        click(PickingInProcessPage.removeShipDateFilter);
    }

    public void verifySearchRecordsWithInvalidShipmentNoInvalidShipDate() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, "abc");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, "02/23/2001");
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidCustomerValidAssignedTo() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.assignedRFTableHeader);
        Utility_Functions.timeWait(2);
        String assignedRF = getText(PickingInProcessPage.assignedRF1);
        char[] firstChar = assignedRF.toCharArray();
        String customer = getText(PickingInProcessPage.customer1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, customer);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.assignedRFFilterTextField);
        sendKeys(PickingInProcessPage.assignedRFFilterTextField, String.valueOf(firstChar[0]));
        Utility_Functions.timeWait(3);
        click(PickingInProcessPage.assignedToFilterValue);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String customer1 = getText(PickingInProcessPage.customer1);
        Assert.assertTrue("Verifying customer filter", customer.contentEquals(customer1));
        //String assignedRF1 = getText(PickingInProcessPage.assignedRF1);
        //Assert.assertTrue("Verifying Assigned To filter", assignedRF.contentEquals(assignedRF1));
        click(PickingInProcessPage.clearFilterLink);
        //click(PickingInProcessPage.removeassignedRFFilter);
    }


    public void verifySearchRecordsWithInvalidCustomerValidAssignedTo() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.assignedRFTableHeader);
        String assignedRF = getText(PickingInProcessPage.assignedRF1);
        char[] firstChar = assignedRF.toCharArray();
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, "abc987");
        click(PickingInProcessPage.assignedRFFilterTextField);
        sendKeys(PickingInProcessPage.assignedRFFilterTextField, String.valueOf(firstChar[0]));
        Utility_Functions.timeWait(3);
        click(PickingInProcessPage.assignedToFilterValue);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidCustomerInvalidAssignedTo() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String customer = getText(PickingInProcessPage.customer1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, customer);
        click(PickingInProcessPage.assignedRFFilterTextField);
        sendKeys(PickingInProcessPage.assignedRFFilterTextField, "xyz");
        Utility_Functions.timeWait(3);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);

    }

    public void verifySearchRecordsWithValidCustomerValidShipVia() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String customer = getText(PickingInProcessPage.customer1);
        String shipvia = getText(PickingInProcessPage.shipVia1).trim();
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, customer);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='" + shipvia + "']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String customer1 = getText(PickingInProcessPage.customer1);
        Assert.assertTrue("Verifying customer filter", customer.contentEquals(customer1));
        String shipVia2 = getText(PickingInProcessPage.shipVia1);
        Assert.assertTrue("Verifying Ship Via filter", shipvia.contentEquals(shipVia2));
        click(PickingInProcessPage.removeCustomerFilter);
        click(PickingInProcessPage.removeShipViaFilter);
    }


    public void verifySearchRecordsWithInvalidCustomerValidShipVia() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String shipvia = getText(PickingInProcessPage.shipVia1).trim();
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, "abc987");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='" + shipvia + "']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidCustomerInvalidShipVia() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String customer = getText(PickingInProcessPage.customer1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, customer);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='C']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);

    }

    public void verifySearchRecordsWithValidCustomerValidTruckName() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameTruck1);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(2);
        String customer = getText(PickingInProcessPage.customer1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, customer);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page with Truck Name");
        String customer1 = getText(PickingInProcessPage.customer1);
        Assert.assertTrue("Verifying customer filter", customer.contentEquals(customer1));
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithInvalidCustomerValidTruckName() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, "abc987");
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameTruck1);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidCustomerInvalidTruckName() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String customer = getText(PickingInProcessPage.customer1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, customer);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameSrs);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);

    }

    public void verifySearchRecordsWithValidCustomerValidPickStatus() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusInProcess);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String customer = getText(PickingInProcessPage.customer1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, customer);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String customer1 = getText(PickingInProcessPage.customer1);
        Assert.assertTrue("Verifying customer filter", customer.contentEquals(customer1));
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);

    }

    public void verifySearchRecordsWithInvalidCustomerValidPickStatus() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, "abc987");
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusAll);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
    }

    public void verifySearchRecordsWithValidCustomerValidPickStatusStagedPrintY() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueY);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String customer = getText(PickingInProcessPage.customer1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, customer);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String customer1 = getText(PickingInProcessPage.customer1);
        Assert.assertTrue("Verifying customer filter", customer.contentEquals(customer1));
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithInvalidCustomerValidPickStatusStagedPrintY() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, "abc987");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidCustomerValidStagedPrintN() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueN);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String customer = getText(PickingInProcessPage.customer1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, customer);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String customer1 = getText(PickingInProcessPage.customer1);
        Assert.assertTrue("Verifying customer filter", customer.contentEquals(customer1));
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithInvalidCustomerValidPickStatusStagedPrintN() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, "abc987");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidCustomerValidShipDate() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String customer = getText(PickingInProcessPage.customer1);
        String shipdate = getText(PickingInProcessPage.shipDate);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, customer);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, shipdate);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String customer1 = getText(PickingInProcessPage.customer1);
        Assert.assertTrue("Verifying customer filter", customer.contentEquals(customer1));
        String shipdate1 = getText(PickingInProcessPage.shipDate);
        Assert.assertTrue("Verifying Ship Date filter", shipdate.contentEquals(shipdate1));
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithInvalidCustomerValidShipDate() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String shipdate = getText(PickingInProcessPage.shipDate);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, "abc987");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, shipdate);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidCustomerInvalidShipDate() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String customer = getText(PickingInProcessPage.customer1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.customerFilterTextField, customer);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, "02/23/2001");
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidShipViaValidTruckName() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameTruck1);
        click(PickingInProcessPage.applyFiltersButton);
        String shipvia = getText(PickingInProcessPage.shipVia1).trim();
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='" + shipvia + "']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String shipVia2 = getText(PickingInProcessPage.shipVia1);
        Assert.assertTrue("Verifying shipVia filter", shipvia.contentEquals(shipVia2));
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.removeTruckNameFilter);
        click(PickingInProcessPage.removeShipViaFilter);
    }


    public void verifySearchRecordsWithInvalidShipViaInvalidTruckName() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='C']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameSrs);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidShipViaValidPickStatus() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusInProcess);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipvia = getText(PickingInProcessPage.shipVia1).trim();
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='" + shipvia + "']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String shipVia2 = getText(PickingInProcessPage.shipVia1);
        Assert.assertTrue("Verifying Ship Via filter", shipvia.contentEquals(shipVia2));
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.removeShipViaFilter);
        click(PickingInProcessPage.pickStatusLink);

    }

    public void verifySearchRecordsWithInvalidShipViaInvalidPickStatus() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusInProcess);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='C']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
    }

    public void verifySearchRecordsWithValidShipViaValidPickStatusStagedPrintY() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueY);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipvia = getText(PickingInProcessPage.shipVia1).trim();
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='" + shipvia + "']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String shipVia2 = getText(PickingInProcessPage.shipVia1);
        Assert.assertTrue("Verifying Ship Via filter", shipvia.contentEquals(shipVia2));
        click(PickingInProcessPage.pickStatusLink);
        click(PickingInProcessPage.removeShipViaFilter);

    }

    public void verifySearchRecordsWithInvalidShipViaValidPickStatusStagedPrintY() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='C']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueY);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidShipViaValidPickStatusStagedPrintN() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueN);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipvia = getText(PickingInProcessPage.shipVia1).trim();
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='" + shipvia + "']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String shipVia2 = getText(PickingInProcessPage.shipVia1);
        Assert.assertTrue("Verifying Ship Via filter", shipvia.contentEquals(shipVia2));
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        click(PickingInProcessPage.removeShipViaFilter);

    }

    public void verifySearchRecordsWithInvalidShipViaValidPickStatusStagedPrintN() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='C']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueN);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }
    public void verifySearchRecordsWithValidShipViaValidShipDate() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String shipvia= getText(PickingInProcessPage.shipVia1).trim();
        String shipdate = getText(PickingInProcessPage.shipDate);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='"+shipvia+"']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, shipdate);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page with valid ship via and Ship Date");
        String shipVia2= getText(PickingInProcessPage.shipVia1);
        Assert.assertTrue("Verifying Ship Via filter",shipvia.contentEquals(shipVia2));
        String shipdate1 = getText(PickingInProcessPage.shipDate);
        Assert.assertTrue("Verifying Ship Date filter", shipdate.contentEquals(shipdate1));
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.removeShipViaFilter);
        click(PickingInProcessPage.removeShipDateFilter);
    }

    public void verifySearchRecordsWithInvalidShipViaValidShipDate() {
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String shipdate = getText(PickingInProcessPage.shipDate);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='C']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, shipdate);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(1);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithValidShipViaInvalidShipDate() {
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        String shipvia= getText(PickingInProcessPage.shipVia1).trim();
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipViaFilter);
        WebElement ele = ownDriver.findElement(By.xpath("//li[@aria-label='"+shipvia+"']//div[contains(@class,'chkbox')]/div"));
        click(ele);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, "02/23/2001");
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(1);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.clearFilterLink);
    }
    public void verifySearchRecordsWithValidShipDateValidPickStatus() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusInProcess);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipdate = getText(PickingInProcessPage.shipDate);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, shipdate);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String shipdate1 = getText(PickingInProcessPage.shipDate);
        Assert.assertTrue("Verifying Ship Date filter", shipdate.contentEquals(shipdate1));
        click(PickingInProcessPage.pickStatusLink);
        click(PickingInProcessPage.removeShipDateFilter);
       // click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithInvalidShipDateValidPickStatus() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusInProcess);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, "02/23/2001");
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }
    public void verifySearchRecordsWithValidShipDateValidPickStatusStagedPrintY() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueY);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipdate = getText(PickingInProcessPage.shipDate);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, shipdate);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String shipdate1 = getText(PickingInProcessPage.shipDate);
        Assert.assertTrue("Verifying Ship Date filter", shipdate.contentEquals(shipdate1));
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
        //click(PickingInProcessPage.removeShipDateFilter);

    }

    public void verifySearchRecordsWithInvalidShipDateValidPickStatusStagedPrintY() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueY);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, "02/23/2001");
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }
    public void verifySearchRecordsWithValidShipDateValidPickStatusStagedPrintN() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueN);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipdate = getText(PickingInProcessPage.shipDate);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, shipdate);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String shipdate1 = getText(PickingInProcessPage.shipDate);
        Assert.assertTrue("Verifying Ship Date filter", shipdate.contentEquals(shipdate1));
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);

    }

    public void verifySearchRecordsWithInvalidShipDateValidPickStatusStagedPrintN() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueN);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, "02/23/2001");
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }
    public void verifySearchRecordsWithValidTruckNameValidShipDateValidPickStatus() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusInProcess);
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameTruck1);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipdate = getText(PickingInProcessPage.shipDate);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, shipdate);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        Utility_Functions.timeWait(2);
        String shipdate1 = getText(PickingInProcessPage.shipDate);
        Assert.assertTrue("Verifying Ship Date filter", shipdate.contentEquals(shipdate1));
        click(PickingInProcessPage.pickStatusLink);
        click(PickingInProcessPage.removeShipDateFilter);
        click(PickingInProcessPage.removeTruckNameFilter);
    }

    public void verifySearchRecordsWithInvalidTruckNameValidShipDateValidPickStatus() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusInProcess);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipdate = getText(PickingInProcessPage.shipDate);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameSrs);
        click(PickingInProcessPage.shipDateDropDownFilter);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, shipdate);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }
    public void verifySearchRecordsWithValidTruckNameInvalidShipDateValidPickStatus() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusInProcess);
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameTruck1);
        sendKeys(PickingInProcessPage.shipDateDropDownFilter, "02/23/2001");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.clearFilterLink);
    }


    public void verifySearchRecordsWithValidShipmentNoCustomerTruckNamePickStatusInProcess() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusInProcess);
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameTruck1);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipment = getText(PickingInProcessPage.shipmentNumber1);
        String customer= getText(PickingInProcessPage.customer1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, shipment);
        sendKeys(PickingInProcessPage.customerFilterTextField,customer);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String customer1= getText(PickingInProcessPage.customer1);
        Assert.assertTrue("Verifying customer filter",customer.contentEquals(customer1));
        String shipment1 = getText(PickingInProcessPage.shipmentNumber1);
        Assert.assertTrue("Verifying shipment filter", shipment.contentEquals(shipment1));
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithInvalidShipmentNoCustomerTruckNamePickStatusInProcess() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusInProcess);
        click(PickingInProcessPage.truckNameFilter);
        click(PickingInProcessPage.truckNameTruck1);
        sendKeys(PickingInProcessPage.customerFilterTextField, "abc987");
        Utility_Functions.timeWait(2);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, "abc");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        click(PickingInProcessPage.clearFilterLink);
    }
    public void verifySearchRecordsWithValidShipmentNoCustomerPickStatusStagedPrintY() {
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.pickStatusLink);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueY);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        String shipment = getText(PickingInProcessPage.shipmentNumber1);
        String customer= getText(PickingInProcessPage.customer1);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, shipment);
        Utility_Functions.timeWait(2);
        sendKeys(PickingInProcessPage.customerFilterTextField,customer);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page");
        String customer1= getText(PickingInProcessPage.customer1);
        Assert.assertTrue("Verifying customer filter",customer.contentEquals(customer1));
        String shipment1 = getText(PickingInProcessPage.shipmentNumber1);
        Assert.assertTrue("Verifying shipment filter", shipment.contentEquals(shipment1));
        click(PickingInProcessPage.clearFilterLink);
    }

    public void verifySearchRecordsWithInvalidShipmentNoCustomerPickStatusStagedPrintY() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        click(PickingInProcessPage.pickStatusValue);
        click(PickingInProcessPage.pickStatusStaged);
        click(PickingInProcessPage.packListPrintStatus);
        click(PickingInProcessPage.packListPrintStatusValueY);
        sendKeys(PickingInProcessPage.customerFilterTextField, "abc987");
        Utility_Functions.timeWait(2);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, "abc");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.noResultFoundMsg, "Shipment Record is not present");
        click(PickingInProcessPage.clearFilterLink);
    }
    public void verifyManualPickingInPickingInProcessPage() {
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.manualTableHeader);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.manualTableHeader);
        Utility_Functions.timeWait(2);
        String shipment = getText(PickingInProcessPage.shipmentNumber1);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.manualColumnValue1);
        commonObj.validateElementExists(PickingInProcessPage.completeManualPickPopUp, "complete Manual Picking Popup opens successfully ");
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.completeManualPickPopUpText, "complete Manual Picking Popup message is displaying ");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.completeManualPickPopUpCancel);
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "User navigates back to the Picking In Process page ");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.manualColumnValue1);
        commonObj.validateElementExists(PickingInProcessPage.completeManualPickPopUp, "complete Manual Picking Popup opens successfully ");
        click(PickingInProcessPage.completeManualPickPopUpConfirm);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.printPackingListPopUp, "Print Packing List Popup opens successfully ");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.printPackingListPopUpCancel);
        Utility_Functions.timeWait(2);
        List<WebElement> li = ownDriver.findElements(By.xpath("//button[text()='Cancel ']"));
        if (li.size()>0){click(PickingInProcessPage.completeManualPickPopUpCancel);

        }
        commonObj.validateElementExists(PickingInProcessPage.shipmentNumber1, "Shipment Details is present in the Picking In Process page ");
        click(PickingInProcessPage.pickStatusLink);
        click(PickingInProcessPage.searchFilterIcon);
        waitForVisible(PickingInProcessPage.searchFilterHeader);
        sendKeys(PickingInProcessPage.shipmentFilterTextField, shipment);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.applyFiltersButton);
        Utility_Functions.timeWait(2);
        String shipment1 = getText(PickingInProcessPage.shipmentNumber1);
        Assert.assertTrue("Verifying shipment present after manual picking is done", shipment.contentEquals(shipment1));
        commonObj.validateElementExists(PickingInProcessPage.greenCheckManual, "Manual Picking is done successfully ");
    }
    public void navigateToExceptionQueue() {
        click(PickingInProcessPage.exceptionQueueSideMenu);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.exceptionQueuePageHeader, "User navigates to the Exception Queue page successfully ");
        Utility_Functions.timeWait(2);
    }

    public void verifySortingInExceptionQueuePage() {
        click(PickingInProcessPage.dateTimeColumnSort);
        click(PickingInProcessPage.dateTimeColumnSort);
        click(PickingInProcessPage.sourceColumnHeader);
        click(PickingInProcessPage.sourceColumnSort);
        click(PickingInProcessPage.sourceColumnSort);
        click(PickingInProcessPage.errorReasonColumnHeader);
        click(PickingInProcessPage.errorReasonColumnSort);
        click(PickingInProcessPage.errorReasonColumnSort);
        click(PickingInProcessPage.statusColumnHeader);
        click(PickingInProcessPage.statusColumnSort);
        click(PickingInProcessPage.statusColumnSort);
    }
    public void verifyExportToExcelInExceptionQueuePage() {
        String item = getText(PickingInProcessPage.itemNumber1);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.itemNumberTextField);
        sendKeys(PickingInProcessPage.itemNumberTextField, item);
        Utility_Functions.timeWait(3);
        click(PickingInProcessPage.exportToExcelButton);
        }
    public void verifyPaginationInExceptionQueuePage() {
        Utility_Functions.xScrollIntoView(ownDriver, PickingInProcessPage.nextPageArrow);
        commonObj.validateElementExists(PickingInProcessPage.paginationFirstPage, "First Page arrow is disabled");
        commonObj.validateElementExists(PickingInProcessPage.paginationPrevPage, "Previous Page arrow is disabled");
        int page = Integer.parseInt(Utility_Functions.getText(ownDriver, PickingInProcessPage.pageNumber).trim());
        moveToNextPage();
        Utility_Functions.timeWait(2);
        int newPage = Integer.parseInt(Utility_Functions.getText(ownDriver, PickingInProcessPage.pageNumber).trim());
        Assert.assertTrue(newPage > page);
        //move back to the previous page
        moveToPreviousPage();
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.paginationFirstPage, "First Page arrow is disabled");
        commonObj.validateElementExists(PickingInProcessPage.paginationPrevPage, "Previous Page arrow is disabled");
        //move to last page
        moveToLastPage();
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.paginationLastPage, "Last Page arrow is disabled");
        commonObj.validateElementExists(PickingInProcessPage.paginationNextPage, "Next Page arrow is disabled");
        //move to previous Page
        int lastPage = Integer.parseInt(Utility_Functions.getText(ownDriver, PickingInProcessPage.pageNumber).trim());
        moveToPreviousPage();
        Utility_Functions.timeWait(2);
        int prevPage = Integer.parseInt(Utility_Functions.getText(ownDriver, PickingInProcessPage.pageNumber).trim());
        Assert.assertTrue(lastPage > prevPage);

        //click(PickingInProcessPage.nextPageArrow);
        //click(PickingInProcessPage.lastPageArrow);
        //click(PickingInProcessPage.previousPageArrow);
        //click(PickingInProcessPage.firstPageArrow);
    }
    public void verifyResolutionNotesInExceptionQueuePage() {
        click(PickingInProcessPage.viewDetailsBox);
        click(PickingInProcessPage.eyeIcon);
        click(PickingInProcessPage.resolutionNotesBox);
        String notes = Utility_Functions.getRandomName().toUpperCase();
        Utility_Functions.xClearAndSendKeys(ownDriver, ownDriver.findElement(By.xpath("//*[@id='textArea']")), notes);
        click(PickingInProcessPage.resolutionBoxSave);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.resolutionNotesSaveMessage, "Resolution Notes saved message  showing successfully");
        commonObj.validateElementExists(PickingInProcessPage.savedCommentIcon, "Resolution Notes saved successfully");
    }
    public void verifyUIInExceptionQueuePage() {
        commonObj.validateElementExists(PickingInProcessPage.dateTimeColumnHeader, "Date Time Column header showing successfully");
        commonObj.validateElementExists(PickingInProcessPage.sourceColumnHeader, "Source Column header showing successfully");
        commonObj.validateElementExists(PickingInProcessPage.errorReasonColumnHeader, "Error Reason Column header showing successfully");
        commonObj.validateElementExists(PickingInProcessPage.statusColumnHeader, "Status Column header showing successfully");
        commonObj.validateElementExists(PickingInProcessPage.itemNumberColumnHeader, "Item Number Column header showing successfully");
        commonObj.validateElementExists(PickingInProcessPage.filtersButtonLink, "Filter hyperlink showing successfully");
        commonObj.validateElementExists(PickingInProcessPage.exportToExcelButton, "Export to excel button showing successfully");
    }
    public void verifyClosingRecordsInExceptionQueuePage() {
        click(PickingInProcessPage.statusDropdown);
        click(PickingInProcessPage.statusOpenValue);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.itemNumberColumnHeader);
        click(PickingInProcessPage.itemNumberColumnSort);
        String item = getText(PickingInProcessPage.itemNumber1);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.exceptionCheckbox);
        click(PickingInProcessPage.viewDetailsBox);
        click(PickingInProcessPage.eyeIcon);
        commonObj.validateElementExists(PickingInProcessPage.hideDetailsIcon, "Hide Details showing successfully");
        click(PickingInProcessPage.closeEntryIcon);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(PickingInProcessPage.exceptionClosedMessage, "Exception Closed message showing successfully");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.itemNumberTextField);
        sendKeys(PickingInProcessPage.itemNumberTextField, item);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.statusDropdown);
        click(PickingInProcessPage.statusClosedValue);
        Utility_Functions.timeWait(3);
        String item1 = getText(PickingInProcessPage.itemNumber1);
        Assert.assertTrue("Verifying Closed item", item.contentEquals(item1));

    }
    public void verifyClosingMultipleRecordsInExceptionQueuePage() {
        click(PickingInProcessPage.statusDropdown);
        click(PickingInProcessPage.statusOpenValue);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.itemNumberColumnHeader);
        click(PickingInProcessPage.itemNumberColumnSort);
        String item1 = getText(PickingInProcessPage.itemNumber1);
        String item2 = getText(PickingInProcessPage.itemNumber2);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.exceptionCheckbox);
        click(PickingInProcessPage.exceptionCheckbox2);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.closedSelectedEntriesButton);
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(PickingInProcessPage.exceptionClosedMessage, "Exception Closed message showing successfully");
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.itemNumberTextField);
        sendKeys(PickingInProcessPage.itemNumberTextField, item1);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.statusDropdown);
        click(PickingInProcessPage.statusClosedValue);
        Utility_Functions.timeWait(3);
        String item3 = getText(PickingInProcessPage.itemNumber1);
        Assert.assertTrue("Verifying Multiple Closed item", item1.contentEquals(item3));
        click(PickingInProcessPage.clearFilterLink);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.itemNumberTextField);
        sendKeys(PickingInProcessPage.itemNumberTextField, item2);
        Utility_Functions.timeWait(2);
        click(PickingInProcessPage.statusDropdown);
        click(PickingInProcessPage.statusClosedValue);
        Utility_Functions.timeWait(3);
        String item4 = getText(PickingInProcessPage.itemNumber1);
        Assert.assertTrue("Verifying Multiple Closed item", item2.contentEquals(item4));

    }
    public void verifySearchWithSourceInExceptionQueuePage() {
        String source = getText(PickingInProcessPage.source1).trim();
        click(PickingInProcessPage.sourceProcessDropdown);
        WebElement ele = ownDriver.findElement(By.xpath("//*[@valuekey='sourceProcessCode']/div/ul/li[2]/span[contains(text(),'"+ source +"')]"));
        click(ele);
        Utility_Functions.timeWait(1);
        String source1= getText(PickingInProcessPage.source1);
        Assert.assertTrue("Verifying Source value", source.contentEquals(source1));
        commonObj.validateElementExists(PickingInProcessPage.itemNumber1, "Item is present with selected source Process in the Exception Queue page ");
        click(PickingInProcessPage.sourceProcessDropdown);
        click(PickingInProcessPage.SourceAllValue);
        Utility_Functions.timeWait(1);
        commonObj.validateElementExists(PickingInProcessPage.itemNumber1, "Item is present with 'All' source Process in the Exception Queue page ");

    }
    public void verifySearchWithDateInExceptionQueuePage() {
        String date = getText(PickingInProcessPage.date1).trim();
        click(PickingInProcessPage.fromDate);
        sendKeys(PickingInProcessPage.fromDate, date);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.toDate);
        sendKeys(PickingInProcessPage.toDate, date);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.statusFilter);
        Utility_Functions.timeWait(1);
        String date1= getText(PickingInProcessPage.date1);
        Assert.assertTrue("Verifying Date Value",date.contentEquals(date1));
        commonObj.validateElementExists(PickingInProcessPage.itemNumber1, "Item is present with selected Date Range in the Exception Queue page ");
        }
    public void verifySearchWithErrorReasonInExceptionQueuePage() {
        String errorReason = getText(PickingInProcessPage.errorreason1).trim();
        click(PickingInProcessPage.errorReasonDropdown);
        WebElement ele = ownDriver.findElement(By.xpath("//*[@valuekey='exceptionReasonsCode']/div/ul/li/span[contains(text(),'"+errorReason+"')]"));
        click(ele);
        Utility_Functions.timeWait(1);
        String errorReason1= getText(PickingInProcessPage.errorreason1);
        Assert.assertTrue("Verifying Error Reason Value",errorReason.contentEquals(errorReason1));
        commonObj.validateElementExists(PickingInProcessPage.itemNumber1, "Item is present with selected Error Reason Code in the Exception Queue page ");
        click(PickingInProcessPage.errorReasonDropdown);
        click(PickingInProcessPage.errorReasonAllValue);
        commonObj.validateElementExists(PickingInProcessPage.itemNumber1, "Item is present with 'All' Error Reason Code in the Exception Queue page ");

    }
    public void verifySearchWithStatusInExceptionQueuePage() {
        click(PickingInProcessPage.statusDropdown);
        click(PickingInProcessPage.statusOpenValue);
        Utility_Functions.timeWait(3);
        String status= getText(PickingInProcessPage.status1);
        Assert.assertTrue("Verifying Error Reason Value",status.contentEquals("Open"));
        commonObj.validateElementExists(PickingInProcessPage.itemNumber1, "Item is present with Open status in the Exception Queue page ");
        click(PickingInProcessPage.statusDropdown);
        click(PickingInProcessPage.statusClosedValue);
        Utility_Functions.timeWait(3);
        String status1= getText(PickingInProcessPage.status1);
        Assert.assertTrue("Verifying Error Reason Value",status1.contentEquals("Closed"));
        commonObj.validateElementExists(PickingInProcessPage.itemNumber1, "Item is present with Closed status in the Exception Queue page ");
        click(PickingInProcessPage.statusDropdown);
        click(PickingInProcessPage.statusAllValue);
        commonObj.validateElementExists(PickingInProcessPage.itemNumber1, "Item is present with 'All' Error Reason Code in the Exception Queue page ");

    }
    public void verifySearchWithDateSourceInExceptionQueuePage() {
        String date = getText(PickingInProcessPage.date1).trim();
        String source = getText(PickingInProcessPage.source1).trim();
        click(PickingInProcessPage.fromDate);
        sendKeys(PickingInProcessPage.fromDate, date);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.toDate);
        sendKeys(PickingInProcessPage.toDate, date);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.statusFilter);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.sourceProcessDropdown);
        WebElement ele = ownDriver.findElement(By.xpath("//*[@valuekey='sourceProcessCode']/div/ul/li[2]/span[contains(text(),'"+ source +"')]"));
        click(ele);
        Utility_Functions.timeWait(3);
        String date1= getText(PickingInProcessPage.date1);
        Assert.assertTrue("Verifying Date Value",date.contains(date));
        commonObj.validateElementExists(PickingInProcessPage.date1, "Item is present with selected Date Range in the Exception Queue page ");
        String source1= getText(PickingInProcessPage.source1);
        Assert.assertTrue("Verifying Source Value",source.contentEquals(source));
        commonObj.validateElementExists(PickingInProcessPage.source1, "Item is present with selected source Process in the Exception Queue page ");
        commonObj.validateElementExists(PickingInProcessPage.rangeFilter, "Range Filter is present in the Exception Queue page ");
        commonObj.validateElementExists(PickingInProcessPage.sourceProcessFilter, "Source Process filter is present in the Exception Queue page ");
        click(PickingInProcessPage.clearFilterLink);
    }
    public void verifySearchWithDateErrorReasonInExceptionQueuePage() {
        String date = getText(PickingInProcessPage.date1).trim();
        String errorReason = getText(PickingInProcessPage.errorreason1).trim();
        click(PickingInProcessPage.fromDate);
        sendKeys(PickingInProcessPage.fromDate, date);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.toDate);
        sendKeys(PickingInProcessPage.toDate, date);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.statusFilter);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.errorReasonDropdown);
        WebElement ele = ownDriver.findElement(By.xpath("//*[@valuekey='exceptionReasonsCode']/div/ul/li/span[contains(text(),'"+errorReason+"')]"));
        click(ele);
        Utility_Functions.timeWait(1);
        String errorReason1= getText(PickingInProcessPage.errorreason1);
        Assert.assertTrue("Verifying Error Reason Value",errorReason.contentEquals(errorReason1));
        commonObj.validateElementExists(PickingInProcessPage.itemNumber1, "Item is present with selected Error Reason Code in the Exception Queue page ");
        String date1= getText(PickingInProcessPage.date1);
        Assert.assertTrue("Verifying Date Value",date.contains(date1));
        commonObj.validateElementExists(PickingInProcessPage.itemNumber1, "Item is present with selected Date Range in the Exception Queue page ");
        commonObj.validateElementExists(PickingInProcessPage.rangeFilter, "Range Filter is present in the Exception Queue page ");
        commonObj.validateElementExists(PickingInProcessPage.errorReasonFilter, "Error Reason filter is present in the Exception Queue page ");
        click(PickingInProcessPage.clearFilterLink);
    }
    public void verifySearchWithDateStatusInExceptionQueuePage() {
        String date = getText(PickingInProcessPage.date1).trim();
        String status = getText(PickingInProcessPage.status1).trim();
        click(PickingInProcessPage.fromDate);
        sendKeys(PickingInProcessPage.fromDate, date);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.toDate);
        sendKeys(PickingInProcessPage.toDate, date);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.statusFilter);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.statusDropdown);
        WebElement ele = ownDriver.findElement(By.xpath("//*[@valuekey='exceptionStatusCode']/div/ul/li/span[contains(text(),'"+status+"')]"));
        click(ele);
        Utility_Functions.timeWait(1);
        String status1= getText(PickingInProcessPage.status1);
        Assert.assertTrue("Verifying Status Value",status.contentEquals(status1));
        commonObj.validateElementExists(PickingInProcessPage.status1, "Item is present with valid status in the Exception Queue page ");
        String date1= getText(PickingInProcessPage.date1);
        Assert.assertTrue("Verifying Date Value",date.contains(date1));
        commonObj.validateElementExists(PickingInProcessPage.itemNumber1, "Item is present with selected Date Range in the Exception Queue page ");
        commonObj.validateElementExists(PickingInProcessPage.rangeFilter, "Range Filter is present in the Exception Queue page ");
        commonObj.validateElementExists(PickingInProcessPage.statusFilter, "Status filter is present in the Exception Queue page ");
        click(PickingInProcessPage.clearFilterLink);
    }
    public void verifySearchWithSourceStatusInExceptionQueuePage() {
        String status = getText(PickingInProcessPage.status1).trim();
        String source = getText(PickingInProcessPage.source1).trim();
        click(PickingInProcessPage.statusFilter);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.statusDropdown);
        WebElement ele = ownDriver.findElement(By.xpath("//*[@valuekey='exceptionStatusCode']/div/ul/li/span[contains(text(),'"+status+"')]"));
        click(ele);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.sourceProcessDropdown);
        WebElement ele1 = ownDriver.findElement(By.xpath("//*[@valuekey='sourceProcessCode']/div/ul/li[2]/span[contains(text(),'"+ source +"')]"));
        click(ele1);
        Utility_Functions.timeWait(1);
        String status1= getText(PickingInProcessPage.status1);
        Assert.assertTrue("Verifying Status Value",status.contentEquals(status1));
        commonObj.validateElementExists(PickingInProcessPage.status1, "Item is present with valid status in the Exception Queue page ");
        String source1= getText(PickingInProcessPage.source1);
        Assert.assertTrue("Verifying Source Value",source.contentEquals(source1));
        commonObj.validateElementExists(PickingInProcessPage.source1, "Item is present with selected source Process in the Exception Queue page ");
        commonObj.validateElementExists(PickingInProcessPage.sourceProcessFilter, "Source Process Filter is present in the Exception Queue page ");
        commonObj.validateElementExists(PickingInProcessPage.statusFilter, "Status filter is present in the Exception Queue page ");
        click(PickingInProcessPage.clearFilterLink);
    }
    public void verifySearchWithErrorReasonStatusInExceptionQueuePage() {
        String status = getText(PickingInProcessPage.status1).trim();
        String errorReason = getText(PickingInProcessPage.errorreason1).trim();
        click(PickingInProcessPage.statusFilter);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.statusDropdown);
        WebElement ele = ownDriver.findElement(By.xpath("//*[@valuekey='exceptionStatusCode']/div/ul/li/span[contains(text(),'"+status+"')]"));
        click(ele);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.errorReasonDropdown);
        WebElement ele1 = ownDriver.findElement(By.xpath("//*[@valuekey='exceptionReasonsCode']/div/ul/li/span[contains(text(),'"+errorReason+"')]"));
        click(ele1);
        Utility_Functions.timeWait(1);
        String status1= getText(PickingInProcessPage.status1);
        Assert.assertTrue("Verifying Status Value",status.contentEquals(status1));
        commonObj.validateElementExists(PickingInProcessPage.status1, "Item is present with valid status in the Exception Queue page ");
        String errorrReason1= getText(PickingInProcessPage.errorreason1);
        Assert.assertTrue("Verifying Error Reason Value",errorReason.contentEquals(errorrReason1));
        commonObj.validateElementExists(PickingInProcessPage.errorreason1, "Item is present with selected Error Reason Code in the Exception Queue page ");
        commonObj.validateElementExists(PickingInProcessPage.sourceProcessFilter, "Source Process Filter is present in the Exception Queue page ");
        commonObj.validateElementExists(PickingInProcessPage.statusFilter, "Status filter is present in the Exception Queue page ");
        click(PickingInProcessPage.clearFilterLink);
    }
    public void verifySearchWithAllFiltersInExceptionQueuePage() {
        String date = getText(PickingInProcessPage.date1).trim();
        String source = getText(PickingInProcessPage.source1).trim();
        String errorReason = getText(PickingInProcessPage.errorreason1).trim();
        String status = getText(PickingInProcessPage.status1).trim();
        click(PickingInProcessPage.fromDate);
        sendKeys(PickingInProcessPage.fromDate,date);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.toDate);
        sendKeys(PickingInProcessPage.toDate,date);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.statusFilter);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.errorReasonDropdown);
        WebElement ele = ownDriver.findElement(By.xpath("//*[@valuekey='exceptionReasonsCode']/div/ul/li/span[contains(text(),'"+errorReason+"')]"));
        click(ele);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.sourceProcessDropdown);
        WebElement ele1 = ownDriver.findElement(By.xpath("//*[@valuekey='sourceProcessCode']/div/ul/li[2]/span[contains(text(),'"+ source +"')]"));
        click(ele1);
        Utility_Functions.timeWait(1);
        click(PickingInProcessPage.statusDropdown);
        WebElement ele2 = ownDriver.findElement(By.xpath("//*[@valuekey='exceptionStatusCode']/div/ul/li/span[contains(text(),'"+status+"')]"));
        click(ele2);
        Utility_Functions.timeWait(1);
        String status1= getText(PickingInProcessPage.status1);
        Assert.assertTrue("Verifying Status Value",status.contentEquals(status1));
        commonObj.validateElementExists(PickingInProcessPage.status1, "Item is present with valid status in the Exception Queue page ");
        String errorrReason1= getText(PickingInProcessPage.errorreason1);
        Assert.assertTrue("Verifying Error Reason Value",errorReason.contentEquals(errorrReason1));
        commonObj.validateElementExists(PickingInProcessPage.errorreason1, "Item is present with selected Error Reason Code in the Exception Queue page ");
        String source1= getText(PickingInProcessPage.source1);
        Assert.assertTrue("Verifying Source Value",source.contentEquals(source1));
        commonObj.validateElementExists(PickingInProcessPage.source1, "Item is present with selected source Process in the Exception Queue page ");
        String date1= getText(PickingInProcessPage.date1);
        Assert.assertTrue("Verifying Date Value",date.contains(date1));
        commonObj.validateElementExists(PickingInProcessPage.itemNumber1, "Item is present with selected Date Range in the Exception Queue page ");
        commonObj.validateElementExists(PickingInProcessPage.sourceProcessFilter, "Source Process Filter is present in the Exception Queue page ");
        commonObj.validateElementExists(PickingInProcessPage.rangeFilter, "Range Filter is present in the Exception Queue page ");
        commonObj.validateElementExists(PickingInProcessPage.statusFilter, "Status filter is present in the Exception Queue page ");
        commonObj.validateElementExists(PickingInProcessPage.errorReasonFilter, "Error Reason filter is present in the Exception Queue page ");
        click(PickingInProcessPage.clearFilterLink);
    }
}