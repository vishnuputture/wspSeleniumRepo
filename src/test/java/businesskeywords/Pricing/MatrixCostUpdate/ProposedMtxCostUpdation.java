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
     *
     *
     * This method to modify PO field with different values
     *
     */
    public void verifyProposedMtxFieldWithDiffValues() {
        int randNumber=Utility_Functions.xRandomFunction();
        String validValue=Integer.toString(randNumber);
        CancelUpdatedValue(validValue,MatrixCostUpdatePage.ProposedMtxCost);
        CancelUpdatedValue(jsonData.getData("11_integer_value"),MatrixCostUpdatePage.ProposedMtxCost);
        CancelUpdatedValue(jsonData.getData("Enter_0"),MatrixCostUpdatePage.ProposedMtxCost);
    }

    public void CancelUpdatedValue(String fieldValue, By field){
        try {
            int fieldDigit=Integer.parseInt(fieldValue);
            String decValue;
            if(fieldValue.equals("0") || fieldDigit<1000000) {
                decValue = Utility_Functions.xformatVal(fieldValue, ".0000");
            }else{
                decValue = Utility_Functions.xformatVal(fieldValue, ".000");
            }
            System.out.println(fieldValue);
            System.out.println(decValue);
            Utility_Functions.xSendKeys(driver, report, MatrixCostUpdatePage.ProposedMtxCost, fieldValue, "Enter "+fieldValue+" into Proposed Matrix Cost Field");
            click(MatrixCostUpdatePage.saveButton, "Click Save Changes Button");
            click(MatrixCostUpdatePage.cancelButton, "Click Cancel Button");
            Utility_Functions.timeWait(2);
            String exp_pOValue = driver.findElement(field).getAttribute("value");
            Utility_Functions.xAssertEquals(report,decValue,exp_pOValue,"Field Matrix Cost value updated");
        }catch (Exception e){
            Utility_Functions.xIsElementDisplayed(report, driver.findElement(MatrixCostUpdatePage.invalidErrorPopUp), "Invalid input popUp "+fieldValue);
        }
    }
}
