package businesskeywords.SalesQuotes.WorkWithSalesQuotes;

import businesskeywords.SalesQuotes.WorkWithSalesQuotes.WorkWithSalesQuote;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import pages.pricing.OrderByCustomerPage;
import supportLibraries.Utility_Functions;

public class ShippingConfirmation  extends ReusableLib {
    CommonActions commonObj=new CommonActions(helper);
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public ShippingConfirmation(Helper helper) {
        super(helper);
    }

    /**
     * This method navigate To Sipping Confirmation Page
     *
     */
    public void navigateToShippingConfirmation() {
        commonObj.masterToOrderProcessing();
        click(pages.pricing.ShippingConfirmation.shipConf,"Click Shipping Confirmation Program");
    }

    /**
     * This method to Order Confirmation
     *
     */
    public void orderConfirmation() {
        Utility_Functions.actionKey(Keys.ENTER, driver);
        sendKeys(pages.pricing.ShippingConfirmation.orderNum1,Utility_Functions.xGetJsonData("SOSmoke"));
        sendKeys(pages.pricing.ShippingConfirmation.orderNum2,"01");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        sendKeys(pages.pricing.ShippingConfirmation.qtyToShip,"1");
        click(pages.pricing.ShippingConfirmation.confOrder,"Click Confirm Order");
    }

    /**
     * This method navigate To exit from Shipping COnfirmation Program
     *
     */
    public void exitShippingConfirmation() {
        Utility_Functions.actionKey(Keys.F3, driver);
        click(pages.pricing.ShippingConfirmation.exitShipConf,"Exit from Shipping Confirmation Program");
    }


}
