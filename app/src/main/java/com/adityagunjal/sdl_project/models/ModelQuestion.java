package com.adityagunjal.sdl_project.models;

public class ModelQuestion {
    public int id, userid, answers;
    public String question, username, dateOfAnswer;

    public ModelQuestion(){}

    public ModelQuestion(int id, int userid, int answers, String question, String username, String dateOfAnswer) {
        this.id = id;
        this.userid = userid;
        this.answers = answers;
        this.question = question;
        this.username = username;
        this.dateOfAnswer = dateOfAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getAnswers() {
        return answers;
    }

    public String getDateOfAnswer() {
        return dateOfAnswer;
    }

    public void setDateOfAnswer(String dateOfAnswer) {
        this.dateOfAnswer = dateOfAnswer;
    }

    public void setAnswers(int answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
