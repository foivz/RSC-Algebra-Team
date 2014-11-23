package com.rsc.idonor.webservices;

import com.rsc.idonor.model.Institution;
import com.rsc.idonor.model.User;

import java.util.List;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public interface WebService {

    public void getUser(String username, String password, WebServiceResponse callback);

    public void postRegisterUser(User user, WebServiceResponse callback);


}
