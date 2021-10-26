package pages.makePayments;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SchedulePaymentPage extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public SchedulePaymentPage(Helper helper) {
        super(helper);
    }

    public static By headerTitleSchedulePayments= By.xpath("//h1[text()='Scheduled Payments']");
    public static By addNewPayment=By.xpath("//button[text()='Add New Scheduled Payment']");
    public static By checkBox=By.cssSelector("#userAgreedOne");
    public static By clickHere=By.xpath("//a[text()='click here']");
    public static By agreeEnrol=By.xpath("//button[text()='Agree & Enroll']");
    public static By disagreeExit=By.xpath("//button[text()='Disagree & Exit']");
    public static By scheduleTab=By.xpath("//h2[text()='Schedule']");
    public static By errorTerms=By.xpath("//span[@class='agree-error ng-star-inserted']");
    public static By invoiceTitle=By.xpath("//h1[text()='Invoices']");
    public static By choosePaySchedule=By.id("pay-schedule-select");
    public static By weeklySchedule=By.xpath("//option[text()='Schedule: Weekly']");
    public static By oneTimeSchedule=By.xpath("//option[text()='Schedule: One-Time Payment']");
    public static By calenderTab=By.xpath("//label[@class='start-date-label']");
    public static By endDateTab=By.xpath("//label[@class='end-date-label']");
    public static By startDate=By.xpath("//i[@class='fa fa-calendar']");
    public static By endDate=By.xpath("(//i[@class='fa fa-calendar'])[2]");
    public static By calenderDatePicker=By.xpath("//div[contains(@class,'datepicker__calendar__nav__header')]");
    public static By calenderArrPicker=By.xpath("//div[contains(@class,'datepicker__calendar__nav__arrow')]");
    public static By dateUnSelect=By.xpath("//div[contains(@style,'rgb(215, 215, 215)')]/span");
    public static By totalDay=By.xpath("//div[contains(@class,'calendar__month__day')]/span");
    public static By startDateField=By.xpath("//input[@placeholder='mm/dd/yyyy' and contains(@class,'win-start')]");
    public static By endDateField=By.xpath("//input[@placeholder='mm/dd/yyyy' and contains(@class,'win-end')]");
    public static By noEndDate=By.xpath("//label[@for='noEndDateOne']");
    public static By saveAndContinue=By.xpath("//button[text()='Save and Continue']");
    public static By startError=By.id("SchedulePaymentsCustomStartDateError");
    public static By dateError=By.id("SchedulePaymentsCustomDateError");
    public static By endError=By.id("SchedulePaymentsCustomEndDateError");
    public static By endDateDisabled=By.xpath("//input[@disabled]");
    public static By chooseSupplier=By.id("supplier-select");
    public static By supplier=By.xpath("//option[@value='99599']");
    public static By outstandingBalance=By.xpath("//label[@for='outstandingBalance']");
    public static By otherAmount=By.xpath("//label[@for='otherAmount']");
    public static By dollarAmountInput=By.id("dollarAmountInput");
    public static By chooseSupError=By.xpath("//div[contains(@class,'win-form-input-err-msg')]");
    public static By otherAmtError=By.xpath("(//div[contains(@class,'win-form-input-err-msg')])[2]");
    public static By editLink=By.xpath("//a[text()='Edit']");
    public static By creditCardTab=By.xpath("//li[text()=' Credit Card ']");
    public static By saveSubPayInfo=By.xpath("//button[@class='win-btn win-btn-primary schedule-payment-supplier-amount-save-continue']");
}