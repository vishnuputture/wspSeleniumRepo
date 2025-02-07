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

        Response resp = given().auth().basic(jsonData.getData("UserId"),jsonData.getData("Password"))
                .header("consumer", jsonData.getData("UserId"))
                .header("client_id", jsonData.getData("ClientId"))
                .header("client_secret", jsonData.getData("ClientSecret"))
                .when().get("/marketplace/exp/v1/api/" + jsonData.getData("UserID") + "/customer/wiseAccounts?accountNumber=007222&companyNumber=00155")
                .then().assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK")
                .body("fullName[0]", equalTo(jsonData.getData("FullName")))
                .body("companyNumber[0]", equalTo(jsonData.getData("CompanyId")))
                .header("Content-Type", "application/json; charset=UTF-8").extract().response();
    }
    public void getLocation(){
        RestAssured.baseURI = "https://extapiqa.winwholesale.com";

        Response resp = given().auth().basic(jsonData.getData("UserId"),jsonData.getData("Password"))
                .header("consumer", jsonData.getData("UserId"))
                .header("client_id", jsonData.getData("ClientId"))
                .header("client_secret", jsonData.getData("ClientSecret"))
                .when().get("/marketplace/exp/v1/api/" + jsonData.getData("UserID") + "/locations/" + jsonData.getData("CompanyId"))
                .then().assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK")
                .body("localCompanyID", equalTo(jsonData.getData("CompanyId")))
                .body("localCompanyName", equalTo(jsonData.getData("LocalCompanyName")))
                .body("presidentFormalName", equalTo(jsonData.getData("PresidentName")))
                .body("prospectAccount", equalTo(jsonData.getData("ProspectAccount"))).extract().response();
    }

    public void postInventoryAvailability(){
        RestAssured.baseURI = "http://mule4qa1:8091";

        Response resp = given().auth().basic(jsonData.getData("UserId"), jsonData.getData("Password"))
                .header("consumer", jsonData.getData("UserId"))
                .header("client_id", jsonData.getData("ClientId"))
                .header("client_secret", jsonData.getData("ClientSecret"))
                .header("Content-Type", "application/json")
                .body(jsonData.getData("PayloadBody"))
                .when().post("/marketplace/exp/v1/api/" + jsonData.getData("UserId") + "/inventoryAvailabilities")
                .then().assertThat().statusCode(201).statusLine("HTTP/1.1 201 Created")
                .body("meta.count", equalTo(Integer.parseInt(jsonData.getData("Count"))))
                .body("data[0].locationQuantities[0].companyNumber", equalTo(jsonData.getData("CompanyId")))
                .body("data[1].itemNumber", equalTo(jsonData.getData("ItemNumber")))
                .body("data[2].locationQuantities[0].availableQuantity", equalTo(Integer.parseInt(jsonData.getData("Quantity1"))))
                .body("data[2].locationQuantities[1].availableQuantity", equalTo(Integer.parseInt(jsonData.getData("Quantity2"))))
                .body("data[3].locationQuantities[0].companyName", equalTo(jsonData.getData("Company1")))
                .body("data[3].locationQuantities[1].companyName", equalTo(jsonData.getData("Company2"))).extract().response();

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
        RestAssured.baseURI = "http://mule4qa1:8091";

        Response resp = given().auth().basic(jsonData.getData("UserId"), jsonData.getData("Password"))
                .header("consumer", jsonData.getData("UserId"))
                .header("client_id", jsonData.getData("ClientId"))
                .header("client_secret", jsonData.getData("ClientSecret"))
                .header("Content-Type", "application/json")
                .body(jsonData.getData("PayloadBody"))
                .when().post("/marketplace/exp/v1/api/" + jsonData.getData("UserId") + "/items/pricing")
                .then().assertThat().statusCode(201).statusLine("HTTP/1.1 201 Created")
                .body("companyNumber", equalTo(jsonData.getData("CompanyId")))
                .body("customerNumber", equalTo(jsonData.getData("CustomerId")))
                .body("productPrices[2].productCode", equalTo(jsonData.getData("Product")))
                .body("productPrices[4].quantityBreaks[0].quantity", equalTo(Integer.parseInt(jsonData.getData("Quantity"))))
                .body("productPrices[5].quantityBreaks[0].price", equalTo(Float.parseFloat(jsonData.getData("Price"))))
                .body("productPrices[9].quantityBreaks[1].priceSource", equalTo(jsonData.getData("PriceSource")))
                .body("productPrices[14].quantityBreaks[0].minSellingQuantity", equalTo(Integer.parseInt(jsonData.getData("MinQuantity"))))
                .body("productPrices[17].quantityBreaks[0].sellingUom", equalTo(jsonData.getData("UOM"))).extract().response();

        if (resp.statusCode() == 201) {
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Body '" + resp.getBody().asString(), Status.PASS);
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Code '" + resp.getStatusCode() + "' is matching With Response Code '" + 201 + "'",
                    Status.PASS);
        } else {
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Code '" + resp.getStatusCode() + "' is not matching With Response Code '" + 201 + "'",
                    Status.FAIL);
        }
    }
    public void postTaxCalculation(){
        RestAssured.baseURI = "https://extapiqa.winwholesale.com";

        Response resp = given().auth().basic(jsonData.getData("UserId"), jsonData.getData("Password"))
                .header("consumer", jsonData.getData("UserId"))
                .header("client_id", jsonData.getData("ClientId"))
                .header("client_secret", jsonData.getData("ClientSecret"))
                .header("Content-Type", "application/xml")
                .body(jsonData.getData("PayloadBody"))
                .when().post("/marketplace/exp/v1/api/" + jsonData.getData("UserId") + "/taxCalculations")
                .then().assertThat().statusCode(201).statusLine("HTTP/1.1 201 Created")
                .header("Content-Type", "text/xml; charset=UTF-8")
                .body("Envelope.Body.VertexEnvelope.Login.UserName", equalTo(jsonData.getData("Username")))
                .body("Envelope.Body.VertexEnvelope.QuotationResponse.Seller.Division", equalTo(jsonData.getData("Seller")))
                .body("Envelope.Body.VertexEnvelope.QuotationResponse.Seller.PhysicalOrigin.City", equalTo(jsonData.getData("City")))
                .body("Envelope.Body.VertexEnvelope.QuotationResponse.Customer.CustomerCode", equalTo(jsonData.getData("Customer")))
                .body("Envelope.Body.VertexEnvelope.QuotationResponse.SubTotal", equalTo(jsonData.getData("Subtotal")))
                .body("Envelope.Body.VertexEnvelope.QuotationResponse.LineItem.Product", equalTo(jsonData.getData("Product")))
                .extract().response();

        if (resp.statusCode() == 201) {
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Body '" + resp.getBody().asString(), Status.PASS);
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Code '" + resp.getStatusCode() + "' is matching With Response Code '" + 201 + "'",
                    Status.PASS);
        } else {
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Code '" + resp.getStatusCode() + "' is not matching With Response Code '" + 201 + "'",
                    Status.FAIL);
        }
    }
    public void postOrders(){
        RestAssured.baseURI = "https://extapiqa.winwholesale.com";

        Response resp = given().auth().basic(jsonData.getData("UserId"), jsonData.getData("Password"))
                .header("consumer", jsonData.getData("UserId"))
                .header("client_id", jsonData.getData("ClientId"))
                .header("client_secret", jsonData.getData("ClientSecret"))
                .header("Content-Type", "application/json")
                .body(jsonData.getData("PayloadBody"))
                .when().post("/marketplace/exp/v1/api/" + jsonData.getData("UserId") + "/orders")
                .then().assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK")
                .body("message", equalTo("Order published successfully"))
                .extract().response();

        if (resp.statusCode() == 200) {
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Body '" + resp.getBody().asString(), Status.PASS);
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Code '" + resp.getStatusCode() + "' is matching With Response Code '" + 200 + "'",
                    Status.PASS);
        } else {
            report.updateTestLogAPI("VerifyVal", "" + " Expected Response Code '" + resp.getStatusCode() + "' is not matching With Response Code '" + 200 + "'",
                    Status.FAIL);
        }
    }
}
