package com.cydeo.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithPath {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init() {

        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://3.87.215.11:8000";
    }

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
    @DisplayName("GET one spartan with Path Method")
    @Test
    public void test1(){

        Map<String ,Object> queryMap=new HashMap<>();
        queryMap.put("name","Lorenza");
        queryMap.put("gender","Female");
        queryMap.put("phone","3312820936");

        Response response=given().log().all()
                .and().pathParam("id",10)
                .and().queryParams(queryMap)
                .accept(ContentType.JSON)
                .when().get("api/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        int actualID=response.path("id");
        String actualName=response.path("name");
        long actualPhone=response.path("phone");

        assertEquals(10,actualID);
        assertEquals("Lorenza",actualName);
        assertEquals(3312820936l,actualPhone);

    }

    @DisplayName("GET all spartan and navigate with Path()")
    @Test
    public void test2(){

        Response response=given().log().all()
                .accept(ContentType.JSON)
                .when().get("/api/spartans");

        response.prettyPrint();

        int firstID=response.path("id[0]");
        System.out.println("firstID = " + firstID);

        int lastID=response.path("id[-1]");
        System.out.println("lastID = " + lastID);

        String lastFirstName=response.path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);

        List<String > names=response.path("name");
        System.out.println(names);

        for (String each : names) {
            System.out.println(each);
        }


    }


}
