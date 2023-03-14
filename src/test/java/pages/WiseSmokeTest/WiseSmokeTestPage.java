package pages.WiseSmokeTest;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;
import pages.pricing.ShippingConfirmation;

public class WiseSmokeTestPage extends ReusableLib {
    public WiseSmokeTestPage (Helper helper) {super(helper);}


    public static By userName = By.id("I_6_53");
    public static By passWord = By.id("I_7_53");
    public static By Where = By.id("I_22_7");
    public static By headerTitle = By.id("D_2_16");
    public static By whichLC = By.id("D_5_13_W1");
    public static By f12Cancel = By.id("btnCancel");
    public static By orderProcessing = By.id("D_7_7");
    public static By ONC = By.id("D_16_47");
    public static By backOrderONC = By.id("D_10_7");
    public static By alwaysNewOrderONC = By.id("I_18_65");
    public static By F3exit = By.id("btnExit");
    public static By Enter = By.id("btnSubmit");
    public static By SalesQuote = By.id("D_5_47");
    public static By WorkWithSQ = By.id("D_5_7");
    public static By WWSQPgmTitle = By.id("pgmTitle");
    public static By CreateSQ = By.id("btnCF06");
    public static By CustNum = By.id("S1CUST");
    public static By Search = By.id("fontAwesome1");
    public static By QuoteJobName = By.id("S1JOBNME");
    public static By NextCNSQ = By.id("btnSubmit");
    public static By BackCNSQ = By.id("btnBack");
    public static By SQPgmTitle = By.id("wbScrTitle");
    public static By SQNum = By.id("S1NBR");
    public static By SQEmail = By.id("inpEmail");
    public static By Type =  By.id("S5TYPE");
    public static By SQTitle = By.xpath("//*[@id=\'wbScrTitle\']");
    public static By itemNumber = By.id("D_3_23");
    public static By itemDesc1 = By.id("I_4_23");
    public static By itemDesc2 = By.id("I_5_23");
    public static By matrixCost = By.id("I_9_23");
    public static By poCost = By.id("I_10_23");
    public static By listPrice = By.id("I_11_23");
    public static By Accept = By.id("btnCF09");
    public static By Qty = By.id("S5QTY");
    public static By BtnF3Exit = By.id("BtnF3_Exit");
    public static By SortQuote = By.xpath("//div[contains(text(),'Quote Number')]");//By.xpath("//*[@id=\'SQ120S1\']/div[2]/div/div");
    public static By PostoQuote = By.id("VDPOSTO");
    public static By btnBack = By.id("btnBack");
    public static By SQOptionsDD = By.id("S1Opt.1");
    public static By SQOptionsField = By.xpath("(//input[@name='gdbnpy'])[1]");
    public static By Convert = By.xpath("(//div[normalize-space()='13-Convert'])[1]");
    public static By SONum = By.id("SONBR");
    public static By soQtyShippedDD = By.id("SCQTYSHP");
    public static By shipmentsTab = By.xpath(("//*[@id='TabPanel1']/div[2]/span[3]/span[2]"));
    //public static By shipmentsTab = By.xpath("By.xpath(\"//span[text()='Shipments'][@istab='true']\");");
    public static By rgtClk = By.id("My Account.1");
    public static By createPcsOrd = By.xpath("//td[text()='Create Purchase Order']");
    public static By convertEntShp = By.xpath("//td[text()='Convert Entire Shipment']");
    public static By counterPicks = By.id("counterPickLink.1");
    public static By searchBtn = By.id("imgVendorSearch");
    public static By btnNext = By.id("btnNext");
    public static By ddnFreightCode = By.id("slbFreightCode"); //Full Frt Allowed/ NO Frt Chrg.
    public static By btnCreatePO = By.id("btnAdd");
    public static By txtPONumber = By.id("outPONumber.1");
    public static By relatedPO = By.id("Related_PO.1");
    public static By hdrRelatedPO = By.xpath("//h3[text()='Related Purchase Orders']");
    public static By btnSaveExit = By.id("btnSaveExit");
    public static By btnExitcustTab = By.id("btnExitBill");
    public static By btnSave = By.id("btnSave");
    public static By btnEnter = By.id("btnEnter");
    public static By saleQExtBtn = By.id("btnCF03");

