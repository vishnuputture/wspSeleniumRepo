package businesskeywords.Pricing.MatrixCostUpdate;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import pages.pricing.matrixcost.MatrixCostUpdatePage;
import supportLibraries.Utility_Functions;

public class ProposedMtxCostUpdation extends ReusableLib {

    CommonActions commonObj = new CommonActions(helper);

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public ProposedMtxCostUpdation(Helper helper) {
        super(helper);
    }

    /**
     * This method to modify Proposed field with different values
     */
    public void verifyProposedMtxFieldWithDiffValues() {
        MatrixCostUpdate mtxCostUpd=new MatrixCostUpdate(helper);
        int randNumber = Utility_Functions.xRandomFunction();
        String validValue = Integer.toString(randNumber);
        mtxCostUpd.selectRecord(MatrixCostUpdatePage.radioButton);
        CancelUpdatedValue(validValue, MatrixCostUpdatePage.ProposedMtxCost);
        CancelUpdatedValue(jsonData.getData("11_integer_value"), MatrixCostUpdatePage.ProposedMtxCost);
        CancelUpdatedValue(jsonData.getData("Enter_0"), MatrixCostUpdatePage.ProposedMtxCost);
    }

    public void CancelUpdatedValue(String fieldValue, By field) {
        try {
            System.out.println(fieldValue);
            Utility_Functions.xSendKeys(driver, report, field, fieldValue, "Enter " + fieldValue + " into Matrix Cost Field");
            click(MatrixCostUpdatePage.saveButton, "Click Save Changes Button");
            Utility_Functions.timeWait(5);
            System.out.println("After 3 sec");
            Utility_Functions.xIsDisplayed(driver,MatrixCostUpdatePage.infMessage);
            System.out.println("Present");
            try {
                Utility_Functions.xClickIfAvlbl(driver, driver.findElement(MatrixCostUpdatePage.cbtn));
            }catch (Exception e){}
            System.out.println("Present");
            try {
                Utility_Functions.xClickIfAvlbl(driver, report, driver.findElement(MatrixCostUpdatePage.cancelButton), "Click Cancel button");
            }catch (Exception e){}
            System.out.println("Present");
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
