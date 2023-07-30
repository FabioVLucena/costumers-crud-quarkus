package br.com.aeon.controller;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.com.aeon.entity.Costumer;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@QuarkusTest
@Tag("Integration Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CostumerControllerTest {

	@Test
	@Order(1)
	void shouldNotAddCostumerWithParamsNull() {
		JsonObject jsonObject = 
				Json.createObjectBuilder()
					.build();
		
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(jsonObject.toString())
			.when()
			.post("/api/costumers")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	@Order(1)
	void shouldNotAddCostumerWithParamsEmpty() {
		JsonObject jsonObject = 
				Json.createObjectBuilder()
					.add("name", "")
					.add("lastName", "")
					.add("age", "")
					.add("email", "")
					.build();
		
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(jsonObject.toString())
			.when()
			.post("/api/costumers")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	@Order(1)
	void shouldNotAddCostumerWithParamsLess3Caracters() {
		JsonObject jsonObject = 
				Json.createObjectBuilder()
					.add("name", "fi")
					.add("lastName", "co")
					.add("age", 22)
					.add("email", "costumer-email@gmail.com")
					.build();
		
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(jsonObject.toString())
			.when()
			.post("/api/costumers")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	@Order(1)
	void shouldNotAddCostumerWithInvalidEmail() {
		JsonObject jsonObject = 
				Json.createObjectBuilder()
					.add("name", "first")
					.add("lastName", "costumer")
					.add("age", 22)
					.add("email", "invalidemail")
					.build();
		
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(jsonObject.toString())
			.when()
			.post("/api/costumers")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	@Order(1)
	void shouldCreateCostumer() {
		JsonObject jsonObject = 
				Json.createObjectBuilder()
					.add("name", "first")
					.add("lastName", "costumer")
					.add("age", 22)
					.add("email", "costumer@gmail.com")
					.build();
		
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(jsonObject.toString())
			.when()
			.post("/api/costumers")
			.then()
			.statusCode(Status.CREATED.getStatusCode());
	}

	@Test
	@Order(2)
	void getAllCostumers() {
		Costumer[] costumerArr = given()
			.when()
			.get("/api/costumers")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.extract()
			.as(Costumer[].class);
		
		assertEquals(1, costumerArr.length);
		assertEquals("first", costumerArr[0].getName());
		assertEquals("costumer", costumerArr[0].getLastName());
		assertEquals(22, costumerArr[0].getAge());
		assertEquals("costumer@gmail.com", costumerArr[0].getEmail());
	}
	
	@Test
	@Order(2)
	void shouldNotUpdateCostumerWithParamsNull() {
		JsonObject jsonObject =
				Json.createObjectBuilder()
					.build();
		
		given()
		    .contentType(MediaType.APPLICATION_JSON)
		    .body(jsonObject.toString())
			.when()
			.put("/api/costumers/1")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}
	
	@Test
	@Order(2)
	void shouldNotUpdateCostumerWithParamsEmpty() {
		JsonObject jsonObject =
				Json.createObjectBuilder()
					.add("name", "")
					.add("lastName", "")
					.add("age", "")
					.add("email", "")
					.build();
		
		given()
		    .contentType(MediaType.APPLICATION_JSON)
		    .body(jsonObject.toString())
			.when()
			.put("/api/costumers/1")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}
	
	@Test
	@Order(2)
	void shouldNotUpdateCostumerWithParamsLess3Caracters() {
		JsonObject jsonObject =
				Json.createObjectBuilder()
					.add("name", "fi")
					.add("lastName", "co")
					.add("age", 44)
					.add("email", "costumer-new-email@gmail.com")
					.build();
		
		given()
		    .contentType(MediaType.APPLICATION_JSON)
		    .body(jsonObject.toString())
			.when()
			.put("/api/costumers/1")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}
	
	@Test
	@Order(2)
	void shouldNotUpdateCostumerWithInvalidEmail() {
		JsonObject jsonObject =
				Json.createObjectBuilder()
					.add("name", "first mod")
					.add("lastName", "costumer mod")
					.add("age", 44)
					.add("email", "costumerinvalidemail@gmail@.com")
					.build();
		
		given()
		    .contentType(MediaType.APPLICATION_JSON)
		    .body(jsonObject.toString())
			.when()
			.put("/api/costumers/1")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}
	
	@Test
	@Order(2)
	void shouldUpdateCostumer() {
		JsonObject jsonObject =
				Json.createObjectBuilder()
					.add("name", "first mod")
					.add("lastName", "costumer mod")
					.add("age", 44)
					.add("email", "costumer-new-email@gmail.com")
					.build();
		
		Costumer costumer = given()
		    .contentType(MediaType.APPLICATION_JSON)
		    .body(jsonObject.toString())
			.when()
			.put("/api/costumers/1")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.extract()
			.as(Costumer.class);
		
		assertEquals("first mod", costumer.getName());
		assertEquals("costumer mod", costumer.getLastName());
		assertEquals(44, costumer.getAge());
		assertEquals("costumer-new-email@gmail.com", costumer.getEmail());
	}

	@Test
	@Order(3)
	void shouldDeleteCostumer() {
		given()
			.when()
			.delete("/api/costumers/1")
			.then()
			.statusCode(Status.NO_CONTENT.getStatusCode());
	}
	
}
