package dao.services.interfaces;

import entety.Consumer;
import dao.repositories.interfaces.ConsumerRepository;

public interface ConsumerService extends ConsumerRepository {
    boolean removeConsumer(Consumer consumer);
    boolean addConsumer(Consumer consumer);
    boolean editConsumerEmail(Consumer consumer);
    boolean editConsumerPhone(Consumer consumer);
    boolean editConsumerPassword(Consumer consumer);
}
