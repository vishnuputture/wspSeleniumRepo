package businesskeywords.WISERegression;

import businesskeywords.WiseSmokeTest.SuccessFailureHeadersValidation;
import com.winSupply.core.BaseSmokeTest;
import com.winSupply.core.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.Purchasing.DiscountGroups;
import pages.Purchasing.MailingMasterSearchPage;
import pages.Purchasing.OptionsConstantsPage;
import pages.Purchasing.SPO.SpoPage;
import pages.Purchasing.VendorInformationPage;
import pages.common.LoginPage;
import pages.inventory.ItemMasterPage;
import supportLibraries.Utility_Functions;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static supportLibraries.Utility_Functions.getDesiredTime;

public class SPORegressionTest extends BaseSmokeTest {
    public Utility_Functions utility_functions;
    SuccessFailureHeadersValidation successFailureHeadersValidation = null;

    public SPORegressionTest(Helper helper) {
        super(helper);
        utility_functions = new Utility_Functions(helper);
    }

    public void captureVendorNumber() {
        utility_functions.navigations(1, 1);
        sendKeys(MailingMasterSearchPage.action, "I");
        sendKeys(MailingMasterSearchPage.accountPrefix, "V");
        click(MailingMasterSearchPage.accountNumber);
        String tempVendorNumber = getElement(MailingMasterSearchPage.vendorNumber).getText();
        Utility_Functions.xUpdateJson("temp_Vendor_Number", tempVendorNumber);
        click(MailingMasterSearchPage.cancel);
        utility_functions.exitToMaster(2);
    }

    public void createProductCode() {
        utility_functions.navigations(7, 24, 13);
        sendKeys(OptionsConstantsPage.actionTextBox, "A");
        String temp_Product_Code = jsonData.getData("Product Code") + utility_functions.appendLocalTime();
        Utility_Functions.xUpdateJson("Product_Code", temp_Product_Code);
        sendKeysAndEnter(OptionsConstantsPage.productCode, temp_Product_Code, "Entered the necessary Product code");
        sendKeys(OptionsConstantsPage.descriptionLine1, "Adding description 1", "Description for Product code");
        click(OptionsConstantsPage.submitButton);
        utility_functions.exitToMaster(3);
    }

    public void createManufactureCode() {
        utility_functions.navigations(7, 24, 14);
        sendKeys(OptionsConstantsPage.actionTextBox, "A");
        String temp_Vendor_Name = jsonData.getData("Vendor Name") + utility_functions.appendLocalTime();
        Utility_Functions.xUpdateJson("Vendor_Name", temp_Vendor_Name);
        sendKeys(OptionsConstantsPage.vendorCode, temp_Vendor_Name);
        sendKeys(OptionsConstantsPage.descriptionLine1, "Adding description 1", "Description for Vendor Number");
        sendKeys(OptionsConstantsPage.searchVendor, Utility_Functions.xGetJsonData("temp_Vendor_Number"), "Entering the Vendor code");
        sendKeysAndEnter(OptionsConstantsPage.minimumGMPercent, "2", "Entering minimum GM percent");
        click(OptionsConstantsPage.submitButton);
        utility_functions.exitToMaster(3);
    }

    public void addDiscountGroup() {
        utility_functions.navigations(11, 24, 13);
        click(DiscountGroups.addDiscountGroup);
        sendKeysAndEnter(DiscountGroups.manufactureCode, Utility_Functions.xGetJsonData("Vendor_Name"), "Entering Manufacture code for Discount group");
        sendKeysAndEnter(DiscountGroups.productCode, Utility_Functions.xGetJsonData("Product_Code"), "Entering Manufacture code for Discount group");
        sendKeysAndEnter(DiscountGroups.vendorCode, Utility_Functions.xGetJsonData("Vendor_Name"), "Entering Manufacture code for Discount group");
        sendKeysAndEnter(DiscountGroups.multiplier, "1", "Entering multiplier for Discount group");
        sendKeysAndEnter(DiscountGroups.costBase, "1", "Entering cost base for Discount group");
        click(DiscountGroups.F9toConfirm);
        click(DiscountGroups.cancel);
        utility_functions.exitToMaster(3);
    }


