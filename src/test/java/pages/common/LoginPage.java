package pages.common;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class LoginPage extends ReusableLib {

    public LoginPage(Helper helper) {
        super(helper);
    }

    public static By userNametxtBox = By.id("I_6_53");
    public static By passWordtxtBox = By.id("I_7_53");
    public static By pendingScreenTitle = By.id("D_1_16");
    public static By informationScreenTitle = By.id("D_1_31");
    public static By signOnPageTitle = By.id("D_1_23");

    /* Web Application login -Brandon*/
    public static By winLogin = By.id("winlogo");
    public static By winLoginProd = By.xpath("/html/body/div/div/div[2]/div[2]/div[1]");
    public static By userName = By.id("username");
    public static By password = By.id("password");
    public static By prodUsername = By.id("id_win_username");
    public static By submit = By.xpath("//input[@type='submit']");
    public static By toasterCloseIcon = By.xpath("//a[contains(@class,'toast-close-icon')]");
    public static By pageTitle = By.xpath("//h2[contains(@class,'page-title')]");
    public static By companySelector = By.id("companySelectorToggle");
    public static By companyLabel = By.xpath("//*[text()=' WIN Into Company ']");
    public static By winCompanyNumber = By.cssSelector("div.form-group.company-selection.mb-0>input");
    public static By selectButton = By.cssSelector("div.select-btn-div>button");

    public static By passwordExpiresMsg = By.xpath("//div[contains(text(),'password')]");

}
