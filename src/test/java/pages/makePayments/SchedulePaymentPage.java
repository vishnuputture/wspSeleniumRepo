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
    public static By checkBox=By.xpath("//label[@for='userAgreedOne']");
    public static By clickHere=By.xpath("//a[text()='click here']");
    public static By agreeEnrol=By.xpath("//button[text()='Agree & Enroll']");
    public static By disagreeExit=By.xpath("//button[text()='Disagree & Exit']");
    public static By scheduleTab=By.xpath("//h2[text()='Schedule']");
    public static By errorTerms=By.xpath("//span[@class='agree-error ng-star-inserted']");
    public static By invoiceTitle=By.xpath("//h1[text()='Invoices']");

}