package com.rsc.idonor.webservices;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class Request {

    public enum Method {
        GET, POST
    }

    private Method method;
    private String url;
    private String postParameters;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPostParameters() {
        return postParameters;
    }

    public void setPostParameters(String postParameters) {
        this.postParameters = postParameters;
    }

}
