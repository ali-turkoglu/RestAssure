package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init(){

        //save baseurl inside this variable so that we dont need to type each http method.
        RestAssured.baseURI="http://3.87.215.11:8000";
    }

 /*TASK
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
    */

    @DisplayName("Get request to /api/spartans/10 end point")
    @Test
    public void test1(){

        Response response=given().accept(ContentType.XML).get("/api/spartans/10");

        assertEquals(406,response.statusCode());

        assertEquals("application/xml;charset=UTF-8",response.contentType());



    }
}
