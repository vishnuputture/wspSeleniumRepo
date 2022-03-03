package pages.PurchaseOrders;

import org.openqa.selenium.By;

public class InventoryReceiptPage {
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
    public static By processBtn = By.id("btnCF09");
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

}
