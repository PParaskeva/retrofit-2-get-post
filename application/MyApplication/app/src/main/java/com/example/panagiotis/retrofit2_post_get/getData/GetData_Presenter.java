package com.example.panagiotis.retrofit2_post_get.getData;

import com.example.panagiotis.retrofit2_post_get.Services.Connection;
import com.example.panagiotis.retrofit2_post_get.Services.IData;
import com.example.panagiotis.retrofit2_post_get.pojo.post.Avatar;
import com.example.panagiotis.retrofit2_post_get.pojo.post.User;
import com.example.panagiotis.retrofit2_post_get.pojo.post.get.ServerResults;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class GetData_Presenter implements IContract_GetData.IPresenter_GetData {
    private IData iData;
    private IContract_GetData.IView_GetData iView_getData;

    public GetData_Presenter(IContract_GetData.IView_GetData iView_getData) {
        this.iView_getData = iView_getData;
    }

    @Override
    public void GetData(String userID, String token) {
        iView_getData.showProgressDialog();
        iData= Connection.getConnection_Get();
        iData.getServerResults(userID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ServerResults>() {
                    @Override
                    public void onCompleted() {
                        iView_getData.dismissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView_getData.dismissProgressDialog();
                    }

                    @Override
                    public void onNext(ServerResults serverResults) {
                        iView_getData.presentUserData(serverResults);
                    }
                });
    }

    @Override
    public void postImageAvatar(String userid, String avatar) {
        iView_getData.showProgressDialog();
        iData= Connection.getConnection_Post();
        iData.postAvatar(userid,new Avatar(avatar))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        iView_getData.dismissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView_getData.dismissProgressDialog();
                    }

                    @Override
                    public void onNext(User user) {

                    }
                });
    }

    @Override
    public void start() {
        iView_getData.setPresenter(this);
    }
}
