package businesskeywords.Pricing.SalesOrders;

import businesskeywords.warehousing.Objects.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Settings;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import commonkeywords.DBCall;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.common.MasterPage;
import pages.pricing.SalesOrders.ShipmentInquiryPage;
import pages.pricing.SalesOrders.SalesOrdersPage;
import supportLibraries.Utility_Functions;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ShipmentInquiry extends ReusableLib {
    CommonActions common = new CommonActions(helper);
    private final FrameworkDriver ownDriver;
    public static Properties properties;
    private final User wiseUser;
    private final User webUser;
    private final User dbUser;
    private final int numberOfItems;
    private final int numberOfShipments;
    private final boolean useLastOrder;
    private final DBCall dbCall;
    private final ShipmentInquiryPage page;

    public ShipmentInquiry(Helper helper) {
        super(helper);
        ownDriver = helper.getGSDriver();
        properties = Settings.getInstance();
        page = new ShipmentInquiryPage(helper);
        String environment = jsonData.getData("environment");
        String company = jsonData.getData("company");
        wiseUser = new User(helper, jsonData.getData("user"), jsonData.getData("password"), environment, company);
        webUser = new User(helper, jsonData.getData("webUser"), environment, company);
        dbUser = new User(helper, jsonData.getData("dbUser"), jsonData.getData("dbPassword"), environment, company);
        numberOfItems = jsonData.getInt("numberOfItems");
        numberOfShipments = jsonData.getInt("numberOfShipments");
        useLastOrder = jsonData.getBoolean("useLastOrder");
        dbCall = new DBCall(dbUser);
    }

    public void loginToWISE() {
        wiseUser.loginToWISE();
    }

    public void navigateToShipmentInquiry() {
        String url = properties.getProperty("shipmentInquiryDev");
        ownDriver.get(url);
        webUser.selectCompany();
        common.validateText(page.header, "SHIPMENT INQUIRY", "Validating Header: SHIPMENT INQUIRY");
    }

    public void applyFiltersAndValidateDetails() throws IOException {
        waitForElementDisappear(page.spinner, 10);
        SalesOrder salesOrder = loadOrder();
        for (Shipment shipment : salesOrder.getShipments()) {
            if (salesOrder.getShipments().indexOf(shipment) > 0)
                click(page.clearFilters, "Clearing Filters");
            String shipmentNumber = addLeadingZero(shipment.getShipmentNumber());
            String customer = salesOrder.getBillTo() + " - " + salesOrder.getAlphabeticName();
            sendKeysAndEnter(page.shipmentNumber, shipment.getShipmentNumber(),
                    "Entering in Shipment Number: " + shipmentNumber);
            waitForElementDisappear(page.spinner, 10);
            sendKeysAndEnter(page.customerNumber, salesOrder.getBillTo(),
                    "Entering Customer Number: "+ salesOrder.getBillTo());
            waitForElementDisappear(page.spinner, 10);
            String shipDate = common.formatDate(shipment.getShipDate());
            String dateRange = shipDate + " - " + shipDate;
            Utility_Functions.validateFieldMatch(report, ownDriver.findElement(page.orderDate), dateRange);
            click(page.shipmentStatus, "Clicking Shipment Status Dropdown");
            click(page.shipmentStatusOpen, "Selecting Open from Dropdown");
            waitForElementDisappear(page.spinner, 10);
            click(page.shipVia, "Clicking Ship Via Dropdown");
            click(By.xpath("//span[normalize-space()='"+ shipment.getShipVia() +"']"),
                    "Selecting "+ shipment.getShipVia() + " From Dropdown");
            waitForElementDisappear(page.spinner, 10);
            String primaryNumber = salesOrder.getPrimarySalesperson().substring(0, 3);
            sendKeysAndEnter(page.salesPerson, primaryNumber,
                    "Inputting Salesperson: " + primaryNumber);
            //need to add written by
            sendKeysAndEnter(page.customerPO, salesOrder.getPoNumber(),
                    "Inputting Customer PO: "+ salesOrder.getPoNumber());
            waitForElementDisappear(page.spinner, 10);
            sendKeysAndEnter(page.jobName, salesOrder.getJobName(),
                    "Inputting Job Name: " + salesOrder.getJobName());
            waitForElementDisappear(page.spinner, 10);
            click(page.orderSource, "Clicking Order Source Dropdown");
            click(page.orderSourceWise, "Selecting WISE from Dropdown");
            waitForElementDisappear(page.spinner, 10);
            common.validateText(By.xpath("(//a[normalize-space()='" + shipmentNumber + "'])[1]"), shipmentNumber,
                    "Validating Shipment Number in Table");
//            common.validateText(page.customerNumberAndName, customer, "Validating Customer Number and Name in Table"); //Sometimes the customer name is not available
            common.validateText(By.xpath("//td[normalize-space()='" + shipDate + "']"), shipDate, "Validating Order Date in Table");
            String customerPO = salesOrder.getPoNumber().toUpperCase();
            common.validateText(By.xpath("(//td[contains(text(),'" + customerPO + "')])"),
                    customerPO, "Validating Customer PO: "+ customerPO);
            String jobNumber = salesOrder.getJobName().toUpperCase();
            common.validateText(By.xpath("(//td[contains(text(),'" + jobNumber + "')])"),
                    jobNumber, "Validating Job Name: "+ jobNumber);
            common.validateText(page.amount, addTrailingZero(shipment.getAmount()),
                    "Validating Shipment Amount: " + shipment.getAmount());
            common.validateText(By.xpath("(//td[contains(text(),'Open')])[1]"), "Open", "Validating Status: Open");
            //common.validateText(By.xpath("(//td[contains(text(),'Open')])[2]"), "Open", "Validating Workflow Status: Open");
            common.validateText(By.xpath("(//td[contains(text(),'No')])[1]"), "No", "Validating Direct: No");
            common.validateText(By.xpath("(//td[contains(text(),'WISE')])[1]"), "WISE", "Validating Order Source: WISE");
            click(By.xpath("(//a[normalize-space()='" + shipmentNumber + "'])[1]"), "Clicking Shipment Number");
            waitForElementDisappear(page.spinner, 10);
            validateDetails(salesOrder, shipment);
        }
    }

    public void validateDetails(SalesOrder salesOrder, Shipment shipment) {
        String customerNumberAndName = salesOrder.getBillTo() + " - " + salesOrder.getAlphabeticName();
        String shipDate = common.formatDate(shipment.getShipDate());
        String shipmentNumber = addLeadingZero(shipment.getShipmentNumber());
        String number = String.format("%03d", salesOrder.getShipments().indexOf(shipment) + 1);
        common.validateText(page.detailsHeader, "Shipment Inquiry - Details", "Validating Details Header");
        common.verifyElementContainsText(page.orderInformationHeader, "Order Information - "+ salesOrder.getOrderNumber(),
                "Validating Order Number: " + salesOrder.getOrderNumber());
//        common.validateText(By.xpath("//div[normalize-space()='" + customerNumberAndName + "']"), customerNumberAndName, //CUSTOMER IS SOMETIMES MISSING
//        "Validating Customer Number and Name: " + customerNumberAndName);
        String poNumber = salesOrder.getPoNumber().toUpperCase();
        common.validateText(By.xpath("//div[normalize-space()='" + poNumber + "']"), poNumber,
        "Validating PO Number: " + poNumber);
//        common.validateText(By.xpath("(//div[contains(text(),'" + shipDate + "')])[1]"), shipDate,  //DATES ARE WRONG IN SHIPMENT INQUIRY
//                "Validating Order Date: " + shipDate);
//        common.validateText(By.xpath("(//div[@class='ng-star-inserted'][normalize-space()='" + shipDate + "'])[1]"),
//                shipDate, "Validating Ship Date: " + shipDate);
        common.validateText(By.xpath("//div[normalize-space()='" + shipment.getPaymentTerms() + "']"),
                shipment.getPaymentTerms(), "Validating Payment Terms: " + shipment.getPaymentTerms());
        String jobName = salesOrder.getJobName().toUpperCase();
        common.validateText(By.xpath("//div[normalize-space()='" + jobName + "']"), jobName,
                "Validating Job Name: " + jobName);
        click(page.orderInformationLess, "Collapsing Order Information Card");
        click(page.shipmentMoreButton, "Expanding Shipment Information Card");
        common.verifyElementContainsText(page.shipmentInformationHeader, "Shipment Information - " + number,
                "Validating Shipment Information Header: " + "Shipment Information - " + number);
        String directFlag = shipment.getDirect().equalsIgnoreCase("YES") ? "Y" : "N";
        common.validateText(By.xpath("//div[@class='shipment-information']//div[@class='row']//div[contains(text(),'" + directFlag + "')]"),
                directFlag, "Validating Direct Flag: " + directFlag);
        common.validateText(By.xpath("//div[normalize-space()='" + shipment.getDeliveryType() + "']"), shipment.getDeliveryType(),
                "Validating Delivery Type: " + shipment.getDeliveryType());
        /* To do: ship complete */
        common.validateText(By.xpath("//div[@class='row']//div[contains(text(),'" + shipment.getShipVia() + "')]"),
                shipment.getShipVia(), "Validating Ship Via: " + shipment.getShipVia());
        common.validateText(By.xpath("//label[normalize-space()='Sold To " + salesOrder.getBillTo() + "']"),
                "Sold To " + salesOrder.getBillTo(), "Validating Sold To Customer: " + salesOrder.getBillTo());

        List<WebElement> soldToAddressElements = ownDriver.findElements(By.xpath("//div[contains(text(),'" +
                salesOrder.getBillToName() + "')]"));
        int size = soldToAddressElements.size();
        WebElement soldToAddress = soldToAddressElements.get(size > 2 ? size - 2 : size - 1);
        String fullAddress = salesOrder.getBillToName().replaceAll("\\s+", " ") + " ";
        fullAddress += salesOrder.getAddressLine1().isEmpty() ? "" : salesOrder.getAddressLine1() + " ";
        fullAddress += shipment.getAddressLine2().isEmpty() ? "" : ", " + shipment.getAddressLine2() + " ";
        fullAddress += salesOrder.getCity() + ", " + salesOrder.getState() + ", " + salesOrder.getZip();
        common.validateTextWithLines(soldToAddress, fullAddress, "Validating Sold To Address: "+ fullAddress);

        common.validateText(By.xpath("//label[normalize-space()='Ship To " + shipment.getAcct() + "']"),
                "Ship To " + shipment.getAcct(), "Validating Ship To Customer: " + shipment.getAcct());
        List<WebElement> shipToAddressElements = ownDriver.findElements(By.xpath("//div[contains(text(),'" +
                shipment.getName() + "')]"));
        int shipToSize = shipToAddressElements.size();
        WebElement shipToAddress = shipToAddressElements.get(shipToSize - 1);
        String fullShipTo = shipment.getName().replaceAll("\\s+", " ") + " ";
        fullShipTo += shipment.getAddressLine1().isEmpty() ? "" : shipment.getAddressLine1();
        fullShipTo += shipment.getAddressLine2().isEmpty() ? " " : ", " + shipment.getAddressLine2() + " ";
        fullShipTo += shipment.getCity() + ", " + shipment.getState() + ", " + shipment.getZip();
        common.validateTextWithLines(shipToAddress, fullShipTo, "Validating Ship To Address: "+ fullShipTo);
        String deliveryInstructions = shipment.getDeliveryInstructions().trim();
        common.validateText(By.xpath("//div[contains(text(), '" + deliveryInstructions + "')]"), deliveryInstructions,
                "Validating Delivery Instructions: " + deliveryInstructions);
        click(page.shipmentMoreButton, "Collapsing Shipment Information Card");
        click(page.moreInformationButton, "Expanding More Information Card");
        common.validateText(By.xpath("//div[normalize-space()='" + salesOrder.getPrimarySalesperson() + "']"),
                salesOrder.getPrimarySalesperson(), "Validating Primary Salesperson: " + salesOrder.getPrimarySalesperson());
        common.validateText(By.xpath("(//div[contains(text(),'"+ wiseUser.getName().toUpperCase() +"')])[1]"), wiseUser.getName().toUpperCase(),
                "Validating Keyed By: " + wiseUser.getName().toUpperCase());
        common.validateText(By.xpath("//div[normalize-space()='" + shipment.getShipDate() + "']"), shipment.getShipDate(),
                "Validating Keyed Date: " + shipment.getShipDate());
        String username = wiseUser.getName().toUpperCase();
        common.validateText(By.xpath("(//div[contains(text(),'"+ username +"')])[2]"), username,
                "Validating Last Access By: " + username);
        String placedBy = salesOrder.getPlacedBy().toUpperCase();
        common.validateText(By.xpath("//div[normalize-space()='" + placedBy + "']"), placedBy,
                "Validating Placed By: " + placedBy);
        click(page.moreInformationLessButton, "Collapsing More Information Card");
        validateItems(shipment.getItems());
        if (salesOrder.getShipments().indexOf(shipment) != salesOrder.getShipments().size() - 1)
            click(page.backBreadCrumb, "Clicking Back Bread Crumb");
    }

    public void validateItems(ArrayList<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            By filter = i == 0 ? page.itemFilter : page.itemFilterFilled;
            sendKeys(filter, item.getNumber(), "Inputting Item Number: " + item.getNumber());
            common.validateText(By.xpath("//td[normalize-space()='"+ item.getLineNumber() + "']"), item.getLineNumber(),
                    "Validating Line Number: "+ item.getLineNumber());
            common.validateText(By.xpath("//td[normalize-space()='"+ item.getNumber() + "']"), item.getNumber(),
                    "Validating Item Number: "+ item.getNumber());
            common.validateText(page.itemBackOrder, item.getQuantityOnBackOrder(),
                    "Validating Qty on Backorder: "+ item.getQuantityOnBackOrder());
            common.validateText(page.itemShip, item.getQtyToShip(),
                    "Validating Qty to Ship: "+ item.getQtyToShip());
            common.validateText(page.itemUnitPrice, item.getUnitPrice(),
                    "Validating Unit Price: "+ item.getUnitPrice());
            String extendedPrice = roundDollarAmount(item.getExtendedPrice());
            String averageCost = addZero(item.getAverageCost());
            String extendedCost = roundDollarAmount(item.getExtendedCost());
            String grossMargin = grossMargin(extendedPrice, extendedCost);
            common.validateText(page.itemExtendedPrice, extendedPrice,
                    "Validating Extended Price: "+ item.getExtendedPrice());
            common.validateText(page.itemAverageCost, averageCost,
                    "Validating Average Cost: "+ averageCost);
            common.validateText(page.itemExtendedCost, extendedCost,
                    "Validating Extended Average Cost: "+ extendedCost);
            common.validateText(page.itemGrossMargin, grossMargin,
                    "Validating Gross Margin Percent: "+ grossMargin);
        }
    }

    public String grossMargin(String price, String cost) {
        BigDecimal p = new BigDecimal(price.replace(",", ""));
        BigDecimal c = new BigDecimal(cost.replace(",", ""));
        BigDecimal grossMargin = p.subtract(c).divide(p, 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        if (grossMargin.compareTo(BigDecimal.valueOf(-999.99)) < 0) {
            grossMargin = BigDecimal.valueOf(-999.99);
        } else if (grossMargin.compareTo(BigDecimal.valueOf(999.99)) > 0) {
            grossMargin = BigDecimal.valueOf(999.99);
        }
        return grossMargin.setScale(2, RoundingMode.HALF_UP).toString();
    }


    public String roundDollarAmount(String amountStr) {
        BigDecimal amount = new BigDecimal(amountStr);
        BigDecimal roundedAmount = amount.setScale(2, RoundingMode.HALF_UP);
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        return formatter.format(roundedAmount);
    }
    public String addZero(String number) {
        String result = number;
        if (result.indexOf('.') == 0) {
            result = "0" + result;
        }
        return result;
    }

    public static String addTrailingZero(String input) {
        int indexOfDecimal = input.indexOf(".");
        if (indexOfDecimal != -1 && input.length() - indexOfDecimal == 2) {
            input = input + "0";
        }
        return input;
    }
    public static String addLeadingZero(String input) {
        String[] parts = input.split("-");
        String suffix = parts[1];
        if (suffix.length() < 3) {
            suffix = "0" + suffix;
        }
        return parts[0] + "-" + suffix;
    }
    public void createSalesOrder() throws SQLException, JsonProcessingException {
        SalesOrders so = new SalesOrders(helper);
        common.masterToOrderProcessing();
        common.orderProcessingToSalesOrders();
        sendKeysAndEnter(SalesOrdersPage.billToAcct, dbCall.getRandomSubAccount(),"Entering bill to account number");
        while (Utility_Functions.xIsDisplayed(ownDriver,
                By.xpath("//div[contains(text(),'Error: Customer number is invalid due to missing b')]"))) {
            sendKeysAndEnter(SalesOrdersPage.billToAcct, dbCall.getRandomSubAccount(),"Entering bill to account number");
        }
        waitForElementDisappear(page.puiSpinner, 15);
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.directShipDropDown), 0);
        waitForElementDisappear(page.puiSpinner, 15);
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.shipCompleteDropDown), 0);
        waitForElementDisappear(page.puiSpinner, 15);
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.deliveryTypeDropDown), 1);
        waitForElementDisappear(page.puiSpinner, 15);
        if (Utility_Functions.xIsDisplayed(ownDriver, SalesOrdersPage.closeTooltip)) {
            click(SalesOrdersPage.closeTooltip);
        }
        waitForElementDisappear(page.puiSpinner, 15);
        click(SalesOrdersPage.matrixSearch);
        click(SalesOrdersPage.firstMatrixColumn);
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.shipViaDropDown), 1);
        waitForElementDisappear(page.puiSpinner, 15);
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.nextAction), 2);
        sendKeys(SalesOrdersPage.txtJobName, RandomStringUtils.randomAlphanumeric(10), "Entering Job Name");
        sendKeys(SalesOrdersPage.poNumberEntry, RandomStringUtils.randomAlphanumeric(10), "Entering PO Number");
        sendKeys(SalesOrdersPage.placedByEntry, "Smoke", "Entering Placed By");
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.primarySalespersonDropDown), 1);
        SalesOrder salesOrder = generateSalesOrder(getValue(By.id("inOrderNum")));
        salesOrder.setAlphabeticName(dbCall.getAlphabeticName(salesOrder.getBillTo()));
        Shipment shipmentData = generateShipment();
        shipmentData.setBillTo(salesOrder.getBillTo());
        shipmentData.setBillToName(salesOrder.getBillToName());
        shipmentData.setAlphabeticName(dbCall.getAlphabeticName(shipmentData.getAcct()));
        so.navigateToItemsTab();
        int lineNumber = 1;
        ArrayList<String> items = dbCall.getRandomItems(numberOfItems * numberOfShipments, "");
        for (int i = 1; i <= numberOfShipments; i++) {
            Shipment shipment = new Shipment(shipmentData);
            double cost = 0;
            for (int j = 1; j <= numberOfItems; j++) {
                String itemNumber = items.get(0);
                int quantity = Utility_Functions.genRandNum(10) + 1;
                String qty = String.valueOf(quantity);
                Item item = addItem(itemNumber, qty, qty, ""+ lineNumber++);
                shipment.addItem(item);
                items.remove(itemNumber);
                waitForElementDisappear(page.puiSpinner, 15);
                cost += Double.parseDouble(item.getExtendedPrice());
            }
            shipment.setAmount(String.valueOf(cost));
            salesOrder.getShipments().add(shipment);
            shipment.setShipmentNumber(salesOrder.getOrderNumber() + "-" + String.format("%02d", i));
            report.updateTestLog("Finishing Shipment",
                    "Finished creating shipment " + shipment.getShipmentNumber(), Status.PASS);
        }
        if (numberOfShipments > 1)
            createShipments(salesOrder);
        else {
            click(SalesOrdersPage.shipmentTab);
            waitForElementDisappear(page.puiSpinner, 15);
            String deliveryInstructions = generateRandomSentence(120);
            sendKeys(By.id("inShipmentShippingInstructions.1"), deliveryInstructions);
            salesOrder.getShipments().get(0).setDeliveryInstructions(deliveryInstructions);
        }
        saveOrder(salesOrder);
    }

    private void createShipments(SalesOrder salesOrder) {
        click(SalesOrdersPage.shipmentTab);
        waitForElementDisappear(page.puiSpinner, 15);
        Utility_Functions.contextClickOnElement(ownDriver,SalesOrdersPage.elementForContextClick);
        click(SalesOrdersPage.contextOptionLineDetails,"Opening Line Details");
        click(By.id("btnDltOpnItm"), "Removing All Items");
        if(isDisplayed(SalesOrdersPage.updateShipmentBtn)) {
            click(SalesOrdersPage.updateShipmentBtn, "Updating shipment after removing items");
        }
        for (Shipment shipment : salesOrder.getShipments()) {
            int index = salesOrder.getShipments().indexOf(shipment) + 1;
            System.out.println("Adding Items to shipment " +index);
            waitForElementDisappear(page.puiSpinner, 15);
            click(SalesOrdersPage.maintainShipmentBtn, "Clicking on maintain shipment button");
            waitForElementDisappear(page.puiSpinner, 15);
            int row = 1;
            for (Item item : shipment.getItems()) {
                System.out.println("Shipment "+index + " item: "+ item.getNumber());
                click(By.id("cbxSelectLine."+row));
                sendKeys(By.id("inQtySchedule."+row), item.getQtyToShip());
                sendKeys(By.id("inQtyBO."+row), "0");
                row++;
            }
            String deliveryInstructions = generateRandomSentence(120);
            sendKeys(By.id("tbxDeliveryInstructions"), deliveryInstructions);
            shipment.setDeliveryInstructions(deliveryInstructions);
            Utility_Functions.actionKey(Keys.F9, ownDriver);
            //click(By.id("btnAddGoToNew"));
            waitForElementDisappear(page.puiSpinner, 15);
            Utility_Functions.actionKey(Keys.PAGE_DOWN, ownDriver);
            Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(By.id("slbNextAction."+ index)), 2);
        }
    }

    public Item addItem(String item, String qtyOrdered, String qtyToShip, String lineNumber) {
        String itemNumber = item;
        sendKeys(SalesOrdersPage.qtyOrdered, qtyOrdered,"Entering ordered quantity");
        sendKeys(SalesOrdersPage.itemNumber, itemNumber, "Inputting Item");
        boolean isNew = itemNumber.contains("Brandon");

        if (getText(SalesOrdersPage.tbxUnitPrice).isEmpty())
            sendKeys(SalesOrdersPage.tbxUnitPrice,"1","Entering value in Unit Price");

        if (isNew) {
            click(By.id("imgAddAsterisk"));
            Utility_Functions.actionKey(Keys.F9, ownDriver);
            itemNumber = getText(By.id("outWinItemNumber"));
        }
        sendKeys(SalesOrdersPage.qtyToShip, qtyToShip,"Entering quantity to ship");
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
        if(isDisplayed(SalesOrdersPage.itemAlreadyOnOrderWindow)){
            Utility_Functions.xMouseDoubleClick(ownDriver, ownDriver.findElement(SalesOrdersPage.lineNumberToCombine));
        }
        if(isDisplayed(SalesOrdersPage.itemEntryIssueWindow)){
            click(SalesOrdersPage.combineSelect);
            click(SalesOrdersPage.continuebtn);
        }
        if(isDisplayed(SalesOrdersPage.creditLimitHeader)){
            click(SalesOrdersPage.creditLimitContinue);
        }
        Item newItem = new Item(itemNumber, qtyOrdered, qtyToShip, lineNumber);
        newItem.setUnitPrice(getValue(By.id("outSbfUnitPrice."+lineNumber)));
        newItem.setAverageCost(getText(By.id("outSbfUnitCost."+lineNumber)));
        newItem.setExtendedPrice(getText(By.id("outSbfExtPriceOrdered."+lineNumber)));
        newItem.setGrossMarginPercent(getValue(By.id("outSbfGMPct."+lineNumber)));
        return newItem;
    }

    public void exitSalesOrder() throws IOException {
        click(SalesOrdersPage.paymentsTab, "Clicking Payments Tab");
        String payment = "";
        SalesOrder salesOrder = loadOrder();
        for (Shipment shipment : salesOrder.getShipments()) {
            int index = salesOrder.getShipments().indexOf(shipment);
            waitForElementDisappear(page.puiSpinner, 15);
            click(By.id("OutShipNumber2.1"), "Clicking Shipment to Apply Payment");
            waitForElementDisappear(page.puiSpinner, 15);
            click(By.id("outPayCodeText"), "Deleting Payment Terms");
            ownDriver.findElement(By.id("outPayCodeText")).clear();
            waitForElementDisappear(page.puiSpinner, 15);
            if (isDisplayed(By.className(("pui-tip-close"))))
                click(By.className("pui-tip-close"));
            if (index == 0) {
                click(By.id("PaymentTermsSearch"), "Clicking Payment Terms");
                click(By.xpath("//*[@id=\"grdPaymentTerms\"]/div[4]/div"), "Clicking a Payment Term");
                payment = getValue(By.id("outPayCodeText"));
            } else {
                sendKeys(By.id("outPayCodeText"), payment);
            }
            shipment.setPaymentTerms(payment);
            Utility_Functions.xSelectDropdownByVisibleText(ownDriver, SalesOrdersPage.paymentMethodDropdown, "Cash");
            click(SalesOrdersPage.btnApplyPayment, "Applying  payment method");
        }
        click(SalesOrdersPage.btnSaveExitPayment,"saving and exiting");
        click(SalesOrdersPage.btnExit," exiting SOE");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
        saveOrder(salesOrder);
    }

    public void exitWise() {
        sendKeysAndEnter(MasterPage.sqlTxtBox, "90", "Logging off");
    }

    public Shipment generateShipment() {
        Shipment shipment = new Shipment();
        shipment.setAcct(getValue(SalesOrdersPage.inShipToAcct));
        shipment.setName(getValue(SalesOrdersPage.outSaveCustName));
        shipment.setAddressLine1(getValue(SalesOrdersPage.outShipToAddLine1));
        shipment.setAddressLine2(getValue(SalesOrdersPage.outShipToAddLine2));
        shipment.setCity(getValue(SalesOrdersPage.outShipToCity));
        shipment.setState(getValue(SalesOrdersPage.outShipToState));
        shipment.setZip(getValue(SalesOrdersPage.outShipToZipCode));
        shipment.setPhone(getValue(SalesOrdersPage.outShipToPhone));
        shipment.setShipDate(getValue(By.id("dteShipDate")));
        shipment.setRequiredDate(getValue(By.id("dteRequiredDate")));
        shipment.setDirect(Utility_Functions.xgetSelectedDropdownValue(ownDriver, By.id("ddbDirectShip")));
        shipment.setDeliveryType(Utility_Functions.xgetSelectedDropdownValue(ownDriver, By.id("ddbDeliveryType")));
        shipment.setShipVia(Utility_Functions.xgetSelectedDropdownValue(ownDriver, By.id("ddbShipViaLabel")));
        shipment.setDeliveryInstructions(getText(By.id("inDeliveryInstructions")));
        return shipment;
    }

    public SalesOrder generateSalesOrder(String orderNumber) {
        SalesOrder salesOrder = new SalesOrder(orderNumber);
        salesOrder.setAddressLine1(getText(SalesOrdersPage.billToAddLine1));
        salesOrder.setAddressLine2(getText(SalesOrdersPage.billToAddLine2));
        salesOrder.setCity(getText(SalesOrdersPage.billToCity));
        salesOrder.setState(getText(SalesOrdersPage.billToState));
        salesOrder.setZip(getText(SalesOrdersPage.billToZip));
        salesOrder.setPhoneNumber(getText(SalesOrdersPage.billToPhone));
        salesOrder.setBillTo(getValue(SalesOrdersPage.billToAcct));
        salesOrder.setBillToName(getValue(SalesOrdersPage.billName));
        salesOrder.setWrittenBy(getValue(SalesOrdersPage.writtenByDropDown));
        salesOrder.setFilledBy(getValue(SalesOrdersPage.filledByEntry));
        salesOrder.setJobName(getValue(SalesOrdersPage.txtJobName));
        salesOrder.setPoNumber(getValue(SalesOrdersPage.poNumberEntry));
        salesOrder.setPlacedBy(getValue(SalesOrdersPage.placedByEntry));
        salesOrder.setPrimarySalesperson(Utility_Functions.xgetSelectedDropdownValue(ownDriver,
                SalesOrdersPage.primarySalespersonDropDown));

        return salesOrder;
    }

    public void saveOrder(SalesOrder salesOrder) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String order = mapper.writeValueAsString(salesOrder);
        Utility_Functions.xUpdateJson("SalesOrder", order);
    }
    public SalesOrder loadOrder() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Utility_Functions.xGetJsonData("SalesOrder"), SalesOrder.class);
    }

    final private String[] nouns = {"farmer", "rooster", "judge", "man", "maiden", "cow", "dog", "cat", "cheese"};
    final private String[] verbs = {"kept", "waked", "married", "milked", "tossed", "chased", "lay in"};
    final private String[] modifiers = {"that crowed in the morn", "sowing his corn", "all shaven and shorn", "all forlorn", "with the crumpled horn"};

    public String generateRandomSentence(int max) {
        StringBuilder sentence = new StringBuilder();
        while (sentence.length() < max) {
            randomSimpleSentence(sentence, max);
            if (sentence.length() < max && Math.random() > 0.75) {
                sentence.append(" and ");
            }
        }
        sentence.setLength(max);
        return sentence.toString();
    }

    private void randomSimpleSentence(StringBuilder sentence, int max) {
        sentence.append("this is ");
        if (sentence.length() < max && Math.random() > 0.15) {
            randomNounPhrase(sentence, max);
        }
        if (sentence.length() < max) {
            sentence.append("the house that Jack built");
        }
    }

    private void randomNounPhrase(StringBuilder sentence, int max) {
        int n = (int) (Math.random() * nouns.length);
        sentence.append("the ").append(nouns[n]);
        if (sentence.length() < max && Math.random() > 0.75) {
            int m = (int) (Math.random() * modifiers.length);
            sentence.append(" ").append(modifiers[m]);
        }
        int v = (int) (Math.random() * verbs.length);
        sentence.append(" that ").append(verbs[v]).append(" ");
        if (sentence.length() < max && Math.random() > 0.5) {
            randomNounPhrase(sentence, max);
        }
    }
}
