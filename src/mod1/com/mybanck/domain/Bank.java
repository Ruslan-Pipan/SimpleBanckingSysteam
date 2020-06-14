package mod1.com.mybanck.domain;


import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Cunstomer> cunstomers = new ArrayList<>();
    private int numClient = 0;
    private static Bank bank = new Bank();

    private Bank() {
    }

    public static Bank getBank(){
        return bank;
    }

    public Cunstomer getCunstomer(int custNo){
        if (custNo < cunstomers.size())
            return cunstomers.get(custNo);
        return null;
    }

    public void addCunstomer(Cunstomer newCunstomer){
        cunstomers.add(newCunstomer);
        numClient++;
    }
}
