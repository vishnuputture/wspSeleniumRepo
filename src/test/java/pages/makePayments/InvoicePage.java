package pages.makePayments;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class InvoicePage extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public InvoicePage(Helper helper) {
        super(helper);
    }

    public static By headerTitleInvoice= By.xpath("//h1[text()='Invoices']");

    public static By rowItemCount=By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]");


    public static By tableInvoiceHeader=By.xpath("//div[contains(@class,'invoice-table-header-invoice-num')]");
    public static By tableInvoiceNumbers=By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]//a");

    public static By tableTotalHeader=By.xpath("//div[contains(@class,'invoice-table-header-align-right')][1]");
    public static By tableInvoiceTotal=By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]//div[7]");

}
