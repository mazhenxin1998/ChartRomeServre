package com.mzx.serverread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author ZhenXinMa
 * @date 2020/1/30 21:09
 */
public class ThreadServerRead implements Runnable {

    private Socket socket;
    private DataInputStream in;
    private volatile boolean flag = true;

    public ThreadServerRead(Socket socket){
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        try {
            String first_message = in.readUTF();
            System.out.println(first_message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(flag){




        }

    }

}
