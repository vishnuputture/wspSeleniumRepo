package businesskeywords.Inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import pages.common.MasterPage;
import pages.inventory.BuyersInquiryPage;
import supportLibraries.Utility_Functions;


public class inquiryBuyer extends ReusableLib {

    CommonActions commonObj;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public inquiryBuyer(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    /**
     * This method validates the title of Buyer's Inquiry Page
     */
    public void navigateToBuyersInquiry() {
        commonObj.masterToInventory();
        commonObj.inventoryToInquiryBuyer();
        commonObj.validateText(BuyersInquiryPage.pageTitle, "Buyer's Inquiry", "Validating Buyer's Inquiry page title");
    }

    /**
     * Keyword to select random Stock No in Buyer's Inquiry Page
     */
    public void selectRandomStock() {
        click(BuyersInquiryPage.btnStockNo, "Click on [Stock No] button");
        String itemNo = commonObj.selectRandomItemNumber();
        jsonData.putData("itemNo", itemNo);
        String itemNoActual = getAttribute(BuyersInquiryPage.tbxStockNo, "value");
        Utility_Functions.xAssertEquals(report, itemNo, itemNoActual, "Validating selected item no is displayed");
    }

    /**
     * Keyword to select desired Stock No. in Buyer's Inquiry Page
     */
    public void selectStock() {
        String itemNo = jsonData.getData("itemNo");
        sendKeysAndEnter(BuyersInquiryPage.tbxStockNo, itemNo, "Enter Stock No");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to get stock data from Buyer's Inquiry Page
     */
    public void getStockData() {
        String withReceipts = getText(BuyersInquiryPage.withReceipts).trim();
        jsonData.putData("WithReceipts", withReceipts);
        String onPO = getText(BuyersInquiryPage.onPO).trim();
        jsonData.putData("OnPO", onPO);
        String availToSell = getText(BuyersInquiryPage.AvailToSell).trim();
        jsonData.putData("AvailableToSell", availToSell);
    }

    /**
     * Keyword to verify stock data from Purchase Order Page
     */
    public void verifyStockData() {
        int qty = Integer.parseInt(jsonData.getData("QtyOrdered"));
        String text = jsonData.getData("ConvFactor");
        double convFactor = Double.valueOf(text);
        int diff = (int) (qty * convFactor);

        String textWithReceipts = jsonData.getData("WithReceipts");
        int withReceiptsExpected;
        if (textWithReceipts.isEmpty()) {
            textWithReceipts += diff;
            withReceiptsExpected = Integer.parseInt(textWithReceipts);
        } else {
            withReceiptsExpected = Integer.parseInt(textWithReceipts) + diff;
        }

        String textOnPO = jsonData.getData("OnPO");
        int onPOExpected;
        if (textOnPO.isEmpty()) {
            textOnPO += diff;
            onPOExpected = Integer.parseInt(textOnPO);
        } else {
            onPOExpected = Integer.parseInt(textOnPO) + diff;
        }

        int withReceiptsActual = Integer.parseInt(getText(BuyersInquiryPage.withReceipts).trim());
        int onPOActual = Integer.parseInt(getText(BuyersInquiryPage.onPO).trim());

        Utility_Functions.xAssertEquals(report, withReceiptsExpected, withReceiptsActual, "validate [With Receipts] value");
        Utility_Functions.xAssertEquals(report, onPOExpected, onPOActual, "validate [On PO] value");
    }

}
