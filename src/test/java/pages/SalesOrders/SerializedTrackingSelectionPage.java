package pages.SalesOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SerializedTrackingSelectionPage extends ReusableLib {

    public SerializedTrackingSelectionPage(Helper helper) {
        super(helper);
    }
    public By serialNoEntry = By.id("I_4_20");
}
