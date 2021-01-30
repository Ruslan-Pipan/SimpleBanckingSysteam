package dao.repositories.interfaces;

import exceptions.BadVerification;
import entety.Consumer;

import java.util.List;
import java.util.Set;

public interface ConsumerRepository {
    List<Consumer> getConsumersList();
    Set<Consumer> getConsumersSet();
    Consumer findConsumerById(int id);
    Consumer findConsumerByBankAcc(long banckAcc);
    Consumer findConsumerByEmail(String email) throws BadVerification;

}
