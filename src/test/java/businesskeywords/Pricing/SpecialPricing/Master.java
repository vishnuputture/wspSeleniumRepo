package businesskeywords.Pricing.SpecialPricing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;

import commonkeywords.CommonActions;

import pages.MasterPage;

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


    /**
     * This method is invoked to sign out of wise application
     */

    public void signOut() {
        click(MasterPage.btnSignOut, "Click on signout");
    }
}
