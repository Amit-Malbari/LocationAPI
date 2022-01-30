package helper;

import java.util.ArrayList;
import java.util.List;

import constant.EndPoints;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import model.AddPlace;
import model.GetPlace;
import model.Location;
import utils.Config;

public class LocationHelper {
	
	// these class conatins functions that will fetch data from endpoints
	//GET/POST/PATC/DELETE/PUT
	// Read data from config variables
	// Rest Assured about URL and port
	// Make getRequest on this url and send data to TestGET.java
	
	private static final String BASE_URL=Config.getInstance().getValue("baseURL");
	//private static final Integer PORT=Integer.parseInt(Config.getInstance().getValue("port"));
	RequestSpecification req;
	ResponseSpecification resspec;
	public LocationHelper(){
		RestAssured.baseURI=BASE_URL;
		req =new RequestSpecBuilder()
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON)
				.build();
		resspec =new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.build();
		//RestAssured.port=PORT;
		//RestAssured.useRelaxedHTTPSValidation();
	}
	
	
	public Response getLocation(String place_id){
		//Response response=RestAssured.given().contentType(ContentType.JSON).get("http://localhost:3000/persons").andReturn();
		RequestSpecification res=RestAssured
				.given()
				.spec(req)
				.queryParam("place_id", place_id);
		Response response=res.when().get(EndPoints.GET_LOCATION).then().spec(resspec).extract().response();
		GetPlace place=response.as(GetPlace.class);
		return response;
	}
	
	public Response createPlace() {
		AddPlace p =new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline house");
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		 
		
		RequestSpecification res=RestAssured.given().spec(req)
		.body(p);
		Response response =res.when().post(EndPoints.CREATE_LOCATION).
				then().spec(resspec).extract().response();
		return response;
	}
	
	public Response updateLocation(String place_id) {
		AddPlace p =new AddPlace();
		p.setAddress("2551, side layout, cohen 09");
		p.setPlace_id(place_id);
		p.setKey("qaclick123");
		RequestSpecification res=RestAssured.given().spec(req)
				.queryParam("place_id", place_id)
				.body(p);
		Response response=res.when().put(EndPoints.UPDATE_LOCATION).then().spec(resspec).extract().response();
		return response;
	}
	
	public Response delete(String place_id) {
		AddPlace p =new AddPlace();
		p.setPlace_id(place_id);
		RequestSpecification res=RestAssured.given().spec(req)
				.body(p);
		Response response=res.when().delete(EndPoints.DELETE_LOCATION).then().spec(resspec).extract().response();
		return response;
	}

}
