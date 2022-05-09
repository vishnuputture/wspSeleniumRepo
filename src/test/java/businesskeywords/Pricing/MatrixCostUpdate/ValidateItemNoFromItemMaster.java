package businesskeywords.Pricing.MatrixCostUpdate;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import pages.pricing.matrixcost.MatrixCostUpdatePage;
import supportLibraries.Utility_Functions;

import java.util.ArrayList;

public class ValidateItemNoFromItemMaster extends ReusableLib {
    CommonActions commonObj=new CommonActions(helper);
    public static ArrayList<String> values;
    private FrameworkDriver ownDriver;
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public ValidateItemNoFromItemMaster(Helper helper) {
        super(helper);
        ownDriver=helper.getGSDriver();
    }

    /**
     * This method to modify Proposed field and Update
     */
    public void modiftPropoField() {
        values=new ArrayList();
        PoCostUpdation poUpdation = new PoCostUpdation(helper);
        Utility_Functions.xIsElementDisplayed(report,ownDriver.findElement(MatrixCostUpdatePage.greenActFilOpt),"'*Green indicates active filter option' is Present");
        String itemN0 = ownDriver.findElement(MatrixCostUpdatePage.itemNumber).getText();
        System.out.println("Item no : "+itemN0);
        String decValue = poUpdation.updateField(poUpdation.fieldWithValidRandomValue(),MatrixCostUpdatePage.ProposedMtxCost);
        values.add(itemN0);
        String[] arrSplit = decValue.split("\\.");
        values.add(arrSplit[0]);
        System.out.println("Size : "+values.size());
    }

    /**
     * This method to modify PO field with valid input
     */
    public void modifyPOCostField() {
        values=new ArrayList();
        PoCostUpdation poUpdation=new PoCostUpdation(helper);
        String itemN0 = ownDriver.findElement(MatrixCostUpdatePage.itemNumber).getText();
        System.out.println("Item no : "+itemN0);
        String decValue = poUpdation.updateField(poUpdation.fieldWithValidRandomValue(),MatrixCostUpdatePage.poField);
        values.add(itemN0);
        String[] arrSplit = decValue.split("\\.");
        values.add(arrSplit[0]);
        System.out.println("Size : "+values.size());
    }

    /**
     * This method to select first row record from the Matrix cost Update table
     */
    public void slctRcrdFromTable() {
        click(MatrixCostUpdatePage.checkBox, "Disable CheckBox");
        click(MatrixCostUpdatePage.radioButton, "Click radio button");
    }

    /**
     * This method Navigate to Item Master and Validate Item number and updated proposed cost
     */
    public void validateItemMasterPropoField() {
        commonObj.inventoryToItemMaster();
        System.out.println("Size : "+values.size());
        System.out.println("String[] values : " + values.get(0) + " & " + values.get(1));
        sendKeys(MatrixCostUpdatePage.txtBoxSearch, values.get(0));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        String expValue = ownDriver.findElement(MatrixCostUpdatePage.itmMsrPropoFld).getAttribute("value");
        System.out.println("Exp : " + expValue);
        String[] arrSplit = expValue.split("\\.");
        Utility_Functions.xAssertEquals(report, values.get(1)+".0000",arrSplit[0]+".0000", ",","Updated value");
        click(MatrixCostUpdatePage.btnF3);
    }

    /**
     * This method Navigate to Item Master and Validate Item number and updated PO cost
     */
    public void validateItemMasterPOField() {
        commonObj.inventoryToItemMaster();
        System.out.println("Size : "+values.size());
        System.out.println("String[] values : " + values.get(0) + " & " + values.get(1));
        sendKeys(MatrixCostUpdatePage.txtBoxSearch, values.get(0));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        String expValue = ownDriver.findElement(MatrixCostUpdatePage.poFieldItem).getAttribute("value");
        System.out.println("Exp : " + expValue);
        String[] arrSplit = expValue.split("\\.");
        Utility_Functions.xAssertEquals(report, values.get(1)+".0000",arrSplit[0]+".0000", ",","Updated value");
        click(MatrixCostUpdatePage.btnF3);
    }
}
