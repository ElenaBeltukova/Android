package com.example.myapplication;

import java.io.Serializable;

public class Question implements Serializable {
    private int questionResId; // тут лежит id вопроса
    private boolean answerTrue; // какой должен быть ответ
    private boolean answerRes; // какой был дан ответ

    public Question(int questionResId, boolean answerTrue) {
        this.questionResId = questionResId;
        this.answerTrue = answerTrue;
    }

    public int getQuestionResId() {
        return questionResId;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    public boolean isAnswerRes() {
        return answerRes;
    }

    public void setAnswerRes(boolean answerRes) {
        this.answerRes = answerRes;
    }
}