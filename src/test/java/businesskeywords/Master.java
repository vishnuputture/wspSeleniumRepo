package businesskeywords;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;

import commonkeywords.CommonActions;

import org.openqa.selenium.WebElement;

import pages.AddSpecialPricingPage;
import pages.MasterPage;
import supportLibraries.Utility_Functions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

public class Master extends ReusableLib {
	CommonActions commonObj;
	 /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public Master(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        
    }
    
   /* public void goToSpecialPricing(){
    	click(MasterPage.orderProcessMenu);
    	navigateToSplPricing();
    	
    	
    }*/
    
    public void navigateToSplPricing() {
    	//CommonActions commonObj = new CommonActions(helper);
    	commonObj.orderProcToSplPricing();
    }
    
    /*public void goToSqlApp() {
    	sendKeys(MasterPage.sqlTxtBox,"STRSQL","Go to SQL application");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	if(Utility_Functions.xWaitForElementPresent(driver,MasterPage.sqlTitleLbl, 5)) {
    		String title = Utility_Functions.getText(driver,MasterPage.sqlTitleLbl);
    		System.out.println("Text: "+title);
    		Utility_Functions.xAssertEquals(report, jsonData.getData("sqltitle"), title.trim(), "");
    	}else {
    		System.out.println("Text: Not found");
    		throw new NoSuchElementException("Could not find :"+MasterPage.sqlTitleLbl);
    	}
    }*/
    public void signOut() {
    	click(MasterPage.btnSignOut,"Click on signout");
    }
}
