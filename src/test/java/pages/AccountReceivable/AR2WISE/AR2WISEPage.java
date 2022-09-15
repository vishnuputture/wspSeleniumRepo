package pages.AccountReceivable.AR2WISE;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class AR2WISEPage extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public AR2WISEPage(Helper helper) {
        super(helper);
    }

    public static By headerTitle = By.className("panel-header");
    public static By openMenu = By.xpath("//a[@title='Click to open menu']/i");
    public static By noResultFound = By.xpath("//span[text()='No results found']");
    public static By winLabel = By.id("typeahead-company");
    public static By dropDownSearchResult = By.xpath("//ngb-highlight[@ng-reflect-result='99599 - Automation Test Co.']");
    public static By expandMinimize=By.xpath("//i[@class='fa fa-minus-square-o']");
    public static By invalidCompany=By.xpath("//input[contains(@class,'invalid-company')]");
    public static By businessDays=By.xpath("//span/span[@class='ng-star-inserted']");
    public static By businessDay=By.id("businessDay");
    public static By searchAll=By.xpath("//label[contains(text(),'Search All')]/parent::div/div/input");
    public static By resultStatus=By.xpath("//span[@class='row text-lg-center']");
    public static By arrowIcon=By.xpath("//div[contains(@class,'mat-select-arrow-wrapper')]/div");
    public static By totalRecords=By.xpath("//div[@class='mat-paginator-range-label']");
    public static By nextPage=By.xpath("//button[@ng-reflect-disabled='false' and @aria-label='Next page']/span");
    public static By previousPage=By.xpath("//button[@ng-reflect-disabled='false' and @aria-label='Previous page']/span");
    public static By columnName=By.xpath("//tr/th/preceding::button");
}
