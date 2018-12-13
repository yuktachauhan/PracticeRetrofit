package com.example.android.retrofitexample.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.retrofitexample.R;
import com.example.android.retrofitexample.model.ModelLogin;
import com.example.android.retrofitexample.model.ModelToken;
import com.example.android.retrofitexample.rest.ApiClient;
import com.example.android.retrofitexample.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText username,password;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username= (EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
    }

    public void UserLogin(){
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        String user=username.getText().toString().trim();
        String pwd=password.getText().toString().trim();

        if(user.isEmpty()){
            progressDialog.dismiss();
            username.setError("Enter your valid username");
            username.requestFocus();
            return;
        }

        if(pwd.isEmpty() || pwd.length()<8){
            progressDialog.dismiss();
            password.setError("Enter your password");
            password.requestFocus();
            return;
        }

        ApiInterface apiInterface= ApiClient.ApiClient().create(ApiInterface.class);
        ModelLogin modelLogin =new ModelLogin(user,pwd);
        Call<ModelToken> call=apiInterface.Login(modelLogin);

        call.enqueue(new Callback<ModelToken>() {
            @Override
            public void onResponse(Call<ModelToken> call, Response<ModelToken> response) {
                progressDialog.dismiss();
                if (response.code()==200) {
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    SharedPreferences myPref=getApplicationContext().getSharedPreferences("mypref",0);  //0 means private mode
                    SharedPreferences.Editor editor=myPref.edit();
                    editor.putString("token",response.body().getToken()).commit();
                    token= myPref.getString("token",response.body().getToken());
                    Log.i("MY STORED TOKEN IS =",token);

                } else if(response.code()==400){
                    Toast.makeText(LoginActivity.this, "Please Verify your confirmation Email", Toast.LENGTH_LONG).show();
                }
                Log.i("LoginActivity", "onResponse is called");
            }
            @Override
            public void onFailure(Call<ModelToken> call, Throwable t) {
                progressDialog.dismiss();
                Log.i("LoginActivity","onFailure is called");
                Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loggedIn(View v){
        UserLogin();
    }

    public void forgetPassword(View v){
       Intent intent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
       startActivity(intent);
    }

    public void SignedUp(View v){
        Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
}
