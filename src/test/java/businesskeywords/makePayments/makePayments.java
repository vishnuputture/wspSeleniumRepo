package businesskeywords.makePayments;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import pages.makePayments.InvoicePage;
import pages.makePayments.MakePaymentLandingPage;
import supportLibraries.Utility_Functions;

public class makePayments extends ReusableLib {

    CommonActions commonObj;
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public makePayments(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    public void logInToMakePayments()
    {
        commonObj.validateText(MakePaymentLandingPage.signIn,"Sign In","validating make payment dropdown");
        click(MakePaymentLandingPage.signIn);
        sendKey(MakePaymentLandingPage.userEmail,jsonData.getData("userName"));
        sendKey(MakePaymentLandingPage.userPassword,jsonData.getData("userPassword"));
        click(MakePaymentLandingPage.submitbtn);
        commonObj.validateText(MakePaymentLandingPage.makePaymentdrpdwntext,"Make Payments","validating make payment dropdown");
    }

    public void navigateToInvoice()
    {
        Utility_Functions.xHoverElementclicks(driver.findElement(MakePaymentLandingPage.makePaymentdrpdwntext),driver);
        click(MakePaymentLandingPage.invoices);
        commonObj.validateText(InvoicePage.headerTitleInvoice,"Invoices","User in invoice Page");

    }
}
