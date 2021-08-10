package pages.inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class ItemMasterPage extends ReusableLib{

    public ItemMasterPage(Helper helper) {
        super(helper);
    }

    public static By pageTitle=By.xpath("//div[@id='Title']");
    public static By addItemAction = By.xpath("//div[@title='F8 - Add Item']");
    public static By txtBoxDescription = By.xpath("//input[@title='Description Blank']");
    public static By txtBoxUOM = By.xpath("//input[@id='inSellingUOM']");
    public static By btnSave = By.xpath("//input[@id='btnSaveChanges']");
    public static By txtBoxSearch = By.xpath("//input[@id='inItemNumber']");
    public static By messageAddSuccessful = By.xpath("//div[@id='dynoInfoBox']/span[@class='growl-text']");
    public static By deleteItemAction = By.xpath("//div[@id='lnkDeleteItem']");
    public static By btnAlertContinue = By.xpath("//input[@value='Continue']");
    public static By txtInputPkgQuantity = By.xpath("//input[@id='inBuyStdPackQty']");
    public static By btnExit = By.xpath("//div/input[@id='btnExit']");

}
