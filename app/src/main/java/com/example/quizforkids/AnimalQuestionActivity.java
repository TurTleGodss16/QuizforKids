package com.example.quizforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnimalQuestionActivity extends AppCompatActivity {

    private int curr_ques = 0, correct_ques = 0, incorrect_ques = 0, score = 0, totalScore = 0;
    private ImageView imageQuestion, nextButton, backButton, submitButton;
    private TextView numberQuestion;
    private EditText userAnswer;
    private HashSet<String> listQuestion = new HashSet<>();
    private HashMap<Integer, String> answerRecord = new HashMap<>();
    private List<String> list;
    private databaseHelper_score scoreDB;

    private String[] animalsList = {"bird", "buffalo", "cat", "chicken", "dog", "duck", "frog", "horse", "pig", "sheep"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_question);

        scoreDB = new databaseHelper_score(this, null, null, 1);

        imageQuestion = findViewById(R.id.imageQuestion);
        numberQuestion = findViewById(R.id.numberQuestion);
        userAnswer = findViewById(R.id.animalAnswer);
        nextButton = findViewById(R.id.nextButtonAnimal);
        backButton = findViewById(R.id.backButtonAnimal);
        submitButton = findViewById(R.id.submitButtonAnimal);

        submitButton.setVisibility(View.INVISIBLE);

        Random random = new Random();
        while (listQuestion.size() != 4) {
            int max = 9, min = 0;
            int randomNum = random.nextInt(max - min + 1) + min;
            listQuestion.add(animalsList[randomNum]);
        }

        list = new ArrayList<>(listQuestion);

        showQuestion(curr_ques);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAnswer();
                curr_ques++;
                if (curr_ques < 4)
                {
                    showQuestion(curr_ques);
                }
                if(curr_ques == 3)
                    submitButton.setVisibility(View.VISIBLE);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAnswer();
                if (curr_ques > 0) {
                    curr_ques--;
                    showQuestion(curr_ques);
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitQuiz();
            }
        });
    }

    private void showQuestion(int index) {
        String imageName = list.get(index);
        int resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        imageQuestion.setImageResource(resourceId);
        numberQuestion.setText("Question " + (index + 1));

        String savedAnswer = answerRecord.get(index);
        userAnswer.setText(savedAnswer != null ? savedAnswer : "");
    }

    private void saveAnswer() {
        String answer = userAnswer.getText().toString().trim();
        answerRecord.put(curr_ques, answer);
    }

    private void submitQuiz() {
        saveAnswer();

        for (int questionID : answerRecord.keySet()) {
            String answer = answerRecord.get(questionID);
            if (answer.isEmpty()) {
                incorrect_ques++;
            } else {
                if (isValidAnimal(answer)) {
                    correct_ques++;
                } else {
                    incorrect_ques++;
                }
            }
        }

        score = 3 * correct_ques - incorrect_ques;
        String username = getIntent().getStringExtra("username");

        boolean isInserted = scoreDB.insertData(username, score, correct_ques, incorrect_ques);
        if (isInserted) {
            Toast.makeText(AnimalQuestionActivity.this, "Score recorded", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(AnimalQuestionActivity.this, "Recording failed", Toast.LENGTH_LONG).show();
        }

        int totalScore = scoreDB.getTotalScore(username);
        finish();
        Intent intent = new Intent(AnimalQuestionActivity.this, AnimalFinishActivity.class);
        intent.putExtra("username_out", username);
        intent.putExtra("score_out", String.valueOf(score));
        intent.putExtra("correct_ques_out", String.valueOf(correct_ques));
        intent.putExtra("incorrect_ques_out", String.valueOf(incorrect_ques));
        intent.putExtra("total_score_out", String.valueOf(totalScore));
        startActivity(intent);
    }

    private boolean isValidAnimal(String answer) {
        for (String animal : animalsList) {
            if (animal.equalsIgnoreCase(answer)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scoreDB.close();
    }
}