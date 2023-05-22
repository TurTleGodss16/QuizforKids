package com.example.quizforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class CartoonQuestionActivity extends AppCompatActivity {

    private final String[] questionList = {
            "Which movie has the character Pooh?",
            "Which movie has the character Tom cat?",
            "Which movie has the character Jerry mouse?",
            "Which movie has the character Eeyore donkey?",
            "Which movie has the character Piglet pig?",
            "Which movie has the character Donnald duck?",
            "Which movie has the character Pluto dog?",
            "Which movie has the character Goofy?",
            "Which movie has the character Daisy duck?",
            "Which movie has the character Mickey mouse?"
    };

    private final String[] answer = {
            "The many adventures of Winnie the Pooh",
            "Mickey Mouse",
            "Tom & Jerry"
    };

    private int[] selectedChoices = new int[4];
    private int[] checkQuestion = new int[4];
    private TextView numberQuestion, cartoonQuestion, option1, option2, option3;
    private ImageView backButton, nextButton, submitButton;
    private RadioGroup choice;
    private HashSet<String> listQuestion = new HashSet<>();
    private List<String> list;
    private int curr_ques = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoon_question);

        numberQuestion = findViewById(R.id.numberQuestionCartoon);
        cartoonQuestion = findViewById(R.id.cartoonQuestion);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);

        choice = findViewById(R.id.group);

        backButton = findViewById(R.id.backButtonCartoon);
        nextButton = findViewById(R.id.nextButtonCartoon);
        submitButton = findViewById(R.id.submitButtonCartoon);

        // Get 4 random questions - 4 random images
        Random random = new Random();
        while (listQuestion.size() != 4) {
            // Random between 1 and 10
            int max = 10, min = 1;
            int randomNum = random.nextInt(max - min + 1) + 1;
            listQuestion.add(questionList[randomNum]);
        }

        for (int i = 0; i < 4; i++) checkQuestion[i] = 0;
        checkQuestion[0] = 1;

        // Set default multiple choice answers for all questions
        option1.setText(answer[0]);
        option2.setText(answer[1]);
        option3.setText(answer[2]);

        // Convert from hash set to list string
        list = new ArrayList<>(listQuestion);
        cartoonQuestion.setText(list.get(0));
        if (curr_ques == 0) backButton.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.INVISIBLE);

        choice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int questionIndex = getCurrentQuestionIndex();
                selectedChoices[questionIndex] = i;
            }
        });

        backButton.setOnClickListener(clickListener);
        nextButton.setOnClickListener(clickListener);
        submitButton.setOnClickListener(clickListener);
    }

    private int getCurrentQuestionIndex() {
        return curr_ques;
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.submitButtonCartoon:
                    calculateResults();
                    break;
                case R.id.nextButtonCartoon:
                    nextQuestion();
                    break;
                case R.id.backButtonCartoon:
                    previousQuestion();
                    break;
            }
        }
    };

    private void calculateResults() {
        int selectedChoiceIndex = selectedChoices[getCurrentQuestionIndex()];
        String selectedChoice = getChoiceText(selectedChoiceIndex);
        String correctAnswer = answer[getCurrentQuestionIndex()];

        if (selectedChoice.equalsIgnoreCase(correctAnswer)) {
            Toast.makeText(this, "Correct Answer!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect Answer!", Toast.LENGTH_SHORT).show();
        }

        // Add your code to calculate and show the final results
        // You can start a new activity to display the results
    }

    private void nextQuestion() {
        int selection_next = choice.getCheckedRadioButtonId();

        if (selection_next == -1) {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
        } else {
            curr_ques++;
            if (checkQuestion[curr_ques] == 1) {
                int questionIndex = getCurrentQuestionIndex();
                int selectedChoice = selectedChoices[questionIndex];
                choice.check(selectedChoice);
                checkQuestion[curr_ques] = 1;
            } else {
                choice.clearCheck();
                checkQuestion[curr_ques] = 1;
            }
        }

        if (curr_ques == 1) {
            numberQuestion.setText("Question 2");
            nextButton.setVisibility(View.VISIBLE);
            backButton.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.INVISIBLE);
        } else if (curr_ques == 2) {
            numberQuestion.setText("Question 3");
            nextButton.setVisibility(View.VISIBLE);
            backButton.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.INVISIBLE);
        } else if (curr_ques == 3) {
            numberQuestion.setText("Question 4");
            nextButton.setVisibility(View.INVISIBLE);
            backButton.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.VISIBLE);
        }

        cartoonQuestion.setText(list.get(curr_ques));
    }

    private void previousQuestion() {
        curr_ques--;
        int questionIndex = getCurrentQuestionIndex();
        int selectedChoice = selectedChoices[questionIndex];
        choice.check(selectedChoice);

        if (curr_ques == 0) {
            numberQuestion.setText("Question 1");
            nextButton.setVisibility(View.VISIBLE);
            backButton.setVisibility(View.INVISIBLE);
            submitButton.setVisibility(View.INVISIBLE);
        } else if (curr_ques == 1) {
            numberQuestion.setText("Question 2");
            nextButton.setVisibility(View.VISIBLE);
            backButton.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.INVISIBLE);
        } else if (curr_ques == 2) {
            numberQuestion.setText("Question 3");
            nextButton.setVisibility(View.VISIBLE);
            backButton.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.INVISIBLE);
        }

        cartoonQuestion.setText(list.get(curr_ques));
    }

    private String getChoiceText(int choiceIndex) {
        RadioButton radioButton = findViewById(choiceIndex);
        return radioButton != null ? radioButton.getText().toString() : "";
    }
}
