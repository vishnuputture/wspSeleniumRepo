package businesskeywords.AccountReceivable.ARToWISE;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.AccountReceivable.AR2WISE.AR2WISEPage;
import pages.AccountReceivable.MiscChargesAndAdjustmentsPage.MiscChargesAndAdjustmentsPage;
import pages.Purchasing.PurchaseOrderDetailsPage;
import pages.Purchasing.VendorInvoiceReconciliationPage;
import pages.common.LoginPage;
import pages.common.MasterPage;
import pages.pricing.SalesOrders.SalesOrdersPage;
import pages.pricing.SpecialPricePage;
import pages.pricing.pricingmatrix.PricingMatrixPage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

import java.lang.Math;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class AR2WISE extends ReusableLib {
    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public AR2WISE(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * Keyword to Navigate to AR2WISE
     */
    public void navigateToAr2WISE() {
        String url = properties.getProperty("AR2WISE");
        ownDriver.get(url);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateElementExists(AR2WISEPage.headerTitle, "[A/R G/L DETAIL INQUIRY] header is present");
    }

    /**
     * Keyword to validate header - [A/R G/L DETAIL INQUIRY]
     */
    public void vrfyARGLDInquiryHeader() {
        commonObj.validateText(AR2WISEPage.hdrARGLDInquiry, "A/R G/L DETAIL INQUIRY", "Validating [A/R G/L DETAIL INQUIRY] page header");
    }

    /**
     * Keyword to navigate to [TRANSACTION EXCEPTION LIST] page
     */
    public void navToTrnctnExcptnLstPage() {
        click(AR2WISEPage.btnOpenMenu, "Clicked open menu btn");
        click(AR2WISEPage.menuTrnsctnExcptn, "Click [Transaction Exception] menu");
        commonObj.validateText(AR2WISEPage.hdrTrnsctnExcptnList, "TRANSACTION EXCEPTION LIST", "Validating [TRANSACTION EXCEPTION LIST] page header");
    }

    public By anchorTag(String textName) {
        return By.xpath("//a[text()='" + textName + "']");
    }

    public By buttonTag(String textName) {
        return By.xpath("//button[text()='" + textName + "']");
    }

    public By divTag(String text) {
        return By.xpath("//div[text()='" + text + "']");
    }

    public By spanTag(String text) {
        return By.xpath("//span[text()=\"" + text + "\"]");
    }

    public By labelTag(String textName) {
        return By.xpath("//label[contains(text(),'" + textName + "')]");
    }

    public By pTag(String text) {
        return By.xpath("//p[text()='" + text + "']");
    }

    public void navigateToFixBusinessDays() {
        click(AR2WISEPage.openMenu, "CLick Open Menu Icon");
        click(anchorTag("Fix Business Days"));
        commonObj.validateText(AR2WISEPage.headerTitle, "fix business day", "[fix business day] header is present");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, AR2WISEPage.noResultFound, "[No Result Found] message is present");
    }

    public void fixBusinessDaysUI() {
        String[] labels = {"Company Number and Name", "Business Day", "Search All"};
        for (String label : labels) {
            commonObj.validateElementExists(labelTag(label), "[" + label + "] label is present");
        }
    }

    public void selectCompanyAR() {
        Utility_Functions.timeWait(3);
        sendKeys(AR2WISEPage.winLabel, jsonData.getData("companyName"), "Enter [" + jsonData.getData("companyName") + "] into Company Number and Name text box");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, By.xpath("//ngb-highlight[contains(@ng-reflect-result,'" + jsonData.getData("companyName") + "')]"), "click " + jsonData.getData("companyName"));
        Utility_Functions.timeWait(4);
    }

    public void fixBusinessDaysRecordUI() {
        selectCompanyAR();
        String[] buttons = {" Customer Number ", " Customer Name ", " Invoice Number ", " Business Day ", " Document Type ", " Ship Date ", " Amount ", " Gross Margin Amount "};
        for (String button : buttons) {
            commonObj.validateElementExists(buttonTag(button), "[" + button + "] is present");
        }
        commonObj.validateElementExists(AR2WISEPage.expandMinimize, "Expand-Hide Icon is present");
    }

    public void verifyError(String companyNo) {
        sendKeysAndTab(AR2WISEPage.winLabel, companyNo, "Enter [" + companyNo + "] into Company Number and Name text box");
        commonObj.validateElementExists(AR2WISEPage.invalidCompany, "Error field present");
    }

    public void verifyCompanyNoAndName() {
        verifyError(jsonData.getData("specialCharacterCompanyN0"));
        verifyError(jsonData.getData("negativeCompanyNo"));
        verifyError(jsonData.getData("bankCompanyNo"));
        verifyError(jsonData.getData("invalidCompanyNo"));
    }

    public void selectBusinessDay(int option) {
        click(AR2WISEPage.businessDays);
        Utility_Functions.xAssertEquals(report, ownDriver.findElements(By.xpath("//li")).get(option).getAttribute("data.val"), "" + option + "", "[" + option + "] Business Days dropdown options present");
        click(ownDriver.findElements(By.xpath("//li")).get(option));
        Utility_Functions.timeWait(5);
    }

    public void verifySearchRecordUsingBusinessDays() {
        selectCompanyAR();
        commonObj.validateText(AR2WISEPage.businessDays, "ALL", "By default [ALL] is present");
        int dropVal = 0;
        for (int i = 1; i < 24; i++) {
            selectBusinessDay(i);
            if (!isDisplayed(AR2WISEPage.noResultFound)) {
                String businessDays = ownDriver.findElements(AR2WISEPage.businessDay).get(1).getAttribute("ng-reflect-model");
                dropVal = i;
                if (i < 10) {
                    dropVal = dropVal + 1;
                    Utility_Functions.xAssertEquals(report, businessDays, "0" + i, "Validation matches");
                    Utility_Functions.xSelectDropdownByName(ownDriver, ownDriver.findElements(AR2WISEPage.businessDay).get(1), "0" + (dropVal) + "");
                } else {
                    dropVal = dropVal - 1;
                    Utility_Functions.xAssertEquals(report, businessDays, i, "Validation matches");
                    Utility_Functions.xSelectDropdownByName(ownDriver, ownDriver.findElements(AR2WISEPage.businessDay).get(1), "" + (dropVal) + "");
                }
                break;
            }
        }
        Utility_Functions.timeWait(3);
        selectBusinessDay(dropVal);
        String businessDays = ownDriver.findElements(AR2WISEPage.businessDay).get(1).getAttribute("ng-reflect-model");
        if (dropVal < 10)
            Utility_Functions.xAssertEquals(report, businessDays, "0" + dropVal, "Validation matches");
        else
            Utility_Functions.xAssertEquals(report, businessDays, "" + dropVal, "Validation matches");
    }

    public void verifySearchError(String search) {
        sendKeys(AR2WISEPage.searchAll, search, "Enter [" + search + "] into Search All text box");
        if (!search.equals("  ")) {
            commonObj.validateElementExists(divTag("0 of 0"), "No result found");
            commonObj.validateText(AR2WISEPage.resultStatus, "Results hidden by '" + search + "' filter", "[Results hidden by '" + search + "' filter]");
        } else {
            commonObj.validateElementExists(By.xpath("//tr/td"), "For [Bank space] in [Search All] field Records are found");
        }
    }

    public void verifySearchAllField() {
        selectCompanyAR();
        for (int i = 1; i < 24; i++) {
            selectBusinessDay(i);
            if (!isDisplayed(AR2WISEPage.noResultFound)) {
                break;
            }
        }
        verifySearchError(jsonData.getData("searchSpecialCharacter"));
        verifySearchError(jsonData.getData("searchBank"));
        verifySearchError(jsonData.getData("searchInvalid"));
    }

    public void searchResult(int index) {
        String search = ownDriver.findElements(By.xpath("//tr/td")).get(index).getText();
        if (search.contains("-")) {
            String[] split = search.split("-");
            sendKeys(AR2WISEPage.searchAll, split[0], "Enter [" + split[0] + "] into Search All Input Field");
        } else {
            sendKeys(AR2WISEPage.searchAll, search, "Enter [" + search + "] into Search All Input Field");
        }
        Utility_Functions.timeWait(3);
        Utility_Functions.xAssertEquals(report, search, ownDriver.findElements(By.xpath("//tr/td")).get(index), "Validation matches [" + search + "]");
    }

    public void verifySearchResult() {
        selectCompanyAR();
        for (int i = 0; i < 8; i++) {
            if (i != 3 && i != 4) {
                searchResult(i);
            }
        }
        String search = ownDriver.findElements(By.xpath("//tr/td/preceding::select")).get(0).getAttribute("ng-reflect-model");
        sendKeys(AR2WISEPage.searchAll, search, "Enter [" + search + "] into Search All Input Field");
        Utility_Functions.xAssertEquals(report, search, ownDriver.findElements(By.xpath("//tr/td/preceding::select")).get(0).getAttribute("ng-reflect-model"), "Validation matches [" + search + "]");
    }

    public int itemCountSelect(String recordNo) {
        Utility_Functions.timeWait(5);
        Utility_Functions.xMouseClick(ownDriver, AR2WISEPage.arrowIcon);
        try {
            click(spanTag(recordNo), "Select [" + recordNo + "]");
        } catch (Exception e) {
            Utility_Functions.timeWait(4);
            Utility_Functions.xMouseClick(ownDriver, By.xpath("//div[contains(@class,'mat-select-arrow-wrapper')]/div"));
            click(spanTag(recordNo), "Select [" + recordNo + "]");
        }
        Utility_Functions.timeWait(2);
        String[] records = getText(AR2WISEPage.totalRecords).split("of ");
        int count = Integer.parseInt(records[1]);
        commonObj.validateElementExists(By.xpath("//tr"), "Total records found [" + count + "] for " + recordNo + " option");
        return count;
    }

    public void verifyItemPerPage() {
        selectCompanyAR();
        itemCountSelect("10");
        itemCountSelect("50");
        itemCountSelect("500");
        itemCountSelect("1000");
    }

    public void verifyPagination() {
        selectCompanyAR();
        int pageCount = itemCountSelect("10");
        int sum = ownDriver.findElements(By.xpath("//tr")).size() - 1;
        while (ownDriver.findElement(AR2WISEPage.nextPage).isEnabled()) {
            Utility_Functions.xMouseClick(ownDriver, AR2WISEPage.nextPage);
            int recordCount = ownDriver.findElements(By.xpath("//tr")).size() - 1;
            sum = sum + recordCount;
            try {
                isDisplayed(ownDriver.findElement(AR2WISEPage.nextPage));
            } catch (Exception e) {
                click(AR2WISEPage.previousPage, "Verify Previous Arrow is enabled");
                break;
            }
        }
        Utility_Functions.xAssertEquals(report, sum, pageCount, "Count matches");
    }

    public void sortingCol() {
        List<WebElement> ele = ownDriver.findElements(By.xpath("//tr/th"));
        List<WebElement> els = ownDriver.findElements(By.xpath("//tr/td"));
        int rowCount = ownDriver.findElements(By.xpath("//tr")).size();
        int columnCount = ele.size();
        for (int i = 0; i < columnCount; i++) {
            Utility_Functions.waitTillClickHardSleep(report, ownDriver, By.xpath("(//tr/th)[" + i + "]"), "Click [" + getText(AR2WISEPage.columnName).trim() + "]");
            /*int j=0;
            for(int k=j;k<rowCount;k++){
                if(Integer.parseInt(els.get(i).getText().trim()Integer.parseInt(els.get(k+8).getText().trim())){
                    j=k+8;
                }else{
                }
            }*/
        }
    }

    public void verifySortableColumn() {
        selectCompanyAR();
        sortingCol();
    }

    public void verifyMinMaximize() {
        selectCompanyAR();
        click(AR2WISEPage.expandMinimize, "CLick Expand/Hide icon");
        try {
            click(By.xpath("//label[text()='Company Number and Name']"));
            throw new Exception("Expand/Hide icon is not working");
        } catch (Exception e) {
            commonObj.validateText(AR2WISEPage.headerTitle, "fix business day", "[Company Number and Name, Business Day, Search All] is minimized");
        }
        click(AR2WISEPage.headerTitle, "CLick Expand/Hide icon");
        click(By.xpath("//label[text()='Company Number and Name']"));
    }

    public By labelField(String text) {
        return By.xpath("//label[contains(text(),'" + text + "')]/parent::div/div/input");
    }

    public void verifyCreditDebitMemo() {
        selectCompanyAR();
        String invoiceNo[] = Utility_Functions.xGetJsonData("InvoiceNumber").split("-");
        sendKeys(labelField("Search All"), invoiceNo[0], "Enter invoice No. [" + invoiceNo[0] + "] into Search All field");
        commonObj.validateElementExists(By.xpath("//td[contains(text(),'" + invoiceNo[0] + "')]"), "Invoice Number exist " + invoiceNo[0]);
        commonObj.validateElementExists(By.xpath("//span[contains(text(),'" + jsonData.getData("memo") + "')]"), "Document type exist " + jsonData.getData("memo"));
        String currentDate = Utility_Functions.getCurrentDate();
        commonObj.validateElementExists(By.xpath("//td[contains(text(),'" + currentDate + "')]"), "Ship Date exist " + currentDate);
    }

    public void verifyARGLDetailInquiryUI() {
        String[] labels = {"Company Number and Name", "Journal Type", "GL Account Number", "Date", "Search All"};
        for (String label : labels) {
            commonObj.validateElementExists(labelTag(label), "[" + label + "] label is present");
        }
        String[] actions = {"ACTIONS", "TOTALS", "GL Account:  | Total: "};
        for (String action : actions) {
            commonObj.validateElementExists(pTag(action), "[" + action + "] Action label is present");
        }
        commonObj.validateElementExists(AR2WISEPage.castReceipt, "[Cash Receipt] label is present");
    }

    public void selectJournalType() {
        String[] journalTypes = {"ARJ", "NTR", "CRJ"};
        for (String journalType : journalTypes) {
            Utility_Functions.xSelectDropdownByName(ownDriver, AR2WISEPage.journalType, journalType);
            Utility_Functions.timeWait(5);
            if (!isDisplayed(spanTag("No results found"))) {
                break;
            }
        }
    }

    public void glAccount() {
        String account = getAttribute(AR2WISEPage.glAccountNumber, "ng-reflect-model");
        commonObj.validateElementExists(By.xpath("//p[text()='GL Account: " + account + " | Total: ']"), "GL Account matches");
        Utility_Functions.timeWait(2);
        int rowCount = ownDriver.findElements(By.xpath("//tr")).size() - 1;
        commonObj.validateElementExists(By.xpath("//span/strong[text()=' " + rowCount + "']"), "Row count matches");
        commonObj.validateElementExists(By.xpath("//div[text()='1 - " + rowCount + " of " + rowCount + "']"), "Pagination count matches");
    }

    public Double getTotalAmount() {
        int count = ownDriver.findElements(By.xpath("//td")).size();
        double sum = 0;
        int ittr = 0;
        for (int i = 3; i < count - 1; ) {
            String amt = ownDriver.findElements(By.xpath("//td")).get(i).getText().trim().replace(",", "");
            if (amt.contains("CR")) {
                amt = amt.replace("CR", "");
                double amount = Double.parseDouble(amt);
                sum = sum - amount;
            } else {
                double amount = Double.parseDouble(amt);
                sum = sum + amount;
            }
            i = i + 6;
            ittr++;
        }
        if (ittr == 1) {
            sum = Math.abs(sum);
        }
        return sum;
    }

    public void totalAmount() {
        DecimalFormat df = new DecimalFormat("0.00");
        Double sum = getTotalAmount();
        String amountVal = getText(AR2WISEPage.totalAmount).replace(",", "");
        if (amountVal.contains(".00")) {
            amountVal = amountVal.replace(".00", ".0");
        } else {
            sum = Double.valueOf(df.format(sum));
        }
        Utility_Functions.xAssertEquals(report, amountVal, "" + sum + "", "Total Amount matches");
    }

    public void getCompanyAndJournal() {
        selectCompanyAR();
        selectJournalType();
    }

    public void verifyARGLDetailInquiryRecordsUI() {
        getCompanyAndJournal();
        glAccount();
        totalAmount();
        String[] buttons = {" Customer ", " Date ", " Reference ", " Amount ", " User ", " Business Day "};
        for (String button : buttons) {
            commonObj.validateElementExists(buttonTag(button), "[" + button + "] is present");
        }
        commonObj.validateElementExists(AR2WISEPage.expandMinimize, "Expand-Hide Icon is present");
        commonObj.validateElementExists(buttonTag(" Export to Excel "), "[Export to Excel] button is present");
    }

    public void verifyMinMaximizeARGL() {
        selectCompanyAR();
        click(AR2WISEPage.expandMinimize, "CLick Expand/Hide icon");
        try {
            click(By.xpath("//label[text()='Company Number and Name']"));
            throw new Exception("Expand/Hide icon is not working");
        } catch (Exception e) {
            commonObj.validateText(AR2WISEPage.headerTitle, "A/R G/L DETAIL INQUIRY", "[Company Number and Name, Journal Type, GL Account Number, Date, Search All] is minimized");
        }
        click(AR2WISEPage.headerTitle, "CLick Expand/Hide icon");
        click(By.xpath("//label[text()='Company Number and Name']"));
    }

    public void verifyEditDateField() {
        getCompanyAndJournal();
        String date = Utility_Functions.xGetCurrentDate("MM/YYYY");
        Utility_Functions.xAssertEquals(report, getAttribute(AR2WISEPage.datePick, "value"), date, date + " is present");
        sendKeysAndEnter(AR2WISEPage.datePick, jsonData.getData("date"), "Enter date");
        Utility_Functions.xAssertEquals(report, getAttribute(AR2WISEPage.datePick, "value"), jsonData.getData("date"), jsonData.getData("date") + " is present");
    }

    public void dateField(String datePattern) {
        String date = jsonData.getData(datePattern);
        sendKeysAndEnter(AR2WISEPage.datePick, date, "Enter " + date);
        //commonObj.validateText(spanTag("No results found"),"No results found","[No results found] message is present");
    }

    public void verifyDateField() {
        getCompanyAndJournal();
        dateField("specialCharDate");
        dateField("AlphaDate");
        dateField("invalidFormat");
        dateField("invalidDate");
    }

    public void verifyBreakDownPopupUI() {
        String[] cols = {" Account ", " Debit ", " Credit "};
        for (String col : cols) {
            commonObj.validateText(By.xpath("//button[contains(text(),'" + col + "')]"), col.trim(), "Column present [" + col + "]");
        }
        commonObj.validateText(buttonTag("Close"), "Close", "[Close] button is present");
    }

    public void verifyBreakDownDetails() {
        String customer = getText(By.xpath("//span/a"));
        String invoice = ownDriver.findElements(By.xpath("//td")).get(2).getText();
        click(By.xpath("//span/a"), "Click [" + getText(By.xpath("//span/a")) + "]");
        String[] headers = {"A/R G/L DETAIL BREAKDOWN", customer, "Reference: " + invoice};
        for (String header : headers) {
            commonObj.validateText(By.xpath("//h2[contains(text(),'" + header + "')]"), header, "Header is present [" + header + "]");
        }
    }

    public double verifyDebitCreditCalculationBeakDown(int debCred) {
        int size = ownDriver.findElements(AR2WISEPage.breakDownCal).size();
        double sum = 0;
        for (int i = debCred; i < size; ) {
            String debit = ownDriver.findElements(AR2WISEPage.breakDownCal).get(i).getText().replace(",", "").trim();
            if (debit.equals("")) {
                debit = "0";
            }
            double debitInt = Double.parseDouble(debit);
            sum = sum + debitInt;
            i = i + 3;
        }
        return sum;
    }

    public void findRecords() {
        String[] options = {"a", "b", "c", "d"};
        int j = 0;
        for (String option : options) {
            sendKeys(AR2WISEPage.winLabel, option);
            Utility_Functions.timeWait(3);
            List<WebElement> els = ownDriver.findElements(By.xpath("//ngb-highlight"));
            int dropDownOptions = els.size();
            for (int i = 0; i < dropDownOptions; i++) {
                click(ownDriver.findElements(By.xpath("//ngb-highlight")).get(i), "Click [" + ownDriver.findElements(By.xpath("//ngb-highlight")).get(i).getText() + "]");
                Utility_Functions.timeWait(4);
                if (isDisplayed(By.xpath("//span/a"))) {
                    j = -1;
                    break;
                }
                sendKeys(AR2WISEPage.winLabel, option);
                Utility_Functions.timeWait(2);
            }
            if (j == -1) {
                verifyBreakDownDetails();
                verifyBreakDownPopupUI();
                Double debitSum = verifyDebitCreditCalculationBeakDown(1);
                Double creditSum = verifyDebitCreditCalculationBeakDown(2);
                String debit = ownDriver.findElements(AR2WISEPage.debitCreditTotal).get(1).getText();
                String credit = ownDriver.findElements(AR2WISEPage.debitCreditTotal).get(2).getText();
                if (debit.endsWith("0")) {
                    Utility_Functions.xAssertEquals(report, debit.substring(0, debit.length() - 1).replace(",",""), "" + debitSum + "", "Debit sum Matches [" + debitSum + "]");
                } else {
                    Utility_Functions.xAssertEquals(report, debit.replace(",",""), "" + debitSum + "", "Debit sum Matches [" + debitSum + "]");
                }
                if (credit.endsWith("0")) {
                    Utility_Functions.xAssertEquals(report, credit.substring(0, credit.length() - 1).replace(",",""), "" + creditSum + "", "Credit sum Matches [" + creditSum + "]");
                } else {
                    Utility_Functions.xAssertEquals(report, credit.replace(",",""), "" + creditSum + "", "Debit sum Matches [" + creditSum + "]");
                }
                break;
            }
        }
    }

    public void verifyFindARJRecords() {
        Utility_Functions.xSelectDropdownByName(ownDriver, AR2WISEPage.journalType, "ARJ");
        Utility_Functions.timeWait(3);
        findRecords();
        click(buttonTag("Close"), "Click [Close] button");
    }

    public void verifyFindNTRRecords() {
        Utility_Functions.xSelectDropdownByName(ownDriver, AR2WISEPage.journalType, "NTR");
        Utility_Functions.timeWait(3);
        findRecords();
        click(buttonTag("Close"), "Click [Close] button");
    }

    public void verifyFindCRJRecords() {
        Utility_Functions.xSelectDropdownByName(ownDriver, AR2WISEPage.journalType, "CRJ");
        Utility_Functions.timeWait(3);
        findRecords();
        click(buttonTag("Close"), "Click [Close] button");
    }

    public void verifyFilteredAmount() {
        Double sum = getTotalAmount();
        String amountVal = getText(AR2WISEPage.filteredTotal).replace(",", "");
        if (amountVal.contains(".00")) {
            amountVal = amountVal.replace(".00", ".0");
        }
        if (amountVal.contains("CR")) {
            amountVal = amountVal.replace("CR", "");
        }
        Utility_Functions.xAssertEquals(report, amountVal, "" + sum + "", "Total Amount matches");
    }

    public void verifySearchFilterItems() {
        getCompanyAndJournal();
        Utility_Functions.timeWait(3);
        String customer = getText(By.xpath("//span/a"));
        sendKeysAndEnter(AR2WISEPage.searchAllARGL, customer, "Search for Customer Name [" + customer + "]");
        Utility_Functions.timeWait(3);
        int count = ownDriver.findElements(By.xpath("//span/a")).size();
        commonObj.validateText(By.xpath("//span/strong[text()=' " + count + "']"), "" + count + "", "Filtered Result Matches to [" + count + "]");
        verifyFilteredAmount();
        for (int i = 1; i < 5; i++) {
            String colVal = ownDriver.findElements(By.xpath("//td")).get(i).getText().trim();
            sendKeysAndEnter(AR2WISEPage.searchAllARGL, colVal, "Search for [" + colVal + "]");
            Utility_Functions.timeWait(3);
            count = ownDriver.findElements(By.xpath("//span/a")).size();
            commonObj.validateText(By.xpath("//span/strong[text()=' " + count + "']"), "" + count + "", "Filtered Result Matches to [" + count + "]");
            verifyFilteredAmount();
        }
    }

    public void verifySearchErrorALGL(String search) {
        sendKeys(AR2WISEPage.searchAllARGL, search, "Enter [" + search + "] into Search All text box");
        if (!search.equals("  ")) {
            commonObj.validateText(AR2WISEPage.resultStatus, "Results hidden by '" + search + "' filter", "[Results hidden by '" + search + "' filter]");
        } else {
            commonObj.validateElementExists(By.xpath("//tr/td"), "For [Bank space] in [Search All] field Records are found");
        }
    }

    public void verifySearchAllFieldARGL() {
        verifySearchErrorALGL(jsonData.getData("searchSpecialCharacter"));
        commonObj.validateText(By.xpath("//span/strong[text()=' 0']"), "0", "Filtered Result Matches to [0]");
        Utility_Functions.xAssertEquals(report, "0.00", "0.00", "Total Amount matches [0.00]");
        verifySearchErrorALGL(jsonData.getData("searchNegative"));
        commonObj.validateText(By.xpath("//span/strong[text()=' 0']"), "0", "Filtered Result Matches to [0]");
        Utility_Functions.xAssertEquals(report, "0.00", "0.00", "Total Amount matches [0.00]");
        verifySearchErrorALGL(jsonData.getData("searchInvalid"));
        commonObj.validateText(By.xpath("//span/strong[text()=' 0']"), "0", "Filtered Result Matches to [0]");
        Utility_Functions.xAssertEquals(report, "0.00", "0.00", "Total Amount matches [0.00]");
    }

    public void verifyArrowFunctionality() {
        Utility_Functions.timeWait(3);
        itemCountSelect("10");
        Boolean bl = isDisplayed(AR2WISEPage.nextPage);
        if (bl) {
            click(AR2WISEPage.nextPage, "Click [>] Arrow");
            Utility_Functions.xAssertEquals(report, true, isDisplayed(AR2WISEPage.previousPage), "");
            click(AR2WISEPage.previousPage, "Click [<] Previous Arrow");
            Utility_Functions.xAssertEquals(report, true, isDisplayed(AR2WISEPage.nextPage), "");
        }
    }

    public void verifyCashReceiptUI() {
        Utility_Functions.xAssertEquals(report, getAttribute(AR2WISEPage.cashRecComName, "value"), jsonData.getData("companyName"), "");
        Utility_Functions.xAssertEquals(report, getAttribute(AR2WISEPage.cashRecDate, "placeholder"), Utility_Functions.xGetCurrentDate("MM/dd/YYYY"), "");
        int count = ownDriver.findElements(AR2WISEPage.totalBusinessDays).size();
        Utility_Functions.xAssertEquals(report, count, 24, "Total business days: ");
        commonObj.validateElementExists(AR2WISEPage.glAccount, "GL Account text Box present");
        commonObj.validateElementExists(AR2WISEPage.description, "Description text Box present");
        commonObj.validateElementExists(AR2WISEPage.amountReceived, "Amount Received text Box present");
        commonObj.validateText(labelTag("Number of Transactions "), "Number of Transactions", "Label [Number of Transactions] is present");
        commonObj.validateText(labelTag("Total Received"), "Total Received", "Label [Total Received] is present");
        commonObj.validateElementExists(AR2WISEPage.processBtn, "Process Button is present");
        commonObj.validateElementExists(buttonTag("Exit"), "Exit Button is present");
    }

    public void saveExitSalesOrdersCreateShipment() {
        Utility_Functions.xSelectDropdownByVisibleText(ownDriver, SalesOrdersPage.paymentMethodDropdown, "Cash");
        click(SalesOrdersPage.btnApplyPayment, "Applying  payment method");
        click(SalesOrdersPage.printAndExitbtn, "Click on Print&Exit button");
    }

    public void changeShipmentStatusCA() {
        Utility_Functions.timeWait(3);
        Utility_Functions.xSelectDropdownByIndex(ownDriver, ownDriver.findElement(SalesOrdersPage.shipmentStatus), 1);
        click(AR2WISEPage.PaymentType, "Click Payment Tab");
        saveExitSalesOrdersCreateShipment();
        if (isDisplayed(SalesOrdersPage.printAndExitbtn)) {
            Utility_Functions.xScrollIntoView(ownDriver, ownDriver.findElement(SalesOrdersPage.printAndExitbtn));
            Utility_Functions.xClickHiddenElement(ownDriver, SalesOrdersPage.printAndExitbtn);
            click(SalesOrdersPage.btnContinue, "Click on continue button");
        }
        click(SalesOrdersPage.pickingImage);
        Utility_Functions.xScrollIntoView(ownDriver, ownDriver.findElement(SalesOrdersPage.invoiceImage));
        click(SalesOrdersPage.invoiceImage, "clicking invoice image");
        click(SalesOrdersPage.continuebtn, "Click on continue button");
        if (isDisplayed(SalesOrdersPage.btnExitSalesOrderSummary)) {
            click(SalesOrdersPage.btnExitSalesOrderSummary, "Exiting Sales Order Summary Page");
        }
    }

    public void navigateToCashReceipt() {
        click(anchorTag(" Cash Receipts "), "CLick [Cash Receipts]");
        commonObj.validateText(By.xpath("//h2"), "cash receipts", "Header is present [cash receipts]");
    }

    public void verifyInvoiceReflectedOnCashRecAdjEntry() {
        sendKeys(VendorInvoiceReconciliationPage.dueDateTextBox, Utility_Functions.xGetJsonData("AccountNumber"), "Enter customer Number");
        sendKeysAndEnter(LoginPage.userNametxtBox, "CA", "Enter Payment Type");

    }

    public void clickCashReceiptAdjEntry() {
        click(MiscChargesAndAdjustmentsPage.cashReceiptAdjEntry, "Click [Entry - Payments & Adjustments]");
        if (isDisplayed(AR2WISEPage.warningMsg)) {
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
            sendKeysAndEnter(MasterPage.sqlTxtBox, "clrpfm ar1lck", "Enter [clrpfm ar1lck]");
            commonObj.validateText(SpecialPricePage.papMessage, "Member AR1LCK file AR1LCK in DTA99599 cleared.", "Message is present");
            click(MiscChargesAndAdjustmentsPage.cashReceiptAdjEntry, "Click [Entry - Payments & Adjustments]");
        }
        commonObj.validateText(MiscChargesAndAdjustmentsPage.cashAdjHeader, "Cash Receipts/Adj Entry", "Validating [Cash Receipts/Adj Entry] page title");
    }

    public void verifyARRecordsFromWise() {
        sendKeysAndEnter(AR2WISEPage.datePick, "01/2020", "Enter Date");
        Utility_Functions.timeWait(5);
        String invoiceNo = Utility_Functions.xGetJsonAsString("SalesOrder");
        sendKeys(AR2WISEPage.searchAllARGL, invoiceNo);
        Utility_Functions.timeWait(5);
        commonObj.validateElementExists(By.xpath("//td[contains(text(),' " + invoiceNo + "-01')]"), "Invoice number record found");
        commonObj.validateText(By.xpath("//span/a"), "099599-AUTOMATION TEST CO.", "Company Name matches");
        String user = getProperties("STGUserName").toUpperCase();
        commonObj.validateText(By.xpath("//td[contains(text(),'" + Utility_Functions.xGetCurrentDate("MM/dd/YYYY") + "')]"), Utility_Functions.xGetCurrentDate("MM/dd/YYYY"), "Date matches");
        commonObj.validateText(By.xpath("//td[contains(text(),'" + user + "')]"), user, "User name matches");
    }

    public void navigateToCashReceiptAdjScreen() {
        click(MasterPage.accountReceivable, "Click [Account Receivable]");
        clickCashReceiptAdjEntry();
    }

    public void verifyInvoiceReflectedOnCash() {
        sendKeys(VendorInvoiceReconciliationPage.dueDateTextBox, "099599", "Enter customer Number");
        sendKeysAndEnter(LoginPage.userNametxtBox, "CA", "Enter Payment Type");
        sendKeys(AR2WISEPage.referenceNo, Utility_Functions.xGetJsonData("salesOrder"), "Enter Sales order number");
        sendKeys(PurchaseOrderDetailsPage.datePOD, "01", "Enter invoice number");
        sendKeysAndEnter(AR2WISEPage.amount, Utility_Functions.xGetJsonData("receivedAmount"), "Enter Amount");
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        sendKeys(AR2WISEPage.okToApply, "Y", "Enter [Y] into [OK to apply?] check box");
        sendKeys(AR2WISEPage.printReceipt, "N", "Enter [Y] into [Print Receipt?] check box");
        Utility_Functions.actionKey(Keys.F9, ownDriver);
        sendKeysAndEnter(AR2WISEPage.commentBox, "Automation Test", "Enter comment");
        commonObj.validateText(VendorInvoiceReconciliationPage.warningMessage, "Successful application of entries to customer account.", "Success message present");
    }

    public void selectJournal() {
        Utility_Functions.xSelectDropdownByName(ownDriver, AR2WISEPage.journalType, jsonData.getData("journalType"));
        Utility_Functions.timeWait(5);
    }

    public void navigateToBankDepositScreen() {
        click(MasterPage.accountReceivable, "Click [Account Receivable]");
        click(AR2WISEPage.bankDeposit, "Click [Entry - Bank Deposit]");
    }

    public void clearInput() {
        int count = ownDriver.findElements(AR2WISEPage.optionChekBx).size();
        for (int i = 0; i < count; ) {
            ownDriver.findElements(AR2WISEPage.optionChekBx).get(i).clear();
            i = i + 2;
        }
    }

    public void verifyEntryBankDeposit() {
        sendKeys(AR2WISEPage.invoiceTicket, "NO", "Enter [NO] to KeyYESto invoice tickets");
        sendKeysAndEnter(AR2WISEPage.printJournal, "NO", "Enter [NO] to KeyYESto print journal");
        if (isDisplayed(AR2WISEPage.warningMsg)) {
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
            sendKeysAndEnter(MasterPage.sqlTxtBox, "clrpfm ar1lck", "Enter [clrpfm ar1lck]");
            commonObj.validateText(SpecialPricePage.papMessage, "Member AR1LCK file AR1LCK in DTA99599 cleared.", "Message is present");
            click(AR2WISEPage.bankDeposit, "Click [Entry - Bank Deposit]");
            sendKeys(AR2WISEPage.invoiceTicket, "NO", "Enter [NO] to KeyYESto invoice tickets");
            sendKeysAndEnter(AR2WISEPage.printJournal, "NO", "Enter [NO] to KeyYESto print journal");
        }
        commonObj.validateText(PricingMatrixPage.dispListTitle, "Select Deposits to Take to Bank", "Header is present");
        clearInput();
        while (isDisplayed(SpecialPriceAllowancePage.downButton)) {
            click(SpecialPriceAllowancePage.downButton);
            clearInput();
        }
    }

    public void verifyNTRRecordFromWise() {
        sendKeysAndEnter(AR2WISEPage.datePick, "01/2020", "Enter Date");
        Utility_Functions.timeWait(5);
        String invoiceNo = Utility_Functions.xGetJsonAsString("InvoiceNumber");
        sendKeys(AR2WISEPage.searchAllARGL, invoiceNo);
        Utility_Functions.timeWait(5);
        commonObj.validateElementExists(By.xpath("//td[contains(text(),' " + invoiceNo + "')]"), "Invoice number record found");
        commonObj.validateElementExists(By.xpath("//span/a[contains(text(),'" + Utility_Functions.xGetJsonData("AccountName") + "')]"), "Company Name matches");
        String user = getProperties("STGUserName").toUpperCase();
        commonObj.validateText(By.xpath("//td[contains(text(),'" + Utility_Functions.xGetCurrentDate("MM/dd/YYYY") + "')]"), Utility_Functions.xGetCurrentDate("MM/dd/YYYY"), "Date matches");
        commonObj.validateText(By.xpath("//td[contains(text(),'" + user + "')]"), user, "User name matches");
    }

    /**
     * Keyword to validate UI of [TRANSACTION EXCEPTION LIST] page
     */
    public void vrfyUIOfTrnctnExcptnLstPage() {
        String[] labels = {"Company Number and Name", "Date", "Document Type", "Search All"};
        for (int i = 0; i < labels.length; i++) {
            commonObj.validateText(By.xpath("//label[contains(text(),'" + labels[i] + "')]"), labels[i], "Validating field [" + labels[i] + "]");
        }
        commonObj.validateElementExists(AR2WISEPage.tbxCompany, "Validating presence of [Company Number and Name] type-ahead textbox");
        commonObj.validateElementExists(AR2WISEPage.tbxDate, "Validating presence of [Date] textbox");
        commonObj.validateElementExists(AR2WISEPage.ddnDocumentType, "Validating presence of [Document Type] dropdown");
        commonObj.validateElementExists(AR2WISEPage.tbxSearchAll, "Validating presence of [Search ALl] textbox");

        List<String> docTypeOptionsActual = Utility_Functions.xgetDropdownOptionsAsList(ownDriver, AR2WISEPage.ddnDocumentType);
        String[] docTypeOptionsExpected = {"Select one", "Non-Trade Payment", "Gross Margin Manager", "Non-Trade Invoices", "Inventory Adjustments", "Misc. Inventory Adjustments"};
        for (int i = 0; i < docTypeOptionsExpected.length; i++) {
            Utility_Functions.xAssertEquals(report, docTypeOptionsExpected[i], docTypeOptionsActual.get(i).trim(), "Validating [Document Type] options");
        }
    }

    /**
     * Keyword to select company
     */
    public void vrfyCompanyNameAndNumber() {
        String[] invalidValues = jsonData.getData("InvalidData").split(",");
        for (int i = 0; i < invalidValues.length; i++) {
            sendKeysAndTab(AR2WISEPage.tbxCompany, invalidValues[i], "Enter invalid data in [Company Number and Name] textbox");
            Utility_Functions.timeWait(1);
            String color = getElement(AR2WISEPage.tbxCompany).getCssValue("border-color");
            Utility_Functions.xAssertEquals(report, "rgb(204, 0, 0)", color, "Verify border color after entering - " + invalidValues[i] + " in [Company Number and Name] textbox");
        }
        String company = jsonData.getData("Company");
        sendKeysAndTab(AR2WISEPage.tbxCompany, company, "Enter valid data in [Company Number and Name] textbox");
        Utility_Functions.timeWait(1);
        String color = getElement(AR2WISEPage.tbxCompany).getCssValue("border-color");
        Utility_Functions.xAssertEquals(report, "rgb(204, 204, 204)", color, "Verify border color after entering - " + company + " in [Company Number and Name] textbox");
    }

    /**
     * Keyword to verify [Company Number and Name] type-ahead options
     */
    public void vrfyCompanyNumberAndNameTypeahead() {
        //String company = jsonData.getData("Company");
        sendKeys(AR2WISEPage.tbxCompany, "9", "Enter partial company number in [Company Number and Name] textbox");
        List<WebElement> lstCompanySuggestions = getListElement(AR2WISEPage.lstCompanyDdnOptions);
        List<String> lstText = Utility_Functions.xGetTextVisibleListString(ownDriver, lstCompanySuggestions);
        int count = 0;
        for (String option : lstText) {
            if (option.contains("9"))
                count++;
        }
        if (count == lstCompanySuggestions.size())
            report.updateTestLog("Verify options", "Company type-ahead displays correct options", Status.PASS);
        else
            report.updateTestLog("Verify options", "Company type-ahead displays incorrect options", Status.FAIL);
    }

    /**
     * Keyword to validate collapse functionality in [TRANSACTION EXCEPTION LIST] page
     */
    public void vrfyCollapse() {
        commonObj.validateElementExists(AR2WISEPage.btnCollapse, "Validating presence of [Collapse] button");
        click(AR2WISEPage.btnCollapse, "Clicking on [Collapse] button");
        commonObj.validateElementExists(AR2WISEPage.btnExpand, "Validating presence of [Expand] button");

        boolean flag = false;
        String[] labels = {"Company Number and Name", "Date", "Document Type", "Search All"};
        for (int i = 0; i < labels.length; i++) {
            flag = waitForElementDisappear(By.xpath("//label[contains(text(),'" + labels[i] + "')]"), 1);
        }
        flag = waitForElementDisappear(AR2WISEPage.tbxCompany, 1);
        flag = waitForElementDisappear(AR2WISEPage.tbxDate, 1);
        flag = waitForElementDisappear(AR2WISEPage.ddnDocumentType, 1);
        flag = waitForElementDisappear(AR2WISEPage.tbxSearchAll, 1);

        if (flag)
            report.updateTestLog("Verify filters are hidden", "Verify filters are hidden", Status.PASS);
        else
            report.updateTestLog("Verify filters are hidden", "Verify filters are hidden", Status.FAIL);

        commonObj.validateText(AR2WISEPage.txtNoResultsFound, "No results found", "Validating [No results found] text");
    }

    /**
     * Keyword to Collapse filters
     */
    public void collapse() {
        commonObj.validateElementExists(AR2WISEPage.btnCollapse, "Validating presence of [Collapse] button");
        click(AR2WISEPage.btnCollapse, "Clicking on [Collapse] button");
        commonObj.validateElementExists(AR2WISEPage.btnExpand, "Validating presence of [Expand] button");
    }

    /**
     * Keyword to validate Expand functionality in [TRANSACTION EXCEPTION LIST] page
     */
    public void vrfyExpand() {
        commonObj.validateElementExists(AR2WISEPage.btnExpand, "Validating presence of [Expand] button");
        click(AR2WISEPage.btnExpand, "Clicking on [Expand] button");
        commonObj.validateElementExists(AR2WISEPage.btnCollapse, "Validating presence of [Collapse] button");
        vrfyUIOfTrnctnExcptnLstPage();
    }

    /**
     * Keyword to Expand filters
     */
    public void expand() {
        commonObj.validateElementExists(AR2WISEPage.btnExpand, "Validating presence of [Expand] button");
        click(AR2WISEPage.btnExpand, "Clicking on [Expand] button");
        commonObj.validateElementExists(AR2WISEPage.btnCollapse, "Validating presence of [Collapse] button");
    }

    /**
     * Keyword to enter company details with Date and Doc Type
     */
    public void enterCompanyData() {
        selectCompanyNumberAndName();
        selectDate();
        selectDocType();
    }

    /**
     * Keyword to select company
     */
    public void selectCompanyNumberAndName() {
        String company = jsonData.getData("Company");
        sendKeys(AR2WISEPage.tbxCompany, company, "Enter company number in [Company Number and Name] textbox");
        Utility_Functions.timeWait(3);
        List<WebElement> lstCompanySuggestions = getListElement(AR2WISEPage.lstCompanyDdnOptions);
        for (WebElement option : lstCompanySuggestions) {
            String optionText = option.getText().trim();
            if (optionText.contains(company)) {
                click(option, "Selecting [" + company + "] from Company dropdown list");
                break;
            }
        }
        String selectedCompany = getAttribute(AR2WISEPage.tbxCompany, "value");
        if (selectedCompany.contains(company))
            report.updateTestLog("Verify Company", "Verify selected Company", Status.PASS);
        else
            report.updateTestLog("Verify Company", "Failed to select desired Company", Status.FAIL);
    }

    /**
     * Keyword to select Date
     */
    public void selectDate() {
        String date = jsonData.getData("Date");
        sendKeys(AR2WISEPage.tbxDate, date, "Enter date in [Date] textbox");
    }

    /**
     * Keyword to select Date
     */
    public void selectDate(String date) {
        sendKeys(AR2WISEPage.tbxDate, date, "Enter date in [Date] textbox");
    }

    /**
     * Keyword to validate Date field
     */
    public void vrfyDateField() {
        String[] invalidValues = jsonData.getData("InvalidData").split(",");
        for (int i = 0; i < invalidValues.length; i++) {
            sendKeys(AR2WISEPage.tbxDate, invalidValues[i], "Enter invalid data in [Date] textbox");
            Utility_Functions.timeWait(1);
            String date = getAttribute(AR2WISEPage.tbxDate, "value");
            Utility_Functions.xAssertEquals(report, "", date, "Verifying Date textbox after entering invalid values");
        }
        String dateExpected = jsonData.getData("Date");
        sendKeys(AR2WISEPage.tbxDate, dateExpected, "Enter date in [Date] textbox");
        Utility_Functions.timeWait(1);
        String dateActual = getAttribute(AR2WISEPage.tbxDate, "value");
        Utility_Functions.xAssertEquals(report, dateExpected, dateActual, "Verifying Date textbox after entering valid values");
    }

    /**
     * Keyword to select Document Type
     */
    public void selectDocType() {
        String docType = jsonData.getData("DocumentType");
        Utility_Functions.xSelectDropdownByName(ownDriver, report, AR2WISEPage.ddnDocumentType, docType, "Selected document type");
        waitForElementDisappear(AR2WISEPage.loaderIcon, 30);
    }

    /**
     * Keyword to select Document Type
     */
    public void selectDocType(String docType) {
        Utility_Functions.xSelectDropdownByName(ownDriver, report, AR2WISEPage.ddnDocumentType, docType, "Selected document type");
        waitForElementDisappear(AR2WISEPage.loaderIcon, 30);
    }

    /**
     * Keyword to validate Document Type in results table
     */
    public void vrfyDocumentType() {
        String docType = jsonData.getData("DocumentType");
        xWaitForElementVisible(AR2WISEPage.thDocumentType, 5);
        List<WebElement> lstElements = getListElement(AR2WISEPage.lstDocumentTypeTable);
        List<String> lstText = Utility_Functions.xGetTextVisibleListString(ownDriver, lstElements);
        int count = 0;
        for (String text : lstText) {
            if (text.trim().equalsIgnoreCase(docType)) {
                count++;
            }
        }
        if (count == lstText.size())
            report.updateTestLog("Verify Document Type", "Verify Document Type", Status.PASS);
        else
            report.updateTestLog("Verify Document Type", "Verify Document Type", Status.FAIL);
    }

    /**
     * Keyword to validate Document Type in results table
     */
    public void vrfyDocumentType(String docType) {
        xWaitForElementVisible(AR2WISEPage.thDocumentType, 5);
        List<WebElement> lstElements = getListElement(AR2WISEPage.lstDocumentTypeTable);
        List<String> lstText = Utility_Functions.xGetTextVisibleListString(ownDriver, lstElements);
        int count = 0;
        for (String text : lstText) {
            if (text.trim().equalsIgnoreCase(docType)) {
                count++;
            }
        }
        if (count == lstText.size())
            report.updateTestLog("Verify Document Type", "Verify Document Type", Status.PASS);
        else
            report.updateTestLog("Verify Document Type", "Verify Document Type", Status.FAIL);
    }

    /**
     * Keyword to validate Document Type - Misc. Inventory in results table
     */
    public void vrfyMiscInvDocumentType() {
        String docType = "Misc. Inventory";
        xWaitForElementVisible(AR2WISEPage.thDocumentType, 5);
        List<WebElement> lstElements = getListElement(AR2WISEPage.lstDocumentTypeTable);
        List<String> lstText = Utility_Functions.xGetTextVisibleListString(ownDriver, lstElements);
        int count = 0;
        for (String text : lstText) {
            if (text.trim().equalsIgnoreCase(docType)) {
                count++;
            }
        }
        if (count == lstText.size())
            report.updateTestLog("Verify Document Type", "Verify Document Type", Status.PASS);
        else
            report.updateTestLog("Verify Document Type", "Verify Document Type", Status.FAIL);
    }

    /**
     * Keyword to validate Resolve functionality in results table
     * i.e., click on green tick besides status and validate status
     */
    public void vrfyResolve() {
        String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        List<WebElement> lstStatus = getListElement(AR2WISEPage.lstStatusTable);
        int index = 0;
        for (WebElement status : lstStatus) {
            String text = status.getText().trim();
            if (text.equalsIgnoreCase("Unresolved")) {
                index = lstStatus.indexOf(status);
                break;
            }
        }
        List<WebElement> lstResolveBtn = getListElement(AR2WISEPage.lstResolveBtnTable);
        click(lstResolveBtn.get(0), "Clicking on [Resolve] button for the first entry in table");
        waitForElementDisappear(AR2WISEPage.loaderIcon, 30);
        lstStatus = getListElement(AR2WISEPage.lstStatusTable);
        String newStatus = lstStatus.get(index).getText().trim();
        Utility_Functions.xHighlight(ownDriver, lstStatus.get(index), "green");
        commonObj.validateContainsText("Resolved - " + date, newStatus, "Validating status after clicking on [Resolve] button");
    }

    /**
     * Keyword to validate Unresolve functionality in results table
     * i.e., click on red cross besides status and validate status
     */
    public void vrfyUnresolve() {
        List<WebElement> lstStatus = getListElement(AR2WISEPage.lstStatusTable);
        int index = 0;
        for (WebElement status : lstStatus) {
            String text = status.getText().trim();
            if (text.contains("Resolved")) {
                index = lstStatus.indexOf(status);
                break;
            }
        }
        List<WebElement> lstUnresolveBtn = getListElement(AR2WISEPage.lstUnresolveBtnTable);
        click(lstUnresolveBtn.get(0), "Clicking on [Unresolve] button for the first entry in table");
        waitForElementDisappear(AR2WISEPage.loaderIcon, 30);
        lstStatus = getListElement(AR2WISEPage.lstStatusTable);
        String newStatus = lstStatus.get(index).getText().trim();
        Utility_Functions.xHighlight(ownDriver, lstStatus.get(index), "green");
        Utility_Functions.xAssertEquals(report, "Unresolved", newStatus, "Validating status after clicking on [Unresolve] button");
        Utility_Functions.timeWait(5);
    }

    /**
     * Keyword to click on View button randomly from results table
     */
    public int clickRandomViewBtn() {
        List<WebElement> lstViewBtn = getListElement(AR2WISEPage.lstViewBtnTable);
        int random = Utility_Functions.xRandomFunction(0, lstViewBtn.size() - 1);
        Utility_Functions.xHighlight(ownDriver, lstViewBtn.get(random), "green");
        Utility_Functions.xScrollIntoViewClck(ownDriver, lstViewBtn.get(random));

        switch (jsonData.getData("DocumentType")) {
            case "Non-Trade Payment":
                commonObj.validateText(AR2WISEPage.hdrNTRWindow, "Non-Trade Payment", "Validating [Non-Trade Payment] pop-up header");
                break;
            case "Gross Margin Manager":
                commonObj.validateText(AR2WISEPage.hdrGMMWindow, "Gross Margin Manager", "Validating [Gross Margin Manager] pop-up header");
                break;
            case "Non-Trade Invoices":
                commonObj.validateText(AR2WISEPage.hdrNTIWindow, "Non-Trade Invoices", "Validating [Non-Trade Invoices] pop-up header");
                break;
            case "Inventory Adjustments":
                commonObj.validateText(AR2WISEPage.hdrInvAdjWindow, "Inventory Adjustments", "Validating [Inventory Adjustments] pop-up header");
                break;
        }
        return random;
    }

    /**
     * Keyword to click on View button for a record with [Resolved] or [Unresolved] status from results table
     */
    public int clickViewBtnToResolveUnresolve() {
        String desiredStatus = jsonData.getData("DesiredStatus");
        List<WebElement> lstStatus = getListElement(AR2WISEPage.lstStatusTable);
        int index = 0;
        for (WebElement status : lstStatus) {
            String text = status.getText().trim();
            if (text.equalsIgnoreCase(desiredStatus)) {
                index = lstStatus.indexOf(status);
                break;
            }
        }
        List<WebElement> lstViewBtn = getListElement(AR2WISEPage.lstViewBtnTable);
        Utility_Functions.xHighlight(ownDriver, lstViewBtn.get(index), "green");
        Utility_Functions.xScrollIntoViewClck(ownDriver, lstViewBtn.get(index));

        switch (jsonData.getData("DocumentType")) {
            case "Non-Trade Payment":
                commonObj.validateText(AR2WISEPage.hdrNTRWindow, "Non-Trade Payment", "Validating [Non-Trade Payment] pop-up header");
                break;
            case "Gross Margin Manager":
                commonObj.validateText(AR2WISEPage.hdrGMMWindow, "Gross Margin Manager", "Validating [Gross Margin Manager] pop-up header");
                break;
            case "Non-Trade Invoices":
                commonObj.validateText(AR2WISEPage.hdrNTIWindow, "Non-Trade Invoices", "Validating [Non-Trade Invoices] pop-up header");
                break;
            case "Inventory Adjustments":
                commonObj.validateText(AR2WISEPage.hdrInvAdjWindow, "Inventory Adjustments", "Validating [Inventory Adjustments] pop-up header");
                break;
        }
        return index;
    }

    /**
     * Keyword to validate presence of fields and buttons in [NON-TRADE PAYMENT] pop-up
     */
    public void vrfyNTPPopup() {
        List<WebElement> lstFields = getListElement(AR2WISEPage.lstFieldsFirstSet);
        String[] expectedLabels = {"Reference #:", "Memo Type:", "Payment Type:", "Date:", "Resolved:", "Resolved By:"};
        for (int i = 0; i < expectedLabels.length; i++) {
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabels[i], actual, "Validating Fields in [NON-TRADE PAYMENT] popup");
        }
        lstFields = getListElement(AR2WISEPage.lstFieldsScndSet);
        String[] expectedLabelsx = {"Vendor Name:", "Accounting Period:", "Business Day:"};
        for (int i = 0; i < expectedLabelsx.length; i++) {
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabelsx[i], actual, "Validating Fields in [NON-TRADE PAYMENT] popup");
        }
        commonObj.validateElementExists(AR2WISEPage.btnCancelNTRWindow, "Validating presence of [Cancel] button");
        commonObj.validateElementExists(AR2WISEPage.btnResolveNTRWindow, "Validating presence of [Resolve] button");
    }

    /**
     * Keyword to validate presence of fields and buttons in [GROSS MARGIN MANAGER] pop-up
     */
    public void vrfyGMMRPopup() {
        List<WebElement> lstFields = getListElement(AR2WISEPage.lstGMMFieldsFirstSet);
        String[] expectedLabels = {"Invoice:", "Purchase Order ID:", "Reference #:", "Resolved:", "Resolved By:", "Date:"};
        for (int i = 0; i < expectedLabels.length; i++) {
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabels[i], actual, "Validating Fields in [GROSS MARGIN MANAGER] popup");
        }
        lstFields = getListElement(AR2WISEPage.lstGMMFieldsScndSet);
        String[] expectedLabelsx = {"Gross Margin Manager:", "B Formula Gross Margin:", "Recorded:", "Description:", "Explanation:"};
        for (int i = 0; i < expectedLabelsx.length; i++) {
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabelsx[i], actual, "Validating Fields in [GROSS MARGIN MANAGER] popup");
        }
        commonObj.validateElementExists(AR2WISEPage.btnCancelNTRWindow, "Validating presence of [Cancel] button");
        commonObj.validateElementExists(AR2WISEPage.btnResolveNTRWindow, "Validating presence of [Resolve] button");
    }

    /**
     * Keyword to validate presence of fields and buttons in [NON-TRADE INVOICES] pop-up
     */
    public void vrfyNTIPopup() {
        List<WebElement> lstFields = getListElement(AR2WISEPage.lstNTIFieldsFirstSet);
        String[] expectedLabels = {"Date:", "Resolved:", "Resolved By:"};
        for (int i = 0; i < expectedLabels.length; i++) {
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabels[i], actual, "Validating Fields in [NON-TRADE INVOICES] popup");
        }
        lstFields = getListElement(AR2WISEPage.lstNTIFieldsScndSet);
        String[] expectedLabelsx = {"Customer:", "Transaction Type:", "User ID:", "Invoice Number:", "Accounting Period:"};
        for (int i = 0; i < expectedLabelsx.length; i++) {
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabelsx[i], actual, "Validating Fields in [NON-TRADE INVOICES] popup");
        }
        commonObj.validateElementExists(AR2WISEPage.btnCancelNTRWindow, "Validating presence of [Cancel] button");
        commonObj.validateElementExists(AR2WISEPage.btnResolveNTRWindow, "Validating presence of [Resolve] button");
    }

    /**
     * Keyword to validate presence of fields and buttons in [INVENTORY ADJUSTMENTS] pop-up
     */
    public void vrfyInvAdjPopup() {
        List<WebElement> lstFields = getListElement(AR2WISEPage.lstInvAdjFieldsFirstSet);
        String[] expectedLabels = {"Item:", "User:", "Resolved:", "Resolved By:", "Date:"};
        for (int i = 0; i < expectedLabels.length; i++) {
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabels[i], actual, "Validating Fields in [INVENTORY ADJUSTMENTS] popup");
        }
        lstFields = getListElement(AR2WISEPage.lstInvAdjFieldsScndSet);
        String[] expectedLabelsx = {"Inventory Adjustment Quantity:", "Inventory Adjustment Cost:", "Recorded:", "Description:", "Reference:", "Adjustment Type:", "GL Account #:", "GL Account Description:"};
        for (int i = 0; i < expectedLabelsx.length; i++) {
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabelsx[i], actual, "Validating Fields in [INVENTORY ADJUSTMENTS] popup");
        }
        commonObj.validateElementExists(AR2WISEPage.btnCancelNTRWindow, "Validating presence of [Cancel] button");
        commonObj.validateElementExists(AR2WISEPage.btnResolveNTRWindow, "Validating presence of [Resolve] button");
    }

    /**
     * Keyword to validate presence of fields and buttons in [INVENTORY ADJUSTMENTS] pop-up for Misc. Inventory Adjustments Doc Type
     */
    public void vrfyMiscInvAdjPopup() {
        List<WebElement> lstFields = getListElement(AR2WISEPage.lstInvAdjFieldsFirstSet);
        String[] expectedLabels = {"Item:", "User:", "Resolved:", "Resolved By:", "Date:"};
        for (int i = 0; i < expectedLabels.length; i++) {
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabels[i], actual, "Validating Fields in [INVENTORY ADJUSTMENTS] popup");
        }
        lstFields = getListElement(AR2WISEPage.lstInvAdjFieldsScndSet);
        String[] expectedLabelsx = {"Inventory Adjustment Quantity:", "Inventory Adjustment Cost:", "Recorded:", "Description:", "Reference:", "Adjustment Type:", "Adjustment Subtype:", "GL Account #:", "GL Account Description:"};
        for (int i = 0; i < expectedLabelsx.length; i++) {
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabelsx[i], actual, "Validating Fields in [INVENTORY ADJUSTMENTS] popup");
        }
        commonObj.validateElementExists(AR2WISEPage.btnCancelNTRWindow, "Validating presence of [Cancel] button");
        commonObj.validateElementExists(AR2WISEPage.btnResolveNTRWindow, "Validating presence of [Resolve] button");
    }

    /**
     * Keyword to click on Cancel button in [NON-TRADE PAYMENT] pop-up
     */
    public void closeNTPPopup() {
        click(AR2WISEPage.btnCancelNTRWindow, "Clicking on [Cancel] Button");
        waitForElementDisappear(AR2WISEPage.hdrNTRWindow, 5);
    }

    /**
     * Keyword to resolve a record from [NON-TRADE PAYMENT] pop-up
     */
    public void resolveRecordNTPPopup() {
        int index = clickViewBtnToResolveUnresolve();
        sendKeys(AR2WISEPage.tbxComments, jsonData.getData("Comments"), "Enter date in [Comments] textarea");
        click(AR2WISEPage.btnResolveNTRWindow, "Clicking on [Resolve] Button");
        waitForElementDisappear(AR2WISEPage.loaderIcon, 30);

        String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        List<WebElement> lstStatus = getListElement(AR2WISEPage.lstStatusTable);
        String newStatus = lstStatus.get(index).getText().trim();
        Utility_Functions.xHighlight(ownDriver, lstStatus.get(index), "green");
        commonObj.validateContainsText("Resolved - " + date, newStatus, "Validating status after clicking on [Resolve] button");
    }

    /**
     * Keyword to validate pagination label and default value
     */
    public void vrfyPagination() {
        commonObj.validateText(AR2WISEPage.lblItemsPerPage, "Items per page:", "Validating [Items per page:] pagination label");
        commonObj.validateText(AR2WISEPage.tbxPagination, "1000", "Validating pagination is default set to 1000");
    }

    /**
     * Keyword to validate Items Per Page functionality
     */
    public void vrfyItemsPerPage() {
        int[] itemsPerPageOptionsExp = {10, 50, 500, 1000};
        for (int i = 0; i < itemsPerPageOptionsExp.length; i++) {
            selectItemsPerPage(itemsPerPageOptionsExp[i]);
        }
    }

    /**
     * Keyword to select Items Per Page functionality
     */
    public void selectItemsPerPage(int itemsPerPage) {
        click(AR2WISEPage.ddnItemsPerPage, "Clicking on Items Per Page dropdown button");
        click(By.xpath("//mat-option/span[text()='" + itemsPerPage + "']"), "Selected Items per page");
        Utility_Functions.timeWait(2);
        List<WebElement> lstTableData = getListElement(AR2WISEPage.lstDateColValue);
        if (lstTableData.size() <= itemsPerPage)
            report.updateTestLog("Verify Count", "Table is showing [" + lstTableData.size() + "] items per page", Status.PASS);
        else
            report.updateTestLog("Verify Count", "Table is showing [" + lstTableData.size() + "] items per page", Status.FAIL);
    }

    /**
     * Keyword to validate pagination Next and Previous
     */
    public void vrfyPaginationNextPrev() {
        selectItemsPerPage(10);
        commonObj.verifyElementContainsText(AR2WISEPage.txtPaginatorRange, "1 - 10", "Validating pagination range");
        click(AR2WISEPage.btnPaginationNext, "Clicking on [Pagination - NEXT] button");
        commonObj.verifyElementContainsText(AR2WISEPage.txtPaginatorRange, "11 - 20", "Validating pagination range");
        click(AR2WISEPage.btnPaginationPrevious, "Clicking on [Pagination - NEXT] button");
        commonObj.verifyElementContainsText(AR2WISEPage.txtPaginatorRange, "1 - 10", "Validating pagination range");
    }

    /**
     * Keyword to validate column sorting in [TRANSACTION EXCEPTION LIST] page
     */
    public void vrfySortingTrnsctnExcptnPage() {
        vrfyColumnSorting("Date", AR2WISEPage.thDate, AR2WISEPage.lstDateColValue);
        vrfyColumnSorting("Description", AR2WISEPage.thDescription, AR2WISEPage.lstDescriptionColValue);
        vrfyColumnSorting("Status", AR2WISEPage.thStatus, AR2WISEPage.lstStatusColValue);
    }

    /**
     * Keyword to validate column sorting in [TRANSACTION EXCEPTION LIST] page
     */
    public void vrfyColumnSorting(String colName, By colHeader, By tableData) {
        click(colHeader, "Clicking on [" + colName + "] header for sorting Ascending");
        Utility_Functions.timeWait(2);
        String sorting = getAttribute(colHeader, "aria-sort");
        List<WebElement> lstTableData = getListElement(tableData);
        List<String> lstText = Utility_Functions.xGetTextVisibleListString(ownDriver, lstTableData);
        if (sorting.equalsIgnoreCase("ascending")) {
            List<String> sortedList = new ArrayList<String>(lstText);
            Collections.sort(sortedList);
            boolean isSorted = sortedList.equals(lstText);
            if (isSorted)
                report.updateTestLog("Verify sorting", "[" + colName + "] column data is sorted Ascending", Status.PASS);
            else
                report.updateTestLog("Verify sorting", "[" + colName + "] column data is NOT sorted in ASC order", Status.FAIL);
        }
        click(colHeader, "Clicking on [" + colName + "] header for sorting Descending");
        Utility_Functions.timeWait(2);
        sorting = getAttribute(colHeader, "aria-sort");
        lstTableData = getListElement(tableData);
        lstText = Utility_Functions.xGetTextVisibleListString(ownDriver, lstTableData);
        if (sorting.equalsIgnoreCase("descending")) {
            List<String> sortedList = new ArrayList<String>(lstText);
            Collections.sort(sortedList, Collections.reverseOrder());
            boolean isSortedDesc = sortedList.equals(lstText);
            if (isSortedDesc)
                report.updateTestLog("Verify sorting", "[" + colName + "] column data is sorted Descending", Status.PASS);
            else
                report.updateTestLog("Verify sorting", "[" + colName + "] column data is NOT sorted in DESC order", Status.FAIL);
        }
    }

    /**
     * Keyword to validate column data in [TRANSACTION EXCEPTION LIST] page
     */
    public void vrfySearchAllFilter() {
        List<WebElement> lstTableData = getListElement(AR2WISEPage.lstDateColValue);
        int random = Utility_Functions.xRandomFunction(0, lstTableData.size() - 1);
        String dateExpected = lstTableData.get(random).getText().trim();
        enterSearchAllText(dateExpected);
        vrfyColumnData("Date", dateExpected, AR2WISEPage.lstDateColValue);
        enterSearchAllText("");

        lstTableData = getListElement(AR2WISEPage.lstAmountColValue);
        random = Utility_Functions.xRandomFunction(0, lstTableData.size() - 1);
        String amountExpected = lstTableData.get(random).getText().trim();
        enterSearchAllText(amountExpected);
        vrfyColumnData("Amount", amountExpected, AR2WISEPage.lstAmountColValue);
        enterSearchAllText("");

        lstTableData = getListElement(AR2WISEPage.lstDescriptionColValue);
        random = Utility_Functions.xRandomFunction(0, lstTableData.size() - 1);
        String descriptionExpected = lstTableData.get(random).getText().trim();
        enterSearchAllText(descriptionExpected);
        vrfyColumnData("Description", descriptionExpected, AR2WISEPage.lstDescriptionColValue);
    }

    /**
     * Keyword to enter data in [Search All] textbox in [TRANSACTION EXCEPTION LIST] page
     */
    public void enterSearchAllText(String searchAllText) {
        sendKeysAndEnter(AR2WISEPage.tbxSearchAll, searchAllText, "Enter data in [Search All] textbox");
        Utility_Functions.timeWait(1);
        waitForElementDisappear(AR2WISEPage.loaderIcon, 30);
    }

    /**
     * Keyword to enter data in [Search All] textbox in [TRANSACTION EXCEPTION LIST] page
     */
    public void enterCurrentDateInSearchAll() {
        String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        sendKeysAndEnter(AR2WISEPage.tbxSearchAll, date, "Enter current date in [Search All] textbox");
        Utility_Functions.timeWait(1);
        waitForElementDisappear(AR2WISEPage.loaderIcon, 30);
    }

    /**
     * Keyword to validate column data in [TRANSACTION EXCEPTION LIST] page
     */
    public void vrfyColumnData(String colName, String expectedText, By tableData) {
        List<WebElement> lstTableData = getListElement(tableData);
        List<String> lstText = Utility_Functions.xGetTextVisibleListString(ownDriver, lstTableData);
        int count = 0;
        for (String text : lstText) {
            if (text.equalsIgnoreCase(expectedText))
                count++;
        }
        if (count == lstText.size())
            report.updateTestLog("Verify table data", "[" + colName + "] column data is filtered properly", Status.PASS);
        else
            report.updateTestLog("Verify table data", "[" + colName + "] column data is NOT filtered properly", Status.FAIL);
    }

    /**
     * Keyword to Search All filter in [TRANSACTION EXCEPTION LIST] page
     */
    public void vrfyInvalidSearchAllFilter() {
        String invalidDate = "99/0000";
        String invalidAmount = "54164-6";
        String invalidDescription = "Test Automation Invalid Description";

        enterSearchAllText(invalidDate);
        commonObj.validateText(AR2WISEPage.txtResultsHidden, "Results hidden by '" + invalidDate + "' filter", "Validating [Results hidden by] text");
        enterSearchAllText("");
        waitForElementDisappear(AR2WISEPage.txtResultsHidden, 1);

        enterSearchAllText(invalidAmount);
        commonObj.validateText(AR2WISEPage.txtResultsHidden, "Results hidden by '" + invalidAmount + "' filter", "Validating [Results hidden by] text");
        enterSearchAllText("");
        waitForElementDisappear(AR2WISEPage.txtResultsHidden, 1);

        enterSearchAllText(invalidDescription);
        commonObj.validateText(AR2WISEPage.txtResultsHidden, "Results hidden by '" + invalidDescription + "' filter", "Validating [Results hidden by] text");
        enterSearchAllText("");
        waitForElementDisappear(AR2WISEPage.txtResultsHidden, 1);
    }

    /**
     * Keyword to change Document Type and verify the results in [TRANSACTION EXCEPTION LIST] page
     */
    public void changeDocTypeAndVrfyResults() {
        String[] docType = jsonData.getData("DocumentType").split(",");
        for (int i = 0; i < docType.length; i++) {
            selectDocType(docType[i]);
            vrfyDocumentType(docType[i]);
        }
    }

    /**
     * Keyword to verify presence of No Results found msg in [TRANSACTION EXCEPTION LIST] page
     */
    public void vrfyNoResultsMsg() {
        commonObj.validateText(AR2WISEPage.txtNoResultsFound, "No results found", "Validating [No results found] text");
    }

    /**
     * Keyword to validate Date Filter in [TRANSACTION EXCEPTION LIST] page
     */
    public void validateDateFilter() {
        String dateExpected = jsonData.getData("Date");
        selectDate(dateExpected);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        waitForElementDisappear(AR2WISEPage.loaderIcon, 30);
        vrfyDateColumnData(dateExpected);

        dateExpected = jsonData.getData("Date2");
        selectDate(dateExpected);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        waitForElementDisappear(AR2WISEPage.loaderIcon, 30);
        //selectDocType("Gross Margin Manager");
        //selectDocType();
        vrfyDateColumnData(dateExpected);
    }

    /**
     * Keyword to validate Date column data in [TRANSACTION EXCEPTION LIST] page
     */
    public void vrfyDateColumnData(String dateExpected) {
        List<WebElement> lstTableData = getListElement(AR2WISEPage.lstDateColValue);
        int count = 0;
        for (WebElement element : lstTableData) {
            String[] text = element.getText().trim().split("/");
            String dateActual = text[0] + "/" + text[2];
            if (dateActual.equalsIgnoreCase(dateExpected))
                count++;
        }
        if (count == lstTableData.size())
            report.updateTestLog("Verify table data", "[Date] column data is filtered properly", Status.PASS);
        else
            report.updateTestLog("Verify table data", "[Date] column data is NOT filtered properly", Status.FAIL);
    }

    /**
     * Keyword to validate Data in [TRANSACTION EXCEPTION LIST] page
     */
    public void vrfyTableData() {
        String extendedAmountExpected = Utility_Functions.xGetJsonData("CycleCounts_Amount");
        String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        String descriptionExpected = "OTHER";
        List<WebElement> lstTableData = getListElement(AR2WISEPage.lstDateColValue);
        boolean flag = false;
        for (int i = 1; i <= lstTableData.size(); i++) {
            String dateActual = getText(By.xpath("//tr[" + i + "]/td[contains(@class,'mat-column-date')]"));
            String amountActual = getText(By.xpath("//tr[" + i + "]/td[contains(@class,'mat-column-extendedAmount')]"));
            String descriptionActual = getText(By.xpath("//tr[" + i + "]/td[contains(@class,'mat-column-explanation')]/span"));
            if (dateActual.equalsIgnoreCase(date) && amountActual.contains(extendedAmountExpected) && descriptionActual.contains(descriptionExpected)) {
                flag = true;
                Utility_Functions.xHighlight(ownDriver, By.xpath("//tbody/tr[" + i + "]"), "red");
                break;
            }
        }
        if (flag)
            report.updateTestLog("Verify table data", "Table is showing recently created Investment Adjusted record created in WISE", Status.PASS);
        else
            report.updateTestLog("Verify table data", "Table is NOT showing recently created Investment Adjusted record created in WISE", Status.FAIL);
    }

    /**
     * Keyword to validate Data in [TRANSACTION EXCEPTION LIST] page
     */
    public void vrfyTableDataGMM() {
        String amount = jsonData.getData("GMMgrAmount");
        String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        String descriptionExpected = "TESTING PURPOSE";
        List<WebElement> lstTableData = getListElement(AR2WISEPage.lstDateColValue);
        boolean flag = false;
        for (int i = 1; i <= lstTableData.size(); i++) {
            String dateActual = getText(By.xpath("//tr[" + i + "]/td[contains(@class,'mat-column-date')]"));
            String amountActual = getText(By.xpath("//tr[" + i + "]/td[contains(@class,'mat-column-amount')]"));
            String descriptionActual = getText(By.xpath("//tr[" + i + "]/td[contains(@class,'mat-column-explanation')]/span"));
            if (dateActual.equalsIgnoreCase(date) && amountActual.contains(amount) && descriptionActual.contains(descriptionExpected)) {
                flag = true;
                Utility_Functions.xHighlight(ownDriver, By.xpath("//tbody/tr[" + i + "]"), "red");
                break;
            }
        }
        if (flag)
            report.updateTestLog("Verify table data", "Table is showing recently created Gross Margin Manager record created in WISE", Status.PASS);
        else
            report.updateTestLog("Verify table data", "Table is NOT showing recently created Gross Margin Manager record created in WISE", Status.FAIL);
    }

    /**
     * Keyword to validate Data in [TRANSACTION EXCEPTION LIST] page
     */
    public void vrfyTableDataNTI() {
        String amount = jsonData.getData("InvoiceAmount");
        String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        String descriptionExpected = jsonData.getData("AccountName");
        List<WebElement> lstTableData = getListElement(AR2WISEPage.lstDateColValue);
        boolean flag = false;
        for (int i = 1; i <= lstTableData.size(); i++) {
            String dateActual = getText(By.xpath("//tr[" + i + "]/td[contains(@class,'mat-column-date')]"));
            String amountActual = getText(By.xpath("//tr[" + i + "]/td[contains(@class,'mat-column-amount')]"));
            String descriptionActual = getText(By.xpath("//tr[" + i + "]/td[contains(@class,'mat-column-explanation')]/span"));
            if (dateActual.equalsIgnoreCase(date) && amountActual.equalsIgnoreCase(amount) && descriptionActual.contains(descriptionExpected)) {
                flag = true;
                Utility_Functions.xHighlight(ownDriver, By.xpath("//tbody/tr[" + i + "]"), "red");
                break;
            }
        }
        if (flag)
            report.updateTestLog("Verify table data", "Table is showing recently created Non Trade Invoices record created in WISE", Status.PASS);
        else
            report.updateTestLog("Verify table data", "Table is NOT showing recently created Non Trade Invoices record created in WISE", Status.FAIL);
    }

    /**
     * Keyword to validate Data in [TRANSACTION EXCEPTION LIST] page
     */
    public void vrfyTableDataMiscInvAdj() {
        String amount = jsonData.getData("Amount");
        String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        String descriptionExpected = jsonData.getData("Explanation");
        List<WebElement> lstTableData = getListElement(AR2WISEPage.lstDateColValue);
        boolean flag = false;
        for (int i = 1; i <= lstTableData.size(); i++) {
            String dateActual = getText(By.xpath("//tr[" + i + "]/td[contains(@class,'mat-column-date')]"));
            String amountActual = getText(By.xpath("//tr[" + i + "]/td[contains(@class,'mat-column-extendedAmount')]"));
            String descriptionActual = getText(By.xpath("//tr[" + i + "]/td[contains(@class,'mat-column-explanation')]/span"));
            if (dateActual.equalsIgnoreCase(date) && amountActual.contains(amount) && descriptionActual.contains(descriptionExpected)) {
                flag = true;
                Utility_Functions.xHighlight(ownDriver, By.xpath("//tbody/tr[" + i + "]"), "red");
                break;
            }
        }
        if (flag)
            report.updateTestLog("Verify table data", "Table is showing recently created Misc. Inventory Adjustments record created in WISE", Status.PASS);
        else
            report.updateTestLog("Verify table data", "Table is NOT showing recently created Misc. Inventory Adjustments record created in WISE", Status.FAIL);
    }

}
