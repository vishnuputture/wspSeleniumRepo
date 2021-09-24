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
    
    public void addActiveContract() {
    	click(SpecialPriceAllowancePage.btnAddContract,"Clicking on add contract button");
    	sendKeys(SpecialPriceAllowancePage.txtBoxContractname,"Automation Active","Entering contract name");
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
        sendKeys(SpecialPriceAllowancePage.txtBoxVendorNo,"000388","Entering vendor number");
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxGroupNo,"5","Entering group number");
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
}
