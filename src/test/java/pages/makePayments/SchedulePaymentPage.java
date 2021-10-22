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
}
