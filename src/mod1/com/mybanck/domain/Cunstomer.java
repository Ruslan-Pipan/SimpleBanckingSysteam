package mod1.com.mybanck.domain;

import java.util.ArrayList;
import java.util.List;

public class Cunstomer {
    private List<Account> accounts;
    private String fullName;
    private int custumerNumber;
    private static int custumerNumberBase = 1000;

    public Cunstomer(String fullName) {
        accounts = new ArrayList();
        this.fullName = fullName;
        this.custumerNumber = custumerNumberBase+1;
    }

    public Account getAccount(int accNo){
        if (accNo < accounts.size()){
            return accounts.get(accNo);
        }
        return null;
    }

    public void addAccount(Account acc){
        accounts.add(acc);
    }

    @Override
    public String toString() {
        return "Cunstomer{" +
                "accounts=" + accounts +
                ", fullName='" + fullName + '\'' +
                ", custumerNumber=" + custumerNumber +
                '}';
    }
}
