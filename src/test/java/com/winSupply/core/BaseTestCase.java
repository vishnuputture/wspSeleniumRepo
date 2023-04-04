package com.winSupply.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mattermost.MattermostAPIHandler;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qtest.QTestManager;
import com.winSupply.framework.FrameworkParameters;
import com.winSupply.framework.Settings;
import com.winSupply.framework.TestCaseBean;
import com.winSupply.framework.Util;
import com.winSupply.framework.selenium.ResultSummaryManager;
import com.winSupply.framework.selenium.SeleniumTestParameters;

/**
 * Abstract base class for all the test cases to be automated
 *
 * @author winSupply
 */
public abstract class BaseTestCase {
	/**
	 * The current scenario
	 */
	protected String currentScenario;
	/**
	 * The current test case
	 */

	/**
	 * The {@link Properties} object with settings loaded from the framework
	 * properties file
	 */
	protected Properties properties;
	protected String currentTestcase;

	private ResultSummaryManager resultSummaryManager = ResultSummaryManager.getInstance();

	protected static ExtentHtmlReporter htmlReporter;
	protected static ExtentReports extentReport;
	protected static ExtentTest extentTest;

	protected static String TEST_STATUS;

	private MattermostAPIHandler mattermost;

	public static int countReRunFailedTestCase = 1;
	public static boolean rerun = false;
	public static int rerunCounter = 0;
	public static boolean sResponseMessage;

	public static int getCounter() {
		return rerunCounter;
	}

	public static void updateCounter() {
		rerunCounter++;
	}

	public static boolean getRerun() {
		return rerun;
	}

	public static void toggleRerun() {
		rerun = !rerun;
	}

	public static void updateReRun() {
		rerun = true;
	}

	/**
	 * Function to do the required framework setup activities before executing the
	 * overall test suite
	 *
	 * @param testContext The TestNG {@link ITestContext} of the current test suite
	 */
	@BeforeSuite
	public void setUpTestSuite(ITestContext testContext) {
		System.out.println("Test suite : "+testContext.getSuite().getName()+" Thread "+Thread.currentThread().getId());
		numberOfTests = 0;
		resultSummaryManager.setRelativePath();
		resultSummaryManager.initializeTestBatch(testContext.getSuite().getName());

		int nThreads;
		if ("false".equalsIgnoreCase(testContext.getSuite().getParallel())) {
			nThreads = 1;

		} else {
			nThreads = testContext.getCurrentXmlTest().getThreadCount();

		}
		System.out.println("Results thread "+nThreads);
		// Note: Separate threads may be spawned through usage of DataProvider
		// testContext.getSuite().getXmlSuite().getDataProviderThreadCount();
		// This will be at test case level (multiple instances on same test case
		// in parallel)
		// This level of threading will not be reflected in the summary report

		resultSummaryManager.initializeSummaryReport(nThreads);
		resultSummaryManager.setupErrorLog();
		generateExtentReports();

		String qtestSave = getProperties("SaveDataToQTest");
		if (qtestSave.equalsIgnoreCase("true")) {
			QTestManager.createQtestSuite(testContext.getSuite().getName());
		}
		if (getProperties("Mattermost_post_summary").equalsIgnoreCase("true")) {
			String channel = MattermostAPIHandler.getChannelIDByName(properties.getProperty("Mattermost_channel_name"));
			MattermostAPIHandler.postMessage(channel, "Starting Test Suite: " + testContext.getSuite().getName());
		}

	}

	/**
	 * Function to do the required framework setup activities before executing each
	 * test case
	 */
	@BeforeMethod
	public void setUpTestRunner(Method method) {
		System.out.println("Before test : "+method.getName());
		FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();

		if (frameworkParameters.getStopExecution()) {
			if (getProperties("Mattermost_post_summary").equalsIgnoreCase("true")) {
				String channel = MattermostAPIHandler.getChannelIDByName(properties.getProperty("Mattermost_channel_name"));
				MattermostAPIHandler.postMessage(channel, "After Test Suite: " + properties.getProperty("RunConfiguration") + "\n" +
						"Failed tests: " + failedTestCase.size() + "/" + numberOfTests);
			}

			resultSummaryManager.wrapUp(true);
			System.out.println("Before test : "+method.getName()+" 2 ");
			try {
				extentReport.flush();
			}catch(Exception e){
				System.out.println("report flush failed");
			}


			System.out.println("Before test : "+method.getName()+" 3 ");
			resultSummaryManager.launchResultSummary();
			System.out.println("Before test : "+method.getName()+" 4 ");

			// Throwing TestNG SkipException within a configuration method
			// causes all subsequent test methods to be skipped/aborted
			throw new SkipException("Test execution terminated by user! All subsequent tests aborted...");
		}
	}

