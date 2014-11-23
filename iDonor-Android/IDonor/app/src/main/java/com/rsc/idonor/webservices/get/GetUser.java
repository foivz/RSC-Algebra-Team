package com.rsc.idonor.webservices.get;

import com.google.gson.annotations.SerializedName;
import com.rsc.idonor.model.User;
import com.rsc.idonor.webservices.Response;

import java.io.Serializable;

/**
 * Created by darkosmoljo on 23/11/14.
 */
public class GetUser extends Response implements Serializable {

    @SerializedName("response")
    private User user;

    public User getUser() {
        return user;
    }
}
