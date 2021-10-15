package random.org;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RandomResult {

    private RandomPOJORandom random;
    private int bitsUsed;
    private int bitsLeft;
    private int requestsLeft;
    private int advisoryDelay;
}
