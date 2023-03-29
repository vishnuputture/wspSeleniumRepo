package pages.pricing.SalesOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class ShipmentInquiryPage extends ReusableLib {

    public ShipmentInquiryPage(Helper helper) {
        super(helper);
    }
    String shipmentInquiry = "//div[@ng-reflect-ng-class='[object Object]']//div//p-header//div//div//";
    String shipmentDetails = "//body[1]/app-root[1]/div[1]/div[1]/shipment-detail[1]/div[1]/";
    String orderInformation = "div[3]/div[1]/p-panel[1]/div[1]/div[1]/";
    String itemData = "//*[@id=\"containerFluid\"]/shipment-detail/div/div[6]/div/shipment-line-item-list/div/div/div/p-table/div/div/div[2]/div[2]/table/tbody/tr/td";

    public By shipmentNumber = By.xpath(shipmentInquiry + "win-typeahead[@field='shipmentNumber']//input[@role='searchbox']");
    public By customerNumber = By.xpath(shipmentInquiry + "win-typeahead-customer-search[@field='customerNumber']//input[@role='searchbox']");
    public By orderDate = By.xpath(shipmentInquiry + "p-calendar[@selectionmode='range']//input[@type='text']");
    public By shipmentStatus = By.xpath("//input[@id='shipmentStatusCode']");
    public By shipVia = By.xpath("//input[@id='shipViaId']");
    public By salesPerson = By.xpath("//p-autocomplete[@ng-reflect-field='salesPersonName']//input[@role='searchbox']");
    public By writtenBy = By.xpath("//input[@id='writtenByUserID']");
    public By customerPO = By.xpath("//p-autocomplete[@ng-reflect-field='customerPOSuggestions']//input[@role='searchbox']");
    public By jobName = By.xpath("//p-autocomplete[@ng-reflect-field='jobNameSuggestions']//input[@role='searchbox']");
    public By quoteNumber = By.xpath("//p-autocomplete[@ng-reflect-field='quoteNumberSuggestions']//input[@role='searchbox']");
    public By status = By.xpath("//p-dropdown[@optionlabel='shipmentStatusCode']//span[@ng-reflect-ng-class='[object Object]']");
    public By workFlowStatus = By.xpath("//p-dropdown[@optionlabel='workFlowStatusDescription']//span[@ng-reflect-ng-class='[object Object]']");
    public By direct = By.xpath("//span[normalize-space()='All Orders']");
    public By orderSource = By.xpath("//p-dropdown[@optionlabel='salesOrderTypeDescription']//span[@ng-reflect-ng-class='[object Object]']");
    public By clearFilters = By.xpath("//a[@ng-reflect-tooltip='Clear Filters']");
    public By advancedFiltersButton = By.xpath("//a[@class='fa fa-filter_list']");
    public By clearFiltersButton = By.xpath("//a[@class='fa fa-close2']");
    public By closeFiltersButton = By.xpath("//button[@class='p-sidebar-close p-sidebar-icon p-link ng-tns-c157-2 p-ripple ng-star-inserted']");
    public By shipmentStatusOpen = By.xpath("//span[normalize-space()='Open']");
    public By orderSourceWise = By.xpath("//span[normalize-space()='WISE']");
    public By customerNumberAndName = By.xpath("//span[@class='pointer ng-star-inserted']");
    public By orderDateTable = By.xpath("//td[@class='pr-3 single-line-parent align-right ng-star-inserted']");
    public By amount = By.xpath("//td[@class='pr-3 single-line-parent align-right more-spaces ng-star-inserted']");
    public By header = By.xpath("//h2[normalize-space()='SHIPMENT INQUIRY']");
    public By spinner = By.xpath("//i[@class='fa fa-spinner fa-spin fa-3x fa-fw win-spinner']");
    public By shipmentCustomerPO = By.xpath("//*[@id=\"p-panel-15-content\"]/div/div/shipment-line-item-list/div/div[1]/div/p-table/div/div/div[2]/div[2]/table/tbody/tr/td[2]");

    public By detailsHeader = By.xpath("//h2[normalize-space()='Shipment Inquiry - Details']");
    public By orderInformationHeader = By.xpath(shipmentDetails + orderInformation + "p-header[1]");
    public By subTotal = By.xpath("/html/body/app-root/div/div/shipment-detail/div/div[3]/div/p-panel/div/div[2]/div/div/div/order-information/div/div/div[2]/table/tr[1]/td[2]");
    public By orderOpenPill = By.xpath("(//span[@class='blue-badge-pill pill'][normalize-space()='Open'])[1]");
    public By shipmentInformationHeader = By.xpath(shipmentDetails + "div[4]/div[1]/p-panel[1]/div[1]/div[1]/p-header[1]");
    public By shipmentOpenPill = By.xpath("(//span[@class='blue-badge-pill pill'][normalize-space()='Open'])[2]");
    public By shipmentMoreButton = By.xpath(shipmentDetails + "div[4]/div[1]/p-panel[1]/div[1]/div[1]/div[1]/button[1]/span[1]");
    public By shipmentDeliveryInstructions = By.xpath("//*[@id=\"p-panel-10-content\"]/div/div/div/shipment-information/div/div[3]/div[3]/div");

    public By soldToAddress = By.xpath("//div[@class='row mt-4']//div[3]//div[1]//div[1]");
    public By shipToAddress = By.xpath("//div[@class='row mt-4']//div[3]//div[1]//div[1]");
    public By moreInformationButton = By.xpath("//span[@class='pi pi-plus ng-star-inserted']");
    public By moreInformationLessButton = By.xpath("//p-panel[@ng-reflect-toggleable='false']//div//div//span");
    public By itemFilter = By.xpath("//input[@class='form-control p-inputtext p-component']");
    public By itemFilterFilled = By.xpath("//input[@class='form-control p-inputtext p-component p-filled']");

    public By backBreadCrumb = By.xpath("//a[normalize-space()='< Back /']");
    public By orderInformationLess = By.xpath(shipmentDetails + orderInformation + "div[1]/button[1]/span[1]");

    public By itemBackOrder = By.xpath(itemData + "[1]");
    public By itemShip = By.xpath(itemData + "[2]");
    public By itemUnitPrice = By.xpath(itemData + "[3]");
    public By itemExtendedPrice = By.xpath(itemData + "[4]");
    public By itemAverageCost = By.xpath(itemData + "[5]");
    public By itemExtendedCost = By.xpath(itemData + "[6]");
    public By itemMatrixCost = By.xpath(itemData + "[7]");
    public By itemExtendedMatrixCost = By.xpath(itemData + "[8]");
    public By itemGrossMargin = By.xpath(itemData + "[9]");

    public By puiSpinner = By.id("_pui_loading_animation");

}
