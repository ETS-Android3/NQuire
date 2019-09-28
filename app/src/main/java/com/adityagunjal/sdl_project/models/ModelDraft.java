package com.adityagunjal.sdl_project.models;

import java.util.HashMap;

public class ModelDraft {
    public String userID, questionID,answerID;
    public HashMap<String, String> answer;

    public ModelDraft(){}

    public ModelDraft(String userID, String questionID, HashMap<String, String> answer) {
       this.userID = userID;
       this.questionID = questionID;

       this.answer = answer;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }


    public HashMap<String, String> getAnswer() {
        return answer;
    }

    public void setAnswer(HashMap<String, String> answer) {
        this.answer = answer;
    }
}
