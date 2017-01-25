package com.example.panagiotis.retrofit2_post_get.postData;

import com.example.panagiotis.retrofit2_post_get.Services.Connection;
import com.example.panagiotis.retrofit2_post_get.Services.IData;
import com.example.panagiotis.retrofit2_post_get.pojo.post.User;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Panagiotis on 25/01/2017.
 */

public class PostData_Presenter implements IContract_PostData.IPresenter_PostData {

    IData iData;
    IContract_PostData.IView_PostData iView_postData;

    public PostData_Presenter(IContract_PostData.IView_PostData iView_postData) {
        this.iView_postData = iView_postData;
    }

    @Override
    public void postData(String email, String password) {
        iView_postData.showProgressDialog();
        iData= Connection.getConnection_Post();
        iData.createUser(new User(email,password))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        iView_postData.dismissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView_postData.dismissProgressDialog();
                    }

                    @Override
                    public void onNext(User user) {

                    }
                });
    }

    @Override
    public void start() {
        iView_postData.setPresenter(this);
    }
}
