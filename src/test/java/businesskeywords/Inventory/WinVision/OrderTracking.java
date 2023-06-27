package businesskeywords.Inventory.WinVision;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.testng.Assert;
import pages.common.MasterPage;
import pages.inventory.WinVisionPage;
import supportLibraries.Utility_Functions;
import static supportLibraries.Utility_Functions.*;

public class OrderTracking extends ReusableLib{

    CommonActions commonObj;
    private FrameworkDriver ownDriver;
    public OrderTracking(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    public void navigateToOrderTrackingAndVerifyUI(){
        commonObj.validateText(WinVisionPage.welcomeHeader, "Welcome!", "Validating [Welcome!] Page header");
        Utility_Functions.xHoverElementclicks(ownDriver.findElement(By.xpath("//div[@title='Menu']")),ownDriver);
        click(WinVisionPage.menuIcon);
        waitForVisible(WinVisionPage.orderTracking);
        click(WinVisionPage.orderTracking);
        Utility_Functions.timeWait(5);
        waitForVisible(WinVisionPage.pageHeader);
        commonObj.validateText(WinVisionPage.pageHeader, "Order Tracking", "Validating Header [Order Tracking ]");
        waitForVisible(WinVisionPage.searchFilterHeader);
    }

    public void verifyOTHelpIcon() {
        click(WinVisionPage.helpIcon);
        waitForVisible(WinVisionPage.orderTrackingPopUp);
        commonObj.validateText(WinVisionPage.otPopUpBody, "Tracks visibility into local company shipments coming from a given RDC.", "Validating message [Tracks visibility into local company shipments coming from a given RDC.]");
        commonObj.validateElementExists(WinVisionPage.closePopUpButton, "Validated presence of close button");
        click(WinVisionPage.closePopUpButton);
        waitForElementDisappear(WinVisionPage.closePopUpButton, globalWait);
        Boolean value = xWaitForElementDisappear(ownDriver, WinVisionPage.orderTrackingPopUp, globalWait );
        Assert.assertTrue(value);
    }
    public void verifyUIWithoutRecord(){
        commonObj.validateElementExists(WinVisionPage.helpIcon, "Validated presence of Help icon");
        commonObj.validateText(WinVisionPage.noResultText, "There are no orders to be tracked at this time", "Validating message [There are no orders to be tracked at this time]");
        waitForVisible(WinVisionPage.searchFilterHeader);
    }

}
