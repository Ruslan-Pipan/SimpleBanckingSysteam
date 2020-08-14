package mod1.com.mybanck.domain;


import loger.BadLog;
import loger.GoodLog;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Consumer> consumers = new ArrayList<>();
    private static int numClient = 0;
    private static Bank bank = new Bank();

    private Bank() {
    }

    public static Bank getBank(){
        GoodLog.getInstance().log("Return instance bank.");
        return bank;
    }

    public Consumer getCunstomer(int custNo){
        if (custNo < consumers.size()){
            GoodLog.getInstance().log("Get consumer id:" + custNo);
            return consumers.get(custNo);
        }
        BadLog.getInstance().log("Consumer dont get, consumer " + custNo + "not in the bank.");
        return null;
    }

    public void addConsumer(Consumer newConsumer){
        consumers.add(newConsumer);
        numClient++;
        GoodLog.getInstance().log("Consumer add in system under the number" + numClient);
    }
}
