package random.org;

import java.util.ArrayList;
import java.util.List;

public class RandomPOJORandom {

    private List<RandomData> data = new ArrayList<>();
    private String completionTime;

    protected RandomPOJORandom() {}

    protected RandomPOJORandom(List<RandomData> data, String completionTime) {
        this.data = data;
        this.completionTime = completionTime;
    }

    protected RandomPOJORandom(String completionTime) {
        this.completionTime = completionTime;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public List<RandomData> getData() {
        return data;
    }
}
