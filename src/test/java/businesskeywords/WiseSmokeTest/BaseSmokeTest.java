package businesskeywords.WiseSmokeTest;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import pages.WiseSmokeTest.BaseSmokeTestPage;
import supportLibraries.Utility_Functions;

public class BaseSmokeTest extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public FrameworkDriver ownDriver;
    public CommonActions commonObj;

    public BaseSmokeTest(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }


    public void url() {
        String env = jsonData.getData("environment").toUpperCase();
        String url;
        switch (env) {

            case "STG":
                url = properties.getProperty("STGURL");
                break;

            case "PROD":
                url = properties.getProperty("PRODURL");
                break;

            case "QA":
                url = properties.getProperty("QAURL");
                break;

            default:
                throw new RuntimeException("Invalid Environment please check");
        }
        ownDriver.get(url);
    }

    public void login() {
        sendKeys(BaseSmokeTestPage.userName, jsonData.getData("userName"), "Entering User Name");
        sendKeys(BaseSmokeTestPage.passWord, jsonData.getData("passWord"), "Entering Password");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(BaseSmokeTestPage.headerTitle, "WISE - Wholesalers Information Services Executive", "Validating header title for main menu");
    }

    public void win() {
        sendKeysAndEnter(BaseSmokeTestPage.Where, "win " + jsonData.getData("winCompany"), "WIN to company " + jsonData.getData("winCompany"));
    }
}
