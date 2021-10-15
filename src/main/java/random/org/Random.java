package random.org;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Random {

    private String jsonrpc;
    private RandomResult result;
    private int id;
}
