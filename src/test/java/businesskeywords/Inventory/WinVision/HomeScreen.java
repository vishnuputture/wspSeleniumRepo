package businesskeywords.Inventory.WinVision;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import pages.inventory.WinVisionPage;
import supportLibraries.Utility_Functions;


public class HomeScreen extends ReusableLib {
    CommonActions commonObj;
    private FrameworkDriver ownDriver;
    public HomeScreen(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    public void navigateToWinVisionHomePage() {
        String url = properties.getProperty("winVisionQA");
        ownDriver.get(url);
        commonObj.validateElementExists(WinVisionPage.verifyCredentialsButton, "Navigated to Login Page");

        String userName = properties.getProperty("CycleCostUserName");
        String Password = properties.getProperty("winVisionPass");
        waitForVisible(WinVisionPage.verifyCredentialsButton);
        sendKeys(WinVisionPage.usernameTextbox, userName, "Entering username " + userName);
        sendKeys(WinVisionPage.passwordTextbox, Password, "Entering password ******");
        click(WinVisionPage.verifyCredentialsButton, "Clicked on Verify Credentials Button");
        Utility_Functions.timeWait(5);
        waitForVisible(WinVisionPage.welcomeHeader);

    }

    public void verifyWinVisionHomePage() {

        commonObj.validateText(WinVisionPage.welcomeHeader, "Welcome!", "Validating [Welcome!] Page header");
        commonObj.validateText(WinVisionPage.welcomeSubHeader, "Welcome to the new WinVision", "Validating [Welcome to the new WinVision] Page sub-header");
        Utility_Functions.xHoverElementclicks(ownDriver.findElement(By.xpath("//div[@title='Menu']")),ownDriver);
        click(WinVisionPage.menuIcon);
        waitForVisible(WinVisionPage.onlineCatalog);
        waitForVisible(WinVisionPage.orderTracking);
        waitForVisible(WinVisionPage.commodityPricing);
        waitForVisible(WinVisionPage.servicePartners);
        waitForVisible(WinVisionPage.lineCards);
    }
}
