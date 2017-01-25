package com.example.panagiotis.retrofit2_post_get.realmModels;

import io.realm.RealmObject;


public class User_local_data extends RealmObject {
    private String userid;
    private String imageUrl;
    private String token;
    private String password;

    public User_local_data() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
