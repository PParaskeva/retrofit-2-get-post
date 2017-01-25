package com.example.panagiotis.retrofit2_post_get.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.panagiotis.retrofit2_post_get.R;
import com.example.panagiotis.retrofit2_post_get.postData.IContract_PostData;
import com.example.panagiotis.retrofit2_post_get.postData.PostData_Presenter;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class Login_Fragment extends Fragment implements IContract_PostData.IView_PostData {

    private ProgressDialog pDialog;
    private IContract_PostData.IPresenter_PostData iPresenter_postData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iPresenter_postData=new PostData_Presenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login_, container, false);
        return v;
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
