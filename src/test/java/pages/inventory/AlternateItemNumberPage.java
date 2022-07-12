package pages.inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class AlternateItemNumberPage extends ReusableLib {

    public AlternateItemNumberPage(Helper helper) {
        super(helper);
    }

    public static By pageTitleAlternateItemNoRevision = By.id("hdrAltItmRevisionProcName");
    public static By revisionAlternateItemNoMenu = By.xpath("//div[text()='8']/following-sibling::div");
    public static By tbxItemNumber = By.id("inpItmNbr");
    public static By lstBtnMoreAlternateItemNos = By.xpath("//div[contains(@id,'More$1')]");
    public static By lstTbxMoreItemNos = By.xpath("//input[contains(@id,'inpPN')]");
    public static By iconCross = By.id("faAltItmExit");
    public static By btnExit = By.id("btnExit");

    /******************************************** ALTERNATE ITEM NUMBERS popup ********************************************/
    public static By hdrAlternateItemNoPopup = By.id("hdrALtItmNbr");
}
