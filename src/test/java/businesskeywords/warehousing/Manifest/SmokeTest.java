package businesskeywords.warehousing.Manifest;

import businesskeywords.warehousing.Objects.*;
import businesskeywords.Pricing.SalesOrders.SalesOrders;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Settings;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import commonkeywords.DBCall;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.*;
import pages.common.MasterPage;
import pages.pricing.SalesOrders.SalesOrdersPage;
import pages.warehouse.RTSPlannerPage;
import supportLibraries.Utility_Functions;

import java.io.*;
import java.sql.*;
import java.util.*;


public class SmokeTest extends ReusableLib {
    private final CommonActions commonObj;
    private final DBCall dbCall;
    private Orders orders;
    private final FrameworkDriver ownDriver;
    public static Properties properties = Settings.getInstance();
    private ArrayList<String> vendors = new ArrayList<>();
    private final String environment = "DEV";//Prod, QA, DEV
    private final String company = "695";
    private final User wiseUser = new User(helper, "btjones1", "Nobodyknows1(", environment, company);
    private final User webUser = new User(helper, "wztestqa", environment, company);
    private final RTSPlanner planner = new RTSPlanner(helper, company, environment);
    private final DriverApp driverApp = new DriverApp(helper);
    private ArrayList<Manifest> manifests = new ArrayList<>();
    private int numberOfPurchaseOrders = 2;
    private int numberOfSameVendors = 2;
    private int numberOfSalesOrders = 1;
    private int numberOfShipments = 3;
    private int numberOfItems = 1;
    private boolean useLastOrders = false;
    private boolean useLastManifests = false;

    public SmokeTest(Helper helper) throws SQLException {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
        orders = new Orders();
        dbCall = new DBCall(company, environment);
    }

    public void initiateSmokeTest() throws SQLException, IOException {
        if (!useLastOrders) {
            wiseUser.loginToWISE();
            vendors = getRandomVendors();
            for (int i = 1; i <= numberOfSalesOrders; i++) {
                orders.getSalesOrders().add(createSalesOrder());
            }
            saveOrders();
            exitWise();
        }

        if (!useLastManifests) {
            orders = loadOrders();
            navigateToManifest();
            createManifestRTS();
            saveManifests();
        } else manifests = loadManifests();

        driverApp.loginToDriverApp(webUser);
        for (Manifest manifest : manifests) {
            driverApp.verifyManifestCard(manifest);
            driverApp.openManifest(manifest);
            driverApp.validateStops(manifest);
            driverApp.startDelivery();
        }
    }

    public void navigateToManifest() {
        boolean isDev = webUser.getEnvironment().equalsIgnoreCase("DEV");
        String url = properties.getProperty("manifestURL"+environment);
        if (!isDev) webUser.getDailyPassword();
        ownDriver.get(url);
        if (!isDev) webUser.loginToWebApp();
        waitForElementDisappear(RTSPlannerPage.loading, 5);
        webUser.selectCompany();
        waitForElementDisappear(RTSPlannerPage.loading, 5);
    }

    public void createManifestRTS() throws SQLException {
        planner.setSalesOrders(orders.getSalesOrders());
        planner.navigateToRTS();
        manifests.add(planner.createManifestRTSPlanner());
        planner.applyManifestFilters();
        planner.verifyManifestCard();
        for (SalesOrder salesOrder : orders.getSalesOrders()) {
            for (Shipment shipment : salesOrder.getShipments()) {
                planner.searchShipment(shipment);
                planner.dragShipment(shipment);
                planner.applyShipmentFilters(shipment);
                planner.searchShipment(shipment);
                planner.validateTruckIcon(shipment);
                planner.clearShipmentFilters(shipment);
                //planner.validateManifestDetails();
            }
        }
        for (PurchaseOrder purchaseOrder : orders.getPurchaseOrders()) {
            planner.searchPurchaseOrder(purchaseOrder);
            planner.dragPurchaseOrder(purchaseOrder);
            //planner.validateManifestDetails();
        }
        planner.generate();
    }

