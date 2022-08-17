package businesskeywords.Marketplace;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import commonkeywords.DBCall;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MarketplaceAPI extends ReusableLib {
    String response = "";
    public MarketplaceAPI(Helper helper) {
        super(helper);
    }

}
