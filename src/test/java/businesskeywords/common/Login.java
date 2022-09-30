package businesskeywords.common;

import com.mattermost.MattermostAPIHandler;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Settings;
import com.winSupply.framework.selenium.FrameworkDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.common.LoginPage;
import pages.common.MasterPage;
import pages.warehouse.SmokeTestPage;
import supportLibraries.Utility_Functions;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Login extends ReusableLib {

    public static Properties properties = Settings.getInstance();
    private FrameworkDriver ownDriver;
    private String environment = "DEV";
    private String company = "99599";
    private String username = "wztestqa";

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */


    public Login(Helper helper) {
        super(helper);
        ownDriver = helper.getGSDriver();
    }

    public Login(Helper helper, String env, String comp) {
        this(helper);
        environment = env.isEmpty() ? environment : env.toUpperCase();
        environment = environment.equals("PROD") ? "Prod" : environment;
        company = comp.isEmpty() ? company : comp;
    }

    public Login(Helper helper, String env, String comp, String user) {
        this(helper, env, comp);
        username = user;
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

        if (env.equalsIgnoreCase("PROD")) {
            sendKeys(MasterPage.sqlTxtBox, "where");
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
            String currentCompany = getText(MasterPage.companyLbl);
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
            String[] companyArr = getProperties("CompanyNumber").split(String.valueOf(','));
            List<String> companyList = Arrays.asList(companyArr);
            if (!companyList.contains(currentCompany)) {
                sendKeys(MasterPage.sqlTxtBox, "win " + companyList.get(0));
                Utility_Functions.actionKey(Keys.ENTER, ownDriver);

            }
        }
    }


    public void launchSelfServicePriceSheet() {
        String url = properties.getProperty("URLPriceSheet" + properties.getProperty("ENV"));
        ownDriver.get(url);
        ngWaitRequestToFinish();
    }

    public void launchMakePayments() {
        String url = properties.getProperty("URLMakePayments");

        ownDriver.get(url);
        //    ngWaitRequestToFinish();
    }
    public void getDailyPassword() {
        String URL = "https://vprasad:Y25EpK!qSg@daily.winwholesale.com/";
        ownDriver.get(URL);
        click(ownDriver.findElement(By.xpath("//a[text()='"+environment+" ']")));
        Utility_Functions.timeWait(4);
        String pass = getText(By.xpath("//h2"));
        Utility_Functions.xUpdateJson(environment+"Password", pass);
        //System.out.println(environment + " " + pass +" "+ properties.getProperty(environment+"Password") + " " + properties.getProperty("GlobalUserName"));
    }

    public void winLogin() {
        By logo = environment.equals("Prod") ? LoginPage.winLoginProd : LoginPage.winLogin;
        if (Utility_Functions.xWaitForElementPresent(ownDriver, logo, 5)) {
            sendKeys(environment.equals("Prod") ? LoginPage.prodUsername : LoginPage.userName, username);
            sendKeys(LoginPage.password, Utility_Functions.xGetJsonData(environment+"Password"));
            Utility_Functions.waitTillClickHardSleep(report, ownDriver, LoginPage.submit, "");
        }
    }
    public void selectCompany() {
        if (isDisplayed(LoginPage.toasterCloseIcon)) {
            click(LoginPage.toasterCloseIcon);
        }
        if (!Utility_Functions.xIsDisplayed(ownDriver, LoginPage.pageTitle)) {
            Utility_Functions.waitTillClickHardSleep(report, ownDriver, LoginPage.companySelector, "");
            click(LoginPage.companyLabel);
            if (System.getProperty("company") == null) {
                sendKey(LoginPage.winCompanyNumber, company);
            } else {
                sendKey(LoginPage.winCompanyNumber, System.getProperty("company"));
            }
            click(LoginPage.selectButton);
        }
    }
}
