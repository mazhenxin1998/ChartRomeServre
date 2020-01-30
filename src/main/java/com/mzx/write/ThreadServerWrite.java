package com.mzx.write;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author ZhenXinMa
 * @date 2020/1/30 21:06
 */
public class ThreadServerWrite implements Runnable{

    private Socket socket;
    private DataOutputStream out;
    private boolean flag = true;

    public ThreadServerWrite(Socket socket){
        this.socket = socket;
        try {
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while(flag){

        }


    }
}
