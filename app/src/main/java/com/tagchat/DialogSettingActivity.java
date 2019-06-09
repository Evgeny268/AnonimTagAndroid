package com.tagchat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class DialogSettingActivity extends AppCompatActivity {

    RadioGroup myGender;
    RadioGroup friendGender;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_setting);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        editText = findViewById(R.id.editTextTags);
        myGender = findViewById(R.id.radioGroupMyGender);
        friendGender = findViewById(R.id.radioGroupInterlocGender);
        myGender.check(R.id.radioButtonInotSet);
        friendGender.check(R.id.radioButtonInterlocAny );
    }

    public void onClickStartSearch(View view) {
        String tags = editText.getText().toString();
        if (tags.length()==0){
            Toast toast = Toast.makeText(getApplicationContext(),
                    R.string.textForgotTags,
                    Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        tags = tags.replace(" ","");
        String []stTags = tags.split(",");
        ArrayList<String> tagsList = new ArrayList<>();
        for (int i = 0; i < stTags.length; i++) {
            tagsList.add(stTags[i]);
        }
        int radioButtonID = myGender.getCheckedRadioButtonId();
        View radioButton = myGender.findViewById(radioButtonID);
        int mySex = myGender.indexOfChild(radioButton);
        radioButtonID = friendGender.getCheckedRadioButtonId();
        radioButton = friendGender.findViewById(radioButtonID);
        int friendSex = friendGender.indexOfChild(radioButton);
        Transfer transfer = new Transfer((byte)mySex,(byte)friendSex,tagsList,false);
        Intent intent = new Intent(DialogSettingActivity.this,WaitingScreen.class);
        intent.putExtra("Transfer",transfer);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }
}