    public static By binLocationSearch = By.id("faBinSearch.1");
    public static By firstBinSelect = By.id("chkBinLocation.1");

    public static By MMOptionbox = By.id("I_8_2");

    public static By inventoryHeader = By.id("OP18_heading");
    public static By purchaseOrdNo = By.id("VDORNM");
    public static By vendorInvoiceNo = By.id("VDVIV#");
    public static By unitCostField = By.id("VUC.1");
    public static By receivedBy = By.id("VDRCBY");
    public static By receiverDoc = By.id("WKRDOC");
    public static By qtyReceived = By.id("VQR.1");
    public static By displayProgramMes = By.id("D_1_16");
    public static By receivedDate = By.id("VDDTSH");
    public static By receiverDocNotes = By.id("VDNOTE");
    public static By freightCharges = By.id("VDFRT");
    public static By exitBtn = By.id("OP18_linkCF03");
    public static By orderInUsePopUp = By.id("hdrOrderInUse");
    public static By outOrderNum = By.id("outOrderNum");
    public static By outUserId = By.id("outUserId");
    public static By outWorkStation = By.id("outWorkStation");
    public static By btnContinue = By.id("btnContinue");
    public static By cancelPO = By.id("Btn_Cancel");
    public static By processButton = By.id("btnCF09");
    public static By vendor = By.id("constant8");
    public static By lastOrdNo = By.id("OutputField2");
    public static By poInquiry = By.id("constant4");
    public static By poInqIR = By.id("RC1CTL_VDORNM");
    public static By ReceivedByIN = By.id("RC1CTL_VDRCBY");
    public static By poInqLink = By.xpath("//a[text()='Purchase Order Inquiry']");
    public static By poHeaderLink = By.xpath("//a[text()='Purchase Order Header']");
    public static By headerIR = By.id("RC1CTL_heading");
    public static By ShipmentType = By.id("P1PTSHPTYP");
    public static By orderTotal = By.xpath("//div[contains(@id,'P1RECNME')]");
    public static By VendNameDet = By.id("outP4VENDNAME");
    public static By itmDesc = By.id("DS.1");
    public static By itemNo = By.id("IM.1");
    public static By relSaleOrd = By.id("Hyperlink1.1");
    public static By exitIR = By.id("RC1CTL_linkCF03");
    public static By mainInvProcessbtn = By.id("RC1CTL_linkCF09");
    public static By ReceiverDocNum = By.id("RC1CTL_WKRDOC");
    public static By FFATitle = By.id("OutputField1");
    public static By AllocateFreight = By.id("DEFFRT");
    public static By btnExitInvRec = By.id("OP18_linkCF03");



    public static By Operator = By.id("I_2_17");
    public static By ReceiverDocVIR = By.id("I_4_17");
    public static By VIRDueDate = By.id("I_5_17");

    public static By InvMngmnt = By.id("D_11_7");
    public static By ItemLedger = By.id("D_8_7");
    public static By itmnumledger = By.id("inpItemNbr");
    public static By SCline1 = By.id("outSC.1");
    public static By SCline2 = By.id("outSC.2");
    public static By QtyAfterLine1 = By.id("outQtyAfter.1");
    public static By QtyAfterLine2 = By.id("outQtyAfter.2");

