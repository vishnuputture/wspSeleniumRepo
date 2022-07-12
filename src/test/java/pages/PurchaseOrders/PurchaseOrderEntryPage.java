package pages.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class PurchaseOrderEntryPage extends ReusableLib {

    public PurchaseOrderEntryPage(Helper helper) {
        super(helper);
    }

    public static By po_entry = By.id("D_6_7");
    public static By poHeaderTitle = By.id("D_1_16");
    public static By vendorNo = By.id("D_4_2");
    public static By zipCode = By.id("D_8_16");
    public static By orderNo = By.id("D_4_41");
    public static By shipTypeCode = By.id("D_9_7_W1");
    public static By shipTypeDesc = By.id("D_9_12_W1");
    public static By lastOrderNo = By.id("D_6_41");
    public static By soSuffix = By.id("D_12_28");
    public static By stateCode = By.id("D_8_8");
    public static By stateZip = By.id("D_9_39");
    public static By customerNo = By.id("D_5_2");
    public static By typeShipment = By.id("I_5_56");
    public static By poEntryHeader = By.id("D_1_29");
    public static By orderNoField = By.id("D_2_2");
    public static By orderNum = By.id("D_2_12");
    public static By CustNo = By.id("D_3_2");
    public static By CustNoTxtBx = By.id("I_3_12");
    public static By shipToTxt = By.id("D_3_35");
    public static By custBrowse = By.id("btnCF01");
    public static By shipToTxtBx = By.id("I_3_44");
    public static By stateField = By.id("D_8_2");
    public static By zipField = By.id("D_8_12");
    public static By shipToState = By.id("D_8_38");
    public static By shipToZip = By.id("D_8_48");
    public static By typeShip = By.id("D_2_30");
    public static By orderInf = By.id("D_11_1");
    public static By salesOdrNo = By.id("D_12_2");
    public static By custOrdNo = By.id("D_13_2");
    public static By paymentTerm = By.id("D_14_2");
    public static By salesOrderNo = By.id("D_12_21");

    public static By custOrdNoTxtBx = By.id("I_13_21");
    public static By createdSO = By.id("D_2_25_W1");
    public static By payTermBx = By.id("I_14_21");
    public static By jobNameBx = By.id("I_15_21");
    public static By DateReqBx = By.id("I_16_21");
    public static By shipViaBx = By.id("I_17_21");
    public static By salesPersonBx = By.id("I_18_21");
    public static By terrCodeBx = By.id("I_19_21");
    public static By prepaidBx = By.id("I_20_21");
    public static By dateShipBx = By.id("I_21_21");
    public static By BusinessDayBx = By.id("I_22_21");

    public static By createInNewBx = By.id("I_16_65");
    public static By freightZoneBx = By.id("I_17_65");
    public static By PickUpDelvBx = By.id("I_18_65");
    public static By FOBBx = By.id("I_19_65");
    public static By PickingSlipBx = By.id("I_20_65");
    public static By writtenBy = By.id("I_21_65");
    public static By placedBy = By.id("I_22_65");
    public static By firstVendor = By.id("I_8_2");
    public static By freightCharges = By.id("D_11_51");
    public static By enterFreightCharges = By.id("I_11_70");
    public static By shipViaBrowse = By.id("btnCF04");
    public static By shipViaSel = By.id("I_9_2_W1");
    public static By addressCust = By.id("D_8_5");

    public static By errorMsgPO = By.id("D_24_2");
    public static By vendorNoInput = By.id("I_4_16");
    public static By orderNoInput = By.id("I_4_56");
    public static By customerNoInput = By.id("I_5_16");
    public static By firstCustomer = By.id("I_8_2");

    public static By costOption = By.id("I_20_19");
    public static By costOptionLink = By.id("D_20_2");
    public static By firstCostOptionCode = By.id("I_6_2_W1");

    public static By actionInpput = By.id("I_2_61");
    public static By shipToinput = By.id("I_6_47");
    public static By toaddressLine1 = By.id("I_7_47");

    public static By shipDateInput = By.id("I_12_70");


    /***************************************** Purchase Order Inquiry page elements *****************************************/

    public static By ddnType = By.id("P1PTSHPTYP");
    public static By lstOptionsColumn = By.xpath("//div[starts-with(@id,'P1OPT')]/input");
    public static By lstOrderNumberColumn = By.xpath("//div[starts-with(@id,'P1ORDNUM')]");
    public static By btnNext = By.id("btnSubmit");
    public static By btnNextPage = By.xpath("//span[contains(@class,'next-paging-link') or (text()='Next')]");
    public static By tbxOrderNumber = By.id("I_3_56");

}
