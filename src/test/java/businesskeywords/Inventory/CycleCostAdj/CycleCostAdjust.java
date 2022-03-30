package businesskeywords.Inventory.CycleCostAdj;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import pages.inventory.CostAdjustmentPage;
import supportLibraries.Utility_Functions;

import java.text.DecimalFormat;

public class CycleCostAdjust extends ReusableLib {
    CommonActions commonObj = new CommonActions(helper);
    public static int exp_Number;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public CycleCostAdjust(Helper helper) {
        super(helper);
    }


        /**
         *
         * This method validates the Cycle Count Adjustment Option
         *
         */
        public void validateCycleCountAdj () {
        commonObj.masterToInventory();
        commonObj.inventoryToInvAdjustments();
        commonObj.validateText(CostAdjustmentPage.cycleCountAdj, "cycle count adjustments", "Validating Cycle Cost Adjustment Option present in the menu under the option 1 Cycle Count Adjustments");
    }

        /**
         *
         * This method to exit from Cycle cost Adjustment
         *
         */
        public void exitFromCycleCostAdj () {
        commonObj.exitFromCycleCostAdj();
    }

        /**
         *
         * This method navigate to Cycle Count Adjustment Option and Enter Valid Value
         *
         */
        public void navCycleCostAdjProgram () {
        click(CostAdjustmentPage.cycleCountAdj, "Click Cycle Cost Adjustments Program");
    }

        /**
         *
         * This method navigate to Cycle Count Adjustment Option and Enter Valid Value
         *
         */
        public String validValue () {
        click(CostAdjustmentPage.cycleCountAdj, "Click Cycle Cost Adjustments Program");
        int num = Utility_Functions.genRandNum(99);
        exp_Number = num;
        String noSpaceStr = selectQuantity("" + num + "");
        int onH = Integer.parseInt(noSpaceStr);
        int res = num - onH;
        System.out.println("res: " + res);
        String quantityValue = driver.findElement(CostAdjustmentPage.quantity).getText();
        int qnt = Integer.parseInt(quantityValue);
        System.out.println("qnt: " + qnt);
        Utility_Functions.xAssertEquals(report, qnt, res, "Quantity: ");
        selectExplanation("Other");
        click(CostAdjustmentPage.process, "Click on Process");
        Utility_Functions.xIsElementDisplayed(report, driver.findElement(CostAdjustmentPage.procConfm), "Process Confirmation PopUp");
        click(CostAdjustmentPage.postBtn, "Click On Post Button and Record(s) should be processed");
        click(driver.findElement(CostAdjustmentPage.continueBtn), "Click on Continue button");
        return quantityValue;
    }

        /**
         *
         * This method navigate to Item Ledger
         *
         */
        public void navLedger () {
        Utility_Functions.timeWait(3);
        Utility_Functions.xmouseOver(driver, CostAdjustmentPage.itemLedger);
        click(CostAdjustmentPage.itemLedger);
        click(CostAdjustmentPage.itemLedSearch);
        sendKeys(CostAdjustmentPage.includeYes, "Y");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        sendKeys(CostAdjustmentPage.optBox, "1", "Item Number already Created in Item Master should be entered");
        Utility_Functions.actionKey(Keys.ENTER, driver);
    }

        /**
         *
         * This method Exit from Item Ledger Program
         *
         */
        public void exitItemLedgerProgram () {
        click(CostAdjustmentPage.extBtnLedger, "Click on Exit Button");
    }

        /**
         *
         * This method to validate Item Ledger Field
         *
         */
        public void validateItemLedgerFiled () {
        String value = validValue();
        navLedger();
        Utility_Functions.waitForElementVisible(driver, CostAdjustmentPage.units, 10);
        String unit = driver.findElement(CostAdjustmentPage.units).getText();
        System.out.println("unit: " + unit);
        String explanation = driver.findElement(CostAdjustmentPage.explanationLedger).getText();
        System.out.println("explanation: " + explanation);
        System.out.println("exp_Number: " + exp_Number);
        Utility_Functions.xAssertEquals(report, driver.findElement(CostAdjustmentPage.qtyAfter).getText(), "" + exp_Number + "", "Qlt After");
        Utility_Functions.xAssertEquals(report, value, unit, "Units: ");
        String exp = explanation.replaceAll("\\s", "");
        Utility_Functions.xAssertEquals(report, exp.toUpperCase(), "OTHER", "Explanation: ");
    }