	/**
	 * Function to do the required framework teardown activities after executing
	 * each test case
	 *
	 * @param testParameters The {@link SeleniumTestParameters} object passed from
	 *                       the test case
	 * @param driverScript   The {@link CoreScript} object passed from the test case
	 */

	public static List<String> failedTestCase = new ArrayList<String>();
	public static Integer numberOfTests;
	public static String currentPacakage;
	public static String currentTestCase;

	protected synchronized void tearDownTestRunner(SeleniumTestParameters testParameters, CoreScript coreScript) {
		System.out.println("tear down : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
		numberOfTests++;
		TestCaseBean testCaseBean = coreScript.getTestCaseBean();
		String testReportName = coreScript.getReportName();
		String executionTime = coreScript.getExecutionTime();
		String testStatus = coreScript.getTestStatus();

		TEST_STATUS = testStatus;// Added this code to extract test case status for linking e2e automation

		// resultSummaryManager.updateResultSummary(testParameters, testReportName,
		// executionTime, testStatus);
		Properties properties = Settings.getInstance();

		/* DB-Updating reports to database */

		currentPacakage = testParameters.getCurrentScenario();
		currentTestCase = testParameters.getCurrentTestcase();
		// Update tests in QTest Suite
		String qtestSave = getProperties("SaveDataToQTest");

		if (qtestSave.equalsIgnoreCase("true")) {
			QTestManager.createTestRunInsideTestSuite(testParameters.getCurrentTestcase());
			QTestManager.updateTestRunStatus(testStatus, coreScript.getReport().getTestStepBeanList());
			String path = resultSummaryManager.getReportPath() + Util.getFileSeparator() + "Screenshots"
					+ Util.getFileSeparator();
			QTestManager.uploadTestScreenshotstoTestLog(path, currentTestCase);
			QTestManager.updateSteps(coreScript.getReport().getTestStepBeanList()); //UNCOMMENT THIS IF YOU WANT TO UPLOAD STEPS AFTER TEST COMPLETION
		}
		if (getProperties("Mattermost_post_details").equalsIgnoreCase("true")) {
			String channel = MattermostAPIHandler.getChannelIDByName(properties.getProperty("Mattermost_channel_name"));
			MattermostAPIHandler.postMessage(channel, testParameters.getCurrentTestcase() + ": " + testStatus);
		}

		if ("Failed".equalsIgnoreCase(testStatus)) {
			failedTestCase.add(
					"testscripts." + testParameters.getCurrentScenario() + "." + testParameters.getCurrentTestcase());
			Assert.fail(coreScript.getFailureDescription());

		}



	}

	/**
	 * Function to do the required framework teardown activities after executing the
	 * overall test suite
	 */
	@AfterSuite
	public void tearDownTestSuite(ITestContext testContext) {
		System.out.println("Test suite : "+testContext.getSuite().getName()+" Thread "+Thread.currentThread().getId());
		if (getProperties("Mattermost_post_summary").equalsIgnoreCase("true")) {
			String channel = MattermostAPIHandler.getChannelIDByName(properties.getProperty("Mattermost_channel_name"));
			MattermostAPIHandler.postMessage(channel, "After Test Suite: " + testContext.getSuite().getName() + "\n" +
					"Failed tests: " + failedTestCase.size() + "/" + numberOfTests);
		}

		resultSummaryManager.wrapUp(true);
		extentReport.flush();
		resultSummaryManager.launchResultSummary();

	}

	/**
	 * Function to set Extent Report Path within Framework and initialze extent
	 * objects
	 */
	private void generateExtentReports() {
		Properties properties = Settings.getInstance();
		htmlReporter = new ExtentHtmlReporter(resultSummaryManager.getReportPath() + Util.getFileSeparator()
				+ "Extent Result" + Util.getFileSeparator() + "ExtentReport.html");

		htmlReporter.config().setDocumentTitle(" Extent Report");

		// # Added Line Item to Run Suite of Suite on 12th April 2022
		htmlReporter.setAppendExisting(true);

		htmlReporter.config().setReportName("Extent Report for Automation");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);

		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);
		extentReport.setSystemInfo("Project Name", properties.getProperty("ProjectName"));
		extentReport.setSystemInfo("Framework", " Maven");
		extentReport.setSystemInfo("Framework Version", "3.2");
		extentReport.setSystemInfo("Author", "NUMQ");



	}

	/**
	 * Function to get system variable value if system variable is null then get val from Gobal Properties
	 * @param key
	 * @return
	 */
	protected  String getProperties(String key){
		String val=System.getProperty(key);
		properties= Settings.getInstance();
		if(val==null){
			val=     properties.getProperty(key);
		}
		return val;
	}

}
