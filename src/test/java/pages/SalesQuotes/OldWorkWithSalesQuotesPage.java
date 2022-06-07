package pages.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;
//Working on deprecating
public class OldWorkWithSalesQuotesPage extends ReusableLib {
    public OldWorkWithSalesQuotesPage(Helper helper) {
        super(helper);
    }
    public static By createQuote=By.id("btnCF06");
    public static By searchJobName=By.id("S1JOBNME");
    public static By searchCust=By.id("VDFILTER");
    public static By creationDate=By.id("VDPOSTODT");
    public static By custNumberCret=By.xpath("//input[@id='S1CUST']");
    public static By quoteJobName=By.xpath("//input[@id='S1JOBNME']"); //Redundant with line 12?
    public static By nextBtn=By.id("btnSubmit");
    public static By quoteNumber=By.id("S1NBR"); //Not found
    public static By custSearch=By.id("S1BILL"); //Not Found
    public static By typeItem=By.id("S5TYPE");
    public static By qty=By.id("S5QTY");
    public static By testDesc=By.id("I_4_23"); //Not Found
    public static By mtxCost=By.id("I_9_23"); //Not Found
    public static By poCost=By.id("I_10_23"); //Not Found
    public static By listPrc=By.id("I_11_23"); //Not Found
    public static By accept=By.id("btnCF09");

    public static By process=By.id("btnCF09"); //Redundant with line 26?
    public static By itemNumber=By.id("D_3_23"); //Not Found
    public static By itemNumberItem=By.id("S1ITEM.1");
    public static By salesQuote=By.id("combined quote version");
    public static By itemConvert=By.id("SFITEM.1"); //Not Found
    public static By itemDescConvert=By.id("SFDSC1.1");
    public static By qtyConvert=By.id("SFTOCVTQT.1");
    public static By backOrdQty=By.id("SFIQTYN.1");

    public static By billToAcc=By.id("inBillToAcct"); //Not Found
    public static By txtJobName=By.id("txtJobname"); //Not Found
    public static By salesOrderNum=By.id("inOrderNum"); //Not Found
    public static By ordInfItemNo=By.id("outItemNumber.1"); //Not Found
    public static By ordInfItemDesc=By.id("outSbfItemDesc3.1"); //Not Found
    public static By ordInfQty=By.id("outSbfQtyOrd.1"); //Not Found
    public static By sltOpt=By.id("I_8_2"); //Not Found
    public static By freightCode=By.id("slbFreightCode"); //Not Found
    public static By createPO=By.id("btnAdd"); //Not Found
    public static By relatedPO=By.id("Related_PO.1"); //Not Found
    public static By saveExt=By.id("btnSaveExit"); //Not Found
    public static By extBtn=By.id("btnExitBill"); //Not Found
    public static By backBtn=By.id("btnBack");
    public static By searchBtn=By.id("imgVendorSearch"); //Not Found
    public static By overRide=By.id("btnCF09"); //Redundant with lines 28 and 26?
    public static By nextButton=By.id("btnNext"); //Not Found
    public static By saleQExtBtn=By.id("btnCF03");
    public static By convert = By.xpath("//a[text()='Convert']"); //Change to ID
    public static By exitIR=By.id("RC1CTL_linkCF03"); //Not Found
    public static By ordInfBackOrd = By.xpath("//div[@id='outQtyBackorder.1']/a"); //Not Found
    public static By purchaseOrdNo = By.xpath("//div[@id='outPONumber.1']/a"); //Not Found
    public static By shipmentTab = By.xpath("//*[text()='Shipments']"); //Not Found
    public static By itemsTab = By.xpath("//*[text()='Items']"); //Not Found
    public static By rgtClk = By.xpath("//label[@for='My Account.1']"); //Not Found
    public static By crtPcsOrd = By.xpath("//td[text()='Create Purchase Order']"); //Not Found
    public static By cntEntShp = By.xpath("//td[text()='Convert Entire Shipment']"); //Not Found
    public static By workingSalesQuotes = By.xpath("//div[text()='1']/following-sibling::div/a[contains(text(),'Work')]"); //Not Found

//Inventory Receipt
//Does this section even need to be here?
//There's already an InventoryReceiptPage
    public static By purcOrdNo=By.id("VDORNM");
    public static By exitBtn=By.id("OP18_linkCF03");
    public static By proc=By.id("RC1CTL_linkCF09"); //Not Found? The Inv Rec page has an ID for btnCF09, which is the process button
    public static By qtyRec=By.id("VQR.1"); //Not Found
    public static By itmDesc=By.id("DS.1"); //Not Found
    public static By itemNo=By.id("IM.1"); //Not Found
    public static By relSaleOrd=By.xpath("//div[@id='Hyperlink1.1']/a"); //Not Found

}
