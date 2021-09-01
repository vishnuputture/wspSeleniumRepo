package businesskeywords.Pricing;

import businesskeywords.SalesQuotes.WorkWithSalesQuotes.WorkWithSalesQuote;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import pages.pricing.OrderByCustomerPage;
import pages.pricing.ReportsMenu;
import supportLibraries.Utility_Functions;

public class ReportMenu extends ReusableLib {
    CommonActions commonObj=new CommonActions(helper);
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public ReportMenu(Helper helper) {
        super(helper);
    }

    /**
     * This method navigate To Print Invoices Page
     *
     */
    public void navigateToPrintInvoicesProgram() {
        click(ReportsMenu.reportMenu,"Click Reports Menu");
        click(ReportsMenu.printInvoices,"Click Print Invoices");
    }

    /**
     * This method to create Invoices
     *
     */
    public void createInvoices() {
        sendKeys(ReportsMenu.enterSel,"I");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        sendKeys(ReportsMenu.KeyNo1, WorkWithSalesQuote.salesOrder);
        sendKeys(ReportsMenu.KeyNo2, "01");
        Utility_Functions.actionKey(Keys.F1, driver);
        System.out.println("Invoice Done......");
    }

    /**
     * This method exit from Report Menu
     *
     */
    public void exitReportMenu() {
        click(ReportsMenu.exitButton);
        click(ReportsMenu.exitButton,"exit from Report Menu");
    }
}
