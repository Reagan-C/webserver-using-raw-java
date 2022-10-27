import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5000)){
            System.out.println("Server started");
            while (true) {
                new ServerThread(serverSocket.accept()).start();

                System.out.println("The client " + serverSocket.getInetAddress() + ": "
                + serverSocket.getLocalPort() + " connected");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}