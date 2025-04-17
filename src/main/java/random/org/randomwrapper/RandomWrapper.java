package random.org.randomwrapper;

import okhttp3.*;
import org.json.JSONObject;
import random.org.Random;
import random.org.RandomPOJORandom;
import random.org.RandomResult;
import random.org.request.RandomRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class RandomWrapper {

    private static final String URL = "https://api.random.org/json-rpc/4/invoke";
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private static final Logger LOGGER = Logger.getLogger(RandomWrapper.class.getName());

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

    public Random sendData() throws RuntimeException {
        Random randomPOJO = new Random();
        String responseBody = null;
        try {
            RandomRequest randomRequest = new RandomRequest(n, min, max, replacement, API_KEY);
            Request.Builder requestBuilder = new Request.Builder()
                    .url(URL)
                    .post(RequestBody.create(randomRequest.toString(), MEDIA_TYPE_JSON))
                    .addHeader("Content-Type", "application/json");

            Request request = requestBuilder.build();

            try (Response response = CLIENT.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    responseBody = Objects.requireNonNull(response.body()).string();
                    if (debugging) System.out.println(responseBody);
                    JSONObject obj = new JSONObject(responseBody);

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
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new RuntimeException(responseBody, e);
        }
        return randomPOJO;
    }
}