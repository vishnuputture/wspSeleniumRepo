package businesskeywords.Pricing.MatrixCostUpdate;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import pages.MatrixCostUpdatePage;
import supportLibraries.Utility_Functions;

public class PoCostUpdation extends ReusableLib {
    CommonActions commonObj;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public PoCostUpdation(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    /**
     *
     *
     * This method to modify PO field with different values
     *
     */
    public void modifyPOField() {
        click(MatrixCostUpdatePage.checkBox,"Disable CheckBox");
        int randNumber=Utility_Functions.xRandomFunction();
        String validValue=Integer.toString(randNumber);
        updatePOValue(validValue);
        updatePOValue(jsonData.getData("11_integer_value"));
        updatePOValue(jsonData.getData("Enter_0"));
    }

    public void updatePOValue(String value){
        try {
            String decValue=update(value);
            String exp_pOValue = driver.findElement(MatrixCostUpdatePage.poField).getAttribute("value");
            Utility_Functions.xAssertEquals(report,decValue,exp_pOValue,"PO value updated");
        }catch (Exception e){
            Utility_Functions.xIsElementDisplayed(report, driver.findElement(MatrixCostUpdatePage.invalidErrorPopUp), "Invalid input PopUp"+value);
        }
    }

    public String update(String value){
        System.out.println(value);
        String decValue=Utility_Functions.xformatVal(value,".0000");
        System.out.println(decValue);
        Utility_Functions.xSendKeys(driver, report, MatrixCostUpdatePage.poField, value, "Enter "+value+" into PO Field");
        click(MatrixCostUpdatePage.saveButton, "Click Save Changes Button");
        click(MatrixCostUpdatePage.updateButton, "Click Update Button");
        Utility_Functions.timeWait(2);
        return decValue;
    }
}
