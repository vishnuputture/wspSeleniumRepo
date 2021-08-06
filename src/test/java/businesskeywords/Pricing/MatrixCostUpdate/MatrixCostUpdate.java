package businesskeywords.Pricing.MatrixCostUpdate;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.opencv.core.Mat;
import org.openqa.selenium.WebElement;
import pages.AddSpecialPricingPage;
import pages.MatrixCostUpdatePage;
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
     * This method navigate from Average Cost To Last Cost and validate Updated PO value
     *
     */
    public void navigateFromAverageCostToLastCost() {
        exp_itemNumber=driver.findElement(MatrixCostUpdatePage.itemNumber).getText();
        click(MatrixCostUpdatePage.lastCostRadioBtn,"Click Last Cost Radio Button");
        Utility_Functions.timeWait(2);
    }

    /**
     * This method navigate from Last Cost To Average Cost and validate Updated PO Value
     *
     */
    public void navigateFromLastCostToAverageCost() {
        String act_itemNumber=driver.findElement(MatrixCostUpdatePage.itemNumber).getText();
        String act_pOValue=driver.findElement(MatrixCostUpdatePage.updatedPoValue).getText();
        click(MatrixCostUpdatePage.avgCostRadioBtn,"Click Average Cost Radio Button");

        Utility_Functions.timeWait(5);

        String exp_itemNumber=driver.findElement(MatrixCostUpdatePage.validateItemNumber(act_itemNumber)).getText();
        String exp_pOValue=driver.findElement(MatrixCostUpdatePage.validateUpdatedPoValue(act_pOValue)).getText();
        Utility_Functions.xAssertEquals(report,act_itemNumber,exp_itemNumber,"Item Number");
        Utility_Functions.xAssertEquals(report,act_pOValue,exp_pOValue,"Updated Po Value");
    }

    /**
     * This method navigates to Average Cost
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
        String exp_itemNumber="*99599014371";
        System.out.println("exp_itemNumber "+exp_itemNumber);
        String totalPage= getText(MatrixCostUpdatePage.totalPages);
        int pages=Integer.parseInt(totalPage);
        for(int i=1;i<=pages;i++) {
            try{
            Boolean bl=Utility_Functions.validateLinks(Utility_Functions.findElementsByXpath(driver, "//a[@href='javascript:void(0)']"), exp_itemNumber);
                System.out.println(bl);
                if(bl==false){int k=1/0;}
            Utility_Functions.xAssertEquals(report,exp_itemNumber,exp_itemNumber,"Item number found in page "+i);
            }catch (Exception e){
                click(MatrixCostUpdatePage.nextPage);
                System.out.println("Page Count "+ i);
            }
        }
        }
}