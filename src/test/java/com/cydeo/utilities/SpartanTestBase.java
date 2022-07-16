package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    //BeforeAll is an annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init() {

        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = ConfigurationReader.getProperty("spartan.url");

        String dbUrl = ConfigurationReader.getProperty("spartan.dbUrl");
        String dbUsername = "SP";
        String dbPassword = "SP";

        //DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }

    @AfterAll
    public static void teardown(){

        //DBUtils.destroy();
    }




}
