package pages.makePayments;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class MakePaymentLandingPage extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public MakePaymentLandingPage(Helper helper) {
        super(helper);
    }

    public static By signIn= By.xpath("//a[contains(.,'Sign In')]");
            //By.xpath("//app-root//app-header//span/ul//li[3]//a[text()=' Sign In']");


    public static By userEmail=By.xpath("//input[@id='email_field']");
    public static By userPassword=By.xpath("//input[@id='si_password']");
    public static By submitbtn=By.xpath("//button[contains(text(),'Sign In')][contains(@class,'c-button--sign-in')]");
    //landing page

    public static By makePaymentdrpdwntext=By.xpath("//a[text()='Make Payments ']");

    public static By invoices=By.xpath("//a[text()='Make Payments ']/following-sibling::div//li[1]/a");

    public static By schedulePayment=By.xpath("//a[text()='Make Payments ']/following-sibling::div//li[3]/a");


}
