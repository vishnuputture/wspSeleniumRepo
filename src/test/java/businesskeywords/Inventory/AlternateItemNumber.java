package businesskeywords.Inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import commonkeywords.CommonActions;
import org.openqa.selenium.WebElement;
import pages.inventory.AlternateItemNumberPage;

import java.util.List;


public class AlternateItemNumber extends ReusableLib {

    CommonActions commonObj;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public AlternateItemNumber(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    /**
     * This method validates the title of [ALTERNATE ITEM NUMBER REVISIONS] Page
     */
    public void navigateToAlternateItemNoRevisions() {
        commonObj.masterToInventory();
        commonObj.inventoryToAlternateItemNoRevision();
        commonObj.validateText(AlternateItemNumberPage.pageTitleAlternateItemNoRevision, "ALTERNATE ITEM NUMBER REVISIONS (I-348)", "Validating [ALTERNATE ITEM NUMBER REVISIONS] page title");
    }

    /**
     * Keyword to enter item number
     */
    public void enterItemNumber() {
        String itemNo = jsonData.getData("ItemNo");
        sendKeysAndEnter(AlternateItemNumberPage.tbxItemNumber, itemNo, "Enter Item Number [" + itemNo + "] in [Item Number] textbox");
    }

    /**
     * Keyword to validate Alternate Item Number
     */
    public void verifyAlternateItemNo() {
        String alternateItemNo = jsonData.getData("AlternateItemNo");

        List<WebElement> lstMoreAlternateItemsBtn = getListElement(AlternateItemNumberPage.lstBtnMoreAlternateItemNos);
        click(lstMoreAlternateItemsBtn.get(0), "Click [More Alternate Item Numbers] button");
        waitForElementPresent(AlternateItemNumberPage.hdrAlternateItemNoPopup);

        List<WebElement> lstMoreAlternateItemsTbx = getListElement(AlternateItemNumberPage.lstTbxMoreItemNos);
        boolean flag = false;
        for (WebElement element : lstMoreAlternateItemsTbx) {
            String value = element.getAttribute("value");
            if (!value.isEmpty()) {
                value.trim();
            }
            if (value.equalsIgnoreCase(alternateItemNo)) {
                flag = true;
                break;
            }
        }
        if (flag)
            report.updateTestLog("Verify Alternate Item Number", "Alternate Item Number is displayed", Status.PASS);
        else
            report.updateTestLog("Verify Alternate Item Number", "Alternate Item Number is NOT displayed", Status.FAIL);
    }


}
