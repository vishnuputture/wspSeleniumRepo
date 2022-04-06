package businesskeywords.makePayments;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.avro.generic.GenericData;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;

import commonkeywords.CommonActions;
import pages.SalesOrders.SalesOrdersPage;

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
import pages.makePayments.*;

import pages.pricing.PriceSheet.SelfServicePriceSheetPage;

import pages.pricing.SpecialPricePage;

import software.amazon.awssdk.services.cloudfront.model.TooManyQueryStringsInCachePolicyException;
import supportLibraries.Utility_Functions;

import javax.print.DocFlavor;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
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
        d=driver.getWebDriver();
    }

    public void logInToMakePayments()
    {
        Utility_Functions.xWaitForElementClickable(driver,MakePaymentLandingPage.signIn,10);
        Utility_Functions.timeWait(4);
        commonObj.validateText(MakePaymentLandingPage.signIn,"Sign In","validating make payment dropdown");
        click(MakePaymentLandingPage.signIn);
        sendKey(MakePaymentLandingPage.userEmail,jsonData.getData("userName"));
        sendKey(MakePaymentLandingPage.userPassword,jsonData.getData("userPassword"));
        click(MakePaymentLandingPage.submitbtn);
        commonObj.validateText(MakePaymentLandingPage.makePaymentdrpdwntext,"Make Payments","validating make payment dropdown");
        Utility_Functions.timeWait(3);
    }

    public void navigateToInvoice()
    {
    	Utility_Functions.xWaitForElementPresent(driver, MakePaymentLandingPage.makePaymentdrpdwntext, 5);
        Utility_Functions.xHoverElementclicks(driver.findElement(MakePaymentLandingPage.makePaymentdrpdwntext),driver);
        click(MakePaymentLandingPage.invoices);
        commonObj.validateText(InvoicePage.headerTitleInvoice,"Invoices","User in invoice Page");

    }



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

    public void sortInvoiceDiscount() {
        for (int i = 0; i < 2; i++) {

            click(InvoicePage.tableDiscountHeader);
            List<WebElement> invoiceList = driver.findElements(InvoicePage.tableInvoiceDiscount);

            for (int j = 0; j < invoiceList.size(); j++) {
                String temp = invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k = j + 1;

                String inv = driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]" + "[" + k + "]" + "//div[8]")).getText();
                //         System.out.println(inv);
                if (temp.equalsIgnoreCase(inv)) {
                    report.updateTestLog("Matches Invoice Total", inv, Status.PASS);
                } else {
                    report.updateTestLog(" Invoice Total Doesnt Match", inv, Status.FAIL);
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


    public void validateInvoiceStatusFilter() {
    	int statusCount = Utility_Functions.xGetSelectOptionCount(driver,driver.findElement(MakePaymentLandingPage.invoiceStatusDropDown));
    	for(int i=0 ;i<statusCount;i++ ) {
    		Utility_Functions.xSelectDropdownByIndex(driver,driver.findElement(MakePaymentLandingPage.invoiceStatusDropDown),i);
    		Utility_Functions.timeWait(2);
    		if(!Utility_Functions.isExist(driver, MakePaymentLandingPage.lblNoRecords)) {
    		click(MakePaymentLandingPage.btnTableExpand);
    		Utility_Functions.timeWait(4);
    		String temp = driver.findElement(MakePaymentLandingPage.txtRecordCount).getText().split("of")[0].trim().substring(1);
    		int displayCount = 0;
    		for(WebElement e :driver.findElements(MakePaymentLandingPage.recordRow) ) {
    			if(e.getAttribute("hidden")==null) {
    				displayCount++;
    			}
    		}
    		//System.out.println("count :"+temp.split("of")[0].trim().substring(1));
    		if(String.valueOf(displayCount).equals(temp)) {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+String.valueOf(displayCount), Status.PASS);
    		}else {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+String.valueOf(displayCount), Status.FAIL);
    		}
    		}
    	}
    }

    public void validateInvoiceDateFilter() {
    	Utility_Functions.xSelectDropdownByVisibleText(driver, MakePaymentLandingPage.invoiceStatusDropDown, "All");
    	int statusCount = Utility_Functions.xGetSelectOptionCount(driver,driver.findElement(MakePaymentLandingPage.invoiceDatesDropDown));
    	for(int i=0 ;i<statusCount-1;i++ ) {
    		Utility_Functions.xSelectDropdownByIndex(driver,driver.findElement(MakePaymentLandingPage.invoiceDatesDropDown),i);
    		Utility_Functions.timeWait(2);
    		if(!Utility_Functions.isExist(driver, MakePaymentLandingPage.lblNoRecords)) {
    		click(MakePaymentLandingPage.btnTableExpand);
    		Utility_Functions.timeWait(4);
    		String temp = driver.findElement(MakePaymentLandingPage.txtRecordCount).getText().split("of")[0].trim().substring(1);
    		//System.out.println("count :"+temp.split("of")[0].trim().substring(1));
    		int displayCount = 0;
    		for(WebElement e :driver.findElements(MakePaymentLandingPage.recordRow) ) {
    			if(e.getAttribute("hidden")==null) {
    				displayCount++;
    			}
    		}
    		if(String.valueOf(displayCount).equals(temp)) {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+String.valueOf(displayCount), Status.PASS);
    		}else {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+String.valueOf(displayCount), Status.FAIL);
    		}
    		click(MakePaymentLandingPage.btnTableContract);
    		}
    	}
    }

    public void validateInvoiceCustomDateFilter() {
    	Utility_Functions.xSelectDropdownByVisibleText(driver, MakePaymentLandingPage.invoiceStatusDropDown, "All");
    	Utility_Functions.xSelectDropdownByVisibleText(driver, MakePaymentLandingPage.invoiceDatesDropDown, "Custom");

    	Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		//c.add(Calendar.DATE, 1);
		dt = c.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		String strDate= formatter.format(dt);
		Utility_Functions.timeWait(2);
		sendKeys(MakePaymentLandingPage.dateFilterTo,strDate,"Entering to date");
		c.add(Calendar.DATE, -2);
		dt = c.getTime();
		strDate= formatter.format(dt);
		Utility_Functions.timeWait(2);
		sendKeys(MakePaymentLandingPage.dateFilterFrom,strDate,"Entering from date");

		click(MakePaymentLandingPage.dateFilterApply);
		Utility_Functions.timeWait(2);
		if(!Utility_Functions.isExist(driver, MakePaymentLandingPage.lblNoRecords)) {
			click(MakePaymentLandingPage.btnTableExpand);
    		Utility_Functions.timeWait(4);
    		String temp = driver.findElement(MakePaymentLandingPage.txtRecordCount).getText().split("of")[0].trim().substring(1);
    		//System.out.println("count :"+temp.split("of")[0].trim().substring(1));
    		int displayCount = 0;
    		for(WebElement e :driver.findElements(MakePaymentLandingPage.recordRow) ) {
    			if(e.getAttribute("hidden")==null) {
    				displayCount++;
    			}
    		}
    		//if(String.valueOf(driver.findElements(MakePaymentLandingPage.recordRow).size()).equals(temp)) {
    		if(String.valueOf(displayCount).equals(temp)) {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+displayCount, Status.PASS);
    		}else {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+displayCount, Status.FAIL);
    		}
		}
    }

    public void validateInvoiceSearchFilter() {
    	Utility_Functions.xSelectDropdownByVisibleText(driver, MakePaymentLandingPage.invoiceStatusDropDown, "All");
    	//Utility_Functions.xSelectDropdownByVisibleText(driver, MakePaymentLandingPage.invoiceDatesDropDown, "Custom");
    	sendKeys(MakePaymentLandingPage.txtBoxInvoiceSearch,Utility_Functions.xGetJsonData("CreatedSalesOrder"),"Entering filter data");
    	Utility_Functions.actionKey(Keys.ENTER, driver);

    	Utility_Functions.timeWait(2);
		if(!Utility_Functions.isExist(driver, MakePaymentLandingPage.lblNoRecords)) {
			click(MakePaymentLandingPage.btnTableExpand);
    		Utility_Functions.timeWait(4);
    		String temp = driver.findElement(MakePaymentLandingPage.txtRecordCount).getText().split("of")[0].trim().substring(1);
    		//System.out.println("count :"+temp.split("of")[0].trim().substring(1));
    		int displayCount = 0;
    		for(WebElement e :driver.findElements(MakePaymentLandingPage.recordRow) ) {
    			if(e.getAttribute("hidden")==null) {
    				displayCount++;
    			}
    		}
    		//if(String.valueOf(driver.findElements(MakePaymentLandingPage.recordRow).size()).equals(temp)) {
    		if(String.valueOf(displayCount).equals(temp)) {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+displayCount, Status.PASS);
    		}else {
    			report.updateTestLog("Record validation", "Expected "+temp+" received "+displayCount, Status.FAIL);
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

    public void makeMultiPaymentBA() {
        click(InvoicePage.supplierCheckBox);

        List<WebElement> invoiceCheckBoxCount = driver.findElements(InvoicePage.invoiceCheckBox);
        int count = Integer.parseInt(jsonData.getData("invoiceCount"));
        for (int j = 0; j < count; j++) {
            int k = j + 1;
            driver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]" + "[" + k + "]" + "//label")).click();
        }

        click(InvoicePage.makePaymentBtn);
        Utility_Functions.timeWait(3);
        commonObj.validateText(MakeAPaymentPage.pageTitle, "Make a Payment", "User navigated To Make a Payment Page");
        //     commonObj.validateText(MakeAPaymentPage.paymentAmount,"20.00","Amount Matches");
        click(MakeAPaymentPage.bankAccPay);
        click((MakeAPaymentPage.saveContinuebtn));
        Utility_Functions.timeWait(2);
        click(MakeAPaymentPage.submitPaymentbtn);
        Utility_Functions.timeWait(3);
        commonObj.validateText(MakeAPaymentPage.headerPaymentConfirmation, "Payment Confirmation", "Payment Successful");
        scrollToView();
        String txt = driver.findElement(MakeAPaymentPage.confirmationNumber).getText();
        Utility_Functions.xUpdateJson("MPInvoiceNumberBA", txt);
    }




    
   

    }



