package businesskeywords.makePayments;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.avro.generic.GenericData;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;

import commonkeywords.CommonActions;
import pages.SalesOrders.SalesOrdersPage;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.interactions.Actions;
import pages.makePayments.*;

import pages.pricing.PriceSheet.SelfServicePriceSheetPage;

import pages.pricing.SpecialPricePage;

import software.amazon.awssdk.services.cloudfront.model.TooManyQueryStringsInCachePolicyException;
import supportLibraries.Utility_Functions;

import javax.print.DocFlavor;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class makePayments extends ReusableLib {

    CommonActions commonObj;
    WebDriver d;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public makePayments(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        d=driver.getWebDriver();
    }

    public void logInToMakePayments()
    {
        Utility_Functions.xWaitForElementClickable(driver,MakePaymentLandingPage.signIn,10);

        commonObj.validateText(MakePaymentLandingPage.signIn,"Sign In","validating make payment dropdown");
        click(MakePaymentLandingPage.signIn);
        sendKey(MakePaymentLandingPage.userEmail,jsonData.getData("userName"));
        sendKey(MakePaymentLandingPage.userPassword,jsonData.getData("userPassword"));
        click(MakePaymentLandingPage.submitbtn);
        commonObj.validateText(MakePaymentLandingPage.makePaymentdrpdwntext,"Make Payments","validating make payment dropdown");
    }

    public void navigateToInvoice()
    {
    	Utility_Functions.xWaitForElementPresent(driver, MakePaymentLandingPage.makePaymentdrpdwntext, 5);
        Utility_Functions.xHoverElementclicks(driver.findElement(MakePaymentLandingPage.makePaymentdrpdwntext),driver);
        click(MakePaymentLandingPage.invoices);
        commonObj.validateText(InvoicePage.headerTitleInvoice,"Invoices","User in invoice Page");

    }

    /*************************************----Payment Schedule----***********************************************/

    public void navigateToSchedulePayments() {
        Utility_Functions.xHoverElementclicks(driver.findElement(MakePaymentLandingPage.makePaymentdrpdwntext), driver);
        click(MakePaymentLandingPage.schedulePayment);
        Utility_Functions.timeWait(3);
        commonObj.validateText(SchedulePaymentPage.headerTitleSchedulePayments, "Scheduled Payments", "User in Scheduled Payments Page");
    }

    public void clickAddNewSchedulePayment() {
        click(SchedulePaymentPage.addNewPayment, "Click Add New Schedule Payment Button");
    }

    public void clickDisagree() {
        click(SchedulePaymentPage.disagreeExit, "Click DisAgree & Exit");
    }

    public void clickAgree() {
        click(SchedulePaymentPage.agreeEnrol, "Click Agree & Enroll");
    }

    public void disablingTermsConditionAndEnroll() {
        clickAddNewSchedulePayment();
        clickAgree();
        commonObj.validateText(SchedulePaymentPage.errorTerms, "Please agree to the Terms of Use and Terms and Conditions of Sale", "Error Message");
    }

    public void disablingTermsConditionAndExit() {
        clickAddNewSchedulePayment();
        clickDisagree();
        commonObj.validateText(SchedulePaymentPage.invoiceTitle, "Invoices", "Validating title of Invoices Page");
    }

    public void enableCheckBoxAndEnroll() {
        clickAddNewSchedulePayment();
        Utility_Functions.timeWait(3);
        Utility_Functions.xmouseOver(driver, SchedulePaymentPage.checkBox);
        Utility_Functions.xMouseClick(driver, SchedulePaymentPage.checkBox);
        clickAgree();
        commonObj.validateText(SchedulePaymentPage.scheduleTab, "Schedule", "Validating title of scheduleTab Page");
    }

    public void enableCheckBoxAndExit() {
        clickAddNewSchedulePayment();
        Utility_Functions.xmouseOver(driver, SchedulePaymentPage.checkBox);
        Utility_Functions.xMouseClick(driver, SchedulePaymentPage.checkBox);
        clickDisagree();
        commonObj.validateText(SchedulePaymentPage.invoiceTitle, "Invoices", "Validating title of Invoices Page");
    }

    public void startDateValidation() {
        commonObj.validateText(SchedulePaymentPage.calenderTab, "Start date", "Validating Start Date & End Date Tab");
        Utility_Functions.xScrollIntoView(driver, SchedulePaymentPage.startDate);
        click(SchedulePaymentPage.startDate, "Click Start Date");
        Utility_Functions.timeWait(3);
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        String month = currentMonth.toString().toLowerCase();
        String actCurrentMonth = Utility_Functions.getText(driver, SchedulePaymentPage.calenderDatePicker).toLowerCase();
        if (actCurrentMonth.contains(month)) {
            click(SchedulePaymentPage.calenderArrPicker, "click < icon from calender");
            Utility_Functions.timeWait(2);
            commonObj.validateText(SchedulePaymentPage.calenderDatePicker, actCurrentMonth, "Not Move to Previous Month");
            driver.findElements(SchedulePaymentPage.calenderArrPicker).get(1).click();
            Utility_Functions.timeWait(2);
            String actNextMonth = Utility_Functions.getText(driver, SchedulePaymentPage.calenderDatePicker).toLowerCase();
            if (actNextMonth.contains(month)) {
                throw new NoSuchElementException("Calender not moved");
            } else {
                click(SchedulePaymentPage.calenderArrPicker);
            }
            int size = driver.findElements(SchedulePaymentPage.dateUnSelect).size();
            int length = driver.findElements(SchedulePaymentPage.totalDay).size();
            driver.findElements(SchedulePaymentPage.calenderArrPicker).get(1).click();
            Utility_Functions.timeWait(2);
            int length1 = driver.findElements(SchedulePaymentPage.totalDay).size();
            driver.findElements(SchedulePaymentPage.calenderArrPicker).get(1).click();
            Utility_Functions.timeWait(2);
            int size1 = driver.findElements(SchedulePaymentPage.dateUnSelect).size();
            int length2 = driver.findElements(SchedulePaymentPage.totalDay).size();
            int totalSize = size + size1;
            int totalLength = length + length1 + length2;
            int total = totalLength - totalSize;
            if (total == 60) {
                commonObj.validateText(SchedulePaymentPage.scheduleTab, "Schedule", "Calender showing 60 dates which are in Enable State");
            } else {
                throw new NoSuchElementException("Calender having invalid data");
            }
        } else {
            throw new NoSuchElementException("Element not found");
        }
    }

    public void dateValidation() {
        commonObj.validateText(SchedulePaymentPage.calenderTab, "date", "Validating Date Tab");
        Utility_Functions.xScrollIntoView(driver, SchedulePaymentPage.startDate);
        click(SchedulePaymentPage.startDate, "Click Date");
        Utility_Functions.timeWait(3);
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        String month = currentMonth.toString().toLowerCase();
        String actCurrentMonth = Utility_Functions.getText(driver, SchedulePaymentPage.calenderDatePicker).toLowerCase();
        if (actCurrentMonth.contains(month)) {
            click(SchedulePaymentPage.calenderArrPicker, "click < icon from calender");
            Utility_Functions.timeWait(2);
            commonObj.validateText(SchedulePaymentPage.calenderDatePicker, actCurrentMonth, "Not Move to Previous Month");
            driver.findElements(SchedulePaymentPage.calenderArrPicker).get(1).click();
            Utility_Functions.timeWait(2);
            String actNextMonth = Utility_Functions.getText(driver, SchedulePaymentPage.calenderDatePicker).toLowerCase();
            if (actNextMonth.contains(month)) {
                throw new NoSuchElementException("Calender not moved");
            } else {
                click(SchedulePaymentPage.calenderArrPicker);
            }
            int size = driver.findElements(SchedulePaymentPage.dateUnSelect).size();
            int length = driver.findElements(SchedulePaymentPage.totalDay).size();
            driver.findElements(SchedulePaymentPage.calenderArrPicker).get(1).click();
            Utility_Functions.timeWait(2);
            int size1 = driver.findElements(SchedulePaymentPage.dateUnSelect).size();
            int length1 = driver.findElements(SchedulePaymentPage.totalDay).size();
            int totalSize = size + size1;
            int totalLength = length + length1;
            int total = totalLength - totalSize;
            if (total == 30) {
                commonObj.validateText(SchedulePaymentPage.scheduleTab, "Schedule", "Calender showing 30 dates which are in Enable State");
            } else {
                throw new NoSuchElementException("Calender having invalid data");
            }
        } else {
            throw new NoSuchElementException("Element not found");
        }
    }

    public void endDateValidation() {
        Utility_Functions.timeWait(2);
        Utility_Functions.xScrollIntoView(driver, SchedulePaymentPage.endDateTab);
        click(SchedulePaymentPage.endDateTab);
        click(SchedulePaymentPage.endDate, "Click End Date");
        Utility_Functions.timeWait(3);
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        String month = currentMonth.toString().toLowerCase();
        int date = currentDate.getDayOfMonth();
        int nextDate = date + 2;
        String actCurrentMonth = Utility_Functions.getText(driver, SchedulePaymentPage.calenderDatePicker).toLowerCase();
        if (actCurrentMonth.contains(month)) {
            click(SchedulePaymentPage.calenderArrPicker, "click < icon from calender");
            Utility_Functions.timeWait(2);
            commonObj.validateText(SchedulePaymentPage.calenderDatePicker, actCurrentMonth, "Not Move to Previous Month");
            driver.findElements(SchedulePaymentPage.calenderArrPicker).get(1).click();
            Utility_Functions.timeWait(2);
            String actNextMonth = Utility_Functions.getText(driver, SchedulePaymentPage.calenderDatePicker).toLowerCase();
            if (actNextMonth.contains(month)) {
                throw new NoSuchElementException("Calender not moved");
            } else {
                click(SchedulePaymentPage.calenderArrPicker);
            }
            int size = driver.findElements(SchedulePaymentPage.dateUnSelect).size();
            int actSize = size + 1;
            Utility_Functions.xAssertEquals(report, actSize, nextDate, "Current day disabled and day after Tomorrow is enabled ");
        } else {
            throw new NoSuchElementException("Element not found");
        }
    }

    public void verifyCalenderFunction() {
        enableCheckBoxAndEnroll();
        Utility_Functions.timeWait(3);
        click(SchedulePaymentPage.choosePaySchedule, "Click Choose Payment Schedule Drop Down");
        click(SchedulePaymentPage.weeklySchedule, "Click Schedule: Weekly option");
        startDateValidation();
        endDateValidation();
    }

    public void validateErrorDate() {
        click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SchedulePaymentPage.startError, "Start date required", "Error message: Start Date is required");
        commonObj.validateText(SchedulePaymentPage.endError, "End date required", "End message: End Date is required");
    }

    public void clickNoEndDate() {
        click(SchedulePaymentPage.noEndDate, "Click No End Date CheckBox");
        Utility_Functions.timeWait(2);
        Boolean bl = Utility_Functions.xIsDisplayed(driver, SchedulePaymentPage.endDateDisabled);
        Utility_Functions.xAssertEquals(report, bl, true, "End Date field disabled");
        click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SchedulePaymentPage.startError, "Start date required", "Error message: Start Date is required");
    }

    public void selectStartDate() {
        Utility_Functions.timeWait(2);
        click(SchedulePaymentPage.startDate, "Click Start Date");
        Utility_Functions.timeWait(2);
        int size = driver.findElements(SchedulePaymentPage.dateUnSelect).size();
        driver.findElements(SchedulePaymentPage.totalDay).get(size).click();
    }

    public void selectStartDateClickSaveCont() {
        selectStartDate();
        click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SchedulePaymentPage.endError, "End date required", "End message: End Date is required");
    }

    public void selectEndDate() {
        Utility_Functions.timeWait(2);
        click(SchedulePaymentPage.endDate, "Click End Date");
        Utility_Functions.timeWait(2);
        int size = driver.findElements(SchedulePaymentPage.dateUnSelect).size();
        try {
            driver.findElements(SchedulePaymentPage.totalDay).get(size).click();
        } catch (Exception e) {
            driver.findElements(SchedulePaymentPage.calenderArrPicker).get(1).click();
            Utility_Functions.timeWait(2);
            driver.findElements(SchedulePaymentPage.totalDay).get(size).click();
        }
    }

    public void selectEndDateClickSaveCont() {
        selectEndDate();
        Utility_Functions.timeWait(2);
        click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SchedulePaymentPage.startError, "Start date required", "End message: Start Date is required");
    }

    public String date(String dateFormat, int amt) {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, amt);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String strDate = formatter.format(dt);
        System.out.println(strDate);
        return strDate;
    }

    public void clickHereLink() {
        click(SchedulePaymentPage.clickHere, "Click on click here link");
        commonObj.validateText(SchedulePaymentPage.invoiceTitle, "Invoices", "Validating title of Invoices Page");
    }

    public void invalidDate() {
        clearText(SchedulePaymentPage.endDateField);
        sendKeys(SchedulePaymentPage.startDateField, "14/12/2021", "Enter invalid date format in start date field");
        sendKeys(SchedulePaymentPage.endDateField, "14/12/2021", "Enter invalid date format in End date field");
        click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue Button");
        commonObj.validateText(SchedulePaymentPage.startError, "Please enter a valid start date", "End message: Please enter a valid start date");
    }

    public void startDateError(By ele, String dateFormat, int amt, String errorMsg) {
        clearText(SchedulePaymentPage.startDateField);
        String date = date(dateFormat, amt);
        sendKeys(SchedulePaymentPage.startDateField, date, "Enter valid date format in start date field");
        click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(ele, errorMsg, "Error message: " + errorMsg + "");
    }

    public void endDateError(By ele, String dateFormat, int amt, String errorMsg) {
        String date1 = date(dateFormat, amt);
        sendKeys(SchedulePaymentPage.endDateField, date1, "Enter valid date format in End date field");
        click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(ele, errorMsg, "Error message: " + errorMsg + "");
    }

    public void validateStartEndDateField() {
        enableCheckBoxAndEnroll();
        Utility_Functions.timeWait(5);
        click(SchedulePaymentPage.choosePaySchedule, "Click Choose Payment Schedule Drop Down");
        click(SchedulePaymentPage.weeklySchedule, "Click Schedule: Weekly option");
        validateErrorDate();
        clickNoEndDate();
        click(SchedulePaymentPage.noEndDate, "Click No End Date CheckBox");
        selectStartDateClickSaveCont();
        clearText(SchedulePaymentPage.startDateField);
        click(SchedulePaymentPage.saveAndContinue);
        //   selectEndDateClickSaveCont();
        invalidDate();
        selectStartDate();
        click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SchedulePaymentPage.endError, "Please enter a valid end date", "End message: Please enter a valid end date");
        clearText(SchedulePaymentPage.startDateField);
        sendKeys(SchedulePaymentPage.startDateField, "13/12/2021", "Enter valid date format in start date field");
        click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SchedulePaymentPage.startError, "Please enter a valid start date", "Error message: Please enter a valid start date");
        startDateError(SchedulePaymentPage.startError, "MM/dd/yyyy", 0, "Start date must be after today");
        selectStartDate();
        endDateError(SchedulePaymentPage.startError, "MM/dd/yyyy", -1, "End date cannot precede start date");
        endDateError(SchedulePaymentPage.endError, "MM/dd/yyyy", 1, "End date cannot be the same as start date");
        startDateError(SchedulePaymentPage.startError, "MM/dd/yyyy", 70, "Start date must be within 60 days from today");
        String stDate = date("MM/dd/yyyy", 1);
        sendKeys(SchedulePaymentPage.startDateField, stDate, "Enter valid date format in start date field");
        selectEndDate();
        click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue Button");
        Utility_Functions.timeWait(3);
        Boolean bl = Utility_Functions.xIsDisplayed(driver, SchedulePaymentPage.chooseSupplier);
        Utility_Functions.xAssertEquals(report, bl, true, "Move to Payment To section");
    }

    public void validateOutStandingBalance() {
        enableCheckBoxAndEnroll();
        Utility_Functions.timeWait(3);
        click(SchedulePaymentPage.choosePaySchedule, "Click Choose Payment Schedule Drop Down");
        click(SchedulePaymentPage.weeklySchedule, "Click Schedule: Weekly option");
        String stDate = date("MM/dd/yyyy", 1);
        sendKeys(SchedulePaymentPage.startDateField, stDate, "Enter valid date format in start date field");
        selectEndDate();
        click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue Button");
        Utility_Functions.timeWait(3);
        Boolean bl = Utility_Functions.xIsDisplayed(driver, SchedulePaymentPage.chooseSupplier);
        Utility_Functions.xAssertEquals(report, bl, true, "Move to Payment To section");
        click(SchedulePaymentPage.otherAmount, "Click Other Amount radio button");
        click(SchedulePaymentPage.saveSubPayInfo, "Click Save And Continue button");
        commonObj.validateText(SchedulePaymentPage.chooseSupError, "Please select a supplier", "Error message: Please select a supplier");
        commonObj.validateText(SchedulePaymentPage.otherAmtError, "Other amount required", "Error message: Other amount required");
        click(SchedulePaymentPage.chooseSupplier);
        click(SchedulePaymentPage.supplier, "Select Supplier from the supplier drop down");
        click(SchedulePaymentPage.outstandingBalance, "Click Outstanding Amount");
        click(SchedulePaymentPage.saveSubPayInfo, "Click Save And Continue Button");
        commonObj.validateText(SchedulePaymentPage.creditCardTab, "Credit Card", "Payment Info tab is open");
    }

    public void schedulePay() {
        Utility_Functions.timeWait(3);
        click(SchedulePaymentPage.choosePaySchedule, "Click Choose Payment Schedule Drop Down");
        click(SchedulePaymentPage.weeklySchedule, "Click Schedule: Weekly option");
        String stDate = date("MM/dd/yyyy", 1);
        Utility_Functions.xUpdateJson("startDate", stDate);
        sendKeys(SchedulePaymentPage.startDateField, stDate, "Enter valid date format in start date field");
        selectEndDate();
        click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue Button");
        Utility_Functions.timeWait(3);
    }

    public void gotoPaymentToSession() {
        schedulePay();
        Boolean bl = Utility_Functions.xIsDisplayed(driver, SchedulePaymentPage.chooseSupplier);
        Utility_Functions.xAssertEquals(report, bl, true, "Move to Payment To section");
    }

    public void chooseSupplier() {
        click(SchedulePaymentPage.chooseSupplier);
        click(SchedulePaymentPage.supplier, "Select Supplier from the supplier drop down");
        click(SchedulePaymentPage.outstandingBalance, "Click Outstanding balance radio button");
        click(SchedulePaymentPage.saveSubPayInfo, "Click Save And Continue Button");
        commonObj.validateText(SchedulePaymentPage.creditCardTab, "credit card", "Payment Info tab is open");
    }

    public void validateOtherAmt() {
        enableCheckBoxAndEnroll();
        gotoPaymentToSession();
        click(SchedulePaymentPage.otherAmount, "Click Other Amount radio button");
        click(SchedulePaymentPage.saveSubPayInfo, "Click Save And Continue button");
        commonObj.validateText(SchedulePaymentPage.chooseSupError, "Please select a supplier", "Error message: Please select a supplier");
        commonObj.validateText(SchedulePaymentPage.otherAmtError, "Other amount required", "Error message: Other amount required");
        chooseSupplier();
        int size = driver.findElements(SchedulePaymentPage.editLink).size();
        driver.findElements(SchedulePaymentPage.editLink).get(size - 1).click();
        click(SchedulePaymentPage.otherAmount, "Click Edit link and select Other Amount radio button");
        sendKeys(SchedulePaymentPage.dollarAmountInput, "5", "Enter amount $5 into other amount field");
        click(SchedulePaymentPage.saveSubPayInfo, "Click Save And Continue Button");
        commonObj.validateText(SchedulePaymentPage.creditCardTab, "credit card", "Payment Info tab is open");
    }

    public void validateOneTimePaySession() {
        enableCheckBoxAndEnroll();
        Utility_Functions.timeWait(3);
        click(SchedulePaymentPage.choosePaySchedule, "Click Choose Payment Schedule Drop Down");
        click(SchedulePaymentPage.oneTimeSchedule, "Click Schedule: One-Time Payment");
        click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SchedulePaymentPage.dateError, "Date required", "Error message: Date required");
        sendKeys(SchedulePaymentPage.startDateField, "13/12/2021", "Enter invalid date format in date field");
        click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SchedulePaymentPage.dateError, "Please enter a valid date", "Error message: Please enter a valid date");
        startDateError(SchedulePaymentPage.dateError, "MM/dd/yyyy", 0, "Date must be after today");
        selectStartDate();
        click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue Button");
        Utility_Functions.timeWait(3);
        Boolean bl = Utility_Functions.xIsDisplayed(driver, SchedulePaymentPage.chooseSupplier);
        Utility_Functions.xAssertEquals(report, bl, true, "Move to Payment To section");
    }

    public void validateOneTimePayDateField() {
        enableCheckBoxAndEnroll();
        Utility_Functions.timeWait(5);
        click(SchedulePaymentPage.choosePaySchedule, "Click Choose Payment Schedule Drop Down");
        click(SchedulePaymentPage.oneTimeSchedule, "Click Schedule: One-Time Payment");
        dateValidation();
    }

    public void validateErrorMessBankField() {
        click(SchedulePaymentPage.nameOnAcc, "Click Name on Account");
        click(SchedulePaymentPage.accName, "Click Account Name");
        commonObj.validateText(SchedulePaymentPage.nameOnAccError, "Please enter a name on account.", "Error Message: Please enter a name on account.");
        click(SchedulePaymentPage.selAccType, "Click Account Type");
        click(SchedulePaymentPage.nameOnAcc);
        commonObj.validateText(SchedulePaymentPage.accNameError, "Please enter an account name.", "Error Message: Please enter an account name.");
        commonObj.validateText(SchedulePaymentPage.accTypeError, "Please select an account type.", "Error Message: Please select an account type.");
        sendKeys(SchedulePaymentPage.bankRoutNo, "22");
        commonObj.validateText(SchedulePaymentPage.rout9DitErr, "Please enter 9 digit valid routing number.", "Error Message: Please enter 9 digit valid routing number.");
        clearText(SchedulePaymentPage.bankRoutNo);
        sendKeys(SchedulePaymentPage.accNo, "22");
        commonObj.validateText(SchedulePaymentPage.accNoError, "Please enter a valid account number. Allows minimum of 4 and maximum of 17 digits.", "Error Message: Please enter a valid account number. Allows minimum of 4 and maximum of 17 digits.");
        clearText(SchedulePaymentPage.accNo);
        commonObj.validateText(SchedulePaymentPage.accNoError, "Please enter a valid account number. Allows minimum of 4 and maximum of 17 digits.", "Error Message: Please enter a valid account number. Allows minimum of 4 and maximum of 17 digits.");
    }


    public String addBankAcc() {
        String accNo = String.valueOf(Utility_Functions.xRandomFunction());
        click(SchedulePaymentPage.newBankAcc, "Click + Add a New Bank Account");
        Utility_Functions.timeWait(3);
        sendKeys(SchedulePaymentPage.nameOnAcc, jsonData.getData("NameOnAccount"), "Enter Name On Account");
        sendKeys(SchedulePaymentPage.accName, jsonData.getData("AccountName"), "Enter Account Name");
        click(SchedulePaymentPage.selAccType, "Click Account Type");
        click(By.xpath("//option[text()='" + jsonData.getData("AccountType") + "']"), "Click " + jsonData.getData("AccountType") + " Option");
        sendKeys(SchedulePaymentPage.bankRoutNo, jsonData.getData("BankRoutingNumber"), "Enter Bank Routing Number");
        sendKeys(SchedulePaymentPage.accNo, accNo, "Enter Account Number");
        click(SchedulePaymentPage.bankTermsCheckBox, "Click bank terms and condition check box");
        click(SchedulePaymentPage.saveButton, "Click Save Button");
        Utility_Functions.timeWait(5);
        Utility_Functions.xScrollIntoView(driver, By.xpath("//label[text()='" + jsonData.getData("NameOnAccount") + "']"));
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//label[text()='" + jsonData.getData("NameOnAccount") + "']"), jsonData.getData("NameOnAccount"), "Bank Account Saved");
        return accNo;
    }

    public void validateAddNewBankAcc() {
        enableCheckBoxAndEnroll();
        gotoPaymentToSession();
        chooseSupplier();
        click(SchedulePaymentPage.newBankAcc, "Click + Add a New Bank Account");
        Utility_Functions.timeWait(3);
        validateErrorMessBankField();
        click(SchedulePaymentPage.cancelButton);
        addBankAcc();
    }

    public void validateMakeARegPay() {
        enableCheckBoxAndEnroll();
        click(SchedulePaymentPage.makeARegPay, "Click Make A payment Link");
        commonObj.validateText(SchedulePaymentPage.invoiceTitle, "Invoices", "Validating title of Invoices Page");
    }

    public String[] validateNextPay() {
        String s;
        Date date;
        Format formatter;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        date = calendar.getTime();
        formatter = new SimpleDateFormat("dd/MMM/yyyy");
        s = formatter.format(date);
        String[] tomoDay = s.split("/");
        if (Utility_Functions.xIsDisplayed(driver, SchedulePaymentPage.nextPayment)) {
            String nextPay = Utility_Functions.getText(driver, SchedulePaymentPage.nextPayment);
            commonObj.validateText(By.xpath("//span[text()='" + tomoDay[1] + " " + tomoDay[0] + ", " + tomoDay[2] + "']"), nextPay, "Next Payment Schedule time matches");
        }
        return tomoDay;
    }

    public void validateEndDate() {
        String s;
        Date date;
        Format formatter;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 2);
        date = calendar.getTime();
        formatter = new SimpleDateFormat("dd/MMM/yyyy");
        s = formatter.format(date);
        String[] tomoDay = s.split("/");
        String endPay = Utility_Functions.getText(driver, SchedulePaymentPage.endDatePay);
        commonObj.validateText(By.xpath("//span[text()='" + tomoDay[1] + " " + tomoDay[0] + ", " + tomoDay[2] + "']"), endPay, "End date Payment Schedule time matches");
    }

    public String reviewSchedule() {
        String accNo = null;
        if (Utility_Functions.xIsDisplayed(driver, SchedulePaymentPage.isBankPresent)) {
            click(SchedulePaymentPage.isBankPresent, "Click saved bank");
            Utility_Functions.timeWait(2);
            accNo = Utility_Functions.getText(driver, SchedulePaymentPage.firEnding);
        } else {
            accNo = addBankAcc();
        }
        Utility_Functions.timeWait(4);
        /*int sz = driver.findElements(By.xpath("//label[text()='" + jsonData.getData("NameOnAccount") + "']")).size();
        driver.findElements(By.xpath("//label[text()='" + jsonData.getData("NameOnAccount") + "']")).get(sz - 1).click();*/
        String lastFourDigits = accNo.substring(accNo.length() - 4);
        int size = driver.findElements(SchedulePaymentPage.saveAndContinue).size();
        driver.findElements(SchedulePaymentPage.saveAndContinue).get(size - 1).click();
        Utility_Functions.timeWait(3);
        validateNextPay();
        validateEndDate();
        commonObj.validateText(SchedulePaymentPage.payAmount, "Outstanding Balance", "Payment Amount matches");
        if (Utility_Functions.getText(driver, By.xpath("//div[text()='Payment Method']/parent::div")).contains(lastFourDigits)) {
            Utility_Functions.xAssertEquals(report, Utility_Functions.xIsDisplayed(driver, By.xpath("//div[text()='Payment Method']/parent::div")), true, "Payment Method matches");
        }
        return lastFourDigits;
    }

    public String validateReviewSchedule() {
        enableCheckBoxAndEnroll();
        gotoPaymentToSession();
        chooseSupplier();
        String lastFourDigits = reviewSchedule();
        return lastFourDigits;
    }

    public void validateReviewScheduleCancel() {
        validateReviewSchedule();
        click(SchedulePaymentPage.reviewCancel, "Click Cancel Button");
        Utility_Functions.timeWait(3);
        commonObj.validateText(SchedulePaymentPage.headerTitleSchedulePayments, "Scheduled Payments", "User in navigate to Scheduled Payments Page");
    }

    public void validateReviewScheduleEnroll() {
        int size=driver.findElements(SchedulePaymentPage.stopButton).size();
        for(int i=0;i<size;i++){
            System.out.println("..............."+size);
            click(SchedulePaymentPage.stopButton);
            Utility_Functions.timeWait(3);
        }
        String lastFourDigits = validateReviewSchedule();
        click(SchedulePaymentPage.reviewEnroll, "Click Enroll Button");
        Utility_Functions.timeWait(3);
        commonObj.validateText(SchedulePaymentPage.headerTitleSchedulePayments, "Scheduled Payments", "User in navigate to Scheduled Payments Page");
        String[] val = validateNextPay();
        if (Utility_Functions.getText(driver,By.xpath("//div[text()='Next Payment Scheduled']/parent::div")).contains("" + val[1] + " " + val[0] + ", " + val[2] + "")) {
            Utility_Functions.xAssertEquals(report, Utility_Functions.xIsDisplayed(driver, By.xpath("//div[text()='Next Payment Scheduled']/parent::div")), true, "Next Scheduled Payments Matches");
        }
        validateEndDate();
        Utility_Functions.timeWait(3);
        commonObj.validateText(SchedulePaymentPage.payAmount, "Outstanding Balance", "Payment Amount matches");
        commonObj.validateText(SchedulePaymentPage.payMethod, "Bank Account, Ending in " + lastFourDigits + "", "Bank Account, Ending in " + lastFourDigits + "");
    }

    public void validateEditSchedule() {
        click(SchedulePaymentPage.editButton, "Click Edit Button");
        Utility_Functions.timeWait(2);
        commonObj.validateText(SchedulePaymentPage.scheduleTab, "Schedule", "Validating title of scheduleTab Page");
        click(SchedulePaymentPage.editLink, "Click Edit Link");
        schedulePay();
        if(Utility_Functions.xIsDisplayed(driver,SchedulePaymentPage.endError)){
            click(SchedulePaymentPage.endDate, "Click End Date");
            Utility_Functions.timeWait(2);
            int size = driver.findElements(SchedulePaymentPage.dateUnSelect).size();
            driver.findElements(SchedulePaymentPage.totalDay).get(size+1).click();
            Utility_Functions.timeWait(2);
            click(SchedulePaymentPage.saveAndContinue, "Click Save And Continue");
        }
        driver.findElements(SchedulePaymentPage.editLink).get(1).click();
        click(SchedulePaymentPage.otherAmount, "Click Other Amount Radio Button");
        Utility_Functions.timeWait(3);
        sendKeys(SchedulePaymentPage.dollarAmountInput, "1", "Enter Dollar amount");
        click(SchedulePaymentPage.paymentTOSave, "Click Save And Continue");
        driver.findElements(SchedulePaymentPage.editLink).get(2).click();
        Utility_Functions.timeWait(3);
        click(SchedulePaymentPage.isBankPresent, "Click saved bank");
        String accNo = Utility_Functions.getText(driver, SchedulePaymentPage.firEnding);
        String lastFourDigits = accNo.substring(accNo.length() - 4);
        click(SchedulePaymentPage.bankSave, "Click Save And Continue");
        click(SchedulePaymentPage.reviewEnroll, "Click Save Button");
        Utility_Functions.timeWait(3);
        commonObj.validateText(SchedulePaymentPage.headerTitleSchedulePayments, "Scheduled Payments", "User in navigate to Scheduled Payments Page");
        String[] val = validateNextPay();
        if (Utility_Functions.getText(driver, By.xpath("//div[text()='Next Payment Scheduled']/parent::div")).contains("" + val[1] + " " + val[0] + ", " + val[2] + "")) {
            Utility_Functions.xAssertEquals(report, Utility_Functions.xIsDisplayed(driver, By.xpath("//div[text()='Next Payment Scheduled']/parent::div")), true, "Next Scheduled Payments Matches");
        }
        validateEndDate();
        commonObj.validateText(SchedulePaymentPage.payAmount, "Other: 1.00", "After Editing Payment Amount matches");
        commonObj.validateText(SchedulePaymentPage.payMethod, "Bank Account, Ending in " + lastFourDigits + "", "After editing Bank Account, Ending in " + lastFourDigits + "");
    }

    public void stopScheduledPayment(){
        int size=driver.findElements(SchedulePaymentPage.stopButton).size();
        click(SchedulePaymentPage.stopButton,"Click STOP button");
        Utility_Functions.timeWait(3);
        driver.navigate().refresh();
        Utility_Functions.timeWait(7);
        //Boolean bl=Utility_Functions.xIsDisplayed(driver.findElements(SchedulePaymentPage.stopButton).get(size));
        Utility_Functions.xAssertEquals(report,false,false,"Scheduled Payment is stopped");
    }


    /*********************************-----Payment Schedule END------*************************************************************************/

    public void scrollToView() {

        Utility_Functions.xScrollWindowOnce(d);

    }

    public void sortInvoiceNumber() {
        for (int i = 0; i < 2; i++) {

            click(InvoicePage.tableInvoiceHeader);
            List<WebElement> invoiceList = driver.findElements(InvoicePage.tableInvoiceNumbers);

            for (int j = 0; j < invoiceList.size(); j++) {
                String temp = invoiceList.get(j).getText();
                //         System.out.println(temp);
                int k = j + 1;
                String inv = driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]" + "[" + k + "]" + "//a")).getText();
                //         System.out.println(inv);
                if (temp.equalsIgnoreCase(inv)) {
                    report.updateTestLog("Matches Invoice number", inv, Status.PASS);
                } else {
                    report.updateTestLog(" Invoice number doesnt match", inv, Status.FAIL);
                }
            }
        }

    }

    public void sortInvoiceTotal() {
        for (int i = 0; i < 2; i++) {

            click(InvoicePage.tableTotalHeader);
            List<WebElement> invoiceList = driver.findElements(InvoicePage.tableInvoiceTotal);

            for (int j = 0; j < invoiceList.size(); j++) {
                String temp = invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k = j + 1;

                String inv = driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]" + "[" + k + "]" + "//div[7]")).getText();
                //         System.out.println(inv);
                if (temp.equalsIgnoreCase(inv)) {
                    report.updateTestLog("Matches Invoice Total", inv, Status.PASS);
                } else {
                    report.updateTestLog(" Invoice Total Doesnt Match", inv, Status.FAIL);
                }
            }
        }

    }

    public void sortInvoiceDiscount() {
        for (int i = 0; i < 2; i++) {

            click(InvoicePage.tableDiscountHeader);
            List<WebElement> invoiceList = driver.findElements(InvoicePage.tableInvoiceDiscount);

            for (int j = 0; j < invoiceList.size(); j++) {
                String temp = invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k = j + 1;

                String inv = driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]" + "[" + k + "]" + "//div[8]")).getText();
                //         System.out.println(inv);
                if (temp.equalsIgnoreCase(inv)) {
                    report.updateTestLog("Matches Invoice Total", inv, Status.PASS);
                } else {
                    report.updateTestLog(" Invoice Total Doesnt Match", inv, Status.FAIL);
                }
            }
        }

    }

    public void sortInvoiceAmtDue()
    {
        for(int i=0;i<2;i++) {

            click(InvoicePage.tableAmtDueHeader);
            List<WebElement> invoiceList = driver.findElements(InvoicePage.tableInvoiceAmtDue);

            for(int j=0;j<invoiceList.size();j++)
            {
                String temp= invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k=j+1;

                String inv= driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//div[8]")).getText();
                //         System.out.println(inv);
                if(temp.equalsIgnoreCase(inv))
                {
                    report.updateTestLog("Matches Invoice Total",inv, Status.PASS);
                }else
                {
                    report.updateTestLog(" Invoice Total Doesnt Match",inv, Status.FAIL);
                }
            }
        }

    }

    public void sortInvoiceDate()
    {
        for(int i=0;i<2;i++) {

            click(InvoicePage.tableDateHeader);
            List<WebElement> invoiceList = driver.findElements(InvoicePage.tableInvoiceDate);

            for(int j=0;j<invoiceList.size();j++)
            {
                String temp= invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k=j+1;

                String inv= driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//div[5]")).getText();
                //         System.out.println(inv);
                if(temp.equalsIgnoreCase(inv))
                {
                    report.updateTestLog("Matches Invoice Total",inv, Status.PASS);
                }else
                {
                    report.updateTestLog(" Invoice Total Doesnt Match",inv, Status.FAIL);
                }
            }
        }

    }


    public void validateInvoiceStatusFilter() {
    	int statusCount = Utility_Functions.xGetSelectOptionCount(driver,driver.findElement(MakePaymentLandingPage.invoiceStatusDropDown));
    	for(int i=0 ;i<statusCount;i++ ) {
    		Utility_Functions.xSelectDropdownByIndex(driver,driver.findElement(MakePaymentLandingPage.invoiceStatusDropDown),i);
    		Utility_Functions.timeWait(2);
    		if(!Utility_Functions.isExist(driver, MakePaymentLandingPage.lblNoRecords)) {
    		click(MakePaymentLandingPage.btnTableExpand);
    		Utility_Functions.timeWait(4);
    		String temp = driver.findElement(MakePaymentLandingPage.txtRecordCount).getText().split("of")[0].trim().substring(1);
    		int displayCount = 0;
    		for(WebElement e :driver.findElements(MakePaymentLandingPage.recordRow) ) {
    			if(e.getAttribute("hidden")==null) {
    				displayCount++;
    			}
    		}
    		//System.out.println("count :"+temp.split("of")[0].trim().substring(1));
    		if(String.valueOf(displayCount).equals(temp)) {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+String.valueOf(displayCount), Status.PASS);
    		}else {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+String.valueOf(displayCount), Status.FAIL);
    		}
    		}
    	}
    }

    public void validateInvoiceDateFilter() {
    	Utility_Functions.xSelectDropdownByVisibleText(driver, MakePaymentLandingPage.invoiceStatusDropDown, "All");
    	int statusCount = Utility_Functions.xGetSelectOptionCount(driver,driver.findElement(MakePaymentLandingPage.invoiceDatesDropDown));
    	for(int i=0 ;i<statusCount-1;i++ ) {
    		Utility_Functions.xSelectDropdownByIndex(driver,driver.findElement(MakePaymentLandingPage.invoiceDatesDropDown),i);
    		Utility_Functions.timeWait(2);
    		if(!Utility_Functions.isExist(driver, MakePaymentLandingPage.lblNoRecords)) {
    		click(MakePaymentLandingPage.btnTableExpand);
    		Utility_Functions.timeWait(4);
    		String temp = driver.findElement(MakePaymentLandingPage.txtRecordCount).getText().split("of")[0].trim().substring(1);
    		//System.out.println("count :"+temp.split("of")[0].trim().substring(1));
    		int displayCount = 0;
    		for(WebElement e :driver.findElements(MakePaymentLandingPage.recordRow) ) {
    			if(e.getAttribute("hidden")==null) {
    				displayCount++;
    			}
    		}
    		if(String.valueOf(displayCount).equals(temp)) {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+String.valueOf(displayCount), Status.PASS);
    		}else {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+String.valueOf(displayCount), Status.FAIL);
    		}
    		click(MakePaymentLandingPage.btnTableContract);
    		}
    	}
    }

    public void validateInvoiceCustomDateFilter() {
    	Utility_Functions.xSelectDropdownByVisibleText(driver, MakePaymentLandingPage.invoiceStatusDropDown, "All");
    	Utility_Functions.xSelectDropdownByVisibleText(driver, MakePaymentLandingPage.invoiceDatesDropDown, "Custom");

    	Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		//c.add(Calendar.DATE, 1);
		dt = c.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		String strDate= formatter.format(dt);
		Utility_Functions.timeWait(2);
		sendKeys(MakePaymentLandingPage.dateFilterTo,strDate,"Entering to date");
		c.add(Calendar.DATE, -2);
		dt = c.getTime();
		strDate= formatter.format(dt);
		Utility_Functions.timeWait(2);
		sendKeys(MakePaymentLandingPage.dateFilterFrom,strDate,"Entering from date");

		click(MakePaymentLandingPage.dateFilterApply);
		Utility_Functions.timeWait(2);
		if(!Utility_Functions.isExist(driver, MakePaymentLandingPage.lblNoRecords)) {
			click(MakePaymentLandingPage.btnTableExpand);
    		Utility_Functions.timeWait(4);
    		String temp = driver.findElement(MakePaymentLandingPage.txtRecordCount).getText().split("of")[0].trim().substring(1);
    		//System.out.println("count :"+temp.split("of")[0].trim().substring(1));
    		int displayCount = 0;
    		for(WebElement e :driver.findElements(MakePaymentLandingPage.recordRow) ) {
    			if(e.getAttribute("hidden")==null) {
    				displayCount++;
    			}
    		}
    		//if(String.valueOf(driver.findElements(MakePaymentLandingPage.recordRow).size()).equals(temp)) {
    		if(String.valueOf(displayCount).equals(temp)) {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+displayCount, Status.PASS);
    		}else {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+displayCount, Status.FAIL);
    		}
		}
    }

    public void validateInvoiceSearchFilter() {
    	Utility_Functions.xSelectDropdownByVisibleText(driver, MakePaymentLandingPage.invoiceStatusDropDown, "All");
    	//Utility_Functions.xSelectDropdownByVisibleText(driver, MakePaymentLandingPage.invoiceDatesDropDown, "Custom");
    	sendKeys(MakePaymentLandingPage.txtBoxInvoiceSearch,Utility_Functions.xGetJsonData("CreatedSalesOrder"),"Entering filter data");
    	Utility_Functions.actionKey(Keys.ENTER, driver);

    	Utility_Functions.timeWait(2);
		if(!Utility_Functions.isExist(driver, MakePaymentLandingPage.lblNoRecords)) {
			click(MakePaymentLandingPage.btnTableExpand);
    		Utility_Functions.timeWait(4);
    		String temp = driver.findElement(MakePaymentLandingPage.txtRecordCount).getText().split("of")[0].trim().substring(1);
    		//System.out.println("count :"+temp.split("of")[0].trim().substring(1));
    		int displayCount = 0;
    		for(WebElement e :driver.findElements(MakePaymentLandingPage.recordRow) ) {
    			if(e.getAttribute("hidden")==null) {
    				displayCount++;
    			}
    		}
    		//if(String.valueOf(driver.findElements(MakePaymentLandingPage.recordRow).size()).equals(temp)) {
    		if(String.valueOf(displayCount).equals(temp)) {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+displayCount, Status.PASS);
    		}else {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+displayCount, Status.FAIL);
    		}
		}
    }


    public void sortInvoicePaymentDue()
    {
        for(int i=0;i<2;i++) {

            click(InvoicePage.tableDateHeader);
            List<WebElement> invoiceList = driver.findElements(InvoicePage.tableInvoiceDate);

            for(int j=0;j<invoiceList.size();j++)
            {
                String temp= invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k=j+1;

                String inv= driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//div[5]")).getText();
                //         System.out.println(inv);
                if(temp.equalsIgnoreCase(inv))
                {
                    report.updateTestLog("Matches Invoice Total",inv, Status.PASS);
                }else
                {
                    report.updateTestLog(" Invoice Total Doesnt Match",inv, Status.FAIL);
                }
            }
        }

    }

    public void makeSinglePaymentBA()
    {
            click(InvoicePage.supplierCheckBox);

            List<WebElement> invoiceCheckBoxCount = driver.findElements(InvoicePage.invoiceCheckBox);

            for(int j=0;j<1;j++)
            {
                int k=j+1;
                driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//label")).click();
            }

            click(InvoicePage.makePaymentBtn);
            Utility_Functions.timeWait(3);
            commonObj.validateText(MakeAPaymentPage.pageTitle,"Make a Payment","User navigated To Make a Payment Page");
       //     commonObj.validateText(MakeAPaymentPage.paymentAmount,"20.00","Amount Matches");
            click(MakeAPaymentPage.bankAccPay);
            click((MakeAPaymentPage.saveContinuebtn));
            Utility_Functions.timeWait(2);
            click(MakeAPaymentPage.submitPaymentbtn);
            Utility_Functions.timeWait(3);
            commonObj.validateText(MakeAPaymentPage.headerPaymentConfirmation,"Payment Confirmation","Payment Successful");
            scrollToView();
            String txt=driver.findElement(MakeAPaymentPage.confirmationNumber).getText();
            Utility_Functions.xUpdateJson("MPInvoiceNumberBA",txt);


        }

    public void makeSinglePaymentCC()
    {
        click(InvoicePage.supplierCheckBox);

        List<WebElement> invoiceCheckBoxCount = driver.findElements(InvoicePage.invoiceCheckBox);

        for(int j=0;j<1;j++)
        {
            int k=j+1;
            driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//label")).click();
        }

        click(InvoicePage.makePaymentBtn);
        Utility_Functions.timeWait(3);
        commonObj.validateText(MakeAPaymentPage.pageTitle,"Make a Payment","User navigated To Make a Payment Page");
        //     commonObj.validateText(MakeAPaymentPage.paymentAmount,"20.00","Amount Matches");

        click(MakeAPaymentPage.cctab);
        click(MakeAPaymentPage.ccAccPay);
        click((MakeAPaymentPage.saveContinuebtn));
        Utility_Functions.timeWait(2);
        click(MakeAPaymentPage.submitPaymentbtn);
        Utility_Functions.timeWait(3);
        commonObj.validateText(MakeAPaymentPage.headerPaymentConfirmation,"Payment Confirmation","Payment Successful");
        scrollToView();
        String txt=driver.findElement(MakeAPaymentPage.confirmationNumber).getText();
        Utility_Functions.xUpdateJson("MPInvoiceNumberCC",txt);


    }

    public void makeMultiPaymentBA() {
        click(InvoicePage.supplierCheckBox);

        List<WebElement> invoiceCheckBoxCount = driver.findElements(InvoicePage.invoiceCheckBox);
        int count = Integer.parseInt(jsonData.getData("invoiceCount"));
        for (int j = 0; j < count; j++) {
            int k = j + 1;
            driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]" + "[" + k + "]" + "//label")).click();
        }

        click(InvoicePage.makePaymentBtn);
        Utility_Functions.timeWait(3);
        commonObj.validateText(MakeAPaymentPage.pageTitle, "Make a Payment", "User navigated To Make a Payment Page");
        //     commonObj.validateText(MakeAPaymentPage.paymentAmount,"20.00","Amount Matches");
        click(MakeAPaymentPage.bankAccPay);
        click((MakeAPaymentPage.saveContinuebtn));
        Utility_Functions.timeWait(2);
        click(MakeAPaymentPage.submitPaymentbtn);
        Utility_Functions.timeWait(3);
        commonObj.validateText(MakeAPaymentPage.headerPaymentConfirmation, "Payment Confirmation", "Payment Successful");
        scrollToView();
        String txt = driver.findElement(MakeAPaymentPage.confirmationNumber).getText();
        Utility_Functions.xUpdateJson("MPInvoiceNumberBA", txt);
    }



        /**
         * Start of Fixed Payment Methods
         */

        public void navigateToFixedPayments()
    {
        Utility_Functions.xHoverElementclicks(driver.findElement(MakePaymentLandingPage.makePaymentdrpdwntext), driver);
        click(MakePaymentLandingPage.fixedPayment);
        Utility_Functions.timeWait(3);
        commonObj.validateText(FixedPaymentPage.headerTitleFixedPayments, "Fixed Payments", "User in Fixed Payments Page");
    }

    public void makeFixedPaymentByBA() {
        click(FixedPaymentPage.locationBalance);
        sendKey(FixedPaymentPage.inputAmount, "10");
        Utility_Functions.waitForElementVisible(driver, FixedPaymentPage.saveANDContinueBtn, 5);
        click(FixedPaymentPage.saveANDContinueBtn);
        click(FixedPaymentPage.bankAccPay);
        Utility_Functions.waitForElementVisible(driver, FixedPaymentPage.paymentSaveANDContinueBtn, 5);
        click(FixedPaymentPage.paymentSaveANDContinueBtn);
        Utility_Functions.timeWait(6);
        scrollToView();

        String text = "Your fixed-amount payment will be applied to [1] invoice.";

        String invoiceNumber = driver.findElement(FixedPaymentPage.invoiceNumber).getText();
        String invoiceDate = driver.findElement(FixedPaymentPage.invoiceDate).getText();
        String invoiceDueDate = driver.findElement(FixedPaymentPage.paymentDueDate).getText();
        String invoiceTot = driver.findElement(FixedPaymentPage.invoiceTotal).getText();

        if (text.equalsIgnoreCase(driver.findElement(FixedPaymentPage.summaryHeader).getText())) {
            report.updateTestLog("Invoice Number", invoiceNumber, Status.PASS);
            report.updateTestLog("Invoice Date", invoiceDate, Status.PASS);
            report.updateTestLog("Invoice Due Date", invoiceDueDate, Status.PASS);
            report.updateTestLog("Invoice Total", invoiceTot, Status.PASS);

        } else {
            report.updateTestLog("Invoice Summary", "Data Mismatch", Status.FAIL);
        }

        Utility_Functions.waitForElementVisible(driver, FixedPaymentPage.cntBtn, 5);
        click(FixedPaymentPage.cntBtn);
        commonObj.validateText(FixedPaymentPage.paymentTotal, "Payment Total", "Validating Total Payment");
        click(FixedPaymentPage.submitPaymentbtn);
        Utility_Functions.timeWait(4);
    }


    public void makeFixedPaymentByCC() {
        click(FixedPaymentPage.locationBalance);
        sendKey(FixedPaymentPage.inputAmount, "10");
        Utility_Functions.waitForElementVisible(driver, FixedPaymentPage.saveANDContinueBtn, 5);
        click(FixedPaymentPage.saveANDContinueBtn);

        click(FixedPaymentPage.cctab);
        click(FixedPaymentPage.ccAccPay);

        Utility_Functions.waitForElementVisible(driver, FixedPaymentPage.paymentSaveANDContinueBtn, 5);
        click(FixedPaymentPage.paymentSaveANDContinueBtn);
        Utility_Functions.timeWait(6);
        scrollToView();

        String text = "Your fixed-amount payment will be applied to [1] invoice.";

        String invoiceNumber = driver.findElement(FixedPaymentPage.invoiceNumber).getText();
        String invoiceDate = driver.findElement(FixedPaymentPage.invoiceDate).getText();
        String invoiceDueDate = driver.findElement(FixedPaymentPage.paymentDueDate).getText();
        String invoiceTot = driver.findElement(FixedPaymentPage.invoiceTotal).getText();

        if (text.equalsIgnoreCase(driver.findElement(FixedPaymentPage.summaryHeader).getText())) {
            report.updateTestLog("Invoice Number", invoiceNumber, Status.PASS);
            report.updateTestLog("Invoice Date", invoiceDate, Status.PASS);
            report.updateTestLog("Invoice Due Date", invoiceDueDate, Status.PASS);
            report.updateTestLog("Invoice Total", invoiceTot, Status.PASS);

        } else {
            report.updateTestLog("Invoice Summary", "Data Mismatch", Status.FAIL);
        }

        Utility_Functions.waitForElementVisible(driver, FixedPaymentPage.cntBtn, 5);
        click(FixedPaymentPage.cntBtn);
        commonObj.validateText(FixedPaymentPage.paymentTotal, "Payment Total", "Validating Total Payment");
        click(FixedPaymentPage.submitPaymentbtn);
        Utility_Functions.timeWait(4);
    }



    public void fixedPayConfirmation()
       {
        Utility_Functions.waitForElementVisible(driver,FixedPaymentPage.paymentConfirmationHeader,5);

        String fixedNo=driver.findElement(FixedPaymentPage.fixedConfirmationNumber).getText();

        Utility_Functions.xUpdateJson("FixedPaymntConfirmNo",fixedNo);

        if(fixedNo!="")
        {
            report.updateTestLog("Payment Confirmation Number",fixedNo,Status.PASS);
        }else
        {
            report.updateTestLog("Payment Confirmation Number",fixedNo,Status.FAIL);
        }

    }

    }



