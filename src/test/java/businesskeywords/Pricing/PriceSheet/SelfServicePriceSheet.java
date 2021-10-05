package businesskeywords.Pricing.PriceSheet;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import commonkeywords.CommonActions;
import org.openqa.selenium.WebElement;
import pages.SalesOrders.SalesOrdersPage;
import pages.pricing.PriceSheet.PriceSheetDetails;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
import pages.pricing.SpecialPricePage;
import supportLibraries.Utility_Functions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class SelfServicePriceSheet extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    CommonActions commonObj;
   // String strDate;
    static String code;

    public SelfServicePriceSheet(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    public void navigateToSelfServicePriceSheet()
    {
          click(SelfServicePriceSheetPage.companySelector);
          click(SelfServicePriceSheetPage.companyLabel);
          sendKey(SelfServicePriceSheetPage.winCompanyNumber,"99599");
          commonObj.validateText(SelfServicePriceSheetPage.selectButton,"Select","Validating Select button");
          click(SelfServicePriceSheetPage.selectButton);
          commonObj.validateText(SelfServicePriceSheetPage.headerTitle, "SELF SERVICE PRICE SHEETS","Validating Landing page title");

    }

    public String generateDate()
    {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy");
        String strDate= formatter.format(dt);
        System.out.println(strDate);
        Utility_Functions.xUpdateJson("priceSheetEffDate",strDate);
        return strDate;
    }

    public void codeGen()
    {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        Utility_Functions.xUpdateJson("priceSheetCode",generatedString);
        System.out.println(generatedString);
    }



    public void  addPriceSheetDetails()
    {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strPriceDate= formatter.format(dt);
        System.out.println(strPriceDate);

        codeGen();




        click(SelfServicePriceSheetPage.addPriceSheetbtn);
        autoComplete(SelfServicePriceSheetPage.manufacturer,"koh",SelfServicePriceSheetPage.manufacturerList,jsonData.getData("Manufacturer"));
        sendKey(SelfServicePriceSheetPage.priceSheetName,jsonData.getData("SheetName"));
       // sendKeyDate(SelfServicePriceSheetPage.effectiveDate,strPriceDate);
        Utility_Functions.xSendkeysAndTab(driver.findElement(SelfServicePriceSheetPage.effectiveDate),strPriceDate);
        sendKey(SelfServicePriceSheetPage.priceSheetCode,Utility_Functions.xGetJsonData("priceSheetCode"));
        click(SelfServicePriceSheetPage.choosePriceSheet);
        Utility_Functions.xUploadFile(report,"C:\\Users\\Subha\\Downloads\\CostPriceSheetTemplate.xlsx");
        click(SelfServicePriceSheetPage.saveUpload);
        commonObj.validateText(SelfServicePriceSheetPage.successMessage,"Price Sheet successfully uploaded","upload Successful");
    }

    public  void validateUpload()
    {
      String strdate=generateDate();;
      commonObj.validateText(SelfServicePriceSheetPage.uploadedDataName,jsonData.getData("SheetName"),"Name Matched");
      commonObj.validateText(SelfServicePriceSheetPage.uploadedDataManufacturer,jsonData.getData("Manufacturer"),"Manufacturer Matched");
      commonObj.validateText(SelfServicePriceSheetPage.uploadedDataCode,strdate+"-"+Utility_Functions.xGetJsonData("priceSheetCode"),"Code Matched");
      commonObj.validateText(SelfServicePriceSheetPage.uploadedDataStatus,"available","Status Matched");
    }

    public void navigateToPriceSheetDetailsPage()
    {
        click(SelfServicePriceSheetPage.uploadedDataName);
        commonObj.validateText(PriceSheetDetails.detailsPageHeader,"Price Sheet Details","inside Details Page");

    }

    public void verifyUploadedSheetDetails()
    {
     commonObj.validateText(PriceSheetDetails.codeValue,Utility_Functions.xGetJsonData("priceSheetEffDate")+"-"+Utility_Functions.xGetJsonData("priceSheetCode"),"code Matched");
     commonObj.validateText(PriceSheetDetails.manufacturerValue,jsonData.getData("Manufacturer"),"Manufacturer Matched");
     commonObj.validateText(PriceSheetDetails.statusValue,"Available","Status Matched");
     String temp=driver.findElement(PriceSheetDetails.nameValue).getAttribute("value");
     System.out.println("The innertext is "+temp);
    // commonObj.validateText(PriceSheetDetails.nameValue,temp,"name matched");
     if(temp.equalsIgnoreCase(jsonData.getData("SheetName")))
     {
         report.updateTestLog("VerifyRecord", "Name Matched", Status.PASS);
     }else
     {
         report.updateTestLog("VerifyRecord", "Name Mis-Matched", Status.FAIL);
     }

    }
}
