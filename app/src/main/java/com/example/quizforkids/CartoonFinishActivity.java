package com.example.quizforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CartoonFinishActivity extends AppCompatActivity {

    private TextView result;
    private CartoonQuestionActivity cartoonQuiz;
    private Button retryQuiz, newQuiz;
    private ImageView logoff;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoon_finish);

        result = (TextView) findViewById(R.id.resultCartoonView);
        cartoonQuiz = new CartoonQuestionActivity();

        retryQuiz = (Button) findViewById(R.id.retryCartoon);
        newQuiz = (Button) findViewById(R.id.newAnimalQuiz);
        logoff = (ImageView) findViewById(R.id.logOffCartoon);

        retryQuiz.setOnClickListener(clickListener);
        newQuiz.setOnClickListener(clickListener);
        logoff.setOnClickListener(clickListener);

        String username_out = getIntent().getStringExtra("username_out");
        String score_out = getIntent().getStringExtra("score_out");
        String correct_ques_out = getIntent().getStringExtra("correct_ques_out");
        String incorrect_ques_out = getIntent().getStringExtra("incorrect_ques_out");
        String totalScore = getIntent().getStringExtra("total_score_out");

        result.setText("Well done " + username_out + ", you have finised the Animal quiz\n" + "You have " + correct_ques_out
                + " correct questions and " + incorrect_ques_out + " incorrect questions\n" + "Your mark is " + score_out + "\nYour total score is " + totalScore);

    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId())
            {
                case R.id.retryCartoon:
                    Intent intent = new Intent(CartoonFinishActivity.this, CartoonActivity.class);
                    startActivity(intent);
                    break;

                case R.id.newAnimalQuiz:
                    Intent intent1 = new Intent(CartoonFinishActivity.this, AnimalActivity.class);
                    startActivity(intent1);
                    break;

                case R.id.logOffCartoon:
                    Intent intent2 = new Intent(CartoonFinishActivity.this, MainActivity.class);
                    startActivity(intent2);
                    break;
            }
        }
    };
}