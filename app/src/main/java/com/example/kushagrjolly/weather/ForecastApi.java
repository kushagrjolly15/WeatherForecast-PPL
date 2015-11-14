package com.example.kushagrjolly.weather;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;

/**
 * Created by ayushb on 14/11/15.
 */
public interface ForecastApi {
    @GET("/26.61,77.23")
    void getDistance(
            Callback<Response> callback
    );

}