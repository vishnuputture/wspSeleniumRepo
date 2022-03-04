package pages.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class InventoryReceiptsPage extends ReusableLib{

    public InventoryReceiptsPage(Helper helper) {
        super(helper);
    }

    public static By pageTitle = By.id("OP18_heading");
    public static By tbxPONumber = By.id("VDORNM");
    public static By tbxQtyRcvd = By.id("VQR.1");
    public static By btnExit = By.xpath("//input[@value='Exit']");
    public static By btnProcess = By.xpath("//input[@value='Process']");
    public static By chbxEnableDisableCost = By.id("Checkbox2");
    public static By growlText = By.className("growl-text");
    public static By lstUnitCostTbx = By.xpath("//input[starts-with(@id,'VUC')]");



}
