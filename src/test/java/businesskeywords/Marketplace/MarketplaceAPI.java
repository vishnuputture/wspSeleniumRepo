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
}
