package businesskeywords.Pricing.SpecialPricing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import commonkeywords.DBCall;
import org.openqa.selenium.NoSuchElementException;
import pages.pricing.AddSpecialPricingPage;
import supportLibraries.Utility_Functions;

import java.util.List;

public class AddSpecialPrice extends ReusableLib {

    CommonActions commonObj;
    AddSpecialPricingPage pageObj;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public AddSpecialPrice(Helper helper) {

        super(helper);
        commonObj = new CommonActions(helper);
        pageObj = new AddSpecialPricingPage(helper);
    }

    /**
     * This method call for random customer number and item number from DB
     */

    public void getCustItemData() {
        List<String> data = DBCall.getWISEDATA();

        Utility_Functions.xUpdateJson("CustomerNo", data.get(0));
        Utility_Functions.xUpdateJson("ItemNo", data.get(1));

    }

    /**
     * This method validates the title of Special Price Page
     *
     * @throws NoSuchElementException
     */
    public void validateAddSpecialPriceTitle() throws NoSuchElementException {

        commonObj.masterToOrderProcessing();
        commonObj.orderProcToSplPricing();
        commonObj.splPricingToAddPricing();


        commonObj.validateText(AddSpecialPricingPage.addSpecialPriceTitle, "Add/Maintain Special Pricing", "Validating add special price page title");
    }

    /**
     * This method tests the error message which is displayed on the screen when an user enters invalid customer number
     */

    public void enterInvalidcustNum() {

       /* commonObj.masterToOrderProcessing();
        commonObj.orderProcToSplPricing();
        commonObj.splPricingToAddPricing();
        commonObj.validateText(AddSpecialPricingPage.addSpecialPriceTitle, "Add/Maintain Special Pricing", "Validating add special price page title");*/
        validateAddSpecialPriceTitle();
        pageObj.enterCustNumber(jsonData.getData("InvalidCustNum"), "Entering invalid customer number");
       /* sendKeys(AddSpecialPricingPage.custNumTxtBox, jsonData.getData("InvalidCustNum"), "Entering invalid customer number");
        Utility_Functions.actionKey(Keys.ENTER, driver);*/

        commonObj.validateText(AddSpecialPricingPage.validationLbl, "Customer Number does not exist", "Validating message for invalid customer number");

    }

    /**
     * This method tests the error message which is displayed on the screen when an user enters invalid item number
     */

    public void enterInvaliditemNum() {

       /* commonObj.masterToOrderProcessing();
        commonObj.orderProcToSplPricing();
        commonObj.splPricingToAddPricing();
        commonObj.validateText(AddSpecialPricingPage.addSpecialPriceTitle, "Add/Maintain Special Pricing", "Validating add special price page title");*/
        validateAddSpecialPriceTitle();
        /*sendKeys(AddSpecialPricingPage.custNumTxtBox, Utility_Functions.xGetJsonAsString("CustomerNo"), "Entering customer number");
        sendKeys(AddSpecialPricingPage.itemNumTxtBox, jsonData.getData("InvalidItemNum"), "Entering invalid item number");
        Utility_Functions.actionKey(Keys.ENTER, driver);*/
        pageObj.enterCustItemNumber(Utility_Functions.xGetJsonAsString("CustomerNo"), "Entering customer number", jsonData.getData("InvalidItemNum"), "Entering invalid item number");

        commonObj.validateText(AddSpecialPricingPage.validationLbl, "Item Number does not exist", "Validating message for invalid item number");


    }

    /**
     * This method is invoked to exit out of Special Price Screen
     */

    public void addAndExitSpecialPrice() {


        try {
            commonObj.validateText(AddSpecialPricingPage.addSpecialPriceTitle, "Add/Maintain Special Pricing", "Validating add special price page title");
        } catch (NoSuchElementException e) {
           /* commonObj.masterToOrderProcessing();
            commonObj.orderProcToSplPricing();
            commonObj.splPricingToAddPricing();

            commonObj.validateText(AddSpecialPricingPage.addSpecialPriceTitle, "Add/Maintain Special Pricing", "Validating add special price page title");*/
            validateAddSpecialPriceTitle();
        }
        String custNo = Utility_Functions.xGetJsonAsString("CustomerNo");
        String itemNo = Utility_Functions.xGetJsonAsString("ItemNo");
        if (custNo == null || itemNo == null) {
            List<String> data = DBCall.getWISEDATA();

            Utility_Functions.xUpdateJson("CustomerNo", data.get(0));
            Utility_Functions.xUpdateJson("ItemNo", data.get(1));
            custNo = Utility_Functions.xGetJsonAsString("CustomerNo");
            itemNo = Utility_Functions.xGetJsonAsString("ItemNo");
        }
        commonObj.addSpecialPriceRecord(custNo, itemNo);
        commonObj.exitAddSpecialPricingToSplPrice();
    }

    /**
     * This method adds multiple special price records and exits the page after records are added successfully
     */

    public void addAndExitMultipleSpecialPrice() {
        try {
            commonObj.validateText(AddSpecialPricingPage.addSpecialPriceTitle, "Add/Maintain Special Pricing", "Validating add special price page title");
        } catch (NoSuchElementException e) {

            commonObj.splPricingToAddPricing();

            commonObj.validateText(AddSpecialPricingPage.addSpecialPriceTitle, "Add/Maintain Special Pricing", "Validating add special price page title");
        }


        String[] custNumList = jsonData.getData("validCustNum").split(",");
        String[] itemNumList = jsonData.getData("validItemNum").split(",");

        for (int i = 0; i < custNumList.length; i++) {
            commonObj.addSpecialPriceRecord(custNumList[i], itemNumList[i]);
        }
        commonObj.exitAddSpecialPricingToSplPrice();


    }

    /**
     * This method add a special price record
     * Has a date method which takes a calendar date and generates a date in MM/dd/yy format and attaches it to the record for expiry date
     */

   /* public void addSpecialPriceRecord() {
        sendKeys(AddSpecialPricingPage.custNumTxtBox, jsonData.getData("validCustNum"), "Entering customer number");
        sendKeys(AddSpecialPricingPage.itemNumTxtBox, jsonData.getData("itemNum"), "Entering item number");
        sendKeys(AddSpecialPricingPage.specialPriceTxtBox, jsonData.getData("specialPrice"), "Entering special price");

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        String strDate = formatter.format(dt);
        System.out.println(strDate);

        sendKeys(AddSpecialPricingPage.expDateTxtBox, strDate, "Entering date");
        Utility_Functions.actionKey(Keys.ENTER, driver);

        if (Utility_Functions.xWaitForElementPresent(driver, AddSpecialPricingPage.successLbl, 5)) {
            String successMessage = Utility_Functions.getText(driver, AddSpecialPricingPage.successLbl);
            System.out.println("Text: " + successMessage);
            Utility_Functions.xAssertEquals(report, jsonData.getData("success"), successMessage.trim(), "Validating success message");
        } else {
            System.out.println("Text: Not found");
            throw new NoSuchElementException("Could not find :" + AddSpecialPricingPage.successLbl);
        }
    }*/

    /**
     * This methods is invoked to navigate nack to master page from special price page
     */

    public void exitSplPriceToMaster() {


        commonObj.exitAddSpecialPricingToSplPrice();
        commonObj.exitSplPriceToMasterPage();
    }


}
