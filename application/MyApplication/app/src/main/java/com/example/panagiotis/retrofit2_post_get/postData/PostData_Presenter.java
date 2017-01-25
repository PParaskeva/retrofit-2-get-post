package com.example.panagiotis.retrofit2_post_get.postData;

import com.example.panagiotis.retrofit2_post_get.Services.IData;

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
        
    }

    @Override
    public void start() {
        iView_postData.setPresenter(this);
    }
}
