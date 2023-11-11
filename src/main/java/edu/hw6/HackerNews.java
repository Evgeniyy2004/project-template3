package edu.hw6;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

public class HackerNews {
    public long[] hackerNewsTopStories() {
        try {
            var request = HttpRequest.newBuilder()
                .uri(new URI("https://hacker-news.firebaseio.com/v0/topstories.json"))
                .GET()
                .header("nothing", "special")
                .timeout(Duration.of())

        } catch (URISyntaxException e) {
            return new long[0];
        }
    }

    public String news(long id) {

    }
}
