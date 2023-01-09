package businesskeywords.warehousing.Manifest;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.common.MasterPage;
import pages.warehouse.DriversPage;
import pages.warehouse.TruckPage;
import supportLibraries.Utility_Functions;

import java.util.List;
import java.util.Random;

public class Drivers extends ReusableLib {
    CommonActions commonObj;
    Trucks truck;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public Drivers(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        truck = new Trucks(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * Keyword to Navigate to Drivers Screen
     */
    public void navigateToDriversScreen() {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, TruckPage.menuIconTruck, "Click Menu");
        //callSelectCompany();
        Utility_Functions.timeWait(3);
        try {
            click(DriversPage.subMenuDriver, "Navigate to Driver page");
        } catch (Exception e) {
            Utility_Functions.waitTillClickHardSleep(report, ownDriver, TruckPage.menuIconTruck, "Click Menu");
            Utility_Functions.timeWait(3);
            click(DriversPage.subMenuDriver, "Navigate to Driver page");
        }
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.timeWait(3);
        commonObj.validateText(DriversPage.driversHeader, "Drivers", "Drivers Screen Header is present");
    }

    /**
     * Keyword to Generate Random Text
     */
    public String genText() {
        String userName = "";
        for (int i = 0; i <= 16; i++) {
            Random random = new Random();
            char randomizedCharacter = (char) (random.nextInt(26) + 'A');
            userName = userName + randomizedCharacter;
        }
        return userName;
    }

    public By labelTag(String labelName) {
        return By.xpath("//label[text()='" + labelName + "']");
    }

    /**
     * Keyword to verify the availability of field again Drivers Screen
     */
    public void verifyAvailFieldDrivers() {
        String[] actText = {"Driver Name", "Alias", "Status", "CDL", "CDL Expiration", "Accept Adjustment", "Rank"};
        List<WebElement> els = ownDriver.findElements(By.xpath("//th"));
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        String[] labels = {"Driver", "Status", "Status", "CDL", "Rank"};
        for (String label : labels) {
            commonObj.validateText(labelTag(label), label, "Label [" + label + "] is present");
        }
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(TruckPage.helpIcon, "Help Icon '?' is present");
        int size = ownDriver.findElements(TruckPage.pagination).size();
        Utility_Functions.xAssertEquals(report, size, 4, "Next page and previous page arrow icon is present");
        String page = Utility_Functions.getText(ownDriver, TruckPage.currentPage);
        commonObj.validateElementExists(TruckPage.currentPage, "Current Page " + page + " " + Utility_Functions.getText(ownDriver, TruckPage.outOf) + "");
        List<WebElement> elm = ownDriver.findElements(TruckPage.show);
        String[] acText = {"10", "15", "30"};
        int j = 0;
        for (WebElement el : elm) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), acText[j], "");
            j++;
        }
    }

    /**
     * Keyword to Verify functionality of Help (?)icon
     */
    public void helpIcon() {
        Utility_Functions.timeWait(3);
        click(TruckPage.helpIcon);
        Utility_Functions.timeWait(3);
    }

    /**
     * Keyword to Verify Pagination against current page
     */
    public void selectPage(int actPageNo, String expPage, String arrowIcon) {
        Utility_Functions.timeWait(2);
        Utility_Functions.xScrollWindow(ownDriver);
        Utility_Functions.timeWait(2);
        Utility_Functions.xClickHiddenElement(ownDriver, ownDriver.findElements(DriversPage.pageArrow).get(actPageNo));
        Utility_Functions.xScrollWindow(ownDriver);
        String pageNo = ownDriver.findElement(TruckPage.currentPage).getAttribute("ng-reflect-model");
        Utility_Functions.xAssertEquals(report, pageNo, expPage, "Moved to " + pageNo + " Page");
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to Verify functionality of Pagination
     */
    public void pagination() {
        int size = 2;
        Utility_Functions.xScrollIntoView(ownDriver, DriversPage.pageArrow);
        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(ownDriver, DriversPage.onePage)) {
            commonObj.validateText(DriversPage.onePage, "of 1", "One page is available");
        } else {
            Utility_Functions.xClickHiddenElement(ownDriver, ownDriver.findElements(DriversPage.pageArrow).get(2));
            Utility_Functions.xScrollIntoView(ownDriver, DriversPage.pageArrow);
            Utility_Functions.timeWait(2);
            while (!Utility_Functions.xIsDisplayed(ownDriver, DriversPage.lastPage)) {
                size++;
                Utility_Functions.xClickHiddenElement(ownDriver, ownDriver.findElements(DriversPage.pageArrow).get(2));
                Utility_Functions.xScrollIntoView(ownDriver, DriversPage.pageArrow);
            }
            Utility_Functions.timeWait(2);
            Utility_Functions.xClickHiddenElement(ownDriver, ownDriver.findElements(DriversPage.pageArrow).get(0));
            Utility_Functions.xScrollIntoView(ownDriver, DriversPage.pageArrow);
            selectPage(2, "2", "Right Arrow (>)");
            selectPage(1, "1", "Left Arrow (<)");
            selectPage(3, "" + size + "", "Right double Arrow (>>)");
            selectPage(0, "1", "Left double Arrow (<<)");
        }
    }

    /**
     * Keyword to verify Page Count
     */
    public void valPageCount(int pageNum) {
        Utility_Functions.xScrollWindow(ownDriver);
        Utility_Functions.xScrollWindow(ownDriver);
        Utility_Functions.timeWait(4);
        try {
            click(By.xpath("//span[text()='" + pageNum + "']"), "Click on '" + pageNum + "' Present below the Left corner of the page");
        } catch (Exception e) {
            Utility_Functions.xClickHiddenElement(ownDriver, By.xpath("//span[text()='" + pageNum + "']"));
        }
        int driverCount = ownDriver.findElements(DriversPage.driverNameCount).size();
        if (driverCount == pageNum) {
            Utility_Functions.xAssertEquals(report, "" + driverCount + "", "" + pageNum + "", "'" + pageNum + "' is in disable state and showing " + pageNum + " driver Count");
        } else {
            commonObj.validateElementExists(DriversPage.driverNameCount, "Total count is " + driverCount + "");
        }
    }

    /**
     * Keyword to Verify functionality of Page Count
     */
    public void funPageCount() {
        Utility_Functions.xScrollWindow(ownDriver);
        if (Utility_Functions.xIsDisplayed(ownDriver, DriversPage.onePage)) {
            int driverCount = ownDriver.findElements(DriversPage.driverNameCount).size();
            commonObj.validateText(DriversPage.onePage, "of 1", "One page is available having count " + driverCount + "");
        } else {
            valPageCount(10);
            valPageCount(15);
            valPageCount(30);
        }
    }

    public void navigateToAddNewDriver() {
        click(DriversPage.addNewDriver, "Click on add new driver button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(DriversPage.popupHeader, "New Driver", "Verify New Driver Header");
    }

    /**
     * Keyword to verify the UI of Add New Driver Screen
     */
    public void uiAddNewDriver() {
        String[] actText = {"First Name", "Last Name", "Alias", "Rank", "Status", "Accept Adjustments", "Employee", "Username", "CDL?"};
        List<WebElement> els = ownDriver.findElements(DriversPage.addNewDriverLabel);
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        Utility_Functions.timeWait(2);
        commonObj.validateText(DriversPage.saveDriverDis, "Save Driver", "Save Button is Exist and button is disabled");
        commonObj.validateElementExists(DriversPage.crossIcon, "Cross icon is present");
        click(DriversPage.crossIcon, "Click Cross Icon");
        Utility_Functions.timeWait(2);
        commonObj.validateText(DriversPage.driversHeader, "Drivers", "Verify Drivers screen Header");
    }

    /**
     * Keyword to Add new Driver
     */
    public void addNewDriver() {
        sendKeys(DriversPage.firstName, jsonData.getData("firstName"), "Entering first name");
        String lastName = genText();
        sendKeys(DriversPage.lastname, lastName, "Entering last name");
        sendKeys(DriversPage.alias, jsonData.getData("alias"), "Entering alias name");
        sendKeys(DriversPage.username, genText(), "Entering username name");
        click(DriversPage.saveDriver, "Saving record");
        Utility_Functions.timeWait(3);
        commonObj.validateText(TruckPage.deletePopUp, "Invalid username. Please make sure it is their WinZone username and not their WISE username.", "[Invalid username. Please make sure it is their WinZone username and not their WISE username.] message found");
        sendKeys(DriversPage.username, "wz99599a", "Entering username name");
        click(DriversPage.saveDriver, "Saving record");
        Utility_Functions.timeWait(3);
        String actText = "Driver " + jsonData.getData("firstName") + " " + lastName + " (" + jsonData.getData("alias") + ") successfully created.";
        String expTest = Utility_Functions.getText(ownDriver, By.xpath("//span[contains(text(),'" + jsonData.getData("firstName") + " " + lastName + "')]"));
        Utility_Functions.xAssertEquals(report, actText, expTest, "Driver successfully added pop up message");
        String actTx = "" + jsonData.getData("firstName") + " " + lastName + "";
        Utility_Functions.timeWait(3);
        Utility_Functions.xUpdateJson("Driver", actTx);
    }

    /**
     * Keyword to select search filter drop down
     */
    public By getDropDownVal(String option) {
        return By.xpath("//option[contains(text(),'" + option + "')]");
    }

    public String selectFilter(By by, String label, String option) {
        click(by);
        Utility_Functions.timeWait(1);
        String name = Utility_Functions.getText(ownDriver, getDropDownVal(option));
        System.out.println(name);
        Utility_Functions.timeWait(1);
        click(getDropDownVal(option), "Select " + option + " " + label + " drop Down");
        return name;
    }

    public By getTruck(String label) {
        return By.xpath("//table/tbody/tr[1]/td[count(//table/thead/tr/th[contains(text(),'" + label + "')]/preceding-sibling::th)+1]");
    }

    /**
     * Keyword to Apply filter
     */
    public void applyFilterDriver() {
        //selectFilter(DriversPage.driverNameSelect, "Driver Name", Utility_Functions.xGetJsonData("Driver"));
        selectFilter(DriversPage.rankSelect, "Rank", "Primary");
        Utility_Functions.timeWait(3);
        selectFilter(DriversPage.statusSelect, "Status", "Active");
        Utility_Functions.timeWait(2);
        //commonObj.validateText(DriversPage.driverFirstName, Utility_Functions.xGetJsonData("Driver"), "After filter Driver Name: ");
        commonObj.validateText(By.xpath("//table/tbody/tr[1]/td[7]"), "Primary", "After filter rank: ");
        commonObj.validateText(By.xpath("//table/tbody/tr[1]/td[3]/descendant::span"), "Active", "After filter status: ");
    }

    /**
     * Keyword to Navigate to Update Driver Page
     */
    public void navigateToUpdateDriverPage() {
        Utility_Functions.timeWait(3);
        Utility_Functions.xClickHiddenElement(ownDriver, By.xpath("//td"));
        Utility_Functions.timeWait(2);
        commonObj.validateText(DriversPage.popupHeader, "Driver Details", "Update Driver Page header is present");
    }

    /**
     * Keyword to Verify UI od Update Driver Page
     */
    public void updateDriverUI() {
        String[] actText = {"First Name", "Last Name", "Alias", "Rank", "Status", "Accept Adjustments", "Employee", "Username", "CDL?"};
        List<WebElement> els = ownDriver.findElements(DriversPage.addNewDriverLabel);
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        Utility_Functions.timeWait(2);
        commonObj.validateText(buttonTg("Edit"), "Edit", "Edit Driver Button is Exist");
        commonObj.validateText(buttonTg("Cancel"), "Cancel", "Cancel Driver Button is Exist");
        commonObj.validateText(TruckPage.deleteButton, "Delete", "Delete Driver Button is Exist");
        commonObj.validateElementExists(DriversPage.crossIcon, "Cross icon is present");
    }

    public By buttonTg(String text){
        return By.xpath("//*[contains(text(),'"+text+"')]");
    }

    /**
     * Keyword to delete Driver
     */
    public void deleteDriver() {
        if (Utility_Functions.xIsDisplayed(ownDriver, DriversPage.deleteButtonDisable)) {
            Utility_Functions.xHoverElementclicks(ownDriver.findElement(DriversPage.deleteButtonDisable), ownDriver);
            commonObj.validateElementExists(DriversPage.deleteButtonDisable, "Delete Driver button is disabled and message: Driver is tied manifest history. you may change status to inactive");
        } else {
            Utility_Functions.xClickHiddenElement(ownDriver, TruckPage.deleteButton);
            Utility_Functions.timeWait(2);
            click(By.xpath("//button[text()='Delete Driver ? ']"));
            Utility_Functions.timeWait(3);
            commonObj.validateText(TruckPage.deletePopUp, "Driver " + Utility_Functions.xGetJsonData("Driver") + " (" + jsonData.getData("alias") + ") successfully deleted.", "Deleted Driver Successful message is present");
        }
    }

    /**
     * Keyword to Update Driver
     */
    public void updateDriver() {
        click(buttonTg("Edit"),"Click Edit Button");
        click(DriversPage.statusDriver, "Click Status drop down");
        click(DriversPage.inActive, "Select InActive From the status drop down");
        click(DriversPage.saveDriver, "Click Save Driver");
        Utility_Functions.timeWait(3);
        commonObj.validateText(TruckPage.deletePopUp, "Driver " + Utility_Functions.xGetJsonData("Driver") + " (" + jsonData.getData("alias") + ") successfully updated.", "Driver Update message should display");
    }

    public String driverUserName(){
        String userName="wz99599a";
        int i=0;
        while(!isDisplayed(TruckPage.deletePopUp)){
            userName=userName.substring(0,1).toUpperCase()+userName.substring(1);
            i++;
        }
        return userName;
    }
}
