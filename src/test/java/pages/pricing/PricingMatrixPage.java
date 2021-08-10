package pages.pricing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class PricingMatrixPage extends ReusableLib {
    public PricingMatrixPage(Helper helper) {
        super(helper);
    }

    public static By addRow=By.id("btnCF01");
    public static By enterLink=By.id("btnSubmit");
    public static By actionField=By.xpath("//input[@maxlength='1']");
    public static By mtxRowCode=By.xpath("//input[@maxlength='3']");
    public static By descLine1=By.xpath("//input[@name='InputField2']");
    public static By validateRow(String row){
        return By.xpath("//div[text()='"+row+"']");
    }

}
