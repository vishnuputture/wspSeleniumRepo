package businesskeywords.Inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import pages.PurchaseOrders.InventoryReceiptsPage;
import pages.common.MasterPage;
import pages.inventory.BuyersInquiryPage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;


public class inventoryReceipts extends ReusableLib {

    CommonActions commonObj;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public inventoryReceipts(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    /**
     * This method validates the title of Inventory Receipts Page
     */
    public void navigateToInventoryReceipts() {
        commonObj.masterToPurchaseOrder();
        commonObj.purchaseOrderToInventoryReceipt();
        commonObj.validateText(InventoryReceiptsPage.pageTitle, "inventory receipts -", "Validating Inventory Receipts page title");
    }

    /**
     * This method exits from Inventory Receipts Page to master page
     */
    public void navigateInventoryReceiptsToMaster()  {
        Utility_Functions.xScrollWindow(driver);
        click(InventoryReceiptsPage.btnExit,"Click Exit Button");
        click(SpecialPriceAllowancePage.btnExit,"Click Exit Button");
    }

    /**
     * Keyword to enter existing PO Number in Inventory Receipts Page
     */
    public void enterExistingPONumber(){
        String poNumber = jsonData.getData("OrderNumber");
        sendKeysAndEnter(InventoryReceiptsPage.tbxPONumber, poNumber,"Enter Purchase Order Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to select desired Stock No. in Buyer's Inquiry Page
     */
    public void enterQtyRcvdAndProcess(){
        String qty = jsonData.getData("QtyOrdered");
        sendKeysAndEnter(InventoryReceiptsPage.tbxQtyRcvd, qty,"Enter Quantity Received");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        Utility_Functions.actionKey(Keys.ENTER, driver);
        click(InventoryReceiptsPage.btnProcess,"Click Process Button");
    }





}
