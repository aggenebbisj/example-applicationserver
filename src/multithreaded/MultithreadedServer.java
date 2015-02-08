
package multithreaded;

import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedServer {
    private static final int SERVER_PORT = 8000;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        System.out.println("Server listening on port " + SERVER_PORT + "...");
        while (true) {
            // Block until client sends something to server
            Socket clientSocket = serverSocket.accept();
            // Spawn a new worker thread to handle request
            new Thread(new Worker(clientSocket)).start();
        }
    }
    
}
