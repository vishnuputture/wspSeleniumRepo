package pages.pricing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class ShippingConfirmation extends ReusableLib {

    public ShippingConfirmation(Helper helper) {
        super(helper);
    }

    public static By shipConf=By.xpath("//div[text()='6']/following-sibling::div/a[contains(text(),'Shipping')]");
    public static By orderNum1=By.id("I_4_16");
    public static By orderNum2=By.id("I_4_23");
    public static By confOrder=By.id("btnAdd");
    public static By exitShipConf=By.id("btnExit");
    public static By qtyToShip=By.id("InQtyToShp.1");
}
