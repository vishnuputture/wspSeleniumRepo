package businesskeywords.Pricing.MatrixCostUpdate;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import pages.pricing.ItemMasterPage;
import pages.pricing.MatrixCostUpdatePage;
import supportLibraries.Utility_Functions;

import java.util.ArrayList;

public class ValidateItemNoFromItemMaster extends ReusableLib {
    CommonActions commonObj;
    PoCostUpdation poUpdation;
    public static ArrayList<String> values;
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public ValidateItemNoFromItemMaster(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        poUpdation = new PoCostUpdation(helper);
    }

    /**
     * This method to modify PO field with valid input
     */
    public void modifyPOCostField() {
        values=new ArrayList();
        String itemN0 = driver.findElement(MatrixCostUpdatePage.itemNumber).getText();
        System.out.println("Item no : "+itemN0);
        String decValue = poUpdation.updateField(poUpdation.fieldWithValidRandomValue(),MatrixCostUpdatePage.poField);
        values.add(itemN0);
        values.add(decValue);
        System.out.println("Size : "+values.size());
    }

    /**
     * This method to select first row record from the Matrix cost Update table
     */
    public void selectRecordFromTable() {
        click(MatrixCostUpdatePage.checkBox, "Disable CheckBox");
        click(MatrixCostUpdatePage.radioButton, "Click radio button");
    }

    /**
     * This method Navigate to Item Master and Validate Item number and updated cost
     */
    public void validateItemMasterField() {
        commonObj.inventoryToItemMaster();
        System.out.println("Size : "+values.size());
        System.out.println("String[] values : " + values.get(0) + " & " + values.get(1));
        sendKeys(ItemMasterPage.txtBoxSearch, values.get(0));
        Utility_Functions.actionKey(Keys.ENTER, driver);
        String expValue = driver.findElement(ItemMasterPage.poField).getAttribute("value");
        System.out.println("Exp : " + expValue);
        Utility_Functions.xAssertEquals(report, values.get(1),expValue, ",","Updated value");
        click(ItemMasterPage.btnF3);
    }
}
