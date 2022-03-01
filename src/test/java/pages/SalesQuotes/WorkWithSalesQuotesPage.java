package pages.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class WorkWithSalesQuotesPage extends ReusableLib {

    public WorkWithSalesQuotesPage(Helper helper) {
        super(helper);
    }
    public static By createQuote=By.id("btnCF06");
    public static By searchJobName=By.id("S1JOBNME");
    public static By searchCust=By.id("VDFILTER");
    public static By creationDate=By.id("VDPOSTODT");
    public static By custNumberCret=By.xpath("//input[@id='S1CUST']");
    public static By quoteJobName=By.xpath("//input[@id='S1JOBNME']");
    public static By nextBtn=By.id("btnSubmit");
    public static By quoteNumber=By.id("S1NBR");
    public static By custSearch=By.id("S1BILL");
    public static By typeItem=By.id("S5TYPE");
    public static By qty=By.id("S5QTY");
    public static By testDesc=By.id("I_4_23");
    public static By mtxCost=By.id("I_9_23");
    public static By poCost=By.id("I_10_23");
    public static By listPrc=By.id("I_11_23");
    public static By accept=By.id("btnCF09");
    public static By process=By.id("btnCF09");
    public static By itemNumber=By.id("D_3_23");
    public static By itemNumberItem=By.id("S1ITEM.1");
    public static By salesQuote=By.id("combined quote version");
    public static By itemConvert=By.id("SFITEM.1");
    public static By itemDescConvert=By.id("SFDSC1.1");
    public static By qtyConvert=By.id("SFTOCVTQT.1");
    public static By backOrdQty=By.id("SFIQTYN.1");

    public static By billToAcc=By.id("inBillToAcct");
    public static By txtJobName=By.id("txtJobname");
    public static By salesOrderNum=By.id("inOrderNum");
    public static By ordInfItemNo=By.id("outItemNumber.1");
    public static By ordInfItemDesc=By.id("outSbfItemDesc3.1");
    public static By ordInfQty=By.id("outSbfQtyOrd.1");
    public static By sltOpt=By.id("I_8_2");
    public static By freightCode=By.id("slbFreightCode");
    public static By createPO=By.id("btnAdd");
    public static By relatedPO=By.id("Related_PO.1");
    public static By saveExt=By.id("btnSaveExit");
    public static By extBtn=By.id("btnExitBill");
    public static By backBtn=By.id("btnBack");
    public static By searchBtn=By.id("imgVendorSearch");
    public static By overRide=By.id("btnCF09");
    public static By nextButton=By.id("btnNext");
    public static By saleQExtBtn=By.id("btnCF03");
    public static By convert = By.xpath("//a[text()='Convert']");
    public static By exitIR=By.id("RC1CTL_linkCF03");
    public static By ordInfBackOrd = By.xpath("//div[@id='outQtyBackorder.1']/a");
    public static By purchaseOrdNo = By.xpath("//div[@id='outPONumber.1']/a");
    public static By shipmentTab = By.xpath("//*[text()='Shipments']");
    public static By itemsTab = By.xpath("//*[text()='Items']");
    public static By rgtClk = By.xpath("//label[@for='My Account.1']");
    public static By crtPcsOrd = By.xpath("//td[text()='Create Purchase Order']");
    public static By cntEntShp = By.xpath("//td[text()='Convert Entire Shipment']");
    public static By workingSalesQuotes = By.xpath("//div[text()='1']/following-sibling::div/a[contains(text(),'Work')]");

//Inventory Receipt
    public static By purcOrdNo=By.id("VDORNM");
    public static By exitBtn=By.id("OP18_linkCF03");
    public static By proc=By.id("RC1CTL_linkCF09");
    public static By qtyRec=By.id("VQR.1");
    public static By itmDesc=By.id("DS.1");
    public static By itemNo=By.id("IM.1");
    public static By relSaleOrd=By.xpath("//div[@id='Hyperlink1.1']/a");

}
