package com.rsc.idonor.webservices;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class WebServiceManager {

    private static WebServiceManager instance;

    public WebServiceManager getInstance() {
        if (instance == null) {
            instance = new WebServiceManager();
        }

        return instance;
    }



}
