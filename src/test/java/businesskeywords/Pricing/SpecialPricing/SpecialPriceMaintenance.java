package businesskeywords.Pricing.SpecialPricing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;

import commonkeywords.*;

import org.openqa.selenium.WebElement;
import pages.pricing.SpecialPricePage;
import pages.MasterPage;
import supportLibraries.Utility_Functions;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import java.util.Arrays;
import java.util.List;

public class SpecialPriceMaintenance extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    private CommonActions commonObj;

    public SpecialPriceMaintenance(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    /**
     * This method is invoked to delete special price record created during the test case and exit the maintenance page
     */
    public void delSpecialPriceRecordandExit() {
        //CommonActions commonObj= new CommonActions(helper);
        try {
            commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance", "Validating special price page title");
        } catch (NoSuchElementException e) {
            commonObj.masterToOrderProcessing();
            commonObj.orderProcToSplPricing();
            //commonObj.splPricingToAddPricing();

            commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance", "Validating special price page title");
        }

        String optXpath = "//div[@class='A20'][text()='" + Utility_Functions.xGetJsonAsString("CustomerNo") + "']/preceding-sibling::input[1]";
        sendKeys(By.xpath(optXpath), "4");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        click(SpecialPricePage.btnProcessF9);
        click(SpecialPricePage.btnF3);
    }

    /**
     * This method is invoked to delete special price record created during the test case
     */
    public void delAddedSpclPrice() {
        //CommonActions commonObj= new CommonActions(helper);
        try {
            commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance", "Validating special price page title");
        } catch (NoSuchElementException e) {
            commonObj.masterToOrderProcessing();
            commonObj.orderProcToSplPricing();
            //commonObj.splPricingToAddPricing();

            commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance", "Validating special price page title");
        }
        sendKeys(SpecialPricePage.posToCustNo, "0");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        String optXpath = "//div[@class='A20'][text()='" + Utility_Functions.xGetJsonAsString("CustomerNo") + "']/preceding-sibling::input[1]";
        sendKeys(By.xpath(optXpath), "4");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        click(SpecialPricePage.btnProcessF9, "Processing special price delete request");
    }

    /**
     * This method deletes all the special price record displayed on maintenance page
     *
     * @throws InterruptedException
     */

    public void delAllPriceRecord() throws InterruptedException {
        try {
            commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance", "Validating special price page title");
        } catch (NoSuchElementException e) {
            commonObj.masterToOrderProcessing();
            commonObj.orderProcToSplPricing();

            commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance", "Validating special price page title");
        }
        sendKeys(SpecialPricePage.posToCustNo, "0");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        List<WebElement> eleList = driver.findElements(SpecialPricePage.selectPricingRecordBox);
        if (eleList.size() > 0) {
            for (WebElement element : eleList) {
                sendKeys(element, "4");
            }
            Utility_Functions.actionKey(Keys.ENTER, driver);
            waitForElementDisappear(MasterPage.loadingAnime, globalWait);
            click(SpecialPricePage.btnProcessF9, "Processing special price delete request");
        }

    }

    /**
     * This method validates the default special price filter value displayed on maintenance page
     */

    public void validateSplPriceFilterValue() {
        //CommonActions commonObj= new CommonActions(helper);
        try {
            commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance", "Validating special price page title");
        } catch (NoSuchElementException e) {
            commonObj.masterToOrderProcessing();
            commonObj.orderProcToSplPricing();
            //commonObj.splPricingToAddPricing();
            commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance", "Validating special price page title");
        }
        Utility_Functions.xAssertEquals(report, jsonData.getData("filterValue"), getValue(SpecialPricePage.filterTxtBox), "Validating default filter value");
    }

    /**
     * This method validates the sorted list of special prices displayed on the maintenance page as per the customer number provided as filter
     */
    public void positionCustNum() {
        //sendKeys(SpecialPricePage.posToCustNo,"250");
        String[] custNumList = jsonData.getData("validCustNum").split(",");
        String[] tempCustNumList = custNumList;
        int[] custNumIntList = Arrays.stream(custNumList).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < custNumIntList.length; i++) {
            //int x =Integer.parseInt(custNumList[i]);
            if (custNumIntList[i] < Integer.parseInt(jsonData.getData("filterCustNum"))) {
                //custNumList = ArrayUtils.remove(custNumList, i);
                custNumList = ArrayUtils.removeElement(custNumList, tempCustNumList[i]);
            }
        }
        sendKeys(SpecialPricePage.posToCustNo, jsonData.getData("filterCustNum"), "Entering customer number to filter");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        if (custNumList.length > 0) {
            Arrays.sort(custNumList);
            //String[] custNumStringList=Arrays.stream(custNumIntList).sorted().mapToObj(String::valueOf).toArray(String[]::new);
            for (int i = 0; i < custNumList.length; i++) {
                Utility_Functions.xAssertEquals(report, custNumList[i], driver.findElement(By.xpath("//div[starts-with(@id,'D_1" + i + "')]")).getText(), "Validating filtered result");
            }
        } else {
            if (!Utility_Functions.isExist(driver, SpecialPricePage.selectPricingRecordBox)) {
                report.updateTestLog("VerifyRecord", "No special price record should be displayed", Status.PASS);
            } else {
                report.updateTestLog("VerifyRecord", "No special price record should be displayed", Status.FAIL);
            }
        }


    }

    /**
     * This method validates the sorted list of special prices displayed on the maintenance page when sorted by Item number and the filter value is lower than the lowest item number
     */
    public void positionCustItemBegin() {
        String[] custNumList = jsonData.getData("validCustNum").split(",");
        String[] itemNumList = jsonData.getData("validItemNum").split(",");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sendKeys(SpecialPricePage.posToItemNo, jsonData.getData("filterItemNum"), "Entering item number to filter");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        for (int i = 0; i < custNumList.length; i++) {
            Utility_Functions.xAssertEquals(report, custNumList[i], driver.findElement(By.xpath("//div[starts-with(@id,'D_1" + i + "')]")).getText(), "Validating filtered result");
            Utility_Functions.xAssertEquals(report, itemNumList[i], driver.findElement(By.xpath("//div[@id='D_1" + i + "_13']")).getText(), "Validating filtered result");
        }

        click(SpecialPricePage.sortByCustNumBtn, "");

    }

    /**
     * This method validates the sorted list of special prices displayed on the maintenance page when sorted by Item number and the filter value is higher than the highest item number
     */
    public void positionCustItemEnd() {
        sendKeys(SpecialPricePage.posToItemNo, jsonData.getData("filterItemNum"), "Entering item number to filter");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        if (!Utility_Functions.isExist(driver, SpecialPricePage.selectPricingRecordBox)) {
            report.updateTestLog("VerifyRecord", "No special price record should be displayed", Status.PASS);
            commonObj.validateText(SpecialPricePage.noItemMsgLbl, "No Pricing Records to Display", "Validating special price message for no records");
        } else {
            report.updateTestLog("VerifyRecord", "No special price record should be displayed", Status.FAIL);
        }
        driver.findElement(SpecialPricePage.posToItemNo).clear();
        Utility_Functions.actionKey(Keys.ENTER, driver);
        click(SpecialPricePage.sortByCustNumBtn, "");

    }

    /**
     * This method validates that proper error message is displayed when no special price record is available for display on the special price maintenance page
     */
    public void validateNoSpecialPriceMessage() {
        if (!Utility_Functions.isExist(driver, SpecialPricePage.selectPricingRecordBox)) {
            report.updateTestLog("VerifyRecord", "No special price record should be displayed", Status.PASS);
            commonObj.validateText(SpecialPricePage.noItemMsgLbl, "No Pricing Records to Display", "Validating special price message for no records");
        } else {
            report.updateTestLog("VerifyRecord", "No special price record should be displayed", Status.FAIL);
        }
    }

    /**
     * This method validates the sorted list of special prices displayed on the maintenance page when sorted by Item number and the filter value is higher than the lowest item number and lower than the highest item number
     */
    public void positionItemNumMiddle() {
        String[] custNumList = jsonData.getData("filteredCustNum").split(",");
        String[] itemNumList = jsonData.getData("filteredItemNum").split(",");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sendKeys(SpecialPricePage.posToItemNo, jsonData.getData("filterItemNum"), "Entering item number to filter");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        for (int i = 0; i < custNumList.length; i++) {
            Utility_Functions.xAssertEquals(report, custNumList[i], driver.findElement(By.xpath("//div[starts-with(@id,'D_1" + i + "')]")).getText(), "Validating filtered result");
            Utility_Functions.xAssertEquals(report, itemNumList[i], driver.findElement(By.xpath("//div[@id='D_1" + i + "_13']")).getText(), "Validating filtered result");
        }

        click(SpecialPricePage.sortByCustNumBtn, "");

    }

    /**
     * This method clicks on the sort by item number button
     */
    public void sortByItemNum() {
        click(SpecialPricePage.sortByItemNumBtn, "Sort records by item number");
    }

    /**
     * This method takes the user directly from the special price page to the master page
     */
    public void exitSplPricePage() {
        //CommonActions spCommonObj = new CommonActions(helper);
        commonObj.exitSplPriceToMasterPage();
    }

    public void deleteSpecialPricePAPRecord() {

        try {
            commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance", "Validating special price page title");
        } catch (NoSuchElementException e) {
            commonObj.masterToOrderProcessing();
            commonObj.orderProcToSplPricing();
            //commonObj.splPricingToAddPricing();

            commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance", "Validating special price page title");
        }

        String optXpath = "//div[@class='A20'][text()='" + Utility_Functions.xGetJsonAsString("CustomerNo") + "']/preceding-sibling::input[1]";
        sendKeys(By.xpath(optXpath), "4");
        Utility_Functions.actionKey(Keys.ENTER, driver);
        // 	click(SpecialPricePage.btnProcessF9);
        Utility_Functions.xScrollIntoView(driver, driver.findElement(SpecialPricePage.papMessage));
        String res = driver.findElement(SpecialPricePage.papMessage).getText();
        System.out.println(res);
        Utility_Functions.xAssertEquals(report, "Price as Parent records are display only", res, "Validating pap record exists");
        click(SpecialPricePage.btnF3);
    }
    
    public void cleanUp()
    {
    	DBCall.delteDBTableData();
    }

}
