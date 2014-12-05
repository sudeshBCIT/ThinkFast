package com.example.a00807017.thinkfast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This activity creates a new user and stores the user information into the database.
 *
 * @authors:    ThinkFast!
 *              Lynn Yuen, Sudesh Yadav, and Sandra Buchanan
 *              Fall Term 2014
 */

public class NewUser extends Activity {

    EditText editTextUserName,editTextPassword,editTextConfirmPassword;
    Button btnCreateAccount;
    DataBaseAdapter dataBaseAdapter;   // Database instance

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        // Get instance of database adapter
        dataBaseAdapter = DataBaseAdapter.getOneInstance(this);
        dataBaseAdapter = dataBaseAdapter.open();

        // Get views and button references
        editTextUserName=(EditText)findViewById(R.id.newuser_name);
        editTextPassword=(EditText)findViewById(R.id.new_password);
        editTextConfirmPassword=(EditText)findViewById(R.id.confirm_password);
        btnCreateAccount=(Button)findViewById(R.id.btn_submit);

        // On button click, retrieve user input and check that both passwords match
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();

                // Check if any of the fields are vacant
                if(userName.equals("")||password.equals("")||confirmPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field is Empty", Toast.LENGTH_LONG).show();
                    return;
                }
                // Check if both password matches
                if(!password.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    // Save the new user information in the database
                    dataBaseAdapter.insertEntry(userName, password);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();

                    // Creates and starts the intent for the Options activity
                    Intent optionsIntent = new Intent(getApplicationContext(), Options.class);
                    optionsIntent.putExtra("USERNAME", userName);
                    startActivity(optionsIntent);
                }
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        dataBaseAdapter.close();
    }
}