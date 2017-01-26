package com.example.panagiotis.retrofit2_post_get;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.panagiotis.retrofit2_post_get.Fragments.Login_Fragment;
import com.example.panagiotis.retrofit2_post_get.Fragments.Profile_Fragment;
import com.example.panagiotis.retrofit2_post_get.realmModels.User_local_data;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();
        if(realm.where(User_local_data.class).count()==0){
            fragment_transfer(new Login_Fragment());
        }
        else {
            fragment_transfer(new Profile_Fragment());
        }

    }

    public void fragment_transfer(Fragment f) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment;
        fragment = f;
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
