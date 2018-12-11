package com.example.android.retrofitexample.model;

import com.google.gson.annotations.SerializedName;

public class ModelToken {
    @SerializedName("username")
    private String username;

    @SerializedName("token")
    private String token;

    public ModelToken(String username,String token){
        this.username=username;
        this.token=token;
    }

    public String getUsername(){
        return username;
    }

    public String getToken(){
        return token;
    }
}
