package com.tagchat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WaitingScreen extends AppCompatActivity {

    ClientWaiter clientWaiter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Transfer transfer = (Transfer) getIntent().getSerializableExtra("Transfer");
        clientWaiter = new ClientWaiter(this);
        clientWaiter.execute(transfer);
    }

    public void goToDialog(){
        Intent intent = new Intent(WaitingScreen.this, DialogActivity.class);
        startActivity(intent);
    }

    public void onClickStopSearch(View view) {
     clientWaiter.cancel(true);
     MessageListener.stopListen();
     InternetWorker.close();
     startActivity(new Intent(WaitingScreen.this,DialogSettingActivity.class));
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }
}
