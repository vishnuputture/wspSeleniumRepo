package businesskeywords.Pricing.MatrixCostUpdate;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import pages.pricing.matrixcost.MatrixCostUpdatePage;
import supportLibraries.Utility_Functions;

public class ProposedMtxCostUpdation extends ReusableLib {

    CommonActions commonObj = new CommonActions(helper);
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public ProposedMtxCostUpdation(Helper helper) {
        super(helper);
        ownDriver=helper.getGSDriver();
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
            Utility_Functions.xSendKeys(ownDriver, report, field, fieldValue, "Enter " + fieldValue + " into Matrix Cost Field");
            click(MatrixCostUpdatePage.saveButton, "Click Save Changes Button");
            Utility_Functions.timeWait(5);
            System.out.println("After 3 sec");
            Utility_Functions.xIsDisplayed(ownDriver,MatrixCostUpdatePage.infMessage);
            System.out.println("Present");
            try {
                Utility_Functions.xClickIfAvlbl(ownDriver, ownDriver.findElement(MatrixCostUpdatePage.cbtn));
            }catch (Exception e){}
            System.out.println("Present");
            try {
                Utility_Functions.xClickIfAvlbl(ownDriver, report, ownDriver.findElement(MatrixCostUpdatePage.cancelButton), "Click Cancel button");
            }catch (Exception e){}
            System.out.println("Present");
            Utility_Functions.timeWait(3);
            String exp_pOValue = ownDriver.findElement(field).getAttribute("value");
            System.out.println("exp_pOValue: " + exp_pOValue);
            String[] arrSplit = exp_pOValue.split("\\.");
            System.out.println("After Splite: " + arrSplit[0]);
            if(fieldValue.equals("0")) {
                Utility_Functions.xAssertEquals(report, exp_pOValue,".0000", "Field Matrix Cost value updated");
            }else{Utility_Functions.xAssertEquals(report, fieldValue+".0000", arrSplit[0]+".0000", "Field Matrix Cost value updated");}
            } catch (Exception e) {
            Utility_Functions.xIsElementDisplayed(report, ownDriver.findElement(MatrixCostUpdatePage.invalidErrorPopUp), "Invalid input popUp " + fieldValue);
        }
    }

}
