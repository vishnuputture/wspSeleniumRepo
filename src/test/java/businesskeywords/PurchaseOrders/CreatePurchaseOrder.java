package businesskeywords.PurchaseOrders;

import businesskeywords.SalesOrders.SalesOrders;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Report;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.aspectj.weaver.bcel.Utility;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import pages.PurchaseOrders.*;
import pages.SalesOrders.SalesOrdersPage;
import pages.inventory.ItemMasterPage;
import pages.pricing.SpecialPricePage;
import pages.pricing.matrixcost.MatrixCostUpdatePage;
import pages.pricing.spa.CustomerGroupMaintenancePage;
import pages.pricing.spa.SpecialPriceAllowancePage;
import pages.PurchaseOrders.CostOptionspage;
import pages.PurchaseOrders.PurchaseOrderDetailsPage;
import pages.PurchaseOrders.PurchaseOrderEntryPage;
import pages.PurchaseOrders.VendorNotesPage;
import pages.common.MasterPage;
import supportLibraries.Utility_Functions;

import java.security.Key;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class CreatePurchaseOrder extends ReusableLib {
    CommonActions commonObj;
    public static String customeraddress1;
    public static String customeraddress2;

    public CreatePurchaseOrder(Helper helper) {

        super(helper);
        commonObj = new CommonActions(helper);
    }

    public void navigateFromPODetailsToMaster() {
        click(SpecialPriceAllowancePage.btnF11, "Click [F11 Header] Button");
        Utility_Functions.xScrollWindow(driver);
        click(SpecialPriceAllowancePage.btnExit, "Click Exit Button");
        click(SpecialPriceAllowancePage.btnExit, "Click Exit Button");
        click(SpecialPriceAllowancePage.btnExit, "Click Exit Button");
    }

    public void validatePOHeadingTitle() {

        commonObj.masterToPurchaseOrder();
        commonObj.navigateToPurchaseOrderEntry();

    }

    public void validatePOEntryUI() {

        commonObj.validateElementExists(PurchaseOrderEntryPage.orderNo, "Order Number  is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.customerNo, "Customer Number is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.vendorNo, "Vendor Number is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.typeShipment, "Type Shipment is present");
    }

    public void enterFreightCharges(){
        sendKeys(PurchaseOrderEntryPage.enterFreightCharges, "FFA"+Keys.ENTER, "Entered FFA Frieght Code");
    }

    public void getVendor() {
        click(PurchaseOrderEntryPage.vendorNo, "Click on Vendor Number");
        sendKeys(PurchaseOrderEntryPage.firstVendor, "1" + Keys.ENTER, "Selecting the First Vendor in the search");
        sendKeys(PurchaseOrderEntryPage.enterFreightCharges, "FFA", "Entered FFA Frieght Code");
    }

    public void findVendor() {
        click(PurchaseOrderEntryPage.vendorNo, "Click on Vendor Number");
        sendKeys(PurchaseOrderEntryPage.firstVendor, "1" + Keys.ENTER, "Selecting the First Vendor in the search");
        sendKeys(PurchaseOrderEntryPage.enterFreightCharges, "FFA" + Keys.ENTER, "Entered FFA Frieght Code");

    }

    public void findVendorWithoutFreightCharges() {
        click(PurchaseOrderEntryPage.vendorNo, "Click on Vendor Number");
        sendKeys(PurchaseOrderEntryPage.firstVendor, "1" + Keys.ENTER, "Selecting the First Vendor in the search");
    }


    public void validatePODetailsUI() {

        commonObj.validateElementExists(PurchaseOrderDetailsPage.action1, "Action is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.ediStat, "EDI Stat  is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.orderno, "Order No is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.lineNo, "Line No is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.vendor, "Vendor is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.shipVia, "Ship Via is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.customerPOD, "Customer is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.quantityOrdered, "Quantity Ordered is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.quantityOpen, "Quantity Opened is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.itemNumberPOD, "Item Number is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.pricePOD, "Price is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.umPOD, "UM is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.disc, "Disc is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.extendedAmount, "Extended Amount is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.descPOD, "Desc is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.line2, "Line 2 is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.vendorPart, "Vendor Part is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.relatedSo, "Related SO is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.datePOD, "Date  is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.weightPOD, "Weight is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.pkgQty, "Pkg Qty is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.convFactor, "Conv Factor is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.sendViaFax, "Send Via Fax is present");
        commonObj.validateElementExists(PurchaseOrderDetailsPage.tyPOD, "TY  is present");

    }

    public void noVendorNoOrderError(By ele, String errMsg) {
        clearText(PurchaseOrderEntryPage.orderNoInput);
        clearText(PurchaseOrderEntryPage.vendorNoInput);
        sendKeys(PurchaseOrderEntryPage.vendorNoInput, String.valueOf(Keys.ENTER), "Entering blank Vendor no & Ordder no");
        Utility_Functions.timeWait(5);
        commonObj.validateText(ele, errMsg, "Error message: " + errMsg + "");
    }

    public void alphNumVendorNoError(By ele, String errMsg) {
        clearText(PurchaseOrderEntryPage.orderNoInput);
        clearText(PurchaseOrderEntryPage.vendorNoInput);
        sendKeys(PurchaseOrderEntryPage.vendorNoInput, "!@#$%^&*()123" + String.valueOf(Keys.ENTER), "Entering alphanumeric Vendor No having special characters");
        Utility_Functions.timeWait(5);
        commonObj.validateText(ele, errMsg, "Error message: " + errMsg + "");
    }

    public void validateVendorNo() {
        noVendorNoOrderError(PurchaseOrderEntryPage.errorMsgPO, "ERROR - Vendor or Order Number cannot be blank");
        alphNumVendorNoError(PurchaseOrderEntryPage.errorMsgPO, "ERROR - Vendor or Customer Number was not found");
    }

    public void invalidOrderNoErrror(By ele, String errMsg) {
        sendKeys(PurchaseOrderEntryPage.vendorNoInput, "000");
        sendKeys(PurchaseOrderEntryPage.customerNoInput, "000");
        sendKeys(PurchaseOrderEntryPage.orderNoInput, "000" + String.valueOf(Keys.ENTER), "Entering invalid Order No ");
        Utility_Functions.timeWait(5);
        commonObj.validateText(ele, errMsg, "Error message: " + errMsg + "");
    }

    public void alphaNumOrderNoError(By ele, String errMsg) {
        sendKeys(PurchaseOrderEntryPage.vendorNoInput, "000");
        sendKeys(PurchaseOrderEntryPage.customerNoInput, "000");
        sendKeys(PurchaseOrderEntryPage.orderNoInput, "!@#$%^&*()123" + String.valueOf(Keys.ENTER), "Entering Alphanumeric  Order No with special characters ");
        Utility_Functions.timeWait(5);
        commonObj.validateText(ele, errMsg, "Error message: " + errMsg + "");
    }


    public void validateOrderNo() {
        invalidOrderNoErrror(PurchaseOrderEntryPage.errorMsgPO, "ERROR - Vendor or Customer Number was not found");
        alphaNumOrderNoError(PurchaseOrderEntryPage.errorMsgPO, "ERROR - Vendor or Customer Number was not found");
    }

    public void addVendornotes() {
        commonObj.navigateToVendorNotes();
        sendKeys(VendorNotesPage.vendorInputVendorNotes, jsonData.getData("validVendorNo") + Keys.ENTER, "Searching with Vendor No");
        Utility_Functions.timeWait(5);
        sendKeys(VendorNotesPage.actionVendorNotes, "A", "Changing Action to A ");
        Utility_Functions.timeWait(3);
        sendKeys(VendorNotesPage.Line1VendorNotes, jsonData.getData("vendorNotes1") + Keys.RETURN, "Entering VendorNotes in Line1");
        Utility_Functions.timeWait(8);
        // commonObj.validateText(VendorNotesPage.actionVendorNotes,"i","Action Code changed to I");
        commonObj.validateText(VendorNotesPage.erroMsgVendorNotes, "V" + jsonData.getData("validVendorNo") + " - Vendor Notes Changed.", "Vendor notes changed message");

    }

    public void deleteVendornotes() {
        sendKeys(VendorNotesPage.vendorInputVendorNotes, jsonData.getData("validVendorNo") + Keys.ENTER, "Searching with Vendor No");
        Utility_Functions.timeWait(5);
        sendKeys(VendorNotesPage.actionVendorNotes, "D" + Keys.ENTER, "Changing Action to D ");
        Utility_Functions.timeWait(5);
        commonObj.validateText(VendorNotesPage.erroMsgVendorNotes, "V" + jsonData.getData("validVendorNo") + " - All Vendor Notes Will be Deleted.  Press Enter to Delete.",
                "Vendor notes deleted");
        sendKeys(VendorNotesPage.vendorInputVendorNotes, String.valueOf(Keys.ENTER));
        Utility_Functions.timeWait(5);
        commonObj.validateText(VendorNotesPage.vendorInputVendorNotes, "", "Verify Line1 Vendor note is deleted and blank");
    }

    public void applyCostPriceAndDisc(String costoption, String quantity, String discount) {
        click(PurchaseOrderEntryPage.vendorNo, "Click on Vendor Number");
        sendKeys(PurchaseOrderEntryPage.firstVendor, "1" + Keys.ENTER, "Selecting the First Vendor in the search");
        sendKeys(PurchaseOrderEntryPage.enterFreightCharges, "FFA", "Entered FFA Frieght Code");
        //selectListPriceCost();
        sendKeys(PurchaseOrderEntryPage.costOption, costoption + Keys.ENTER, "Entered Cost Price Option ");
        sendKeys(PurchaseOrderDetailsPage.itemNumberPOD, String.valueOf(Keys.F4), "F4 Prompt on Item Number");
        sendKeys(PurchaseOrderDetailsPage.firstItemNumber, "1" + Keys.ENTER, "Selecting First Item Number");
        sendKeys(PurchaseOrderDetailsPage.quantityOrdered, quantity + Keys.ENTER, "Entered  Quantity Order");
        Utility_Functions.timeWait(5);
        sendKeys(PurchaseOrderDetailsPage.disc, discount + Keys.ENTER, "Entered Disc");
        Utility_Functions.timeWait(5);

        if (Double.parseDouble(discount) > 0) {

            double extAmtCalc = Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.quantityOrdered).getAttribute("value").trim())
                    * Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.pricePOD).getAttribute("value").trim())
                    * (1 - (Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.disc).getAttribute("value").trim())) / 100);
            Utility_Functions.timeWait(5);
            double avgPrice = Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.pricePOD).getAttribute("value").trim());
            String formattedavgPrice = String.format("%.4f", avgPrice);

            commonObj.validateText(PurchaseOrderDetailsPage.errorMsgPOD, "WARNING- Cost variance -- F5 to accept.  Average cost is     " +
                    formattedavgPrice, "Verifying Cost variance validation");
            String extendedAmt = String.valueOf(Math.abs(Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.getExtendedAmountPOD).getAttribute("value").trim())));


            Utility_Functions.xAssertEquals(report, String.valueOf(extAmtCalc), extendedAmt);
        } else {
            double extAmtCalc = Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.quantityOrdered).getAttribute("value").trim())
                    * Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.pricePOD).getAttribute("value").trim());
            Utility_Functions.timeWait(5);
            double avgPrice = Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.pricePOD).getAttribute("value").trim());
            String formattedavgPrice = String.format("%.4f", avgPrice);

            String extendedAmt = String.valueOf(Math.abs(Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.getExtendedAmountPOD).getAttribute("value").trim())));


            Utility_Functions.xAssertEquals(report, String.valueOf(extAmtCalc), extendedAmt);

        }
    }

    public void addPOWithListPriceAndDisc() {
        applyCostPriceAndDisc(jsonData.getData("listCostOption"), jsonData.getData("quantityOrdered"),
                jsonData.getData("discount"));


    }

    public void addPOWithLastPriceAndDisc() {
        applyCostPriceAndDisc(jsonData.getData("lastCostOption"), jsonData.getData("quantityOrdered"),
                jsonData.getData("discount"));
        sendKeys(PurchaseOrderDetailsPage.extendedAmount, String.valueOf(Keys.F5));
        Utility_Functions.timeWait(5);
    }

    public void addPOWithLastPriceWithoutDisc() {
        sendKeys(PurchaseOrderDetailsPage.itemNumberPOD, String.valueOf(Keys.F4), "F4 Prompt on Item Number");
        sendKeys(PurchaseOrderDetailsPage.secondItemNumber, "1" + Keys.ENTER, "Selecting First Item Number");
        Utility_Functions.timeWait(5);
        sendKeys(PurchaseOrderDetailsPage.quantityOrdered, jsonData.getData("secondQuantityOrdered") + Keys.ENTER, "Entered  Quantity Order");
        Utility_Functions.timeWait(5);
        sendKeys(PurchaseOrderDetailsPage.extendedAmount, String.valueOf(Keys.ENTER));
        Utility_Functions.timeWait(5);

        Utility_Functions.xWaitForElementPresent(driver, driver.findElements(PurchaseOrderDetailsPage.getLineItemsList), 5);

        List<WebElement> items = driver.findElements(PurchaseOrderDetailsPage.getLineItemsList);

        double itemsAmt = 0;
        for (WebElement e : items) {
            String itemValues[] = (e.getAttribute("outerText")).trim().split("[^A-Za-z0-9.]+");

            String formattedPrice = String.format("%.2f", Double.parseDouble(itemValues[itemValues.length - 2]));
            itemsAmt += Double.parseDouble(formattedPrice);
        }
        Utility_Functions.timeWait(5);
        Utility_Functions.getText(driver, PurchaseOrderDetailsPage.amountHeader);
        String totalAmtHeader = Utility_Functions.getText(driver, PurchaseOrderDetailsPage.amountHeader).trim();
        Utility_Functions.xAssertEquals(report, String.format("%.2f", itemsAmt), totalAmtHeader);
    }


    public void addDuplicateLineItem() {
        click(PurchaseOrderEntryPage.vendorNo, "Click on Vendor Number");
        sendKeys(PurchaseOrderEntryPage.firstVendor, "1" + Keys.ENTER, "Selecting the First Vendor in the search");
        sendKeys(PurchaseOrderEntryPage.enterFreightCharges, "FFA", "Entered FFA Frieght Code");
        sendKeys(PurchaseOrderEntryPage.costOption, jsonData.getData("lastCostOption") + Keys.ENTER, "Entered Cost Price Option ");
        Utility_Functions.timeWait(5);
        addPOWithLastPriceWithoutDisc();
        Utility_Functions.timeWait(5);
        addPOWithLastPriceWithoutDisc();

    }

    public void enquireOrderNo() {
        sendKeys(PurchaseOrderEntryPage.actionInpput, "I", "Changing Action to I");
        sendKeys(PurchaseOrderEntryPage.orderNoInput, jsonData.getData("orderNo") + Keys.ENTER, "Enter Order Order");
        Utility_Functions.timeWait(5);
    }

    public void enquirePOrderNo() {
        sendKeys(PurchaseOrderEntryPage.actionInpput, "I", "Changing Action to I");
        sendKeys(PurchaseOrderEntryPage.orderNoInput, Utility_Functions.xGetJsonData("PONumber") + Keys.ENTER, "Enter Order Order");
        Utility_Functions.timeWait(5);
    }

    public void typeShipmentD() {
        sendKeys(PurchaseOrderEntryPage.actionInpput, "D", "Changing Action to D");
        sendKeys(PurchaseOrderEntryPage.orderNoInput, Utility_Functions.xGetJsonData("PONumber") + Keys.ENTER, "Enter Order Order");
        Utility_Functions.timeWait(5);
    }

    public void enter() {
        Utility_Functions.actionKey(Keys.ENTER, driver);
    }

    public void validateCostOptionFunc() {
        click(PurchaseOrderEntryPage.costOptionLink);
        Utility_Functions.timeWait(5);
        commonObj.validateText(CostOptionspage.costOptionHeader, "Cost Option Codes", "Validate Cost Option Pop-up");

        sendKeys(CostOptionspage.postionTo, jsonData.getData("invalidCostOption") + Keys.ENTER, "Enter invalid Cost Code");
        Utility_Functions.timeWait(5);
        commonObj.validateText(CostOptionspage.erroMsg, "No Code entries to display", "Validate Incorrect CostOption");

        clearText(CostOptionspage.postionTo);
        sendKeys(CostOptionspage.postionTo, jsonData.getData("invalidCostOptionSpecialChar") + Keys.ENTER, "Enter invalid Cost Code with special Characters");
        Utility_Functions.timeWait(5);
        commonObj.validateText(CostOptionspage.erroMsg, "No Code entries to display", "Validate Incorrect CostOption");
        Utility_Functions.timeWait(5);
        clearText(CostOptionspage.postionTo);
        Utility_Functions.timeWait(5);
        sendKeys(CostOptionspage.postionTo, jsonData.getData("validCostOption") + Keys.ENTER, "Enter valid Cost Code with special Characters");
        Utility_Functions.timeWait(5);
        sendKeys(CostOptionspage.codeSelectinput, "1" + Keys.ENTER, "E");
        Utility_Functions.timeWait(5);
        commonObj.validateText(PurchaseOrderEntryPage.poHeaderTitle, "Purchase Order Headings", "Validating Entry - Purchase Order");

    }

    public void selectFirstCustomer() {
        clearText(PurchaseOrderEntryPage.customerNoInput);
        clearText(PurchaseOrderEntryPage.orderNoInput);
        click(PurchaseOrderEntryPage.customerNo);
        Utility_Functions.timeWait(5);

        customeraddress1 = Utility_Functions.getText(driver, MailingMasterSearchPage.firstaddressLine1, "innerText").toLowerCase().replace(" ", "").trim();
        customeraddress2 = Utility_Functions.getText(driver, MailingMasterSearchPage.firstaddressLine2, "innerText").toLowerCase().replace(" ", "").trim();
        sendKeys(MailingMasterSearchPage.firstCustomerSelect, "1" + Keys.ENTER, "Select Customer No");
        Utility_Functions.timeWait(5);

        commonObj.validateText(PurchaseOrderEntryPage.errorMsgPO, "ERROR - Vendor or Order Number cannot be blank", " Validate Vendor Or Order blank after Customer selection");

        Utility_Functions.timeWait(2);
        Assert.assertNotNull(driver.findElement(PurchaseOrderEntryPage.orderNoInput).getAttribute("value"), "Validate Order No is auto populated");

    }

    public void addPOWithPOPriceAndDisc() {
        applyCostPriceAndDisc(jsonData.getData("POCostOption"), jsonData.getData("quantityOrdered"),
                jsonData.getData("discount"));
        sendKeys(PurchaseOrderDetailsPage.extendedAmount, String.valueOf(Keys.F5));
        Utility_Functions.timeWait(5);
        Utility_Functions.xWaitForElementPresent(driver, driver.findElements(PurchaseOrderDetailsPage.getLineItemsList), 5);

        Utility_Functions.xIsElementDisplayed(report, driver.findElement(PurchaseOrderDetailsPage.getLineItemsList), "Added Line Item displayed");


    }

    public void validateShipmentType() {
        System.out.println("Customer Page Address One=" + CreatePurchaseOrder.customeraddress1);
        System.out.println("Customer Page Address Two=" + CreatePurchaseOrder.customeraddress2);
        String shipto = Utility_Functions.getText(driver, PurchaseOrderEntryPage.shipToinput, "value").replace(" ", "").toLowerCase().trim();
        String toaddress = Utility_Functions.getText(driver, PurchaseOrderEntryPage.toaddressLine1, "value").replace(" ", "").toLowerCase().trim();

        System.out.println("PO Order Ship Address1=" + shipto);
        System.out.println("PO Order To Address1=" + toaddress);

        //Assert.assertTrue(CreatePurchaseOrder.customeraddress1.contains(shipto));
        //Assert.assertTrue(CreatePurchaseOrder.customeraddress2.contains(toaddress));
        Utility_Functions.xAssertEquals(report, CreatePurchaseOrder.customeraddress1, shipto);
        Utility_Functions.xAssertEquals(report, CreatePurchaseOrder.customeraddress2, toaddress);

    }

    public void addPOWithListPriceWithoutDisc() {
        applyCostPriceAndDisc(jsonData.getData("listCostOption"), jsonData.getData("quantityOrdered"), jsonData.getData("nodiscount"));
        sendKeys(PurchaseOrderDetailsPage.extendedAmount, String.valueOf(Keys.ENTER));
        Utility_Functions.timeWait(8);
        System.out.println("Validation Msg" + Utility_Functions.getText(driver.findElement(PurchaseOrderDetailsPage.errorMsgPOD), "outerText"));

        //    commonObj.validateText(PurchaseOrderDetailsPage.errorMsgPOD," ERROR - Discount can NOT be blank when using list price F4 Overrides","No Discount validation");
        String message = Utility_Functions.getText(driver.findElement(PurchaseOrderDetailsPage.errorMsgPOD), "outerText").replace(" ", " ").trim();
        Utility_Functions.xAssertEquals(report, "ERROR - Discount can NOT be blank when using list price F4 Overrides", message,
                "No Discount validation");
    }

    public void validateFrieghtCharges() {
        click(PurchaseOrderEntryPage.freightCharges, "Click on Frieght Charges Hyperlink");
        Utility_Functions.timeWait(5);

        sendKeys(FrieghtChargesPage.positionTo, jsonData.getData("invalidFrieght") + Keys.ENTER, "Enter Invalid Frieght Code");
        Utility_Functions.timeWait(5);
        commonObj.validateText(FrieghtChargesPage.errMsg, "No Code entries to display", "Validate FriegntCharges message for invalid code");
        clearText(FrieghtChargesPage.positionTo);

        Utility_Functions.timeWait(5);

        sendKeys(FrieghtChargesPage.positionTo, jsonData.getData("invalidFrieghtSpecialChar") + Keys.ENTER, "Enter Invalid Frieght Code with Special character");
        Utility_Functions.timeWait(5);
        commonObj.validateText(FrieghtChargesPage.errMsg, "No Code entries to display", "Validate FriegntCharges message for invalid code with Special Characters");

        clearText(FrieghtChargesPage.positionTo);
        sendKeys(FrieghtChargesPage.positionTo, jsonData.getData("validFrieght") + Keys.ENTER, "Enter Invalid Frieght Code with Special character");
        Utility_Functions.timeWait(5);

        sendKeys(FrieghtChargesPage.selectfirstCode, "1" + Keys.ENTER, "Selecting First Code FFA");
        Utility_Functions.timeWait(5);


        Utility_Functions.xAssertEquals(report, jsonData.getData("validFrieght"), driver.findElement(PurchaseOrderEntryPage.enterFreightCharges),
                "value", "Validate selected Frieght FFA");


    }

    /**
     * Keyword to enter [Qty] and [Item Number] in Purchase Order Details page
     */
    public void enterOrderedItemNumber() {
        String itemNo = Utility_Functions.xGetJsonAsString("ItemNoMaster");
        sendKeysAndEnter(PurchaseOrderDetailsPage.quantityOrdered, jsonData.getData("QtyOrdered"), "Enter Quantity Ordered");
        sendKeysAndEnter(PurchaseOrderDetailsPage.itemNumberPOD, itemNo, "Enter Item Number as[" + itemNo + "]");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to enter [Qty] and [Item Number] in Purchase Order Details page
     */
    public void enterItemNumberAndQty() {
        String itemNo = jsonData.getData("itemNo");
        String qty = jsonData.getData("QtyOrdered");
        sendKeysAndEnter(PurchaseOrderDetailsPage.quantityOrdered, qty, "Enter Quantity Ordered");
        sendKeysAndEnter(PurchaseOrderDetailsPage.itemNumberPOD, itemNo, "Enter Item Number as[" + itemNo + "]");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to select random [Item Number] and enter [Qty] in Purchase Order Details page
     */
    public void findOrderAndEnterQty() {
        click(PurchaseOrderDetailsPage.itemNumberPOD);
        click(PurchaseOrderDetailsPage.f4);

        String itemNumber = commonObj.selectRandomItemNumber();
        String itemNumberSelected = getAttribute(PurchaseOrderDetailsPage.itemNumberPOD, "value");
        Utility_Functions.xAssertEquals(report, itemNumber, itemNumberSelected, "Selected Item Number is displayed");

        sendKeysAndEnter(PurchaseOrderDetailsPage.quantityOrdered, jsonData.getData("QtyOrdered"), "Enter Quantity Ordered");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    /**
     * Keyword to verify Item List Price in Purchase Order Details page
     */
    public void vrfyItemListPrice() {
        String priceExpected = Utility_Functions.xGetJsonAsString("ListPrice").trim();
        String priceActual = getAttribute(PurchaseOrderDetailsPage.pricePOD, "value").trim();
        Utility_Functions.xAssertEquals(report, priceExpected, priceActual, "Validating [Price] in Purchase Order Details page");
    }

    /**
     * Keyword to verify Item PO Price in Purchase Order Details page
     */
    public void vrfyItemPOPrice() {
        String priceExpected = Utility_Functions.xGetJsonAsString("POCost").trim();
        String priceActual = getAttribute(PurchaseOrderDetailsPage.pricePOD, "value").trim();
        Utility_Functions.xAssertEquals(report, priceExpected, priceActual, "Validating [Price] in Purchase Order Details page");
    }

    /**
     * Keyword to verify error in Purchase Order Details page
     */
    public void verifyError(String expected) {
        Utility_Functions.xScrollIntoView(driver, PurchaseOrderDetailsPage.errorMsgPOD);
        String actual = getText(PurchaseOrderDetailsPage.errorMsgPOD).trim();
        Utility_Functions.xAssertEquals(report, expected, actual, "Validating error message in Purchase Order Details page");
    }

    /**
     * Keyword to Press Enter and verify Sales Order error in Purchase Order Details page
     */
    public void verifySalesOrderError() {
        String error1 = jsonData.getData("Error1");
        String error2 = jsonData.getData("Error2");

        click(PurchaseOrderDetailsPage.btnSubmit, "Clicked Submit button");
        verifyError(error1);

        sendKeysAndEnter(PurchaseOrderDetailsPage.relatedSo, "879818", "Enter random value in Related SO tbx");
        click(PurchaseOrderDetailsPage.btnSubmit, "Clicked Submit button");
        verifyError(error2);
    }

    /**
     * Keyword to Press Enter and verify Sales Order error in Purchase Order Details page
     */
    public void vrfyCostVarianceError() {
        click(PurchaseOrderDetailsPage.btnSubmit, "Clicked Submit button");
        Utility_Functions.timeWait(5);
        String error = getText(PurchaseOrderDetailsPage.errorMsgPOD);
        if (error.contains("F8 to accept")) {
            Utility_Functions.actionKey(Keys.F8, driver);
        }
        error = getText(PurchaseOrderDetailsPage.errorMsgPOD);
        if (error.contains("WARNING- Price field is zero or blank")) {
            sendKeysAndEnter(PurchaseOrderDetailsPage.pricePOD, "100", "Enter value in Price tbx");
            waitForElementDisappear(MasterPage.loadingAnime, globalWait);
            Utility_Functions.timeWait(2);
        }
        error = getText(PurchaseOrderDetailsPage.errorMsgPOD);
        if (error.contains("ERROR - Multiplier can NOT be 0")) {
            sendKeysAndEnter(PurchaseOrderDetailsPage.tbxMultiplier, "1", "Enter value in Multiplier tbx");
            waitForElementDisappear(MasterPage.loadingAnime, globalWait);
            Utility_Functions.timeWait(3);
        }
        error = getText(PurchaseOrderDetailsPage.errorMsgPOD);
        if (error.contains("F4 Overrides")) {
            Utility_Functions.actionKey(Keys.F4, driver);
        }
        try {
            Utility_Functions.xScrollIntoView(driver, PurchaseOrderDetailsPage.errorMsgPOD);
            if(getText(PurchaseOrderDetailsPage.errorMsgPOD).equals("WARNING- Cost variance -- F5 to accept.  Average cost is")) {
                commonObj.verifyElementContainsText(PurchaseOrderDetailsPage.errorMsgPOD, "WARNING- Cost variance -- F5 to accept.  Average cost is", "Verify Cost Variance Warning message");
                getConvFactorValue();
                Utility_Functions.actionKey(Keys.F5, driver);
            }else {
                throw new NoSuchElementException("Not found");
            }
        }catch (Exception e){
        }
    }

    /**
     * Keyword to get Conv Factor value from Purchase Order Details page
     */
    public void getConvFactorValue() {
        String convFactor = getText(PurchaseOrderDetailsPage.convFactorValue).trim();
        jsonData.putData("ConvFactor", convFactor);
    }

    /**
     * Keyword to get Conv Factor value from Purchase Order Details page
     */
    public void getOrderNumberValue() {
        String orderNo = getText(PurchaseOrderDetailsPage.orderNo2).trim();
        jsonData.putData("PONumber", orderNo);
    }

    /**
     * Keyword to enter [Relates SO] in Purchase Order Details page
     */
    public void enterRelatedSO() {
        String relatedSO = jsonData.getData("SalesOrderNo");
        sendKeysAndEnter(PurchaseOrderDetailsPage.relatedSo, relatedSO, "Enter Related SO");
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
    }

    public void navigateToCreateSo() {
        sendKeys(PurchaseOrderEntryPage.enterFreightCharges, "FFA", "Entered FFA Frieght Code");
        click(PurchaseOrderDetailsPage.createSo, "Click Create SO");
        commonObj.validateText(PurchaseOrderEntryPage.poEntryHeader, "Purchase Order Entry", "[  Purchase Order Entry] header is present");
    }

    public void directPoCreateSoError() {
        sendKeys(PurchaseOrderEntryPage.enterFreightCharges, "FFA", "Entered FFA Frieght Code");
        click(PurchaseOrderDetailsPage.createSo, "Click Create SO");
        commonObj.validateText(SpecialPricePage.papMessage, "You must have a Related Sales Order # to process this Direct Ship.", "[You must have a Related Sales Order # to process this Direct Ship.] Error message is present");
    }

    public void verifyOrderNo(){
        String orderNumber = getAttribute(PurchaseOrderEntryPage.tbxOrderNumber, "value");
        click(SpecialPricePage.btnF3, "Click Exit Link");
        String[] lastOrderNo=getText(PurchaseOrderEntryPage.lastOrderNo).split("--> ");
        Utility_Functions.xAssertEquals(report,orderNumber,lastOrderNo[1],"");
    }

    public void typeShipFields() {
        commonObj.validateText(PurchaseOrderEntryPage.typeShip, "Type Shipment", "[Type Shipment] sub Header is present");
        commonObj.validateText(PurchaseOrderEntryPage.orderNoField, "Order No.", "[Order No.] field is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.orderNum, "Order number field is present");
        commonObj.validateText(PurchaseOrderEntryPage.CustNo, "Cust. No.", "[Cust No.] field is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.CustNoTxtBx, "[Cust No.] Text Box is present");
        commonObj.validateText(PurchaseOrderEntryPage.shipToTxt, "Ship-To#", "[Ship-To#] field is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.shipToTxtBx, "Ship To Text Box is present");

        commonObj.validateText(PurchaseOrderEntryPage.stateField, "State", "[Sate] field is present for Customer No.");
        commonObj.validateElementExists(PurchaseOrderEntryPage.zipField, "[Zip] Text Box is present for Customer No.");
        commonObj.validateText(PurchaseOrderEntryPage.shipToState, "State", "[State] field is present");
        commonObj.validateText(PurchaseOrderEntryPage.shipToZip, "Zip", "[Zip] field is present");
    }

    public void ordrInfoFields() {
        commonObj.validateElementExists(PurchaseOrderEntryPage.orderInf, "[ O R D E R   I N F O R M A T I O N.] sub Header is present");
        commonObj.validateText(PurchaseOrderEntryPage.salesOdrNo, "Sales Order No.  .", "[Sales Order No.  .] field is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.custOrdNo, "Customer Order number field is present");
        commonObj.validateText(PurchaseOrderEntryPage.CustNo, "Cust. No.", "[Cust No.] field is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.paymentTerm, "[Payment Temp] field is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.salesOrderNo, "[Sales Order] is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.custOrdNoTxtBx, "[Customer Order No.] Text Box is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.payTermBx, "[Payment Terms] Text Box is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.jobNameBx, "[Job Name] Text Box is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.DateReqBx, "[Date Requested] Text Box is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.shipViaBx, "[Ship Vis] Text Box is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.salesPersonBx, "[Sales Person] Text Box is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.terrCodeBx, "[Terr. Code.] Text Box is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.prepaidBx, "[Prepaid/Collect] Text Box is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.dateShipBx, "[Date Shipped] Text Box is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.BusinessDayBx, "[Business Day] Text Box is present");
    }

    public void OrderInformUi() {
        commonObj.validateElementExists(PurchaseOrderEntryPage.createInNewBx, "[Create In New SOE] Text Box is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.freightZoneBx, "[Freight Zone] Text Box is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.PickUpDelvBx, "[Pick-Up/Delivery] Text Box is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.FOBBx, "[F.B.O.] Text Box is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.PickingSlipBx, "[Packing Slip] Text Box is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.writtenBy, "[Written By] Text Box is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.placedBy, "[Placed By] Text Box is present");
    }

    public void verifyPoEntryUi() {
        typeShipFields();
        ordrInfoFields();
        OrderInformUi();
    }

    public void exitPoEntry() {
        click(SpecialPricePage.btnF3, "Click Exit Link");
        click(SpecialPricePage.btnF3, "Click Exit Link");
        click(SpecialPricePage.btnF3, "Click Exit Link");
    }

    public void selectCustNo() {
        click(PurchaseOrderEntryPage.CustNoTxtBx, "Hover on Customer No");
        click(PurchaseOrderEntryPage.custBrowse, "Click [F1-Cust. Browse]");
        String address = getText(PurchaseOrderEntryPage.addressCust);
        Utility_Functions.xUpdateJson("Cust_Address", address.trim());
        String[] stateZip = getText(PurchaseOrderEntryPage.stateZip).split(" ");
        Utility_Functions.xUpdateJson("city", stateZip[1].replace(",", "").trim());
        Utility_Functions.xUpdateJson("ZipCode", stateZip[3].trim());
        Utility_Functions.xUpdateJson("State", stateZip[2]);
        String sateZipCode = stateZip[3].replace("-", "");
        sendKeys(PurchaseOrderEntryPage.firstVendor, "1" + Keys.ENTER, "Select Customer No");
        Utility_Functions.timeWait(4);
        Utility_Functions.xScrollIntoView(driver, PurchaseOrderEntryPage.vendorNo);
        commonObj.validateText(PurchaseOrderEntryPage.vendorNo, address.trim(), "Customer Address is present");
        commonObj.validateText(PurchaseOrderEntryPage.stateCode, stateZip[2].trim(), "State: " + stateZip[2] + " is present");
        commonObj.validateText(PurchaseOrderEntryPage.zipCode, sateZipCode.trim(), "Zip: " + sateZipCode + " is present");
    }

    public void selectShipVia() {
        click(PurchaseOrderEntryPage.shipViaBx, "Click Ship Via Text Box");
        click(PurchaseOrderEntryPage.shipViaBrowse, "Click [F4-ShipVia Browse]");
        String shipTypeC = getText(PurchaseOrderEntryPage.shipTypeCode);
        sendKeys(PurchaseOrderEntryPage.shipViaSel, "1" + Keys.ENTER, "Select [Ship Type]");
        Utility_Functions.timeWait(4);
        /*commonObj.validateText(PurchaseOrderEntryPage.shipViaBx, shipTypeC.trim(), "ship Via: " + shipTypeC + " is present");
        commonObj.validateText(PurchaseOrderEntryPage.writtenBy, properties.getProperty("STGUserName"), properties.getProperty("STGUserName") + "Witten by is present");*/
    }

    public void processSo() {
        Utility_Functions.actionKey(Keys.F9, driver);
        try {
            if (isDisplayed(SalesOrdersPage.continuebtn)) {
                click(SalesOrdersPage.continuebtn);
                Utility_Functions.timeWait(2);
            }
        } catch (Exception e) {
        }
        String so = getText(PurchaseOrderEntryPage.createdSO);
        Utility_Functions.xUpdateJson("SalesOrder_POEntry", so);
        enter();
    }

    public void billingInfo() {
        String billAccount = getAttribute(SalesOrdersPage.billToAcct, "value");
        String address = getAttribute(SalesOrdersPage.billName, "value");
        String billCity = getText(SalesOrdersPage.billCity);
        String billState = getText(SalesOrdersPage.billState);
        String billZipCode = getText(SalesOrdersPage.billZipCode);
        Utility_Functions.xAssertEquals(report, billAccount, "099599", "");
        Utility_Functions.xAssertEquals(report, address, Utility_Functions.xGetJsonData("Cust_Address"), "");
        Utility_Functions.xAssertEquals(report, billCity, Utility_Functions.xGetJsonData("city"), "");
        Utility_Functions.xAssertEquals(report, billState, Utility_Functions.xGetJsonData("State"), "");
        Utility_Functions.xAssertEquals(report, billZipCode, Utility_Functions.xGetJsonData("ZipCode"), "");
    }

    public void shippingInfo() {
        String billAccount = getAttribute(SalesOrdersPage.inShipToAcct, "value");
        String address = getAttribute(SalesOrdersPage.outSaveCustName, "value");
        String billCity = getAttribute(SalesOrdersPage.outShipToCity, "value");
        String billState = getAttribute(SalesOrdersPage.outShipToState, "value");
        String billZipCode = getAttribute(SalesOrdersPage.outShipToZipCode, "value");
        Utility_Functions.xAssertEquals(report, billAccount, "099599", "");
        Utility_Functions.xAssertEquals(report, address, Utility_Functions.xGetJsonData("Cust_Address"), "");
        Utility_Functions.xAssertEquals(report, billCity, Utility_Functions.xGetJsonData("city"), "");
        Utility_Functions.xAssertEquals(report, billState, Utility_Functions.xGetJsonData("State"), "");
        Utility_Functions.xAssertEquals(report, billZipCode, Utility_Functions.xGetJsonData("ZipCode"), "");
    }

    public void verifySalesOrderEntryFields() {
        sendKeys(SalesOrdersPage.salesOrderField,Utility_Functions.xGetJsonData("SalesOrder_POEntry")+Keys.ENTER,"Enter Sale Order");
        Utility_Functions.timeWait(4);
        billingInfo();
        shippingInfo();
    }

    public void exitSalesOrderEntry(){
        Utility_Functions.xClickHiddenElement(driver,SpecialPricePage.btnF3);
    }

    public void soFieldVerification(){
        int orderNoLength=getText(PurchaseOrderEntryPage.orderNum).length();
        Utility_Functions.xAssertEquals(report,orderNoLength,6,"");
        int SOLength=getText(PurchaseOrderEntryPage.salesOrderNo).length();
        Utility_Functions.xAssertEquals(report,SOLength,6,"");
        int SOSuffixLength=getText(PurchaseOrderEntryPage.soSuffix).length();
        Utility_Functions.xAssertEquals(report,SOSuffixLength,2,"");
    }

    public void verifyActionCode(){
        String actionCode=getAttribute(PurchaseOrderEntryPage.actionInpput,"value");
        Utility_Functions.xAssertEquals(report,actionCode,"C","Action code changed to [C]");
    }

    public void verifyRelatedSO() {
        String relatedSO = Utility_Functions.xGetJsonData("SalesOrder_POEntry");
        sendKeysAndEnter(PurchaseOrderDetailsPage.relatedSo, relatedSO, "Enter Related SO without suffix");
        commonObj.validateText(PurchaseOrderDetailsPage.errorMsgPOD,"ERROR - Related Sales Order Not Found.","[ERROR - Related Sales Order Not Found.] error message is found");
        Utility_Functions.timeWait(3);
        sendKeysAndEnter(PurchaseOrderDetailsPage.relatedSo, jsonData.getData("RelatedSONegativeValue"), "Enter Related SO Negative Value");
        commonObj.validateText(PurchaseOrderDetailsPage.errorMsgPOD,"ERROR - Related Sales Order Not Found.","[ERROR - Related Sales Order Not Found.] error message is found");
        Utility_Functions.timeWait(3);
        sendKeysAndEnter(PurchaseOrderDetailsPage.relatedSo, jsonData.getData("RelatedSOZeroValue"), "Enter Related SO Zero Value");
        commonObj.validateText(PurchaseOrderDetailsPage.errorMsgPOD,"ERROR - Related Sales Order Not Found.","[ERROR - Related Sales Order Not Found.] error message is found");
        Utility_Functions.timeWait(3);
        sendKeysAndEnter(PurchaseOrderDetailsPage.relatedSo, jsonData.getData("RelatedSOAlphaNumericValue"), "Enter Related SO Alpha Numeric Value");
        commonObj.validateText(PurchaseOrderDetailsPage.errorMsgPOD,"ERROR - Related Sales Order Not Found.","[ERROR - Related Sales Order Not Found.] error message is found");
    }

    public void verifyPoDetailsWithLineNoAction(){
        sendKeys(PurchaseOrderDetailsPage.actionInput,"I","Enter [I] on Action");
        sendKeys(PurchaseOrderDetailsPage.lineno,"1"+Keys.ENTER,"Enter [1] on Line No");
        Utility_Functions.timeWait(4);
        String itemNo=jsonData.getData("itemNo");
        String relatedSO=getAttribute(PurchaseOrderDetailsPage.relatedSo,"value");
        String itemNoAct=getAttribute(PurchaseOrderDetailsPage.itemNumberPOD,"value");
        Utility_Functions.xAssertEquals(report,itemNo,itemNoAct,"");
        Utility_Functions.xAssertEquals(report,relatedSO,Utility_Functions.xGetJsonData("SalesOrderNumber"),"");
    }

    public void deleteOrderNo(){
        Utility_Functions.actionKey(Keys.F6,driver);
        typeShipmentD();
        Utility_Functions.timeWait(4);
        commonObj.validateText(PurchaseOrderDetailsPage.errorMsg,"ERROR","[ERROR] is present");
        commonObj.validateText(PurchaseOrderDetailsPage.errorMsgPOD,"ERROR - Order Number was not found","[ERROR - Order Number was not found] is present");
    }

    public void navigateToPoUserIdentifier(){
        click(InventoryReceiptsPage.btnUpdateOnHandQty,"Click [F12=Preferences]");
        Utility_Functions.timeWait(2);
    }

    public void validateIdentifier(){
        String nameAppear=getAttribute(PurchaseOrderDetailsPage.nameAppear,"value");
        String emailAddress=getAttribute(PurchaseOrderDetailsPage.emailAddress,"value");
        String CountryCode=getAttribute(PurchaseOrderDetailsPage.teleNo,"value");
        String teleNo=getAttribute(PurchaseOrderDetailsPage.teleNo1,"value");
        String teleNo1=getAttribute(PurchaseOrderDetailsPage.teleNo2,"value");
        Utility_Functions.xAssertEquals(report,nameAppear,jsonData.getData("nameAppear"),"");
        Utility_Functions.xAssertEquals(report,emailAddress,jsonData.getData("emailAddress"),"");
        Utility_Functions.xAssertEquals(report,CountryCode,jsonData.getData("CountryCode"),"");
        Utility_Functions.xAssertEquals(report,teleNo,jsonData.getData("teleNo"),"");
        Utility_Functions.xAssertEquals(report,teleNo1,jsonData.getData("teleNo1"),"");
    }

    public void clickReturn(){
        click(SpecialPriceAllowancePage.btnReturn,"Click [F12=Return]");
        Utility_Functions.timeWait(2);
    }

    public void verifyPreferences(){
        if(properties.getProperty("ENV").equals("PROD")){
            commonObj.validateText(PurchaseOrderDetailsPage.userIdentifier,properties.getProperty("PRODUserName"),properties.getProperty("PRODUserName")+" User Identifier is present");
        }else {
            commonObj.validateText(PurchaseOrderDetailsPage.userIdentifier,properties.getProperty("STGUserName"),properties.getProperty("STGUserName")+" User Identifier is present");
        }
        sendKeys(PurchaseOrderDetailsPage.nameAppear,jsonData.getData("nameAppear"),"Enter How Name Will Appear");
        sendKeys(PurchaseOrderDetailsPage.emailAddress,jsonData.getData("emailAddress"),"Enter E-Mail Address");
        sendKeys(PurchaseOrderDetailsPage.teleNo,jsonData.getData("CountryCode"));
        sendKeys(PurchaseOrderDetailsPage.teleNo1,jsonData.getData("teleNo"));
        sendKeys(PurchaseOrderDetailsPage.teleNo2,jsonData.getData("teleNo1"),"Enter Telephone Number");
        enter();
        enter();
    }

    public void verifyErrorMsgMailEDI(){
        sendKeys(PurchaseOrderDetailsPage.sendViaMail,"Y","Enter Send Via Mail as [Y]");
        sendKeys(PurchaseOrderDetailsPage.sendViaEDI,"Y","Enter Send Via EDI as [Y]");
        enter();
        Utility_Functions.timeWait(2);
        commonObj.validateText(PurchaseOrderDetailsPage.errorMsgPOD,"ERROR - You may NOT Email an EDI order.","[ERROR - You may NOT Email an EDI order.] error message is present");
    }

    public void sendViaMail(){
        sendKeys(PurchaseOrderDetailsPage.sendViaMail,"Y","Enter Send Via Mail as [Y]");
        enter();
        commonObj.validateText(PurchaseOrderDetailsPage.PODHeader,"Purchase Order Details","[Purchase Order Details] Header is present");
    }

    public void changeMailDetails(){
        int size=driver.findElements(By.xpath("//div[contains(text(),'AutomationTest')]/preceding::input")).size();
        sendKeys(driver.findElements(By.xpath("//div[contains(text(),'AutomationTest')]/preceding::input")).get(size-1),"2"+Keys.ENTER,"Enter [2] into input field");
        verifyPreferences();
        commonObj.validateText(By.xpath("//div[contains(text(),'"+jsonData.getData("nameAppear")+"')]"),jsonData.getData("nameAppear"),"Contact field is updated");
        commonObj.validateText(By.xpath("//div[contains(text(),'"+jsonData.getData("emailAddress")+"')]"),jsonData.getData("emailAddress"),"Email address field is updated");
    }

    public void selectMailDetails(){
        int size=driver.findElements(By.xpath("//div[contains(text(),'AutomationTest')]/preceding::input")).size();
        sendKeys(driver.findElements(By.xpath("//div[contains(text(),'AutomationTest')]/preceding::input")).get(size-1),"1"+Keys.ENTER,"Enter [1] into input field");
        Utility_Functions.timeWait(2);
        String color=driver.findElement(By.xpath("//div[contains(text(),'"+jsonData.getData("nameAppear")+"')]")).getCssValue("color");
        Utility_Functions.xAssertEquals(report,color,"rgba(0, 86, 156, 1)","Mail Id is selected and turned into blue color");
    }

    public void deselectMailDetails(){
        int size=driver.findElements(By.xpath("//div[contains(text(),'AutomationTest')]/preceding::input")).size();
        sendKeys(driver.findElements(By.xpath("//div[contains(text(),'AutomationTest')]/preceding::input")).get(size-1),"4"+Keys.ENTER,"Enter [1] into input field");
        Utility_Functions.timeWait(3);
        String color=driver.findElement(By.xpath("//div[contains(text(),'"+jsonData.getData("nameAppear")+"')]")).getCssValue("color");
        Utility_Functions.xAssertEquals(report,color,"rgba(0, 0, 0, 1)","Mail Id is selected and turned into blue color");
    }

    public void exitFromPOSend(){
        click(MatrixCostUpdatePage.exitButton,"Exit from Po Print/Send Page");
    }

    public void navigateToPoHeading(){
        click(SpecialPriceAllowancePage.btnExit, "Click Exit Button");
        commonObj.validateText(PurchaseOrderDetailsPage.poPrintSendHeader,"Purchase Order Print/Send","[Purchase Order Print/Send] header is present");
    }

    public void confPopUp(){
        Utility_Functions.timeWait(2);
        commonObj.validateText(PurchaseOrderDetailsPage.EditMailTextHeader,"Edit E-Mail Text","[Edit E-Mail Text] is present");
        commonObj.validateText(PurchaseOrderDetailsPage.emailContact,jsonData.getData("nameAppear"),"E-Mail Contact "+jsonData.getData("nameAppear")+" is present");
        commonObj.validateText(PurchaseOrderDetailsPage.emailAddressPopup,jsonData.getData("emailAddress"),"E-Mail Contact "+jsonData.getData("emailAddress")+" is present");
    }

    public void editMailSubject(){
        sendKeysAndEnter(PurchaseOrderDetailsPage.subjectEditMail,"Mail Has been sent to Vendor","Enter subject");
        click(CustomerGroupMaintenancePage.submitBtn,"Click [F9=Process]");
        Utility_Functions.timeWait(4);
        commonObj.validateText(CustomerGroupMaintenancePage.GroupNameMessage,"A purchase order has been emailed to a contact.","[A purchase order has been emailed to a contact.] is present");
    }

    public void processMail(){
        click(CustomerGroupMaintenancePage.submitBtn,"Click [F9=Process]");
        Utility_Functions.timeWait(3);
        confPopUp();
        click(CustomerGroupMaintenancePage.submitBtn,"Click [F9=Process]");
        Utility_Functions.timeWait(4);
        commonObj.validateText(CustomerGroupMaintenancePage.GroupNameMessage,"A purchase order has been emailed to a contact.","[A purchase order has been emailed to a contact.] is present");
        click(CustomerGroupMaintenancePage.submitBtn,"Click [F9=Process]");
        Utility_Functions.timeWait(4);
        commonObj.validateText(PurchaseOrderDetailsPage.confirmPrintSendPopup,"Confirm Print/Send","[Confirm Print/Send] is present");
        commonObj.validateText(PurchaseOrderDetailsPage.closedSentViaMail,"This PO is Closed and/or has already been sent via email.","[This PO is Closed and/or has already been sent via email.] is present");
        exitFromPOSend();
        click(CustomerGroupMaintenancePage.submitBtn,"Click [F9=Process]");
        sendKeys(PurchaseOrderDetailsPage.continueTextBox,"Y","Enter [Y]");
        click(CustomerGroupMaintenancePage.submitBtn,"Click [F9=Process]");
        Utility_Functions.timeWait(2);
        editMailSubject();
    }
}