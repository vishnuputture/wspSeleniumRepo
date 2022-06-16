package pages.inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SalesPersonPage extends ReusableLib {

    public SalesPersonPage(Helper helper) {
        super(helper);
    }

    public static By salesPersonInquiryTitle = By.id("OutputField1");
    public static By searchForItem = By.id("txtVDITM");
    public static By customerTextBox = By.id("VDCSTN");
    public static By pricingColumn = By.id("VDMATR");
    public static By itemNumberSearchIcon = By.xpath("//div[@id='fontAwesome1']/i[@class='fa fa-search']");
    public static By customerSearchIcon = By.xpath("//div[@id='fontAwesome2']/i[@class='fa fa-search']");
    public static By pricingColSearchIcon = By.xpath("//div[@id='fontAwesome3']/i[@class='fa fa-search']");
    public static By rowStatic=By.id("constant49");
    public static By noRow=By.id("FMMSG");
    public static By fMROW=By.id("FMROW");
    public static By mutlType=By.id("FMTORC");
    public static By mutlValue=By.id("FMMULT1");
    public static By positionPricingColumn=By.id("I_4_34");
    public static By attachIcon=By.xpath("//i[@class='fa fa-paperclip']");
    public static By viewIcon=By.xpath("//i[@class='fa fa-eye']");
    public static By costCalculation = By.id("VDCOST");
    public static By priceCalculation = By.id("VDPRIC");
    public static By grossMargCalculation = By.id("VDMUP");
    public static By qtyCalculation = By.id("VDQTY");
    public static By extTolCalculation = By.id("VDNET");
    public static By itemDescription = By.id("D_11_28");
    public static By mfPDVN = By.id("D_11_25");
    public static By itemDesc = By.id("IMDSC1");
    public static By errorItem= By.id("MSG1");
    public static By invalidColumn= By.id("MSG3");
    public static By customerNote = By.xpath("//div[@id='CustomerNotes_copy']/a");
    public static By searchIconForItem=By.xpath("//div[@id='fontAwesome1']/i");
    public static By hdrItemNotes=By.xpath("//h3[text()='Item Notes']");
    public static By itemNotes1=By.id("IXN$1");

    /********************************** Quick View **********************************/

    public static By listPrice=By.id("outListPrice");
    public static By matrixCost=By.id("IBCSTA1");
    public static By grossMargin=By.id("DSMUPA1");
    public static By marginPercent=By.id("IBMUP1");

    /********************************** Inventory tab **********************************/

    public static By hdrInventory=By.id("OutputField4");
    public static By hdrQuantity=By.id("OutputField5");
    public static By hdrDirectShips=By.id("DSJILBL");
    public static By hdrPurchasing=By.id("OutputField6");
    public static By hdrQuantityBreak=By.id("QTYDS");

    public static By lblInventoryAvaiToSell=By.id("constant22");
    public static By lblInventoryWithReceipts=By.id("constant23");
    public static By lblInventorySelling=By.id("constant37");
    public static By lblInventoryPackage=By.id("constant38");

    public static By lblQtyOnHand=By.id("constant24");
    public static By lblQtyInHold=By.id("constant25");
    public static By lblQtyOnPO=By.id("constant26");
    public static By lblQtyUnscheduled=By.id("lblUnscheduled");
    public static By lblQtyOnSO=By.id("constant27");
    public static By lblQtyOnBO=By.id("constant41");
    public static By lblQtyOnSQ=By.id("lblOnSalesQuote");

    public static By lblDirShipOnHand=By.id("constant44");
    public static By lblDirShipInHold=By.id("constant45");
    public static By lblDirShipOnPO=By.id("constant46");
    public static By lblDirShipOnSO=By.id("constant47");
    public static By lblDirShipOnBO=By.id("constant48");

    public static By lblPrchsngPurchase=By.id("constant42");
    public static By lblPrchsngWeight=By.id("constant40");
    public static By lblPrchsngPckg=By.id("constant43");
    public static By lblPrchsngConvFact=By.id("constant39");
    public static By lblPrchsngLT=By.id("constant36");

    public static By tabItemBinDetails=By.xpath("//span[contains(text(),'Item-Bin Details')]");
    public static By lstBinDetailsTableHeader=By.xpath("//div[@id='BinGrid']/div[@class='cell header-cell']/div");
    public static By lnkExportToExcel=By.xpath("//div[@id='BinGrid']//following-sibling::div[contains(@class,'win-table-paging-bar paging-bar')]//span[contains(@class,'csv-paging-link') and text()='Export to Excel']");
    public static By lblTotal=By.id("OutputField7");
}
