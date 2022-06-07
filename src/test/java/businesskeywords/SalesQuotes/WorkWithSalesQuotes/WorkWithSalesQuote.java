package businesskeywords.SalesQuotes.WorkWithSalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import pages.SalesQuotes.OldWorkWithSalesQuotesPage;
import pages.SalesQuotes.SalesQuoteConversionToSalesOrderPage;
import supportLibraries.Utility_Functions;

public class WorkWithSalesQuote extends ReusableLib {

    CommonActions commonObj;
    public static String itemN;
    public static String salesOrder;
    public static String purchaseOrder;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public WorkWithSalesQuote(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver=helper.getGSDriver();
    }

    /**
     * This method navigate To working Sales Quotes
     */
    public void navigateToWorkingSalesQuote() {
        commonObj.navigateToSalesQuotes();
        commonObj.navigationToWorkingSalesQuotes();
    }

    /**
     * This method to click next button
     *
     */
    public void clickNextBtn() {
        waitForElementClickable(OldWorkWithSalesQuotesPage.nextBtn,10);
        click(OldWorkWithSalesQuotesPage.nextBtn, "Click Next Button");
    }

    /**
     * This method to click Back button
     *
     */
    public void clickBackBtn() {
        waitForElementClickable(OldWorkWithSalesQuotesPage.backBtn,10);
        click(OldWorkWithSalesQuotesPage.backBtn, "Click back Button");
    }

    /**
     * This method to Create Quote
     *
     */
    public void createQuote() {
        click(OldWorkWithSalesQuotesPage.createQuote,"Click Create Quote");
        String custNumber=jsonData.getData("CustNum");
        String jobName=jsonData.getData("JobName");
        Utility_Functions.xSendKeys(ownDriver.findElement(OldWorkWithSalesQuotesPage.custNumberCret),custNumber);
        Utility_Functions.xSendKeys(ownDriver.findElement(OldWorkWithSalesQuotesPage.quoteJobName),jobName);
        clickNextBtn();
        clickNextBtn();
    }

    /**
     * This method to add/Edit Item
     *
     */
    public void addItemToQuote() {
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(OldWorkWithSalesQuotesPage.typeItem),0);
        waitForVisible(OldWorkWithSalesQuotesPage.itemNumber);
        System.out.println("After Item no Wait");
        String itemNo=Utility_Functions.getText(ownDriver, OldWorkWithSalesQuotesPage.itemNumber);
        Utility_Functions.xUpdateJson("SQItem",itemNo);
        System.out.println("itemNo: "+itemNo);
        String desc=jsonData.getData("TestDescription");

