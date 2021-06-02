package commonkeywords;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import pages.AddSpecialPricingPage;
import pages.MasterPage;
import pages.SpecialPricePage;
import pages.SqlStatementPage;
import supportLibraries.Utility_Functions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonActions extends ReusableLib {
	
	/**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public CommonActions(Helper helper) {
        super(helper);
    }
    
    public void masterToOrderProcessing() {
    	click(MasterPage.orderProcessMenu,"Click order processing");
    }
    
    public void orderProcToSplPricing() {
    	click(MasterPage.specialPricingMenu,"Click special price");
    }
    
    public void splPricingToAddPricing() {
    	click(SpecialPricePage.specialPricingAdd,"Click on Add");
    }
    
    public void validateText(By ele,String text,String msg) {
    	if(Utility_Functions.xWaitForElementPresent(driver,ele, 5)) {
    		String title = Utility_Functions.getText(driver,ele);
    		System.out.println("Text: "+title);
    		Utility_Functions.xAssertEquals(report, text, title.trim(), msg);
    	}else {
    		System.out.println("Text: Not found");
    		throw new NoSuchElementException("Could not find :"+ele);
    	}
    }
    
    public void addSpecialPriceRecord(String custNum,String itemNum) {
    	/*sendKeys(AddSpecialPricingPage.custNumTxtBox,jsonData.getData("validCustNum"));
    	sendKeys(AddSpecialPricingPage.itemNumTxtBox,jsonData.getData("itemNum"));*/
    	sendKeys(AddSpecialPricingPage.custNumTxtBox,custNum,"Entering customer number");
    	sendKeys(AddSpecialPricingPage.itemNumTxtBox,itemNum,"Entering item number");
    	sendKeys(AddSpecialPricingPage.specialPriceTxtBox,jsonData.getData("specialPrice"),"Entering special price");
    	
    	Date dt = new Date();
    	Calendar c = Calendar.getInstance(); 
    	c.setTime(dt); 
    	c.add(Calendar.DATE, 1);
    	dt = c.getTime();
    	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");  
        String strDate= formatter.format(dt);  
    	System.out.println(strDate);
    	
    	sendKeys(AddSpecialPricingPage.expDateTxtBox,strDate,"Entering expiration date");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	
    	if(Utility_Functions.xWaitForElementPresent(driver,AddSpecialPricingPage.successLbl, 5)) {
    		String successMessage = Utility_Functions.getText(driver,AddSpecialPricingPage.successLbl);
    		System.out.println("Text: "+successMessage);
    		Utility_Functions.xAssertEquals(report, jsonData.getData("success"), successMessage.trim(), "Validating success message");
    	}else {
    		System.out.println("Text: Not found");
    		throw new NoSuchElementException("Could not find :"+AddSpecialPricingPage.successLbl);
    	}
    }
    public void exitAddSpecialPricingToSplPrice() {
    	click(AddSpecialPricingPage.btnF12,"Click F12");
    }
    
    public void exitSplPriceToMasterPage() {
    	Utility_Functions.xWaitForElementToBeClickable(driver, SpecialPricePage.btnF3, 5);
    		
    	
    	click(SpecialPricePage.btnF3,"Click F3");
    }
    
    public void goToSqlApp() {
    	sendKeys(MasterPage.sqlTxtBox,"STRSQL","Go to SQL application");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	if(Utility_Functions.xWaitForElementPresent(driver,MasterPage.sqlTitleLbl, 5)) {
    		String title = Utility_Functions.getText(driver,MasterPage.sqlTitleLbl);
    		System.out.println("Text: "+title);
    		Utility_Functions.xAssertEquals(report, "Enter SQL Statements", title.trim(), "Validating SQL page title");
    	}else {
    		System.out.println("Text: Not found");
    		throw new NoSuchElementException("Could not find :"+MasterPage.sqlTitleLbl);
    	}
    }
    public void sqlRetrieveSpecialPrice(String custNum, String itemNum) {
    	sendKeys(SqlStatementPage.sqltxtBox1,"select * from im08");
    	sendKeys(SqlStatementPage.sqltxtBox2,"where cacust = '"+custNum+"'");
    	sendKeys(SqlStatementPage.sqltxtBox3,"and caitem = '"+itemNum+"'","Enter Sql statement to retrieve data");
    	
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    }
    
    public void sqlInsertSpecialPricePAP(String custNum, String itemNum,String Date1,String Date2) {
    	System.out.println(Date1+"-"+Date2);
    	sendKeys(SqlStatementPage.sqltxtBox1,"insert into im08 VALUES");
    	sendKeys(SqlStatementPage.sqltxtBox2,"('1',"+"'"+custNum+"'"+","+"'"+itemNum+"'"+", ' ',' ', ' ',4, 0,");
    	sendKeys(SqlStatementPage.sqltxtBox3,"'"+Date1+"'"+","+"'"+Date2+"'"+", 0, 0, 0, ' ', 'PAP')");
    	
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    }

}
