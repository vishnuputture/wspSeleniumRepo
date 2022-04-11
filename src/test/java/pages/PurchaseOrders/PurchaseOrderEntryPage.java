package pages.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class PurchaseOrderEntryPage extends ReusableLib {

    public PurchaseOrderEntryPage(Helper helper) {
        super(helper);
    }

    public static By po_entry=By.id("D_6_7");
    public static By poHeaderTitle=By.id("D_1_16");
    public static By vendorNo=By.id("D_4_2");
    public static By orderNo=By.id("D_4_41");
    public static By customerNo=By.id("D_5_2");
    public static By typeShipment=By.id("I_5_56");

    public static By firstVendor=By.id("I_8_2");
    public static By freightCharges=By.id("D_11_51");
    public static By enterFreightCharges=By.id("I_11_70");

    public  static By errorMsgPO=By.id("D_24_2");
    public static By vendorNoInput=By.id("I_4_16");
    public static By orderNoInput=By.id("I_4_56");
    public static By customerNoInput=By.id("I_5_16");
    public static By firstCustomer=By.id("I_8_2");

    public static By costOption=By.id("I_20_19");
    public static By costOptionLink=By.id("D_20_2");
    public static By firstCostOptionCode=By.id("I_6_2_W1");

    public static By actionInpput=By.id("I_2_61");
    public static By shipToinput=By.id("I_6_47");
    public static By toaddressLine1=By.id("I_7_47");

    public static By shipDateInput=By.id("I_12_70");


    /***************************************** Purchase Order Inquiry page elements *****************************************/

    public static By ddnType = By.id("P1PTSHPTYP");
    public static By lstOptionsColumn = By.xpath("//div[starts-with(@id,'P1OPT')]/input");
    public static By lstOrderNumberColumn = By.xpath("//div[starts-with(@id,'P1ORDNUM')]");
    public static By btnNext = By.id("btnSubmit");
    public static By btnNextPage = By.xpath("//span[contains(@class,'next-paging-link') or (text()='Next')]");
    public static By tbxOrderNumber = By.id("I_3_56");

}
