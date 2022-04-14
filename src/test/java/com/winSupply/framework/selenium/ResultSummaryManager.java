package com.winSupply.framework.selenium;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import com.winSupply.framework.*;
import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import org.apache.commons.io.FileUtils;

import com.winSupply.framework.ReportThemeFactory.Theme;
import org.json.JSONException;
import org.json.simple.JSONObject;

/**
 * Singleton class that manages the result summary creation during a batch
 * execution
 *
 * @author winSupply
 */
public class ResultSummaryManager {
    private static final ResultSummaryManager RESULT_SUMMARY_MANAGER = new ResultSummaryManager();

    static String summaryPath;

    /**
     * Function to return the singleton instance of the {@link ResultSummaryManager}
     * object
     *
     * @return Instance of the {@link ResultSummaryManager} object
     */
    public static ResultSummaryManager getInstance() {
        return RESULT_SUMMARY_MANAGER;
    }

    private SeleniumReport summaryReport;

    private ReportSettings reportSettings;
    private String reportPath;

    private Date overallStartTime, overallEndTime;
    private Properties properties;

    private FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();

    private SeleniumTestParameters testParameters;

    private ResultSummaryManager() {
        // To prevent external instantiation of this class
    }

    @SuppressWarnings("resource")
    private boolean checkExceptionInErrorLogTxt() throws IOException {
        boolean isException = false;
        String encryptedPath = WhitelistingPath
                .cleanStringForFilePath(reportPath + Util.getFileSeparator() + "ErrorLog.txt");
        File file = new File(encryptedPath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Exception")) {
                    isException = true;
                    break;
                } else {
                    isException = false;
                }
            }
        } catch (FileNotFoundException e) {
        }
        return isException;

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    private void createResultSummaryHeader(int nThreads) {
        summaryReport
                .addResultSummaryHeading(reportSettings.getProjectName() + " - Automation Execution Results Summary");
        summaryReport.addResultSummarySubHeading("Date & Time",
                ": " + Util.getFormattedTime(overallStartTime, properties.getProperty("DateFormatString")), "OnError",
                ": " + properties.getProperty("OnError"));
        summaryReport.addResultSummarySubHeading("Run Configuration", ": " + frameworkParameters.getRunConfiguration(),
                "No. of threads", ": " + nThreads);

        summaryReport.addResultSummaryTableHeadings();
    }

    public String getReportPath() {
        return this.reportPath;
    }

    public String getResultPath() {
        String encryptedHtml = summaryPath;
        return encryptedHtml;
    }

    private void initializeReportSettings() {
       /* if (properties.getProperty("ReportPath") != null) {
            reportPath = properties.getProperty("ReportPath");
        } else {
            reportPath = TimeStamp.getInstance();
        }*/
        reportPath = TimeStamp.getInstance();

        reportSettings = new ReportSettings(reportPath, "");

        reportSettings.setDateFormatString(properties.getProperty("DateFormatString"));
        reportSettings.setProjectName(properties.getProperty("ProjectName"));
        reportSettings.setGenerateExcelReports(Boolean.parseBoolean(properties.getProperty("ExcelReport")));
        reportSettings.setGenerateHtmlReports(Boolean.parseBoolean(properties.getProperty("HtmlReport")));
        reportSettings.setLinkTestLogsToSummary(true);
    }

    /**
     * Function to initialize the summary report
     *
     * @param nThreads The number of parallel threads configured for the test batch
     *                 execution
     */
    public void initializeSummaryReport(int nThreads) {
        initializeReportSettings();
        ReportTheme reportTheme = ReportThemeFactory
                .getReportsTheme(Theme.valueOf(properties.getProperty("ReportsTheme")));

        summaryReport = new SeleniumReport(reportSettings, reportTheme, testParameters);

        summaryReport.initialize();
        summaryReport.initializeResultSummary();

        createResultSummaryHeader(nThreads);
    }

    /**
     * Function to initialize the test batch execution
     *
     * @param runConfiguration The run configuration to be executed
     */
    public void initializeTestBatch(String runConfiguration) {
        overallStartTime = Util.getCurrentTime();

        properties = Settings.getInstance();

        frameworkParameters.setRunConfiguration(runConfiguration);

        frameworkParameters
                .setStartCapturingObjects(Boolean.parseBoolean(properties.getProperty("StartCapturingObjects")));

        frameworkParameters.setHealObject(Boolean.parseBoolean(properties.getProperty("HealObjects")));

        frameworkParameters.setForceHeal(Boolean.parseBoolean(properties.getProperty("ForceHeal")));
    }

    /**
     * Function to launch the summary report at the end of the test batch execution
     */
    public void launchResultSummary() {
        if (reportSettings.shouldGenerateHtmlReports()) {
            try {
                /**
                 * Use this Area for Sending any Mails through framework
                 */
                String encryptedPath = WhitelistingPath
                        .cleanStringForFilePath(reportPath + Util.getFileSeparator() + "ErrorLog.txt");
                if (Boolean.parseBoolean(properties.getProperty("LaunchGSCentral"))) {
                    URI url = null;
                    try {
                        url = new URI(properties.getProperty("GSCentralURL"));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    java.awt.Desktop.getDesktop().browse(url);
                } else {
                    if (checkExceptionInErrorLogTxt()) {
                        File f = new File(encryptedPath);
                     //   java.awt.Desktop.getDesktop().edit(f);
                    }
                    String encryptedHtml = WhitelistingPath.cleanStringForFilePath(reportPath + Util.getFileSeparator()
                            + "Extent Result" + Util.getFileSeparator() + "ExtentReport.Html");

                    File htmlFile = new File(encryptedHtml);
                    java.awt.Desktop.getDesktop().browse(htmlFile.toURI());

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Function to set the absolute path of the framework (to be used as a relative
     * path)
     */
    public void setRelativePath() {
        String encryptedPath = WhitelistingPath.cleanStringForFilePath(System.getProperty("user.dir"));
        String relativePath = new File(encryptedPath).getAbsolutePath();
        if (relativePath.contains("supportlibraries")) {
            relativePath = new File(encryptedPath).getParent();
        }
        frameworkParameters.setRelativePath(relativePath);
    }

    /**
     * Function to set up the error log file within the test report
     */
    public void setupErrorLog() {
        String errorLogFile = reportPath + Util.getFileSeparator() + "ErrorLog.txt";
        String encryptedPath = WhitelistingPath.cleanStringForFilePath(errorLogFile);
        try {
            System.setErr(new PrintStream(new FileOutputStream(encryptedPath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while setting up the Error log!");
        }
    }

    /**
     * Function to update the results summary with the status of the test instance
     * which was executed
     *
     * @param testParameters The {@link SeleniumTestParameters} object containing
     *                       the details of the test instance which was executed
     * @param testReportName The name of the test report file corresponding to the
     *                       test instance
     * @param executionTime  The time taken to execute the test instance
     * @param testStatus     The Pass/Fail status of the test instance
     */
    public void updateResultSummary(SeleniumTestParameters testParameters, String testReportName, String executionTime,
                                    String testStatus) {
        summaryReport.updateResultSummary(testParameters, testReportName, executionTime, testStatus);

        summaryPath = WhitelistingPath.cleanStringForFilePath(reportPath + Util.getFileSeparator() + "HTML Results"
                + Util.getFileSeparator() + testParameters.getCurrentScenario() + "_"
                + testParameters.getCurrentTestcase() + "_Instance1.Html");
    }

    /**
     * Function to do the required wrap-up activities after completing the test
     * batch execution
     *
     * @param testExecutedInUnitTestFramework Boolean variable indicating whether
     *                                        the test is executed in JUnit/TestNG
     */
    public void wrapUp(Boolean testExecutedInUnitTestFramework) {
        overallEndTime = Util.getCurrentTime();
        String totalExecutionTime = Util.getTimeDifference(overallStartTime, overallEndTime);
        summaryReport.addResultSummaryFooter(totalExecutionTime);

        String encrpytedResultSrc = WhitelistingPath.cleanStringForFilePath(frameworkParameters.getRelativePath()
                + Util.getFileSeparator() + properties.getProperty("TestNgReportPath") + Util.getFileSeparator()
                + frameworkParameters.getRunConfiguration());

        String encryptedCss = WhitelistingPath
                .cleanStringForFilePath(frameworkParameters.getRelativePath() + Util.getFileSeparator()
                        + properties.getProperty("TestNgReportPath") + Util.getFileSeparator() + "testng.css");

        if (testExecutedInUnitTestFramework && System.getProperty("ReportPath") == null) {
            File testNgResultSrc = new File(encrpytedResultSrc);
            File testNgResultCssFile = new File(encryptedCss);
            File testNgResultDest = summaryReport.createResultsSubFolder("TestNG Results");
            testNgResultSrc.mkdirs();


            try {
                FileUtils.copyDirectoryToDirectory(testNgResultSrc, testNgResultDest);
                FileUtils.copyFileToDirectory(testNgResultCssFile, testNgResultDest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void postResultToTestRail(TestCaseBean tcb, String testCaseStatus, String executionTime, String tcName) {
        try {
            APIClient client = new APIClient("https://hellonimbly.testrail.io");
            client.setUser("gopesh@hellonimbly.com");
            client.setPassword("Lieutenant@2");
            tcName=tcName.split("_")[1];
            int tcID=Integer.parseInt(tcName);
            Map<String, Object> addRunMap = new HashMap<>();
            ArrayList casesID = new ArrayList();
            casesID.add(tcID);
            addRunMap.put("suite_id", 4);
            addRunMap.put("name", "Auto_Demo_Run");
            addRunMap.put("include_all", false);
            addRunMap.put("case_ids", casesID);


            JSONObject addRun = (JSONObject) client.sendPost("add_run/4", addRunMap);
            long runId = (long) addRun.get("id");

            int tcStatusId = getStatusID(testCaseStatus);
            Map<String, Object> resltMap = new HashMap<>();
            resltMap.put("status_id", tcStatusId);
            resltMap.put("comment", tcb.getFailureReason());
            resltMap.put("version", "tcStatusId");
            resltMap.put("elapsed", executionTime);
            ArrayList<Map<String, Object>> listDat = new ArrayList<>();
            for (TestStepBean tsb : tcb.getTestSteps()) {
                Map<String, Object> testStep = new HashMap<>();
                String actual;
                int statusid = getStatusID(tsb.getTestStepStatus());
                if (statusid==1) {
                    actual = tsb.getTestStepDescription();
                } else {
                    actual = "Method:- " + tsb.getMethodName() + "\n" +
                            "\t\t\t\"FailureReason:-" +  tsb.getTestStepDescription()+ "\n" +
                            "\t\t\t\"StackTrace:-" + tsb.getStackTrace();
                }
                testStep.put("content", "Step"+tsb.getTestStepNumber());
                testStep.put("expected", tsb.getTestStepName());
                testStep.put("actual", actual);
                testStep.put("status_id", tcStatusId);

                listDat.add(testStep);
            }

            resltMap.put("custom_step_results", listDat);


            JSONObject addResult = (JSONObject) client.sendPost("add_result_for_case/" + runId + "/"+tcID, resltMap);
            long resultID = (long) addResult.get("id");
            String encryptedHtml = WhitelistingPath.cleanStringForFilePath(reportPath + Util.getFileSeparator()
                    + "ScreenShots");
            String[] pathnames;
            File f = new File(encryptedHtml);

            pathnames = f.list();

            for (String pathname : pathnames) {

                if (pathname.contains(tcName)) {
                    String ssPath = encryptedHtml + Util.getFileSeparator() + pathname;

                    client.sendPost("add_attachment_to_result/" + resultID, ssPath);
                }
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getCause());
        } catch (APIException apiException) {
            System.out.println(apiException.getCause());
        }


    }

    public int getStatusID(String status) {
        int statusID = 1;
        switch (status.toUpperCase()) {
            case "PASS":

            case "PASSED":
                statusID = 1;
                break;
            case "FAIL":
            case "FAILED":
                statusID = 5;
                break;
            default:
                statusID = 3;
                break;

        }

        return statusID;
    }
}