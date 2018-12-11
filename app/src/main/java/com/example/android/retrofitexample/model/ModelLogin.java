package com.example.android.retrofitexample.model;

import com.google.gson.annotations.SerializedName;

public class ModelLogin {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public ModelLogin(String username,String password){

        this.password=password;
        this.username=username;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
