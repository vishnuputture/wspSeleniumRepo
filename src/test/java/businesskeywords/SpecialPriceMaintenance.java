package businesskeywords;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;

import commonkeywords.*;

import org.openqa.selenium.WebElement;
import pages.SpecialPricePage;
import pages.AddSpecialPricingPage;
import pages.MasterPage;
import supportLibraries.Utility_Functions;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import java.util.Arrays;
import java.util.List;

public class SpecialPriceMaintenance extends ReusableLib  {
	/**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
	private CommonActions commonObj;
    public SpecialPriceMaintenance(Helper helper) {
        super(helper);
        commonObj= new CommonActions(helper);
    }
    
    /*public void goToAddSpecialPricing() {
    	click(SpecialPricePage.specialPricingAdd);
    }*/
    public void delSpecialPriceRecordandExit() {
    	//CommonActions commonObj= new CommonActions(helper);
    	try {
    		commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance","Validating special price page title");
    	}catch(NoSuchElementException e) {
    		commonObj.masterToOrderProcessing();
        	commonObj.orderProcToSplPricing();
        	//commonObj.splPricingToAddPricing();
        	
        	commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance","Validating special price page title");
    	}

    	String optXpath = "//div[@class='A20'][text()='"+Utility_Functions.xGetJsonAsString("CustomerNo")+"']/preceding-sibling::input[1]";
    	sendKeys(By.xpath(optXpath),"4");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
		waitForElementDisappear(MasterPage.loadingAnime,globalWait);
    	click(SpecialPricePage.btnProcessF9);
    	click(SpecialPricePage.btnF3);
    }
    
    public void delAddedSpclPrice() {
    	//CommonActions commonObj= new CommonActions(helper);
    	try {
    		commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance","Validating special price page title");
    	}catch(NoSuchElementException e) {
    		commonObj.masterToOrderProcessing();
        	commonObj.orderProcToSplPricing();
        	//commonObj.splPricingToAddPricing();
        	
        	commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance","Validating special price page title");
    	}
    	sendKeys(SpecialPricePage.posToCustNo,"0");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	/*String[] custNumList = jsonData.getData("validCustNum").split(",");
    	for(int i=0; i<custNumList.length;i++) {
    		String optXpath = "//div[@class='A20'][text()='"+custNumList[i]+"']/preceding-sibling::input[1]";
    		sendKeys(By.xpath(optXpath),"4");
    	}*/
    	String optXpath = "//div[@class='A20'][text()='"+Utility_Functions.xGetJsonAsString("CustomerNo")+"']/preceding-sibling::input[1]";
		sendKeys(By.xpath(optXpath),"4");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
		waitForElementDisappear(MasterPage.loadingAnime,globalWait);
    	click(SpecialPricePage.btnProcessF9,"Processing special price delete request");
    }
    
    public void delAllPriceRecord() throws InterruptedException {
    	//CommonActions commonObj= new CommonActions(helper);
    	try {
    		commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance","Validating special price page title");
    	}catch(NoSuchElementException e) {
    		commonObj.masterToOrderProcessing();
        	commonObj.orderProcToSplPricing();
        	//commonObj.splPricingToAddPricing();
        	
        	commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance","Validating special price page title");
    	}
    	//click(SpecialPricePage.sortByItemNumBtn,"");
    	sendKeys(SpecialPricePage.posToCustNo,"0");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
		waitForElementDisappear(MasterPage.loadingAnime,globalWait);
    	//click(SpecialPricePage.btnRefreshF5);
    	//System.out.println(Utility_Functions.xWaitForElementPresent(driver,SpecialPricePage.selectPriciRecordBox, 25));
    	List<WebElement> eleList = driver.findElements(SpecialPricePage.selectPricingRecordBox);
    	if(eleList.size()>0) {
    		for(WebElement element : eleList) {
        		sendKeys(element,"4");	
        	}
        	Utility_Functions.actionKey(Keys.ENTER, driver);
			waitForElementDisappear(MasterPage.loadingAnime,globalWait);
        	click(SpecialPricePage.btnProcessF9,"Processing special price delete request");
    	}
    	
    }
    
    public void validateSplPriceFilterValue() {
    	//CommonActions commonObj= new CommonActions(helper);
    	try {
    		commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance","Validating special price page title");
    	}catch(NoSuchElementException e) {
    		commonObj.masterToOrderProcessing();
        	commonObj.orderProcToSplPricing();
        	//commonObj.splPricingToAddPricing();
        	commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance","Validating special price page title");
    	}
    	Utility_Functions.xAssertEquals(report, jsonData.getData("filterValue"), getValue(SpecialPricePage.filterTxtBox), "Validating default filter value");
    }
    
    public void positionCustNum() {
    	//sendKeys(SpecialPricePage.posToCustNo,"250");
    	String[] custNumList = jsonData.getData("validCustNum").split(",");
    	String[] tempCustNumList = custNumList;
    	int[] custNumIntList = Arrays.stream(custNumList).mapToInt(Integer::parseInt).toArray();
    	for(int i=0;i<custNumIntList.length;i++) {
    		//int x =Integer.parseInt(custNumList[i]);
    		if(custNumIntList[i]< Integer.parseInt(jsonData.getData("filterCustNum"))) {
    			//custNumList = ArrayUtils.remove(custNumList, i);
    			custNumList=ArrayUtils.removeElement(custNumList,tempCustNumList[i]);
    		}
    	}
    	sendKeys(SpecialPricePage.posToCustNo,jsonData.getData("filterCustNum"),"Entering customer number to filter");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	if(custNumList.length>0) {
    		Arrays.sort(custNumList);
        	//String[] custNumStringList=Arrays.stream(custNumIntList).sorted().mapToObj(String::valueOf).toArray(String[]::new);
        	for(int i=0;i<custNumList.length;i++) {
        		Utility_Functions.xAssertEquals(report, custNumList[i],driver.findElement(By.xpath("//div[starts-with(@id,'D_1"+i+"')]")).getText(), "Validating filtered result");
        	}
    	}else {
    		if(!Utility_Functions.isExist(driver, SpecialPricePage.selectPricingRecordBox)) {
    			report.updateTestLog("VerifyRecord", "No special price record should be displayed", Status.PASS);
    		}else {
    			report.updateTestLog("VerifyRecord", "No special price record should be displayed", Status.FAIL);
    		}
    	}
    	
    	
    	
    }
    
