package pages;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SalesAnalysisPage extends ReusableLib {
    public SalesAnalysisPage(Helper helper) {super(helper);}

    public static By spaApplication=By.xpath("//div[text()='13']/following-sibling::div");

}
