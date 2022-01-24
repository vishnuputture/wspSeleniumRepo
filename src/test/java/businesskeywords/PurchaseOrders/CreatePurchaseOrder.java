package businesskeywords.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import pages.PurchaseOrders.PurchaseOrderDetailsPage;
import pages.PurchaseOrders.PurchaseOrderEntryPage;
import pages.PurchaseOrders.VendorNotesPage;
import supportLibraries.Utility_Functions;

public class CreatePurchaseOrder extends ReusableLib {
    CommonActions commonObj;

    public CreatePurchaseOrder(Helper helper) {

        super(helper);
        commonObj = new CommonActions(helper);
    }

    public void validatePOHeadingTitle()  {

        commonObj.masterToPurchaseOrder();
        commonObj.navigateToPurchaseOrderEntry();

    }

    public  void validatePOEntryUI(){

        commonObj.validateElementExists(PurchaseOrderEntryPage.orderNo,"Order Number  is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.customerNo,"Customer Number is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.vendorNo,"Vendor Number is present");
        commonObj.validateElementExists(PurchaseOrderEntryPage.typeShipment,"Type Shipment is present");
    }

   public void findVendor(){
        click(PurchaseOrderEntryPage.vendorNo,"Click on Vendor Number");
        sendKeys(PurchaseOrderEntryPage.firstVendor,"1"+Keys.ENTER,"Selecting the First Vendor in the search"  );
       sendKeys(PurchaseOrderEntryPage.enterFreightCharges,"FFA"+Keys.ENTER,"Entered FFA Frieght Code");

   }

   public  void validatePODetailsUI(){

        commonObj.validateElementExists(PurchaseOrderDetailsPage.action1,"Action is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.ediStat,"EDI Stat  is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.orderno,"Order No is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.lineNo,"Line No is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.vendor,"Vendor is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.shipVia,"Ship Via is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.customerPOD,"Customer is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.quantityOrdered,"Quantity Ordered is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.quantityOpen,"Quantity Opened is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.itemNumberPOD,"Item Number is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.pricePOD,"Price is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.umPOD,"UM is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.disc,"Disc is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.extendedAmount,"Extended Amount is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.descPOD,"Desc is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.line2,"Line 2 is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.vendorPart,"Vendor Part is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.relatedSo,"Related SO is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.datePOD,"Date  is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.weightPOD,"Weight is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.pkgQty,"Pkg Qty is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.convFactor,"Conv Factor is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.sendViaFax,"Send Via Fax is present");
       commonObj.validateElementExists(PurchaseOrderDetailsPage.tyPOD,"TY  is present");

   }

   public void noVendorNoOrderError(By ele, String errMsg)
   {
       clearText(PurchaseOrderEntryPage.orderNoInput);
       clearText(PurchaseOrderEntryPage.vendorNoInput);
       sendKeys(PurchaseOrderEntryPage.vendorNoInput, String.valueOf(Keys.ENTER),"Entering blank Vendor no & Ordder no");
       Utility_Functions.timeWait(5);
       commonObj.validateText(ele,errMsg,"Error message: " + errMsg + "");
   }

  public void alphNumVendorNoError(By ele, String errMsg)
  {
      clearText(PurchaseOrderEntryPage.orderNoInput);
      clearText(PurchaseOrderEntryPage.vendorNoInput);
      sendKeys(PurchaseOrderEntryPage.vendorNoInput,"!@#$%^&*()123"+ String.valueOf(Keys.ENTER),"Entering alphanumeric Vendor No having special characters");
      Utility_Functions.timeWait(5);
      commonObj.validateText(ele,errMsg,"Error message: " + errMsg + "");
  }

   public void validateVendorNo()
   {
       noVendorNoOrderError(PurchaseOrderEntryPage.errorMsgPO,"ERROR - Vendor or Order Number cannot be blank") ;
       alphNumVendorNoError(PurchaseOrderEntryPage.errorMsgPO,"ERROR - Vendor or Customer Number was not found");
   }

   public void invalidOrderNoErrror(By ele, String errMsg)
   {
   sendKeys(PurchaseOrderEntryPage.vendorNoInput,"000");
   sendKeys(PurchaseOrderEntryPage.customerNoInput,"000");
   sendKeys(PurchaseOrderEntryPage.orderNoInput,"000"+ String.valueOf(Keys.ENTER),"Entering invalid Order No ");
   Utility_Functions.timeWait(5);
   commonObj.validateText(ele,errMsg,"Error message: " + errMsg + "");
   }

   public void alphaNumOrderNoError(By ele, String errMsg)
   {
       sendKeys(PurchaseOrderEntryPage.vendorNoInput,"000");
       sendKeys(PurchaseOrderEntryPage.customerNoInput,"000");
       sendKeys(PurchaseOrderEntryPage.orderNoInput,"!@#$%^&*()123"+ String.valueOf(Keys.ENTER),"Entering Alphanumeric  Order No with special characters ");
       Utility_Functions.timeWait(5);
       commonObj.validateText(ele,errMsg,"Error message: " + errMsg + "");
   }


   public void validateOrderNo()
   {
       invalidOrderNoErrror(PurchaseOrderEntryPage.errorMsgPO,"ERROR - Vendor or Customer Number was not found");
       alphaNumOrderNoError(PurchaseOrderEntryPage.errorMsgPO,"ERROR - Vendor or Customer Number was not found");
   }

  public void addVendornotes()
  {
      commonObj.navigateToVendorNotes();
     sendKeys(VendorNotesPage.vendorInputVendorNotes,jsonData.getData("validVendorNo")+Keys.ENTER,"Searching with Vendor No");
     Utility_Functions.timeWait(5);
      sendKeys(VendorNotesPage.actionVendorNotes,"A","Changing Action to A ");
      Utility_Functions.timeWait(3);
      sendKeys(VendorNotesPage.Line1VendorNotes,jsonData.getData("vendorNotes1")+Keys.RETURN,"Entering VendorNotes in Line1");
            Utility_Functions.timeWait(8);
    // commonObj.validateText(VendorNotesPage.actionVendorNotes,"i","Action Code changed to I");
    commonObj.validateText(VendorNotesPage.erroMsgVendorNotes,"V"+jsonData.getData("validVendorNo") +" - Vendor Notes Changed.","Vendor notes changed message");

  }

  public void deleteVendornotes()
  {
      sendKeys(VendorNotesPage.vendorInputVendorNotes,jsonData.getData("validVendorNo")+Keys.ENTER,"Searching with Vendor No");
      Utility_Functions.timeWait(5);
      sendKeys(VendorNotesPage.actionVendorNotes,"D"+Keys.ENTER,"Changing Action to D ");
      Utility_Functions.timeWait(5);
      commonObj.validateText(VendorNotesPage.erroMsgVendorNotes,"V"+jsonData.getData("validVendorNo") +" - All Vendor Notes Will be Deleted.  Press Enter to Delete.",
              "Vendor notes deleted");
      sendKeys(VendorNotesPage.vendorInputVendorNotes, String.valueOf(Keys.ENTER));
      Utility_Functions.timeWait(5);
      commonObj.validateText(VendorNotesPage.vendorInputVendorNotes,"","Verify Line1 Vendor note is deleted and blank");
  }

}