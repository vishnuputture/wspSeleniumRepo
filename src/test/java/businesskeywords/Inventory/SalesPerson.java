package businesskeywords.Inventory;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import org.openqa.selenium.By;
import commonkeywords.CommonActions;
import pages.inventory.SalesPersonPage;
import pages.pricing.OrderByCustomerPage;
import pages.pricing.spa.SpecialPriceAllowancePage;

public class SalesPerson extends ReusableLib {

    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public SalesPerson(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * This method validates the title of Sales Person Page
     */
    public void navigateToSalesPersonScreen() {
        commonObj.masterToInventory();
        commonObj.inventoryToSalesPerson();
    }

    public void exitFromSAlesPersonScreen() {
        click(OrderByCustomerPage.exitutton, "Click [F3 - Exit]");
        commonObj.validateText(SpecialPriceAllowancePage.header, "Inventory Management - Main Menu", "Validating Inventory Management - Main Menu page title");
    }

    public By linkElement(String text) {
        return By.xpath("//a[text()='" + text + "']");
    }

    public By hElement(String text) {
        return By.xpath("//h3[text()='" + text + "']");
    }

    public void verifyHeaders() {
        String[] headers = {"Actions", "CALCULATIONS", "Quick View", "Item Notes"};
        for (String header : headers)
            commonObj.validateText(By.xpath("//h3[text()='" + header + "']"), header, header + " header is present");
    }

    public void verifyCalculationLabels() {
        String[] headers = {"Cost:", "Gross Margin % :", "Price:", "Quantity:","List Price:","Matrix Cost:","Gross Margin $:","Bin Location:","WSS Net Price:","Margin Percent:"};
        for (String header : headers)
            commonObj.validateText(By.xpath("//h3[text()='" + header + "']"), header, header + " header is present");
    }

    public void verifyInventoryLabels() {
        String[] headers = {"INVENTORY", "QUANTITY", "DIRECT SHIPS", "QUANTITY BREAK","Manufacturer:","Available to Sell:","with Receipts:","Selling UOM:","Package Qty:","PURCHASING","On BO:","On SO:","Unscheduled:","On PO:","In Hold:","On Hand:","L/T:","Conversion Factor:","Package Qty:","Weight(lbs):","Purchase UOM:","On SQ:","No Quantity Breaks Available"};
        for (String header : headers)
            commonObj.validateText(By.xpath("//h3[text()='" + header + "']"), header, header + " header is present");
    }

    public By divElement(String text) {
        return By.xpath("//div[text()='" + text + "']");
    }

    public void verifyLinks() {
        String links[] = {"< Previous Item", "Next Item >", "Alternate Item", "Customer Notes", "Direct/Job Qty", "IOV", "Item-Bin Maintenance", "Item Ledger", "Job Usage", "Kit Maintenance", "Orders by Item", "Preferred Co. Usage", "Quotes by Item", "Rich Data"};
        for (String link : links)
            commonObj.validateText(linkElement(link), link, link + " is present");
    }

    public void verifyTextBoxFields() {
        commonObj.validateElementExists(SalesPersonPage.itemNumberSearchIcon, "[Search for Item] Text box is present");
        commonObj.validateElementExists(SalesPersonPage.customerTextBox, "[Customer] Text box is present");
        commonObj.validateElementExists(SalesPersonPage.pricingColumn, "[Pricing Column] Text box is present");
        commonObj.validateElementExists(SalesPersonPage.rowStatic, "[Row] Text is present");
        commonObj.validateElementExists(SalesPersonPage.itemNumberSearchIcon, "Search Icon for Search for Item is present");
        commonObj.validateElementExists(SalesPersonPage.customerSearchIcon, "Search Icon for Customer is present");
        commonObj.validateElementExists(SalesPersonPage.pricingColSearchIcon, "Search Icon for Pricing Column is present");
        commonObj.validateElementExists(SalesPersonPage.attachIcon, "Attach icon is present");
        commonObj.validateElementExists(SalesPersonPage.viewIcon, "View Icon is present");
    }

    /**
     * This method UI of Sales Person Page
     */
    public void verifySalesPersonScreenUI() {
        verifyTextBoxFields();
        verifyLinks();
        verifyHeaders();
        verifyCalculationLabels();
        verifyInventoryLabels();
    }
}
