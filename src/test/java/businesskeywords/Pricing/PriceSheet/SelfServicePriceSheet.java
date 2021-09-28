package businesskeywords.Pricing.PriceSheet;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import pages.pricing.PriceSheet.SelfServicePriceSheetPage;
import pages.pricing.SpecialPricePage;
import supportLibraries.Utility_Functions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SelfServicePriceSheet extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    CommonActions commonObj;

    public SelfServicePriceSheet(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    public void navigateToSelfServicePriceSheet()
    {
          click(SelfServicePriceSheetPage.companySelector);
          click(SelfServicePriceSheetPage.companyLabel);
          sendKey(SelfServicePriceSheetPage.winCompanyNumber,"99599");
          click(SelfServicePriceSheetPage.selectButton);
          commonObj.validateText(SelfServicePriceSheetPage.headerTitle, "SELF SERVICE PRICE SHEETS","Validating Landing page title");

    }

    public void  addPriceSheetDetails()
    {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate= formatter.format(dt);
        System.out.println(strDate);

        click(SelfServicePriceSheetPage.addPriceSheetbtn);
        autoComplete(SelfServicePriceSheetPage.manufacturer,"koh",SelfServicePriceSheetPage.manufacturerList,"KOHLER CO");
        sendKey(SelfServicePriceSheetPage.pricesheetName,"TestAutomation001");
        sendKey(SelfServicePriceSheetPage.effectiveDtae,strDate);
        sendKey(SelfServicePriceSheetPage.priceSheetCode,"code001");
        click(SelfServicePriceSheetPage.choosePriceSheet);
       // sendKey();
        Utility_Functions.xUploadFile(report,"C:\\Users\\Subha\\Downloads\\CostPriceSheetTemplate.xlsx");
        click(SelfServicePriceSheetPage.saveUpload);
    }
}
