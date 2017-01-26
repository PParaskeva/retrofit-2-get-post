package com.example.panagiotis.retrofit2_post_get.pojo.post;

import com.google.gson.annotations.SerializedName;

public class Avatar {
    @SerializedName("avatar")
    String avatar;

    public Avatar(String avatar) {
        this.avatar = avatar;
    }
}