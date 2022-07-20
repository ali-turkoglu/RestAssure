package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;

public class JsonToJavaTest extends SpartanTestBase {

    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void test1(){

        Response response=given()
                .accept(ContentType.JSON)
                .pathParam("id",15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).and().extract().response();

        // GET the json and convert it to the map

        Map<String ,Object> jsonMap=response.as(Map.class);

        System.out.println("jsonMap = " + jsonMap);

        String actualName=(String) jsonMap.get("name");
        assertThat(actualName,is("Meta"));

    }

    @DisplayName("GET all spartan to Java data structure")
    @Test
    public void test2(){

        Response response=get("/api/spartans").then().statusCode(200).extract().response();

        //response.prettyPrint();

        // we need to convert json to java which is deserialized

        List<Map<String ,Object>> jsonList=response.as(List.class);
        //System.out.println("jsonList = " + jsonList);  --> listOfMap

        //System.out.println("jsonList.get(1) = " + jsonList.get(1));  --> map

        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(1).get("name"));


    }

}
