package com.exam.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
//Code Comment:Web socket server does not use this one in the project
public class SocketThread extends Thread {

    private final ServerSocket serverSocket;

    public SocketThread(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                // handle the socket connection here
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() throws IOException {
        serverSocket.close();
    }
}
