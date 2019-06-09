package com.tagchat;

import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class InternetWorker implements Runnable{
    private static Socket socket;
    private static ObjectOutputStream oos;
    public static ObjectInputStream ois;
    private static boolean alreadyInit = false;

    @Override
    public void run() {
        init();
    }

    private static boolean init(){
        if (alreadyInit) return true;
        try {
            socket = new Socket("ecombine.ddns.net",4005);
        } catch (IOException e) {
            return false;
        }
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        }catch (IOException e) {
            Log.println(1,"ANNONIMTAG","cant create oos and ois");
            return false;
        }
        alreadyInit = true;
        return true;
    }

    public static boolean sentObject(Transfer transfer){
        try {
            oos.writeObject(transfer);
            oos.flush();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static void close(){
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        alreadyInit = false;
    }


    public static ObjectOutputStream getOos() {
        return oos;
    }

    public static ObjectInputStream getOis() {
        return ois;
    }
}
