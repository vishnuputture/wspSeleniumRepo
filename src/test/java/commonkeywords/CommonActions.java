package commonkeywords;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;

import pages.SalesQuotes.WorkWithSalesQuotesPage;
import pages.common.MasterPage;
import pages.common.SqlStatementPage;

import pages.*;
import pages.inventory.CostAdjustmentPage;
import pages.pricing.AddSpecialPricingPage;
import pages.pricing.OrderByCustomerPage;
import pages.pricing.SpecialPricePage;
import pages.pricing.matrixcost.InventoryManagementMainMenuPage;
import pages.pricing.matrixcost.InventoryManagementMenu2Page;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import supportLibraries.Utility_Functions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

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

	/**
	 *
	 *
	 * This method navigates from master to inventory management page
	 *
	 */
	public void masterToInventory() {
		click(MasterPage.inventoryMenu,"Click inventory management");
	}

	/**
	 *
	 *
	 * This method navigates from master to order processing page
	 *
	 */
	public void masterToOrderProcessing() {
		click(MasterPage.orderProcessMenu,"Click order processing");
	}

	public void masterToSalesAnalysis() {
	    click(MasterPage.salesAnalysisMenu, "Click sales analysis");
    }

    public void salesAnalysisToSPAApplication() {
	    click(SalesAnalysisPage.spaApplication, "Click Special Price Allowance");
    }

	/**
	 *
	 *
	 * This method navigates from order processing page to special price maintenance page
	 *
	 */
	public void orderProcToSplPricing() {
		click(OrderProcessingPage.specialPricingMenu,"Click special price");
	}

	/**
	 *
	 *
	 * This method navigates from order inventory to item master page
	 *
	 */
	public void inventoryToItemMaster() {
		click(MasterPage.itemMasterMenu,"Click item master");
	}
	
	/**
	 *
	 *
	 * This method navigates from inventory to inventory adjustments receipt correction page
	 *
	 */
	public void inventoryAdjustToInvCorrections() {
		click(MasterPage.inventoryReceiptMenu,"Click inventory adjustment");
	}
	/**
	 *
	 *
	 * This method navigates from inventory to average cost adjustments page
	 *
	 */
	public void inventoryAdjustToavgCostAdjust() {
		click(MasterPage.avgCostAdjustmentMenu,"Click average cost adjustment");
	}
	
	/**
	 *
	 *
	 * This method navigates from inventory to inventory adjustment page
	 *
	 */
	public void inventoryToInvAdjustments() {
		click(MasterPage.inventoryAdjustmentMenu,"Click inventory adjustment");
	}

	/**
	 *
	 *
	 * This method navigates from special price maintenance page to add special price page
	 *
	 */
	public void splPricingToAddPricing() {
		click(SpecialPricePage.specialPricingAdd,"Click on Add");
	}

	/**
	 *
	 *
	 * This method validates the text displayed in an element
	 *
	 */
	public void validateText(By ele,String text,String msg) {
		if(Utility_Functions.xWaitForElementPresent(driver,ele, 5)) {
			String title = Utility_Functions.getText(driver,ele);
			System.out.println("Text: "+title);
			Utility_Functions.xAssertEquals(report, text.toLowerCase(), title.trim().toLowerCase(), msg);
		}else {
			System.out.println("Text: Not found");
			throw new NoSuchElementException("Could not find :"+ele);
		}
	}

	public void validateElementExists(By ele, String msg) {
        if(Utility_Functions.xWaitForElementPresent(driver,ele, 5)) {
            System.out.println("Element: "+ ele + " exists");

        }else {
            System.out.println("Element: Not found");
            throw new NoSuchElementException("Could not find :"+ele);
        }
    }

	/**
	 *
	 *
	 * This method adds new special price records as per provided data
	 *
	 */
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

	/**
	 *
	 *
	 * This method navigates from add special price page to special price maintenance page
	 *
	 */
	public void exitAddSpecialPricingToSplPrice() {
		click(AddSpecialPricingPage.btnF12,"Click F12");
	}

	/**
	 *
	 *
	 * This method navigates from special price maintenance page to master page
	 *
	 */
	public void exitSplPriceToMasterPage() {
		Utility_Functions.xWaitForElementToBeClickable(driver, SpecialPricePage.btnF3, 5);


		click(SpecialPricePage.btnF3,"Click F3");
	}

	/**
	 *
	 *
	 * This method navigates to the sql query execution page
	 *
	 */
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

	/**
	 *
	 *
	 * This method retrieves special price using sql query from within the WISE application
	 *
	 */
	public void sqlRetrieveSpecialPrice(String custNum, String itemNum) {
		sendKeys(SqlStatementPage.sqltxtBox1,"select * from im08");
		sendKeys(SqlStatementPage.sqltxtBox2,"where cacust = '"+custNum+"'");
		sendKeys(SqlStatementPage.sqltxtBox3,"and caitem = '"+itemNum+"'","Enter Sql statement to retrieve data");

		Utility_Functions.actionKey(Keys.ENTER, driver);
	}

	/**
	 *
	 *
	 * This method adds new special price with PAP using sql query from within the WISE application
	 *
	 */
	public void sqlInsertSpecialPricePAP(String custNum, String itemNum,String Date1,String Date2) {
		System.out.println(Date1+"-"+Date2);
		sendKeys(SqlStatementPage.sqltxtBox1,"insert into DTA99599/IM08 VALUES");
		sendKeys(SqlStatementPage.sqltxtBox2,"('1',"+"'"+custNum+"'"+","+"'"+itemNum+"'"+", ' ',' ', ' ',4, 0,");
		sendKeys(SqlStatementPage.sqltxtBox3,"'"+Date1+"'"+","+"'"+Date2+"'"+", 0, 0, 0, ' ', 'PAP')");

		Utility_Functions.actionKey(Keys.ENTER, driver);
	}

