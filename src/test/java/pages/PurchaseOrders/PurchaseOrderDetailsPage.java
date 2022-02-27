package pages.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class PurchaseOrderDetailsPage  extends ReusableLib {

    public PurchaseOrderDetailsPage(Helper helper) {
        super(helper);
    }

    public static By PODHeader= By.id("D_1_16");
    public  static By action1=By.id("D_2_3");
    public static By lineNo=By.id("D_3_3");
    public static By shipVia=By.id("D_4_3");
    public static By orderno=By.id("D_2_37");
    public static By weight=By.id("D_2_57");
    public static By vendor=By.id("D_3_37");
    public static By shipViaPOD=By.id("D_4_3");
    public static By customerPOD=By.id("D_4_37");
    public static By quantityOrdered=By.id("I_8_6");
    public static By quantityOpen=By.id("D_8_15");
    public static By itemNumberPOD=By.id("I_8_23");
    public static By pricePOD=By.id("I_8_43");
    public static By umPOD=By.id("I_8_58");
    public static By disc=By.id("I_8_61");
    public static By extendedAmount=By.id("I_8_68");
    public static By descPOD=By.id("I_9_8");
    public static By line2=By.id("I_9_48");
   public static By vendorPart=By.id("I_10_18");
   public static By relatedSo=By.id("I_10_52");
   public static By datePOD=By.id("I_11_9");
   public static By weightPOD=By.id("I_11_26");
   public static By pkgQty=By.id("I_11_45");
   public static By convFactor=By.id("D_11_52");
   public static By ediStat=By.id("D_2_15");
   public static By sendViaFax=By.id("D_3_33");
   public static By tyPOD=By.id("I_8_3");

  public static By firstItemNumber=By.id("I_11_3");
  public static By secondItemNumber= By.id("I_12_3");
  public static By errorMsgPOD=By.id("D_24_2");
  public static By getExtendedAmountPOD=By.id("I_8_68");

  public static By getLineItemsList=By.xpath("//div[string-length(normalize-space(text()))>75 ]");

  public static By amountHeader=By.id("D_1_65");
  public static By convFactorValue = By.id("D_11_65");
  public static By orderNo2 = By.id("D_2_49");
  public static By btnSubmit=By.id("btnSubmit");

  /******************************* Actions panel locators *******************************/

  public static By f4 = By.id("btnCF04");


}

