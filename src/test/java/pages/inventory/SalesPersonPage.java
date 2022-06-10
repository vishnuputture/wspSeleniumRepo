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
    public static By customerNote = By.xpath("//div[@id='CustomerNotes_copy']/a");
    public static By searchIconForItem=By.xpath("//div[@id='fontAwesome1']/i");
}
