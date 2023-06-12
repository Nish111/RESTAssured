package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GETAndPOSTExamples {

	//@Test
	public void testGet() {
		baseURI = "https://reqres.in/api";
		given().
			get("/users?page=2").
		then().
			statusCode(200).
		body("data[2].first_name", equalTo("Tobias"))
				.body("data.last_name", hasItems("Funke", "Ferguson"));
		
	}
	@Test
	public void testPost() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Nishant");
		map.put("job", "Developer");
		System.out.println(map);
		JSONObject request = new JSONObject(map);
		System.out.println(request);
		System.out.println(request.toJSONString());
		
		JSONObject req = new JSONObject(map);
		req.put("name", "Isagi");
		req.put("job", "Score Goals");
		System.out.println(req);
		System.out.println(req.toJSONString());
		
		baseURI = "https://reqres.in/api";
		
		given()
		.header("Content-Type", "applicatoon/JSON")
		.contentType(ContentType.JSON)
			.body(req.toJSONString())
		.when()
			.post("/users")
		.then().statusCode(201)
		.log().all();
		// 201 for successful creation
		
	}

}
