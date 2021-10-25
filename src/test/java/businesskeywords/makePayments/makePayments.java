package businesskeywords.makePayments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;

import commonkeywords.CommonActions;
import pages.SalesOrders.SalesOrdersPage;
import pages.makePayments.InvoicePage;
import pages.makePayments.MakePaymentLandingPage;
import pages.makePayments.SchedulePaymentPage;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
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
    	Utility_Functions.xWaitForElementPresent(driver, MakePaymentLandingPage.signIn, 5);
        commonObj.validateText(MakePaymentLandingPage.signIn,"Sign In","validating make payment dropdown");
        click(MakePaymentLandingPage.signIn);
        sendKey(MakePaymentLandingPage.userEmail,jsonData.getData("userName"));
        sendKey(MakePaymentLandingPage.userPassword,jsonData.getData("userPassword"));
        click(MakePaymentLandingPage.submitbtn);
        commonObj.validateText(MakePaymentLandingPage.makePaymentdrpdwntext,"Make Payments","validating make payment dropdown");
    }

    public void navigateToInvoice()
    {
    	Utility_Functions.xWaitForElementPresent(driver, MakePaymentLandingPage.makePaymentdrpdwntext, 5);
        Utility_Functions.xHoverElementclicks(driver.findElement(MakePaymentLandingPage.makePaymentdrpdwntext),driver);
        click(MakePaymentLandingPage.invoices);
        commonObj.validateText(InvoicePage.headerTitleInvoice,"Invoices","User in invoice Page");

    }

    public void navigateToSchedulePayments()
    {
        Utility_Functions.xHoverElementclicks(driver.findElement(MakePaymentLandingPage.makePaymentdrpdwntext),driver);
        click(MakePaymentLandingPage.schedulePayment);
        commonObj.validateText(SchedulePaymentPage.headerTitleSchedulePayments,"Scheduled Payments","User in invoice Page");

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
}
