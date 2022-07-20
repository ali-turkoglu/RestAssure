package com.cydeo.day05;

import com.cydeo.utilities.DBUtils;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;

import java.util.Map;

public class SpartanAPIvsDB extends SpartanTestBase {

    @DisplayName("GET one spartan from api and database")
    @Test
    public void testDB1(){

        //get id,name,gender phone  from database
        String query="SELECT spartan_id,name,gender,phone\n" +
                "FROM spartans \n" +
                "where spartan_id=15";

        // save data inside to map
        Map<String, Object> dbMap = DBUtils.getRowMap(query);

        System.out.println("dbMap = " + dbMap);

        //get same information from api
        Map<String ,Object> apiMap=given()
                                        .accept(ContentType.JSON)
                                        .pathParam("id",15)
                                    .when()
                                        .get("/api/spartans/{id}")
                                    .then()
                                        .statusCode(200)
                                    .extract().as(Map.class);

        System.out.println("apiMap = " + apiMap);

        //compare
        assertThat(apiMap.get("id").toString(),is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"),is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"),is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(),is(dbMap.get("PHONE").toString()));


    }


}
