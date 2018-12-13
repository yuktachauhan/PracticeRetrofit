package com.example.android.retrofitexample.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.retrofitexample.R;
import com.example.android.retrofitexample.model.ModelSignUp;
import com.example.android.retrofitexample.rest.ApiClient;
import com.example.android.retrofitexample.rest.ApiInterface;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private EditText firstName,lastName,username,email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

         firstName=(EditText) findViewById(R.id.firstname);
         lastName=(EditText) findViewById(R.id.lastname);
         username=(EditText) findViewById(R.id.username);
         email=(EditText) findViewById(R.id.email);
         password=(EditText) findViewById(R.id.password);
    }



    public void userSignup() {
        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setMessage("Signing up...");
        progressDialog.show();

        String fname = firstName.getText().toString().trim();
        String lname = lastName.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        String user = username.getText().toString().trim();
        String emailId = email.getText().toString().trim();

        if(fname.isEmpty()){
            progressDialog.dismiss();
            firstName.setError("Firstname should not be empty");
            firstName.requestFocus();
            return;
        }

        if(lname.isEmpty()){
            progressDialog.dismiss();
            lastName.setError("Lastname should not be empty");
            lastName.requestFocus();
            return;
        }
        if(user.isEmpty()){
            progressDialog.dismiss();
            username.setError("Username should not be empty");
            username.requestFocus();
            return;
        }
        if(emailId.isEmpty()){
            progressDialog.dismiss();
            email.setError("email should not be empty");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
            progressDialog.dismiss();
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }

        if(pwd.isEmpty() || pwd.length()<8){
            progressDialog.dismiss();
            password.setError("password should contain atleast 8 characters");
            password.requestFocus();
            return;
        }


        ApiInterface apiInterface = ApiClient.ApiClient().create(ApiInterface.class);
        ModelSignUp modelResponse=new ModelSignUp(emailId,user,pwd,fname,lname);
        Call<ModelSignUp> call=apiInterface.Register(modelResponse);


        call.enqueue(new Callback<ModelSignUp>() {
            @Override
            public void onResponse(Call<ModelSignUp> call, Response<ModelSignUp> response) {
                progressDialog.dismiss();
                Log.i("SignUpActivity","onResponse is called");
                if(response.isSuccessful()) {
                    startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                }else if(response.code()==400){
                    Toast.makeText(SignUpActivity.this, "email or username  already exists.", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ModelSignUp> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SignUpActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                Log.i("SignUpActivity","onFailure is called");
            }
        });

    }

    public void Signup(View v){
        userSignup();
    }

    public void login(View v){
        Intent intent =new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}
