package com.example.panagiotis.retrofit2_post_get.postData;

import com.example.panagiotis.retrofit2_post_get.Services.Connection;
import com.example.panagiotis.retrofit2_post_get.Services.IData;
import com.example.panagiotis.retrofit2_post_get.pojo.post.PostRespond;
import com.example.panagiotis.retrofit2_post_get.pojo.post.User;
import com.example.panagiotis.retrofit2_post_get.realmModels.User_local_data;

import io.realm.Realm;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Panagiotis on 25/01/2017.
 */

public class PostData_Presenter implements IContract_PostData.IPresenter_PostData {

    private IData iData;
    private IContract_PostData.IView_PostData iView_postData;
    private Realm realm;

    public PostData_Presenter(IContract_PostData.IView_PostData iView_postData) {
        this.iView_postData = iView_postData;
    }

    @Override
    public void postData(String email, final String password) {
        iView_postData.showProgressDialog();
        iData= Connection.getConnection_Post();
        iData.createUser(new User(email,password))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PostRespond>() {
                    @Override
                    public void onCompleted() {
                        iView_postData.dismissProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        iView_postData.dismissProgressDialog();
                    }

                    @Override
                    public void onNext(final PostRespond postRespond) {
                        realm = Realm.getDefaultInstance();
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realm.delete(User_local_data.class);
                            }
                        });

                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                User_local_data userLocalData = realm.createObject(User_local_data.class);
                                userLocalData.setToken(postRespond.getToken());
                                userLocalData.setUserid(postRespond.getUserid());
                                userLocalData.setPassword(password);
                            }
                        });
                        iView_postData.transferToProfile();
                    }
                });
    }

    @Override
    public void start() {
        iView_postData.setPresenter(this);
    }
}
