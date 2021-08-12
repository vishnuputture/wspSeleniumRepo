package businesskeywords.Pricing.MatrixCostUpdate;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Util;
import commonkeywords.CommonActions;
import org.apache.commons.lang3.StringUtils;
import org.opencv.core.Mat;
import org.openqa.selenium.By;
import pages.pricing.MatrixCostUpdatePage;
import supportLibraries.Utility_Functions;

public class PoCostUpdation extends ReusableLib {
    CommonActions commonObj = new CommonActions(helper);
    ProposedMtxCostUpdation propMtrxUpd=new ProposedMtxCostUpdation(helper);
    MatrixCostUpdate mtxCostUpd=new MatrixCostUpdate(helper);

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public PoCostUpdation(Helper helper) {
        super(helper);
    }

    /**
     *
     * This method to modify PO field with different values
     *
     */
    public void verifyPOFieldWithDiffValues() {
        mtxCostUpd.selectRecord(MatrixCostUpdatePage.radioButton);
        propMtrxUpd.CancelUpdatedValue(fieldWithValidRandomValue(),MatrixCostUpdatePage.poField);
        propMtrxUpd.CancelUpdatedValue(jsonData.getData("11_integer_value"),MatrixCostUpdatePage.poField);
        propMtrxUpd.CancelUpdatedValue(jsonData.getData("Enter_0"),MatrixCostUpdatePage.poField);
    }

    /**
     *
     * This method to modify field with Valid Random values
     *
     */
    public String fieldWithValidRandomValue() {
        int randNumber=Utility_Functions.xRandomFunction();
        String validValue=Integer.toString(randNumber);
        int leg=validValue.length();
        System.out.println("Leght of random value: "+leg);
        if(leg>7){
            validValue= StringUtils.chop(validValue);
            System.out.println("Chopped last string"+validValue);
        }
        return validValue;
    }

    /**
     *
     * This method to Unselect and select checkbox and save Records
     *
     */
    public void unSelectAndSelectChekBox() {
        String checked=driver.findElement(MatrixCostUpdatePage.checkBox).getAttribute("checked");
        System.out.println("Is it checked  : "+checked);
        try{
            checked.equals("true");
            System.out.println("Entered if");
            click(MatrixCostUpdatePage.checkBox,"Disable the Update Matrix Cost checkBox");
        }catch (Exception e){
            System.out.println("Disabled the Update Matrix Cost checkBox");
        }
        mtxCostUpd.navigateToAverageCost();
        String color=driver.findElement(MatrixCostUpdatePage.avgColor).getCssValue("color");
        System.out.println("Color : "+color);
        Utility_Functions.xAssertEquals(report,color,"rgba(51, 153, 0, 1)","Average Cost Column values are green");
        click(MatrixCostUpdatePage.checkBox,"Select Check Box");
        waitForElementClickable(MatrixCostUpdatePage.saveButton,3);
        click(MatrixCostUpdatePage.saveButton);
        Utility_Functions.xIsElementDisplayed(report,driver.findElement(MatrixCostUpdatePage.continueButton),"Verify 'No changes to Update' PopUp is displayed");
        waitForElementClickable(MatrixCostUpdatePage.continueButton,3);
        click(MatrixCostUpdatePage.continueButton,"Click continue button");
    }

    /**
     *
     * This method to unselect Checkbox and navigate to Average Cost
     *
     */
    public void unSelToAvgCost() {
        String checked = driver.findElement(MatrixCostUpdatePage.checkBox).getAttribute("checked");
        System.out.println("Is it checked  : " + checked);
        try {
            checked.equals("true");
            System.out.println("Entered if");
            click(MatrixCostUpdatePage.checkBox, "Disable the Update Matrix Cost checkBox");
        } catch (Exception e) {
            System.out.println("Disabled the Update Matrix Cost checkBox");
        }
        mtxCostUpd.navigateToAverageCost();
        String color = driver.findElement(MatrixCostUpdatePage.avgColor).getCssValue("color");
        System.out.println("Color : " + color);
        Utility_Functions.xAssertEquals(report, color, "rgba(51, 153, 0, 1)", "Average Cost Column values are green");
    }


    /**
     *
     * This method to Validate PO Field value
     *
     */
    public void validatePoValue(String value){
        try {
            String decValue=updateField(value,MatrixCostUpdatePage.poField);
            String exp_pOValue = driver.findElement(MatrixCostUpdatePage.poField).getAttribute("value");
            Utility_Functions.xAssertEquals(report,decValue,exp_pOValue,"PO value updated");
        }catch (Exception e){
            Utility_Functions.xIsElementDisplayed(report, driver.findElement(MatrixCostUpdatePage.invalidErrorPopUp), "Invalid input PopUp"+value);
        }
    }

    /**
     *
     * This method to update field value and click discard button
     *
     */
    public void updateFieldClickDiscard() {
        updateField(fieldWithValidRandomValue(),MatrixCostUpdatePage.poField);
        click(MatrixCostUpdatePage.discardButton,"CLick Discard Button");
    }

    /**
     *
     * This method to update Filed
     *
     */
    public String updateField(String fieldValue,By by){
        System.out.println("Field Value "+fieldValue);
        String decValue;
        if(fieldValue.equals("0")) {
            decValue = Utility_Functions.xformatVal(fieldValue, ".0000");
        }else{
        decValue = Utility_Functions.xformatVal(fieldValue, ".000");
        }
        System.out.println("Dec value "+decValue);
        Utility_Functions.timeWait(2);
        Utility_Functions.xSendKeys(driver, report, by, fieldValue, "Enter "+fieldValue+" into Field");
        click(MatrixCostUpdatePage.saveButton, "Click Save Changes Button");
        click(MatrixCostUpdatePage.updateButton, "Click Update Button");
        Utility_Functions.timeWait(2);
        return decValue;
    }
}
