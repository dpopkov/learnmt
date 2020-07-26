package learn.mt.mpogr.httpserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThroughputHttpServer {
    private static final String INPUT_FILE = "resources/throughput/war-and-peace.txt";
    private static int numberOfThreads = 1;
    private static int port = 8000;

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            numberOfThreads = Integer.parseInt(args[1]);
        }
        String text = Files.readString(Paths.get(INPUT_FILE));
        startServer(text);
    }

    private static void startServer(String text) throws IOException {
        System.out.printf("Starting HttpServer on port %d using %d threads%n",
                port, numberOfThreads);
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/search", new WordCountHandler(text));
        Executor executor = Executors.newFixedThreadPool(numberOfThreads);
        server.setExecutor(executor);
        server.start();
    }
}
