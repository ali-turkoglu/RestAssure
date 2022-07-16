package com.cydeo.day03;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithParameters extends HrTestBase {

    /*//BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init() {

        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://3.87.215.11:1000/ords/hr";
    }
*/

    /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */
    @DisplayName("GET request to  /countries with Query Param")
    @Test
    public void test1(){

        Response response=given()
                .log().all()
                .accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("America"));

    }

     /*
        Send a GET request to employees and get only employees who works as a IT_PROG

     */
    @DisplayName("GET request to employees in IT_PROG")
    @Test
    public void test2(){

        Response response=given().log().all()
                .and().queryParam("job_id","IT_PROG")
                .accept(ContentType.JSON)
                .when().get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("IT_PROG"));


    }

}
