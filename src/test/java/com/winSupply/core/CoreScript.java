package com.winSupply.core;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.winSupply.framework.*;
import com.winSupply.framework.selenium.*;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.winSupply.framework.ReportThemeFactory.Theme;
import com.winSupply.framework.selenium.FrameworkDriver;

import io.appium.java_client.AppiumDriver;
import supportLibraries.Utility_Functions;

import com.winSupply.core.*; // Added on 04 July 2021

/**
 * FrameworkDriver script class which encapsulates the coreScript logic of the framework
 *
 * @author winSupply
 */
public class CoreScript {
    /**
     * CoreScript constructor
     *
     * @param testParameters A {@link SeleniumTestParameters} object
     */
    public CoreScript(SeleniumTestParameters testParameters) {
        this.testParameters = testParameters;
        this.extentReport = testParameters.getExtentReport();
        this.extentTest = testParameters.getExtentTest();
    }

    private List<String> businessFlowData;
    private int currentIteration, currentSubIteration;
    private Date startTime, endTime;

    private String executionTime;

    private JsonDataExcess jsonData;
    private ReportSettings reportSettings;
    private SeleniumReport report;


    private FrameworkDriver driver;
    private WebDriverUtil driverUtil;

    private Helper helper;
    private Properties properties;
    private Properties mobileProperties;

    private final FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();

    private Boolean linkScreenshotsToTestLog = true;
    private final SeleniumTestParameters testParameters;

    private String reportPath;
    private APIReusuableLibrary apiDriver = new APIReusuableLibrary();
    public ExtentReports extentReport;
    public ExtentTest extentTest;

    public Map<String, String> reusableHandle = new HashMap<>();
    private ObjectMachinate objectHandling;

    private WebDriver pureWebDriver;


    /**
     * Function to execute the given test case
     */
    public void driveTestExecution() {
        try {
            startUp();
            initDriver();
            initTestIterations();
            initTestReport();
            initTestData();
            executeBusinessCase();
        } catch (FrameworkException fx) {
            exceptionHandler(fx, fx.getErrorName());
        } catch (Exception ex) {
            exceptionHandler(ex, "Error");
        } finally {
            quitWebDriver();
            wrapUp();
        }

    }

    private void exceptionHandler(Exception ex, String exceptionName) {
        // Error reporting
        String exceptionDescription;
        String errorMessage = ex.getMessage();

        exceptionDescription = Utility_Functions.xExptnsMsg(ex.getMessage());


        if (exceptionDescription == null) {
            exceptionDescription = ex.toString();

        }

        if (ex.getCause() != null) {

            report.updateTestLog(exceptionName, exceptionDescription + " <b>Caused by: </b>"
                    + Utility_Functions.xExptnsMsg(ex.getCause().getMessage()), Status.FAIL);// Updated on 17th May 2020
        } else {
            report.updateTestLog(exceptionName, exceptionDescription, Status.FAIL);
        }

        // Print stack trace for detailed debug information
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        String stackTrace = stringWriter.toString();
        report.updateTestLog("Exception stack trace", stackTrace, Status.DEBUG);

        // Error response
        if (frameworkParameters.getStopExecution()) {
            report.updateTestLog("GS Info", "Test execution terminated by user! All subsequent tests aborted...",
                    Status.DONE);
            currentIteration = testParameters.getEndIteration();
        } else {
            OnError onError = OnError.valueOf(properties.getProperty("OnError"));
            switch (onError) {
                // Stop option is not relevant when run from QC
                case NEXT_ITERATION:
                    report.updateTestLog("GS Info",
                            "Test case iteration terminated by user! Proceeding to next iteration (if applicable)...",
                            Status.DONE);
                    break;

                case NEXT_TESTCASE:
                    report.updateTestLog("GS Info",
                            "Test case terminated by user! Proceeding to next test case (if applicable)...", Status.DONE);
                    currentIteration = testParameters.getEndIteration();
                    break;

                case STOP:
                    frameworkParameters.setStopExecution(true);
                    report.updateTestLog("GS Info", "Test execution terminated by user! All subsequent tests aborted...",
                            Status.DONE);
                    currentIteration = testParameters.getEndIteration();
                    break;

                default:
                    throw new FrameworkException("Unhandled OnError option!");
            }
        }
    }

    private void executeTestCase() {
        initializeTestScript();
        executeTestIterations();
    }


