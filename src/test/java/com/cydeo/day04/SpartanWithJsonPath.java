package com.cydeo.day04;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class SpartanWithJsonPath extends SpartanTestBase {


    /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */
    @DisplayName("GET one spartan with JsonPath")
    @Test
    public void test1(){

        Response response=given().log().all()
                .and().pathParam("id",10)
                .accept(ContentType.JSON)
                .when().get("api/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        // print name with path method
        System.out.println(response.path("name").toString());

        // assigning response to jsonpath
        JsonPath jsonPath=response.jsonPath();

        int id= jsonPath.getInt("id");
        String name=jsonPath.getString("name");
        String gender=jsonPath.getString("gender");
        long number=jsonPath.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("number = " + number);


    }


}
