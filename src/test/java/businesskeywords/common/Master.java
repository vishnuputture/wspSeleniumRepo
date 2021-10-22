package businesskeywords.common;

import org.openqa.selenium.Keys;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;

import commonkeywords.CommonActions;

import pages.OrderProcessingPage;
import pages.common.MasterPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import supportLibraries.Utility_Functions;

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

    /**
     * This method is invoked to navigate to special pricing screen
     */

    public void navigateToSplPricing() {
        commonObj.orderProcToSplPricing();
    }

    public void navigateToSalesAnalysis() {
        commonObj.masterToSalesAnalysis();
    }

    public void navigateToSPAApplication() {
        commonObj.salesAnalysisToSPAApplication();
    }


    /**
     * This method is invoked to navigate to Working with sales quotes
     */
    public void navigateToSalesQuotesMenu() {
        commonObj.navigateToSalesQuotes();
    }

    public void navigateToWorkingSalesQuotes() {
        commonObj.navigationToWorkingSalesQuotes();
    }
    
    public void runPriceSheetBatch() {
    	sendKeys(MasterPage.sqlTxtBox,"call is202cl");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    }


    /**
     * This method is invoked to sign out of wise application
     */

    public void signOut() {
        click(MasterPage.btnSignOut, "Click on signout");
    }
}
