package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.whrabbit.bioscoop.DatabaseLayer.DatabaseHandler;
import com.example.whrabbit.bioscoop.Domain.Customer;

public class MainActivity extends AppCompatActivity {

    Button loginBttn, registrationBttn;
    EditText passwordBox, userNameBox;
    TextView wrongPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHandler dbh = new DatabaseHandler(getApplicationContext(), null, null, 1);
        passwordBox = (EditText) findViewById(R.id.passEditText);
        userNameBox = (EditText) findViewById(R.id.usernameEditText);
        wrongPasswordView = (TextView) findViewById(R.id.wrongPasswordView);
        wrongPasswordView.setVisibility(View.INVISIBLE);



        registrationBttn = (Button) findViewById(R.id.registrationBttn);
        registrationBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(i);
                wrongPasswordView.setVisibility(View.INVISIBLE);
            }
        });

        loginBttn = (Button) findViewById(R.id.loginBttn);
        loginBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = userNameBox.getText().toString();
                Log.i("LOG",passwordBox.getText().toString());

            if(userNameBox.getText().length() > 0 && passwordBox.getText().length() > 0) {

                if (passwordBox.getText().toString().equals(dbh.getPassword(username))) {
                    Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(i);
                    wrongPasswordView.setVisibility(View.INVISIBLE);

                } else {
                    wrongPasswordView.setVisibility(View.VISIBLE);
                }
            }else{
                wrongPasswordView.setVisibility(View.VISIBLE);
            }


            }
        });
    }
}
