package businesskeywords.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.PurchaseOrders.OptionsConstantsPage;
import pages.PurchaseOrders.PurchaseOrderDetailsPage;
import pages.PurchaseOrders.PurchaseOrderEntryPage;
import pages.PurchaseOrders.PurchaseOrderInquiryPage;
import pages.SalesOrders.SalesOrdersPage;
import pages.common.MasterPage;
import pages.inventory.CostAdjustmentPage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import supportLibraries.Utility_Functions;

import java.util.List;


public class PurchaseOrderInquiry extends ReusableLib {

    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public PurchaseOrderInquiry(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
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
    public void clickExitBtnPOInquiry() {
        Utility_Functions.xScrollPage(ownDriver);
        int count = ownDriver.findElements(PurchaseOrderInquiryPage.btnExitPOInquiryDtls).size();
        click(ownDriver.findElements(PurchaseOrderInquiryPage.btnExitPOInquiryDtls).get(count - 1), "Click [Exit] button");
    }

    /**
     * This method navigate To Po Inquiry
     */
    public void navigateToPoInquiry() {
        commonObj.masterToPurchaseOrder();
        click(PurchaseOrderInquiryPage.poInquiry, "Click Inquiry - Purchase Orders");
        commonObj.validateText(PurchaseOrderInquiryPage.inquiryHeader, "Purchase Order Inquiry", "'Purchase Order Inquiry' header is present");
    }

    /**
     * Keyword to click on [Exit] button
     */
    public void clickOnExitBtn() {
        click(PurchaseOrderInquiryPage.btnExitItemLedger, "Click [Exit] button");
    }

    /**
     * Keyword to press [F3] Exit button
     */
    public void pressF3ExitBtn() {
        Utility_Functions.actionKey(Keys.F3, ownDriver);
        report.updateTestLog("Press [F3] button", "Press [F3] button", Status.PASS);
    }

    /**
     * Keyword to press [F12] Exit button
     */
    public void pressF12CancelBtn() {
        Utility_Functions.actionKey(Keys.F12, ownDriver);
        report.updateTestLog("Press [F12] button", "Press [F12] button", Status.PASS);
    }

    /**
     * Keyword to click on [Exit] button
     */
    public void clickOnF12ReturnBtn() {
        try {
            click(PurchaseOrderInquiryPage.btnF12Return, "Click [F12=Return] button");
        }catch (Exception e){
            click(PurchaseOrderInquiryPage.btnExitItemLedger, "Click [Exit] button");
        }
    }

    /**
     * Keyword to exit to master from PURCHASE ORDER INQUIRY Page
     */
    public void navigatePOInquiryToMaster() {
        Utility_Functions.xScrollPage(ownDriver);
        if (isDisplayed(PurchaseOrderInquiryPage.btnExitPOInquiryDtls)) {
            click(PurchaseOrderInquiryPage.btnExitPOInquiryDtls, "Click [Exit] button");
        }
        Utility_Functions.actionKey(Keys.F3, ownDriver);
    }

    /**
     * Keyword to enter PO Number and select action in PURCHASE ORDER INQUIRY Page
     */
    public void enterPONumberAndSelectAction() {
        String poNumber = Utility_Functions.xGetJsonData("PONumber");
        sendKeysAndEnter(PurchaseOrderInquiryPage.tbxPONumber, poNumber, "Enter Purchase Order Number");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        String action = jsonData.getData("Action");

        List<WebElement> lstOrderNo = getListElement(PurchaseOrderInquiryPage.lstOrderNumber);
        List<WebElement> lstAction = getListElement(PurchaseOrderInquiryPage.lstActions);

        int index = 0;
        for (WebElement element : lstOrderNo) {
            String text = element.getText().trim();
            if (text.equalsIgnoreCase(poNumber)) {
                index = lstOrderNo.indexOf(element);
                break;
            }
        }
        sendKeys(lstAction.get(index), action, "Selected PO Number");
        click(PurchaseOrderInquiryPage.btnSubmit, "Click [Submit] Button");
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
        for (WebElement element : lstOrderNo) {
            String text = element.getText().trim();
            flag = text.equalsIgnoreCase(poNumber);
        }
        if (!flag)
            report.updateTestLog("Verify Order Number Not available", "Verify Order Number Not available in table", Status.PASS);
        else
            report.updateTestLog("Verify Order Number Not available", "Verify Order Number Not available in table", Status.FAIL);
    }

    /**
     * Keyword to verify Quatity and Item Number in PURCHASE ORDER INQUIRY and Item Ledger Page
     */
    public void vrfyQtyAndItemLedgerDetails() {
        String qty = jsonData.getData("Qty");
        commonObj.validateText(PurchaseOrderInquiryPage.qty, qty, "validate Quantity");

        String itemNumber = getElement(PurchaseOrderInquiryPage.itemNumber).getText().trim();
        click(PurchaseOrderInquiryPage.btnItemLedger, "Click [Item Ledger] Button");
        String itemNumberActual = getAttribute(PurchaseOrderInquiryPage.tbxItemNumber, "value");
        Utility_Functions.xAssertEquals(report, itemNumber, itemNumberActual, "Validate Item Number textbox value");
    }

    public void navigateToItemLedger() {
        String itemNumber = getElement(PurchaseOrderInquiryPage.itemNumber).getText().trim();
        click(PurchaseOrderInquiryPage.btnItemLedger, "Click [Item Ledger] Button");
        String itemNumberActual = getAttribute(PurchaseOrderInquiryPage.tbxItemNumber, "value");
        Utility_Functions.xAssertEquals(report, itemNumber, itemNumberActual, "Validate Item Number textbox value");
    }

    /**
     * Keyword to come back to Inventory Receipts from Item Ledger Page
     */
    public void backToInventoryReceipts() {
        click(PurchaseOrderInquiryPage.btnExitItemLedger, "Click [Exit] Button");
        click(PurchaseOrderInquiryPage.btnExitPOInquiryDtls, "Click [Exit] Button");
    }

    /**
     * Keyword to select Status
     */
    public void selectStatus() {
        Utility_Functions.xSelectDropdownByName(ownDriver, PurchaseOrderInquiryPage.ddnStatus, jsonData.getData("Status"));
    }

    /**
     * Keyword to Enter Vendor and check for result
     */
    public void enterVendor() {
        String vendorNo = jsonData.getData("VendorNo");
        sendKeys(PurchaseOrderInquiryPage.vendorTextField, vendorNo + Keys.ENTER, "Enter Vendor Number");
        Utility_Functions.timeWait(2);
        commonObj.validateText(PurchaseOrderInquiryPage.vendorVerify, vendorNo, vendorNo + " is present");
    }

    /**
     * Keyword to validate Order By field
     */
    public void orderBy() {
        click(PurchaseOrderInquiryPage.orderBySearchIcon, "Click Order By Search Icon");
        Utility_Functions.timeWait(2);
        List<WebElement> els = getListElement(PurchaseOrderInquiryPage.optField);
        for (int i = 0; i < els.size(); i++) {
            Utility_Functions.timeWait(2);
            els = getListElement(PurchaseOrderInquiryPage.optField);
            Utility_Functions.xSendKeys(ownDriver, els.get(i), "1" + Keys.ENTER);
            if (isDisplayed(PurchaseOrderInquiryPage.noResult1)) {
                commonObj.validateText(PurchaseOrderInquiryPage.noResult1, "* No results to display based on the selected criteria.", "'* No results to display based on the selected criteria.' is present");
                commonObj.validateText(PurchaseOrderInquiryPage.noResult2, "* The order may not exist or it may be omitted due to current settings.", "'* The order may not exist or it may be omitted due to current settings.' is present");
                commonObj.validateText(PurchaseOrderInquiryPage.noResult3, "* To continue searching, choose less restrictive search criteria.", "'* To continue searching, choose less restrictive search criteria.' is present");
            }
            click(PurchaseOrderInquiryPage.orderBySearchIcon, "Click Order By Search Icon");
            Utility_Functions.timeWait(2);
        }
        click(SalesOrdersPage.btnCancel, "Click [F12=Cancel]");
    }

    /**
     * Keyword to verify presence of [No Detail Lines to Display]
     */
    public void vrfyNoDetailLineToDisplay() {
        commonObj.validateText(PurchaseOrderInquiryPage.msgNoDetailLinesToDisplay, "* No Detail Lines to Display", "validate [No Detail Lines to Display] msg");
    }

    /**
     * Keyword to verify switch view functionality in table
     */
    public void vrfySwitchView() {
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
    public void clickSwitchView(By element, String tableHeaderName) {
        click(PurchaseOrderInquiryPage.btnSwitchViewTable, "Click [Switch View] Button");
        commonObj.validateElementExists(element, "Verify presence of [" + tableHeaderName + "] table header");
    }

    /**
     * Keyword to validate [Type] dropdown options
     */
    public void verifyTypeDdnOptions() {
        List<String> lstTypeDdnValues = Utility_Functions.xgetDropdownOptionsAsList(ownDriver, PurchaseOrderInquiryPage.ddnType);
        String[] typeOptions = {"ALL (All-Direct and Stock)", "DIRECT (Direct Shipment)", "RFQ (Request For Quote)", "STOCK (Stock)"};

        boolean flag = false;
        if (lstTypeDdnValues.size() == typeOptions.length) {
            for (int i = 0; i < typeOptions.length; i++) {
                if (typeOptions[i].equalsIgnoreCase(lstTypeDdnValues.get(i)))
                    flag = true;
                else
                    flag = false;
            }
        }
        if (flag)
            report.updateTestLog("Verify Type ddn options", "Verify Type dropdown options", Status.PASS);
        else
            report.updateTestLog("Verify Type ddn options", "Verify Type dropdown options", Status.FAIL);
    }

    /**
     * Keyword to vallidate [Type] dropdown filter
     */
    public void vrfyTypeDdnFilter() {
        selectTypeAndVrfyTableData("DIRECT (Direct Shipment)", "DIRECT");
        selectTypeAndVrfyTableData("STOCK (Stock)", "STOCK");
    }

    /**
     * Keyword to select [Type] dropdown option and verify table data
     */
    public void selectTypeAndVrfyTableData(String filterDdn, String colValue) {
        Utility_Functions.xSelectDropdownByName(ownDriver, PurchaseOrderInquiryPage.ddnType, filterDdn);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        boolean flag = false;
        List<WebElement> listElement = getListElement(PurchaseOrderInquiryPage.lstShipmentColText);
        for (WebElement e : listElement) {
            String text = e.getText().trim();
            if (text.equalsIgnoreCase(colValue))
                flag = true;
            else
                flag = false;
        }
        if (flag)
            report.updateTestLog("Verify Type filter", "Verify filter by Shipment = [" + colValue + "]", Status.PASS);
        else
            report.updateTestLog("Verify Type filter", "Verify filter by Shipment = [" + colValue + "]", Status.FAIL);
    }

    /**
     * Keyword to validate [Status] dropdown options
     */
    public void verifyStatusDdnOptions() {
        List<String> lstStatusDdnValues = Utility_Functions.xgetDropdownOptionsAsList(ownDriver, PurchaseOrderInquiryPage.ddnStatus);
        String[] statusOptions = {"All", "Closed", "Open"};

        boolean flag = false;
        if (lstStatusDdnValues.size() == statusOptions.length) {
            for (int i = 0; i < statusOptions.length; i++) {
                if (statusOptions[i].equalsIgnoreCase(lstStatusDdnValues.get(i)))
                    flag = true;
                else
                    flag = false;
            }
        }
        if (flag)
            report.updateTestLog("Verify Status ddn options", "Verify Status dropdown options", Status.PASS);
        else
            report.updateTestLog("Verify Status ddn options", "Verify Status dropdown options", Status.FAIL);
    }

    /**
     * Keyword to vallidate [Status] dropdown filter
     */
    public void vrfyStatusDdnFilter() {
        selectStatusAndVrfyTableData("All", "OPEN CLOSED");
        selectStatusAndVrfyTableData("Closed", "CLOSED");
        selectStatusAndVrfyTableData("Open", "OPEN");
    }

    /**
     * Keyword to select [Status] dropdown option and verify table data
     */
    public void selectStatusAndVrfyTableData(String filterDdn, String colValue) {
        Utility_Functions.xSelectDropdownByName(ownDriver, PurchaseOrderInquiryPage.ddnStatus, filterDdn);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);

        boolean flag = false;
        List<WebElement> listElement = getListElement(PurchaseOrderInquiryPage.lstStatusColText);
        for (WebElement e : listElement) {
            String text = e.getText().trim();
            if (colValue.contains(text))
                flag = true;
            else
                flag = false;
        }
        if (flag)
            report.updateTestLog("Verify Status filter", "Verify filter by Status = [" + colValue + "]", Status.PASS);
        else
            report.updateTestLog("Verify Status filter", "Verify filter by Status = [" + colValue + "]", Status.FAIL);
    }

    /**
     * Keyword to click on [Buyers Inquiry] link in PURCHASE ORDER INQUIRY Page
     */
    public void clickBuyersInquiry() {
        click(PurchaseOrderInquiryPage.lnkBuyersInquiry, "Click [Buyers Inquiry] link");
        commonObj.validateText(PurchaseOrderInquiryPage.hdrBuyersInquiry, "Buyer's Inquiry", "Validate [Buyer's Inquiry] page header");
    }

    /**
     * Keyword to click on [Buyers Worksheet] link in PURCHASE ORDER INQUIRY Page
     */
    public void clickBuyersWorksheet() {
        click(PurchaseOrderInquiryPage.lnkBuyersWorksheet, "Click [Buyers Worksheet] link");
        commonObj.validateText(PurchaseOrderInquiryPage.hdrBuyersWorksheet, "Buyer's Worksheet - Processing Options", "Validate [Buyers Worksheet] page header");
    }

    /**
     * Keyword to click on [PO Entry] link in PURCHASE ORDER INQUIRY Page
     */
    public void clickPOEntry() {
        try {
            click(PurchaseOrderInquiryPage.lnkPOEntry, "Click [PO Entry] link");
        }catch (Exception e){
            click(By.xpath("//a[text()='PO Entry']"), "Click [PO Entry] link");
        }
        commonObj.validateText(PurchaseOrderInquiryPage.hdrPOEntry, "Purchase Order Headings", "Validate [Purchase Order Headings] page header");
    }

    /**
     * Keyword to click on [PO Preferences] link in PURCHASE ORDER INQUIRY Page
     */
    public void clickPOPreferences() {
        click(PurchaseOrderInquiryPage.lnkPOPreferences, "Click [PO Preferences] link");
        commonObj.validateText(PurchaseOrderInquiryPage.hdrPOPreferences, "Purchase Order User Preferences", "Validate [Purchase Order User Preferences] page header");
    }

    public By fields(String text) {
        return By.xpath("//div[contains(text(),'" + text + "')]");
    }

    public By aTag(String text) {
        return By.xpath("//a[contains(text(),'" + text + "')]");
    }

    public void validatePoInquiryDetailsUI() {
        commonObj.validateText(PurchaseOrderInquiryPage.hdrPOInquiryDetails, "Purchase Order Inquiry - Details", "Validating [Purchase Order Inquiry - Details] title");
        String fields[] = {"Position", "Item", "Status", "Number", "Date", "Vendor", "Type", "Amount", "Options", "Line", "Quantity", "Number/VPN", "Description", "Price", "Ext", "Documents", "Actions"};
        for (String field : fields) {
            commonObj.validateElementExists(fields(field), field + " is present");
        }
        String links[] = {"More Info", "PDF/Vendor", "PO Entry", "Show Closed"};
        for (String link : links) {
            commonObj.validateText(aTag(link), link, link + " is present");
        }
        commonObj.validateText(By.xpath("//h3[text()='Actions']"), "Actions", "Actions sub header is present");
    }

    public By optionsCol(String text) {
        return By.xpath("//option[text()='" + text + "']");
    }

    public void navigateToOpenOrderByItemPage() {
        Utility_Functions.xSelectDropdownByName(ownDriver, report, PurchaseOrderInquiryPage.detailsOptionsColumn, "5-Order By Item", "Select [5-Order By Item]");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(SpecialPriceAllowancePage.header, "Open Orders By Item", "[Open Orders By Item] header is present");
    }

    public void navigateToItemLedgerPage() {
        Utility_Functions.xSelectDropdownByName(ownDriver, report, PurchaseOrderInquiryPage.detailsOptionsColumn, "6-Item Ledger", "Select [6-Item Ledger]");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(PurchaseOrderInquiryPage.hdrItemLedger, "Item Ledger (I-385)", "[Item Ledger (I-385)] header is present");
    }

    public void navigateToBuyersInquiryPage() {
        Utility_Functions.xSelectDropdownByName(ownDriver, report, PurchaseOrderInquiryPage.detailsOptionsColumn, "7-Buyers Inquiry", "Select [7-Buyers Inquiry]");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(PurchaseOrderInquiryPage.hdrBuyersInquiry, "Buyer's Inquiry", "Validate [Buyer's Inquiry] page header");
    }

    public void navigateToVPNRevisionsPage() {
        Utility_Functions.xSelectDropdownByName(ownDriver, report, PurchaseOrderInquiryPage.detailsOptionsColumn, "8-VPN Revisions", "Select [8-VPN Revisions]");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(PurchaseOrderInquiryPage.hdrBuyersWorksheet, "vendor part number revisions - (i-733-a)", "Validate [vendor part number revisions - (i-733-a)] page header");
    }

    public void navigateToLocalCompanyInformationPage() {
        Utility_Functions.xSelectDropdownByName(ownDriver, report, PurchaseOrderInquiryPage.detailsOptionsColumn, "10-Local Company Usage", "Select [10-Local Company Usage]");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateText(By.xpath("//span[text()='Local Company Information']"), "Local Company Information", "Validate [Local Company Information] page header");
    }

    public void validateOptions() {
        String[] options = {"5-Order By Item", "6-Item Ledger", "7-Buyers Inquiry", "8-VPN Revisions", "10-Local Company Usage"};
        for (String option : options) {
            commonObj.validateText(optionsCol(option), option, option + " is present");
        }
        navigateToOpenOrderByItemPage();
        clickOnExitBtn();
        navigateToItemLedgerPage();
        clickOnExitBtn();
        navigateToBuyersInquiryPage();
        clickOnExitBtn();
        navigateToVPNRevisionsPage();
        clickOnExitBtn();
        navigateToLocalCompanyInformationPage();
        click(PurchaseOrderInquiryPage.exitCustInfo, "Exit from [Local Company Information Screen]");
    }

    public void verifyItemNumberFields() {
        sendKeysAndEnter(PurchaseOrderInquiryPage.itemNumberTextBox, jsonData.getData("alphaNumeric"), "Enter Alphanumeric value into Item Number Input field");
        vrfyNoDetailLineToDisplay();
        sendKeysAndEnter(PurchaseOrderInquiryPage.itemNumberTextBox, jsonData.getData("InvalidItemNumber"), "Enter Invalid Item Number value into Item Number Input field");
        vrfyNoDetailLineToDisplay();
        sendKeysAndEnter(PurchaseOrderInquiryPage.itemNumberTextBox, jsonData.getData("NegativeItemNumber"), "Enter Negative Item Number value into Item Number Input field");
        vrfyNoDetailLineToDisplay();
        sendKeysAndEnter(PurchaseOrderInquiryPage.itemNumberTextBox, jsonData.getData("nineteenDigitItemNo"), "Enter nineteen Digit ItemNo value into Item Number Input field");
    }

    public void verifyNextBackExitButtonItemLedger() {
        Utility_Functions.xSelectDropdownByName(ownDriver, report, PurchaseOrderInquiryPage.detailsOptionsColumn, "6-Item Ledger", "Select [6-Item Ledger]");
        click(PurchaseOrderInquiryPage.btnSubmit, "Click [Next] Button");
        commonObj.validateText(PurchaseOrderInquiryPage.hdrItemLedger, "Item Ledger (I-385)", "[Item Ledger (I-385)] header is present");
        clickOnExitBtn();
        commonObj.validateText(PurchaseOrderInquiryPage.hdrPOInquiryDetails, "Purchase Order Inquiry - Details", "Validating [Purchase Order Inquiry - Details] title");
    }

    public By divTag(String text) {
        return By.xpath("//div[text()='" + text + "']");
    }

    public By divTagContains(String text) {
        return By.xpath("//div[contains(text(),'" + text + "')]");
    }

    public void validateClosedPO() {
        selectStatus();
        commonObj.validateElementExists(divTag(Utility_Functions.xGetJsonData("PONumber")), "Closed PO present");
    }

    public void validateClosedItem() {
        commonObj.validateText(PurchaseOrderInquiryPage.statusPoDetails, "CLOSED", "[CLOSED] status is present");
        commonObj.validateText(PurchaseOrderInquiryPage.orderNumberPoDetails, Utility_Functions.xGetJsonData("PONumber"), Utility_Functions.xGetJsonData("PONumber") + " is present");
        commonObj.validateText(PurchaseOrderInquiryPage.itemNumber, Utility_Functions.xGetJsonData("FullyReceivedItem"), Utility_Functions.xGetJsonData("FullyReceivedItem") + " is present");
    }

    public void validateClosedItems() {
        commonObj.validateText(PurchaseOrderInquiryPage.statusPoDetails, "CLOSED", "[CLOSED] status is present");
        int rowCount = ownDriver.findElements(By.xpath("//div[contains(text(),'" + Utility_Functions.xGetJsonData("FullyReceivedItem") + "')]")).size();
        Utility_Functions.xAssertEquals(report, "" + rowCount + "", "2", "Two closed records are present");
    }

    public void verifyPositionToLine() {
        sendKeysAndEnter(PurchaseOrderInquiryPage.positionToLine, jsonData.getData("alphaNumeric"), "Enter AlphaNumeric value into [position To Line] text box");
        commonObj.validateElementExists(PurchaseOrderInquiryPage.errorTitle, "Error [Invalid line number entered.]");
        sendKeysAndEnter(PurchaseOrderInquiryPage.positionToLine, jsonData.getData("InvalidItemNumber"), "Enter AlphaNumeric value into [position To Line] text box");
        commonObj.validateElementExists(PurchaseOrderInquiryPage.errorTitle, "Error [Invalid line number entered.]");
        sendKeysAndEnter(PurchaseOrderInquiryPage.positionToLine, jsonData.getData("NegativeItemNumber"), "Enter AlphaNumeric value into [position To Line] text box");
        commonObj.validateElementExists(PurchaseOrderInquiryPage.errorTitle, "Error [Invalid line number entered.]");
    }

    public void validateStatus() {
        String getValue = getText(PurchaseOrderInquiryPage.showAllLink);
        click(PurchaseOrderInquiryPage.showAllLink, "CLick " + getValue + " link");
        if (getValue.equals("Show All")) {
            commonObj.validateText(PurchaseOrderInquiryPage.statusPoDetails, "ALL", "[ALL] status is present");
        } else if (getValue.equals("Show Open")) {
            commonObj.validateText(PurchaseOrderInquiryPage.statusPoDetails, "OPEN", "[OPEN] status is present");
        } else {
            commonObj.validateText(PurchaseOrderInquiryPage.statusPoDetails, "CLOSED", "[CLOSED] status is present");
        }
    }

    public void verifyMoreInfoLink() {
        click(aTag("More Info"), "Click [More Info] link");
        commonObj.validateText(OptionsConstantsPage.buyingDiscPageHeader, "Purchase Order Inquiry - More Info", "[Purchase Order Inquiry - More Info] page title");
        commonObj.validateText(PurchaseOrderInquiryPage.orderNumber, Utility_Functions.xGetJsonData("PONumber"), Utility_Functions.xGetJsonData("PONumber") + " order number is present");
        clickOnF12ReturnBtn();
        validateStatus();
        validateStatus();
        validateStatus();
    }

    public void verifyItemLedgerUI() {
        String[] fields = {"UOM:", "Number", "Customer", "Source", "On-Hand", "In-Hold:", "From", "To", "Unscheduled:", "Value:", "Manufacturer"};
        for (String field : fields) {
            commonObj.validateElementExists(divTagContains(field), field + " is present");
        }
        commonObj.validateText(By.xpath("//h3[text()='Actions']"), "Actions", "Actions sub header is present");
        commonObj.validateText(aTag("Bin Locations"), "Bin Locations", "[Bin Locations] Link is present");
        commonObj.validateText(aTag("Stock Only"), "Stock Only", "[Bin Locations] Link is present");
        commonObj.validateText(aTag("Next Item >"), "Next Item >", "[Next Item >] Link is present");
        commonObj.validateText(aTag("< Previous Item"), "< Previous Item", "[< Previous Item] Link is present");
        String[] ledgerTableHeaders = {"Reference", "SC", "UOM", "By", "Explanation", "Price", "Units", "Qty After", "Job Name & Item", "Status", "Dsp Serial"};
        for (String ledgerTableHeader : ledgerTableHeaders) {
            commonObj.validateElementExists(divTagContains(ledgerTableHeader), ledgerTableHeader + " is present");
        }
    }

    public void verifyItemNumberLedger() {
        sendKeysAndEnter(PurchaseOrderInquiryPage.tbxItemNumber, jsonData.getData("InvalidItemNo"), "Enter invalid Item Number");
        commonObj.validateElementExists(PurchaseOrderInquiryPage.stockOnlyLinkDis, "Stock Only Link is disabled and no records found");
        sendKeysAndEnter(PurchaseOrderInquiryPage.tbxItemNumber, jsonData.getData("NegativeItemNo"), "Enter negative Item Number");
        commonObj.validateElementExists(PurchaseOrderInquiryPage.stockOnlyLinkDis, "Stock Only Link is disabled and no records found");
        sendKeysAndEnter(PurchaseOrderInquiryPage.tbxItemNumber, jsonData.getData("AlphaNumericItemNo"), "Enter Alpha numeric Item Number");
        commonObj.validateElementExists(PurchaseOrderInquiryPage.stockOnlyLinkDis, "Stock Only Link is disabled and no records found");
        sendKeysAndEnter(PurchaseOrderInquiryPage.tbxItemNumber, jsonData.getData("SpecialCharItemNo"), "Enter Special Character Item Number");
    }

    public void verifyCustomerLedger() {
        sendKeysAndEnter(PurchaseOrderInquiryPage.customerTextBox, jsonData.getData("InvalidItemNo"), "Enter invalid Customer Number");
        commonObj.validateElementExists(CostAdjustmentPage.explanationLedger, "[No records found] message is present");
        sendKeysAndEnter(PurchaseOrderInquiryPage.customerTextBox, jsonData.getData("NegativeItemNo"), "Enter negative Customer Number");
        commonObj.validateElementExists(CostAdjustmentPage.explanationLedger, "[No records found] message is present");
        sendKeysAndEnter(PurchaseOrderInquiryPage.customerTextBox, jsonData.getData("AlphaNumericItemNo"), "Enter Alpha numeric Customer Number");
        commonObj.validateElementExists(CostAdjustmentPage.explanationLedger, "[No records found] message is present");
        sendKeysAndEnter(PurchaseOrderInquiryPage.customerTextBox, jsonData.getData("SpecialCharItemNo"), "Enter Special Character Customer Number");
    }

    public void verifySourceFieldLedger() {
        sendKeysAndEnter(PurchaseOrderInquiryPage.inpSrcCode, jsonData.getData("InvalidCode"), "Enter invalid Source Code");
        commonObj.validateElementExists(CostAdjustmentPage.explanationLedger, "[No records found] message is present");
        sendKeysAndEnter(PurchaseOrderInquiryPage.inpSrcCode, jsonData.getData("NegativeCode"), "Enter negative Source Code");
        commonObj.validateElementExists(CostAdjustmentPage.explanationLedger, "[No records found] message is present");
        sendKeysAndEnter(PurchaseOrderInquiryPage.inpSrcCode, jsonData.getData("AlphaNumericCode"), "Enter Alpha numeric Source Code");
        commonObj.validateElementExists(CostAdjustmentPage.explanationLedger, "[No records found] message is present");
        sendKeysAndEnter(PurchaseOrderInquiryPage.inpSrcCode, jsonData.getData("SpecialCharCode"), "Enter Special Character Source Code");
    }

    public void pressF9() {
        Utility_Functions.actionKey(Keys.F9, ownDriver);
    }

    public void verifyCalendarPicker() {
        click(PurchaseOrderInquiryPage.calendarIcon, "Click Calendar Icon");
        click(PurchaseOrderInquiryPage.yearPicker);
        click(PurchaseOrderInquiryPage.firstDay, "Select day");
        click(ownDriver.findElements(PurchaseOrderInquiryPage.calendarIcon).get(1));
        click(PurchaseOrderInquiryPage.firstDay, "Select day");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        commonObj.validateElementExists(PurchaseOrderInquiryPage.gridExist, "Records are present");
    }

    public void validatePOForNoItem() {
        vrfyNoDetailLineToDisplay();
        commonObj.validateText(PurchaseOrderInquiryPage.statusPoDetails, "OPEN", "[OPEN] status is present");
        commonObj.validateText(PurchaseOrderInquiryPage.stockType, "STOCK", "[STOCK] type is present");
        commonObj.validateText(PurchaseOrderInquiryPage.openAmount, ".00", "[$.00] status is present");
        commonObj.validateText(PurchaseOrderInquiryPage.dateField, Utility_Functions.xGetCurrentDate("MM/dd/yy"), "[" + Utility_Functions.xGetCurrentDate("MM/dd/yy") + "] date is present");
    }

    public void validateOpenItem() {
        commonObj.validateText(PurchaseOrderInquiryPage.statusPoDetails, "OPEN", "[OPEN] status is present");
        commonObj.validateText(PurchaseOrderInquiryPage.orderNumberPoDetails, Utility_Functions.xGetJsonData("PONumber"), Utility_Functions.xGetJsonData("PONumber") + " is present");
        commonObj.validateText(PurchaseOrderInquiryPage.itemNumber, Utility_Functions.xGetJsonData("FullyReceivedItem"), Utility_Functions.xGetJsonData("FullyReceivedItem") + " is present");
    }

    public void clickUntilStatusVisible() {
        while (!jsonData.getData("Status").equals(getText(PurchaseOrderInquiryPage.statusPoDetails))) {
            click(PurchaseOrderInquiryPage.showAllLink);
        }
    }

    public void validateAmount() {
        clickUntilStatusVisible();
        int line = Integer.parseInt(getText(PurchaseOrderInquiryPage.lineColumn).replace(".0", ""));
        Utility_Functions.xUpdateJson("LineItemCount", "" + line + "");
        int qty = Integer.parseInt(getText(PurchaseOrderInquiryPage.qty));
        String unitPrice = getText(PurchaseOrderInquiryPage.unitPriceColumn).replace(".00", "").trim();
        String extPrice = getText(PurchaseOrderInquiryPage.extPriceColumn).replace(",", "").replace(".00", "").trim();
        int unitCost = line * qty;
        Utility_Functions.xUpdateJson("UnitPricePoDetails", "" + unitCost + "");
        Utility_Functions.xAssertEquals(report, unitPrice, "" + unitCost + "", "");
        int extCost = qty * unitCost;
        Utility_Functions.xUpdateJson("ExtPricePoDetails", "" + extCost + "");
        String amount = getText(PurchaseOrderInquiryPage.openAmount).replace("$", "").replace(",", "").replace(".00", "").trim();
        Utility_Functions.xAssertEquals(report, extPrice, "" + extCost + "", "");
        Utility_Functions.xAssertEquals(report, amount, "" + extCost + "", "");
    }

    public void verifyPoDetailsWithLineNoAction() {
        sendKeys(PurchaseOrderDetailsPage.actionInput, "I", "Enter [I] on Action");
        sendKeys(PurchaseOrderDetailsPage.lineno, "1" + Keys.ENTER, "Enter [1] on Line No");
        Utility_Functions.timeWait(4);
        commonObj.validateElementExists(By.xpath("//div[contains(text(),'" + Utility_Functions.xGetJsonData("LineItemCount") + "')]"), Utility_Functions.xGetJsonData("LineItemCount") + " Line Item is present");
        commonObj.validateElementExists(By.xpath("//div[contains(text(),'" + Utility_Functions.xGetJsonData("UnitPricePoDetails") + "')]"), Utility_Functions.xGetJsonData("UnitPricePoDetails") + " Unit Price is present");
        commonObj.validateElementExists(By.xpath("//div[contains(text(),'" + Utility_Functions.xGetJsonData("ExtPricePoDetails") + "')]"), Utility_Functions.xGetJsonData("ExtPricePoDetails") + " Ext Price is present");
    }

    public void validateTotalQty() {
        clickUntilStatusVisible();
        Utility_Functions.xUpdateJson("POItemQty", "");
        clickPOEntry();
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        verifyPoDetailsWithLineNoAction();
    }
}
