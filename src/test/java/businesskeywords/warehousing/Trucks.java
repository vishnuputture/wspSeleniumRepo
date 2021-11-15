package businesskeywords.warehousing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.common.MasterPage;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
import pages.warehouse.DriversPage;
import pages.warehouse.ManifestsPage;
import pages.warehouse.TruckPage;
import supportLibraries.Utility_Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trucks extends ReusableLib {
    CommonActions commonObj;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public Trucks(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    public void navigateToShippingManifest() {
        //driver.get(jsonData.getData("Url"));
        driver.get("http://wservicedev.winwholesale.com/shipping-manifest-manager/#/truck-list/warehousing");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to select Company
     *
     */
    public void selectCompany(){
        click(SelfServicePriceSheetPage.companySelector);
        click(SelfServicePriceSheetPage.companyLabel);
        sendKey(SelfServicePriceSheetPage.winCompanyNumber, "99599");
        click(SelfServicePriceSheetPage.selectButton);
        Utility_Functions.timeWait(5);
    }

    /**
     * Keyword to Generate Random Text
     *
     */
    public String genText(){
        String userName = "";
        for(int i=0; i<=10;i++) {
            Random random = new Random();
            char randomizedCharacter = (char) (random.nextInt(26) + 'A');
            userName = userName+randomizedCharacter;
        }
        return userName;
    }

    /**
     * Keyword to Navigate to Trucks Screen
     *
     */
    public void navigateToTrucksScreen(){
        selectCompany();
        click(TruckPage.menuIconTruck);
        click(TruckPage.subMenuTruck, "Navigate to trucks page");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.timeWait(3);
        commonObj.validateText(TruckPage.trucksHeader,"Trucks","Trucks Screen Header is present");
    }

    /**
     * Keyword to verify the availability of field again Truck Screen
     *
     */
    public void verifyAvailFieldTruck() {
        String[] actText={"Truck Name","License Plate Number","License Plate Expiration","Status","CDL Required","Year","Make","Model"};
        List<WebElement> els=driver.findElements(By.xpath("//th"));
        int i=0;
        for(WebElement el:els){
            Utility_Functions.xAssertEquals(report,el.getText().trim(),actText[i],"");
            i++;
        }
        commonObj.validateElementExists(TruckPage.helpIcon,"Help Icon '?' is present");
        commonObj.validateElementExists(TruckPage.filterSearch,"Search filter icon is present");
        int size=driver.findElements(TruckPage.pagination).size();
        Utility_Functions.xAssertEquals(report,size,4,"Next page and previous page arrow icon is present");
        String page=Utility_Functions.getText(driver,TruckPage.currentPage);
        commonObj.validateElementExists(TruckPage.currentPage,"Current Page "+page+" "+Utility_Functions.getText(driver,TruckPage.outOf)+"");
        List<WebElement> elm=driver.findElements(TruckPage.show);
        String[] acText={"10","15","30"};
        int j=0;
        for(WebElement el:elm){
            Utility_Functions.xAssertEquals(report,el.getText().trim(),acText[j],"");
            j++;
        }
    }

    /**
     * Keyword to Verify functionality of Help (?)icon
     *
     */
    public void helpIconTruck() {
        click(TruckPage.helpIcon);
        Utility_Functions.timeWait(3);
    }

    /**
     * Keyword to Verify Pagination against current page
     *
     */
    public void selectPage(int actPageNo,String expPage,String arrowIcon){
        click(driver.findElements(DriversPage.pageArrow).get(actPageNo),"Click on "+arrowIcon+" Present below the Right Corner of the page");
        Utility_Functions.xScrollWindow(driver);
        String pageNo=driver.findElement(TruckPage.currentPage).getAttribute("ng-reflect-model");
        Utility_Functions.xAssertEquals(report,pageNo,expPage,"Moved to "+pageNo+" Page");
        Utility_Functions.timeWait(2);
    }

    /**
     * Keyword to Verify functionality of Pagination
     *
     */
    public void paginationTruck() {
        int size=2;
        Utility_Functions.xScrollWindow(driver);
        Utility_Functions.timeWait(2);
        if(Utility_Functions.xIsDisplayed(driver,DriversPage.onePage)){
            commonObj.validateText(DriversPage.onePage,"of 1","One page is available");
        }else {
            click(driver.findElements(DriversPage.pageArrow).get(2));
            Utility_Functions.xScrollWindow(driver);
            while (!Utility_Functions.xIsDisplayed(driver, DriversPage.lastPage)) {
                size++;
                click(driver.findElements(DriversPage.pageArrow).get(2));
                Utility_Functions.xScrollWindow(driver);
            }
            click(driver.findElements(DriversPage.pageArrow).get(0));
            Utility_Functions.xScrollWindow(driver);
            selectPage(2, "2", "Right Arrow (>)");
            selectPage(1, "1", "Left Arrow (<)");
            selectPage(3, "" + size + "", "Right double Arrow (>>)");
            selectPage(0, "1", "Left double Arrow (<<)");
        }
    }

    /**
     * Keyword to verify Page Count
     *
     */
    public void valPageCount(int pageNum) {
        Utility_Functions.xScrollWindow(driver);
        click(By.xpath("//span[text()='"+pageNum+"']"),"Click on '"+pageNum+"' Present below the Left corner of the page");
        int truckCount=driver.findElements(DriversPage.driverNameCount).size();
        if(truckCount==pageNum) {
            Utility_Functions.xAssertEquals(report, "" + truckCount + "", "" + pageNum + "", "'" + pageNum + "' is in disable state and showing " + pageNum + " driver Count");
        }else{
            commonObj.validateElementExists(DriversPage.driverNameCount,"Total Truck added count is "+truckCount+"");
        }
    }

    /**
     * Keyword to Verify functionality of Page Count
     *
     */
    public void funPageCountTruck() {
        Utility_Functions.xScrollWindow(driver);
        if(Utility_Functions.xIsDisplayed(driver,DriversPage.onePage)){
            int truckCount = driver.findElements(DriversPage.driverNameCount).size();
            commonObj.validateText(DriversPage.onePage,"of 1","One page is available having drivers count "+truckCount+"");
        }else {
            int truckCount = driver.findElements(DriversPage.driverNameCount).size();
            Utility_Functions.xAssertEquals(report, "" + truckCount + "", "10", "The Driver Count is 10 by default");
            valPageCount(10);
            valPageCount(15);
            valPageCount(30);
            valPageCount(30);
            valPageCount(10);
        }
    }

    public void navigateToAddNewTruck(){
        click(TruckPage.addNewTruckBtn, "Click on add new Truck button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(TruckPage.addNewTruckHeader,"Add New Truck","Verify Add new Truck Header");
    }

    /**
     * Keyword to verify the UI of Add New Truck Screen
     *
     */
    public void uiAddNewTruck() {
        String[] actText={"Truck Name","License Plate Number","License Plate Expiration","Status","CDL Required","Year","Make","Model","VIN","Truck Weight - Unloaded"};
        List<WebElement> els=driver.findElements(TruckPage.newTruckLabel);
        int i=0;
        for(WebElement el:els){
            Utility_Functions.xAssertEquals(report,el.getText().trim(),actText[i],"");
            i++;
        }
        Utility_Functions.timeWait(2);
        commonObj.validateText(TruckPage.saveBtnDis,"Save Truck","Save Button is Exist and button is disabled");
        commonObj.validateElementExists(DriversPage.crossIcon,"Cross icon is present");
        click(DriversPage.crossIcon,"Click Cross Icon");
        Utility_Functions.timeWait(3);
        commonObj.validateText(TruckPage.trucksHeader,"Trucks","Verify Trucks screen Header");
    }

    /**
     * Keyword to Add Truck
     *
     */
    public void addNewTruck() {
        String truckName=genText();
        int licensePlateNo=Utility_Functions.xRandomFunction();
        sendKeys(TruckPage.truckNameInput, truckName, "Entering truck name");
        sendKeys(TruckPage.licensePlateNumberInput, ""+licensePlateNo+"", "Entering license plate number");
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
        String actText="Truck "+truckName+" successfully created.";
        String expTest=Utility_Functions.getText(driver,By.xpath("//span[contains(text(),'"+truckName+"')]"));
        Utility_Functions.xAssertEquals(report,actText,expTest,"Truck successfully added pop up message");
        System.out.println("//a[text()='"+truckName+"']");
        String actTx=""+truckName+"";
        Utility_Functions.timeWait(3);
        String expTx=driver.findElement(By.xpath("//a[text()='"+truckName+"']")).getText();
        Utility_Functions.xAssertEquals(report,actTx,expTx,"Truck successfully added");
    }

    /**
     * Keyword to verify UI of Search Filters
     *
     */
    public void searchFiltersTruckUI() {
        click(TruckPage.filterSearch,"Click Search Filter icon");
        Utility_Functions.timeWait(2);
        Utility_Functions.xScrollWindow(driver);
        Utility_Functions.timeWait(2);
        commonObj.validateText(TruckPage.searchFilterPanelTitle,"Search Filters","Search Filters panel title is present");
        String[] actText = {"Truck Name", "License Plate Number","Status", "CDL Required"};
        List<WebElement> els = driver.findElements(TruckPage.searchFiltersLabel);
        int i = 0;
        for (WebElement el : els) {
            Utility_Functions.xAssertEquals(report, el.getText().trim(), actText[i], "");
            i++;
        }
        commonObj.validateElementExists(TruckPage.filtersCrossIcon,"Cross icon is present");
        commonObj.validateText(TruckPage.applyFiltersDis,"Apply Filters","Apply Filters button exist and button is disabled");
       // commonObj.validateText(TruckPage.clearFilters,"Clear All Filters","Clear All Filters button is exist and text is in red color");
    }

    /**
     * Keyword to select search filter drop down
     *
     */
    public By getDropDownVal(String option) {
        return  By.xpath("//option[contains(text(),'"+option+"')]");
    }

    public String selectFilter(By by,String label,String option) {
        click(by);
        Utility_Functions.timeWait(1);
        String name=Utility_Functions.getText(driver,getDropDownVal(option));
        System.out.println(name);
        Utility_Functions.timeWait(1);
        click(getDropDownVal(option),"Select "+option+" "+label+" drop Down");
        return name;
    }

    public By getTruck(String label){
        return By.xpath("//table/tbody/tr[1]/td[count(//table/thead/tr/th[contains(text(),'"+label+"')]/preceding-sibling::th)+1]");
    }

    /**
     * Keyword to Apply filter
     *
     */
    public void applyFilter() {
        String truckN=Utility_Functions.getText(driver,TruckPage.truckFirstName);
        String licensePlateN=Utility_Functions.getText(driver,getTruck("License Plate Number"));
        String status=Utility_Functions.getText(driver,getTruck("Status"));
        String cDLRequired=Utility_Functions.getText(driver,getTruck("CDL Required"));

        click(TruckPage.filterSearch,"Click search filter icon");
        Utility_Functions.timeWait(1);
        selectFilter(TruckPage.truckFilter,"Truck Name",truckN);
        selectFilter(TruckPage.licensePlateNoFilter,"License Plate Number",licensePlateN);
        selectFilter(TruckPage.statusFilter,"Status",status);
        selectFilter(TruckPage.cdlRequiredFilter,"CDL Required",cDLRequired);
        click(TruckPage.applyFilter,"Click Apply Filters");
        Utility_Functions.timeWait(2);
        commonObj.validateText(TruckPage.truckFirstName,truckN,"After filter Truck Name: ");
        commonObj.validateText(getTruck("License Plate Number"),licensePlateN,"After filter License Plate Number: ");
        commonObj.validateText(getTruck("Status"),status,"After filter status: ");
        commonObj.validateText(getTruck("CDL Required"),cDLRequired,"After filter status: ");
    }
}
