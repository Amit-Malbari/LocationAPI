package location;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import helper.LocationHelper;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import model.GetPlace;

public class AddLocation {
	private LocationHelper locationHelper;
	private String place_id;
	private Response response;
	@BeforeClass
	public void beforeClass() {
		locationHelper=new LocationHelper();
		
	}
	
	@Test
	public void createPlace() {
		response=locationHelper.createPlace();
		place_id=response.jsonPath().getString("place_id");
		assertEquals(200, response.statusCode());
	}
	
	@Test(dependsOnMethods = "createPlace")
	public void updatePlace() {
		response=locationHelper.updateLocation(place_id);
		assertEquals(200, response.statusCode());
	}
	
	@Test(dependsOnMethods = "updatePlace")
	public void getPlace() {
		response=locationHelper.getLocation(place_id);
		assertEquals(200, response.statusCode());
	}
	
	@Test(dependsOnMethods = "getPlace")
	public void delete() {
		response=locationHelper.delete(place_id);
		assertEquals(200, response.statusCode());
	}
	
	@Test(dependsOnMethods = "delete")
	public void getDeletedPlace() {
		response=locationHelper.getLocation(place_id);
		assertEquals(404, response.statusCode());
	}
	
	@Test(dependsOnMethods = "getDeletedPlace")
	public void updateDeletedPlace() {
		response=locationHelper.updateLocation(place_id);
		assertEquals(404, response.statusCode());
	}
}
