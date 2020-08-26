package mod1.test;

import mod1.com.mybanck.domain.Consumer;
import mod1.com.mybanck.domain.bankException.BadVerification;

public class TestConsumer {
    public static void main(String[] args) throws BadVerification {
        Consumer consumer = new Consumer.CunsumerBild("Ruslan","Pipan").setId(1).setNumber("3800686536489").setEmail("rus.pipan@ukr.net").setPass("Max-width:12").build();
        System.out.println(consumer);
    }
}
