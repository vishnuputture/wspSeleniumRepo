package businesskeywords.AccountReceivable.ARToWISE;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
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
        }
        return sum;
    }

    public void totalAmount() {
        Double sum = getTotalAmount();
        String amountVal = getText(AR2WISEPage.totalAmount).replace(",", "");
        if (amountVal.contains(".00")) {
            amountVal = amountVal.replace(".00", ".0");
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
            String debit = ownDriver.findElements(AR2WISEPage.breakDownCal).get(i).getText().trim();
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
                    Utility_Functions.xAssertEquals(report, debit.substring(0, debit.length() - 1), "" + debitSum + "", "Debit sum Matches [" + debitSum + "]");
                } else {
                    Utility_Functions.xAssertEquals(report, debit, "" + debitSum + "", "Debit sum Matches [" + debitSum + "]");
                }
                if (credit.endsWith("0")) {
                    Utility_Functions.xAssertEquals(report, credit.substring(0, credit.length() - 1), "" + creditSum + "", "Credit sum Matches [" + creditSum + "]");
                } else {
                    Utility_Functions.xAssertEquals(report, credit, "" + creditSum + "", "Debit sum Matches [" + creditSum + "]");
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
        commonObj.validateElementExists(By.xpath("//span/a[contains(text(),'"+Utility_Functions.xGetJsonData("AccountName")+"')]"), "Company Name matches");
        String user = getProperties("STGUserName").toUpperCase();
        commonObj.validateText(By.xpath("//td[contains(text(),'" + Utility_Functions.xGetCurrentDate("MM/dd/YYYY") + "')]"), Utility_Functions.xGetCurrentDate("MM/dd/YYYY"), "Date matches");
        commonObj.validateText(By.xpath("//td[contains(text(),'" + user + "')]"), user, "User name matches");
    }
}