    public SalesOrder createSalesOrder() throws SQLException {
        SalesOrders so = new SalesOrders(helper);
        commonObj.masterToOrderProcessing();
        commonObj.orderProcessingToSalesOrders();
        sendKeysAndEnter(SalesOrdersPage.billToAcct, dbCall.getRandomSubAccount(),"Entering bill to account number");
        while (Utility_Functions.xIsDisplayed(ownDriver,
                By.xpath("//div[contains(text(),'Error: Customer number is invalid due to missing b')]"))) {
            sendKeysAndEnter(SalesOrdersPage.billToAcct, dbCall.getRandomSubAccount(),"Entering bill to account number");
        }
        waitForElementDisappear(By.id("_pui_loading_animation"), 15);
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.directShipDropDown), 0);
        waitForElementDisappear(By.id("_pui_loading_animation"), 15);
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.shipCompleteDropDown), 0);
        waitForElementDisappear(By.id("_pui_loading_animation"), 15);
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.deliveryTypeDropDown), 1);
        waitForElementDisappear(By.id("_pui_loading_animation"), 15);
        if (Utility_Functions.xIsDisplayed(ownDriver, SalesOrdersPage.closeTooltip)) {
            click(SalesOrdersPage.closeTooltip);
        }
        Utility_Functions.timeWait(2);
        click(SalesOrdersPage.matrixSearch);
        click(SalesOrdersPage.firstMatrixColumn);
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.shipViaDropDown), 1);
        waitForElementDisappear(By.id("_pui_loading_animation"), 15);
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.nextAction), 2);
//        Utility_Functions.xSelectDropDownByPartialText(ownDriver, report, ownDriver.findElement(SalesOrdersPage.nextAction),
//                "Ship When Available");
        String randomString = RandomStringUtils.randomAlphanumeric(10);
        sendKeys(SalesOrdersPage.txtJobName, randomString, "Entering Job Name");
        sendKeys(SalesOrdersPage.poNumberEntry, randomString, "Entering PO Number");
        sendKeys(SalesOrdersPage.placedByEntry, "Smoke", "Entering Placed By");
        Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(SalesOrdersPage.primarySalespersonDropDown), 1);
        SalesOrder salesOrder = generateSalesOrder(getValue(By.id("inOrderNum")));
        Shipment shipmentData = generateShipment();
        shipmentData.setBillTo(salesOrder.getBillTo());
        shipmentData.setBillToName(salesOrder.getBillToName());
        shipmentData.setAlphabeticName(dbCall.getAlphabeticName(shipmentData.getAcct()));
        so.navigateToItemsTab();
        int lineNumber = 1;
        ArrayList<String> items = dbCall.getRandomItems(numberOfItems * numberOfShipments, "");
        for (int i = 1; i <= numberOfShipments; i++) {
            Shipment shipment = new Shipment(shipmentData);
            for (int j = 1; j <= numberOfItems; j++) {
                String itemNumber = items.get(0);
                String qty = String.valueOf(Utility_Functions.genRandNum(10) + 1);
                Item item = addItem(itemNumber, qty, qty, ""+ lineNumber++);
                shipment.addItem(item);
                items.remove(itemNumber);
                waitForElementDisappear(By.id("_pui_loading_animation"), 15);
            }
            salesOrder.getShipments().add(shipment);
            shipment.setShipmentNumber(salesOrder.getOrderNumber() + "-" + String.format("%02d", i));
            report.updateTestLog("Finishing Shipment",
                    "Finished creating shipment " + shipment.getShipmentNumber(), Status.PASS);
        }
        createShipments(salesOrder);
        exitSalesOrder();
        return salesOrder;
    }

    private void createShipments(SalesOrder salesOrder) throws SQLException {
        click(SalesOrdersPage.shipmentTab);
        waitForElementDisappear(By.id("_pui_loading_animation"), 15);
        Utility_Functions.contextClickOnElement(ownDriver,SalesOrdersPage.elementForContextClick);
        click(SalesOrdersPage.contextOptionLineDetails,"Opening Line Details");
        click(By.id("btnDltOpnItm"), "Removing All Items");
        if(isDisplayed(SalesOrdersPage.updateShipmentBtn)) {
            click(SalesOrdersPage.updateShipmentBtn, "Updating shipment after removing items");
        }
        for (Shipment shipment : salesOrder.getShipments()) {
            int index = salesOrder.getShipments().indexOf(shipment) + 1;
            System.out.println("Adding Items to shipment " +index);
            waitForElementDisappear(By.id("_pui_loading_animation"), 15);
            click(SalesOrdersPage.maintainShipmentBtn, "Clicking on maintain shipment button");
            waitForElementDisappear(By.id("_pui_loading_animation"), 15);
            int row = 1;
            for (Item item : shipment.getItems()) {
                System.out.println("Shipment "+index + " item: "+ item.getNumber());
                click(By.id("cbxSelectLine."+row));
                sendKeys(By.id("inQtySchedule."+row), item.getQtyToShip());
                sendKeys(By.id("inQtyBO."+row), "0");
                row++;
            }
            sendKeys(By.id("tbxDeliveryInstructions"), getRandomString(120));
            Utility_Functions.actionKey(Keys.F9, ownDriver);
            //click(By.id("btnAddGoToNew"));
            waitForElementDisappear(By.id("_pui_loading_animation"), 15);
            Utility_Functions.actionKey(Keys.PAGE_DOWN, ownDriver);
            Utility_Functions.xSelectDropdownByIndex(ownDriver.findElement(By.id("slbNextAction."+ index)), 2);
            if (vendors.size() > 0)
                convertToPO(salesOrder, shipment);
        }
    }

    public void convertToPO(SalesOrder salesOrder, Shipment shipment) throws SQLException {
        int index = salesOrder.getShipments().indexOf(shipment);
        waitForElementDisappear(By.id("_pui_loading_animation"), 15);
        Utility_Functions.contextClickOnElement(ownDriver, By.id("outShipmentNumber."+ (index + 1)));
        waitForElementDisappear(By.id("_pui_loading_animation"), 15);
        Utility_Functions.xHoverElementclicks(ownDriver.findElement(SalesOrdersPage.contextOptionCreatePO),ownDriver);
        waitForElementDisappear(By.id("_pui_loading_animation"), 15);
        Utility_Functions.xHoverElementClk(ownDriver.findElement( By.xpath("//td[text()='Convert Entire Shipment']")),ownDriver);
        click(By.id("btnNext"), "Next");
        String vendorNumber = vendors.get(0);
        vendors.remove(0);
        sendKeysAndEnter(By.id("inVendorNumber"), vendorNumber, "Adding Vendor Number");
        String vendorName = getValue(By.id("outCustomerPONumber_copy"));
        Utility_Functions.xSelectDropdownByIndex(ownDriver, getElement(By.id("slbCostMethod")), 0);
        Utility_Functions.xSelectDropdownByIndex(ownDriver, getElement(By.id("slbFreightCode")), 2);
        String freightCode = Utility_Functions.xgetSelectedDropdownValue(ownDriver, By.id("slbFreightCode"));
        click(By.id("btnAdd"), "Generating PO");
        PurchaseOrder purchaseOrder = new PurchaseOrder(getText(By.cssSelector("div[id='outPONumber.1'] a")));
        click(By.id("btnBack"), "Exiting PO");
        purchaseOrder.setItems(shipment.getItems());
        purchaseOrder.setDate(shipment.getShipDate());
        purchaseOrder.setFreightCode(freightCode);
        purchaseOrder.setVendorNumber(vendorNumber);
        purchaseOrder.setVendor(vendorName);
        dbCall.setPOAddress(purchaseOrder);
        orders.getPurchaseOrders().add(purchaseOrder);
        System.out.println("Purchase Order "+purchaseOrder.getNumber()+" created");
    }

    public Item addItem(String qtyOrdered, String qtyToShip, String lineNumber) {
        return addItem("Brandon "+ RandomStringUtils.randomAlphanumeric(25), qtyOrdered, qtyToShip, lineNumber);
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
        return new Item(itemNumber, qtyOrdered, qtyToShip, lineNumber);
    }

    public void exitSalesOrder() {//UPDATE PAYMENT TERMS TO COPY THE FIRST SHIPMENT'S PAYMENT TERMS
        click(SalesOrdersPage.paymentsTab, "Clicking Payments Tab");
        String payment = "";
        for (int i = 1; i <= numberOfShipments; i++) {
            Utility_Functions.timeWait(2);
            click(By.id("OutShipNumber2.1"), "Clicking Shipment to Apply Payment");
            Utility_Functions.timeWait(2);
            click(By.id("outPayCodeText"), "Deleting Payment Terms");
            ownDriver.findElement(By.id("outPayCodeText")).clear();
            Utility_Functions.timeWait(2);
            if (isDisplayed(By.className(("pui-tip-close"))))
                click(By.className("pui-tip-close"));
            if (i == 1) {
                click(By.id("PaymentTermsSearch"), "Clicking Payment Terms");
                click(By.xpath("//*[@id=\"grdPaymentTerms\"]/div[4]/div"), "Clicking a Payment Term");
                payment = getText(By.id("outPayCodeText"));
            } else {
                sendKeys(By.id("outPayCodeText"), payment);
            }
            Utility_Functions.xSelectDropdownByVisibleText(ownDriver, SalesOrdersPage.paymentMethodDropdown, "Cash");
            //sendKeys(By.id("PIappliedAmount1"), getValue(By.id("OutputField7")));
            click(SalesOrdersPage.btnApplyPayment, "Applying  payment method");
        }
        click(SalesOrdersPage.btnSaveExitPayment,"saving and exiting");
        click(SalesOrdersPage.btnExit," exiting SOE");
        //sendKeysAndEnter(By.xpath("//*[@id=\"I_20_7\"]"), "23", "Going to Master Menu");
        Utility_Functions.actionKey(Keys.F3, ownDriver);
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
        shipment.setDeliveryInstructions(getValue(By.id("inDeliveryInstructions")));
        return shipment;
    }

    public SalesOrder generateSalesOrder(String orderNumber) {
        SalesOrder salesOrder = new SalesOrder(orderNumber);
        salesOrder.setBillTo(getValue(SalesOrdersPage.billToAcct));
        salesOrder.setBillToName(getValue(SalesOrdersPage.billName));
        salesOrder.setWrittenBy(getValue(SalesOrdersPage.writtenByDropDown));
        salesOrder.setFilledBy(getValue(SalesOrdersPage.filledByEntry));
        salesOrder.setJobName(getValue(SalesOrdersPage.txtJobName));
        salesOrder.setPoNumber(getValue(SalesOrdersPage.poNumberEntry));
        salesOrder.setPlacedBy(getValue(SalesOrdersPage.placedByEntry));
        salesOrder.setPrimarySalesperson(getValue(SalesOrdersPage.primarySalespersonDropDown));

        return salesOrder;
    }
    public static String getRandomString(int n) {
        String AlphaNumericString = " ABCDEFGHIJKLMNOPQRSTUVWXYZ "
                + " 0123456789 "
                + " abcdefghijklmnopqrstuvxyz ";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length()  * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    public ArrayList<String> getRandomVendors() throws SQLException {
        int total = numberOfPurchaseOrders - numberOfSameVendors;
        total = (total == 0 && numberOfPurchaseOrders > 0) ? 1 : total;
        if (numberOfPurchaseOrders == 0 || total == 0) return new ArrayList<>();
        int number = total * numberOfSalesOrders;
        ArrayList<String> vendors = dbCall.getRandomAccount("vendor", number);
        int index = 0;
        for (int i = number; i < numberOfPurchaseOrders * numberOfSalesOrders; i++) {
            vendors.add(vendors.get(index));
            index++;
        }
        return vendors;
    }

    public void saveOrders() throws IOException {
        String fileName = "orders.ser";
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(orders);
        out.close();
    }

    public Orders loadOrders() throws IOException {
        String fileName = "orders.ser";
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        try {
            return (Orders) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveManifests() throws IOException {
        String fileName = "manifests.ser";
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(manifests);
        out.close();
    }
    @SuppressWarnings("unchecked")
    public ArrayList<Manifest> loadManifests() throws IOException {
        String fileName = "manifests.ser";
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))){
            return (ArrayList<Manifest>) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
