package pages.WiseSmokeTest;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class BaseSmokeTestPage extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public BaseSmokeTestPage(Helper helper) {
        super(helper);
    }

    public static By userName = By.id("I_6_53");
    public static By passWord = By.id("I_7_53");
    public static By Where = By.id("I_22_7");
    public static By headerTitle = By.id("D_2_16");

}
