package businesskeywords.AccountReceivable.ARToWISE;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.AccountReceivable.AR2WISE.AR2WISEPage;
import pages.common.MasterPage;
import supportLibraries.Utility_Functions;

import java.util.List;

public class AR2WISE extends ReusableLib {
    CommonActions commonObj;
    private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public AR2WISE(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
        ownDriver = helper.getGSDriver();
    }

    /**
     * Keyword to Navigate to AR2WISE
     */
    public void navigateToAr2WISE() {
        String url = properties.getProperty("AR2WISE");
        ownDriver.get(url);
        waitForElementDisappear(MasterPage.loadingAnime, globalWait);
        commonObj.validateElementExists(AR2WISEPage.headerTitle, "[A/R G/L DETAIL INQUIRY] header is present");
    }

    public By anchorTag(String textName) {
        return By.xpath("//a[text()='" + textName + "']");
    }

    public By buttonTag(String textName) {
        return By.xpath("//button[text()='" + textName + "']");
    }

    public By divTag(String text) {
        return By.xpath("//div[text()='" + text + "']");
    }

    public By spanTag(String text) {
        return By.xpath("//span[text()=\"" + text + "\"]");
    }

    public By labelTag(String textName) {
        return By.xpath("//label[contains(text(),'" + textName + "')]");
    }

