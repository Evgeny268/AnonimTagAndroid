package com.tagchat;

import java.io.IOException;
import java.util.ArrayList;

public class SendMessage extends Thread{
    private ArrayList<Transfer> list = new ArrayList<>();

    public void sendMessage(Transfer transfer){
        synchronized (this) {
            list.add(transfer);
        }
        this.start();
    }

    @Override
    public void run() {
        synchronized (this){
            for (int i = 0; i < list.size(); i++) {
                try {
                    InternetWorker.getOos().writeObject(list.get(i));
                    InternetWorker.getOos().flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
