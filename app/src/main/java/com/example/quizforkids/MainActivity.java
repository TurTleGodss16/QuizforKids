package com.example.quizforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usernameInp, passwordInp;
    private TextView register;
    private Button loginButton;
    databaseHelper_register myDB;

    public MainActivity(){};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new databaseHelper_register(this, null, null, 1);

        usernameInp = (EditText) findViewById(R.id.userNameInput);
        passwordInp = (EditText) findViewById(R.id.passwordInput);
        loginButton = (Button) findViewById(R.id.loginButton);
        register = (TextView) findViewById(R.id.register);

        loginButton.setOnClickListener(login);
        register.setOnClickListener(registerScreen);

    }

    //Login activity
    private final View.OnClickListener login =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Get username and password and login to the main menu page of the Quiz application
                    String username = usernameInp.getText().toString();
                    String password = passwordInp.getText().toString();

                    Cursor res = myDB.viewRecord(username, password);
                    //Get and check if username and password are valid in the database
                    if(res.moveToFirst())
                    {
                        //Launch the main page
                        Intent intent = new Intent(MainActivity.this, MainMenu.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    }
                    //If invalid, display a toast
                    else
                    {
                        Toast.makeText(MainActivity.this, "Account is invalid", Toast.LENGTH_LONG).show();
                    }

                }
            };

    //Register activity
    private final View.OnClickListener registerScreen =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Launch the register page
                    Intent intent = new Intent(MainActivity.this, Register.class);
                    startActivity(intent);
                }
            };

}
