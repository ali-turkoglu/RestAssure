package com.cydeo.day04;

import com.cydeo.utilities.HrTestBase;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init(){
        baseURI="http://api.cybertektraining.com";
        //http://api.cybertektraining.com/teacher/{id}
    }

    @DisplayName("GET request to individuals student")
    @Test
    public void test(){

        //send a get request to student id 23401 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606

                using JsonPath
             */
        Response response=given()
                .pathParam("id",23401)
                .accept(ContentType.JSON)
                .when().get("students/{id}");

        JsonPath jsonPath=response.jsonPath();

        response.prettyPrint();

    }



}
