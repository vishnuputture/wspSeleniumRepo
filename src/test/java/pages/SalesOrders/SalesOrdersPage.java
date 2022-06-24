package pages.SalesOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;


public class SalesOrdersPage extends ReusableLib {

	 public SalesOrdersPage(Helper helper) {
	        super(helper);
	    }
	public static By pageTitle = By.id("wob-app-header");
	public static By billToAcct = By.id("inBillToAcct");
	public static By txtJobName = By.id("txtJobname");
	public static By poNumberEntry = By.id("inCustPO");
	public static By placedByEntry = By.id("inPlacedBy");
	public static By primarySalespersonDropDown = By.id("ddbPrimarySalesperson");
	public static By matrixColumn = By.id("txtS2MatrixColumn");
	public static By writtenByDropDown = By.id("ddbWrittenBy");
	public static By filledByEntry = By.id("txtFilledBy");
	public static By matrixError = By.xpath("//*[@id=\"5250\"]/div[138]");
	public static By closeError = By.xpath("//*[@id=\"5250\"]/div[138]/div[2]");
	public static By matrixSearch = By.xpath("//*[@id=\"faMatrixSearch\"]/i");
	public static By firstMatrixColumn = By.id("OutMatrixID.1");
	public static By billName = By.id("inBillToAcct_copy");
	public static By billCity = By.id("outBillToCity");
	public static By billState = By.id("outBillToState");
	public static By billZipCode = By.id("outBillToZipCode");
	public static By inShipToAcct = By.id("inShipToAcct");
	public static By outSaveCustName = By.id("outSaveCustName");
	public static By outShipToCity = By.id("outShipToCity");
	public static By outShipToState = By.id("outShipToState");
	public static By outShipToZipCode = By.id("outShipToZipCode");
	public static By deliveryTypeDropDown = By.id("ddbDeliveryType");
	public static By deliveryTypeError = By.xpath("//*[@id=\"5250\"]/div[138]");
	public static By directShipDropDown = By.id("ddbDirectShip");
	public static By shipViaDropDown = By.id("ddbShipViaLabel");
	public static By shipCompleteDropDown = By.id("ddbShipComplete");
	public static By btnExit = By.id("btnExitBill");

	//item tab
	public static By itemsTab = By.xpath("//span[text()='Items'][@istab='true']");
	public static By qtyOrdered = By.id("inQtyOrder");
	public static By itemNumber = By.id("inItemNumber");
	public static By qtyToShip = By.id("inQtyShip");
	public static By salesOrderField = By.id("inOrderNum");
	public static By btnSaveExit = By.id("btnSaveExit");
	public static By closeTooltip = By.xpath("//div[@class='pui-tip-close']");
	public static By itemEntryIssueWindow = By.id("pnlItemEntryIssues");
	public static By combineSelect = By.id("rdbUserActChoice1.1");
	public static By itemAlreadyOnOrderWindow = By.id("Dialog1");
	public static By lineNumberToCombine = By.id("OutputField2.1");

	//shipment tab
	public static By shipmentTab = By.xpath("//span[text()='Shipments'][@istab='true']");
	public static By lblRelatedPO = By.id("Related_PO.1");
	public static By shipmentStatus=By.id("inShipmentStatus.1");
	public static By printAndExitbtn= By.id("btnSavePrint");
	public static By btnContinue = By.id("Continue");
	public static By shipmentDeliveryTypeDropDown = By.id("slbShipType.1");
	public static By  elementForContextClick= By.xpath("//select[@id='slbShipType.1']/parent::div");
	public static By directShipDropdown = By.xpath("//*[contains(@id,'slbDirectShip')]");
	public static By contextOptionLineDetails = By.xpath("//td[text()='Line Details']");
	public static By contextOptionCreatePO = By.xpath("//td[text()='Create Purchase Order']");
	public static By contextOptionCreatePOSelectedItem = By.xpath("//*[text()='Convert Entire Shipment']"); ////*[contains(text(),'Convert']
	public static By shipmentItemName = By.id("outItemNumb.1");
	public static By ordInfItemNo=By.id("outItemNumber.1");
	public static By ordInfItemDesc=By.id("outSbfItemDesc3.1");
	public static By ordInfBackOrd = By.id("outQtyBackorder.1");
	public static By ordInfQty=By.id("outSbfQtyOrd.1");
	public static By rgtClk = By.id("My Account.1");
	public static By crtPcsOrd = By.xpath("//td[text()='Create Purchase Order']");
	public static By cntEntShp = By.xpath("//td[text()='Convert Entire Shipment']");
	public static By contextOptionDeleteItem = By.xpath("//td[text()='Delete Item From Shipment']");
	public static By updateShipmentBtn = By.id("btnSave");
	public static By maintainShipmentBtn = By.id("btnCreateNewShipment");
	public static By createShipmentDeliveryTypeDropDown = By.id("slbDeliveryType");
	public static By selectItemCheckBox = By.id("cbxSelectLine.1");
	public static By qtyToShiptxtBox = By.id("inQtySchedule.1");
	public static By btnCreateShipment = By.id("btnAddGoToNew");
	public static By backOrder=By.id("inQtyBO.1");
	//public static By btnSaveExitShipment = By.id("btnSaveExit");

	//payments tab
	public static By paymentMethodDropdown = By.id("PIpaymentMethod1");
	public static By btnApplyPayment = By.id("applyPmtBtn1");
	public static By btnSaveExitPayment = By.id("btnSaveExit");

	//image invoice
	public static  By invoiceImage=By.id("ImgInvoice");
	public static By continuebtn=By.id("btnContinue");

	public static By btnExitSalesOrderSummary=By.id("btnExit");

    // Order INformation Page (Landing Page)
	public static By loadSalesOrder= By.xpath("//div[@id='lblSalesOrderNumber']//following-sibling::div[2]/i");
	public  static By orderStatus=By.xpath("//select[@id='SelectOrderStatusDescription']");

	/********************************************* CREATE PURCHASE ORDER popup *********************************************/

	public static By hdrCreatePO=By.xpath("//h3[text()='Create Purchase Order']");
	public static By btnNext=By.id("btnNext");
	public static By btnBack=By.id("btnBack");
	public static By btnCancel=By.id("btnCancel");
	public static By searchBtn=By.id("imgVendorSearch");
	public static By hdrBuildPO=By.xpath("//h3[text()='Build Purchase Order for All Items on a Shipment']");
	public static By tbxVendorNumber = By.id("inVendorNumber");
	public static By ddnFreightCode = By.id("slbFreightCode"); //Full Frt Allowed/ NO Frt Chrg.
	public static By btnCreatePO = By.id("btnAdd");
	public static By txtPONumber = By.id("outPONumber.1");
	public static By relatedPO=By.id("Related_PO.1");
	public static By hdrRelatedPO=By.xpath("//h3[text()='Related Purchase Orders']");

}
