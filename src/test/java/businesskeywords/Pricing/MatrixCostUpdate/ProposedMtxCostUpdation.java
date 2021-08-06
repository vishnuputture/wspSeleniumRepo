package businesskeywords.Pricing.MatrixCostUpdate;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import pages.MatrixCostUpdatePage;
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
    public void modifyProposedMtxCostField() {
        click(MatrixCostUpdatePage.checkBox,"Disable CheckBox");
        int randNumber=Utility_Functions.xRandomFunction();
        String validValue=Integer.toString(randNumber);
        updateValue(validValue);
        updateValue(jsonData.getData("11_integer_value"));
        updateValue(jsonData.getData("Enter_0"));
    }

    public void updateValue(String value){
        try {
            System.out.println(value);
            String decValue=Utility_Functions.xformatVal(value,".0000");
            System.out.println(decValue);
            Utility_Functions.xSendKeys(driver, report, MatrixCostUpdatePage.ProposedMtxCost, value, "Enter "+value+" into Proposed Matrix Cost Field");
            click(MatrixCostUpdatePage.saveButton, "Click Save Changes Button");
            click(MatrixCostUpdatePage.updateButton, "Click Update Button");
            Utility_Functions.timeWait(2);
            String exp_pOValue = driver.findElement(MatrixCostUpdatePage.ProposedMtxCost).getAttribute("value");
            Utility_Functions.xAssertEquals(report,decValue,exp_pOValue,"Proposed Matrix Cost value updated");
        }catch (Exception e){
            Utility_Functions.xIsElementDisplayed(report, driver.findElement(MatrixCostUpdatePage.invalidErrorPopUp), "Invalid input popUp"+value);
        }
    }
}
