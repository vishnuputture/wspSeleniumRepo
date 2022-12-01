package businesskeywords.warehousing.Manifest;

import businesskeywords.warehousing.Objects.*;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import pages.warehouse.DriverAppPage;

public class DriverApp extends ReusableLib {
    private final CommonActions commonObj;
    private final FrameworkDriver ownDriver;

    public DriverApp(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    public void loginToDriverApp(User user) {
        String username = "WZTEST" + user.getCompany() + "A";
        String password = user.getDailyPassword("Prod");
        String URL = "http://lcoservicedev.winwholesale.com/shipping-manifest-mobile/#/auth";
        ownDriver.get(URL);
        sendKeysAndTab(DriverAppPage.username, username, "Inputting Username");
        sendKeys(DriverAppPage.password, password, "Inputting Password");
        click(DriverAppPage.loginButton, "Attempting to login...");
    }

    public void verifyManifestCard(Manifest manifest) {
        String manifestId = manifest.getId();
        String truck = manifest.getTruck();
        String time = manifest.getTime().replaceFirst("^0+(?!$)", "");
        String dateAndTime = manifest.getDate() + " " + time;
        commonObj.validateText(By.xpath("//ion-col[normalize-space()='"+ manifestId +"']"),
                manifestId, "Validating Manifest Number");
        commonObj.validateText(By.xpath("(//ion-col[contains(text(),'"+ truck +"')])[1]"),
                truck, "Validating Truck");
        commonObj.validateText(By.xpath("(//ion-col[normalize-space()='"+ dateAndTime +"'])[1]"),//(//ion-col[normalize-space()='11/18/2022 4:30 PM'])[1]
                dateAndTime, "Validating Date and Time");                                   //(//ion-col[normalize-space()='11/18/2022 04:30 PM'])[1]
    }

    public void openManifest(Manifest manifest) {
        String manifestId = manifest.getId();
        click(By.xpath("//ion-col[normalize-space()='"+ manifestId +"']"), "Opening Manifest");
    }

    public void validateStops(Manifest manifest) {
        for (Stop stop : manifest.getStops()) {
            boolean isShipment = stop.getOrderType().equalsIgnoreCase("SO");
            int stopId = manifest.getStops().indexOf(stop) + 1;
            String company, addressLine1, addressLine2, addressLine3, addressLine4;

            commonObj.validateText(By.xpath("//div[normalize-space()='Stop #"+ stopId +"']"),
                    "Stop #"+ stopId, "Validating Stop Number");
            if (isShipment) {
                Shipment shipment = stop.getShipments().get(0);
                company = shipment.getBillTo();
                addressLine1 =  shipment.getName();
                addressLine2 = shipment.getAddressLine1();
                addressLine3 = shipment.getAddressLine2();
                addressLine4 = shipment.getCity() + ", " + shipment.getState() + " " + shipment.getZip();
            } else {
                PurchaseOrder purchaseOrder = stop.getPurchaseOrders().get(0);
                company = purchaseOrder.getVendor();
                addressLine1 = purchaseOrder.getAddressLine1();
                addressLine2 = purchaseOrder.getAddressLine2();
                addressLine3 = purchaseOrder.getAddressLine3();
                addressLine4 = purchaseOrder.getCity() + ", " + purchaseOrder.getState() + " " + purchaseOrder.getZip();
            }
            commonObj.validateText(By.xpath("//div[normalize-space()='"+ company +"']"),
                    company, "Validating Address");
            commonObj.validateText(By.xpath("//div[normalize-space()='"+ addressLine1 +"']"),
                    addressLine1, "Validating Address");
            commonObj.validateText(By.xpath("//div[normalize-space()='"+ addressLine2 +"']"),
                    addressLine2, "Validating Address");
            if (addressLine3 != null) {
                commonObj.validateText(By.xpath("//div[normalize-space()='" + addressLine3 + "']"),
                        addressLine3, "Validating Address");
            }
            commonObj.validateText(By.xpath("//div[normalize-space()='"+ addressLine4 +"']"),
                    addressLine4, "Validating Address");
        }
    }
}
