package random.org.randomwrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import random.org.Random;
import random.org.RandomRequest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RandomWrapper {

    private final String URL = "https://api.random.org/json-rpc/4/invoke";
    private final String API_KEY;
    private final int n;
    private final int min;
    private final int max;
    private final boolean replacement;
    private final boolean debugging;

    public RandomWrapper(String api_key, int n, int min, int max, boolean replacement, boolean debugging) {
        API_KEY = api_key;
        this.n = n;
        this.min = min;
        this.max = max;
        this.replacement = replacement;
        this.debugging = debugging;
    }

    public Random sendData() {
        Random randomPOJO = null;
        try {
            RandomRequest randomRequest = new RandomRequest(n, min, max, API_KEY);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .POST(HttpRequest.BodyPublishers.ofString(randomRequest.toString()))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (debugging) System.out.println(response.body());

            randomPOJO = new ObjectMapper().readValue(response.body(), Random.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return randomPOJO;
    }
}