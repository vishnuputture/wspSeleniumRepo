package businesskeywords.Pricing.MatrixCostUpdate;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import pages.pricing.MatrixCostUpdatePage;
import pages.pricing.PricingMatrixPage;
import pages.pricing.SpecialPricePage;
import supportLibraries.Utility_Functions;

public class PricingMatrix extends ReusableLib {
    CommonActions commonObj;
    public static String exp_itemNumber;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public PricingMatrix(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    /**
     * This method navigate To Pricing Matrix Page
     *
     */
    public void navigateToPricingMatrixPage() {
        commonObj.masterToOrderProcessing();
        commonObj.navigateToOptionsAndConstantsMenu();
        commonObj.navigateToPricingMatrix();
    }

    /**
     * This method Navigation to ACMTRW Program
     *
     */
    public void navToaCMTRWProgram() {
        click(PricingMatrixPage.addRow,"Click F1=Add Row");
    }

    /**
     * This method to Add a row to Pricing Matrix
     *
     */
    public int addRowToPricingMatrix() {
        navToaCMTRWProgram();
        sendKeys(PricingMatrixPage.actionField,"A","Insert 'A' to Action field");
        int rowName=Utility_Functions.xRandomFunction(999);
        sendKeys(PricingMatrixPage.mtxRowCode,""+rowName+"");
        sendKeys(PricingMatrixPage.descLine1,"Test Automation","Insert description in Description Line1 field");
        click(PricingMatrixPage.enterLink,"Press ENTER");
        return rowName;
    }

    /**
     * This method Validate Row ia added
     *
     */
    public void addRowAndValidateRowAdded() {
        int row=addRowToPricingMatrix();
        String name=Integer.toString(row);
        click(SpecialPricePage.btnF3,"Click F3=Exit");
        sendKeys(PricingMatrixPage.mtxRowCode,name);
        click(PricingMatrixPage.enterLink,"Press ENTER");
        String exp=driver.findElement(PricingMatrixPage.validateRow(name)).getText();
        Utility_Functions.xAssertEquals(report,exp,name,"Row added");
    }

    /**
     * This method Exit Pricing Matrix
     *
     */
    public void exitPricingMatrix() {
        commonObj.exitFromPricingMatrix();
    }
}
