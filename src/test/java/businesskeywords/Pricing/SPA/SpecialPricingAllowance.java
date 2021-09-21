package businesskeywords.Pricing.SPA;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;

import commonkeywords.CommonActions;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import pages.pricing.AddSpecialPricingPage;
import pages.pricing.SpecialPricePage;
import pages.pricing.spa.SpecialPriceAllowancePage;

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
}
