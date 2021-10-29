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
    public static By invoiceStatusDropDown = By.id("invoice-status-select");
    public static By invoiceDatesDropDown = By.id("invoice-dates-select");
    public static By txtBoxInvoiceSearch = By.id("invoice-search");
    public static By btnTableExpand = By.xpath("//i[@class='fa fa-plus ng-star-inserted']");
    public static By btnTableContract = By.xpath("//i[@class='fa fa-minus ng-star-inserted']");
    public static By txtRecordCount = By.xpath("//span[@class='win-invoice-table-supplier-label-selected-invoices']");
    public static By lblNoRecords = By.xpath("//div[contains(text(),' No records match your search.')]");
    public static By recordRow = By.xpath("//div[@class='win-invoice-table-supplier-open-item win-highlight-row ng-star-inserted']");
    public static By dateFilterTo = By.xpath("//span[text()='To:']/following-sibling::input");
    public static By dateFilterFrom = By.xpath("//span[text()='From:']/following-sibling::input");
    public static By dateFilterApply = By.xpath("//span[text()='Apply']");


}
