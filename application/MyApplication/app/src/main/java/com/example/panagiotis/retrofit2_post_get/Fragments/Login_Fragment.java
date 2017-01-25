package com.example.panagiotis.retrofit2_post_get.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panagiotis.retrofit2_post_get.R;
import com.example.panagiotis.retrofit2_post_get.postData.IContract_PostData;
import com.example.panagiotis.retrofit2_post_get.postData.PostData_Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class Login_Fragment extends Fragment implements IContract_PostData.IView_PostData {

    private ProgressDialog pDialog;
    private IContract_PostData.IPresenter_PostData iPresenter_postData;
    @BindView(R.id.login_password) TextView loginPassword;
    @BindView(R.id.login_user_name) TextView loginUserName;
    @BindView(R.id.login_picture) ImageView loginImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iPresenter_postData=new PostData_Presenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login_, container, false);
        ButterKnife.bind(this,v);

        return v;
    }
    @OnClick(R.id.login_button)
    public void loginFunction(){
        iPresenter_postData.postData(
                String.valueOf(loginUserName.getText()),
                String.valueOf(loginPassword.getText())
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        iPresenter_postData.start();
    }

    @Override
    public void showProgressDialog() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if(pDialog.isShowing() || pDialog!=null){
            pDialog.dismiss();
        }
    }

    @Override
    public void setPresenter(IContract_PostData.IPresenter_PostData presenter) {
        this.iPresenter_postData=checkNotNull(presenter);
    }
}
