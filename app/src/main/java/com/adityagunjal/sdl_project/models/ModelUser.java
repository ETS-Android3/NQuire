package com.adityagunjal.sdl_project.models;

public class ModelUser {
    public String firstName, lastName, username, email, registrationID;

    public ModelUser(){}

    public ModelUser(String firstName, String lastName, String username, String email, String registrationID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.registrationID = registrationID;
    }
}
