package com.qtest;

import static io.restassured.config.EncoderConfig.encoderConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.testng.Assert;

import com.winSupply.framework.Settings;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class QtestAPIHandler {

	public static Properties properties = Settings.getInstance();
	public static String accessToken;
	public static String companyName;
	public static int testLogID=0;

	/**
	 * Get login token from QTest
	 * 
	 * @return
	 */
	public static void getloginToken(String username, String password) {
		if (accessToken == null) {
			companyName = properties.getProperty("CompanyName");
			Map<String, String> keys = new HashMap<String, String>() {
				{
					put("grant_type", "password");
					put("username", username);
					put("password", password);
				}
			};

			RequestSpecification request = RestAssured.given();
			request.header("Authorization", "Basic U2FsZXNAZ3NpbmZvc29sdXRpb25zLmNvbTo=");
			request.header("Content-Type", "application/x-www-form-urlencoded");
			request.formParams(keys);
			Response res = request.post("https://" + companyName + ".qtestnet.com/oauth/token");
			int code = res.getStatusCode();
			Assert.assertEquals(code, 200);
			System.out.println("Connected established with the QTest Server...");
			JsonPath jsonResponse = res.jsonPath();
			accessToken = jsonResponse.get("access_token");
			String tokenType = jsonResponse.get("token_type");

		}
	}

	/**
	 * Get all existing project from QTest can be used to retrive project ID for
	 * subsequent operations
	 * 
	 * @return
	 */
	public static Map<Integer, String> getAllProjects() {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + accessToken);
		Response res = request.get("https://" + companyName + ".qtestnet.com/api/v3/projects?assigned=true");
		int code = res.getStatusCode();
		Assert.assertEquals(code, 200);
		JsonPath jsonResponse = res.jsonPath();
		List<String> projectNames = jsonResponse.getList("name");
		List<Integer> projectId = jsonResponse.getList("id");

		Map<Integer, String> projectMapper = new HashMap<Integer, String>();
		for (int i = 0; i < projectNames.size(); i++) {
			projectMapper.put(projectId.get(i), projectNames.get(i));
		}

		return projectMapper;
	}

	/**
	 * Get all tests and ID present in the project
	 * 
	 * @return
	 */
	public static Map<Integer, String> getAllTests(int projectID) {

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + accessToken);
		Response res = request
				.get("https://" + companyName + ".qtestnet.com/api/v3/projects/" + projectID + "/test-cases?size=1000");
		int code = res.getStatusCode();
		Assert.assertEquals(code, 200);
		JsonPath jsonResponse = res.jsonPath();
		List<String> testNames = jsonResponse.getList("name");
		List<Integer> testId = jsonResponse.getList("id");
		List<String> testPid = jsonResponse.getList("pid");
		Map<Integer, String> idMapper = new HashMap<Integer, String>();
		for (int i = 0; i < testId.size(); i++) {
			idMapper.put(testId.get(i), testPid.get(i));
		}
		return idMapper;
	}

	/**
	 * Create Test Cycle in a given project
	 */
	public static void createTestCycle(String projectID) {

		JSONObject json = new JSONObject();
		json.put("name", "Test Cycle Test Java 2");
		json.put("description", "The cycle created with Eclipse Java");
		String jsonBody = json.toJSONString();

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + accessToken);
		request.header("Content-Type", "application/json");
		request.body(jsonBody);

		Response res = request
				.post("https://" + companyName + ".qtestnet.com/api/v3/projects/" + projectID + "/test-cycles");
		int code = res.getStatusCode();
		Assert.assertEquals(code, 200);

		JsonPath jsonResponse = res.jsonPath();
		int testCycleID = jsonResponse.getInt("id");

	}

	/**
	 * This method returns a mapper for test cycle id and pid
	 * 
	 * @return
	 */
	public static Map<Integer, String> getTestCycleID(int projectID) {

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + accessToken);
		Response res = request
				.get("https://" + companyName + ".qtestnet.com/api/v3/projects/" + projectID + "/test-cycles");
		int code = res.getStatusCode();
		Assert.assertEquals(code, 200);
		JsonPath jsonResponse = res.jsonPath();
		List<String> testNames = jsonResponse.getList("name");
		List<Integer> testId = jsonResponse.getList("id");
		List<String> testPid = jsonResponse.getList("pid");
		Map<Integer, String> idMapper = new HashMap<Integer, String>();
		for (int i = 0; i < testId.size(); i++) {
			idMapper.put(testId.get(i), testPid.get(i));
		}
		return idMapper;
	}

	/**
	 * Create test suite inside a test cycle
	 */
	public static int addTestSuitetoTestCycle(int projectID, int testCycleID, String testSuiteName) {

		JSONObject json = new JSONObject();

		json.put("name", testSuiteName);
		String jsonBody = json.toJSONString();
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + accessToken);
		request.header("Content-Type", "application/json");
		request.body(jsonBody);

		Response res = request.post("https://" + companyName + ".qtestnet.com/api/v3/projects/" + projectID
				+ "/test-suites?parentId=" + testCycleID + "&parentType=test-cycle");
		int code = res.getStatusCode();
		Assert.assertEquals(code, 200);
		JsonPath jsonResponse = res.jsonPath();
		int testSuiteID = jsonResponse.getInt("id");
		return testSuiteID;
	}

	/**
	 * Add test case to Test suite
	 */
	public static int addTestCasetoTestSuite(int projectID, int testSuiteID, int testCaseID, String testRunName) {

		JSONObject json = new JSONObject();
		json.put("name", testRunName);
		JSONObject jsonNest = new JSONObject();
		jsonNest.put("id", testCaseID);
		json.put("test_case", jsonNest);
		String jsonBody = json.toJSONString();

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + accessToken);
		request.header("Content-Type", "application/json");
		request.body(jsonBody);

		Response res = request.post("https://" + companyName + ".qtestnet.com/api/v3/projects/" + projectID
				+ "/test-runs?parentId=" + testSuiteID + "&parentType=test-suite");
		int code = res.getStatusCode();
		Assert.assertEquals(code, 201);
		JsonPath jsonResponse = res.jsonPath();
		int testRunID = jsonResponse.getInt("id");
		return testRunID;
	}

	/**
	 * Update status of the test case
	 * 
	 * @param projectID
	 * @param testRunID
	 * @param status
	 */
	public static void updateTestStatus(int projectID, int testRunID, String status) {

		String submittedBy = "Automation Test";
		int statusID = testStatusId(status);
		JSONObject json = new JSONObject();
		json.put("submittedBy", submittedBy);
		json.put("exe_start_date", getQtestFormatTimeStamp());
		json.put("exe_end_date", getQtestFormatTimeStamp());
		JSONObject jsonNest = new JSONObject();
		jsonNest.put("id", statusID);
		jsonNest.put("name", status);
		json.put("status", jsonNest);
		String jsonBody = json.toJSONString();

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + accessToken);
		request.header("Content-Type", "application/json");
		request.body(jsonBody);

		Response res = request.post("https://" + companyName + ".qtestnet.com/api/v3/projects/" + projectID
				+ "/test-runs/" + testRunID + "/test-logs");
		int code = res.getStatusCode();

		JsonPath jsonResponse = res.jsonPath();
		testLogID = jsonResponse.getInt("id");
		System.out.println(testLogID);
		Assert.assertEquals(code, 201);

	}

	/**
	 * Upload test screenshots to the current test case
	 * 
	 * @param projectID
	 * @param filePath
	 * @param fileName
	 * @throws IOException
	 */
	public static void uploadTestLogAttachment(int projectID, String filePath, String fileName) throws IOException {

		byte[] binaryImage = getBinaryImage(filePath, fileName);
		RequestSpecification request = RestAssured.given();
		request.config(RestAssured.config()
				.encoderConfig(encoderConfig().encodeContentTypeAs("application/png", ContentType.BINARY)));
		request.header("Authorization", "Bearer " + "16fe9baa-2f24-4dd7-9736-cd5ab9b9db63");
		request.header("Content-Type", "application/png");
		request.header("File-Name", fileName);
		request.body(binaryImage);
		if(testLogID!=0)
		{
		Response res = request.post("https://" + companyName + ".qtestnet.com/api/v3/projects/" + projectID
				+ "/test-logs/" + testLogID + "/blob-handles");
		JsonPath jsonResponse = res.jsonPath();
		Assert.assertTrue(jsonResponse.get("name").equals(fileName));
		int code = res.getStatusCode();
		Assert.assertEquals(code, 201);
		}

	}

	/**
	 * Convert image to binary byte[] file
	 * 
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static byte[] getBinaryImage(String filePath, String fileName) throws IOException {

		byte[] bytesJson = "".getBytes();
		String encoded = null;

		try {
			bytesJson = Files.readAllBytes(Paths.get(filePath, fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytesJson;

	}

	/**
	 * Get status id for test runs
	 * 
	 * @param status
	 * @return
	 */
	public static int testStatusId(String status) {
		int id = 0;
		switch (status) {
		case "Passed":
			id = 601;
			break;
		case "Failed":
			id = 602;
			break;
		case "Incomplete":
			id = 603;
			break;
		case "Blocked":
			id = 604;
			break;
		case "Unexecuted":
			id = 605;
			break;
		default:
			System.out.println("Status Undetermined");
		}
		return id;
	}

	/**
	 * Get time stamp format accepted by QTest
	 * 
	 * @return
	 */
	public static String getQtestFormatTimeStamp() {
		Date date = new Date();
		Instant timeInstance = date.toInstant();
		String time = timeInstance.toString();
		return time;
	}

}
