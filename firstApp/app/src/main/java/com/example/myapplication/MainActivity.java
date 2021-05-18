package com.example.myapplication;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.ArrayList;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private Button yesBtn;
    private Button noBtn;
    private Button showAnswer;
    private Button restartAnswer;
    private TextView textView;
    private LinearLayout linearLayout;
    private Question[] questions = new Question[]{
            new Question(R.string.question0,false),
            new Question(R.string.question1,true),
            new Question(R.string.question2,false),
            new Question(R.string.question3,false),
            new Question(R.string.question4,true)
    };
    private int questionIndex = 0;
    private ArrayList<Question> questionList = new ArrayList<Question>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("SYSTEM INFO: ", "Метод onCreate() запущен");

        if(savedInstanceState != null){
            questionIndex = savedInstanceState.getInt("questionIndex");
            questionList = (ArrayList<Question>) savedInstanceState.getSerializable("questionList");
        }

        yesBtn = findViewById(R.id.btnYes);
        noBtn = findViewById(R.id.btnNo);
        textView = findViewById(R.id.textView);
        showAnswer = findViewById(R.id.showAnswer);
        restartAnswer = findViewById(R.id.restartAnswer);
        linearLayout = findViewById(R.id.linearLayout);
        textView.setText(questions[questionIndex].getQuestionResId());

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questions[questionIndex].setAnswerRes(true);
                questionList.add(questions[questionIndex]);

                if(questions[questionIndex].isAnswerTrue())
                    Toast.makeText(MainActivity.this,R.string.correct,Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,R.string.incorrect,Toast.LENGTH_SHORT).show();

                if (questionIndex+1 == questions.length) {
                    Intent intentRes = new Intent(MainActivity.this, ResultActivity.class);
                    intentRes.putExtra("questionList", questionList);
                    startActivity(intentRes);
                    linearLayout.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                    showAnswer.setVisibility(View.GONE);
                } else {
                    questionIndex = (questionIndex + 1) % questions.length;
                    textView.setText(questions[questionIndex].getQuestionResId());
                }
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questions[questionIndex].setAnswerRes(false);
                questionList.add(questions[questionIndex]);

                if(questions[questionIndex].isAnswerTrue())
                    Toast.makeText(MainActivity.this,R.string.incorrect,Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,R.string.correct,Toast.LENGTH_SHORT).show();

                if (questionIndex+1 == questions.length) {
                    Intent intentRes = new Intent(MainActivity.this, ResultActivity.class);
                    intentRes.putExtra("questionList", questionList);
                    startActivity(intentRes);
                    linearLayout.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                    showAnswer.setVisibility(View.GONE);
                } else {
                    questionIndex = (questionIndex + 1) % questions.length;
                    textView.setText(questions[questionIndex].getQuestionResId());
                }
            }
        });
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                intent.putExtra("answer",questions[questionIndex].isAnswerTrue());
                startActivity(intent);
            }
        });
        restartAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionIndex = 0;
                questionList.clear();
                linearLayout.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                showAnswer.setVisibility(View.VISIBLE);
                textView.setText(questions[questionIndex].getQuestionResId());
            }
        });
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d("SYSTEM INFO: ", "Метод onStart() запущен");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d("SYSTEM INFO: ", "Метод onResume() запущен");
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d("SYSTEM INFO: ", "Метод onSaveInstanceState() запущен");
        savedInstanceState.putInt("questionIndex",questionIndex);
        savedInstanceState.putSerializable("questionList", (Serializable)questionList);
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d("SYSTEM INFO: ", "Метод onPause() запущен");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d("SYSTEM INFO: ", "Метод onStop() запущен");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("SYSTEM INFO: ", "Метод onDestroy() запущен");
    }
}