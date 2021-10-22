package businesskeywords.common;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import pages.common.LoginPage;
import supportLibraries.Utility_Functions;
import org.openqa.selenium.Keys;

public class Login extends ReusableLib {

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */


    public Login(Helper helper) {
        super(helper);
    }


    /**
     * This method is invoked to login to WISE application
     */

    public void launchApp(){
        String env = getProperties("ENV");
        String url = properties.getProperty(env + "URL");
        driver.get(url);
        ngWaitRequestToFinish();
    }

    /**
     * Keyword to  enter user name and password and submit
     *
     */
    public void siteLogin() {
    	waitForVisible(LoginPage.signOnPageTitle);
    //	Utility_Functions.xUpdateJsonWithArray("Test", "1");
       // Utility_Functions.xUpdateJsonWithArray("Test", "2");
        String user = getProperties("UserName");
        String password = getProperties("Password");
        sendKeys(LoginPage.userNametxtBox, user, "Entering username " + user);
        sendKeys(LoginPage.passWordtxtBox, password, "Entering password ******");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        ngWaitRequestToFinish();
        if (Utility_Functions.xIsDisplayed(driver, LoginPage.pendingScreenTitle)) {
            Utility_Functions.actionKey(Keys.ENTER, driver);
        }
    }

    public void launchSelfServicePriceSheet(){
        String url = properties.getProperty("URLPriceSheet");
        driver.get(url);
        ngWaitRequestToFinish();
    }

    public void launchMakePayments(){
        String url = properties.getProperty("URLMakePayments");
        driver.get(url);
    //    ngWaitRequestToFinish();
    }
}
