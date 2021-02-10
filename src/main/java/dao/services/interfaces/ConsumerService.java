package dao.services.interfaces;

import entety.Consumer;

/**
 * The interface provides GRUD a contract between classes on how want interact with the table "consumer".
 * And extends {@link dao.services.interfaces.ConsumerRepository}.
 *
 * @author Ruslan Pipan
 * @version 1.1
 * */
public interface ConsumerService extends ConsumerRepository {
    /**
     * This method removed consumers from the database.
     * @return true if consumer present in the database, and if the removal was successful, else false.
     * @param consumer this consumer will be removed from the database and lost his instance.
     * */
    boolean removeConsumer(Consumer consumer);
    /**
     * This method provides added consumers in the database.
     *
     * @return true if the consumer will be added in the database, else false.
     * @param consumer this consumer will be added in the database.
     * */
    boolean addConsumer(Consumer consumer);

    /**
     * This method provides edit consumer email.
     *
     * @return true if all went well, if email has not change return false.
     * @param consumer this consumer email is changes.
     * @param email its new email.
     * */
    boolean editConsumerEmail(Consumer consumer, String email);
    /**
     * This method provides edit consumer phone number.
     *
     * @return true if all went well, if phone number has not change return false.
     * @param consumer this consumer phone number is changes.
     * @param phone its new phone number.
     * */
    boolean editConsumerPhone(Consumer consumer, String phone);
    /**
     * This method provides edit consumer password.
     *
     * @return true if all went well, if password has not change return false.
     * @param consumer this consumer password is changes.
     * @param pass its new password.
     * */
    boolean editConsumerPassword(Consumer consumer, String pass);
}
