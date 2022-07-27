package pages.pricing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class ReportsMenu extends ReusableLib {

    public ReportsMenu(Helper helper) {
        super(helper);
    }

    public static By reportMenu = By.xpath("//div[text()='22']/following-sibling::div/a[contains(text(),'Report')]");
    public static By printInvoices = By.xpath("//div[text()='9']/following-sibling::div/a[contains(text(),'Invoices')]");
    public static By enterSel = By.id("I_17_50");
    public static By KeyNo1 = By.id("I_17_18");
    public static By KeyNo2 = By.id("I_17_25");
    public static By exitButton = By.id("btnExit");

}
