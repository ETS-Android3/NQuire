package com.adityagunjal.sdl_project.models;

import com.adityagunjal.sdl_project.R;

public class ModelFeed {
    public int id, likes, comments, profile_pic, dislikes;
    public String name, question, lastUpdate, answer;

    public ModelFeed(int id, int likes,int dislikes, int comments, int profile_pic, String name, String question, String lastUpdate, String answer){
        this.id = id;
        this.likes = likes;
        this.comments = comments;
        this.profile_pic = profile_pic;
        this.name = name;
        this.question = question;
        this.lastUpdate = lastUpdate;
        this.dislikes = dislikes;
        this.answer = answer;
    }

    public ModelFeed() {
        this.id = 1;
        this.likes = 0;
        this.comments = 0;
        this.dislikes = 0;
        this.profile_pic = R.drawable.ic_profile_pic;
        this.name = "Username";
        this.question = "This is Question";
        this.lastUpdate = "1 hr ago";
        this.answer = "Answer goes here !";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(int profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
