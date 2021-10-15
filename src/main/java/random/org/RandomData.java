package random.org;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class RandomData {

    private List<Integer> list;

    protected RandomData(Integer number) {
        list = new ArrayList<>();
        list.add(number);
    }
}