package com.winSupply.core;

import java.util.Map;

import com.winSupply.framework.JsonDataExcess;
import com.winSupply.framework.selenium.FrameworkDriver;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.winSupply.framework.APIReusuableLibrary;
import com.winSupply.framework.selenium.SeleniumReport;
import com.winSupply.framework.selenium.WebDriverUtil;

/**
 * Wrapper class for common framework objects, to be used across the entire test
 * case and dependent libraries
 * 
 * @author winSupply
 */
public class Helper {


	private final SeleniumReport report;
	private  final JsonDataExcess jsonData;
	private FrameworkDriver driver;
	private WebDriverUtil driverUtil;
	private APIReusuableLibrary apiDriver;
	private ExtentTest extentTest;
	private Map<String, String> reusableHandle;
	private int endIteration;


	/**
	 * Constructor to initialize all the objects wrapped by the
	 * {@link Helper} class
	 * 
	 * @param jsonData
	 *            The {@link JsonDataExcess} object
	 * @param report
	 *            The {@link SeleniumReport} object
	 * @param driver
	 *            The {@link WebDriver} object
	 * @param driverUtil
	 *            The {@link WebDriverUtil} object
	 * @param reusableHandle
	 * @param extentTest
	 * @param apiDriver
	 */

	public Helper(JsonDataExcess jsonData, SeleniumReport report, FrameworkDriver driver,
                  WebDriverUtil driverUtil, APIReusuableLibrary apiDriver, ExtentTest extentTest,
                  Map<String, String> reusableHandle, int endIteration) {
		this.jsonData = jsonData;
		this.report = report;
		this.driver = driver;
		this.driverUtil = driverUtil;
		this.apiDriver = apiDriver;
		this.extentTest = extentTest;
		this.reusableHandle = reusableHandle;
		this.endIteration= endIteration;

	
	}

	/**
	 * Function to get the {@link JsonDataExcess} object
	 * 
	 * @return The {@link JsonDataExcess} object
	 */
	public JsonDataExcess getDataTable() {
		return jsonData;
	}

	/**
	 * Function to get the {@link SeleniumReport} object
	 * 
	 * @return The {@link SeleniumReport} object
	 */
	public SeleniumReport getReport() {
		return report;
	}

	/**
	 * Function to get the {@link FrameworkDriver} object
	 * 
	 * @return The {@link FrameworkDriver} object
	 */
	public FrameworkDriver getGSDriver() {
		return driver;
	}

	/**
	 * Function to get the {@link WebDriverUtil} object
	 * 
	 * @return The {@link WebDriverUtil} object
	 */
	public WebDriverUtil getDriverUtil() {
		return driverUtil;
	}

	/**
	 * Function to get the {@link APIReusuableLibrary} object
	 * 
	 * @return The {@link APIReusuableLibrary} object
	 */
	public APIReusuableLibrary getApiDriver() {
		return apiDriver;
	}

	/**
	 * Function to get the {@link ExtentTest} object
	 * 
	 * @return The {@link ExtentTest} object
	 */
	public ExtentTest getExtentTest() {
		return extentTest;
	}

	public Map<String, String> getReusablehandle() {
		return reusableHandle;
	}
	public int getEnditeration() {
		return endIteration;
	}
	

	


}