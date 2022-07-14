package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequests {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init(){

        //save baseurl inside this variable so that we dont need to type each http method.
        RestAssured.baseURI="http://3.87.215.11:1000/ords/hr";

    }

    @Test
    public void test1(){

        Response response=RestAssured.get("/regions");

        System.out.println(response.statusCode());
    }

     /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains   Americas
     */
    @DisplayName("Get request to regions/2")
    @Test
    public void test2(){

        Response response=given().accept("application/json").when().get("/regions/2");

        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.contentType());

        Assertions.assertTrue(response.body().asString().contains("Americas"));


    }

}
