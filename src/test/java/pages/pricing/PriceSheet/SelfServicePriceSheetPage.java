package pages.pricing.PriceSheet;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SelfServicePriceSheetPage extends ReusableLib {


    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public SelfServicePriceSheetPage(Helper helper) {
        super(helper);
    }

    public static By companySelector=By.id("companySelectorToggle");
    public static By companyLabel= By.xpath("//*[text()=' WIN Into Company ']");
    public static By winCompanyNumber= By.cssSelector("div.form-group.company-selection.mb-0>input");
    public static By selectButton=By.cssSelector("div.select-btn-div>button");

    //landing Page

    public static By headerTitle=By.xpath("//h2[text()='SELF SERVICE PRICE SHEETS']");

    public static By addPriceSheetbtn= By.xpath("//button[text()=' Add Price Sheet ']");

    //Modal

    public static By manufacturer= By.xpath("//div[@class='form-group']//input[@role='searchbox']");
    public static By manufacturerList=By.xpath("//ul[@role='listbox']//li");

    public static By pricesheetName= By.xpath("//input[@id='name']");
    public static By effectiveDtae=By.xpath("//div[@class='form-group']//input[@name='date']");
    public static By priceSheetCode=By.xpath("//div[@class='form-group']//input[@formcontrolname='code']");

    public static By choosePriceSheet=By.xpath("//span[text()=' Choose Price Sheet ']");

    public static  By saveUpload = By.xpath("//button[text()='Save & Upload ']");

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

}
