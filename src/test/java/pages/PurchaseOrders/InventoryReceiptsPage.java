package pages.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class InventoryReceiptsPage extends ReusableLib{

    public InventoryReceiptsPage(Helper helper) {
        super(helper);
    }

    public static By pageTitle = By.id("OP18_heading");
    public static By tbxPONumber = By.id("VDORNM");
    public static By tbxQtyRcvd = By.id("VQR.1");
    public static By btnExit = By.id("RC1CTL_linkCF03");
    public static By btnProcess = By.id("RC1CTL_linkCF09");
    public static By chbxEnableDisableCost = By.id("Checkbox2");
    public static By growlText = By.className("growl-text");
    public static By lstUnitCostTbx = By.xpath("//input[starts-with(@id,'VUC')]");
    public static By lstRelatedSalesOrder = By.xpath("//div[starts-with(@id,'Hyperlink')]/a");
    public static By pageNo = By.className("paging-number");
    public static By btnPrevious = By.xpath("//span[contains(@class,'previous-paging-link') or (text()='Previous')]");
    public static By btnNext = By.xpath("//span[contains(@class,'next-paging-link') or (text()='Next')]");
    public static By btnCancelPO = By.xpath("//input[@value='Cancel Purchase Order']");
    public static By btnPOInquiry = By.id("op18_cf01");
    public static By purchaseOrderInquiry = By.xpath("//a[text()='Purchase Order Inquiry']");
    public static By tbxFreightCost = By.id("VUF.1");
    public static By receiverDocument = By.id("RCFCTL_WKRDOC");
    public static By receivedDate = By.id("RCFCTL_VDDTSH");
    public static By freightCode = By.id("RCFCTL_VDFRT");
    public static By freightAmount = By.id("FAMT");
    public static By grossMargin = By.id("VDGMMR");

    /**************************************** Sales Order Inquiry page ****************************************/

    public static By hdrSalesOrderInquiry = By.id("outConstant4");
    public static By tbxOrderNo = By.id("txtS1FLTRORD");

    /**************************************** Cancel Purchase Order popup elements ****************************************/

    public static By hdrCancelPO = By.xpath("//div[text()='CANCEL PURCHASE ORDER']");
    public static By msgWarning = By.xpath("//div[@id='OutText']/p");
    public static By btnNo = By.xpath("//input[@value='No']");
    public static By btnYes = By.xpath("//input[@value='Yes']");
    public static By btnContinue = By.id("btnContinue");

    /************************* QUANTITY RECEIVED IS GREATER THAN DIRECT SHIP BOQUANTITY popup elements *************************/

    public static By hdrOverReceivedPOPopup = By.xpath("//div[starts-with(text(),'Quantity Received is Greater')]");
    public static By btnUpdateOnHandQty = By.id("btnCF21");
    public static By btnUpdateSO = By.id("btnCF18");

    /**************************************** Freight Allocation popup ****************************************/

    public static By allocateFreight = By.id("DEFFRT");
    public static By amountToAllocate = By.id("VDFAMT");
}
