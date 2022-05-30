package pages.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class VendorInvoiceReconciliationPage extends ReusableLib {

    public VendorInvoiceReconciliationPage(Helper helper) {
        super(helper);
    }

    public static By vendorInvoiceRecHeader = By.id("D_1_20");
    public static By PoMainMenuHeader = By.id("D_2_27");
    public static By receiverDoc = By.id("I_4_17");
    public static By selectVendor = By.id("I_12_2");
    public static By operatorTextBox = By.id("I_2_17");
    public static By dueDateTextBox = By.id("I_5_17");
    public static By receivedDate = By.id("D_6_17");
    public static By vendorInvTextBox = By.id("I_7_17");
    public static By invoiceTotalTextBox = By.id("I_9_17");
    public static By discountPercentTextBox = By.id("I_10_17");
    public static By amountTextBox = By.id("I_11_17");
    public static By allocMethodTextBox = By.id("I_14_17");
    public static By custFrtAmountTextBox = By.id("I_15_17");
    public static By debitMemoTextBox = By.id("I_16_17");
    public static By transDateTextBox = By.id("I_17_17");
    public static By detailedValue = By.id("D_18_17");
    public static By paymentTerms = By.id("I_19_17");
    public static By netVal = By.id("D_19_21");
    public static By warMessage = By.id("D_21_2");
    public static By orderNo = By.id("D_3_51");
    public static By originalVendor = By.id("I_4_51");
    public static By vendor = By.id("D_5_51");
    public static By recDocNote = By.id("D_6_51");
    public static By freightAmountTextBox = By.id("I_13_17");
    public static By totalNetDue = By.id("D_12_17");
    public static By invoicingVdrTextBox = By.id("I_7_51");
    public static By receivedBy = By.id("D_9_51");
    public static By gmMarginAmt = By.id("D_11_51");
    public static By freightCharges = By.id("I_13_55");
    public static By allocationMethod = By.id("D_14_19");
    public static By qtyManual = By.id("D_15_32");
    public static By dollarCustPayFreight = By.id("D_16_32");
    public static By byWeightNotAllowed = By.id("D_17_26");
    public static By boxHeader=By.xpath("//div[@class='box-header']");
    public static By f4Prompt=By.id("btnCF04");
    public static By newDoc=By.id("btnCF05");
    public static By moreKeys=By.id("btnCF24");

}
