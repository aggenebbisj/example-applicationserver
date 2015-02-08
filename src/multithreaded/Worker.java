
package multithreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Worker thread to handle the incoming requests
 */
class Worker implements Runnable {
    private static final String httpHeader = "HTTP/1.1 200\n\n";
    
    // Reference to client that made request
    private final Socket client;

    Worker(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            // Read the first line of the incoming request
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("receiving (first line):" + in.readLine());
            
            // Write the system time back to client
            OutputStream output = client.getOutputStream();
            output.write((httpHeader + "time: " + System.currentTimeMillis()).getBytes());
            System.out.println("Request handled by " + Thread.currentThread().getName());
            
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(MultithreadedServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
