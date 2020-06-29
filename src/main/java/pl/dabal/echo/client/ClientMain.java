package pl.dabal.echo.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClientMain {
    public static void main(String[] args) throws InterruptedException {

        int numberOfThreads = 5;
        ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            pool.submit(new ClientThread());
        }
        pool.shutdown();
        pool.awaitTermination(30, TimeUnit.SECONDS);
    }
}
