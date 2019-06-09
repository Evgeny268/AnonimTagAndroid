package com.tagchat;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class DialogActivity extends AppCompatActivity {

    EditText editText;
    ListView listView;
    MessageAdapter messageAdapter;
    MessageRender messageRender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        long mills = 100L;
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(mills);
        }
        listView = findViewById(R.id.listViewMessages);
        messageAdapter = new MessageAdapter(this);
        listView.setAdapter(messageAdapter);
        editText = findViewById(R.id.editTextMessageText);
        messageRender = new MessageRender(this);
        messageRender.execute();
    }

    public void onClickSendMessage(View view) {
        Transfer transfer = new Transfer(editText.getText().toString());
        SendMessage sendMessage = new SendMessage();
        sendMessage.sendMessage(transfer);
        final Message message = new Message(transfer.getText().toString(),true);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageAdapter.add(message);
                listView.setSelection(listView.getCount()-1);
            }
        });
        editText.setText("");
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        messageRender.cancel(true);
        MessageListener.stopListen();
        InternetWorker.close();
        startActivity(new Intent(DialogActivity.this, DialogSettingActivity.class));

    }

    public void goBack(){
        messageRender.cancel(true);
        MessageListener.stopListen();
        InternetWorker.close();
        startActivity(new Intent(DialogActivity.this, DialogSettingActivity.class));
    }
}
