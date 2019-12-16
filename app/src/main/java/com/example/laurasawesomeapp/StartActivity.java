package com.example.laurasawesomeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StartActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_acitivity);

        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.example.laurasawesomeapp", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        /*
        Display splash
         */
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                if(currentUser == null)
                {
                    Intent notAssignedIntent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(notAssignedIntent);
                }
                else
                {
                    signOut();
                    Intent assignedIntent = new Intent(StartActivity.this, ChatLobbyActivity.class);
                    startActivity(assignedIntent);
                }
                finish();

            }
        },SPLASH_TIME_OUT);
    }
    public boolean checkIfInsigned(){
        return false;
    }
    public void signOut() {
        mAuth.signOut();
        LoginManager.getInstance().logOut();
    }
}
