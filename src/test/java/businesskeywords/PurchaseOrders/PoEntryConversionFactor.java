package businesskeywords.PurchaseOrders;

import businesskeywords.common.Login;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.PurchaseOrders.InventoryReceiptsPage;
import pages.PurchaseOrders.PoEntryConversionFactorPage;
import pages.PurchaseOrders.PurchaseOrderDetailsPage;
import pages.PurchaseOrders.PurchaseOrderEntryPage;
import pages.common.MasterPage;
import pages.inventory.CostAdjustmentPage;
import pages.inventory.ItemMasterPage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

import java.util.List;

public class PoEntryConversionFactor extends ReusableLib {
    CommonActions commonObj;
    businesskeywords.SPO.Spo sp;
    Login login;
    private FrameworkDriver ownDriver;

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
        ownDriver=helper.getGSDriver();
    }

    public void selectItem(){
        click(CostAdjustmentPage.searchIcon, "Click Search Icon");
        Utility_Functions.timeWait(2);
        Utility_Functions.xUpdateJson("ItemNoMaster", Utility_Functions.getText(ownDriver, PoEntryConversionFactorPage.storeItem));
        sendKeys(PoEntryConversionFactorPage.selectItem, "1", "Select Item Number");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    }

    public void getDetails(){
        String purchasingUOM = getAttribute(PoEntryConversionFactorPage.inPurchasingUOM, "value");
        Utility_Functions.xUpdateJson("PurchasingUOM", purchasingUOM);
        String buyStdPackQty = getAttribute(PoEntryConversionFactorPage.inBuyStdPackQty, "value");
        Utility_Functions.xUpdateJson("BuyStdPackQty", buyStdPackQty);
        String covFactor = getAttribute(PoEntryConversionFactorPage.inCovFactor, "value");
        Utility_Functions.xUpdateJson("CovFactor", covFactor);
        String listPrice= getAttribute(ItemMasterPage.txtBoxListPrice, "value");
        Utility_Functions.xUpdateJson("ListPrice", listPrice);
        String poCost= getAttribute(ItemMasterPage.txtBoxPoCost, "value");
        Utility_Functions.xUpdateJson("PoCost", poCost);
        String lastCost= getText(ItemMasterPage.inCostsLast);
        Utility_Functions.xUpdateJson("LastCost", lastCost);
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

    public void findVendorFreight(){
        click(PurchaseOrderEntryPage.vendorNo,"Click on Vendor Number");
        sendKeys(PurchaseOrderEntryPage.firstVendor,"1"+Keys.ENTER,"Selecting the First Vendor in the search"  );
        sendKeys(PurchaseOrderEntryPage.enterFreightCharges,"FFA","Entered FFA Frieght Code");
        if(jsonData.getData("CostOption")!=null) {
            sendKeys(PurchaseOrderEntryPage.costOption, jsonData.getData("CostOption") + Keys.ENTER, "Entered Cost Option as"+Utility_Functions.xGetJsonData("CostOption"));
        }else
        {
            Utility_Functions.actionKey(Keys.ENTER,ownDriver);
        }
    }

    /**
     * This method to validate MU, Pkg ty, Conv Factor
     */
    public void verifyMUPkgQtyConvFact() {
        Utility_Functions.timeWait(2);
        String orderedQty = jsonData.getData("OrderedQty");
        sendKeys(PoEntryConversionFactorPage.ordered, orderedQty+Keys.ENTER, "Enter ordered");
        Utility_Functions.timeWait(2);
        sendKeys(PoEntryConversionFactorPage.itemNo, Utility_Functions.xGetJsonData("ItemNoMaster")+Keys.ENTER, "Enter Item Number");
        Utility_Functions.timeWait(3);
        Utility_Functions.xAssertEquals(report,getAttribute(PoEntryConversionFactorPage.muFiled,"value").trim(),Utility_Functions.xGetJsonData("PurchasingUOM").trim(),"");
        Utility_Functions.xAssertEquals(report,getAttribute(PoEntryConversionFactorPage.pkgQty,"value").trim(),Utility_Functions.xGetJsonData("BuyStdPackQty").trim(),"");
        Utility_Functions.xAssertEquals(report,getText(PoEntryConversionFactorPage.convFactor).trim(),Utility_Functions.xGetJsonData("CovFactor").trim(),"");
    }

    /**
     * This method to Exit Po Entry
     */
    public void exitToMaster() {
        if(Utility_Functions.xIsDisplayed(ownDriver,SpecialPriceAllowancePage.showContractDate)) {
            click(SpecialPriceAllowancePage.showContractDate, "Click F11=Header");
        }
        Utility_Functions.xScrollWindow(ownDriver);
        click(SpecialPriceAllowancePage.btnExit,"Click Exit Button");
        click(SpecialPriceAllowancePage.btnExit,"Click Exit Button");
    }

    public String chooseCostOption(int costOption){
        switch (costOption){
            case 1:
                return "ListPrice";
            case 2:
                return "PoCost";
            case 3:
                return "LastCost";
            case 4:
                return "WssCost";
            default:
                return "ListPrice";
        }
    }

    /**
     * This method to calculate Extend Price
     */
    public void extendPrice(Double price){
        Double orderedQty = Double.parseDouble(jsonData.getData("OrderedQty"));
        Double extendPrice=Double.parseDouble(getAttribute(PoEntryConversionFactorPage.extendPrice,"value"));
        Double expExtendPrice=price*orderedQty;
        Utility_Functions.xAssertEquals(report,extendPrice,expExtendPrice,"");
    }

    /**
     * This method to calculate List Price and extent price
     */
    public void calculateCost() {
        Utility_Functions.timeWait(2);
        int costOpt=Integer.parseInt(jsonData.getData("CostOption"));
        String cost=chooseCostOption(costOpt);
        Double costVal=Double.parseDouble(Utility_Functions.xGetJsonData(cost));
        Double covFactor=Double.parseDouble(Utility_Functions.xGetJsonData("CovFactor"));
        Double price=Double.parseDouble(getAttribute(PoEntryConversionFactorPage.priceField,"value"));
        Utility_Functions.xAssertEquals(report,price,covFactor*costVal,"");
        extendPrice(price);
    }

    /**
     * Keyword to find and select a random order of type direct
     */
    public void selectDirectOrderAndVerify(){
        String orderType = jsonData.getData("OrderType");
        String action = jsonData.getData("Action");
        String typeShipment = jsonData.getData("TypeShipment");

        findOrderNumber(orderType);
        verifyAction(action);
        verifyTypeShipment(typeShipment);

        String text = getAttribute(PurchaseOrderEntryPage.enterFreightCharges, "value");
        if (text.isEmpty()){
            sendKeys(PurchaseOrderEntryPage.enterFreightCharges,"FFA","Entered FFA Frieght Code");
        }
        click(PurchaseOrderDetailsPage.btnSubmit, "Clicked Submit button");
    }

    /**
     * Keyword to find and select a random order of type direct
     */
    public void findOrderNumber(String orderType){
        click(PurchaseOrderEntryPage.orderNo,"Click on Order Number");
        Utility_Functions.xSelectDropdownByValue(ownDriver, PurchaseOrderEntryPage.ddnType, orderType);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        String orderNumberSelected = selectRandomPO();

        xWaitForElementPresent(ownDriver, PurchaseOrderEntryPage.tbxOrderNumber, globalWait);
        String orderNumberActual = getAttribute(PurchaseOrderEntryPage.tbxOrderNumber, "value");
        Utility_Functions.xAssertEquals(report, orderNumberSelected, orderNumberActual,"Selected Order Number is displayed");
    }

    /**
     * Keyword to select a random Purchase order
     */
    public String selectRandomPO(){
        int randomIndex = Utility_Functions.xRandomFunction(1, 5);
        while (randomIndex>=1){
            boolean flag = getElement(PurchaseOrderEntryPage.btnNextPage).isEnabled();
            if (flag)
                click(PurchaseOrderEntryPage.btnNextPage);
            else
                break;
            randomIndex--;
        }
        List<WebElement> lstOptions = getListElement(PurchaseOrderEntryPage.lstOptionsColumn);
        List<WebElement> lstOrderNumber = getListElement(PurchaseOrderEntryPage.lstOrderNumberColumn);
        List<String> lstOrderNumberText = Utility_Functions.xGetTextVisibleListString(ownDriver, lstOrderNumber);
        int random = Utility_Functions.xRandomFunction(0, lstOptions.size()-1);
        String selectedPO = lstOrderNumberText.get(random);
        sendKeys(lstOptions.get(random), "2", "Selected an Order with Order Number as ["+selectedPO+"]");
        click(PurchaseOrderEntryPage.btnNext,"Click on Next button");

        return selectedPO;
    }

    /**
     * Keyword to select Action text in Purchase Order Headings page
     */
    public void selectAction(){
        sendKeys(PurchaseOrderEntryPage.actionInpput, jsonData.getData("Action"), "Enter Action");
    }

    /**
     * Keyword to validate Action text in Purchase Order Headings page
     */
    public void verifyAction(String actionExpected){
        String actionActual = getAttribute(PurchaseOrderEntryPage.actionInpput, "value");
        Utility_Functions.xAssertEquals(report, actionExpected, actionActual,"Action text is verified");
    }

    /**
     * Keyword to validate Action text in Purchase Order Headings page
     */
    public void verifyActionChange(){
        String action = jsonData.getData("ActionExpected");
        verifyAction(action);
    }

    /**
     * Keyword to validate Type Shipment text in Purchase Order Headings page
     */
    public void verifyTypeShipment(String typeShipmentExpected){
        String typeShipmentActual = getAttribute(PurchaseOrderEntryPage.orderNoInput, "value");
        Utility_Functions.xAssertEquals(report, typeShipmentExpected, typeShipmentActual,"Type Shipment text is verified");
    }

    /**
     * Keyword to validate Action text in Purchase Order Headings page
     */
    public void verifyTypeShipmentChange(){
        String action = jsonData.getData("TypeShipmentExpected");
        verifyTypeShipment(action);
    }

    /**
     * Keyword to enter existing PO order Number in Purchase Order Headings page
     */
    public void enterOrderNumber(){
        sendKeysAndEnter(PurchaseOrderEntryPage.orderNoInput, jsonData.getData("PONumber"), "Enter existing PO Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }
}
