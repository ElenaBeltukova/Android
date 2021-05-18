package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultTextView = findViewById(R.id.resultTextView);

        ArrayList<Question> questions;
        questions = (ArrayList<Question>) getIntent().getSerializableExtra("questionList");
        if (questions != null) {
            for (Question question:questions) {
                resultTextView.append(getString(question.getQuestionResId()) + "\n"
                        + getString(R.string.trueAnswer) + (question.isAnswerTrue()?getString(R.string.yes):getString(R.string.no)) + "\n"
                        + getString(R.string.youAnswer) + (question.isAnswerRes()?getString(R.string.yes):getString(R.string.no)) + "\n");
            }
        }

    }

}