        /**
         *
         * This method navigate to Cycle Count Adjustment Option
         *
         */
        public void validateUpadtedCount () {
        click(CostAdjustmentPage.cycleCountAdj, "Click Cycle Cost Adjustments Program");
        selectQuantity("687");
        String error = driver.findElement(CostAdjustmentPage.countFiled).getAttribute("title");
        Utility_Functions.xAssertEquals(report, error, "Warning: Quantity exceeds limit of 500. Ensure value is correct.", "Error: ");
        int num = Utility_Functions.genRandNum(99);
        String noSpaceStr = selectQuantity("" + num + "");
        Utility_Functions.timeWait(5);

        int onH = 0;
        if(!noSpaceStr.isEmpty())
            onH = Integer.parseInt(noSpaceStr);

        int res = num - onH;
        System.out.println("res: " + res);
        int qnt = Integer.parseInt(driver.findElement(CostAdjustmentPage.quantity).getText());
        System.out.println("qnt: " + qnt);
        Utility_Functions.xAssertEquals(report, qnt, res, "Quantity: ");
        sendKeys(CostAdjustmentPage.explanation, "OTHER@#097", "Enter alpha numeric value along with special characters in the explanation box");
        selectExplanation("Other");
        click(CostAdjustmentPage.process, "Click on Process");
        Utility_Functions.xWaitForElementVisible(driver, driver.findElement(CostAdjustmentPage.procConfm), 15);
        Utility_Functions.xIsElementDisplayed(report, driver.findElement(CostAdjustmentPage.procConfm), "Process Confirmation PopUp");
        click(CostAdjustmentPage.cancelBtn, "Click On Cancel Button");
        Utility_Functions.xIsElementDisplayed(report, driver.findElement(CostAdjustmentPage.quantity), "No adjustment is made for the item");
        click(CostAdjustmentPage.process, "Click on Process");
        Utility_Functions.xIsElementDisplayed(report, driver.findElement(CostAdjustmentPage.procConfm), "Process Confirmation PopUp");
        click(CostAdjustmentPage.postBtn, "Click On Post Button and Record(s) should be processed");
        click(driver.findElement(CostAdjustmentPage.continueBtn), "Click on Continue button");
        Utility_Functions.xWaitForElementDisappear(driver, CostAdjustmentPage.quantity, 3);
    }

        /**
         *
         * This method to Negative value on Count Field
         *
         */
        public void nagValidation () {
        click(CostAdjustmentPage.cycleCountAdj, "Click Cycle Cost Adjustments Program");
        selectQuantity("-12");
        String color = driver.findElement(CostAdjustmentPage.countFiled).getCssValue("border-color");
        System.out.println("color: " + color);
        Utility_Functions.xAssertEquals(report, color, "rgb(204, 0, 0)", "Error Color: ");
        String error = driver.findElement(CostAdjustmentPage.countFiled).getAttribute("title");
        Utility_Functions.xAssertEquals(report, error, "Quantity is missing or invalid", "Error: ");
    }

        /**
         *
         * This method to get Explanation
         *
         */
        public void selectExplanation (String value){
        sendKeys(CostAdjustmentPage.explanation, value, "Enter " + value + " to Explanation field");
    }

