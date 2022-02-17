package businesskeywords.PurchaseOrders;

import businesskeywords.common.Login;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Util;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import pages.PurchaseOrders.PoEntryConversionFactorPage;
import pages.common.MasterPage;
import pages.inventory.CostAdjustmentPage;
import pages.inventory.ItemMasterPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

public class PoEntryConversionFactor extends ReusableLib {
    CommonActions commonObj;
    businesskeywords.SPO.Spo sp;
    Login login;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public PoEntryConversionFactor(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        sp = new businesskeywords.SPO.Spo(helper);
        login = new Login(helper);
    }

    public void selectItem(){
        click(CostAdjustmentPage.searchIcon, "Click Search Icon");
        Utility_Functions.timeWait(2);
        Utility_Functions.xUpdateJson("ItemNoMaster", Utility_Functions.getText(driver, CustomerGroupMaintenancePage.secGroupName));
        sendKeys(CostAdjustmentPage.optBox, "1", "Select Item Number");
        Utility_Functions.actionKey(Keys.ENTER, driver);
    }

    public void getDetails(){
        String purchasingUOM = getAttribute(PoEntryConversionFactorPage.inPurchasingUOM, "value");
        Utility_Functions.xUpdateJson("PurchasingUOM", purchasingUOM);
        String buyStdPackQty = getAttribute(PoEntryConversionFactorPage.inBuyStdPackQty, "value");
        Utility_Functions.xUpdateJson("BuyStdPackQty", buyStdPackQty);
        String covFactor = getAttribute(PoEntryConversionFactorPage.inCovFactor, "value");
        Utility_Functions.xUpdateJson("CovFactor", covFactor);
    }

    /**
     * This method get MF/PD/VN Code from item master
     */
    public void getPurchasing() {
        selectItem();
        getDetails();
    }

    /**
     * This method get MF/PD/VN Code from item master
     */
    public void editPurchasing() {
        String stdQty = jsonData.getData("StdQty");
        String convFactor = jsonData.getData("convFactor");
        selectItem();
        sendKeys(PoEntryConversionFactorPage.inBuyStdPackQty,stdQty,"Edit Std pkg qty");
        sendKeys(PoEntryConversionFactorPage.inCovFactor,convFactor+Keys.ENTER,"Edit Conversion Factor");
        Utility_Functions.timeWait(2);
        commonObj.validateText(PoEntryConversionFactorPage.toaster,"Fields have recently been changed=>VERIFY CHANGES!","'Fields have recently been changed=>VERIFY CHANGES!' is present");
        getDetails();
    }

    /**
     * This method to validate MU, Pkg ty, Conv Factor
     */
    public void verifyMUPkgQtyConvFact() {
        String orderedQty = jsonData.getData("OrderedQty");
        sendKeys(PoEntryConversionFactorPage.ordered, orderedQty+Keys.ENTER, "Enter ordered");
        sendKeys(PoEntryConversionFactorPage.itemNo, Utility_Functions.xGetJsonData("ItemNoMaster")+Keys.ENTER, "Enter Item Number");
        Utility_Functions.xAssertEquals(report,getAttribute(PoEntryConversionFactorPage.muFiled,"value").trim(),Utility_Functions.xGetJsonData("PurchasingUOM").trim(),"");
        Utility_Functions.xAssertEquals(report,getAttribute(PoEntryConversionFactorPage.pkgQty,"value").trim(),Utility_Functions.xGetJsonData("BuyStdPackQty").trim(),"");
        Utility_Functions.xAssertEquals(report,getText(PoEntryConversionFactorPage.convFactor).trim(),Utility_Functions.xGetJsonData("CovFactor").trim(),"");
    }

    /**
     * This method to Exit Po Entry
     */
    public void exitToMaster() {
        if(Utility_Functions.xIsDisplayed(driver,SpecialPriceAllowancePage.showContractDate)) {
            click(SpecialPriceAllowancePage.showContractDate, "Click F11=Header");
        }
        Utility_Functions.xScrollWindow(driver);
        click(SpecialPriceAllowancePage.btnExit,"Click Exit Button");
        click(SpecialPriceAllowancePage.btnExit,"Click Exit Button");
    }

    /**
     * This method to calculate Extend Price
     */
    public void extendPrice(Double price){
        int orderedQty = Integer.parseInt(jsonData.getData("OrderedQty"));
        int extendPrice=Integer.parseInt(getAttribute(PoEntryConversionFactorPage.extendPrice,"value"));
        Double expExtendPrice=price*orderedQty;
        Utility_Functions.xAssertEquals(report,extendPrice,expExtendPrice,"");
    }

    /**
     * This method to calculate List Price and extent price
     */
    public void calculateListPrice() {
        int packQty=Integer.parseInt(Utility_Functions.xGetJsonData("BuyStdPackQty"));
        Double covFactor=Double.parseDouble(Utility_Functions.xGetJsonData("CovFactor"));
        Double price=Double.parseDouble(getAttribute(PoEntryConversionFactorPage.priceField,"value"));
        Utility_Functions.xAssertEquals(report,price,covFactor*packQty,"");
        extendPrice(price);
    }
}
