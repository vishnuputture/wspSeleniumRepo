package pages.pricing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class PricingMatrixPage extends ReusableLib {
    public PricingMatrixPage(Helper helper) {
        super(helper);
    }

    public static By addRow=By.id("btnCF01");
    public static By cellSecRow=By.id("I_10_11");
    public static By cellSecRowSecCol=By.id("I_10_21");
    public static By firstRow=By.id("D_8_4");
    public static By secondRow=By.id("D_10_4");
    public static By firstCol=By.id("D_7_12");
    public static By secondCol=By.id("D_7_22");
    public static By copyRow=By.id("btnCF02");
    public static By f8END=By.id("btnCF08");
    public static By enterLink=By.id("btnSubmit");
    public static By actionField=By.xpath("//input[@maxlength='1']");
    public static By mtxRowCode=By.xpath("//input[@maxlength='3']");
    public static By descLine1=By.xpath("//input[@name='InputField2']");
    public static By notBlank=By.xpath("//input[@name='InputField4']");
    public static By copyRowFrom=By.xpath("//input[@name='InputField0']");
    public static By copyRowTo=By.xpath("//input[@name='InputField1']");
    public static By selectColFrom=By.xpath("//input[@name='InputField2']");
    public static By selectColTo=By.xpath("//input[@name='InputField3']");
    public static By strRow=By.xpath("//input[@name='InputField1']");
    public static By strCol=By.xpath("//input[@name='InputField2']");
    public static By firstCell=By.xpath("//input[@name='InputField4']");
    public static By secondCell=By.xpath("//input[@name='InputField6']");

    public static By validateRow(String row){
        return By.xpath("//div[text()='"+row+"']");
    }

}
