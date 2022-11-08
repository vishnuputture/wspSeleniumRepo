package businesskeywords.Inventory.CycleCountRecommendation;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import pages.inventory.CycleCountRecommendationPage;
import pages.Purchasing.SPO.SpoPage;
import pages.common.MasterPage;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
import pages.warehouse.BinMaintenance.BinMaintenancePage;
import pages.warehouse.DriversPage;
import pages.warehouse.ManifestsPage;
import supportLibraries.Utility_Functions;

public class CycleCountRecommendation extends ReusableLib {

    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public CycleCountRecommendation(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * Keyword to Navigate to Suggested PO
     */
    public void navigateToCycleCountRecommendation() {
        String url = properties.getProperty("cycleCountRec");
        ownDriver.get(url);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    public By button(String btn) {
        return By.xpath("//button[text()='" + btn + "']");
    }

    public void navigateToAddItemScreen() {
        click(button(" Add Items "), "Click [Add Item] Button");
        commonObj.validateText(DriversPage.newDriverHeader, "Add Items", "[Add Items] header is present");
    }

    public By header(String text) {
        return By.xpath("//h2[text()='" + text + "']");
    }

    public void navigateToReAssignUserScreen() {
        click(button(" Reassign Users "), "Click [Reassign Users] Button");
        commonObj.validateText(header("Re-Assign Users"), "Re-Assign Users", "[Re-Assign Users] header is present");
    }

    /**
     * Keyword to login to SPO
     */
    public void winLogin() {
        if (Utility_Functions.xWaitForElementPresent(ownDriver, SpoPage.winLogin, 10)) {
            sendKeys(SpoPage.userName, properties.getProperty("CycleCostUserName"));
            sendKeys(SpoPage.password, Utility_Functions.xGetJsonData("spoPass"));
            Utility_Functions.waitTillClickHardSleep(report, ownDriver, SpoPage.submit, "");
        }
    }

    /**
     * Keyword to select Company
     */
    public void selectCompanyCycleCost() {
        winLogin();
        if (isDisplayed(BinMaintenancePage.toasterCloseIcon)) {
            click(BinMaintenancePage.toasterCloseIcon);
        }
        if (isDisplayed(ManifestsPage.closePopUp)) {
            Utility_Functions.timeWait(6);
            click(ManifestsPage.closePopUp);
        }
        if (!Utility_Functions.xIsDisplayed(ownDriver, SpoPage.companyName)) {
            if (Utility_Functions.xIsDisplayed(ownDriver, SelfServicePriceSheetPage.companySelector)) {
                Utility_Functions.waitTillClickHardSleep(report, ownDriver, SelfServicePriceSheetPage.companySelector, "");
                click(SelfServicePriceSheetPage.companyLabel);
                if (System.getProperty("company") == null) {
                    sendKey(SelfServicePriceSheetPage.winCompanyNumber, "99599");
                } else {
                    sendKey(SelfServicePriceSheetPage.winCompanyNumber, System.getProperty("company"));
                }
                click(SelfServicePriceSheetPage.selectButton);
            }
        }
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, By.xpath("//h2"), "");
    }

    public void clickInventory() {
        click(CycleCountRecommendationPage.inventorySub);
    }

    /**
     * This method validates the UI of Add Items Popup
     */
    public void verifyADDITEMSUI() {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, CycleCountRecommendationPage.cycleCountRecHeader, "[Cycle Count Recommendation] Header is present");
        navigateToAddItemScreen();
        String[] options = {"By Individual Item", "By Bin Location Range", "By Quantity", "By MF/PD/VN Code", "By Zone"};
        for (int i = 1; i < options.length; i++)
            Utility_Functions.xAssertEquals(report, options[i - 1], ownDriver.findElements(CycleCountRecommendationPage.addItemOptions).get(i), "[" + options[i] + "] drop down option is present");
        commonObj.validateText(button(" Cancel "), "Cancel", "[Cancel] button is present");
        commonObj.validateText(button(" Process "), "Process", "[Process] button is present");
        click(button(" Cancel "), "Click [Cancel] button");
        commonObj.validateText(CycleCountRecommendationPage.cycleCountRecHeader, "Cycle Count Recommendation", "[Cycle Count Recommendation] Header is present");
    }

    public void verifyReAssignUserUI() {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, CycleCountRecommendationPage.cycleCountRecHeader, "[Cycle Count Recommendation] Header is present");
        navigateToReAssignUserScreen();
        String[] options = {"User Count", "Current User", "Name", "New User", "Name"};
        for (String option : options)
            Utility_Functions.xAssertEquals(report, option, ownDriver.findElement(By.xpath("//th[text()='" + option + "']")), "[" + option + "] column is present");
        commonObj.validateText(CycleCountRecommendationPage.reAssignUserCancelBtn, "Cancel", "[Cancel] button is present");
        commonObj.validateText(CycleCountRecommendationPage.reAssignUserProcessBtn, "Process", "[Process] button is present");
        click(CycleCountRecommendationPage.reAssignUserCancelBtn, "Click [Cancel] button");
        commonObj.validateText(CycleCountRecommendationPage.cycleCountRecHeader, "Cycle Count Recommendation", "[Cycle Count Recommendation] Header is present");
    }

    public void verifyCycleCountRecAuditorUI() {
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, CycleCountRecommendationPage.cycleCountRecHeader, "[Cycle Count Recommendation] Header is present");
        navigateToReAssignUserScreen();
        String[] options = {"User Count", "Current User", "Name", "New User", "Name"};
        for (String option : options)
            Utility_Functions.xAssertEquals(report, option, ownDriver.findElement(By.xpath("//th[text()='" + option + "']")), "[" + option + "] column is present");
        commonObj.validateText(CycleCountRecommendationPage.reAssignUserCancelBtn, "Cancel", "[Cancel] button is present");
        commonObj.validateText(CycleCountRecommendationPage.reAssignUserProcessBtn, "Process", "[Process] button is present");
        click(CycleCountRecommendationPage.reAssignUserCancelBtn, "Click [Cancel] button");
        commonObj.validateText(CycleCountRecommendationPage.cycleCountRecHeader, "Cycle Count Recommendation", "[Cycle Count Recommendation] Header is present");
    }
}
