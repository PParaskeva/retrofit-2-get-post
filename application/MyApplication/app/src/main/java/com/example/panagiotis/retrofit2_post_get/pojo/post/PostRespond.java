package com.example.panagiotis.retrofit2_post_get.pojo.post;

 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

public class PostRespond {

    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("token")
    @Expose
    private String token;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
