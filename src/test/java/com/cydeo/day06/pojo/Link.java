package com.cydeo.day06.pojo;
/*
      "links": [
              {
              "rel": "self",
              "href": "http://3.87.215.11:1000/ords/hr/regions/1"
              }
*/
public class Link {

    private String rel;
    public String href;

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Link{" +
                "rel='" + rel + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
