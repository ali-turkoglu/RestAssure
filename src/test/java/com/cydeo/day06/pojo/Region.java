package com.cydeo.day06.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/*
    "items": [
        {
            "region_id": 1,
            "region_name": "Europe",
            "links": [
                {
                    "rel": "self",
                    "href": "http://3.87.215.11:1000/ords/hr/regions/1"
                }
            ]
        },
 */
@Data
public class Region {

    //if your jsonkey and variable name not matching, you can map it with jsonProperty
    @JsonProperty("region_id")
    private int rId;

    @JsonProperty("region_name")
    private String rName;

    @JsonProperty("links")
    private List<Link> links;

}
