package com.cydeo.day04;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class ORDSApiTestWithPath extends HrTestBase {


    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1(){

        Response response=given()
                .accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(200,response.statusCode());

        // print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        // print hasmore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        // print first CountryId
        System.out.println("response.path(\"country_id\") = " + response.path("items[0].country_id"));

        // print second Country name
        System.out.println("response.path(\"country_name\") = " + response.path("items[4].country_name"));

        // print "http://3.87.215.11:1000/ords/hr/countries/CA"
        System.out.println("response.path(\"items[2].links[0].href\") = " + response.path("items[2].links[0].href"));
        
        // get me all country names
       List<String> countryNames=response.path("items.country_name");

        for (String each : countryNames) {
            System.out.println(each);
        }

    }


}
