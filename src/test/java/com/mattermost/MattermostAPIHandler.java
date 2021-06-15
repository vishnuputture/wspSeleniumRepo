package com.mattermost;

import com.winSupply.framework.Settings;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.*;

public class MattermostAPIHandler {

	public static Properties properties = Settings.getInstance();
	public static String accessToken;
	private static String mattermosturl;

	/**
	 * Get login token from Mattermost
	 * 
	 * @return
	 */
	public static void getloginToken(String username, String password) {
		if (accessToken == null) {
			mattermosturl = properties.getProperty("Mattermost_URL");
			JSONObject bod = new JSONObject();
			bod.put("login_id", username);
			bod.put("password", password);
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			request.body(bod.toJSONString());
			Response res = request.post(mattermosturl + "/users/login");
			int code = res.getStatusCode();
			Assert.assertEquals(code, 200);
			System.out.println("Connected established with the Mattermost Server...");
			Headers header = res.getHeaders();
			accessToken = header.getValue("Token");


		}
	}

	/**
	 * Post a message to the mattermost channel
	 * 
	 * @return
	 */
	public static void postMessage(String msg) {
		String channel_id = properties.getProperty("Mattermost_channelID");
		postMessage(channel_id, msg);
	}

	/**
	 * Post a message to the mattermost channel
	 *
	 * @return
	 */
	public static void postMessage(String channel_id, String msg) {
		getloginToken(properties.getProperty("Mattermost_user"), properties.getProperty("Mattermost_password"));
		JSONObject bod = new JSONObject();
		bod.put("channel_id", channel_id);
		bod.put("message", msg);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer " + accessToken);
		request.body(bod.toJSONString());
		Response res = request.post(mattermosturl + "/posts");
		int code = res.getStatusCode();
		Assert.assertEquals(code, 201);
	}

	public static String getChannelIDByName(String channel_name) {
		String channel_id = "";
		getloginToken(properties.getProperty("Mattermost_user"), properties.getProperty("Mattermost_password"));
		String teamName = properties.getProperty("Mattermost_team_name");
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer " + accessToken);
		Response res = request.get(mattermosturl + "/teams/name/" + teamName + "/channels/name/" + channel_name);
		int code = res.getStatusCode();
		Assert.assertEquals(code, 200);
		JsonPath jsonResponse = res.jsonPath();
		channel_id = jsonResponse.get("id");
		return channel_id;
	}

}
