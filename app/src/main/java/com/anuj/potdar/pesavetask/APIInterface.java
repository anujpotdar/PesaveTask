package com.anuj.potdar.pesavetask;

import com.anuj.potdar.pesavetask.transaction.Response;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by potda on 5/23/2018.
 */

public interface APIInterface {

    @GET("apiv2/CronController/jsonTest")
    Call<Response> getTransactionDetails();

}
