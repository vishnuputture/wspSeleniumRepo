package businesskeywords.Pricing.MatrixCostUpdate;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Util;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import pages.pricing.AddSpecialPricingPage;
import pages.pricing.MatrixCostUpdatePage;
import supportLibraries.Utility_Functions;

public class MatrixCostUpdate extends ReusableLib {
    CommonActions commonObj;
    public static String exp_itemNumber;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public MatrixCostUpdate(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    /**
     * This method navigate To Matrix Cost Update Page
     *
     */
    public void navigateToMatrixCostUpdatePage() {
        commonObj.masterToInventory();
        commonObj.InventoryManagementMainMenuToInventoryManagementMenu2();
        commonObj.InventoryManagementMenu2ToMatrixCostUpdate();
    }

    /**
     * This method navigates from Matrix Cost Update page to Inventory Management-Menu2 page
     *
     */
    public void exitMatrixToInventoryManagementMenu2Page() {
        Utility_Functions.xWaitForElementVisible(driver, MatrixCostUpdatePage.exitButton, 5);
        click(MatrixCostUpdatePage.exitButton,"Click F3-Exit");
        clickIfAvlble(MatrixCostUpdatePage.warningMessagePopUp);
        Utility_Functions.xWaitForElementPresent(driver, MatrixCostUpdatePage.inventoryManagementMenu2Header, 5);
    }

    /**
     * This method navigates from Inventory Management-Menu2 to Inventory Management MainMenu page
     *
     */
    public void exitInventoryMenu2ToInventoryManagementMainMenuPage() {
        Utility_Functions.xWaitForElementVisible(driver, AddSpecialPricingPage.btnF12, 5);
        click(AddSpecialPricingPage.btnF12,"Click F12-Back");
    }

    /**
     * This method to validate PO value
     *
     */
    public void validatePOValue(String itemNo,String exp_poValue) {
        vrfyElmentExist(MatrixCostUpdatePage.validateUpdatedPoValue(itemNo),"Item Number");
        String act_pOValue=driver.findElement(MatrixCostUpdatePage.validateUpdatedPoValue(itemNo)).getAttribute("value");
        Utility_Functions.xAssertEquals(report,act_pOValue,exp_poValue,"Updated PO value ");
        //Utility_Functions.validateFieldMatch(report,driver.findElement(MatrixCostUpdatePage.validateUpdatedPoValue(itemNo)),act_pOValue);
    }

    /**
     * This method to validate item Number
     *
     */
    public void validateItemNumber(String exp_itemNumber) {
        vrfyElmentExist(MatrixCostUpdatePage.validateItemNumber(exp_itemNumber),"Item Number");
        String act_itemNumber=driver.findElement(MatrixCostUpdatePage.validateItemNumber(exp_itemNumber)).getText();
        Utility_Functions.xAssertEquals(report,act_itemNumber,exp_itemNumber,"Item Number ");
        //Utility_Functions.validateFieldMatch(report,driver.findElement(MatrixCostUpdatePage.validateItemNumber(exp_itemNumber)),act_itemNumber);
    }

    /**
     * This method to validate Select Functionality
     *
     */
    public void selectFunctionalityDrop() {
        click(MatrixCostUpdatePage.selectFunDrop,"click select function dropDown");
        Utility_Functions.xIsElementDisplayed(report,driver.findElement(MatrixCostUpdatePage.f1HelpOpt),"Verify 'F1 - Help' option is displayed");
        Utility_Functions.xIsElementDisplayed(report,driver.findElement(MatrixCostUpdatePage.f3ExitOpt),"Verify 'F3 - Exit' option is displayed");
    }

    /**
     * This method to validate F1 - Help Functionality
     *
     */
    public void verifyF1HelpFunctionality() {
        click(MatrixCostUpdatePage.selectFunDrop,"click select function dropDown");
        click(MatrixCostUpdatePage.f1HelpOpt,"click select 'F1 - Help' option from the drop down");
        Utility_Functions.timeWait(5);
        Utility_Functions.xSwitchToWindow(driver,report,1);
        Utility_Functions.xSwitchToParentWin(driver);
    }

    /**
     * This method to validate F3 - Exit Functionality
     *
     */
    public void verifyF3ExitFunctionality() {
        click(MatrixCostUpdatePage.selectFunDrop,"click select function dropDown");
        click(MatrixCostUpdatePage.f3ExitOpt,"click select 'F3 - Exit' option from the drop down");
        Utility_Functions.xIsElementDisplayed(report,driver.findElement(MatrixCostUpdatePage.warningMessagePopUp),"Verify Warning message PopUp is displayed");
        click(MatrixCostUpdatePage.cancelButton,"Click Cancel Button");
        waitForElementClickable(MatrixCostUpdatePage.selectFunDrop,2);
        click(MatrixCostUpdatePage.selectFunDrop,"click select function dropDown");
        waitForElementClickable(MatrixCostUpdatePage.f3ExitOpt,2);
        click(MatrixCostUpdatePage.f3ExitOpt,"click select 'F3 - Exit' option from the drop down");
        click(MatrixCostUpdatePage.updateButton,"Click Yes Button");
    }

    /**
     * This method to navigates to Average Cost
     *
     */
    public void navigateToAverageCost() {
        click(MatrixCostUpdatePage.avgCostRadioBtn,"click Average cost radio button");
    }

    /**
     * This method navigates to Last Cost
     *
     */
    public void navigateToLastCost() {
        click(MatrixCostUpdatePage.lastCostRadioBtn,"click Last cost radio button");
    }

    /**
     * This method Search for an item from the list Pages
     *
     */
    public void searchItemNoFromTheList() throws InterruptedException {
        String exp_itemNumber=ValidateItemNoFromItemMaster.values.get(0);
        Thread.sleep(8000);
        System.out.println("exp_itemNumber "+exp_itemNumber);
        String totalPage= getText(MatrixCostUpdatePage.totalPages);
        int pages=Integer.parseInt(totalPage);
        for(int i=1;i<=pages;i++) {
            try{
            /*Boolean bl=Utility_Functions.validateLinks(Utility_Functions.findElementsByXpath(driver, "//a[@href='javascript:void(0)']"), exp_itemNumber);
                System.out.println(bl);
                if(bl==false){int k=1/0;}*/
                validateItemNumber(exp_itemNumber);
                validatePOValue(exp_itemNumber,ValidateItemNoFromItemMaster.values.get(1));
            Utility_Functions.xAssertEquals(report,exp_itemNumber,exp_itemNumber,"Item number found in page "+i);
            break;
            }catch (Exception e){
                    clickIfAvlble(MatrixCostUpdatePage.nextPage, "Item number not present on the page and Go to next Page");
                    System.out.println("Page Count " + i);
            }
        }
    }

//TC-148
    /**
     * This method to validate functionality of Exclude M/C Report
     *
     */
    public void updateExcludeMcReport() {
        String itemNo=selectRecord(MatrixCostUpdatePage.excMCRepoRadioBtn);
        System.out.println("ITEM NO :"+itemNo);
        click(MatrixCostUpdatePage.saveButton,"Click save button");
        Utility_Functions.waitForElementVisible(driver,MatrixCostUpdatePage.updateButton,2);
        click(MatrixCostUpdatePage.cancelButton,"Click Cancel Button");
        vrfyElmentExist(MatrixCostUpdatePage.validateItemNumber(itemNo),"Item Number: "+itemNo+" is displayed after click on Cancel button");
        click(MatrixCostUpdatePage.saveButton,"Click save button");
        click(MatrixCostUpdatePage.updateButton,"Click Update button");
        Utility_Functions.xWaitForElementDisappear(driver,MatrixCostUpdatePage.validateItemNumber(itemNo),3);
        Utility_Functions.xIsElementDisplayed(report,driver.findElement(MatrixCostUpdatePage.avgColor),"Item Number: "+itemNo+" is Not displayed after click on Update button");
    }

//TC-148
    /**
     * This method to validate No Action Functionality
     *
     */
    public void validateNoAction() {
        String itemNo=selectRecord(MatrixCostUpdatePage.noActionRadioBtn);
        click(MatrixCostUpdatePage.saveButton,"Click save button");
        click(MatrixCostUpdatePage.continueButton,"Click Continue Button");
        vrfyElmentExist(MatrixCostUpdatePage.validateItemNumber(itemNo),"Item Number: "+itemNo+" is displayed after click on Continue button");
    }

//TC-154
    /**
     * This method to functionality of Discard Button
     *
     */
    public void validateDiscardBtn() {
        String itemNo=selectRecord(MatrixCostUpdatePage.discardButton);
        navigateToLastCost();
        String color=driver.findElement(MatrixCostUpdatePage.lastColor).getCssValue("color");
        Utility_Functions.xAssertEquals(report,color,"rgba(51, 153, 0, 1)","Last Cost Column values are green");
        click(MatrixCostUpdatePage.discardButton,"CLick Discard Button");
        navigateToAverageCost();
        String color1=driver.findElement(MatrixCostUpdatePage.avgColor).getCssValue("color");
        Utility_Functions.xAssertEquals(report,color1,"rgba(51, 153, 0, 1)","Average Cost Column values are green");
        vrfyElmentExist(MatrixCostUpdatePage.validateItemNumber(itemNo),"Item Number: "+itemNo+" is displayed after click on Discard button");
    }

    /**
     * This method to Select records from the table
     *
     */
    public String selectRecord(By by) {
        click(MatrixCostUpdatePage.checkBox,"Unselect CheckBox");
        click(by,"Select record from the list");
        String itemNo=driver.findElement(MatrixCostUpdatePage.itemNumber).getText();
        return itemNo;
    }

    /**
     * This method to functionality of Cancel Button
     *
     */
    public void validateCancelBtn() {
        String itemNo=selectRecord(MatrixCostUpdatePage.cancelButton);
        vrfyElmentExist(MatrixCostUpdatePage.validateItemNumber(itemNo),"Item Number: "+itemNo+" is displayed after click on Cancel button");
    }
}