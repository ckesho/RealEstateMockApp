package com.keshogroup.boomtownapp;

/**
 * Created by Chris on 8/3/2015.
 */
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by Chris on 8/2/2015.
 */
@Root(name = "pictureresults", strict = false)
public class Pictureresults {

    @Element(required = false, name = "message")
    public Object message;

    @Element(required = false, name = "request")
    public Object request;

    @Element(required = false, name = "response")
    public Response response;


    public Pictureresults() {}
}//end of task
