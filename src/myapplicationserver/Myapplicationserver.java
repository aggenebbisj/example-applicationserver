package myapplicationserver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Myapplicationserver {

    private static final int SERVER_PORT = 8000;
    
    public void run() {
        ServerSocket serverSocket = openServerSocket();
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                OutputStream output = clientSocket.getOutputStream();
                final long currentTimeMillis = System.currentTimeMillis();
                output.write(("HTTP/1.1 200 OK\n\n<html><body><h1>Hello Singlethreaded Server " +
                        currentTimeMillis + "</h1></body></html>").getBytes());
                output.close();
                System.out.println("Handled request @ " + currentTimeMillis);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private ServerSocket openServerSocket() {
        try {
            return new ServerSocket(SERVER_PORT);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        new Myapplicationserver().run();        
    }
    
}
