package learn.mt.mpogr.httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class WordCountHandler implements HttpHandler {
    private final String text;

    public WordCountHandler(String text) {
        this.text = text;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        String[] keyValue = query.split("=");
        String action = keyValue[0];
        String word = keyValue[1];
        if (!"word".equals(action)) {
            exchange.sendResponseHeaders(400, 0);
            return;
        }
        long time = System.currentTimeMillis();
        long count = countWord(word);
        time = System.currentTimeMillis() - time;
        System.out.printf("Searching of '%s' took %d milliseconds%n", word, time);
        byte[] responseBytes = Long.toString(count).getBytes();
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream out = exchange.getResponseBody();
        out.write(responseBytes);
        out.close();
    }

    private long countWord(String word) {
        long count = 0;
        int index = 0;
        int wordLength = word.length();
        while (index >= 0) {
            index = text.indexOf(word, index);
            if (index >= 0) {
                count++;
                index += wordLength;
            }
        }
        return count;
    }
}