        sendKey(OldWorkWithSalesQuotesPage.testDesc,desc);
        sendKey(OldWorkWithSalesQuotesPage.mtxCost,"1");
        sendKey(OldWorkWithSalesQuotesPage.poCost,"1");
        sendKey(OldWorkWithSalesQuotesPage.listPrc,"2");
        click(OldWorkWithSalesQuotesPage.accept,"Click F9=Accept");
        sendKey(OldWorkWithSalesQuotesPage.qty,"1");
    }

    public void verifyQuoteDetails()
    {
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        String itemNm=ownDriver.findElement(OldWorkWithSalesQuotesPage.itemNumberItem).getText();
        itemN = itemNm.trim();
        Utility_Functions.xAssertEquals(report,itemN,Utility_Functions.xGetJsonData("SQItem"),"Item Number: ");
        clickBackBtn();
    }

    /**
     * This method to Convert to Sales Order
     *
     */
    public void convertSaleOrd() {
        click(OldWorkWithSalesQuotesPage.convert,"Click Convert");
        click(OldWorkWithSalesQuotesPage.overRide,"Click Override");
        sendKey(SalesQuoteConversionToSalesOrderPage.salesOrderQtyShipped, "1");
        click(OldWorkWithSalesQuotesPage.nextBtn,"Click Next Button");
        String itemNo=ownDriver.findElement(OldWorkWithSalesQuotesPage.itemConvert).getText();
        String act_desc=jsonData.getData("TestDescription");
        String act_Qty=jsonData.getData("Quantity");
        String qty=ownDriver.findElement(OldWorkWithSalesQuotesPage.qtyConvert).getText().trim();
        String backQty=ownDriver.findElement(OldWorkWithSalesQuotesPage.backOrdQty).getText().trim();
        String exp_desc=ownDriver.findElement(OldWorkWithSalesQuotesPage.itemDescConvert).getText().trim();
        String expItemNo=itemNo.trim();
        Utility_Functions.xAssertEquals(report,expItemNo,itemN,"Item Number: ");

        Utility_Functions.xAssertEquals(report,exp_desc,act_desc,"Item Description: ");
        Utility_Functions.xAssertEquals(report,qty,act_Qty+".0","Original Quantity: ");
        Utility_Functions.xAssertEquals(report,backQty,"1.0","Back order quantity: ");
        click(OldWorkWithSalesQuotesPage.process,"Click Process Button");
    }


    /**
     * This method to verify Customer and JobName
     *
     */
    public String validateCustJobName() {
        System.out.println("validateCustJobName");
        String cust=ownDriver.findElement(OldWorkWithSalesQuotesPage.billToAcc).getAttribute("value");
        System.out.println("cust: "+cust);
        String jobNm=ownDriver.findElement(OldWorkWithSalesQuotesPage.txtJobName).getAttribute("value");
        System.out.println("jobNm: "+jobNm);
        salesOrder=ownDriver.findElement(OldWorkWithSalesQuotesPage.salesOrderNum).getAttribute("value");
        Utility_Functions.xUpdateJson("SOSmoke",salesOrder);
        Utility_Functions.xUpdateJsonWithArray("SOSmoke1",salesOrder);
        System.out.println("Sales Order: "+salesOrder);
        String custNumber=jsonData.getData("CustNum");
        String jobName=jsonData.getData("JobName");
        Utility_Functions.xAssertEquals(report,custNumber,cust,"Customer Number: ");
        Utility_Functions.xAssertEquals(report,jobName,jobNm,"Job Name: ");
        click(OldWorkWithSalesQuotesPage.nextButton,"Click Next Button");
        return salesOrder;
    }

    /**
     * This method to verify Inventory Information
     *
     */
    public void inventoryInformation() {
        click(OldWorkWithSalesQuotesPage.itemsTab);
        String itemNo=Utility_Functions.getText(ownDriver, OldWorkWithSalesQuotesPage.ordInfItemNo);
        String itemDesc=Utility_Functions.getText(ownDriver, OldWorkWithSalesQuotesPage.ordInfItemDesc);
        String bckQty=Utility_Functions.getText(ownDriver, OldWorkWithSalesQuotesPage.ordInfBackOrd);
        String qty=ownDriver.findElement(OldWorkWithSalesQuotesPage.ordInfQty).getAttribute("value");
        String testDesc=jsonData.getData("TestDescription");
        Utility_Functions.xAssertEquals(report,itemN,itemNo,"Item Number: ");
        Utility_Functions.xAssertEquals(report,itemDesc,testDesc,"Item Description: ");
        Utility_Functions.xAssertEquals(report,qty,"1","Item Quantity: ");
        Utility_Functions.xAssertEquals(report,bckQty,"1.00","Item Back Order Quantity: ");
    }

    /**
     * This method navigate to Shipments Tab
     *
     */
    public void shipmentTab() {
        click(OldWorkWithSalesQuotesPage.shipmentTab,"Click Shipments Tab");
    }

    /**
     * This method to save and Exit
     *
     */
    public void saveExt() {
        click(OldWorkWithSalesQuotesPage.saveExt,"Click Save & Exit Button");
    }

    /**
     * This method to click Exit button
     *
     */
    public void clkExit() {
        click(OldWorkWithSalesQuotesPage.extBtn,"Click Exit Button");
    }

    /**
     * This method to create Purchase order
     *
     */
      public void createPurchaseOrd() {
        shipmentTab();
        Utility_Functions.rightClick(ownDriver, OldWorkWithSalesQuotesPage.rgtClk);
        click(OldWorkWithSalesQuotesPage.crtPcsOrd,"Click create Purchase order");
        click(OldWorkWithSalesQuotesPage.cntEntShp,"Click Convert Entire Shipment");
        click(OldWorkWithSalesQuotesPage.nextButton,"Click Next Button");
        click(OldWorkWithSalesQuotesPage.searchBtn);
        sendKey(OldWorkWithSalesQuotesPage.sltOpt,"1");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(OldWorkWithSalesQuotesPage.freightCode),1);
        click(OldWorkWithSalesQuotesPage.createPO,"Click Create PO Button");
        purchaseOrder=Utility_Functions.getText(ownDriver, OldWorkWithSalesQuotesPage.purchaseOrdNo);
        Utility_Functions.xUpdateJson("POSmoke",purchaseOrder);
        Utility_Functions.xUpdateJsonWithArray("POSmoke1",purchaseOrder);
        System.out.println("purchaseOrder: "+purchaseOrder);
        click(OldWorkWithSalesQuotesPage.backBtn,"Click Back Button");
        String RelPO=Utility_Functions.getText(ownDriver, OldWorkWithSalesQuotesPage.relatedPO);
        Utility_Functions.xAssertEquals(report,"Related P.O.",RelPO,"Related P.O.: ");
        saveExt();
        clkExit();
        click(OldWorkWithSalesQuotesPage.saleQExtBtn,"Click Exit Button");
        click(OldWorkWithSalesQuotesPage.saleQExtBtn,"Click Exit Button");
    }



    /**
     * This method exit from SalesQuotes To MasterPage
     *
     */
    public void exitSalesQuotesToMasterPage() {

        commonObj.exitSalesQuotesToMasterPage();
    }

}