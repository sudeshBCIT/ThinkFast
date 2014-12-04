package com.example.a00807017.thinkfast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity {

    Button btnSignIn,btnNewUser;
    DataBaseAdapter dataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // create a instance of SQLite Database
        dataBaseAdapter = DataBaseAdapter.getOneInstance(this);
        dataBaseAdapter = dataBaseAdapter.open();

        // Get The Reference Of Buttons
        btnSignIn=(Button)findViewById(R.id.btn_signin);
        btnNewUser=(Button)findViewById(R.id.btn_newuser);

        // Set OnClick Listener on SignUp button
        btnNewUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intentNewUser=new Intent(getApplicationContext(),NewUser.class);
                startActivity(intentNewUser);
            }
        });
    }
    // Method to handleClick Event of Sign In Button
    public void signIn(View v)
    {

        // get the References of views
        final EditText editTextUserName=(EditText)findViewById(R.id.username);
        final  EditText editTextPassword=(EditText)findViewById(R.id.password);

        Button btnSignIn=(Button)findViewById(R.id.btn_signin);

        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword= dataBaseAdapter.getPassword(userName);

                // check if the Stored password matches with  Password entered by user
                if(password.equals(storedPassword))
                {
                    Toast.makeText(Login.this, "Congrats: Login Successful", Toast.LENGTH_LONG).show();
                    Intent optionsIntent = new Intent(getApplicationContext(), Options.class);
                    optionsIntent.putExtra("USERNAME", userName);
                    startActivity(optionsIntent);
                }
                else
                {
                    Toast.makeText(Login.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
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