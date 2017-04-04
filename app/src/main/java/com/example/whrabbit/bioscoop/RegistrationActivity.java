package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.whrabbit.bioscoop.DatabaseLayer.DatabaseHandler;
import com.example.whrabbit.bioscoop.Domain.Customer;

import org.w3c.dom.Text;

public class RegistrationActivity extends AppCompatActivity {


    EditText firstName, Lastname, Email, address, city, postalCode, username, password;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

       final DatabaseHandler db = new DatabaseHandler(getApplicationContext(), null, null, 1);

        firstName = (EditText) findViewById(R.id.voorNaamEditText);
        Lastname = (EditText) findViewById(R.id.achternaamEditText);
        Email = (EditText) findViewById(R.id.mailEditText);
        address = (EditText) findViewById(R.id.adresEditText);
        city = (EditText) findViewById(R.id.woonplaatsEditText);
        postalCode = (EditText) findViewById(R.id.postcodeEditText);
        username = (EditText) findViewById(R.id.usernameEnterEditText);
        password = (EditText) findViewById(R.id.passwordEnterEditText);






        button = (Button) findViewById(R.id.registrationApplyBttn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Customer customer = new Customer();

                customer.setFirstName(firstName.getText().toString());
                customer.setLastName(Lastname.getText().toString());
                customer.setUsername(username.getText().toString());
                customer.setEmail(Email.getText().toString());
                customer.setCity(city.getText().toString());
                customer.setPostalCode(postalCode.getText().toString());
                customer.setPassword(password.getText().toString());
                customer.setStreet(address.getText().toString());



                db.addCustomer(customer);

                Log.i("TAG", db.getCustomer(username.getText().toString()).getFirstName());

                firstName.setText("");
                Lastname.setText("");
                username.setText("");
                password.setText("");
                city.setText("");
                address.setText("");
                postalCode.setText("");
                Email.setText("");






            }
        });





    }
}
