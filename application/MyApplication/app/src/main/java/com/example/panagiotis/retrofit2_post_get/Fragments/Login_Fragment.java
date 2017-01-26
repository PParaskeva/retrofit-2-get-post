package com.example.panagiotis.retrofit2_post_get.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.panagiotis.retrofit2_post_get.Constants.Constants;
import com.example.panagiotis.retrofit2_post_get.MainActivity;
import com.example.panagiotis.retrofit2_post_get.R;
import com.example.panagiotis.retrofit2_post_get.postData.IContract_PostData;
import com.example.panagiotis.retrofit2_post_get.postData.PostData_Presenter;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class Login_Fragment extends Fragment implements IContract_PostData.IView_PostData {

    private ProgressDialog pDialog;
    private IContract_PostData.IPresenter_PostData iPresenter_postData;
    private Realm realm;
    private String imageUri="";
    private Bitmap bitmap;
    @BindView(R.id.login_password) EditText loginPassword;
    @BindView(R.id.login_user_name) EditText loginUserName;
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
                String.valueOf(loginPassword.getText()),
                imageUri
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
    public void transferToProfile() {
        ((MainActivity)getActivity()).fragment_transfer(new Profile_Fragment());
    }

    @Override
    public void setPresenter(IContract_PostData.IPresenter_PostData presenter) {
        this.iPresenter_postData=checkNotNull(presenter);
    }

    @OnClick(R.id.login_picture)
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
                loginImage.setImageBitmap(bitmap);
                imageUri=BitMapToString(bitmap);

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

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

}
