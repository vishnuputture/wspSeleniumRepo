package com.qtest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.winSupply.framework.Settings;

public class QTestManager {
	public static int projectid = 39801;
	public static int testCycleid;
	// Mapping between UI id and Test ID
	public static Map<Integer, String> testCaseMapper;
	// Mapping between UI Test Case ID and ID of created Test Run
	public static Map<String, Integer> testRunMapper = new HashMap<>();
	public static String projectName;
	public static String uiTestCycleID;
	public static String testSuiteName;
	public static String testRunName;
	public static int testSuiteID;
	public static int testCaseid;
	public static int testRunid;
	public static Properties properties = Settings.getInstance();

	/**
	 * Get project id by using project name
	 * 
	 * @param projectName
	 * @return
	 */
	public static int getQtestProjectID(String projectName) {
		Map<Integer, String> projectMapper = QtestAPIHandler.getAllProjects();
		int currentProjectid = 0;
		for (Integer key : projectMapper.keySet()) {
			if (projectMapper.get(key).equalsIgnoreCase(projectName)) {
				currentProjectid = key;
				break;
			}
		}
		return currentProjectid;
	}

	/**
	 * get test cycle id
	 * 
	 * @param uitestCycleID
	 * @return
	 */
	public static int getTestCycleID(String uitestCycleID) {
		Map<Integer, String> testCycleMapper = QtestAPIHandler.getTestCycleID(projectid);
		int testCycleid = 0;
		for (Integer key : testCycleMapper.keySet()) {
			if (testCycleMapper.get(key).equalsIgnoreCase(uitestCycleID)) {
				testCycleid = key;
				break;
			}
		}
		return testCycleid;
	}

	/**
	 * Create map for test id and UI id
	 */
	public static void getTestCaseMapper() {
		testCaseMapper = QtestAPIHandler.getAllTests(projectid);
	}

	/**
	 * Get test case id from test case map
	 * 
	 * @param uiTestID
	 * @return
	 */
	public static int getTestCaseID(String uiTestID) {
		int testCaseID = 0;

		for (Integer key : testCaseMapper.keySet()) {
			if (testCaseMapper.get(key).equalsIgnoreCase(uiTestID)) {
				testCaseID = key;
				break;
			}
		}

		return testCaseID;
	}

	/**
	 * Get test case id from running test
	 * 
	 * @return
	 */
	public static String getTestID(String currenttestCaseName) {
		String[] str = currenttestCaseName.split("_");
		String s = str[0] + "-" + str[1];
		String st = s;
		return st;
	}

	/**
	 * Get test run name from running test
	 * 
	 * @return
	 */
	public static String getTestRunName(String currenttestCaseName) {
		String s;
		try {
			String[] str = currenttestCaseName.split("_");
			s = str[2];
		} catch (Exception e) {
			s = currenttestCaseName;
		}
		return s;
	}

	public static List<String> getAllScreenshots(String path, String testCaseName) {

		File scrnFile = new File(path);
		File[] files = scrnFile.listFiles();
		List<String> scrnshotName = new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains(testCaseName)) {
				scrnshotName.add(files[i].getName());
			}
		}
		return scrnshotName;
	}

	/*********************
	 * Methods to create tests run and log
	 ********************************/
	public static void main(String[] args) {

		// updateTestRunStatus("Passed");
	}

	/**
	 * This method will be initialized in beforeSuite Creates connection with API
	 * Find Test Cycle and create Test suite
	 */
	public static void createQtestSuite() {
		// properties = Settings.getInstance();
		String username = properties.getProperty("qTest_UserName");
		String password = properties.getProperty("qTest_Password");
		createAPIconnection(username, password);
		createTestSuite();
	}

	/**
	 * Create a test run for current test case inside the test suite
	 * 
	 * @param currentTestCase
	 */
	public static void createTestRunInsideTestSuite(String currentTestCase) {
		createTestRuninTestSuite(currentTestCase);
	}

	/**
	 * Connect with QTest with API
	 */
	public static void createAPIconnection(String username, String password) {
		QtestAPIHandler.getloginToken(username, password);

		projectName = properties.getProperty("qTest_ProjectName");
		projectid = getQtestProjectID(projectName);
		System.out.println("Connected with QTest with API");
	}

	/**
	 * Create a test suite with existing test cycle
	 */
	public static void createTestSuite() {
		uiTestCycleID = properties.getProperty("qTest_CycleID");
		testCycleid = getTestCycleID(uiTestCycleID);
		testSuiteName = properties.getProperty("BuildName") + "_"
				+ QtestAPIHandler.getQtestFormatTimeStamp().split("\\.")[0];
		testSuiteID = QtestAPIHandler.addTestSuitetoTestCycle(projectid, testCycleid, testSuiteName);
		getTestCaseMapper();
		System.out.println("Test suite and test mapping created");
	}

	/**
	 * Create test inside the test Suite
	 */
	public static void createTestRuninTestSuite(String currentTestCase) {
		String uiTestID = getTestID(currentTestCase).toUpperCase();

		testCaseid = getTestCaseID(uiTestID);
		if (testCaseid != 0) {
			// for(int testCaseid : testCaseMapper.keySet()) {
			testRunid = QtestAPIHandler.addTestCasetoTestSuite(projectid, testSuiteID, testCaseid,
					getTestRunName(currentTestCase));
			testRunMapper.put(uiTestID, testRunid);
		}
		// }
	}

	/**
	 * update status of current test run: "Passed" "Failed" "Incomplete" "Blocked"
	 * "Unexecuted"
	 * 
	 * @param status
	 */
	public static void updateTestRunStatus(String status) {
		if (testCaseid != 0) {
			QtestAPIHandler.updateTestStatus(projectid, testRunid, status);
		}
	}

	/**
	 * Update status of a test run with given test id "Passed" "Failed" "Incomplete"
	 * "Blocked" "Unexecuted"
	 * 
	 * @param uiTestID
	 * @param status
	 */
	public static void updateTestRunStatus(String uiTestID, String status) {
		int testrunID = testRunMapper.get(uiTestID);
		QtestAPIHandler.updateTestStatus(projectid, testrunID, status);
	}

	/**
	 * Upload Test Screenshot to the test log
	 * 
	 * @param path
	 * @param testCaseName
	 */
	public static void uploadTestScreenshotstoTestLog(String path, String testCaseName) {
		List<String> allScreenshots = getAllScreenshots(path, testCaseName);

		for (String imgName : allScreenshots) {
			try {
				QtestAPIHandler.uploadTestLogAttachment(projectid, path, imgName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
