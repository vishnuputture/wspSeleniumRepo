package businesskeywords.Pricing.MatrixCostUpdate;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import pages.pricing.MatrixCostUpdatePage;
import pages.pricing.SpecialPricePage;
import supportLibraries.Utility_Functions;

public class ValidateItemNoFromItemLedger extends ReusableLib {
    CommonActions commonObj;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public ValidateItemNoFromItemLedger(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    /**
     * This method navigation To ItemLedger Page and validate item Number
     *
     */
    public void navigationToItemLedger() {
        Utility_Functions.xWaitForElementVisible(driver, MatrixCostUpdatePage.itemNumberLink, 5);
        String act_itemNo=getText(MatrixCostUpdatePage.itemNumberLink);
        click(MatrixCostUpdatePage.itemNumberLink,"Click Item Number");
        String exp_itemNo=driver.findElement(MatrixCostUpdatePage.firstItemNumber).getAttribute("value");
        Utility_Functions.xAssertEquals(report,act_itemNo,exp_itemNo,"Item Number");
        Utility_Functions.xWaitForElementPresent(driver, MatrixCostUpdatePage.itemLedgerHeader, 5);
    }

    /**
     *
     *
     * This method navigates from Matrix Cost Update page to Inventory Management-Menu2 page
     *
     */
    public void exitItemLedgerToMatrixToInventory() {
        Utility_Functions.xWaitForElementVisible(driver, SpecialPricePage.btnF3, 5);
        System.out.println("Visible");
        click(SpecialPricePage.btnF3,"Click Exit Button");
        Utility_Functions.xWaitForElementPresent(driver, MatrixCostUpdatePage.inventoryManagementMenu2Header, 5);
    }
}
