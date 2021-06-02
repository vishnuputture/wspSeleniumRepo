package businesskeywords;

import com.paulhammant.ngwebdriver.NgWebDriver;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import supportLibraries.Utility_Functions;
import org.openqa.selenium.Keys;

public class Login extends ReusableLib {
	
	 /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
	

	
    public Login(Helper helper) {
        super(helper);
    }
    
    public void siteLogin() {
         String  env = getProperties("ENV");
		String url = properties.getProperty(env + "URL");
    	driver.get(url);
        ngWaitRequestToFinish();

    	String user = properties.getProperty("UserName");
    	String password = properties.getProperty("Password");
        sendKeys(LoginPage.userNametxtBox,user,"Entering username "+user);
        sendKeys(LoginPage.passWordtxtBox,password,"Entering password ******");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        if(Utility_Functions.xIsDisplayed(driver,LoginPage.pendingScreenTitle)) {
        	Utility_Functions.actionKey(Keys.ENTER, driver);
        }
    }
}