//Vishnu
	/**
	 *
	 * This method navigates from inventory management Main Menu to inventory management Menu2 page
	 *
	 */
	public void InventoryManagementMainMenuToInventoryManagementMenu2() {
		click(InventoryManagementMainMenuPage.moreInventorySelections,"Click More Inventory Selection");
	}

	/**
	 *
	 * This method navigates from inventory management Menu2 page to matrix cost update page
	 *
	 */
	public void InventoryManagementMenu2ToMatrixCostUpdate() {
		click(InventoryManagementMenu2Page.matrixCostUpdate,"Click Matrix Cost Update");
	}

	/**
	 *
	 * This method navigates from Order Processing Menu to Options And Constants Menu
	 *
	 */
	public void navigateToOptionsAndConstantsMenu() {
		click(MasterPage.optionAndConstantsMenu,"Click Options And Constants Menu");
	}

	/**
	 *
	 * This method navigates from Options And Constants Menu to Pricing Matrix
	 *
	 */
	public void navigateToPricingMatrix() {
		click(MasterPage.pricingMatrix,"Click Pricing Matrix");
	}

	/**
	 *
	 * This method Exit From Pricing Matrix
	 *
	 */
	public void exitFromPricingMatrix() {
		click(SpecialPricePage.btnF3,"Exit from Pricing Matrix");
	}

	/**
	 *
	 * This method Exit From Cycle Cost Adjustment
	 *
	 */
	public void exitFromCycleCostAdj() {
		click(CostAdjustmentPage.exitBtn,"Exit Cycle Cost Adjustment");
	}

	/**
	 *
	 * This method navigate to Sales Quotes Menu
	 *
	 */
	public void navigateToSalesQuotes() {
		click(MasterPage.salesQuotes,"Click on Sales Quotes Menu");
	}
	
	/**
	 *
	 * This method navigate to Sales Orders Menu
	 *
	 */
	public void orderProcessingToSalesOrders() {
		click(MasterPage.salesOrdersMenu,"Click on Sales Orders Menu");
	}

	/**
	 *
	 * This method navigate to Working Sales Sales Quotes Program
	 *
	 */
	public void navigationToWorkingSalesQuotes() {
		click(WorkWithSalesQuotesPage.workingSalesQuotes,"Click on Working Sales Sales Quotes");
	}

	/**
	 *
	 *
	 * This method navigates from Master to Purchase Order Menu
	 *
	 */
	public void masterToPurchaseOrder() {
		click(MasterPage.purchaseOrderMenu,"Click Purchase Order");
	}

	/**
	 *
	 *
	 * This method navigates from Purchase Order Menu to Inventory Receipt Program
	 *
	 */
	public void purchaseOrderToInventoryReceipt() {
		click(MasterPage.inventoryReciptMenu,"Click Inventory Receipt");
	}

	/**
	 *
	 *
	 * This method navigates from sales Quotes Page to master page
	 *
	 */
	public void exitSalesQuotesToMasterPage() {
		Utility_Functions.xWaitForElementToBeClickable(driver, SpecialPricePage.btnF3, 5);
		click(SpecialPricePage.btnF3,"Click F3");
	}

	/**
	 *
	 *
	 * This method navigates from order processing to Order by Customer page
	 *
	 */
	public void ordeprocessingtoOrderbyCustomerpage() {
		click(OrderByCustomerPage.orderByCust,"Click Order By Customer");
	}

	/**
	 *
	 * This method Exit From Customer Group Maintenance
	 *
	 */
	public void exitFromCustomerGroupMaintenance() {
		click(SpecialPricePage.btnF3,"Exit Customer Group Maintenance");
	}
}