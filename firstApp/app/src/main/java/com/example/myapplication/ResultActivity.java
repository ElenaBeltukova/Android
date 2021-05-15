package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView resultTextView;
    private Question[] resultList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle arguments = getIntent().getExtras();
        Question question;
        int count = getIntent().getIntExtra("count", 0);
        resultTextView = findViewById(R.id.resultTextView);

        if(arguments!= null){
            for (int i = 0; i < count; i++) {
                question = (Question) arguments.getSerializable("questions"+i);

                resultTextView.append(getString(question.getQuestionResId()) + "\n"
                        + getString(R.string.trueAnswer) + (question.isAnswerTrue()?getString(R.string.yes):getString(R.string.no)) + "\n"
                        + getString(R.string.youAnswer) + (question.isAnswerRes()?getString(R.string.yes):getString(R.string.no)) + "\n");
            }
        }

    }

}