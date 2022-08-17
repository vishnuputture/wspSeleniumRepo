package businesskeywords.Pricing.PriceSheet;

import businesskeywords.common.Login;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.Purchasing.SPO.SpoPage;
import pages.pricing.PriceSheet.PriceSheetDetails;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
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
    private FrameworkDriver ownDriver;
    Login login;
    // String strDate;
    static String code;

    public SelfServicePriceSheet(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        login = new Login(helper);
        ownDriver = helper.getGSDriver();
    }

    public By linkEle(String val) {
        return By.xpath("//a[text()='" + val + "']");
    }

    public By labelInput(String label) {
        return By.xpath("//label[contains(text(),'" + label + "')]/parent::div/descendant::input");
    }

    public By checkedFields(String field) {
        return By.xpath("//label[text()='" + field + "']/following-sibling::input[contains(@class,'ng-untouched')]");
    }

    public By nonEditableField(String field) {
        return By.xpath("//label[text()='" + field + "']/following-sibling::div");
    }

    public void navigateToSelfServicePriceSheet() {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, By.xpath("//h1"), "Wait for page to be loaded");
        click(SelfServicePriceSheetPage.companySelector);
        click(SelfServicePriceSheetPage.companyLabel);
        sendKey(SelfServicePriceSheetPage.winCompanyNumber, "99599");
        commonObj.validateText(SelfServicePriceSheetPage.selectButton, "Select", "Validating Select button");
        click(SelfServicePriceSheetPage.selectButton);
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, SelfServicePriceSheetPage.headerTitle, "Wait for page to be loaded");
        // Utility_Functions.timeWait(6);
        commonObj.validateText(SelfServicePriceSheetPage.headerTitle, "SELF SERVICE PRICE SHEETS", "Validating Landing page title");
    }

    public void extractSheetDetails() {
        Utility_Functions.timeWait(5);
        if(!ownDriver.findElement(PriceSheetDetails.updateListPrice).isSelected()) {
            click(PriceSheetDetails.updateListPrice);
        }
        if(!ownDriver.findElement(PriceSheetDetails.updatePOCost).isSelected()) {
            click(PriceSheetDetails.updatePOCost);
        }
        if(!ownDriver.findElement(PriceSheetDetails.updateMatrixCost).isSelected()) {
            click(PriceSheetDetails.updateMatrixCost);
        }
        Utility_Functions.xUpdateJson("PoCostMultiplier", ownDriver.findElement(SelfServicePriceSheetPage.poCostMultiplier).getText().replace("$",""));
        Utility_Functions.xUpdateJson("MatrixCostMultiplier", ownDriver.findElement(SelfServicePriceSheetPage.matrixCostMultiplier).getText().replace("$",""));
        Utility_Functions.xUpdateJson("ListPrice", ownDriver.findElement(SelfServicePriceSheetPage.listPrice).getText().replace("$",""));
        click(PriceSheetDetails.saveButton);
        Utility_Functions.timeWait(2);
    }

    public void reLoadPageIfProcessNowButtonNotAvailable() {
        click(PriceSheetDetails.menuPriceSheet);
        if (isDisplayed(PriceSheetDetails.toolTipPriceSheet)) {
            click(PriceSheetDetails.toolTipPriceSheet);
        }
        navigateToSelfServicePriceSheet();
        navigateToPriceSheetDetailsPage();
    }

    public void checkProcessNowButton() {
        Utility_Functions.timeWait(3);
        checkDateInvalid(1);
        Utility_Functions.timeWait(3);
        if (!isDisplayed(PriceSheetDetails.processNowButton)) {
            reLoadPageIfProcessNowButtonNotAvailable();
        }
        Utility_Functions.timeWait(3);
        if (isDisplayed(PriceSheetDetails.processNowDisButton)) {
            click(PriceSheetDetails.updateListPrice);
            checkDateInvalid(1);
        }
    }

    public void processSheet() {
        checkProcessNowButton();
        clickProcessNow();
    }

    public void clickProcessNow() {
        try {
            click(PriceSheetDetails.processNowButton, "Click [Process Now] Button");
        } catch (Exception e) {
            Utility_Functions.timeWait(5);
            click(PriceSheetDetails.processNowButton, "Click [Process Now] Button");
        }
    }

    public void openNewTab() {
        Utility_Functions.openNewTab(ownDriver);
    }

    public void validateSheetProcessed() {
        Utility_Functions.timeWait(4);
        ownDriver.navigate().refresh();
        Utility_Functions.timeWait(6);
        String status = ownDriver.findElement(By.xpath("//tbody//tr//td[text()='" + Utility_Functions.xGetJsonData("priceSheetName") + "']//following-sibling::td//span")).getText();
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
        c.add(Calendar.DATE, 2);
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
        Utility_Functions.xSendkeysAndTab(ownDriver.findElement(SelfServicePriceSheetPage.effectiveDate), strPriceDate);
        sendKey(SelfServicePriceSheetPage.priceSheetCode, Utility_Functions.xGetJsonData("priceSheetCode"));

        click(SelfServicePriceSheetPage.choosePriceSheet);
        String path = commonObj.getFilePath() + File.separator + "CostPriceSheetTemplate.xlsx";
        System.out.println(path);
        Utility_Functions.xUploadFile(report, path);
        click(SelfServicePriceSheetPage.saveUpload);
        Utility_Functions.timeWait(2);
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, SelfServicePriceSheetPage.successMessage, "Wait for page to be loaded");
        commonObj.validateText(SelfServicePriceSheetPage.successMessage, "Price Sheet successfully uploaded", "upload Successful");
        Utility_Functions.xUpdateJson("priceSheetName", name);
    }

    public void validateUpload() {
        String strdate = generateDate();
        Utility_Functions.timeWait(4);
        commonObj.validateText(SelfServicePriceSheetPage.successMessage, "Price Sheet successfully uploaded", "upload Successful");
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataName, Utility_Functions.xGetJsonData("priceSheetName"), "Name Matched");
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataManufacturer, jsonData.getData("Manufacturer"), "Manufacturer Matched");
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataStatus, "available", "Status Matched");
    }


    public void navigateToPriceSheetDetailsPage() {
        try {
            click(SelfServicePriceSheetPage.uploadedDataName);
        } catch (Exception e) {
            Utility_Functions.timeWait(5);
            click(SelfServicePriceSheetPage.uploadedDataName);
        }
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, PriceSheetDetails.detailsPageHeader, "Wait for Page to be loaded");
        commonObj.validateText(PriceSheetDetails.detailsPageHeader, "Price Sheet Details", "inside Details Page");
    }


    public void verifyUploadedSheetDetails() {
        Utility_Functions.timeWait(4);
        commonObj.validateText(PriceSheetDetails.codeValue, Utility_Functions.xGetJsonData("priceSheetEffDate") + "-" + Utility_Functions.xGetJsonData("priceSheetCode"), "code Matched");
        commonObj.validateText(PriceSheetDetails.manufacturerValue, jsonData.getData("Manufacturer"), "Manufacturer Matched");
        commonObj.validateText(PriceSheetDetails.statusValue, "Available", "Status Matched");
        String temp = ownDriver.findElement(PriceSheetDetails.nameValue).getAttribute("value");
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


        Utility_Functions.xSendkeysAndTab(ownDriver.findElement(PriceSheetDetails.processedDate), strPriceDate);
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
        Utility_Functions.timeWait(2);
        if (isDisplayed(PriceSheetDetails.invalidDate)) {
            Date dt = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, i);
            dt = c.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            String strPriceDate = formatter.format(dt);
            Utility_Functions.xSendkeysAndTab(ownDriver.findElement(PriceSheetDetails.processedDate), strPriceDate);
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

    public void processDatePick() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strPriceDate = formatter.format(dt);
        Utility_Functions.timeWait(3);
        Utility_Functions.xSendkeysAndTab(ownDriver.findElement(PriceSheetDetails.processedDate), strPriceDate);
    }

    public void markReady() {
        processDatePick();
        Utility_Functions.timeWait(3);
        try {
            click(PriceSheetDetails.markAsReadyButton);
        }catch (Exception e){
            click(SelfServicePriceSheetPage.updateListPriceCheckBox, "Click Update List Price Check Box");
            click(PriceSheetDetails.markAsReadyButton);
        }
        Utility_Functions.timeWait(5);
        commonObj.validateText(SelfServicePriceSheetPage.uploadedDataStatus, "Ready to Process", "Status Matched");
    }

    /**
     * This method To Search by Name
     */
    public void searchByName(String fieldValue) {
        Utility_Functions.timeWait(7);
        Boolean bl = Utility_Functions.waitForElementVisible(ownDriver, SelfServicePriceSheetPage.firstSearchByName, 10);
        System.out.println("bl............" + bl);
        if (bl) {
            sendKeys(SelfServicePriceSheetPage.searchByName, "" + fieldValue + "", "Search By Name");
            Utility_Functions.timeWait(4);
            boolean bl1 = Utility_Functions.xIsDisplayed(ownDriver, By.xpath("//li[contains(text(),'" + fieldValue + "')]"));
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
        if (Utility_Functions.waitForElementVisible(ownDriver, SelfServicePriceSheetPage.firstSearchByName, 10)) {
            int size = ownDriver.findElements(SelfServicePriceSheetPage.filterManufacturer).size();
            click(ownDriver.findElements(SelfServicePriceSheetPage.filterManufacturer).get(size - 1));
            sendKeys(SelfServicePriceSheetPage.filterManufacturerDropField, "" + fieldValue + "", "Filter By Manufacturer");
            Utility_Functions.waitForElementVisible(ownDriver, By.xpath("//li[contains(text(),'" + fieldValue + "')]"), 10);
            click(By.xpath("//span[contains(text(),'" + fieldValue + "')]"));
            Utility_Functions.timeWait(6);
            Boolean bl1 = Utility_Functions.xIsDisplayed(ownDriver, SelfServicePriceSheetPage.firstSearchByName);
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
        Utility_Functions.waitForElementVisible(ownDriver, SelfServicePriceSheetPage.filterByStatus, 10);
        click(SelfServicePriceSheetPage.filterByStatus, "Click Filter By Status drop down");
        click(By.xpath("//span[text()='" + dropOpt + "']"), "CLick " + dropOpt + " Option");
        Utility_Functions.timeWait(3);
        try {
            String val = Utility_Functions.getText(ownDriver, By.xpath("//span[text()=' " + dropOpt + " ']")).trim();
            int size = ownDriver.findElements(By.xpath("//span[text()=' " + dropOpt + " ']")).size();
            Utility_Functions.xAssertEquals(report, val, dropOpt, "Status: " + dropOpt + " showing " + size + " Price sheet...");
        } catch (Exception e) {
            Utility_Functions.xAssertEquals(report, dropOpt, dropOpt, "Status: " + dropOpt + " showing 0 Price sheet...");
        }
    }

    /**
     * This method To search Effective date
     */
    public void date(String startDate, String endDate) {
        Utility_Functions.waitForElementVisible(ownDriver, SelfServicePriceSheetPage.startDate, 10);
        sendKeys(SelfServicePriceSheetPage.startDate, "" + startDate + "", "Enter Start Date: " + startDate + "");
        Utility_Functions.timeWait(4);
        sendKeys(SelfServicePriceSheetPage.endDate, "" + endDate + "", "Enter End Date: " + endDate + "");
        Utility_Functions.timeWait(6);
        click(ownDriver.findElement(By.xpath("//h2[text()='SELF SERVICE PRICE SHEETS']")));
        Boolean bl = Utility_Functions.xIsDisplayed(ownDriver, SelfServicePriceSheetPage.firstSearchByName);
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
        ownDriver.navigate().refresh();
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
        ownDriver.navigate().refresh();
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
        ownDriver.navigate().refresh();
        Utility_Functions.timeWait(3);
        date(jsonData.getData("Start_Date"), jsonData.getData("End_Date"));
    }

    /**
     * This method To Verify ERROR MESSAGE
     */
    public void verifyError(String val, String errMsg) {
        Utility_Functions.timeWait(2);
        Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElement(SelfServicePriceSheetPage.listPr));
        Utility_Functions.timeWait(2);
        Utility_Functions.xSendkeysAndTab(report, ownDriver.findElement(SelfServicePriceSheetPage.listPriceInput), val, "Enter " + val + " into List Price roe text field");
        String color = ownDriver.findElement(SelfServicePriceSheetPage.listPr).getCssValue("background-color");
        Utility_Functions.xAssertEquals(report, color, "rgba(204, 0, 0, 0.1)", "List Price Input box Color changed to RED");
        int size = ownDriver.findElements(SelfServicePriceSheetPage.errorIcon).size();
        Utility_Functions.xmouseOver(ownDriver, ownDriver.findElements(SelfServicePriceSheetPage.errorIcon).get(size - 1));
        String error = ownDriver.findElements(SelfServicePriceSheetPage.errorIcon).get(size - 1).getAttribute("data-original-title");
        Utility_Functions.xmouseOut(ownDriver, ownDriver.findElements(SelfServicePriceSheetPage.errorIcon).get(size - 1));
        Utility_Functions.timeWait(3);
        Utility_Functions.xAssertEquals(report, error, errMsg, "Error Message: ");
    }

    /**
     * This method To Verify List Price
     */
    public void verifyListPriceRow() {
        Utility_Functions.timeWait(8);
        Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElement(SelfServicePriceSheetPage.listPr));
        Utility_Functions.timeWait(2);
        Utility_Functions.xSendkeysAndTab(report, ownDriver.findElement(SelfServicePriceSheetPage.listPriceInput), "0", "");
        String color = ownDriver.findElement(SelfServicePriceSheetPage.listPr).getCssValue("background-color");
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
        Utility_Functions.xSendkeysAndTab(ownDriver.findElement(SelfServicePriceSheetPage.effectiveDate), strPriceDate);
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
        wisePo();
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 0);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strPriceDate = formatter.format(dt);
        Utility_Functions.timeWait(2);
        Utility_Functions.xSendkeysAndTab(ownDriver.findElement(PriceSheetDetails.processedDate), strPriceDate);
        Utility_Functions.timeWait(2);
        int size = ownDriver.findElements(PriceSheetDetails.errorMessage).size();
        int count = 0;
        if (Utility_Functions.xIsDisplayed(ownDriver, PriceSheetDetails.errorMessage)) {
            for (int i = 0; count < size; count++) {
                if (count == size - 1) {
                    Utility_Functions.xScrollIntoView(ownDriver, ownDriver.findElements(PriceSheetDetails.errorMessage).get(i));
                }
                Utility_Functions.timeWait(2);
                Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElements(PriceSheetDetails.errorMessage).get(i));
                Utility_Functions.timeWait(2);
                Utility_Functions.xSendkeysAndTab(report, ownDriver.findElement(SelfServicePriceSheetPage.listPriceInput), "2", "");
            }
        }
        Utility_Functions.timeWait(2);
        click(PriceSheetDetails.saveButton);
        Utility_Functions.timeWait(6);
        commonObj.validateText(PriceSheetDetails.statusValue, "Maintaining", "Status Matched");
    }

    public void priceSheetUi() {
        commonObj.validateElementExists(SelfServicePriceSheetPage.iconI, "[i] icon is present");
        commonObj.validateElementExists(SelfServicePriceSheetPage.downloadNewVersion, "[CostPriceSheetTemplate.xlsx] link is present");
        commonObj.validateText(linkEle("Dismiss"), "Dismiss", "[Dismiss] link is present");
        commonObj.validateElementExists(SelfServicePriceSheetPage.helpIcon, "[?] icon is present");
        String[] labels = {"Search By Name", "Filter By Manufacturer", "Filter By Status", "Filter By Effective Date"};
        for (String label : labels) {
            commonObj.validateElementExists(labelInput(label), "[" + label + "] is present");
        }
        commonObj.validateElementExists(SelfServicePriceSheetPage.startDate, "[Start Date] is present");
        commonObj.validateElementExists(SelfServicePriceSheetPage.endDate, "[End Date] is present");
        commonObj.validateText(SelfServicePriceSheetPage.addPriceSheetbtn, "Add Price Sheet", "[Add Price Sheet] button is present");
        String[] colms = {"Name", "Manufacturer", "Code", "Status", "Type", "Effective Date", "Processed Date"};
        for (String colm : colms) {
            commonObj.validateElementExists(By.xpath("//th"), "[" + colm + "] column is present");
        }
        commonObj.validateElementExists(SelfServicePriceSheetPage.menuIcon, "Menu icon is present");
        commonObj.validateElementExists(SelfServicePriceSheetPage.logoutIcon, "LogOut icon is present");
    }

    public void ingestingStatus() {
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,By.xpath("//tbody//tr//td[text()='" + Utility_Functions.xGetJsonData("priceSheetName") + "']//following-sibling::td//span"),"");
        String status = ownDriver.findElement(By.xpath("//tbody//tr//td[text()='" + Utility_Functions.xGetJsonData("priceSheetName") + "']//following-sibling::td//span")).getText();
        if (status.equalsIgnoreCase("Ingesting")) {
            report.updateTestLog("VerifyRecord", "status Matched", Status.PASS);
        } else {
            report.updateTestLog("VerifyRecord", "status Mis-Matched", Status.FAIL);
        }
        /*ownDriver.navigate().refresh();
        Utility_Functions.timeWait(6);
        String status1 = ownDriver.findElement(By.xpath("//tbody//tr//td[text()='" + Utility_Functions.xGetJsonData("priceSheetName") + "']//following-sibling::td//span")).getText();
        if (status1.equalsIgnoreCase("Processed")) {
            report.updateTestLog("VerifyRecord", "status Matched", Status.PASS);
        } else {
            report.updateTestLog("VerifyRecord", "status Mis-Matched", Status.FAIL);
        }*/
    }

    public void processSheetNonEditable() {
        Utility_Functions.timeWait(4);
        String[] fields = {"Update List Price", "Update PO Cost", "Update Matrix Cost"};
        for (String field : fields) {
            commonObj.validateElementExists(checkedFields(field), "[" + fields + "] check box is disabled");
        }
        commonObj.validateElementExists(nonEditableField("Effective Date"), "[Effective Date] field is disabled");
        commonObj.validateElementExists(nonEditableField("Process Date"), "[Process Date] field is disabled");
    }

    public void deletePriceSheet() {
        int size = ownDriver.findElements(SelfServicePriceSheetPage.deleteIcon).size();
        for (int i = 0; i < size; i++) {
            Utility_Functions.timeWait(2);
            Utility_Functions.xMouseClick(ownDriver, ownDriver.findElements(SelfServicePriceSheetPage.deleteIcon).get(0));
            Utility_Functions.timeWait(2);
            commonObj.validateText(SelfServicePriceSheetPage.deletePriceSheetPopup, "Delete Price Sheet?", "[Delete Price Sheet?] popup is present");
            click(SelfServicePriceSheetPage.deleteButton, "Click Delete Button");
            Utility_Functions.timeWait(2);
            commonObj.validateText(SpoPage.popUp, "Price Sheet successfully deleted", "upload deleted");
        }
    }

    public void percentageListNegative(int wisePriceInt) {
        Utility_Functions.xScrollIntoView(ownDriver, PriceSheetDetails.newListPrice);
        Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElement(PriceSheetDetails.newListPrice));
        int lessListPrice = wisePriceInt - 1;
        int negativeListPrice = lessListPrice - wisePriceInt;
        double neg = Double.parseDouble("" + negativeListPrice + "");
        double wiseP = Double.parseDouble("" + wisePriceInt + "");
        double val = neg / wiseP;
        double percentageListNegative = (val) * 100;
        sendKeysAndTab(PriceSheetDetails.newListPriceHigh, "" + lessListPrice + "", "Enter List price less than Wise List Price");
        String listPercentage = ownDriver.findElements(PriceSheetDetails.wiseListPriceChangePrice).get(0).getText().replace("$", "");
        Utility_Functions.xAssertEquals(report, listPercentage, "" + percentageListNegative + "%", "");
    }

    public void percentageListPositive(int wisePriceInt) {
        Utility_Functions.xScrollIntoView(ownDriver, PriceSheetDetails.newListPrice);
        Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElement(PriceSheetDetails.newListPrice));
        int greatListPrice = wisePriceInt + 1;
        int posListPrice = greatListPrice - wisePriceInt;
        double pos = Double.parseDouble("" + posListPrice + "");
        double wiseP = Double.parseDouble("" + wisePriceInt + "");
        double val = pos / wiseP;
        double percentageListPositive = (val) * 100;
        sendKeysAndTab(PriceSheetDetails.newListPriceHigh, "" + greatListPrice + "", "Enter List price less than Wise List Price");
        String listPercentage = ownDriver.findElements(PriceSheetDetails.wiseListPriceChangePrice).get(0).getText().replace("$", "");
        Utility_Functions.xAssertEquals(report, listPercentage, "" + percentageListPositive + "%", "");
    }

    public void listPriceCalculation() {
        String wiseListPrice = getText(PriceSheetDetails.wiseListPriceChangePrice);
        String wisePrice = wiseListPrice.replace("$", "");
        Double wisePriceDbl = Double.parseDouble(wisePrice);
        int wisePriceInt = Integer.parseInt(wisePriceDbl.toString().replace(".0", ""));
        percentageListNegative(wisePriceInt);
        percentageListPositive(wisePriceInt);
    }

    public void calculatePoCost(int wisePriceInt) {
        Utility_Functions.xScrollIntoView(ownDriver, PriceSheetDetails.newListPrice);
        Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElement(PriceSheetDetails.newListPrice));
        int greatListPrice = wisePriceInt + 1;
        sendKeysAndTab(PriceSheetDetails.newListPriceHigh, "" + greatListPrice + "", "Enter List price");
        Utility_Functions.xScrollIntoView(ownDriver, ownDriver.findElements(PriceSheetDetails.newListPrice).get(1));
        Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElements(PriceSheetDetails.newListPrice).get(1));
        sendKeysAndTab(PriceSheetDetails.newListPriceHigh, "0.5", "Enter PO COst Multiplier");
        double calculatedPoCost = greatListPrice * 0.5000;
        commonObj.validateText(PriceSheetDetails.currentHighlights, "" + calculatedPoCost + "000", "Calculated Po Cost Matches");
        Utility_Functions.actionKey(Keys.TAB, ownDriver);
        double wisePoCost = Double.parseDouble(getText(PriceSheetDetails.currentHighlights).replace("$", ""));
        Utility_Functions.actionKey(Keys.TAB, ownDriver);
        double poPercentageCostChange = (calculatedPoCost - wisePoCost) * 100;
        double actPoPerChang = poPercentageCostChange / wisePoCost;
        double actPoPerChange = Math.round(actPoPerChang * 10.0) / 10.0;
        commonObj.validateText(PriceSheetDetails.currentHighlights, "" + actPoPerChange+ "%", "");
    }

    public void poCostCalculation() {
        String wiseListPrice = getText(PriceSheetDetails.wiseListPriceChangePrice);
        String wisePrice = wiseListPrice.replace("$", "");
        Double wisePriceDbl = Double.parseDouble(wisePrice);
        int wisePriceInt = Integer.parseInt(wisePriceDbl.toString().replace(".0", ""));
        calculatePoCost(wisePriceInt);
    }

    public void wisePo(){
        String wiseListPrice = ownDriver.findElements(PriceSheetDetails.wiseListPriceChangePrice).get(3).getText();
        String wisePrice = wiseListPrice.replace("$", "");
        Utility_Functions.xUpdateJson("wisePO",wisePrice);
    }

    public void calculateMatrixCost(int wisePriceInt) {
        Utility_Functions.xScrollIntoView(ownDriver, PriceSheetDetails.newListPrice);
        Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElement(PriceSheetDetails.newListPrice));
        int greatListPrice = wisePriceInt + 1;
        sendKeysAndTab(PriceSheetDetails.newListPriceHigh, "" + greatListPrice + "", "Enter List price");
        Utility_Functions.timeWait(2);
        Utility_Functions.xScrollIntoView(ownDriver, ownDriver.findElements(PriceSheetDetails.newListPrice).get(1));
        Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElements(PriceSheetDetails.newListPrice).get(1));
        Utility_Functions.actionKey(Keys.TAB, ownDriver);
        Utility_Functions.actionKey(Keys.TAB, ownDriver);
        Utility_Functions.actionKey(Keys.TAB, ownDriver);
        Utility_Functions.actionKey(Keys.TAB, ownDriver);
        Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElement(PriceSheetDetails.currentHighlights));
        sendKeysAndTab(PriceSheetDetails.newListPriceHigh, "0.5", "Enter Matrix COst Multiplier");
        double calculatedMatrixCost = greatListPrice * 0.5000;
        commonObj.validateText(PriceSheetDetails.currentHighlights, "" + calculatedMatrixCost + "000", "Calculated Matrix Cost Matches");
        Utility_Functions.actionKey(Keys.TAB, ownDriver);
        double wiseMatrixCost = Double.parseDouble(getText(PriceSheetDetails.currentHighlights).replace("$", ""));
        Utility_Functions.actionKey(Keys.TAB, ownDriver);
        double poPercentageCostChange = (calculatedMatrixCost - wiseMatrixCost) * 100;
        double actPoPerChange = poPercentageCostChange / wiseMatrixCost;
        commonObj.validateText(PriceSheetDetails.currentHighlights, "" + actPoPerChange + "%", "");
    }

    public void matrixCostCalculation() {
        String wiseListPrice = getText(PriceSheetDetails.wiseListPriceChangePrice);
        String wisePrice = wiseListPrice.replace("$", "");
        Double wisePriceDbl = Double.parseDouble(wisePrice);
        int wisePriceInt = Integer.parseInt(wisePriceDbl.toString().replace(".0", ""));
        calculateMatrixCost(wisePriceInt);
    }

    public void validateSheetErrorProcess() {
        Utility_Functions.timeWait(4);
        ownDriver.navigate().refresh();
        Utility_Functions.timeWait(6);
        String status = ownDriver.findElement(By.xpath("//tbody//tr//td[text()='" + Utility_Functions.xGetJsonData("priceSheetName") + "']//following-sibling::td//span")).getText();
        if (status.equalsIgnoreCase("Processing Error")) {
            report.updateTestLog("VerifyRecord", "status Matched", Status.PASS);
        } else {
            //report.updateTestLog("VerifyRecord", "status Mis-Matched", Status.FAIL);
        }
    }

    public void processErrorNoItem() {
        Utility_Functions.timeWait(4);
        checkProcessNowButton();
        Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElement(By.xpath("//tr/td")));
        //sendKeys(PriceSheetDetails.newListPriceHigh, "Qse77T", "Enter invalid Item Number");
        click(PriceSheetDetails.updatePOCost);
        clickProcessNow();
    }

    public void selectErrorFilter(By filter, By res, String filterOpt, String notPres) {
        click(PriceSheetDetails.itemFilter, "Click Item Filter");
        click(filter, "Select [" + filterOpt + "] option from the item Filter dropdown");
        /*commonObj.validateElementExists(res, "[" + filterOpt + "] item number present");
        Boolean bl = isDisplayed(filter);
        Utility_Functions.xAssertEquals(report, bl, false, "[" + notPres + "] Item not present ");*/
    }

    public void verifyErrorFilter() {
        Utility_Functions.timeWait(4);
        checkProcessNowButton();
        Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElement(By.xpath("//tr/td")));
        /*sendKeysAndTab(PriceSheetDetails.newListPriceHigh, "Qse7#@$$$s237T", "Enter invalid Item Number");
        commonObj.validateText(PriceSheetDetails.errorFilter, "Errors found", "[Errors found] error message is present for invalid item number");*/
        selectErrorFilter(PriceSheetDetails.withError, PriceSheetDetails.redInvalidItem, "With Error", "Without Error");
        selectErrorFilter(PriceSheetDetails.withoutError, PriceSheetDetails.withoutErrorItem, "Without Error", "With Error");
        click(PriceSheetDetails.itemFilter, "Click Item Filter");
        click(PriceSheetDetails.allFilter, "Select [All] option from the item Filter dropdown");
        /*commonObj.validateElementExists(PriceSheetDetails.redInvalidItem, "[With Error] item number present");
        commonObj.validateElementExists(PriceSheetDetails.withoutErrorItem, "[Without Error] item number present");*/
    }
}
