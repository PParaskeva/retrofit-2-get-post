package com.example.panagiotis.retrofit2_post_get.Services;

import com.example.panagiotis.retrofit2_post_get.Constants.Constants;
import com.example.panagiotis.retrofit2_post_get.pojo.post.User;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Panagiotis on 25/01/2017.
 */

public interface IData {

    @POST(Constants.Relative_Post_Details_URL)
    Observable<User> createUser(
            @Body User user
    );

}
