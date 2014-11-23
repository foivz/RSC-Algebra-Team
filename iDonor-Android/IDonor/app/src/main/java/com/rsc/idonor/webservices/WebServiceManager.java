package com.rsc.idonor.webservices;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rsc.idonor.model.User;
import com.rsc.idonor.utils.Utils;
import com.rsc.idonor.webservices.get.GetUser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class WebServiceManager implements WebService{

    private static final String TAG = "WebServiceManager";


    private static WebServiceManager instance;

    public WebServiceManager getInstance() {
        if (instance == null) {
            instance = new WebServiceManager();
        }

        return instance;
    }
    private Gson gson;

    private WebServiceManager() {
        this.gson = new GsonBuilder().create();
    }

    private class AsyncCall extends AsyncTask<Object, Void, Void> {

        private Request mRequest;
        private Class mTypeOf;
        private WebServiceResponse mCallback;
        private Gson mGson;

        public AsyncCall(Request request, Class typeOf, WebServiceResponse callback) {
            this.mRequest = request;
            this.mTypeOf = typeOf;
            this.mCallback = callback;
            this.mGson = new GsonBuilder().create();
        }

        @Override
        protected Object doInBackground(Object... voids) {
            Log.i(TAG, "call on: " + mRequest.getUrl());

            Object response = null;
            HttpClient httpClient = null;
            String jsonResponse = null;

            try {

                httpClient = new DefaultHttpClient();

                if (mRequest.getMethod() == Request.Method.GET) {

                    HttpGet get = new HttpGet(mRequest.getUrl());

                    HttpResponse httpResponse = httpClient.execute(get);
                    InputStream is = httpResponse.getEntity().getContent();
                    jsonResponse = Utils.convertStreamToString(is);

                } else {

                    HttpPost post = new HttpPost(mRequest.getUrl());

                    Log.d(TAG, "POST parameters: " + mRequest.getPostParameters());

                    post.setEntity(new StringEntity(mRequest.getPostParameters(), "UTF8"));
                    post.setHeader("Content-type", "application/json");

                    HttpResponse httpResponse = httpClient.execute(post);
                    InputStream is = httpResponse.getEntity().getContent();
                    jsonResponse = Utils.convertStreamToString(is);

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (httpClient != null && httpClient.getConnectionManager() != null) {
                    httpClient.getConnectionManager().shutdown();
                }
            }

            try {
                if (jsonResponse != null) {

                    Log.d(TAG, "String response: " + jsonResponse);

                    if (mTypeOf != null) {
                        response = this.mGson.fromJson(jsonResponse, mTypeOf);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (response != null && response instanceof Response) {
                switch (((Response) response).getStatus()) {
                    case 500:
                        Log.e(TAG, "Status 500: " + ((Response) response).getMessage());
                        break;
                    case 200:
                        Log.i(TAG, "Status 200: " + ((Response) response).getMessage());
                        break;
                    default:
                        Log.w(TAG, "Status " + ((Response) response).getStatus() + ": "
                                + ((Response) response).getMessage());
                }
            }
        }
    }

    private void performClientCall(Request request, Class typeOf, WebServiceResponse responseCallback) {

        Log.i(TAG, "call on: " + request.getUrl());

        Object response = null;
        HttpClient httpClient = null;
        String jsonResponse = null;

        try {

            httpClient = new DefaultHttpClient();

            if (request.getMethod() == Request.Method.GET) {

                HttpGet get = new HttpGet(request.getUrl());

                HttpResponse httpResponse = httpClient.execute(get);
                InputStream is = httpResponse.getEntity().getContent();
                jsonResponse = Utils.convertStreamToString(is);

            } else {

                HttpPost post = new HttpPost(request.getUrl());

                Log.d(TAG, "POST parameters: " + request.getPostParameters());

                post.setEntity(new StringEntity(request.getPostParameters(), "UTF8"));
                post.setHeader("Content-type", "application/json");

                HttpResponse httpResponse = httpClient.execute(post);
                InputStream is = httpResponse.getEntity().getContent();
                jsonResponse = Utils.convertStreamToString(is);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null && httpClient.getConnectionManager() != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }

        try {
            if (jsonResponse != null) {

                Log.d(TAG, "String response: " + jsonResponse);

                if (typeOf != null) {
                    response = this.gson.fromJson(jsonResponse, typeOf);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response != null && response instanceof Response) {
            switch (((Response) response).getStatus()) {
                case 500:
                    Log.e(TAG, "Status 500: " + ((Response) response).getMessage());
                    break;
                case 200:
                    Log.i(TAG, "Status 200: " + ((Response) response).getMessage());
                    break;
                default:
                    Log.w(TAG, "Status " + ((Response) response).getStatus() + ": "
                            + ((Response) response).getMessage());
            }
        }


    }

    @Override
    public void getUser(String username, String password, WebServiceResponse callback) {

        Request request = new Request();

        request.setMethod(Request.Method.GET);
        request.setUrl(Utils.returnUrlWithParams("api/user/login"));

        performClientCall(request, GetUser.class, callback);
    }

    @Override
    public void postRegisterUser(User user, WebServiceResponse callback) {

    }

}
