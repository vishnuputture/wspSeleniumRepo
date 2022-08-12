package businesskeywords.common;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import pages.common.SqlStatementPage;
import pages.pricing.SpecialPricePage;
import supportLibraries.Utility_Functions;

public class SqlStatement extends ReusableLib {

    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public SqlStatement(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * This method Validates the record created in  UI exists in DB. The query is fetched from common action sqlRetrieveSpecialPrice method
     */

    public void retrieveAndValidateRetrievedSpPrice() {

        commonObj.goToSqlApp();
        commonObj.sqlRetrieveSpecialPrice(Utility_Functions.xGetJsonAsString("CustomerNo"), Utility_Functions.xGetJsonAsString("ItemNo"));

        if (Utility_Functions.xWaitForElementPresent(ownDriver, SqlStatementPage.resultRow, 5)) {
            String result = Utility_Functions.getText(ownDriver, SqlStatementPage.resultRow);
            System.out.println("Text: " + result);
            result = result.replaceAll("\\s", "");
            result = result.replaceAll("\\.0*$", "");
            String cust_item = Utility_Functions.xGetJsonAsString("CustomerNo") + Utility_Functions.xGetJsonAsString("ItemNo");
            Utility_Functions.xAssertEquals(report, "1" + Utility_Functions.xGetJsonAsString("CustomerNo") + Utility_Functions.xGetJsonAsString("ItemNo") + jsonData.getData("specialPrice"), result, "Validating result set");
            //Utility_Functions.xAssertEquals(report, "1" + Utility_Functions.xGetJsonAsString("CustomerNo") + Utility_Functions.xGetJsonAsString("ItemNo"), result, "Validating result set");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + SqlStatementPage.resultRow);
        }
    }

    /**
     * This method Validates the record deleted in  UI doesnt exists in DB. The query is fetched from common action sqlRetrieveSpecialPrice method
     * The UI should display End Of data instead of ant records.
     */
    public void retrieveAndValidateDeletedSpPrice() {
        commonObj.goToSqlApp();
        commonObj.sqlRetrieveSpecialPrice(Utility_Functions.xGetJsonAsString("CustomerNo"), Utility_Functions.xGetJsonAsString("ItemNo"));

        if (Utility_Functions.xWaitForElementPresent(ownDriver, SqlStatementPage.resultRow, 5)) {
            String result = Utility_Functions.getText(ownDriver, SqlStatementPage.resultRow);
            System.out.println("Text: " + result);
            Utility_Functions.xAssertEquals(report, "********  End of data  ********", result, "No Record-End of data");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + SqlStatementPage.resultRow);
        }
    }

    /**
     * This method first inserts PAP record in the DB  and then validates from UI that PAP records cant be deleted.
     */


    public void insertSpecialPricePAPRecord() {


        commonObj.goToSqlApp();


        commonObj.sqlInsertSpecialPricePAP(Utility_Functions.xGetJsonAsString("CustomerNo"), Utility_Functions.xGetJsonAsString("ItemNo"), jsonData.getData("Date1"), jsonData.getData("Date2"));

        if (Utility_Functions.xWaitForElementPresent(ownDriver, SqlStatementPage.resultRowInsert, 5)) {
            String result = Utility_Functions.getText(ownDriver, SqlStatementPage.resultRowInsert);
            System.out.println("Text: " + result);
            Utility_Functions.xAssertEquals(report, "1 rows inserted in IM08 in DTA99599.", result, "Validating Successful Insert");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + SqlStatementPage.resultRow);
        }
    }

    /**
     * This methods is used to exit sql instance from UI
     */

    public void exitSql() {
        click(SpecialPricePage.btnF3, "Click on F3");
        if (Utility_Functions.xWaitForElementPresent(ownDriver, SqlStatementPage.sqlTitle, 5)) {
            click(SpecialPricePage.btnF3, "Click on F3");
            sendKeys(SqlStatementPage.choiceTxtBox, "2");
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        } else {
            throw new NoSuchElementException("Could not find :" + SqlStatementPage.sqlTitle);
        }
    }

    /**
     * This method is used to exit sql instance after inserting an record in UI
     */
    public void exitInsertSql() {
        click(SpecialPricePage.btnF3, "Click on F3");
        if (Utility_Functions.xWaitForElementPresent(ownDriver, SqlStatementPage.sqlTitle, 5)) {
            //	click(SpecialPricePage.btnF3,"Click on F3");
            sendKeys(SqlStatementPage.choiceTxtBox, "2");
            Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        } else {
            throw new NoSuchElementException("Could not find :" + SqlStatementPage.sqlTitle);
        }
    }

}
