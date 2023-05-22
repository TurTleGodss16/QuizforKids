package com.example.quizforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimalFinishActivity extends AppCompatActivity {

    private TextView result;
    private AnimalQuestionActivity animalQuiz;
    private Register account;
    private Button retryQuiz, newQuiz;
    private ImageView logoff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        result = (TextView) findViewById(R.id.resultView);
        animalQuiz = new AnimalQuestionActivity();
        account = new Register();

        retryQuiz = (Button) findViewById(R.id.retryAnimal);
        newQuiz = (Button) findViewById(R.id.newCartoonQuiz);

        logoff = (ImageView) findViewById(R.id.logOffAnimal);

        retryQuiz.setOnClickListener(clickListener);
        newQuiz.setOnClickListener(clickListener);
        logoff.setOnClickListener(clickListener);

        String username_out = getIntent().getStringExtra("username_out");
        String score_out = getIntent().getStringExtra("score_out");
        String correct_ques_out = getIntent().getStringExtra("correct_ques_out");
        String incorrect_ques_out = getIntent().getStringExtra("incorrect_ques_out");
        String totalScore = getIntent().getStringExtra("total_score_out");
        //Cursor res = getResult.viewRecord(username_out, score_out, correct_ques_out, incorrect_ques_out);

        result.setText("Well done " + username_out + ", you have finised the Animal quiz\n" + "You have " + correct_ques_out
                        + " correct questions and " + incorrect_ques_out + " incorrect questions\n" + "Your mark is " + score_out + "\nYour total score is " + totalScore);

        /*
        String username_out = getIntent().getStringExtra("username_out");
        String score_out = getIntent().getStringExtra("score_out");
        String correct_ques_out = getIntent().getStringExtra("correct_ques_out");
        String incorrect_ques_out = getIntent().getStringExtra("incorrect_ques_out");
        result.setText(username_out + "\n" + score_out + "\n" + correct_ques_out + "\n" + incorrect_ques_out);
        */
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId())
            {
                case R.id.retryAnimal:
                    Intent intent = new Intent(AnimalFinishActivity.this, AnimalActivity.class);
                    startActivity(intent);
                    break;

                case R.id.newCartoonQuiz:
                    Intent intent1 = new Intent(AnimalFinishActivity.this, CartoonActivity.class);
                    startActivity(intent1);
                    break;

                case R.id.logOffAnimal:
                    Intent intent2 = new Intent(AnimalFinishActivity.this, MainActivity.class);
                    startActivity(intent2);
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}