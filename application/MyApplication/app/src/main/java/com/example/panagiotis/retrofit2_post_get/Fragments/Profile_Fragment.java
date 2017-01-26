package com.example.panagiotis.retrofit2_post_get.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panagiotis.retrofit2_post_get.Constants.Constants;
import com.example.panagiotis.retrofit2_post_get.R;
import com.example.panagiotis.retrofit2_post_get.getData.GetData_Presenter;
import com.example.panagiotis.retrofit2_post_get.getData.IContract_GetData;
import com.example.panagiotis.retrofit2_post_get.pojo.post.get.ServerResults;
import com.example.panagiotis.retrofit2_post_get.realmModels.User_local_data;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    private Bitmap bitmap;
    private String userID="";

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
        userID=results.get(0).getUserid();
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
        RealmResults<User_local_data> results = realm.where(User_local_data.class).findAll();

        profilePassword.setText(results.get(0).getPassword());

        profileImage.setImageBitmap(StringToBitMap(results.get(0).getImageUrl()));
    }

    @Override
    public void setPresenter(IContract_GetData.IPresenter_GetData presenter) {
        this.iPresenter_getData=checkNotNull(presenter);
    }

    @OnClick(R.id.imageView)
    public void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, Constants.REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        InputStream stream = null;
        if (requestCode == Constants.REQUEST_CODE && resultCode == Activity.RESULT_OK)
            try {
                if (bitmap != null) {
                    bitmap.recycle();
                }
                stream = getActivity().getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);
                profileImage.setImageBitmap(bitmap);
                final User_local_data userLocalData = realm.where(User_local_data.class).findFirst();

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        userLocalData.setImageUrl(BitMapToString(bitmap));
                    }
                });
                iPresenter_getData.postImageAvatar(userID,BitMapToString(bitmap));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (stream != null)
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
