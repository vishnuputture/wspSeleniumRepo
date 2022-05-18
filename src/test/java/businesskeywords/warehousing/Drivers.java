package businesskeywords.warehousing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.common.MasterPage;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
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
        ownDriver=helper.getGSDriver();
    }

    /**
     * Keyword to Navigate to Drivers Screen
     */
    public void navigateToDriversScreen() {
        click(TruckPage.menuIconTruck);
        truck.callSelectCompany();
        click(DriversPage.subMenuDriver, "Navigate to Drivers page");
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

    /**
     * Keyword to verify the availability of field again Drivers Screen
     */
    public void verifyAvailFieldDrivers() {
        String[] actText = {"Driver Name", "Alias", "Rank", "Status", "CDL", "CDL Expiration", "Accept Adjustment"};
        List<WebElement> els = ownDriver.findElements(By.xpath("//th"));
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        Utility_Functions.timeWait(2);
        commonObj.validateElementExists(TruckPage.helpIcon, "Help Icon '?' is present");
        commonObj.validateElementExists(TruckPage.filterSearch, "Search filter icon is present");
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
        Utility_Functions.xClickHiddenElement(ownDriver,ownDriver.findElements(DriversPage.pageArrow).get(actPageNo));
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
        Utility_Functions.xScrollIntoView(ownDriver,DriversPage.pageArrow);
        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(ownDriver, DriversPage.onePage)) {
            commonObj.validateText(DriversPage.onePage, "of 1", "One page is available");
        } else {
            Utility_Functions.xClickHiddenElement(ownDriver,ownDriver.findElements(DriversPage.pageArrow).get(2));
            Utility_Functions.xScrollIntoView(ownDriver,DriversPage.pageArrow);
            Utility_Functions.timeWait(2);
            while (!Utility_Functions.xIsDisplayed(ownDriver, DriversPage.lastPage)) {
                size++;
                Utility_Functions.xClickHiddenElement(ownDriver,ownDriver.findElements(DriversPage.pageArrow).get(2));
                Utility_Functions.xScrollIntoView(ownDriver,DriversPage.pageArrow);
            }
            Utility_Functions.timeWait(2);
            Utility_Functions.xClickHiddenElement(ownDriver,ownDriver.findElements(DriversPage.pageArrow).get(0));
            Utility_Functions.xScrollIntoView(ownDriver,DriversPage.pageArrow);
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
        }catch (Exception e){
            Utility_Functions.xClickHiddenElement(ownDriver,By.xpath("//span[text()='" + pageNum + "']"));
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
            int driverCount = ownDriver.findElements(DriversPage.driverNameCount).size();
            Utility_Functions.xAssertEquals(report, "" + driverCount + "", "10", "The Count is 10 by default");
            valPageCount(10);
            valPageCount(15);
            valPageCount(30);
        }
    }

    public void navigateToAddNewDriver() {
        click(DriversPage.addNewDriver, "Click on add new driver button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(DriversPage.newDriverHeader, "Add New Driver", "Verify Add new Driver Header");
    }

    /**
     * Keyword to verify the UI of Add New Driver Screen
     */
    public void uiAddNewDriver() {
        String[] actText = {"First Name", "Last Name", "Alias", "Rank", "Status", "Accept Adjustments", "Employee", "Username", "Has CDL?"};
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
        String rank = Utility_Functions.getText(ownDriver, getTruck("Rank"));
        String status = Utility_Functions.getText(ownDriver, getTruck("Status"));
        click(TruckPage.filterSearch, "Click search filter icon");
        Utility_Functions.timeWait(1);
        selectFilter(DriversPage.driverNameSelect, "Driver Name", Utility_Functions.xGetJsonData("Driver"));
        selectFilter(DriversPage.rankSelect, "Rank", rank);
        selectFilter(DriversPage.statusSelect, "Status", status);
        click(TruckPage.applyFilter, "Click Apply Filters");
        Utility_Functions.timeWait(2);
        commonObj.validateText(DriversPage.driverFirstName, Utility_Functions.xGetJsonData("Driver"), "After filter Driver Name: ");
        commonObj.validateText(getTruck("Rank"), rank, "After filter rank: ");
        commonObj.validateText(getTruck("Status"), status, "After filter status: ");
    }

    /**
     * Keyword to Navigate to Update Driver Page
     */
    public void navigateToUpdateDriverPage() {
        Utility_Functions.timeWait(3);
        Utility_Functions.xClickHiddenElement(ownDriver,By.xpath("//td/a"));
        Utility_Functions.timeWait(2);
        commonObj.validateText(DriversPage.updateDriverHeader, "Update Driver", "Update Driver Page header is present");
    }

    /**
     * Keyword to Verify UI od Update Driver Page
     */
    public void updateDriverUI() {
        String[] actText = {"First Name", "Last Name", "Alias", "Rank", "Status", "Accept Adjustments", "Employee", "Username", "Has CDL?"};
        List<WebElement> els = ownDriver.findElements(DriversPage.addNewDriverLabel);
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        Utility_Functions.timeWait(2);
        commonObj.validateText(DriversPage.saveDriverDis, "Save Driver", "Save Driver Button is Exist");
        commonObj.validateText(TruckPage.deleteButton, "Delete Driver", "Delete Driver Button is Exist");
        commonObj.validateElementExists(DriversPage.crossIcon, "Cross icon is present");
    }

    /**
     * Keyword to delete Driver
     *
     */
    public void deleteDriver() {
        if (Utility_Functions.xIsDisplayed(ownDriver,DriversPage.deleteButtonDisable)) {
            Utility_Functions.xHoverElementclicks(ownDriver.findElement(DriversPage.deleteButtonDisable),ownDriver);
            commonObj.validateElementExists(DriversPage.deleteButtonDisable,"Delete Driver button is disabled and message: Driver is tied manifest history. you may change status to inactive");
        } else {
            click(TruckPage.deleteButton, "Click Delete Driver Button");
            commonObj.validateElementExists(TruckPage.deleteConfPopUp, "Delete Driver Confirmation Pop Up is present");
            click(TruckPage.noButtonPopUp, "Click No Button");
            commonObj.validateText(DriversPage.updateDriverHeader, "Update Driver", "Update Driver Page header is present");
            click(TruckPage.deleteButton, "Click Delete Driver Button");
            commonObj.validateElementExists(TruckPage.deleteConfPopUp, "Delete Driver Confirmation Pop Up is present");
            click(TruckPage.yesButtonPopUp, "Click Yes Button");
            Utility_Functions.timeWait(3);
            commonObj.validateText(TruckPage.deletePopUp, "Driver " + Utility_Functions.xGetJsonData("Driver") + " (" + jsonData.getData("alias") + ") successfully deleted.", "Deleted Driver Successful message is present");
        }
    }

    /**
     * Keyword to Update Driver
     *
     */
    public void updateDriver() {
        click(DriversPage.statusDriver,"Click Status drop down");
        click(DriversPage.inActive,"Select InActive From the status drop down");
        click(DriversPage.saveDriver,"Click Save Driver");
        Utility_Functions.timeWait(3);
        commonObj.validateText(TruckPage.deletePopUp,"Driver " + Utility_Functions.xGetJsonData("Driver") + " (" + jsonData.getData("alias") + ") successfully updated.","Driver Update message should display");
    }
}
