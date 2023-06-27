package supportLibraries;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.jayway.jsonpath.*;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.*;
import com.winSupply.framework.selenium.FrameworkDriver;
import com.winSupply.framework.selenium.SeleniumReport;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.Matchers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import pages.WiseSmokeTest.WiseSmokeTestPage;
import pages.common.MasterPage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThat;

/*import freemarker.coreScript.ReturnInstruction.Return;
import supportlibraries.api.FileZipper;*/
@SuppressWarnings("unused")
public class Utility_Functions extends ReusableLib {

    /*
     * ******************************************************************* Function
     * Name: Random String Function Author : winSupply Team Purpose :
     * ******************************************************************
     */
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    static Properties properties = Settings.getInstance();
    private FrameworkDriver ownDriver;

    public static String jsonFile = "src/test/resources/MiddleLayerDataFiles/" + properties.getProperty("RunTimeDataFile") + ".json";
//"src/test/resources/Datatables/RunTimeData.json";
    /*
     * ******************************************************************* Function
     * Name: xHighlight Author : winSupply Team Highlight the element Parameters:
     * driver, webelement, color = e.g yellow, green etc
     * ******************************************************************
     */

    public Utility_Functions(Helper helper) {
        super(helper);
        ownDriver = helper.getGSDriver();

    }

    public static boolean collapseicon(FrameworkDriver driver, String xpath) {
        try {
            List<WebElement> expandicons = Utility_Functions.findElementsByCSSSelector(driver, xpath);
            System.out.println(expandicons.size());
            int expandicons_count = expandicons.size();
            for (int i = expandicons_count - 1; i >= 0; i--) {
                expandicons.get(i).click();
                expandicons = Utility_Functions.findElementsByCSSSelector(driver, xpath);
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Function to convert ResultSet into a List of Map of Object
     *
     * @param rs Pass Result Set
     * @return List of HashMap of Objects
     * @throws SQLException
     */
    public static List<HashMap<String, Object>> convertResultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<HashMap<String, Object>> list = new ArrayList<>();

        while (rs.next()) {
            HashMap<String, Object> row = new HashMap<>(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i).toLowerCase(), rs.getString(i));
            }
            list.add(row);
        }

        return list;
    }

    public static List<HashMap<String, Object>> convertResultSetToListN(ResultSet rs, List<String> columns)
            throws SQLException {
        // ResultSetMetaData md = rs.getMetaData();
        // int columns = md.getColumnCount();
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        while (rs.next()) {
            HashMap<String, Object> row = new HashMap<String, Object>();
            for (int i = 1; i <= columns.size(); ++i) {
                row.put(columns.get(i - 1), rs.getString(i));
            }
            list.add(row);
        }

        return list;
    }

    public static List<Object> convertResultSetToList(Statement stmt, String sql, String clmName) {
        List<Object> list = new ArrayList<Object>();
        try {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(rs.getString(clmName));
            }
        } catch (SQLException e) {

        }

