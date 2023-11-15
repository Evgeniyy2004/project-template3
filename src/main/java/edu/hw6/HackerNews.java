package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import static java.net.http.HttpClient.newHttpClient;

public class HackerNews {
    public long[] hackerNewsTopStories() {
        try {
            var request = HttpRequest.newBuilder()
                .uri(new URI("https://hacker-news.firebaseio.com/v0/topstories.json"))
                .GET()
                .header("nothing", "special")
                .timeout(Duration.of(30, ChronoUnit.SECONDS))
                .build();
            var client = newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
            var str = client.body().split("]|,|\\[");
            var res = Arrays.stream(str).filter(x -> !x.isEmpty()).toList();
            long[] result = new long[(int) res.size()];
            for (int i = 0; i < res.size(); i++) {
                result[i] = Long.parseLong(res.get(i));
            }
            return result;

        } catch (URISyntaxException | IOException | InterruptedException e) {
            return new long[0];
        }

    }

    public String news(long id) throws IOException, URISyntaxException {
        String myRegex = String.format("https://hacker-news.firebaseio.com/v0/item/%d.json", id);
        return (new JSONObject(IOUtils.toString(new URL(myRegex)))).get("title").toString();
    }
}
