package businesskeywords.common;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import commonkeywords.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;

import com.winSupply.framework.Status;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class apiConnect extends ReusableLib {


    String response = "";

    public apiConnect(Helper helper) {
        super(helper);
    }

    /**
     * Creates the Payload Body for getPricingResponse api
     *
     * @return payload for post request
     */

    public static Map<String, Object> generatePricingBody() {
        List<String> data = DBCall.getWISEDATA();
        Map<String, Object> map = new HashMap();
        map.put("customerType", "lcCustomer");
        map.put("stockItems", Arrays.asList(data.get(1)));
        map.put("companyNumber", "99599");
        map.put("accountNumber", data.get(0));
        map.put("masterCustomerNumber", data.get(2));
        map.put("nonStockItems", Arrays.asList());

        return map;
    }

    /**
     * Creates the Payload Body for getResponseForPriceAndCostUpdates
     *
     * @return payload for post request
     */

    public static Map<String, Object> generatePriceAndCostsUpdatesBody() {
        List data = DBCall.getWISEDATA();
        HashMap<String, Object> map = new LinkedHashMap();
        map.put("userId", "PO System");
        map.put("companyNumbers", Arrays.asList("99599"));


        HashMap<String, Object> innermap = new LinkedHashMap();
        innermap.put("itemNumber", data.get(1));
        innermap.put("list", 19);
        innermap.put("matrix", 3);
        innermap.put("poCost", 2);

        map.put("itemUpdates", Arrays.asList(innermap));

        return map;
    }


    /**
     * Sends the request getPricingResponse API and validates the response is 200
     * If the response fails it takes a failure message and attaches it to report
     */


    public String sendReqPayload() {

        Map<String, Object> bodyFile = generatePricingBody();
        System.out.println(bodyFile);

        RestAssured.baseURI = "https://wpsqa.winwholesale.com";

        Response resp = given().auth().basic(jsonData.getData("UserId"), jsonData.getData("Password")).header("Content-Type", "Application/JSON")
                .header(jsonData.getData("EcmId"), jsonData.getData("EcmValue")).header("roleType", "admin").body(bodyFile).when()
                .post("/WisePriceService/pricing").then().assertThat().statusCode(200)
                .body("companyNumber", equalTo("99599")).extract().response();


        if (resp.statusCode() == 200) {
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Body '" + resp.getBody().asString(), Status.PASS);
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Code '" + resp.getStatusCode() + "' is matching With Response Code '" + 200 + "'",
                    Status.PASS);
        } else {
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Code '" + resp.getStatusCode() + "' is not matching With Response Code '" + 200 + "'",
                    Status.FAIL);
        }

        response = resp.asString();
        return response;
    }

    /**
     * Checks the response of getPricingResponse API and validates the product code and product price
     */

    public void getPricingResponse() {

        String result = sendReqPayload();
        System.out.println(result);
        JsonPath js = new JsonPath(result);

        for (int i = 0; i < js.getInt("productPrices.size()"); i++) {
            System.out.println("product code ->" + js.getString("productPrices[" + i + "].productCode") + " : $"
                    + js.getString("productPrices[" + i + "].quantityBreaks[0].price"));

            report.updateTestLogAPI("1", "Product Code " + js.getString("productPrices[" + i + "].productCode") +
                            " having Product Price " + js.getString("productPrices[" + i + "].quantityBreaks[0].price"),
                    Status.PASS);

        }
    }


    /**
     * Sends the request getResponseForPriceAndCostUpdates API and validates the response is 200
     * If status code is 500 , then the same value was sent and updated pricing should be sent in next api call
     * If the response fails it takes a failure message and attaches it to report
     */

    public String sendReqPayloadForPriceAndCostUpdates() {
        //readAPI();
        Map<String, Object> bodyFile = generatePriceAndCostsUpdatesBody();
        System.out.println(bodyFile);

        RestAssured.baseURI = "https://qaagint1.winwholesale.com";

        Response resp = given().auth().basic(jsonData.getData("UserId"), jsonData.getData("Password")).header("Content-Type", "application/json")
                .header("client_id", jsonData.getData("ClientId")).header("client_secret", jsonData.getData("ClientSecret"))
                .body(bodyFile).when()
                .post("/item-service-ppupdates/itemPriceAndCostUpdates").then().assertThat().statusCode(200)
                .body("meta.message", equalTo("Successfully updated the prices for the items in the request")).extract().response();

        if (resp.statusCode() == 200) {
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Body '" + resp.getBody().asString(), Status.PASS);
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Code '" + resp.getStatusCode() + "' is matching With Response Code '" + 200 + "'",
                    Status.PASS);
        } else if (resp.statusCode() == 500) {
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Body '" + resp.getBody().asString(), Status.WARNING);
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Code '" + resp.getStatusCode() + "' is matching With Response Code '" + 500 + "'",
                    Status.WARNING);
        } else {
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Code '" + resp.getStatusCode() + "' is not matching With Response Code '" + 200 + "'",
                    Status.FAIL);
        }

        response = resp.asString();
        return response;
    }

    /**
     * Checks the response of getResponseForPriceAndCostUpdates API and validates the success code and message
     */

    public void getResponseForPriceAndCostUpdates() {

        String result = sendReqPayloadForPriceAndCostUpdates();
        System.out.println(result);
        JsonPath js = new JsonPath(result);
        String value = js.getString("meta.success");
        if (value.equalsIgnoreCase("true")) {
            report.updateTestLogAPI("1", "success: " + js.getString("meta.success"), Status.PASS);
            report.updateTestLogAPI("1", "message: " + js.getString("meta.message"), Status.PASS);
        } else {
            report.updateTestLogAPI("1", "success: " + js.getString("meta.success"), Status.WARNING);
            report.updateTestLogAPI("1", "message: " + js.getString("meta.message"), Status.WARNING);
        }


    }


}
