package pages.common;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class LoginPage extends ReusableLib{
	
    public LoginPage(Helper helper) {
        super(helper);
    }
    
    public static By userNametxtBox=By.id("I_6_53");
    
    public static By passWordtxtBox = By.id("I_7_53");
    
    public static By pendingScreenTitle = By.xpath("//div[contains(text(),'Messages')]");
    
    public static By signOnPageTitle = By.id("D_1_23");
    
    
}
