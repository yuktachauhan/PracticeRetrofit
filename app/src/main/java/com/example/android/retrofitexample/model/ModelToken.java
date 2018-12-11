package com.example.android.retrofitexample.model;

import com.google.gson.annotations.SerializedName;

public class ModelToken {
    @SerializedName("username")
    private String username;

    @SerializedName("token")
    private String Token;

    public ModelToken(String user,String token){
        this.username=user;
        this.Token=token;
    }

    public String getUsername(){
        return username;
    }

    public String getToken(){
        return Token;
    }
}
