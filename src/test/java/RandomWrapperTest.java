import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import random.org.Random;
import random.org.randomwrapper.RandomWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class RandomWrapperTest {

    private static final Integer[] testData = new Integer[]{0, 1, 2, 3, 4, 5};
    private static final List<Integer> numbers = new ArrayList<>();
    private static Random random;

    @BeforeAll
    static void setUp() {
        numbers.addAll(Arrays.asList(testData));
        random = new RandomWrapper(
                "******-*****-*****-******-**********",
                1,
                0,
                5,
                false,
                false).sendData();
    }

    @Test
    public void isNumberInDiapason() {
        Integer number = Integer.parseInt(random.getResult()
                .getRandom()
                .getData()
                .get(0)
                .getList().toString()
                .replaceAll("\\[", "")
                .replaceAll("]", ""));

        assertTrue(numbers.contains(number));
    }
}
