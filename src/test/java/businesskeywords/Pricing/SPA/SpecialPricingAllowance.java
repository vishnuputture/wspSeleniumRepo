package businesskeywords.Pricing.SPA;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;

import commonkeywords.CommonActions;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import pages.pricing.AddSpecialPricingPage;
import pages.pricing.SpecialPricePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SpecialPricingAllowance extends ReusableLib {

	public SpecialPricingAllowance(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    CommonActions commonObj;
    
    public void validateHideExpiredContract() {
    	click(SpecialPriceAllowancePage.btnHideShowExpired, "Clicking on hide expired button");
    	List<WebElement> expiredCont = driver.findElements(SpecialPriceAllowancePage.lblExpired);
    	if(expiredCont.size()>0) {
    		report.updateTestLog("Validate expired contracts", "No expired contracts should be displayed", Status.FAIL);
    	}else {
    		report.updateTestLog("Validate expired contracts", "No expired contracts should be displayed", Status.PASS);
    	}
    }
    
    public void validateShowExpiredContract() {
    	click(SpecialPriceAllowancePage.btnHideShowExpired, "Clicking on show expired button");
    	List<WebElement> expiredCont = driver.findElements(SpecialPriceAllowancePage.lblExpired);
    	if(expiredCont.size()>0) {
    		report.updateTestLog("Validate expired contracts", "Expired contracts should be displayed", Status.PASS);
    	}else {
    		report.updateTestLog("Validate expired contracts", "Expired contracts should be displayed", Status.FAIL);
    	}
    }
    
    public void navAddContractToSPA() {
    	click(SpecialPriceAllowancePage.btnReturn);
    }
    
    public void searchContract() {
    	sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxSearchCon,jsonData.getData("InvalidSearchtext"),"Searching with invalid contract name");
    	if (Utility_Functions.xWaitForElementPresent(driver, SpecialPriceAllowancePage.lblNoRecords, 5)) {
            String searchMessage = Utility_Functions.getText(driver, SpecialPriceAllowancePage.lblNoRecords);
            System.out.println("Text: " + searchMessage);
            Utility_Functions.xAssertEquals(report, "* NO RECORDS TO DISPLAY *", searchMessage.trim(), "Validating message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + SpecialPriceAllowancePage.lblNoRecords);
        }
    	
    	sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxSearchCon,jsonData.getData("Contract_Name"),"Searching with valid contract name");
    	
    	String contractName =  Utility_Functions.getText(driver, SpecialPriceAllowancePage.lblContractName);
    	Utility_Functions.xAssertEquals(report, jsonData.getData("Contract_Name").toUpperCase(), contractName.trim(), "Validating search result");
    }
    
    public void addActiveContract() {
    	click(SpecialPriceAllowancePage.btnAddContract,"Clicking on add contract button");
    	sendKeys(SpecialPriceAllowancePage.txtBoxContractname,jsonData.getData("Contract_Name"),"Entering contract name");
    	Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        String startDate = formatter.format(dt);
        
        sendKeys(SpecialPriceAllowancePage.txtBoxStartDate,startDate,"Entering contract start date");
        c.setTime(dt);
        c.add(Calendar.DATE, 5);
        dt = c.getTime();
        //SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        String endDate = formatter.format(dt);
        sendKeys(SpecialPriceAllowancePage.txtBoxEndDate,endDate,"Entering contract start date");
        sendKeys(SpecialPriceAllowancePage.txtBoxVendorNo,jsonData.getData("VendorNo"),"Entering vendor number");
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxGroupNo,jsonData.getData("GroupNo"),"Entering group number");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        if (Utility_Functions.xWaitForElementPresent(driver, SpecialPriceAllowancePage.lblSuccess, 5)) {
            String successMessage = Utility_Functions.getText(driver, SpecialPriceAllowancePage.lblSuccess);
            System.out.println("Text: " + successMessage);
            Utility_Functions.xAssertEquals(report, "Successfully added Contract. Add another or F12 Return.", successMessage.trim(), "Validating success message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + SpecialPriceAllowancePage.lblSuccess);
        }
        
    }
    
    public void addExpiredContract() {
    	//click(SpecialPriceAllowancePage.btnAddContract,"Clicking on add contract button");
    	sendKeys(SpecialPriceAllowancePage.txtBoxContractname,jsonData.getData("Expired_Contract_Name"),"Entering contract name");
    	Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -5);
        c.add(Calendar.YEAR, -3);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        String startDate = formatter.format(dt);
        
        sendKeys(SpecialPriceAllowancePage.txtBoxStartDate,startDate,"Entering contract start date");
        c.setTime(dt);
        c.add(Calendar.DATE, -1);
        c.add(Calendar.YEAR, 1);
        dt = c.getTime();
        //SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        String endDate = formatter.format(dt);
        sendKeys(SpecialPriceAllowancePage.txtBoxEndDate,endDate,"Entering contract start date");
        sendKeys(SpecialPriceAllowancePage.txtBoxVendorNo,jsonData.getData("VendorNo"),"Entering vendor number");
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxGroupNo,jsonData.getData("GroupNo"),"Entering group number");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        if (Utility_Functions.xWaitForElementPresent(driver, SpecialPriceAllowancePage.lblSuccess, 5)) {
            String successMessage = Utility_Functions.getText(driver, SpecialPriceAllowancePage.lblSuccess);
            System.out.println("Text: " + successMessage);
            Utility_Functions.xAssertEquals(report, "Successfully added Contract. Add another or F12 Return.", successMessage.trim(), "Validating success message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + SpecialPriceAllowancePage.lblSuccess);
        }
    }
    
    public void contractDeletionValidation() {
    	sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxSearchCon,jsonData.getData("Contract_Name"),"Searching with active contract name");
    	sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxOption,"4","Deleting active contract");
    	if (Utility_Functions.xWaitForElementPresent(driver, SpecialPriceAllowancePage.lblInvalidDeletionMessage, 5)) {
            String deleteMessage = Utility_Functions.getText(driver, SpecialPriceAllowancePage.lblInvalidDeletionMessage);
            System.out.println("Text: " + deleteMessage);
            Utility_Functions.xAssertEquals(report, "Contract Active - Delete not allowed.", deleteMessage.trim(), "Validating deletion message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + SpecialPriceAllowancePage.lblInvalidDeletionMessage);
            
        }
    	clearText(SpecialPriceAllowancePage.txtBoxOption);
    	sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxSearchCon,jsonData.getData("Expired_Contract_Name"),"Searching with expired contract name");
    	sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxOption,"4","Deleting expired contract");
    	click(SpecialPriceAllowancePage.btnDelete,"Confirming delete");
    	
    	if (Utility_Functions.xWaitForElementPresent(driver, SpecialPriceAllowancePage.lblSuccess, 5)) {
            String deleteMessage = Utility_Functions.getText(driver, SpecialPriceAllowancePage.lblSuccess);
            System.out.println("Text: " + deleteMessage);
            Utility_Functions.xAssertEquals(report, "Contracts have been deleted.", deleteMessage.trim(), "Validating success message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + SpecialPriceAllowancePage.lblSuccess);
            
        }
    	
    }
}
