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

import org.openqa.selenium.interactions.Actions;
import pages.makePayments.InvoicePage;
import pages.makePayments.MakePaymentLandingPage;
import pages.makePayments.SchedulePaymentPage;
import pages.pricing.SpecialPricePage;
import supportLibraries.Utility_Functions;

import javax.print.DocFlavor;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

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
        d = driver.getWebDriver();
    }

    public void logInToMakePayments() {
        commonObj.validateText(MakePaymentLandingPage.signIn, "Sign In", "validating make payment dropdown");
        click(MakePaymentLandingPage.signIn);
        sendKey(MakePaymentLandingPage.userEmail, jsonData.getData("userName"));
        sendKey(MakePaymentLandingPage.userPassword, jsonData.getData("userPassword"));
        click(MakePaymentLandingPage.submitbtn);
        commonObj.validateText(MakePaymentLandingPage.makePaymentdrpdwntext, "Make Payments", "validating make payment dropdown");
    }

    public void navigateToInvoice() {
        Utility_Functions.xHoverElementclicks(driver.findElement(MakePaymentLandingPage.makePaymentdrpdwntext), driver);
        click(MakePaymentLandingPage.invoices);
        commonObj.validateText(InvoicePage.headerTitleInvoice, "Invoices", "User in invoice Page");

    }

    /*************************************----Payment Schedule----***********************************************/

    public void navigateToSchedulePayments() {
        Utility_Functions.xHoverElementclicks(driver.findElement(MakePaymentLandingPage.makePaymentdrpdwntext), driver);
        click(MakePaymentLandingPage.schedulePayment);
        commonObj.validateText(SchedulePaymentPage.headerTitleSchedulePayments, "Scheduled Payments", "User in Scheduled Payments Page");
    }

    public void clickAddNewSchedulePayment() {
        click(SchedulePaymentPage.addNewPayment, "Click Add New Schedule Payment Button");
    }

    public void clickDisagree() {
        click(SchedulePaymentPage.disagreeExit, "Click DisAgree & Exit");
    }

    public void clickAgree() {
        click(SchedulePaymentPage.agreeEnrol, "Click Agree & Enroll");
    }

    public void disablingTermsConditionAndEnroll() {
        clickAddNewSchedulePayment();
        clickAgree();
        commonObj.validateText(SchedulePaymentPage.errorTerms, "Please agree to the Terms of Use and Terms and Conditions of Sale", "Error Message");
    }

    public void disablingTermsConditionAndExit() {
        clickAddNewSchedulePayment();
        clickDisagree();
        commonObj.validateText(SchedulePaymentPage.invoiceTitle, "Invoices", "Validating title of Invoices Page");
    }

    public void enableCheckBoxAndEnroll() {
        clickAddNewSchedulePayment();
        Utility_Functions.xmouseOver(driver, SchedulePaymentPage.checkBox);
        Utility_Functions.xMouseClick(driver, SchedulePaymentPage.checkBox);
        clickAgree();
        commonObj.validateText(SchedulePaymentPage.scheduleTab, "Schedule", "Validating title of scheduleTab Page");
    }

    public void enableCheckBoxAndExit() {
        clickAddNewSchedulePayment();
        Utility_Functions.xmouseOver(driver, SchedulePaymentPage.checkBox);
        Utility_Functions.xMouseClick(driver, SchedulePaymentPage.checkBox);
        clickDisagree();
        commonObj.validateText(SchedulePaymentPage.invoiceTitle, "Invoices", "Validating title of Invoices Page");
    }

    public void startDateValidation() {
        commonObj.validateText(SchedulePaymentPage.calenderTab, "Start date", "Validating Start Date & End Date Tab");
        Utility_Functions.xScrollIntoView(driver, SchedulePaymentPage.startDate);
        click(SchedulePaymentPage.startDate, "Click Start Date");
        Utility_Functions.timeWait(3);
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        String month = currentMonth.toString().toLowerCase();
        String actCurrentMonth = Utility_Functions.getText(driver, SchedulePaymentPage.calenderDatePicker).toLowerCase();
        if (actCurrentMonth.contains(month)) {
            click(SchedulePaymentPage.calenderArrPicker, "click < icon from calender");
            Utility_Functions.timeWait(2);
            commonObj.validateText(SchedulePaymentPage.calenderDatePicker, actCurrentMonth, "Not Move to Previous Month");
            driver.findElements(SchedulePaymentPage.calenderArrPicker).get(1).click();
            Utility_Functions.timeWait(2);
            String actNextMonth = Utility_Functions.getText(driver, SchedulePaymentPage.calenderDatePicker).toLowerCase();
            if (actNextMonth.contains(month)) {
                throw new NoSuchElementException("Calender not moved");
            } else {
                click(SchedulePaymentPage.calenderArrPicker);
            }
            int size = driver.findElements(SchedulePaymentPage.dateUnSelect).size();
            int length = driver.findElements(SchedulePaymentPage.totalDay).size();
            driver.findElements(SchedulePaymentPage.calenderArrPicker).get(1).click();
            Utility_Functions.timeWait(2);
            int length1 = driver.findElements(SchedulePaymentPage.totalDay).size();
            driver.findElements(SchedulePaymentPage.calenderArrPicker).get(1).click();
            Utility_Functions.timeWait(2);
            int size1 = driver.findElements(SchedulePaymentPage.dateUnSelect).size();
            int length2 = driver.findElements(SchedulePaymentPage.totalDay).size();
            int totalSize = size + size1;
            int totalLength = length + length1 + length2;
            int total = totalLength - totalSize;
            if (total == 60) {
                commonObj.validateText(SchedulePaymentPage.scheduleTab, "Schedule", "Calender showing 60 dates which are in Enable State");
            } else {
                throw new NoSuchElementException("Calender having invalid data");
            }
        } else {
            throw new NoSuchElementException("Element not found");
        }
    }

    public void endDateValidation() {
        Utility_Functions.timeWait(2);
        Utility_Functions.xScrollIntoView(driver, SchedulePaymentPage.endDateTab);
        click(SchedulePaymentPage.endDateTab);
        click(SchedulePaymentPage.endDate, "Click End Date");
        Utility_Functions.timeWait(3);
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        String month = currentMonth.toString().toLowerCase();
        int date = currentDate.getDayOfMonth();
        int nextDate = date + 2;
        System.out.println("nextDate..." + nextDate);
        String actCurrentMonth = Utility_Functions.getText(driver, SchedulePaymentPage.calenderDatePicker).toLowerCase();
        if (actCurrentMonth.contains(month)) {
            click(SchedulePaymentPage.calenderArrPicker, "click < icon from calender");
            Utility_Functions.timeWait(2);
            commonObj.validateText(SchedulePaymentPage.calenderDatePicker, actCurrentMonth, "Not Move to Previous Month");
            driver.findElements(SchedulePaymentPage.calenderArrPicker).get(1).click();
            Utility_Functions.timeWait(2);
            String actNextMonth = Utility_Functions.getText(driver, SchedulePaymentPage.calenderDatePicker).toLowerCase();
            if (actNextMonth.contains(month)) {
                throw new NoSuchElementException("Calender not moved");
            } else {
                click(SchedulePaymentPage.calenderArrPicker);
            }
            int size = driver.findElements(SchedulePaymentPage.dateUnSelect).size();
            System.out.println("size..."+size);
            int actSize=size+1;
            Utility_Functions.xAssertEquals(report,actSize,nextDate,"Current day disabled and day after Tomorrow is enabled ");
        } else {
            throw new NoSuchElementException("Element not found");
        }
    }

    public void verifyCalenderFunction() {
        enableCheckBoxAndEnroll();
        Utility_Functions.timeWait(3);
        click(SchedulePaymentPage.choosePaySchedule, "Click Choose Payment Schedule Drop Down");
        click(SchedulePaymentPage.weeklySchedule, "Click Schedule: Weekly option");
        startDateValidation();
        endDateValidation();
    }

    public void clickHereLink() {
        click(SchedulePaymentPage.clickHere, "Click on click here link");
        commonObj.validateText(SchedulePaymentPage.invoiceTitle, "Invoices", "Validating title of Invoices Page");
    }

    /*********************************-----Payment Schedule END------*************************************************************************/

    public void scrollToView() {

        Utility_Functions.xScrollWindowOnce(d);

    }

    public void sortInvoiceNumber() {
        for (int i = 0; i < 2; i++) {

            click(InvoicePage.tableInvoiceHeader);
            List<WebElement> invoiceList = driver.findElements(InvoicePage.tableInvoiceNumbers);

            for (int j = 0; j < invoiceList.size(); j++) {
                String temp = invoiceList.get(j).getText();
                //         System.out.println(temp);
                int k = j + 1;
                String inv = driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]" + "[" + k + "]" + "//a")).getText();
                //         System.out.println(inv);
                if (temp.equalsIgnoreCase(inv)) {
                    report.updateTestLog("Matches Invoice number", inv, Status.PASS);
                } else {
                    report.updateTestLog(" Invoice number doesnt match", inv, Status.FAIL);
                }
            }
        }

    }

    public void sortInvoiceTotal() {
        for (int i = 0; i < 2; i++) {

            click(InvoicePage.tableTotalHeader);
            List<WebElement> invoiceList = driver.findElements(InvoicePage.tableInvoiceTotal);

            for (int j = 0; j < invoiceList.size(); j++) {
                String temp = invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k = j + 1;

                String inv = driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]" + "[" + k + "]" + "//div[7]")).getText();
                //         System.out.println(inv);
                if (temp.equalsIgnoreCase(inv)) {
                    report.updateTestLog("Matches Invoice Total", inv, Status.PASS);
                } else {
                    report.updateTestLog(" Invoice Total Doesnt Match", inv, Status.FAIL);
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
}
