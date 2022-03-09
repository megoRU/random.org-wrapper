package random.org.randomwrapper;

import org.json.JSONObject;
import random.org.Random;
import random.org.RandomPOJORandom;
import random.org.RandomResult;
import random.org.request.RandomRequest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;

public final class RandomWrapper {

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
        Random randomPOJO = new Random();
        try {
            RandomRequest randomRequest = new RandomRequest(n, min, max, API_KEY);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .POST(HttpRequest.BodyPublishers.ofString(randomRequest.toString()))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();

            if (debugging) System.out.println(body);

            JSONObject obj = new JSONObject(body);

            String[] split = obj
                    .getJSONObject("result").getJSONObject("random").getJSONArray("data")
                    .toString()
                    .replaceAll("\\[", "")
                    .replaceAll("]", "")
                    .split(",");

            randomPOJO.setJsonrpc(obj.getString("jsonrpc"));
            randomPOJO.setResult(
                    new RandomResult(
                            new RandomPOJORandom(
                                    new ArrayList<>(Arrays.asList(split)),
                                    obj.getJSONObject("result").getJSONObject("random").getString("completionTime")
                            ),

                            obj.getJSONObject("result").getInt("bitsUsed"),
                            obj.getJSONObject("result").getInt("bitsLeft"),
                            obj.getJSONObject("result").getInt("requestsLeft"),
                            obj.getJSONObject("result").getInt("advisoryDelay")

                    ));
            randomPOJO.setId(obj.getInt("id"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return randomPOJO;
    }
}