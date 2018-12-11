package com.example.android.retrofitexample.model;

import com.google.gson.annotations.SerializedName;

public class ModelSignUp {

    @SerializedName("email")
    private  String email;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("first_name")
    private String first_name;

    @SerializedName("last_name")
    private String last_name;

    public ModelSignUp(String email, String username, String password, String first_name, String last_name){
        this.email=email;
        this.username=username;
        this.password=password;
        this.first_name=first_name;
        this.last_name=last_name;
    }

    public String getEmail(){
        return email;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getFirstname(){
        return first_name;
    }

    public String getLastname(){
        return last_name;
    }
}
