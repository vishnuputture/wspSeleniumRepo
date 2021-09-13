package pages;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class OrderProcessingPage extends ReusableLib {
    public OrderProcessingPage (Helper helper) {super(helper);}

    public static By specialPricingMenu=By.xpath("//div[text()='9']/following-sibling::div");
    


}
