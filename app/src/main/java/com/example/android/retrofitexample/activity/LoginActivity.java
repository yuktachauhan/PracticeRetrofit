package com.example.android.retrofitexample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.retrofitexample.R;
import com.example.android.retrofitexample.model.ModelLogin;
import com.example.android.retrofitexample.rest.ApiClient;
import com.example.android.retrofitexample.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username= (EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
    }

    public void UserLogin(){

        String user=username.getText().toString().trim();
        String pwd=password.getText().toString().trim();

        ApiInterface apiInterface= ApiClient.ApiClient().create(ApiInterface.class);
        ModelLogin modelLogin =new ModelLogin(user,pwd);
        Call<ModelLogin> call=apiInterface.Login(modelLogin);

        call.enqueue(new Callback<ModelLogin>() {
            @Override
            public void onResponse(Call<ModelLogin> call, Response<ModelLogin> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Successfull login is done", Toast.LENGTH_LONG);
                } else {
                    Toast.makeText(LoginActivity.this, "login is not done", Toast.LENGTH_LONG);
                }
                Log.i("LoginActivity", "onResponse is called");
            }
            @Override
            public void onFailure(Call<ModelLogin> call, Throwable t) {
                Log.i("LoginActivity","onFailure is called");
                Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_LONG);
            }
        });
    }

    public void loggedIn(View v){
        UserLogin();
    }

    public void SignedUp(View v){
        Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
}
