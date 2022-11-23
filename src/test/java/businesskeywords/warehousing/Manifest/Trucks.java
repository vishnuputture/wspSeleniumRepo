package businesskeywords.warehousing.Manifest;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.common.MasterPage;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
import pages.warehouse.DeliveredShipmentsPage;
import pages.warehouse.DriversPage;
import pages.warehouse.TruckPage;
import supportLibraries.Utility_Functions;

import java.util.List;
import java.util.Random;

public class Trucks extends ReusableLib {
    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public Trucks(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }


    public void navigateToShippingManifest() {
        //driver.get(jsonData.getData("Url"));
        ownDriver.get(properties.getProperty("manifestURL"));
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to select Company
     */
    public void selectCompany() {
        click(SelfServicePriceSheetPage.companySelector);
        click(SelfServicePriceSheetPage.companyLabel);
        sendKey(SelfServicePriceSheetPage.winCompanyNumber, "99599");
        click(SelfServicePriceSheetPage.selectButton);
    }

    public void callSelectCompany() {
        Utility_Functions.timeWait(7);
        if (Utility_Functions.xIsDisplayed(ownDriver, DeliveredShipmentsPage.unAuth)) {
            selectCompany();
            click(TruckPage.menuIconTruck);
        }
    }

    /**
     * Keyword to Generate Random Text
     */
    public String genText() {
        String userName = "";
        for (int i = 0; i <= 10; i++) {
            Random random = new Random();
            char randomizedCharacter = (char) (random.nextInt(26) + 'A');
            userName = userName + randomizedCharacter;
        }
        return userName;
    }

    /**
     * Keyword to Navigate to Trucks Screen
     */
    public void navigateToTrucksScreen() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.menuIconTruck,"Click Menu");
        //callSelectCompany();
        Utility_Functions.timeWait(3);
        try {
            click(TruckPage.subMenuTruck, "Navigate to trucks page");
        }catch (Exception e){
            Utility_Functions.waitTillClickHardSleep(report,ownDriver,TruckPage.menuIconTruck,"Click Menu");
            Utility_Functions.timeWait(3);
            click(TruckPage.subMenuTruck, "Navigate to trucks page");
        }
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.timeWait(3);
        commonObj.validateText(TruckPage.trucksHeader, "Trucks", "Trucks Screen Header is present");
    }

    public By labelTag(String labelName){
        return By.xpath("//label[text()='"+labelName+"']");
    }

    /**
     * Keyword to verify the availability of field again Truck Screen
     */
    public void verifyAvailFieldTruck() {
        String[] actText = {"Truck Name", "License Plate Number", "Plate Expiration", "Status", "CDL Required", "Year", "Make", "Model"};
        List<WebElement> els = ownDriver.findElements(By.xpath("//th"));
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        String[] labels={"Truck Name","License Plate Number","Status","CDL Required"};
        for(String label:labels){
            commonObj.validateText(labelTag(label),label,"Label ["+label+"] is present");
        }
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
    public void helpIconTruck() {
        click(TruckPage.helpIcon);
        Utility_Functions.timeWait(3);
    }

    /**
     * Keyword to Verify Pagination against current page
     */
    public void selectPage(int actPageNo, String expPage, String arrowIcon) {
        click(ownDriver.findElements(DriversPage.pageArrow).get(actPageNo), "Click on " + arrowIcon + " Present below the Right Corner of the page");
        Utility_Functions.xScrollWindow(ownDriver);
        String pageNo = ownDriver.findElement(TruckPage.currentPage).getAttribute("ng-reflect-model");
        Utility_Functions.xAssertEquals(report, pageNo, expPage, "Moved to " + pageNo + " Page");
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to Verify functionality of Pagination
     */
    public void paginationTruck() {
        int size = 2;
        Utility_Functions.xScrollWindow(ownDriver);
        Utility_Functions.timeWait(2);
        if (Utility_Functions.xIsDisplayed(ownDriver, DriversPage.onePage)) {
            commonObj.validateText(DriversPage.onePage, "of 1", "One page is available");
        } else {
            click(ownDriver.findElements(DriversPage.pageArrow).get(2));
            Utility_Functions.xScrollWindow(ownDriver);
            while (!Utility_Functions.xIsDisplayed(ownDriver, DriversPage.lastPage)) {
                size++;
                click(ownDriver.findElements(DriversPage.pageArrow).get(2));
                Utility_Functions.xScrollWindow(ownDriver);
            }
            click(ownDriver.findElements(DriversPage.pageArrow).get(0));
            Utility_Functions.xScrollWindow(ownDriver);
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
        click(By.xpath("//span[text()='" + pageNum + "']"), "Click on '" + pageNum + "' Present below the Left corner of the page");
        int truckCount = ownDriver.findElements(DriversPage.driverNameCount).size();
        if (truckCount == pageNum) {
            Utility_Functions.xAssertEquals(report, "" + truckCount + "", "" + pageNum + "", "'" + pageNum + "' is in disable state and showing " + pageNum + " driver Count");
        } else {
            commonObj.validateElementExists(DriversPage.driverNameCount, "Total Truck added count is " + truckCount + "");
        }
    }

    /**
     * Keyword to Verify functionality of Page Count
     */
    public void funPageCountTruck() {
        Utility_Functions.xScrollWindow(ownDriver);
        if (Utility_Functions.xIsDisplayed(ownDriver, DriversPage.onePage)) {
            int truckCount = ownDriver.findElements(DriversPage.driverNameCount).size();
            commonObj.validateText(DriversPage.onePage, "of 1", "One page is available having drivers count " + truckCount + "");
        } else {
            int truckCount = ownDriver.findElements(DriversPage.driverNameCount).size();
            Utility_Functions.xAssertEquals(report, "" + truckCount + "", "10", "The Driver Count is 10 by default");
            valPageCount(10);
            valPageCount(15);
            valPageCount(30);
            valPageCount(30);
            valPageCount(10);
        }
    }

    public void navigateToAddNewTruck() {
        click(TruckPage.addNewTruckBtn, "Click on [New Truck] button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(TruckPage.addNewTruckHeader, "New Truck", "Verify Add new Truck Header");
    }

    /**
     * Keyword to verify the UI of Add New Truck Screen
     */
    public void uiAddNewTruck() {
        String[] actText = {"Truck Name", "License Plate Number", "License Plate Expiration", "Status", "CDL Required", "Year", "Make", "Model", "VIN", "Truck Weight - Unloaded"};
        List<WebElement> els = ownDriver.findElements(TruckPage.newTruckLabel);
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        Utility_Functions.timeWait(2);
        commonObj.validateText(TruckPage.saveBtnDis, "Save Truck", "Save Button is Exist and button is disabled");
        commonObj.validateElementExists(DriversPage.crossIcon, "Cross icon is present");
        click(DriversPage.crossIcon, "Click Cross Icon");
        Utility_Functions.timeWait(3);
        commonObj.validateText(TruckPage.trucksHeader, "Trucks", "Verify Trucks screen Header");
    }

    public By spanTag(String text){
        return By.xpath("//span[contains(text(),'"+text+"')]");
    }

    /**
     * Keyword to Add Truck
     */
    public void addNewTruck() {
        String truckName = genText();
        int licensePlateNo = Utility_Functions.xRandomFunction();
        sendKeys(TruckPage.truckNameInput, truckName, "Entering truck name");
        Utility_Functions.xUpdateJson("TruckName", truckName);
        sendKeys(TruckPage.licensePlateNumberInput, "" + licensePlateNo + "", "Entering license plate number");
        Utility_Functions.xUpdateJson("licensePlateNo", "" + licensePlateNo + "");
        click(TruckPage.newTruckPlateExpInput);
        click(TruckPage.licensePlateExpSelect, "Select License Plate Expiration Date");
        click(TruckPage.statusDrop);
        click(TruckPage.statusDrop);
        Utility_Functions.xSelectDropdownByName(ownDriver,TruckPage.statusDrop,"Active");
        Utility_Functions.timeWait(2);
        int size=ownDriver.findElements(TruckPage.cdlRequiredFilter).size();
        Utility_Functions.xSelectDropdownByName(ownDriver,ownDriver.findElements(TruckPage.cdlRequiredFilter).get(size-1),"Yes");
        sendKeys(TruckPage.yearInput, jsonData.getData("MakeYear"), "Entering year");
        sendKeys(TruckPage.makeInput, jsonData.getData("Make"), "Entering make");
        sendKeys(TruckPage.modelInput, jsonData.getData("Model"), "Entering model");
        sendKeys(TruckPage.vinInput, jsonData.getData("Vin"), "Entering vin number");
        sendKeys(TruckPage.emptyTruckWeightInput, jsonData.getData("EmptyWeight"), "Entering empty truck weight");
        sendKeys(TruckPage.weightLimitInput, jsonData.getData("WeightLimit"), "Entering weight limit");
        click(TruckPage.saveBtn, "Saving record");
        Utility_Functions.timeWait(4);
        String actText = "Truck " + truckName + " successfully created.";
        String expTest = Utility_Functions.getText(ownDriver, By.xpath("//span[contains(text(),'" + truckName + "')]"));
        Utility_Functions.xAssertEquals(report, actText, expTest, "Truck successfully added pop up message");
        /* System.out.println("//a[text()='"+truckName+"']");
        String actTx=""+truckName+"";
        Utility_Functions.timeWait(3);
        String expTx=driver.findElement(By.xpath("//a[text()='"+truckName+"']")).getText();
        Utility_Functions.xUpdateJson("TruckName",truckName);*/
        //Utility_Functions.xAssertEquals(report,actTx,expTx,"Truck successfully added");
    }

    /**
     * Keyword to Add Truck
     */
    public void addNewTruck2() {
        String truckName = genText();
        int licensePlateNo = Utility_Functions.xRandomFunction();
        sendKeys(TruckPage.truckNameInput, truckName, "Entering truck name");
        Utility_Functions.xUpdateJson("TruckName1", truckName);
        sendKeys(TruckPage.licensePlateNumberInput, "" + licensePlateNo + "", "Entering license plate number");
        Utility_Functions.xUpdateJson("licensePlateNo1", "" + licensePlateNo + "");
        click(TruckPage.newTruckPlateExpInput);
        click(TruckPage.licensePlateExpSelect, "Select License Plate Expiration Date");
        sendKeys(TruckPage.yearInput, jsonData.getData("MakeYear"), "Entering year");
        sendKeys(TruckPage.makeInput, jsonData.getData("Make"), "Entering make");
        sendKeys(TruckPage.modelInput, jsonData.getData("Model"), "Entering model");
        sendKeys(TruckPage.vinInput, jsonData.getData("Vin"), "Entering vin number");
        sendKeys(TruckPage.emptyTruckWeightInput, jsonData.getData("EmptyWeight"), "Entering empty truck weight");
        sendKeys(TruckPage.weightLimitInput, jsonData.getData("WeightLimit"), "Entering weight limit");
        click(TruckPage.saveBtn, "Saving record");
        Utility_Functions.timeWait(2);
        String actText = "Truck " + truckName + " successfully created.";
        String expTest = Utility_Functions.getText(ownDriver, By.xpath("//span[contains(text(),'" + truckName + "')]"));
        Utility_Functions.xAssertEquals(report, actText, expTest, "Truck successfully added pop up message");
        /* System.out.println("//a[text()='"+truckName+"']");
        String actTx=""+truckName+"";
        Utility_Functions.timeWait(3);
        String expTx=driver.findElement(By.xpath("//a[text()='"+truckName+"']")).getText();
        Utility_Functions.xUpdateJson("TruckName",truckName);*/
        //Utility_Functions.xAssertEquals(report,actTx,expTx,"Truck successfully added");
    }

    /**
     * Keyword to verify UI of Search Filters
     */
    public void searchFiltersTruckUI() {
        String[] actText = {"Truck Name", "License Plate Number", "Status", "CDL Required"};
        List<WebElement> els = ownDriver.findElements(TruckPage.searchFiltersLabel);
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        commonObj.validateElementExists(TruckPage.filtersCrossIcon, "Cross icon is present");
        commonObj.validateText(TruckPage.applyFiltersDis, "Apply Filters", "Apply Filters button exist and button is disabled");
        // commonObj.validateText(TruckPage.clearFilters,"Clear All Filters","Clear All Filters button is exist and text is in red color");
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
    public void applyFilter() {
        String truckN = Utility_Functions.xGetJsonData("TruckName");
        String licensePlateN = Utility_Functions.xGetJsonData("licensePlateNo");
        String status = jsonData.getData("status");
        String cDLRequired = "Yes";
        selectFilter(TruckPage.truckFilter, "Truck Name", truckN);
        selectFilter(TruckPage.licensePlateNoFilter, "License Plate Number", licensePlateN);
        selectFilter(TruckPage.statusFilter, "Status", status);
        selectFilter(TruckPage.cdlRequiredFilter, "CDL Required", cDLRequired);
        Utility_Functions.timeWait(2);
        commonObj.validateText(thTag(truckN), truckN, "After filter Truck Name: ");
        commonObj.validateText(thTag(licensePlateN), licensePlateN, "After filter License Plate Number: ");
        commonObj.validateText(By.xpath("//td/descendant::span"), status, "After filter status: ");
        commonObj.validateText(thTag(cDLRequired), cDLRequired, "After filter CDL Required: ");
    }

    public By thTag(String text){
        return By.xpath("//td[contains(text(),'"+text+"')]");
    }

    /**
     * Keyword to Navigate to Update Truck Page
     */
    public void navigateToUpdateTruckPage() {
        Utility_Functions.timeWait(3);
        Utility_Functions.xClickHiddenElement(ownDriver, By.xpath("//td/a"));
        Utility_Functions.timeWait(2);
        commonObj.validateText(TruckPage.updateTruckHeader, "Update Truck", "Update Truck Page header is present");
    }

    /**
     * Keyword to verify the UI of Update Truck Screen
     */
    public void uiUpdateTruck() {
        String[] actText = {"Truck Name", "License Plate Number", "License Plate Expiration", "Status", "CDL Required", "Year", "Make", "Model", "VIN", "Truck Weight - Unloaded", "Total Truck Weight Limit"};
        List<WebElement> els = ownDriver.findElements(TruckPage.newTruckLabel);
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        Utility_Functions.timeWait(2);
        commonObj.validateText(TruckPage.saveBtnDis, "Save Truck", "Save Truck Button is Exist and button is disabled");
        commonObj.validateText(TruckPage.deleteButton, "Delete Truck", "Delete Truck Button is Exist and button is disabled");
        commonObj.validateElementExists(DriversPage.crossIcon, "Cross icon is present");
    }

    /**
     * Keyword to verify Delete Truck
     */
    public void deleteTruck() {
        Utility_Functions.timeWait(2);
        Utility_Functions.xClickHiddenElement(ownDriver, TruckPage.deleteButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(TruckPage.deleteConfPopUp, "Click Delete Truck Button and Delete Confirmation Pop Up is present");
        click(TruckPage.noButtonPopUp, "Click No Button");
        commonObj.validateText(TruckPage.updateTruckHeader, "Update Truck", "Update Truck Page header is present");
        Utility_Functions.xClickHiddenElement(ownDriver, TruckPage.deleteButton);
        Utility_Functions.timeWait(3);
        commonObj.validateElementExists(TruckPage.deleteConfPopUp, "Click Delete Truck Button and Delete Confirmation Pop Up is present");
        click(TruckPage.yesButtonPopUp, "Click Yes Button");
        Utility_Functions.timeWait(3);
        String exp = "Truck could not be deleted because it is assigned to a manifest.";
        String act = Utility_Functions.getText(ownDriver, TruckPage.deletePopUp);
        if (act.equals(exp)) {
            commonObj.validateElementExists(TruckPage.deletePopUp, exp + ": pop message is present");
        } else {
            commonObj.validateText(TruckPage.deletePopUp, "Truck " + Utility_Functions.xGetJsonData("TruckName") + " successfully deleted.", "Deleted truck Successful message is present");
        }
    }

    /**
     * Keyword to Update Truck
     */
    public void updateTruck() {
        click(TruckPage.statusDrop, "Click Status drop down");
        click(DriversPage.inActive, "Select InActive From the status drop down");
        Utility_Functions.timeWait(2);
        click(TruckPage.newTruckPlateExpInput);
        int size = ownDriver.findElements(TruckPage.licensePlateExpSelectInActive).size() - 1;
        click(ownDriver.findElements(TruckPage.licensePlateExpSelectInActive).get(size), "Select License Plate Expiration Date");
        Utility_Functions.timeWait(2);
        click(TruckPage.saveTruckButton, "Click Save Truck");
        Utility_Functions.timeWait(3);
        commonObj.validateText(TruckPage.deletePopUp, Utility_Functions.xGetJsonData("TruckName") + " successfully updated.", "Truck Update message should display");
    }
}
