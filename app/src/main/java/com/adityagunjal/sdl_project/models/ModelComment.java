package com.adityagunjal.sdl_project.models;

public class ModelComment {

    private int commentProfilePic;
    private String username;
    private String lastUpdated;
    private String comment;

    public ModelComment(int commentProfilePic, String username, String lastUpdated, String comment){
        this.commentProfilePic = commentProfilePic;
        this.username = username;
        this.lastUpdated = lastUpdated;
        this.comment = comment;
    }

    public int getCommentProfilePic() {
        return commentProfilePic;
    }

    public void setCommentProfilePic(int commentProfilePic) {
        this.commentProfilePic = commentProfilePic;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
