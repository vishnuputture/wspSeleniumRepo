package businesskeywords.warehousing.Manifest;

import businesskeywords.warehousing.Objects.*;
import com.paulhammant.ngwebdriver.NgWebDriver;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import commonkeywords.DBCall;
import org.openqa.selenium.*;
import pages.warehouse.*;
import supportLibraries.Utility_Functions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RTSPlanner extends ReusableLib {
    CommonActions commonObj;
    private FrameworkDriver ownDriver;
    private NgWebDriver ngWebDriver;
    private Manifest manifest;
    private ArrayList<SalesOrder> salesOrders;
    private String company;
    private String environment;
    private Driver driver;
    private DBCall dbCall;

    Calendar cal = Calendar.getInstance();
    long timeStamp = getTimeStamp();

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     * @param company
     * @param environment
     */
    public RTSPlanner(Helper helper, String company, String environment) throws SQLException {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
        this.company = company;
        this.environment = environment;
        this.dbCall = new DBCall(company, environment);
        this.driver = dbCall.getDriver();
        this.ngWebDriver = ownDriver.getNgWebDriver();
    }

    public Manifest getManifest() {
        return manifest;
    }

    public void setManifest(Manifest manifest) {
        this.manifest = manifest;
    }

    public ArrayList<SalesOrder> getSalesOrders() {
        return salesOrders;
    }

    public void setSalesOrders(ArrayList<SalesOrder> salesOrders) {
        this.salesOrders = salesOrders;
    }

    public void navigateToRTS() {
        click(ManifestsPage.menuIcon);
        click(ManifestsPage.subMenuRTS, "Navigate to Ready to Ship Planner");
        waitForElementDisappear(RTSPlannerPage.loading, 5);
        click(ManifestsPage.closeMenu);
        commonObj.validateText(RTSPlannerPage.RTSHeader, "READY TO SHIP PLANNER", "READY TO SHIP PLANNER Screen Header is present");
    }

    public Manifest createManifestRTSPlanner() throws SQLException {
        click(RTSPlannerPage.createManifest, "Opening Create Manifest Modal");
        Utility_Functions.timeWait(2);
        click(RTSPlannerPage.dateModal, "Opening Calendar");
        click(By.cssSelector("td[data-date='"+timeStamp+"']"));
        click(RTSPlannerPage.startTime, "Selecting Time");
        String date = getValue(RTSPlannerPage.dateModal);
        String time = getValue(RTSPlannerPage.startTime);
        String truck = dbCall.getTruck();

//        Utility_Functions.xSelectDropdownByName(ownDriver, report, RTSPlannerPage.truckDropDown, truck, "Selecting Dropdown");
//        Utility_Functions.xSelectDropDownByPartialText(ownDriver, report, RTSPlannerPage.driverDropDown, driverName);
        click(RTSPlannerPage.truckDropDown, "Clicking Truck Dropdown");
        click(By.xpath("//win-select[@id='editTruck']//span[contains(text(),'"+ truck +"')]"));
        Utility_Functions.timeWait(1);
        click(RTSPlannerPage.driverDropDown, "Clicking Driver Dropdown");
        click(By.xpath("//div[contains(@class,'win-dropdown active')]//span[contains(text(),'"+ driver.getFirstName() +"')]"));
        click(RTSPlannerPage.completeCreate, "Creating Manifest");
        Utility_Functions.timeWait(4);
        while (Utility_Functions.xIsDisplayed(ownDriver, RTSPlannerPage.manifestExists)) {
            click(RTSPlannerPage.startTime, "Beginning to update time");
            if (time.contains("45"))
                click(RTSPlannerPage.hourUpArrow, "Increasing hours by 1");
            click(RTSPlannerPage.minuteUpArrow, "Increasing by 15 minutes");
            time = getValue(RTSPlannerPage.startTime);
            click(RTSPlannerPage.closeGrowl, "Closing Error Message");
            click(RTSPlannerPage.completeCreate, "Creating Manifest");
        }
        waitForElementDisappear(RTSPlannerPage.loading, 5);
        String manifestId = getText(RTSPlannerPage.successMessage);
        report.updateTestLog("Manifest created", manifestId, Status.PASS);
        manifestId = manifestId.substring((manifestId.indexOf("#")) + 1, manifestId.indexOf(" successfully"));
        manifest = generateManifest(manifestId, date, time, truck, driver);
        return manifest;
    }

    public void applyManifestFilters() {
        click(RTSPlannerPage.manifestsFilters, "Opening Filters Modal");
        click(RTSPlannerPage.manifestFilterFromDate, "Opening Calendar");
        click(By.cssSelector("td[data-date='"+timeStamp+"']"));
        Utility_Functions.timeWait(1);
        click(RTSPlannerPage.manifestFilterToDate, "Opening Calendar");
        click(By.cssSelector("td[data-date='"+timeStamp+"']"));
        click(RTSPlannerPage.manifestFilterStatus, "Clicking Status Dropdown");
        click(By.xpath("//div[contains(@class, 'win-dropdown active')]//span[contains(text(),'"+ manifest.getStatus() +"')]"));
        click(RTSPlannerPage.manifestFilterDriver, "Clicking Driver Dropdown");
        click(By.xpath("//div[contains(@class, 'win-dropdown active')]//span[contains(text(),'"+ driver.getFirstName() +"')]"));
        click(RTSPlannerPage.manifestFilterTruck, "Clicking Truck Dropdown");
        click(By.xpath("//div[contains(@class, 'win-dropdown active')]//span[contains(text(),'"+ manifest.getTruck() +"')]"));
//        Utility_Functions.xSelectDropdownByName(ownDriver, report, RTSPlannerPage.manifestFilterStatus,
//                manifest.getStatus(), "Selecting Dropdown");
//        Utility_Functions.xSelectDropDownByPartialText(ownDriver, report, RTSPlannerPage.manifestFilterDriver,
//                manifest.getDriver().getFirstName());
//        Utility_Functions.xSelectDropdownByName(ownDriver, report, RTSPlannerPage.manifestFilterTruck,
//                manifest.getTruck(), "Selecting Dropdown");
        click(RTSPlannerPage.manifestFilterApply, "Applying Filters");
        Utility_Functions.timeWait(1);
    }
    public void verifyManifestCard() {
        String manifestTime = manifest.getTime().replaceFirst("^0+(?!$)", "");
        String dateAndTime = manifest.getDate() + " " + manifestTime;
        System.out.println(dateAndTime);
        commonObj.validateText(By.xpath("(//div[@ng-reflect-klass='summary-title'][normalize-space()='"+manifest.getStatus()+"'])[1]"), manifest.getStatus(),
                "Manifest Status is on card");
        commonObj.validateText(By.xpath("//span[normalize-space()='"+manifest.getNumber()+"']"), manifest.getNumber(),
                "Manifest Number is on card");
        commonObj.validateText(By.xpath("(//span[normalize-space()='"+dateAndTime+"'])[1]"),
                dateAndTime, "Date and Time Present");
        commonObj.validateText(By.xpath("(//span[normalize-space()='"+manifest.getDriver().getFullName()+"'])[1]"),
                manifest.getDriver().getFullName(), "Driver Present");
        String truck = manifest.getTruck().contains("'")
                ? "\""+manifest.getTruck()+"\""
                : "'"+manifest.getTruck()+"'";
        commonObj.validateText(By.xpath("(//span[normalize-space()="+truck+"])[1]"),
                manifest.getTruck(), "Truck Present");
    }

    public Manifest generateManifest(String manifestId, String date, String time, String truck, Driver driver) {
        Manifest manifest = new Manifest(manifestId);
        manifest.setDate(date);
        manifest.setTime(time);
        manifest.setTruck(truck);
        manifest.setDriver(driver);
        manifest.setStatus("Created");
        return manifest;
    }
    public String formatDate(String date) {
        SimpleDateFormat oldFormat = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String newDate = date;
        try {
            newDate = simpleDateFormat.format(oldFormat.parse(date));
        } catch (ParseException e) {e.printStackTrace();}
        return newDate;
    }
    public void typeInField(WebElement element, String value) throws InterruptedException {
        element.clear();
        for (int i = 0; i < value.length(); i++){
            char c = value.charAt(i);
            String s = String.valueOf(c);
            Thread.sleep(300);
            element.sendKeys(s);
        }
    }
    public void searchShipment(Shipment shipment) {
        String shipmentNumber = shipment.getShipmentNumber();
        String addressLine3 = shipment.getCity() + ", " + shipment.getState() + ", "
                + shipment.getZip().replace("-", "");
        String date = formatDate(shipment.getShipDate());

        click(RTSPlannerPage.shipmentSearch);
        //sendKeysAndEnter(RTSPlannerPage.shipmentSearch,  shipmentNumber, "Typing in Shipment Number");
        try {
            typeInField(ownDriver.findElement(RTSPlannerPage.shipmentSearch), shipmentNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Utility_Functions.timeWait(2);
        commonObj.validateText(By.xpath("(//td[normalize-space()='"+shipmentNumber+"'])[1]"),
                shipmentNumber, "Shipment is present");
        commonObj.validateText(By.xpath("(//td[normalize-space()='"+date+"'])[1]"),
                date, "Ship Date is present");
        commonObj.validateText(By.xpath("(//div[normalize-space()='"+shipment.getAddressLine1()+"'])[1]"),
                shipment.getAddressLine1(), "Address Line 1 is present");
        commonObj.validateText(By.xpath("(//div[normalize-space()='"+shipment.getAddressLine2()+"'])[1]"),
                shipment.getAddressLine2(), "Address Line 2 is present");
        commonObj.validateText(By.xpath("(//div[normalize-space()='"+addressLine3+"'])[1]"),
                addressLine3, "Address Line 3 is present");
        commonObj.validateText(By.xpath("//tr[@id='"+shipmentNumber+"']//td[contains(text(), '"+shipment.getShipVia()+"')]"),
                shipment.getShipVia(), "Ship Via Present");

    }

    public void dragShipment(Shipment shipment) {
        String shipmentNumber = shipment.getShipmentNumber();
        commonObj.dragAndDrop(By.xpath("//tr[@id='"+shipmentNumber+"']//span//i"),
                By.xpath("//span[normalize-space()='"+manifest.getNumber()+"']"));
        manifest.addOrder(shipment);
        waitForElementDisappear(RTSPlannerPage.loading, 5);
        commonObj.validateText(By.xpath("(//span[normalize-space()='"+manifest.getStops().size()+"'])[1]"),
                String.valueOf(manifest.getStops().size()), "Number of stops is correct");
        waitForElementDisappear(RTSPlannerPage.loading, 5);
    }

    public void dragPurchaseOrder(PurchaseOrder purchaseOrder) {
        String poNumber = purchaseOrder.getNumber();
        commonObj.dragAndDrop(By.xpath("//tr[@id='"+poNumber+"']//span//i"),
                By.xpath("//span[normalize-space()='"+manifest.getNumber()+"']"));
        manifest.addOrder(purchaseOrder);
        waitForElementDisappear(RTSPlannerPage.loading, 5);
        commonObj.validateText(By.xpath("(//span[normalize-space()='"+manifest.getStops().size()+"'])[1]"),
                String.valueOf(manifest.getStops().size()), "Number of stops is correct");
        waitForElementDisappear(RTSPlannerPage.loading, 5);
    }

    public void generate() {
        waitForElementDisappear(RTSPlannerPage.loading, 5);
        Utility_Functions.xHoverElementclicks(
                ownDriver.findElement(By.xpath("//span[normalize-space()='"+manifest.getNumber()+"']")), ownDriver);
        Utility_Functions.xHoverElementClk(
                ownDriver.findElement(By.xpath("//*[@id=\"containerFluid\"]/ready-to-ship-planner" +
                        "/div[1]/div[2]/div/div[2]/div[1]/win-summary-card/div/div[1]/div/span[1]")),ownDriver);
        waitForElementDisappear(RTSPlannerPage.loading, 5);
        manifest.setStatus("Generated");
        click(RTSPlannerPage.manifestsFilters, "Opening Filters Modal");
        click(RTSPlannerPage.manifestFilterStatus, "Clicking Status Dropdown");
        click(By.xpath("//div[contains(@class, 'win-dropdown active')]//span[contains(text(),'"+ manifest.getStatus() +"')]"));
        click(RTSPlannerPage.manifestFilterApply, "Applying Filters");
        Utility_Functions.timeWait(1);
        commonObj.validateText(By.xpath("(//div[contains(text(),'"+manifest.getStatus()+"')])[1]"), manifest.getStatus(),
                "Manifest Status 'Generated' is on card");
    }

    public void clearShipmentFilters(Shipment shipment) {
        String shipmentNumber = shipment.getShipmentNumber();
        waitForElementDisappear(RTSPlannerPage.loading, 5);
        click(RTSPlannerPage.shipmentFilters, "Opening Filters Modal");
        click(RTSPlannerPage.clearShipmentFilters, "Clearing Filters");
        click(RTSPlannerPage.shipmentApply, "Applying Filters");
        waitForElementDisappear(RTSPlannerPage.loading, 5);
    }

    public void applyShipmentFilters(Shipment shipment) {
        String shipmentNumber = shipment.getShipmentNumber();
        click(RTSPlannerPage.shipmentFilters, "Opening Filters Modal");
//        sendKeys(RTSPlannerPage.shipmentCustomerName, salesOrder.getBillTo(), "Adding Bill To Name");
//        click(By.xpath("(//li[@role='option'])[1]"), "Clicking Result Dropdown");
        click(RTSPlannerPage.shipmentStartDate, "Opening Calendar");
        click(By.cssSelector("td[data-date='"+timeStamp+"']"));
        Utility_Functions.timeWait(1);
        click(RTSPlannerPage.shipmentEndDate, "Opening Calendar");
        click(By.cssSelector("td[data-date='"+timeStamp+"']"));
        sendKeys(By.xpath("(//input[@placeholder='Enter Manifest Number'])[1]"), manifest.getNumber(), "Adding number");
        Utility_Functions.timeWait(1);
        click(By.xpath("(//li[@role='option'])[1]"), "Clicking Result Dropdown");
//        Utility_Functions.xSelectDropdownByName(ownDriver, report, RTSPlannerPage.shipmentShipVia,
//                shipment.getShipVia(), "Selecting Dropdown");
        click(RTSPlannerPage.shipmentShipVia, "Clicking Ship Via Dropdown");
        click(By.xpath("//div[contains(@class, 'win-dropdown active')]//span[normalize-space()='"+ shipment.getShipVia() +"']"));
        click(RTSPlannerPage.shipmentApply, "Applying Filters");
        waitForElementDisappear(RTSPlannerPage.loading, 5);
    }

    public void validateTruckIcon(Shipment shipment) {
        String shipmentNumber = shipment.getShipmentNumber();
        commonObj.validateElementExists(By.xpath("//td[normalize-space()='"+shipmentNumber+"']//i"), "Validating Truck icon is now on order");
    }

    public void validateManifestDetails(SalesOrder salesOrder) throws SQLException {
        waitForElementDisappear(RTSPlannerPage.loading, 5);
        click(By.xpath("//span[normalize-space()='"+manifest.getNumber()+"']"), "Clicking Manifest Card");
        String title = "Manifest #: "+manifest.getId();
        commonObj.validateText(By.xpath("//h2[normalize-space()='"+title+"']"), title,
                "Validating Manifest Number");
        validateValue(RTSPlannerPage.detailsDate, manifest.getDate(), "Validating Date");
        validateValue(RTSPlannerPage.detailsTime, manifest.getTime(), "Validating Time");
        validateValue(RTSPlannerPage.detailsTruck, manifest.getTruck(), "Validating Truck");
        validatePartialValue(RTSPlannerPage.detailsDriver, manifest.getDriver().getFullName(), "Validating Driver");
        int count = 0;
        for (Stop stop : manifest.getStops()) {
            String stopNumber = String.valueOf(manifest.getStops().indexOf(stop) + 1);
            commonObj.validateText(By.xpath("(//span[normalize-space()='Stop #"+stopNumber+"'])[1]"),
                    "Stop #"+stopNumber, "Validating Stop Number");
            Shipment firstShipment = stop.getShipments().get(0);
            String addressLine2 = firstShipment.getAddressLine2();
            String alphaName = dbCall.getAlphabeticName(salesOrder.getBillTo());

            String shipToName = !firstShipment.getName().equalsIgnoreCase(alphaName)
                    ? firstShipment.getName() + " "
                    : "";

            String address = alphaName + " - "
                    + shipToName
                    + firstShipment.getAddressLine1() + " "
                    + (!addressLine2.isEmpty() ? (" " + addressLine2) : "")
                    + firstShipment.getCity() + " "
                    + firstShipment.getState() + ", "
                    + firstShipment.getZip();
            String stopDiv = count == 0 ? "" : "["+count+"]";
//            commonObj.validateText(By.xpath("//*[@id=\"stopList\"]/div"+stopDiv+"/div[1]/div/span[2]"),
//                    address, "Validating Address");
                for (Shipment shipment : stop.getShipments()) {
                    String shipmentNumber = shipment.getShipmentNumber();
                    commonObj.validateText(By.xpath("(//span[normalize-space()='"+shipmentNumber+"'])[1]"),
                            shipmentNumber, "Validating Shipment Number");
                    Utility_Functions.actionKey(Keys.PAGE_DOWN, ownDriver);
                }
            count++;
            Utility_Functions.actionKey(Keys.PAGE_DOWN, ownDriver);
        }
        Utility_Functions.actionKey(Keys.HOME, ownDriver);
        click(RTSPlannerPage.detailsClose, "Closing Details Modal");
    }
    public void validateManifestDetails() throws SQLException {
        click(By.xpath("//span[normalize-space()='"+manifest.getNumber()+"']"), "Clicking Manifest Card");
        String title = "Manifest #: "+manifest.getId();
        commonObj.validateText(By.xpath("//h2[normalize-space()='"+title+"']"), title,
                "Validating Manifest Number");
        waitForElementDisappear(RTSPlannerPage.loading, 5);
        validateValue(RTSPlannerPage.detailsDate, manifest.getDate(), "Validating Date");
        validateValue(RTSPlannerPage.detailsTime, manifest.getTime(), "Validating Time");
        validateDropDownValue(RTSPlannerPage.detailsTruck, manifest.getTruck(), "Validating Truck");
        validateDropDownValue(RTSPlannerPage.detailsDriver, manifest.getDriver().getFullName(), "Validating Driver");
        for (Stop stop : manifest.getStops()) {
            String stopNumber = String.valueOf(manifest.getStops().indexOf(stop) + 1);
            boolean isPO = stop.getOrderType().equalsIgnoreCase("PO");
            if (isPO) {
                commonObj.validateText(By.xpath("(//span[normalize-space()='Stop #" + stopNumber + " - Pickup'])[1]"),
                        "Stop #" + stopNumber + " - Pickup", "Validating Stop Number");
                for (PurchaseOrder po : stop.getPurchaseOrders()) {
                    String poNumber = po.getNumber();
                    commonObj.validateText(By.xpath("(//span[normalize-space()='" + poNumber + "'])[1]"),
                            poNumber, "Validating PO Number");
                    Utility_Functions.actionKey(Keys.PAGE_DOWN, ownDriver);
                }
            } else {
                commonObj.validateText(By.xpath("(//span[normalize-space()='Stop #" + stopNumber + "'])[1]"),
                        "Stop #" + stopNumber, "Validating Stop Number");
                for (Shipment shipment : stop.getShipments()) {
                    String shipmentNumber = shipment.getShipmentNumber();
                    commonObj.validateText(By.xpath("(//span[normalize-space()='"+shipmentNumber+"'])[1]"),
                            shipmentNumber, "Validating Shipment Number");
                    Utility_Functions.actionKey(Keys.PAGE_DOWN, ownDriver);
                }
            }
            Utility_Functions.actionKey(Keys.PAGE_DOWN, ownDriver);
        }
        Utility_Functions.actionKey(Keys.HOME, ownDriver);
        click(RTSPlannerPage.detailsClose, "Closing Details Modal");
    }

    public void validateValue(By ele, String text, String msg) {
        if(Utility_Functions.xWaitForElementPresent(ownDriver, ele, 5)) {
            String title = getValue(ele);
            if (title.equalsIgnoreCase(text)) {
                System.out.println("Text: " + title);
                Utility_Functions.xAssertEquals(report, text.toLowerCase(), title.trim().toLowerCase(), msg);
            } else {
                System.out.println("Found text: "+ title + " Expected text: "+ text);
                report.updateTestLog("Verify text", msg, Status.FAIL);
            }
        }else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :"+ ele);
        }
    }

    public void validateDropDownValue(By ele, String text, String msg) {
        if(Utility_Functions.xWaitForElementPresent(ownDriver, ele, 5)) {
            String title = Utility_Functions.xgetSelectedDropdownValue(ownDriver, ele).toLowerCase().trim();
            if (title.contains(text.toLowerCase().trim())) {
                System.out.println("Text: " + title);
                title = text;
                Utility_Functions.xAssertEquals(report, text, title, msg);
            } else {
                System.out.println("Found text: "+ title + " Expected text: "+ text);
                report.updateTestLog("Verify text", msg, Status.FAIL);
            }
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :"+ ele);
        }
    }

    public void validatePartialValue(By ele, String text, String msg) {
        if(Utility_Functions.xWaitForElementPresent(ownDriver, ele, 5)) {
            String title = getValue(ele).toLowerCase();
            if (title.contains(text.toLowerCase())) {
                System.out.println("Text: " + title);
                title = text;
                Utility_Functions.xAssertEquals(report, text, title, msg);
            } else {
                System.out.println("Found text: "+ title + " Expected text: "+ text);
                report.updateTestLog("Verify text", msg, Status.FAIL);
            }
        }else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :"+ ele);
        }
    }

    public long getTimeStamp() {
        long time = new Date().getTime();
        Date date = new Date(time - time % (24 * 60 * 60 * 1000));
        return date.getTime();
    }

    public void searchPurchaseOrder(PurchaseOrder purchaseOrder) {
        String date = formatDate(purchaseOrder.getDate());
        String addressLine4 = purchaseOrder.getCity() + ", " + purchaseOrder.getState() + ", "
                + purchaseOrder.getZip().replace("-", "");
//        Utility_Functions.xSelectDropdownByName(ownDriver, report, By.xpath("(//select[@id='show'])[1]"),
//                "Purchase Orders", "Switching to Purchase Orders");
        click(RTSPlannerPage.orderDropDown);
        click(RTSPlannerPage.poSelect);
        waitForElementDisappear(RTSPlannerPage.loading, 5);
        sendKeys(RTSPlannerPage.poSearch, purchaseOrder.getNumber(), "Searching PO");
        waitForElementDisappear(RTSPlannerPage.loading, 5);
        sendKeys(RTSPlannerPage.vendorSearch, purchaseOrder.getVendor(), "Searching Vendor");
        waitForElementDisappear(RTSPlannerPage.loading, 5);
        click(By.xpath("(//tr[@id='"+purchaseOrder.getNumber()+"'])[1]"), "Clicking PO");
        commonObj.validateText(By.xpath("(//td[normalize-space()='" + purchaseOrder.getNumber() + "'])[1]"),
                purchaseOrder.getNumber(), "Purchase Order is present");
        commonObj.validateText(By.xpath("(//td[normalize-space()='" + date + "'])[1]"),
                date, "Date is present");
        commonObj.validateText(By.xpath("(//strong[normalize-space()='" + titleCase(purchaseOrder.getAddressLine1()) + "'])[1]"),
                titleCase(purchaseOrder.getAddressLine1()), "Address Line 1 is present");
        commonObj.validateText(By.xpath("(//div[normalize-space()='" + purchaseOrder.getAddressLine2() + "'])[1]"),
                purchaseOrder.getAddressLine2(), "Address Line 2 is present");
        if (!purchaseOrder.getAddressLine3().isEmpty()) {
            commonObj.validateText(By.xpath("(//div[normalize-space()='" + purchaseOrder.getAddressLine3() + "'])[1]"),
                    purchaseOrder.getAddressLine3(), "Address Line 3 is present");
        }
        commonObj.validateText(By.xpath("(//div[normalize-space()='" + addressLine4 + "'])[1]"),
                addressLine4, "Address Line 3 is present");
    }
    public static String titleCase(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        StringBuilder converted = new StringBuilder();
        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }
}
