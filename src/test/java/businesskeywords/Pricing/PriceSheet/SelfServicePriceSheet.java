package businesskeywords.Pricing.PriceSheet;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;

public class SelfServicePriceSheet extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    CommonActions commonObj;

    public SelfServicePriceSheet(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    public void navigateToSelfServicePriceSheet()
    {
        driver.get(jsonData.getData("UrlSelfService"));

    }
}
