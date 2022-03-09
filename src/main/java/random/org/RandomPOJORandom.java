package random.org;

import java.util.ArrayList;
import java.util.List;

public class RandomPOJORandom {

    private List<String> data = new ArrayList<>();
    private String completionTime;

    public RandomPOJORandom() {}

    public RandomPOJORandom(List<String> data, String completionTime) {
        this.data = data;
        this.completionTime = completionTime;
    }

    public RandomPOJORandom(String completionTime) {
        this.completionTime = completionTime;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public List<String> getData() {
        return data;
    }
}
