package com.example.panagiotis.retrofit2_post_get.postData;

import com.example.panagiotis.retrofit2_post_get.MVP.BasedPresenter;
import com.example.panagiotis.retrofit2_post_get.MVP.BasedView;

public interface IContract_PostData {
    public interface IPresenter_PostData extends BasedPresenter{
        public void postData(String email,String password);
    }

    public interface IView_PostData extends BasedView<IContract_PostData.IPresenter_PostData>{
        public void showProgressDialog();
        public void dismissProgressDialog();
        public void transferToProfile();
    }
}
