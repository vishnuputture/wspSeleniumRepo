package businesskeywords.Pricing.MatrixCostUpdate;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import pages.pricing.MatrixCostUpdatePage;
import supportLibraries.Utility_Functions;

public class ProposedMtxCostUpdation extends ReusableLib {

    CommonActions commonObj;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public ProposedMtxCostUpdation(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    /**
     * This method to modify PO field with different values
     */
    public void verifyProposedMtxFieldWithDiffValues() {
        int randNumber = Utility_Functions.xRandomFunction();
        String validValue = Integer.toString(randNumber);
        String checked = driver.findElement(MatrixCostUpdatePage.checkBox).getAttribute("checked");
        System.out.println("Is it checked  : " + checked);
        try {
            checked.equals("true");
            System.out.println("Entered if");
            click(MatrixCostUpdatePage.checkBox, "Disable the Update Matrix Cost checkBox");
            click(MatrixCostUpdatePage.radioButton);
        } catch (Exception e) {
            System.out.println("Disabled the Update Matrix Cost checkBox");
        }
        CancelUpdatedValue(validValue, MatrixCostUpdatePage.ProposedMtxCost);
        CancelUpdatedValue(jsonData.getData("11_integer_value"), MatrixCostUpdatePage.ProposedMtxCost);
        CancelUpdatedValue(jsonData.getData("Enter_0"), MatrixCostUpdatePage.ProposedMtxCost);
    }

    public void CancelUpdatedValue(String fieldValue, By field) {
        try {
            System.out.println(fieldValue);
            Utility_Functions.xSendKeys(driver, report, field, fieldValue, "Enter " + fieldValue + " into Matrix Cost Field");
            click(MatrixCostUpdatePage.saveButton, "Click Save Changes Button");
            click(MatrixCostUpdatePage.cancelButton, "Click Cancel Button");
            Utility_Functions.timeWait(3);
            String exp_pOValue = driver.findElement(field).getAttribute("value");
            System.out.println("exp_pOValue: " + exp_pOValue);
            String[] arrSplit = exp_pOValue.split("\\.");
            System.out.println("After Splite: " + arrSplit[0]);
            if(fieldValue.equals("0")) {
                Utility_Functions.xAssertEquals(report, exp_pOValue,".0000", "Field Matrix Cost value updated");
            }else{Utility_Functions.xAssertEquals(report, fieldValue+".0000", arrSplit[0]+".0000", "Field Matrix Cost value updated");}
            } catch (Exception e) {
            Utility_Functions.xIsElementDisplayed(report, driver.findElement(MatrixCostUpdatePage.invalidErrorPopUp), "Invalid input popUp " + fieldValue);
        }
    }

}
