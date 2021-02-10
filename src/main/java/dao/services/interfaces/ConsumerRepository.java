package dao.services.interfaces;

import exceptions.BadVerification;
import entety.Consumer;

import java.util.List;
import java.util.Set;

/**
 * The interface provides a contract between classes on how want interact with the table "consumer".
 *
 * @author Ruslan Pipan
 * @version 1.0
 * */
public interface ConsumerRepository {
    /**
     * The method gets all consumers.
     * @return List<Consumer> if all went well, else Empty List.
     * */
    List<Consumer> getConsumersList();
    /**
     * The method gets all consumers.
     * @return Set<Consumer> if all went well, else Empty Set.
     * */
    Set<Consumer> getConsumersSet();
    /**
     * This method get consumer by id.
     *
     * @return Consumer if all went well, else we obtain null.
     * @param id its consumer id.
     * */
    Consumer findConsumerById(int id);

    /**
     * This method get consumer by bank account.
     *
     * @return Consumer if all went well, else null.
     * @param banckAcc this bank account.
     * */
    Consumer findConsumerByBankAcc(long banckAcc);

    /**
     * This method get consumer by email.
     *
     * @return Consumer if all went well, else null.
     * @param email this email consumer.
     * */
    Consumer findConsumerByEmail(String email) throws BadVerification;

}
