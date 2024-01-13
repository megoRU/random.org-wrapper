package random.org;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
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

}