package pages.pricing.pricingmatrix;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class PricingMatrixPage extends ReusableLib {
    public PricingMatrixPage(Helper helper) {
        super(helper);
    }

    public static By addRow = By.id("btnCF01");
    public static By addCol = By.id("btnCF04");
    public static By cellSecRow = By.id("I_10_11");
    public static By cellSecRowSecCol = By.id("I_10_21");
    public static By firstRow = By.id("D_8_4");
    public static By secondRow = By.id("D_10_4");
    public static By firstCol = By.id("D_7_12");
    public static By secondCol = By.id("D_7_22");
    public static By copyRow = By.id("btnCF02");
    public static By copyCol = By.id("btnCF05");
    public static By txtBoxAction = By.id("I_4_25");
    public static By txtMatrixRowCode = By.id("I_6_25");
    public static By txtDescription1 = By.id("I_8_25");
    public static By f8END = By.id("btnCF08");
    public static By enterLink = By.id("btnSubmit");
    public static By actionField = By.xpath("//input[@maxlength='1']");
    public static By mtxRowCode = By.xpath("//input[@maxlength='3']");
    public static By descLine1 = By.xpath("//input[@name='InputField2']");
    public static By notBlank = By.xpath("//input[@name='InputField4']");
    public static By copyColFrom = By.id("I_3_22_W1");
    public static By copyColTo = By.id("I_3_36_W1");
    public static By selectRowFrom = By.id("I_4_22_W1");
    public static By selectRowTo = By.id("I_4_36_W1");
    public static By strRow = By.id("I_3_14");
    public static By strCol = By.id("I_4_14");
    public static By firstCell = By.xpath("//input[@name='InputField4']");
    public static By secondCell = By.xpath("//input[@name='InputField6']");
    public static By pageTitle = By.id("D_2_45");
    public static By btnF3Exit = By.id("btnExit");
    public static By copyRow1 = By.id("D_8_4");
    public static By copyRow2 = By.id("D_10_4");
    public static By copyCol1 = By.id("D_7_12");
    public static By copyCol2 = By.id("D_7_22");
    public static By btnF24 = By.id("btnCF24");
    public static By col7 = By.id("D_7_72");
    public static By row5 = By.id("D_16_4");
    public static By dispListTitle = By.id("D_2_20");
    public static By selectRowTxtBox = By.id("I_4_26");
    public static By selectColTxtBox = By.id("I_4_28");
    public static By rowValueForPricingMatrix = By.id("I_9_26");
    public static By colValueForPricingMatrix = By.id("I_9_28");



    public static By getButton(String id) {
        return By.id(id);
    }

    public static By validateRow(String row) {
        return By.xpath("//div[text()='" + row + "']");
    }

}
