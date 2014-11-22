package com.rsc.idonor.webservices;

import com.rsc.idonor.model.Institution;
import com.rsc.idonor.model.User;

import java.util.List;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public interface WebService {

    public User getUser(String username, String password);
    public User getFacebookUser();
    public User getTwitterUser();

    public boolean postRegisterUser(User user);

    public List<Institution> getAllInstitutions();

}
