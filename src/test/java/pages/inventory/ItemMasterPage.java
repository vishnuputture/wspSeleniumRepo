package pages.inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class ItemMasterPage extends ReusableLib {

    public ItemMasterPage(Helper helper) {
        super(helper);
    }

    public static By search_itemMaster_Lookup = By.xpath("//div[@id='faItemSearch']//i[@class='fa fa-search']");

    public static By pageTitle = By.xpath("//div[@id='Title']");
    public static By addItemAction = By.xpath("//div[@title='F8 - Add Item']");
    public static By txtBoxDescription = By.xpath("//input[@title='Description Blank']");
    public static By itemDesc1 = By.id("inItemDesc1");
    public static By mfSearchIcon = By.xpath("//div[@id='faMftCdeSrch']/i[@class='fa fa-search']");
    public static By pdSearchIcon = By.xpath("//div[@id='faPrdCdeSrch']/i[@class='fa fa-search']");
    public static By vnSearchIcon = By.xpath("//div[@id='faVendorCdeSrch']/i[@class='fa fa-search']");
    public static By uomSearch = By.xpath("//div[@id='faSellingUOMSrch']/i[@class='fa fa-search']");
    public static By uomSearchPur = By.xpath("//div[@id='faPurchasingUOMSrch']/i[@class='fa fa-search']");
    public static By itemDesc2 = By.id("InItemDesc2");
    public static By calcMethod = By.id("inCalcMethod");
    public static By stdPkgQty = By.id("inStdPackQty");
    public static By btnSerialized = By.id("btnSerialized");
    public static By itemNote2 = By.id("inItemNotes2");
    public static By itemNote3 = By.id("inItemNotes3");
    public static By itemNum = By.id("D_2_11_W1");
    public static By abcAbsoluteCalHeader = By.id("D_1_16_W1");
    public static By itemDescription = By.id("D_2_31_W1");
    public static By tipIcon = By.xpath("//div[@class='pui-tip-content']");
    public static By verifySerializedBtn = By.xpath("//div[@id='btnSerialized']/descendant::span");
    public static By poundStdPkg = By.id("inPdsPerStdKG");
    public static By purchasingMin = By.id("inPurchsingMin");
    public static By purchasingMax = By.id("inPurchasingMax");
    public static By inCovFactor = By.id("inCovFactor");
    public static By inTaxable = By.id("inTaxable");
    public static By poundStdPkgPur = By.id("inPdsperStdKG");
    public static By itemDetail = By.id("hdrItemDesc1");
    public static By itemType = By.id("inItemType");
    public static By historyMonth = By.id("inHstMntRtn");
    public static By txtBoxUOM = By.xpath("//input[@id='inSellingUOM']");
    public static By btnSave = By.xpath("//input[@id='btnSaveChanges']");
    public static By txtBoxSearch = By.xpath("//input[@id='inItemNumber']");
    public static By messageAddSuccessful = By.xpath("//div[@id='dynoInfoBox']/span[@class='growl-text']");
    public static By deleteItemAction = By.xpath("//div[@id='lnkDeleteItem']");
    public static By btnAlertContinue = By.id("btnButton1");
    public static By txtInputPkgQuantity = By.xpath("//input[@id='inBuyStdPackQty']");
    public static By btnExit = By.xpath("//div/input[@id='btnExit']");
    public static By txtBoxListPrice = By.id("inListPrice");
    public static By txtBoxMatrix = By.id("inMatrix");
    public static By txtBoxPoCost = By.id("inPOCost");
    public static By inCostsLast = By.id("inCostsLast");
    public static By manufacturerCode = By.id("inManufacturerCode");
    public static By productCode = By.id("inProductCode");
    public static By vendorCode = By.id("inVendorCode");
    public static By tbxConvFactor = By.id("inCovFactor");
    public static By tbxPriceMatrixRow = By.id("inPrcMxtRow");
    public static By btnSearchPriceMatrixRow = By.xpath("//div[@title='Search Price Matrix Row']/i[contains(@class,'fa-search')]");

    public static By itemSearchIcon = By.id("faItemSearch");
    public static By itemNotes1 = By.id("inItemNotes1");
    public static By tooltip = By.className("pui-tip-content");
    public static By tbxQuantityBreak = By.id("inQtyBreak");
    public static By btnSearchQuantityBreak = By.xpath("//div[@title='Search Quantity Break']/i[contains(@class,'fa-search')]");
    public static By tbxLostSales = By.id("inLostSales");
    public static By tbxExtraHistory = By.id("inExtraHistory");
    public static By tbxFreightClassCode = By.id("inFrtClssCde");
    public static By lblMonthsOnWise = By.id("lblMnthsnWise");
    public static By lblAverage = By.id("inAverage");
    public static By tbxGrossMargin = By.id("inGrsMrgPct");
    public static By tbxMinGrossMargin = By.id("inMinGM");

    /************************************ Item Master Browse - Local Page elements ************************************/

    public static By btnDown = By.id("down_button");
    public static By itemNumber = By.id("D_11_6");
    public static By itemNumber1 = By.id("D_2_11_W1");

    /************************************ Matrix Row Codes Page elements ************************************/

    public static By hdrMatrixRowCodes = By.id("D_2_21");
    public static By firstRow = By.id("D_11_6");
    public static By hdrQtyBreakDiscountCodes = By.id("wbScrTitle");
    public static By msgNoQtyBreakDiscountCodeRecords = By.id("constant4");

    /************************************ Matrix Row Codes Page elements ************************************/

    public static By tbxActionFCC = By.id("I_4_25");
    public static By tbxFCC = By.id("I_6_25");
    public static By tbxDescriptionFCC = By.id("I_8_25");
    public static By lblCode = By.id("D_7_4");
    public static By wssNetPrice = By.id("outWSSNtPrc");

    public static By addItem = By.id("lnkAddItem");

}
