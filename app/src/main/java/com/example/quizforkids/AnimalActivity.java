package com.example.quizforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnimalActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        startButton = (Button) findViewById(R.id.startAnimalButton);
        startButton.setOnClickListener(onClickStart);
    }

    private final View.OnClickListener onClickStart = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AnimalActivity.this, AnimalQuestionActivity.class);
            String username = getIntent().getStringExtra("username");
            intent.putExtra("username", username);
            startActivity(intent);
        }
    };
}