package com.keshogroup.boomtownapp;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by Chris on 8/2/2015.
 */
@Root(name = "searchresults", strict = false)
//@Namespace( reference = "http://www.zillow.com/static/xsd/SearchResults.xsd http://www.zillowstatic.com/vstatic/85c1a99/static/xsd/SearchResults.xsd")
//@Default(required = false,value = DefaultType.PROPERTY)
public class Searchresults {


    // @Element(name = "zpid")
    // public Integer zpid;

    @Element(required = false, name = "message")
    public Object message;

    @Element(required = false, name = "request")
    public Object request;

    @Element(required = false, name = "response")
    public Response response;

    //public Response response;


    //@Element(required = false, name = "results")
    //public String results;




    @Element(required = false, name = "citystatezip")
    public String citystatezip;

    @Element(required = false, name = "latitude")
    public Double latitude;


    public Searchresults() {}
}//end of task

