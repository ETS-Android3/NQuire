package com.adityagunjal.sdl_project.models;

public class ModelDraft {
    public int qid, aid;
    public String question, answer;

    public ModelDraft(){}

    public ModelDraft(int qid, int aid, String question, String answer) {
        this.qid = qid;
        this.aid = aid;
        this.question = question;
        this.answer = answer;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
