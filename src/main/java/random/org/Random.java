package random.org;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Random {

    private String jsonrpc;
    private RandomResult result;
    private int id;

    public String getFirstResult() {
        return result.getRandom().getData().getFirst();
    }

    public List<String> getListResult() {
        return result.getRandom().getData();
    }
}
