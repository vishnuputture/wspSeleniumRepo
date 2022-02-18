package pages.pricing.PriceSheet;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class PriceSheetDetails extends ReusableLib
{
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public PriceSheetDetails(Helper helper) {
        super(helper);
    }

    public static By detailsPageHeader= By.xpath("//h2[text()='Price Sheet Details']");

    public static By codeValue=By.xpath("//div[contains(@class,'sheet-detail d-flex justify-content-between')]/div[1]/p");
    public static By manufacturerValue=By.xpath("//div[contains(@class,'sheet-detail d-flex justify-content-between')]/div[2]/p");
    public static By statusValue=By.xpath("//div[contains(@class,'sheet-detail d-flex justify-content-between')]/div[3]//span");

    public static By nameValue=By.xpath("//input[@id='priceSheetName']");

    public static By wiseItems=By.xpath("//*[@id='hot-data']/div[1]/div/div/div/table/tbody/tr[1]/td[1]");

    public static By sheetMultiplier=By.xpath("//input[@id='sheetMultiplier']");
    public static By updateListPrice=By.xpath("//input[@id='listPrice']");
    public static By updatePOCost=By.xpath("//input[@id='poCost']");
    public static By updateMatrixCost=By.xpath("//input[@id='matrixCost']");
    public static By processedDate=By.xpath("//div[@class='date-picker form-group']/input[@id='processDate']");
    public static By activeDay=By.xpath("//div[@class='datepicker-days']/table//tr//td[@class='active day']");
    public static By errorMessage=By.xpath("//div[@class='custom-error-display']");

    public static By saveButton=By.xpath("//button[text()='Save']");

    public static By onHoldButton=By.xpath("//button[text()='Place On Hold']");

    public static By removeHoldButton=By.xpath("//button[text()='Remove Hold']");

    public static By markAsReadyButton=By.xpath("//button[text()='Ready to Process']");

}
