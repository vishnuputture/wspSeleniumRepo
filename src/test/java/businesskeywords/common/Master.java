package businesskeywords.common;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;

import commonkeywords.CommonActions;

import pages.common.MasterPage;

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


    /**
     * This method is invoked to sign out of wise application
     */

    public void signOut() {
        click(MasterPage.btnSignOut, "Click on signout");
    }
}
