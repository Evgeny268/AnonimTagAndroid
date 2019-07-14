package com.tagchat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mSettings;
    Button buttonBegin;
    CheckBox checkBoxTerms;
    boolean firstStart = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mSettings = getSharedPreferences(AppSetting.APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains(AppSetting.APP_FIRST_START)){
            firstStart = mSettings.getBoolean(AppSetting.APP_FIRST_START,false);
            if (firstStart) startActivity(new Intent(MainActivity.this,DialogSettingActivity.class));
        }
        buttonBegin = findViewById(R.id.buttonAppBegin);
        checkBoxTerms = findViewById(R.id.checkBoxTerms);
    }

    public void onClickButtonBegin(View view){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putBoolean(AppSetting.APP_FIRST_START,true);
        editor.apply();
        Intent intent = new Intent(MainActivity.this, DialogSettingActivity.class);
        startActivity(intent);
    }

    public void onClickTerms(View view){
        if (checkBoxTerms.isChecked()){
            buttonBegin.setEnabled(true);
        }else {
            buttonBegin.setEnabled(false);
        }
    }

    @Override
    public void onBackPressed() {

    }
}