    public static By OrderProcessingMenu = By.id("D_7_7");
    public static By SOInquiry = By.id("D_5_47");
    public static By SONumberSOI = By.id("txtS1FLTRORD");
    public static By SOline2 = By.xpath("(//div[@class='combo-main win-combo-option'])[2]");
    public static By SOENTRY9 = By.xpath("//div[normalize-space()='9-SO Entry']");
    public static By Itemstab = By.xpath("//*[@id=\'TabPanel1\']/div[2]/span[2]/span[2]");
    public static By BasicTab = By.id("CssButton1");
    public static By QtyToShip = By.id("outQtyToShip.1");
    public static By PaymentsTab = By.xpath("//*[@id=\'TabPanel1\']/div[2]/span[4]/span[2]");
    public static By PaymentMethod = By.id("PIpaymentMethod1");
    public static By CashMethod = By.xpath("//*[@id=\'PIpaymentMethod1\']/option[3]");
    public static By ApplyButton = By.id("applyPmtBtn1");
    public static By PrintNExit = By.id("btnSavePrint");
    public static By OrdAck = By.id("ImgOrderAck");
    public static By PackingList = By.id("ImgPacklist");
    public static By CreateOrdAcknow = By.id("I_7_40_W1");
    public static By PrintYesNo = By.id("I_10_14_W1");
    public static By PrinterName = By.id("I_10_16_W1");
    public static By SendTo = By.id("I_15_14_W1");
    public static By counterPickQuantity = By.id("InCntrPckQty.1");
    public static By confirmManualPicks = By.id("btnButton1");

    public static By ShippingConfirmation = By.id("D_10_7");
    public static By DaysBusiness = By.id("I_3_19");
    public static By SCOrdNum = By.id("I_4_16");
    public static By SCSuffix = By.id("I_4_23");
    public static By SCQTYShip = By.id("InQtyToShp.1");
    public static By SCConfirmOrder = By.id("btnAdd");
    public static By SCUnconfirmOrder = By.id("btnRmv");
    public static By SConfAppHeader = By.id("D_1_16");
    public static By SOILine2status = By.id("OutS1ordstsd.2");
    public static By SOILine3status = By.id("OutS1ordstsd.3");

    public static By Reports22 = By.id("D_14_47");
    public static By PrintInvoices = By.id("D_13_7");
    public static By IRselection = By.id("I_17_50");
    public static By ReportInvOrd1 = By.id("I_17_18");
    public static By ReportSuffOrd1 = By.id("I_17_25");

    public static By INVMgmnt = By.id("D_11_7");
    public static By SPInq = By.id("D_5_7");
    public static By SPINQQItemNumber = By.id("txtVDITM");
    public static By SPINQHeaderText = By.id("OutputField1");
    public static By BuyersInq = By.id("D_6_7");
    public static By BuyersInqHeaderText = By.id("D_1_30");
    public static By BuyersStockNumber = By.id("I_2_15");
    public static By BuyersItemDesc1 = By.id("D_2_34");
    public static By BuyersItemDesc2 = By.id("D_3_34");

    public static By LiveInvShopping = By.id("D_10_47");
    public static By LiveInvShopHeaderText = By.id("D_2_29");
    public static By Industry = By.id("I_6_62");
    public static By LivePosToItem = By.id("I_6_23");
    public static By FirstItemLiveShop = By.id("D_10_6");

    public static By PurchaseOrder = By.id("D_15_7");
    public static By POEntry = By.id("D_6_7");
    public static By ActionCodePOEntry = By.id("I_2_61");
    public static By POHeaderText = By.id("D_1_16");
    public static By PONumEntry = By.id("I_4_56");
    public static By VendorPOEntry = By.id("I_3_16");
    public static By POInquiry = By.id("D_5_47");
    public static By PostoOrderPOI = By.id("P1POSTOORD");
    public static By POIHeaderText = By.id("constant4");
    public static By POrderLine1 = By.id("P1ORDNUM.1");
    public static By POrderLine2 = By.id("P1ORDNUM.2");
    public static By POStatusLine1 = By.id("P1ORDSTS.1");
    public static By POStatusLine2 = By.id("P1ORDSTS.2");

    public static By signoff = By.id("D_18_7");

    public static By poStatusDropDown = By.id("ddbP1PTODRSTS");
    public static By poOptionDropDown = By.id("P1OPT.1");

    public static By SOENTRY5 = By.xpath("//div[normalize-space()='5-Display PDFs']");
    public static By optBox = By.id("I_11_3");

    public static By cancelF12 = By.id("btnCF12");

}

