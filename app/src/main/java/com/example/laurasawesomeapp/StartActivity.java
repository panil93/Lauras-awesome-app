package com.example.laurasawesomeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.facebook.AccessToken;

public class StartActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    private boolean isTheUserAssigned;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_acitivity);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        isTheUserAssigned = accessToken != null && !accessToken.isExpired();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                if(isTheUserAssigned==true) {
                    Intent assignedIntent = new Intent(StartActivity.this, ChatLobbyActivity.class);
                    startActivity(assignedIntent);
                }
                else{
                    Intent notAssignedIntent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(notAssignedIntent);
                }
                finish();

            }
        },SPLASH_TIME_OUT);
    }
    public boolean checkIfInsigned(){
        return false;
    }
}