        /**
         *
         * This method to get quantity
         *
         */
        public String selectQuantity (String count){
        sendKeys(CostAdjustmentPage.countFiled, count, "Quantity");
        click(CostAdjustmentPage.searchIcon);
        Utility_Functions.xWaitForElementVisible(driver, CostAdjustmentPage.includeYes, 15);
        sendKeys(CostAdjustmentPage.includeYes, "Y");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        sendKeys(CostAdjustmentPage.optBox, "1", "Item Number already Created in Item Master should be entered");
        String onHold = driver.findElement(CostAdjustmentPage.onHold).getText();
        System.out.println("onHold: " + onHold);
        String noSpaceStr = onHold.replaceAll("\\s", "");
        System.out.println(noSpaceStr);
        Utility_Functions.actionKey(Keys.ENTER, driver);
        return noSpaceStr;
    }

        /**
         *
         * This method to get Total adjusted value from the table
         *
         */
        public void verifyCount () {
        int num = Utility_Functions.genRandNum(99);
        selectQuantity("" + num + "");
        sendKeys(CostAdjustmentPage.countFiled2, "" + num + "", "Quantity");
        selectExplanation("Other");
        click(CostAdjustmentPage.searchIcon2);
        exp_Number = num;
        Utility_Functions.timeWait(2);
        sendKeys(CostAdjustmentPage.optBox, "1", "Item Number already Created in Item Master should be entered");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        getCount();
        getAdjQt();
        getAmount();
        click(CostAdjustmentPage.process, "Click on Process");
        Utility_Functions.xIsElementDisplayed(report, driver.findElement(CostAdjustmentPage.procConfm), "Process Confirmation PopUp");
        click(CostAdjustmentPage.postBtn, "Click On Post Button and Record(s) should be processed");
        click(driver.findElement(CostAdjustmentPage.continueBtn), "Click on Continue button");
        Utility_Functions.xWaitForElementDisappear(driver, CostAdjustmentPage.quantity, 3);
    }

        /**
         *
         * This method to get count
         *
         */
        public int getCount () {
        int cont = exp_Number + exp_Number;
        System.out.println("cont: " + cont);
        String countNumb = driver.findElement(CostAdjustmentPage.countMod).getText();
        System.out.println("countNumb: " + countNumb);
        String exp = countNumb.replaceAll("\\s", "");
        int cn = Integer.parseInt(exp);
        Utility_Functions.xAssertEquals(report, cn, cont, "Count: ");
        return cn;
    }

        /**
         *
         * This method to get Adj Quantity
         *
         */
        public int getAdjQt () {
        String fAdjQt = driver.findElement(CostAdjustmentPage.firRowAdj).getText();
        String adjAxp = fAdjQt.replaceAll(".00", "");
        String seAdjQt = driver.findElement(CostAdjustmentPage.secRowAdj).getText();
        String secExp = seAdjQt.replaceAll(".00", "");
        System.out.println("fAdjQt: " + adjAxp);
        System.out.println("seAdjQt: " + secExp);
        int fRow = Integer.parseInt(adjAxp);
        int sRow = Integer.parseInt(secExp);
        int adjQt = fRow + sRow;
        String qnt = driver.findElement(CostAdjustmentPage.adjQtyMod).getText();
        int adjustQt = Integer.parseInt(qnt);
        Utility_Functions.xAssertEquals(report, adjQt, adjustQt, "AdjQt: ");
        return adjustQt;
    }

        /**
         *
         * This method to get Amount
         *
         */
        public String getAmount () {
        String fAmount = driver.findElement(CostAdjustmentPage.firRowAmt).getText();
        String fAmountxp = fAmount.replaceAll(",", "");
        String seAmount = driver.findElement(CostAdjustmentPage.secRowAmt).getText();
        String seAmountxp = seAmount.replaceAll(",", "");
        System.out.println("fAmount: " + fAmountxp);
        System.out.println("seAmount: " + seAmountxp);

        double d = Double.parseDouble(fAmountxp);
        System.out.println(d);

        double d1 = Double.parseDouble(seAmountxp);
        System.out.println(d1);

        double amt = d + d1;
        String amont = driver.findElement(CostAdjustmentPage.amountyMod).getText();
        double amount = Double.parseDouble(amont);
        System.out.println("amount : "+amount);
        Utility_Functions.xAssertEquals(report, "" + amt + "", "" + amount + "", "Amount: ");
        return amont;
    }
}