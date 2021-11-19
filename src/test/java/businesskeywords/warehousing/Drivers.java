package businesskeywords.warehousing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
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

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public Drivers(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    /**
     * Keyword to Select Company
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
     * Keyword to Navigate to Drivers Screen
     *
     */
    public void navigateToDriversScreen(){
        selectCompany();
        click(TruckPage.menuIconTruck);
        click(DriversPage.subMenuDriver, "Navigate to Drivers page");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.timeWait(3);
        commonObj.validateText(DriversPage.driversHeader,"Drivers","Drivers Screen Header is present");
    }

    /**
     * Keyword to Generate Random Text
     *
     */
    public String genText(){
        String userName = "";
        for(int i=0; i<=16;i++) {
            Random random = new Random();
            char randomizedCharacter = (char) (random.nextInt(26) + 'A');
            userName = userName+randomizedCharacter;
        }
        return userName;
    }

    /**
     * Keyword to verify the availability of field again Drivers Screen
     *
     */
    public void verifyAvailFieldDrivers() {
        String[] actText={"Driver Name","Alias","Rank","Status","CDL","CDL Expiration","Accept Adjustment"};
        List<WebElement> els=driver.findElements(By.xpath("//th"));
        int i=0;
        for(WebElement el:els){
            Utility_Functions.xAssertEquals(report,el.getText().trim(),actText[i],"");
            i++;
        }
        Utility_Functions.timeWait(2);
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
    public void helpIcon() {
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
    public void pagination() {
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
        int driverCount=driver.findElements(DriversPage.driverNameCount).size();
        if(driverCount==pageNum) {
            Utility_Functions.xAssertEquals(report, "" + driverCount + "", "" + pageNum + "", "'" + pageNum + "' is in disable state and showing " + pageNum + " driver Count");
        }else{
            commonObj.validateElementExists(DriversPage.driverNameCount,"Total count is "+driverCount+"");
        }
    }

    /**
     * Keyword to Verify functionality of Page Count
     *
     */
    public void funPageCount() {
        Utility_Functions.xScrollWindow(driver);
        if(Utility_Functions.xIsDisplayed(driver,DriversPage.onePage)){
            int driverCount = driver.findElements(DriversPage.driverNameCount).size();
            commonObj.validateText(DriversPage.onePage,"of 1","One page is available having count "+driverCount+"");
        }else {
            int driverCount = driver.findElements(DriversPage.driverNameCount).size();
            System.out.println("........." + driverCount + ".......");
            Utility_Functions.xAssertEquals(report, "" + driverCount + "", "10", "The Count is 10 by default");
            valPageCount(10);
            valPageCount(15);
            valPageCount(30);
            valPageCount(30);
            valPageCount(10);
        }
    }

    public void navigateToAddNewDriver(){
        click(DriversPage.addNewDriver, "Click on add new driver button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(DriversPage.newDriverHeader,"Add New Driver","Verify Add new Driver Header");
    }

    /**
     * Keyword to verify the UI of Add New Driver Screen
     *
     */
    public void uiAddNewDriver() {
        String[] actText={"First Name","Last Name","Alias","Rank","Status","Accept Adjustments","Employee","Username","Has CDL?"};
        List<WebElement> els=driver.findElements(DriversPage.addNewDriverLabel);
        int i=0;
        for(WebElement el:els){
            Utility_Functions.xAssertEquals(report,el.getText().trim(),actText[i],"");
            i++;
        }
        Utility_Functions.timeWait(2);
        commonObj.validateText(DriversPage.saveDriverDis,"Save Driver","Save Button is Exist and button is disabled");
        commonObj.validateElementExists(DriversPage.crossIcon,"Cross icon is present");
        click(DriversPage.crossIcon,"Click Cross Icon");
        Utility_Functions.timeWait(2);
        commonObj.validateText(DriversPage.driversHeader,"Drivers","Verify Drivers screen Header");
    }

    /**
     * Keyword to Add new Driver
     *
     */
    public void addNewDriver() {
        sendKeys(DriversPage.firstName, jsonData.getData("firstName"), "Entering first name");
        String lastName=genText();
        sendKeys(DriversPage.lastname, lastName, "Entering last name");
        sendKeys(DriversPage.alias, jsonData.getData("alias"), "Entering alias name");
        sendKeys(DriversPage.username, genText(), "Entering username name");
        click(DriversPage.saveDriver, "Saving record");
        Utility_Functions.timeWait(3);
        click(DriversPage.cdlSort);
        Utility_Functions.timeWait(2);
        String actText="Driver "+jsonData.getData("firstName")+" "+lastName+" ("+jsonData.getData("alias")+") successfully created.";
        String expTest=Utility_Functions.getText(driver,By.xpath("//span[contains(text(),'"+jsonData.getData("firstName")+" "+lastName+"')]"));
        Utility_Functions.xAssertEquals(report,actText,expTest,"Driver successfully added pop up message");
        System.out.println("//a[text()='"+jsonData.getData("firstName")+" "+lastName+"']");
        String actTx=""+jsonData.getData("firstName")+" "+lastName+"";
        Utility_Functions.timeWait(3);
        String expTx=driver.findElement(By.xpath("//a[text()='"+jsonData.getData("firstName")+" "+lastName+"']")).getText();
        Utility_Functions.xUpdateJson("Driver",expTx);
        Utility_Functions.xAssertEquals(report,actTx,expTx,"Driver successfully added");
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
    public void applyFilterDriver() {
        String rank=Utility_Functions.getText(driver,getTruck("Rank"));
        String status=Utility_Functions.getText(driver,getTruck("Status"));
        click(TruckPage.filterSearch,"Click search filter icon");
        Utility_Functions.timeWait(1);
        selectFilter(DriversPage.driverNameSelect,"Driver Name",Utility_Functions.xGetJsonData("Driver"));
        selectFilter(DriversPage.rankSelect,"Rank",rank);
        selectFilter(DriversPage.statusSelect,"Status",status);
        click(TruckPage.applyFilter,"Click Apply Filters");
        Utility_Functions.timeWait(2);
        commonObj.validateText(DriversPage.driverFirstName,Utility_Functions.xGetJsonData("Driver"),"After filter Driver Name: ");
        commonObj.validateText(getTruck("Rank"),rank,"After filter rank: ");
        commonObj.validateText(getTruck("Status"),status,"After filter status: ");
    }
}
