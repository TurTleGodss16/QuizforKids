package com.example.quizforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CartoonActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoon);

        startButton = (Button) findViewById(R.id.startCartoonButton);

        startButton.setOnClickListener(clickListener);
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(CartoonActivity.this, CartoonQuestionActivity.class);
            startActivity(intent);
        }
    };
}