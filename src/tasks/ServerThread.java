package tasks;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread{

    private Socket socket;
    public static final String JSON_RESPONSE_STARTING = "{\n" ;
    public static final String SLACK_USERNAME = "\"slackUsername\": " + "Reagan, ";
    public static final boolean BACKEND = true;
    public static final int AGE = 28;
    public static final String BIO = ", \"bio\": I am Reagan, a passionate junior backend engineer with java language" +
                                        " who has an eye for details \n";
    public static final String JSON_RESPONSE_ENDING = "}";
    public static final String RESPONSE =  JSON_RESPONSE_STARTING + SLACK_USERNAME + "\"backend\": " + BACKEND +
                                            ", \"age\": " + AGE + BIO + JSON_RESPONSE_ENDING;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("HTTP/1.0 200 OK");
            out.println("Content-type: text/html");
            out.println("Server: Reagss");
            out.println("");
            out.println("<p>" + RESPONSE + "</p>");

        } catch (IOException e) {
            System.out.println("Error reading socket:" + e.getMessage());
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}
