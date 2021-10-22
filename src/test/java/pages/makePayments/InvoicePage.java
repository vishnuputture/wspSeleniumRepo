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
}
