package random.org;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class RandomData {

    private List<Integer> list;

    protected RandomData(List<Integer> list) {
        this.list = list;
    }
}