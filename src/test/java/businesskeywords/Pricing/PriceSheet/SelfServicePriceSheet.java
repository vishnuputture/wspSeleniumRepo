package businesskeywords.Pricing.PriceSheet;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.SalesOrders.SalesOrdersPage;
import pages.pricing.PriceSheet.PriceSheetDetails;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
import pages.pricing.SpecialPricePage;
import supportLibraries.Utility_Functions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class SelfServicePriceSheet extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    CommonActions commonObj;
    // String strDate;
    static String code;

    public SelfServicePriceSheet(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    public void navigateToSelfServicePriceSheet() {
        click(SelfServicePriceSheetPage.companySelector);
        click(SelfServicePriceSheetPage.companyLabel);
        sendKey(SelfServicePriceSheetPage.winCompanyNumber, "99599");
        commonObj.validateText(SelfServicePriceSheetPage.selectButton, "Select", "Validating Select button");
        click(SelfServicePriceSheetPage.selectButton);
        commonObj.validateText(SelfServicePriceSheetPage.headerTitle, "SELF SERVICE PRICE SHEETS", "Validating Landing page title");

    }

    public String generateDate() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy");
        String strDate = formatter.format(dt);
        System.out.println(strDate);
        Utility_Functions.xUpdateJson("priceSheetEffDate", strDate);
        return strDate;
    }

    public void codeGen() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        Utility_Functions.xUpdateJson("priceSheetCode", generatedString);
        System.out.println(generatedString);
    }


    public void addPriceSheetDetails() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strPriceDate = formatter.format(dt);
        System.out.println(strPriceDate);

        codeGen();


        click(SelfServicePriceSheetPage.addPriceSheetbtn);
        autoComplete(SelfServicePriceSheetPage.manufacturer, "koh", SelfServicePriceSheetPage.manufacturerList, jsonData.getData("Manufacturer"));
        sendKey(SelfServicePriceSheetPage.priceSheetName, jsonData.getData("SheetName"));
        // sendKeyDate(SelfServicePriceSheetPage.effectiveDate,strPriceDate);
        Utility_Functions.xSendkeysAndTab(driver.findElement(SelfServicePriceSheetPage.effectiveDate), strPriceDate);
        sendKey(SelfServicePriceSheetPage.priceSheetCode, Utility_Functions.xGetJsonData("priceSheetCode"));
        click(SelfServicePriceSheetPage.choosePriceSheet);
        String path = commonObj.getFilePath() + File.separator + "CostPriceSheetTemplate.xlsx";
        System.out.println(path);
        Utility_Functions.xUploadFile(report, path);
        click(SelfServicePriceSheetPage.saveUpload);
        commonObj.validateText(SelfServicePriceSheetPage.successMessage, "Price Sheet successfully uploaded", "upload Successful");
    }

    public void validateUpload() {
        String strdate = generateDate();
        ;
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataName, jsonData.getData("SheetName"), "Name Matched");
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataManufacturer, jsonData.getData("Manufacturer"), "Manufacturer Matched");
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataCode, strdate + "-" + Utility_Functions.xGetJsonData("priceSheetCode"), "Code Matched");
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataStatus, "available", "Status Matched");
    }

    public void navigateToPriceSheetDetailsPage() {
        click(SelfServicePriceSheetPage.uploadedDataName);
        commonObj.validateText(PriceSheetDetails.detailsPageHeader, "Price Sheet Details", "inside Details Page");

    }

    public void verifyUploadedSheetDetails() {
        commonObj.validateText(PriceSheetDetails.codeValue, Utility_Functions.xGetJsonData("priceSheetEffDate") + "-" + Utility_Functions.xGetJsonData("priceSheetCode"), "code Matched");
        commonObj.validateText(PriceSheetDetails.manufacturerValue, jsonData.getData("Manufacturer"), "Manufacturer Matched");
        commonObj.validateText(PriceSheetDetails.statusValue, "Available", "Status Matched");
        String temp = driver.findElement(PriceSheetDetails.nameValue).getAttribute("value");
        System.out.println("The innertext is " + temp);
        // commonObj.validateText(PriceSheetDetails.nameValue,temp,"name matched");
        if (temp.equalsIgnoreCase(jsonData.getData("SheetName"))) {
            report.updateTestLog("VerifyRecord", "Name Matched", Status.PASS);
        } else {
            report.updateTestLog("VerifyRecord", "Name Mis-Matched", Status.FAIL);
        }

    }


    public void verifyItemUpload() {
        List<WebElement> arr = getRowData(PriceSheetDetails.wiseItems);
        String[] items = jsonData.getData("WiseItem").split(",");
        for (int i = 0; i < arr.size(); i++) {
            Utility_Functions.xAssertEquals(report, arr.get(i).getText(), items[i], "Wise Item Present");
        }
    }

    public void fillSheetData() {

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 0);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strPriceDate = formatter.format(dt);

        sendKey(PriceSheetDetails.sheetMultiplier, "0.4");
        click(PriceSheetDetails.updateListPrice);
        click(PriceSheetDetails.updatePOCost);
        click(PriceSheetDetails.updateMatrixCost);


        Utility_Functions.xSendkeysAndTab(driver.findElement(PriceSheetDetails.processedDate), strPriceDate);


        click(PriceSheetDetails.saveButton);
        commonObj.validateText(PriceSheetDetails.statusValue, "Maintaining", "Status Matched");

    }

    public void saveSheet() {
        click(PriceSheetDetails.saveButton);
        Utility_Functions.timeWait(1);
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataStatus, "Maintaining", "Status Matched");
    }


    public void sheetOnHold() {
        click(PriceSheetDetails.onHoldButton);
        Utility_Functions.timeWait(1);
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataStatus, "On-Hold", "Status Matched");

    }

    public void unHoldSheet() {
        click(PriceSheetDetails.removeHoldButton);
        Utility_Functions.timeWait(1);
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataStatus, "Maintaining", "Status Matched");

    }

    public void markReady() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 0);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strPriceDate = formatter.format(dt);

        Utility_Functions.xSendkeysAndTab(driver.findElement(PriceSheetDetails.processedDate), strPriceDate);
        click(PriceSheetDetails.markAsReadyButton);
        Utility_Functions.timeWait(1);
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataStatus, "Ready to Process", "Status Matched");


    }

    /**
     * This method To Search by Name
     */
    public void searchByName(String fieldValue) {
        Utility_Functions.timeWait(7);
        Boolean bl = Utility_Functions.waitForElementVisible(driver, SelfServicePriceSheetPage.firstSearchByName, 10);
        System.out.println("bl............" + bl);
        if (bl) {
            sendKeys(SelfServicePriceSheetPage.searchByName, "" + fieldValue + "", "Search By Name");
            Utility_Functions.waitForElementVisible(driver, By.xpath("//li[contains(text(),'" + fieldValue + "')]"), 10);
            click(By.xpath("//li[contains(text(),'" + fieldValue + "')]"));
            Utility_Functions.timeWait(6);
            Utility_Functions.waitForElementVisible(driver, SelfServicePriceSheetPage.firstSearchByName, 10);
            Utility_Functions.xAssertEquals(report, Utility_Functions.xIsDisplayed(driver, SelfServicePriceSheetPage.firstSearchByName), true, "");
        } else {
            throw new NoSuchElementException("Pricing Sheet not found");
        }
    }

    /**
     * This method To Filter By Manufacturer
     */
    public void filterByManufacturer(String fieldValue) {
        if (Utility_Functions.waitForElementVisible(driver, SelfServicePriceSheetPage.firstSearchByName, 10)) {
            int size = driver.findElements(SelfServicePriceSheetPage.filterManufacturer).size();
            click(driver.findElements(SelfServicePriceSheetPage.filterManufacturer).get(size - 1));
            sendKeys(SelfServicePriceSheetPage.filterManufacturerDropField, "" + fieldValue + "", "Filter By Manufacturer");
            Utility_Functions.waitForElementVisible(driver, By.xpath("//li[contains(text(),'" + fieldValue + "')]"), 10);
            click(By.xpath("//span[contains(text(),'" + fieldValue + "')]"));
            Utility_Functions.timeWait(6);
            Utility_Functions.waitForElementVisible(driver, SelfServicePriceSheetPage.firstSearchByName, 10);
            Utility_Functions.xAssertEquals(report, Utility_Functions.xIsDisplayed(driver, SelfServicePriceSheetPage.firstSearchByName), true, "");
        } else {
            throw new NoSuchElementException("Pricing Sheet is not found");
        }
    }

    /**
     * This method To Filter By Status
     */
    public void filterByStatus(String dropOpt) {
        Utility_Functions.waitForElementVisible(driver, SelfServicePriceSheetPage.filterByStatus, 10);
        click(SelfServicePriceSheetPage.filterByStatus, "Click Filter By Status drop down");
        click(By.xpath("//span[text()='" + dropOpt + "']"), "CLick " + dropOpt + " Option");
        Utility_Functions.timeWait(3);
        try {
            String val = Utility_Functions.getText(driver, By.xpath("//span[text()=' " + dropOpt + " ']")).trim();
            int size = driver.findElements(By.xpath("//span[text()=' " + dropOpt + " ']")).size();
            Utility_Functions.xAssertEquals(report, val, dropOpt, "Status: " + dropOpt + " showing " + size + " Price sheet");
        } catch (Exception e) {
            Utility_Functions.xAssertEquals(report, dropOpt, dropOpt, "Status: " + dropOpt + " showing 0 Price sheet");
        }
    }

    /**
     * This method To search Effective date
     */
    public void date(String startDate, String endDate) {
        Utility_Functions.waitForElementVisible(driver, SelfServicePriceSheetPage.startDate, 10);
        sendKeys(SelfServicePriceSheetPage.startDate, "" + startDate + "", "Enter Start Date: " + startDate + "");
        Utility_Functions.timeWait(4);
        sendKeys(SelfServicePriceSheetPage.endDate, "" + endDate + "", "Enter End Date: " + endDate + "");
        Utility_Functions.timeWait(6);
        click(driver.findElement(By.xpath("//h2[text()='SELF SERVICE PRICE SHEETS']")));
        Boolean bl = Utility_Functions.xIsDisplayed(driver, SelfServicePriceSheetPage.firstSearchByName);
        if (bl) {
            Utility_Functions.xAssertEquals(report, bl, true, "");
        } else {
            throw new NoSuchElementException("Effective date for start date:" + startDate + " and endDate:" + endDate + " not found");
        }
    }

    /**
     * This method To Filter search by name
     */
    public void filterSearchByName() {
        searchByName(jsonData.getData("SheetName"));
    }

    public void refreshToCancel() {
        driver.navigate().refresh();
    }

    /**
     * This method To Filter By Manufacturer
     */
    public void filterByManufacturer() {
        Utility_Functions.timeWait(3);
        filterByManufacturer(jsonData.getData("Manufacturer"));
    }

    public void closeIcon() {
        click(SelfServicePriceSheetPage.closeIcon);
    }

    /**
     * This method To Filter By Status
     */
    public void filterByStatus() {
        Utility_Functions.timeWait(3);
        filterByStatus(jsonData.getData("Available"));
        filterByStatus(jsonData.getData("Maintaining"));
        filterByStatus(jsonData.getData("On-Hold"));
        filterByStatus(jsonData.getData("Ready_to_Process"));
        filterByStatus(jsonData.getData("Processed"));
        filterByStatus(jsonData.getData("Unavailable"));
    }

    /**
     * This method To Filter By Status
     */
    public void filterByEffectiveDate() {
        Utility_Functions.timeWait(3);
        date(jsonData.getData("Start_Date"), jsonData.getData("End_Date"));
    }

    /**
     * This method To Filter Price sheet Records
     */
    public void filterPriceSheetRecords() {
        searchByName(jsonData.getData("SheetName"));
        driver.navigate().refresh();
        Utility_Functions.timeWait(3);
        filterByManufacturer(jsonData.getData("Manufacturer"));
        click(SelfServicePriceSheetPage.closeIcon);
        Utility_Functions.timeWait(3);
        filterByStatus(jsonData.getData("Available"));
        filterByStatus(jsonData.getData("Maintaining"));
        filterByStatus(jsonData.getData("On-Hold"));
        filterByStatus(jsonData.getData("Ready_to_Process"));
        filterByStatus(jsonData.getData("Processed"));
        filterByStatus(jsonData.getData("Unavailable"));
        driver.navigate().refresh();
        Utility_Functions.timeWait(3);
        date(jsonData.getData("Start_Date"), jsonData.getData("End_Date"));
    }
}
