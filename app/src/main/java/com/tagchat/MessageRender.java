package com.tagchat;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.util.Log;

import java.util.ArrayList;

public class MessageRender extends AsyncTask<Transfer,Transfer,Transfer> {
    DialogActivity dialogActivity;

    public MessageRender(DialogActivity dialogActivity) {
        this.dialogActivity = dialogActivity;
    }

    @Override
    protected Transfer doInBackground(Transfer... transfers) {
        while (!isCancelled()){
            ArrayList<Transfer> list = null;
            list = MessageListener.getTransfer();
            if (list.size()==0) {
                synchronized (MessageListener.lock2) {
                    try {
                        MessageListener.lock2.wait();
                        list = MessageListener.getTransfer();
                    } catch (InterruptedException e) {
                        Log.println(1, "ANONIMTAG", "Ошибка синхронизации MessageRender");
                        e.printStackTrace();
                    }
                }
            }
            if (list.size()!=0){
                for (int i = 0; i < list.size(); i++) {
                    publishProgress(list.get(i));
                }
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Transfer... values) {
        long mills = 100L;
        Vibrator vibrator = (Vibrator) dialogActivity.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(mills);
        }
        for (int i = 0; i < values.length; i++) {

            if (values[i].getText().equals("/stop")){
                dialogActivity.goBack();
            }else {
                Message message = new Message(values[i].getText(), false);
                dialogActivity.messageAdapter.add(message);
                dialogActivity.listView.setSelection(dialogActivity.listView.getCount() - 1);
            }

        }
    }
}
