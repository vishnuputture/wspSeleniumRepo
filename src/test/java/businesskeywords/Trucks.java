package businesskeywords;

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

    public void addNewtruck (){

        driver.get("http://wservicedev.winwholesale.com/shipping-manifest-manager/#/truck-list/warehousing");
        waitForElementDisappear(MasterPage.loadingAnime,globalWait);
        click(TruckPage.companySelectorToggle);
        //ngWaitRequestToFinish();
        click(TruckPage.winIntoCompany);
        sendKeys(TruckPage.companyNumInput,"99599");
        Utility_Functions.xMouseClick(driver,TruckPage.selectButton);
       // Utility_Functions.xmouseOver(driver,TruckPage.menuIcontruch);
        click(TruckPage.menuIcontruch);
        click(TruckPage.subManuTruck);
        waitForElementDisappear(MasterPage.loadingAnime,globalWait);
        click(TruckPage.addNewTruckBtn);

        sendKeys(TruckPage.truckNameInput,"Test11");

        sendKeys(TruckPage.licensePlateNumberInput,"111234");

        sendKeysAndTabOut(TruckPage.newTruckPlateExpInput,"06/20/2021");

        sendKeys(TruckPage.yearInput,"2019");
        sendKeys(TruckPage.makeInput,"Toyato");
        sendKeys(TruckPage.modelInput,"Trundra");

        sendKeys(TruckPage.vinInput,"123456543");
        sendKeys(TruckPage.emptyTruckWeightInput,"4000");
        sendKeys(TruckPage.weightLimitInput,"5000");
        click(TruckPage.saveBtn);
        waitForElementDisappear(MasterPage.loadingAnime,globalWait);


    }
}
