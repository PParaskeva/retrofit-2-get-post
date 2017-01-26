package com.example.panagiotis.retrofit2_post_get.getData;

import com.example.panagiotis.retrofit2_post_get.MVP.BasedPresenter;
import com.example.panagiotis.retrofit2_post_get.MVP.BasedView;
import com.example.panagiotis.retrofit2_post_get.pojo.post.get.ServerResults;

/**
 * Created by Panagiotis on 25/01/2017.
 */

public interface IContract_GetData {
    public interface IPresenter_GetData extends BasedPresenter {
        public void GetData(String userID,String token);
        public void postImageAvatar(String userid,String avatar);
    }

    public interface IView_GetData extends BasedView<IContract_GetData.IPresenter_GetData> {
        public void showProgressDialog();
        public void dismissProgressDialog();
        public void presentUserData(ServerResults serverResults);
    }
}
