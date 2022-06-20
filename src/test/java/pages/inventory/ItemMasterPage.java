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
    public static By itemDesc1=By.id("inItemDesc1");
    public static By itemDesc2=By.id("InItemDesc2");
    public static By txtBoxUOM = By.xpath("//input[@id='inSellingUOM']");
    public static By btnSave = By.xpath("//input[@id='btnSaveChanges']");
    public static By txtBoxSearch = By.xpath("//input[@id='inItemNumber']");
    public static By messageAddSuccessful = By.xpath("//div[@id='dynoInfoBox']/span[@class='growl-text']");
    public static By deleteItemAction = By.xpath("//div[@id='lnkDeleteItem']");
    public static By btnAlertContinue = By.xpath("//input[@value='Continue']");
    public static By txtInputPkgQuantity = By.xpath("//input[@id='inBuyStdPackQty']");
    public static By btnExit = By.xpath("//div/input[@id='btnExit']");
    public static By txtBoxListPrice = By.id("inListPrice");
    public static By txtBoxMatrix = By.id("inMatrix");
    public static By txtBoxPoCost = By.id("inPOCost");
    public static By inCostsLast= By.id("inCostsLast");
    public static By manufacturerCode = By.id("inManufacturerCode");
    public static By productCode = By.id("inProductCode");
    public static By vendorCode = By.id("inVendorCode");
    public static By tbxConvFactor = By.id("inCovFactor");

    public static By itemSearchIcon = By.id("faItemSearch");
    public static By itemNotes1=By.id("inItemNotes1");

    /************************************ Item Master Browse - Local Page elements ************************************/

    public static By btnDown = By.id("down_button");
    public static By itemNumber = By.id("D_11_6");
}
