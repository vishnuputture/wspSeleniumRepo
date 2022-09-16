package businesskeywords.AccountReceivable.ARToWISE;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.WebElement;
import pages.AccountReceivable.AR2WISE.AR2WISEPage;
import supportLibraries.Utility_Functions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AR2WISE extends ReusableLib {

    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public AR2WISE(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * Keyword to launch AR2Wise app
     */
    public void launchAR2Wise(){
        String url = properties.getProperty("AR2WISE_URL");
        ownDriver.get(url);
        ngWaitRequestToFinish();
        vrfyARGLDInquiryHeader();
    }

    /**
     * Keyword to validate header - [A/R G/L DETAIL INQUIRY]
     */
    public void vrfyARGLDInquiryHeader(){
        commonObj.validateText(AR2WISEPage.hdrARGLDInquiry, "A/R G/L DETAIL INQUIRY", "Validating [A/R G/L DETAIL INQUIRY] page header");
    }

    /**
     * Keyword to navigate to [TRANSACTION EXCEPTION LIST] page
     */
    public void navToTrnctnExcptnLstPage(){
        click(AR2WISEPage.btnOpenMenu, "Clicked open menu btn");
        click(AR2WISEPage.menuTrnsctnExcptn, "Click [Transaction Exception] menu");
        commonObj.validateText(AR2WISEPage.hdrTrnsctnExcptnList, "TRANSACTION EXCEPTION LIST", "Validating [TRANSACTION EXCEPTION LIST] page header");
    }

    /**
     * Keyword to select company
     */
    public void selectCompanyNumberAndName(){
        String company = jsonData.getData("Company");
        sendKeys(AR2WISEPage.tbxCompany,company,"Enter company number in [Company Number and Name] textbox");
        click(AR2WISEPage.ddnCompany, "Selected Company from Company dropdown list");
    }

    /**
     * Keyword to select Date
     */
    public void selectDate(){
        String date = jsonData.getData("Date");
        sendKeys(AR2WISEPage.tbxDate, date,"Enter date in [Date] textbox");
    }

    /**
     * Keyword to select Document Type
     */
    public void selectDocType(){
        String docType = jsonData.getData("DocumentType");
        Utility_Functions.xSelectDropdownByName(ownDriver, report, AR2WISEPage.ddnDocumentType, docType, "Selected document type");
        waitForElementDisappear(AR2WISEPage.loaderIcon, 30);
    }

    /**
     * Keyword to validate Document Type in results table
     */
    public void vrfyDocumentType(){
        String docType = jsonData.getData("DocumentType");
        xWaitForElementVisible(AR2WISEPage.thDocumentType, 5);
        List<WebElement> lstElements = getListElement(AR2WISEPage.lstDocumentTypeTable);
        List<String> lstText = Utility_Functions.xGetTextVisibleListString(ownDriver, lstElements);
        int count = 0;
        for(String text : lstText){
            if (text.trim().equalsIgnoreCase(docType)){
                count++;
            }
        }
        if (count== lstText.size())
            report.updateTestLog("Verify Document Type", "Verify Document Type", Status.PASS);
        else
            report.updateTestLog("Verify Document Type", "Verify Document Type", Status.FAIL);
    }

    /**
     * Keyword to validate Document Type - Misc. Inventory in results table
     */
    public void vrfyMiscInvDocumentType(){
        String docType = "Misc. Inventory";
        xWaitForElementVisible(AR2WISEPage.thDocumentType, 5);
        List<WebElement> lstElements = getListElement(AR2WISEPage.lstDocumentTypeTable);
        List<String> lstText = Utility_Functions.xGetTextVisibleListString(ownDriver, lstElements);
        int count = 0;
        for(String text : lstText){
            if (text.trim().equalsIgnoreCase(docType)){
                count++;
            }
        }
        if (count== lstText.size())
            report.updateTestLog("Verify Document Type", "Verify Document Type", Status.PASS);
        else
            report.updateTestLog("Verify Document Type", "Verify Document Type", Status.FAIL);
    }

    /**
     * Keyword to validate Resolve functionality in results table
     * i.e., click on green tick besides status and validate status
     */
    public void vrfyResolve() {
        String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        List<WebElement> lstStatus = getListElement(AR2WISEPage.lstStatusTable);
        int index = 0;
        for(WebElement status : lstStatus){
            String text = status.getText().trim();
            if (text.equalsIgnoreCase("Unresolved")){
                index = lstStatus.indexOf(status);
                break;
            }
        }
        List<WebElement> lstResolveBtn = getListElement(AR2WISEPage.lstResolveBtnTable);
        click(lstResolveBtn.get(0), "Clicking on [Resolve] button for the first entry in table");
        waitForElementDisappear(AR2WISEPage.loaderIcon, 30);
        lstStatus = getListElement(AR2WISEPage.lstStatusTable);
        String newStatus = lstStatus.get(index).getText().trim();
        Utility_Functions.xHighlight(ownDriver, lstStatus.get(index), "green");
        commonObj.validateContainsText("Resolved - "+date, newStatus, "Validating status after clicking on [Resolve] button");
    }

    /**
     * Keyword to validate Unresolve functionality in results table
     * i.e., click on red cross besides status and validate status
     */
    public void vrfyUnresolve() {
        List<WebElement> lstStatus = getListElement(AR2WISEPage.lstStatusTable);
        int index = 0;
        for(WebElement status : lstStatus){
            String text = status.getText().trim();
            if (text.contains("Resolved")){
                index = lstStatus.indexOf(status);
                break;
            }
        }
        List<WebElement> lstUnresolveBtn = getListElement(AR2WISEPage.lstUnresolveBtnTable);
        click(lstUnresolveBtn.get(0), "Clicking on [Unresolve] button for the first entry in table");
        waitForElementDisappear(AR2WISEPage.loaderIcon, 30);
        lstStatus = getListElement(AR2WISEPage.lstStatusTable);
        String newStatus = lstStatus.get(index).getText().trim();
        Utility_Functions.xHighlight(ownDriver, lstStatus.get(index), "green");
        Utility_Functions.xAssertEquals(report, "Unresolved", newStatus, "Validating status after clicking on [Unresolve] button");
        Utility_Functions.timeWait(5);
    }

    /**
     * Keyword to click on View button randomly from results table
     */
    public int clickRandomViewBtn() {
        List<WebElement> lstViewBtn = getListElement(AR2WISEPage.lstViewBtnTable);
        int random = Utility_Functions.xRandomFunction(0, lstViewBtn.size()-1);
        Utility_Functions.xHighlight(ownDriver, lstViewBtn.get(random), "green");
        Utility_Functions.xScrollIntoViewClck(ownDriver, lstViewBtn.get(random));

        switch (jsonData.getData("DocumentType")){
            case "Non-Trade Payment":
                commonObj.validateText(AR2WISEPage.hdrNTRWindow, "Non-Trade Payment", "Validating [Non-Trade Payment] pop-up header");
                break;
            case "Gross Margin Manager":
                commonObj.validateText(AR2WISEPage.hdrGMMWindow, "Gross Margin Manager", "Validating [Gross Margin Manager] pop-up header");
                break;
            case "Non-Trade Invoices":
                commonObj.validateText(AR2WISEPage.hdrNTIWindow, "Non-Trade Invoices", "Validating [Non-Trade Invoices] pop-up header");
                break;
            case "Inventory Adjustments":
                commonObj.validateText(AR2WISEPage.hdrInvAdjWindow, "Inventory Adjustments", "Validating [Inventory Adjustments] pop-up header");
                break;
        }
        return random;
    }

    /**
     * Keyword to click on View button for a record with [Resolved] or [Unresolved] status from results table
     */
    public int clickViewBtnToResolveUnresolve() {
        String desiredStatus = jsonData.getData("DesiredStatus");
        List<WebElement> lstStatus = getListElement(AR2WISEPage.lstStatusTable);
        int index = 0;
        for(WebElement status : lstStatus){
            String text = status.getText().trim();
            if (text.equalsIgnoreCase(desiredStatus)){
                index = lstStatus.indexOf(status);
                break;
            }
        }
        List<WebElement> lstViewBtn = getListElement(AR2WISEPage.lstViewBtnTable);
        Utility_Functions.xHighlight(ownDriver, lstViewBtn.get(index), "green");
        Utility_Functions.xScrollIntoViewClck(ownDriver, lstViewBtn.get(index));

        switch (jsonData.getData("DocumentType")){
            case "Non-Trade Payment":
                commonObj.validateText(AR2WISEPage.hdrNTRWindow, "Non-Trade Payment", "Validating [Non-Trade Payment] pop-up header");
                break;
            case "Gross Margin Manager":
                commonObj.validateText(AR2WISEPage.hdrGMMWindow, "Gross Margin Manager", "Validating [Gross Margin Manager] pop-up header");
                break;
            case "Non-Trade Invoices":
                commonObj.validateText(AR2WISEPage.hdrNTIWindow, "Non-Trade Invoices", "Validating [Non-Trade Invoices] pop-up header");
                break;
            case "Inventory Adjustments":
                commonObj.validateText(AR2WISEPage.hdrInvAdjWindow, "Inventory Adjustments", "Validating [Inventory Adjustments] pop-up header");
                break;
        }
        return index;
    }

    /**
     * Keyword to validate presence of fields and buttons in [NON-TRADE PAYMENT] pop-up
     */
    public void vrfyNTPPopup() {
        List<WebElement> lstFields = getListElement(AR2WISEPage.lstFieldsFirstSet);
        String[] expectedLabels = {"Reference #:", "Memo Type:", "Payment Type:", "Date:", "Resolved:", "Resolved By:"};
        for (int i=0; i<expectedLabels.length; i++){
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabels[i], actual, "Validating Fields in [NON-TRADE PAYMENT] popup");
        }
        lstFields = getListElement(AR2WISEPage.lstFieldsScndSet);
        String[] expectedLabelsx = {"Vendor Name:", "Accounting Period:", "Business Day:"};
        for (int i=0; i<expectedLabelsx.length; i++){
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabelsx[i], actual, "Validating Fields in [NON-TRADE PAYMENT] popup");
        }
        commonObj.validateElementExists(AR2WISEPage.btnCancelNTRWindow, "Validating presence of [Cancel] button");
        commonObj.validateElementExists(AR2WISEPage.btnResolveNTRWindow, "Validating presence of [Resolve] button");
    }

    /**
     * Keyword to validate presence of fields and buttons in [GROSS MARGIN MANAGER] pop-up
     */
    public void vrfyGMMRPopup() {
        List<WebElement> lstFields = getListElement(AR2WISEPage.lstGMMFieldsFirstSet);
        String[] expectedLabels = {"Invoice:", "Purchase Order ID:", "Reference #:", "Resolved:", "Resolved By:", "Date:"};
        for (int i=0; i<expectedLabels.length; i++){
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabels[i], actual, "Validating Fields in [GROSS MARGIN MANAGER] popup");
        }
        lstFields = getListElement(AR2WISEPage.lstGMMFieldsScndSet);
        String[] expectedLabelsx = {"Gross Margin Manager:", "B Formula Gross Margin:", "Recorded:", "Description:", "Explanation:"};
        for (int i=0; i<expectedLabelsx.length; i++){
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabelsx[i], actual, "Validating Fields in [GROSS MARGIN MANAGER] popup");
        }
        commonObj.validateElementExists(AR2WISEPage.btnCancelNTRWindow, "Validating presence of [Cancel] button");
        commonObj.validateElementExists(AR2WISEPage.btnResolveNTRWindow, "Validating presence of [Resolve] button");
    }

    /**
     * Keyword to validate presence of fields and buttons in [NON-TRADE INVOICES] pop-up
     */
    public void vrfyNTIPopup() {
        List<WebElement> lstFields = getListElement(AR2WISEPage.lstNTIFieldsFirstSet);
        String[] expectedLabels = {"Date:", "Resolved:", "Resolved By:"};
        for (int i=0; i<expectedLabels.length; i++){
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabels[i], actual, "Validating Fields in [NON-TRADE INVOICES] popup");
        }
        lstFields = getListElement(AR2WISEPage.lstNTIFieldsScndSet);
        String[] expectedLabelsx = {"Customer:", "Transaction Type:", "User ID:", "Invoice Number:", "Accounting Period:"};
        for (int i=0; i<expectedLabelsx.length; i++){
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabelsx[i], actual, "Validating Fields in [NON-TRADE INVOICES] popup");
        }
        commonObj.validateElementExists(AR2WISEPage.btnCancelNTRWindow, "Validating presence of [Cancel] button");
        commonObj.validateElementExists(AR2WISEPage.btnResolveNTRWindow, "Validating presence of [Resolve] button");
    }

    /**
     * Keyword to validate presence of fields and buttons in [INVENTORY ADJUSTMENTS] pop-up
     */
    public void vrfyInvAdjPopup() {
        List<WebElement> lstFields = getListElement(AR2WISEPage.lstInvAdjFieldsFirstSet);
        String[] expectedLabels = {"Item:", "User:", "Resolved:", "Resolved By:", "Date:"};
        for (int i=0; i<expectedLabels.length; i++){
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabels[i], actual, "Validating Fields in [INVENTORY ADJUSTMENTS] popup");
        }
        lstFields = getListElement(AR2WISEPage.lstInvAdjFieldsScndSet);
        String[] expectedLabelsx = {"Inventory Adjustment Quantity:", "Inventory Adjustment Cost:", "Recorded:", "Description:", "Reference:", "Adjustment Type:", "GL Account #:", "GL Account Description:"};
        for (int i=0; i<expectedLabelsx.length; i++){
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabelsx[i], actual, "Validating Fields in [INVENTORY ADJUSTMENTS] popup");
        }
        commonObj.validateElementExists(AR2WISEPage.btnCancelNTRWindow, "Validating presence of [Cancel] button");
        commonObj.validateElementExists(AR2WISEPage.btnResolveNTRWindow, "Validating presence of [Resolve] button");
    }

    /**
     * Keyword to validate presence of fields and buttons in [INVENTORY ADJUSTMENTS] pop-up for Misc. Inventory Adjustments Doc Type
     */
    public void vrfyMiscInvAdjPopup() {
        List<WebElement> lstFields = getListElement(AR2WISEPage.lstInvAdjFieldsFirstSet);
        String[] expectedLabels = {"Item:", "User:", "Resolved:", "Resolved By:", "Date:"};
        for (int i=0; i<expectedLabels.length; i++){
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabels[i], actual, "Validating Fields in [INVENTORY ADJUSTMENTS] popup");
        }
        lstFields = getListElement(AR2WISEPage.lstInvAdjFieldsScndSet);
        String[] expectedLabelsx = {"Inventory Adjustment Quantity:", "Inventory Adjustment Cost:", "Recorded:", "Description:", "Reference:", "Adjustment Type:", "Adjustment Subtype:", "GL Account #:", "GL Account Description:"};
        for (int i=0; i<expectedLabelsx.length; i++){
            String actual = lstFields.get(i).getText().trim();
            Utility_Functions.xAssertEquals(report, expectedLabelsx[i], actual, "Validating Fields in [INVENTORY ADJUSTMENTS] popup");
        }
        commonObj.validateElementExists(AR2WISEPage.btnCancelNTRWindow, "Validating presence of [Cancel] button");
        commonObj.validateElementExists(AR2WISEPage.btnResolveNTRWindow, "Validating presence of [Resolve] button");
    }

    /**
     * Keyword to click on Cancel button in [NON-TRADE PAYMENT] pop-up
     */
    public void closeNTPPopup() {
        click(AR2WISEPage.btnCancelNTRWindow, "Clicking on [Cancel] Button");
        waitForElementDisappear(AR2WISEPage.hdrNTRWindow, 5);
    }

    /**
     * Keyword to resolve a record from [NON-TRADE PAYMENT] pop-up
     */
    public void resolveRecordNTPPopup() {
        int index = clickViewBtnToResolveUnresolve();
        sendKeys(AR2WISEPage.tbxComments, jsonData.getData("Comments"), "Enter date in [Comments] textarea");
        click(AR2WISEPage.btnResolveNTRWindow, "Clicking on [Resolve] Button");
        waitForElementDisappear(AR2WISEPage.loaderIcon, 30);

        String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        List<WebElement> lstStatus = getListElement(AR2WISEPage.lstStatusTable);
        String newStatus = lstStatus.get(index).getText().trim();
        Utility_Functions.xHighlight(ownDriver, lstStatus.get(index), "green");
        commonObj.validateContainsText("Resolved - "+date, newStatus, "Validating status after clicking on [Resolve] button");
    }





}