package com.unodos;

import java.net.Socket;

public abstract class Service implements Runnable {
    private Socket socket;

    public Service(Socket socket) {
        this.socket = socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void launch() {
        new Thread(this).start();
    }
}
