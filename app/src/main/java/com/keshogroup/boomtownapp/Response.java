package com.keshogroup.boomtownapp;

import org.simpleframework.xml.Element;

/**
 * Created by Chris on 8/2/2015.
 */
public class Response {


    @Element(required = false, name = "results")
    Results results;

    @Element(required = false, name = "images")
    Theimages images;

}
