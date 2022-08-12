package businesskeywords.Inventory;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import pages.common.MasterPage;
import pages.inventory.AlternateCustomerPage;
import supportLibraries.Utility_Functions;

public class AlternateCustomer extends ReusableLib {

    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public AlternateCustomer(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * This method validates the title of [ALTERNATE CUSTOMER NUMBER REVISIONS] Page
     */
    public void navigateToAlternateCustomerNumberRevisions() {
        commonObj.masterToMailingMaster();
        commonObj.mailingMasterToAlternateCustomerRevision();
        commonObj.validateText(AlternateCustomerPage.pageTitleAlternateCustomerRevision, "Alternate Customer Number Revisions", "Validating [Alternate Customer Number Revisions] page title");
    }

    /**
     * Keyword to select random Customer in page - [Alternate Customer Number Revisions]
     */
    public String selectRandomCustomer() {
        Utility_Functions.actionKey(Keys.F1, ownDriver);
        int count = Utility_Functions.xRandomFunction(1, 10);
        while (count > 0) {
            click(AlternateCustomerPage.btnDown);
            waitForElementDisappear(MasterPage.loadingAnime, globalWait);
            count--;
        }
        String customerNumber = getText(AlternateCustomerPage.acct1).trim();
        sendKeysAndEnter(AlternateCustomerPage.opt1, "1", "Select Customer");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        String custNoActual = getAttribute(AlternateCustomerPage.tbxCustomerNumber, "value");
        Utility_Functions.xAssertEquals(report, customerNumber, custNoActual, "Validating selected Customer Number");
        return customerNumber;
    }

    /**
     * Keyword to enter Alternate Customer Numbers
     */
    public void enterRandomAltCustNo() {
        String randomCustomerNumber = "TEST" + Utility_Functions.xRandomFunction();
        String msgCustNo = randomCustomerNumber.substring(0, randomCustomerNumber.length() - 1);
        sendKeysAndEnter(AlternateCustomerPage.tbxAltCustomerNo1, randomCustomerNumber, "Entering random Alternate Customer Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateText(AlternateCustomerPage.notificationMsg, "Alternate customer number " + msgCustNo + " successfully added.", "Validating success message");
        String customerNumber = getAttribute(AlternateCustomerPage.tbxCustomerNumber, "value");
        String customerName = getText(AlternateCustomerPage.lblCustomerName);
        jsonData.putData("CustomerNumber", customerNumber);
        jsonData.putData("CustomerName", customerName);
        jsonData.putData("AltCustomerNumber", randomCustomerNumber);
    }
}
