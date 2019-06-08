package com.tagchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

public class DialogActivity extends AppCompatActivity {

    EditText editText;
    ListView listView;
    MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        listView = findViewById(R.id.listViewMessages);
        messageAdapter = new MessageAdapter(this);
        listView.setAdapter(messageAdapter);
        editText = findViewById(R.id.editTextMessageText);
    }
}
