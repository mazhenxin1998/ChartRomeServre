package com.mzx.server;


import com.mzx.serverread.ThreadServerRead;
import com.mzx.write.ThreadServerWrite;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhenXinMa
 * @date 2020/1/30 21:03
 */
public class Receive {


    public static volatile Map<Integer, ThreadServerWrite> sends = new HashMap<Integer, ThreadServerWrite>();
    public static volatile Map<Integer, ThreadServerRead> receive = new HashMap<Integer, ThreadServerRead>();
    public static volatile Integer user_id = null;

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        ThreadPoolExecutor pool = new ThreadPoolExecutor(100, 100, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });

        try {
            serverSocket = new ServerSocket(10001);
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (true) {

            try {
                System.out.println("开始等待客户端连接");
                clientSocket = serverSocket.accept();
                System.out.println("客户端连接成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //为每个用户开启读写线程
            ThreadServerRead read = new ThreadServerRead(clientSocket);
            pool.execute(read);
            ThreadServerWrite write = new ThreadServerWrite(clientSocket);
            pool.execute(write);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sends.put(user_id, write);
            receive.put(user_id,read);

        }

    }


}
