package businesskeywords.PurchaseOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import pages.PurchaseOrders.*;
import pages.inventory.ItemMasterPage;
import supportLibraries.Utility_Functions;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class CreatePurchaseOrder extends ReusableLib {
    CommonActions commonObj;
    public static    String  customeraddress1;
    public static    String  customeraddress2;
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

    public void findVendorWithoutFreightCharges() {
        click(PurchaseOrderEntryPage.vendorNo, "Click on Vendor Number");
        sendKeys(PurchaseOrderEntryPage.firstVendor, "1" + Keys.ENTER, "Selecting the First Vendor in the search");
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
    public void applyCostPriceAndDisc(String costoption,String quantity,String discount)
    {
        click(PurchaseOrderEntryPage.vendorNo,"Click on Vendor Number");
        sendKeys(PurchaseOrderEntryPage.firstVendor,"1"+Keys.ENTER,"Selecting the First Vendor in the search"  );
        sendKeys(PurchaseOrderEntryPage.enterFreightCharges,"FFA","Entered FFA Frieght Code");
        //selectListPriceCost();
        sendKeys(PurchaseOrderEntryPage.costOption,costoption+Keys.ENTER,"Entered Cost Price Option ");
        sendKeys(PurchaseOrderDetailsPage.itemNumberPOD, String.valueOf(Keys.F4),"F4 Prompt on Item Number");
        sendKeys(PurchaseOrderDetailsPage.firstItemNumber,"1"+Keys.ENTER,"Selecting First Item Number");
        sendKeys(PurchaseOrderDetailsPage.quantityOrdered,quantity+Keys.ENTER,"Entered  Quantity Order");
        Utility_Functions.timeWait(5);
        sendKeys(PurchaseOrderDetailsPage.disc,discount+Keys.ENTER,"Entered Disc");
        Utility_Functions.timeWait(5);

        if(Double.parseDouble(discount)>0) {

            double extAmtCalc = Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.quantityOrdered).getAttribute("value").trim())
                    * Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.pricePOD).getAttribute("value").trim())
                    * (1 - (Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.disc).getAttribute("value").trim())) / 100);
            Utility_Functions.timeWait(5);
            double avgPrice = Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.pricePOD).getAttribute("value").trim());
            String formattedavgPrice = String.format("%.4f", avgPrice);

            commonObj.validateText(PurchaseOrderDetailsPage.errorMsgPOD, "WARNING- Cost variance -- F5 to accept.  Average cost is       " +
                    formattedavgPrice, "Verifying Cost variance validation");
            String extendedAmt = String.valueOf(Math.abs(Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.getExtendedAmountPOD).getAttribute("value").trim())));


            Utility_Functions.xAssertEquals(report, String.valueOf(extAmtCalc), extendedAmt);
        }
        else
        {
                        double extAmtCalc = Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.quantityOrdered).getAttribute("value").trim())
                    * Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.pricePOD).getAttribute("value").trim());
            Utility_Functions.timeWait(5);
            double avgPrice = Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.pricePOD).getAttribute("value").trim());
            String formattedavgPrice = String.format("%.4f", avgPrice);

            String extendedAmt = String.valueOf(Math.abs(Double.parseDouble(driver.findElement(PurchaseOrderDetailsPage.getExtendedAmountPOD).getAttribute("value").trim())));


            Utility_Functions.xAssertEquals(report, String.valueOf(extAmtCalc), extendedAmt);

        }
    }
     public  void addPOWithListPriceAndDisc()
     {
    applyCostPriceAndDisc(jsonData.getData("listCostOption"), jsonData.getData("quantityOrdered"),
            jsonData.getData("discount"));



}
    public  void addPOWithLastPriceAndDisc()
    {
        applyCostPriceAndDisc(jsonData.getData("lastCostOption"), jsonData.getData("quantityOrdered"),
                jsonData.getData("discount"));
        sendKeys(PurchaseOrderDetailsPage.extendedAmount,String.valueOf(Keys.F5));
        Utility_Functions.timeWait(5);
    }

    public void addPOWithLastPriceWithoutDisc()
    {
        sendKeys(PurchaseOrderDetailsPage.itemNumberPOD, String.valueOf(Keys.F4),"F4 Prompt on Item Number");
        sendKeys(PurchaseOrderDetailsPage.secondItemNumber,"1"+Keys.ENTER,"Selecting First Item Number");
        Utility_Functions.timeWait(5);
        sendKeys(PurchaseOrderDetailsPage.quantityOrdered,jsonData.getData("secondQuantityOrdered")+Keys.ENTER,"Entered  Quantity Order");
        Utility_Functions.timeWait(5);
        sendKeys(PurchaseOrderDetailsPage.extendedAmount,String.valueOf(Keys.ENTER));
        Utility_Functions.timeWait(5);

        Utility_Functions.xWaitForElementPresent(driver,driver.findElements(PurchaseOrderDetailsPage.getLineItemsList),5);

        List<WebElement> items=driver.findElements(PurchaseOrderDetailsPage.getLineItemsList);

        double itemsAmt=0;
        for(WebElement e:items) {
            String itemValues[] =(e.getAttribute("outerText")).trim().split("[^A-Za-z0-9.]+");

            String formattedPrice=String.format("%.2f", Double.parseDouble(itemValues[itemValues.length-2]));
            itemsAmt+=Double.parseDouble(formattedPrice);
        }
        Utility_Functions.timeWait(5);
        Utility_Functions.getText(driver,PurchaseOrderDetailsPage.amountHeader);
       String totalAmtHeader= Utility_Functions.getText(driver,PurchaseOrderDetailsPage.amountHeader).trim();
       Utility_Functions.xAssertEquals(report,String.format("%.2f",itemsAmt),totalAmtHeader);
    }


    public void addDuplicateLineItem()
    {
        click(PurchaseOrderEntryPage.vendorNo,"Click on Vendor Number");
        sendKeys(PurchaseOrderEntryPage.firstVendor,"1"+Keys.ENTER,"Selecting the First Vendor in the search"  );
        sendKeys(PurchaseOrderEntryPage.enterFreightCharges,"FFA","Entered FFA Frieght Code");
        sendKeys(PurchaseOrderEntryPage.costOption,jsonData.getData("lastCostOption")+Keys.ENTER,"Entered Cost Price Option ");
        Utility_Functions.timeWait(5);
        addPOWithLastPriceWithoutDisc();
        Utility_Functions.timeWait(5);
        addPOWithLastPriceWithoutDisc();

    }

    public  void enquireOrderNo()
    {
        sendKeys(PurchaseOrderEntryPage.actionInpput,"I","Changing Action to I");
        sendKeys(PurchaseOrderEntryPage.orderNoInput,jsonData.getData("orderNo")+Keys.ENTER,"Enter Order Order");
        Utility_Functions.timeWait(5);
    }

    public void validateCostOptionFunc()
    {
       click(PurchaseOrderEntryPage.costOptionLink);
      Utility_Functions.timeWait(5);
      commonObj.validateText(CostOptionspage.costOptionHeader,"Cost Option Codes","Validate Cost Option Pop-up");

      sendKeys(CostOptionspage.postionTo,jsonData.getData("invalidCostOption")+Keys.ENTER,"Enter invalid Cost Code");
        Utility_Functions.timeWait(5);
      commonObj.validateText(CostOptionspage.erroMsg,"No Code entries to display","Validate Incorrect CostOption");

      clearText( CostOptionspage.postionTo);
        sendKeys(CostOptionspage.postionTo,jsonData.getData("invalidCostOptionSpecialChar")+Keys.ENTER,"Enter invalid Cost Code with special Characters");
        Utility_Functions.timeWait(5);
        commonObj.validateText(CostOptionspage.erroMsg,"No Code entries to display","Validate Incorrect CostOption");
        Utility_Functions.timeWait(5);
        clearText( CostOptionspage.postionTo);
        Utility_Functions.timeWait(5);
        sendKeys(CostOptionspage.postionTo,jsonData.getData("validCostOption")+Keys.ENTER,"Enter valid Cost Code with special Characters");
        Utility_Functions.timeWait(5);
        sendKeys(CostOptionspage.codeSelectinput,"1"+Keys.ENTER,"E");
        Utility_Functions.timeWait(5);
        commonObj.validateText(PurchaseOrderEntryPage.poHeaderTitle,"Purchase Order Headings","Validating Entry - Purchase Order");

    }

    public  void selectFirstCustomer()
    {
        clearText(PurchaseOrderEntryPage.customerNoInput);
        clearText(PurchaseOrderEntryPage.orderNoInput);
        click(PurchaseOrderEntryPage.customerNo);
        Utility_Functions.timeWait(5);

    customeraddress1 = Utility_Functions.getText(driver,MailingMasterSearchPage.firstaddressLine1,"innerText").toLowerCase().replace(" ","").trim();
     customeraddress2=Utility_Functions.getText(driver,MailingMasterSearchPage.firstaddressLine2,"innerText").toLowerCase().replace(" ","").trim();
        sendKeys(MailingMasterSearchPage.firstCustomerSelect, "1"+Keys.ENTER,"Select Customer No");
        Utility_Functions.timeWait(5);

        commonObj.validateText(PurchaseOrderEntryPage.errorMsgPO,"ERROR - Vendor or Order Number cannot be blank"," Validate Vendor Or Order blank after Customer selection");

        Utility_Functions.timeWait(2);
        Assert.assertNotNull(driver.findElement(PurchaseOrderEntryPage.orderNoInput).getAttribute("value"),"Validate Order No is auto populated");

    }

    public void addPOWithPOPriceAndDisc()
    {
      applyCostPriceAndDisc(jsonData.getData("POCostOption"), jsonData.getData("quantityOrdered"),
              jsonData.getData("discount"));
      sendKeys(PurchaseOrderDetailsPage.extendedAmount,String.valueOf(Keys.F5));
      Utility_Functions.timeWait(5);
      Utility_Functions.xWaitForElementPresent(driver,driver.findElements(PurchaseOrderDetailsPage.getLineItemsList),5);

      Utility_Functions.xIsElementDisplayed(report, driver.findElement(PurchaseOrderDetailsPage.getLineItemsList),"Added Line Item displayed");


  }

    public void validateShipmentType()
    {
      System.out.println("Customer Page Address One="+CreatePurchaseOrder.customeraddress1);
      System.out.println("Customer Page Address Two="+CreatePurchaseOrder.customeraddress2);
      String shipto=Utility_Functions.getText(driver,PurchaseOrderEntryPage.shipToinput,"value").replace(" ","").toLowerCase().trim();
      String toaddress=Utility_Functions.getText(driver,PurchaseOrderEntryPage.toaddressLine1,"value").replace(" ","").toLowerCase().trim();

      System.out.println("PO Order Ship Address1="+shipto);
      System.out.println("PO Order To Address1="+toaddress);

         //Assert.assertTrue(CreatePurchaseOrder.customeraddress1.contains(shipto));
         //Assert.assertTrue(CreatePurchaseOrder.customeraddress2.contains(toaddress));
          Utility_Functions.xAssertEquals(report,CreatePurchaseOrder.customeraddress1,shipto);
      Utility_Functions.xAssertEquals(report,CreatePurchaseOrder.customeraddress2,toaddress);

  }

    public void  addPOWithListPriceWithoutDisc()
    {
        applyCostPriceAndDisc(jsonData.getData("listCostOption"), jsonData.getData("quantityOrdered"),jsonData.getData("nodiscount"));
        sendKeys(PurchaseOrderDetailsPage.extendedAmount,String.valueOf(Keys.ENTER));
        Utility_Functions.timeWait(8);
        System.out.println("Validation Msg"+Utility_Functions.getText(driver.findElement(PurchaseOrderDetailsPage.errorMsgPOD),"outerText"));

    //    commonObj.validateText(PurchaseOrderDetailsPage.errorMsgPOD," ERROR - Discount can NOT be blank when using list price F4 Overrides","No Discount validation");
       String message=Utility_Functions.getText(driver.findElement(PurchaseOrderDetailsPage.errorMsgPOD),"outerText").replace(" "," ").trim();
        Utility_Functions.xAssertEquals(report,"ERROR - Discount can NOT be blank when using list price F4 Overrides",message,
                "No Discount validation");
    }

    public void validateFrieghtCharges()
    {
        click(PurchaseOrderEntryPage.freightCharges,"Click on Frieght Charges Hyperlink");
         Utility_Functions.timeWait(5);

         sendKeys(FrieghtChargesPage.positionTo,jsonData.getData("invalidFrieght")+Keys.ENTER,"Enter Invalid Frieght Code");
        Utility_Functions.timeWait(5);
        commonObj.validateText(FrieghtChargesPage.errMsg,"No Code entries to display","Validate FriegntCharges message for invalid code");
        clearText(FrieghtChargesPage.positionTo);

        Utility_Functions.timeWait(5);

        sendKeys(FrieghtChargesPage.positionTo,jsonData.getData("invalidFrieghtSpecialChar")+Keys.ENTER,"Enter Invalid Frieght Code with Special character");
        Utility_Functions.timeWait(5);
        commonObj.validateText(FrieghtChargesPage.errMsg,"No Code entries to display","Validate FriegntCharges message for invalid code with Special Characters");

        clearText(FrieghtChargesPage.positionTo);
        sendKeys(FrieghtChargesPage.positionTo,jsonData.getData("validFrieght")+Keys.ENTER,"Enter Invalid Frieght Code with Special character");
        Utility_Functions.timeWait(5);

        sendKeys(FrieghtChargesPage.selectfirstCode,"1"+Keys.ENTER,"Selecting First Code FFA");
        Utility_Functions.timeWait(5);


        Utility_Functions.xAssertEquals(report,jsonData.getData("validFrieght"),driver.findElement(PurchaseOrderEntryPage.enterFreightCharges),
                  "value","Validate selected Frieght FFA");


    }

}