package businesskeywords.SalesQuotes.WorkWithSalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Util;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import pages.SalesQuotes.WorkWithSalesQuotesPage;
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
        waitForElementClickable(WorkWithSalesQuotesPage.nextBtn,10);
        click(WorkWithSalesQuotesPage.nextBtn, "Click Next Button");
    }

    /**
     * This method to click Back button
     *
     */
    public void clickBackBtn() {
        waitForElementClickable(WorkWithSalesQuotesPage.backBtn,10);
        click(WorkWithSalesQuotesPage.backBtn, "Click back Button");
    }

    /**
     * This method to Create Quote
     *
     */
    public void createQuote() {
        click(WorkWithSalesQuotesPage.createQuote,"Click Create Quote");
        String custNumber=jsonData.getData("CustNum");
        String jobName=jsonData.getData("JobName");
        Utility_Functions.xSendKeys(ownDriver.findElement(WorkWithSalesQuotesPage.custNumberCret),custNumber);
        Utility_Functions.xSendKeys(ownDriver.findElement(WorkWithSalesQuotesPage.quoteJobName),jobName);
        clickNextBtn();
        clickNextBtn();
    }

    /**
     * This method to add/Edit Item
     *
     */
    public void addItemToQuote() {
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(WorkWithSalesQuotesPage.typeItem),0);
        waitForVisible(WorkWithSalesQuotesPage.itemNumber);
        System.out.println("After Item no Wait");
        String itemNo=Utility_Functions.getText(ownDriver, WorkWithSalesQuotesPage.itemNumber);
        Utility_Functions.xUpdateJson("SQItem",itemNo);
        System.out.println("itemNo: "+itemNo);
        String desc=jsonData.getData("TestDescription");

        sendKey(WorkWithSalesQuotesPage.testDesc,desc);
        sendKey(WorkWithSalesQuotesPage.mtxCost,"1");
        sendKey(WorkWithSalesQuotesPage.poCost,"1");
        sendKey(WorkWithSalesQuotesPage.listPrc,"2");
        click(WorkWithSalesQuotesPage.accept,"Click F9=Accept");
        sendKey(WorkWithSalesQuotesPage.qty,"1");
    }

    public void verifyQuoteDetails()
    {
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        String itemNm=ownDriver.findElement(WorkWithSalesQuotesPage.itemNumberItem).getText();
        itemN = itemNm.trim();
        Utility_Functions.xAssertEquals(report,itemN,Utility_Functions.xGetJsonData("SQItem"),"Item Number: ");
        clickBackBtn();
    }

    /**
     * This method to Convert to Sales Order
     *
     */
    public void convertSaleOrd() {
        click(WorkWithSalesQuotesPage.convert,"Click Convert");
        click(WorkWithSalesQuotesPage.overRide,"Click Override");
        click(WorkWithSalesQuotesPage.nextBtn,"Click Next Button");
        String itemNo=ownDriver.findElement(WorkWithSalesQuotesPage.itemConvert).getText();
        String act_desc=jsonData.getData("TestDescription");
        String act_Qty=jsonData.getData("Quantity");
        String qty=ownDriver.findElement(WorkWithSalesQuotesPage.qtyConvert).getText().trim();
        String backQty=ownDriver.findElement(WorkWithSalesQuotesPage.backOrdQty).getText().trim();
        String exp_desc=ownDriver.findElement(WorkWithSalesQuotesPage.itemDescConvert).getText().trim();
        String expItemNo=itemNo.trim();
        Utility_Functions.xAssertEquals(report,expItemNo,itemN,"Item Number: ");

        Utility_Functions.xAssertEquals(report,exp_desc,act_desc,"Item Description: ");
        Utility_Functions.xAssertEquals(report,qty,act_Qty+".0","Original Quantity: ");
        Utility_Functions.xAssertEquals(report,backQty,"1.0","Back order quantity: ");
        click(WorkWithSalesQuotesPage.process,"Click Process Button");
    }


    /**
     * This method to verify Customer and JobName
     *
     */
    public String validateCustJobName() {
        System.out.println("validateCustJobName");
        String cust=ownDriver.findElement(WorkWithSalesQuotesPage.billToAcc).getAttribute("value");
        System.out.println("cust: "+cust);
        String jobNm=ownDriver.findElement(WorkWithSalesQuotesPage.txtJobName).getAttribute("value");
        System.out.println("jobNm: "+jobNm);
        salesOrder=ownDriver.findElement(WorkWithSalesQuotesPage.salesOrderNum).getAttribute("value");
        Utility_Functions.xUpdateJson("SOSmoke",salesOrder);
        Utility_Functions.xUpdateJsonWithArray("SOSmoke1",salesOrder);
        System.out.println("Sales Order: "+salesOrder);
        String custNumber=jsonData.getData("CustNum");
        String jobName=jsonData.getData("JobName");
        Utility_Functions.xAssertEquals(report,custNumber,cust,"Customer Number: ");
        Utility_Functions.xAssertEquals(report,jobName,jobNm,"Job Name: ");
        click(WorkWithSalesQuotesPage.nextButton,"Click Next Button");
        return salesOrder;
    }

    /**
     * This method to verify Inventory Information
     *
     */
    public void inventoryInformation() {
        click(WorkWithSalesQuotesPage.itemsTab);
        String itemNo=Utility_Functions.getText(ownDriver, WorkWithSalesQuotesPage.ordInfItemNo);
        String itemDesc=Utility_Functions.getText(ownDriver, WorkWithSalesQuotesPage.ordInfItemDesc);
        String bckQty=Utility_Functions.getText(ownDriver, WorkWithSalesQuotesPage.ordInfBackOrd);
        String qty=ownDriver.findElement(WorkWithSalesQuotesPage.ordInfQty).getAttribute("value");
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
        click(WorkWithSalesQuotesPage.shipmentTab,"Click Shipments Tab");
    }

    /**
     * This method to save and Exit
     *
     */
    public void saveExt() {
        click(WorkWithSalesQuotesPage.saveExt,"Click Save & Exit Button");
    }

    /**
     * This method to click Exit button
     *
     */
    public void clkExit() {
        click(WorkWithSalesQuotesPage.extBtn,"Click Exit Button");
    }

    /**
     * This method to create Purchase order
     *
     */
      public void createPurchaseOrd() {
        shipmentTab();
        Utility_Functions.rightClick(ownDriver, WorkWithSalesQuotesPage.rgtClk);
        click(WorkWithSalesQuotesPage.crtPcsOrd,"Click create Purchase order");
        click(WorkWithSalesQuotesPage.cntEntShp,"Click Convert Entire Shipment");
        click(WorkWithSalesQuotesPage.nextButton,"Click Next Button");
        click(WorkWithSalesQuotesPage.searchBtn);
        sendKey(WorkWithSalesQuotesPage.sltOpt,"1");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(WorkWithSalesQuotesPage.freightCode),1);
        click(WorkWithSalesQuotesPage.createPO,"Click Create PO Button");
        purchaseOrder=Utility_Functions.getText(ownDriver, WorkWithSalesQuotesPage.purchaseOrdNo);
        Utility_Functions.xUpdateJson("POSmoke",purchaseOrder);
        Utility_Functions.xUpdateJsonWithArray("POSmoke1",purchaseOrder);
        System.out.println("purchaseOrder: "+purchaseOrder);
        click(WorkWithSalesQuotesPage.backBtn,"Click Back Button");
        String RelPO=Utility_Functions.getText(ownDriver, WorkWithSalesQuotesPage.relatedPO);
        Utility_Functions.xAssertEquals(report,"Related P.O.",RelPO,"Related P.O.: ");
        saveExt();
        clkExit();
        click(WorkWithSalesQuotesPage.saleQExtBtn,"Click Exit Button");
        click(WorkWithSalesQuotesPage.saleQExtBtn,"Click Exit Button");
    }



    /**
     * This method exit from SalesQuotes To MasterPage
     *
     */
    public void exitSalesQuotesToMasterPage() {

        commonObj.exitSalesQuotesToMasterPage();
    }

}