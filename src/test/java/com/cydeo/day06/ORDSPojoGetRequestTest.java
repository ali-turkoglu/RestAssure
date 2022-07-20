package com.cydeo.day06;

import com.cydeo.day06.pojo.Region;
import com.cydeo.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;

public class ORDSPojoGetRequestTest extends HrTestBase {

    @Test
    public void regionTest(){

        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println("region1 = " + region1);

        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());

        List<Region> regions=jsonPath.getList("items");

    }

}
