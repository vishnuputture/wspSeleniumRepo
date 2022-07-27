package businesskeywords.Pricing.MatrixCostUpdate;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import pages.pricing.SpecialPricePage;
import pages.pricing.matrixcost.MatrixCostUpdatePage;
import supportLibraries.Utility_Functions;

public class ValidateItemNoFromItemLedger extends ReusableLib {
    CommonActions commonObj = new CommonActions(helper);
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public ValidateItemNoFromItemLedger(Helper helper) {
        super(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * This method navigation To ItemLedger Page and validate item Number
     */
    public void navigationToItemLedger() {
        Utility_Functions.xWaitForElementVisible(ownDriver, MatrixCostUpdatePage.itemNumberLink, 5);
        String act_itemNo = getText(MatrixCostUpdatePage.itemNumberLink);
        click(MatrixCostUpdatePage.itemNumberLink, "Click Item Number");
        String exp_itemNo = ownDriver.findElement(MatrixCostUpdatePage.firstItemNumber).getAttribute("value");
        Utility_Functions.xAssertEquals(report, act_itemNo, exp_itemNo, "Item Number");
        Utility_Functions.xWaitForElementPresent(ownDriver, MatrixCostUpdatePage.itemLedgerHeader, 5);
    }

    /**
     * This method navigates from Matrix Cost Update page to Inventory Management-Menu2 page
     */
    public void exitItemLedgerToMatrixToInventory() {
        Utility_Functions.xWaitForElementVisible(ownDriver, SpecialPricePage.btnF3, 5);
        System.out.println("Visible");
        click(SpecialPricePage.btnF3, "Click Exit Button");
        Utility_Functions.xWaitForElementPresent(ownDriver, MatrixCostUpdatePage.inventoryManagementMenu2Header, 5);
    }
}
