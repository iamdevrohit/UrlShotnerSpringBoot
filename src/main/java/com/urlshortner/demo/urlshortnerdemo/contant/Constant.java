package com.urlshortner.demo.urlshortnerdemo.contant;

public class Constant {

    static final public String API_MAPPER="/API/urlshortner";

    static public String REDIS_HOST="localhost";

    static public int REDIS_PORT=6379;

    public static final String[] PERMITTED_APIS_LIST={API_MAPPER+"/user_register",API_MAPPER+"/user_login",API_MAPPER+"/"};

}
