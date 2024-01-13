package random.org.request;

import lombok.Getter;
import random.org.RandomList;

@Getter
public class RandomRequest {

    private final String jsonrpc = "2.0";
    private final String method = "generateIntegers";
    private final RandomList params;
    private final Integer ID = 42;

    public RandomRequest(int n, int min, int max, boolean replacement, String apiKey) {
        this.params = new RandomList(n, min, max, replacement, apiKey);
    }

    @Override
    public String toString() {
        return "{" +
                "\"jsonrpc\": \"" + jsonrpc + "\"" +
                ", \"method\": \"" + method + "\"" +
                ", \"params\": " + params +
                ", \"id\":" + ID +
                '}';
    }
}
