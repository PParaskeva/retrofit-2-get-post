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
import com.example.panagiotis.retrofit2_post_get.getData.GetData_Presenter;
import com.example.panagiotis.retrofit2_post_get.getData.IContract_GetData;
import com.example.panagiotis.retrofit2_post_get.pojo.post.get.ServerResults;
import com.example.panagiotis.retrofit2_post_get.realmModels.User_local_data;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class Profile_Fragment extends Fragment implements IContract_GetData.IView_GetData {

    @BindView(R.id.Profile_email) TextView profileEmail;
    @BindView(R.id.Profile_Password) TextView profilePassword;
    @BindView(R.id.imageView) ImageView profileImage;

    private ProgressDialog pDialog;
    private IContract_GetData.IPresenter_GetData iPresenter_getData;
    private Realm realm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iPresenter_getData=new GetData_Presenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_profile_, container, false);
        ButterKnife.bind(this,v);
        realm = Realm.getDefaultInstance();
        RealmResults<User_local_data> results =
                realm.where(User_local_data.class).findAll();
        iPresenter_getData.GetData(results.get(0).getUserid(),
                results.get(0).getToken());
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        iPresenter_getData.start();
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
    public void presentUserData(ServerResults serverResults) {
        profileEmail.setText(serverResults.getEmail());
        realm = Realm.getDefaultInstance();
        RealmResults<User_local_data> results =
                realm.where(User_local_data.class).findAll();
        profilePassword.setText(results.get(0).getPassword());

        Picasso.with(getActivity())
                .load(results.get(0).getImageUrl())
                .fit()
                .centerCrop()
                .into(profileImage);
    }

    @Override
    public void setPresenter(IContract_GetData.IPresenter_GetData presenter) {
        this.iPresenter_getData=checkNotNull(presenter);
    }
}
