package businesskeywords.specialPricing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import pages.MasterPage;
import pages.TruckPage;
import supportLibraries.Utility_Functions;

public class Trucks extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public Trucks(Helper helper) {
        super(helper);
    }

    public void navigateToShippingManifest() {
        driver.get(jsonData.getData("Url"));
        //driver.get("http://wservicedev.winwholesale.com/shipping-manifest-manager/#/truck-list/warehousing");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to Add
     */
    public void addNewtruck() {
        click(TruckPage.companySelectorToggle, "Click on company toggle");
        //ngWaitRequestToFinish();
        click(TruckPage.winIntoCompany);
        sendKeys(TruckPage.companyNumInput, jsonData.getData("CompanyNum"), "Entering company number");
        Utility_Functions.xMouseClick(driver, TruckPage.selectButton);
        // Utility_Functions.xmouseOver(driver,TruckPage.menuIcontruch);
        click(TruckPage.menuIcontruch);
        click(TruckPage.subManuTruck, "Navigate to trucks page");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        click(TruckPage.addNewTruckBtn, "Click on add new truck button");

        sendKeys(TruckPage.truckNameInput, jsonData.getData("TruckName"), "Entering truck name");

        sendKeys(TruckPage.licensePlateNumberInput, jsonData.getData("LicensePlateNum"), "Entering license plate number");

        sendKeysAndTabOut(TruckPage.newTruckPlateExpInput, "06/20/2021");

        sendKeys(TruckPage.yearInput, jsonData.getData("MakeYear"), "Entering year");
        sendKeys(TruckPage.makeInput, jsonData.getData("Make"), "Entering make");
        sendKeys(TruckPage.modelInput, jsonData.getData("Model"), "Entering model");

        sendKeys(TruckPage.vinInput, jsonData.getData("Vin"), "Entering vin number");
        sendKeys(TruckPage.emptyTruckWeightInput, jsonData.getData("EmptyWeight"), "Entering empty truck weight");
        sendKeys(TruckPage.weightLimitInput, jsonData.getData("WeightLimit"), "Entering weight limit");
        click(TruckPage.saveBtn, "Saving record");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);


    }
}
