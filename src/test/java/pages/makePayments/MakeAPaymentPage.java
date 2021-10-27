package pages.makePayments;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class MakeAPaymentPage extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public MakeAPaymentPage(Helper helper) {
        super(helper);
    }

    public static By pageTitle= By.xpath("//h1[contains(.,'Make a Payment')]");

    public static By paymentAmount= By.xpath("//payment-amount//p");

    public static By bankAccPay=By.xpath("//div[@class='tabs-container']//input[@id='bank+0']/following-sibling::label");

    public static By saveContinuebtn= By.xpath("//button[contains(.,'Save and Continue')]");

    public static By submitPaymentbtn=By.xpath("//button[contains(.,'Submit Payment')]");

    public static By headerPaymentConfirmation=By.xpath("//h1[@class='win-h1-page-title'][contains(.,' Payment Confirmation')]");

    public static By confirmationNumber=By.xpath("//section//div[@class='flex-item desktop'][2]/span");

}
