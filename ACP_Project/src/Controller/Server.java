package Controller;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4444)) {
            while (true){
                new Controller(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("IOException with Server: " + e.getMessage());
        }
    }
}
