package businesskeywords.Pricing.MatrixCostUpdate;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
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
     * This method to display Starting rows and columns
     *
     */
    public void disRow_Col() {
        sendKeys(PricingMatrixPage.strRow,"");
        sendKeys(PricingMatrixPage.strCol,"");
        click(PricingMatrixPage.enterLink);
    }

    /**
     * This method to Copy Row to Pricing Matrix
     *
     */
    public void copyRowPricingMtx() {
        disRow_Col();
        click(PricingMatrixPage.copyRow,"click F2=Cpy Row");
        String rowFrom=getRow(PricingMatrixPage.firstRow);
        String rowTo=getRow(PricingMatrixPage.secondRow);
        sendKeys(PricingMatrixPage.copyRowFrom,rowFrom);
        sendKeys(PricingMatrixPage.copyRowTo,rowTo,"Copy Row from: "+rowFrom+" to Row: "+rowTo+"");

        String colFrom=getRow(PricingMatrixPage.firstCol);
        String colTo=getRow(PricingMatrixPage.secondCol);
        sendKeys(PricingMatrixPage.selectColFrom,colFrom);
        sendKeys(PricingMatrixPage.selectColTo,colTo,"Select Column from: "+colFrom+" to Column: "+colTo+"");
        Utility_Functions.timeWait(2);
        click(PricingMatrixPage.enterLink,"Click ENTER");
        Utility_Functions.timeWait(2);
        sendKeys(PricingMatrixPage.notBlank,"N");
        click(PricingMatrixPage.enterLink,"Click ENTER");
        Utility_Functions.xClickIfAvlbl(driver,PricingMatrixPage.f8END);
        disRow_Col();
        String actRow1=driver.findElement(PricingMatrixPage.firstCell).getAttribute("value");
        String expRow1=driver.findElement(PricingMatrixPage.cellSecRow).getAttribute("value");
        System.out.println("from copy "+actRow1);
        System.out.println("from home "+expRow1);
        String actRow2=driver.findElement(PricingMatrixPage.secondCell).getText();
        String expRow2=driver.findElement(PricingMatrixPage.cellSecRowSecCol).getText();
        Utility_Functions.xVrfyTextAvlble(report,expRow1,actRow1,"Row copied from: "+rowFrom+" to Row: "+rowTo+" ");
    }

    /**
     * This method to get row name
     *
     */
    public String getRow(By by) {
        String row=driver.findElement(by).getText();
        return row;
    }

    /**
     * This method to get column name
     *
     */
    public String getCol(By by) {
        String col=driver.findElement(by).getText();
        return col;
    }

    /**
     * This method Exit Pricing Matrix
     *
     */
    public void exitPricingMatrix() {
        commonObj.exitFromPricingMatrix();
    }
}
