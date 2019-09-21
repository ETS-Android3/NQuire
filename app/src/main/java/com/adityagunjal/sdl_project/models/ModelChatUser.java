package com.adityagunjal.sdl_project.models;

public class ModelChatUser {
    String username, lastMessage, profilePicPath, lastUpdated;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicPath() {
        return profilePicPath;
    }

    public void setProfilePicPath(String profilePicPath) {
        this.profilePicPath = profilePicPath;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public ModelChatUser(){}

    public ModelChatUser(String username, String lastMessage, String lastUpdated, String profilePicPath) {
        this.username = username;
        this.lastMessage = lastMessage;
        this.profilePicPath = profilePicPath;
        this.lastUpdated = lastUpdated;
    }
}
