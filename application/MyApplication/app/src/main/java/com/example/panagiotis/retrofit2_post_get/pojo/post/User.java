package com.example.panagiotis.retrofit2_post_get.pojo.post;


import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
