package pages.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class CostOptionspage extends ReusableLib {

    public CostOptionspage(Helper helper) {   super(helper);        }

    public static By costOptionHeader= By.id("D_1_18_W1");
    public static By postionTo=By.id("I_3_19_W1");

    public static By erroMsg=By.id("D_10_4_W1");
    public static By codeSelectinput=By.id("I_6_2_W1");

    }
