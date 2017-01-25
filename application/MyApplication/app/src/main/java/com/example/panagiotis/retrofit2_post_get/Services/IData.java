package com.example.panagiotis.retrofit2_post_get.Services;

import com.example.panagiotis.retrofit2_post_get.Constants.Constants;
import com.example.panagiotis.retrofit2_post_get.pojo.post.PostRespond;
import com.example.panagiotis.retrofit2_post_get.pojo.post.User;
import com.example.panagiotis.retrofit2_post_get.pojo.post.get.ServerResults;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface IData {

    @POST(Constants.Relative_Post_Details_URL)
    Observable<PostRespond> createUser(
            @Body User user
    );

    @GET("users/{userid}")
    Observable<ServerResults> getServerResults(
            @Path("userid") String userid
    );


}
