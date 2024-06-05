package com.unodos;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private ServerSocket listenSocket;
    private Class<? extends Service> service;

    public Server(Class<? extends Service> service, int port) throws IOException {
        this.service = service;
        listenSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        try {
            while(true) {
                Socket client = listenSocket.accept();
                Service newService = service.getConstructor(Socket.class).newInstance(client);
                newService.launch();
            }
        } catch (IOException e) {
            try { this.listenSocket.close(); }
            catch (IOException _e) {};
            System.err.println("[Listener error] "+e.getMessage());
        } catch (InvocationTargetException | InstantiationException
                 | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