    private void executeBusinessCase() {

        if (properties.getProperty("Approach").equalsIgnoreCase("KeywordDriven")) {
            executeTestCase();
        }
    }

    private void executeTestIterations() {
        while (currentIteration <= testParameters.getEndIteration()) {
            report.addTestLogSection("Iteration: " + Integer.toString(currentIteration));
            report.setIteration(currentIteration);
            // Evaluate each test iteration for any errors
            try {
                executeTestcase(businessFlowData);
            } catch (FrameworkException fx) {
                exceptionHandler(fx, fx.getErrorName());
            } catch (InvocationTargetException ix) {
                exceptionHandler(ix, "Error");
            } catch (Exception ex) {
                exceptionHandler(ex, "Error");
            }

            currentIteration++;
        }
    }

    private void executeTestcase(List<String> businessFlowData)
            throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        Map<String, Integer> keywordDirectory = new HashMap<String, Integer>();

        for (int currentKeywordNum = 0; currentKeywordNum < businessFlowData.size(); currentKeywordNum++) {
            String[] currentFlowData = businessFlowData.get(currentKeywordNum).split(",");
            String currentKeyword = currentFlowData[0];

            int nKeywordIterations;
            if (currentFlowData.length > 1) {
                nKeywordIterations = Integer.parseInt(currentFlowData[1]);
            } else {
                nKeywordIterations = 1;
            }

            for (int currentKeywordIteration = 0; currentKeywordIteration < nKeywordIterations; currentKeywordIteration++) {
                if (keywordDirectory.containsKey(currentKeyword)) {
                    keywordDirectory.put(currentKeyword, keywordDirectory.get(currentKeyword) + 1);
                } else {
                    keywordDirectory.put(currentKeyword, 1);
                }
                currentSubIteration = keywordDirectory.get(currentKeyword);



                jsonData.setCurrentRow(testParameters.getCurrentTestcase(), currentIteration);

                if (currentSubIteration > 1) {
                    report.addTestLogSubSection(currentKeyword + " (Sub-Iteration: " + currentSubIteration + ")");
                    report.setSubIteration(currentSubIteration);
                } else {
                    report.addTestLogSubSection(currentKeyword);
                }

                invokeBusinessComponent(currentKeyword);
            }
        }
    }

    /**
     * Function to get the execution time for the test case
     *
     * @return The test execution time
     */
    public String getExecutionTime() {
        return executionTime;
    }

    /**
     * Function to get the decription of any failure that may occur during the
     * script execution
     *
     * @return The failure description (relevant only if the test fails)
     */
    public String getFailureDescription() {
        return report.getFailureDescription();
    }

    private int getNumberOfIterations() {
        String encryptedDatatablePath = WhitelistingPath.cleanStringForFilePath(
                "src" + Util.getFileSeparator()
                        + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator() + "Datatables");
        String datatablePath = encryptedDatatablePath+ Util.getFileSeparator() +
                testParameters.getCurrentScenario() + ".json";

        JsonDataExcess jsonDataExcess= new JsonDataExcess(datatablePath,properties.getProperty("DefaultDataTag"));
        jsonDataExcess.setCurrentRow(testParameters.getCurrentTestcase(),0);




        if (properties.getProperty("Approach").equalsIgnoreCase("KeywordDriven")) {
            return jsonDataExcess.getIteration();
        }

        return 1;
    }

    /**
     * Function to get the name of the test report
     *
     * @return The test report name
     */
    public String getReportName() {
        return reportSettings.getReportName();
    }

    /**
     * Function to get the testcasebean object for the test case
     *
     * @return The testcasebean object
     */
    public TestCaseBean getTestCaseBean() {
        report.setFinalValues();
        return report.getTestCaseBean();
    }


    /**
     * Function to get the status of the test case executed
     *
     * @return The test status
     */
    public String getTestStatus() {
        return report.getTestStatus();
    }



    private void initializeBusinessFlow() {
        String encryptedBusinessFlowAccess = WhitelistingPath.cleanStringForFilePath(
                frameworkParameters.getRelativePath() + Util.getFileSeparator() + "src" + Util.getFileSeparator()
                        + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator() + "Datatables");


//        File businessFile = new File(encryptedBusinessFlowAccess + Util.getFileSeparator() +
//                testParameters.getCurrentScenario() + ".json");

        //*** Changed on 04 July 2021 ****//
        String datatablePath = frameworkParameters.getRelativePath() + Util.getFileSeparator() + "src"
                + Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator()
                + "Datatables";
        TestConfigurations t = new TestConfigurations();
        String subPath = t.currentMethodName.toString().replace("package testcases.", "");
        String encryptedDatatablePath;
        if (subPath != null && !subPath.contains("package testcases")) {
            encryptedDatatablePath = WhitelistingPath.cleanStringForFilePath(
                    datatablePath + "/" + subPath + "/" + testParameters.getCurrentScenario() + ".json");
        }
        else {
            encryptedDatatablePath = WhitelistingPath.cleanStringForFilePath(
                    datatablePath + "/" + testParameters.getCurrentScenario() + ".json");
        }

        File businessFile = new File(encryptedDatatablePath);

        //*** end ****//



        Object businessKeywords = null;
        ReadContext wctx;
        try {
            wctx = JsonPath.parse(businessFile);
            businessKeywords = wctx.read("$." + "Business_Flow[*]." + testParameters.getCurrentTestcase());


        } catch (IOException e) {
            throw new FrameworkException("The test case \"" + testParameters.getCurrentTestcase()
                    + "\" is not found in the Business Flow sheet!");
        }



        List<String> busnssObj = (List<String>) businessKeywords;
        String businessKeyword = "";
        if (busnssObj.size() ==0) {

            throw new FrameworkException("The test case \"" + testParameters.getCurrentTestcase()
                    + "\" is not found in the Business Flow sheet!");
        }else{
            businessKeyword = busnssObj.get(0);
        }
        String[] businessKeywordsArr = businessKeyword.split(",");
        String dataValue;
        businessFlowData = new ArrayList<String>();
        for (String bkey : businessKeywordsArr
        ) {
            businessFlowData.add(bkey.trim());
        }
        if (businessFlowData.isEmpty()) {
            throw new FrameworkException(
                    "No business flow found against the test case \"" + testParameters.getCurrentTestcase() + "\"");
        }
    }


    private synchronized void initTestData() {
        String datatablePath = frameworkParameters.getRelativePath() + Util.getFileSeparator() + "src"
                + Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator()
                + "Datatables";

        //*** Changed on 04 July 2021 ****//
        TestConfigurations t = new TestConfigurations();
        String subPath = t.currentMethodName.toString().replace("package testcases", "");
        String encryptedDatatablePath;
        if (subPath != null) {
            encryptedDatatablePath = WhitelistingPath.cleanStringForFilePath(
                    datatablePath + "/" + subPath + "/" + testParameters.getCurrentScenario() + ".json");
        }
        else {
            encryptedDatatablePath = WhitelistingPath.cleanStringForFilePath(
                    datatablePath + "/" + testParameters.getCurrentScenario() + ".json");
        }

        //*** end ****//

        String runTimeDatatablePath;
        Boolean includeTestDataInReport = Boolean.parseBoolean(properties.getProperty("IncludeTestDataInReport"));
        if (includeTestDataInReport) {
            runTimeDatatablePath = reportPath + Util.getFileSeparator() + "Datatables";
            String encryptedRunTimeDatatablePath = WhitelistingPath.cleanStringForFilePath(
                    runTimeDatatablePath + Util.getFileSeparator() + testParameters.getCurrentScenario() + ".json");

            File runTimeDatatable = new File(encryptedRunTimeDatatablePath);
            if (!runTimeDatatable.exists()) {
                File datatable = new File(encryptedDatatablePath);

                try {
                    FileUtils.copyFile(datatable, runTimeDatatable);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new FrameworkException(
                            "Error in creating run-time datatable: Copying the datatable failed...");
                }
            }

        } else {
            runTimeDatatablePath = datatablePath;
        }


        jsonData= new JsonDataExcess(encryptedDatatablePath,properties.getProperty("DefaultDataTag"));



    }

    private void initializeReportSettings() {
        if (System.getProperty("ReportPath") != null) {
            reportPath = System.getProperty("ReportPath");
        } else {
            reportPath = TimeStamp.getInstance();
        }

        reportSettings = new ReportSettings(reportPath, testParameters.getCurrentScenario() + "_"
                + testParameters.getCurrentTestcase() + "_" + testParameters.getCurrentTestInstance());

        reportSettings.setDateFormatString(properties.getProperty("DateFormatString"));
        reportSettings.setLogLevel(Integer.parseInt(properties.getProperty("LogLevel")));
        reportSettings.setProjectName(properties.getProperty("ProjectName"));
        reportSettings.setGenerateExcelReports(Boolean.parseBoolean(properties.getProperty("ExcelReport")));
        reportSettings.setGenerateHtmlReports(Boolean.parseBoolean(properties.getProperty("HtmlReport")));
        reportSettings.setGenerateSeeTestReports(
                Boolean.parseBoolean(mobileProperties.getProperty("SeeTestReportGeneration")));
        reportSettings.setGeneratePerfectoReports(
                Boolean.parseBoolean(mobileProperties.getProperty("PerfectoReportGeneration")));
        reportSettings
                .setTakeScreenshotFailedStep(Boolean.parseBoolean(properties.getProperty("TakeScreenshotFailedStep")));
        reportSettings
                .setTakeScreenshotPassedStep(Boolean.parseBoolean(properties.getProperty("TakeScreenshotPassedStep")));
        if (isAPITest()) {
            reportSettings.setTakeScreenshotFailedStep(false);
            reportSettings.setTakeScreenshotPassedStep(false);
        }
        // Added Code for DB Query Test cases on 13th June -2019
        if (isDBTest()) {
            reportSettings.setTakeScreenshotFailedStep(false);
            reportSettings.setTakeScreenshotPassedStep(false);
        }
        reportSettings.setConsolidateScreenshotsInWordDoc(
                Boolean.parseBoolean(properties.getProperty("ConsolidateScreenshotsInWordDoc")));
        reportSettings.setisMobileExecution(isMobileAutomation());
        reportSettings.setAPIAutomation(isAPITest());

        reportSettings.setLinkScreenshotsToTestLog(this.linkScreenshotsToTestLog);
    }

    private void initializeTestCase() {
        driverUtil = new WebDriverUtil(driver);
        helper = new Helper(jsonData, report, driver, driverUtil, apiDriver, extentTest, reusableHandle,
                testParameters.getEndIteration());
        driver.setRport(report);

    }

    private void initTestIterations() {
        switch (testParameters.getIterationMode()) {
            case RUN_ALL_ITERATIONS:
                int nIterations = getNumberOfIterations();
                testParameters.setEndIteration(nIterations);

                currentIteration = 1;
                break;

            case RUN_ONE_ITERATION_ONLY:
                currentIteration = 1;
                break;

            case RUN_RANGE_OF_ITERATIONS:
                if (testParameters.getStartIteration() > testParameters.getEndIteration()) {
                    throw new FrameworkException("Error", "StartIteration cannot be greater than EndIteration!");
                }
                currentIteration = testParameters.getStartIteration();
                break;

            default:
                throw new FrameworkException("Unhandled Iteration Mode!");
        }
    }

    private void initTestReport() {
        initializeReportSettings();
        ReportTheme reportTheme = ReportThemeFactory
                .getReportsTheme(Theme.valueOf(properties.getProperty("ReportsTheme")));

        report = new SeleniumReport(reportSettings, reportTheme, testParameters);

        report.initialize();
        setReportParameters();
        report.initializeTestLog();

    }

    private void initializeTestScript() {
        driverUtil = new WebDriverUtil(driver);
        apiDriver.setRport(report);
        helper = new Helper(jsonData, report, driver, driverUtil, apiDriver, extentTest, reusableHandle,
                testParameters.getEndIteration());
        if (!isAPITest() && !isDBTest()) {
            driver.setRport(report);
            objectHandling = new ObjectMachinate(report, pureWebDriver, frameworkParameters, testParameters);
            driver.setObjectHandling(objectHandling);
        }

        initializeBusinessFlow();
    }

    @SuppressWarnings("rawtypes")
    private void initDriver() {
        String remoteUrl = System.getProperty("RemoteUrl");
        remoteUrl = remoteUrl == null ? properties.getProperty("RemoteUrl") : remoteUrl;

        switch (testParameters.getExecutionMode()) {
            case API:
                break;
            case DB:
                break;

            case LOCAL:
                WebDriver webDriver = WebDriverFactory.getWebDriver(testParameters.getBrowser());
                driver = new FrameworkDriver(webDriver);
                pureWebDriver = webDriver;
                driver.setTestParameters(testParameters);
                maximizeWindow();
                break;
            case REMOTE:
                WebDriver remoteWebDriver = WebDriverFactory.getRemoteWebDriver(testParameters.getBrowser(),

                        remoteUrl);
                driver = new FrameworkDriver(remoteWebDriver);
                pureWebDriver = remoteWebDriver;
                driver.setTestParameters(testParameters);
                maximizeWindow();
                break;

            case GRID:
                WebDriver remoteGridDriver = WebDriverFactory.getRemoteWebDriver(testParameters.getBrowser(),
                        // testParameters.getBrowserVersion(), testParameters.getPlatform(),
                        remoteUrl);
                driver = new FrameworkDriver(remoteGridDriver);
                pureWebDriver = remoteGridDriver;
                driver.setTestParameters(testParameters);
                maximizeWindow();
                break;

            case MOBILE:
                WebDriver appiumDriver = AppiumDriverFactory.getAppiumDriver(testParameters.getMobileExecutionPlatform(),
                        testParameters.getDeviceName(), testParameters.getMobileOSVersion(),
                        testParameters.shouldInstallApplication(), mobileProperties.getProperty("AppiumURL"));
                driver = new FrameworkDriver(appiumDriver);
                pureWebDriver = appiumDriver;
                driver.setTestParameters(testParameters);

                break;

            case PERFECTO:
                if (testParameters.getMobileToolName().equals(ToolName.APPIUM)) {
                    WebDriver appiumPerfectoDriver = PerfectoDriverFactory.getPerfectoAppiumDriver(
                            testParameters.getMobileExecutionPlatform(), testParameters.getDeviceName(),
                            mobileProperties.getProperty("PerfectoHost"));
                    driver = new FrameworkDriver(appiumPerfectoDriver);
                    pureWebDriver = appiumPerfectoDriver;
                    driver.setTestParameters(testParameters);

                } else if (testParameters.getMobileToolName().equals(ToolName.REMOTE_WEBDRIVER)) {
                    WebDriver remotePerfectoDriver = PerfectoDriverFactory
                            .getPerfectoRemoteWebDriverForDesktop(testParameters);
                    driver = new FrameworkDriver(remotePerfectoDriver);
                    pureWebDriver = remotePerfectoDriver;
                    driver.setTestParameters(testParameters);
                }

                break;

            case SAUCELABS:
                if (testParameters.getMobileToolName().equals(ToolName.APPIUM)) {
                    AppiumDriver appiumSauceDriver = SauceLabsDriverFactory.getSauceAppiumDriver(
                            testParameters.getMobileExecutionPlatform(), testParameters.getDeviceName(),
                            mobileProperties.getProperty("SauceHost"), testParameters);
                    driver = new FrameworkDriver(appiumSauceDriver);
                    pureWebDriver = appiumSauceDriver;
                    driver.setTestParameters(testParameters);

                } else if (testParameters.getMobileToolName().equals(ToolName.REMOTE_WEBDRIVER)) {
                    WebDriver remoteSauceDriver = SauceLabsDriverFactory.getSauceRemoteWebDriver(
                            mobileProperties.getProperty("SauceHost"), testParameters.getBrowser(),
                            testParameters.getBrowserVersion(), testParameters.getPlatform(), testParameters);
                    driver = new FrameworkDriver(remoteSauceDriver);
                    pureWebDriver = remoteSauceDriver;
                    driver.setTestParameters(testParameters);
                }

                break;

            case TESTOBJECT:

                WebDriver testObjectAppiumDriver = SauceLabsDriverFactory.getTestObjectAppiumDriver(
                        testParameters.getMobileExecutionPlatform(), testParameters.getDeviceName(),
                        mobileProperties.getProperty("TestObjectHost"), testParameters);
                driver = new FrameworkDriver(testObjectAppiumDriver);
                pureWebDriver = testObjectAppiumDriver;
                driver.setTestParameters(testParameters);

                break;

            case FASTEST:
                if (testParameters.getMobileToolName().equals(ToolName.REMOTE_WEBDRIVER)) {
                    WebDriver fastestRemoteDriver = FastestDriverFactory.getRemoteWebDriver(testParameters.getBrowser(),
                            testParameters.getBrowserVersion(), testParameters.getPlatform(),
                            mobileProperties.getProperty("FastestHost"), testParameters.getCurrentTestcase());
                    driver = new FrameworkDriver(fastestRemoteDriver);
                    pureWebDriver = fastestRemoteDriver;
                    driver.setTestParameters(testParameters);
                } else if (testParameters.getMobileToolName().equals(ToolName.APPIUM)) {
                    WebDriver mintAppiumtDriver = FastestDriverFactory.getMintAppiumDriver(
                            testParameters.getMobileExecutionPlatform(), testParameters.getDeviceName(),
                            mobileProperties.getProperty("MintHost"), testParameters.getMobileOSVersion());
                    driver = new FrameworkDriver(mintAppiumtDriver);
                    pureWebDriver = mintAppiumtDriver;
                    driver.setTestParameters(testParameters);
                }

                break;

            case BROWSERSTACK:
                if (testParameters.getMobileToolName().equals(ToolName.REMOTE_WEBDRIVER)) {
                    WebDriver browserstackRemoteDrivermobile = BrowserStackDriverFactory
                            .getBrowserStackRemoteWebDriverMobile(testParameters.getMobileExecutionPlatform(),
                                    testParameters.getDeviceName(), mobileProperties.getProperty("BrowserStackHost"),
                                    testParameters);
                    driver = new FrameworkDriver(browserstackRemoteDrivermobile);
                    pureWebDriver = browserstackRemoteDrivermobile;
                    driver.setTestParameters(testParameters);

                } else if (testParameters.getMobileToolName().equals(ToolName.DEFAULT)) {
                    WebDriver browserstackRemoteDriver = BrowserStackDriverFactory.getBrowserStackRemoteWebDriver(
                            mobileProperties.getProperty("BrowserStackHost"), testParameters.getBrowser(),
                            testParameters.getBrowserVersion(), testParameters.getPlatform(), testParameters);

                    driver = new FrameworkDriver(browserstackRemoteDriver);
                    pureWebDriver = browserstackRemoteDriver;
                    driver.setTestParameters(testParameters);
                }

                break;

            default:
                throw new FrameworkException("Unhandled Execution Mode!");
        }
    }

    private void invokeBusinessComponent(String currentKeyword)
            throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        Boolean isMethodFound = false;
        final String CLASS_FILE_EXTENSION = ".class";
        String encryptedBusinessComponentsPath = WhitelistingPath
                .cleanStringForFilePath(frameworkParameters.getRelativePath() + Util.getFileSeparator() + "target"
                        + Util.getFileSeparator() + "test-classes" + Util.getFileSeparator() + "businesskeywords");
        String encryptedComponentGroupsPath = WhitelistingPath
                .cleanStringForFilePath(frameworkParameters.getRelativePath() + Util.getFileSeparator() + "target"
                        + Util.getFileSeparator() + "test-classes" + Util.getFileSeparator() + "componentgroups");
        File[] packageDirectories = {new File(encryptedBusinessComponentsPath),
                new File(encryptedComponentGroupsPath)};

        for (File packageDirectory : packageDirectories) {
            File[] packageFiles = packageDirectory.listFiles();
            String packageName = packageDirectory.getName();

            for (int i = 0; i < packageFiles.length; i++) {
                File packageFile = packageFiles[i];
                String fileName = packageFile.getName();



                /*
                 * inserted to handle the sub directory under Businesscomponent
                 * modified on: 25-06-2021
                 */

                if (packageFile.isDirectory()) {
                    File packageSubDirectory =  packageFile;
                    String packageSubName = packageSubDirectory.getName();
                    File[] packageSubFiles = packageSubDirectory.listFiles();
                    for (int j = 0; j < packageSubFiles.length; j++) {
                        File packageSubFile = packageSubFiles[j];
                        String fileSubName = packageSubFile.getName();

                        String encryptedReusableComponents = "";
                        if (packageSubFile.isDirectory()) {
                            File packageSub2Directory =  packageSubFile;
                            String packageSub2Name = packageSub2Directory.getName();
                            File[] packageSub2Files = packageSub2Directory.listFiles();
                            for (int k = 0; k < packageSub2Files.length; k++) {
                                File packageSub2File = packageSub2Files[k];
                                String fileSub2Name = packageSub2File.getName();
                                if (fileSub2Name.endsWith(CLASS_FILE_EXTENSION)) {
                                    // Remove the .class extension to get the class name
                                    String className = fileSub2Name.substring(0, fileSub2Name.length() - CLASS_FILE_EXTENSION.length());
                                    encryptedReusableComponents = WhitelistingPath
                                            .cleanStringForFilePath(packageName+ "." + packageSubName + "." + packageSub2Name + "." + className);
                                    Class<?> reusableComponents = Class.forName(encryptedReusableComponents);
                                    Method executeComponent;
                                    try {
                                        // Convert the first letter of the method to lowercase
                                        // (in line with java naming conventions)
                                        currentKeyword = currentKeyword.substring(0, 1).toLowerCase() + currentKeyword.substring(1);
                                        executeComponent = reusableComponents.getMethod(currentKeyword, (Class<?>[]) null);
                                        if (executeComponent == null){
                                            continue;
                                        }
                                    } catch (NoSuchMethodException ex) {
                                        // If the method is not found in this class, search the
                                        // next class
                                        continue;
                                    }
                                    isMethodFound = true;
                                    report.setCurrentClassName(className);
                                    report.setBusinessComponent(currentKeyword);

                                    Constructor<?> ctor = reusableComponents.getDeclaredConstructors()[0];
                                    Object businessComponent = ctor.newInstance(helper);
                                    executeComponent.invoke(businessComponent, (Object[]) null);
                                    break;
                                }
                            }
                            if (isMethodFound){
                                break;
                            }
                        }

                        if (fileSubName.endsWith(CLASS_FILE_EXTENSION)) {
                            // Remove the .class extension to get the class name
                            String className = fileSubName.substring(0, fileSubName.length() - CLASS_FILE_EXTENSION.length());
                            encryptedReusableComponents = WhitelistingPath
                                    .cleanStringForFilePath(packageName + "." + packageSubName + "." + className);
                            Class<?> reusableComponents = Class.forName(encryptedReusableComponents);
                            Method executeComponent;
                            try {
                                // Convert the first letter of the method to lowercase
                                // (in line with java naming conventions)
                                currentKeyword = currentKeyword.substring(0, 1).toLowerCase() + currentKeyword.substring(1);
                                executeComponent = reusableComponents.getMethod(currentKeyword, (Class<?>[]) null);
                            } catch (NoSuchMethodException ex) {
                                // If the method is not found in this class, search the
                                // next class
                                continue;
                            }
                            isMethodFound = true;
                            report.setCurrentClassName(className);
                            report.setBusinessComponent(currentKeyword);

                            Constructor<?> ctor = reusableComponents.getDeclaredConstructors()[0];
                            Object businessComponent = ctor.newInstance(helper);
                            executeComponent.invoke(businessComponent, (Object[]) null);
                            break;
                        }
                    }
                    if (isMethodFound){
                        break;
                    }
                }


                // We only want the .class files
                if (fileName.endsWith(CLASS_FILE_EXTENSION)) {
                    // Remove the .class extension to get the class name
                    String className = fileName.substring(0, fileName.length() - CLASS_FILE_EXTENSION.length());

                    String encryptedReusableComponents = WhitelistingPath
                            .cleanStringForFilePath(packageName + "." + className);
                    Class<?> reusableComponents = Class.forName(encryptedReusableComponents);
                    Method executeComponent;

                    try {
                        // Convert the first letter of the method to lowercase
                        // (in line with java naming conventions)
                        currentKeyword = currentKeyword.substring(0, 1).toLowerCase() + currentKeyword.substring(1);
                        executeComponent = reusableComponents.getMethod(currentKeyword, (Class<?>[]) null);
                    } catch (NoSuchMethodException ex) {
                        // If the method is not found in this class, search the
                        // next class
                        continue;
                    }

                    isMethodFound = true;

                    report.setCurrentClassName(className);
                    report.setBusinessComponent(currentKeyword);

                    Constructor<?> ctor = reusableComponents.getDeclaredConstructors()[0];
                    Object businessComponent = ctor.newInstance(helper);

                    executeComponent.invoke(businessComponent, (Object[]) null);

                    break;
                }
            }
        }

        if (!isMethodFound) {
            throw new FrameworkException("Keyword " + currentKeyword + " not found within any class "
                    + "inside the businesskeywords package");
        }
    }

    private boolean isAPITest() {
        boolean isAPI = false;
        if (testParameters.getExecutionMode().equals(ExecutionMode.API)) {
            isAPI = true;
        }
        return isAPI;
    }

    private boolean isDBTest() {
        boolean isDB = false;
        if (testParameters.getExecutionMode().equals(ExecutionMode.DB)) {
            isDB = true;
        }
        return isDB;
    }

    private boolean isMobileAutomation() {
        boolean isMobileAutomation = false;
        if (testParameters.getMobileToolName().equals(ToolName.APPIUM)) {
            isMobileAutomation = true;
        }
        return isMobileAutomation;
    }

    private void maximizeWindow() {
        driver.manage().window().maximize();
    }

    private void quitWebDriver() {
        switch (testParameters.getExecutionMode()) {
            case API:
                break;
            case LOCAL:
            case REMOTE:
            case GRID:
            case MOBILE:
            case SAUCELABS:
            case TESTOBJECT:
            case PERFECTO:
            case BROWSERSTACK:
            case FASTEST:

                driver.quit();
                break;
            default:

                driver.quit();
                throw new FrameworkException("Unhandled Execution Mode!");
        }

    }

    private void setDefaultTestParameters() {
        if (testParameters.getIterationMode() == null) {
            testParameters.setIterationMode(IterationOptions.RUN_ONE_ITERATION_ONLY);
        }

        if (testParameters.getExecutionMode() == null) {
            testParameters.setExecutionMode(ExecutionMode.valueOf(properties.getProperty("DefaultExecutionMode")));
        }

        if (testParameters.getMobileExecutionPlatform() == null) {
            testParameters.setMobileExecutionPlatform(
                    MobileExecutionPlatform.valueOf(mobileProperties.getProperty("DefaultMobileExecutionPlatform")));
        }

        if (testParameters.getMobileToolName() == null) {
            testParameters.setMobileToolName(ToolName.valueOf(mobileProperties.getProperty("DefaultMobileToolName")));
        }

        if (testParameters.getDeviceName() == null) {
            testParameters.setDeviceName(mobileProperties.getProperty("DefaultDevice"));
        }

        if (testParameters.getBrowser() == null) {
            testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
        }

        if (testParameters.getPlatform() == null) {
            testParameters.setPlatform(Platform.valueOf(properties.getProperty("DefaultPlatform")));
        }

        if (testParameters.getSeeTestPort() == null) {
            testParameters.setSeeTestPort(mobileProperties.getProperty("SeeTestDefaultPort"));
        }

        testParameters.setInstallApplication(
                Boolean.parseBoolean(mobileProperties.getProperty("InstallApplicationInDevice")));

    }


    private void setExtentTestcase() {
        extentTest = extentReport.createTest(
                testParameters.getCurrentTestcase() + "-" + testParameters.getCurrentTestInstance(),
                testParameters.getCurrentTestDescription());

    }

    /**
     * Function to configure the linking of screenshots to the corresponding test
     * log
     *
     * @param linkScreenshotsToTestLog Boolean variable indicating whether
     *                                 screenshots should be linked to the
     *                                 corresponding test log
     */
    public void setLinkScreenshotsToTestLog(Boolean linkScreenshotsToTestLog) {
        this.linkScreenshotsToTestLog = linkScreenshotsToTestLog;
    }

    private void setReportParameters() {
        report.setExtentTest(extentTest);
        report.setDriver(driver);
    }

    private void startUp() {
        startTime = Util.getCurrentTime();

        properties = Settings.getInstance();
        mobileProperties = Settings.getMobilePropertiesInstance();

        setDefaultTestParameters();
        setExtentTestcase();
    }

    private void wrapUp() {
        endTime = Util.getCurrentTime();

        List<Map<String, Object>> listDat= new ArrayList<>();
        for (TestStepBean tsb :report.getTestStepBeanList()) {
            Map<String,Object> testStep= new HashMap<>();
            testStep.put("StepName", tsb.getTestStepName());
            testStep.put("StepNo", tsb.getTestStepNumber());
            testStep.put("StepDescription", tsb.getTestStepDescription());
            testStep.put("StepStatus", tsb.getTestStepStatus());
            testStep.put("ExecutionTime", tsb.getTestStepExectuionTime());
            testStep.put("Iteration", tsb.getIteration());
            testStep.put("SubIteration", tsb.getSubIteration());
            testStep.put("BusinessComponent", tsb.getBusinessComponent());
            // Failure Details
            testStep.put("FailureReason", tsb.getFailureReason());
            testStep.put("MethodName", tsb.getMethodName());
            testStep.put("ClassName", tsb.getClassName());
            testStep.put("StackTrace", tsb.getStackTrace());
            testStep.put("BriefErroMessage", tsb.getBriefErrorMesg());
            listDat.add(testStep);
        }
        System.out.print(  listDat.toString());
    }
}