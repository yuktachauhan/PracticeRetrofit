package com.example.android.retrofitexample.rest;

import com.example.android.retrofitexample.model.ModelLogin;
import com.example.android.retrofitexample.model.ModelSignUp;
import com.example.android.retrofitexample.model.ModelToken;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("auth/register/")
    Call<ModelSignUp> Register(@Body ModelSignUp modelResponse) ;

    @POST("auth/login/")
    Call<ModelToken> Login(@Body ModelLogin modelLogin);

    @GET("user/classroom")
    Call<ResponseBody> Token(@Header("Authorization") String token);
}
