package businesskeywords.makePayments;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import commonkeywords.CommonActions;
import org.openqa.selenium.WebDriver;
import pages.makePayments.FixedPaymentPage;
import pages.makePayments.MakePaymentLandingPage;
import supportLibraries.Utility_Functions;

public class fixedPayments extends ReusableLib {
    CommonActions commonObj;
    WebDriver d;
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public fixedPayments(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        d=driver.getWebDriver();
    }

    public void scrollToView() {

        Utility_Functions.xScrollWindowOnce(d);

    }

    /**
     * Start of Fixed Payment Methods
     */

    public void navigateToFixedPayments()
    {
        Utility_Functions.xHoverElementclicks(driver.findElement(MakePaymentLandingPage.makePaymentdrpdwntext), driver);
        click(MakePaymentLandingPage.fixedPayment);
        Utility_Functions.timeWait(3);
        commonObj.validateText(FixedPaymentPage.headerTitleFixedPayments, "Fixed Payments", "User in Fixed Payments Page");
    }

    public void makeFixedPaymentByBA() {
        click(FixedPaymentPage.locationBalance);
        sendKey(FixedPaymentPage.inputAmount, "10");
        Utility_Functions.waitForElementVisible(driver, FixedPaymentPage.saveANDContinueBtn, 5);
        click(FixedPaymentPage.saveANDContinueBtn);
        click(FixedPaymentPage.bankAccPay);
        Utility_Functions.waitForElementVisible(driver, FixedPaymentPage.paymentSaveANDContinueBtn, 5);
        click(FixedPaymentPage.paymentSaveANDContinueBtn);
        Utility_Functions.timeWait(6);
        scrollToView();

        String text = "Your fixed-amount payment will be applied to [1] invoice.";

        String invoiceNumber = driver.findElement(FixedPaymentPage.invoiceNumber).getText();
        String invoiceDate = driver.findElement(FixedPaymentPage.invoiceDate).getText();
        String invoiceDueDate = driver.findElement(FixedPaymentPage.paymentDueDate).getText();
        String invoiceTot = driver.findElement(FixedPaymentPage.invoiceTotal).getText();

        if (text.equalsIgnoreCase(driver.findElement(FixedPaymentPage.summaryHeader).getText())) {
            report.updateTestLog("Invoice Number", invoiceNumber, Status.PASS);
            report.updateTestLog("Invoice Date", invoiceDate, Status.PASS);
            report.updateTestLog("Invoice Due Date", invoiceDueDate, Status.PASS);
            report.updateTestLog("Invoice Total", invoiceTot, Status.PASS);

        } else {
            report.updateTestLog("Invoice Summary", "Data Mismatch", Status.FAIL);
        }

        Utility_Functions.waitForElementVisible(driver, FixedPaymentPage.cntBtn, 5);
        click(FixedPaymentPage.cntBtn);
        commonObj.validateText(FixedPaymentPage.paymentTotal, "Payment Total", "Validating Total Payment");
        click(FixedPaymentPage.submitPaymentbtn);
        Utility_Functions.timeWait(4);
    }


    public void makeFixedPaymentByCC() {
        click(FixedPaymentPage.locationBalance);
        sendKey(FixedPaymentPage.inputAmount, "10");
        Utility_Functions.waitForElementVisible(driver, FixedPaymentPage.saveANDContinueBtn, 5);
        click(FixedPaymentPage.saveANDContinueBtn);

        click(FixedPaymentPage.cctab);
        click(FixedPaymentPage.ccAccPay);

        Utility_Functions.waitForElementVisible(driver, FixedPaymentPage.paymentSaveANDContinueBtn, 5);
        click(FixedPaymentPage.paymentSaveANDContinueBtn);
        Utility_Functions.timeWait(6);
        scrollToView();

        String text = "Your fixed-amount payment will be applied to [1] invoice.";

        String invoiceNumber = driver.findElement(FixedPaymentPage.invoiceNumber).getText();
        String invoiceDate = driver.findElement(FixedPaymentPage.invoiceDate).getText();
        String invoiceDueDate = driver.findElement(FixedPaymentPage.paymentDueDate).getText();
        String invoiceTot = driver.findElement(FixedPaymentPage.invoiceTotal).getText();

        if (text.equalsIgnoreCase(driver.findElement(FixedPaymentPage.summaryHeader).getText())) {
            report.updateTestLog("Invoice Number", invoiceNumber, Status.PASS);
            report.updateTestLog("Invoice Date", invoiceDate, Status.PASS);
            report.updateTestLog("Invoice Due Date", invoiceDueDate, Status.PASS);
            report.updateTestLog("Invoice Total", invoiceTot, Status.PASS);

        } else {
            report.updateTestLog("Invoice Summary", "Data Mismatch", Status.FAIL);
        }

        Utility_Functions.waitForElementVisible(driver, FixedPaymentPage.cntBtn, 5);
        click(FixedPaymentPage.cntBtn);
        commonObj.validateText(FixedPaymentPage.paymentTotal, "Payment Total", "Validating Total Payment");
        click(FixedPaymentPage.submitPaymentbtn);
        Utility_Functions.timeWait(10);
    }



    public void fixedPayConfirmationCC()
    {
        Utility_Functions.waitForElementVisible(driver,FixedPaymentPage.paymentConfirmationHeader,5);

        String fixedNo=driver.findElement(FixedPaymentPage.fixedConfirmationNumber).getText();

        Utility_Functions.xUpdateJson("FixedPaymntConfirmNoCC",fixedNo);

        if(fixedNo!="")
        {
            report.updateTestLog("Payment Confirmation Number",fixedNo,Status.PASS);
        }else
        {
            report.updateTestLog("Payment Confirmation Number",fixedNo,Status.FAIL);
        }

    }

    public void fixedPayConfirmationBA()
    {
        Utility_Functions.waitForElementVisible(driver,FixedPaymentPage.paymentConfirmationHeader,5);

        String fixedNo=driver.findElement(FixedPaymentPage.fixedConfirmationNumber).getText();

        Utility_Functions.xUpdateJson("FixedPaymntConfirmNoBA",fixedNo);

        if(fixedNo!="")
        {
            report.updateTestLog("Payment Confirmation Number",fixedNo,Status.PASS);
        }else
        {
            report.updateTestLog("Payment Confirmation Number",fixedNo,Status.FAIL);
        }

    }
}
