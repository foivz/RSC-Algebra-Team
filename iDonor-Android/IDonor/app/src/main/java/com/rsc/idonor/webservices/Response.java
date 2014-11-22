package com.rsc.idonor.webservices;

import com.google.gson.annotations.SerializedName;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class Response {


    public static final int RESPONSE_STATUS_OK = 200;
    public static final int RESPONSE_STATUS_SERVER_ERROR = 500;

    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
