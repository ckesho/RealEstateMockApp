package com.keshogroup.boomtownapp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Chris on 8/3/2015.
 */
public class Theimages {
    //@Element(required = false, name = "image")
    //Theimage image;

    @ElementList(required = false, name = "image")
    List<String> image;
}
