package pages.SalesOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SerializedTrackingSelectionPage extends ReusableLib {
    public SerializedTrackingSelectionPage(Helper helper) {
        super(helper);
    }

    public static By serialNoEntry = By.id("I_4_20");
    public static By serializedHeader = By.id("D_2_15");
    public static By btnEnterSerialNum = By.id("btnCF11");
    public static By serialNumField = By.id("I_11_7");
    public static By btnProcessSerialNum = By.id("btnCF09");
    public static By btnBack = By.id("btnCF12");
}
