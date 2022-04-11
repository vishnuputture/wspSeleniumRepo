package businesskeywords.Pricing.PriceSheet;

import bsh.util.Util;
import businesskeywords.common.Login;
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
    Login login;
    // String strDate;
    static String code;

    public SelfServicePriceSheet(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        login=new Login(helper);
    }

    public void navigateToSelfServicePriceSheet() {
        click(SelfServicePriceSheetPage.companySelector);
        click(SelfServicePriceSheetPage.companyLabel);
        sendKey(SelfServicePriceSheetPage.winCompanyNumber, "99599");
        commonObj.validateText(SelfServicePriceSheetPage.selectButton, "Select", "Validating Select button");
        click(SelfServicePriceSheetPage.selectButton);
        commonObj.validateText(SelfServicePriceSheetPage.headerTitle, "SELF SERVICE PRICE SHEETS", "Validating Landing page title");

    }

    public void extractSheetDetails() {
        Utility_Functions.timeWait(5);
        Utility_Functions.xUpdateJson("PoCostMultiplier", driver.findElement(SelfServicePriceSheetPage.poCostMultiplier).getAttribute("value"));
        Utility_Functions.xUpdateJson("MatrixCostMultiplier", driver.findElement(SelfServicePriceSheetPage.matrixCostMultiplier).getAttribute("value"));
        Utility_Functions.xUpdateJson("ListPrice", driver.findElement(SelfServicePriceSheetPage.listPrice).getText());
    }

    public void reLoadPageIfProcessNowButtonNotAvailable() {
        click(PriceSheetDetails.menuPriceSheet);
        if(isDisplayed(PriceSheetDetails.toolTipPriceSheet)) {
            click(PriceSheetDetails.toolTipPriceSheet);
        }
        navigateToSelfServicePriceSheet();
        navigateToPriceSheetDetailsPage();
    }

    public void processSheet() {
        Utility_Functions.timeWait(3);
        checkDateInvalid(1);
        Utility_Functions.timeWait(3);
        if (!isDisplayed(PriceSheetDetails.processNowButton)) {
            reLoadPageIfProcessNowButtonNotAvailable();
        }
        Utility_Functions.timeWait(3);
        if (isDisplayed(PriceSheetDetails.processNowDisButton)) {
            click(PriceSheetDetails.updateListPrice);
        }
        clickProcessNow();
    }

    public void clickProcessNow(){
        try {
            click(PriceSheetDetails.processNowButton, "Click [Process Now] Button");
        } catch (Exception e) {
            Utility_Functions.timeWait(5);
            click(PriceSheetDetails.processNowButton, "Click [Process Now] Button");
        }
    }

    public void openNewTab() {
        Utility_Functions.openNewTab(driver);
    }

    public void validateSheetProcessed() {
        Utility_Functions.timeWait(4);
        driver.navigate().refresh();
        Utility_Functions.timeWait(6);
        String status = driver.findElement(By.xpath("//tbody//tr//td[text()='" + Utility_Functions.xGetJsonData("priceSheetName") + "']//following-sibling::td//span")).getText();
        System.out.println("Status :" + status);
        if (status.equalsIgnoreCase("Processed")) {
            report.updateTestLog("VerifyRecord", "status Matched", Status.PASS);
        } else {
            report.updateTestLog("VerifyRecord", "status Mis-Matched", Status.FAIL);
        }
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
        String name = Utility_Functions.getRandomName();
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 2);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strPriceDate = formatter.format(dt);
        System.out.println(strPriceDate);

        codeGen();

        Utility_Functions.timeWait(6);
        click(SelfServicePriceSheetPage.addPriceSheetbtn);

        autoComplete(SelfServicePriceSheetPage.manufacturer, "koh", SelfServicePriceSheetPage.manufacturerList, jsonData.getData("Manufacturer"));
        sendKey(SelfServicePriceSheetPage.priceSheetName, name);
        // sendKeyDate(SelfServicePriceSheetPage.effectiveDate,strPriceDate);
        Utility_Functions.xSendkeysAndTab(driver.findElement(SelfServicePriceSheetPage.effectiveDate), strPriceDate);
        sendKey(SelfServicePriceSheetPage.priceSheetCode, Utility_Functions.xGetJsonData("priceSheetCode"));

        click(SelfServicePriceSheetPage.choosePriceSheet);
        String path = commonObj.getFilePath() + File.separator + "CostPriceSheetTemplate.xlsx";
        System.out.println(path);
        Utility_Functions.xUploadFile(report, path);
        click(SelfServicePriceSheetPage.saveUpload);
        Utility_Functions.timeWait(2);
        commonObj.validateText(SelfServicePriceSheetPage.successMessage, "Price Sheet successfully uploaded", "upload Successful");
        Utility_Functions.xUpdateJson("priceSheetName", name);
    }

    public void validateUpload() {
        String strdate = generateDate();
        ;
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataName, Utility_Functions.xGetJsonData("priceSheetName"), "Name Matched");
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataManufacturer, jsonData.getData("Manufacturer"), "Manufacturer Matched");
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataCode, strdate + "-" + Utility_Functions.xGetJsonData("priceSheetCode"), "Code Matched");
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataStatus, "available", "Status Matched");
        commonObj.validateText(SelfServicePriceSheetPage.successMessage, "Price Sheet successfully uploaded", "upload Successful");
    }


    public void navigateToPriceSheetDetailsPage() {
        try {
            click(SelfServicePriceSheetPage.uploadedDataName);
        } catch (Exception e) {
            Utility_Functions.timeWait(5);
            click(SelfServicePriceSheetPage.uploadedDataName);
        }
        commonObj.validateText(PriceSheetDetails.detailsPageHeader, "Price Sheet Details", "inside Details Page");
    }


    public void verifyUploadedSheetDetails() {
        commonObj.validateText(PriceSheetDetails.codeValue, Utility_Functions.xGetJsonData("priceSheetEffDate") + "-" + Utility_Functions.xGetJsonData("priceSheetCode"), "code Matched");
        commonObj.validateText(PriceSheetDetails.manufacturerValue, jsonData.getData("Manufacturer"), "Manufacturer Matched");
        commonObj.validateText(PriceSheetDetails.statusValue, "Available", "Status Matched");
        String temp = driver.findElement(PriceSheetDetails.nameValue).getAttribute("value");
        System.out.println("The innertext is " + temp);
        // commonObj.validateText(PriceSheetDetails.nameValue,temp,"name matched");
        if (temp.equalsIgnoreCase(Utility_Functions.xGetJsonData("priceSheetName"))) {
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
        Utility_Functions.timeWait(4);
        try {
            Utility_Functions.timeWait(6);
            click(PriceSheetDetails.saveButton);
        } catch (Exception e) {
            Utility_Functions.timeWait(6);
            click(PriceSheetDetails.saveButton);
        }
        commonObj.validateText(PriceSheetDetails.statusValue, "Maintaining", "Status Matched");

    }

    public void checkDateInvalid(int i) {
        if (isDisplayed(PriceSheetDetails.invalidDate)) {
            Date dt = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, i);
            dt = c.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            String strPriceDate = formatter.format(dt);
            Utility_Functions.xSendkeysAndTab(driver.findElement(PriceSheetDetails.processedDate), strPriceDate);
            Utility_Functions.timeWait(3);
        }
    }

    public void saveSheet() {
        checkDateInvalid(0);
        click(PriceSheetDetails.saveButton);
        Utility_Functions.timeWait(5);
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

    public void processDatePick(){
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strPriceDate = formatter.format(dt);
        Utility_Functions.timeWait(3);
        Utility_Functions.xSendkeysAndTab(driver.findElement(PriceSheetDetails.processedDate), strPriceDate);
    }

    public void markReady() {
        processDatePick();
        Utility_Functions.timeWait(3);
        click(PriceSheetDetails.markAsReadyButton);
        Utility_Functions.timeWait(5);
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
            Utility_Functions.timeWait(4);
            boolean bl1 = Utility_Functions.xIsDisplayed(driver, By.xpath("//li[contains(text(),'" + fieldValue + "')]"));
            if (bl1) {
                click(By.xpath("//li[contains(text(),'" + fieldValue + "')]"));
                Utility_Functions.timeWait(4);
                Utility_Functions.xAssertEquals(report, bl1, true, "");
            } else {
                Utility_Functions.xAssertEquals(report, bl1, false, "Price Sheet Name: " + fieldValue + " not found...");
            }
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
            Boolean bl1 = Utility_Functions.xIsDisplayed(driver, SelfServicePriceSheetPage.firstSearchByName);
            if (bl1) {
                Utility_Functions.xAssertEquals(report, bl1, true, "Manufacturer: " + fieldValue + " found...");
            } else {
                Utility_Functions.xAssertEquals(report, false, false, "Manufacturer: " + fieldValue + " not found...");
            }
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
            Utility_Functions.xAssertEquals(report, val, dropOpt, "Status: " + dropOpt + " showing " + size + " Price sheet...");
        } catch (Exception e) {
            Utility_Functions.xAssertEquals(report, dropOpt, dropOpt, "Status: " + dropOpt + " showing 0 Price sheet...");
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
            Utility_Functions.xAssertEquals(report, bl, false, "Effective date for start date:" + startDate + " and endDate:" + endDate + " not found... ");
        }
    }

    /**
     * This method To Filter search by name
     */
    public void filterSearchByName() {
        searchByName(Utility_Functions.xGetJsonData("priceSheetName"));
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

    /**
     * This method To Verify ERROR MESSAGE
     */
    public void verifyError(String val, String errMsg) {
        Utility_Functions.timeWait(2);
        Utility_Functions.xMouseDoubleClick(driver, driver.findElement(SelfServicePriceSheetPage.listPrice));
        Utility_Functions.timeWait(2);
        Utility_Functions.xSendkeysAndTab(report, driver.findElement(SelfServicePriceSheetPage.listPriceInput), val, "Enter " + val + " into List Price roe text field");
        String color = driver.findElement(SelfServicePriceSheetPage.listPrice).getCssValue("background-color");
        Utility_Functions.xAssertEquals(report, color, "rgba(204, 0, 0, 0.1)", "List Price Input box Color changed to RED");
        int size=driver.findElements(SelfServicePriceSheetPage.errorIcon).size();
        Utility_Functions.xmouseOver(driver, driver.findElements(SelfServicePriceSheetPage.errorIcon).get(size-1));
        String error = driver.findElements(SelfServicePriceSheetPage.errorIcon).get(size-1).getAttribute("data-original-title");
        Utility_Functions.xmouseOut(driver,driver.findElements(SelfServicePriceSheetPage.errorIcon).get(size-1));
        Utility_Functions.timeWait(3);
        Utility_Functions.xAssertEquals(report, error, errMsg, "Error Message: ");
    }

    /**
     * This method To Verify List Price
     */
    public void verifyListPriceRow() {
        Utility_Functions.timeWait(8);
        Utility_Functions.xMouseDoubleClick(driver, driver.findElement(SelfServicePriceSheetPage.listPrice));
        Utility_Functions.timeWait(2);
        Utility_Functions.xSendkeysAndTab(report, driver.findElement(SelfServicePriceSheetPage.listPriceInput), "0", "");
        String color = driver.findElement(SelfServicePriceSheetPage.listPrice).getCssValue("background-color");
        if (color.equals("rgba(255, 255, 255, 1)")) {
            click(SelfServicePriceSheetPage.updateListPriceCheckBox, "Click Update List Price Check Box");
        }
        verifyError("0", "List Price cannot be 0 when Update List Price is checked.");
        verifyError("-1", "List Price must be numeric and not negative.");
        verifyError(" ", "List Price cannot be blank when Update List Price is checked.");
        verifyError("12345678", "List Price must be numeric and not negative.");
    }

    public void addPriceSheetDetailsNetSheet() {
        String name = Utility_Functions.getRandomName();
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
        Utility_Functions.timeWait(5);
        System.out.println("DOne.....");
        sendKey(SelfServicePriceSheetPage.priceSheetName, name);
        Utility_Functions.xSendkeysAndTab(driver.findElement(SelfServicePriceSheetPage.effectiveDate), strPriceDate);
        sendKey(SelfServicePriceSheetPage.priceSheetCode, Utility_Functions.xGetJsonData("priceSheetCode"));
        click(SelfServicePriceSheetPage.netPrice);
        click(SelfServicePriceSheetPage.choosePriceSheet);
        String path = commonObj.getFilePath() + File.separator + "CostPriceSheetTemplate.xlsx";
        System.out.println(path);
        Utility_Functions.xUploadFile(report, path);
        click(SelfServicePriceSheetPage.saveUpload);
        commonObj.validateText(SelfServicePriceSheetPage.successMessage, "Price Sheet successfully uploaded", "upload Successful");
        Utility_Functions.xUpdateJson("priceSheetName", name);
    }

    public void fillSheetDataNetPrice() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 0);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strPriceDate = formatter.format(dt);
        Utility_Functions.timeWait(2);
        Utility_Functions.xSendkeysAndTab(driver.findElement(PriceSheetDetails.processedDate), strPriceDate);
        Utility_Functions.timeWait(2);
        int size = driver.findElements(PriceSheetDetails.errorMessage).size();
        int count = 0;
        if (Utility_Functions.xIsDisplayed(driver, PriceSheetDetails.errorMessage)) {
            for (int i = 0; count < size; count++) {
                if (count == size - 1) {
                    Utility_Functions.xScrollIntoView(driver, driver.findElements(PriceSheetDetails.errorMessage).get(i));
                }
                Utility_Functions.timeWait(2);
                Utility_Functions.xMouseDoubleClick(driver, driver.findElements(PriceSheetDetails.errorMessage).get(i));
                Utility_Functions.timeWait(2);
                Utility_Functions.xSendkeysAndTab(report, driver.findElement(SelfServicePriceSheetPage.listPriceInput), "2", "");
            }
        }
        Utility_Functions.timeWait(2);
        click(PriceSheetDetails.saveButton);
        Utility_Functions.timeWait(6);
        commonObj.validateText(PriceSheetDetails.statusValue, "Maintaining", "Status Matched");
    }
}
