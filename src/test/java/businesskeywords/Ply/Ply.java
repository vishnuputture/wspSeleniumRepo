package businesskeywords.Ply;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.avro.generic.GenericData;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;

import commonkeywords.CommonActions;
import pages.SalesOrders.SalesOrdersPage;
import pages.common.MasterPage;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.interactions.Actions;
import pages.makePayments.*;

import pages.pricing.PriceSheet.SelfServicePriceSheetPage;

import pages.pricing.SpecialPricePage;

import pages.ply.*;

import software.amazon.awssdk.services.cloudfront.model.TooManyQueryStringsInCachePolicyException;
import supportLibraries.Utility_Functions;

import javax.print.DocFlavor;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Ply extends ReusableLib {

    CommonActions commonObj;
    WebDriver d;
	private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public Ply(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        d=helper.getGSDriver().getWebDriver();
		ownDriver=helper.getGSDriver();
    }
    
 public void fixedPaymentValidationBA() {
    	click(PlyPage.paymentsAuditMenu,"Navigate to payments audit page");
    	if(Utility_Functions.xWaitForElementPresent(ownDriver,PlyPage.plyPageTitle, 5)) {
			String title = Utility_Functions.getText(ownDriver,PlyPage.plyPageTitle);
			System.out.println("Text: "+title);
			Utility_Functions.xAssertEquals(report, "My Account Payment Maintenace", title.trim(), "Validating Ply page title");
		}else {
			System.out.println("Text: Not found");
			throw new NoSuchElementException("Could not find :"+PlyPage.plyPageTitle);
		}
    	
    	List<WebElement> auditRecords = ownDriver.findElements(By.xpath("//div[text()='"+Utility_Functions.xGetJsonData("FixedPaymntConfirmNoBA")+"']"));
    	
    	if(auditRecords.size()<1) {
    		report.updateTestLog("Find payment confirmation record", "Fixed payment confirmation record not found", Status.FAIL);
    	}else {
    		report.updateTestLog("Find payment confirmation record", "Fixed payment confirmation record found", Status.PASS);
    	}
    }
 
 public void fixedPaymentValidationCC() {
 	click(PlyPage.paymentsAuditMenu,"Navigate to payments audit page");
 	if(Utility_Functions.xWaitForElementPresent(ownDriver,PlyPage.plyPageTitle, 5)) {
			String title = Utility_Functions.getText(ownDriver,PlyPage.plyPageTitle);
			System.out.println("Text: "+title);
			Utility_Functions.xAssertEquals(report, "My Account Payment Maintenace", title.trim(), "Validating Ply page title");
		}else {
			System.out.println("Text: Not found");
			throw new NoSuchElementException("Could not find :"+PlyPage.plyPageTitle);
		}
 	
 	List<WebElement> auditRecords = ownDriver.findElements(By.xpath("//div[text()='"+Utility_Functions.xGetJsonData("FixedPaymntConfirmNoCC")+"']"));
 	
 	if(auditRecords.size()<1) {
 		report.updateTestLog("Find payment confirmation record", "Fixed payment confirmation record not found", Status.FAIL);
 	}else {
 		report.updateTestLog("Find payment confirmation record", "Fixed payment confirmation record found", Status.PASS);
 	}
 }
    
}