    public void addFreightChargesCode() {
        utility_functions.navigations(11, 18);
        sendKeys(VendorInformationPage.actionCode, "I");
        sendKeysAndEnter(VendorInformationPage.vendorNo, Utility_Functions.xGetJsonData("temp_Vendor_Number"), "Adding freight charge code to the Vendor");
        if (getElement(VendorInformationPage.freightChargesCode).getAttribute("value").equals(null))
            sendKeysAndEnter(VendorInformationPage.freightChargesCode, "FFA", "Added the freight charges code");
        Utility_Functions.xUpdateJson("Freight Charges Code", getElement(VendorInformationPage.freightChargesCode).getAttribute("value"));
        utility_functions.exitToMaster(2);
    }

    public void createAnItem() {
        utility_functions.navigations(7, 12);
        click(ItemMasterPage.addItem);
        sendKeys(ItemMasterPage.itemDesc1, "DESCRIPTION FOR ITEM");
        sendKeys(ItemMasterPage.manufacturerCode, Utility_Functions.xGetJsonData("Vendor_Name"));
        sendKeys(ItemMasterPage.productCode, Utility_Functions.xGetJsonData("Product_Code"));
        sendKeys(ItemMasterPage.vendorCode, Utility_Functions.xGetJsonData("Vendor_Name"));
        sendKeys(ItemMasterPage.txtBoxUOM, "EA");
        sendKeys(ItemMasterPage.stdPkgQty, "1");
        sendKeys(ItemMasterPage.poundStdPkg, "0.250");
        sendKeys(ItemMasterPage.tbxConvFactor, "1");
        Utility_Functions.xUpdateJson("Std_Pkg_Qty", "1");
        Utility_Functions.xUpdateJson("Pound_Std_Pkg", "0.250");
        click(ItemMasterPage.btnSave);
        Utility_Functions.xUpdateJson("Current_Item_Number", getElement(ItemMasterPage.txtBoxSearch).getAttribute("value"));
        sendKeys(ItemMasterPage.calcMethod, "S");
        sendKeys(ItemMasterPage.purchasingMin, "1");
        sendKeys(ItemMasterPage.purchasingMax, "10");
        click(ItemMasterPage.btnExit);
        utility_functions.exitToMaster(2);
    }

    public void captureCostOption() {
//        utility_functions.navigations(11, 24, 1);
//        Utility_Functions.xUpdateJson("default_cost_option", getElement(OptionsConstantsPage.discMultOpt).getAttribute("value"));
//        utility_functions.exitToMaster(2);
    }

    public void captureDiscountMultiplierOption() {
        utility_functions.navigations(11, 24, 10);
        Utility_Functions.xUpdateJson("default_discount_multiplier_option", getElement(OptionsConstantsPage.discMultOpt).getAttribute("value"));
        utility_functions.exitToMaster(3);
    }

    public void winIntoCompany() {
        click(LoginPage.companySelector);
        click(LoginPage.winIntoCompanyToggle);
        sendKeysAndEnter(LoginPage.winCompanyNumber, jsonData.getData("winCompany"), "Entering the desired company number");
        waitForElementDisappear(SpoPage.loadingSpinner, 10);
    }

    public void createTemplate() {
        waitForVisible(SpoPage.newWorksheetBtn);
        click(SpoPage.newWorksheetBtn);
        String localDateAndTime = utility_functions.appendLocalDate() + utility_functions.appendLocalTime();
        sendKeys(SpoPage.worksheetSheetTemplateName, jsonData.getData("Template_name") + localDateAndTime);
        Utility_Functions.xUpdateJson("Purchasing_Template_Name", jsonData.getData("Template_name") + localDateAndTime);
        sendKeysAndTab(SpoPage.manufacturerCode, Utility_Functions.xGetJsonData("Vendor_Name"), "Entering the MF code");
        sendKeysAndTab(SpoPage.productCode, Utility_Functions.xGetJsonData("Product_Code"), "Entering the Product code");
        sendKeysAndTab(SpoPage.vendorName, Utility_Functions.xGetJsonData("Vendor_Name"), "Entering the Vendor code");
        waitForVisible(SpoPage.vendorNumber);
        Assert.assertEquals(getElement(SpoPage.vendorNumber).getAttribute("value").replaceAll("[^0-9]", ""), Utility_Functions.xGetJsonData("temp_Vendor_Number"));
        sendKeys(SpoPage.leadTime, "1");
    }

