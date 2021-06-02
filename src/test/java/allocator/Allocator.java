package allocator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.Platform;

import com.winSupply.framework.selenium.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.KlovReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.winSupply.framework.FrameworkParameters;
import com.winSupply.framework.IterationOptions;
import com.winSupply.framework.Settings;
import com.winSupply.framework.Util;

/**
 * Class to manage the batch execution of test scripts within the framework
 * 
 * @author winSupply
 */
public class Allocator {
	private FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();
	private Properties properties;
	private Properties mobileProperties;
	private ResultSummaryManager resultSummaryManager = ResultSummaryManager.getInstance();

	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extentReport;
	private static ExtentTest extentTest;
	private static KlovReporter klovReporter = new KlovReporter();

	/**
	 * The entry point of the test batch execution <br>
	 * Exits with a value of 0 if the test passes and 1 if the test fails
	 * 
	 * @param args
	 *            Command line arguments to the Allocator (Not applicable)
	 */
	public static void main(String[] args) {
		Allocator allocator = new Allocator();
		allocator.driveBatchExecution();
	}

	private void driveBatchExecution() {
		resultSummaryManager.setRelativePath();
		properties = Settings.getInstance();
		mobileProperties = Settings.getMobilePropertiesInstance();
		String runConfiguration;
		if (System.getProperty("RunConfiguration") != null) {
			runConfiguration = System.getProperty("RunConfiguration");
		} else {
			runConfiguration = properties.getProperty("RunConfiguration");
		}
		resultSummaryManager.initializeTestBatch(runConfiguration);

		int nThreads = Integer.parseInt(properties.getProperty("NumberOfThreads"));
		resultSummaryManager.initializeSummaryReport(nThreads);

		resultSummaryManager.setupErrorLog();

		generateExtentReports();



		resultSummaryManager.wrapUp(false);

		if (Boolean.parseBoolean(properties.getProperty("GenerateKlov"))) {
			extentReport.attachReporter(klovReporter);
		}

		extentReport.flush();

		resultSummaryManager.launchResultSummary();


	}




	private void generateExtentReports() {
		integrateWithKlov();
		htmlReporter = new ExtentHtmlReporter(resultSummaryManager.getReportPath() + Util.getFileSeparator()
				+ "Extent Result" + Util.getFileSeparator() + "ExtentReport.html");
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);
		extentReport.setSystemInfo("Project Name", properties.getProperty("ProjectName"));
		extentReport.setSystemInfo("Framework", "Selenium Maven");
		extentReport.setSystemInfo("Framework Version", "3.2");
		extentReport.setSystemInfo("Author", "winSupply");

		htmlReporter.config().setDocumentTitle(" Extent Report");
		htmlReporter.config().setReportName("Extent Report for GS");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}

	private void integrateWithKlov() {
		String dbHost = properties.getProperty("DBHost");
		String dbPort = properties.getProperty("DBPort");
		if (Boolean.parseBoolean(properties.getProperty("GenerateKlov"))) {
			klovReporter.initMongoDbConnection(dbHost, Integer.valueOf(dbPort));
			klovReporter.setProjectName(properties.getProperty("GenerateKlov"));
			klovReporter.setReportName("GS Reports");
			klovReporter.setKlovUrl(properties.getProperty("KlovURL"));
		}
	}

}