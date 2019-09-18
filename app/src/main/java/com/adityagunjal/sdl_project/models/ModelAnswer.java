package com.adityagunjal.sdl_project.models;

public class ModelAnswer {
    public int profile_pic;
    public String username;
    public String lastUpdated;
    public String Answer;
    public int id, upvotes, downvotes, comments;

    public ModelAnswer(){}

    public ModelAnswer(int id, int profile_pic, String username, String lastUpdated, String answer, int upvotes, int downvotes, int comments) {
        this.profile_pic = profile_pic;
        this.username = username;
        this.lastUpdated = lastUpdated;
        this.Answer = answer;
        this.id = id;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
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

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
