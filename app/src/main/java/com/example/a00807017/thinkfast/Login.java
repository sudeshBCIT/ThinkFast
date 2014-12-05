package com.example.a00807017.thinkfast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This activity determines whether the user is logging in or setting up a new account based on
 * the button listener.  If the user is logging in, password verification is performed based on
 * the information saved in the database.  If the user is a new user, the NewUser activity is
 * called.
 *
 * @authors:    ThinkFast!
 *              Lynn Yuen, Sudesh Yadav, and Sandra Buchanan
 *              Fall Term 2014
 */

public class Login extends Activity {

    Button btnSignIn,btnNewUser;  // Buttons for determining login or new user
    DataBaseAdapter dataBaseAdapter;  // Database adapter instance

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Create an instance of the database
        dataBaseAdapter = DataBaseAdapter.getOneInstance(this);
        dataBaseAdapter = dataBaseAdapter.open();

        // Gets the reference of the buttons
        btnSignIn=(Button)findViewById(R.id.btn_signin);
        btnNewUser=(Button)findViewById(R.id.btn_newuser);

        // On button click, go to NewUser activity
        btnNewUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Creates and starts the intent for the NewUser activity
                Intent intentNewUser=new Intent(getApplicationContext(),NewUser.class);
                startActivity(intentNewUser);
            }
        });
    }
    // Method to handle click event of sign in button
    public void signIn(View v)
    {
        // Retrieve the view and button references
        final EditText editTextUserName=(EditText)findViewById(R.id.username);
        final  EditText editTextPassword=(EditText)findViewById(R.id.password);
        Button btnSignIn=(Button)findViewById(R.id.btn_signin);

        // On button click, retrieve user input and check that passwords match
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Retrieve the user name and password
                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

                // Fetch the stored password from the database for the specified user name
                String storedPassword= dataBaseAdapter.getPassword(userName);

                // Check if stored password matches password of user's input
                if(password.equals(storedPassword))
                {   // Passwords match
                    Toast.makeText(Login.this, "Congrats: Login Successful", Toast.LENGTH_LONG).show();
                    // Creates and starts the intent for the Options activity
                    Intent optionsIntent = new Intent(getApplicationContext(), Options.class);
                    optionsIntent.putExtra("USERNAME", userName);
                    startActivity(optionsIntent);
                }
                else
                {   // Passwords don't match and send error message
                    Toast.makeText(Login.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database
        dataBaseAdapter.close();
    }
}