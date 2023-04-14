package pages.inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;


public class ItemLedgerPage extends ReusableLib {

    public ItemLedgerPage(Helper helper) {
        super(helper);
    }

    public static By lblLedgerPrice = By.xpath("//div[@id='outPrice.1']");

    public static By lblLedgerSC = By.xpath("//div[@id='outSC.1']");
    public static By lblQtyAfter = By.xpath("//div[@id='outQtyAfter.1']");
    public static By txtItemNumber = By.xpath("//input[@id='inpItemNbr']");
    public static By btnExit = By.xpath("//div/input[@id='btnExit']");
    public static By updatedUserName = By.xpath("//div[@id='outBy.1']");
}
