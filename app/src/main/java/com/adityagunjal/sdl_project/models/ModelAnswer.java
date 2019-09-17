package com.adityagunjal.sdl_project.models;

public class ModelAnswer {
    private int profile_pic;
    private String username;
    private String lastUpdated;
    private String Answer;
    private int id, likes, dislikes, comments;

    public ModelAnswer(int id, int profile_pic, String username, String lastUpdated, String answer, int likes, int dislikes, int comments) {
        this.profile_pic = profile_pic;
        this.username = username;
        this.lastUpdated = lastUpdated;
        this.Answer = answer;
        this.id = id;
        this.likes = likes;
        this.dislikes = dislikes;
        this.comments = comments;
    }

    public int getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(int profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
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

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
