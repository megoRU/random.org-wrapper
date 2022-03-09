package random.org;

public class RandomList {

    private final String apiKey;
    private final int n;
    private final int min;
    private final int max;
    private final boolean replacement = false;

    public RandomList(int n, int min, int max, String apiKey) {
        this.n = n;
        this.min = min;
        this.max = max;
        this.apiKey = apiKey;
    }

    @Override
    public String toString() {
        return "{" +
                "\"apiKey\": \"" + apiKey + "\"" +
                ", \"n\": " + n +
                ", \"min\": " + min +
                ", \"max\": " + max +
                ", \"replacement\": " + replacement +
                '}';
    }
}
