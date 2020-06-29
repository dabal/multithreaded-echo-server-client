package pl.dabal.echo.client;

import java.io.IOException;

public class ClientThread implements Runnable {


    @Override
    public void run() {
        EchoClient echoClient = new EchoClient();
        try {
            echoClient.startConnection("127.0.0.1",6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println(echoClient.sendMessage(String.format("%s - %d", Thread.currentThread().getName(), i)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            echoClient.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
