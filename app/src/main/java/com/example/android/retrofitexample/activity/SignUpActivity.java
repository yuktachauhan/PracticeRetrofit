package com.example.android.retrofitexample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.retrofitexample.R;
import com.example.android.retrofitexample.model.ModelSignUp;
import com.example.android.retrofitexample.rest.ApiClient;
import com.example.android.retrofitexample.rest.ApiInterface;

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
        String fname = firstName.getText().toString().trim();
        String lname = lastName.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        String user = username.getText().toString().trim();
        String emailId = email.getText().toString().trim();

        /*if(fname.isEmpty()){
            Toast.makeText(SignUpActivity.this,"Firstname should not be empty",Toast.LENGTH_SHORT);
        }

        if(lname.isEmpty()){
            Toast.makeText(SignUpActivity.this,"Lastname should not be empty",Toast.LENGTH_SHORT);
        }

        if(pwd.isEmpty() || pwd.length()<8){
            Toast.makeText(SignUpActivity.this,"password should contain atleast 8 characters",Toast.LENGTH_SHORT);
        }

        if(user.isEmpty()){
            Toast.makeText(SignUpActivity.this,"Username shoud not be empty",Toast.LENGTH_SHORT);
        }

        if(emailId.isEmpty()){
            Toast.makeText(SignUpActivity.this,"email shoud not be empty",Toast.LENGTH_SHORT);
        }*/

        ApiInterface apiInterface = ApiClient.ApiClient().create(ApiInterface.class);
        ModelSignUp modelResponse=new ModelSignUp(emailId,user,pwd,fname,lname);
        Log.i("SignUpActivity","modelResponse is called");
        Call<ModelSignUp> call=apiInterface.Register(modelResponse);


        call.enqueue(new Callback<ModelSignUp>() {
            @Override
            public void onResponse(Call<ModelSignUp> call, Response<ModelSignUp> response) {
                Log.i("SignUpActivity","onResponse is called");
                if(response.code()==400) {
                    Toast.makeText(SignUpActivity.this, "email or username  already exists.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SignUpActivity.this,"Registration is successfull",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ModelSignUp> call, Throwable t) {
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
