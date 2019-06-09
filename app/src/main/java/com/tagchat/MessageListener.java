package com.tagchat;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MessageListener extends Thread {

    private static ArrayList<Transfer> list = new ArrayList<>();
    public static Object lock = new Object();
    public static Object lock2 = new Object();
    public static boolean shutdown = false;
    @Override
    public void run() {
        Thread in = new Thread(new InternetWorker());
        in.start();
        try {
            in.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!isInterrupted() && !shutdown) {
            Transfer tin = null;
            try {
                tin = (Transfer) InternetWorker.ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
                Log.println(1,"ANONIMTAG","Listener ошибка при чтении tIn");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Log.println(1,"ANONIMTAG","Listener ошибка дессириализации");
            }
            synchronized (lock) {
                list.add(tin);
            }
            synchronized (lock2){
                lock2.notify();
            }
        }
        shutdown = false;
        list.clear();
    }

    public static ArrayList<Transfer> getTransfer(){
        ArrayList<Transfer> tempList;
        synchronized (lock){
            tempList = new ArrayList<>(list);
            list.clear();
        }
        return tempList;
    }

    public static void stopListen(){
        shutdown = true;
    }
}
