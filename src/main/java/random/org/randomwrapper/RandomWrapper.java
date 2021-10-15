package random.org.randomwrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import random.org.Random;
import random.org.RandomRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
            ObjectMapper objectMapper = new ObjectMapper();
            java.net.URL url = new URL(URL);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = randomRequest.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            StringBuilder response = new StringBuilder();

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                if (debugging) {
                    System.out.println(response);
                }
            }
            randomPOJO = objectMapper.readValue(response.toString(), Random.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return randomPOJO;
    }
}