package businesskeywords.warehousing.Objects;

import com.mattermost.MattermostAPIHandler;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.common.LoginPage;
import pages.common.MasterPage;
import supportLibraries.Utility_Functions;

import java.io.Serializable;

public class User extends ReusableLib implements Serializable {
    private final CommonActions commonObj;
    private final FrameworkDriver ownDriver;
    private String name;
    private String password;
    private String environment;
    private String company;

    public User(Helper helper, String name, String password, String environment, String company) {
        super(helper);
        ownDriver = helper.getGSDriver();
        commonObj = new CommonActions(helper);
        this.name = name;
        this.password = password;
        this.environment = environment;
        this.company = company;
    }

    public User(Helper helper, String name, String environment, String company) {
        super(helper);
        ownDriver = helper.getGSDriver();
        commonObj = new CommonActions(helper);
        this.name = name;
        this.environment = environment;
        this.company = company;
    }
    public User() {
        super(null);
        ownDriver = helper.getGSDriver();
        commonObj = new CommonActions(helper);
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getDailyPassword(String environment) {
        String URL = "https://btjones1:Nobodyknows1(@daily.winwholesale.com/";
        ownDriver.get(URL);
        environment = environment.equalsIgnoreCase("prod") ? "Prod" : environment;
        click(By.xpath("//a[normalize-space()='"+environment+"']"));
        Utility_Functions.timeWait(4);
        return getText(By.xpath("//h2"));
    }

    public void getDailyPassword() {
        String URL = "https://btjones1:Nobodyknows1(@daily.winwholesale.com/";
        ownDriver.get(URL);
        click(ownDriver.findElement(By.xpath("//a[text()='"+environment+" ']")));
        Utility_Functions.timeWait(4);
        this.password = getText(By.xpath("//h2"));
    }

    public void loginToWebApp() {
        By logo = environment.equalsIgnoreCase("Prod") ? LoginPage.winLoginProd : LoginPage.winLogin;
        if (Utility_Functions.xWaitForElementPresent(ownDriver, logo, 5)) {
            sendKeys(environment.equalsIgnoreCase("Prod") ? LoginPage.prodUsername : LoginPage.userName, name);
            sendKeys(LoginPage.password, password);
            Utility_Functions.waitTillClickHardSleep(report, ownDriver, LoginPage.submit, "");
        }
    }

    public void loginToWISE() {
        String url = environment.equalsIgnoreCase("prod")
                ? "https://wise.winwholesale.com/wise?workstnid=&skin=WOBHighContrast"
                : "https://wisetest.winwholesale.com/wise?skin=WOBDark";
        ownDriver.get(url);
        sendKeys(LoginPage.userNametxtBox, name, "Entering username");
        sendKeysAndEnter(LoginPage.passWordtxtBox, password, "Entering password ******");
        if (Utility_Functions.xIsDisplayed(ownDriver, LoginPage.informationScreenTitle)) {
            MattermostAPIHandler.postMessage(properties.getProperty("STGUserName") + " password will expire soon");
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        }
        if (Utility_Functions.xIsDisplayed(ownDriver, LoginPage.pendingScreenTitle)) {
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        }
        sendKeysAndEnter(MasterPage.sqlTxtBox, "win " + company, "WIN to company "+ company);
    }

    public void navigateToURL() {

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
