package businesskeywords.common;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import pages.common.MasterPage;
import supportLibraries.Utility_Functions;

public class Master extends ReusableLib {
    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public Master(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
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

    public void goToPly() {
        sendKeys(MasterPage.sqlTxtBox, "ply", "Go to PLY application");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
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
        sendKeys(MasterPage.sqlTxtBox, "call is202cl");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    }


    /**
     * This method is invoked to sign out of wise application
     */

    public void signOut() {
        click(MasterPage.btnSignOut, "Click on signout");
    }

    /**
     * This method is used to navigate to Billing Instructions screen
     */
    public void navigateToBillingInstructions() {
        click(MasterPage.orderProcessMenu);
        click(MasterPage.billingInstructions);
    }

}
