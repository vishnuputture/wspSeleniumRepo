package businesskeywords.makePayments;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.JavascriptExecutor;

import pages.makePayments.InvoicePage;
import pages.makePayments.MakePaymentLandingPage;
import pages.makePayments.SchedulePaymentPage;
import pages.pricing.SpecialPricePage;
import supportLibraries.Utility_Functions;

import java.util.List;

public class makePayments extends ReusableLib {

    CommonActions commonObj;
    WebDriver d;
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public makePayments(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        d=driver.getWebDriver();
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

    public void navigateToSchedulePayments()
    {
        Utility_Functions.xHoverElementclicks(driver.findElement(MakePaymentLandingPage.makePaymentdrpdwntext),driver);
        click(MakePaymentLandingPage.schedulePayment);
        commonObj.validateText(SchedulePaymentPage.headerTitleSchedulePayments,"Scheduled Payments","User in Scheduled Payments Page");
    }

    public void clickAddNewSchedulePayment(){
        click(SchedulePaymentPage.addNewPayment,"Click Add New Schedule Payment Button");
    }

    public void clickDisagree(){
        click(SchedulePaymentPage.disagreeExit,"Click DisAgree & Exit");
    }

    public void clickAgree(){
        click(SchedulePaymentPage.agreeEnrol,"Click Agree & Enroll");
    }

    public void disablingTermsConditionAndEnroll(){
        clickAddNewSchedulePayment();
        clickAgree();
        commonObj.validateText(SchedulePaymentPage.errorTerms,"Please agree to the Terms of Use and Terms and Conditions of Sale","Error Message");
    }

    public void disablingTermsConditionAndExit(){
        clickAddNewSchedulePayment();
        clickDisagree();
        commonObj.validateText(SchedulePaymentPage.invoiceTitle, "Invoices", "Validating title of Invoices Page");
    }

    public void clickHereLink(){
        click(SchedulePaymentPage.clickHere,"Click on click here link");
        commonObj.validateText(SchedulePaymentPage.invoiceTitle, "Invoices", "Validating title of Invoices Page");
    }

    public void scrollToView()
    {

        Utility_Functions.xScrollWindowOnce(d);

    }

    public void sortInvoiceNumber()
    {
        for(int i=0;i<2;i++) {

            click(InvoicePage.tableInvoiceHeader);
            List<WebElement> invoiceList = driver.findElements(InvoicePage.tableInvoiceNumbers);

            for(int j=0;j<invoiceList.size();j++)
            {
                String temp= invoiceList.get(j).getText();
       //         System.out.println(temp);
                int k=j+1;
               String inv= driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//a")).getText();
      //         System.out.println(inv);
               if(temp.equalsIgnoreCase(inv))
               {
                   report.updateTestLog("Matches Invoice number",inv, Status.PASS);
               }else
               {
                   report.updateTestLog(" Invoice number doesnt match",inv, Status.FAIL);
               }
            }
        }

    }

    public void sortInvoiceTotal()
    {
        for(int i=0;i<2;i++) {

            click(InvoicePage.tableTotalHeader);
            List<WebElement> invoiceList = driver.findElements(InvoicePage.tableInvoiceTotal);

            for(int j=0;j<invoiceList.size();j++)
            {
                String temp= invoiceList.get(j).getText();
        //        System.out.println(temp);
                int k=j+1;

                String inv= driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//div[7]")).getText();
       //         System.out.println(inv);
                if(temp.equalsIgnoreCase(inv))
                {
                    report.updateTestLog("Matches Invoice Total",inv, Status.PASS);
                }else
                {
                    report.updateTestLog(" Invoice Total Doesnt Match",inv, Status.FAIL);
                }
            }
        }

    }
}