    public void positionCustItemBegin() {
    	String[] custNumList = jsonData.getData("validCustNum").split(",");
    	String[] itemNumList = jsonData.getData("validItemNum").split(",");
    	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	sendKeys(SpecialPricePage.posToItemNo,jsonData.getData("filterItemNum"),"Entering item number to filter");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	for(int i=0;i<custNumList.length;i++) {
    		Utility_Functions.xAssertEquals(report, custNumList[i],driver.findElement(By.xpath("//div[starts-with(@id,'D_1"+i+"')]")).getText(), "Validating filtered result");
    		Utility_Functions.xAssertEquals(report, itemNumList[i],driver.findElement(By.xpath("//div[@id='D_1"+i+"_13']")).getText(), "Validating filtered result");
    	}
    	
    	click(SpecialPricePage.sortByCustNumBtn,"");
    	
    }
    
    public void positionCustItemEnd() {
    	sendKeys(SpecialPricePage.posToItemNo,jsonData.getData("filterItemNum"),"Entering item number to filter");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	if(!Utility_Functions.isExist(driver, SpecialPricePage.selectPricingRecordBox)) {
			report.updateTestLog("VerifyRecord", "No special price record should be displayed", Status.PASS);
			commonObj.validateText(SpecialPricePage.noItemMsgLbl, "No Pricing Records to Display","Validating special price message for no records");
		}else {
			report.updateTestLog("VerifyRecord", "No special price record should be displayed", Status.FAIL);
		}
    	driver.findElement(SpecialPricePage.posToItemNo).clear();
    	//sendKeys(SpecialPricePage.posToItemNo,"0","Entering item number to filter");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	click(SpecialPricePage.sortByCustNumBtn,"");
    	//click(SpecialPricePage.btnRefreshF5);
    	//driver.findElement(SpecialPricePage.btnRefreshF5).click();
    }
    
    public void validateNoSpecialPriceMessage() {
    	if(!Utility_Functions.isExist(driver, SpecialPricePage.selectPricingRecordBox)) {
			report.updateTestLog("VerifyRecord", "No special price record should be displayed", Status.PASS);
			commonObj.validateText(SpecialPricePage.noItemMsgLbl, "No Pricing Records to Display","Validating special price message for no records");
		}else {
			report.updateTestLog("VerifyRecord", "No special price record should be displayed", Status.FAIL);
		}
    }
    
    public void positionItemNumMiddle() {
    	String[] custNumList = jsonData.getData("filteredCustNum").split(",");
    	String[] itemNumList = jsonData.getData("filteredItemNum").split(",");
    	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	sendKeys(SpecialPricePage.posToItemNo,jsonData.getData("filterItemNum"),"Entering item number to filter");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
    	for(int i=0;i<custNumList.length;i++) {
    		Utility_Functions.xAssertEquals(report, custNumList[i],driver.findElement(By.xpath("//div[starts-with(@id,'D_1"+i+"')]")).getText(), "Validating filtered result");
    		Utility_Functions.xAssertEquals(report, itemNumList[i],driver.findElement(By.xpath("//div[@id='D_1"+i+"_13']")).getText(), "Validating filtered result");
    	}
    	
    	click(SpecialPricePage.sortByCustNumBtn,"");
    	
    }
    
    public void sortByItemNum() {
    	click(SpecialPricePage.sortByItemNumBtn,"Sort records by item number");
    }
    public void exitSplPricePage() {
    	//CommonActions spCommonObj = new CommonActions(helper);
    	commonObj.exitSplPriceToMasterPage();
    }
    
    public void deleteSpecialPricePAPRecord()
    {
    	
    	try {
    		commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance","Validating special price page title");
    	}catch(NoSuchElementException e) {
    		commonObj.masterToOrderProcessing();
        	commonObj.orderProcToSplPricing();
        	//commonObj.splPricingToAddPricing();
        	
        	commonObj.validateText(SpecialPricePage.spclPriceTitle, "Special Price Maintenance","Validating special price page title");
    	}

    	String optXpath = "//div[@class='A20'][text()='"+Utility_Functions.xGetJsonAsString("CustomerNo")+"']/preceding-sibling::input[1]";
    	sendKeys(By.xpath(optXpath),"4");
    	Utility_Functions.actionKey(Keys.ENTER, driver);
   // 	click(SpecialPricePage.btnProcessF9);
    	Utility_Functions.xScrollIntoView(driver, driver.findElement(SpecialPricePage.papMessage));
    	String res= driver.findElement(SpecialPricePage.papMessage).getText();
    	System.out.println(res);
    	Utility_Functions.xAssertEquals(report,"Price as Parent records are display only",res, "Validating pap record exists");
    	click(SpecialPricePage.btnF3);
    }
    
}
