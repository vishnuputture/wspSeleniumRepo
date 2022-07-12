package businesskeywords.common;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.mattermost.MattermostAPIHandler;
import com.winSupply.framework.selenium.FrameworkDriver;

import org.openqa.selenium.By;

import pages.common.LoginPage;
import pages.common.MasterPage;
import pages.warehouse.ReceivingInProcess.ReceivingInProcessPage;
import supportLibraries.Utility_Functions;
import org.openqa.selenium.Keys;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Login extends ReusableLib {

    private FrameworkDriver ownDriver;
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */


    public Login(Helper helper) {
        super(helper);
        ownDriver=helper.getGSDriver();
    }


    /**
     * This method is invoked to login to WISE application
     */

    public void launchApp() {
        String env = getProperties("ENV");
        String url = properties.getProperty(env + "URL");
        ownDriver.get(url);
        ngWaitRequestToFinish();
    }

    /**
     * Keyword to  enter user name and password and submit
     */
    public void siteLogin() {
        waitForVisible(LoginPage.signOnPageTitle);
        //	Utility_Functions.xUpdateJsonWithArray("Test", "1");
        // Utility_Functions.xUpdateJsonWithArray("Test", "2");


        String env = getProperties("ENV");
        String user = getProperties(env + "UserName");
        String password = getProperties(env + "Password");
        sendKeys(LoginPage.userNametxtBox, user, "Entering username " + user);
        sendKeys(LoginPage.passWordtxtBox, password, "Entering password ******");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        ngWaitRequestToFinish();
        if (Utility_Functions.xIsDisplayed(ownDriver, LoginPage.informationScreenTitle)) {
            MattermostAPIHandler.postMessage(properties.getProperty("STGUserName") + " password will expire soon");
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        }
        if (Utility_Functions.xIsDisplayed(ownDriver, LoginPage.pendingScreenTitle)) {
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        }

        if(env.equalsIgnoreCase("PROD")){
            sendKeys(MasterPage.sqlTxtBox,"where");
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
            String currentCompany = getText(MasterPage.companyLbl);
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
            String [] companyArr = getProperties("CompanyNumber").split(String.valueOf(','));
            List <String> companyList = Arrays.asList(companyArr);
            if(!companyList.contains(currentCompany)){
                sendKeys(MasterPage.sqlTxtBox,"win "+companyList.get(0));
                Utility_Functions.actionKey(Keys.ENTER, ownDriver);

            }
        }
    }


    public void launchSelfServicePriceSheet(){
        String url = properties.getProperty("URLPriceSheet"+properties.getProperty("ENV"));
        ownDriver.get(url);
        ngWaitRequestToFinish();
    }

    public void launchMakePayments() {
        String url = properties.getProperty("URLMakePayments");

        ownDriver.get(url);
    //    ngWaitRequestToFinish();
    }
}
