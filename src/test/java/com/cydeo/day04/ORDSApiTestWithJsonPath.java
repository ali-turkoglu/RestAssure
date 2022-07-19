package com.cydeo.day04;

import com.cydeo.utilities.HrTestBase;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class ORDSApiTestWithJsonPath extends HrTestBase {



    @DisplayName("GET request to Countries")
    @Test
    public void test1(){

        Response response=get("/countries");

        // get the second country name with JsonPath
        JsonPath jsonPath=response.jsonPath();

        String  secondCountry = jsonPath.get("items[1].name");
        System.out.println("secondCountry = " + secondCountry);

        //get all country id
        List<String > allCountryIDs=jsonPath.getList("items.country_id");
        System.out.println("allCountryIDs = " + allCountryIDs);

        // get all country names where their region id is equal to 2
        List<String> id2Countries=jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("id2Countries = " + id2Countries);

    }


    @DisplayName("GET request to employees with query param")
    @Test
    public void test2(){

        // we added limit query param to get 107 employees
        Response response= given()
                .queryParam("limit",107)
                .accept(ContentType.JSON)
                .when().get("/employees");

        // get me all email of employees who is working as IT_PROG
        JsonPath jsonPath=response.jsonPath();

        List<String > emailListOfIT_PROG=jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.email");
        System.out.println("emailListOfIT_PROG = " + emailListOfIT_PROG);

        //get me first name of employees who is making more than 10000
        List<String > namesOfEmpEarnMoreThan10k=jsonPath.getList("items.findAll{it.salary>10000}.first_name");
        System.out.println("namesOfEmpEarnMoreThan10k = " + namesOfEmpEarnMoreThan10k);
        System.out.println("namesOfEmpEarnMoreThan10k.size() = " + namesOfEmpEarnMoreThan10k.size());

    }
}
