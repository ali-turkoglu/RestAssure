package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;

public class SpartanHamcrestTest extends SpartanTestBase {

    @DisplayName("GET spartan/search and chaining together")
    @Test
    public void test1(){

        List<String > nameList=given()
                .accept(ContentType.JSON)
                .queryParams("nameContains","j",
                                        "gender","Male")
        .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .body("totalElement",equalTo(3))
                .extract().response().jsonPath().getList("content.name");

        System.out.println(nameList);

    }

    @DisplayName("GET spartan/search and chaining together")
    @Test
    public void test2(){

        int statusCode=given()
                .accept(ContentType.JSON)
                .queryParams("nameContains","j",
                        "gender","Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .body("totalElement",equalTo(3))
                .extract().statusCode();

        System.out.println(statusCode);

    }

}
