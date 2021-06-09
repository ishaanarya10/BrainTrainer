package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView sumTextView;
    Random rand;
    ArrayList<Integer> answers = new ArrayList<>();
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQues = 0;
    TextView scoreTextView;
    CountDownTimer countDownTimer;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    public void playAgain(View view){

        score = 0;
        numberOfQues = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQues));
        createQuestion();
        startTimer();
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

    }

    public void chooseAnswer(View view){

//        Log.i("Tag:", view.getTag().toString());

        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){

            resultTextView.setText("Correct!");
            score++;

        }else{
            resultTextView.setText("Wrong :(");
        }

        numberOfQues++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQues));
        createQuestion();

    }

    public void start(View view){

        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        createQuestion();
        startTimer();
        resultTextView.setText("");

    }

    public void createQuestion(){

        rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for(int i=0;i<4;i++){

            if(i == locationOfCorrectAnswer){
                answers.add(a + b);
            }else{

                int wrongAnswer = rand.nextInt(41);

                while(wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41);
                }

                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void startTimer(){

        countDownTimer = new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf((l/1000) + "s"));
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        };

        countDownTimer.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);

        sumTextView = (TextView) findViewById(R.id.sumTextView);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);

        timerTextView = (TextView) findViewById(R.id.timerTextView);

        playAgainButton = (Button) findViewById(R.id.playAgainButton);

        gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}