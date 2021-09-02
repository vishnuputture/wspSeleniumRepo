package businesskeywords.SalesQuotes.WorkWithSalesQuotes;

import businesskeywords.SalesQuotes.WorkWithSalesQuotes.WorkWithSalesQuote;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.aspectj.weaver.ast.Or;
import org.openqa.selenium.Keys;
import pages.OrderProcessingPage;
import pages.SalesQuotes.WorkWithSalesQuotesPage;
import pages.pricing.OrderByCustomerPage;
import supportLibraries.Utility_Functions;

public class OrderByCustomer extends ReusableLib {
    CommonActions commonObj=new CommonActions(helper);
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public OrderByCustomer(Helper helper) {
        super(helper);
    }

    /**
     * This method navigate To Order By Customer Page
     *
     */
    public void navigateToOrderByCustomer() {
        commonObj.masterToOrderProcessing();
        commonObj.ordeprocessingtoOrderbyCustomerpage();
    }


    /**
     * This method validate Order Details
     *
     */
    public void orderDetails() {
        sendKeys(OrderByCustomerPage.orderNum,Utility_Functions.xGetJsonData("SOSmoke") + "-01");
        click(OrderByCustomerPage.nexttn, "Click Next Button");
        String custNo = Utility_Functions.getText(driver, OrderByCustomerPage.custNum);
        String orderNo = Utility_Functions.getText(driver, OrderByCustomerPage.orderNumFiled).trim();
        String jobName = Utility_Functions.getText(driver, OrderByCustomerPage.jobName).trim();
        String CustNb = jsonData.getData("CustNum");
        String JobName = jsonData.getData("JobName");
        Utility_Functions.xAssertEquals(report, CustNb, custNo, "Customer Number: ");
        Utility_Functions.xAssertEquals(report, JobName, jobName, "Job Name: ");
        Utility_Functions.xAssertEquals(report, Utility_Functions.xGetJsonData("SOSmoke") + "-01", orderNo, "Order Number: ");
        //sendKeys(OrderByCustomerPage.optFiled, "1");
        Utility_Functions.actionKey(Keys.ENTER, driver);
    }

    /**
     * This method validate Back Order Quantity
     *
     */
    public void validBOQty() {
        orderDetails();
        System.out.println("OK.....................");
        String boQty= Utility_Functions.getText(driver,OrderByCustomerPage.boQty).trim();
        Utility_Functions.xAssertEquals(report,"1",boQty,"Blank BO QTY: ");  //Need To Change after App Fix
    }

    /**
     * This method exit from Order By Customer
     *
     */
    public void exitOrderByCustomer() {
        click(OrderByCustomerPage.exitutton,"exit from Order By Customer");
        Utility_Functions.actionKey(Keys.F3, driver);
    }

//*********************************************************************************************************

    /**
     * This method validate Order Status for invoice and close
     *
     */
    public void orderStatusInvoice() {
        orderDetails();
        String invoiced=Utility_Functions.getText(driver,OrderByCustomerPage.invoiced);
        String closed=Utility_Functions.getText(driver,OrderByCustomerPage.closed);
        Utility_Functions.xAssertEquals(report, invoiced, "Invoiced", "Order Status: ");
        Utility_Functions.xAssertEquals(report, closed, "Closed", "Order Status: ");

    }


    /**
     * This method validate Order Status
     *
     */
    public void orderStatus() {
        orderDetails();
        String status=Utility_Functions.getText(driver,OrderByCustomerPage.stats);
        String inProcess = jsonData.getData("InProgressStatus");
        Utility_Functions.xAssertEquals(report, inProcess, status, "Order Status: ");
    }



}
