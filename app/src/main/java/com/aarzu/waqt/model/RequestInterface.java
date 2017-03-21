package com.aarzu.waqt.model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Drac Android on 2/10/2017.
 */
        /*We need to create a Interface and define the Request endpoints. Here we use GET request to obtain JSON data.
        The endpoint is defined using @GET annotation.
        Our request URL is http://api.learn2crack.com/android/jsonandroid,
        where http://api.learn2crack.com is base url and android/jsonandroid is endpoint.

        For our request method getJSON() the JSONResponse object is wrapped in Call object.
        By using Call, the request is made Asynchronous so you need not worry about UI blocking or AsyncTask.
        The JSON response received after making the request it is stored in JSONResponse object.
*/

public interface RequestInterface {
    @GET("showEvent.php")
    Call<JSONResponse> getJSON();

    @GET("showMosque.php")
    Call<JSONResponse> getMosqueJSON();

    @GET("showIftar.php")
    Call<JSONResponse> getIftarJSON();

    @GET("showSher.php")
    Call<JSONResponse> getSherJSON();

    @GET("showTshirt.php")
    Call<JSONResponse> getTshirtJSON();

    @GET("showNamaz.php")
    Call<JSONResponse> getNamazJSON();
}
