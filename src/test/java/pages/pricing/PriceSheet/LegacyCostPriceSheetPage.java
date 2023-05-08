package pages.pricing.PriceSheet;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class LegacyCostPriceSheetPage extends ReusableLib {
    public LegacyCostPriceSheetPage(Helper helper) {
        super(helper);
    }

    public static By optionOneForSheet = By.id("I_11_2");
    public static By status = By.id("D_5_61");
    public static By sheetMultiplier = By.id("I_12_30");
    public static By listPrice = By.id("I_13_30");
    public static By poCost = By.id("I_14_30");
    public static By matrixCost = By.id("I_15_30");
    public static By priceSheetCode = By.id("I_5_30");
    public static By priceSheetName = By.id("I_6_30");
    public static By successMessageValidation = By.id("D_24_2");
    public static By returnButton = By.id("btnCF12");
    public static By selectStatus = By.id("I_4_73");
    public static By changeItemDetails = By.id("btnCF17");
    public static By runComparisonReport = By.id("btnCF13");
    public static By itemDescription = By.id("D_11_7");
    public static By processNow = By.id("I_19_30");
    public static By processSheetMessage = By.xpath("//div[contains(@id,'4_W1') and @class='A20']");
    public static By AddItem = By.id("btnCF06");
    public static By AddItemNumberLink = By.xpath("//div[@id='D_9_2']/a[1]");
    public static By ItemNumberValue = By.id("I_9_28");
    public static By ListPriceValue = By.id("I_17_28");
    public static By chooseFirstItemNumber = By.id("I_11_3");
    public static By enter = By.id("btnSubmit");
    public static By processDate = By.id("I_18_30");
    public static By processImmediately = By.id("btnCF09");
    public static String currentSheetStatus = "A = Available";
    public static String changedSheetStatus = "M = Maintaining";
    public static String processedSheetStatus = "P = Processed";

}

