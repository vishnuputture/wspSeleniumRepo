package pages.makePayments;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class FixedPaymentPage extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public FixedPaymentPage(Helper helper) {
        super(helper);
    }

    public static By headerTitleFixedPayments = By.xpath("//h1[text()='Fixed Payments']");
    public static By locationBalance = By.xpath("//div[contains(@class,'supplier-div')]//span[text()='Location Balance']");
    public static By inputAmount = By.xpath("//input[@id='saPayAmount']");
    public static By saveANDContinueBtn = By.xpath("//button[text()='Save & Continue']");
    public static By bankAccPay = By.xpath("//div[@class='tabs-container']//input[@id='bank+0']/following-sibling::label");

    //CC Payment

    public static By cctab = By.xpath("//li[contains(text(),' Credit Card ')]");
    public static By ccAccPay = By.xpath("//div[@class='tabs-container']//input[@id='credit_1']/following-sibling::label");


    public static By paymentSaveANDContinueBtn = By.xpath("//button[contains(.,'Save and Continue')]");

    public static By summaryHeader = By.xpath("//div[contains(@class,'invoice-summary-main-text')]");

    public static By invoiceNumber = By.xpath("//div[@class='invoice-detail-row'][1]/span");
    public static By invoiceDate = By.xpath("//div[@class='invoice-detail-row'][2]/span");
    public static By paymentDueDate = By.xpath("//div[@class='invoice-detail-row'][3]/span");
    public static By invoiceTotal = By.xpath("//div[@class='invoice-detail-row'][4]/span");

    public static By cntBtn = By.xpath("//button[text()='CONTINUE']");

    public static By paymentTotal = By.xpath("//p[text()='Payment Total']");
    public static By submitPaymentbtn = By.xpath("//button[text()='Submit Payment']");

    //Payment Confirmation Page

    public static By paymentConfirmationHeader = By.xpath("//h1");
    public static By fixedConfirmationNumber = By.xpath("//section//div[@class='flex-item desktop'][2]/span");


}
