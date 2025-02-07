package businesskeywords.AccountReceivable.MakePayments;


import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.AccountReceivable.makePayments.InvoicePage;
import pages.AccountReceivable.makePayments.MakeAPaymentPage;
import pages.AccountReceivable.makePayments.MakePaymentLandingPage;
import pages.warehouse.ElectronicCommerce.ElectronicCommercePage;
import supportLibraries.Utility_Functions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class makePayments extends ReusableLib {

    CommonActions commonObj;
    WebDriver d;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public makePayments(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver=helper.getGSDriver();
        d=ownDriver.getWebDriver();
    }

    public void logInToMakePayments()
    {
        Utility_Functions.xWaitForElementClickable(ownDriver,MakePaymentLandingPage.signIn,10);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,MakePaymentLandingPage.signIn,"Click Sign In");
        Utility_Functions.waitTillClickHardSleep(report,ownDriver,MakePaymentLandingPage.signIn,"Wait for page to be loaded");
        sendKey(MakePaymentLandingPage.userEmail,jsonData.getData("userName"));
        sendKey(MakePaymentLandingPage.userPassword,jsonData.getData("userPassword"));
        click(MakePaymentLandingPage.submitbtn);
        Utility_Functions.timeWait(6);
        Utility_Functions.waitTillClickHardSleep(report,ownDriver, ElectronicCommercePage.searchBox,"validating make payment dropdown");
        commonObj.validateText(MakePaymentLandingPage.makePaymentdrpdwntext,"Make Payments","validating make payment dropdown");
        Utility_Functions.timeWait(3);
    }

    public void navigateToInvoice()
    {
    	Utility_Functions.xWaitForElementPresent(ownDriver, MakePaymentLandingPage.makePaymentdrpdwntext, 5);
        Utility_Functions.xHoverElementclicks(ownDriver.findElement(MakePaymentLandingPage.makePaymentdrpdwntext),ownDriver);
        click(MakePaymentLandingPage.invoices);
        commonObj.validateText(InvoicePage.headerTitleInvoice,"Invoices","User in invoice Page");

    }



    public void scrollToView() {

        Utility_Functions.xScrollWindowOnce(d);

    }

    public void sortInvoiceNumber() {
        for (int i = 0; i < 2; i++) {

            click(InvoicePage.tableInvoiceHeader);
            List<WebElement> invoiceList = ownDriver.findElements(InvoicePage.tableInvoiceNumbers);

            for (int j = 0; j < invoiceList.size(); j++) {
                String temp = invoiceList.get(j).getText();
                //         System.out.println(temp);
                int k = j + 1;
                String inv = ownDriver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]" + "[" + k + "]" + "//a")).getText();
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
            List<WebElement> invoiceList = ownDriver.findElements(InvoicePage.tableInvoiceTotal);

            for (int j = 0; j < invoiceList.size(); j++) {
                String temp = invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k = j + 1;

                String inv = ownDriver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]" + "[" + k + "]" + "//div[7]")).getText();
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
            List<WebElement> invoiceList = ownDriver.findElements(InvoicePage.tableInvoiceDiscount);

            for (int j = 0; j < invoiceList.size(); j++) {
                String temp = invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k = j + 1;

                String inv = ownDriver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]" + "[" + k + "]" + "//div[8]")).getText();
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
            List<WebElement> invoiceList = ownDriver.findElements(InvoicePage.tableInvoiceAmtDue);

            for(int j=0;j<invoiceList.size();j++)
            {
                String temp= invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k=j+1;

                String inv= ownDriver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//div[8]")).getText();
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
            List<WebElement> invoiceList = ownDriver.findElements(InvoicePage.tableInvoiceDate);

            for(int j=0;j<invoiceList.size();j++)
            {
                String temp= invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k=j+1;

                String inv= ownDriver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//div[5]")).getText();
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
    	int statusCount = Utility_Functions.xGetSelectOptionCount(ownDriver,ownDriver.findElement(MakePaymentLandingPage.invoiceStatusDropDown));
    	for(int i=0 ;i<statusCount;i++ ) {
    		Utility_Functions.xSelectDropdownByIndex(ownDriver,ownDriver.findElement(MakePaymentLandingPage.invoiceStatusDropDown),i);
    		Utility_Functions.timeWait(2);
            Utility_Functions.waitTillClickHardSleep(report,ownDriver,MakePaymentLandingPage.invoiceStatusDropDown,"");
    		if(!Utility_Functions.isExist(ownDriver, MakePaymentLandingPage.lblNoRecords)) {
                if(Utility_Functions.xIsDisplayed(ownDriver,MakePaymentLandingPage.btnTableExpand)) {
                    click(MakePaymentLandingPage.btnTableExpand);
                }
    		Utility_Functions.timeWait(4);
    		String temp = ownDriver.findElement(MakePaymentLandingPage.txtRecordCount).getText().split("of")[0].trim().substring(1);
    		int displayCount = 0;
    		for(WebElement e :ownDriver.findElements(MakePaymentLandingPage.recordRow) ) {
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
    	Utility_Functions.xSelectDropdownByVisibleText(ownDriver, MakePaymentLandingPage.invoiceStatusDropDown, "All");
    	int statusCount = Utility_Functions.xGetSelectOptionCount(ownDriver,ownDriver.findElement(MakePaymentLandingPage.invoiceDatesDropDown));
    	for(int i=0 ;i<statusCount-1;i++ ) {
    		Utility_Functions.xSelectDropdownByIndex(ownDriver,ownDriver.findElement(MakePaymentLandingPage.invoiceDatesDropDown),i);
    		Utility_Functions.timeWait(2);
            Utility_Functions.waitTillClickHardSleep(report,ownDriver,MakePaymentLandingPage.invoiceStatusDropDown,"");
    		if(!Utility_Functions.isExist(ownDriver, MakePaymentLandingPage.lblNoRecords)) {
                if(Utility_Functions.xIsDisplayed(ownDriver,MakePaymentLandingPage.btnTableExpand)) {
                    click(MakePaymentLandingPage.btnTableExpand);
                }
    		Utility_Functions.timeWait(4);
    		String temp = ownDriver.findElement(MakePaymentLandingPage.txtRecordCount).getText().split("of")[0].trim().substring(1);
    		//System.out.println("count :"+temp.split("of")[0].trim().substring(1));
    		int displayCount = 0;
    		for(WebElement e :ownDriver.findElements(MakePaymentLandingPage.recordRow) ) {
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
        Utility_Functions.timeWait(5);
    	Utility_Functions.xSelectDropdownByVisibleText(ownDriver, MakePaymentLandingPage.invoiceStatusDropDown, "All");
    	Utility_Functions.xSelectDropdownByVisibleText(ownDriver, MakePaymentLandingPage.invoiceDatesDropDown, "Custom");

    	Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		//c.add(Calendar.DATE, 1);
		dt = c.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		String strDate= formatter.format(dt);
		Utility_Functions.timeWait(2);
        Utility_Functions.timeWait(5);
		sendKeys(MakePaymentLandingPage.dateFilterTo,strDate,"Entering to date");
		c.add(Calendar.DATE, -2);
		dt = c.getTime();
		strDate= formatter.format(dt);
		Utility_Functions.timeWait(2);
		sendKeys(MakePaymentLandingPage.dateFilterFrom,strDate,"Entering from date");

		click(MakePaymentLandingPage.dateFilterApply);
		Utility_Functions.timeWait(2);
		if(!Utility_Functions.isExist(ownDriver, MakePaymentLandingPage.lblNoRecords)) {
			click(MakePaymentLandingPage.btnTableExpand);
    		Utility_Functions.timeWait(4);
    		String temp = ownDriver.findElement(MakePaymentLandingPage.txtRecordCount).getText().split("of")[0].trim().substring(1);
    		//System.out.println("count :"+temp.split("of")[0].trim().substring(1));
    		int displayCount = 0;
    		for(WebElement e :ownDriver.findElements(MakePaymentLandingPage.recordRow) ) {
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
    	Utility_Functions.xSelectDropdownByVisibleText(ownDriver, MakePaymentLandingPage.invoiceStatusDropDown, "All");
    	//Utility_Functions.xSelectDropdownByVisibleText(driver, MakePaymentLandingPage.invoiceDatesDropDown, "Custom");
    	sendKeys(MakePaymentLandingPage.txtBoxInvoiceSearch,Utility_Functions.xGetJsonData("CreatedSalesOrder"),"Entering filter data");
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);

    	Utility_Functions.timeWait(2);
		if(!Utility_Functions.isExist(ownDriver, MakePaymentLandingPage.lblNoRecords)) {
            if (isDisplayed(MakePaymentLandingPage.btnTableExpand)) {
                click(MakePaymentLandingPage.btnTableExpand);
            }
    		Utility_Functions.timeWait(4);
    		String temp = ownDriver.findElement(MakePaymentLandingPage.txtRecordCount).getText().split("of")[0].trim().substring(1);
    		//System.out.println("count :"+temp.split("of")[0].trim().substring(1));
    		int displayCount = 0;
    		for(WebElement e :ownDriver.findElements(MakePaymentLandingPage.recordRow) ) {
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
            List<WebElement> invoiceList = ownDriver.findElements(InvoicePage.tableInvoiceDate);

            for(int j=0;j<invoiceList.size();j++)
            {
                String temp= invoiceList.get(j).getText();
                //        System.out.println(temp);
                int k=j+1;

                String inv= ownDriver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//div[5]")).getText();
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

            List<WebElement> invoiceCheckBoxCount = ownDriver.findElements(InvoicePage.invoiceCheckBox);

            for(int j=0;j<1;j++)
            {
                int k=j+1;
                ownDriver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//label")).click();
            }

            click(InvoicePage.makePaymentBtn);
            Utility_Functions.timeWait(3);
            if(isDisplayed(By.xpath("//div[contains(text(),'*Before you continue,')]"))){
            }else {
                commonObj.validateText(MakeAPaymentPage.pageTitle, "Make a Payment", "User navigated To Make a Payment Page");
                //     commonObj.validateText(MakeAPaymentPage.paymentAmount,"20.00","Amount Matches");
                click(MakeAPaymentPage.bankAccPay);
                click((MakeAPaymentPage.saveContinuebtn));
                Utility_Functions.timeWait(2);
                click(MakeAPaymentPage.submitPaymentbtn);
                Utility_Functions.timeWait(3);
                commonObj.validateText(MakeAPaymentPage.headerPaymentConfirmation, "Payment Confirmation", "Payment Successful");
                scrollToView();
                String txt = ownDriver.findElement(MakeAPaymentPage.confirmationNumber).getText();
                Utility_Functions.xUpdateJson("MPInvoiceNumberBA", txt);
            }
        }

    public void makeSinglePaymentCC()
    {
        click(InvoicePage.supplierCheckBox);
        click(By.xpath("//div[contains(text(),'Amt')]"));
        List<WebElement> invoiceCheckBoxCount = ownDriver.findElements(InvoicePage.invoiceCheckBox);

        for(int j=0;j<1;j++)
        {
            int k=j+1;
            ownDriver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]"+"["+k+"]"+"//label")).click();
        }
        String amt=getText(By.xpath("(//div[contains(@class,'item invoice-table')]/span)[2]"));
        click(InvoicePage.makePaymentBtn);
        Utility_Functions.timeWait(3);
        commonObj.validateText(MakeAPaymentPage.pageTitle,"Make a Payment","User navigated To Make a Payment Page");
        //     commonObj.validateText(MakeAPaymentPage.paymentAmount,"20.00","Amount Matches");
        commonObj.validateText(By.xpath("//div/p/following-sibling::h2"),"$"+amt,"Total Amount: $"+amt);
        click(MakeAPaymentPage.cctab);
        click(MakeAPaymentPage.ccAccPay);
        click((MakeAPaymentPage.saveContinuebtn));
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("//p/span[contains(text(),'$"+amt+"')]"),"$"+amt,"Pay,ent Total: $"+amt);
        click(MakeAPaymentPage.submitPaymentbtn);
        Utility_Functions.timeWait(3);
        commonObj.validateText(MakeAPaymentPage.headerPaymentConfirmation,"Payment Confirmation","Payment Successful");
        scrollToView();
        String txt=ownDriver.findElement(MakeAPaymentPage.confirmationNumber).getText();
        Utility_Functions.xUpdateJson("MPInvoiceNumberCC",txt);
        commonObj.validateText(By.xpath("//p/span[contains(text(),'$"+amt+"')]"),"$"+amt,"Pay,ent Total: $"+amt);

    }

    public void makeMultiPaymentBA() {
        click(InvoicePage.supplierCheckBox);

        List<WebElement> invoiceCheckBoxCount = ownDriver.findElements(InvoicePage.invoiceCheckBox);
        int count = Integer.parseInt(jsonData.getData("invoiceCount"));
        for (int j = 0; j < count; j++) {
            int k = j + 1;
            ownDriver.findElement(By.xpath("//div[contains(@class,'win-invoice-table-supplier-open-item')]" + "[" + k + "]" + "//label")).click();
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
        String txt = ownDriver.findElement(MakeAPaymentPage.confirmationNumber).getText();
        Utility_Functions.xUpdateJson("MPInvoiceNumberBA", txt);
    }




    
   

    }