    public void createTemplateValidations() {
        click(SpoPage.findProducts);
        xWaitForElementVisible(SpoPage.createTemplate, 5);
        click(SpoPage.createTemplate);
        xWaitForElementVisible(SpoPage.popUp, 5);
        String expectedValue = getElement(SpoPage.popUp).getText().trim().replaceAll("[/:]", "");
        String actualValue = Utility_Functions.xGetJsonData("Purchasing_Template_Name").replaceAll("[/:]", "").trim() + " " + successFailureHeadersValidation.verifyPurchasingOrderTemplateCreationSuccessMessage.getOutcomeMessage();
        Assert.assertEquals(expectedValue, actualValue);
        waitForElementDisappear(SpoPage.loadingSpinner, 10);
    }

    public void searchWorkSheet() {
        WebDriverWait wait = new WebDriverWait(helper.getGSDriver().getWebDriver(), 10);
        wait.until(ExpectedConditions.visibilityOf(getElement(SpoPage.search)));
        click(SpoPage.search);
        sendKeys(SpoPage.purchasingTemplateName, Utility_Functions.xGetJsonData("Purchasing_Template_Name").replaceAll("[:]", "").trim());
        clickTypeAhead(Utility_Functions.xGetJsonData("Purchasing_Template_Name").replaceAll(":", "").trim());
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(SpoPage.applyFilter));
        element.click();
        waitForElementDisappear(SpoPage.loadingSpinner, 10);
    }

    public void searchTemplate() {
        WebDriverWait wait = new WebDriverWait(helper.getGSDriver().getWebDriver(), 10);
        searchWorkSheet();
        wait.until(ExpectedConditions.elementToBeClickable(SpoPage.refreshWorksheet));
        click(SpoPage.refreshWorksheet);
        waitForElementDisappear(SpoPage.loadingSpinner, 10);
        while (true) {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(SpoPage.popUp));
                String actualValue = getElement(SpoPage.popUp).getText();
                Assert.assertEquals(actualValue, successFailureHeadersValidation.verifyTheWorksheetRefreshedSuccessMessage.getOutcomeMessage());
                break;
            } catch (Exception e) {
                click(SpoPage.refreshWorksheet);
            }
        }
        waitForElementDisappear(SpoPage.loadingSpinner, 10);
        click(SpoPage.refreshWorksheet);
        waitForElementDisappear(SpoPage.loadingSpinner, 10);
        Assert.assertEquals(getElement(SpoPage.extractESTDateAndTime).getText().replace("Last Updated: ", "").trim(), getDesiredTime("America/New_York", "MM/dd/yy h:mm a"));
        waitForElementDisappear(SpoPage.loadingSpinner, 10);
    }

    public void verifyDeleteButton() {
        click(getElement(SpoPage.workSheet));
        waitForElementDisappear(SpoPage.loadingSpinner, 10);
        click(SpoPage.backButton);
        waitForElementDisappear(SpoPage.loadingSpinner, 10);
        Assert.assertTrue(getElement(SpoPage.deleteButton).isDisplayed());
    }

    public void verifyDeleteButtonWarningMessage() {
        click(SpoPage.deleteButton);
        waitForElementDisappear(SpoPage.loadingSpinner, 10);
        waitForVisible(SpoPage.deleteMessage);
        String expectedPopUpMessage = getElement(SpoPage.deleteMessage).getText().replaceAll("\n", " ").trim();
        String actualPopUpMessage = "Are you sure you want to permanently delete the saved worksheet" + " " + Utility_Functions.xGetJsonData("Purchasing_Template_Name").replaceAll("[:]", "").trim() + "?";
        Assert.assertEquals(expectedPopUpMessage, actualPopUpMessage);
        click(SpoPage.deleteNo);
        click(SpoPage.deleteButton);
        click(SpoPage.deleteYes);
        waitForElementDisappear(SpoPage.loadingSpinner, 10);
//        click(SpoPage.closeIcn);
        click(SpoPage.clearFilter);
    }

    public void verifyWorksheetAfterDelete() {
        searchWorkSheet();
        List<WebElement> noOfRows = ownDriver.findElements(By.xpath("//table[@role='grid']/thead/following::tbody/tr"));
        Assert.assertEquals(noOfRows.size(), 1);
        click(SpoPage.clearFilter);
    }

    public void verifyDefaultPagination() {
        int noOfDefaultRows = Integer.parseInt(getElement(SpoPage.defaultPagination).getText());
        Assert.assertEquals(Integer.parseInt(jsonData.getData("defaultRowCount")), noOfDefaultRows);
    }

    public void verifyRows(int noOfRows) {
        List<WebElement> arr = getRowData(SpoPage.templateTableRows);
        Assert.assertEquals(noOfRows, arr.size());
    }

    public void verifyPurchasingPagination() {
        verifyDefaultPagination();
        verifyRows(Integer.parseInt(jsonData.getData("defaultRowCount")));
        click(SpoPage.chooseRowsFifteen);
        verifyRows(Integer.parseInt(jsonData.getData("changedRowCount")));
    }

    public void verifyPurchasingNavigations() {
        int currentSheetNumber = getCurrentPageNumber();
        int totalSheetCount = getTotalNumberOfSheets();

        if (totalSheetCount > currentSheetNumber)
            click(SpoPage.nextPageNavigation);
        Assert.assertEquals(currentSheetNumber + 1, getCurrentPageNumber());
        click(SpoPage.lastPageNavigation);
        Assert.assertEquals(totalSheetCount, getCurrentPageNumber());
    }

    public int getCurrentPageNumber() {
        return Integer.parseInt(getElement(SpoPage.currentSheetNumber).getAttribute("value").trim());
    }

    public int getTotalNumberOfSheets() {
        return Integer.parseInt(getElement(SpoPage.noOfSheets).getText().replaceAll("[^0-9]", "").trim());
    }

    public void templateValidations() throws InterruptedException {
        click(SpoPage.newWorksheetBtn);
        Assert.assertEquals(verifyManufacturingCodeErrorMessages(), successFailureHeadersValidation.verifyWarningMessageForInvalidManufactureCode.getOutcomeMessage());
        Assert.assertEquals(verifyProductCodeErrorMessages(), successFailureHeadersValidation.verifyWarningMessageForInvalidProductCode.getOutcomeMessage());
        Assert.assertEquals(verifyVendorCodeErrorMessages(), successFailureHeadersValidation.verifyWarningMessageForInvalidVendorCode.getOutcomeMessage());
        click(SpoPage.cancel);
        Assert.assertEquals(getCancelTemplateWarningMessage(), successFailureHeadersValidation.verifyCancelTemplateWarningMessage.getOutcomeMessage());
        click(SpoPage.cancelTemplateYes);
        waitForElementDisappear(SpoPage.loadingSpinner, 10);
    }

    public String verifyManufacturingCodeErrorMessages() {
        sendKeysAndTab(SpoPage.manufacturerCode, jsonData.getData("badManufactureCode"), "Entering an invalid MF code");
        waitForVisible(SpoPage.invalidManufactureCode);
        String manufactureCodeError = getElement(SpoPage.invalidManufactureCode).getText().trim();
        return manufactureCodeError;
    }

    public String verifyProductCodeErrorMessages() {
        sendKeysAndTab(SpoPage.productCode, jsonData.getData("badManufactureCode"), "Entering an invalid Product code");
        waitForVisible(SpoPage.productCode);
        return getElement(SpoPage.invalidProductCode).getText().trim();
    }

    public String verifyVendorCodeErrorMessages() {
        sendKeysAndTab(SpoPage.vendorName, jsonData.getData("badManufactureCode"), "Entering an invalid Vendor code");
        waitForVisible(SpoPage.vendorName);
        return getElement(SpoPage.invalidVendorCode).getText().trim();
    }

    public String getCancelTemplateWarningMessage() {
        waitForVisible(SpoPage.cancelTemplateWarningMessage);
        return getElement(SpoPage.cancelTemplateWarningMessage).getText().trim();
    }

    public void verifySalesOptions() {
//        verifyCostOption();
        verifyMonthsSupplyOrderError();
        verifyPurchasingMultiplier();
        verifyLeadTimeFunctionality();
        verifyMonthOfHistorySelection();
    }

    public void verifyCostOption() {
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "List Price");
        map.put(1, "PO Cost");
        String expectedCostOption = getElement(SpoPage.defaultCostOption).getAttribute("value").trim();
        Assert.assertEquals(map.get(Integer.parseInt(Utility_Functions.xGetJsonData("default_cost_option"))), expectedCostOption);
    }

    public void verifyMonthsSupplyOrderError() {
        WebDriverWait wait = new WebDriverWait(helper.getGSDriver().getWebDriver(), 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(SpoPage.newWorksheetBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SpoPage.newWorksheetBtn));
        createTemplate();
        sendKeys(SpoPage.monthsSupplyToOrder, "112");
        click(SpoPage.findProducts);
        waitForVisible(SpoPage.monthSupplyToOrderErrorMessage);
        Assert.assertEquals(getElement(SpoPage.monthSupplyToOrderErrorMessage).getText().trim(), successFailureHeadersValidation.verifyMonthSupplyToOrderErrorMessage.getOutcomeMessage());

    }

    public void verifyPurchasingMultiplier() {
        String purchasingMultiplierValue = String.valueOf(utility_functions.convertDecimalToInteger(getElement(SpoPage.purchasingMultiplierDefaultValue).getAttribute("value").trim()));
        Assert.assertEquals(purchasingMultiplierValue, Utility_Functions.xGetJsonData("default_discount_multiplier_option"));
    }

    public void verifyLeadTimeFunctionality() {
        verifyLeadTimeDefaultValue();
        verifyLeadTimeErrorMessage();
    }

    public void verifyLeadTimeErrorMessage() {
        sendKeys(SpoPage.leadTime, "99999");
        String leadTimeErrorMessage = getElement(SpoPage.leadTimeErrorMessage).getText().trim();
        Assert.assertEquals(leadTimeErrorMessage, successFailureHeadersValidation.verifyLeadTimeErrorMessage.getOutcomeMessage());
    }

    public void verifyLeadTimeDefaultValue() {
        clearText(SpoPage.leadTime);
        String defaultLeadTimeValue = getElement(SpoPage.leadTime).getAttribute("value");
        String expectedValue = defaultLeadTimeValue.isEmpty() ? null : defaultLeadTimeValue;
        Assert.assertNull(expectedValue);
    }

    public void verifyMonthOfHistorySelection() {
        verifyNumberOfMonthBoxes();
        verifyDefaultNoOfSelectedBoxes();
        verifyTheNumberOfSelectedBoxesAfterChange();
        verifyMonthsOfHistoryOnEnteringZero();
        verifyMonthsOfHistoryOnEnteringNumberGreater();
    }

    public void verifyNumberOfMonthBoxes() {
        List<WebElement> selectedBoxes = getRowData(SpoPage.noOfSelectedBoxes);
        List<WebElement> deSelectedBoxes = getRowData(SpoPage.noOfDeSelectedBoxes);
        List<WebElement> noOfMonthsInsertedBoxes = getRowData(SpoPage.noOfMonthsInsertedBoxes);
        int selectedBoxesSize = selectedBoxes.size();
        int deSelectedBoxesSize = deSelectedBoxes.size();
        int noOfMonthsInsertedBoxesSize = noOfMonthsInsertedBoxes.size();
        Assert.assertEquals(selectedBoxesSize + deSelectedBoxesSize + noOfMonthsInsertedBoxesSize, Integer.parseInt(jsonData.getData("NumberOfMonthBoxes")));
    }

    public void verifyDefaultNoOfSelectedBoxes() {
        List<WebElement> selectedBoxes = getRowData(SpoPage.noOfSelectedBoxes);
        Assert.assertEquals(selectedBoxes.size(), Integer.parseInt(jsonData.getData("defaultNumberOfSelectedBoxes")));
        int defaultNoOfMonths = Integer.parseInt(getElement(SpoPage.trailingMonths).getAttribute("value"));
        Assert.assertEquals(defaultNoOfMonths, Integer.parseInt(jsonData.getData("defaultNumberOfSelectedBoxes")));
    }

    public void verifyTheNumberOfSelectedBoxesAfterChange() {
        clearText(SpoPage.trailingMonths);
        sendKeys(SpoPage.trailingMonths, jsonData.getData("dynamicMonthsChange"));
        List<WebElement> selectedBoxes = getRowData(SpoPage.noOfSelectedBoxes);
        Assert.assertEquals(selectedBoxes.size(), Integer.parseInt(jsonData.getData("dynamicMonthsChange")));
    }

    public void verifyMonthsOfHistoryOnEnteringZero() {
        clearText(SpoPage.trailingMonths);
        sendKeys(SpoPage.trailingMonths, jsonData.getData("MonthsOfHistoryToZero"));
        Assert.assertEquals(Integer.parseInt(getElement(SpoPage.trailingMonths).getAttribute("value")), 1);
    }

    public void verifyMonthsOfHistoryOnEnteringNumberGreater() {
        clearText(SpoPage.trailingMonths);
        sendKeys(SpoPage.trailingMonths, jsonData.getData("dynamicMonthsChangeGreaterThanTheValue"));
        Assert.assertEquals(Integer.parseInt(getElement(SpoPage.trailingMonths).getAttribute("value")), Integer.parseInt(jsonData.getData("NumberOfMonthBoxes")));
    }

    public void verifyDaysToRefresh() {
        verifyHoursDropdowns();
        verifyMinutesDropdownValues();
        verifyMeridian();
        verifyDayOfTheWeek();
    }

    public void verifyHoursDropdowns() {
        click(SpoPage.newWorksheetBtn);
        Select s = new Select(getElement(SpoPage.hourDropdown));
        List<WebElement> capturedListOfHours = s.getOptions();
        int indx = 1;
        for (WebElement el : capturedListOfHours) {
            String capturedValue = el.getText();
            if (indx < 10) {
                String expectedValue = 0 + "" + indx;
                Assert.assertEquals(capturedValue, expectedValue);
            } else {
                Assert.assertEquals(capturedValue, Integer.toString(indx));
            }
            indx++;
        }
    }

    public void verifyMinutesDropdownValues() {
        List<Integer> listOfNumbers = IntStream.iterate(0, i -> i + Integer.parseInt(jsonData.getData("minuteIntervals"))).limit(4).boxed().collect(Collectors.toList());
        List<Integer> expectedNumbers = new ArrayList<>();
        Collections.sort(listOfNumbers);
        Select s = new Select(getElement(SpoPage.minutesDropdown));
        List<WebElement> capturedListOfMinutes = s.getOptions();
        for (WebElement el : capturedListOfMinutes) {
            String capturedValue = el.getText();
            if (capturedValue.equals("00")) {
                expectedNumbers.add(0);
            } else {
                expectedNumbers.add(Integer.parseInt(capturedValue));
            }
        }

        Collections.sort(expectedNumbers);
        listOfNumbers.equals(expectedNumbers);
    }

    public void verifyMeridian() {
        Select s = new Select(getElement(SpoPage.meridianDropdown));
        List<String> meridianList = s.getOptions().stream().map(x -> x.getText()).collect(Collectors.toList());
        List<String> expectedMeridianElements = new ArrayList<>();
        expectedMeridianElements.add("AM");
        expectedMeridianElements.add("PM");
        Collections.sort(expectedMeridianElements);
        meridianList.equals(expectedMeridianElements);
    }

    public void verifyDayOfTheWeek() {
        verifyFiveDaysAreSelected();
        verifyTheDayNames();
        verifyDayOptionsOnSelectAndDeSelect();
    }

    public void verifyFiveDaysAreSelected() {
        scrollTillTheEndOfPage();
        click(SpoPage.dayOfTheWeekButton);
        List<WebElement> noOfDaysOfTheWeek = ownDriver.findElements(SpoPage.daysOfTheWeekDropdown);
        Assert.assertEquals(noOfDaysOfTheWeek.size(), Integer.parseInt(jsonData.getData("noOfDaysOfTheWeek")));
    }

    public void verifyTheDayNames() {
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        List<WebElement> days = ownDriver.findElements(SpoPage.namesOfDays);
        int indx = 0;
        for (WebElement element : days) {
            Assert.assertEquals(element.getText(), daysOfWeek[indx]);
            indx++;
        }
    }

    public void verifyDayOptionsOnSelectAndDeSelect() {
        scrollTillTheEndOfPage();
        click(SpoPage.selectADay);
        List<WebElement> noOfDaysOfTheWeek = ownDriver.findElements(SpoPage.daysOfTheWeekDropdown);
        Assert.assertEquals(noOfDaysOfTheWeek.size(), Integer.parseInt(jsonData.getData("noOfDaysOfTheWeek")) - 1);
        click(SpoPage.selectADay);
        List<WebElement> currentNoOfDaysOfTheWeek = ownDriver.findElements(SpoPage.daysOfTheWeekDropdown);
        Assert.assertEquals(currentNoOfDaysOfTheWeek.size(), Integer.parseInt(jsonData.getData("noOfDaysOfTheWeek")));
    }
}
