package com.tagchat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class InternetWorker implements Runnable{
    private static Socket socket;
    private static ObjectOutputStream oos;
    public static ObjectInputStream ois;

    @Override
    public void run() {
        init();
    }

    private static boolean init(){
        try {
            socket = new Socket("ecombine.ddns.net",4004);
        } catch (IOException e) {
            return false;
        }
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        }catch (IOException e) {
            return false;
        }
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

    public static ObjectOutputStream getOos() {
        return oos;
    }

    public static ObjectInputStream getOis() {
        return ois;
    }
}
