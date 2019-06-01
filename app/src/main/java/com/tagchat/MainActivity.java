package com.tagchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    Button buttonBegin;
    CheckBox checkBoxTerms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonBegin = findViewById(R.id.buttonAppBegin);
        checkBoxTerms = findViewById(R.id.checkBoxTerms);
    }

    public void onClickButtonBegin(View view){
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
}
