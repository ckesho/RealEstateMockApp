package com.keshogroup.boomtownapp;

import org.simpleframework.xml.Element;

/**
 * Created by Chris on 8/2/2015.
 */
public class Result{

    @Element(required = false, name = "zpid")
    public String zpid;
}