    public void navigateToFixBusinessDays() {
        click(AR2WISEPage.openMenu, "CLick Open Menu Icon");
        click(anchorTag("Fix Business Days"));
        commonObj.validateText(AR2WISEPage.headerTitle, "fix business day", "[fix business day] header is present");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, AR2WISEPage.noResultFound, "[No Result Found] message is present");
    }

    public void fixBusinessDaysUI() {
        String[] labels = {"Company Number and Name", "Business Day", "Search All"};
        for (String label : labels) {
            commonObj.validateElementExists(labelTag(label), "[" + label + "] label is present");
        }
    }

    public void selectCompany() {
        Utility_Functions.timeWait(3);
        sendKeys(AR2WISEPage.winLabel, "99599", "Enter [99599] into Company Number and Name text box");
        Utility_Functions.waitTillClickHardSleep(report, ownDriver, AR2WISEPage.dropDownSearchResult, "click [99599 - Automation Test Co.]");
        Utility_Functions.timeWait(4);
    }

    public void fixBusinessDaysRecordUI() {
        selectCompany();
        String[] buttons = {" Customer Number ", " Customer Name ", " Invoice Number ", " Business Day ", " Document Type ", " Ship Date ", " Amount ", " Gross Margin Amount "};
        for (String button : buttons) {
            commonObj.validateElementExists(buttonTag(button), "[" + button + "] is present");
        }
        commonObj.validateElementExists(AR2WISEPage.expandMinimize, "Expand-Hide Icon is present");
    }

    public void verifyError(String companyNo) {
        sendKeys(AR2WISEPage.winLabel, companyNo, "Enter [" + companyNo + "] into Company Number and Name text box");
        commonObj.validateElementExists(AR2WISEPage.invalidCompany, "Error field present");
    }

    public void verifyCompanyNoAndName() {
        verifyError(jsonData.getData("specialCharacterCompanyN0"));
        verifyError(jsonData.getData("negativeCompanyNo"));
        verifyError(jsonData.getData("bankCompanyNo"));
        verifyError(jsonData.getData("invalidCompanyNo"));
    }

    public void selectBusinessDay(int option) {
        click(AR2WISEPage.businessDays);
        Utility_Functions.xAssertEquals(report, ownDriver.findElements(By.xpath("//li")).get(option).getAttribute("data.val"), "" + option + "", "[" + option + "] Business Days dropdown options present");
        click(ownDriver.findElements(By.xpath("//li")).get(option));
        Utility_Functions.timeWait(3);
    }

    public void verifySearchRecordUsingBusinessDays() {
        selectCompany();
        commonObj.validateText(AR2WISEPage.businessDays, "ALL", "By default [ALL] is present");
        int dropVal = 0;
        for (int i = 1; i < 24; i++) {
            selectBusinessDay(i);
            if (!isDisplayed(AR2WISEPage.noResultFound)) {
                String businessDays = ownDriver.findElements(AR2WISEPage.businessDay).get(1).getAttribute("ng-reflect-model");
                dropVal = i;
                if (i < 10) {
                    dropVal = dropVal + 1;
                    Utility_Functions.xAssertEquals(report, businessDays, "0" + i, "Validation matches");
                    Utility_Functions.xSelectDropdownByName(ownDriver, ownDriver.findElements(AR2WISEPage.businessDay).get(1), "0" + (dropVal) + "");
                } else {
                    dropVal = dropVal - 1;
                    Utility_Functions.xAssertEquals(report, businessDays, i, "Validation matches");
                    Utility_Functions.xSelectDropdownByName(ownDriver, ownDriver.findElements(AR2WISEPage.businessDay).get(1), "" + (dropVal) + "");
                }
                break;
            }
        }
        Utility_Functions.timeWait(3);
        selectBusinessDay(dropVal);
        String businessDays = ownDriver.findElements(AR2WISEPage.businessDay).get(1).getAttribute("ng-reflect-model");
        if (dropVal < 10)
            Utility_Functions.xAssertEquals(report, businessDays, "0" + dropVal, "Validation matches");
        else
            Utility_Functions.xAssertEquals(report, businessDays, "" + dropVal, "Validation matches");
    }

    public void verifySearchError(String search) {
        sendKeys(AR2WISEPage.searchAll, search, "Enter [" + search + "] into Search All text box");
        if (!search.equals("  ")) {
            commonObj.validateElementExists(divTag("0 of 0"), "No result found");
            commonObj.validateText(AR2WISEPage.resultStatus, "Results hidden by '" + search + "' filter", "[Results hidden by '" + search + "' filter]");
        } else {
            commonObj.validateElementExists(By.xpath("//tr/td"), "For [Bank space] in [Search All] field Records are found");
        }
    }

    public void verifySearchAllField() {
        selectCompany();
        for (int i = 1; i < 24; i++) {
            selectBusinessDay(i);
            if (!isDisplayed(AR2WISEPage.noResultFound)) {
                break;
            }
        }
        verifySearchError(jsonData.getData("searchSpecialCharacter"));
        verifySearchError(jsonData.getData("searchNegative"));
        verifySearchError(jsonData.getData("searchBank"));
        verifySearchError(jsonData.getData("searchInvalid"));
    }

    public void searchResult(int index) {
        String search = ownDriver.findElements(By.xpath("//tr/td")).get(index).getText();
        if (search.contains("-")) {
            String[] split = search.split("-");
            sendKeys(AR2WISEPage.searchAll, split[0], "Enter [" + split[0] + "] into Search All Input Field");
        } else {
            sendKeys(AR2WISEPage.searchAll, search, "Enter [" + search + "] into Search All Input Field");
        }
        Utility_Functions.timeWait(3);
        Utility_Functions.xAssertEquals(report, search, ownDriver.findElements(By.xpath("//tr/td")).get(index), "Validation matches [" + search + "]");
    }

    public void verifySearchResult() {
        selectCompany();
        for (int i = 0; i < 8; i++) {
            if (i != 3 && i != 4) {
                searchResult(i);
            }
        }
        String search = ownDriver.findElements(By.xpath("//tr/td/preceding::select")).get(0).getAttribute("ng-reflect-model");
        sendKeys(AR2WISEPage.searchAll, search, "Enter [" + search + "] into Search All Input Field");
        Utility_Functions.xAssertEquals(report, search, ownDriver.findElements(By.xpath("//tr/td/preceding::select")).get(0).getAttribute("ng-reflect-model"), "Validation matches [" + search + "]");
    }

    public int itemCountSelect(String recordNo) {
        Utility_Functions.timeWait(5);
        Utility_Functions.xMouseClick(ownDriver, AR2WISEPage.arrowIcon);
        try {
            click(spanTag(recordNo), "Select [" + recordNo + "]");
        } catch (Exception e) {
            Utility_Functions.timeWait(4);
            Utility_Functions.xMouseClick(ownDriver, By.xpath("//div[contains(@class,'mat-select-arrow-wrapper')]/div"));
            click(spanTag(recordNo), "Select [" + recordNo + "]");
        }
        Utility_Functions.timeWait(2);
        String[] records = getText(AR2WISEPage.totalRecords).split("of ");
        int count = Integer.parseInt(records[1]);
        commonObj.validateElementExists(By.xpath("//tr"), "Total records found [" + count + "] for " + recordNo + " option");
        return count;
    }

    public void verifyItemPerPage() {
        selectCompany();
        itemCountSelect("10");
        itemCountSelect("50");
        itemCountSelect("90");
        itemCountSelect("500");
        itemCountSelect("1000");
    }

    public void verifyPagination() {
        selectCompany();
        int pageCount = itemCountSelect("10");
        int sum = ownDriver.findElements(By.xpath("//tr")).size() - 1;
        while (ownDriver.findElement(AR2WISEPage.nextPage).isEnabled()) {
            Utility_Functions.xMouseClick(ownDriver, AR2WISEPage.nextPage);
            int recordCount = ownDriver.findElements(By.xpath("//tr")).size() - 1;
            sum = sum + recordCount;
            try {
                isDisplayed(ownDriver.findElement(AR2WISEPage.nextPage));
            } catch (Exception e) {
                click(AR2WISEPage.previousPage,"Verify Previous Arrow is enabled");
                break;
            }
        }
        Utility_Functions.xAssertEquals(report, sum, pageCount, "Count matches");
    }

    public void sortingCol(){
        List<WebElement> ele=ownDriver.findElements(By.xpath("//tr/th"));
        List<WebElement> els=ownDriver.findElements(By.xpath("//tr/td"));
        int rowCount=ownDriver.findElements(By.xpath("//tr")).size();
        int columnCount=ele.size();
        for(int i=0;i<columnCount;i++){
            click(ele.get(i),"Click ["+getText(AR2WISEPage.columnName).trim()+"]");
            /*int j=0;
            for(int k=j;k<rowCount;k++){
                if(Integer.parseInt(els.get(i).getText().trim()Integer.parseInt(els.get(k+8).getText().trim())){
                    j=k+8;
                }else{

                }
            }*/
        }
    }

    public void verifySortableColumn(){
        selectCompany();
        sortingCol();
    }
}
