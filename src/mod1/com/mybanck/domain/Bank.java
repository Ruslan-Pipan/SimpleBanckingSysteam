package mod1.com.mybanck.domain;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Cunstomer> cunstomers;
    private int maxCunstomer = 1_000;

    public Bank() {
        this.cunstomers = new ArrayList<>(maxCunstomer);
    }

    public Cunstomer getCunstomer(int custNo){
        if (custNo < cunstomers.size())
            return cunstomers.get(custNo);
        return null;
    }

    public void addCunstomer(Cunstomer newCunstomer){
        if (cunstomers.size() <= maxCunstomer)
        cunstomers.add(newCunstomer);
    }
}
