package com.example.quizforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainMenu extends AppCompatActivity {

    private ImageView backButton, animalView, cartoonView, logoff;
    private TextView animalTxt, cartoonTxt, animalScoreTxt, cartoonScoreTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        backButton = (ImageView) findViewById(R.id.backButton);
        animalView = (ImageView) findViewById(R.id.animalImg);
        cartoonView = (ImageView) findViewById(R.id.cartoonImg);
        logoff = (ImageView) findViewById(R.id.logoffMainmenu);

        animalTxt = (TextView) findViewById(R.id.animalTxtView);
        cartoonTxt = (TextView) findViewById(R.id.cartoonTxtView);
        animalScoreTxt = (TextView) findViewById(R.id.animalScoreTxt);
        cartoonScoreTxt = (TextView) findViewById(R.id.cartoonScoreTxt);

        backButton.setOnClickListener(backButtonClick);
        animalView.setOnClickListener(onClickQuestion);
        cartoonView.setOnClickListener(onClickQuestion);
        animalTxt.setOnClickListener(onClickQuestion);
        cartoonTxt.setOnClickListener(onClickQuestion);
        logoff.setOnClickListener(onClickQuestion);
    }

    //On Click back button
    private final View.OnClickListener backButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainMenu.this, MainActivity.class);
            startActivity(intent);
        }
    };

    //On Click lead to the quiz
    private final View.OnClickListener onClickQuestion = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId())
            {
                //Animal area
                case R.id.animalImg:
                case R.id.animalTxtView:
                    Intent animalIntent = new Intent(MainMenu.this, AnimalActivity.class);
                    String username = getIntent().getStringExtra("username");
                    animalIntent.putExtra("username", username);
                    startActivity(animalIntent);
                    break;

                //Cartoon area
                case R.id.cartoonImg:
                case R.id.cartoonTxtView:
                    Intent cartoonIntent = new Intent(MainMenu.this, CartoonActivity.class);
                    startActivity(cartoonIntent);
                    break;

                //Logoff
                case R.id.logoffMainmenu:
                    Intent intent = new Intent(MainMenu.this, MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}