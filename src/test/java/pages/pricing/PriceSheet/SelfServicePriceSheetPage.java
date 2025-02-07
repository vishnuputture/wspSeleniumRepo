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

    public static By companySelector = By.id("companySelectorToggle");
    public static By companyLabel = By.xpath("//*[text()=' WIN Into Company ']");
    public static By winCompanyNumber = By.xpath("//input[@role='searchbox' and contains(@class,'p-autocomplete-input')]");
    public static By selectCompany = By.xpath("//td[contains(@class,'inserted') and text()='99599']");
    public static By selectButton = By.cssSelector("div.select-btn-div>button");

    //landing Page

    public static By headerTitle = By.xpath("//h2[text()='SELF SERVICE PRICE SHEETS']");
    public static By iconI = By.xpath("//span[@class='fa-info_outline']");
    public static By addPriceSheetbtn = By.xpath("//button[text()=' Add Price Sheet ']");
    public static By downloadNewVersion = By.xpath("//a[@download='CostPriceSheetTemplate.xlsx']");
    public static By helpIcon = By.xpath("//a[contains(@class,'help-icon')]");
    public static By startDate = By.id("startDate");
    public static By endDate = By.id("endDate");
    public static By logoutIcon = By.xpath("//i[contains(@class,'log-out-icon')]");
    public static By menuIcon = By.xpath("//i[contains(@class,'menu-icon')]");
    public static By deleteIcon = By.xpath("//td[contains(text(),'TestAuto:')]/parent::tr/descendant::i");
    public static By deleteButton = By.xpath("//button[contains(text(),'Delete')]");
    public static By deletePriceSheetPopup = By.xpath("//span[contains(text(),'Delete Price Sheet?')]");

    //Modal

    public static By manufacturer = By.xpath("//div[@class='form-group']//input[@role='searchbox']");
    public static By manufacturerList = By.xpath("//ul[@role='listbox']//li");

    public static By priceSheetName = By.xpath("//input[@id='name']");
    public static By effectiveDate = By.xpath("//div[@class='form-group']//input[@name='date']");
    public static By priceSheetCode = By.xpath("//div[@class='form-group']//input[@formcontrolname='code']");

    public static By choosePriceSheet = By.xpath("//span[text()=' Choose Price Sheet ']");

    public static By saveUpload = By.xpath("//button[text()='Save & Upload ']");


    public static By successMessage = By.xpath("//div[@role='alert']//div//*[contains(text(),'Sheet')]");
    public static By uploadedDataName = By.xpath("//tbody//tr[1]//td[1]");
    public static By uploadedDataManufacturer = By.xpath("//tbody//tr[1]//td[2]");

    public static By uploadFile = By.xpath("//span[@class='weight file-label']/following::input[@type='file']");
    public static By uploadedDataStatus = By.xpath("//tbody//tr[1]//td[4]");
    public static By poCostMultiplier = By.xpath("//tbody//tr[1]//td[13]");
    public static By netPrice = By.id("netSheet");
    public static By matrixCostMultiplier = By.xpath("//tbody//tr[1]//td[17]");
    public static By listPrice = By.xpath("//tbody//tr[1]//td[9]");
    public static By listPr = By.xpath("//tbody//tr[1]//td[8]");
    public static By firstSearchByName = By.xpath("//tr[@class='ng-star-inserted']/td");
    public static By searchByName = By.xpath("//label[contains(text(),'Search By Name')]/parent::div/descendant::input");
    public static By filterManufacturer = By.xpath("//label[contains(text(),'Filter By Manufacturer')]/parent::div/descendant::span");
    public static By filterManufacturerDropField = By.xpath("//label[contains(text(),'Filter By Manufacturer')]/parent::div/descendant::input[contains(@class,'dropdown')]");
    public static By filterByStatus = By.xpath("//input[@name='searchTerm']");
    public static By closeIcon = By.xpath("//i[contains(@class,'dropdown-clear-icon')]");
    public static By updateListPriceCheckBox = By.id("listPrice");
    public static By errorIcon = By.xpath("//i[@class='fa fa-error_outline']");
    public static By listPriceInput = By.xpath("//textarea[@class='handsontableInput']");

}
