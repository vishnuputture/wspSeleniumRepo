package businesskeywords.Pricing.pricingRoutine;

import businesskeywords.Pricing.SPA.SPAScreenVerification;
import businesskeywords.Pricing.SPA.SpecialPricingAllowance;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import commonkeywords.DBCall;
import org.openqa.selenium.Keys;
import pages.AccountReceivable.MiscChargesAndAdjustmentsPage.MiscChargesAndAdjustmentsPage;
import pages.Purchasing.PurchaseOrderInquiryPage;
import pages.pricing.AddSpecialPricingPage;
import pages.pricing.SalesOrders.CustomerNotesPage;
import pages.pricing.SalesOrders.SalesOrdersPage;
import pages.pricing.SpecialPricePage;
import pages.pricing.pricingRoutine.PricingRoutinePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

public class PricingRoutine extends ReusableLib {

    CommonActions commonObj;
    private FrameworkDriver ownDriver;
    SpecialPricingAllowance spa;
    SPAScreenVerification spaScreenVerification=new SPAScreenVerification(helper);

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public PricingRoutine(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
        spa=new SpecialPricingAllowance(helper);
    }

    public void createSPAContractCustomerItem(){
        spaScreenVerification.validateSPAApplicationTitle();
        String name = spa.addRanActiveContract();
        click(SpecialPriceAllowancePage.btnReturn);
        sendKeysAndEnter(SpecialPriceAllowancePage.txtBoxSearchCon, name, "Search Contract Name");
        spa.assignIndividualItem();
        spa.loadSPOption(name);
    }

    public void dataBaseValidation() throws Exception {
        System.out.println("ckdnlk.......::: "+DBCall.sQLGetDATA(jsonData.getData("columnName1"), "001314"));
        System.out.println("ckdnlk.......::: "+DBCall.sQLGetDATA(jsonData.getData("columnName2"), "001314"));
        if(!DBCall.sQLGetDATA(jsonData.getData("columnName1"), "001195").contains("85.000000")){
            throw new Exception("Data mismatch");
        }else {
            System.out.println("Passed...........");
        }
        if(!DBCall.sQLGetDATA(jsonData.getData("columnName2"), "001195").contains(jsonData.getData("SpecialPriceMethod"))){
            throw new Exception("Data mismatch");
        }
    }

    public void addItemsSingleItemToSalesOrder() {
        sendKeys(SalesOrdersPage.qtyOrdered,"1","Entering ordered quantity");
        sendKeys(SalesOrdersPage.itemNumber,jsonData.getData("itemNo1"),"Entering item Number");
        sendKeys(SalesOrdersPage.qtyToShip,"1","Entering quantity to ship");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        if(isDisplayed(SalesOrdersPage.itemAlreadyOnOrderWindow)){
            Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElement(SalesOrdersPage.lineNumberToCombine));
        }
        if(isDisplayed(SalesOrdersPage.itemEntryIssueWindow)){
            click(SalesOrdersPage.combineSelect);
            click(SalesOrdersPage.continuebtn);
        }
        if(isDisplayed(SalesOrdersPage.creditLimitHeader)){
            click(SalesOrdersPage.creditLimitContinue);
        }
        Utility_Functions.xUpdateJsonWithArray("outerUnitPrice",ownDriver.findElement(SalesOrdersPage.outerUnitPrice).getAttribute("value"));
        Utility_Functions.xUpdateJsonWithArray("SalesOrderNo",ownDriver.findElement(SalesOrdersPage.salesOrderField).getAttribute("value"));
        Utility_Functions.xUpdateJson("SalesOrder",ownDriver.findElement(SalesOrdersPage.salesOrderField).getAttribute("value"));
    }

    public void addSpecialPrice(){
        click(SpecialPriceAllowancePage.btnAddContract,"Click Add");
        commonObj.validateText(PurchaseOrderInquiryPage.hdrBuyersWorksheet,"Add/Maintain Special Pricing","Header is present [Add/Maintain Special Pricing]");
        sendKeys(AddSpecialPricingPage.custNumTxtBox, jsonData.getData("CustomerNo"),"Enter customer number");
        sendKeys(AddSpecialPricingPage.itemNumTxtBox, jsonData.getData("ItemNo"),"Enter Item number");
        sendKeys(MiscChargesAndAdjustmentsPage.invoiceAmount, jsonData.getData("SpecialPrice"),"Enter Item number");
        sendKeys(PricingRoutinePage.startDate,Utility_Functions.getCurrentDate(),"Enter start date as current date");
        sendKeysAndEnter(PricingRoutinePage.endDate,Utility_Functions.yearAdjust(1),"Enter End date");
        click(SpecialPriceAllowancePage.btnReturn,"Click Cancel Button");
        commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance", "Validating special price page title");
        sendKeys(PricingRoutinePage.custNo,jsonData.getData("CustomerNo"),"Enter Customer No");
        sendKeysAndEnter(PricingRoutinePage.itemNo,jsonData.getData("ItemNo"),"Enter Item No");
        Utility_Functions.xUpdateJson("specialPrice",getText(PricingRoutinePage.specialPrice).trim());
        click(CustomerNotesPage.btnExit);
    }
}
