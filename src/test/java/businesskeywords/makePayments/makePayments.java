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
import pages.makePayments.MakeAPaymentPage;
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
        Utility_Functions.xWaitForElementClickable(driver,MakePaymentLandingPage.signIn,10);
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

    public void sortInvoiceDiscount()
    {
        for(int i=0;i<2;i++) {

            click(InvoicePage.tableDiscountHeader);
            List<WebElement> invoiceList = driver.findElements(InvoicePage.tableInvoiceDiscount);

            for(int j=0;j<invoiceList.size();j++)
            {
                String temp= invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k=j+1;

                String inv= driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//div[8]")).getText();
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

    public void sortInvoiceAmtDue()
    {
        for(int i=0;i<2;i++) {

            click(InvoicePage.tableAmtDueHeader);
            List<WebElement> invoiceList = driver.findElements(InvoicePage.tableInvoiceAmtDue);

            for(int j=0;j<invoiceList.size();j++)
            {
                String temp= invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k=j+1;

                String inv= driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//div[8]")).getText();
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

    public void sortInvoiceDate()
    {
        for(int i=0;i<2;i++) {

            click(InvoicePage.tableDateHeader);
            List<WebElement> invoiceList = driver.findElements(InvoicePage.tableInvoiceDate);

            for(int j=0;j<invoiceList.size();j++)
            {
                String temp= invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k=j+1;

                String inv= driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//div[5]")).getText();
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

    public void sortInvoicePaymentDue()
    {
        for(int i=0;i<2;i++) {

            click(InvoicePage.tableDateHeader);
            List<WebElement> invoiceList = driver.findElements(InvoicePage.tableInvoiceDate);

            for(int j=0;j<invoiceList.size();j++)
            {
                String temp= invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k=j+1;

                String inv= driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//div[5]")).getText();
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

    public void makeSinglePaymentBA()
    {
            click(InvoicePage.supplierCheckBox);

            List<WebElement> invoiceCheckBoxCount = driver.findElements(InvoicePage.invoiceCheckBox);

            for(int j=0;j<1;j++)
            {
                int k=j+1;
                driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//label")).click();
            }

            click(InvoicePage.makePaymentBtn);
            Utility_Functions.timeWait(3);
            commonObj.validateText(MakeAPaymentPage.pageTitle,"Make a Payment","User navigated To Make a Payment Page");
       //     commonObj.validateText(MakeAPaymentPage.paymentAmount,"20.00","Amount Matches");
            click(MakeAPaymentPage.bankAccPay);
            click((MakeAPaymentPage.saveContinuebtn));
            Utility_Functions.timeWait(2);
            click(MakeAPaymentPage.submitPaymentbtn);
            Utility_Functions.timeWait(3);
            commonObj.validateText(MakeAPaymentPage.headerPaymentConfirmation,"Payment Confirmation","Payment Successful");
            scrollToView();
            String txt=driver.findElement(MakeAPaymentPage.confirmationNumber).getText();
            Utility_Functions.xUpdateJson("MPInvoiceNumberBA",txt);


        }

    public void makeSinglePaymentCC()
    {
        click(InvoicePage.supplierCheckBox);

        List<WebElement> invoiceCheckBoxCount = driver.findElements(InvoicePage.invoiceCheckBox);

        for(int j=0;j<1;j++)
        {
            int k=j+1;
            driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//label")).click();
        }

        click(InvoicePage.makePaymentBtn);
        Utility_Functions.timeWait(3);
        commonObj.validateText(MakeAPaymentPage.pageTitle,"Make a Payment","User navigated To Make a Payment Page");
        //     commonObj.validateText(MakeAPaymentPage.paymentAmount,"20.00","Amount Matches");

        click(MakeAPaymentPage.cctab);
        click(MakeAPaymentPage.ccAccPay);
        click((MakeAPaymentPage.saveContinuebtn));
        Utility_Functions.timeWait(2);
        click(MakeAPaymentPage.submitPaymentbtn);
        Utility_Functions.timeWait(3);
        commonObj.validateText(MakeAPaymentPage.headerPaymentConfirmation,"Payment Confirmation","Payment Successful");
        scrollToView();
        String txt=driver.findElement(MakeAPaymentPage.confirmationNumber).getText();
        Utility_Functions.xUpdateJson("MPInvoiceNumberCC",txt);


    }

    public void makeMultiPaymentBA()
    {
        click(InvoicePage.supplierCheckBox);

        List<WebElement> invoiceCheckBoxCount = driver.findElements(InvoicePage.invoiceCheckBox);
        int count= Integer.parseInt(jsonData.getData("invoiceCount"));
        for(int j=0;j<count;j++)
        {
            int k=j+1;
            driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//label")).click();
        }

        click(InvoicePage.makePaymentBtn);
        Utility_Functions.timeWait(3);
        commonObj.validateText(MakeAPaymentPage.pageTitle,"Make a Payment","User navigated To Make a Payment Page");
        //     commonObj.validateText(MakeAPaymentPage.paymentAmount,"20.00","Amount Matches");
        click(MakeAPaymentPage.bankAccPay);
        click((MakeAPaymentPage.saveContinuebtn));
        Utility_Functions.timeWait(2);
        click(MakeAPaymentPage.submitPaymentbtn);
        Utility_Functions.timeWait(3);
        commonObj.validateText(MakeAPaymentPage.headerPaymentConfirmation,"Payment Confirmation","Payment Successful");
        scrollToView();
        String txt=driver.findElement(MakeAPaymentPage.confirmationNumber).getText();
        Utility_Functions.xUpdateJson("MPInvoiceNumberBA",txt);


    }






}
