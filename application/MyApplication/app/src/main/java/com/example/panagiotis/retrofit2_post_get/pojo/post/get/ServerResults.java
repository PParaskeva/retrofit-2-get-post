package com.example.panagiotis.retrofit2_post_get.pojo.post.get;

 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

public class ServerResults {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("avatar")
    @Expose
    private Object avatar;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

}
