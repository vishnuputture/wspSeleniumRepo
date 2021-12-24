package pages.SPO;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SpoPage extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public SpoPage(Helper helper) {
        super(helper);
    }

    public static By winLogin= By.id("winlogo");
    public static By userName= By.id("username");
    public static By password= By.id("password");
    public static By submit=By.xpath("//input[@type='submit']");
    public static By spoPageTitle=By.xpath("//h2[contains(@class,'page-title')]");
    public static By helpIcon=By.xpath("//i[@class='fa fa-help']");
    public static By pagination=By.xpath("//nav[@id='suggestedOrderLanderPagination1']//child::li[contains(@class,'pagination')]");
    public static By onePage=By.xpath("//nav[@id='suggestedOrderLanderPagination1']//child::span[text()='of 1']");
    public static By worksheetCount=By.xpath("//tbody[@class='ui-table-tbody']/tr[@style]");
    public static By disClearAllFilter=By.xpath("//button[contains(text(),'Clear All Filters') and @disabled]");
    public static By newWorksheetBtn=By.xpath("//span[text()='New Worksheet']");
    public static By dayOfTheWeek=By.xpath("//label[text()='Day of the Week']/ancestor::div/descendant::label[contains(@class,'multiselect')]");
    public static By dayCheckBox=By.xpath("//span[contains(@class,'ui-chkbox')]/parent::div");
    public static By popUp=By.xpath("//span[@class='ui-growl-title']");
    public static By worksheetNameFilter=By.xpath("//input[@placeholder='Worksheet Name']");
    public static By filteredWorksheet=By.xpath("//a[@class='header-name-link']");
    public static By worksheetSheetTemplateName=By.id("worksheetSheetTemplateName");
    public static By trailingMonths=By.id("trailingMonths");
    public static By selectedDays=By.xpath("//label[contains(@class,'multiselect')]");
    public static By costOption=By.id("costOption");
    public static By costOptions=By.xpath("//select[@id='costOption']//option");
    public static By hourDropDown=By.id("hour1");
    public static By minuteDropDown=By.id("minute1");
    public static By meridianDropDown=By.id("meridian1");
    public static By refreshIcon=By.xpath("//i[@class='fa fa-refresh']");
    public static By lastUpdate=By.xpath("//span[contains(text(),'Last Updated:')]/parent::div");
    public static By disFindProduct=By.xpath("//button[contains(text(),'Find Products') and @disabled]");
    public static By worksheetNameLink=By.xpath("//span[contains(text(),'Last Updated:')]/preceding::a[@class='header-name-link']");
    public static By worksheetNameHeader=By.id("modalLabel");
    public static By vendorNoHeader=By.xpath("//i[contains(@class,'sticky-note')]/ancestor::h3");
    public static By closeIcon=By.xpath("//button[@class='close']/descendant::i[@class='fa fa-times']");
    public static By trashIcon=By.xpath("//i[contains(@class,'fa fa-trash')]");
    public static By savedTag=By.xpath("//span[contains(@class,'badge-success')]");
    public static By deleteSavedWorksheetPopup=By.xpath("//i[contains(@class,'fa-question')]");
    public static By exclamationWarn=By.xpath("//i[contains(@class,'fa-exclamation-triangle')]");
    public static By saveWorksheetBtn=By.xpath("//span[text()='Save Worksheet']");
    public static By startNewWorkSheet=By.xpath("//button[text()='Start New Worksheet ']");
    public static By leadTime=By.id("leadTime");
    public static By multiplier=By.xpath("//span[text()='Multiplier: ']");
    public static By discount=By.xpath("//span[text()='Discount: ']");
}
