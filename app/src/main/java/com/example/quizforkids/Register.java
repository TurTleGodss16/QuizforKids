package com.example.quizforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText usernameInp, passwordInp;
    private Button register;
    databaseHelper_register myDB;

    public Register(){

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgister);

        myDB = new databaseHelper_register(this, null, null, 1);

        usernameInp = (EditText) findViewById(R.id.usernameRegInput);
        passwordInp = (EditText) findViewById(R.id.passwordRegInput);
        register = (Button) findViewById(R.id.registerButton);

        usernameInp.requestFocus(); //Focus on the username field
        register.setOnClickListener(clickListener);
    }

    //Click listener for register button
    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            addRecord();
        }
    };

    //Add account info to the database
    public void addRecord(){
        boolean isInserted = myDB.insertData(usernameInp.getText().toString(), passwordInp.getText().toString());
        if(isInserted == true)
        {
            Toast.makeText(Register.this, "Register Successfully", Toast.LENGTH_LONG).show();
            resetDataField();
        }
        else
        {
            Toast.makeText(Register.this, "Error, can't register", Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
    }

    //Reset all data input fields
    public void resetDataField(){
        usernameInp.setText("");
        passwordInp.setText("");
    }


}