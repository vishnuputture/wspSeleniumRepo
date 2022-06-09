package pages.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;
public class SalesQuoteDetailLinesConversionRecapPage extends ReusableLib {

    public SalesQuoteDetailLinesConversionRecapPage(Helper helper) {
        super(helper);
    }
    public static By process=By.id("btnCF09");
    public static By itemConvert=By.id("SFITEM.1");
    public static By itemDescConvert=By.id("SFDSC1.1");
    public static By backOrdQty=By.id("SFIQTYN.1");
    public static By qtyConvert=By.id("SFTOCVTQT.1");
    public static By salesQuote=By.id("combined quote version");
    public static By saleQExtBtn=By.id("btnCF03");
    public static By backBtn=By.id("btnCF12");
    public static By recapHeader=By.id("wbScrTitle");

}
