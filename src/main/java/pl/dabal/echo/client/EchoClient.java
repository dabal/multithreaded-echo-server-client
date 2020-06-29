package pl.dabal.echo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        EchoClient echoClient = new EchoClient();
        try {
            echoClient.startConnection("127.0.0.1", 6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 100; i++) {
            String response = echoClient.sendMessage(String.format("%s - %d", Thread.currentThread().getName(), i));
            Thread.sleep(100);
            System.out.println(response);
        }
        String response = echoClient.sendMessage(".");
        System.out.println(response);
    }
}