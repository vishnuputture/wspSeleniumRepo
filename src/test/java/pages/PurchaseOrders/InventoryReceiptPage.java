package pages.PurchaseOrders;

import org.openqa.selenium.By;

public class InventoryReceiptPage {
    public static By inventoryHeader=By.id("OP18_heading");
    public static By purchaseOrdNo=By.id("VDORNM");
    public static By vendorInvoiceNo=By.id("VDVIV#");
    public static By receivedBy=By.id("VDRCBY");
    public static By receiverDoc=By.id("WKRDOC");
    public static By receivedDate=By.id("VDDTSH");
    public static By receiverDocNotes=By.id("VDNOTE");
    public static By freightCharges=By.id("VDFRT");
    public static By exitBtn=By.id("OP18_linkCF03");
    public static By cancelPO=By.id("Btn_Cancel");
    public static By processBtn=By.id("btnCF09");
    public static By vendor=By.id("constant8");
    public static By lastOrdNo=By.id("OutputField2");
    public static By poInquiry=By.id("constant4");
    public static By poInqLink=By.xpath("//a[text()='Purchase Order Inquiry']");

}
