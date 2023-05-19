package com.qtest;

import static io.restassured.config.EncoderConfig.encoderConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;

import com.winSupply.framework.TestStepBean;
import org.json.JSONArray;
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
    public static int testLogID = 0;
    public static List<Map<String, Integer>> stepIds;
    public static String startDate;

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
            System.out.println("Connection established with the QTest Server...");
            JsonPath jsonResponse = res.jsonPath();
            accessToken = jsonResponse.get("access_token");
            String tokenType = jsonResponse.get("token_type");
            startDate = getQtestFormatTimeStamp();
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
        Response res = request.get("https://" + companyName + ".qtestnet.com/api/v3/projects/" + projectID + "/test-cases?size=1000");
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
            idMapper.put(testId.get(i), testNames.get(i));
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

    public static int createTestCycle(int projectID, String testCycleName) {
        JSONObject json = new JSONObject();

        json.put("name", testCycleName);
        json.put("description", "Automated cycle");
        String jsonBody = json.toJSONString();
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + accessToken);
        request.header("Content-Type", "application/json");
        request.body(jsonBody);

        Response res = request.post("https://" + companyName + ".qtestnet.com/api/v3/projects/" + projectID
                + "/test-cycles");
        int code = res.getStatusCode();
        Assert.assertEquals(code, 200);
        JsonPath jsonResponse = res.jsonPath();
        int testCycleID = jsonResponse.getInt("id");
        return testCycleID;
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
        System.out.println("Test Run: " + testRunID);
        System.out.println("Test Case: " + testCaseID);
        System.out.println("Suite: " + testSuiteID);
        return testRunID;
    }

    public static List<Map<String, Integer>> getTestStepIds(int projectID, int testRunID) {
        List<Map<String, Integer>> testStepIds = new ArrayList<>();
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + accessToken);
        Response res = request.get("https://" + companyName + ".qtestnet.com/api/v3/projects/" + projectID + "/test-runs/" + testRunID + "/test-logs");
        int code = res.getStatusCode();
        Assert.assertEquals(code, 200);
        JsonPath jsonResponse = res.jsonPath();
        List<Object> items = jsonResponse.getList("items");
        for (Object item : items) {
            if (item instanceof Map) {
                Map<String, Object> itemMap = (Map<String, Object>) item;
                List<Map<String, Object>> testStepLogs = (List<Map<String, Object>>) itemMap.get("test_step_logs");
                for (Map<String, Object> testStepLog : testStepLogs) {
                    int testStepId = (int) testStepLog.get("test_step_id");
                    int testStepLogId = (int) testStepLog.get("test_step_log_id");
                    Map<String, Integer> testStepIdMap = new HashMap<>();
                    testStepIdMap.put("test_step_id", testStepId);
                    testStepIdMap.put("test_step_log_id", testStepLogId);
                    testStepIds.add(testStepIdMap);
                }
            }
        }
        stepIds = testStepIds;
        return testStepIds;
    }


    public static void createTestRun(int projectID, int testRunID) {
        String submittedBy = "Automation Test";
        JSONObject json = new JSONObject();
        json.put("submittedBy", submittedBy);
        json.put("exe_start_date", startDate);
        json.put("exe_end_date", startDate);
        JSONObject jsonNest = new JSONObject();
        jsonNest.put("id", 605);
        jsonNest.put("name", "Unexecuted");
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
        if (code == 400) {
            System.out.println(jsonResponse.getString("message").toUpperCase());
            return;
        }
        testLogID = jsonResponse.getInt("id");
        System.out.println("Test Log ID: " + testLogID);
        Assert.assertEquals(code, 201);
    }

    /**
     * Update status of the test case
     *
     * @param projectID
     * @param testRunID
     * @param status
     */
    public static void updateTestStatus(int projectID, int testRunID, String status, List<TestStepBean> testStepBeanList) {
        String submittedBy = "Automation Test";
        int statusID = testStatusId(status);
        JSONObject json = new JSONObject();
        json.put("submittedBy", submittedBy);
        json.put("exe_start_date", startDate);
        json.put("exe_end_date", getQtestFormatTimeStamp());
        JSONObject jsonNest = new JSONObject();
        jsonNest.put("id", statusID);
        jsonNest.put("name", status);
        json.put("status", jsonNest);

        JSONArray testStepsArray = new JSONArray();
        List<Map<String, Integer>> testSteps = getTestStepIds(projectID, testRunID);
        for (int i = 0; i < testSteps.size(); i++) {
            Map<String, Integer> testStep = testSteps.get(i);
            int testStepId = testStep.get("test_step_id");
            int testStepLogId = testStep.get("test_step_log_id");
            String stepStatus = "Unexecuted";
            String actualResult = "Unavailable";
            int statusId = 605;
            if (i < testStepBeanList.size()) {
                TestStepBean step = testStepBeanList.get(i);
                statusId = testStatusId(step.getTestStepStatus());
                stepStatus = step.getTestStepStatus();
                actualResult = step.getTestStepDescription();
            }
            JSONObject testStepObj = new JSONObject();
            JSONObject statusObj = new JSONObject();
            statusObj.put("id", statusId);
            statusObj.put("name", stepStatus);
            testStepObj.put("test_step_id", testStepId);
            testStepObj.put("test_step_log_id", testStepLogId);
            testStepObj.put("order", i + 1);
            testStepObj.put("status", statusObj);
            testStepObj.put("actual_result", actualResult);
            testStepsArray.put(testStepObj);
        }

        json.put("test_step_logs", testStepsArray);
        String jsonBody = json.toJSONString();

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + accessToken);
        request.header("Content-Type", "application/json");
        request.body(jsonBody);

        Response res = request.put("https://" + companyName + ".qtestnet.com/api/v3/projects/" + projectID
                + "/test-runs/" + testRunID + "/test-logs/" + testLogID);
        int code = res.getStatusCode();
        Assert.assertEquals(code, 200);
    }

    public static void updateTestSteps(int projectID, int testCaseId, List<TestStepBean> testSteps) {
        for (int i = 0; i < testSteps.size(); i++) {
            TestStepBean step = testSteps.get(i);
            JSONObject json = new JSONObject();
            json.put("description", step.getTestStepDescription());
            json.put("expected", step.getTestStepDescription());
            json.put("order", testSteps.indexOf(step) + 1);
            json.put("plain_value_text", step.getTestStepDescription());
            String jsonBody = json.toJSONString();
            RequestSpecification request = RestAssured.given();
            request.header("Authorization", "Bearer " + accessToken);
            request.header("Content-Type", "application/json");
            request.body(jsonBody);
            System.out.println(step.getTestStepDescription());
            Response res = request.post("https://" + companyName + ".qtestnet.com/api/v3/projects/" + projectID
                    + "/test-cases/" + testCaseId + "/test-steps");
            int code = res.getStatusCode();
            Assert.assertEquals(code, 201);
        }
    }

    /**
     * Upload test screenshots to the current test case
     *
     * @param projectID
     * @param filePath
     * @param fileName
     * @throws IOException
     */
    public static void uploadTestLogAttachment(int projectID, String filePath, List<String> screenShots) throws IOException {
        if (stepIds.isEmpty()) {
            System.out.println("NO TEST STEPS FOR THIS TEST CASE, UNABLE TO ATTACH PHOTOS TO STEPS!");
            return;
        }
        for (int i = 0; i < screenShots.size(); i++) {
            if (i >= stepIds.size()) {
                System.out.println("NUMBER OF STEPS IN QTEST DO NOT MATCH WITH STEPS COMPLETED.");
                return;
            }
            String fileName = screenShots.get(i);
            Map<String, Integer> testStep = stepIds.get(i);
            int testStepLogId = testStep.get("test_step_log_id");
            byte[] binaryImage = getBinaryImage(filePath, fileName);
            RequestSpecification request = RestAssured.given();
            request.config(RestAssured.config()
                    .encoderConfig(encoderConfig().encodeContentTypeAs("application/png", ContentType.BINARY)));
            request.header("Authorization", "Bearer " + accessToken);
            request.header("Content-Type", "application/png");
            request.header("File-Name", fileName);
            request.body(binaryImage);
            if (testLogID != 0) {
                Response res = request.post("https://" + companyName + ".qtestnet.com/api/v3/projects/" + projectID
                        + "/test-step-logs/" + testStepLogId + "/blob-handles");
                JsonPath jsonResponse = res.jsonPath();
                Assert.assertTrue(jsonResponse.get("name").equals(fileName));
                int code = res.getStatusCode();
                Assert.assertEquals(code, 201);
            }
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
        switch (status.toUpperCase()) {
            case "PASSED":
                id = 601;
                break;
            case "PASS":
                id = 601;
                break;
            case "FAILED":
                id = 602;
                break;
            case "FAIL":
                id = 602;
                break;
            case "INCOMPLETE":
                id = 603;
                break;
            case "BLOCKED":
                id = 604;
                break;
            case "UNEXECUTED":
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
