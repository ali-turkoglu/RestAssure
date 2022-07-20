package com.cydeo.day06;

import com.cydeo.day06.pojo.Search;
import com.cydeo.day06.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("GET one spartan and convert it to Spartan Object")
    @Test
    public void oneSpartanPojo() {

        Response response = given()
                .accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .extract().response();

        // Deserialization json to Pojo

        // 1. using as() method
        Spartan spartan15 = response.as(Spartan.class);

        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());
        System.out.println("spartan15.getPhone() = " + spartan15.getPhone());


        System.out.println("    =====================================================");
        // 2.way of deserialization json to java
        JsonPath jsonPath=response.jsonPath();

        Spartan s15 = jsonPath.getObject("", Spartan.class);

        System.out.println("s15.getId() = " + s15.getId());
        System.out.println("s15.getName() = " + s15.getName());
        System.out.println("s15.getGender() = " + s15.getGender());
        System.out.println("s15.getPhone() = " + s15.getPhone());

    }

    @DisplayName("GET one spartan from search endpoint result and use POJO")
    @Test
    public void spartanSearchWithPojo(){

        // spartan/search?nameContains=a&gender=Male
        // send get request to above endpoint and save first object with type SpartanPOJO
        JsonPath jsonPath=given()
                .accept(ContentType.JSON)
                .queryParams("nameContains","a","gender","Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();

        Spartan firstSpartan = jsonPath.getObject("content[0]", Spartan.class);

        System.out.println("firstSpartan = " + firstSpartan);

        System.out.println("firstSpartan.getName() = " + firstSpartan.getName());

    }

    @Test
    public void test3(){

        Response response=given()
                .accept(ContentType.JSON)
                .queryParams("nameContains","a","gender","Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        Search searchResult = response.as(Search.class);

        System.out.println("searchResult = " + searchResult);

        System.out.println("searchResult.getContent().size() = " + searchResult.getContent().size());

    }

    @DisplayName("GET /spartans/search and save as List<Spartan>")
    @Test
    public void test4(){

        Response response=given()
                .accept(ContentType.JSON)
                .queryParams("nameContains","a","gender","Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        List<Spartan> list = response.jsonPath().getList("content", Spartan.class);

        System.out.println("list.get(1).getName() = " + list.get(1).getName());

    }


}