        return list;
    }

    public static Date convertStrToDate(String date) {
        SimpleDateFormat stf = new SimpleDateFormat("MM/dd/yyyy");
        Date val = null;
        try {
            val = stf.parse(date);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return val;
    }

    public static String xGetCurrentDate(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void copyResultsIntoShareFolder(String srcdir, String destdir) {

        String reportPath = System.getProperty("user.dir");
        reportPath = reportPath + "\\" + srcdir;
        File srcDir = new File(reportPath);
        File destDir = new File(destdir);
        try {
            FileUtils.copyDirectoryToDirectory(srcDir, destDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String DateAdd(String effectiveDate, int days) {
        // effectiveDate = effectiveDate.split("T")[0];
        DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            // Date date = new
            // SimpleDateFormat("yyyy-M-d").parse(effectiveDate);
            // effectiveDate = df1.format(effectiveDate);
            c.setTime(df1.parse(effectiveDate));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        c.add(Calendar.DATE, days);
        return df1.format(c.getTime());
    }

    @SuppressWarnings("deprecation")
    public static String DateAdd(String effectiveDate, int days, String dateFormat) {
        // effectiveDate = effectiveDate.split("T")[0];
//		DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat(dateFormat);
        String newDate = null;
        String newt = null;

        Calendar c = Calendar.getInstance();
        try {
            // Date date = new
            // SimpleDateFormat("yyyy-M-d").parse(effectiveDate);
            // effectiveDate = df1.format(effectiveDate);
            c.setTime(df2.parse(effectiveDate));
            c.add(Calendar.DATE, days);
            newDate = df2.format(c.getTime());
//			Date dtDob = new Date(nextDay);
//			newDate = sdf.format(dtDob);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub

        return newDate;
    }

    public static String dateAddition(String date_claim, int days) {
        // effectiveDate = effectiveDate.split("T")[0];
        DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            // Date date = new
            // SimpleDateFormat("yyyy-M-d").parse(effectiveDate);
            // effectiveDate = df1.format(effectiveDate);
            c.setTime(df1.parse(date_claim));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        c.add(Calendar.DATE, days);

        System.out.println(df1.format(c.getTime()));

        return df1.format(c.getTime());
    }

    public static boolean findElementBylinkText(FrameworkDriver driver, String element_linkText) {
        int count = driver.findElements(By.linkText(element_linkText)).size();
        if (count == 0) {
            System.out.println("The element is not found " + element_linkText);
            return false;
        }

        System.out.println("The element is present " + element_linkText);
        return true;
    }

    public static WebElement findElementByXpath(FrameworkDriver driver, String element_xpath) {
        xWaitForElementPresent(driver, By.xpath(element_xpath), 10);
        return driver.findElement(By.xpath(element_xpath));
    }

    public static WebElement findElementByXpath(FrameworkDriver driver, String element_xpath, int timewait) {
        xWaitForElementPresent(driver, By.xpath(element_xpath), timewait);
        return driver.findElement(By.xpath(element_xpath));
    }

    /**
     * Method to find element by xpath String
     *
     * @param driver            Pass driver instance
     * @param webElenmentString pass string value of element
     * @param toBeReplced       pass String to be replaced within WebElmentString
     * @param replacedBy        Pass string which will replace
     * @return
     */
    public static WebElement findElementByXpath(FrameworkDriver driver, String webElenmentString, String toBeReplced,
                                                String replacedBy) {
        try {
            By el = By.xpath(webElenmentString.replace(toBeReplced, replacedBy));
            xWaitForElementPresent(driver, el, 80);
            xWaitForElementClickable(driver, el, 10);
            return driver.findElement(el);
        } catch (Exception e) {
            return null;
        }

    }

    public static WebElement findElementByXpath(FrameworkDriver driver, String webElenmentString, String toBeReplced,
                                                String replacedBy, int timewait) {
        try {
            By el = By.xpath(webElenmentString.replace(toBeReplced, replacedBy));
            xWaitForElementPresent(driver, el, timewait);
            return driver.findElement(el);
        } catch (Exception e) {

            return null;
        }

    }

    public static boolean findElementByXpath(Report report, FrameworkDriver driver, String element_xpath) {
        int count = 0;
        count = driver.findElements(By.xpath(element_xpath)).size();
        if (count == 0) {
            System.out.println("The element is not found " + element_xpath);
            return false;
        }
        System.out.println("The element is present" + element_xpath);
        return true;
    }

    public static List<WebElement> findElementsByCSSSelector(FrameworkDriver driver, String xpath) {
        try {
            return driver.findElements(By.cssSelector(xpath));
        } catch (Exception e) {
            System.out.println("Elements not found " + xpath);
            return null;
        }

    }

    // public static boolean loginToPage(FrameworkDriver driver, WebElement
    // UserName,
    // WebElement Password, )
    public static List<WebElement> findElementsByTagName(WebElement Ele, String tagName) {
        try {
            return Ele.findElements(By.tagName(tagName));
        } catch (Exception e) {
            System.out.println("Elements not found with tag name" + tagName);
            return null;
        }

    }

    /*
     * ******************************************************************* Function
     * Name: inputData Author : winSupply Team Inputs the data: tagID, value
     * ******************************************************************
     */

    public static List<WebElement> findElementsByXpath(FrameworkDriver driver, String element_xpath) {

        return driver.findElements(By.xpath(element_xpath));
    }

    /*
     * ******************************************************************* Function
     * Name: inputData Author : winSupply Team
     * returns list of webelements
     * ******************************************************************
     */

    public static List<WebElement> findElementsByXpath(FrameworkDriver driver, By ele) {

        return driver.findElements(ele);
    }

    /*
     * ******************************************************************* Function
     * Name: findWebElementByClassName Author : CBRE SF Automation Purpose : Element
     * finder by ClassName
     * ******************************************************************
     */
    public static WebElement findWebElementByClassName(FrameworkDriver driver, String string) {
        return driver.findElement(By.className(string));
    }

    // ******************************************************************
    /*
     * Function Name: findWebElementById Author : winSupply Team Element finder by
     * ID ******************************************************************
     */
    public static WebElement findWebElementById(FrameworkDriver driver, String string) {
        return driver.findElement(By.id(string));
    }

    /*
     * ******************************************************************* Function
     * Name: findWebElementByName Author : CBRE SF Automation Purpose : Element
     * finder by Name
     * ******************************************************************
     */
    public static WebElement findWebElementByName(FrameworkDriver driver, String string) {
        return driver.findElement(By.name(string));
    }

    /**
     * Method to get Attribute value from a List of Hash Map of WebElement
     *
     * @param elmentList pass a list List<HashMap<String, WebElement>>
     * @param rowNum     Pass List Index number
     * @param columName  Pass Key Value of Hash Map
     * @param attr       Pass Attribute Name e.g. value, title
     * @return
     */
    public static String getAttrVal(List<HashMap<String, WebElement>> elmentList, int rowNum, String columName,
                                    String attr) {

        String val = elmentList.get(rowNum).get(columName).getAttribute(attr);
        return val;
    }

    /**
     * Function to get column Name the their Index For CheckBox column Name is
     * default CheckBox
     *
     * @param tbHeaderXpath Pass table Xpath
     * @return columNameIndex
     */
    public static HashMap<String, String> getColumnIndex(FrameworkDriver driver, String tbHeaderXpath) {

        List<WebElement> tbcolumn = Utility_Functions.findElementsByXpath(driver, tbHeaderXpath + "/td/child::*");
        HashMap<String, String> columNameIndex = getWebElNameNdIndex(tbcolumn, 1);
        return columNameIndex;

    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static ArrayList<String> getDataFromList(List<HashMap<String, String>> dataList, String srcTypKey,
                                                    String srchTypVal, String keyName) {

        ArrayList<String> ar = new ArrayList<String>();
        for (HashMap<String, String> map : dataList) {
            if (map.get(srcTypKey).toUpperCase().contains(srchTypVal.toUpperCase())) {
                String val = map.get(keyName);
                ar.add(val);

            }

        }
        return ar;

    }

    public static List<HashMap<String, String>> getDataListFromList(List<HashMap<String, String>> dataList,
                                                                    String specificSrchKey, String srchTypVal) {
        List<HashMap<String, String>> iList = new ArrayList<HashMap<String, String>>();
        for (HashMap<String, String> map : dataList) {
            if (map.get(specificSrchKey).toUpperCase().contains(srchTypVal.toUpperCase())) {
                iList.add(map);
            }

        }
        return iList;

    }

    public static List<HashMap<String, String>> getDataListFromList(List<HashMap<String, String>> dataList,
                                                                    String specificSrchKey, String srchTypVal, String specificSrchKey1, String srchTypVal1) {
        List<HashMap<String, String>> iList = new ArrayList<HashMap<String, String>>();
        for (HashMap<String, String> map : dataList) {
            if (map.get(specificSrchKey).toUpperCase().contains(srchTypVal.toUpperCase())
                    && map.get(specificSrchKey1).toUpperCase().contains(srchTypVal1.toUpperCase())) {
                iList.add(map);
            }

        }
        return iList;

    }

    public static WebElement getElmentFromList(List<HashMap<String, WebElement>> elmentList, int rowNum,
                                               String columName) {
        WebElement val = elmentList.get(rowNum).get(columName);
        return val;
    }

    /*
     * Function will fetch the data form validation board and retrieve all the
     * validation present with validation id
     */
    public static List<List<String>> getErrorTableData(FrameworkDriver driver) {
        Utility_Functions.xWaitForElementDisappear(driver, By.cssSelector("span[id *='status.start']"), 60);
        String xpathToTable = "//table[@id='q2iForm:validationTableRndr']/tbody/tr";
        List<List<String>> errorTable = new ArrayList<List<String>>();
        int rowCount = driver.findElements(By.xpath(xpathToTable)).size();
        int columnCount = driver.findElements(By.xpath(xpathToTable + "[1]/td")).size();
        for (int i = 1; i <= rowCount; i++) {
            ArrayList<String> row = new ArrayList<String>();
            for (int j = 1; j <= columnCount; j++) {
                String xpathToElement = xpathToTable + "[" + i + "]/td[" + j + "]";
                String value;
                List<WebElement> rowValue = driver.findElements(By.xpath(xpathToElement));
                if (j == 1) {

                    value = driver.findElements(By.xpath(xpathToElement + "/img")).get(0).getAttribute("src")
                            .toString();
                    if (value.contains("validation"))
                        value = value.substring(value.lastIndexOf('-') + 1, value.lastIndexOf('.'));
                    else
                        value = "10";

                } else
                    value = rowValue.get(0).getText();
                row.add(value);
            }
            errorTable.add(row);
        }

        return errorTable;
    }

    public static String getSelectedText(WebElement e) {
        Select dropdown = new Select(e);
        if (dropdown.getOptions().size() <= 0) {
            timeWait(1);
        }
        return dropdown.getFirstSelectedOption().getText();
    }


    /**
     * Funtion ot Get properties value from DBQueries property fiile
     *
     * @param queryName pass name {Look into DBQueries file}
     * @return query String
     */
    public static String getSQlQuery(String queryName) {

        FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();

        if (frameworkParameters.getRelativePath() == null) {
            throw new FrameworkException("FrameworkParameters.relativePath is not set!");
        }

        Properties properties = new Properties();

        try {
            String encryptedGlobalPropertiesPath = WhitelistingPath
                    .cleanStringForFilePath(frameworkParameters.getRelativePath() + Util.getFileSeparator() + "src"
                            + Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources"
                            + Util.getFileSeparator() + "DBQueries.properties");
            properties.load(new FileInputStream(encryptedGlobalPropertiesPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FrameworkException("FileNotFoundException while loading the DBQueries Settings file");
        } catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("IOException while loading the DBQueries Settings file");
        }

        return properties.getProperty(queryName);
    }

    /**
     * Function to get all WebElments of a Table
     *
     * @param rowcount       Number of Row which are not empty
     * @param tblXpathString Pass String of table header xpath
     * @param tblRow         Pass String of Table row xpath
     * @param columIndex     Pass HashMap of Column and index
     * @return
     */
    public static List<HashMap<String, WebElement>> getTblElments(FrameworkDriver driver, int rowcount,
                                                                  String tblXpathString, String tblRow, HashMap<String, String> columIndex) {
        List<HashMap<String, WebElement>> tbleElments = new ArrayList<HashMap<String, WebElement>>();

        for (int i = 0; i <= rowcount; i++) {
            HashMap<String, WebElement> rowElements = new HashMap<String, WebElement>();
            for (String key : columIndex.keySet()) {
                String tbleRow = tblXpathString + tblRow.replace("_rNum", Integer.toString(i + 1))
                        + "/td[_cNum]/child::*".replace("_cNum", columIndex.get(key));

                List<WebElement> wbe = Utility_Functions.findElementsByXpath(driver, tbleRow);
                for (WebElement e : wbe) {

                    String atr = e.getAttribute("type");
                    if (e.getTagName().equals("input") && (e.getAttribute("type").contains("text")
                            || e.getAttribute("type").contains("checkbox"))) {

                        WebElement el = Utility_Functions.findElementByXpath(driver,
                                tbleRow.replace("*", "input[@type='" + atr + "']"));

                        rowElements.put(key, el);

                    } else if (e.getTagName().equals("a")) {

                        WebElement el = Utility_Functions.findElementByXpath(driver, tbleRow.replace("*", "a"));

                        rowElements.put(key + "a", el);
                    } else if (e.getTagName().equals("img")) {
                        WebElement el = Utility_Functions.findElementByXpath(driver, tbleRow.replace("*", "img"));

                        rowElements.put(key + "img", el);
                    } else if (e.getTagName().equals("select")) {
                        WebElement el = Utility_Functions.findElementByXpath(driver, tbleRow.replace("*", "select"));

                        rowElements.put(key, el);
                    } else if (e.getTagName().equals("text()")) {
                        WebElement el = Utility_Functions.findElementByXpath(driver, tbleRow.replace("*", "text()"));

                        rowElements.put(key, el);
                    }

                }

            }
            if (!rowElements.isEmpty()) {
                tbleElments.add(rowElements);

            }

        }
        return tbleElments;

    }

    /**
     * Method to Get total Row number which has data, method will append string to
     * get column value //*[@src or @keyinfo or((@value or @title)
     * and @type!='hidden')]" in xpath to check row count *
     *
     * @param driver
     * @param tbleColumXpathStr Pass Table Column Xpath string
     * @return
     */
    public static int getTblRowCount(FrameworkDriver driver, String tbleColumXpathStr) {
        tbleColumXpathStr = tbleColumXpathStr + "//*[@keyinfo or((@value or @title) and @type!='hidden')]";
        List<WebElement> tbRows = Utility_Functions.findElementsByXpath(driver, tbleColumXpathStr);
        return tbRows.size();

    }

    public static String getText(FrameworkDriver driver, By element) {
        xHighlight(driver, driver.findElement(element), "red");
        return driver.findElement(element).getText();
    }

    public static String getText(FrameworkDriver driver, By element, String attr) {
        WebElement webE = driver.findElement(element);
        return getText(webE, attr);
    }

    /*
     * ******************************************************************* Function
     * Name: xSelectRadio Author : winSupply Team Selects the radio button : driver,
     * webelement ******************************************************************
     */

    public static String getText(FrameworkDriver driver, WebElement element) {
        xHighlight(driver, element, "red");
        return (String) ((JavascriptExecutor) driver.getWebDriver()).executeScript("return jQuery(arguments[0]).text();", element);

    }

    public static String getText(HashMap<String, WebElement> elements, String objName, String attributeName) {
        return elements.get(objName).getAttribute(attributeName);
    }


    public static String getText(WebElement element) {
        return element.getText().trim();
    }

    public static String getText(WebElement element, FrameworkDriver driver) {
        xScrollIntoView(driver, element);
        xHighlight(driver, element, "green");
        return element.getText().trim();
    }

    public static String getText(WebElement element, String Attribute) {
        try {
            return element.getAttribute(Attribute);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(xExptnsMsg(e.getMessage()));
            return null;
        }

    }

    public static String getValFromList(List<HashMap<String, String>> dataList, String srcTypKey, String srchTypVal,
                                        String keyName) {
        String val = "";
        for (HashMap<String, String> map : dataList) {
            if (map.get(srcTypKey).toUpperCase().contains(srchTypVal.toUpperCase())) {
                val = map.get(keyName);
                break;
            }

        }
        return val;

    }

    public static HashMap<String, String> getWebElNameNdIndex(List<WebElement> listWebEl, int startIndex) {
        int i = startIndex;
        HashMap<String, String> columNameIndex = new HashMap<String, String>();
        int lastIndex = 0;
        for (WebElement webElement : listWebEl) {

            String tagname = webElement.getTagName();
            if (webElement.getTagName().contains("input")) {
                columNameIndex.put("CheckBox", Integer.toString(i));
                i++;
            } else if (webElement.getTagName().contains("label")) {
                columNameIndex.put(Utility_Functions.getText(webElement).trim(), Integer.toString(i));
                i++;
            } else if (webElement.getTagName().contains("span")) {
                columNameIndex.put(Utility_Functions.getText(webElement).trim(), Integer.toString(i));
                i++;
            } else if (webElement.getTagName().contains("h3")) {
                columNameIndex.put(Utility_Functions.getText(webElement).trim(), Integer.toString(i));
                i++;
            } else if (webElement.getTagName().contains("th")) {
                if (Utility_Functions.getText(webElement).trim() != null) {
                    columNameIndex.put(Utility_Functions.getText(webElement).trim(), Integer.toString(i));
                } else if (webElement.getAttribute("id") != null) {
                    columNameIndex.put(Utility_Functions.getText(webElement, "id").trim(), Integer.toString(i));
                }

                i++;
            }
        }
        return columNameIndex;
    }

    public static void HtmlZoomOut(FrameworkDriver driver, int zoomOutlevel) {
        WebElement html = driver.findElement(By.tagName("html"));
        // html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
        for (int i = 0; i < zoomOutlevel; i++) {
            html.sendKeys(Keys.chord(org.openqa.selenium.Keys.CONTROL, org.openqa.selenium.Keys.SUBTRACT));
        }
        if (zoomOutlevel == 0)
            html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
        Utility_Functions.timeWait(1);
    }

    // implicit wait
    public static void impWait(int time, FrameworkDriver driver) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public static boolean isAlert(FrameworkDriver driver) {

        try {

            Alert al = driver.switchTo().alert();
            String a = al.getText();
            System.out.println(a);
            al.accept();
            return true;

        } catch (NoAlertPresentException e) {

            return false;

        }

    }

    /**
     * Method to Accept Alert
     *
     * @param driver
     * @return
     */
    public static String isAlertPresent(FrameworkDriver driver) {

        try {

            Alert al = driver.switchTo().alert();
            String a = al.getText();
            System.out.println(a);
            al.accept();
            return a;

        } catch (NoAlertPresentException e) {

            return null;

        }

    }

    public static boolean isAttributePresent(WebElement element, String attribute) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null) {
                result = true;
            }
        } catch (Exception e) {
        }

        return result;
    }

    /**
     * Utility method to check whether element is exist
     *
     * @param driver
     * @param el     Pass By element
     * @return
     */
    public static Boolean isExist(FrameworkDriver driver, By el) {

        try {
            driver.findElement(el);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Element Not exist");
            return false;

        }
    }

    /*
     * Function to Navigate to the top menu with Hover properties*
     */
    public static boolean navigateToPage_using_TopMenu(FrameworkDriver driver, WebElement Element1, WebElement Element2) {
        xmouseOver(driver, Element1);
        Utility_Functions.timeWait(5);
        Utility_Functions.xSendKeys(driver, Element2, Keys.ENTER);
        return true;
    }

    public static int NumofDigits(int Number) {
        int Count = 0;

        for (Count = 0; Number > 0; Number = Number / 10) {
            Count = Count + 1;
        }
        return Count - 1;
    }

    public static void Onkeydown(WebElement el) {
        el.sendKeys(Keys.ARROW_DOWN);

    }

    public static String parseDateFormat(String inDate) {
        String Date = inDate;
        // System.out.println(Date.length());
        if (Date.length() <= 8) {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            try {
                Date date = new SimpleDateFormat("MM/dd/yy").parse(inDate);
                Date = dateFormat.format(date);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return Date;
    }
    /*
     * @Purpose : Find Element by CSS Selector function
     */

    public static Map<String, String> parseUrlEncodedString(String val) {
        Map<String, String> mapVal = new HashMap<String, String>();
        String[] splitStrng = val.split("&");
        for (String string : splitStrng) {
            String[] str = string.split("=");
            mapVal.put(str[0], str.length > 1 ? str[1] : "");

        }

        return mapVal;
    }

    /*
     * Web Element detail sniffer: give it the same xpath to get details
     */
    public static void printElementDetailsByXPath(FrameworkDriver driver, String xpath) {
        List<WebElement> list = driver.findElements(By.xpath(xpath));
        if (list.size() > 0) {
            WebElement tar = list.get(0);
            System.out.println("[" + xpath + "]:\nEnabled:" + tar.isEnabled() + "\tSelected:" + tar.isSelected()
                    + "\tDisplayed:" + tar.isDisplayed() + "\tSize:" + list.size());
        } else {
            System.out.println("Could not find any element based on XPath:[" + xpath + "]");
        }
    }

    public static double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.####");
        return Double.valueOf(twoDForm.format(d));
    }

    public static StringSelection setClipBoardData(String str) {
        StringSelection ss = new StringSelection(str);
        // copy the above string to clip board
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        return ss;

    }

    /*
     * ******************************************************************* Function
     * Name: timeWait, impWait, xWaitForElementVisible, xWaitForElementPresent,
     * xWaitForElementClickable, xWaitForElementAttribute Author : Team winSupply :
     * Hover and action Parameters: webelement, driver
     * ******************************************************************
     */
    // Static wait for specified time
    public static void timeWait(int time) {
        try {
            long result = time * 1000;
            Thread.sleep(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void msTimeWait(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void validateAndClickInformation(FrameworkDriver driver, String ExpectedValidationText) {
        Utility_Functions.xWaitForElementDisappear(driver, By.cssSelector("span[id *='status.start']"), 60);
        String xpathToTable = "//table[@id='q2iForm:validationTableRndr']/tbody/tr/td[2]";
        List<WebElement> informations = driver.findElements(By.xpath(xpathToTable));
        boolean found = false;
        for (WebElement info : informations) {
            System.out.println(info.getText());
            if (info.getText().contains(ExpectedValidationText)) {
                found = true;
                xClick(driver, info, false);
                Utility_Functions.xWaitForElementDisappear(driver, By.id("workbench:status.start"), 30);
                break;
            }
        }

        if (!found) {
            System.out.println("unable to find validations");
        }

    }

    public static boolean validateErrorMessage(FrameworkDriver driver) {
        Utility_Functions.xWaitForElementDisappear(driver, By.cssSelector("span[id *='status.start']"), 60);
        List<List<String>> messageTable = getErrorTableData(driver);
        int errorCount = 0;
        for (int i = 0; i < messageTable.size(); i++) {
            if (Integer.parseInt(messageTable.get(i).get(0)) >= 200) {
                errorCount++;
            } else {

            }

            // }
        }
        if (errorCount >= 1) {
            System.out.println("Failed with more than 1 validation error");
            return false;
        } else {
            return true;
        }
    }

    /*
     * ******************************************************************* Function
     * Name: validateFieldMatch Author : winSupply Team Validates field match:
     * driver, webelement, expression value
     * ******************************************************************
     */
    public static boolean validateFieldMatch(FrameworkDriver driver, WebElement tar, String expValue) {
        String val = tar.getAttribute("value");
        if (val.equalsIgnoreCase(expValue)) {
            xHighlight(driver, tar, "green");
            return true;
        } else {
            xHighlight(driver, tar, "red");
            return false;
        }
    }

    public static boolean validateFieldMatch(Report report, WebElement tar, String expValue) {
        String val = tar.getAttribute("value");
        if (val.equalsIgnoreCase(expValue)) {
            report.updateTestLog("VerifyTextContains",
                    "Expected text '" + expValue + "' is matching With Actual Text '" + val + "'", Status.PASS);
            return true;
        } else {

            return false;
        }
    }

    public static boolean validateFieldMatch(WebElement tar, String expValue) {
        String val = tar.getAttribute("value");
        if (val.equalsIgnoreCase(expValue)) {

            return true;
        } else {

            return false;
        }
    }

    public static boolean validateLinks(List<WebElement> list, String webElementText) {
        for (WebElement element : list) {
            System.out.println("Util " + element.getText());
            System.out.println("Web text " + webElementText);
            if (element.getText().equals(webElementText)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to Update Json file
     *
     * @param path  Pass JsonPath
     * @param key   Pass Key Name
     * @param value Pass Value
     */
    public static void xAddJsonObj(String path, String key, Object value) {

        File f = createJsonFile(jsonFile);

        WriteContext wctx;
        try {
            wctx = JsonPath.parse(f);
            wctx.put("$." + path, key, value);

            PrintWriter pw = new PrintWriter(jsonFile);
            pw.write(wctx.jsonString());
            pw.flush();
            pw.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.print(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Function to Add Day to String Date
     *
     * @param days   Number of days
     * @param action ADD/MINUS
     * @param date   String date
     * @return
     */
    public static String xAddOrMinusDays(int days, String action, String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        if (action.toUpperCase().contains("ADD")) {

            LocalDate d = convertStrToDate(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                    .plusDays(days);

            return dtf.format(d).toString();
        } else {
            LocalDate d = convertStrToDate(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                    .minusDays(days);
            return dtf.format(d).toString();
        }

    }

    /**
     * Function to Add/minus Months to String Date
     *
     * @param months Number of days
     * @param action ADD/MINUS
     * @param date   String date
     * @return
     */
    public static String xAddOrMinusMonths(int months, String action, String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        if (action.toUpperCase().contains("ADD")) {

            LocalDate d = convertStrToDate(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                    .plusMonths(months);

            return dtf.format(d).toString();
        } else {
            LocalDate d = convertStrToDate(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                    .minusMonths(months);

            return dtf.format(d).toString();
        }

    }

    /**
     * Method to  append the existing JSON
     *
     * @param path  Pass the Path
     * @param value
     */
    public static void xAppendJsonObj(String path, Object value) {
        File f = createJsonFile(jsonFile);

        WriteContext wctx;
        try {
            ReadContext rd = JsonPath.parse(f);
            Object r = rd.read("$." + path);
            System.out.println("r " + r);
            wctx = JsonPath.parse(f);
            wctx.set("$." + path, value);
            r = rd.read("$." + path);

            PrintWriter pw = new PrintWriter(jsonFile);
            pw.write(wctx.jsonString());
            pw.flush();
            pw.close();
            System.out.println(r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to  append the existing JSON
     *
     * @param path  Pass the Path
     * @param value
     */
    public static void xAppendJsonObj(String fileName, String path, Object value) {

        String jsonFile = "src/test/resources/MiddleLayerDataFiles/" + fileName + ".json";
        File f = createJsonFile(jsonFile);

        WriteContext wctx;
        try {
            ReadContext rd = JsonPath.parse(f);
            Object r = rd.read("$." + path);
            System.out.println("r " + r);
            wctx = JsonPath.parse(f);
            wctx.set("$." + path, value);
            r = rd.read("$." + path);

            PrintWriter pw = new PrintWriter(jsonFile);
            pw.write(wctx.jsonString());
            pw.flush();
            pw.close();
            System.out.println(r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method to Update Json file
     *
     * @param path  Pass JsonPath
     * @param key   Pass Key Name
     * @param value Pass Value
     */
    public static void xAddJsonObj(String fileName, String path, String key, Object value) {

        String jsonFile = "src/test/resources/MiddleLayerDataFiles/" + fileName + ".json";
        File f = createJsonFile(jsonFile);
        WriteContext wctx;
        try {
            wctx = JsonPath.parse(f);
            wctx.put("$." + path, key, value);

            PrintWriter pw = new PrintWriter(jsonFile);
            pw.write(wctx.jsonString());
            pw.flush();
            pw.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.print(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void xCreateXmlPayLoad(String value) {

        try {

            PrintWriter pw = new PrintWriter("src/test/resources/Datatables/StoreCap.xml");
            pw.write(value);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void xAssertEquals(Report report, double expValue, double actualVal, double delta, String CstmMsg) {
        double del = roundTwoDecimals(Math.abs(expValue - actualVal));
        if (del == delta || del == 0.000) {
            Assert.assertEquals(expValue, expValue, CstmMsg);
            if (del > 0) {
                report.updateTestLog("Validate ", CstmMsg + "Expected text '" + Double.toString(expValue)
                                + "' is matching With Actual Text '" + Double.toString(actualVal) + "'" + "With delta: '0.0001",
                        Status.PASS);
            } else {

                report.updateTestLog("Validate ", CstmMsg + "Expected text '" + Double.toString(expValue)
                        + "' is matching With Actual Text '" + Double.toString(actualVal) + "'", Status.PASS);
            }
        } else {
            Assert.assertEquals(actualVal, expValue, delta, CstmMsg);
            report.updateTestLog("Validate ", CstmMsg + "Expected text '" + Double.toString(expValue)
                    + "' is matching With Actual Text '" + Double.toString(actualVal) + "'", Status.PASS);
        }

    }

    /**
     * Assert two String value are equal
     *
     * @param report   to log value in report
     * @param expValue pass expected value
     * @param Actual   Pass expected value
     * @param CstmMsg  pass Custom Message
     */
    public static void xAssertEquals(Report report, Object expValue, Object Actual, String CstmMsg) {
        Assert.assertEquals(Actual, expValue, CstmMsg);
        report.updateTestLog("xAssertEquals",
                CstmMsg + "Expected val '" + expValue + "' is matching With Actual val '" + Actual + "'", Status.PASS);

    }

    public static void xAssertEquals(SeleniumReport report, Object expValue, Object Actual) {
        Assert.assertEquals(Actual, expValue);
    }

    /**
     * Assert two String value are equal
     *
     * @param report    to log value in report
     * @param expValue  pass expected value
     * @param actualVal Pass expected value
     * @param CstmMsg   pass Custom Message
     */
    public static void xAssertEquals(Report report, String expValue, String actualVal, String CstmMsg) {
        if (actualVal.contains("$")) {
            actualVal = actualVal.replace("$", "").trim();
        }
        Assert.assertEquals(actualVal, expValue, CstmMsg);

        report.updateTestLog("VerifyVal",
                CstmMsg + " Expected text '" + expValue + "' is matching With Actual Text '" + actualVal + "'",
                Status.PASS);

    }

    /**
     * Assert two String value are unequal
     *
     * @param report    to log value in report
     * @param expValue  pass expected value
     * @param actualVal Pass expected value
     * @param CstmMsg   pass Custom Message
     */
    public static void xAssertNotEquals(Report report, String expValue, String actualVal, String CstmMsg) {
        if (actualVal.contains("$")) {
            actualVal = actualVal.replace("$", "").trim();
        }
        Assert.assertNotEquals(actualVal, expValue, CstmMsg);

        report.updateTestLog("VerifyVal",
                CstmMsg + " Expected text '" + expValue + "' not matching With Actual Text '" + actualVal + "'",
                Status.PASS);

    }

    @SuppressWarnings("null")
    public static void xAssertEquals(Report report, String expValue, WebElement elmentForActualVal, String CstmMsg) {
        String actual = getText(elmentForActualVal);
        actual = (actual == null && actual.isEmpty()) ? "" : actual.trim();
        Assert.assertEquals(actual.toUpperCase(), expValue.toUpperCase(), CstmMsg);
        report.updateTestLog("Assert",
                CstmMsg + "Expected text '" + expValue + "' is matching With Actual Text '" + actual + "'",
                Status.PASS);

    }

    public static void xAssertEquals(Report report, String expValue, WebElement elmentForActualVal, String attributeNam,
                                     String CstmMsg) {
        String actual = getText(elmentForActualVal, attributeNam);
        actual = actual.isEmpty() ? "" : actual;
        Assert.assertEquals(actual, expValue, CstmMsg);
        report.updateTestLog("xAssertEquals",
                CstmMsg + "Expected text '" + expValue + "' is matching With Actual Text '" + actual + "'",
                Status.PASS);

    }

    public static void xAssertThat(Report report, Object expValue1, Object expValue2, Object actual, String CstmMsg) {
        try {
            assertThat(actual, Matchers.anyOf(Matchers.is(expValue1), Matchers.is(expValue2)));

            report.updateTestLog("xAssertThat", CstmMsg + " Expected val is '" + expValue1 + " OR " + expValue2
                    + "'  matching With Actual val '" + actual + "'", Status.PASS);

        } catch (AssertionError e) {
            report.updateTestLog("Value ", CstmMsg + actual, Status.PASS);
        }

    }
    /*
     * ******************************************************************* Function
     * Name: xClickButton Author : winSupply Team Added to click on Button items of
     * bootstrap : driver, webelement, highlight
     * ******************************************************************
     */

    public static void xCheckChkBxOfTbl(FrameworkDriver driver, List<HashMap<String, WebElement>> elmentList,
                                        String columName, String compareVal) {
        WebElement e = null;
        for (HashMap<String, WebElement> hashMap : elmentList) {
            String a = getText(hashMap.get(columName), "title");
            String b = getText(hashMap.get(columName), "value");
            String c = (a != null) ? a : b;
            if (c.equalsIgnoreCase(compareVal)) {
                e = hashMap.get("CheckBox");

                xClick(driver, e);
                break;
            }

        }

    }

    /**
     * Function to Check check box of Table row when Table is divided into two
     * tables
     *
     * @param elList1    List of WebElment of table which has @columName
     *                   and @compareVal
     * @param elList2    List of WebElment of second table that has check box
     * @param columName  Column Header Name
     * @param compareVal Value to search in first table
     */
    public static void xCheckChkBxOfTbl(List<HashMap<String, WebElement>> elList1,
                                        List<HashMap<String, WebElement>> elList2, String columName, String compareVal) {
        WebElement e = null;
        int i = 0;
        for (HashMap<String, WebElement> hashMap : elList1) {
            String a = getText(hashMap.get(columName), "title");
            String b = getText(hashMap.get(columName), "value");
            String c = (a != null) ? a : b;
            if (c.equalsIgnoreCase(compareVal)) {
                e = getElmentFromList(elList2, i, "CheckBox");
                xClick(e);
                break;
            }
            i++;

        }

    }

    public static boolean xClick(FrameworkDriver driver, By el) {

        xWaitForElementClickable(driver, el, 20);
        xHighlight(driver, el, "green");
        driver.findElement(el).click();
        return true;
    }

    public static boolean xClick(FrameworkDriver driver, Report report, By el, String custMsg) {

        xClick(driver, el);
        report.updateTestLog("Click", custMsg, Status.PASS);
        return true;
    }

    public static void xClick(FrameworkDriver driver, Report report, WebElement el, String CustomMsg) {

        xWaitForElementClickable(driver, el, 10);
        xHighlight(driver, el, "yellow");
        el.click();
        report.updateTestLog("Click", CustomMsg, Status.PASS);

    }

    public static void xClick(FrameworkDriver driver, WebElement el) {

        xWaitForElementClickable(driver, el, 10);
        xHighlight(driver, el, "green");
        el.click();

    }

    /*
     * ******************************************************************* Function
     * Name: xClick Author : winSupply Team Click on element with or without
     * highlight Parameters: driver, webelement, highlight = true/false
     * ******************************************************************
     */
    public static boolean xClick(FrameworkDriver driver, WebElement el, boolean highlight) {

        if (highlight == true) {
            // if the element needs to be highlighted before click.
            xHighlight(driver, el, "yellow");
        }

        el.click();

        // timeWait(2);
        return true;
    }

    public static void xClick(Report report, WebElement el, String CustomMsg) {

        el.click();
        report.updateTestLog("Click", CustomMsg, Status.PASS);

    }

    /*
     * ******************************************************************* Function
     * Name: xClick Author : winSupply Team Click on element with or without
     * highlight Parameters: driver, webelement, highlight = true/false
     * ******************************************************************
     */
    public static boolean xClick(WebElement el) {

        el.click();

        // timeWait(2);
        return true;
    }

    /**
     * Method to click again on parent element to get child element
     *
     * @param secndEl pass child element
     * @param firstEl pass parent element
     */
    public static void xClickAgainIfNotFound(FrameworkDriver driver, By secndEl, By firstEl) {
        if (!Utility_Functions.xIsDisplayed(driver.findElement(secndEl))) {
            Utility_Functions.xClick(driver.findElement(firstEl));
        }
    }

    /**
     * Method to click again on parent element to get child element
     *
     * @param secndEl pass child element
     * @param firstEl pass parent element
     */
    public static void xClickAgainIfNotFound(WebElement secndEl, WebElement firstEl) {
        if (!Utility_Functions.xIsDisplayed(secndEl)) {
            Utility_Functions.xClick(firstEl);
        }
    }

    /*
     * ******************************************************************* Function
     * Name: findWebElementBylinkText Author : winSupply Team Purpose : Element
     * finder by linkText
     * ******************************************************************
     */

    public static void xClickAltrntElmnt(FrameworkDriver driver, By el1, By el2) {
        try {
            driver.findElement(el1).click();
        } catch (NoSuchElementException e) {
            // TODO: handle exception
            driver.findElement(el2).click();
        }
    }
    /*
     * ******************************************************************* Function
     * Name: findWebElementByXpath Author : winSupply Team : Element finder by xpath
     * ******************************************************************
     */

    public static void xClickAltrntElmnt(WebElement el1, WebElement el2) {
        try {
            el1.click();
        } catch (NoSuchElementException e) {
            // TODO: handle exception
            el2.click();
        }
    }
    /*
     * ******************************************************************* Function
     * Name: findWebElementBylinkText Author : winSupply Team Purpose : Element
     * finder by linkText
     * ******************************************************************
     */

    public static void xClickButton(FrameworkDriver driver, WebElement el, boolean highlight) {
        Utility_Functions.xSendKeys(driver, el, Keys.ENTER);
        try {
            Utility_Functions.xClick(driver, el, highlight);
        } catch (Exception e) {
            // Stale elements should land here and be harmless
        }
    }

    public static void xClickElementFromList(List<WebElement> WebElements, String ElementToBeClicked) {
        try {
            for (WebElement element : WebElements) {
                if (element.getText().equalsIgnoreCase(ElementToBeClicked)) {
                    element.click();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /*
     * ******************************************************************* Function
     * Name: Verify whether the element is present from the list or not
     * ******************************************************************
     */

    public static String xClickgetTextofFirstElementfromList(List<WebElement> list) {
        String text = null;
        boolean isStatus = false;
        if (list.isEmpty()) {
            System.out.println("List is Empty:::");
            return null;
        } else {
            while (!isStatus) {
                for (WebElement elememt : list) {
                    text = elememt.getText();
                    elememt.click();
                    isStatus = true;
                    break;
                }
            }
            return text;
        }
    }

    public static void xClickHiddenElement(FrameworkDriver driver, By element) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript("arguments[0].click()", driver.findElement(element));
    }

    public static void xClickHiddenElement(FrameworkDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript("arguments[0].click()", element);
    }

    public static void xClickIfAvlbl(FrameworkDriver driver, Report report, WebElement el, String cstMsg) {
        if (xIsDisplayed(el)) {
            xClick(driver, report, el, cstMsg);
        }

    }

    public static void xClickIfAvlbl(FrameworkDriver driver, WebElement el) {
        if (xIsDisplayed(el)) {
            xClick(driver, el);
        }

    }

    public static void xClickIfAvlbl(FrameworkDriver driver, By el) {

        if (xIsDisplayed(driver, el)) {
            xClick(driver, el);
        }

    }

    // ***********************************************************

    public static void xclickOnFirstElementfromList(List<WebElement> list) {
        Utility_Functions.timeWait(1);
        if (list.isEmpty()) {
            System.out.println("List is Empty:::");
            return;
        } else {
            for (WebElement elememt : list) {
                elememt.click();
                return;
            }
        }
    }

    // ***********************************************************

    // wait for the the expected xpath count to be present

    /*
     * public void xWaitForXpathCount(FrameworkDriver driver, String xpath, int
     * timeWait) { WebDriverWait wait = new WebDriverWait(driver.getWebDriver(),
     * timeWait);
     * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
     * List<WebElement> elementsList=driver.findElements(By.xpath(xpath));
     * while(elementsList.size()>0){ }
     *
     * }
     */

    /*
     * ******************************************************************* Function
     * Name: Click on random element from the list and return the test value
     * ******************************************************************
     */
    public static String xClickOnRandomElement(List<WebElement> list) {
        String text = null;
        boolean isStatus = false;
        Random random = new Random();
        if (list.isEmpty()) {
            System.out.println("List is Empty:::");
            return null;
        } else {
            while (!isStatus) {
                for (WebElement elememt : list) {
                    int randomValue = random.nextInt(list.size());
                    text = list.get(randomValue).getText().trim();
                    list.get(randomValue).click();
                    isStatus = true;
                    break;
                }
            }
            return text;
        }
    }

    public static String xClickRandomElement(List<WebElement> list) {
        String text = null;
        boolean isStatus = false;
        Random random = new Random();
        Utility_Functions.timeWait(1);
        if (list.isEmpty()) {
            System.out.println("List is Empty:::");
            return null;
        } else {
            while (!isStatus) {
                for (WebElement elememt : list) {
                    int randomValue = random.nextInt(list.size());
                    text = elememt.getText();
                    list.get(randomValue).click();
                    isStatus = true;
                    break;
                }
            }
            return text;
        }
    }

    /**
     * Opens a new window based on click. Built on the assumption that only 1 window
     * opens per link click
     *
     * @param driver     - active webdriver
     * @param tar        - target webelement, applies xClickButton(driver,tar,true)
     * @param waitInSecs
     */
    public static void xClickSwitchNewWindow(FrameworkDriver driver, WebElement tar, int waitInSecs) {
        Set<String> baseWindows = driver.getWindowHandles();
        Utility_Functions.xClickButton(driver, tar, true);
        Utility_Functions.xWaitUntilnoOfWindows(driver, baseWindows.size() + 1, waitInSecs * 100);
        Set<String> newWindows = driver.getWindowHandles();
        for (String window : newWindows) {
            if (!baseWindows.contains(window)) {
                driver.switchTo().window(window);
                return;
            }
        }
    }

    /*
     * ******************************************************************* Function
     * Name: xClickVisibleListElement Author : winSupply Team Purpose : Click the
     * visible element field: driver, List<WebElement>
     * ******************************************************************
     */
    public static void xClickVisibleListElement(FrameworkDriver driver, List<WebElement> eleList) {

        for (WebElement element : eleList) {
            if (element.isDisplayed()) {
                xClickHiddenElement(driver, element);
                break;
            }
        }

    }

    public static void xCloseCurrentWin(FrameworkDriver driver) {
        driver.close();

    }

    // Purpose: switch to windows with specific title
    public static boolean xContainsSwitch(FrameworkDriver driver, String title) {
        timeWait(2);
        Set<String> windows = driver.getWindowHandles();
        for (Iterator<String> i = windows.iterator(); i.hasNext(); ) {
            String window = i.next();
            driver.switchTo().window(window);
            if (driver.getTitle().contains(title)) {
                return true;
            }
        }
        return false;
    }

    public static boolean xContainsValue(FrameworkDriver driver, String title) {
        if (driver.getTitle().contains(title)) {
            return true;
        }

        return false;
    }

    /**
     * Method to connect Oracle DB and return statement
     *
     * @param dbname
     * @param UserName
     * @param Pwd
     * @return
     */
    public static Statement xDBConntion(String dbname, String UserName, String Pwd, String dbType) {
        String url = getDBUrl(dbname);
        // Load Oracle jdbc driver
        try {
            // Class.forName("org.postgresql.FrameworkDriver");
            switch (dbType) {
                case "db2":
                    Class.forName("com.ibm.db2.jcc.DB2Driver");
                    break;
                case "oracle":
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    break;
                default:
                    throw new ClassNotFoundException();
            }

            Connection con = DriverManager.getConnection(url, UserName, Pwd);
            // Create Statement Object
            Statement stmt = con.createStatement();
            //    System.out.println("db statement: "+stmt.toString());
            return stmt;
        } catch (ClassNotFoundException e1) {

            e1.printStackTrace();
            return null;
        } catch (SQLException e) {

            e.printStackTrace();
            return null;
        }

    }

    public static Connection xDBConnectionWise(String dbname, String UserName, String Pwd, String dbType) {
        String url = getDBUrl(dbname);
        // Load Oracle jdbc driver
        try {
            // Class.forName("org.postgresql.FrameworkDriver");
            switch (dbType) {
                case "db2":
                    Class.forName("com.ibm.db2.jcc.DB2Driver");
                    break;
                case "oracle":
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    break;
                default:
                    throw new ClassNotFoundException();
            }

            Connection con = DriverManager.getConnection(url, UserName, Pwd);
            // Create Statement Object
            return con;

        } catch (ClassNotFoundException e1) {

            e1.printStackTrace();
            return null;
        } catch (SQLException e) {

            e.printStackTrace();
            return null;
        }
    }

    public static Statement xDBStatementWise(Connection con) {
        try {
            Statement stmt = con.createStatement();
            //    System.out.println("db statement: "+stmt.toString());
            return stmt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void xDBCloseConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getDBUrl(String dbname) {
        String url = "";
        //   String dbEnv = properties.getProperty("DBEnv");
        switch (dbname) {
            case "db2":
                url = "jdbc:as400://windev1.winwholesale.com;naming=system";
                break;
            case "sql-server":
                url = "";
                break;
        }
        return url;
    }

    // Delete Cache
    public static void xDelCache() throws IOException {
        Runtime.getRuntime().exec("cmd /c /windows/system32/RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
    }

    public static int xElementPresentFromList(ArrayList<String> ArrayList, String ElementPresent) {
        int count = 0;
        try {
            for (int i = 0; i < ArrayList.size(); i++) {
                if (ArrayList.get(i).equals(ElementPresent)) {
                    count++;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public static void xEnterText(FrameworkDriver driver, WebElement el, String strVal) {
        xWaitForElementClickable(driver, el, 10);
        el.click();
        el.sendKeys(strVal);
    }

    public static void xEraseField(WebElement el) {
        String entry = el.getAttribute("value");
        for (int i = 0; i < entry.length(); i++) {
            el.sendKeys(Keys.BACK_SPACE);
        }
    }

    /**
     * Method to upload file using ROBOT class
     *
     * @param msg
     * @author winSupply
     */
    public static String xExptnsMsg(String msg) {
        try {
            String m[] = msg.split("\n");
            System.out.println(m[0]);
            String msgT = m.length > 1 ? m[0] + "\n" + m[1] : m[0];
            return msgT;

        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Method to First element using string and then click and log in report
     *
     * @param driver       Pass the FrameworkDriver instance
     * @param report       Pass report instance
     * @param webElString  Pass String of Element for xpath
     * @param toBeReplaced Pass String which be replaced in webElString
     * @param replacedBy   Pass String name which will replace toBeReplaced String
     * @param CustomMsg    pass custom Massage
     */
    public static void xfindElAndClick(FrameworkDriver driver, Report report, String webElString, String toBeReplaced,
                                       String replacedBy, String CustomMsg) {

        WebElement el = findElementByXpath(driver, webElString, toBeReplaced, replacedBy);
        if (el != null) {
            xClick(driver, el);
            report.updateTestLog("Click", CustomMsg, Status.PASS);
        }

    }

    /**
     * Method to First element using string and then click
     *
     * @param driver       Pass the FrameworkDriver instance
     * @param webElString  Pass String of Element for xpath
     * @param toBeReplaced Pass String which be replaced in webElString
     * @param replacedBy   Pass String name which will replace toBeReplaced String
     */

    public static void xfindElAndClick(FrameworkDriver driver, String webElString, String toBeReplaced, String replacedBy) {

        WebElement el = findElementByXpath(driver, webElString, toBeReplaced, replacedBy);
        if (el != null) {
            xClick(driver, el);
        }
    }

    /**
     * Method to First element using string and then click
     *
     * @param driver       Pass the FrameworkDriver instance
     * @param webElString  Pass String of Element for xpath
     * @param toBeReplaced Pass String which be replaced in webElString
     * @param replacedBy   Pass String name which will replace toBeReplaced String
     */

    public static void xfindElAndClick(FrameworkDriver driver, String webElString, String toBeReplaced, String replacedBy,
                                       int waitTime) {

        WebElement el = findElementByXpath(driver, webElString, toBeReplaced, replacedBy, waitTime);
        if (el != null) {
            xClick(driver, el);
        }
    }
    /*
     * ******************************************************************* Function
     * Name: inputValueWithReport Author : winSupply Team : Inputs the value with in
     * the report: tagID, value, label
     * ******************************************************************
     */

    /**
     * Method to find element using xpath String and Send Key
     *
     * @param driver       FrameworkDriver instance
     * @param textBoxS     xpath string of text box
     * @param toBeReplaced tring to be replaced in typo value xpath
     * @param replacedBy   String to by replaced by
     * @param strVal       Input text
     */
    public static void xfindElandSendKey(FrameworkDriver driver, String textBoxS, String toBeReplaced, String replacedBy,
                                         String strVal) {
        WebElement el = findElementByXpath(driver, textBoxS, toBeReplaced, replacedBy);
        xSendKeys(driver, el, strVal);

    }

    /**
     * Method to find element using xpath string and then select drop down value
     * using visibleName
     *
     * @param driver
     * @param webElString
     * @param toBeReplaced
     * @param replacedBy
     * @param visibleName
     */
    public static void xFindElNdSelectDropdownByName(FrameworkDriver driver, String webElString, String toBeReplaced,
                                                     String replacedBy, String visibleName) {
        if (visibleName != "") {

            WebElement e = findElementByXpath(driver, webElString, toBeReplaced, replacedBy, 10);
            if (e != null) {
                xSelectDropdownByName(driver, e, visibleName);
            }

        }
    }

    public static int xfindTbRwNumByVal(List<HashMap<String, WebElement>> elmentList, String compareVal) {
        WebElement e = null;
        int i = 0;
        for (HashMap<String, WebElement> hashMap : elmentList) {

            for (String k : hashMap.keySet()) {
                if (hashMap.get(k).getAttribute("title").equalsIgnoreCase(compareVal)
                        || hashMap.get(k).getAttribute("value").equalsIgnoreCase(compareVal)) {
                    e = hashMap.get("CheckBox");
                    xClick(e);
                    break;
                }

            }
            i++;

        }

        return i;

    }

    public static String xformatVal(String val, String formatter) {
        val = (val == null) ? "0.0" : val;
        DecimalFormat df = new DecimalFormat(formatter);
        double d1 = Double.parseDouble((val.isEmpty()) ? formatter : val);
        return df.format((d1));
    }

    /*
     * ******************************************************************* Function
     * Name: xSelectDropdown,xSelectDropdownByName Author : winSupply Team
     * Automation Purpose : Hover and action Parameters: webelement, index
     * ******************************************************************
     */
    // Select dropdown by Index

    public static String xGenerateAlphaNumericString() {
        int count = 10;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static String xGet4Decimal(String val) {
        DecimalFormat df = new DecimalFormat("#0.0000");
        double d1 = Double.parseDouble((val.isEmpty()) ? "0.0000" : val);
        return df.format((d1));
    }

    /**
     * Method to Add days to System Date in MM-dd-YYYY format
     *
     * @param adddays
     * @return
     */
    public static String xgetDate(int adddays) {
        LocalDate localDate = LocalDate.now().plusDays(adddays);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");

        return dtf.format(localDate).toString();
    }

    /**
     * Method to Connect with JSON file and return Value for specific Key
     *
     * @param Key Name of Key
     * @return Object
     */
    public static String xGetJsonData(String Key) {
        try {

            Object obj = new JSONParser().parse(new FileReader(jsonFile));

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;
            return (String) jo.get(Key);
        } catch (IOException e) {

            return e.getMessage();
        } catch (org.json.simple.parser.ParseException e) {

            return e.getMessage();
        }
        // return Key;

    }

    public static Object xGetJsonObject(String Key) {
        try {

            Object obj = new JSONParser().parse(new FileReader(jsonFile));

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;
            return jo.get(Key);
        } catch (IOException e) {

            return e.getMessage();
        } catch (org.json.simple.parser.ParseException e) {

            return e.getMessage();
        }
        // return Key;

    }

    @SuppressWarnings("unchecked")
    public static List<HashMap<String, Object>> xGetJsonList(String Key) {
        try {
            Object obj = new JSONParser().parse(new FileReader(jsonFile));

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;
            return (List<HashMap<String, Object>>) jo.get(Key);

        } catch (Exception e) {
            return null;
        }

    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, String> xGetJsonMap(String Key) {
        try {
            Object obj = new JSONParser().parse(new FileReader(jsonFile));

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;
            return (HashMap<String, String>) jo.get(Key);

        } catch (Exception e) {
            return null;
        }

    }

    @SuppressWarnings("unchecked")
    public static String xGetJsonMapKey(String Key, String mapKey) {
        try {
            Object obj = new JSONParser().parse(new FileReader(jsonFile));

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            HashMap<String, String> jmap = new HashMap<String, String>();
            jmap = (HashMap<String, String>) jo.get(Key);

            return jmap.get(mapKey);

        } catch (Exception e) {
            return null;
        }

    }

    public static String xGetTextofFirstElementfromList(List<WebElement> list) {
        String text = null;
        boolean isStatus = false;
        if (list.isEmpty()) {
            System.out.println("List is Empty:::");
            return null;
        } else {
            while (!isStatus) {
                for (WebElement elememt : list) {
                    text = elememt.getText();
                    isStatus = true;
                    break;
                }
            }
            return text;
        }
    }

    /*
     * ******************************************************************* Function
     * Name: xClickVisibleListElement Author : winSupply Team Purpose : Click the
     * visible element field: driver, List<WebElement>
     * ******************************************************************
     */
    public static String xGetTextVisibleListElement(FrameworkDriver driver, List<WebElement> eleList) {

        for (WebElement element : eleList) {
            if (element.isDisplayed()) {
                // xClick(driver, element, true);

                return element.getText();
            }
        }
        return null;

    }

    public static void xHighlight(FrameworkDriver driver, By element, String color) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript("arguments[0].style.cssText += '; position: relative; margin: 0; padding: 0; box-sizing: " +
                "border-box;';", driver.findElement(element));
        js.executeScript("arguments[0].style.border = '2px solid " + color + "'; " +
                "arguments[0].style.width = arguments[0].offsetWidth + 'px'; " +
                "arguments[0].style.height = arguments[0].offsetHeight + 'px';", driver.findElement(element));
    }

    public static void xHighlight(FrameworkDriver driver, WebElement element, String color) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript("arguments[0].style.cssText += '; position: relative; margin: 0; padding: 0; box-sizing: " +
                "border-box;';", element);
        js.executeScript("arguments[0].style.border = '2px solid " + color + "'; " +
                "arguments[0].style.width = arguments[0].offsetWidth + 'px'; " +
                "arguments[0].style.height = arguments[0].offsetHeight + 'px';", element);
    }

    public static void removeHighlight(FrameworkDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript("arguments[0].style.border = '';", element);
    }

    public static void removeHighlight(FrameworkDriver driver, By element) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript("arguments[0].style.border = '';", driver.findElement(element));
    }


    public static boolean xHoverElementclicks(WebElement el, FrameworkDriver driver) {
        Actions builder = new Actions(driver.getWebDriver());
        builder.moveToElement(el).build().perform();
        timeWait(2);
        // builder.click().perform();
        return true;
    }

    public static boolean xHoverElementClk(WebElement el, FrameworkDriver driver) {

        Actions builder = new Actions(driver.getWebDriver());
        builder.moveToElement(el).click().perform();
        return true;
    }

    public static boolean xHoverElementDblClk(WebElement el, FrameworkDriver driver) {

        Actions builder = new Actions(driver.getWebDriver());
        builder.moveToElement(el).doubleClick().perform();
        timeWait(1);
        return true;
    }

    public static boolean xHoverElementInvisibleclicks(WebElement el, FrameworkDriver driver) {
        Actions builder = new Actions(driver.getWebDriver());
        builder.moveToElement(el).build().perform();
        timeWait(2);
        builder.click().perform();
        return true;
    }

    public static boolean actionKey(Keys key, FrameworkDriver driver) {
        Actions builder = new Actions(driver.getWebDriver());
        builder.sendKeys(key).build().perform();
        By loadingAnime = By.id("_pui_loading_animation");
        xWaitForElementDisappear(driver, loadingAnime, 20);
        timeWait(1);
        return true;
    }

    /**
     * Method : If Element is visible then Click
     *
     * @param el
     * @return
     */
    public static Boolean xIfEldisplayClick(FrameworkDriver driver, WebElement el) {
        try {
            if (el.isDisplayed()) {

                xClick(el);
                System.out.println("Element  found");
                return true;
            } else {
                System.out.println("Element not found");
                return false;

            }
        } catch (NoSuchElementException e) {

            System.out.println("Element not found");
            return false;
        }
    }

    public static Boolean xIsDisplayed(FrameworkDriver driver, By el) {
        try {
            if (driver.findElement(el).isDisplayed()) {

                return true;
            } else {

                return false;

            }
        } catch (NoSuchElementException e) {
            return false;
        } catch (Exception e) {

            return false;

        }
    }

    /**
     * Utility Method to Check whether Element is displayed or not
     *
     * @param el
     * @return
     */
    public static Boolean xIsDisplayed(WebElement el) {
        try {
            if (el.isDisplayed()) {

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Utility Method to Check whether Element is displayed or not
     *
     * @param el
     * @return
     */
    public static Boolean xIsDisplayed(WebElement el, int reTry) {
        boolean foo = false;
        for (int i = 0; i < reTry; i++) {
            try {
                if (el.isDisplayed()) {

                    foo = true;
                } else {
                    foo = false;
                    timeWait(1);
                }
            } catch (Exception e) {
                foo = false;
                timeWait(1);
            }
        }
        return foo;
    }

    /*
     * ******************************************************************* Function
     * Name: xClickVisibleListElement Author : winSupply Team Purpose : Click the
     * visible element field: driver, List<WebElement>
     * ******************************************************************
     */
    public static void xIsElementDisplayed(Report report, WebElement el, String Msg) {

        if (el.isDisplayed()) {
            report.updateTestLog("IsElement displayed", Msg, Status.PASS);

        } else {

            report.updateTestLog("IsElement displayed", Msg, Status.FAIL);
        }
    }

    /*
     * Purpose: get the current system date
     */

    public static void xMouseClick(FrameworkDriver driver, WebElement el) {

        String mouseScript = "arguments[0].click();";
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript(mouseScript, el);
    }

    public static void xMouseClick(FrameworkDriver driver, By el) {

        String mouseScript = "arguments[0].click();";
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript(mouseScript, driver.findElement(el));
    }

    public static boolean xMouseDoubleClick(FrameworkDriver driver, WebElement el) {

        Actions builder = new Actions(driver.getWebDriver());
        builder.moveToElement(el).doubleClick().perform();
        return true;
    }

    public static void xmouseOut(FrameworkDriver driver, WebElement el) {
        String mouseOffScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"
                + "evObj.initEvent('mouseout', true, false); arguments[0].dispatchEvent(evObj);"
                + "} else if(document.createEventObject) { arguments[0].fireEvent('onmouseout');}";
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript(mouseOffScript, el);
    }

    public static void xMouseOut(FrameworkDriver driver, WebElement el) {
        String mouseOffScript = "$(arguments[0]).mouseout();";
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript(mouseOffScript, el);
    }

    /*
     * ******************************************************************* Function
     * Name: xmouseOver Author : winSupply Team
     * xHoverElement,xHoverElementDblClk,xHoverElementclicks : Hover and action
     * Parameters: webelement, driver
     * ******************************************************************
     */
    public static void xmouseOver(FrameworkDriver driver, WebElement el) {
        String mouseOverScript = "if(document.createEvent){" + "var evObj = document.createEvent('MouseEvents');"
                + "evObj.initEvent('mouseover', true, false); " + "arguments[0].dispatchEvent(evObj);" + "} "
                + "else if(document.createEventObject) { " + "arguments[0].fireEvent('onmouseover');" + "}";
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript(mouseOverScript, el);
    }

    public static void xmouseOver(FrameworkDriver driver, By el) {
        String mouseOverScript = "if(document.createEvent){" + "var evObj = document.createEvent('MouseEvents');"
                + "evObj.initEvent('mouseover', true, false); " + "arguments[0].dispatchEvent(evObj);" + "} "
                + "else if(document.createEventObject) { " + "arguments[0].fireEvent('onmouseover');" + "}";
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript(mouseOverScript, driver.findElement(el));
    }

    public static void xMouseOver(FrameworkDriver driver, WebElement el) {
        String mouseOverScript = "$(arguments[0]).mouseover();";
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript(mouseOverScript, el);
    }

    public static boolean xMoveToElment(WebElement el, FrameworkDriver driver) {

        Actions builder = new Actions(driver.getWebDriver());
        builder.moveToElement(el).perform();
        return true;
    }

    public static void contextClickOnElement(FrameworkDriver driver, By e) {
        Actions builder = new Actions(driver.getWebDriver());
        builder.contextClick(driver.findElement(e)).perform();
    }

    public static int xRandomFunction() {
        Random random = new Random();
        int value = random.nextInt(9999999) + 1000000;
        return value;
    }

    /*
     *
     */

    public static int xRandomFunction(int MaxNum) {
        Random random = new Random();
        int value = (int) (random.nextInt(MaxNum) + Math.pow(10, NumofDigits(MaxNum)));
        return value;
    }

    /*
     * **********************************************************************************************
     * Function Name: xRandomFunction Author : winSupply Team Purpose : generate random Int in range
     * **********************************************************************************************
     */
    public static int xRandomFunction(int min, int max) {
        Random random = new Random();
        int value = (int) (random.nextInt((max - min) + 1) + min);
        return value;
    }

    public static Object xReadJson(String path) {
        try {

            File f = createJsonFile(jsonFile);//new File(jsonFile);

            Object obj = null;
            ReadContext wctx;
            try {
                wctx = JsonPath.parse(f);
                obj = wctx.read("$." + path);

                return obj;

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                obj = null;
            }
            return obj;

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public static Object xReadJson(String jsonString, String path) {
        try {
            Object obj = null;
            ReadContext wctx;

            wctx = JsonPath.parse(jsonString);
            obj = wctx.read("$." + path);
            return obj;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }


    public static Object xReadCustomJson(String path, String fileName) {
        try {

            File f = createJsonFile("src/test/resources/MiddleLayerDataFiles/" + fileName + ".json");
            Object obj = null;
            ReadContext wctx;
            try {
                wctx = JsonPath.parse(f);
                obj = wctx.read("$." + path);
                return obj;
            } catch (IOException e) {
                e.printStackTrace();
                obj = null;
            }
            return obj;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public static <T> T xReadJson(T path) {
        try {

            File f = new File(jsonFile);
            T obj = null;
            ReadContext wctx;
            try {
                wctx = JsonPath.parse(f);
                obj = wctx.read("$." + path.toString());

                return obj;

            } catch (IOException e) {
                e.printStackTrace();
                obj = null;
            }
            return obj;

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }


    public static String xGetJsonAsString(String path) {
        File f = new File(jsonFile);
        String obj = null;
        ReadContext wctx;
        try {
            wctx = JsonPath.parse(f);
            obj = wctx.read("$." + path).toString();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            obj = null;
        }
        return obj;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> xGetJsonAsMap(String path) {
        File f = new File(jsonFile);
        ObjectMapper mapper = new ObjectMapper();
        Configuration.setDefaults(new Configuration.Defaults() {

            private final JsonProvider jsonProvider = new JacksonJsonProvider();
            private final MappingProvider mappingProvider = new JacksonMappingProvider();

            @Override
            public JsonProvider jsonProvider() {
                return jsonProvider;
            }

            @Override
            public MappingProvider mappingProvider() {
                return mappingProvider;
            }

            @Override
            public Set<Option> options() {
                return EnumSet.noneOf(Option.class);
            }
        });

        TypeRef<Map<String, String>> typeRef = new TypeRef<Map<String, String>>() {
        };
        Map<String, String> obj = new HashMap<String, String>();
        ReadContext wctx;
        try {
            obj = JsonPath.parse(f).read(path, Map.class);
            obj = JsonPath.parse(f).read(path, typeRef);
            wctx = JsonPath.parse(f);

            return obj;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            obj = null;
        }
        return obj;
    }

    @SuppressWarnings("unchecked")
    public static List<HashMap<String, Object>> xGetJsonAsList(String path) {

        File f = new File(jsonFile);
//		ObjectMapper mapper = new ObjectMapper();
//		Configuration.setDefaults(new Configuration.Defaults() {
//
//		    private final JsonProvider jsonProvider = new JacksonJsonProvider();
//		    private final MappingProvider mappingProvider = new JacksonMappingProvider();
//
//		    @Override
//		    public JsonProvider jsonProvider() {
//		        return jsonProvider;
//		    }
//
//		    @Override
//		    public MappingProvider mappingProvider() {
//		        return mappingProvider;
//		    }
//
//		    @Override
//		    public Set<Option> options() {
//		        return EnumSet.noneOf(Option.class);
//		    }
//		});
//		TypeRef<List<HashMap<String, Object>>> typeRef = new TypeRef<List<HashMap<String, Object>>>() {
//		};
        List<HashMap<String, Object>> obj = new ArrayList<>();
        ReadContext wctx;
        try {
            obj = JsonPath.parse(f).read(path, List.class);
            //obj = JsonPath.parse(f).read(path, typeRef);
            wctx = JsonPath.parse(f);

            return obj;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            obj = null;
        }
        return obj;
    }

    @SuppressWarnings("unchecked")
    public static void xsaveJsonData(String key, Object obj) {
        try {

            List<JSONObject> jsonObj = new ArrayList<JSONObject>();
            jsonObj.addAll((Collection<? extends JSONObject>) obj);

            JSONObject jo = new JSONObject();
            jo.put(key, jsonObj);
            PrintWriter pw = new PrintWriter("C:\\Users\\gsamota\\Downloads\\material.json");
            pw.write(jo.toJSONString());
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /*
     * purpose: Login to a page with username, password and click btn
     */

    public static void xScrollIntoView(FrameworkDriver driver, WebElement el) {

        String mouseScript = "arguments[0].scrollIntoView();";

        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript(mouseScript, el);
        xHighlight(driver, el, "yellow");

    }

    public static void xScrollIntoView(FrameworkDriver driver, By el) {

        String mouseScript = "arguments[0].scrollIntoView();";

        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript(mouseScript, driver.findElement(el));
        xHighlight(driver, el, "yellow");

    }

    public static void xScrollIntoViewClck(FrameworkDriver driver, WebElement el) {

        String mouseScript = "arguments[0].scrollIntoView();";
        String mouseScript1 = "arguments[0].click();";
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript(mouseScript, el);
        js.executeScript(mouseScript1, el);
    }

    /*
     * public static void ZipResults(String reportPath) { // TODO Auto-generated
     * method stub FileZipper appZip = new FileZipper();
     * appZip.ZipFiles(reportPath); }
     */

    public static void xScrollPage(FrameworkDriver driver) {
        String mouseScript = "window.scrollTo(document.body.scrollHeight,0);";
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript(mouseScript);
    }

    public static void xScrollToViewSendKeys(FrameworkDriver driver, WebElement el, String strVal) {
        xScrollIntoView(driver, el);
        xHighlight(driver, el, "yellow");
        xWaitForElementVisible(driver, el, 10);
        el.click();
        el.clear();
        el.sendKeys(strVal);
        // timeWait(1);

    }

    /*
     * ******************************************************************* Function
     * Name: xScrollWindow Author : winSupply Team Hover and action Parameters:
     * driver ******************************************************************
     */
    public static void xScrollWindow(FrameworkDriver driver) {
        for (int timeout = 0; ; timeout++) {
            if (timeout >= 5) {
                break;
            }
            JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
            js.executeScript("window.scrollBy(0,400)", "");
            timeWait(2);
        }
    }

    public static void xScrollWindowOnce(FrameworkDriver driver) {
        for (int timeout = 0; ; timeout++) {
            if (timeout >= 2) {
                break;
            }
            JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
            js.executeScript("window.scrollBy(0,200)", "");
            timeWait(2);
        }
    }

    public static void xScrollWindowOnce(WebDriver driver) {
        for (int timeout = 0; ; timeout++) {
            if (timeout >= 2) {
                break;
            }
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,200)", "");
            timeWait(2);
        }
    }

    /*
     * ******************************************************************* Function
     * Name: xHighlight Author : winSupply Team Purpose : Highlight the element
     * Parameters: driver, webelement, color = e.g yellow, green etc
     * ******************************************************************
     */

    /*
     * public boolean validatePDFViewer(){ boolean isPdfPresent = false; String
     * pdfPageSource = driver.getPageSource(); switch(envParameters.getBrowser()){
     * case INTERNET_EXPLORER: if(pdfPageSource.equals("")) isPdfPresent=true;
     * break; case FIREFOX: if(pdfPageSource.contains("pdfViewer"))
     * isPdfPresent=true; break; case CHROME:
     * if(pdfPageSource.contains("application/pdf")) isPdfPresent=true; break;
     * default: break; }
     *
     * return isPdfPresent; }
     */

    /*
     * ******************************************************************* Function
     * Name: xHighlight Author : winSupply Team Purpose : Highlight the element
     * Parameters: driver, webelement, color = e.g yellow, green etc
     * ******************************************************************
     */

    /**
     * Use this for scrolling directly to an object (javascript scroll). Will
     * attempt to put that object near the top of the screen.
     *
     * @param driver
     * @param el
     */
    public static void xScrollWindowToElement(FrameworkDriver driver, WebElement el) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", el);
            timeWait(2);
        } catch (Exception e) {
            // Added to prevent false erroring for referencing a "hidden" object
            System.out.println("Internal error: " + e.getMessage() + "\nContinuing after error.");
        }
    }

    public static void xScrollWindowTop(FrameworkDriver driver) {
        // for (int timeout = 0;; timeout++) {
        // if (timeout >= 5) {
        // break;
        // }
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript("window.scroll(0,0)", "");
        timeWait(2);
        // }
    }

    /*
     * ******************************************************************* Function
     * Name: xHighlight Author : CBRE SF Automation Purpose : Highlight the element
     * Parameters: driver, webelement, color = e.g yellow, green etc
     * ******************************************************************
     */

    public static void xScrollWindowTopByValue(FrameworkDriver driver, int value) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript("window.scrollTo(document.body.scrolllHeight, " + value + ")");
        timeWait(2);
        // }
    }

    /*
     * ******************************************************************* Function
     * Name: xHighlight Author : winSupply Team Purpose : Highlight the element
     * Parameters: driver, webelement, color = e.g yellow, green etc
     * ******************************************************************
     */

    public static void xScrollWindowTopJustOnce(FrameworkDriver driver, int value) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript("window.scrollTo(document.body.scrollHeight, " + value + ")");
        timeWait(2);
    }

    public static void xSelectDropdownByIndex(FrameworkDriver driver, WebElement e, Integer index) {
        try {
            xWaitForElementPresent(driver, e, 15);
            xWaitForElementClickable(driver, e, 15);
            Select answer = new Select(e);

            if (answer.getOptions().size() < 1) {
                timeWait(1);
            }
            answer.selectByIndex(index);
        } catch (Exception e2) {
            System.out.println(" Select by DropDownIndex: Error " + xExptnsMsg(e2.getMessage()));
        }

    }

    public static int xGetSelectOptionCount(FrameworkDriver driver, WebElement e) {
        try {
            xWaitForElementPresent(driver, e, 15);
            xWaitForElementClickable(driver, e, 15);
            Select answer = new Select(e);

            if (answer.getOptions().size() < 1) {
                timeWait(1);
            }
            return answer.getOptions().size();
        } catch (Exception e2) {
            System.out.println(" Get dropdown count: Error " + xExptnsMsg(e2.getMessage()));
            return 0;
        }
    }

    /*
     * ******************************************************************* Function
     * Name: xSwitc Author : winSupply Team Purpose : Highlight the element
     * Parameters: driver, webelement, color = e.g yellow, green etc
     * ******************************************************************
     */

    public static void xSelectDropdownByIndex(WebElement e, Integer index) {

        Select answer = new Select(e);
        answer.selectByIndex(index);

    }

    public static String xgetSelectedDropdownValue(FrameworkDriver driver, By e) {

        Select answer = new Select(driver.findElement(e));

        return answer.getFirstSelectedOption().getText();
        //answer.selectByIndex(index);

    }

    public static void xSelectDropdownByName(FrameworkDriver driver, Report report, WebElement e, String visibleName,
                                             String CustMsg) {
        if (visibleName != "") {
            xSelectDropdownByName(driver, e, visibleName);
        }

        report.updateTestLog("SelectDropDownByName", CustMsg + "-- " + visibleName, Status.PASS);

    }

    public static void xSelectDropdownByName(FrameworkDriver driver, Report report, By e, String visibleName,
                                             String CustMsg) {
        if (visibleName != "") {
            xSelectDropdownByName(driver, e, visibleName);
        }

        report.updateTestLog("SelectDropDownByName", CustMsg + "-- " + visibleName, Status.PASS);

    }

    /*
     * ******************************************************************* Function
     * Name: xSelectDropdownByNameRandomValue Author : winSupply Team Purpose : Select Random value in dropdown
     * Parameters: driver, e
     * ******************************************************************
     */

    public static String xSelectDropdownByNameRandomValue(FrameworkDriver driver, By e) {
        xWaitForElementPresent(driver, e, 10);
        xWaitForElementClickable(driver, e, 10);
        WebElement wb = driver.findElement(e);
        Select dropdown = new Select(wb);
        if (dropdown.getOptions().size() <= 0) {
            timeWait(1);
        }
        String selectedOption = dropdown.getFirstSelectedOption().getText().trim();
        String selectOption = "";
        List<WebElement> lstOptions = dropdown.getOptions();
        for (int i = 0; i < lstOptions.size(); i++) {
            String option = lstOptions.get(i).getText().trim();
            if (option.equalsIgnoreCase(selectedOption)) {
                dropdown.selectByVisibleText(option);
                selectOption = option;
                break;
            }
        }
        return selectOption;
    }

    public static void xSelectDropdownByName(FrameworkDriver driver, WebElement e, String visibleName) {
        xWaitForElementPresent(driver, e, 10);
        xWaitForElementClickable(driver, e, 10);
        Select dropdown = new Select(e);
        if (dropdown.getOptions().size() <= 0) {
            timeWait(1);

        }
        dropdown.selectByVisibleText(visibleName);

    }

    public static void xSelectDropdownByName(FrameworkDriver driver, By e, String visibleName) {
        xWaitForElementPresent(driver, e, 10);
        xWaitForElementClickable(driver, e, 10);
        WebElement wb = driver.findElement(e);
        Select dropdown = new Select(wb);
        if (dropdown.getOptions().size() <= 0) {
            timeWait(1);

        }
        dropdown.selectByVisibleText(visibleName);

    }

    public static void xSelectDropdownByName(WebElement e, String visibleName) {
        Select dropdown = new Select(e);
        dropdown.selectByVisibleText(visibleName);

    }

    public static void xSelectDropdownByNameIfAvlbl(FrameworkDriver driver, Report report, WebElement e, String visibleName,
                                                    String CustMsg) {
        if (xIsDisplayed(e) && visibleName != "") {
            xSelectDropdownByName(driver, e, visibleName);
            report.updateTestLog("SelectDropDownByName", CustMsg + "-- " + visibleName, Status.PASS);
        }

    }

    public static void xSelectDropDownByPartialText(FrameworkDriver driver, Report report, WebElement e, String Text) {
        xWaitForElementPresent(driver, e, 10);
        xWaitForElementClickable(driver, e, 10);
        Select dropdown = new Select(e);
        List<WebElement> optionElements = dropdown.getOptions();
        try {
            for (WebElement optionElement : optionElements) {
                if (optionElement.getText().contains(Text)) {

                    String optionValue = optionElement.getAttribute("Value");

                    dropdown.selectByValue(optionValue);

                    report.updateTestLog("Select drop down value by Partial Text",
                            "Verify User is able to select drop down value as " + optionElement.getText(), Status.PASS);
                    break;
                }
            }

            Thread.sleep(300);

        } catch (Exception e2) {
            System.out.println(e2.getStackTrace());

        }

    }

    public static void xSelectDropDownByPartialText(FrameworkDriver driver, Report report, By e, String Text) {

        xWaitForElementClickable(driver, e, 10);

        WebElement el = driver.findElement(e);
        Select dropdown = new Select(el);
        List<WebElement> optionElements = dropdown.getOptions();
        try {
            for (WebElement optionElement : optionElements) {
                if (optionElement.getText().contains(Text)) {

                    String optionValue = optionElement.getAttribute("Value");

                    dropdown.selectByValue(optionValue);

                    report.updateTestLog("Select drop down value by Partial Text",
                            "Verify User is able to select drop down value as " + optionElement.getText(), Status.PASS);
                    break;
                }
            }

            Thread.sleep(300);

        } catch (Exception e2) {
            System.out.println(e2.getStackTrace());

        }

    }
    /*
     * ******************************************************************* Function
     * Name: xContainsvalue Author : winSupply Team Takes a given driver and window
     * title, returns true if the window title exists
     * ******************************************************************
     */

    public static void xSelectDropDownByPartialText(Report report, WebElement e, String Text) {
        Select dropdown = new Select(e);
        List<WebElement> optionElements = dropdown.getOptions();
        try {
            for (WebElement optionElement : optionElements) {
                String drpDwnText = optionElement.getText();
                if (drpDwnText.contains(Text)) {

                    String optionValue = optionElement.getAttribute("Value");

                    dropdown.selectByValue(optionValue);
                    report.updateTestLog("Select drop down by Partial Text", "Successfuly Selected " + drpDwnText,
                            Status.PASS);
                    break;
                }
            }

            Thread.sleep(300);

        } catch (Exception e2) {
            System.out.println(e2.getStackTrace());

        }
    }

    public static void xSelectDropdownByValue(FrameworkDriver driver, WebElement e, String value) {
        xWaitForElementPresent(driver, e, 10);
        xWaitForElementClickable(driver, e, 10);
        Select dropdown = new Select(e);
        if (dropdown.getOptions().size() <= 0) {
            timeWait(1);
        }
        dropdown.selectByValue(value);
    }

    public static void xSelectDropdownByValue(FrameworkDriver driver, By e, String value) {
        xWaitForElementPresent(driver, e, 10);
        xWaitForElementClickable(driver, e, 10);
        WebElement el = driver.findElement(e);
        Select dropdown = new Select(el);
        if (dropdown.getOptions().size() <= 0) {
            timeWait(1);
        }
        dropdown.selectByValue(value);
    }

    public static void xSelectDropdownByVisibleText(FrameworkDriver driver, By e, String value) {
        xWaitForElementPresent(driver, e, 10);
        xWaitForElementClickable(driver, e, 10);
        WebElement el = driver.findElement(e);
        Select dropdown = new Select(el);
        if (dropdown.getOptions().size() <= 0) {
            timeWait(1);
        }
        dropdown.selectByVisibleText(value);
    }

    public static void xSelectDropdownByVisibleText(FrameworkDriver driver, WebElement el, String value) {
        xWaitForElementPresent(driver, el, 10);
        xWaitForElementClickable(driver, el, 10);
        //WebElement el = driver.findElement(e);
        Select dropdown = new Select(el);
        if (dropdown.getOptions().size() <= 0) {
            timeWait(1);
        }
        dropdown.selectByVisibleText(value);
    }

    /*
     * ******************************************************************************************************
     * Function Name: xgetDropdownValueText Author : winSupply Team Purpose : get Dropdown Options as list
     * ******************************************************************************************************
     */
    public static List<String> xgetDropdownOptionsAsList(FrameworkDriver driver, By e) {
        xWaitForElementPresent(driver, e, 10);
        xWaitForElementClickable(driver, e, 10);
        WebElement wb = driver.findElement(e);
        Select dropdown = new Select(wb);
        List<WebElement> elements = dropdown.getOptions();
        List<String> lstElementText = new ArrayList<String>();
        if (elements.size() > 0) {
            for (WebElement ele : elements) {
                String text = ele.getText();
                if (!text.isEmpty())
                    lstElementText.add(text);
            }
        } else
            timeWait(1);
        return lstElementText;

    }

    public static void xSelectDropdownByValue(WebElement e, String value) {
        Select dropdown = new Select(e);
        dropdown.selectByValue(value);

    }

    public static void xSelectRadio(FrameworkDriver driver, WebElement el) {
        boolean orig = el.isSelected();
        Utility_Functions.xClickButton(driver, el, true);
        if (orig == el.isSelected()) {
            Utility_Functions.xSendKeys(driver, el, " ");
        }

    }

    /**
     * Method to select value for typo Box text box
     *
     * @param driver               Pass driver instance
     * @param typeInputTxt         Pass webElement of typo input text box
     * @param typoValueXpathString Pass xpath String typo value
     * @param toBeReplaced         Pass the String to be replaced in typo value
     *                             xpath
     * @param typeInput            Pass String value to Type in typo box also it
     *                             should be same value which will replace
     *                             toBeReplaced string
     */
    public static void xSelectTypoBxValue(FrameworkDriver driver, WebElement typeInputTxt, String typoValueXpathString,
                                          String toBeReplaced, String typeInput) {
        if (typeInputTxt != null) {
            xSendKeys(driver, typeInputTxt, typeInput);
            xSendKeyBoardkeys(typeInputTxt, Keys.ARROW_DOWN);
            xfindElAndClick(driver, typoValueXpathString, toBeReplaced, typeInput);
        }

    }

    public static void xSendCtrlPlusKey(WebElement el, String key) {
        el.click();
        el.clear();
        el.sendKeys(Keys.chord(Keys.CONTROL, key));

    }

    /**
     * Method to Send Keyboard Keys
     *
     * @param el  Pass webelement
     * @param Key Pass Key
     */
    public static void xSendKeyBoardkeys(WebElement el, Keys Key) {
        try {
            el.sendKeys(Key);

        } catch (Exception e) {
            // TODO: handle exception

        }
    }

    public static void xSendKeyBoardkeys(FrameworkDriver driver, By el, Keys Key) {
        try {
            WebElement wb = driver.findElement(el);
            wb.sendKeys(Key);

        } catch (Exception e) {
            // TODO: handle exception

        }
    }

    public static void xSendKeyBoardkeys(FrameworkDriver driver, By el, String input, Keys Key) {

        WebElement wb = driver.findElement(el);
        xWaitForElementPresent(driver, el, 30);
        wb.sendKeys(input);
        //  xSendKeys(wb,input);
        wb.sendKeys(Key);


    }

    /**
     * Method to Send Keyboard keys in Loop
     *
     * @param el    Pass Webelement of Text box
     * @param Key   Pass Keyboad Key
     * @param count How many times it need to be pressed
     */
    public static void xSendKeyBoardkeys(WebElement el, Keys Key, int count) {
        try {
            for (int i = 0; i < count; i++) {
                el.sendKeys(Key);
            }

        } catch (Exception e) {
            // TODO: handle exception

        }
    }

    /*
     * ******************************************************************* Function
     * Name: Random Function Author : Automation Purpose :
     * ******************************************************************
     */

    public static void xSendKeys(FrameworkDriver driver, Report report, By el, String strVal, String CustomMsg) {

        WebElement elment = driver.findElement(el);
        xWaitForElementClickable(driver, el, 10);
        xSendKeys(report, elment, strVal, CustomMsg);
    }

    public static void xSendKeys(FrameworkDriver driver, Report report, WebElement el, String strVal, String CustomMsg) {
        xWaitForElementClickable(driver, el, 5);
        xSendKeys(el, strVal);
        report.updateTestLog("Enter Data", CustomMsg, Status.PASS);
    }

    public static void xSendKeys(FrameworkDriver driver, WebElement el, Keys keyName) {
        // xHighlight(driver, el, "blue");
        el.sendKeys(keyName);

    }

    public static void xSendKeys(FrameworkDriver driver, By el, Keys keyName) {
        // xHighlight(driver, el, "blue");
        WebElement webel = driver.findElement(el);
        webel.sendKeys(keyName);

    }

    /*
     * ******************************************************************* Function
     * Name: xSendKeys Author : winSupply Team Send keys to the input field: driver,
     * webelement, highlight = true/false
     * ******************************************************************
     */
    public static void xSendKeys(FrameworkDriver driver, WebElement el, String strVal) {

        xWaitForElementVisible(driver, el, 10);
        xHighlight(driver, el, "yellow");
        el.click();
        // el.clear();
        el.sendKeys(strVal);
        // timeWait(1);

    }

    /*
     * ******************************************************************* Function
     * Name: xSendKeys Author : winSupply Team Send keys to the input field: driver,
     * webelement, highlight = true/false
     * ******************************************************************
     */
    public static void xClearAndSendKeys(FrameworkDriver driver, WebElement el, String strVal) {

        xWaitForElementVisible(driver, el, 10);
        xHighlight(driver, el, "yellow");
        el.click();
        el.clear();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        new Actions(driver.getWebDriver()).click(el).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("").perform();
        el.sendKeys(strVal);
        // timeWait(1);

    }

    public static void xSendKeys(FrameworkDriver driver, By el, String strVal) {

        xWaitForElementClickable(driver, el, 10);
        xHighlight(driver, el, "yellow");
        WebElement wb = driver.findElement(el);
        wb.click();
        wb.clear();
        wb.sendKeys(strVal);
        // timeWait(1);

    }

    /*
     * ******************************************************************* Function
     * Name: xSendKeys Author : winSupply Team Send keys to the input field: driver,
     * webelement, highlight = true/false
     * ******************************************************************
     */
    public static void xSendKeys(Report report, WebElement el, String strVal, String CustomMsg) {
        xSendKeys(el, strVal);
        report.updateTestLog("Enter Data", CustomMsg, Status.PASS);
    }

    public static void xSendKeys(Report report, WebElement el, WebElement el1, String strVal, String CustomMsg) {
        try {
            xSendKeys(el, strVal);

            report.updateTestLog("Enter Data", CustomMsg, Status.PASS);

        } catch (Exception e) {
            report.updateTestLog("Enter Data", CustomMsg, Status.PASS);

        }

    }

    public static void xSendKeys(WebElement el, String strVal) {
        if (!strVal.isEmpty() && strVal != "" && strVal != null) {
            el.click();
            el.clear();
            el.sendKeys(strVal);
        }

    }

    /*
     * ******************************************************************* Function
     * Name: Validate whether the page is ready
     * ******************************************************************
     */

    /**
     * Method to enter data in text and tab out and update report
     *
     * @param report
     * @param el
     * @param strVal
     * @param CustomMsg
     */
    public static void xSendkeysAndTab(Report report, WebElement el, String strVal, String CustomMsg) {
        try {
            xSendkeysAndTab(el, strVal);
            report.updateTestLog("Enter Data and Tab out from field ", CustomMsg, Status.PASS);

        } catch (Exception e) {

            report.updateTestLog("Enter data", Utility_Functions.xExptnsMsg(e.getMessage()), Status.FAIL);
        }
    }

    /*
     * ******************************************************************* Function
     * Name: Validate whether the fields present on the page or not
     * ******************************************************************
     */

    public static void xSendkeysAndTab(WebElement el, String strVal) {
        xSendKeys(el, strVal);
        Utility_Functions.timeWait(2);
        el.click();
        el.sendKeys(Keys.TAB);

    }


    public static void xSendKeysIfAvlb(FrameworkDriver driver, Report report, WebElement el, String strVal,
                                       String CustomMsg) {
        if (xIsDisplayed(el) && strVal.length() != 0) {
            xWaitForElementClickable(driver, el, 5);
            xSendKeys(el, strVal);
            report.updateTestLog("Enter Data", CustomMsg, Status.PASS);
        }
    }

    public static void xSendKeysIfDisplay(FrameworkDriver driver, Report report, WebElement el, String strVal,
                                          String CustomMsg) {
        try {
            if (xIsDisplayed(el)) {
                xWaitForElementClickable(driver, el, 5);
                xSendKeys(el, strVal);
                report.updateTestLog("Enter Data", CustomMsg, Status.PASS);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void xSendKeysIfDisplay(FrameworkDriver driver, WebElement el, String strVal, String CustomMsg) {
        try {
            if (xIsDisplayed(el)) {
                xWaitForElementClickable(driver, el, 5);
                xSendKeys(el, strVal);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void xSendKeysNonEmpty(WebElement el, String strVal) {
        if (!(strVal.equals("") || strVal.isEmpty())) {
            // xHighlight(driver, el, "yellow");
            // el.clear();
            // timeWait(1);
            el.sendKeys(strVal);
            el.sendKeys(Keys.TAB);
        }
        // timeWait(1);

    }

    /*
     * ******************************************************************* Function
     * Name: xSendKeysVisibleListElement Author : winSupply Team Purpose : SendKeys
     * to the visible element field: driver, List<WebElement>,string
     * ******************************************************************
     */
    public static void xSendKeysVisibleListElement(List<WebElement> eleList, String strVal) {

        for (WebElement element : eleList) {
            if (element.isDisplayed()) {
                xSendKeys(element, strVal);
                break;
            }
        }

    }

    /*
     * ******************************************************************* Function
     * Name: Miscellaneous functions Author : winSupply Team Purpose : Hover and
     * action Parameters: webelement, index
     * ******************************************************************
     */
    // Maximizing the browser size
    public static void xSetBrowserSize(FrameworkDriver driver) {
        driver.manage().window().maximize();
        // driver.manage().window().setSize(new Dimension(900, 700));
    }

    public static void xswitchToDfultFrame(FrameworkDriver driver) {
        driver.switchTo().defaultContent();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
    }

    /*
     * ******************************************************************* Function
     * Name: xSwitchFrames Author : CBRE SF :
     *
     * ******************************************************************
     */
    public static void xSwitchtoFrame(FrameworkDriver driver, WebElement webElement) {
        List<WebElement> iframeList = driver.findElements(By.tagName("iframe"));
        timeWait(2);
        driver.switchTo().defaultContent();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        for (WebElement element : iframeList) {
            driver.switchTo().frame(element);
            try {
                boolean isTextValuePresent = Utility_Functions.xWaitForElementPresent(driver, webElement, 0);
                if (isTextValuePresent) {
                    System.out.println("Switched to Frame");
                    break;
                } else {
                    driver.switchTo().defaultContent();
                }
            } catch (Exception ex) {
                driver.switchTo().defaultContent();
            }
        }
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

    }

    public static boolean xswitchedToFrame(FrameworkDriver driver, WebElement webElement) {
        List<WebElement> iframeList = driver.findElements(By.tagName("iframe"));
        timeWait(2);
        boolean isswitch = false;
        driver.switchTo().defaultContent();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        for (WebElement element : iframeList) {
            driver.switchTo().frame(element);
            try {
                boolean isTextValuePresent = Utility_Functions.xWaitForElementPresent(driver, webElement, 0);
                if (isTextValuePresent) {
                    isswitch = true;
                    System.out.println("Switched to Frame");
                    break;
                } else {
                    driver.switchTo().defaultContent();
                }
            } catch (Exception ex) {
                driver.switchTo().defaultContent();
                isswitch = false;
            }
        }
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        return isswitch;
    }

    /*
     * ******************************************************************* Function
     * Name: xSwitchFrames Author : CBRE SF :
     *
     * ******************************************************************
     */
    public static boolean xSwitchtoFrame(FrameworkDriver driver, By webElement) {
        driver.switchTo().defaultContent();
        List<WebElement> iframeList = driver.findElements(By.tagName("iframe"));
        timeWait(2);
        boolean isSwitched = false;

        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        for (WebElement element : iframeList) {
            driver.switchTo().frame(element);
            try {
                boolean isTextValuePresent = Utility_Functions.xWaitForElementPresent(driver, webElement, 0);
                if (isTextValuePresent) {
                    System.out.println("Switched to Frame");
                    isSwitched = true;
                    break;
                } else {
                    driver.switchTo().defaultContent();
                    isSwitched = false;
                }
            } catch (Exception ex) {
                driver.switchTo().defaultContent();
                isSwitched = false;
            }
        }
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        return isSwitched;

    }

    /**
     * @param driver       GS Web FrameworkDriver
     * @param webElement   Page WebElement
     * @param FrameTagName Pass Frame tag name(e.g. iframe,frame)
     */
    public static void xSwitchtoFrame(FrameworkDriver driver, WebElement webElement, String FrameTagName) {
        driver.switchTo().defaultContent();

        List<WebElement> iframeList = driver.findElements(By.tagName(FrameTagName));

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        for (WebElement element : iframeList) {
            driver.switchTo().frame(element);
            try {
                boolean isTextValuePresent = Utility_Functions.xWaitForElementPresent(driver, webElement, 0);
                if (isTextValuePresent) {
                    break;
                } else {
                    driver.switchTo().defaultContent();
                }
            } catch (Exception ex) {
                driver.switchTo().defaultContent();
            }
        }
        driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);

    }

    public static void xswitchToFrameById(FrameworkDriver driver, String id) {
        driver.switchTo().frame(id);
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
    }

    /**
     * Method to Switch to frame within frame
     *
     * @param driver       Pass drive instance
     * @param webElement   pass webelment which is present in expected frame
     * @param FrameTagName Pass the Frame tage frame/iframe
     */
    public static void xSwitchtoMultiFrame(FrameworkDriver driver, WebElement webElement, String FrameTagName) {
        driver.switchTo().defaultContent();
        List<WebElement> iframeList = driver.findElements(By.tagName(FrameTagName));
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        for (WebElement element : iframeList) {
            driver.switchTo().frame(element);
            List<WebElement> frameList = driver.findElements(By.tagName(FrameTagName));
            if (frameList.size() > 0) {
                for (WebElement el2 : frameList) {

                    driver.switchTo().frame(el2);

                    boolean isTextValuePresent1 = Utility_Functions.xWaitForElementPresentInFrame(driver, webElement,
                            0);
                    if (isTextValuePresent1) {
                        break;
                    } else {
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame(element);
                    }
                }
                break;
            } else {
                driver.switchTo().defaultContent();
            }

            driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);

        }
    }

    public static void xSwitchToParentWin(FrameworkDriver driver) {

        Utility_Functions.xSwitchToWindow(driver, 0);
        WebDriver popup = driver.getWebDriver();
        System.out.println("Window Title : " + popup.getTitle());

    }

    /**
     * Window switch by window number
     *
     * @param driver
     * @param windowNumber
     */
    public static void xSwitchToWindow(FrameworkDriver driver, int windowNumber) {
        Set<String> window = driver.getWindowHandles();
        Iterator<String> itr = window.iterator();
        int count = 0;

        if (windowNumber < 0)
            return;

        while (itr.hasNext()) {
            driver.switchTo().window(itr.next());
            if (count == windowNumber)
                break;
            count++;
        }
    }

    /*
     * public static void xUpdateListMap(String listKey, String srchByKey, String
     * srchByVal, String mapKey, String val) { List<HashMap<String, Object>> list =
     * xGetJsonList(listKey); for (int i = 0; i < list.size(); i++) { if
     * (list.get(i).get(srchByKey).equalsIgnoreCase(srchByVal)) {
     * list.get(i).replace(mapKey, val); xUpdateJson(listKey, list); break; }
     *
     * }
     *
     * }
     */

    public static void xSwitchToWindow(FrameworkDriver driver, Report report, int windowNumber) {
        Set<String> window = driver.getWindowHandles();
        Iterator<String> itr = window.iterator();
        int count = 0;

        if (windowNumber < 0)
            return;

        while (itr.hasNext()) {
            driver.switchTo().window(itr.next());
            if (count == windowNumber) {

                report.updateTestLog("SwitchToWindow", "Switch To Parent Window", Status.PASS);
                break;
            }

            count++;
        }
    }

    public static void xSwitchToWindow(FrameworkDriver driver, Report report, String windowName) {
        WebDriver popup = driver.getWebDriver();
        Set<String> windowIterator = driver.getWindowHandles();
        System.err.println("No of windows :  " + windowIterator.size());
        for (String s : windowIterator) {
            String windowHandle = s;
            popup = driver.switchTo().window(windowHandle);
            System.out.println("Window Title : " + popup.getTitle());
            System.out.println("Window Url : " + popup.getCurrentUrl());
            if (popup.getTitle().contains(windowName)) {
                System.out.println("Selected Window Title : " + popup.getTitle());
                report.updateTestLog("SwitchToWindow",
                        "Switch to Window  containing Title as  " + popup.getTitle() + " ", Status.PASS);
                // driver = popup;
                break;

            }
        }
    }
    /*
     * ******************************************************************* Function
     * Name: xSwitc Author : winSupply Team Purpose : Switch to Parent Window
     * element Parameters: driver, webelement, color = e.g yellow, green etc
     * ******************************************************************
     */

    public static void xSwitchToWindow(FrameworkDriver driver, String windowName) {
        WebDriver popup = driver.getWebDriver();
        Set<String> windowIterator = driver.getWindowHandles();
        System.err.println("No of windows :  " + windowIterator.size());
        for (String s : windowIterator) {
            String windowHandle = s;
            popup = driver.switchTo().window(windowHandle);
            System.out.println("Window Title : " + popup.getTitle());
            System.out.println("Window Url : " + popup.getCurrentUrl());
            if (popup.getTitle().contains(windowName)) {
                System.out.println("Selected Window Title : " + popup.getTitle());
                // driver = popup;
                break;

            }
        }
    }

    public static void xUpdateExistingMap(String Key, HashMap<String, String> map) {

        HashMap<String, String> m = xGetJsonMap(Key);
        m.putAll(map);
        xUpdateJson(Key, m);

    }

    @SuppressWarnings("unchecked")
    public static void xUpdateJson(String Key, HashMap<String, String> map) {
        try {
            createJsonFile(jsonFile);
            FileReader fr = new FileReader(jsonFile);
            Object obj = new JSONParser().parse(fr);

            JSONObject obj1 = new JSONObject(map);

            /*
             * ObjectMapper objectMapper = new ObjectMapper(); String test =
             * objectMapper.writeValueAsString(list);
             */

            JSONObject jo = (JSONObject) obj;
            jo.put(Key, obj1);
            PrintWriter pw = new PrintWriter(jsonFile);
            pw.write(jo.toJSONString());
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Internal function to Create new json file if not available
     *
     * @param fileName
     * @return
     */
    public static File createJsonFile(String fileName) {
        File rtnFile = null;
        try {
            FileReader fr = new FileReader(fileName);

            rtnFile = new File(fileName);
            if (rtnFile.length() < 1) {
                rtnFile.delete();
                throw new Exception();
            }

            return rtnFile;

        } catch (Exception e) {

            JSONObject jsonObject = new JSONObject();


//            jsonObject.put("Orders", new JSONArray());
//            jsonObject.put("Inventory", "");


            try {
                FileWriter file = new FileWriter(fileName);
                file.write(jsonObject.toJSONString());
                file.close();
                return rtnFile = new File(fileName);
            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }

        return rtnFile;
    }

    @SuppressWarnings("unchecked")
    public static void xUpdateJson(String Key, List<HashMap<String, Object>> list) {
        try {
            createJsonFile(jsonFile);
            Object obj = new JSONParser().parse(new FileReader(jsonFile));
            ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();

            List<JSONObject> jsonObj = new ArrayList<>();

            for (HashMap<String, Object> data : list) {
                JSONObject obj1 = new JSONObject(data);
                jsonObj.add(obj1);
            }
            JSONObject jo = (JSONObject) obj;
            jo.put(Key, jsonObj);
            PrintWriter pw = new PrintWriter(jsonFile);
            pw.write(jo.toJSONString());
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    public static void xUpdateJson(String Key, String value) {
        try {
            createJsonFile(jsonFile);
            Object obj = new JSONParser().parse(new FileReader(jsonFile));

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            jo.put(Key, value);

            PrintWriter pw = new PrintWriter(jsonFile);
            pw.write(jo.toJSONString());

            pw.flush();
            pw.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public static void xUpdateJson(String Key, Object value) {
        try {
            createJsonFile(jsonFile);
            Object obj = new JSONParser().parse(new FileReader(jsonFile));

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            jo.put(Key, value);

            PrintWriter pw = new PrintWriter(jsonFile);
            pw.write(jo.toJSONString());

            pw.flush();
            pw.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public static void xUpdateJsonWithArray(String Key, String value) {
        try {
            createJsonFile(jsonFile);
            Object obj = new JSONParser().parse(new FileReader(jsonFile));

            JSONArray valArray = new JSONArray();


            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;
            valArray = (JSONArray) jo.getOrDefault(Key, valArray);

            valArray.add(value);

            jo.put(Key, valArray);

            PrintWriter pw = new PrintWriter(jsonFile);
            pw.write(jo.toJSONString());

            pw.flush();
            pw.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }

    }

    /**
     * Method to upload file using ROBOT class
     *
     * @param fileLocation
     * @author winSupply
     */
    public static void xUploadFile(Report report, String fileLocation) {
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(3000);
            StringSelection ss = new StringSelection(fileLocation);
            // copy the above string to clip board
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
            robot.setAutoDelay(3000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.setAutoDelay(3000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            report.updateTestLog("Upload ", "File upload" + fileLocation, Status.PASS);
        } catch (Exception exp) {
            exp.printStackTrace();
            report.updateTestLog("Upload", "File upload failed for " + fileLocation, Status.FAIL);
        }

    }

    public static boolean xvalidateFieldAttrValueMatches(Report report, WebElement tar, String expValue,
                                                         String CustMsg) {
        String val = tar.getAttribute("value");
        val = val.isEmpty() ? "" : val;
        if (val != null && val.toUpperCase().contains(expValue.toUpperCase())) {
            report.updateTestLog(CustMsg, "Expected text '" + expValue + "' is matching With Actual Text '" + val + "'",
                    Status.PASS);
            return true;
        } else {
            report.updateTestLog("VerifyTextContains",
                    "Expected text '" + expValue + "' is  Not matching With Text at failure  '" + val + "'",
                    Status.FAIL);
            return false;
        }
    }

    public static List<String> xValidateFieldsPresentonPage(List<String> List1, List<WebElement> WebElements,
                                                            String TextToBeDisplayed) {
        List<String> WebElementsList = new ArrayList<String>();
        for (WebElement element : WebElements) {
            WebElementsList.add(element.getText().toString());
        }
        System.out.println(TextToBeDisplayed + " -- " + WebElementsList + " are present in the page");
        List1.removeAll(WebElementsList);
        System.out.println(TextToBeDisplayed + " -- " + List1 + " which aren't present in the page");
        return List1;
    }

    public static int xValidateFieldsPresentonPage(String text, List<WebElement> WebElements,
                                                   String TextToBeDisplayed) {
        int count = 0;
        if (WebElements.size() == 0) {
            System.out.println("There are no elements identified with the List<WebElement> xpath");
        } else {
            String labelArray[] = new String[WebElements.size()];
            int i1 = 0;
            try {
                for (WebElement element : WebElements) {
                    labelArray[i1] = element.getText();
                    if (labelArray[i1].equalsIgnoreCase(text)) {
                        System.out.println("Verify the " + TextToBeDisplayed + " Fields:::" + element.getText());
                        count++;
                    }
                    i1++;
                }
                i1 = 0;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            Utility_Functions.timeWait(1);
        }
        return count;
    }
    /*
     * ******************************************************************* Function
     * Name: Click on the element from the list of elements
     * ******************************************************************
     */

    public static int xValidateFieldsPresentPage(List<String> FieldList, List<WebElement> WebElements,
                                                 String TextToBeDisplayed) {
        int count = 0;
        if (WebElements.size() == 0) {
            System.out.println("There are no elements identified with the List<WebElement> xpath");
        } else {
            String labelArray[] = new String[WebElements.size()];
            int j = 0, i1 = 0;
            try {
                while (j < FieldList.size()) {
                    for (WebElement element : WebElements) {
                        labelArray[i1] = element.getText();
                        labelArray[i1] = labelArray[i1].replace("\"", "");
                        if (labelArray[i1].equalsIgnoreCase(FieldList.get(j))) {
                            System.out.println("Verify the " + TextToBeDisplayed + " Fields:::" + element.getText());
                            count++;
                        }
                        i1++;
                    }
                    i1 = 0;
                    j++;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            Utility_Functions.timeWait(1);
        }
        return count;
    }

    public static boolean xVrfyTextAvlble(FrameworkDriver driver, Report report, WebElement tar, String expValue,
                                          String CstmMsg) {

        String val = getText(tar, driver);
        if (val.contains(expValue)) {
            report.updateTestLog(CstmMsg, "Expected text '" + expValue + "' is matching With Actual Text '" + val + "'",
                    Status.PASS);
            return true;
        } else {
            xHighlight(driver, tar, "red");
            report.updateTestLog(CstmMsg,
                    "Expected text '" + expValue + "' is  Not matching With Text at failure  '" + val + "'",
                    Status.FAIL);
            return false;
        }
    }

    public static boolean xVrfyTextAvlble(Report report, String expValue, String Actual, String CstmMsg) {
        try {

            Assert.assertEquals(Actual, (expValue));

            if (Actual.equalsIgnoreCase(expValue)) {
                report.updateTestLog("Assertion",
                        CstmMsg + " Expected value '" + expValue + "' is matching With Actual Value '" + Actual + "'",
                        Status.PASS);
                return true;
            } else {
                report.updateTestLog("AssertionFail", CstmMsg + " Expected value '" + expValue
                        + "' is  Not matching With Text at failure  '" + Actual + "'", Status.FAIL);
                return false;
            }
        } catch (AssertionError e) {
            report.updateTestLog("AssertionFail", CstmMsg + e.getMessage(), Status.FAIL);
            return false;
        }
    }

    /**
     * Utility Method to verify target value is matching with expected value
     *
     * @param report
     * @param tar
     * @param expValue
     * @return
     */
    public static boolean xVrfyTextAvlble(Report report, WebElement tar, String expValue) {
        String val = tar.getText();
        if (val != null && val.toUpperCase().equalsIgnoreCase(expValue.toUpperCase())) {
            report.updateTestLog("VerifyTextContains",
                    "Expected text '" + expValue + "' is matching With Actual Text '" + val + "'", Status.PASS);
            return true;
        } else {
            report.updateTestLog("VerifyTextContains",
                    "Expected text '" + expValue + "' is  Not matching With Text at failure  '" + val + "'",
                    Status.FAIL);
            return false;
        }
    }

    public static boolean xVrfyTextAvlble(Report report, WebElement tar, String expValue, String CstmMsg) {
        String val = tar.getText();
        if (val.contains(expValue)) {
            report.updateTestLog(CstmMsg, "Expected text '" + expValue + "' is matching With Actual Text '" + val + "'",
                    Status.PASS);
            return true;
        } else {
            report.updateTestLog(CstmMsg,
                    "Expected text '" + expValue + "' is  Not matching With Text at failure  '" + val + "'",
                    Status.FAIL);
            return false;
        }
    }

    public static boolean xVrfyTextAvlble(WebElement tar, String expValue) {
        String val = tar.getText();
        if (val.contains(expValue)) {

            return true;
        } else {

            return false;
        }
    }

    // ******************************************************************
    // wait for a particular attribute of the webelement
    public static void xWaitForElementAttribute(FrameworkDriver driver, By Locator, String attribute, String message,
                                                int timeWait) {
        int timeout = 0;
        while (!driver.findElement(Locator).getAttribute(attribute).contains(message)) {
            if (timeout >= 20) {
                break;
            }
            timeWait(2);
            timeout++;
        }
    }

    public static void xWaitForElementClickable(FrameworkDriver driver, By el, int timeWait) {

        FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(el));
        wait.until(ExpectedConditions.elementToBeClickable(el));

        // return true;
    }

    // ******************************************************************
    // wait for the element to be clickable
    public static void xWaitForElementClickable(FrameworkDriver driver, WebElement el, int timeWait) {
        // xWaitForLoad(driver, timeWait);
        xWaitForElementPresent(driver, el, 10);
        FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(el));
        wait.until(ExpectedConditions.elementToBeClickable(el));
        xHighlight(driver, el, "green");
        // return true;
    }

    // ******************************************************************
    // wait for element to disappear
    public static boolean xWaitForElementDisappear(FrameworkDriver driver, By locator, int timeWait) {
        try {
            FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                    .ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (StaleElementReferenceException e) {
            System.out.println(e.getMessage());
            FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                    .ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            System.out.println("Element could not disappear within the specified time");
            System.out.println(e.getMessage());
            return false;
        }

    }

    // ******************************************************************
    // wait for element to disappear
    public static boolean xWaitForElementDisappear(FrameworkDriver driver, WebElement locator, int timeWait) {
        try {
            FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                    .ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.invisibilityOf(locator));
            return true;
        } catch (Exception e) {
            System.out.println("Element could not disappear within the specified time");
            System.out.println(e.getMessage());
            return false;
        }

    }

    // ******************************************************************
    // wait for element to disappear
    public static void xwaitForElmntToDisappr(FrameworkDriver driver, By locator, int timeWait, int iteration) {
        int i = 0;
        while (i <= iteration) {
            if (xWaitForElementDisappear(driver, locator, timeWait)) {
                break;
            }

        }

    }

    // ******************************************************************
    // wait for element present
    public static boolean xWaitForElementPresent(FrameworkDriver driver, By locator, int timeWait) {
        try {
            FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                    .ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;

        } catch (NoSuchElementException e) {

            return false;
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean xWaitForElementPresent(FrameworkDriver driver, List<WebElement> elements, int timeWait) {
        FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        return true;
    }

    // ******************************************************************
    // wait for element present
    public static boolean xWaitForElementPresent(FrameworkDriver driver, WebElement el, int timeWait) {
        FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(el));

        return true;
    }

    public static boolean xWaitForElementPresentInFrame(FrameworkDriver driver, WebElement el, int timeWait) {
        try {
            WebDriverWait wait = new WebDriverWait(driver.getWebDriver(), timeWait);
            wait.until(ExpectedConditions.visibilityOf(el));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    // wait for the element to be clickable
    public static void xWaitForElementToBeClickable(FrameworkDriver driver, WebElement el, int timeWait) {

        FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(el));
        xHighlight(driver, el, "blue");
        // return true;
    }

    // wait for the element to be clickable
    public static void xWaitForElementToBeClickable(FrameworkDriver driver, By el, int timeWait) {

        FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(el));
        xHighlight(driver, el, "blue");
        // return true;
    }

    // wait for the element to be visible
    public static boolean xWaitForElementVisible(FrameworkDriver driver, WebElement el, int timeWait) {
        try {

            FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                    .ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.visibilityOf(el));
            return true;
        } catch (StaleElementReferenceException e) {
            FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                    .ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.visibilityOf(el));
            return true;
        }
    }

    public static boolean xWaitForElementVisible(FrameworkDriver driver, By el, int timeWait) {
        try {

            FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                    .ignoring(StaleElementReferenceException.class);
            WebElement wb = driver.findElement(el);
            wait.until(ExpectedConditions.visibilityOf(wb));
            return true;
        } catch (StaleElementReferenceException e) {
            FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                    .ignoring(StaleElementReferenceException.class);
            WebElement wb = driver.findElement(el);
            wait.until(ExpectedConditions.visibilityOf(wb));
            return true;
        }

    }

    public static boolean waitForElementVisible(FrameworkDriver driver, By el, int timeWait) {
        try {

            FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                    .ignoring(StaleElementReferenceException.class);
            WebElement wb = driver.findElement(el);
            wait.until(ExpectedConditions.visibilityOf(wb));
            return true;
        } catch (StaleElementReferenceException e) {
            FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
                    .ignoring(StaleElementReferenceException.class);
            WebElement wb = driver.findElement(el);
            wait.until(ExpectedConditions.visibilityOf(wb));
            return true;
        } catch (NoSuchElementException e) {
            System.out.print(e.getLocalizedMessage());
            return false;
        }

    }


    /*
     * public boolean compare(List<Map<String, Object>> A, List<Map<String, String>>
     * B) { // check size first if (A.size() == B.size()) { // if the Maps are
     * abstracted into a POJO you could implement Comparator on that POJO. In the
     * meantime you can sort manually // sort A Map<Object, Object> sorted =
     * A.entrySet().stream()
     * .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
     * .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
     * LinkedHashMap::new));
     *
     * Collections.sort(B, new Comparator<Map<String, String>>() { public int
     * compare(final Map<String, String> o1, final Map<String, String> o2) { return
     * o1.get("field").compareTo(o2.get("field")); } });
     *
     *
     * for (int i = 0; i < A.size(); i++) { // get map from A & B Map<String,
     * Object> aMap = A.get(i); Map<String, Object> bMap = B.get(i);
     *
     * // check equality of Maps if (!aMap.equals(bMap)) { return false; } } } else
     * { // Data reconciliation failed :: Data size mismatch return false; }
     *
     * // if we get here then all was good return true; }
     */
    public static void xWaitForLoad(FrameworkDriver driver, int timewait) {
        new WebDriverWait(driver.getWebDriver(), timewait)
                .until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
                        .executeScript("return document.readyState").equals("complete"));
    }

    public static boolean xWaitPageLoad(FrameworkDriver driver, WebElement element, int timeWait) {
        WebDriverWait wait = new WebDriverWait(driver.getWebDriver(), timeWait);
        wait.until(ExpectedConditions.visibilityOf(element));
        return true;
    }

    public static void xWaitPageReady(FrameworkDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        if (js.executeScript("return document.readyState").toString().equals("complete")) {
            try {
                Thread.sleep(1000);
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Page is loaded:::");
            return;
        }
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                if (js.executeScript("return document.readyState").toString().equals("complete")) {
                    break;
                }
            }
        }
    }

    /*
     * wait until the popup windows is open or closed
     */
    public static boolean xWaitUntilnoOfWindows(FrameworkDriver driver, int noOfWindows, int timeWait) {
        // TODO Auto-generated method stub
        WebDriverWait wait = new WebDriverWait(driver.getWebDriver(), timeWait);
        wait.until(ExpectedConditions.numberOfWindowsToBe(noOfWindows));
        return true;
    }

    public static void xWinLogin(String userName, String Pws) {
        try {
            Robot robot = new Robot();

        } catch (AWTException e) {

            e.printStackTrace();
        }

    }

    // Generic Map filterbyvalue, with predicate
    public static <K, V> Map<K, V> xFilterMapByVal(Map<K, V> map, Predicate<V> predicate) {
        return map.entrySet().stream().filter(x -> predicate.test(x.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @SuppressWarnings("unchecked")
    public static List<HashMap<String, Object>> getOrderFullfillmentData(String node) {
        List<HashMap<String, Object>> data = Utility_Functions.xGetJsonList("Orders");
        List<HashMap<String, Object>> ordDtls = new ArrayList<HashMap<String, Object>>();

        for (HashMap<String, Object> ordDtl : data) {
            List<HashMap<String, Object>> sortedOrder = new ArrayList<HashMap<String, Object>>();
            String ordId = null;
            long qty1 = 0;
            HashMap<String, Object> ordsMap = new HashMap<String, Object>();

            List<HashMap<String, Object>> skuStore = new ArrayList<HashMap<String, Object>>();
            if (ordDtl.keySet().contains("commerceItems")) {
                skuStore = (List<HashMap<String, Object>>) ordDtl.get("commerceItems");

                for (HashMap<String, Object> skus : skuStore) {
                    HashMap<String, Object> ssOrds = new HashMap<String, Object>();
                    String strNo = (String) (skus.get("shipNode") == null ? "null" : skus.get("shipNode"));
                    String status = (String) skus.get("OMSStatus");
                    if (strNo.contains(node) && !status.equalsIgnoreCase("Shipped")
                            && !status.equalsIgnoreCase("Cancelled") && !status.equalsIgnoreCase("PartiallyShipped")) {
                        ordId = (String) ordDtl.get("OrderID");
                        // qty1 = (long) skus.get("qty");
                        String sku = (String) ordDtl.get("sku");
                        ssOrds.putAll(skus);

                    }
                    if (!ssOrds.isEmpty()) {
                        sortedOrder.add(ssOrds);
                    }

                }

            }

            if (ordId != null) {
                ordsMap.put("OrderId", ordId);
                ordsMap.put("commerceItems", sortedOrder);
                ordDtls.add(ordsMap);
            }

        }
        return ordDtls;

    }

    public static void xSendKey(FrameworkDriver driver, String weblementStrng, String inputText) {
        WebElement el = findElementByXpath(driver, weblementStrng);
        xWaitForElementClickable(driver, el, 10);
        xSendkeysAndTab(el, inputText);
    }

    /**
     * Function to verify actual vs Expected and add retrun as string
     *
     * @param actual   pass actual value
     * @param expected pass expected
     * @param Column   pass table column Name
     * @return
     */
    public static StringBuilder verifyTb(String actual, String expected, String Column) {
        StringBuilder strB = new StringBuilder();

        if ((actual == null ? "Null" : actual).equalsIgnoreCase(expected == null ? "Null" : expected)) {
            String msg = "<p style=\"color:green;\">" + Column + ":- Expected Val:" + expected + "  Actual val: "
                    + actual + " Matched \r\n </p>";
            strB.append(msg);
        } else {
            String msg = "<p style=\"color:red;\">" + Column + " :- Expected Val:" + expected + "  Actual val: "
                    + actual + " NotMatched \r\n";
            strB.append(msg);
        }

        return strB;

    }

    /**
     * Function to verify actual vs Expected and add return as string
     *
     * @param actual
     * @param expected
     * @param expected2
     * @param Column
     * @return
     */
    public static StringBuilder verifyTb(String actual, String expected, String expected2, String Column) {
        StringBuilder strB = new StringBuilder();

        if ((actual == null ? "" : actual).contains(expected)) {
            String msg = "<p style=\"color:green;\">" + Column + ":- Expected Val:" + expected + "  Actual val: "
                    + actual + " Matched \r\n </p>";
            strB.append(msg);

        } else if ((actual == null ? "" : actual).contains(expected2)) {
            String msg = "<p style=\"color:green;\">" + Column + ":- Expected Val:" + expected2 + "  Actual val: "
                    + actual + " Matched \r\n </p>";
            strB.append(msg);
        } else {
            String msg = "<p style=\"color:red;\">" + Column + " :- Expected Val:" + expected + "  Actual val " + actual
                    + " NotMatched \r\n";
            strB.append(msg);
        }

        return strB;

    }

    /**
     * Function to verify actual vs Expected and add return as string
     *
     * @param actual
     * @param expected
     * @param expected2
     * @param Column
     * @return
     */
    public static StringBuilder verifyTb(String actual, String expected, String expected2, String exp3, String Column) {
        StringBuilder strB = new StringBuilder();

        if ((actual == null ? "" : actual).contains(expected)) {
            String msg = "<p style=\"color:green;\">" + Column + ":- Expected Val:" + expected + "  Actual val: "
                    + actual + " Matched \r\n </p>";
            strB.append(msg);

        } else if ((actual == null ? "" : actual).contains(expected2)) {
            String msg = "<p style=\"color:green;\">" + Column + ":- Expected Val:" + expected2 + "  Actual val: "
                    + actual + " Matched \r\n </p>";
            strB.append(msg);
        } else if ((actual == null ? "" : actual).contains(exp3)) {
            String msg = "<p style=\"color:green;\">" + Column + ":- Expected Val:" + exp3 + "  Actual val: " + actual
                    + " Matched \r\n </p>";
            strB.append(msg);
        } else {
            String msg = "<p style=\"color:red;\">" + Column + " :- Expected Val:" + expected + "  Actual val " + actual
                    + " NotMatched \r\n";
            strB.append(msg);
        }

        return strB;

    }

    /**
     * Internal Method to convert String into Document
     *
     * @param xmlString
     * @return
     */
    public static Document convertStringToXMLDocument(String xmlString) {
        // Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            // Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            // Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));

            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeStatement(Statement stmt, Report report) {
        try {
            stmt.close();
        } catch (SQLException e) {
            report.updateTestLog("closeStatement", "Error" + e.getLocalizedMessage(), Status.FAIL);
        }
    }

    /**
     * Method to parse Exception
     *
     * @param e Exception
     * @return
     */
    public static Map<String, String> parseException(Exception e) {
        Map<String, String> mapK = new HashMap<>();
        StackTraceElement[] stackTrace = e.getStackTrace();
        String fullClassName = stackTrace[0].getClassName();
        String className = fullClassName.substring(fullClassName
                .lastIndexOf(".") + 1);
        String methodName = stackTrace[0].getMethodName();
        int lineNumber = stackTrace[0].getLineNumber();
        mapK.put("className", fullClassName);
        mapK.put("methodName", methodName);
        mapK.put("lineNumber", Integer.toString(lineNumber));
        return mapK;

    }

    public static List<HashMap<String, Object>> sortList(List<HashMap<String, Object>> list) {
        Collections.sort(list, new Comparator<HashMap<String, Object>>() {
            private static final String KEY_NAME = "TC_ID";

            @Override
            public int compare(HashMap<String, Object> o1, HashMap<String, Object> o2) {
                String str1 = new String();
                String str2 = new String();

                Double str3 = null;
                Double str4 = null;


                try {
                    str1 = (String) o1.get(KEY_NAME);
                    str2 = (String) o2.get(KEY_NAME);
                    str3 = Double.parseDouble(str1.split("-")[1]);
                    str4 = Double.parseDouble(str2.split("-")[1]);
//                    str3=   new BigInteger(str1.split("-")[1]);
//                    str4=    new BigInteger(str2.split("-")[1]);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                return str3.compareTo(str4);
            }
        });
        List<HashMap<String, Object>> ordDtlsSorted = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ordDtlsSorted.add(list.get(i));

        }
        return ordDtlsSorted;
    }

    public static int createSHHTunnel() {
        String dbhost = properties.getProperty("MYSQLHost");
        ;
        // String      = "52.37.147.83";
        String sshHost = properties.getProperty("SSHHost");
        String sshUserName = properties.getProperty("SSHUser");
        String rport = properties.getProperty("Port");
        String sshKeyPath = properties.getProperty("SSHKeyPath");
        int assinged_port = 4321;
        int lport = 4321;
        int port = 22;
        try {
            JSch jsch = new JSch();
            jsch.addIdentity(sshKeyPath);
            Session session = jsch.getSession(sshUserName, sshHost);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            assinged_port = session.setPortForwardingL(lport, dbhost, Integer.parseInt(rport));
            System.out.println("localhost:" + assinged_port + " -> " + dbhost + ":" + rport);
        } catch (Exception e) {
            System.err.print(e);
        }
        return assinged_port;
    }

    public static Connection xConnectSSHTunnel(int forwardPort, String dbUser, String dbPwd, String dbName) {
        Connection connection = null;
        String driver = "com.mysql.cj.jdbc.FrameworkDriver";
        String url = "jdbc:mysql://" + "localhost" + ":" + forwardPort + "/";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url + dbName, dbUser, dbPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public HashMap<String, String> getHashMapFromList(List<HashMap<String, String>> list, String srchBy) {

        HashMap<String, String> returnHMap = new HashMap<String, String>();
        for (HashMap<String, String> hashMap : list) {
            for (String key : hashMap.keySet()) {
                hashMap.get(key).equalsIgnoreCase(srchBy);
                returnHMap = hashMap;
                break;
            }

        }
        return returnHMap;

    }


//    public static void SSHClient(String serverIp,String command, String usernameString,String password) throws IOException{
//        System.out.println("inside the ssh function");
//        try
//        {
//            Connection conn ;
//
//            conn.connect();
//            boolean isAuthenticated = conn.authenticateWithPassword(usernameString, password);
//            if (isAuthenticated == false)
//                throw new IOException("Authentication failed.");
//            ch.ethz.ssh2.Session sess = conn.openSession();
//            sess.execCommand(command);
//            InputStream stdout = new StreamGobbler(sess.getStdout());
//            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
//            System.out.println("the output of the command is");
//            while (true)
//            {
//                String line = br.readLine();
//                if (line == null)
//                    break;
//                System.out.println(line);
//            }
//            System.out.println("ExitCode: " + sess.getExitStatus());
//            sess.close();
//            conn.close();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace(System.err);
//
//        }
//    }


    private boolean inputData(String tagID, String Value) {
        boolean isElementFound = false;
        List<WebElement> GenRiskInfoList = ownDriver.findElements(By.xpath(".//*[@id[contains(.,'" + tagID + "')]]"));
        Utility_Functions.timeWait(1);

        for (WebElement GenRisk : GenRiskInfoList) {
            // System.out.println(GenRisk.getAttribute("id"));
            if (GenRisk.getAttribute("id").endsWith(tagID)) {
                if (GenRisk.getTagName().equals("input")) {

                    Utility_Functions.xSendKeys(ownDriver, GenRisk, Value);

                    isElementFound = true;
                }
                if (GenRisk.getTagName().equals("select")) {
                    if (Value != null && !(Value.equals(""))) {
                        Utility_Functions.xSelectDropdownByName(GenRisk, Value);
                        isElementFound = true;
                    }
                }
            }
            if (isElementFound)
                break;

        }
        return isElementFound;
        // }
        // System.out.println("WebElement not found for : " + tagID);
    }

    public void inputValueWithReport(String tagID, String Value, String label) {
        if (!inputData(tagID, Value)) {
            report.updateTestLog("Input Value", "Error Entering value for field: <i>" + label + "</i> Value : " + Value,
                    Status.WARNING);
            System.out.println(
                    "WARNING : Problem Encountered while Entering value for field: " + label + " for Value : " + Value);
        } else {
            report.updateTestLog("Input Value", "Entering value for field: <i>" + label + "</i> Value : " + Value,
                    Status.PASS);
        }
    }

    public String removeUsrNdPwd(String url) {
        String returnURL = "";
        if (url.contains("@")) {
            String[] spURL = url.split("@");
            returnURL = spURL[0].split("//")[0] + "//" + spURL[1];

        } else {
            returnURL = url;
        }
        return returnURL;
    }

    public int xValidateFieldsPresentonPage(List<WebElement> WebElements, HashMap<String, String> hashMap,
                                            String TextToBeDisplayed) {
        int count = 0;
        String labelArray[] = new String[WebElements.size()];
        for (int i = 0; i < WebElements.size(); i++) {
            for (WebElement element : WebElements) {
                labelArray[i] = element.getText();
                for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                    if (labelArray[i].equals(entry.getKey())) {
                        hashMap.put(entry.getKey(), entry.getValue());
                        break;
                    }
                }
            }
        }
        return count;
    }

    private XSSFWorkbook openFileForReading(String filePath, String fileName) {

        String encryptedAbsoluteFilePath = WhitelistingPath
                .cleanStringForFilePath(filePath + Util.getFileSeparator() + fileName + ".xlsx");
        String absoluteFilePath = encryptedAbsoluteFilePath;

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(absoluteFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            throw new FrameworkException("The specified file \"" + absoluteFilePath + "\" does not exist!");
        }

        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook(fileInputStream);
            // fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException(
                    "Error while opening the specified Excel workbook \"" + absoluteFilePath + "\"");
        }

        return workbook;
    }


    /**
     * Internal Method to update the report
     *
     * @param report
     * @param tblDtls   pass details string
     * @param output    pass out put
     * @param tableName pass table Name
     */
    public static void customReporting(Report report, String tblDtls, String output, String tableName) {

        if (output.isEmpty()) {
            report.updateTestLog(tblDtls, "Order did not reach in" + tableName, Status.FAIL);
        } else if (output.contains("NotMatched") || output.contains("NotFound") || output.contains("ERROR")) {
            report.updateTestLog(tblDtls, output, Status.FAIL);
        } else {
            report.updateTestLog(tblDtls, output, Status.PASS);
        }

    }
//#######################----------------##################################################//

    /**
     * Assert two String value are equal
     *
     * @param report    to log value in report
     * @param expValue  pass expected value
     * @param actualVal Pass expected value
     * @param Replace   Character to be replaced
     * @param CstmMsg   pass Custom Message
     */
    public static void xAssertEquals(Report report, String expValue, String actualVal, String Replace, String CstmMsg) {
        if (actualVal.contains(Replace)) {
            actualVal = actualVal.replace(Replace, "").trim();
        }
        Assert.assertEquals(actualVal, expValue, CstmMsg);

        report.updateTestLog("VerifyVal",
                CstmMsg + " Expected text '" + expValue + "' is matching With Actual Text '" + actualVal + "'",
                Status.PASS);

    }

    /**
     * Validate Field present on the page
     */
    public static List<String> ValidateFieldsPresentonPage(Report report, List<String> List1, List<WebElement> WebElements,
                                                           String TextToBeDisplayed) {
        List<String> WebElementsList = new ArrayList<String>();
        List<String> WebElementsList1 = new ArrayList<String>();
        for (WebElement element : WebElements) {
            WebElementsList.add(element.getText());
        }
        for (String str : List1) {
            if (WebElementsList.contains(str)) {
                System.out.println("'" + str + "' Present on the page");
                WebElementsList1.add(str);
            } else {
                report.updateTestLog(TextToBeDisplayed,
                        "Text: '" + str + "' Not present on the page",
                        Status.FAIL);
            }
        }
        report.updateTestLog(TextToBeDisplayed,
                "'" + WebElementsList1 + "'" + " present on the page",
                Status.PASS);
        return List1;
    }

    /**
     * Generate Random Number
     */
    public static int genRandNum(int number) {
        Random random = new Random();
        int x = random.nextInt(number);
        return x;
    }

    /**
     * This Method to Right Click on the Element
     */
    public static void rightClick(FrameworkDriver driver, By element) {
        Actions builder = new Actions(driver.getWebDriver());
        WebElement elementLocator = driver.findElement(element);
        builder.contextClick(elementLocator).perform();
    }

    /**
     * This Method to get the list of string
     */
    public static List<String> xGetTextVisibleListString(FrameworkDriver driver, List<WebElement> eleList) {
        List<String> webElementsList = new ArrayList<String>();
        for (WebElement ele : eleList) {
            if (ele.isDisplayed()) {
                if (ele.getText().isEmpty()) {
                    webElementsList.add(ele.getAttribute("value"));
                } else {
                    webElementsList.add(ele.getText());
                }
            }
        }
        return webElementsList;
    }

    public static void openNewTab(FrameworkDriver driver) {
        ((JavascriptExecutor) driver.getWebDriver()).executeScript("window.open('about:blank','_blank');");

        Set<String> handles = driver.getWindowHandles();
        String currentWindowHandle = driver.getWindowHandle();
        Utility_Functions.xUpdateJson("ParentWindow", currentWindowHandle);
        for (String handle : handles) {
            if (!currentWindowHandle.equals(handle)) {
                driver.switchTo().window(handle);
            }
        }
    }

    public static String getRandomName() {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String[] splitName = name.split(" ");
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String[] streetAddress = faker.address().streetAddress().split(" ");
        String getRandomName = "TestAuto:" + firstName + "_" + lastName + "-" + streetAddress[0];
        return getRandomName;
    }

    public static void waitTillClickHardSleep(Report report, FrameworkDriver driver, By el, String custMsg) {
        int count = 0;
        Boolean flag = true;
        while (flag) {
            try {
                driver.findElement(el).click();
                report.updateTestLog("Click", custMsg, Status.PASS);
                flag = false;
            } catch (Exception e) {
                count++;
                try {
                    if (count == 30) {
                        flag = false;
                        throw new Exception("Element Not Found");
                    }
                    Thread.sleep(1000);
                } catch (Exception ex) {
                }
            }
        }
    }

    public static void waitTillClickHardSleep(Report report, FrameworkDriver driver, WebElement el, String custMsg) {
        int count = 0;
        Boolean flag = true;
        while (flag) {
            try {
                el.click();
                report.updateTestLog("Click", custMsg, Status.PASS);
                flag = false;
            } catch (Exception e) {
                count++;
                try {
                    if (count == 30) {
                        flag = false;
                        throw new Exception("Element Not Found");
                    }
                    Thread.sleep(1000);
                } catch (Exception ex) {
                }
            }
        }
    }

    public static String yearAdjust(int year) {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, year);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        return formatter.format(dt);
    }

    public void exitToMaster(int numberOfExitsNeeded) {
        for (int i = 0; i < numberOfExitsNeeded; i++) {
            click(WiseSmokeTestPage.F3exit);

        }
    }

    public void navigations(int... navigators) {
        for (int eachNavigator : navigators) {
            sendKeysAndEnter(MasterPage.sqlTxtBox, Integer.toString(eachNavigator), "Entered" + eachNavigator);
        }
    }

    public int convertDecimalToInteger(String s) {
        int decimalIndex = s.indexOf('.');
        int roundedValue = Integer.parseInt(s.substring(0, decimalIndex).replaceAll("[^0-9]", ""));
        return roundedValue;
    }

    public String updatedDecimalToIntegerValue(String s) {
        int updatedValue = convertDecimalToInteger(s) + 5;
        return Integer.toString(updatedValue);
    }

    public String retrieveDecimalToIntegerValue(String s) {
        int updatedValue = convertDecimalToInteger(s);
        return Integer.toString(updatedValue);
    }

    public String appendLocalDate() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String formatted = now.format(formatter);
        return formatted;
    }

    public String appendFutureLocalDate(int noOfDaysInFuture) {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(noOfDaysInFuture);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String formatted = tomorrow.format(formatter);
        return formatted;
    }

    public String appendLocalTime() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "" + now.format(formatter);
    }

    public void multipleClicksOnTheSameElement(By el, int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            ownDriver.findElement(el).click();
            Thread.sleep(1000);
        }
    }

    public void winIntoCompany() {
        sendKeys(MasterPage.sqlTxtBox, "win " + jsonData.getData("winTestCompany"));
        Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    }

    public String lengthyTextConvertedIntoAString(By element, FrameworkDriver driver) {
        List<WebElement> l = driver.findElements((By) element);
        List<String> plist = new ArrayList<>();
        for (WebElement e : l) {
            plist.add(e.getText().replaceAll("[^0-9a-zA-Z\\s]", ""));
        }
        return plist.stream().collect(Collectors.joining(" ")).toString();
    }

    public String removeTheTrailingZeors(String st) {
        int temp = 0;
        for (int i = st.length() - 1; i >= 0; i--) {
            if (st.charAt(i) != '0') {
                temp = i;
                break;
            }
        }
        if (st.charAt(0) == '.')
            return "0" + st.substring(0, temp + 1);
        return st.substring(0, temp + 1);
    }

    public String generateARandomNumber(int numberToBeGenerated) {
        int desiredNumber = 0;
        Random r = new Random();
        switch (numberToBeGenerated) {
            case 10:
                desiredNumber = r.nextInt(90) + 10;
                break;

            case 100:
                desiredNumber = r.nextInt(900) + 100;
                break;

            case 1000:
                desiredNumber = r.nextInt(9000) + 1000;
                break;

            default:
                System.out.println("The desired value doesn't exist");
                break;

        }
        return Integer.toString(desiredNumber);
    }

    public static String getDesiredTime(String standardTime, String format)
    {
        LocalDateTime currentTime = LocalDateTime.now(ZoneId.of(standardTime));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String estTime = currentTime.format(formatter);
        return  estTime.toUpperCase();
    }
}
