package businesskeywords;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;

import commonkeywords.CommonActions;


import org.openqa.selenium.WebElement;

import pages.SqlStatementPage;
import pages.MasterPage;
import pages.SpecialPricePage;
import supportLibraries.Utility_Functions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SqlStatement extends ReusableLib{

	CommonActions commonObj;
	/**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public SqlStatement(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }
    
    /*public void sqlRetrieveSpecialPrice() {
    	sendKeys(SqlStatementPage.sqltxtBox1,"select * from im08");
    	sendKeys(SqlStatementPage.sqltxtBox2,"where cacust = '"+jsonData.getData("validCustNum")+"'");
    	sendKeys(SqlStatementPage.sqltxtBox3,"and caitem = '"+jsonData.getData("itemNum")+"'","Enter Sql statement to retrieve data");
    	
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    }*/
    
    public void retrieveAndValidateRetrievedSpPrice() {
    	//CommonActions sqlComObj = new CommonActions(helper);
    	commonObj.goToSqlApp();
    	commonObj.sqlRetrieveSpecialPrice(Utility_Functions.xGetJsonAsString("CustomerNo"),Utility_Functions.xGetJsonAsString("ItemNo"));
    	//masterComObj.goToSqlApp();
    	
    	if(Utility_Functions.xWaitForElementPresent(driver,SqlStatementPage.resultRow, 5)) {
    		String result = Utility_Functions.getText(driver,SqlStatementPage.resultRow);
    		System.out.println("Text: "+result);
    		result=result.replaceAll("\\s", "");
    		result=result.replaceAll("\\.0*$", "");
    		Utility_Functions.xAssertEquals(report, "1"+Utility_Functions.xGetJsonAsString("CustomerNo")+Utility_Functions.xGetJsonAsString("ItemNo")+jsonData.getData("specialPrice"),result, "Validating result set");
    	}else {
    		System.out.println("Text: Not found");
    		throw new NoSuchElementException("Could not find :"+SqlStatementPage.resultRow);
    	}
    }
    
    public void retrieveAndValidateDeletedSpPrice() {
    	//CommonActions sqlComObj = new CommonActions(helper);
    	commonObj.goToSqlApp();
    	commonObj.sqlRetrieveSpecialPrice(Utility_Functions.xGetJsonAsString("CustomerNo"),Utility_Functions.xGetJsonAsString("ItemNo"));
    	//masterComObj.goToSqlApp();
    	
    	if(Utility_Functions.xWaitForElementPresent(driver,SqlStatementPage.resultRow, 5)) {
    		String result = Utility_Functions.getText(driver,SqlStatementPage.resultRow);
    		System.out.println("Text: "+result);
    		Utility_Functions.xAssertEquals(report,"********  End of data  ********", result, "No Record-End of data");
    	}else {
    		System.out.println("Text: Not found");
    		throw new NoSuchElementException("Could not find :"+SqlStatementPage.resultRow);
    	}
    }
    
    public void insertSpecialPricePAPRecord() {
    	//CommonActions sqlComObj = new CommonActions(helper);
    	commonObj.goToSqlApp();
    	//commonObj.sqlInsertSpecialPricePAP(Utility_Functions.xGetJsonAsString("CustomerNo"),Utility_Functions.xGetJsonAsString("ItemNo"),Utility_Functions.xGetJsonAsString("Date1"),Utility_Functions.xGetJsonAsString("Date2"));
    	//masterComObj.goToSqlApp();
		Date dt1 = new Date();
		Date dt2 = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt1);
		c.add(Calendar.DATE, -1);
		dt1 = c.getTime();
		c.add(Calendar.DATE, 5);
		dt2 = c.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate= formatter.format(dt1);
		String endDate = formatter.format(dt2);

		commonObj.sqlInsertSpecialPricePAP(Utility_Functions.xGetJsonAsString("CustomerNo"),Utility_Functions.xGetJsonAsString("ItemNo"),strDate,endDate);
    	
    	if(Utility_Functions.xWaitForElementPresent(driver,SqlStatementPage.resultRowInsert, 5)) {
    		String result = Utility_Functions.getText(driver,SqlStatementPage.resultRowInsert);
    		System.out.println("Text: "+result);
    		Utility_Functions.xAssertEquals(report,"1 rows inserted in IM08 in DTA99599.", result, "Validating Successful Insert");
    	}else {
    		System.out.println("Text: Not found");
    		throw new NoSuchElementException("Could not find :"+SqlStatementPage.resultRow);
    	}
    }
    
    public void exitSql() {
    	click(SpecialPricePage.btnF3,"Click on F3");
    	if(Utility_Functions.xWaitForElementPresent(driver,SqlStatementPage.sqlTitle, 5)) {
    		click(SpecialPricePage.btnF3,"Click on F3");
    		sendKeys(SqlStatementPage.choiceTxtBox,"2");
    		Utility_Functions.actionKey(Keys.ENTER, driver);
    	}else {
    		throw new NoSuchElementException("Could not find :"+SqlStatementPage.sqlTitle);
    	}
    }
    
    public void exitInsertSql() {
    	click(SpecialPricePage.btnF3,"Click on F3");
    	if(Utility_Functions.xWaitForElementPresent(driver,SqlStatementPage.sqlTitle, 5)) {
    	//	click(SpecialPricePage.btnF3,"Click on F3");
    		sendKeys(SqlStatementPage.choiceTxtBox,"2");
    		Utility_Functions.actionKey(Keys.ENTER, driver);
    	}else {
    		throw new NoSuchElementException("Could not find :"+SqlStatementPage.sqlTitle);
    	}
    }
}
