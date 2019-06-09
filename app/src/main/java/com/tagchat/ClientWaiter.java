package com.tagchat;

import android.os.AsyncTask;

public class ClientWaiter extends AsyncTask<Transfer,Void,Void> {
    WaitingScreen waitingScreen;

    public ClientWaiter(WaitingScreen waitingScreen) {
        this.waitingScreen = waitingScreen;
    }


    @Override
    protected Void doInBackground(Transfer... transfers) {
        Thread thread = new Thread(new InternetWorker());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MessageListener messageListener = new MessageListener();
        messageListener.start();
        InternetWorker.sentObject(transfers[0]);
        while (MessageListener.getTransfer().size()==0 && !isCancelled()){

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        waitingScreen.goToDialog();
    }
}
