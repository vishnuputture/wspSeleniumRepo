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
    public static By paymentTOSave=By.xpath("//button[contains(@class,'supplier-amount-save-continue')]");
    public static By bankSave=By.xpath("//button[contains(@class,'payment-method-save-continue')]");
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
    public static By newBankAcc=By.xpath("//a[text()='+ Add a New Bank Account']");
    public static By nameOnAcc=By.xpath("//input[@title='Name on account']");
    public static By accName=By.xpath("//input[@title='Account Name']");
    public static By selAccType=By.xpath("//select[@title='Select Account Type']");
    public static By bankRoutNo=By.xpath("//input[@title='Bank Routing Number']");
    public static By accNo=By.xpath("//input[@title='Account Number']");
    public static By bankTermsCheckBox=By.xpath("//div[contains(@class,'co_bank_terms-box')]");
    public static By cancelButton=By.xpath("//button[text()='Cancel']");
    public static By saveButton=By.xpath("//button[text()='Save']");
    public static By nameOnAccError=By.id("co_bank_account_name_err");
    public static By accNameError=By.id("co_bank_name_err");
    public static By accTypeError=By.id("co_bank_accounttype_err");
    public static By accNoError=By.xpath("//div[contains(text(),'valid account number.')]");
    public static By rout9DitErr=By.xpath("//div[contains(text(),'valid routing number.')]");
    public static By makeARegPay=By.xpath("//a[text()='make a regular payment']");
    public static By firEnding=By.xpath("(//div[@class='flex-container ng-star-inserted']/p)[2]");
    public static By nextPayment=By.xpath("//div[text()='Next Payment Scheduled']/parent::div/span");
    public static By endDatePay=By.xpath("//div[text()='End Date']/parent::div/span");
    public static By payAmount=By.xpath("//div[text()='Payment Amount']/parent::div/span");
    public static By payMethod=By.xpath("//div[text()='Payment Method']/parent::div/span");
    public static By isBankPresent=By.xpath("//label[@for='bank+0']");
    public static By reviewCancel=By.xpath("//button[contains(@class,'review-schedule-cancel')]");
    public static By reviewEnroll=By.xpath("//button[contains(@class,'review-schedule-enroll-save')]");
    public static By editButton=By.xpath("//button[text()='Edit']");
    public static By stopButton=By.xpath("//button[text()='Stop']");
}