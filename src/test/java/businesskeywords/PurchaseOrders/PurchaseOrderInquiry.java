package businesskeywords.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Report;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.PurchaseOrders.InventoryReceiptsPage;
import pages.PurchaseOrders.PurchaseOrderInquiryPage;
import pages.common.MasterPage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

import java.util.List;


public class PurchaseOrderInquiry extends ReusableLib {

    CommonActions commonObj;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public PurchaseOrderInquiry(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    /**
     * This method validates the title of Inquiry Purchase Orders Page
     */
    public void navigateToPurchaseOrderInquiry() {
        commonObj.masterToPurchaseOrder();
        commonObj.purchaseOrderToPurchaseOrderInquiry();
        commonObj.validateText(PurchaseOrderInquiryPage.hdrPOInquiry, "PURCHASE ORDER INQUIRY", "Validating [PURCHASE ORDER INQUIRY] page title");
    }

    /**
     * Keyword to click on [Exit] button in PURCHASE ORDER INQUIRY Page
     */
    public void clickExitBtnPOInquiry(){
        Utility_Functions.xScrollIntoView(driver, PurchaseOrderInquiryPage.btnExitPOInquiryDtls);
        click(PurchaseOrderInquiryPage.btnExitPOInquiryDtls, "Click [Exit] button");
    }

    /**
     * Keyword to click on [Exit] button
     */
    public void clickOnExitBtn(){
        click(PurchaseOrderInquiryPage.btnExitItemLedger, "Click [Exit] button");
    }

    /**
     * Keyword to click on [Exit] button
     */
    public void clickOnF12ReturnBtn(){
        click(PurchaseOrderInquiryPage.btnF12Return, "Click [F12=Return] button");
    }

    /**
     * Keyword to exit to master from PURCHASE ORDER INQUIRY Page
     */
    public void navigatePOInquiryToMaster(){
        Utility_Functions.xScrollPage(driver);
        click(PurchaseOrderInquiryPage.btnExitPOInquiryDtls, "Click [Exit] button");
        Utility_Functions.actionKey(Keys.F3, driver);
    }

    /**
     * Keyword to enter PO Number and select action in PURCHASE ORDER INQUIRY Page
     */
    public void enterPONumberAndSelectAction(){
        String poNumber = jsonData.getData("PONumber");
        sendKeysAndEnter(PurchaseOrderInquiryPage.tbxPONumber, poNumber,"Enter Purchase Order Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        String action = jsonData.getData("Action");

        List<WebElement> lstOrderNo = getListElement(PurchaseOrderInquiryPage.lstOrderNumber);
        List<WebElement> lstAction = getListElement(PurchaseOrderInquiryPage.lstActions);

        int index = 0;
        for (WebElement element : lstOrderNo){
            String text = element.getText().trim();
            if (text.equalsIgnoreCase(poNumber)){
                index = lstOrderNo.indexOf(element);
                break;
            }
        }
        sendKeys(lstAction.get(index), action,"Selected PO Number");
        click(PurchaseOrderInquiryPage.btnSubmit,"Click [Submit] Button");
    }

    /**
     * Keyword to enter PO Number in PURCHASE ORDER INQUIRY Page
     */
    public void enterPONumber() {
        String poNumber = jsonData.getData("PONumber");
        sendKeysAndEnter(PurchaseOrderInquiryPage.tbxPONumber, poNumber, "Enter Purchase Order Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to verify that PO Number is NOT present in the table in PURCHASE ORDER INQUIRY Page
     */
    public void vrfyPONotInList() {
        String poNumber = jsonData.getData("PONumber");

        boolean flag = false;
        List<WebElement> lstOrderNo = getListElement(PurchaseOrderInquiryPage.lstOrderNumber);
        for (WebElement element : lstOrderNo){
            String text = element.getText().trim();
            flag = text.equalsIgnoreCase(poNumber);
        }
        if (!flag)
            report.updateTestLog("Verify Order Number Not available", "Verify Order Number Not available in table",Status.PASS);
        else
            report.updateTestLog("Verify Order Number Not available", "Verify Order Number Not available in table",Status.FAIL);
    }

    /**
     * Keyword to verify Quatity and Item Number in PURCHASE ORDER INQUIRY and Item Ledger Page
     */
    public void vrfyQtyAndItemLedgerDetails(){
        String qty = jsonData.getData("Qty");
        commonObj.validateText(PurchaseOrderInquiryPage.qty, qty, "validate Quantity");

        String itemNumber = getElement(PurchaseOrderInquiryPage.itemNumber).getText().trim();
        click(PurchaseOrderInquiryPage.btnItemLedger,"Click [Item Ledger] Button");
        String itemNumberActual = getAttribute(PurchaseOrderInquiryPage.tbxItemNumber, "value");
        Utility_Functions.xAssertEquals(report, itemNumber, itemNumberActual, "Validate Item Number textbox value");
    }

    /**
     * Keyword to come back to Inventory Receipts from Item Ledger Page
     */
    public void backToInventoryReceipts(){
        click(PurchaseOrderInquiryPage.btnExitItemLedger,"Click [Exit] Button");
        click(PurchaseOrderInquiryPage.btnExitPOInquiryDtls,"Click [Exit] Button");
    }

    /**
     * Keyword to select Status
     */
    public void selectStatus(){
        Utility_Functions.xSelectDropdownByName(driver, PurchaseOrderInquiryPage.ddnStatus, jsonData.getData("Status"));
    }

    /**
     * Keyword to verify presence of [No Detail Lines to Display]
     */
    public void vrfyNoDetailLineToDisplay(){
        commonObj.validateText(PurchaseOrderInquiryPage.msgNoDetailLinesToDisplay, "* No Detail Lines to Display", "validate [No Detail Lines to Display] msg");
    }

    /**
     * Keyword to verify switch view functionality in table
     */
    public void vrfySwitchView(){
        commonObj.validateElementExists(PurchaseOrderInquiryPage.btnSwitchViewTable, "Verify presence of Switch View button");
        commonObj.validateElementExists(PurchaseOrderInquiryPage.tblHdrOrderTotal, "Verify presence of [Order Total] table header");

        clickSwitchView(PurchaseOrderInquiryPage.tblHdrJobName, "Job Name");
        clickSwitchView(PurchaseOrderInquiryPage.tblHdrMiscNotes, "Misc Notes");
        clickSwitchView(PurchaseOrderInquiryPage.tblHdrCustomerPO, "Customer PO");
        clickSwitchView(PurchaseOrderInquiryPage.tblHdrQuoteNumber, "Quote Number");
        clickSwitchView(PurchaseOrderInquiryPage.tblHdrReceiverDoc, "Receiver Doc");
    }

    /**
     * Keyword to click switch view and verify presence of table header
     */
    public void clickSwitchView(By element, String tableHeaderName){
        click(PurchaseOrderInquiryPage.btnSwitchViewTable,"Click [Switch View] Button");
        commonObj.validateElementExists(element, "Verify presence of ["+tableHeaderName+"] table header");
    }

    /**
     * Keyword to validate [Type] dropdown options
     */
    public void verifyTypeDdnOptions(){
        List<String> lstTypeDdnValues = Utility_Functions.xgetDropdownOptionsAsList(driver, PurchaseOrderInquiryPage.ddnType);
        String[] typeOptions = {"ALL (All-Direct and Stock)","DIRECT (Direct Shipment)","RFQ (Request For Quote)","STOCK (Stock)"};

        boolean flag = false;
        if (lstTypeDdnValues.size() == typeOptions.length){
            for (int i=0; i<typeOptions.length; i++){
                if (typeOptions[i].equalsIgnoreCase(lstTypeDdnValues.get(i)))
                    flag = true;
                else
                    flag = false;
            }
        }
        if (flag)
            report.updateTestLog("Verify Type ddn options", "Verify Type dropdown options",Status.PASS);
        else
            report.updateTestLog("Verify Type ddn options", "Verify Type dropdown options",Status.FAIL);
    }

    /**
     * Keyword to vallidate [Type] dropdown filter
     */
    public void vrfyTypeDdnFilter(){
        selectTypeAndVrfyTableData("DIRECT (Direct Shipment)", "DIRECT");
        selectTypeAndVrfyTableData("STOCK (Stock)", "STOCK");
    }

    /**
     * Keyword to select [Type] dropdown option and verify table data
     */
    public void selectTypeAndVrfyTableData(String filterDdn, String colValue){
        Utility_Functions.xSelectDropdownByName(driver, PurchaseOrderInquiryPage.ddnType, filterDdn);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        boolean flag = false;
        List<WebElement> listElement = getListElement(PurchaseOrderInquiryPage.lstShipmentColText);
        for (WebElement e : listElement){
            String text = e.getText().trim();
            if (text.equalsIgnoreCase(colValue))
                flag = true;
            else
                flag = false;
        }
        if (flag)
            report.updateTestLog("Verify Type filter", "Verify filter by Shipment = ["+colValue+"]",Status.PASS);
        else
            report.updateTestLog("Verify Type filter", "Verify filter by Shipment = ["+colValue+"]",Status.FAIL);
    }

    /**
     * Keyword to validate [Status] dropdown options
     */
    public void verifyStatusDdnOptions(){
        List<String> lstStatusDdnValues = Utility_Functions.xgetDropdownOptionsAsList(driver, PurchaseOrderInquiryPage.ddnStatus);
        String[] statusOptions = {"All","Closed","Open"};

        boolean flag = false;
        if (lstStatusDdnValues.size() == statusOptions.length){
            for (int i=0; i<statusOptions.length; i++){
                if (statusOptions[i].equalsIgnoreCase(lstStatusDdnValues.get(i)))
                    flag = true;
                else
                    flag = false;
            }
        }
        if (flag)
            report.updateTestLog("Verify Status ddn options", "Verify Status dropdown options",Status.PASS);
        else
            report.updateTestLog("Verify Status ddn options", "Verify Status dropdown options",Status.FAIL);
    }

    /**
     * Keyword to vallidate [Status] dropdown filter
     */
    public void vrfyStatusDdnFilter(){
        selectStatusAndVrfyTableData("All", "OPEN CLOSED");
        selectStatusAndVrfyTableData("Closed", "CLOSED");
        selectStatusAndVrfyTableData("Open", "OPEN");
    }

    /**
     * Keyword to select [Status] dropdown option and verify table data
     */
    public void selectStatusAndVrfyTableData(String filterDdn, String colValue){
        Utility_Functions.xSelectDropdownByName(driver, PurchaseOrderInquiryPage.ddnStatus, filterDdn);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        boolean flag = false;
        List<WebElement> listElement = getListElement(PurchaseOrderInquiryPage.lstStatusColText);
        for (WebElement e : listElement){
            String text = e.getText().trim();
            if (colValue.contains(text))
                flag = true;
            else
                flag = false;
        }
        if (flag)
            report.updateTestLog("Verify Status filter", "Verify filter by Status = ["+colValue+"]",Status.PASS);
        else
            report.updateTestLog("Verify Status filter", "Verify filter by Status = ["+colValue+"]",Status.FAIL);
    }

    /**
     * Keyword to click on [Buyers Inquiry] link in PURCHASE ORDER INQUIRY Page
     */
    public void clickBuyersInquiry(){
        click(PurchaseOrderInquiryPage.lnkBuyersInquiry, "Click [Buyers Inquiry] link");
        commonObj.validateText(PurchaseOrderInquiryPage.hdrBuyersInquiry, "Buyer's Inquiry", "Validate [Buyer's Inquiry] page header");
    }

    /**
     * Keyword to click on [Buyers Worksheet] link in PURCHASE ORDER INQUIRY Page
     */
    public void clickBuyersWorksheet(){
        click(PurchaseOrderInquiryPage.lnkBuyersWorksheet, "Click [Buyers Worksheet] link");
        commonObj.validateText(PurchaseOrderInquiryPage.hdrBuyersWorksheet, "Buyer's Worksheet - Processing Options", "Validate [Buyers Worksheet] page header");
    }

    /**
     * Keyword to click on [PO Entry] link in PURCHASE ORDER INQUIRY Page
     */
    public void clickPOEntry(){
        click(PurchaseOrderInquiryPage.lnkPOEntry, "Click [PO Entry] link");
        commonObj.validateText(PurchaseOrderInquiryPage.hdrPOEntry, "Purchase Order Headings", "Validate [Purchase Order Headings] page header");
    }

    /**
     * Keyword to click on [PO Preferences] link in PURCHASE ORDER INQUIRY Page
     */
    public void clickPOPreferences(){
        click(PurchaseOrderInquiryPage.lnkPOPreferences, "Click [PO Preferences] link");
        commonObj.validateText(PurchaseOrderInquiryPage.hdrPOPreferences, "Purchase Order User Preferences", "Validate [Purchase Order User Preferences] page header");
    }

}
