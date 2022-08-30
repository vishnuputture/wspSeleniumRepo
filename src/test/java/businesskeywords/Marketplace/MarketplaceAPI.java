package businesskeywords.Marketplace;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import commonkeywords.DBCall;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MarketplaceAPI extends ReusableLib {
    public MarketplaceAPI(Helper helper) {
        super(helper);
    }
    public void getWiseAccount(){
        RestAssured.baseURI = "https://extapiqa.winwholesale.com";

        Response resp = given().auth().basic(jsonData.getData("UserId"),jsonData.getData("Password")).header("consumer", jsonData.getData("UserId"))
                .header("client_id", jsonData.getData("ClientId")).header("client_secret", jsonData.getData("ClientSecret")).when()
                .get("/marketplace/exp/v1/api/" + jsonData.getData("UserID") + "/customer/wiseAccounts?accountNumber=007222&companyNumber=00155")
                .then().assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK").body("fullName[0]", equalTo(jsonData.getData("FullName")))
                .body("companyNumber[0]", equalTo(jsonData.getData("CompanyId"))).header("Content-Type", "application/json; charset=UTF-8").extract().response();
    }
    public void getLocation(){
        RestAssured.baseURI = "https://extapiqa.winwholesale.com";

        Response resp = given().auth().basic(jsonData.getData("UserId"),jsonData.getData("Password")).header("consumer", jsonData.getData("UserId"))
                .header("client_id", jsonData.getData("ClientId")).header("client_secret", jsonData.getData("ClientSecret")).when()
                .get("/marketplace/exp/v1/api/" + jsonData.getData("UserID") + "/locations/" + jsonData.getData("CompanyId"))
                .then().assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK").body("localCompanyID", equalTo(jsonData.getData("CompanyId")))
                .body("localCompanyName", equalTo(jsonData.getData("LocalCompanyName"))).body("presidentFormalName", equalTo(jsonData.getData("PresidentName")))
                .body("prospectAccount", equalTo(jsonData.getData("ProspectAccount"))).extract().response();
    }

    public void postInventoryAvailability(){
        RestAssured.baseURI = "http://mule4qa1:8091";

        Response resp = given().auth().basic(jsonData.getData("UserId"), jsonData.getData("Password")).header("consumer", jsonData.getData("UserId"))
                .header("client_id", jsonData.getData("ClientId")).header("client_secret", jsonData.getData("ClientSecret"))
                .header("Content-Type", "application/json").body(jsonData.getData("PayloadBody")).when()
                .post("/marketplace/exp/v1/api/" + jsonData.getData("UserId") + "/inventoryAvailabilities").then().assertThat().statusCode(201)
                .statusLine("HTTP/1.1 201 Created").body("count", equalTo(4))/*.body("companyNumber[0]", equalTo("00302"))*/
                /*.body("itemNumber[1]", equalTo("000502810"))*/.extract().response();

        if (resp.statusCode() == 201) {
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Body '" + resp.getBody().asString(), Status.PASS);
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Code '" + resp.getStatusCode() + "' is matching With Response Code '" + 201 + "'",
                    Status.PASS);
        } else {
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Code '" + resp.getStatusCode() + "' is not matching With Response Code '" + 201 + "'",
                    Status.FAIL);
        }
    }
    public void postItemPricing(){
        RestAssured.baseURI = "https://extapiqa.winwholesale.com";

        /*Response resp = given().auth().basic(jsonData.getData("UserId"), jsonData.getData("Password")).header("consumer", jsonData.getData("UserId"))
                .header("client_id", jsonData.getData("ClientId")).header("client_secret", jsonData.getData("ClientSecret"))
                .when()
                .then()
                .extract().repsonse();*/
    }
    public void postTaxCalculation(){
        RestAssured.baseURI = "https://extapiqa.winwholesale.com";

       /* Response resp = given().auth().basic(jsonData.getData("UserId"), jsonData.getData("Password")).header("consumer", jsonData.getData("UserId"))
                .header("client_id", jsonData.getData("ClientId")).header("client_secret", jsonData.getData("ClientSecret"))
                .when()
                .then()
                .extract().repsonse(); */
    }
    public void postOrders(){
        RestAssured.baseURI = "https://extapiqa.winwholesale.com";
/*
        Response resp = given().auth().basic(jsonData.getData("UserId"), jsonData.getData("Password")).header("consumer", jsonData.getData("UserId"))
                .header("client_id", jsonData.getData("ClientId")).header("client_secret", jsonData.getData("ClientSecret"))
                .when()
                .then()
                .extract().repsonse();*/
    }
}
