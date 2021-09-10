package businesskeywords.SalesQuotes.WorkWithSalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import pages.SalesQuotes.WorkWithSalesQuotesPage;
import supportLibraries.Utility_Functions;


public class POReceiptCorrects extends ReusableLib {
    CommonActions commonObj;
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public POReceiptCorrects(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    /************************Inventory Receipt***********************/
    /**
     * This method navigate To Inventory Receipt
     *
     */
    public void navigateToInventoryReceipt() {
        commonObj.masterToPurchaseOrder();
        commonObj.purchaseOrderToInventoryReceipt();
    }

    /**
     * This method navigate To Inventory Receipt
     *
     */
    public void inventReceipt() {
        sendKey(WorkWithSalesQuotesPage.purcOrdNo,Utility_Functions.xGetJsonData("POSmoke"));
        click(WorkWithSalesQuotesPage.process,"Click Process button");
        String imNo= Utility_Functions.getText(driver,WorkWithSalesQuotesPage.itemNo).trim();
        String itmDesc=Utility_Functions.getText(driver,WorkWithSalesQuotesPage.itmDesc).trim();
        String testDesc=jsonData.getData("TestDescription");
        String relSaleOrd= Utility_Functions.getText(driver,WorkWithSalesQuotesPage.relSaleOrd);
        sendKey(WorkWithSalesQuotesPage.qtyRec,"1");
        Utility_Functions.xAssertEquals(report,imNo,Utility_Functions.xGetJsonData("SQItem"),"Item Number: ");
        Utility_Functions.xAssertEquals(report,testDesc,itmDesc,"Item Description: ");
        Utility_Functions.xAssertEquals(report,Utility_Functions.xGetJsonData("SOSmoke")+"-01",relSaleOrd,"Related Sale Order: ");
        click(WorkWithSalesQuotesPage.proc,"Click Process button");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        click(WorkWithSalesQuotesPage.exitBtn,"Click Exit Button");
    }

    /**
     * This method exit from Purchase Order Page
     *
     */
    public void exitPurchaseToMasterPage() {
        commonObj.exitSalesQuotesToMasterPage();
    }

}
